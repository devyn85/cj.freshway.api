package cjfw.wms.tm.service;

import static cjfw.wms.tm.constant.TmConstant.CONTRACT_TYPE_TEMPORARY;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cjfw.wms.tm.dto.*;
import cjfw.wms.tm.dto.engine.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2026. CJ Freshway Co. all rights reserved.
 * @author : ChoiHwaRang (az86067@cj.net)
 * @date : 2026.01.21
 * @description : TM Engine 응답 데이터 후처리 Service (Jobs 방식)
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2026.01.21 ChoiHwaRang (az86067@cj.net) 생성 </pre>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TmPlanEngineResponseService {

    private static final DateTimeFormatter HHMM_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    public static final ZoneId SEOUL_ZONE_ID = ZoneId.of("Asia/Seoul");

    private static final double CM3_TO_M3 = 1_000_000.0;

    /**
     * 엔진 응답을 클라이언트 응답으로 변환 (List 입력)
     *
     * @param request 배차 요청 정보
     * @param engineResponse 엔진 응답
     * @param vehicles 차량 목록
     * @param orders 주문 목록
     * @param returnOrders 반품 주문 목록
     * @param splitOrderMap 분할배송 정보 맵 (없으면 null)
     * @return 배차 응답 DTO
     */
    public TmSetDispatchResDto createSuccessResponse(
            TmSetDispatchReqDto request,
            TmEngineResponseDto engineResponse,
            List<TmVehicleInfoDto> vehicles,
            List<TmSetDispatchTruthCustResDto> orders,
            List<TmSetDispatchReturnOrderResDto> returnOrders,
            Map<String, TmPlanEtaStepReqDto> splitOrderMap) {

        return doCreateResponse(
                request.getDccode(),
                request.getDeliveryDate(),
                request.getDeliveryType(),
                request.getTmDeliveryType(),
                engineResponse,
                toVehicleMap(vehicles),
                toOrderMap(orders),
                returnOrders,
                splitOrderMap
        );
    }

    /**
     * 엔진 응답을 클라이언트 응답으로 변환 (실비차 배차용 - List 입력)
     *
     * @param request ETA 최적화 요청 정보
     * @param engineResponse 엔진 응답
     * @param vehicles 차량 목록
     * @param orders 주문 목록
     * @param splitOrderMap 분할배송 정보 맵
     * @return 배차 응답 DTO
     */
    public TmSetDispatchResDto createSuccessResponse(
            TmPlanEtaOptimizeAutoReqDto request,
            TmEngineResponseDto engineResponse,
            List<TmVehicleInfoDto> vehicles,
            List<TmSetDispatchTruthCustResDto> orders,
            Map<String, TmPlanEtaStepReqDto> splitOrderMap) {

        return doCreateResponse(
                request.getDccode(),
                request.getDeliveryDate(),
                request.getTmDeliveryType(),
                request.getTmDeliveryType(),
                engineResponse,
                toVehicleMap(vehicles),
                toOrderMap(orders),
                null,
                splitOrderMap
        );
    }

    /**
     * 엔진 응답을 클라이언트 응답으로 변환 (재계산용 - Map 입력)
     *
     * @param request ETA 최적화 요청 정보
     * @param engineResponse 엔진 응답
     * @param vehicleMap 차량 맵
     * @param orderMap 주문 맵
     * @param splitOrderMap 분할배송 정보 맵
     * @return 배차 응답 DTO
     */
    public TmSetDispatchResDto createSuccessResponse(
            TmPlanEtaOptimizeAutoReqDto request,
            TmEngineResponseDto engineResponse,
            Map<String, TmVehicleInfoDto> vehicleMap,
            Map<String, TmSetDispatchTruthCustResDto> orderMap,
            Map<String, TmPlanEtaStepReqDto> splitOrderMap) {

        return doCreateResponse(
                request.getDccode(),
                request.getDeliveryDate(),
                request.getTmDeliveryType(),
                request.getTmDeliveryType(),
                engineResponse,
                vehicleMap,
                orderMap,
                null,
                splitOrderMap
        );
    }

    // ==================== Helper Methods ====================

    /**
     * 차량 List를 Map으로 변환 (carno 기준)
     */
    private Map<String, TmVehicleInfoDto> toVehicleMap(List<TmVehicleInfoDto> vehicles) {
        if (vehicles == null) return new HashMap<>();
        return vehicles.stream()
                .collect(Collectors.toMap(
                        TmVehicleInfoDto::getCarno,
                        v -> v,
                        (oldValue, newValue) -> newValue
                ));
    }

    /**
     * 주문 List를 Map으로 변환 (truthCustKey 기준)
     */
    private Map<String, TmSetDispatchTruthCustResDto> toOrderMap(List<TmSetDispatchTruthCustResDto> orders) {
        if (orders == null) return new HashMap<>();
        return orders.stream()
                .collect(Collectors.toMap(
                        TmSetDispatchTruthCustResDto::getTruthCustKey,
                        o -> o,
                        (oldValue, newValue) -> newValue
                ));
    }

    /**
     * 실패 응답 생성
     */
    public TmSetDispatchResDto createFailureResponse(String message) {
        return TmSetDispatchResDto.builder()
                .status("FAILED")
                .message(message)
                .build();
    }

    // ==================== Core Processing ====================

    /**
     * 응답 DTO 생성 (핵심 후가공 로직)
     *
     * @param dccode 센터코드
     * @param deliveryDate 배송일자
     * @param deliveryType 배송유형
     * @param tmDeliveryType TM 배송유형 (1:배송, 2:새벽, 3:조달, 4:택배, 6:회수)
     * @param engineResponse 엔진 응답
     * @param vehicleMap 차량 Map (carno 기준)
     * @param orderMap 주문 Map (truthCustKey 기준)
     * @param returnOrders 반품 주문 목록
     * @param splitOrderMap 분할배송 정보 Map
     * @return 배차 응답 DTO
     */
    private TmSetDispatchResDto doCreateResponse(
            String dccode,
            String deliveryDate,
            String deliveryType,
            String tmDeliveryType,
            TmEngineResponseDto engineResponse,
            Map<String, TmVehicleInfoDto> vehicleMap,
            Map<String, TmSetDispatchTruthCustResDto> orderMap,
            List<TmSetDispatchReturnOrderResDto> returnOrders,
            Map<String, TmPlanEtaStepReqDto> splitOrderMap) {
        try {
            // 엔진 Response 처리
            List<TmVehicleDispatchInfoDto> vehicleDispatchInfoList = engineResponse.getRoutes().stream()
                    .map(route -> processEngineRoute(route, vehicleMap, orderMap, splitOrderMap, engineResponse.isOnMaxWeight()))
                    .filter(Objects::nonNull)
                    .toList();

            // 미배정 주문 처리
            List<TmSetDispatchUnassignedOrderResDto> unassignedOrders = processUnassignedOrders(
                    engineResponse.getUnassigned(), orderMap, splitOrderMap);

            // 후처리 (다회전 분리)
            List<TmVehicleDispatchInfoDto> finalVehicleList = postProcessRoutes(vehicleDispatchInfoList);

            // unique id 할당
            finalVehicleList.forEach(vehicle -> {
                vehicle.createUniqueId();
                vehicle.getSteps().forEach(TmEngineStepDto::createUniqueId);
            });

            if (returnOrders != null) {
                returnOrders.forEach(TmSetDispatchReturnOrderResDto::createUniqueId);
            }

            String resultMessage = "배차가 성공적으로 완료되었습니다.";
            if (finalVehicleList.isEmpty()) {
                resultMessage = "설정된 조건으로 인해 배차가 되지 않아 미배차 처리되었습니다.";
            }

            return TmSetDispatchResDto.builder()
                    .dccode(dccode)
                    .deliveryDate(deliveryDate)
                    .deliveryType(deliveryType)
                    .tmDeliveryType(tmDeliveryType)
                    .status("SUCCESS")
                    .message(resultMessage)
                    .summary(engineResponse.getSummary())
                    .vehicles(finalVehicleList)
                    .unassignedOrders(unassignedOrders)
                    .returnOrders(returnOrders)
                    .build();

        } catch (Exception e) {
            log.error("응답 생성 중 오류: {}", e.getMessage(), e);
            return createFailureResponse("응답 생성 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    /**
     * 엔진 Route를 차량 배차 정보로 변환
     */
    private TmVehicleDispatchInfoDto processEngineRoute(
            TmEngineRouteDto engineRoute,
            Map<String, TmVehicleInfoDto> vehicleMap,
            Map<String, TmSetDispatchTruthCustResDto> orderMap,
            Map<String, TmPlanEtaStepReqDto> splitOrderMap,
            boolean isOnMaxWeight) {

        TmVehicleInfoDto vehicleInfo = vehicleMap.get(engineRoute.getCarNo());
        if (vehicleInfo == null) {
            log.warn("차량 정보를 찾을 수 없습니다: {}", engineRoute.getCarNo());
            return null;
        }

        // step 데이터 보강 및 집계
        int orderCount = 0;
        int prevDistance = 0;
        double totalWeight = 0.0;
        double totalCube = 0.0;

        for (TmEngineStepDto step : engineRoute.getSteps()) {
            enrichStepDto(step, orderMap, splitOrderMap, prevDistance);

            if (step.getDistance() != null && !step.getDistance().isEmpty()) {
                prevDistance = Integer.parseInt(step.getDistance());
            }

            // job 타입인 경우 집계 (Jobs에서는 "job" 타입)
            if ("job".equals(step.getType())) {
                orderCount++;
                String weight = Optional.ofNullable(step.getWeight()).orElse("0");
                String cube = Optional.ofNullable(step.getCube()).orElse("0");
                if (weight.matches("-?\\d+(\\.\\d+)?")) {
                    totalWeight += Double.parseDouble(weight);
                }
                if (cube.matches("-?\\d+(\\.\\d+)?")) {
                    totalCube += Double.parseDouble(cube);
                }
            }
        }

        // 실비차(TEMPORARY) 분기 처리
        if (CONTRACT_TYPE_TEMPORARY.equals(vehicleInfo.getContractType())) {
            return buildTemporaryVehicleDto(engineRoute, vehicleInfo, orderCount, totalWeight, totalCube);
        } else {
            return buildRegularVehicleDto(engineRoute, vehicleInfo, orderCount, totalWeight, totalCube, isOnMaxWeight);
        }
    }

    /**
     * 실비차 차량 DTO 생성
     * - 용량 정보 제한적 설정
     * - 사용률(weightUsagePercent, cbmUsagePercent) 미계산
     * - 운전자 정보(drivername, phone1, carCapacity) 미설정
     */
    private TmVehicleDispatchInfoDto buildTemporaryVehicleDto(
            TmEngineRouteDto engineRoute,
            TmVehicleInfoDto vehicleInfo,
            int orderCount,
            double totalWeight,
            double totalCube) {

        return TmVehicleDispatchInfoDto.builder()
                .carno(engineRoute.getCarNo())
                .contractType(vehicleInfo.getContractType())
                .vehicleType(vehicleInfo.getContractTypeNm())
                .outGroupCd(vehicleInfo.getOutGroupCd())
                .loadedWeight(Double.toString(totalWeight))
                .loadedCbm(Double.toString(totalCube))
                .orderCount(orderCount)
                .totalTimeMinutes(Optional.ofNullable(engineRoute.getDuration()).orElse(0) / 60)
                .totalTimeDisplay(formatTimeDisplay(Optional.ofNullable(engineRoute.getDuration()).orElse(0) / 60))
                .totalDistanceKm(Optional.ofNullable(engineRoute.getDistance()).orElse(0) / 1000.0)
                .steps(engineRoute.getSteps())
                .cost(engineRoute.getCost())
                .setup(engineRoute.getSetup())
                .duration(engineRoute.getDuration())
                .waitingTime(engineRoute.getWaitingTime())
                .priority(engineRoute.getPriority())
                .violations(engineRoute.getViolations())
                .description(engineRoute.getDescription())
                .geometry(engineRoute.getGeometry())
                .distance(engineRoute.getDistance())
                .roundSeq(engineRoute.getRoundSeq())
                .maxWeight(String.valueOf(vehicleInfo.getMaxWeight()))
                .maxCbm(String.valueOf(Optional.ofNullable(vehicleInfo.getMaxCube())
                        .map(cube -> cube / CM3_TO_M3)
                        .orElse(0.0)))
                .maxLanding(String.valueOf(vehicleInfo.getMaxLanding()))
                .build();
    }

    /**
     * 일반 차량 DTO 생성
     * - 전체 용량 정보 설정
     * - 사용률 계산 포함
     * - 운전자 정보 포함
     */
    private TmVehicleDispatchInfoDto buildRegularVehicleDto(
            TmEngineRouteDto engineRoute,
            TmVehicleInfoDto vehicleInfo,
            int orderCount,
            double totalWeight,
            double totalCube,
            boolean isOnMaxWeight) {

        String minWeight = Optional.ofNullable(vehicleInfo.getMinWeight()).map(Object::toString).orElse("2500");
        String maxWeight = Optional.ofNullable(vehicleInfo.getMaxWeight()).map(Object::toString).orElse("5000");
        double maxCbmM3 = Optional.ofNullable(vehicleInfo.getMaxCube())
                .map(cube -> cube / CM3_TO_M3)
                .orElse(50.0);
        String maxCbm = String.valueOf(maxCbmM3);
        String maxLanding = Optional.ofNullable(vehicleInfo.getMaxLanding()).map(Object::toString).orElse("10");

        if (!isOnMaxWeight) {
            maxWeight = minWeight;
        }

        String carCapacity = vehicleInfo.getCarCapacity();
        if (carCapacity != null) {
            carCapacity += "톤";
        } else if (vehicleInfo.getMaxWeight() != null) {
            carCapacity = vehicleInfo.getMaxWeight() / 1000 + "톤";
        }

        int cbmUsagePercent = (int) ((totalCube * 100) / Math.max(1, maxCbmM3));

        return TmVehicleDispatchInfoDto.builder()
                .carno(engineRoute.getCarNo())
                .outGroupCd(vehicleInfo.getOutGroupCd())
                .vehicleType(vehicleInfo.getContractTypeNm())
                .contractType(vehicleInfo.getContractType())
                .drivername(vehicleInfo.getDrivername())
                .phone1(vehicleInfo.getPhone1())
                .carCapacity(carCapacity)
                .maxWeight(maxWeight)
                .maxCbm(maxCbm)
                .maxLanding(maxLanding)
                .loadedWeight(Double.toString(totalWeight))
                .loadedCbm(Double.toString(totalCube))
                .weightUsagePercent((int) ((totalWeight * 100) / Math.max(1, Double.parseDouble(maxWeight))))
                .cbmUsagePercent(cbmUsagePercent)
                .orderCount(orderCount)
                .totalTimeMinutes(Optional.ofNullable(engineRoute.getDuration()).orElse(0) / 60)
                .totalTimeDisplay(formatTimeDisplay(Optional.ofNullable(engineRoute.getDuration()).orElse(0) / 60))
                .totalDistanceKm(Optional.ofNullable(engineRoute.getDistance()).orElse(0) / 1000.0)
                .steps(engineRoute.getSteps())
                .cost(engineRoute.getCost())
                .setup(engineRoute.getSetup())
                .duration(engineRoute.getDuration())
                .waitingTime(engineRoute.getWaitingTime())
                .priority(engineRoute.getPriority())
                .violations(engineRoute.getViolations())
                .description(engineRoute.getDescription())
                .geometry(engineRoute.getGeometry())
                .distance(engineRoute.getDistance())
                .roundSeq(engineRoute.getRoundSeq())
                .build();
    }

    /**
     * Step 데이터 보강 (주문 정보 매핑)
     */
    private void enrichStepDto(
            TmEngineStepDto step,
            Map<String, TmSetDispatchTruthCustResDto> orderMap,
            Map<String, TmPlanEtaStepReqDto> splitOrderMap,
            Integer prevDistance) {

        if (step.getId() == null) return;

        String originalId = step.getId();
        TmPlanEtaStepReqDto splitData = (splitOrderMap != null) ? splitOrderMap.get(originalId) : null;

        // 분할배송 데이터 처리
        if (splitData != null) {
            int lastIndex = originalId.lastIndexOf("-");
            if (lastIndex != -1) {
                originalId = originalId.substring(0, lastIndex);
            }
            step.setId(originalId);
            step.setSplitDeliveryYn(splitData.getSplitDeliveryYn());
            step.setSplitDeliverySeq(splitData.getSplitDeliverySeq());
            step.setWeight(splitData.getWeight());
            step.setCube(convertCubeToM3(splitData.getCube()));
            step.setOrderQty(splitData.getOrderQty());
            step.setSlipno(splitData.getSlipno());
            step.setSlipline(splitData.getSlipline());
            step.setSlipdt(splitData.getSlipdt());
        }

        TmSetDispatchTruthCustResDto truthCustInfo = orderMap.get(originalId);
        if (truthCustInfo == null) return;

        // arrival 처리 (ETA)
        long arrivalTimestamp = parseArrival(step.getArrival());
        step.setArrival(String.valueOf(arrivalTimestamp));
        step.setExpectedArrivalTime(Instant.ofEpochSecond(arrivalTimestamp)
                .atZone(SEOUL_ZONE_ID).format(HHMM_FORMATTER));

        // 주문 정보 매핑
        step.setCustAddress(truthCustInfo.getAddress());
        step.setCustName(truthCustInfo.getCustName());
        step.setStorerkey(truthCustInfo.getStorerkey());
        step.setCustType(truthCustInfo.getCustType());
        step.setClaimYn(truthCustInfo.getClaimYn());
        step.setFaceInspect(truthCustInfo.getFaceInspect());
        step.setReqdlvtime1From(truthCustInfo.getReqdlvtime1From());
        step.setReqdlvtime1To(truthCustInfo.getReqdlvtime1To());
        step.setKeyCustType(truthCustInfo.getPasswordType());
        step.setSpecialConditionYn(hasSpecialConditions(truthCustInfo) ? "Y" : "N");
        step.setDefCarno(truthCustInfo.getDefCarno());
        step.setCarno(truthCustInfo.getCarno());
        step.setReturnYn(truthCustInfo.getReturnYn());
        step.setTmDeliveryType(truthCustInfo.getTmDeliveryType());
        step.setPasswordType(truthCustInfo.getPasswordType());
        step.setPasswordTypeCd(truthCustInfo.getPasswordTypeCd());
        step.setPop(truthCustInfo.getPop());
        step.setDefPop(truthCustInfo.getDefPop());
        step.setBasePop(truthCustInfo.getBasePop());

        // 분할 데이터가 없을 때만 주문 정보로 채움 (기존 값이 있으면 보존 - ADJUST 모드 대응)
        if (splitData == null) {
            if (step.getOrderQty() == null) {
                step.setOrderQty(truthCustInfo.getOrderQty());
            }
            if (step.getCube() == null) {
                step.setCube(convertCubeToM3(truthCustInfo.getCube()));
            }
            if (step.getWeight() == null) {
                step.setWeight(truthCustInfo.getWeight());
            }
            if (step.getSplitDeliverySeq() == null) {
                step.setSplitDeliverySeq(truthCustInfo.getSplitDeliverySeq());
            }
            if (step.getSplitDeliveryYn() == null) {
                step.setSplitDeliveryYn(truthCustInfo.getSplitDeliveryYn());
            }
            if (step.getSlipline() == null) {
                step.setSlipline(truthCustInfo.getSlipline());
            }
            if (step.getSlipno() == null) {
                step.setSlipno(truthCustInfo.getSlipno());
            }
            if (step.getSlipdt() == null) {
                step.setSlipdt(truthCustInfo.getSlipdt());
            }
        }

        // stepDistance 계산
        if (step.getDistance() != null && !step.getDistance().isEmpty()) {
            int stepDistance = Integer.parseInt(step.getDistance()) - prevDistance;
            step.setStepDistance(Integer.toString(stepDistance));
        }
    }

    /**
     * 후처리 (다회전 분리)
     * Jobs 방식에서는 pickup 병합이 필요 없음
     */
    private List<TmVehicleDispatchInfoDto> postProcessRoutes(List<TmVehicleDispatchInfoDto> routes) {
        // 다회전 차량 분리 (엔진에서 이미 분리된 경우 그대로 반환)
        return routes.stream()
                .flatMap(this::splitRouteByRounds)
                .toList();
    }

    /**
     * 다회전 차량 분리 (reload step 기준)
     * Jobs 방식: start → job → reload → job → end
     * reload step이 나타나면 회차가 증가
     */
    private Stream<TmVehicleDispatchInfoDto> splitRouteByRounds(TmVehicleDispatchInfoDto vehicle) {
        List<TmEngineStepDto> originalSteps = vehicle.getSteps();
        if (originalSteps == null || originalSteps.isEmpty()) {
            return Stream.of(vehicle);
        }

        // 1. 회차 번호(roundSeq) 설정 - reload 기준
        int tempRoundSeq = 1;
        for (int i = 0; i < originalSteps.size(); i++) {
            TmEngineStepDto currentStep = originalSteps.get(i);
            // reload step이 나타나면 회차 증가 (단, start 직후의 reload는 제외)
            if (i > 0 && "reload".equals(currentStep.getType()) && !"start".equals(originalSteps.get(i - 1).getType())) {
                tempRoundSeq++;
            }
            currentStep.setRoundSeq(tempRoundSeq);
        }

        // 2. 회차별로 그룹화
        Map<Integer, List<TmEngineStepDto>> stepsByRound = originalSteps.stream()
                .collect(Collectors.groupingBy(TmEngineStepDto::getRoundSeq));

        // 단일 회차면 원본 그대로 반환
        if (stepsByRound.size() <= 1 && tempRoundSeq == 1) {
            return Stream.of(vehicle);
        }

        // 2-1. 이전 회차에 다음 회차의 reload 복사 (센터 복귀 위치 정보 제공)
        copyReloadToPreviousRounds(stepsByRound);

        // 3. 각 회차를 별도의 차량 정보로 재구성
        List<Integer> sortedRoundKeys = new ArrayList<>(stepsByRound.keySet());
        Collections.sort(sortedRoundKeys);

        List<TmVehicleDispatchInfoDto> finalVehicleList = new ArrayList<>();
        for (int i = 0; i < sortedRoundKeys.size(); i++) {
            Integer roundSeq = sortedRoundKeys.get(i);
            List<TmEngineStepDto> roundSteps = new ArrayList<>(stepsByRound.get(roundSeq));

            // 3-1. start/end step 추가
            addStartEndSteps(roundSteps, i, sortedRoundKeys, stepsByRound);

            // 3-1-1. reload step 제거 (회차 구분자로만 사용, 최종 응답에서 제외)
            roundSteps.removeIf(step -> "reload".equals(step.getType()));

            // 3-2. 회차별 요약 정보 계산
            RoundSummary summary = calculateRoundSummary(roundSteps);

            // 3-3. 회차별 차량 DTO 생성
            TmVehicleDispatchInfoDto roundVehicle = createRoundVehicleDto(vehicle, roundSeq, roundSteps, summary);
            roundVehicle.setAlreadyRounded(true);
            finalVehicleList.add(roundVehicle);
        }
        return finalVehicleList.stream();
    }

    /**
     * 이전 회차에 다음 회차의 reload step을 복사
     * - 1회전에는 reload가 없어서 센터 위치를 알 수 없음
     * - 다음 회차의 reload를 복사하여 센터 복귀 위치 정보 제공
     */
    private void copyReloadToPreviousRounds(Map<Integer, List<TmEngineStepDto>> stepsByRound) {
        List<Integer> sortedRoundKeys = new ArrayList<>(stepsByRound.keySet());
        Collections.sort(sortedRoundKeys);

        for (int i = 0; i < sortedRoundKeys.size() - 1; i++) {
            Integer currentRound = sortedRoundKeys.get(i);
            Integer nextRound = sortedRoundKeys.get(i + 1);

            List<TmEngineStepDto> currentRoundSteps = stepsByRound.get(currentRound);
            List<TmEngineStepDto> nextRoundSteps = stepsByRound.get(nextRound);

            // 현재 회차에 reload가 없으면 다음 회차의 reload 복사
            boolean currentHasReload = currentRoundSteps.stream()
                    .anyMatch(s -> "reload".equals(s.getType()));

            if (!currentHasReload) {
                nextRoundSteps.stream()
                        .filter(s -> "reload".equals(s.getType()))
                        .findFirst()
                        .ifPresent(reload -> {
                            TmEngineStepDto reloadCopy = new TmEngineStepDto();
                            reloadCopy.setType("reload");
                            reloadCopy.setLocation(reload.getLocation());
                            reloadCopy.setArrival(reload.getArrival());
                            reloadCopy.setRoundSeq(currentRound);
                            currentRoundSteps.add(reloadCopy);
                        });
            }
        }
    }

    /**
     * 회차별 start/end step 추가
     */
    private void addStartEndSteps(List<TmEngineStepDto> roundSteps, int currentIndex,
                                   List<Integer> sortedKeys, Map<Integer, List<TmEngineStepDto>> stepsByRound) {
        if (roundSteps.isEmpty()) return;

        // reload step의 location을 센터 위치로 사용
        List<Double> reloadLocation = roundSteps.stream()
                .filter(s -> "reload".equals(s.getType()))
                .map(TmEngineStepDto::getLocation)
                .findFirst()
                .orElse(null);

        if (reloadLocation == null) return;

        // start step 추가
        if (roundSteps.stream().noneMatch(s -> "start".equals(s.getType()))) {
            TmEngineStepDto startStep = new TmEngineStepDto();
            startStep.setType("start");
            startStep.setLocation(reloadLocation);
            startStep.setArrival(roundSteps.get(0).getArrival());
            startStep.setRoundSeq(roundSteps.get(0).getRoundSeq());
            startStep.setDistance("0");
            roundSteps.add(0, startStep);
        }

        // end step 추가
        if (roundSteps.stream().noneMatch(s -> "end".equals(s.getType()))) {
            String endStepArrival;
            if (currentIndex + 1 < sortedKeys.size()) {
                List<TmEngineStepDto> nextRoundSteps = stepsByRound.get(sortedKeys.get(currentIndex + 1));
                endStepArrival = nextRoundSteps.stream()
                        .filter(s -> "reload".equals(s.getType()))
                        .map(TmEngineStepDto::getArrival)
                        .findFirst()
                        .orElse(roundSteps.get(roundSteps.size() - 1).getArrival());
            } else {
                endStepArrival = roundSteps.get(roundSteps.size() - 1).getArrival();
            }

            TmEngineStepDto endStep = new TmEngineStepDto();
            endStep.setType("end");
            endStep.setLocation(reloadLocation);
            endStep.setArrival(endStepArrival);
            endStep.setRoundSeq(roundSteps.get(0).getRoundSeq());
            roundSteps.add(endStep);
        }
    }

    /**
     * 회차별 요약 정보 계산
     */
    private RoundSummary calculateRoundSummary(List<TmEngineStepDto> roundSteps) {
        RoundSummary summary = new RoundSummary();
        long minArrival = Long.MAX_VALUE;
        long maxArrival = 0L;

        for (TmEngineStepDto step : roundSteps) {
            if (step.getWeight() != null && step.getWeight().matches("-?\\d+(\\.\\d+)?")) {
                summary.loadedWeight += Double.parseDouble(step.getWeight());
            }
            if (step.getCube() != null && step.getCube().matches("-?\\d+(\\.\\d+)?")) {
                summary.loadedCbm += Double.parseDouble(step.getCube());
            }
            // Jobs에서는 "job" 타입으로 orderCount 집계
            if ("job".equals(step.getType())) {
                summary.orderCount++;
            }
            if (step.getStepDistance() != null && !step.getStepDistance().isEmpty()) {
                summary.roundDistanceMeters += Integer.parseInt(step.getStepDistance());
            }
            if (step.getArrival() != null && !step.getArrival().isEmpty()) {
                try {
                    long arrival = Long.parseLong(step.getArrival());
                    minArrival = Math.min(arrival, minArrival);
                    maxArrival = Math.max(arrival, maxArrival);
                } catch (NumberFormatException ignored) {}
            }
        }
        summary.totalDurationSeconds = (int) (maxArrival - minArrival);
        return summary;
    }

    /**
     * 회차별 차량 DTO 생성
     */
    private TmVehicleDispatchInfoDto createRoundVehicleDto(TmVehicleDispatchInfoDto original,
                                                            int roundSeq,
                                                            List<TmEngineStepDto> steps,
                                                            RoundSummary summary) {
        int totalTimeMinutes = summary.totalDurationSeconds / 60;
        double roundDistanceKm = summary.roundDistanceMeters / 1000.0;

        double maxWeightValue = parseDoubleOrDefault(original.getMaxWeight(), 1.0);
        double maxCbmValue = parseDoubleOrDefault(original.getMaxCbm(), 1.0);

        int weightUsagePercent = (int) ((summary.loadedWeight * 100) / Math.max(1, maxWeightValue));
        int cbmUsagePercent = (int) ((summary.loadedCbm * 100) / Math.max(1, maxCbmValue));

        return TmVehicleDispatchInfoDto.builder()
                .carno(original.getCarno())
                .outGroupCd(original.getOutGroupCd())
                .vehicleName(original.getVehicleName())
                .drivername(original.getDrivername())
                .phone1(original.getPhone1())
                .carCapacity(original.getCarCapacity())
                .contractType(original.getContractType())
                .maxLanding(original.getMaxLanding())
                .vehicleType(original.getVehicleType())
                .vehicleGroup(original.getVehicleGroup())
                .maxWeight(original.getMaxWeight())
                .maxCbm(original.getMaxCbm())
                .roundSeq(roundSeq)
                // 회차별 재계산된 값
                .loadedWeight(String.format("%.2f", summary.loadedWeight))
                .loadedCbm(String.format("%.2f", summary.loadedCbm))
                .orderCount(summary.orderCount)
                .weightUsagePercent(weightUsagePercent)
                .cbmUsagePercent(cbmUsagePercent)
                .totalTimeMinutes(totalTimeMinutes)
                .totalTimeDisplay(formatTimeDisplay(totalTimeMinutes))
                .totalDistanceKm(roundDistanceKm)
                .duration(summary.totalDurationSeconds)
                // 원본 차량의 기타 정보 상속
                .cost(original.getCost())
                .setup(original.getSetup())
                .waitingTime(original.getWaitingTime())
                .priority(original.getPriority())
                .violations(original.getViolations())
                .description(original.getDescription())
                .distance(original.getDistance())
                .steps(steps)
                .build();
    }

    private double parseDoubleOrDefault(String value, double defaultValue) {
        if (value == null || value.isEmpty()) return defaultValue;
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private String convertCubeToM3(String cubeCm3) {
        if (cubeCm3 == null || cubeCm3.isEmpty()) return "0";
        try {
            double cm3 = Double.parseDouble(cubeCm3);
            return String.valueOf(cm3 / CM3_TO_M3);
        } catch (NumberFormatException e) {
            return "0";
        }
    }

    /**
     * 회차별 요약 정보
     */
    private static class RoundSummary {
        double loadedWeight = 0.0;
        double loadedCbm = 0.0;
        int orderCount = 0;
        int roundDistanceMeters = 0;
        int totalDurationSeconds = 0;
    }

    /**
     * 미배정 주문 처리 (Jobs 방식)
     *
     * Jobs 방식에서는 미배차 시 주문당 1개만 반환됨 (pickup/delivery 쌍 없음)
     * - Jobs: { "id": "K791", "description": "거래처명" }
     * - Shipments(기존): { "id": "123", "description": "123-pickup-stop" }, { "id": "123", "description": "123-delivery-stop" }
     *
     * 따라서 Shipments 방식의 isFilter(중복 제거) 로직이 불필요함
     */
    private List<TmSetDispatchUnassignedOrderResDto> processUnassignedOrders(
            List<TmSetDispatchUnassignedOrderResDto> unassigned,
            Map<String, TmSetDispatchTruthCustResDto> orderMap,
            Map<String, TmPlanEtaStepReqDto> splitOrderMap) {

        if (unassigned == null) return new ArrayList<>();

        return unassigned.stream()
                .filter(order -> orderMap.containsKey(order.getId()))
                .map(order -> {
                    enrichUnassignedOrder(order, orderMap, splitOrderMap);
                    order.createUniqueId();
                    return order;
                })
                .toList();
    }

    /**
     * 미배정 주문 데이터 보강
     */
    private void enrichUnassignedOrder(
            TmSetDispatchUnassignedOrderResDto order,
            Map<String, TmSetDispatchTruthCustResDto> orderMap,
            Map<String, TmPlanEtaStepReqDto> splitOrderMap) {

        String originalId = order.getId();
        TmPlanEtaStepReqDto splitInfo = (splitOrderMap != null) ? splitOrderMap.get(originalId) : null;

        if (splitInfo != null) {
            int lastIndex = originalId.lastIndexOf("-");
            if (lastIndex != -1) {
                originalId = originalId.substring(0, lastIndex);
            }
        }

        TmSetDispatchTruthCustResDto truthCustInfo = orderMap.get(originalId);
        if (truthCustInfo == null) return;

        order.setId(originalId);
        order.setCustName(truthCustInfo.getCustName());
        order.setStorerkey(truthCustInfo.getStorerkey());
        order.setCustAddress(truthCustInfo.getAddress());
        order.setCustType(truthCustInfo.getCustType());
        order.setClaimYn(truthCustInfo.getClaimYn());
        order.setFaceInspect(truthCustInfo.getFaceInspect());
        order.setReqdlvtime1From(truthCustInfo.getReqdlvtime1From());
        order.setReqdlvtime1To(truthCustInfo.getReqdlvtime1To());
        order.setKeyCustType(truthCustInfo.getPasswordType());
        order.setSpecialConditionYn(hasSpecialConditions(truthCustInfo) ? "Y" : "N");
        order.setReturnYn(truthCustInfo.getReturnYn());
        order.setTmDeliveryType(truthCustInfo.getTmDeliveryType());
        order.setSlipdt(truthCustInfo.getSlipdt());
        order.setPasswordType(truthCustInfo.getPasswordType());
        order.setPasswordTypeCd(truthCustInfo.getPasswordTypeCd());
        order.setDefCarno(truthCustInfo.getDefCarno());
        order.setCarno(truthCustInfo.getCarno());
        order.setPop(truthCustInfo.getPop());
        order.setDefPop(truthCustInfo.getDefPop());
        order.setBasePop(truthCustInfo.getBasePop());
        order.setService(truthCustInfo.getService());

        if (splitInfo != null) {
            order.setCube(splitInfo.getCube());
            order.setWeight(splitInfo.getWeight());
            order.setOrderQty(splitInfo.getOrderQty());
            order.setSlipdt(splitInfo.getSlipdt());
            order.setSlipline(splitInfo.getSlipline());
            order.setSlipno(splitInfo.getSlipno());
            order.setSplitDeliveryYn(splitInfo.getSplitDeliveryYn());
            order.setSplitDeliverySeq(splitInfo.getSplitDeliverySeq());
        } else {
            order.setCube(truthCustInfo.getCube());
            order.setWeight(truthCustInfo.getWeight());
            order.setOrderQty(truthCustInfo.getOrderQty());
        }
    }

    /**
     * arrival 파싱 (Unix timestamp 또는 yyyyMMddHHmmss 형식)
     */
    private long parseArrival(String arrival) {
        if (arrival == null || arrival.isEmpty()) return 0L;
        try {
            // yyyyMMddHHmmss 형식 처리
            if (arrival.length() == 14) {
                LocalDateTime dateTime = LocalDateTime.parse(arrival,
                        DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                return dateTime.atZone(SEOUL_ZONE_ID).toEpochSecond();
            }
            // Unix timestamp (초) 형식 처리
            return Long.parseLong(arrival);
        } catch (Exception e) {
            log.warn("도착 시간 파싱 실패: {}. 0을 반환합니다.", arrival);
            return 0L;
        }
    }

    /**
     * 특수조건 여부 확인
     */
    private boolean hasSpecialConditions(TmSetDispatchTruthCustResDto truthCustInfo) {
        return "Y".equals(truthCustInfo.getKidsClYn()) ||
                "Y".equals(truthCustInfo.getDlvWaitYn()) ||
                "Y".equals(truthCustInfo.getDistantYn()) ||
                "Y".equals(truthCustInfo.getLngDistantYn()) ||
                "Y".equals(truthCustInfo.getUnloadLvlYn());
    }

    /**
     * 시간 표시 포맷팅
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
}
