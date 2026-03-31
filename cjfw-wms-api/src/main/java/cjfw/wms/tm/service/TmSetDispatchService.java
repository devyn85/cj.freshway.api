package cjfw.wms.tm.service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.core.utility.StringUtil;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.tm.client.TmEngineClient;
import cjfw.wms.tm.controller.TmNewEngineDataMapper;
import cjfw.wms.tm.domain.TmPlanOption;
import cjfw.wms.tm.dto.*;
import cjfw.wms.tm.dto.engine.TmEngineRequestDto;
import cjfw.wms.tm.dto.engine.TmEngineResponseDto;
import cjfw.wms.tm.dto.engine.TmEngineRouteDto;
import cjfw.wms.tm.dto.engine.TmEngineStepDto;
import cjfw.wms.tm.mapper.TmDispatchListMapper;
import cjfw.wms.tm.mapper.TmEngineDataMapper;
import cjfw.wms.tm.util.TmPlanCommon;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.io.StringReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static cjfw.wms.tm.constant.TmConstant.*;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : AI Assistant
 * @date : 2025.01.14
 * @description : TM 배차 설정 서비스
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.01.14 AI Assistant          생성
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TmSetDispatchService {

    @Value("${tm.test-mode:false}")
    private boolean testMode;

    // 🔧 DB 연결 환경에서 활성화됨 (DELIVERY_GROUP 문제 해결 완료)
    private final CommonDao commonDao;
    private final TmDispatchListMapper dispatchListMapper;
    private final TmNewEngineDataMapper newDataMapper;
    private final TmPlanEngineRequestService tmPlanEngineRequestService;
    private final TmEngineClient engineClient;
    private final TmEngineDataMapper dataMapper;
    private final CmCommonService cmCommonService;
    private final TmPlanOptionService tmPlanOptionService;
    private final TmPlanEngineResponseService tmPlanEngineResponseService;

    private static final String SERVICEID_PREFIX = "tmSetDispatchService.";
    private static final String PAKAGE_NAME = "SPTM_INPLAN";
    private static final String COMMAND_CREATION_WD = "CREATION_WD";
    private static final String COMMAND_CREATION_RT = "CREATION_RT";
    private static final String MODIFYALLOCCARALL = "MODIFYALLOCCARALL";
    private static final String CONFIRM = "CONFIRM";
    private static final String PRECONFIRM = "PRECONFIRM";
    private static final String CANCEL = "CANCEL";
    private static final String[] SP_CREATION_RT_KEY_LIST = { "DELIVERYDT", "DCCODE", "TRUTHCUSTKEY" };

    private final UserContext userContext;

    /**
     * 배차를 설정합니다. (가배차 - DB 저장 없이 결과만 반환)`
     *
     * @param request 배차 설정 요청
     * @return 배차 설정 응답 (CJ 전처리 서버 구조 기반)
     */
    public TmSetDispatchResDto saveDispatchInit(TmSetDispatchReqDto request) {

        try {
            // 저장전 차량정보 체크 시작(2026.01.11)
            String initCarPlanCheckResult = getInitCarPlanCheck(request);
            if (initCarPlanCheckResult != null) {
                throw new UserHandleException(initCarPlanCheckResult);
            }
            // 저장전 차량정보 체크 종료(2026.01.11)

            if(!"ADJUST".equals(request.getPlanEntryType())) { // 자동배차일때만 TM_INPLAN 생성
                updateTmInPlanAndCarNewTx(request);
            }

            // 유저 설정 배차 옵션 조회
            TmDispatchOptionsReqDto req = ModelMapperUtil.map(request, TmDispatchOptionsReqDto.class);
            TmPlanOption planOption = tmPlanOptionService.getPlanOption(req);

            TmDispatchCenterInfoResDto centetInfo = commonDao.selectOne(SERVICEID_PREFIX + "getCenterInfo", request);

            // 배송 데이터 조회
            List<TmSetDispatchTruthCustResDto> orders = commonDao.selectList(SERVICEID_PREFIX + "getOrdersGroupByDestination", request);

            // 반품 데이터 조회
            List<TmSetDispatchReturnOrderResDto> returnOrders = commonDao.selectList(SERVICEID_PREFIX + "getReturnOrdersGroupByDestination", request);

            // 주문 개수 제한 (임시)
//	    	List<TmSetDispatchTruthCustResDto> orders10 = orders.subList(0, Math.min(500, orders.size()));

            if(ObjectUtils.isEmpty(orders)) {
                throw new UserHandleException("배차 대상 거래처가 존재하지 않습니다.");
            }

            TmDispatchVehicleReqDto vehicleReqDto = new TmDispatchVehicleReqDto();
            vehicleReqDto.setDeliveryDate(request.getDeliveryDate());
            vehicleReqDto.setDcname(centetInfo.getDcname());
            vehicleReqDto.setDccode(request.getDccode());
            vehicleReqDto.setPlanEntryType(request.getPlanEntryType());

            List<TmVehicleInfoDto> vehicles = commonDao.selectList(SERVICEID_PREFIX + "getCenterVehicle", vehicleReqDto);
            if(ObjectUtils.isEmpty(vehicles)) {
                throw new UserHandleException("배차 가능 차량이 존재하지 않습니다.");
            }

            String weightMsg = TmPlanCommon.validateVehicleWeight(vehicles);
            if(weightMsg != null) {
                throw new UserHandleException("차량의 용적량 정보를 확인해주세요. " + weightMsg);
            }

            String result = TmPlanCommon.validateVehicleInfo(vehicles, planOption);
            if (!testMode && result != null) {
                throw new UserHandleException("배차에 필요한 차량 정보가 부족합니다. " + result);
            }

            // 반품 주문 정보 중 배차에 필요한 필수 정보 검증
            TmPlanCommon.validateReturnOrderInfo(returnOrders);

            // 차량 개수 제한 (임시)
//	        List<TmVehicleInfoDto> vehicles10 = vehicles.subList(0, Math.min(50, vehicles.size()));

            List<TmEngineRouteDto> tmEngineRouteDto = commonDao.selectList(SERVICEID_PREFIX + "getRouteVehicle", request);

            if("ADJUST".equals(request.getPlanEntryType())) {
                if(tmEngineRouteDto.isEmpty()){
                    throw new UserHandleException("배차된 정보가 없습니다.");
                }

                TmEngineResponseDto engineResponse = getTmResponseDto(request, tmEngineRouteDto);

                TmDispatchVehicleReqDto tmpCarReqDto = new TmDispatchVehicleReqDto();
                tmpCarReqDto.setDeliveryDate(request.getDeliveryDate());
                tmpCarReqDto.setDccode(request.getDccode());

                List<TmVehicleInfoDto> tmpVehiclesList = commonDao.selectList(SERVICEID_PREFIX + "getTmpCar", tmpCarReqDto);

                vehicles.addAll(tmpVehiclesList);

                // 배차 반품 데이터 조회 (NOT EXISTS 조건으로 인해 일부 반품 주문 누락 이슈로 주석 처리)
                // List<TmSetDispatchTruthCustResDto> returnDispatchorders = commonDao.selectList(SERVICEID_PREFIX + "getReturnDispatchOrders", request);
                // orders.addAll(returnDispatchorders);

                // 경로 내 모든 step ID 추출
                Set<String> allRouteStepIds = tmEngineRouteDto.stream()
                        .flatMap(route -> route.getSteps().stream())
                        .map(TmEngineStepDto::getId)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toSet());

                // returnOrders에서 경로에 포함된 반품 주문을 찾아 orders에 추가
                List<TmSetDispatchReturnOrderResDto> dispatchedReturnOrders = returnOrders.stream()
                        .filter(ro -> allRouteStepIds.contains(ro.getCustKey()))
                        .toList();

                dispatchedReturnOrders.forEach(rtOrder -> {
                    TmSetDispatchTruthCustResDto orderDto = TmSetDispatchReturnOrderResDto.to(rtOrder);
                    if (orderDto != null) {
                        orders.add(orderDto);
                    }
                });

                // 배차된 반품 주문을 returnOrders에서 제거
                Set<String> dispatchedReturnOrderIds = dispatchedReturnOrders.stream()
                        .map(TmSetDispatchReturnOrderResDto::getCustKey)
                        .collect(Collectors.toSet());
                returnOrders = new ArrayList<>(returnOrders);
                returnOrders.removeIf(ro -> dispatchedReturnOrderIds.contains(ro.getCustKey()));
                // Jobs 방식 후처리 (TmPlanEngineResponseService 사용)
                TmSetDispatchResDto response = tmPlanEngineResponseService.createSuccessResponse(request, engineResponse, vehicles, orders, returnOrders, null);

                // 재계산 필요 차량 플래그 설정 (Forward Orphan + Stale Route 감지)
                List recalcCarnos = commonDao.selectList(SERVICEID_PREFIX + "getRecalcRequiredVehicles", request);

                if (!recalcCarnos.isEmpty()) {
                    Set<String> recalcCarnoSet = new HashSet<>(recalcCarnos);

                    // 응답 vehicles를 mutable 리스트로 복사
                    List<TmVehicleDispatchInfoDto> mutableVehicles = new ArrayList<>(response.getVehicles());

                    Set<String> existingCarnos = mutableVehicles.stream()
                            .map(TmVehicleDispatchInfoDto::getCarno)
                            .collect(Collectors.toSet());

                    // 기존 차량에 플래그 설정
                    mutableVehicles.stream()
                            .filter(v -> recalcCarnoSet.contains(v.getCarno()))
                            .forEach(v -> v.setNeedRecalculation(true));

                    // 응답에 없는 차량 → stub 생성 (orphan-only 차량이 getRouteVehicle에도 없는 경우)
                    Map<String, TmVehicleInfoDto> vehicleInfoMap = toVehicleMap(vehicles);
                    for (Object carnoObj : recalcCarnos) {
                        String carno = (String) carnoObj;
                        if (!existingCarnos.contains(carno)) {
                            TmVehicleInfoDto info = vehicleInfoMap.get(carno);
                            if (info != null) {
                                TmVehicleDispatchInfoDto stub = TmVehicleDispatchInfoDto.builder()
                                        .carno(carno)
                                        .needRecalculation(true)
                                        .contractType(info.getContractType())
                                        .vehicleType(info.getContractTypeNm())
                                        .outGroupCd(info.getOutGroupCd())
                                        .drivername(info.getDrivername())
                                        .phone1(info.getPhone1())
                                        .steps(new ArrayList<>())
                                        .orderCount(0)
                                        .roundSeq(1)
                                        .build();
                                stub.createUniqueId();
                                mutableVehicles.add(stub);
                            }
                        }
                    }

                    response.setVehicles(mutableVehicles);
                }

                return response;

            }else{

               /* String otdMsg = TmPlanCommon.validateOtdTime(orders, request.getDeliveryDate());
                if(otdMsg != null) {
                    throw new UserHandleException("고객 배송정보의 배송메모/알림/검수정보의 건물 개방시간, 납품가능시간 OTD시간을 확인해주세요. " + otdMsg);
                }*/

                // 4. Engine Request DTO 구성 (Jobs 방식)
                TmEngineRequestDto engineRequest = tmPlanEngineRequestService.createEngineRequest(orders, vehicles, centetInfo, planOption, request);

                // 5. Engine 호출
                TmEngineResponseDto engineResponse = engineClient.callOptimization(engineRequest);
                log.info("Engine 호출 완료");

                engineResponse.getRoutes().stream().forEach(route -> {
                    route.getSteps().stream().forEach(step -> {
                        if("end".equals(step.getType())){
                            step.setGeometry(null);
                        }
                    });
                });


                // 7. 최초 엔진정보 기반 PRECARNO 할당 (TM_INPLAN 착지 기준 PRECARNO 컬럼 업데이트)
                // 배차 저장/확정 시 업데이트 하도록 변경
//            updateEngineResult(request, engineResponse);
                log.info("가배차 완료 - DB 저장 없이 결과 반환");
                engineResponse.setOnMaxWeight(planOption.isOnMaxWeight());
                // Jobs 방식 후처리 (TmPlanEngineResponseService 사용)
                return tmPlanEngineResponseService.createSuccessResponse(request, engineResponse, vehicles, orders, returnOrders, null);
            }
        } catch(UserHandleException e) {
            throw e;
        } catch(Exception e) {
            throw new UserHandleException("MSG.COM.ERR.999",new String[] {"자동배차가 정상적으로 완료되지 않았습니다. 다시 시도해주세요."}, e);
        }
    }

    private void updateTmInPlanAndCarNewTx(TmSetDispatchReqDto request) {
        // 프로시저 응답 상태값
        String resultCode;
        String returnMessage;
        String resultMessage;

        // 프로시저 호출 (SPTM_INPLAN.CREATION_WD)
        // Note: SP가 TM_INPLAN.SPLIT_DELIVERY_YN을 'N'으로 리셋하지만,
        // getRouteStep 쿼리가 TM_INPLAN_SPLIT 존재 여부로 분할배송을 판단하므로 문제없음
        ProcedureParametersFactory.initParamDto(request, request, PAKAGE_NAME);
        request.setAvc_COMMAND(COMMAND_CREATION_WD);
        request.setAvc_DCCODE(request.getDccode());

        // 반품 고려 (실물유무, 거래처 좌표 갱신)

        String[] keyList = { "DELIVERYDT", "DCCODE", "STORERKEY" };
        Object[] valueList = { request.getDeliveryDate(), request.getDccode(), request.getStorerkey()};

        request.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
        int rv = cmCommonService.saveProcedure(request);

        log.info("rv->{}", rv);

        // SPID
        // 프로시저 OUT Parameter(3/4)
        resultCode    = StringUtil.nvl(request.getResultCode());
        resultMessage = StringUtil.nvl(request.getResultMessage());
        returnMessage = StringUtil.nvl(request.getReturnMessage());

        log.info("resultCode->{}", resultCode);
        log.info("resultMessage->{}", resultMessage);
        log.info("returnMessage->{}", returnMessage);

        // 최초 차량 옵션 설정 저장
        commonDao.insert(SERVICEID_PREFIX + "saveInitCarPlan", request);
    }

    /**
     * @description : 실비차 배차 요청
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.27 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
     */
    public TmSetDispatchResDto createOutGroupDispatch(TmSetDispatchOutGroupReqDto request) {

        // 실비차 데이터 GET
        log.info("배차 설정 시작 - ID: {}, 배송일자: {}", request.getRegId(), request.getDeliveryDate());

        // 센터권역 dccode로 요청되지 않는중
        request.setDccode(request.getDccode() != null ? request.getDccode() : request.getGDccode());
        if(request.getTmDeliveryType() == null){
            request.setTmDeliveryType("1");
        }

        try {

            // 미배차 거래처 정보 기준 truthCustkey SET 생성 ( 착지기준 거래처 조회용 )
            Set<String> idSet = request.getUnAssignedOrderList().stream()
                    .map(TmSetDispatchUnassignedOrderResDto::getId)
                    .collect(Collectors.toSet());

            request.setCustCodeList(idSet.toArray(new String[0]));

            // 실비차 배차 시, 미배차 주문 건수가 1000건 이상인 경우 처리 보완
            List<String> list = Arrays.asList(request.getCustCodeList());
            List<List<String>> chunks = IntStream.range(0, (list.size() + 999) / 1000)
                    .mapToObj(i -> list.subList(i * 1000, Math.min((i + 1) * 1000, list.size())))
                    .toList();
            request.setChunkedCustCodeList(chunks);

            List<TmSetDispatchTemporaryCarResDto> temporaryCarList = commonDao.selectList(SERVICEID_PREFIX + "getTemporaryCarByOutGroup", request);

            if(temporaryCarList == null || temporaryCarList.isEmpty()) {
                throw new UserHandleException("배차 가능 차량이 존재하지 않습니다.");
            }

            // 착지 기준 DB 거래처 데이터 조회
            List<TmSetDispatchTruthCustResDto> orders = commonDao.selectList(SERVICEID_PREFIX + "getOrdersGroupByDestination", request);

            if(orders == null || orders.isEmpty()) {
                throw new UserHandleException("배차 대상 거래처가 존재하지 않습니다.");
            }

            // 미할당 주문들
            List<TmSetDispatchUnassignedOrderResDto> orderDtoList = request.getUnAssignedOrderList();

            // 착지 기준 DB 거래처 데이터로부터 미할당 주문들 가공 (엔진요청 otdTime)
            Map<String, TmSetDispatchTruthCustResDto> orderMap = orders.stream()
                    .collect(Collectors.toMap(
                            TmSetDispatchTruthCustResDto::getTruthCustKey,
                            o -> o,
                            (oldValue, newValue) -> newValue
                    ));

            for (TmSetDispatchUnassignedOrderResDto unOrder : orderDtoList) {
                TmSetDispatchTruthCustResDto source = orderMap.get(unOrder.getId());
                if (source == null) continue;

                if (source.getOtdTimeFrom() != null) {
                    unOrder.setOtdTimeFrom(source.getOtdTimeFrom());
                }

                if (source.getOtdTimeTo() != null) {
                    unOrder.setOtdTimeTo(source.getOtdTimeTo());
                }
            }
            // 미할당 주문 가공 끝


            TmDispatchCenterInfoResDto centerInfo = commonDao.selectOne(SERVICEID_PREFIX + "getCenterInfo", request);
//               옵션 정보 필요
            TmEngineRequestDto engineRequest = newDataMapper.buildTemporaryCarEngineRequest(temporaryCarList, orderDtoList, centerInfo, request.getDeliveryDate(), request);

            Map<String, TmPlanEtaStepReqDto> splitOrderMap = new HashMap<>();

            // 미배차 거래처 기준 거래처 MAP 생성 (분할배차 정보 포함)
            request.getUnAssignedOrderList().stream().forEach(order -> {
                if(order.getId() != null) {
                    Integer splitSeq = 0;
                    if("Y".equals(order.getSplitDeliveryYn())) {
                        splitSeq = Integer.parseInt(order.getSplitDeliverySeq()) + 1;
                        TmPlanEtaStepReqDto orderInfo = ModelMapperUtil.map(order, TmPlanEtaStepReqDto.class);
                        splitOrderMap.put(order.getId() + "-" + splitSeq.toString(), orderInfo);
                    }
                }
            });

            // 엔진 response 데이터 매핑용 실비차 차량정보 LIST 생성 ( maxCube, maxWeight, outGroupCd, carno, contractType, carno)
            List<TmVehicleInfoDto> vehicleInfo = temporaryCarList.stream().map(temporaryCar -> {
                return TmVehicleInfoDto.builder()
                        .maxCube(Double.parseDouble(temporaryCar.getMaxCbm()))
                        .maxWeight(Double.parseDouble(temporaryCar.getMaxWeight()))
                        .maxLanding(temporaryCar.getMaxLoadQty())
                        .outGroupCd(temporaryCar.getOutGroupCd())
                        .contractType(CONTRACT_TYPE_TEMPORARY)
                        .contractTypeNm("실비")
                        .carno(temporaryCar.getId())
                        .build();
            }).toList();

            TmEngineResponseDto engineResponse = engineClient.callOptimization(engineRequest);

            log.info("Engine 호출 완료");

            // 엔진이 실제 사용한 차량 수 기준으로 SEQ 채번
            int usedCarCount = (engineResponse.getRoutes() != null) ? engineResponse.getRoutes().size() : 0;

            if (usedCarCount > 0) {
                Map<String, Object> seqParam = new HashMap<>();
                seqParam.put("dccode", request.getDccode());
                seqParam.put("deliveryDate", request.getDeliveryDate());
                seqParam.put("tmDeliveryType", request.getTmDeliveryType());
                seqParam.put("totalCarCount", usedCarCount);
                seqParam.put("gUserId", request.getGUserId());

                commonDao.update(SERVICEID_PREFIX + "upsertActCostCarSeq", seqParam);
                int currentSeq = (int) commonDao.selectOne(SERVICEID_PREFIX + "getActCostCarSeq", seqParam);
                int startSeq = currentSeq - usedCarCount;

                // 엔진 응답의 임시 차량ID → "실비차N" 리매핑
                Map<String, String> idRemap = new HashMap<>();
                int seq = startSeq + 1;
                for (TmEngineRouteDto route : engineResponse.getRoutes()) {
                    String newId = "실비차" + seq++;
                    idRemap.put(route.getVehicle(), newId);
                    route.setVehicle(newId);
                }

                // vehicleInfo의 carno도 리매핑
                vehicleInfo = vehicleInfo.stream()
                        .filter(v -> idRemap.containsKey(v.getCarno()))
                        .peek(v -> v.setCarno(idRemap.get(v.getCarno())))
                        .toList();
            }

            log.info("실비차 가배차 완료 - DB 저장 없이 결과 반환");

            TmPlanEtaOptimizeAutoReqDto dto = TmPlanEtaOptimizeAutoReqDto.builder()
                    .dccode(request.getDccode())
                    .deliveryDate(request.getDeliveryDate())
                    .tmDeliveryType(request.getTmDeliveryType())
                    .build();

            // Jobs 방식 후처리 (TmPlanEngineResponseService 사용)
            return tmPlanEngineResponseService.createSuccessResponse(dto, engineResponse, vehicleInfo, orders, splitOrderMap);
        } catch(UserHandleException e) {
            throw e;
        } catch(Exception e) {
            throw new UserHandleException("MSG.COM.ERR.999",new String[] {"자동배차가 정상적으로 완료되지 않았습니다. 다시 시도해주세요."}, e);
        }
    }

    /**
     * @description : 배차 저장 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.13 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
     */
    public String saveDispatch(TmPlanReqDto request) {
        // 이전 배차 결과 DEL_YN = Y 변경
        if(request.getTmDeliveryType() == null){
            request.setTmDeliveryType("1");
        }
        if (request.isPartialSave()) {
            // guard: 변경 대상이 없으면 cancel/insert 없이 즉시 리턴
            boolean hasVehicles = request.getVehicles() != null && request.getVehicles().length > 0;
            boolean hasUnassigned = request.getUnassignedOrders() != null && request.getUnassignedOrders().length > 0;
            if (!hasVehicles && !hasUnassigned) {
                return CanalFrameConstants.MSG_COM_SUC_CODE;
            }

            // 1. 차량 목록 추출
            List<String> carnoList = Arrays.stream(request.getVehicles())
                    .map(TmPlanReqDto.Vehicle::getCarno)
                    .distinct().collect(Collectors.toList());

            // 2. 주문 스코프 구성: request steps + unassignedOrders
            List<String> cancelScope = Arrays.stream(request.getVehicles())
                    .flatMap(v -> Arrays.stream(v.getSteps()))
                    .filter(s -> STEP_TYPE_DELIVERY.equals(s.getType()))
                    .map(TmPlanEtaStepReqDto::getId)
                    .distinct().collect(Collectors.toList());

            if (request.getUnassignedOrders() != null) {
                for (String key : request.getUnassignedOrders()) {
                    if (!cancelScope.contains(key)) {
                        cancelScope.add(key);
                    }
                }
            }

            // 3. cancel 파라미터 구성 (Oracle IN절 1000개 제한 대응: 청크 분할)
            int chunkSize = 1000;

            List<List<String>> carnoChunks = new ArrayList<>();
            for (int i = 0; i < carnoList.size(); i += chunkSize) {
                carnoChunks.add(carnoList.subList(i, Math.min(i + chunkSize, carnoList.size())));
            }

            List<List<String>> cancelScopeChunks = new ArrayList<>();
            for (int i = 0; i < cancelScope.size(); i += chunkSize) {
                cancelScopeChunks.add(cancelScope.subList(i, Math.min(i + chunkSize, cancelScope.size())));
            }

            Map<String, Object> cancelParams = new HashMap<>();
            cancelParams.put("dccode", request.getDccode());
            cancelParams.put("deliveryDate", request.getDeliveryDate());
            cancelParams.put("tmDeliveryType", request.getTmDeliveryType());
            cancelParams.put("carnoChunks", carnoChunks);
            cancelParams.put("cancelScopeChunks", cancelScopeChunks);

            commonDao.update(SERVICEID_PREFIX + "cancelDispatchPlanRoute", cancelParams);
            commonDao.update(SERVICEID_PREFIX + "cancelSetDispatchInplan", cancelParams);
            commonDao.update(SERVICEID_PREFIX + "updateInitSetDispatchInplanSplit", cancelParams);
        } else {
            // 전체 cancel — Map으로 통일 (carnoChunks/cancelScopeChunks 미세팅 → null → SQL에서 스킵)
            Map<String, Object> cancelParams = new HashMap<>();
            cancelParams.put("dccode", request.getDccode());
            cancelParams.put("deliveryDate", request.getDeliveryDate());
            cancelParams.put("tmDeliveryType", request.getTmDeliveryType());

            commonDao.update(SERVICEID_PREFIX + "cancelDispatchPlanRoute", cancelParams);
            commonDao.update(SERVICEID_PREFIX + "cancelSetDispatchInplan", cancelParams);
            commonDao.update(SERVICEID_PREFIX + "updateInitSetDispatchInplanSplit", cancelParams);
        }

        // 차량 정보 가져오기
        TmDispatchVehicleReqDto vehicleReqDto = new TmDispatchVehicleReqDto();
        vehicleReqDto.setDeliveryDate(request.getDeliveryDate());
        vehicleReqDto.setDccode(request.getDccode());
        List<TmVehicleInfoDto> vehiclesList = commonDao.selectList(SERVICEID_PREFIX + "getCenterVehicle", vehicleReqDto);

        Map<String, TmVehicleInfoDto> vehicleInfoDtoMap = toVehicleMap(vehiclesList);

        insertTmInPlan4Return(request);

        // 벌크 저장을 위한 리스트 시작
        int batchSize = 500;
        List<TmSavePlanRouteReqDto> planEmptyRouteList = new ArrayList<>();
        List<TmSavePlanRouteReqDto> planRouteList = new ArrayList<>();
        List<TmSavePlanRouteReqDto> tmInplanList = new ArrayList<>();
        List<TmSavePlanRouteReqDto> tmInplanRtList = new ArrayList<>();
        List<TmSavePlanRouteReqDto> splitInplanList = new ArrayList<>(); // 분할배차 정보 리스트
        // 벌크 저장을 위한 리스트 종료

        // TRUTHCUSTKEY(착지) 기준으로 TM_INPLAN 주문 조회
        // CARNO - TRUTHCUSTKEY 매핑하여 PLAN_ROUTE 에 저장
        for(TmPlanReqDto.Vehicle vehicle : request.getVehicles()) {
            int dlvDeliveryPriority = 0;

            String tcdcCode = request.getDccode();
            TmVehicleInfoDto vehicleInfoDto = vehicleInfoDtoMap.get(vehicle.getCarno());

            if(vehicleInfoDto != null){
                if(!ObjectUtils.isEmpty(vehicleInfoDto.getTcDcCode())){
                    tcdcCode = vehicleInfoDto.getTcDcCode();
                }
            }

            for(TmPlanEtaStepReqDto stepDto : vehicle.getSteps()) {
                if(STEP_TYPE_DELIVERY.equals(stepDto.getType())) dlvDeliveryPriority++;
                TmSavePlanRouteReqDto planRouteReqDto = new TmSavePlanRouteReqDto();
                planRouteReqDto.setCarno(vehicle.getCarno());
                planRouteReqDto.setDccode(request.getDccode());
                planRouteReqDto.setOutGroupCd(vehicle.getOutGroupCd());
                planRouteReqDto.setDeliveryDate(request.getDeliveryDate());
                planRouteReqDto.setDriveDistance(stepDto.getDistance());
                planRouteReqDto.setDriveTime(stepDto.getDuration());
                planRouteReqDto.setEta(stepDto.getArrival());
                planRouteReqDto.setTruthCustkey(stepDto.getId());
                planRouteReqDto.setCustType(stepDto.getCustType());
                planRouteReqDto.setStorerkey(stepDto.getStorerkey());
                planRouteReqDto.setDeliveryType(stepDto.getTmDeliveryType());
                planRouteReqDto.setSplitDeliverySeq(stepDto.getSplitDeliverySeq());
                planRouteReqDto.setSplitDeliveryYn(stepDto.getSplitDeliveryYn());
                planRouteReqDto.setSlipline(stepDto.getSlipline());
                planRouteReqDto.setSlipno(stepDto.getSlipno());
                planRouteReqDto.setSlipdt(stepDto.getSlipdt());
                planRouteReqDto.setOrderQty(stepDto.getOrderQty());
                planRouteReqDto.setCube(stepDto.getCube());
                planRouteReqDto.setWeight(stepDto.getWeight());
                planRouteReqDto.setTcDcCode(tcdcCode);

                // step roundSeq가 2회전인데 1회전 들어가기 때문에 차량에서 가져옴
                int roundSeq = Optional.ofNullable(vehicle.getRoundSeq())
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .map(s -> {
                            try {
                                return Integer.parseInt(s);
                            } catch (NumberFormatException e) {
                                return 1;
                            }
                        })
                        .orElse(1);

                planRouteReqDto.setRoundSeq(roundSeq);
                planRouteReqDto.setDlvDeliveryPriority(dlvDeliveryPriority);
                planRouteReqDto.setDeliveryPlanDt(TmPlanCommon.convertEta(stepDto.getArrival(), KST));
                if(stepDto.getSplitDeliveryYn() == null || stepDto.getSplitDeliveryYn().isEmpty()) {
                    stepDto.setSplitDeliveryYn("N");
                    planRouteReqDto.setSplitDeliveryYn("N"); // planRouteReqDto도 함께 업데이트
                }
                planRouteReqDto.setStatus(request.isPreDispatch() ? "50" : "90");
                planRouteReqDto.setContractType(vehicle.getContractType());
                planRouteReqDto.setTmDeliveryType(stepDto.getTmDeliveryType());

                // 경로 폴리라인 디코딩 후 저장
                if(stepDto.getGeometry() != null) {
                    List<double[]> decodePolyline = TmNewEngineDataMapper.decode(stepDto.getGeometry());
                    String wktLineString = newDataMapper.toWktLineString(decodePolyline);
                    planRouteReqDto.setGeometry(new StringReader(wktLineString));
                    // 경로 폴리라인이 없을경우 Point 저장
                } else if(stepDto.getLocation() != null) {
                    String wktPointString = newDataMapper.toWktPoint(stepDto.getLocation());
                    planRouteReqDto.setGeometry(new StringReader(wktPointString));
                }
                /* 수동 분할 배차인 경우 - TM_INPLAN 업데이트 후 저장을 위해 리스트에 추가 */
                if ("Y".equals(planRouteReqDto.getSplitDeliveryYn())) {
                    // splitItems가 있는 경우 각 항목별로 리스트에 추가
                    if (stepDto.getSplitItems() != null && !stepDto.getSplitItems().isEmpty()) {
                        for (TmSplitItemDto splitItem : stepDto.getSplitItems()) {
                            TmSavePlanRouteReqDto splitDto = new TmSavePlanRouteReqDto();
                            splitDto.setCarno(planRouteReqDto.getCarno());
                            splitDto.setDccode(planRouteReqDto.getDccode());
                            splitDto.setDeliveryDate(planRouteReqDto.getDeliveryDate());
                            splitDto.setTruthCustkey(planRouteReqDto.getTruthCustkey());
                            splitDto.setTmDeliveryType(planRouteReqDto.getTmDeliveryType());
                            // 상품별 SEQ 사용 (클라이언트에서 전달받은 값)
                            splitDto.setSplitDeliverySeq(splitItem.getSplitDeliverySeq());
                            splitDto.setSplitDeliveryYn(planRouteReqDto.getSplitDeliveryYn());
                            splitDto.setStatus(planRouteReqDto.getStatus());
                            splitDto.setContractType(planRouteReqDto.getContractType());
                            splitDto.setDlvDeliveryPriority(planRouteReqDto.getDlvDeliveryPriority());
                            splitDto.setRoundSeq(planRouteReqDto.getRoundSeq());
                            // splitItem에서 가져오는 값
                            splitDto.setSlipno(splitItem.getSlipno());
                            splitDto.setSlipline(splitItem.getSlipline());
                            splitDto.setSlipdt(splitItem.getSlipdt());
                            splitDto.setOrderQty(splitItem.getOrderQty());
                            splitDto.setCube(splitItem.getCube());
                            splitDto.setWeight(splitItem.getWeight());
                            splitInplanList.add(splitDto);
                        }
                    } else {
                        // splitItems가 없는 경우 기존 방식으로 리스트에 추가 (하위호환성 유지)
                        TmSavePlanRouteReqDto splitDto = ModelMapperUtil.map(planRouteReqDto, userContext, TmSavePlanRouteReqDto.class);
                        splitInplanList.add(splitDto);
                    }
                }

                // 테스트용 추후 제거
                if(request.getTmDeliveryType().equals("WD")||planRouteReqDto.getDeliveryType() == null) {
                    // 경로 저장시에는 tmDeliveryType을 배차유형으로 처리 / 배송,반품 일괄 1로 처리
                    planRouteReqDto.setDeliveryType("1");
                }

                TmSavePlanRouteReqDto dto = ModelMapperUtil.map(planRouteReqDto, userContext, TmSavePlanRouteReqDto.class);

                // 시작점, 종료점 DB 저장
                if("start".equals(stepDto.getType())){
                    planRouteReqDto.setTruthCustkey("start");
                    // commonDao.insert(SERVICEID_PREFIX + "saveEmptyPlanRoute", dto);
                    planEmptyRouteList.add(dto);
                    if (planEmptyRouteList.size() == batchSize) {
                        commonDao.insert(SERVICEID_PREFIX + "saveEmptyPlanRouteBulk", planEmptyRouteList);
                        planEmptyRouteList.clear();
                    }
                } else if ("end".equals(stepDto.getType())) {
                    planRouteReqDto.setTruthCustkey("end");
                    // commonDao.insert(SERVICEID_PREFIX + "saveEmptyPlanRoute", dto);
                    planEmptyRouteList.add(dto);
                    if (planEmptyRouteList.size() == batchSize) {
                        commonDao.insert(SERVICEID_PREFIX + "saveEmptyPlanRouteBulk", planEmptyRouteList);
                        planEmptyRouteList.clear();
                    }
                } else {
                    // commonDao.insert(SERVICEID_PREFIX + "savePlanRoute", dto);
                    planRouteList.add(dto);

                    if (planRouteList.size() == batchSize) {
                        commonDao.insert(SERVICEID_PREFIX + "savePlanRouteBulk", planRouteList);
                        planRouteList.clear();
                    }

                    // commonDao.update(SERVICEID_PREFIX + "updateTmInplanStatus", dto);
                    tmInplanList.add(dto);
                    if (tmInplanList.size() == batchSize) {
                        commonDao.update(SERVICEID_PREFIX + "updateTmInplanStatusBulk", tmInplanList);
                        tmInplanList.clear();
                    }

                    // commonDao.update(SERVICEID_PREFIX + "updateTmInplanStatusRt", dto);
                    tmInplanRtList.add(dto);
                    if (tmInplanRtList.size() == batchSize) {
                        commonDao.update(SERVICEID_PREFIX + "updateTmInplanStatusRtBulk", tmInplanRtList);
                        tmInplanRtList.clear();
                    }

                    // 배차저장, 확정 SP
                    if (request.isPreDispatch()) {
                        updatePreConfirmed(dto);
                    } else {
                        updateSaveDispatchConfirmed(dto);
                    }
                }
            }
        }

        if (!planEmptyRouteList.isEmpty()) {
            commonDao.insert(SERVICEID_PREFIX + "saveEmptyPlanRouteBulk", planEmptyRouteList);
        }

        if (!planRouteList.isEmpty()) {
            commonDao.insert(SERVICEID_PREFIX + "savePlanRouteBulk", planRouteList);
        }

        if (!tmInplanList.isEmpty()) {
            commonDao.update(SERVICEID_PREFIX + "updateTmInplanStatusBulk", tmInplanList);
        }

        if (!tmInplanRtList.isEmpty()) {
            commonDao.update(SERVICEID_PREFIX + "updateTmInplanStatusRtBulk", tmInplanRtList);
        }

        // TM_INPLAN 업데이트 완료 후 TM_INPLAN_SPLIT 저장 (데이터 정합성 보장)
        if (!splitInplanList.isEmpty()) {
            for (TmSavePlanRouteReqDto splitDto : splitInplanList) {
                commonDao.update(SERVICEID_PREFIX + "saveTmInplanSplit", splitDto);
            }
        }

        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

    private List<TmPlanEtaStepReqDto> getReturnOrders(TmPlanReqDto request) {
        List<TmPlanEtaStepReqDto> rtList = new ArrayList<>();
        for(TmPlanReqDto.Vehicle vehicle : request.getVehicles()) {
            for (TmPlanEtaStepReqDto stepDto : vehicle.getSteps()) {
                if (TM_DELIVERY_TYPE_RETURN.equals(stepDto.getTmDeliveryType()) &&
                        STEP_TYPE_DELIVERY.equals(stepDto.getType())) {
                    rtList.add(stepDto);
                }
            }
        }
        return rtList;
    }

    /**
     * only 반품건만 있는 경우 재계산 결과 저장 및 확정 시,
     * TM_INPLAN 데이터 생성 위해 별도 SP 호출
     */
    private void insertTmInPlan4Return(TmPlanReqDto request) {
        List<TmPlanEtaStepReqDto> rtList = getReturnOrders(request);

        if (rtList.isEmpty()) return;
        log.info("[{}][{}] 반품 주문 건 수: {}", request.getDccode(), request.getDeliveryDate(), rtList.size());
        for (TmPlanEtaStepReqDto rt : rtList) {
            insertTmInplan4Rt(request, rt.getId());
        }
    }

    /**
     *
     * @param request       배차 저장 요청
     * @param truthCustkey  실착지 코드
     */
    private void insertTmInplan4Rt(TmPlanReqDto request, String truthCustkey) {
        // 프로시저 응답 상태값
        String resultCode;
        String returnMessage;
        String resultMessage;

        // 프로시저 호출 (SPTM_INPLAN.CREATION_RT)
        ProcedureParametersFactory.initParamDto(request, request, PAKAGE_NAME);
        request.setAvc_COMMAND(COMMAND_CREATION_RT);
        request.setAvc_DCCODE(request.getDccode());

        // 반품 고려 (실물유무, 거래처 좌표 갱신)

        Object[] valueList = { request.getDeliveryDate(), request.getDccode(), truthCustkey };

        request.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(SP_CREATION_RT_KEY_LIST, valueList));
        int rv = cmCommonService.saveProcedure(request);

        log.info("rv->{}", rv);

        // SPID
        // 프로시저 OUT Parameter(3/4)
        resultCode    = StringUtil.nvl(request.getResultCode());
        resultMessage = StringUtil.nvl(request.getResultMessage());
        returnMessage = StringUtil.nvl(request.getReturnMessage());

        log.info("resultCode->{}", resultCode);
        log.info("resultMessage->{}", resultMessage);
        log.info("returnMessage->{}", returnMessage);
    }

    /**
     * @description : 배차 가능 차량 조회 
     * @issues :<pre> 
     * -----------------------------------------------------------  
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.17 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
     */
    public List<TmDispatchPlanCarResDto> getPlanCar(TmDispatchPlanCarReqDto dto) {
        if(dto.getSearchKeyword() != null && !dto.getSearchKeyword().isEmpty()) {
            dto.setSearchCarno(dto.getSearchKeyword().split(","));
        }

        // 자차, 실비차 제외
        dto.setSearchContractType(List.of("DELIVERY","MONTHLY","FIX","FIXTEMPORARY"));
        return commonDao.selectList(SERVICEID_PREFIX + "getCarPlanList", dto);
    }

    /**
     * @description : 주행 가능한 차량 상태 저장 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.17 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
     */
    public String savePlanCar(List<TmDispatchPlanCarReqDto> dtoList) {
        for(TmDispatchPlanCarReqDto dto : dtoList) {
            if(CanalFrameConstants.INSERT.equals(dto.getRowStatus()) || CanalFrameConstants.UPDATE.equals(dto.getRowStatus())) {
                commonDao.selectList(SERVICEID_PREFIX + "saveCarPlan", dto);
            }
        }
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }




    /**
     * 배차 목록을 조회합니다. (개선된 검색 조건 지원)
     */
    public TmGetDispatchListResDto getDispatchList(TmGetDispatchListReqDto request) {
        // 1. 요청 유효성 검증
        request.setCarnoListToArray();

        // 3. 배차 목록 조회
        List<TmDispatchInfoListResDto> dispatchList = dispatchListMapper.selectDispatchList(request);
        TmGetDispatchListResDto response = new TmGetDispatchListResDto();
        response.setDispatchList(dispatchList);

        return response;
    }

    /**
     * @description : 배차 확정 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.09.23 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
     */
    public String updateConfirmed(List<TmDispatchConfirmedReqDto> requestList) {
        for(TmDispatchConfirmedReqDto request : requestList) {
            TmDispatchConfirmedReqDto dto = ModelMapperUtil.map(request, userContext, TmDispatchConfirmedReqDto.class);

//    		commonDao.update(SERVICEID_PREFIX + "updateConfirmed", dto);
            commonDao.update(SERVICEID_PREFIX + "updateConfirmedForSplit", dto);

            // 배차확정 SP
            updateConfirmed(dto);
        }
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

    /**
     * @description : 배차 취소
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.31 OhEunBeom          생성 </pre>
     */
    @Transactional
    public String cancelDispatch(TmCancelDispatchReqDto request) {
        try {

            // 배차취소 SP
            String type = request.getTmDeliveryType();
            switch (type) {
                case "1":
                    for (String docType : new String[]{"WD", "RT"}) {
                        request.setDocType(docType);
                        updateCanceled(request);
                    }
                    break;
                case "3":
                case "4":
                    request.setDocType("DP");
                    updateCanceled(request);
                    break;
                case "6":
                    request.setDocType("WD");
                    updateCanceled(request);
                    break;
                default:
                    break;
            }

            // TM_INPLAN 삭제 - 배차취소 시 반품 데이터 삭제
    		commonDao.update(SERVICEID_PREFIX + "cancelDispatchInplan", request);

            // TM_INPLAN_SPLIT 삭제
            commonDao.update(SERVICEID_PREFIX + "cancelDispatchInplanSplit", request);

            // TM_PLAN_ROUTE 업데이트 — Map으로 전달 (SQL이 carnoChunks 파라미터를 참조하므로 DTO 대신 Map 사용)
            Map<String, Object> cancelRouteParams = new HashMap<>();
            cancelRouteParams.put("dccode", request.getDccode());
            cancelRouteParams.put("deliveryDate", request.getDeliveryDate());
            cancelRouteParams.put("tmDeliveryType", request.getTmDeliveryType());
            commonDao.update(SERVICEID_PREFIX + "cancelDispatchPlanRoute", cancelRouteParams);

            // 실비차 순번 초기화
            commonDao.delete(SERVICEID_PREFIX + "deleteActCostCarSeq", request);

            return CanalFrameConstants.MSG_COM_SUC_CODE;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * @description : 차량번호 변경
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.11.06 OhEunBeom          생성 </pre>
     */
    @Transactional
    public String updateCarNo(TmChangeCarNoReqDto request) { //TODO 리스트 처리 후 삭제예정
        log.info("차량번호 변경 시작 - 센터: {}, 배송일자: {}, 기존차량: {}, 변경차량: {}",
                request.getDccode(), request.getDeliveryDate(), request.getCarno(), request.getReasonCarNo());
        try {
            // TM_INPLAN 업데이트 (CM_CAR 조인하여 차량 정보 가져오기)
            commonDao.update(SERVICEID_PREFIX + "changeCarNoInplan", request);

            log.info("차량번호 변경 완료 - 센터: {}, 배송일자: {}, 기존차량: {}, 변경차량: {}",
                    request.getDccode(), request.getDeliveryDate(), request.getCarno(), request.getReasonCarNo());
            return CanalFrameConstants.MSG_COM_SUC_CODE;
        } catch (Exception e) {
            log.error("차량번호 변경 중 오류 발생 - 센터: {}, 배송일자: {}, 기존차량: {}, 변경차량: {}, 오류: {}",
                    request.getDccode(), request.getDeliveryDate(), request.getCarno(), request.getReasonCarNo(), e.getMessage(), e);
            throw e;
        }
    }

    /**
     * @description : 배차 확정내역 변경
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.01.09 devyn                 생성 </pre>
     */
    @Transactional
    public String saveDispatchList(List<TmChangeCarNoReqDto> requestList) {
        // 1. 리스트 비어있는지 체크
        if (requestList.isEmpty()) {
            throw new UserHandleException("변경 요청 데이터가 없습니다.");
        }

        // 2. newCarNo에 oldCarNo가 두개 이상 들어갔는지 확인
        Map<String, Integer> distinctOldCarNoCountByCarNo =
                requestList.stream()
                        .collect(Collectors.groupingBy(
                                TmChangeCarNoReqDto::getCarno,
                                Collectors.mapping(
                                        TmChangeCarNoReqDto::getOldCarno,
                                        Collectors.collectingAndThen(
                                                Collectors.toSet(),
                                                Set::size
                                        )
                                )
                        ));

        String result01 = TmPlanCommon.validateDupChangeInfo(distinctOldCarNoCountByCarNo);

        if(result01 != null){
            throw new UserHandleException("하나의 차량으로 변경할 차량이 두개 이상으로 선택하셨습니다. " + result01);
        }

        // 2.5 전체차량
        /*TmGetDispatchListReqDto tmGetDispatchListReqDto = new TmGetDispatchListReqDto();
        tmGetDispatchListReqDto.setDccode(requestList.get(0).getDccode());
        tmGetDispatchListReqDto.setDeliveryDate(requestList.get(0).getDeliveryDate());
        tmGetDispatchListReqDto.setDeliveryType(requestList.get(0).getTmDeliveryType());
        List<TmDispatchInfoListResDto> dispatchList = dispatchListMapper.selectDispatchList(tmGetDispatchListReqDto);
        String result25 = TmPlanCommon.validateCheckAllCarNo(requestList, dispatchList);

        if(result25 != null){
            throw new UserHandleException("변경할 차량의 pop정보를 다 선택하지 않았습니다. " + result25);
        }*/

        // 3. 센터에 속한 차량인지 확인
        TmDispatchVehicleReqDto vehicleReqDto = new TmDispatchVehicleReqDto();
        vehicleReqDto.setDeliveryDate(requestList.get(0).getDeliveryDate());
        vehicleReqDto.setDccode(requestList.get(0).getDccode());
        vehicleReqDto.setPlanEntryType("RESULT");

        List<TmVehicleInfoDto> vehicles = commonDao.selectList(SERVICEID_PREFIX + "getCenterVehicle", vehicleReqDto);

        Map<String, TmVehicleInfoDto> vehiclesMap = toVehicleMap(vehicles);

        String result02 = TmPlanCommon.validateCenterVehicleInfo(vehiclesMap, requestList);

        if(result02 != null){
            throw new UserHandleException("센터에 속한 차량이 아닙니다. " + result02);
        }

        // 4. 중복된 차량 찾기
        TmSetDispatchReqDto dupCarNoReq = new TmSetDispatchReqDto();
        dupCarNoReq.setDeliveryDate(requestList.get(0).getDeliveryDate());
        dupCarNoReq.setDccode(requestList.get(0).getDccode());

        List<TmEngineRouteDto> tmEngineRouteList = commonDao.selectList(SERVICEID_PREFIX + "getRouteVehicle", dupCarNoReq);

        Map<String, TmEngineRouteDto> dupCarDto =  new HashMap<String, TmEngineRouteDto>();

        for (TmEngineRouteDto tmEngineRouteDto : tmEngineRouteList) {
            dupCarDto.putIfAbsent(tmEngineRouteDto.getCarNo() + "|" + tmEngineRouteDto.getPriority(), tmEngineRouteDto);
        }

        String result03 = TmPlanCommon.validateDupChangeInfo(dupCarDto, requestList);

        if(result03 != null){
            throw new UserHandleException("중복된 차량이 있습니다. " + result03);
        }

        // 차량정보 수정할 정보
        Map<String, TmChangeCarNoReqDto> chgCarDto =  new HashMap<String, TmChangeCarNoReqDto>();


        try {
            // SY_PROCESSTEMP_TM 배차변경정보 삭제
            commonDao.delete(SERVICEID_PREFIX + "deleteSyProcesstempTm", ModelMapperUtil.map(requestList.stream().findFirst(), userContext, TmChangeCarNoReqDto.class));

            for (TmChangeCarNoReqDto request : requestList) {
                TmChangeCarNoReqDto dto = ModelMapperUtil.map(request, userContext, TmChangeCarNoReqDto.class);

                if(!request.getCarno().equals(request.getOldCarno())) {
                    chgCarDto.putIfAbsent(request.getOldCarno(), dto);
                }

                // SY_PROCESSTEMP_TM 배차변경정보 저장
                commonDao.insert(SERVICEID_PREFIX + "insertSyProcesstempTm", dto);

                // 차량번호 변경 SP
                updateTmInPlanAndChangeCarNo(request);

                // TM_INPLAN_SPLIT 업데이트 (CM_CAR 조인하여 차량 정보 가져오기)
                request.setUserId(userContext.getUserId());
                commonDao.update(SERVICEID_PREFIX + "saveDispatchList", request);
            }

            // ROUTE 차량번호 변경
            changeCarNoListRoute(chgCarDto);

            return CanalFrameConstants.MSG_COM_SUC_CODE;
        } catch (Exception e) {
            throw e;
        }
    }

    // ROUTE 차량번호 변경
    private void changeCarNoListRoute(Map<String, TmChangeCarNoReqDto> chgCarDto) {
        for (TmChangeCarNoReqDto dto : chgCarDto.values()) {
            // newCarNo 데이터가 del_yn = 'Y' OR (동일한 priority AND del_yn = 'N')인 값 삭제
            commonDao.delete(SERVICEID_PREFIX + "deleteAllocNewCar", dto);
            // oldCarNo & priority 데이터를 newCarNo 변경 인서트
            commonDao.insert(SERVICEID_PREFIX + "createAllocNewCar", dto);
            // oldCarNo 데이터가 del_yn = 'Y' OR (동일한 priority AND del_yn = 'N')인 값 삭제
            commonDao.delete(SERVICEID_PREFIX + "deleteAllocOldCar", dto);
        }
    }

    /**
     * @description : 차량번호 변경 SP
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2026.01.09 devyn                 생성 </pre>
     */
    private void updateTmInPlanAndChangeCarNo(TmChangeCarNoReqDto request) {
        // 프로시저 응답 상태값
        String resultCode;
        String returnMessage;
        String resultMessage;

        // 프로시저 호출 (SPTM_INPLAN.MODIFYALLOCCARALL)
        ProcedureParametersFactory.initParamDto(request, request, PAKAGE_NAME);
        request.setAvc_COMMAND(MODIFYALLOCCARALL);
        request.setAvc_DCCODE(request.getDccode());

        String[] keyList = { "DELIVERYDT", "DCCODE", "STORERKEY", "PROCESSTYPE"};
        Object[] valueList = { request.getDeliveryDate(), request.getDccode(), request.getStorerkey(), request.getProcessType()};

        request.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
        int rv = cmCommonService.saveProcedure(request);

        log.info("rv->{}", rv);

        // SPID
        // 프로시저 OUT Parameter(3/4)
        resultCode    = StringUtil.nvl(request.getResultCode());
        resultMessage = StringUtil.nvl(request.getResultMessage());
        returnMessage = StringUtil.nvl(request.getReturnMessage());

        log.info("resultCode->{}", resultCode);
        log.info("resultMessage->{}", resultMessage);
        log.info("returnMessage->{}", returnMessage);
    }

    /**
     * @description : 배차확정 SP
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2026.01.15 devyn                 생성 </pre>
     */
    public void updateSaveDispatchConfirmed(TmSavePlanRouteReqDto request) {
        // 프로시저 응답 상태값
        String resultCode;
        String returnMessage;
        String resultMessage;

        // 프로시저 호출 (SPTM_INPLAN.CONFIRM)
        ProcedureParametersFactory.initParamDto(request, request, PAKAGE_NAME);
        request.setAvc_COMMAND(CONFIRM);
        request.setAvc_DCCODE(request.getDccode());

        String[] keyList = {
                "DELIVERYDT", "DCCODE", "DELIVERYNO",
                "STORERKEY", "DOCNO", "DELIVERYGROUP",
                "CARNO", "PRIORITY", "TRUTHCUSTKEY"
        };

        int roundSeq = (request.getRoundSeq() == 0) ? 1 : request.getRoundSeq();
        Object[] valueList = {
                request.getDeliveryDate(), request.getDccode(), null,
                request.getStorerkey(), null, null,
                request.getCarno(), roundSeq, request.getTruthCustkey()
        };

        request.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
        int rv = cmCommonService.saveProcedure(request);

        log.info("rv->{}", rv);

        // SPID
        // 프로시저 OUT Parameter(3/4)
        resultCode    = StringUtil.nvl(request.getResultCode());
        resultMessage = StringUtil.nvl(request.getResultMessage());
        returnMessage = StringUtil.nvl(request.getReturnMessage());

        log.info("resultCode->{}", resultCode);
        log.info("resultMessage->{}", resultMessage);
        log.info("returnMessage->{}", returnMessage);
    }

    /**
     * @description : 배차확정 SP
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2026.01.15 devyn                 생성 </pre>
     */
    private void updateConfirmed(TmDispatchConfirmedReqDto request) {
        // 프로시저 응답 상태값
        String resultCode;
        String returnMessage;
        String resultMessage;

        // 프로시저 호출 (SPTM_INPLAN.CONFIRM)
        ProcedureParametersFactory.initParamDto(request, request, PAKAGE_NAME);
        request.setAvc_COMMAND(CONFIRM);
        request.setAvc_DCCODE(request.getDccode());

        String[] keyList = {
                "DELIVERYDT", "DCCODE", "DELIVERYNO",
                "STORERKEY", "DOCNO", "DELIVERYGROUP",
                "CARNO", "PRIORITY", "TRUTHCUSTKEY"
        };
        Object[] valueList = {
                request.getSlipDt(), request.getDccode(), null,
                request.getStorerkey(), null, null,
                request.getCarno(), request.getPriority(), request.getTruthcustkey()
        };

        request.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
        int rv = cmCommonService.saveProcedure(request);

        log.info("rv->{}", rv);

        // SPID
        // 프로시저 OUT Parameter(3/4)
        resultCode    = StringUtil.nvl(request.getResultCode());
        resultMessage = StringUtil.nvl(request.getResultMessage());
        returnMessage = StringUtil.nvl(request.getReturnMessage());

        log.info("resultCode->{}", resultCode);
        log.info("resultMessage->{}", resultMessage);
        log.info("returnMessage->{}", returnMessage);
    }

    /**
     * @description : 선배차확정 SP
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2026.01.15 devyn                 생성 </pre>
     */
    private void updatePreConfirmed(TmSavePlanRouteReqDto request) {
        // 프로시저 응답 상태값
        String resultCode;
        String returnMessage;
        String resultMessage;

        // 프로시저 호출 (SPTM_INPLAN.PRECONFIRM)
        ProcedureParametersFactory.initParamDto(request, request, PAKAGE_NAME);
        request.setAvc_COMMAND(PRECONFIRM);
        request.setAvc_DCCODE(request.getDccode());

        String[] keyList = {
                "DELIVERYDT", "DCCODE", "DELIVERYNO", "STORERKEY",
                "DELIVERYGROUP", "CARNO", "PRIORITY", "TRUTHCUSTKEY",
        };

        int roundSeq = (request.getRoundSeq() == 0) ? 1 : request.getRoundSeq();
        Object[] valueList = {
                request.getDeliveryDate(), request.getDccode(), null, request.getStorerkey(),
                null, request.getCarno(), roundSeq, request.getTruthCustkey(),
        };

        request.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
        int rv = cmCommonService.saveProcedure(request);

        log.info("rv->{}", rv);

        // SPID
        // 프로시저 OUT Parameter(3/4)
        resultCode    = StringUtil.nvl(request.getResultCode());
        resultMessage = StringUtil.nvl(request.getResultMessage());
        returnMessage = StringUtil.nvl(request.getReturnMessage());

        log.info("resultCode->{}", resultCode);
        log.info("resultMessage->{}", resultMessage);
        log.info("returnMessage->{}", returnMessage);
    }

    /**
     * @description : 배차취소 SP
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2026.01.20 devyn                 생성 </pre>
     */
    private void updateCanceled(TmCancelDispatchReqDto request) {
        // 프로시저 응답 상태값
        String resultCode;
        String returnMessage;
        String resultMessage;

        // 프로시저 호출 (SPTM_INPLAN.CANCEL)
        ProcedureParametersFactory.initParamDto(request, request, PAKAGE_NAME);
        request.setAvc_COMMAND(CANCEL);
        request.setAvc_DCCODE(request.getDccode());

        String tmDeliveryType =   "WD".equals(request.getDocType()) && !"6".equals(request.getTmDeliveryType()) ? "1"
                : "RT".equals(request.getDocType()) ? "2"
                : request.getTmDeliveryType();

        String[] keyList = {"DCCODE", "DELIVERYDT", "DOCTYPE", "STATUS", "TM_DELIVERYTYPE"};
        Object[] valueList = {request.getDccode(), request.getDeliveryDate(), request.getDocType(), "00", tmDeliveryType};

        request.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
        int rv = cmCommonService.saveProcedure(request);

        log.info("rv->{}", rv);

        // SPID
        // 프로시저 OUT Parameter(3/4)
        resultCode    = StringUtil.nvl(request.getResultCode());
        resultMessage = StringUtil.nvl(request.getResultMessage());
        returnMessage = StringUtil.nvl(request.getReturnMessage());

        log.info("resultCode->{}", resultCode);
        log.info("resultMessage->{}", resultMessage);
        log.info("returnMessage->{}", returnMessage);
    }

    public TmEngineResponseDto getTmResponseDto(TmSetDispatchReqDto request, List<TmEngineRouteDto> tmEngineRouteDto){
        TmEngineResponseDto retDto = new TmEngineResponseDto();
        retDto.setCode(200);

        tmEngineRouteDto.stream().forEach(route -> {
            Map<String, Object> params = new HashMap<>();
            params.put("dccode", request.getDccode());
            params.put("deliveryDate", request.getDeliveryDate());
            params.put("carno", route.getCarNo());
            params.put("priority", route.getPriority());
            params.put("gStorerkey", request.getGStorerkey());
            List<TmEngineStepDto> stepList = commonDao.selectList(SERVICEID_PREFIX + "getRouteStep", (Object) params);
            stepList.stream().forEach(step -> {
                List<Double> doubleList = new ArrayList<>();
                doubleList.add(step.getLongitude());
                doubleList.add(step.getLatitude());
                step.setLocation(doubleList);
                if(step.getGeometry() != null) {
                    step.setGeometry(getStepEncodeGeo(step.getGeometry())); // 지오메트리 인코딩
                }

                // 분할배송인 경우 splitItems 조회
                if ("Y".equals(step.getSplitDeliveryYn())) {
                    Map<String, Object> splitParams = new HashMap<>();
                    splitParams.put("dccode", request.getDccode());
                    splitParams.put("deliveryDate", request.getDeliveryDate());
                    splitParams.put("truthCustkey", step.getId());
                    splitParams.put("carno", route.getCarNo());

                    List<TmSplitItemDto> splitItems = commonDao.selectList(SERVICEID_PREFIX + "getSplitItemsByRoute", (Object) splitParams);
                    step.setSplitItems(splitItems);

                    // splitItems 합계로 step의 weight, orderQty, cube 값 재구성
                    if (splitItems != null && !splitItems.isEmpty()) {
                        double totalWeight = 0.0;
                        double totalOrderQty = 0.0;
                        double totalCube = 0.0;

                        for (TmSplitItemDto item : splitItems) {
                            if (item.getWeight() != null && !item.getWeight().isEmpty()) {
                                try {
                                    totalWeight += Double.parseDouble(item.getWeight());
                                } catch (NumberFormatException e) {
                                    log.warn("Failed to parse weight: {}", item.getWeight());
                                }
                            }
                            if (item.getOrderQty() != null && !item.getOrderQty().isEmpty()) {
                                try {
                                    totalOrderQty += Double.parseDouble(item.getOrderQty());
                                } catch (NumberFormatException e) {
                                    log.warn("Failed to parse orderQty: {}", item.getOrderQty());
                                }
                            }
                            if (item.getCube() != null && !item.getCube().isEmpty()) {
                                try {
                                    totalCube += Double.parseDouble(item.getCube());
                                } catch (NumberFormatException e) {
                                    log.warn("Failed to parse cube: {}", item.getCube());
                                }
                            }
                        }

                        step.setWeight(String.valueOf(totalWeight));
                        step.setOrderQty(String.valueOf((int) totalOrderQty));
                        step.setCube(String.valueOf(totalCube));
                    }
                }
            });
            route.setSteps(stepList);
        });

        // --- 고아 주문 주입 (Forward Orphan) ---
        List<TmEngineStepDto> orphanSteps = commonDao.selectList(
                SERVICEID_PREFIX + "getOrphanOrdersAsSteps", request);

        if (!orphanSteps.isEmpty()) {
            // location 설정
            orphanSteps.forEach(step -> {
                List<Double> loc = new ArrayList<>();
                loc.add(step.getLongitude());
                loc.add(step.getLatitude());
                step.setLocation(loc);
            });

            // (CARNO, PRIORITY) → vehicle ID ("CARNO-PRIORITY") 로 그룹핑
            Map<String, List<TmEngineStepDto>> orphansByVehicle = orphanSteps.stream()
                    .collect(Collectors.groupingBy(
                            step -> step.getCarno() + "-" + step.getRoundSeq()
                    ));

            // 기존 route의 vehicle ID 셋
            Set<String> existingVehicles = tmEngineRouteDto.stream()
                    .map(TmEngineRouteDto::getVehicle)
                    .collect(Collectors.toSet());

            for (Map.Entry<String, List<TmEngineStepDto>> entry : orphansByVehicle.entrySet()) {
                String vehicleId = entry.getKey();
                List<TmEngineStepDto> steps = entry.getValue();

                if (existingVehicles.contains(vehicleId)) {
                    // 기존 route에 step 추가 (같은 CARNO + 같은 회차)
                    tmEngineRouteDto.stream()
                            .filter(r -> vehicleId.equals(r.getVehicle()))
                            .findFirst()
                            .ifPresent(route -> route.getSteps().addAll(steps));
                } else {
                    // 새 route 생성 (orphan-only 차량/회차)

                    int lastDash = vehicleId.lastIndexOf("-");
                    int priority = lastDash != -1 ? Integer.parseInt(vehicleId.substring(lastDash + 1)) : 1;
                    TmEngineRouteDto orphanRoute = TmEngineRouteDto.builder()
                            .vehicle(vehicleId)
                            .priority(priority)
                            .steps(new ArrayList<>(steps))
                            .build();
                    tmEngineRouteDto.add(orphanRoute);
                }
            }
        }

        // 미할당 주문목록
        List<TmSetDispatchUnassignedOrderResDto> unassigned = commonDao.selectList(SERVICEID_PREFIX + "getUnassigned", request);
        // 미할당 로케이션 넣기
        unassigned.stream().forEach(unassign -> {
            List<Double> doubleList = new ArrayList<>();
            doubleList.add(unassign.getLocationLng());
            doubleList.add(unassign.getLocationLat());
            unassign.setLocation(doubleList);
        });

        retDto.setUnassigned(unassigned);

        retDto.setRoutes(tmEngineRouteDto);
        return retDto;
    }

    /**
     * @description : 지오메트리 인코딩
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2026.01.06                       생성 </pre>
     */
    public String getStepEncodeGeo(String geometry){
        List<Object> coordList = new ArrayList<>();

        String content = geometry.replaceAll("[a-zA-Z() ]", " ").trim();

        // 콤마로 각 좌표 쌍을 분리
        String[] points = content.split(",");

        for (String p : points) {
            String[] latLng = p.trim().split("\\s+");
            if (latLng.length >= 2) {
                List<Double> list = new ArrayList<>();
                double lon = Double.parseDouble(latLng[0]); // X (경도)
                double lat = Double.parseDouble(latLng[1]); // Y (위도)
                list.add(lon);
                list.add(lat);
                coordList.add(list);
            }
        }
        return TmNewEngineDataMapper.encode((Object) coordList);
    }

    /**
     * @description : 차량 옵션 설정 저장전 체크
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2026.01.11                      생성 </pre>
     */
    public String getInitCarPlanCheck(TmSetDispatchReqDto request){
        String message = null;
        List<TmVehicleInfoDto> vehicles = commonDao.selectList(SERVICEID_PREFIX + "getInitCarPlanInfo", request);
        for (TmVehicleInfoDto v : vehicles) {
            if (v.getOutGroupCd() == null) return v.getCarno() + " : " + "차량의 출차그룹이 없습니다.";
        }
        return null;
    }

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

}
