package cjfw.wms.tm.service;

import static cjfw.wms.tm.constant.TmConstant.CONTRACT_TYPE_TEMPORARY;
import static cjfw.wms.tm.constant.TmConstant.TM_DELIVERY_TYPE_RETURN;

import java.util.*;
import java.util.stream.Collectors;

import cjfw.wms.tm.dto.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.tm.client.TmEngineClient;
import cjfw.wms.tm.controller.TmNewEngineDataMapper;
import cjfw.wms.tm.domain.TmPlanOption;
import cjfw.wms.tm.dto.engine.TmEngineRequestDto;
import cjfw.wms.tm.dto.engine.TmEngineResponseDto;
import cjfw.wms.tm.entity.TmPlanOptionEntity;
import cjfw.wms.tm.mapper.TmEngineDataMapper;
import cjfw.wms.tm.tmap.Coordinates;
import cjfw.wms.tm.tmap.TmTmapService;
import cjfw.wms.tm.util.TmPlanCommon;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.10.24 
 * @description : 배차계획 재계산 및 ETA 계산 서비스 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.24 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmPlanEtaService {

    @Value("${tm.test-mode:false}")
    private boolean testMode;

    /**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private static final String SERVICEID_PREFIX = "tmPlanEtaService.";
	private static final String SERVICEID_PREFIX_SET_DISPATCH = "tmSetDispatchService.";
	private static final String DISPATCH_OPTION_SERVICEID_PREFIX = "tmDispatchOptionsService.";
    private static final String ORDER_SERVICEID_PREFIX = "tmOrderListService.";

	private final TmEngineClient engineClient;
	private final TmEngineDataMapper dataMapper;
	private final TmNewEngineDataMapper newDataMapper;
	private final TmPlanAutoRequestService planAutoRequestService;
	private final TmPlanEngineResponseService planEngineResponseService;
	private final TmPlanManualRequestService tmPlanManualRequestService;
	private final CommonDao commonDao;
	private final UserContext userContext;
    private final TmTmapService tmTmapService;
    private final TmPlanOptionService tmPlanOptionService;
	
    /**
     * @description : 드라이버 조정 엔진 재계산 요청 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.10 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
     */
	public TmSetDispatchResDto savePlanEtaOptimizeAuto(@RequestBody TmPlanEtaOptimizeAutoReqDto dto){
		try {
            dto.filteredVehicles();
			TmDispatchCenterInfoResDto centerInfo = commonDao.selectOne(SERVICEID_PREFIX_SET_DISPATCH + "getCenterInfo", dto);
			List<TmSetDispatchTruthCustResDto> orders = commonDao.selectList(SERVICEID_PREFIX + "getOrdersGroupByDestinationAndTruthCustkey", dto);

            // 반품 데이터 조회
            List<TmSetDispatchReturnOrderResDto> returnOrders = commonDao.selectList(SERVICEID_PREFIX_SET_DISPATCH + "getReturnOrdersGroupByDestination", dto);

            List<TmVehicleInfoDto> vehicles = commonDao.selectList(SERVICEID_PREFIX + "getCenterVehicleByCarno", dto);
			
			Map<String, TmVehicleInfoDto> temporaryVehicleSettingMap = new HashMap<>();
			List<TmVehicleInfoDto> temporaryVehicleSettingList = commonDao.selectList(SERVICEID_PREFIX + "getOutGroupTemporaryVehicleInfo", dto);
			
		   	TmDispatchOptionsReqDto dispatchOptionReqDto = new TmDispatchOptionsReqDto();
	    	dispatchOptionReqDto.setDccode(dto.getDccode());
	    	dispatchOptionReqDto.setPlanOptionType(dto.getTmDeliveryType());

            TmPlanOption planOption = tmPlanOptionService.getPlanOption(dispatchOptionReqDto);

            if(ObjectUtils.isEmpty(vehicles)) {

                throw new UserHandleException("배차 가능 차량이 존재하지 않습니다.");
            }

            String result = TmPlanCommon.validateVehicleInfo(vehicles, planOption);
            if (!testMode && result != null) {
                throw new UserHandleException("배차에 필요한 차량 정보가 부족합니다. " + result);
            }

	    	// 조차 기준 
	    	if(temporaryVehicleSettingList != null && !temporaryVehicleSettingList.isEmpty()) {
	    		temporaryVehicleSettingList.forEach(temporaryVehicleSetting ->
                        temporaryVehicleSettingMap.put(
                                temporaryVehicleSetting.getOutGroupCd(), temporaryVehicleSetting));
	    	}

            // TODO: 엔진 성능 이슈로 인해 주문, 차량 개수 제한 처리 => 엔진 보완 후 조치 필요
//			List<TmSetDispatchTruthCustResDto> orders10 = orders.subList(0, Math.min(100, orders.size()));
//			List<TmVehicleInfoDto> vehicles10 = vehicles.subList(0, Math.min(50, vehicles.size()));
			
			Map<String, TmPlanEtaStepReqDto> splitOrderMap = new HashMap<>();
			
	    	// DB 차량 정보 데이터
	        Map<String, TmVehicleInfoDto> vehicleMap = vehicles.stream().collect(
										Collectors.toMap(TmVehicleInfoDto::getCarno, v -> v));

            // 2회전 배차 시도 시 차량의 다회전 상태 확인
            TmPlanCommon.validateVehicleStatus(vehicleMap, dto, planOption);

            // 배차에 포함된 반품 주문 목록
            List<TmSetDispatchReturnOrderResDto> targetReturnOrders = new ArrayList<>();

			// STEP 주문 데이터 (분할배차 정보 포함)
			dto.getVehicles().stream().forEach(vehicle -> {

				if(CONTRACT_TYPE_TEMPORARY.equals(vehicle.getContractType())) {
					// 실비차 차량 정보 vehicleMap에 추가
                    TmVehicleInfoDto outGroupInfo = temporaryVehicleSettingMap.get(vehicle.getOutGroupCd());
					if(outGroupInfo != null) {

						TmVehicleInfoDto temporaryCarInfo = new TmVehicleInfoDto();
						BeanUtils.copyProperties(outGroupInfo, temporaryCarInfo);
						temporaryCarInfo.setCarno(vehicle.getCarno());
						temporaryCarInfo.setMaxCube(Double.parseDouble(vehicle.getMaxCbm()));
						temporaryCarInfo.setMaxWeight(Double.parseDouble(vehicle.getMaxWeight()));
						temporaryCarInfo.setMaxLanding(vehicle.getMaxLanding());
						vehicleMap.put(vehicle.getCarno(), temporaryCarInfo);
					}
				}
				vehicle.getSteps().stream().forEach(step -> {
                    if (step.getId() == null) return;
                    int splitSeq = 0;
                    if("Y".equals(step.getSplitDeliveryYn())) {
                        splitSeq = Integer.parseInt(step.getSplitDeliverySeq()) + 1;
                    }
                    splitOrderMap.put(step.getId() + "-" + splitSeq, step);

                    if (TM_DELIVERY_TYPE_RETURN.equals(step.getTmDeliveryType())) {
                        targetReturnOrders.addAll(
                                returnOrders.stream().filter(o ->
                                        o.getCustKey().equals(step.getId())).toList()
                        );
                    }
				});
			});
	        
	        // DB 주문 정보 데이터
//			 It should be uncommented after the engine performance issue is resolved
    		 Map<String, TmSetDispatchTruthCustResDto> orderMap = orders.stream()
    		     .collect(Collectors.toMap(
    		         TmSetDispatchTruthCustResDto::getTruthCustKey,
    		         v -> v)
                 );

            // add return orders into original orders map
            targetReturnOrders.forEach(order -> {
                TmSetDispatchTruthCustResDto rtOrderDto = TmSetDispatchReturnOrderResDto.to(order);
                if (rtOrderDto != null)
                    orderMap.put(order.getId(), rtOrderDto);
            });

			orders = new ArrayList<>(orderMap.values());

            log.info("Return Order : cnt={}, \n{}", targetReturnOrders.size(), targetReturnOrders);

            // 재계산 대상 반품 거래처 위경도 좌표 검증 및 갱신
            validateCustLocationInfo(targetReturnOrders, dto);

            TmEngineRequestDto engineRequest = planAutoRequestService.createAutoEngineRequest(dto, centerInfo, orderMap, vehicleMap, planOption);

//	        engineRequest.setUseRounds(dispatchOptionDto.getUseRounds());
//	        engineRequest.setUseMaxWeight(dispatchOptionDto.getUseMaxWeight());

	        TmEngineResponseDto engineResponse = engineClient.callOptimization(engineRequest);
            engineResponse.setOnMaxWeight(planOption.isOnMaxWeight());

            // end점 경로, 시간, 거리 제거
            engineResponse.getRoutes().stream().forEach(route -> {
                route.getSteps().stream().forEach(step -> {
                    if("end".equals(step.getType())){
                        step.setGeometry(null);
                    }
                });
            });

	        return planEngineResponseService.createSuccessResponse(dto, engineResponse, vehicleMap, orderMap, splitOrderMap);
		} catch(UserHandleException e) {
			throw e;
		} catch(Exception e) {
            log.error("Failed to auto optimization : {}", e.getMessage(), e);
			throw new UserHandleException("MSG.COM.ERR.999",new String[] {"자동배차가 정상적으로 완료되지 않았습니다. 다시 시도해주세요."}, e);
		}
	}

    private void validateCustLocationInfo(List<TmSetDispatchReturnOrderResDto> returnOrders, TmPlanEtaOptimizeAutoReqDto dto) {
        // 1. 거래처 위/경도 확인
        // 1.1 위경도 없으면 주소 확인 -> 주소 없으면 alert
        // 1.2 주소 이용 TMAP api 호출 위경도 조회
        // 1.2.1 성공 시 MS_CUSTDLVINFO 업데이트
        // 1.2.2 위경도 조회 실패시 alert

        for (TmSetDispatchReturnOrderResDto returnOrder : returnOrders) {
            log.info("return order : {}", returnOrder);
            if (!ObjectUtils.isEmpty(returnOrder.getLongitude()) && !ObjectUtils.isEmpty(returnOrder.getLatitude())) continue;
            if (ObjectUtils.isEmpty(returnOrder.getCustAddress())) {
                String msg = "거래처 주소가 없습니다. (" + returnOrder.getCustName() + ")";
                throw new UserHandleException(msg);
            }
            // call tmap api
            try {
                Coordinates coordinates = tmTmapService.getCoordinates(returnOrder.getCustAddress());
                if (coordinates == null) {
                    String msg = "거래처 좌표가 없습니다. (" + returnOrder.getCustName() + ")";
                    throw new UserHandleException(msg);
                }
                returnOrder.setLatitude(coordinates.getLatitude());
                returnOrder.setLongitude(coordinates.getLongitude());
                updateCustCoordinates(returnOrder);
            } catch (Exception e) {
                log.error("Failed to get coordinates : {}", e.getMessage(), e);
                throw new UserHandleException("거래처 좌표 조회 실패하였습니다.", e);
            }
        }
    }

    private void updateCustCoordinates(TmSetDispatchReturnOrderResDto order) {
        TmCustDlvInfoPointReqDto tmCustDlvInfoPointReqDto = ModelMapperUtil.map(order, TmCustDlvInfoPointReqDto.class);
        tmCustDlvInfoPointReqDto.setDlvcustkey(order.getId());
        commonDao.update(ORDER_SERVICEID_PREFIX + "updateCustPointIsNull", tmCustDlvInfoPointReqDto);
    }

    /**
	 * @description : 수동 경로계산 요청 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.03 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	public TmSetDispatchResDto savePlanEtaOptimizeManual(@RequestBody TmPlanEtaOptimizeAutoReqDto dto){
		try {
            dto.filteredVehicles();
			List<TmSetDispatchTruthCustResDto> orders = commonDao.selectList(SERVICEID_PREFIX + "getOrdersGroupByDestinationAndTruthCustkey", dto);
			List<TmVehicleInfoDto> vehicles = commonDao.selectList(SERVICEID_PREFIX + "getCenterVehicleByCarno", dto);

            Map<String, TmVehicleInfoDto> temporaryVehicleSettingMap = new HashMap<>();
            List<TmVehicleInfoDto> temporaryVehicleSettingList = commonDao.selectList(SERVICEID_PREFIX + "getOutGroupTemporaryVehicleInfo", dto);

            
            String result = TmPlanCommon.validateVehicleInfo(vehicles, null);
            if (!testMode && result != null) {
                throw new UserHandleException("배차에 필요한 차량 정보가 부족합니다. " + result);
            }

            // 배차에 포함된 반품 주문 목록
            List<TmSetDispatchReturnOrderResDto> targetReturnOrders = targetReturnOrders(dto);

            // 조차 기준
            if(temporaryVehicleSettingList != null && !temporaryVehicleSettingList.isEmpty()) {
                temporaryVehicleSettingList.forEach(temporaryVehicleSetting -> {
                    temporaryVehicleSettingMap.put(temporaryVehicleSetting.getOutGroupCd(), temporaryVehicleSetting);
                });
            }

	        Map<String, TmVehicleInfoDto> vehicleMap = new HashMap<>();
            for (TmVehicleInfoDto tmVehicleInfoDto : vehicles) {
                vehicleMap.put(tmVehicleInfoDto.getCarno(), tmVehicleInfoDto);
            }

            // 2회전 배차 시도 시 차량의 다회전 상태 확인
            TmPlanCommon.validateVehicleStatus(vehicleMap, dto, null);

	        // DB 착지 데이터
	        Map<String, TmSetDispatchTruthCustResDto> orderMap = new HashMap<>();
            for (TmSetDispatchTruthCustResDto order : orders) {
                orderMap.put(order.getTruthCustKey(), order);
            }

            // add return orders into original orders map
            targetReturnOrders.forEach(order -> {
                TmSetDispatchTruthCustResDto rtOrderDto = TmSetDispatchReturnOrderResDto.to(order);
                if (rtOrderDto != null)
                    orderMap.put(order.getId(), rtOrderDto);
            });

            // 재계산 대상 반품 거래처 위경도 좌표 검증 및 갱신
            validateCustLocationInfo(targetReturnOrders, dto);

            log.info("Return Order : cnt={}, \n{}", targetReturnOrders.size(), targetReturnOrders);

	        Map<String, TmSetDispatchTruthCustResDto> splitOrderMap = new HashMap<>();
	        
			// 요청 기준 착지 데이터 MAP
			dto.getVehicles().forEach(vehicle -> {
//				if(vehicle.get)
				// 실비차 MAP에 추가할 데이터
				// vehicleInfo.getLongitude()
				// vehicleInfo.getLatitude()
				// vehicleInfo.getDrivingFromdate()
				// carno
				// maxWeight
				// maxCube
				// maxLanding
				// contracttype
				// carCapacity
				if(CONTRACT_TYPE_TEMPORARY.equals(vehicle.getContractType())) {
                    TmVehicleInfoDto outGroupInfo = temporaryVehicleSettingMap.get(vehicle.getOutGroupCd());
                    if (outGroupInfo != null) {
                        TmVehicleInfoDto temporaryVehicleInfoDto = new TmVehicleInfoDto();
                        BeanUtils.copyProperties(outGroupInfo, temporaryVehicleInfoDto);
                        temporaryVehicleInfoDto.setCarno(vehicle.getCarno());
                        log.info("vehicle.getMaxCbm() = {}", vehicle.getMaxCbm());
                        if (vehicle.getMaxCbm() != null && !"null".equals(vehicle.getMaxCbm())) {
                            temporaryVehicleInfoDto.setMaxCube(Double.parseDouble(vehicle.getMaxCbm()));
                        }
                        if(vehicle.getMaxWeight() != null && !"null".equals(vehicle.getMaxWeight())) {
                            temporaryVehicleInfoDto.setMaxWeight(Double.parseDouble(vehicle.getMaxWeight()));
                        }
                        if(vehicle.getMaxLanding() != null && !"null".equals(vehicle.getMaxLanding())) {
                            temporaryVehicleInfoDto.setMaxLanding(vehicle.getMaxLanding());
                        }

                        // 차량 MAP에 실비차 정보 추가
                        vehicleMap.put(vehicle.getCarno(), temporaryVehicleInfoDto);
                    }

					
					// 위도, 경도, 시작시간, 계약유형, 용적량은 조회 후 설정
//					temporaryVehicleInfoDto.setContractType();
//					temporaryVehicleInfoDto.setMaxCube(vehicle.getMaxCbm());
//					vehicleDispatchInfoDto.setVehicleType(vehicleInfo.getContractTypeNm());
				}
				vehicle.getSteps().stream().forEach(step -> {
					if(step.getId() != null && !step.getId().isEmpty()) {

						// DB기준 주문정보 복사 (ModelMapper는 동일 source에 대해 같은 인스턴스를 재사용하므로 BeanUtils 사용)
						TmSetDispatchTruthCustResDto orderInfo = orderMap.get(step.getId());
						TmSetDispatchTruthCustResDto splitOrderInfoDto = new TmSetDispatchTruthCustResDto();
						BeanUtils.copyProperties(orderInfo, splitOrderInfoDto);

						// 분할배차정보 덮어쓰기
						splitOrderInfoDto.setWeight(step.getWeight());
						splitOrderInfoDto.setCube(step.getCube());
						splitOrderInfoDto.setOrderQty(step.getOrderQty());
						splitOrderInfoDto.setTmDeliveryType(step.getTmDeliveryType());
						splitOrderInfoDto.setSplitDeliveryYn(step.getSplitDeliveryYn());
						// 분할배송 상품 목록 설정
						splitOrderInfoDto.setSplitItems(step.getSplitItems());

						String seq = step.getSplitDeliverySeq();
						if(step.getSplitDeliverySeq() == null || step.getSplitDeliverySeq().isEmpty() ||  "N".equals(step.getSplitDeliveryYn())) {
							seq = "0";
						}

						splitOrderInfoDto.setSplitDeliverySeq(seq);
						String mapKey = step.getId()  + "-" + step.getType() + "-" + seq;
						splitOrderMap.put(mapKey, splitOrderInfoDto);
					}
				});
			});

			return tmPlanManualRequestService.createManualRequest(dto, vehicleMap, orderMap, splitOrderMap);
		} catch(UserHandleException e) {
			throw e;
		} catch(Exception e) {
			throw new UserHandleException("MSG.COM.ERR.999",new String[] {"수동배차가 정상적으로 완료되지 않았습니다. 다시 시도해주세요."}, e);
		}
	}

    private List<TmSetDispatchReturnOrderResDto> targetReturnOrders(TmPlanEtaOptimizeAutoReqDto dto) {
        // 반품 데이터 조회
        List<TmSetDispatchReturnOrderResDto> returnOrders = commonDao.selectList(SERVICEID_PREFIX_SET_DISPATCH + "getReturnOrdersGroupByDestination", dto);

        List<TmSetDispatchReturnOrderResDto> targetReturnOrders = new ArrayList<>();
        dto.getVehicles().forEach(vehicle -> {
            vehicle.getSteps().stream().forEach(step -> {
                if (ObjectUtils.isEmpty(step.getId())) return;
                if (!TM_DELIVERY_TYPE_RETURN.equals(step.getTmDeliveryType())) return;

                targetReturnOrders.addAll(
                        returnOrders.stream().filter(o ->
                                o.getCustKey().equals(step.getId())).toList()
                );
            });
        });
        return targetReturnOrders;
    }

}
