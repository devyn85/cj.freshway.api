package cjfw.wms.tm.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cjfw.core.exception.UserHandleException;
import cjfw.core.utility.ContextUtil;
import cjfw.wms.tm.dto.TmPlanEtaManualRouteReqDto;
import cjfw.wms.tm.dto.TmPlanEtaManualRouteReqDto.ViaPoint;
import cjfw.wms.tm.dto.TmPlanEtaManualRouteResDto;
import cjfw.wms.tm.dto.TmPlanEtaOptimizeAutoReqDto;
import cjfw.wms.tm.dto.TmPlanEtaStepReqDto;
import cjfw.wms.tm.dto.TmPlanEtaVehicleReqDto;
import cjfw.wms.tm.dto.TmSetDispatchResDto;
import cjfw.wms.tm.dto.TmSetDispatchTruthCustResDto;
import cjfw.wms.tm.dto.TmVehicleDispatchInfoDto;
import cjfw.wms.tm.dto.TmVehicleInfoDto;
import cjfw.wms.tm.util.TmPlanCommon;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2026. CJ Freshway Co. all rights reserved.
 * @author : ChoiHwaRang (az86067@cj.net)
 * @date : 2026.01.23
 * @description : PlanEta Manual API 요청 Service (T-map API 호출)
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2026.01.23 ChoiHwaRang (az86067@cj.net) 생성 - TmNewEngineDataMapper에서 이관
 * 2026.01.23 ChoiHwaRang (az86067@cj.net) 다회전 지원 - carno별 병합 후 단일 T-map 호출
 * 2026.02.01 Claude 단일/다회전 처리 통합 - processVehicleRounds 메서드로 통합 </pre>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TmPlanManualRequestService {

    private final TmPlanManualResponseService tmPlanManualResponseService;

    @Value("${tm.test-mode:false}")
    private boolean testMode;

    private static final String TMAP_URL = ContextUtil.getProperty("spring.tmap.url");
    private static final String TMAP_APPKEY = ContextUtil.getProperty("spring.tmap.appkey");
    private static final String TMAP_PATH_PREFIX = "/routes/routeSequential";
    private static final String TMAP_PATH_SUFFIX = "?version=1";
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmm");

    /** T-map API 최대 viaPoints 수 (타임아웃 방지를 위해 50건으로 제한) */
    private static final int MAX_VIA_POINTS = 50;

    /** RELOAD viaPoint ID 접두사 (회차 구분자) */
    public static final String RELOAD_PREFIX = "RELOAD-";

    /**
     * 수동 재계산 요청 처리
     * - 다회전 지원: 동일 carno의 여러 회차를 하나의 T-map 요청으로 병합
     * - 회차 경계에 센터지 RELOAD viaPoint 추가
     * - 응답에서 RELOAD 기준으로 회차 분리
     *
     * @param request 재계산 요청 DTO
     * @param vehicleMap 차량 정보 Map (carno 기준)
     * @param orderMap 주문 정보 Map (truthCustKey 기준)
     * @param splitOrderMap 분할 주문 정보 Map
     * @return 배차 결과 DTO
     */
    public TmSetDispatchResDto createManualRequest(
            TmPlanEtaOptimizeAutoReqDto request,
            Map<String, TmVehicleInfoDto> vehicleMap,
            Map<String, TmSetDispatchTruthCustResDto> orderMap,
            Map<String, TmSetDispatchTruthCustResDto> splitOrderMap
    ) {
        HttpHeaders headers = createHeaders();
        RestTemplate restTemplate = new RestTemplate();

        TmSetDispatchResDto successResponse = new TmSetDispatchResDto();
        List<TmVehicleDispatchInfoDto> vehicleDispatchInfoList = new ArrayList<>();

        // 1. carno별 그룹화
        Map<String, List<TmPlanEtaVehicleReqDto>> vehiclesByCarno = request.getVehicles().stream()
                .collect(Collectors.groupingBy(TmPlanEtaVehicleReqDto::getCarno));

        // 2. 각 carno 그룹 처리 (단일/다회전 통합)
        for (Map.Entry<String, List<TmPlanEtaVehicleReqDto>> entry : vehiclesByCarno.entrySet()) {
            String carno = entry.getKey();
            List<TmPlanEtaVehicleReqDto> rounds = entry.getValue();

            // 2-1. roundSeq 정렬 (null은 1로 처리)
            rounds.sort(Comparator.comparing(v -> v.getRoundSeq() == null ? 1 : v.getRoundSeq()));

            TmVehicleInfoDto vehicleInfo = vehicleMap.get(carno);
            if (vehicleInfo == null) {
                log.warn("차량 정보를 찾을 수 없습니다: {}", carno);
                continue;
            }

            // 2-2. 단일/다회전 통합 처리
            List<TmVehicleDispatchInfoDto> results = processVehicleRounds(
                    rounds, vehicleInfo, orderMap, splitOrderMap,
                    headers, restTemplate, request.getDeliveryDate());
            vehicleDispatchInfoList.addAll(results);
        }

        successResponse.setTmDeliveryType(request.getTmDeliveryType());
        successResponse.setVehicles(vehicleDispatchInfoList);
        return successResponse;
    }

    /**
     * HTTP 헤더 생성
     */
    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("appKey", TMAP_APPKEY);
        return headers;
    }

    /**
     * 차량 회차 처리 (단일/다회전 통합)
     *
     * @param rounds 회차 목록 (roundSeq 정렬됨, 단일 회차면 size=1)
     * @param vehicleInfo 차량 정보
     * @param orderMap 주문 Map
     * @param splitOrderMap 분할 주문 Map
     * @param headers HTTP 헤더
     * @param restTemplate RestTemplate
     * @param deliveryDate 배송일
     * @return 회차별 TmVehicleDispatchInfoDto 목록
     */
    private List<TmVehicleDispatchInfoDto> processVehicleRounds(
            List<TmPlanEtaVehicleReqDto> rounds,
            TmVehicleInfoDto vehicleInfo,
            Map<String, TmSetDispatchTruthCustResDto> orderMap,
            Map<String, TmSetDispatchTruthCustResDto> splitOrderMap,
            HttpHeaders headers,
            RestTemplate restTemplate,
            String deliveryDate
    ) {
        // 모든 회차의 steps가 비어있으면 빈 리스트 반환
        boolean hasSteps = rounds.stream().anyMatch(r -> !r.getSteps().isEmpty());
        if (!hasSteps) {
            return Collections.emptyList();
        }

        // 주문 step이 없는 빈 회차 제거 후 roundSeq를 1부터 재번호매기기
        rounds = rounds.stream()
                .filter(r -> r.getSteps().stream()
                        .anyMatch(s -> !"start".equals(s.getType()) && !"end".equals(s.getType())))
                .collect(Collectors.toList());
        for (int i = 0; i < rounds.size(); i++) {
            rounds.get(i).setRoundSeq(i + 1);
        }

        // 단일 회차인 경우 roundSeq 설정
        if (rounds.size() == 1) {
            vehicleInfo.setRoundSeq(rounds.get(0).getRoundSeq());
        }

        String longitude = vehicleInfo.getLongitude() == null ? "126.9780" : vehicleInfo.getLongitude().toString();
        String latitude = vehicleInfo.getLatitude() == null ? "37.5665" : vehicleInfo.getLatitude().toString();

        // 출차 시간을 startTime으로 사용
        LocalDateTime dateTime = vehicleInfo.getDrivingStartDateTime();
        String startTime = dateTime.format(OUTPUT_FORMATTER);

        TmPlanEtaManualRouteReqDto routeReqDto = new TmPlanEtaManualRouteReqDto();
        routeReqDto.setStartName("start");
        routeReqDto.setEndName("end");
        routeReqDto.setSearchOption("0");
        routeReqDto.setCoordinateFlag("0");
        routeReqDto.setStartTime(startTime);
        routeReqDto.setStartX(longitude);
        routeReqDto.setStartY(latitude);

        // viaPoints 생성 (단일 회차면 RELOAD 없음, 다회전이면 RELOAD 포함)
        List<ViaPoint> viaPoints = buildMergedViaPoints(
                rounds, vehicleInfo, orderMap, splitOrderMap, deliveryDate);

        if (viaPoints.isEmpty()) {
            return Collections.emptyList();
        }

        // T-map API 호출 (100건 초과 시 청크 분할)
        TmPlanEtaManualRouteResDto tmapResponse = callTmapApiWithChunking(
                viaPoints, routeReqDto, vehicleInfo.getCarno(), headers, restTemplate);
        if (tmapResponse == null) {
            return Collections.emptyList();
        }

        // 통합 응답 변환 (단일/다회전 모두 처리)
        return tmPlanManualResponseService.createTmapResponseUnified(
                tmapResponse, rounds, vehicleInfo, orderMap, splitOrderMap);
    }

    /**
     * viaPoints 생성 (단일/다회전 통합)
     * - 단일 회차: RELOAD 없이 steps만 변환
     * - 다회전: 회차 경계에 RELOAD 추가
     *
     * @param rounds 회차 목록 (roundSeq 정렬됨)
     * @param vehicleInfo 차량 정보
     * @param orderMap 주문 Map
     * @param splitOrderMap 분할 주문 Map
     * @param deliveryDate 배송일
     * @return viaPoints 목록
     */
    private List<ViaPoint> buildMergedViaPoints(
            List<TmPlanEtaVehicleReqDto> rounds,
            TmVehicleInfoDto vehicleInfo,
            Map<String, TmSetDispatchTruthCustResDto> orderMap,
            Map<String, TmSetDispatchTruthCustResDto> splitOrderMap,
            String deliveryDate
    ) {
        List<ViaPoint> mergedViaPoints = new ArrayList<>();

        for (int i = 0; i < rounds.size(); i++) {
            TmPlanEtaVehicleReqDto vehicle = rounds.get(i);
            int roundSeq = vehicle.getRoundSeq() == null ? 1 : vehicle.getRoundSeq();

            // 2회전 이상 시작 전에 센터지 RELOAD 추가 (다회전인 경우만)
            if (i > 0) {
                ViaPoint reloadPoint = createReloadViaPoint(vehicleInfo, roundSeq);
                mergedViaPoints.add(reloadPoint);
                log.info("[다회전] RELOAD viaPoint 추가: roundSeq={}", roundSeq);
            }

            // 해당 회차의 steps를 viaPoints로 변환
            for (TmPlanEtaStepReqDto step : vehicle.getSteps()) {
                ViaPoint viaPoint = createViaPointFromStep(step, roundSeq, orderMap, splitOrderMap, deliveryDate);
                if (viaPoint != null) {
                    mergedViaPoints.add(viaPoint);
                }
            }
        }

        return mergedViaPoints;
    }

    /**
     * 센터지 RELOAD viaPoint 생성
     * - 다음 회차 시작 전 센터 복귀 지점
     * - viaPointId: RELOAD-{roundSeq} 형식 (후처리에서 회차 분리 기준)
     *
     * @param vehicleInfo 차량 정보 (센터 좌표)
     * @param roundSeq 다음 회차 번호
     * @return RELOAD viaPoint
     */
    private ViaPoint createReloadViaPoint(TmVehicleInfoDto vehicleInfo, int roundSeq) {
        ViaPoint reloadPoint = new ViaPoint();

        // 특수 ID: RELOAD-{roundSeq} (후처리에서 회차 분리 기준)
        reloadPoint.setViaPointId(RELOAD_PREFIX + roundSeq);
        reloadPoint.setViaPointName("센터복귀-" + roundSeq + "회전시작");
        reloadPoint.setViaX(vehicleInfo.getLongitude() == null ? "126.9780" : vehicleInfo.getLongitude().toString());
        reloadPoint.setViaY(vehicleInfo.getLatitude() == null ? "37.5665" : vehicleInfo.getLatitude().toString());
        reloadPoint.setViaTime("0");  // 센터 복귀 후 작업 시간 (필요 시 재적재 시간 추가)

        return reloadPoint;
    }

    /**
     * Step 정보를 ViaPoint로 변환
     *
     * @param step step 정보
     * @param roundSeq 회차 번호
     * @param orderMap 주문 Map
     * @param splitOrderMap 분할 주문 Map
     * @param deliveryDate 배송일
     * @return ViaPoint (start/end step인 경우 null)
     */
    private ViaPoint createViaPointFromStep(
            TmPlanEtaStepReqDto step,
            int roundSeq,
            Map<String, TmSetDispatchTruthCustResDto> orderMap,
            Map<String, TmSetDispatchTruthCustResDto> splitOrderMap,
            String deliveryDate
    ) {
        // start/end step은 건너뜀
        if ("start".equals(step.getType()) || "end".equals(step.getType())) {
            return null;
        }

        if (step.getId() == null) {
            return null;
        }

        step.setRoundSeq(roundSeq);

        String seq = step.getSplitDeliverySeq();
        if (seq == null || seq.isEmpty() || "N".equals(step.getSplitDeliveryYn())) {
            seq = "0";
        }

        String viaPointId = step.getId() + "-" + step.getType() + "-" + seq;
        TmSetDispatchTruthCustResDto orderData = splitOrderMap.get(viaPointId);

        if (orderData == null) {
            log.warn("주문 정보를 찾을 수 없습니다: {}", viaPointId);
            return null;
        }

        int workTime = TmPlanCommon.toInt(orderData.getWorkTime(), 10) * 60;

        ViaPoint viaPoint = new ViaPoint();
        viaPoint.setViaPointId(viaPointId);
        viaPoint.setViaPointName(step.getId());
        viaPoint.setViaX(step.getLocation().get(0).toString());
        viaPoint.setViaY(step.getLocation().get(1).toString());
        viaPoint.setViaTime(String.valueOf(workTime));

        // OTD 설정
        TmSetDispatchTruthCustResDto viaOrderMap = orderMap.get(step.getId());
        if (viaOrderMap != null && viaOrderMap.getReqdlvtime1From() != null) {
            List<Long> otdTimeList = viaOrderMap.getOtdTime(deliveryDate);
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMddHHmm");
            viaPoint.setWishStartTime(sdf.format(new java.util.Date(otdTimeList.get(0) * 1000)));
            viaPoint.setWishEndTime(sdf.format(new java.util.Date(otdTimeList.get(1) * 1000)));
        }

        return viaPoint;
    }

    // ========================================================================
    // T-map API 호출 (청크 분할 지원)
    // ========================================================================

    /**
     * T-map API 호출 (100건 초과 시 청크 분할)
     *
     * @param viaPoints 전체 viaPoints
     * @param baseReqDto 기본 요청 DTO (startX/Y, startTime 등)
     * @param carno 차량번호 (로깅용)
     * @param headers HTTP 헤더
     * @param restTemplate RestTemplate
     * @return 병합된 T-map 응답 DTO
     */
    private TmPlanEtaManualRouteResDto callTmapApiWithChunking(
            List<ViaPoint> viaPoints,
            TmPlanEtaManualRouteReqDto baseReqDto,
            String carno,
            HttpHeaders headers,
            RestTemplate restTemplate
    ) {
        // 100건 이하면 기존 로직
        if (viaPoints.size() <= MAX_VIA_POINTS) {
            setEndPointFromLastViaPoint(baseReqDto, viaPoints);
            baseReqDto.setViaPoints(viaPoints);
            return callTmapApi(baseReqDto, carno, headers, restTemplate);
        }

        // 100건 초과: 청크 분할 → 다중 호출 → 응답 병합
        log.info("[청크분할] viaPoints={}건, 100건 초과로 청크 분할 호출 시작 - carno={}", viaPoints.size(), carno);

        List<List<ViaPoint>> chunks = splitViaPointsIntoChunks(viaPoints);
        List<TmPlanEtaManualRouteResDto> responses = new ArrayList<>();

        String currentStartX = baseReqDto.getStartX();
        String currentStartY = baseReqDto.getStartY();
        String currentStartTime = baseReqDto.getStartTime();

        for (int i = 0; i < chunks.size(); i++) {
            List<ViaPoint> chunk = chunks.get(i);
            boolean isLastChunk = (i == chunks.size() - 1);

            log.info("[청크분할] 청크 {}/{} 호출 - viaPoints={}건", i + 1, chunks.size(), chunk.size());

            TmPlanEtaManualRouteReqDto chunkReq = buildChunkRequest(
                    baseReqDto, chunk, currentStartX, currentStartY, currentStartTime, isLastChunk);

            TmPlanEtaManualRouteResDto response = callTmapApi(chunkReq, carno, headers, restTemplate);
            responses.add(response);

            // 다음 청크의 시작점/시간 설정 (마지막 청크가 아닌 경우)
            if (!isLastChunk) {
                ViaPoint lastViaPoint = chunk.get(chunk.size() - 1);
                currentStartX = lastViaPoint.getViaX();
                currentStartY = lastViaPoint.getViaY();
                // arriveTime + viaTime(작업시간) = 실제 출발 시간
                String arriveTime = getLastArriveTime(response);
                int viaTimeSeconds = parseIntOrZero(lastViaPoint.getViaTime());
                currentStartTime = addSecondsToTime(arriveTime, viaTimeSeconds);
            }
        }

        log.info("[청크분할] 청크 분할 호출 완료 - 총 {}개 청크, carno={}", chunks.size(), carno);
        return mergeChunkResponses(responses);
    }

    /**
     * viaPoints를 MAX_VIA_POINTS 단위로 분할
     * - 경계점(마지막 viaPoint)은 다음 청크의 시작점으로 사용됨 (viaPoints에는 미포함)
     *
     * @param viaPoints 전체 viaPoints
     * @return 분할된 청크 목록
     */
    private List<List<ViaPoint>> splitViaPointsIntoChunks(List<ViaPoint> viaPoints) {
        List<List<ViaPoint>> chunks = new ArrayList<>();

        int totalSize = viaPoints.size();
        int startIndex = 0;

        while (startIndex < totalSize) {
            int endIndex = Math.min(startIndex + MAX_VIA_POINTS, totalSize);
            chunks.add(new ArrayList<>(viaPoints.subList(startIndex, endIndex)));
            startIndex = endIndex;
        }

        return chunks;
    }

    /**
     * 청크별 T-map 요청 DTO 생성
     *
     * @param baseReqDto 기본 요청 DTO
     * @param chunk 청크 viaPoints
     * @param startX 시작점 X 좌표
     * @param startY 시작점 Y 좌표
     * @param startTime 시작 시간
     * @param isLastChunk 마지막 청크 여부
     * @return 청크용 요청 DTO
     */
    private TmPlanEtaManualRouteReqDto buildChunkRequest(
            TmPlanEtaManualRouteReqDto baseReqDto,
            List<ViaPoint> chunk,
            String startX,
            String startY,
            String startTime,
            boolean isLastChunk
    ) {
        TmPlanEtaManualRouteReqDto chunkReq = new TmPlanEtaManualRouteReqDto();
        chunkReq.setStartName(baseReqDto.getStartName());
        chunkReq.setEndName(baseReqDto.getEndName());
        chunkReq.setSearchOption(baseReqDto.getSearchOption());
        chunkReq.setCoordinateFlag(baseReqDto.getCoordinateFlag());

        chunkReq.setStartX(startX);
        chunkReq.setStartY(startY);
        chunkReq.setStartTime(startTime);

        // 마지막 viaPoint를 end로 설정
        ViaPoint lastViaPoint = chunk.get(chunk.size() - 1);
        chunkReq.setEndX(lastViaPoint.getViaX());
        chunkReq.setEndY(lastViaPoint.getViaY());

        // 모든 viaPoints 포함 (마지막 viaPoint도 경유지로 전송해야 viaPointId가 응답에 포함됨)
        chunkReq.setViaPoints(chunk);

        return chunkReq;
    }

    /**
     * 청크별 T-map 응답을 하나로 병합
     * - features 연결 (중간 S/E 제거)
     * - totalDistance/totalTime 합산
     *
     * @param responses 청크별 응답 목록
     * @return 병합된 응답 DTO
     */
    private TmPlanEtaManualRouteResDto mergeChunkResponses(List<TmPlanEtaManualRouteResDto> responses) {
        if (responses.isEmpty()) return null;
        if (responses.size() == 1) return responses.get(0);

        TmPlanEtaManualRouteResDto merged = new TmPlanEtaManualRouteResDto();
        List<TmPlanEtaManualRouteResDto.Feature> mergedFeatures = new ArrayList<>();

        int totalDistance = 0;
        int totalTime = 0;

        for (int i = 0; i < responses.size(); i++) {
            TmPlanEtaManualRouteResDto response = responses.get(i);
            if (response == null || response.getFeatures() == null) {
                continue;
            }

            List<TmPlanEtaManualRouteResDto.Feature> features = response.getFeatures();

            for (TmPlanEtaManualRouteResDto.Feature feature : features) {
                String pointType = (feature.getProperties() != null)
                        ? feature.getProperties().getPointType() : null;
                String viaPointId = (feature.getProperties() != null)
                        ? feature.getProperties().getViaPointId() : null;

                // 첫 번째 청크가 아니면 시작점(S) 제거
                if (i > 0 && "S".equals(pointType)) {
                    continue;
                }
                // 마지막 청크가 아니면 종료점(E) 제거 (단, viaPointId가 있으면 경유지이므로 유지)
                if (i < responses.size() - 1 && "E".equals(pointType) && (viaPointId == null || viaPointId.isEmpty())) {
                    continue;
                }

                mergedFeatures.add(feature);
            }

            // totalDistance/totalTime 누적
            if (response.getProperties() != null) {
                totalDistance += parseIntOrZero(response.getProperties().getTotalDistance());
                totalTime += parseIntOrZero(response.getProperties().getTotalTime());
            }
        }

        merged.setFeatures(mergedFeatures);

        // properties 설정
        TmPlanEtaManualRouteResDto.Properties props = new TmPlanEtaManualRouteResDto.Properties();
        props.setTotalDistance(String.valueOf(totalDistance));
        props.setTotalTime(String.valueOf(totalTime));
        merged.setProperties(props);

        log.info("[청크병합] 병합 완료 - features={}개, totalDistance={}, totalTime={}",
                mergedFeatures.size(), totalDistance, totalTime);

        return merged;
    }

    /**
     * 마지막 viaPoint 좌표를 end로 설정
     */
    private void setEndPointFromLastViaPoint(TmPlanEtaManualRouteReqDto reqDto, List<ViaPoint> viaPoints) {
        if (viaPoints == null || viaPoints.isEmpty()) return;
        ViaPoint lastViaPoint = viaPoints.get(viaPoints.size() - 1);
        reqDto.setEndX(lastViaPoint.getViaX());
        reqDto.setEndY(lastViaPoint.getViaY());
    }

    /**
     * T-map 응답에서 마지막 arriveTime 추출
     * - T-map 응답: yyyyMMddHHmmss (14자리)
     * - 요청 startTime: yyyyMMddHHmm (12자리)
     * - 12자리로 변환하여 반환
     */
    private String getLastArriveTime(TmPlanEtaManualRouteResDto response) {
        if (response == null || response.getFeatures() == null) {
            return null;
        }

        // 역순으로 탐색하여 arriveTime이 있는 마지막 feature 찾기
        List<TmPlanEtaManualRouteResDto.Feature> features = response.getFeatures();
        for (int i = features.size() - 1; i >= 0; i--) {
            TmPlanEtaManualRouteResDto.Feature feature = features.get(i);
            if (feature.getProperties() != null && feature.getProperties().getArriveTime() != null) {
                String arriveTime = feature.getProperties().getArriveTime();
                // 14자리(yyyyMMddHHmmss) → 12자리(yyyyMMddHHmm)로 변환
                if (arriveTime.length() >= 12) {
                    return arriveTime.substring(0, 12);
                }
                return arriveTime;
            }
        }
        return null;
    }

    /**
     * 문자열을 int로 변환 (실패 시 0 반환)
     */
    private int parseIntOrZero(String value) {
        if (value == null || value.isEmpty()) return 0;
        try {
            return (int) Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * 시간 문자열에 초를 더하여 반환
     * - 입력: yyyyMMddHHmm (12자리)
     * - 출력: yyyyMMddHHmm (12자리)
     *
     * @param timeStr 시간 문자열 (yyyyMMddHHmm)
     * @param seconds 더할 초
     * @return 계산된 시간 문자열
     */
    private String addSecondsToTime(String timeStr, int seconds) {
        if (timeStr == null || timeStr.length() < 12) {
            return timeStr;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
            LocalDateTime dateTime = LocalDateTime.parse(timeStr.substring(0, 12), formatter);
            LocalDateTime newDateTime = dateTime.plusSeconds(seconds);
            return newDateTime.format(formatter);
        } catch (Exception e) {
            log.warn("시간 계산 실패: timeStr={}, seconds={}", timeStr, seconds);
            return timeStr;
        }
    }

    // ========================================================================
    // T-map API 단건 호출
    // ========================================================================

    /**
     * T-map API 단건 호출
     *
     * @param routeReqDto 요청 DTO
     * @param carno 차량번호 (로깅용)
     * @param headers HTTP 헤더
     * @param restTemplate RestTemplate
     * @return T-map 응답 DTO (실패 시 예외 발생)
     */
    private TmPlanEtaManualRouteResDto callTmapApi(
            TmPlanEtaManualRouteReqDto routeReqDto,
            String carno,
            HttpHeaders headers,
            RestTemplate restTemplate
    ) {
        HttpEntity<TmPlanEtaManualRouteReqDto> entity = new HttpEntity<>(routeReqDto, headers);

        int jobCount = entity.getBody().getViaPoints().size();
        String tMapRequestJobCount = (jobCount <= 30) ? "30" : "100";

        ResponseEntity<TmPlanEtaManualRouteResDto> response = restTemplate.exchange(
                TMAP_URL + TMAP_PATH_PREFIX + tMapRequestJobCount + TMAP_PATH_SUFFIX,
                HttpMethod.POST,
                entity,
                TmPlanEtaManualRouteResDto.class
        );

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new UserHandleException("MSG_COM_ERR_999", new String[]{"수동 경로계산 오류"});
        }

        return response.getBody();
    }
}
