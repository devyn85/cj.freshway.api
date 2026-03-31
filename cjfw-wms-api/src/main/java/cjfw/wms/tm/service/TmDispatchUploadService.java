package cjfw.wms.tm.service;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import cjfw.core.model.ApiResult;
import cjfw.core.model.UserContext;
import cjfw.wms.tm.dto.*;
import cjfw.wms.tm.dto.TmDispatchJsonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.utility.StringUtil;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.tm.dto.TmCustomerLocationResDto;
import cjfw.wms.tm.dto.TmDispatchCenterInfoResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : han@wemeetmobility.com
 * @date : 2025.11.28
 * @description : TM 배차 업로드 Service
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmDispatchUploadService {

    private static final String SERVICEID_PREFIX = "tmDispatchUploadMapper.";

    private static final String SERVICESETDISPATCH_PREFIX = "tmSetDispatchService.";

    private final CommonDao commonDao;

    @Autowired
    private TmSetDispatchService tmSetDispatchService;

    public ApiResult saveUpload(TmDispatchJsonDto jsonDto, UserContext userContext){
        // 1. 데이터 존재 여부 확인
        if (jsonDto == null) {
            log.warn("Upload 할 JSON DATA가 없습니다.");
            return ApiResult.createResult("Upload 할 JSON DATA가 없습니다.", 400);
        }

        // jsonDto.getPrintOutData();
        TmDispatchExcelDto excelDto = null;

        String dccode = jsonDto.getDccode();
        String deliveryDate = jsonDto.getDeliveryDate();

        log.info("Excel 파일 파싱 완료 - 총 {}건", jsonDto.getRows().size());


        String storerkey = userContext.getStorerkey();

        // 4. 센터-실착지 직선거리 계산 및 jsonDto에 설정
        Map<String, Double> distanceMap = calculateLinearDistances(jsonDto, dccode, deliveryDate);
        jsonDto.setLinearDistanceKmByTruthCustKey(distanceMap);

        // 5. JSON 데이터를 TM_INPLAN 업데이트용 DTO로 변환
        List<TmInplanUpdateDto> updateList = getInplanUpdateList(jsonDto);

        // 직선거리를 updateList에 설정
        updateList.forEach(dto -> {
            if (dto.getTruthCustKey() != null) {
                dto.setLinearDistanceKm(distanceMap.get(dto.getTruthCustKey()));
            }
        });

        // 직선거리 오름차순으로 차량별 배송순서(priority) 재배정
        sortByLinearDistance(updateList);

        // 6. T-map 경로 계산 (Phase 2)
        Map<String, TmapDispatchRouteResDto> routeResultsByCarno =null;
        //Map<String, TmapDispatchRouteResDto> routeResultsByCarno = tmTmapRouteService
        //		.calculateRoutesForVehicles(updateList, dccode, deliveryDate);
        //log.info("T-map 경로 계산 완료 - 차량: {}대", routeResultsByCarno.size());

        //Set<String> keySet = routeResultsByCarno.keySet();


        // 7. DB 업데이트 (Phase 3)
        TmDispatchUploadResDto result = saveDispatchExcel(updateList, routeResultsByCarno,
                dccode, deliveryDate, storerkey);

        // 8. 로그 및 응답 반환
        log.info("배차 데이터 업로드 완료 - 총: {}, 성공: {}, 실패: {}", result.getTotalCount(), result.getSuccessCount(),
                result.getFailCount());

        //9. TM ROUTE EMPTY 로 돌림
        createEmptyPlanRoutes(updateList,dccode,deliveryDate);

        return ApiResult.createResult("수동 배차 업로드가 완료되었습니다.", 201);
    }

    public List<TmInplanUpdateDto> getInplanUpdateList(TmDispatchJsonDto jsonDto) {

        return jsonDto.getRows().stream().map(row -> {
            TmInplanUpdateDto.TmInplanUpdateDtoBuilder builder = TmInplanUpdateDto.builder();

            // TM_INPLAN 업데이트 가능한 모든 컬럼 값 추출
            row.getCells().forEach(cell -> {
                Object value = cell.getValue();
                switch (cell.getColumn()) {
                    // WHERE 조건 (PRIMARY KEY)
                    case TRUTH_CUST_KEY -> builder.truthCustKey((String) value);
                    case SLIP_NO -> builder.slipNo((String) value);
                    case DOC_TYPE -> builder.docType((String) value);
                    // UPDATE 대상
                    case POP_NAME -> builder.deliveryGroup((String) value);
                    case AREA_GROUP_NAME -> builder.districtGroup((String) value);
                    case AREA_NAME -> builder.districtCode((String) value);
                    case DONG_CODE -> builder.hjdongCd((String) value);
                    case ZIP_CODE -> builder.zipcode((String) value);
                    case CUST_KEY -> builder.custKey((String) value);
                    case DELIVERY_PRIORITY -> builder.deliveryPriority((String) value);
                    case CARNO -> builder.carno((String) value);
                    case CONTRACT_TYPE -> builder.contractType((String) value);
                    case DELIVERY_TYPE -> builder.deliveryType((String) value);
                    case TURN -> builder.priority(getInteger(value));
                    case TOTAL_WEIGHT -> builder.weight(getDouble(value));
                    case TOTAL_CUBE -> builder.cube(getDouble(value));
                    default -> {
                        log.warn("알 수 없는 case");
                    }
                }
            });

            return builder.build();
        }).collect(Collectors.toList());
    }
    /**
     * 배차 데이터 업로드 처리 (DB 업데이트)
     * TM_INPLAN 업데이트 + TM_PLAN_ROUTE 재생성을 하나의 트랜잭션으로 처리
     *
     * @param updateList TM_INPLAN 업데이트용 DTO 리스트
     * @param routeResultsByCarno T-map API 경로 계산 결과 (차량번호 -> T-map 응답)
     * @param dccode 물류센터 코드
     * @param deliveryDate 배송일자 (YYYYMMDD)
     * @param storerkey 고객사 코드
     * @return 업로드 결과
     */
    @Transactional
    public TmDispatchUploadResDto saveDispatchExcel(
            List<TmInplanUpdateDto> updateList,
            Map<String, TmapDispatchRouteResDto> routeResultsByCarno,
            String dccode,
            String deliveryDate,
            String storerkey
    ) {
        log.info("배차 DB 업데이트 시작 - dccode: {}, deliveryDate: {}, storerkey: {}, 총 건수: {}, 차량: {}대",
                dccode, deliveryDate, storerkey, updateList.size(),0);

        // 1. TM_INPLAN 업데이트
        int updatedCount = updateTmInplan(updateList, dccode, deliveryDate, storerkey);

        // 2. 차량번호별 TM_DELIVERYTYPE 맵 생성
        // Map<String, String> deliveryTypeByCarno = updateList.stream()
        //     .filter(dto -> dto.getCarno() != null && dto.getDeliveryType() != null)
        //     .collect(Collectors.toMap(
        //         TmInplanUpdateDto::getCarno,
        //         TmInplanUpdateDto::getDeliveryType,
        //         (existing, replacement) -> existing  // 중복 시 기존 값 유지
        //     ));

        // 3. TM_PLAN_ROUTE 재생성 (삭제 + 삽입)

        //recreatePlanRoute(routeResultsByCarno, deliveryTypeByCarno, dccode, deliveryDate);

        log.info("DB 업데이트 완료 - TM_INPLAN: {}건, TM_PLAN_ROUTE: 재생성 완료", updatedCount);

        return TmDispatchUploadResDto.builder()
                .totalCount(updateList.size())
                .successCount(updatedCount)
                .failCount(0)
                .errors(null)
                .build();
    }

    /**
     * TM_INPLAN 벌크 업데이트
     *
     * @param updateList TM_INPLAN 업데이트용 DTO 리스트
     * @param dccode 물류센터 코드
     * @param deliveryDate 배송일자
     * @param storerkey 고객사 코드
     * @return 업데이트된 건수
     */
    private int updateTmInplan(
            List<TmInplanUpdateDto> updateList,
            String dccode,
            String deliveryDate,
            String storerkey
    ) {
        TmSetDispatchReqDto request = new TmSetDispatchReqDto();
        request.setDccode(dccode);
        request.setDeliveryDate(deliveryDate);
        commonDao.insert(SERVICESETDISPATCH_PREFIX + "saveInitCarPlan", request);

        // truthCustKey 기준 차량리스트 맵 생성 (같은 실착지 내 중복 차량 제거, 첫 등장 순서 유지)
        Map<String, List<String>> vehicleListByTruthCustKey = updateList.stream()
                .filter(dto -> dto.getTruthCustKey() != null && dto.getCarno() != null)
                .collect(Collectors.groupingBy(
                        TmInplanUpdateDto::getTruthCustKey,
                        Collectors.mapping(TmInplanUpdateDto::getCarno,
                                Collectors.collectingAndThen(Collectors.toCollection(LinkedHashSet::new), ArrayList::new))
                ));

        // MERGE 배치 처리용 리스트 수집
        List<TmSavePlanRouteReqDto> mainReqList  = new ArrayList<>();
        List<TmSavePlanRouteReqDto> splitReqList = new ArrayList<>();

        for (int i = 0; i < updateList.size(); i++) {
            TmInplanUpdateDto item = updateList.get(i);
            String carNo        = item.getCarno();
            String truthCustKey = item.getTruthCustKey();

            TmSavePlanRouteReqDto req = new TmSavePlanRouteReqDto();
            req.setDccode(dccode);
            req.setDeliveryDate(deliveryDate);
            req.setTruthCustkey(truthCustKey);
            req.setCarno(carNo);
            req.setContractType(item.getContractType());
            req.setOutGroupCd("10");
            req.setDeliveryPriority(1);
            req.setRoundSeq(item.getPriority());
            req.setSlipno(item.getSlipNo());
            mainReqList.add(req);

            // 분할배송: 같은 실착지에 차량이 2대 이상인 경우
            List<String> vehicles = truthCustKey != null ? vehicleListByTruthCustKey.get(truthCustKey) : null;
            if (vehicles != null && vehicles.size() > 1) {
                int seqIndex = vehicles.indexOf(carNo);
                if (seqIndex >= 0) {
                    TmSavePlanRouteReqDto splitDto = new TmSavePlanRouteReqDto();
                    splitDto.setDccode(dccode);
                    splitDto.setDeliveryDate(deliveryDate);
                    splitDto.setTruthCustkey(truthCustKey);
                    splitDto.setCarno(carNo);
                    splitDto.setSlipno(item.getSlipNo());
                    splitDto.setSlipdt(deliveryDate);
                    splitDto.setSplitDeliverySeq(String.valueOf(seqIndex + 1));
                    splitDto.setTmDeliveryType(item.getDeliveryType() != null ? item.getDeliveryType() : "1");
                    splitDto.setContractType(item.getContractType());
                    splitDto.setStatus("90");
                    splitReqList.add(splitDto);
                }
            }
        }

        // TM_INPLAN MERGE 배치 실행 (500건씩 분리)
        if (!mainReqList.isEmpty()) {
            int batchSize = 500;
            for (int i = 0; i < mainReqList.size(); i += batchSize) {
                List<TmSavePlanRouteReqDto> chunk = mainReqList.subList(i, Math.min(i + batchSize, mainReqList.size()));
                commonDao.update(SERVICEID_PREFIX + "batchUpdateTmInplanNewList", chunk);
                commonDao.update(SERVICEID_PREFIX + "batchUpdateTmInplanNewRtList", chunk);
            }
        }

        // 분할배송 MERGE 배치 실행 (N번 → 1번 왕복)
        if (!splitReqList.isEmpty()) {
            commonDao.update(SERVICEID_PREFIX + "saveExcelTmInplanSplitList", splitReqList);
        }

        // 배차확정 SP 호출 - 차량 + 실착지 기준으로 중복 제거 후 호출
        Map<String, TmSavePlanRouteReqDto> confirmedMap = new LinkedHashMap<>();
        for (TmInplanUpdateDto item : updateList) {
            String key = item.getCarno() + "|" + item.getTruthCustKey();
            if (!confirmedMap.containsKey(key)) {
                TmSavePlanRouteReqDto dto = new TmSavePlanRouteReqDto();
                dto.setDccode(dccode);
                dto.setDeliveryDate(deliveryDate);
                dto.setStorerkey(storerkey);
                dto.setCarno(item.getCarno());
                dto.setRoundSeq(item.getPriority());
                dto.setTruthCustkey(item.getTruthCustKey());
                confirmedMap.put(key, dto);
            }
        }
        for (TmSavePlanRouteReqDto dto : confirmedMap.values()) {
            tmSetDispatchService.updateSaveDispatchConfirmed(dto);
        }

        log.info("TM_INPLAN 벌크 업데이트 완료 - {}건", updateList.size());
        return updateList.size();
    }





    /**
     * TM_PLAN_ROUTE 순서만 저장 (계산 필드 없이)
     * 엑셀 업로드 수동 배차 시 경로 순서만 먼저 저장하고,
     * ETA/거리/시간/경로 등 계산 필드는 추후 재계산 API로 채움
     *
     * @param updateList 엑셀 파싱 결과 (차량번호, 실착지코드, 순서 포함)
     * @param dccode 물류센터 코드
     * @param deliveryDate 배송일자 (YYYYMMDD)
     */
    public void createEmptyPlanRoutes(
            List<TmInplanUpdateDto> updateList,
            String dccode,
            String deliveryDate
    ) {
        // 1. 차량번호별 그룹핑
        Map<String, List<TmInplanUpdateDto>> byCarno = updateList.stream()
                .filter(dto -> dto.getCarno() != null)
                .collect(Collectors.groupingBy(TmInplanUpdateDto::getCarno));

        if (byCarno.isEmpty()) {
            return;
        }

        // 2. 차량번호 기준 end 경로 삭제 (TO_CUSTKEY = 'end')
        List<String> carnoList = new ArrayList<>(byCarno.keySet());
        if (!carnoList.isEmpty()) {
            Map<String, Object> deleteEndParams = new HashMap<>();
            deleteEndParams.put("dccode", dccode);
            deleteEndParams.put("deliveryDate", deliveryDate);
            deleteEndParams.put("carnoList", carnoList);
            int deletedEnd = commonDao.delete(SERVICEID_PREFIX + "deletePlanRouteEnd", deleteEndParams);
            log.info("TM_PLAN_ROUTE end 경로 삭제(CARNO 기준) - {}건", deletedEnd);
        }

        // 3. 기존 경로 삭제 (실착지 TO_CUSTKEY 기준)
        Set<String> toCustkeySet = updateList.stream()
                .map(TmInplanUpdateDto::getTruthCustKey)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        // Oracle IN절 1000건 제한으로 청크 분리 후 삭제
        if (!toCustkeySet.isEmpty()) {
            List<String> toCustkeyList = new ArrayList<>(toCustkeySet);
            int chunkSize = 1000;
            int totalDeleted = 0;
            for (int i = 0; i < toCustkeyList.size(); i += chunkSize) {
                List<String> chunk = toCustkeyList.subList(i, Math.min(i + chunkSize, toCustkeyList.size()));
                Map<String, Object> deleteParams = new HashMap<>();
                deleteParams.put("dccode", dccode);
                deleteParams.put("deliveryDate", deliveryDate);
                deleteParams.put("toCustkeyList", chunk);
                totalDeleted += commonDao.delete(SERVICEID_PREFIX + "deletePlanRoute", deleteParams);
            }
            log.info("TM_PLAN_ROUTE 기존 경로 삭제(TO_CUSTKEY 기준) - {}건", totalDeleted);
        }

        // 4. start, end 생성
        // byCarno를 읽어서 차량별·priority별 그룹 맵 생성 (carno -> (priority -> list))
        Map<String, Map<Integer, List<TmInplanUpdateDto>>> byCarnoAndPriority = new HashMap<>();
        for (Map.Entry<String, List<TmInplanUpdateDto>> e : byCarno.entrySet()) {
            Map<Integer, List<TmInplanUpdateDto>> byPriority = e.getValue().stream()
                    .collect(Collectors.groupingBy(dto -> dto.getPriority() != null ? dto.getPriority() : 0));
            byCarnoAndPriority.put(e.getKey(), byPriority);
        }

        List<TmSavePlanRouteReqDto> planEmptyRouteList = new ArrayList<>();

        for (Map.Entry<String, Map<Integer, List<TmInplanUpdateDto>>> entry : byCarnoAndPriority.entrySet()) {
            for (Map.Entry<Integer, List<TmInplanUpdateDto>> priorityEntry : entry.getValue().entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .collect(Collectors.toList())) {
                List<TmInplanUpdateDto> list = priorityEntry.getValue();
                if (!list.isEmpty()) {
                    TmSavePlanRouteReqDto planRouteReqDto = new TmSavePlanRouteReqDto();
                    planRouteReqDto.setCarno(list.get(0).getCarno());
                    planRouteReqDto.setDccode(dccode);
                    planRouteReqDto.setTcDcCode(dccode);
                    planRouteReqDto.setDeliveryDate(deliveryDate);
                    planRouteReqDto.setDeliveryType("1");
                    planRouteReqDto.setOutGroupCd("10");
                    planRouteReqDto.setRoundSeq(priorityEntry.getKey());
                    planRouteReqDto.setTruthCustkey("start");
                    planEmptyRouteList.add(planRouteReqDto);
                }
            }
        }

        commonDao.insert(SERVICEID_PREFIX + "batchInsertStartPlanRoute", planEmptyRouteList);




        // 5. 빈 경로 데이터 생성 (순서 정보만)
        List<Map<String, Object>> routeDataList = new ArrayList<>();




        for (Map.Entry<String, List<TmInplanUpdateDto>> entry : byCarno.entrySet()) {
            String carno = entry.getKey();
            Set<String> seenCustKeys = new HashSet<>();
            List<TmInplanUpdateDto> stops = entry.getValue().stream()
                    .sorted(Comparator.comparingInt(dto -> dto.getPriority() != null ? dto.getPriority() : 0))
                    .filter(dto -> {
                        String key = dto.getTruthCustKey() != null ? dto.getTruthCustKey() : "";
                        return seenCustKeys.add(key);
                    })
                    .collect(Collectors.toList());



            String previousCustKey = null;
            for (int i = 0; i < stops.size(); i++) {
                TmInplanUpdateDto stop = stops.get(i);

                Map<String, Object> routeData = new HashMap<>();
                routeData.put("DCCODE", dccode);
                routeData.put("DELIVERYDT", deliveryDate);
                routeData.put("CARNO", carno);
                routeData.put("TM_DELIVERYTYPE", "1");
                routeData.put("PRIORITY", stop.getPriority() != null ? stop.getPriority() : (i + 1));
                routeData.put("FROM_CUSTTYPE", (i == 0) ? "D" : "C");
                routeData.put("FROM_CUSTKEY", (i == 0) ? dccode : previousCustKey);
                routeData.put("TO_CUSTTYPE", "C");
                routeData.put("TO_CUSTKEY", stop.getTruthCustKey());
                routeData.put("STOP_CNT", i);

                previousCustKey = stop.getTruthCustKey();
                routeDataList.add(routeData);
            }
        }

        // 6. 빈 경로 INSERT
        if (!routeDataList.isEmpty()) {
            Map<String, Object> insertParams = new HashMap<>();
            insertParams.put("routeDataList", routeDataList);
            commonDao.insert(SERVICEID_PREFIX + "batchInsertEmptyPlanRoute", insertParams);
            log.info("TM_PLAN_ROUTE 빈 경로 INSERT 완료 - {}건", routeDataList.size());
        }
    }






    /**
     * TM_PLAN_ROUTE 재생성 (기존 경로 삭제 + 새로운 경로 삽입)
     *
     * @param routeResultsByCarno 차량별 T-map API 응답
     * @param deliveryTypeByCarno 차량별 TM_DELIVERYTYPE
     * @param dccode 물류센터 코드
     * @param deliveryDate 배송일자
     */
    private void recreatePlanRoute(
            Map<String, TmapDispatchRouteResDto> routeResultsByCarno,
            Map<String, String> deliveryTypeByCarno,
            String dccode,
            String deliveryDate
    ) {
        // 1. TM_PLAN_ROUTE 기존 경로 삭제
        List<String> carnoList = new ArrayList<>(routeResultsByCarno.keySet());
        if (!carnoList.isEmpty()) {
            // 유니크한 TM_DELIVERYTYPE 리스트 생성
            List<String> deliveryTypeList = deliveryTypeByCarno.values().stream()
                    .distinct()
                    .collect(Collectors.toList());

            Map<String, Object> deleteParams = new HashMap<>();
            deleteParams.put("dccode", dccode);
            deleteParams.put("deliveryDate", deliveryDate);
            deleteParams.put("carnoList", carnoList);
            deleteParams.put("deliveryTypeList", deliveryTypeList);

            int deletedCount = commonDao.delete(
                    SERVICEID_PREFIX + "deletePlanRoute",
                    deleteParams
            );
            log.info("TM_PLAN_ROUTE 기존 경로 삭제 완료 - {}건", deletedCount);
        }

        // 2. T-map 응답을 TM_PLAN_ROUTE INSERT용 데이터로 변환
        List<Map<String, Object>> routeDataList = new ArrayList<>();
        for (Map.Entry<String, TmapDispatchRouteResDto> entry : routeResultsByCarno.entrySet()) {
            String carno = entry.getKey();
            TmapDispatchRouteResDto response = entry.getValue();
            String tmDeliveryType = deliveryTypeByCarno.get(carno);

            // T-map 응답 파싱하여 DB 데이터 생성
            List<Map<String, Object>> vehicleRoutes = convertTmapResponseToDbData(
                    response, carno, tmDeliveryType, dccode, deliveryDate
            );
            routeDataList.addAll(vehicleRoutes);
        }

        // 3. TM_PLAN_ROUTE 새로운 경로 INSERT
        int failCount = 0;
        int actualCount=0;
        if (!routeDataList.isEmpty()) {

            try {
                // TODO: handle exception

                Map<String, Object> insertParams = new HashMap<>();
                insertParams.put("routeDataList", routeDataList);

                int insertedCount = commonDao.insert(
                        SERVICEID_PREFIX + "batchInsertPlanRoute",
                        insertParams
                );

                // Oracle PL/SQL 블록에서 -1 반환 시 실제 INSERT 개수로 대체
                actualCount = (insertedCount == -1) ? routeDataList.size() : insertedCount;
            }catch (Exception e) {

                failCount = failCount+1;
                e.printStackTrace();
            }
            log.info("TM_PLAN_ROUTE 새로운 경로 INSERT 완료 - {}건", actualCount);
        }
    }

    /**
     * T-map 응답을 TM_PLAN_ROUTE INSERT용 데이터로 변환
     *
     * @param response T-map API 응답
     * @param carno 차량번호
     * @param tmDeliveryType 배송유형 (TM_DELIVERYTYPE)
     * @param dccode 물류센터 코드
     * @param deliveryDate 배송일자
     * @return TM_PLAN_ROUTE INSERT용 데이터 리스트
     */
    private List<Map<String, Object>> convertTmapResponseToDbData(
            TmapDispatchRouteResDto response,
            String carno,
            String tmDeliveryType,
            String dccode,
            String deliveryDate
    ) {
        List<Map<String, Object>> routeDataList = new ArrayList<>();

        if (response == null || response.getFeatures() == null) {
            return routeDataList;
        }

        // response.getFeatures()를 순회하며 경유지(pointType이 "B"로 시작)만 추출
        String previousViaPointId = null;  // 이전 경유지의 viaPointId 추적

        for (TmapDispatchRouteResDto.Feature feature : response.getFeatures()) {
            if (feature.getProperties() == null) {
                continue;
            }

            String pointType = feature.getProperties().getPointType();
            if (pointType == null || !pointType.startsWith("B")) {
                continue;  // 경유지(B1, B2, ...)가 아니면 스킵
            }

            // LineString feature만 처리 (Point는 스킵하여 PK 중복 방지)
            String geometryType = feature.getGeometry() != null ? feature.getGeometry().getType() : null;
            if (!"LineString".equals(geometryType)) {
                continue;  // Point feature 스킵
            }

            // 경유지별로 Map 생성
            Map<String, Object> routeData = new HashMap<>();
            routeData.put("DCCODE", dccode);
            routeData.put("DELIVERYDT", deliveryDate);
            routeData.put("CARNO", carno);
            routeData.put("TM_DELIVERYTYPE", "1");

            // FROM_CUSTTYPE: B1(첫 경유지)은 DC에서 출발, B2 이후는 CUSTOMER에서 출발
            String fromCustType = "B1".equals(pointType) ? "D" : "C";
            routeData.put("FROM_CUSTTYPE", fromCustType);

            // FROM_CUSTKEY: B1은 DCCODE, B2 이후는 이전 경유지의 viaPointId
            String fromCustKey = "B1".equals(pointType) ? dccode : previousViaPointId;
            routeData.put("FROM_CUSTKEY", fromCustKey);

            String currentViaPointId = feature.getProperties().getViaPointId();
            routeData.put("PRIORITY", feature.getProperties().getIndex());

            // TO_CUSTTYPE: 모든 경유지의 도착지는 CUSTOMER
            routeData.put("TO_CUSTTYPE", "C");
            routeData.put("TO_CUSTKEY", currentViaPointId);

            // DRIVEDISTANCE, DRIVETIM: null일 경우 0으로 처리
            String distance = feature.getProperties().getDistance();
            String time = feature.getProperties().getTime();
            routeData.put("DRIVEDISTANCE", distance != null ? distance : "0");
            routeData.put("DRIVETIM", time != null ? time : "0");

            routeData.put("TO_EXPCT_DATE", feature.getProperties().getArriveTime());
            // 다음 경유지를 위해 현재 viaPointId 저장
            previousViaPointId = currentViaPointId;

            // ROUTE_POLYLINE: feature.geometry.coordinates를 WKT 형식으로 변환
            if (feature.getGeometry() != null && feature.getGeometry().getCoordinates() != null) {
                try {
                    String geomType = feature.getGeometry().getType();
                    Object coordinates = feature.getGeometry().getCoordinates();
                    String wkt = convertCoordinatesToWkt(geomType, coordinates);
                    routeData.put("ROUTE_POLYLINE", wkt);
                } catch (Exception e) {
                    log.warn("경로 좌표 WKT 변환 실패 - carno: {}, viaPointId: {}",
                            carno, feature.getProperties().getViaPointId(), e);
                    routeData.put("ROUTE_POLYLINE", null);
                }
            } else {
                routeData.put("ROUTE_POLYLINE", null);
            }

            routeDataList.add(routeData);
        }

        log.info("T-map 응답 변환 완료 - carno: {}, 경유지: {}개", carno, routeDataList.size());

        return routeDataList;
    }

    /**

     *
     * @param geometryType geometry 타입 (LineString, Point 등)
     * @param coordinates 좌표 데이터
     * @return WKT 형식 문자열 (LINESTRING(...) 또는 POINT(...))
     */
    private String convertCoordinatesToWkt(String geometryType, Object coordinates) {

        if (coordinates == null) {
            return null;
        }

        if ("LineString".equals(geometryType)) {
            // coordinates는 List<List<Double>> 형태
            if (coordinates instanceof List<?> coordList) {
                return coordList.stream()
                        .filter(item -> item instanceof List<?>)
                        .map(item -> {
                            List<?> point = (List<?>) item;
                            if (point.size() >= 2) {
                                // 경도(X), 위도(Y) 순서
                                return String.format("%.8f %.8f",
                                        ((Number) point.get(0)).doubleValue(),
                                        ((Number) point.get(1)).doubleValue());
                            }
                            return null;
                        })
                        .filter(Objects::nonNull)
                        .collect(Collectors.joining(", ", "LINESTRING (", ")"));
            }
        } else if ("Point".equals(geometryType)) {
            // coordinates는 List<Double> 형태
            if (coordinates instanceof List<?> point && point.size() >= 2) {
                // 경도(X), 위도(Y) 순서
                return String.format("POINT (%.8f %.8f)",
                        ((Number) point.get(0)).doubleValue(),
                        ((Number) point.get(1)).doubleValue());
            }
        }

        log.warn("지원하지 않는 geometry 타입: {}", geometryType);
        return null;
    }


    /**
     * updateList를 직선거리 오름차순으로 정렬
     * - 거리 정보가 없는 항목은 마지막에 배치
     */
    private void sortByLinearDistance(List<TmInplanUpdateDto> updateList) {
        updateList.sort(Comparator.comparingDouble(dto ->
                dto.getLinearDistanceKm() != null ? dto.getLinearDistanceKm() : Double.MAX_VALUE));

        log.info("직선거리 오름차순 정렬 완료 - 총 {}건", updateList.size());
    }

    /**
     * 센터-실착지 직선거리(km) 계산
     *
     * @param jsonDto    업로드 JSON DTO (실착지코드 목록 포함)
     * @param dccode     물류센터 코드
     * @param deliveryDate 배송일자 (YYYYMMDD)
     * @return truthCustKey → 직선거리(km) 맵
     */
    private Map<String, Double> calculateLinearDistances(TmDispatchJsonDto jsonDto, String dccode, String deliveryDate) {
        // 1. 센터 좌표 조회
        Map<String, Object> centerReq = new HashMap<>();
        centerReq.put("dccode", dccode);
        TmDispatchCenterInfoResDto centerInfo = commonDao.selectOne(SERVICESETDISPATCH_PREFIX + "getCenterInfo", (Object) centerReq);

        if (centerInfo == null || centerInfo.getLatitude() == null || centerInfo.getLongitude() == null) {
            log.warn("센터 좌표 정보가 없습니다. dccode: {}", dccode);
            return Collections.emptyMap();
        }

        double centerLat, centerLon;
        try {
            centerLat = Double.parseDouble(centerInfo.getLatitude());
            centerLon = Double.parseDouble(centerInfo.getLongitude());
        } catch (NumberFormatException e) {
            log.warn("센터 좌표 파싱 실패. dccode: {}", dccode);
            return Collections.emptyMap();
        }

        // 2. 고유 실착지코드 목록 추출
        List<String> truthCustKeyList = jsonDto.getRows().stream()
                .flatMap(row -> row.getCells().stream())
                .filter(cell -> cell.getColumn() == TmDispatchJsonColumn.TRUTH_CUST_KEY && cell.getValue() != null)
                .map(cell -> (String) cell.getValue())
                .distinct()
                .collect(Collectors.toList());

        if (truthCustKeyList.isEmpty()) {
            return Collections.emptyMap();
        }

        // 3. 실착지 좌표 조회 (Oracle IN절 1000건 제한 대응, 청크 분리)
        List<TmCustomerLocationResDto> custCoords = new ArrayList<>();
        int chunkSize = 1000;
        for (int i = 0; i < truthCustKeyList.size(); i += chunkSize) {
            List<String> chunk = truthCustKeyList.subList(i, Math.min(i + chunkSize, truthCustKeyList.size()));
            Map<String, Object> params = new HashMap<>();
            params.put("dccode", dccode);
            params.put("deliveryDate", deliveryDate);
            params.put("truthCustKeyList", chunk);
            custCoords.addAll(commonDao.selectList(
                    SERVICEID_PREFIX + "selectCustCoordsByTruthCustKeyList", (Object) params));
        }

        // 4. 직선거리 계산
        Map<String, Double> distanceMap = new HashMap<>();
        for (TmCustomerLocationResDto cust : custCoords) {
            if (cust.getLatitude() == null || cust.getLongitude() == null) {
                continue;
            }
            try {
                double custLat = Double.parseDouble(cust.getLatitude());
                double custLon = Double.parseDouble(cust.getLongitude());
                double distanceKm = haversineDistanceKm(centerLat, centerLon, custLat, custLon);
                distanceMap.put(cust.getTruthcustkey(), distanceKm);
            } catch (NumberFormatException e) {
                log.warn("실착지 좌표 파싱 실패. truthCustKey: {}", cust.getTruthcustkey());
            }
        }

        log.info("직선거리 계산 완료 - 센터: {}, 실착지: {}건", dccode, distanceMap.size());
        return distanceMap;
    }

    /**
     * Haversine 공식을 이용한 두 좌표 간 직선거리(km) 계산
     */
    private static double haversineDistanceKm(double lat1, double lon1, double lat2, double lon2) {
        final double R = 6371.0;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    /**
     * Excel 값을 Integer로 안전하게 변환
     */
    public Integer getInteger(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Number) {
            return ((Number) value).intValue();
        }
        if (value instanceof String) {
            String str = ((String) value).trim();
            if (str.isEmpty()) {
                return null;
            }
            try {
                // A. 정수 형태 문자열 변환 시도 (예: "123")
                return Integer.parseInt(str);
            } catch (NumberFormatException e1) {
                // B. 정수 변환 실패 시, 실수 형태 문자열 변환 시도 (예: "1.00", "1.99")
                try {
                    // Double로 파싱
                    double doubleValue = Double.parseDouble(str);

                    // **** 버림(절사) 처리 ****
                    // 방법 1: (int) 캐스팅 (가장 간단)
                    return (int) doubleValue;

                    /*
                     * // 방법 2: Math.floor() 사용 (음수 포함하여 명확한 버림 처리) // return (int)
                     * Math.floor(doubleValue);
                     */

                } catch (NumberFormatException e2) {
                    // 실수 변환까지 최종 실패
                    // log.warn("Integer 변환 실패: {}", value);
                    return null;
                }
            }
        }
        return null;
    }

    /**
     * Excel 값을 Double로 안전하게 변환
     */
    public Double getDouble(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }
        if (value instanceof String) {
            String str = ((String) value).trim();
            if (str.isEmpty()) {
                return null;
            }
            try {
                return Double.parseDouble(str);
            } catch (NumberFormatException e) {
                log.warn("Double 변환 실패: {}", value);
                return null;
            }
        }
        return null;
    }




}
