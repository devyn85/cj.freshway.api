package cjfw.wms.tm.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import cjfw.wms.tm.dto.engine.TmEngineStepDto;
import cjfw.wms.tm.dto.TmPlanEtaManualRouteResDto;
import cjfw.wms.tm.dto.TmPlanEtaVehicleReqDto;
import cjfw.wms.tm.dto.TmSetDispatchTruthCustResDto;
import cjfw.wms.tm.dto.TmVehicleDispatchInfoDto;
import cjfw.wms.tm.dto.TmVehicleInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2026. CJ Freshway Co. all rights reserved.
 * @author : ChoiHwaRang (az86067@cj.net)
 * @date : 2026.01.23
 * @description : PlanEta Manual API 응답 변환 Service (T-map 응답 처리)
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2026.01.23 ChoiHwaRang (az86067@cj.net) 생성 - TmNewEngineDataMapper에서 이관
 * 2026.01.23 ChoiHwaRang (az86067@cj.net) 다회전 지원 - RELOAD 기준 회차 분리
 * 2026.02.01 Claude 단일/다회전 로직 통합 리팩토링 </pre>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TmPlanManualResponseService {

    public static final ZoneId SEOUL_ZONE_ID = ZoneId.of("Asia/Seoul");

    /** RELOAD viaPoint ID 접두사 (TmPlanManualRequestService와 동일) */
    private static final String RELOAD_PREFIX = "RELOAD-";

    // ========================================================================
    // 통합 엔트리 메서드
    // ========================================================================

    /**
     * T-map 응답을 TmVehicleDispatchInfoDto로 변환 (단일/다회전 통합)
     *
     * @param dto T-map 응답
     * @param rounds 회차 목록 (roundSeq 정렬됨, 단일 회차면 size=1)
     * @param vehicleInfo 차량 정보
     * @param orderMap 주문 Map (미사용, 호환성 유지)
     * @param splitOrderMap 분할 주문 Map
     * @return 회차별 TmVehicleDispatchInfoDto 목록
     */
    public List<TmVehicleDispatchInfoDto> createTmapResponseUnified(
            TmPlanEtaManualRouteResDto dto,
            List<TmPlanEtaVehicleReqDto> rounds,
            TmVehicleInfoDto vehicleInfo,
            Map<String, TmSetDispatchTruthCustResDto> orderMap,
            Map<String, TmSetDispatchTruthCustResDto> splitOrderMap
    ) {
        List<TmVehicleDispatchInfoDto> results = new ArrayList<>();

        if (dto == null || dto.getFeatures() == null || dto.getFeatures().isEmpty()) {
            log.warn("T-map 응답이 비어있습니다.");
            return results;
        }

        // 1. 통합 feature 파싱 (Point + LineString 병합, distance/time 포함)
        Map<String, TmEngineStepDto> stepMap = parseAllFeatures(dto, splitOrderMap);
        log.info("전체 feature 파싱 완료: stepCount={}", stepMap.size());

        // 2. 회차별 분리
        Map<Integer, List<TmEngineStepDto>> stepsByRound;
        if (rounds.size() == 1) {
            // 단일 회차: start/end를 T-map S/E에서 직접 사용
            stepsByRound = createSingleRoundMap(stepMap, dto, vehicleInfo.getRoundSeq());
        } else {
            // 다회전: RELOAD 기준 분리
            stepsByRound = splitStepsByReload(new ArrayList<>(stepMap.values()), rounds);
        }
        log.info("회차별 분리 완료: rounds={}", stepsByRound.keySet());

        // 3. 회차별 geometry 분리
        Map<Integer, List<Object>> geometryByRound = splitGeometryByReload(dto, rounds);

        // 4. 각 회차별 DTO 생성
        for (TmPlanEtaVehicleReqDto round : rounds) {
            int roundSeq = round.getRoundSeq() == null ? 1 : round.getRoundSeq();
            List<TmEngineStepDto> roundSteps = stepsByRound.get(roundSeq);

            if (roundSteps == null || roundSteps.isEmpty()) {
                log.warn("roundSeq={}에 해당하는 step이 없습니다.", roundSeq);
                continue;
            }

            // 4-1. 다회전인 경우 start/end step 추가
            if (rounds.size() > 1) {
                addStartEndStepsForRound(roundSteps, vehicleInfo, stepsByRound, roundSeq, rounds.size());
                // RELOAD step 제거 (회차 구분자로만 사용)
                roundSteps.removeIf(step -> step.getId() != null && step.getId().startsWith(RELOAD_PREFIX));
            }

            // 4-2. step ID/type 파싱 및 arrival 시간 포맷팅
            formatStepDetails(roundSteps);

            // 4-3. 누적값 계산
            calculateCumulativeValues(roundSteps);

            // 4-4. 주문 집계
            OrderSummary summary = calculateOrderSummary(roundSteps);

            // 4-5. vehicle DTO 생성
            List<Object> roundGeometry = geometryByRound.get(roundSeq);
            if (roundGeometry == null && rounds.size() == 1) {
                // 단일 회차: 전체 geometry 사용
                roundGeometry = new ArrayList<>(getAllLineStringGeometries(dto));
            }

            TmVehicleDispatchInfoDto vehicleDto = createVehicleDto(
                    roundSteps, round, vehicleInfo, summary, roundGeometry,
                    rounds.size() == 1 ? dto : null);

            results.add(vehicleDto);
            log.info("roundSeq={} vehicle 생성 완료: steps={}", roundSeq, roundSteps.size());
        }

        return results;
    }

    // ========================================================================
    // 통합 파싱 메서드
    // ========================================================================

    /**
     * T-map feature를 step으로 파싱 (Point + LineString 병합)
     * - Point: viaPoint 좌표 + 주문 정보
     * - LineString: geometry + stepDistance + stepDuration
     * - 동일 viaPointId의 Point/LineString을 병합하여 모든 정보 유지
     */
    private Map<String, TmEngineStepDto> parseAllFeatures(
            TmPlanEtaManualRouteResDto dto,
            Map<String, TmSetDispatchTruthCustResDto> splitOrderMap
    ) {
        Map<String, TmEngineStepDto> stepMap = new LinkedHashMap<>();

        for (TmPlanEtaManualRouteResDto.Feature feature : dto.getFeatures()) {
            if (feature.getGeometry() == null) continue;

            String pointType = feature.getProperties().getPointType();
            String viaPointId = feature.getProperties().getViaPointId();
            String arrival = String.valueOf(parseArrival(feature.getProperties().getArriveTime()));

            // T-map의 시작점 (S) - 별도 저장
            if ("S".equals(pointType)) {
                TmEngineStepDto startStep = stepMap.computeIfAbsent("TMAP_START", k -> new TmEngineStepDto());
                startStep.setId("TMAP_START");
                startStep.setType("start");
                startStep.setArrival(arrival);
                startStep.setExpectedArrivalTime(feature.getProperties().getArriveTime());
                if ("Point".equals(feature.getGeometry().getType())) {
                    startStep.setLocation((List<Double>) feature.getGeometry().getCoordinates());
                }
                continue;
            }

            // T-map의 종료점 (E) - 별도 저장
            if ("E".equals(pointType)) {
                TmEngineStepDto endStep = stepMap.computeIfAbsent("TMAP_END", k -> new TmEngineStepDto());
                endStep.setId("TMAP_END");
                endStep.setType("end");
                endStep.setArrival(arrival);
                endStep.setExpectedArrivalTime(feature.getProperties().getArriveTime());
                if ("Point".equals(feature.getGeometry().getType())) {
                    endStep.setLocation((List<Double>) feature.getGeometry().getCoordinates());
                } else if ("LineString".equals(feature.getGeometry().getType())) {
                    endStep.setGeometry(encode(feature.getGeometry().getCoordinates()));
                }
                continue;
            }

            // 일반 viaPoint (N 제외)
            if ("N".equals(pointType) || ObjectUtils.isEmpty(viaPointId)) {
                continue;
            }

            TmEngineStepDto stepDto = stepMap.computeIfAbsent(viaPointId, k -> new TmEngineStepDto());
            stepDto.setId(viaPointId);
            stepDto.setArrival(arrival);
            // completeTime 저장 (도착시간 + service 시간)
            String completeTime = feature.getProperties().getCompleteTime();
            if (completeTime != null && !completeTime.isEmpty()) {
                stepDto.setCompleteTime(String.valueOf(parseArrival(completeTime)));
            }

            // LineString 처리: geometry + distance + time
            if ("LineString".equals(feature.getGeometry().getType())) {
                stepDto.setGeometry(encode(feature.getGeometry().getCoordinates()));
                // 핵심: LineString에서 distance/time 설정 (버그 수정)
                stepDto.setStepDistance(feature.getProperties().getDistance());
                stepDto.setStepDuration(feature.getProperties().getTime());
            }
            // Point 처리: 좌표 + 주문 정보
            else if ("Point".equals(feature.getGeometry().getType())) {
                stepDto.setLocation((List<Double>) feature.getGeometry().getCoordinates());

                // RELOAD step 처리
                if (viaPointId.startsWith(RELOAD_PREFIX)) {
                    stepDto.setType("reload");
                    int reloadRoundSeq = Integer.parseInt(viaPointId.substring(RELOAD_PREFIX.length()));
                    stepDto.setRoundSeq(reloadRoundSeq);
                } else {
                    // 일반 주문 step: 주문 정보 설정
                    TmSetDispatchTruthCustResDto orderInfo = splitOrderMap.get(viaPointId);
                    if (orderInfo != null) {
                        populateStepFromOrder(stepDto, orderInfo, feature);
                    }
                }
            }
        }

        return stepMap;
    }

    /**
     * 단일 회차용 Map 생성 (T-map S/E를 start/end로 사용)
     */
    private Map<Integer, List<TmEngineStepDto>> createSingleRoundMap(
            Map<String, TmEngineStepDto> stepMap,
            TmPlanEtaManualRouteResDto dto,
            Integer roundSeq
    ) {
        int actualRoundSeq = roundSeq == null ? 1 : roundSeq;
        List<TmEngineStepDto> steps = new ArrayList<>();

        // T-map start를 그대로 사용
        TmEngineStepDto startStep = stepMap.get("TMAP_START");
        if (startStep != null) {
            startStep.setRoundSeq(actualRoundSeq);
            steps.add(startStep);
        }

        // 일반 viaPoint들 추가 (TMAP_START, TMAP_END 제외)
        for (Map.Entry<String, TmEngineStepDto> entry : stepMap.entrySet()) {
            if (!"TMAP_START".equals(entry.getKey()) && !"TMAP_END".equals(entry.getKey())) {
                TmEngineStepDto step = entry.getValue();
                step.setRoundSeq(actualRoundSeq);
                steps.add(step);
            }
        }

        // T-map end를 그대로 사용
        TmEngineStepDto endStep = stepMap.get("TMAP_END");
        if (endStep != null) {
            endStep.setRoundSeq(actualRoundSeq);
            steps.add(endStep);
        }

        Map<Integer, List<TmEngineStepDto>> result = new LinkedHashMap<>();
        result.put(actualRoundSeq, steps);
        return result;
    }

    // ========================================================================
    // 공통 메서드 (중복 제거)
    // ========================================================================

    /**
     * 차량 기본 정보 설정
     */
    private void setVehicleBaseInfo(TmVehicleDispatchInfoDto vehicleDto, TmVehicleInfoDto vehicleInfo) {
        String carCapacity = vehicleInfo.getCarCapacity();
        if (carCapacity != null) {
            carCapacity += "톤";
        }
        if (carCapacity == null && vehicleInfo.getMaxWeight() != null) {
            carCapacity = vehicleInfo.getMaxWeight() / 1000 + "톤";
        }

        vehicleDto.setCarno(vehicleInfo.getCarno());
        vehicleDto.setMaxCbm(vehicleInfo.getMaxCube() == null ? "3000" : vehicleInfo.getMaxCube().toString());
        vehicleDto.setMaxWeight(vehicleInfo.getMaxWeight() == null ? "3000" : vehicleInfo.getMaxWeight().toString());
        vehicleDto.setContractType(vehicleInfo.getContractType());
        vehicleDto.setCarCapacity(carCapacity);
        vehicleDto.setOutGroupCd(vehicleInfo.getOutGroupCd());
        vehicleDto.setVehicleType(vehicleInfo.getContractTypeNm());
        vehicleDto.setDrivername(vehicleInfo.getDrivername());
        vehicleDto.setPhone1(vehicleInfo.getPhone1());
    }

    /**
     * step의 구간 값(stepDistance, stepDuration)을 누적 값(distance, duration)으로 변환
     */
    private void calculateCumulativeValues(List<TmEngineStepDto> steps) {
        int cumulativeDistance = 0;
        int cumulativeDuration = 0;

        for (TmEngineStepDto step : steps) {
            if ("start".equals(step.getType())) {
                step.setDistance("0");
                step.setDuration("0");
                continue;
            }
            if ("end".equals(step.getType())) {
                step.setDistance(String.valueOf(cumulativeDistance));
                step.setDuration(String.valueOf(cumulativeDuration));
                continue;
            }

            // 구간 값을 누적
            if (step.getStepDistance() != null && !step.getStepDistance().isEmpty()) {
                try {
                    cumulativeDistance += Integer.parseInt(step.getStepDistance());
                } catch (NumberFormatException ignored) {}
            }
            if (step.getStepDuration() != null && !step.getStepDuration().isEmpty()) {
                try {
                    cumulativeDuration += Integer.parseInt(step.getStepDuration());
                } catch (NumberFormatException ignored) {}
            }
            step.setDistance(String.valueOf(cumulativeDistance));
            step.setDuration(String.valueOf(cumulativeDuration));
        }
    }

    /**
     * Step ID/type 파싱 및 arrival 시간 포맷팅
     */
    private void formatStepDetails(List<TmEngineStepDto> steps) {
        for (TmEngineStepDto step : steps) {
            // ID 파싱: "C001-delivery-0" → id=C001, type=delivery, splitDeliverySeq=0
            if (step.getId() != null
                    && !"start".equals(step.getType())
                    && !"end".equals(step.getType())
                    && !step.getId().startsWith(RELOAD_PREFIX)
                    && !"TMAP_START".equals(step.getId())
                    && !"TMAP_END".equals(step.getId())) {

                String id = step.getId();
                int lastDashIndex = id.lastIndexOf('-');
                if (lastDashIndex > 0) {
                    int secondToLastDashIndex = id.lastIndexOf('-', lastDashIndex - 1);
                    if (secondToLastDashIndex > 0) {
                        String custKey = id.substring(0, secondToLastDashIndex);
                        String type = id.substring(secondToLastDashIndex + 1, lastDashIndex);
                        String splitDeliverySeq = id.substring(lastDashIndex + 1);

                        step.setSplitDeliverySeq(splitDeliverySeq);
                        step.setDescription(custKey + "-" + type);
                        step.setId(custKey);
                        step.setType(type);
                    }
                }

                step.createUniqueId();
            }

            // arrival → expectedArrivalTime (HH:mm 형식)
            if (step.getArrival() != null && !step.getArrival().isEmpty()) {
                try {
                    String time = Instant.ofEpochSecond(Long.parseLong(step.getArrival()))
                            .atZone(SEOUL_ZONE_ID)
                            .format(DateTimeFormatter.ofPattern("HH:mm"));
                    step.setExpectedArrivalTime(time);
                } catch (NumberFormatException e) {
                    log.warn("arrival 시간 포맷팅 실패: {}", step.getArrival());
                }
            }
        }
    }

    /**
     * 주문 집계 결과
     */
    private static class OrderSummary {
        double totalCbm = 0.0;
        double totalWeight = 0.0;
        int orderCount = 0;
    }

    /**
     * 주문 집계 (CBM, Weight, Count)
     */
    private OrderSummary calculateOrderSummary(List<TmEngineStepDto> steps) {
        OrderSummary summary = new OrderSummary();

        for (TmEngineStepDto step : steps) {
            if ("start".equals(step.getType()) || "end".equals(step.getType())) {
                continue;
            }
            if (step.getCube() != null && step.getWeight() != null) {
                try {
                    summary.totalCbm += Double.parseDouble(step.getCube());
                    summary.totalWeight += Double.parseDouble(step.getWeight());
                    summary.orderCount++;
                } catch (NumberFormatException e) {
                    log.warn("주문 집계 실패: cube={}, weight={}", step.getCube(), step.getWeight());
                }
            }
        }

        return summary;
    }

    /**
     * TmVehicleDispatchInfoDto 생성
     */
    private TmVehicleDispatchInfoDto createVehicleDto(
            List<TmEngineStepDto> steps,
            TmPlanEtaVehicleReqDto round,
            TmVehicleInfoDto vehicleInfo,
            OrderSummary summary,
            List<Object> geometry,
            TmPlanEtaManualRouteResDto dto
    ) {
        TmVehicleDispatchInfoDto vehicleDto = new TmVehicleDispatchInfoDto();

        // 차량 기본 정보 설정
        setVehicleBaseInfo(vehicleDto, vehicleInfo);

        // 주문 집계 설정
        vehicleDto.setLoadedCbm(Double.toString(summary.totalCbm));
        vehicleDto.setLoadedWeight(Double.toString(summary.totalWeight));
        vehicleDto.setOrderCount(summary.orderCount);

        // 누적값에서 총 거리/시간 계산
        int totalDistance = 0;
        int totalDuration = 0;

        // end step에서 최종 누적값 가져오기
        TmEngineStepDto endStep = steps.stream()
                .filter(s -> "end".equals(s.getType()))
                .findFirst()
                .orElse(null);

        if (endStep != null && endStep.getDistance() != null && endStep.getDuration() != null) {
            try {
                totalDistance = Integer.parseInt(endStep.getDistance());
                totalDuration = Integer.parseInt(endStep.getDuration());
            } catch (NumberFormatException ignored) {}
        }

        // 단일 회차이고 T-map 전체 값이 있는 경우 그 값 사용
        if (dto != null && dto.getProperties() != null) {
            try {
                totalDistance = (int) Double.parseDouble(dto.getProperties().getTotalDistance());
                totalDuration = Integer.parseInt(dto.getProperties().getTotalTime());
            } catch (NumberFormatException ignored) {}
        }

        vehicleDto.setDistance(totalDistance);
        vehicleDto.setDuration(totalDuration);
        vehicleDto.setTotalDistanceKm(totalDistance / 1000.0);
        vehicleDto.setTotalTimeMinutes(totalDuration / 60);
        vehicleDto.setTotalTimeDisplay(formatTimeDisplay(vehicleDto.getTotalTimeMinutes()));

        // steps, geometry, roundSeq 설정
        vehicleDto.setSteps(steps);
        vehicleDto.setGeometry(geometry != null ? encode(geometry) : "");
        vehicleDto.setRoundSeq(round.getRoundSeq() == null ? 1 : round.getRoundSeq());
        vehicleDto.createUniqueId();

        return vehicleDto;
    }

    // ========================================================================
    // 주문 정보 설정
    // ========================================================================

    /**
     * 주문 정보를 step에 설정
     */
    private void populateStepFromOrder(
            TmEngineStepDto stepDto,
            TmSetDispatchTruthCustResDto orderInfo,
            TmPlanEtaManualRouteResDto.Feature feature
    ) {
        stepDto.setClaimYn(orderInfo.getClaimYn());
        stepDto.setCustAddress(orderInfo.getAddress());
        stepDto.setCustName(orderInfo.getCustName());
        stepDto.setExpectedArrivalTime(feature.getProperties().getArriveTime());
        stepDto.setWeight(orderInfo.getWeight());
        stepDto.setCube(orderInfo.getCube());
        stepDto.setOrderQty(orderInfo.getOrderQty());
        stepDto.setSlipline(orderInfo.getSlipline());
        stepDto.setSlipdt(orderInfo.getSlipdt());
        stepDto.setSlipno(orderInfo.getSlipno());
        stepDto.setTmDeliveryType(orderInfo.getTmDeliveryType());
        stepDto.setSplitDeliveryYn(orderInfo.getSplitDeliveryYn());
        stepDto.setReqdlvtime1From(orderInfo.getReqdlvtime1From());
        stepDto.setReqdlvtime1To(orderInfo.getReqdlvtime1To());
        if (feature.getProperties().getDeliveryTime() != null) {
            stepDto.setService(Integer.parseInt(feature.getProperties().getDeliveryTime()));
        }
        stepDto.setFaceInspect(orderInfo.getFaceInspect());
        stepDto.setReturnYn(orderInfo.getReturnYn());
        stepDto.setStorerkey(orderInfo.getStorerkey());
        stepDto.setCustType(orderInfo.getCustType());
        stepDto.setPasswordType(orderInfo.getPasswordType());
        stepDto.setPasswordTypeCd(orderInfo.getPasswordTypeCd());
        stepDto.setDefCarno(orderInfo.getDefCarno());
        stepDto.setCarno(orderInfo.getCarno());
        stepDto.setPop(orderInfo.getPop());
        stepDto.setDefPop(orderInfo.getDefPop());
        stepDto.setBasePop(orderInfo.getBasePop());
        stepDto.setSplitDeliverySeq(orderInfo.getSplitDeliverySeq());
        stepDto.setSplitItems(orderInfo.getSplitItems());
    }

    // ========================================================================
    // 다회전 분리 관련 메서드
    // ========================================================================

    /**
     * RELOAD 기준으로 step을 회차별로 분리
     */
    private Map<Integer, List<TmEngineStepDto>> splitStepsByReload(
            List<TmEngineStepDto> allSteps,
            List<TmPlanEtaVehicleReqDto> rounds
    ) {
        Map<Integer, List<TmEngineStepDto>> stepsByRound = new LinkedHashMap<>();

        int firstRoundSeq = rounds.get(0).getRoundSeq() == null ? 1 : rounds.get(0).getRoundSeq();
        int currentRound = firstRoundSeq;
        List<TmEngineStepDto> currentRoundSteps = new ArrayList<>();

        for (TmEngineStepDto step : allSteps) {
            // T-map 자체 시작/종료점은 건너뜀
            if ("TMAP_START".equals(step.getId()) || "TMAP_END".equals(step.getId())) {
                continue;
            }

            // RELOAD step 발견 시 회차 전환
            if ("reload".equals(step.getType())) {
                if (!currentRoundSteps.isEmpty()) {
                    stepsByRound.put(currentRound, new ArrayList<>(currentRoundSteps));
                }

                currentRound = step.getRoundSeq();
                currentRoundSteps = new ArrayList<>();
                currentRoundSteps.add(step);
            } else {
                step.setRoundSeq(currentRound);
                currentRoundSteps.add(step);
            }
        }

        if (!currentRoundSteps.isEmpty()) {
            stepsByRound.put(currentRound, currentRoundSteps);
        }

        return stepsByRound;
    }

    /**
     * 회차별 geometry(LineString) 분리
     */
    private Map<Integer, List<Object>> splitGeometryByReload(
            TmPlanEtaManualRouteResDto dto,
            List<TmPlanEtaVehicleReqDto> rounds
    ) {
        Map<Integer, List<Object>> geometryByRound = new LinkedHashMap<>();

        int firstRoundSeq = rounds.get(0).getRoundSeq() == null ? 1 : rounds.get(0).getRoundSeq();
        int currentRound = firstRoundSeq;
        List<Object> currentGeometry = new ArrayList<>();

        for (TmPlanEtaManualRouteResDto.Feature feature : dto.getFeatures()) {
            if (feature.getGeometry() == null) continue;

            String viaPointId = feature.getProperties().getViaPointId();

            if (viaPointId != null && viaPointId.startsWith(RELOAD_PREFIX)) {
                if (!currentGeometry.isEmpty()) {
                    geometryByRound.put(currentRound, new ArrayList<>(currentGeometry));
                }
                currentRound = Integer.parseInt(viaPointId.substring(RELOAD_PREFIX.length()));
                currentGeometry = new ArrayList<>();
            }

            if ("LineString".equals(feature.getGeometry().getType())) {
                @SuppressWarnings("unchecked")
                List<Double[]> coordinates = (List<Double[]>) feature.getGeometry().getCoordinates();
                if (coordinates != null) {
                    currentGeometry.addAll(coordinates);
                }
            }
        }

        if (!currentGeometry.isEmpty()) {
            geometryByRound.put(currentRound, currentGeometry);
        }

        return geometryByRound;
    }

    /**
     * 회차별 start/end step 추가 (다회전용)
     */
    private void addStartEndStepsForRound(
            List<TmEngineStepDto> roundSteps,
            TmVehicleInfoDto vehicleInfo,
            Map<Integer, List<TmEngineStepDto>> stepsByRound,
            int roundSeq,
            int totalRounds
    ) {
        if (roundSteps.isEmpty()) return;

        List<Double> centerLocation = Arrays.asList(
                vehicleInfo.getLongitude() == null ? 126.9780 : vehicleInfo.getLongitude(),
                vehicleInfo.getLatitude() == null ? 37.5665 : vehicleInfo.getLatitude()
        );

        // === START STEP 생성 ===
        TmEngineStepDto startStep = new TmEngineStepDto();
        startStep.setType("start");
        startStep.setRoundSeq(roundSeq);
        startStep.setLocation(centerLocation);

        TmEngineStepDto firstStep = roundSteps.get(0);
        if ("reload".equals(firstStep.getType())) {
            startStep.setArrival(firstStep.getArrival());
            startStep.setLocation(firstStep.getLocation() != null ? firstStep.getLocation() : centerLocation);
        } else {
            startStep.setArrival(firstStep.getArrival());
        }

        roundSteps.add(0, startStep);

        // === END STEP 생성 ===
        TmEngineStepDto endStep = new TmEngineStepDto();
        endStep.setType("end");
        endStep.setRoundSeq(roundSeq);

        Integer nextRoundSeq = roundSeq + 1;
        List<TmEngineStepDto> nextRoundSteps = stepsByRound.get(nextRoundSeq);

        if (nextRoundSteps != null && !nextRoundSteps.isEmpty()) {
            TmEngineStepDto nextReload = nextRoundSteps.stream()
                    .filter(s -> "reload".equals(s.getType()))
                    .findFirst()
                    .orElse(null);

            if (nextReload != null) {
                endStep.setLocation(nextReload.getLocation() != null ? nextReload.getLocation() : centerLocation);
                endStep.setArrival(nextReload.getArrival());
            } else {
                setEndStepFromLastOrder(endStep, roundSteps, centerLocation);
            }
        } else {
            setEndStepFromLastOrder(endStep, roundSteps, centerLocation);
        }

        roundSteps.add(endStep);
    }

    /**
     * 마지막 주문 정보로 end step 설정
     * - completeTime 사용 (도착시간 + service 시간)
     * - completeTime이 없으면 arrival 사용 (fallback)
     */
    private void setEndStepFromLastOrder(
            TmEngineStepDto endStep,
            List<TmEngineStepDto> roundSteps,
            List<Double> centerLocation
    ) {
        TmEngineStepDto lastOrderStep = null;
        for (int i = roundSteps.size() - 1; i >= 0; i--) {
            TmEngineStepDto step = roundSteps.get(i);
            if (!"start".equals(step.getType()) && !"reload".equals(step.getType()) && step.getLocation() != null) {
                lastOrderStep = step;
                break;
            }
        }

        if (lastOrderStep != null) {
            endStep.setLocation(lastOrderStep.getLocation());
            // completeTime 사용 (도착시간 + service 시간), 없으면 arrival fallback
            String endArrival = lastOrderStep.getCompleteTime();
            if (endArrival == null || endArrival.isEmpty()) {
                endArrival = lastOrderStep.getArrival();
                log.warn("completeTime이 없어 arrival을 사용합니다: stepId={}", lastOrderStep.getId());
            }
            endStep.setArrival(endArrival);
        } else {
            endStep.setLocation(centerLocation);
        }
    }

    // ========================================================================
    // 유틸리티 메서드
    // ========================================================================

    /**
     * T-map 응답에서 모든 LineString geometry를 병합
     */
    public List<Double[]> getAllLineStringGeometries(TmPlanEtaManualRouteResDto dto) {
        List<Double[]> mergedGeometry = new ArrayList<>();

        if (dto == null || dto.getFeatures() == null || dto.getFeatures().isEmpty()) {
            return mergedGeometry;
        }

        for (TmPlanEtaManualRouteResDto.Feature feature : dto.getFeatures()) {
            if (feature.getGeometry() == null) {
                continue;
            }

            if ("LineString".equals(feature.getGeometry().getType())) {
                @SuppressWarnings("unchecked")
                List<Double[]> coordinatesLine = (List<Double[]>) feature.getGeometry().getCoordinates();
                if (coordinatesLine != null && !coordinatesLine.isEmpty()) {
                    mergedGeometry.addAll(coordinatesLine);
                }
            }
        }

        return mergedGeometry;
    }

    /**
     * 도착 시간 문자열을 epoch seconds로 변환
     */
    private long parseArrival(String arrival) {
        if (arrival == null || arrival.isEmpty()) return 0L;
        try {
            if (arrival.length() == 14) {
                LocalDateTime dateTime = LocalDateTime.parse(arrival, DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                return dateTime.atZone(SEOUL_ZONE_ID).toEpochSecond();
            }
            return Long.parseLong(arrival);
        } catch (Exception e) {
            log.warn("도착 시간 파싱 실패: {}. 0을 반환합니다.", arrival);
            return 0L;
        }
    }

    /**
     * 총 소요시간(분)을 표시용 문자열로 변환
     */
    private String formatTimeDisplay(Integer totalMinutes) {
        if (totalMinutes == null || totalMinutes <= 0) {
            return "0분";
        }
        int hours = totalMinutes / 60;
        int minutes = totalMinutes % 60;

        if (hours > 0 && minutes > 0) {
            return hours + "시간 " + minutes + "분";
        } else if (hours > 0) {
            return hours + "시간";
        } else {
            return minutes + "분";
        }
    }

    /**
     * Polyline 인코딩 처리
     */
    public static String encode(Object obj) {
        List<double[]> coords = new ArrayList<>();

        if (obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                if (o instanceof List<?>) {
                    List<?> inner = (List<?>) o;
                    coords.add(new double[]{
                            Double.parseDouble(inner.get(0).toString()),
                            Double.parseDouble(inner.get(1).toString())
                    });
                } else if (o instanceof double[]) {
                    coords.add((double[]) o);
                }
            }
        } else if (obj instanceof double[][] arr) {
            coords.addAll(Arrays.asList(arr));
        }

        StringBuilder encoded = new StringBuilder();
        long lastLat = 0, lastLng = 0;

        for (double[] point : coords) {
            long lng = Math.round(point[0] * 1e5);
            long lat = Math.round(point[1] * 1e5);
            long dLat = lat - lastLat, dLng = lng - lastLng;

            for (long num : new long[]{dLat, dLng}) {
                num <<= 1;
                if (num < 0) num = ~num;
                while (num >= 0x20) {
                    encoded.append((char) ((0x20 | (num & 0x1f)) + 63));
                    num >>= 5;
                }
                encoded.append((char) (num + 63));
            }

            lastLat = lat;
            lastLng = lng;
        }
        return encoded.toString();
    }
}
