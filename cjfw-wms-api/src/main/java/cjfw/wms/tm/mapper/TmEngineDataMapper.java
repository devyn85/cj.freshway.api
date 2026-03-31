package cjfw.wms.tm.mapper;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import cjfw.wms.tm.dto.TmDispatchCenterInfoResDto;
import cjfw.wms.tm.dto.TmDispatchOptionsReqDto;
import cjfw.wms.tm.dto.TmPlanEtaOptimizeAutoReqDto;
import cjfw.wms.tm.dto.TmPlanEtaStepReqDto;
import cjfw.wms.tm.dto.TmSetDispatchReqDto;
import cjfw.wms.tm.dto.TmSetDispatchResDto;
import cjfw.wms.tm.dto.TmSetDispatchReturnOrderResDto;
import cjfw.wms.tm.dto.TmSetDispatchTruthCustResDto;
import cjfw.wms.tm.dto.TmSetDispatchUnassignedOrderResDto;
import cjfw.wms.tm.dto.TmVehicleDispatchInfoDto;
import cjfw.wms.tm.dto.TmVehicleInfoDto;
import cjfw.wms.tm.dto.engine.TmEngineRequestDto;
import cjfw.wms.tm.dto.engine.TmEngineResponseDto;
import cjfw.wms.tm.dto.engine.TmEngineRouteDto;
import cjfw.wms.tm.dto.engine.TmEngineShipmentDto;
import cjfw.wms.tm.dto.engine.TmEngineStepDto;
import cjfw.wms.tm.dto.engine.TmEngineSummaryDto;
import cjfw.wms.tm.dto.engine.TmEngineVehicleDto;
import cjfw.wms.tm.entity.TmPlanOptionEntity;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : AI Assistant
 * @date : 2025.01.14
 * @description : 주문/차량 데이터를 Engine Request로 변환하는 Mapper
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.01.14 AI Assistant          생성
 */
@Slf4j
@Component
public class TmEngineDataMapper {
	
	// 값 미존재 데이터로 인한 임시 기본값
    private static final double DEFAULT_LONGITUDE = 126.9780; 
    private static final double DEFAULT_LATITUDE = 37.5665;
    private static final int DEFAULT_PICKUP_DURATION_SECONDS = 300;  // 5분
    private static final int DEFAULT_DELIVERY_DURATION_SECONDS = 600; // 10분
    private static final int DEFAULT_PRIORITY = 5;
    private static final int AMOUNT_CONVERSION_FACTOR = 100000;
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter HHMM_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private static final ZoneId SEOUL_ZONE_ID = ZoneId.of("Asia/Seoul");

	
	private final ObjectMapper objectMapper;
	
	public TmEngineDataMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}
			
			
	public TmEngineRequestDto planEtaOptimizeAutoParseEngineRequest(TmPlanEtaOptimizeAutoReqDto request, TmDispatchCenterInfoResDto centerInfo, Map<String, TmSetDispatchTruthCustResDto> orderMap, Map<String, TmVehicleInfoDto> vehicleMap, TmDispatchOptionsReqDto dispatchOptionDto) {
		
		List<TmEngineShipmentDto> shipmentDtoList = new ArrayList<>();
		List<TmEngineVehicleDto> vehicleDtoList  = new ArrayList<>();
		
		request.getVehicles().stream().forEach(vehicle -> {
	        	TmVehicleInfoDto vehicleInfo = vehicleMap.get(vehicle.getCarno());
	        
	            // 출발지 (고정차 -> 차고지)
	            List<Double> location = Arrays.asList(
	            	vehicleInfo.getLongitude() != null ? vehicleInfo.getLongitude() : 126.9780, // 서울시청 경도 (기본값)
	            	vehicleInfo.getLatitude() != null ? vehicleInfo.getLatitude() : 37.5665     // 서울시청 위도 (기본값)
	            );
	            
	            int maxLanding = vehicleInfo.getMaxLanding() == null ? 10 : Integer.parseInt(vehicleInfo.getMaxLanding().trim());
	            int minLanding = vehicleInfo.getMinLanding() == null ? 10 : Integer.parseInt(vehicleInfo.getMinLanding().trim());
	            int vehicleLanding = Boolean.TRUE.equals(dispatchOptionDto.getUseMaxLocation()) ? maxLanding : minLanding;
	            int vehicleCube = toInteger(vehicleInfo.getMaxCube(), 15);
	            int vehicleWeight = toInteger(vehicleInfo.getMaxWeight(), 2000);
	            
	            		
	            // capacity 배열 구성 [부피, 무게, 개수, 착지]
	            List<Integer> capacity = Arrays.asList(vehicleCube, vehicleWeight, 50 * 100000, vehicleLanding);
	            
	            
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	            LocalDateTime workStartTime = LocalDateTime.parse(vehicleInfo.getDrivingFromdate(), formatter);
	            LocalDateTime workEndTime = LocalDateTime.parse(vehicleInfo.getDrivingTodate(), formatter);

	            // workEndTime이 workStartTime보다 작으면 startTime + 5시간 [TODO] 제거해야함
	            if (workEndTime.isBefore(workStartTime)) {
	            	workEndTime = workStartTime.plusHours(5);
	            }
	            
	            ArrayList<String> vehicleSkills = new ArrayList<String>();
	            
	            vehicleSkills.add(vehicle.getCarno());

	            workEndTime.format(formatter);

	            // 최소 착지수 설정 (기본착지수 = 최소착지수), null인 경우 1
	            int minStops = vehicleInfo.getMinLanding() == null ? 1 : Integer.parseInt(vehicleInfo.getMinLanding().trim());

	            vehicleDtoList.add(
            		TmEngineVehicleDto.builder()
		            	.id(vehicle.getCarno())
		                .start_location(location)  // 시작 위치 설정
		                .end_location(null)    // 종료 위치 설정 (시작과 동일)
		                .capacity(capacity)        // 적재 용량 설정
		                .skills(vehicleSkills)   // 스킬 (빈 배열) - List<Integer> 타입
		                .vehicle_type("yongcha") // 차량 유형 결정
		                .work_start_time(workStartTime.format(formatter)) // 작업 시작 시간
		                .work_end_time(workEndTime.format(formatter))     // 작업 종료 시간
		                .min_stops(minStops) // 최소 착지수
		                .build(
            		));
			
	        	vehicle.getSteps().stream().forEach(step -> {
	        		if(step.getId() == null) {
	        			return;
	        		}
	        		
	        		if(("2".equals(step.getTmDeliveryType()) && "delivery".equals(step.getType())) || ("1".equals(step.getTmDeliveryType())  && "pickup".equals(step.getType()))) {
	        			return;
	        		}

	        		Integer splitSeq = 0;
	        		
	        		if("Y".equals(step.getSplitDeliveryYn())) {
	        			splitSeq = Integer.parseInt(step.getSplitDeliverySeq()) + 1;
	        		}
	        		
	        		String requestId = step.getId() + "-" + splitSeq.toString();
	        		
	        		TmSetDispatchTruthCustResDto orderInfo = orderMap.get(step.getId());
	        		
	        		ArrayList<String> shipmentSkills = new ArrayList<String>();
	        		
	        		
	                // 픽업 위치 (물류센터 좌표) - null 값 처리
	            	// 배송일경우, 센터 좌표로 설정 ( 수정필요 )
	                List<Double> pickupLocation = Arrays.asList(
	                		parseCoordinate(centerInfo.getLongitude()).orElse(DEFAULT_LONGITUDE),
	                		parseCoordinate(centerInfo.getLatitude()).orElse(DEFAULT_LATITUDE)
	                );


	                // amount 배열 구성 [ORDER_CUBE, ORDER_WEIGHT, ORDER_QTY] - CJ 전처리 서버 스키마 [부피, 무게, 개수]에 맞춤
	                Integer orderCube = toInteger(step.getCube(), 1);     // 기본값: 1
	                Integer orderWeight = toInteger(step.getWeight(), 1); // 기본값: 1
	                Integer orderQty = toInteger(step.getOrderQty(), 1);   // 기본값: 1
	                
	                List<Integer> amount = Arrays.asList(orderCube, orderWeight, orderQty, 1);
	                
	        		// 담당 드라이버 조건 추가
	                shipmentSkills.add(vehicle.getCarno());

	        		shipmentDtoList.add(
						TmEngineShipmentDto.builder()
			        		.id(requestId)
			                .type("pop") // 스크린샷 스키마: 필수 필드
			                .pickup_address(centerInfo.getDcname() != null ? centerInfo.getDcname() : "서울시 중구 태평로 1가") // 기본 주소
			                .pickup_location(pickupLocation) // 스크린샷 스키마: 필수 필드
			                .delivery_address(orderInfo.getCustName() != null ? orderInfo.getCustName() : "서울시 중구 태평로 1가") // 기본 주소
			                .delivery_location(step.getLocation()) // 스크린샷 스키마: 필수 필드
			                .amount(amount) // 배송 수량 정보 [부피, 무게, 개수] - CJ 전처리 서버 스키마 순서
			                .pickup_duration(300) // 픽업 소요 시간 (초) - 기본값 5분
			                .delivery_duration(orderInfo.getWorkTime() == null ? 600 : (int) Math.round(Double.parseDouble(orderInfo.getWorkTime()))) // 배송 소요 시간 (초) - 기본값 10분
			                .skills(shipmentSkills)
			                .priority(5) // 우선순위 (기본값 5)
			        		.build());
		        	});
		        });
		
		return 	TmEngineRequestDto.builder()
				.vehicles(vehicleDtoList)
				.shipments(shipmentDtoList)
				.build();
	}
	
    /**
     * @description : 엔진 요청 Request 상품 Mapper Method 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.12 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
     */
    public TmEngineShipmentDto toEngineShipment(TmSetDispatchTruthCustResDto order, TmDispatchCenterInfoResDto centerInfo, Map<String, String> stepVehicleMap) {
        if (order == null) {
            log.warn("주문 정보가 null입니다.");
            return null;
        }

        try {
        	
            // 픽업 위치 (물류센터 좌표) - null 값 처리
        	// 배송일경우, 센터 좌표로 설정 ( 수정필요 )
            List<Double> centerLocation = Arrays.asList(
                    parseCoordinate(centerInfo.getLongitude()).orElse(DEFAULT_LONGITUDE),
                    parseCoordinate(centerInfo.getLatitude()).orElse(DEFAULT_LATITUDE)
            );

            List<Double> custLocation = Arrays.asList(
                    parseCoordinate(order.getLongitude()).orElse(DEFAULT_LONGITUDE),
                    parseCoordinate(order.getLatitude()).orElse(DEFAULT_LATITUDE)
            );

            List<Integer> amount = Arrays.asList(
                    toInteger(order.getCube(), 1),
                    toInteger(order.getWeight(), 1),
                    toInteger(order.getOrderQty(), 1),
                    1
            );
            
            List<String> skills = new ArrayList<>();
            if (stepVehicleMap != null && stepVehicleMap.containsKey(order.getTruthCustKey())) {
                skills.add(stepVehicleMap.get(order.getTruthCustKey()));
            }

            // 1일경우 배송(pickup:센터 / delivery:고객) 2일경우 반품(pickup:고객 / delivery:센터) 추후 TC센터 또는 조달 고려 필요
            boolean isPickupType = "2".equals(order.getTmDeliveryType());

            return TmEngineShipmentDto.builder()
                    .id(Optional.ofNullable(order.getTruthCustKey()).orElse("UNKNOWN"))
                    .type("pop")
                    .pickup_address(isPickupType ? order.getCustName() : centerInfo.getDcname())
                    .pickup_location(isPickupType ? custLocation : centerLocation)
                    .delivery_address(isPickupType ? centerInfo.getDcname() : order.getCustName())
                    .delivery_location(isPickupType ? centerLocation : custLocation)
                    .amount(amount)
                    .pickup_duration(DEFAULT_PICKUP_DURATION_SECONDS)
                    .delivery_duration(Optional.ofNullable(order.getWorkTime())
                            .map(wt -> (int) Math.round(Double.parseDouble(wt)))
                            .orElse(DEFAULT_DELIVERY_DURATION_SECONDS))
                    .skills(skills)
                    .priority(DEFAULT_PRIORITY)
                    .build();

        } catch (Exception e) {
            log.error("주문 정보를 Engine Shipment로 변환 중 오류 발생 - 주문: {}, 오류: {}", order.getTruthCustKey(), e.getMessage(), e);
            return null;
        }
    }
    
    /**
     * 차량 정보를 Engine Vehicle DTO로 변환합니다.
     *
     * @param vehicle 차량 정보
     * @return Engine Vehicle DTO
     */
    public TmEngineVehicleDto toEngineVehicle(TmVehicleInfoDto vehicle, TmDispatchOptionsReqDto dispatchOptionDto) {
        if (vehicle == null) {
            log.warn("차량 정보가 null입니다.");
            return null;
        }

        try {
            // 출발지 (고정차 -> 차고지)
            List<Double> location = Arrays.asList(
                    Optional.ofNullable(vehicle.getLongitude()).orElse(DEFAULT_LONGITUDE),
                    Optional.ofNullable(vehicle.getLatitude()).orElse(DEFAULT_LATITUDE)
            );
            
            // 임시(엔진 다회전 처리 확인 후 null처리 필요)
            List<Double> endLocation = location;
            
            if(vehicle.getContractType() != null && !"".equals(vehicle.getContractType()) && vehicle.getContractType().toUpperCase().contains("FIX")) {
        		endLocation = location;
        	}
            
            int vehicleLanding = Boolean.TRUE.equals(dispatchOptionDto.getUseMaxLocation())
                    ? toInteger(vehicle.getMaxLanding(), 10)
                    : toInteger(vehicle.getMinLanding(), 10);
            
            int maxLanding = vehicle.getMaxLanding() == null ? 10 : Integer.parseInt(vehicle.getMaxLanding().trim());
            int minLanding = vehicle.getMinLanding() == null ? 10 : Integer.parseInt(vehicle.getMinLanding().trim());
            int vehicleCube = toInteger(vehicle.getMaxCube(), 15);
            int vehicleWeight = toInteger(vehicle.getMaxWeight(), 2000);
            
            // capacity 배열 구성 [부피, 무게, 개수, 착지]
            List<Integer> capacity = Arrays.asList(vehicleCube, vehicleWeight, 50 * 100000, vehicleLanding);
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime workStartTime = LocalDateTime.parse(vehicle.getDrivingFromdate(), formatter);
            LocalDateTime workEndTime = LocalDateTime.parse(vehicle.getDrivingTodate(), formatter);

            // workEndTime이 workStartTime보다 작으면 startTime + 5시간 [TODO] 제거해야함
            if (workEndTime.isBefore(workStartTime)) {
            	workEndTime = workStartTime.plusHours(5);
            }

            workEndTime.format(formatter);

            // 최소 착지수 설정 (기본착지수 = 최소착지수), null인 경우 1
            int minStops = vehicle.getMinLanding() == null ? 1 : Integer.parseInt(vehicle.getMinLanding().trim());

            return TmEngineVehicleDto.builder()
            	.id(vehicle.getCarno())
                .start_location(location)  // 시작 위치 설정
                .end_location(null)    // 종료 위치 설정
                .capacity(capacity)        // 적재 용량 설정
                .skills(Arrays.asList())   // 스킬
                .vehicle_type("fixed") // 차량 유형 결정
                .work_start_time(workStartTime.format(formatter)) // 작업 시작 시간
                .work_end_time(workEndTime.format(formatter))     // 작업 종료 시간
                .min_stops(minStops) // 최소 착지수
                .build();

        } catch (Exception e) {
            log.error("차량 정보를 Engine Vehicle로 변환 중 오류 발생 - 차량: {}, 오류: {}",
                vehicle.getCarno(), e.getMessage(), e);
            return null;
        }
    }

    /**
     * 차량 정보를 Engine Vehicle DTO로 변환합니다.
     *
     * @param vehicle 차량 정보
     * @return Engine Vehicle DTO
     */
    public TmEngineVehicleDto toEngineVehicle(TmVehicleInfoDto vehicle, TmDispatchCenterInfoResDto centerInfo, TmDispatchOptionsReqDto dispatchOptionDto) {
    	// 통합 또는 고도화 필요
        if (vehicle == null) {
            log.warn("차량 정보가 null입니다.");
            return null;
        }

        try {
            // 출발지 (고정차 -> 차고지)
            List<Double> location = Arrays.asList(
                vehicle.getLongitude() != null ? vehicle.getLongitude() : 126.9780, // 서울시청 경도 (기본값)
                vehicle.getLatitude() != null ? vehicle.getLatitude() : 37.5665     // 서울시청 위도 (기본값)
            );
            
            int maxLanding = vehicle.getMaxLanding() == null ? 10 : Integer.parseInt(vehicle.getMaxLanding().trim());
            int minLanding = vehicle.getMinLanding() == null ? 10 : Integer.parseInt(vehicle.getMinLanding().trim());
            int vehicleLanding = Boolean.TRUE.equals(dispatchOptionDto.getUseMaxLocation()) ? maxLanding : minLanding;
            int vehicleCube = toInteger(vehicle.getMaxCube(), 15);
            int vehicleWeight = toInteger(vehicle.getMaxWeight(), 2000);
            		
            // capacity 배열 구성 [부피, 무게, 개수, 착지]
            List<Integer> capacity = Arrays.asList(vehicleCube, vehicleWeight, 50 * 100000, vehicleLanding);
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime workStartTime = LocalDateTime.parse( vehicle.getDrivingFromdate(), formatter);
            LocalDateTime workEndTime = LocalDateTime.parse(vehicle.getDrivingTodate(), formatter);

            // workEndTime이 workStartTime보다 작으면 startTime + 5시간 [TODO] 제거해야함
            if (workEndTime.isBefore(workStartTime)) {
            	workEndTime = workStartTime.plusHours(5);
            }
            
            ArrayList<String> skills = new ArrayList<String>();

            skills.add(vehicle.getCarno());

            workEndTime.format(formatter);

            // 최소 착지수 설정 (기본착지수 = 최소착지수), null인 경우 1
            int minStops = vehicle.getMinLanding() == null ? 1 : Integer.parseInt(vehicle.getMinLanding().trim());

            return TmEngineVehicleDto.builder()
            	.id(vehicle.getCarno())
                .start_location(location)  // 시작 위치 설정
                .end_location(null)    // 종료 위치 설정 (시작과 동일)
                .capacity(capacity)        // 적재 용량 설정
                .skills(skills)   // 스킬 (빈 배열) - List<Integer> 타입
                .vehicle_type("fixed") // 차량 유형 결정
                .work_start_time(workStartTime.format(formatter)) // 작업 시작 시간
                .work_end_time(workEndTime.format(formatter))     // 작업 종료 시간
                .min_stops(minStops) // 최소 착지수
                .build();

        } catch (Exception e) {
            log.error("차량 정보를 Engine Vehicle로 변환 중 오류 발생 - 차량: {}",
                vehicle.getCarno(), e);
            return null;
        }
    }


    /**
     * 좌표 문자열을 Double로 변환합니다.
     *
     * @param coordinateStr 좌표 문자열
     * @return 변환된 좌표 (null 가능)
     */
    private Optional<Double> parseCoordinate(String coordinateStr) {
        if (coordinateStr == null || coordinateStr.trim().isEmpty()) {
            return Optional.empty();
        }
        try {
            return Optional.of(Double.parseDouble(coordinateStr.trim()));
        } catch (NumberFormatException e) {
            log.warn("좌표 변환 실패: {}", coordinateStr);
            return Optional.empty();
        }
    }
    
//    public TmDispatchOptionsReqDto dispatchOption(List<TmPlanOptionEntity> entityList) {
//    	TmDispatchOptionsReqDto dto = new TmDispatchOptionsReqDto();
//
//    	for(TmPlanOptionEntity entity : entityList) {
//	    	if(entity.getPlanOptionCd().equals("IS_MULTI_TURN")) {
//	    		dto.setUseRounds(entity.getSetValue().equals("1"));
//	    	}else if(entity.getPlanOptionCd().equals("MAX_CBM")) {
//	    		dto.setMaxCbm(entity.getSetValue());
//	    	}else if(entity.getPlanOptionCd().equals("MAX_POP_COUNT")) {
//	    		dto.setPopCount(entity.getSetValue());
//	    	}else if(entity.getPlanOptionCd().equals("MAX_TRUTH_CUST_COUNT")) {
//	    		dto.setUseMaxLocation(entity.getSetValue().equals("1"));
//	    	}else if(entity.getPlanOptionCd().equals("MAX_WEIGHT")) {
//	    		dto.setUseMaxWeight(entity.getSetValue().equals("1"));
//	    	}else if(entity.getPlanOptionCd().equals("SPECIAL_CONDITIONS")) {
//	    		dto.setUseSkills(entity.getSetValue().equals("1"));
//	    	}
//    	}
//
//    	return dto;
//    }
    
    /**
     * 성공 응답을 생성합니다. (새로운 구조 기반)
     */
    public TmSetDispatchResDto createSimpleSuccessResponse(
    			TmPlanEtaOptimizeAutoReqDto request, 
	    		TmEngineResponseDto engineResponse, 
	    		List<TmVehicleInfoDto> vehicles,
	    		List<TmSetDispatchTruthCustResDto> orders,
	    		Map<String, TmPlanEtaStepReqDto> splitOrderMap
    		) {
        try {
            // 1. CJ 전처리 서버 summary를 그대로 사용
            TmEngineSummaryDto summary = engineResponse.getSummary();
            
            // carno → 차량 정보 매핑 생성
            Map<String, TmVehicleInfoDto> vehicleMap = new HashMap<>();
            for (int i = 0; i < vehicles.size(); i++) {
            	vehicleMap.put(vehicles.get(i).getCarno(), vehicles.get(i));
            }
            
            // truthCustkey -> 고객정보 매핑
            Map<String, TmSetDispatchTruthCustResDto> orderMap = new HashMap<>();
            for (int i = 0; i < orders.size(); i++) {
            	orderMap.put(orders.get(i).getTruthCustKey(), orders.get(i));
            }
            
            for (int i = 0; i < orders.size(); i++) {
            	orderMap.put(orders.get(i).getTruthCustKey(), orders.get(i));
            }
            
            
            List<TmVehicleDispatchInfoDto> vehicleDispatchInfoList = engineResponse.getRoutes().stream()
                .map(engineRoute -> setOrderDetailSplitOrderInfo(engineRoute, vehicleMap, orderMap, splitOrderMap))
                .toList();
            
            List<TmSetDispatchUnassignedOrderResDto> filteredUnassigned = engineResponse.getUnassigned().stream()
            	    .filter(order -> {
            	        String desc = order.getDescription();
            	        return desc == null || !desc.toLowerCase().contains("pickup");
            	    })
            	    .toList();

        	// 필터링된 리스트를 다시 세팅
        	engineResponse.setUnassigned(filteredUnassigned);
            
            engineResponse.getUnassigned().stream()
            	.forEach((order) -> {
	    			String skillYn = "N";
		      		int lastIndex = order.getId().lastIndexOf("-");
		      		TmPlanEtaStepReqDto splitOrderInfo = splitOrderMap.get(order.getId());
  		      		String originId = order.getId().substring(0, lastIndex);
	    			TmSetDispatchTruthCustResDto truthCustInfo = orderMap.get(originId);
	  		      	if("Y".equals(truthCustInfo.getKidsClYn()) || "Y".equals(truthCustInfo.getDlvWaitYn()) || "Y".equals(truthCustInfo.getDistantYn()) || "Y".equals(truthCustInfo.getLngDistantYn()) || "Y".equals(truthCustInfo.getUnloadLvlYn())) {
	  		    	  skillYn = "Y";
	  		      	}
	  		      
	  		      	order.setId(originId);
		  		    order.setCustName(truthCustInfo.getCustName());
		  		    order.setStorerkey(truthCustInfo.getStorerkey());
		  		    order.setCustAddress(truthCustInfo.getAddress());
		  		    order.setCustType(truthCustInfo.getCustType());
			  		order.setClaimYn(truthCustInfo.getClaimYn());
			  		order.setFaceInspect(truthCustInfo.getFaceInspect());
			  		order.setReqdlvtime1From(truthCustInfo.getReqdlvtime1From());
			  		order.setReqdlvtime1To(truthCustInfo.getReqdlvtime1To());
			  		order.setKeyCustType(truthCustInfo.getPasswordType());
	    			order.setSpecialConditionYn(skillYn);
	    			order.setCube(splitOrderInfo.getCube());
	    			order.setWeight(splitOrderInfo.getWeight());
	    			order.setOrderQty(splitOrderInfo.getOrderQty());
	    			order.setDefDistrictCode(null);
	    			order.setDefCarno(null);
	    			order.setReturnYn("N");
	    			order.setSlipdt(splitOrderInfo.getSlipdt());
	    			order.setSlipline(splitOrderInfo.getSlipline());
	    			order.setSlipno(splitOrderInfo.getSlipno());
	    			order.setSplitDeliveryYn(splitOrderInfo.getSplitDeliveryYn());
	    			order.setSplitDeliverySeq(splitOrderInfo.getSplitDeliverySeq());
	    			
	            });
            
//            engineResponse.setUnassigned(engineResponse.getUnassigned().stream().filter(dto -> "pickup".contains(dto.getDescription())));
            
            for (TmVehicleDispatchInfoDto dispatchInfo : vehicleDispatchInfoList) {
                List<TmEngineStepDto> originalSteps = dispatchInfo.getSteps();
                List<TmEngineStepDto> mergedSteps = mergePickupSteps(originalSteps);
                dispatchInfo.setSteps(mergedSteps);
            }       

            return TmSetDispatchResDto.builder()
                .dccode(request.getDccode())
                .deliveryDate(request.getDeliveryDate())
                .deliveryType(request.getTmDeliveryType())
                .status("SUCCESS")
                .message("배차가 성공적으로 완료되었습니다.")
                .summary(summary)
                .vehicles(vehicleDispatchInfoList)
                .unassignedOrders(engineResponse.getUnassigned())
                .build();
                
        } catch (Exception e) {
            log.error("성공 응답 생성 중 오류: {}", e.getMessage(), e);
            return createSimpleFailureResponse("ERROR", "응답 생성 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
    
    /**
     * 성공 응답을 생성합니다. (새로운 구조 기반)
     */
    public TmSetDispatchResDto createSimpleSuccessResponse(
	    		TmSetDispatchReqDto request, 
	    		TmEngineResponseDto engineResponse, 
	    		List<TmVehicleInfoDto> vehicles,
	    		List<TmSetDispatchTruthCustResDto> orders,
	    		List<TmSetDispatchReturnOrderResDto> returnOrders
    		) {
        try {
            // 1. CJ 전처리 서버 summary를 그대로 사용
            TmEngineSummaryDto summary = engineResponse.getSummary();
            
            // carno → 차량 정보 매핑 생성
            Map<String, TmVehicleInfoDto> vehicleMap = new HashMap<>();
            for (int i = 0; i < vehicles.size(); i++) {
            	vehicleMap.put(vehicles.get(i).getCarno(), vehicles.get(i));
            }
            
            // truthCustkey -> 고객정보 매핑
            Map<String, TmSetDispatchTruthCustResDto> orderMap = new HashMap<>();
            for (int i = 0; i < orders.size(); i++) {
            	orderMap.put(orders.get(i).getTruthCustKey(), orders.get(i));
            }
            
            List<TmVehicleDispatchInfoDto> vehicleDispatchInfoList = engineResponse.getRoutes().stream()
                .map(engineRoute -> setOrderDetailInfo(engineRoute, vehicleMap, orderMap))
                .toList();
            
            List<TmSetDispatchUnassignedOrderResDto> filteredUnassigned = engineResponse.getUnassigned().stream()
            	    .filter(order -> {
            	        String desc = order.getDescription();
            	        return desc == null || !desc.toLowerCase().contains("pickup");
            	    })
            	    .toList();
            
            // 미배차 주문 할당
            engineResponse.setUnassigned(filteredUnassigned);
            
            // 미배차 response 주문 정보 추가 ( DB 데이터 기준 )
            engineResponse.getUnassigned().stream()
            	.forEach((order) -> {
	    			String skillYn = "N";
	    			TmSetDispatchTruthCustResDto truthCustInfo = orderMap.get(order.getId());
	  		      	if("Y".equals(truthCustInfo.getKidsClYn()) || "Y".equals(truthCustInfo.getDlvWaitYn()) || "Y".equals(truthCustInfo.getDistantYn()) || "Y".equals(truthCustInfo.getLngDistantYn()) || "Y".equals(truthCustInfo.getUnloadLvlYn())) {
	  		    	  skillYn = "Y";
	  		      	}
	  		      
		  		    order.setCustName(truthCustInfo.getCustName());
		  		    order.setStorerkey(truthCustInfo.getStorerkey());
		  		    order.setCustAddress(truthCustInfo.getAddress());
		  		    order.setCustType(truthCustInfo.getCustType());
			  		order.setClaimYn(truthCustInfo.getClaimYn());
			  		order.setFaceInspect(truthCustInfo.getFaceInspect());
			  		order.setReqdlvtime1From(truthCustInfo.getReqdlvtime1From());
			  		order.setReqdlvtime1To(truthCustInfo.getReqdlvtime1To());
			  		order.setKeyCustType(truthCustInfo.getPasswordType());
	    			order.setSpecialConditionYn(skillYn);
	    			order.setDefDistrictCode(null);
	    			order.setDefCarno(null);
	    			order.setReturnYn(truthCustInfo.getReturnYn());
	    			order.setCube(truthCustInfo.getCube());
	    			order.setWeight(truthCustInfo.getWeight());
	    			order.setOrderQty(truthCustInfo.getOrderQty());
	    			order.setTmDeliveryType(truthCustInfo.getTmDeliveryType());
    			});
            
            // 동일위치에 존재하는 pickup 병합
            for (TmVehicleDispatchInfoDto dispatchInfo : vehicleDispatchInfoList) {
                List<TmEngineStepDto> originalSteps = dispatchInfo.getSteps();
                List<TmEngineStepDto> mergedSteps = mergePickupSteps(originalSteps);
                dispatchInfo.setSteps(mergedSteps);
            }
            
            
            // 다회전 차량 분할 로직
            List<TmVehicleDispatchInfoDto> finalVehicleList = new ArrayList<>();
            for (TmVehicleDispatchInfoDto originalVehicle : vehicleDispatchInfoList) {
                List<TmEngineStepDto> originalSteps = originalVehicle.getSteps();
                if (originalSteps == null || originalSteps.isEmpty()) {
                    finalVehicleList.add(originalVehicle);
                    continue;
                }

                int tempRoundSeq = 0;
                for (int i = 0; i < originalSteps.size(); i++) {
                    TmEngineStepDto currentStep = originalSteps.get(i);
                    // 배송 유형일때 픽업할경우 다회전으로 판단 ( 추후 반품만 있을때 고려 필요 )
                    if ("pickup".equals(currentStep.getType()) && "1".equals(currentStep.getTmDeliveryType()) && !"start".equals(originalSteps.get(i-1).getType())) {
                        tempRoundSeq++;
                    }
                    currentStep.setRoundSeq(tempRoundSeq);
                }

                // 다회전 번호로 그룹화
                Map<Integer, List<TmEngineStepDto>> stepsByRound = originalSteps.stream()
                        .collect(Collectors.groupingBy(TmEngineStepDto::getRoundSeq));

                List<Integer> sortedRoundKeys = new ArrayList<>(stepsByRound.keySet());
                Collections.sort(sortedRoundKeys);

                for (int i = 0; i < sortedRoundKeys.size(); i++) {
                    Integer roundSeq = sortedRoundKeys.get(i);
                    List<TmEngineStepDto> roundSteps = stepsByRound.get(roundSeq);

                    if (!roundSteps.isEmpty()) {
                        List<Double> pickupLocation = originalSteps.stream()
                                .filter(s -> "pickup".equals(s.getType()))
                                .map(TmEngineStepDto::getLocation)
                                .findFirst()
                                .orElse(null);

                        // start 없을때 추가 처리
                        if (roundSteps.stream().noneMatch(s -> "start".equals(s.getType()))) {
                            TmEngineStepDto firstStep = roundSteps.get(0);
                            TmEngineStepDto startStep = new TmEngineStepDto();
                            startStep.setType("start");
                            startStep.setLocation(pickupLocation);
                            startStep.setArrival(firstStep.getArrival());
                            startStep.setGeometry(null);
                            startStep.setRoundSeq(roundSeq);
                            roundSteps.add(0, startStep);
                        }

                        // end 없을때 추가 처리
                        if (roundSteps.stream().noneMatch(s -> "end".equals(s.getType()))) {
                            TmEngineStepDto endStep = new TmEngineStepDto();
                            String endStepArrival;

                            // 다음 회차가 있는지 확인하여 end step의 도착시간을 설정
                            if (i + 1 < sortedRoundKeys.size()) {
                                List<TmEngineStepDto> nextRoundSteps = stepsByRound.get(sortedRoundKeys.get(i + 1));
                                // 다음 회차의 첫번째 pickup 스텝의 도착시간을 가져옴
                                endStepArrival = nextRoundSteps.stream()
                                        .filter(s -> "pickup".equals(s.getType()))
                                        .map(TmEngineStepDto::getArrival)
                                        .findFirst()
                                        .orElse(roundSteps.get(roundSteps.size() - 1).getArrival());
                            } else {
                                endStepArrival = roundSteps.get(roundSteps.size() - 1).getArrival();
                            }
                            
                            endStep.setType("end");
                            endStep.setLocation(pickupLocation);
                            endStep.setArrival(endStepArrival);
                            endStep.setGeometry(null);
                            endStep.setDuration(null);
                            endStep.setDistance(null);
                            endStep.setRoundSeq(roundSeq);
                            roundSteps.add(endStep);
                        }
                    }
                    
                    int loadedWeight = 0;
                    int loadedCbm = 0;
                    int orderCount = 0;
                    int roundDistanceMeters = 0;
                    long minArrival = 253402271999L;
                    long maxArrival = 0L;

                    for (TmEngineStepDto step : roundSteps) {
                        if (step.getWeight() != null && step.getWeight().matches("-?\\d+(\\.\\d+)?")) {
                            loadedWeight += (int) Math.round(Double.parseDouble(step.getWeight()));
                        }
                        if (step.getCube() != null && step.getCube().matches("-?\\d+(\\.\\d+)?")) {
                            loadedCbm += (int) Math.round(Double.parseDouble(step.getCube()));
                        }
                        if ("delivery".equals(step.getType())) {
                            orderCount++;
                        }
                        if (step.getStepDistance() != null && !"".equals(step.getStepDistance())) {
                        	roundDistanceMeters += Integer.parseInt(step.getStepDistance());
                        }
                        
                        // 이동 시간 구하기 max - min
                        if(step.getArrival() != null && !"".equals(step.getArrival())) {
                        	minArrival = Math.min(Long.parseLong(step.getArrival()), minArrival);
                        	maxArrival = Math.max(Long.parseLong(step.getArrival()), maxArrival);
                        }
                    }
                    long second = maxArrival - minArrival;
                    Instant instantMin = Instant.ofEpochSecond(minArrival);
                    Instant instantMax = Instant.ofEpochSecond(maxArrival);
                    Duration diff = Duration.between(instantMin, instantMax);
                    
                    Integer totalDuration = (int) second;
                    Integer totalTimeMinutes = totalDuration / 60;
                    String totalTimeDisplay = formatTimeDisplay(totalTimeMinutes);
                    
                    double roundDistanceKm = roundDistanceMeters / 1000.0;

//                    int weightUsagePercent = (originalVehicle.getMaxWeight() != null && originalVehicle.getMaxWeight() > 0)
//                            ? (loadedWeight * 100) / originalVehicle.getMaxWeight() : 0;
//                    int cbmUsagePercent = (originalVehicle.getMaxCbm() != null && originalVehicle.getMaxCbm() > 0)
//                            ? (loadedCbm * 100) / originalVehicle.getMaxCbm() : 0;

                    TmVehicleDispatchInfoDto roundVehicle = TmVehicleDispatchInfoDto.builder()
                            .carno(originalVehicle.getCarno())
                            .outGroupCd(originalVehicle.getOutGroupCd())
                            .vehicleName(originalVehicle.getVehicleName())
                            .vehicleType(originalVehicle.getVehicleType())
                            .vehicleGroup(originalVehicle.getVehicleGroup())
                            .maxWeight(originalVehicle.getMaxWeight())
                            .maxCbm(originalVehicle.getMaxCbm())
                            .roundSeq(roundSeq)
                            
                            // 재계산된 값
//                            .loadedWeight(loadedWeight)
//                            .loadedCbm(loadedCbm)
                            .orderCount((int) orderCount)
//                            .weightUsagePercent(weightUsagePercent)
//                            .cbmUsagePercent(cbmUsagePercent)
                            .totalTimeDisplay(totalTimeDisplay)
                            .totalTimeMinutes(diff.toMinutesPart())
                            .totalDistanceKm(roundDistanceKm)
                            .duration(totalDuration)
                            
                            /* [TODO] 아래 값들은 전체 경로에 대한 요약 정보
                             * 분할데이터대로 계산 필요
                             */
                            .cost(originalVehicle.getCost())
                            .setup(originalVehicle.getSetup())
                            .waitingTime(originalVehicle.getWaitingTime())
                            .priority(originalVehicle.getPriority())
                            .violations(originalVehicle.getViolations())
                            .description(originalVehicle.getDescription())
                            .geometry(null)
                            .distance(originalVehicle.getDistance())
                            
                            // 해당 회차의 스텝 리스트 설정
                            .steps(roundSteps)
                            .build();
                    
                    finalVehicleList.add(roundVehicle);
                };
            }
            

            return TmSetDispatchResDto.builder()
                .dccode(request.getDccode())
                .deliveryDate(request.getDeliveryDate())
                .deliveryType(request.getDeliveryType())
                .status("SUCCESS")
                .message("배차가 성공적으로 완료되었습니다.")
                .summary(summary)
                .vehicles(finalVehicleList)
                .unassignedOrders(engineResponse.getUnassigned())
                .returnOrders(returnOrders)
                .build();
                
        } catch (Exception e) {
            log.error("성공 응답 생성 중 오류: {}", e.getMessage(), e);
            return createSimpleFailureResponse("ERROR", "응답 생성 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
    
    public TmSetDispatchResDto createSimpleFailureResponse(String dispatchId, String message) {
        return TmSetDispatchResDto.builder()
            .status("FAILED")
            .message(message)
            .build();
    }
    
    private List<TmEngineStepDto> mergePickupSteps(List<TmEngineStepDto> steps) {
        if (steps == null || steps.isEmpty()) {
            return new ArrayList<>();
        }

        List<TmEngineStepDto> mergedSteps = new ArrayList<>();
        int i = 0;
        while (i < steps.size()) {
            TmEngineStepDto currentStep = steps.get(i);

            if (currentStep.getDescription() != null && currentStep.getDescription().contains("pickup") && "pickup".equals(currentStep.getType())) {
                
                List<TmEngineStepDto> stepsToMerge = new ArrayList<>();
                stepsToMerge.add(currentStep);

                int j = i + 1;
                while (j < steps.size()) {
                    TmEngineStepDto nextStep = steps.get(j);
                    if (nextStep.getDescription() != null && nextStep.getDescription().contains("pickup") 
                        && "pickup".equals(nextStep.getType()) 
                        && Objects.equals(currentStep.getLocation(), nextStep.getLocation())) {
                        stepsToMerge.add(nextStep);
                        j++;
                    } else {
                        break;
                    }
                }

                // 병합할 스텝이 2개 이상인 경우
                if (stepsToMerge.size() > 1) {
                    try {
                        TmEngineStepDto firstStep = stepsToMerge.get(0);
                        
                        String stepJson = objectMapper.writeValueAsString(firstStep);
                        TmEngineStepDto mergedStep = objectMapper.readValue(stepJson, TmEngineStepDto.class);
                        
                        mergedSteps.add(mergedStep);
                        i = j; // 인덱스 점프
                        continue;

                    } catch (Exception e) {
                        log.warn("스텝 병합 중 오류 발생. 원본 스텝을 그대로 사용합니다.", e);
                        mergedSteps.addAll(stepsToMerge);
                        i = j;
                        continue;
                    }
                }
            }
            
            mergedSteps.add(currentStep);
            i++;
        }
        return mergedSteps;
    }
    
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
                }
            }
        } else if (obj instanceof double[][] arr) {
            coords.addAll(Arrays.asList(arr));
        }

        StringBuilder encoded = new StringBuilder();
        long lastLat = 0, lastLng = 0;

        for (double[] point : coords) {
            long lat = Math.round(point[0] * 1e5);
            long lng = Math.round(point[1] * 1e5);
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

    public List<double[]> decode(String encodedPolyline) {
        if (encodedPolyline == null || encodedPolyline.isEmpty()) {
            return Collections.emptyList();
        }

        List<double[]> poly = new ArrayList<>();
        int index = 0;
        int len = encodedPolyline.length();
        int lat = 0;
        int lng = 0;

        while (index < len) {
            int b;
            int shift = 0;
            int result = 0;
            do {
                b = encodedPolyline.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encodedPolyline.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            double latitude = ((double) lat / 1E5);
            double longitude = ((double) lng / 1E5);
            
            // Oracle SDO_GEOMETRY는 (경도, 위도) 순서를 따릅니다.
            // SDO_ORDINATE_ARRAY에 맞추기 위해 (경도, 위도) 순으로 저장합니다.
            poly.add(new double[]{longitude, latitude}); 
        }

        return poly;
    }
    
    public List<Number> flattenCoordinates(List<double[]> polylineList) {
        List<Number> ordinateList = new ArrayList<>();
        if (polylineList == null) {
            return ordinateList;
        }
        
        // 각 double[] (경도, 위도)를 꺼내서 순서대로 단일 리스트에 추가
        for (double[] coordinate : polylineList) {
            // SDO_GEOMETRY는 경도(X), 위도(Y) 순서입니다.
            ordinateList.add(coordinate[0]); // 경도 (Longitude)
            ordinateList.add(coordinate[1]); // 위도 (Latitude)
        }
        return ordinateList;
    }
    
    public String toWktLineString(List<double[]> polylineList) {
        if (polylineList == null || polylineList.isEmpty()) {
            return null;
        }
        return polylineList.stream()
                .map(p -> String.format("%.8f %.8f", p[0], p[1]))
                .collect(Collectors.joining(", ", "LINESTRING (", ")"));
    }
    
    /**
     * 시간을 "X시간 Y분" 형태로 포맷합니다.
     */
    private String formatTimeDisplay(Integer totalMinutes) {
        if (totalMinutes == null || totalMinutes == 0) {
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
    
    public int toInteger(Double value, int defaultValue) {
    	if(value == null) {
    		return defaultValue * 100000;
    	}
        return (int) (value * 100000);
    }
    
    public int toInteger(String s, int defaultValue) {
    	
        if (s == null || s.isBlank()) {
        	return defaultValue * 100000;
        }

        double value = Double.parseDouble(s);
        return (int) (value * 100000);
    }

    /**
     * 단일 Engine route를 완전한 차량 배차 정보로 변환합니다.
     */
    public TmVehicleDispatchInfoDto setOrderDetailInfo(
    		TmEngineRouteDto engineRoute, 
    		Map<String, TmVehicleInfoDto> vehicleMap,  
    		Map<String, TmSetDispatchTruthCustResDto> orderMap
    ) {
        String vehicleId = engineRoute.getVehicle();
        TmVehicleInfoDto vehicleInfo = vehicleMap.get(vehicleId);
        String carno = vehicleInfo != null ? vehicleInfo.getCarno() : "UNKNOWN_" + vehicleId;
        String outGroupCd = vehicleInfo != null ? vehicleInfo.getOutGroupCd() : "10";
        String contractType = vehicleInfo != null ? vehicleInfo.getContractTypeNm() : "임시용차";
        
        Integer maxWeight = 5000;
        Integer maxCbm = 50;
        // 중량/CBM 정보 계산 (임시)
        if(vehicleInfo != null) {
        	maxWeight = vehicleInfo.getMaxWeight() != null ? vehicleInfo.getMaxWeight().intValue() : 5000;
        	maxCbm = vehicleInfo.getMaxCube() != null ? vehicleInfo.getMaxCube().intValue() : 50;
        }
        	
        Integer loadedWeight = engineRoute.getCost() != null ? Math.min(engineRoute.getCost() / 10, maxWeight) : 500;
        Integer weightUsagePercent = (loadedWeight * 100) / maxWeight;
        
        Integer loadedCbm = loadedWeight; // 임시로 중량과 동일하게 설정
        Integer cbmUsagePercent = Math.max(1, (loadedCbm * 100) / Math.max(maxCbm, 1));
        
        // 주문 건수 및 시간/거리 정보
        Integer orderCount = engineRoute.getSteps() != null ? 
            (int) engineRoute.getSteps().stream().filter(step -> "delivery".equals(step.getType())).count() : 0;
        Integer totalTimeMinutes = engineRoute.getDuration() != null ? engineRoute.getDuration() / 60 : 0;
        String totalTimeDisplay = formatTimeDisplay(totalTimeMinutes);
        Double totalDistanceKm = engineRoute.getDistance() != null ? engineRoute.getDistance() / 1000.0 : 0.0;

        if(engineRoute.getSteps() != null) {
        	Integer prevDistance = 0;
        	for(TmEngineStepDto step : engineRoute.getSteps()) {
        		if(step.getId() != null) {
        			String skillYn = "N";
        			TmSetDispatchTruthCustResDto truthCustInfo = orderMap.get(step.getId());
        			
      		      	if("Y".equals(truthCustInfo.getKidsClYn()) || "Y".equals(truthCustInfo.getDlvWaitYn()) || "Y".equals(truthCustInfo.getDistantYn()) || "Y".equals(truthCustInfo.getLngDistantYn()) || "Y".equals(truthCustInfo.getUnloadLvlYn())) {
      		    	  skillYn = "Y";
      		      	}
      		      	
      		      	String expectHhmm = Instant.ofEpochSecond(Long.parseLong(step.getArrival()))
                          .atZone(ZoneId.of("Asia/Seoul"))
                          .format(DateTimeFormatter.ofPattern("HH:mm"));
      		      
      		      	step.setCustAddress(truthCustInfo.getAddress());
      		      	step.setCustName(truthCustInfo.getCustName());
        			step.setStorerkey(truthCustInfo.getStorerkey());
        			step.setCustType(truthCustInfo.getCustType());
        			step.setClaimYn(truthCustInfo.getClaimYn());
        			step.setFaceInspect(truthCustInfo.getFaceInspect());
        			step.setExpectedArrivalTime(expectHhmm);
        			step.setReqdlvtime1From(truthCustInfo.getReqdlvtime1From());
        			step.setReqdlvtime1To(truthCustInfo.getReqdlvtime1To());
        			step.setKeyCustType(truthCustInfo.getPasswordType());
        			step.setSpecialConditionYn(skillYn);
        			step.setDefDistrictCode(null);
        			step.setDefCarno(truthCustInfo.getCarno());
        			step.setReturnYn(truthCustInfo.getReturnYn());
        			step.setTmDeliveryType(truthCustInfo.getTmDeliveryType());
        			step.setOrderQty(truthCustInfo.getOrderQty());
        			step.setCube(truthCustInfo.getCube());
        			step.setWeight(truthCustInfo.getWeight());
        			Integer stepDistance = Integer.parseInt(step.getDistance()) - prevDistance;
        			step.setStepDistance(stepDistance.toString());
        			prevDistance = Integer.parseInt(step.getDistance());
        		}
        	}
        }
        
        return TmVehicleDispatchInfoDto.builder()
            .carno(carno)
            .outGroupCd(outGroupCd)
//            .vehicleName(vehicleName)
            .vehicleType(contractType)
//            .vehicleGroup(vehicleGroup)
//            .maxWeight(maxWeight)
//            .loadedWeight(loadedWeight)
            .weightUsagePercent(weightUsagePercent)
//            .maxCbm(maxCbm)
//            .loadedCbm(loadedCbm)
            .cbmUsagePercent(cbmUsagePercent)
            .orderCount(orderCount)
            .totalTimeMinutes(totalTimeMinutes)
            .totalTimeDisplay(totalTimeDisplay)
            .totalDistanceKm(totalDistanceKm)
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
            .build();
    }
    
    /**
     * 단일 Engine route를 완전한 차량 배차 정보로 변환합니다.
     */
    public TmVehicleDispatchInfoDto setOrderDetailSplitOrderInfo(
    		TmEngineRouteDto engineRoute, 
    		Map<String, TmVehicleInfoDto> vehicleMap,  
    		Map<String, TmSetDispatchTruthCustResDto> orderMap,
    		Map<String, TmPlanEtaStepReqDto> splitOrderMap
    ) {
        String vehicleId = engineRoute.getVehicle();
        TmVehicleInfoDto vehicleInfo = vehicleMap.get(vehicleId);
        String carno = vehicleInfo.getCarno();
        String outGroupCd = vehicleInfo != null ? vehicleInfo.getOutGroupCd() : "10";
        String contractType = vehicleInfo != null ? vehicleInfo.getContractTypeNm() : "임시용차";
        
        Integer maxWeight = 5000;
        Integer maxCbm = 50;
        // 중량/CBM 정보 계산 (임시)
        if(vehicleInfo != null) {
        	maxWeight = vehicleInfo.getMaxWeight() != null ? vehicleInfo.getMaxWeight().intValue() : 5000;
        	maxCbm = vehicleInfo.getMaxCube() != null ? vehicleInfo.getMaxCube().intValue() : 50;
        }
        	
        Integer loadedWeight = engineRoute.getCost() != null ? Math.min(engineRoute.getCost() / 10, maxWeight) : 500;
        Integer weightUsagePercent = (loadedWeight * 100) / maxWeight;
        
        Integer loadedCbm = loadedWeight; // 임시로 중량과 동일하게 설정
        Integer cbmUsagePercent = Math.max(1, (loadedCbm * 100) / Math.max(maxCbm, 1));
        
        // 주문 건수 및 시간/거리 정보
        Integer orderCount = engineRoute.getSteps() != null ? 
            (int) engineRoute.getSteps().stream().filter(step -> "delivery".equals(step.getType())).count() : 0;
        Integer totalTimeMinutes = engineRoute.getDuration() != null ? engineRoute.getDuration() / 60 : 0;
        String totalTimeDisplay = formatTimeDisplay(totalTimeMinutes);
        Double totalDistanceKm = engineRoute.getDistance() != null ? engineRoute.getDistance() / 1000.0 : 0.0;

        if(engineRoute.getSteps() != null) {
        	for(TmEngineStepDto step : engineRoute.getSteps()) {
        		if(step.getId() != null) {
        			
      		      	String originId = step.getId();
        			
      		      	// 분할배송 정보 포함시 분할배송정보 우선 처리
  		      		TmPlanEtaStepReqDto splitData = splitOrderMap.get(step.getId());
  		      		step.setSlipno(splitData.getSlipno());
  		      		step.setSlipline(splitData.getSlipline());
  		      		step.setSlipdt(splitData.getSlipdt());
  		      		int lastIndex = step.getId().lastIndexOf("-");
  		      		originId = step.getId().substring(0, lastIndex);

      		      	
        			String skillYn = "N";
        			TmSetDispatchTruthCustResDto truthCustInfo = orderMap.get(originId);
        			
      		      	if("Y".equals(truthCustInfo.getKidsClYn()) || "Y".equals(truthCustInfo.getDlvWaitYn()) || "Y".equals(truthCustInfo.getDistantYn()) || "Y".equals(truthCustInfo.getLngDistantYn()) || "Y".equals(truthCustInfo.getUnloadLvlYn())) {
      		    	  skillYn = "Y";
      		      	}
      		      	
      		      	String expectHhmm = Instant.ofEpochSecond(Long.parseLong(step.getArrival()))
                          .atZone(ZoneId.of("Asia/Seoul"))
                          .format(DateTimeFormatter.ofPattern("HH:mm"));
      		      	
  		      		step.setId(originId);
  		      		step.setSplitDeliveryYn(splitData.getSplitDeliveryYn());
  		      		step.setSplitDeliverySeq(splitData.getSplitDeliverySeq());
  		      		step.setWeight(splitData.getWeight());
  		      		step.setCube(splitData.getCube());
  		      		step.setOrderQty(splitData.getOrderQty());
      		      	step.setCustAddress(truthCustInfo.getAddress());
      		      	step.setCustName(truthCustInfo.getCustName());
        			step.setStorerkey(truthCustInfo.getStorerkey());
        			step.setCustType(truthCustInfo.getCustType());
        			step.setClaimYn(truthCustInfo.getClaimYn());
        			step.setFaceInspect(truthCustInfo.getFaceInspect());
        			step.setExpectedArrivalTime(expectHhmm);
        			step.setReqdlvtime1From(truthCustInfo.getReqdlvtime1From());
        			step.setReqdlvtime1To(truthCustInfo.getReqdlvtime1To());
        			step.setKeyCustType(truthCustInfo.getPasswordType());
        			step.setSpecialConditionYn(skillYn);
        			step.setDefDistrictCode(null);
        			step.setDefCarno(truthCustInfo.getCarno());
        			step.setReturnYn(truthCustInfo.getReturnYn());
        			step.setTmDeliveryType(truthCustInfo.getTmDeliveryType());
        			
        		}
        	}
        }
        
        return TmVehicleDispatchInfoDto.builder()
            .carno(carno)
            .outGroupCd(outGroupCd)
//            .vehicleName(vehicleName)
            .vehicleType(contractType)
//            .vehicleGroup(vehicleGroup)
//            .maxWeight(maxWeight)
//            .loadedWeight(loadedWeight)
            .weightUsagePercent(weightUsagePercent)
//            .maxCbm(maxCbm)
//            .loadedCbm(loadedCbm)
            .cbmUsagePercent(cbmUsagePercent)
            .orderCount(orderCount)
            .totalTimeMinutes(totalTimeMinutes)
            .totalTimeDisplay(totalTimeDisplay)
            .totalDistanceKm(totalDistanceKm)
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
            .build();
    }

    /**
     * 단일 Engine route를 완전한 차량 배차 정보로 변환합니다.
     */
    public TmVehicleDispatchInfoDto setOrderDetailInfo(TmVehicleDispatchInfoDto engineRoute, Map<String, TmVehicleInfoDto> vehicleMap,  Map<String, TmSetDispatchTruthCustResDto> orderMap) {
        String vehicleId = engineRoute.getCarno();
        TmVehicleInfoDto vehicleInfo = vehicleMap.get(vehicleId);
        String carno = vehicleInfo != null ? vehicleInfo.getCarno() : "UNKNOWN_" + vehicleId;
        String outGroupCd = vehicleInfo != null ? vehicleInfo.getOutGroupCd() : "10";
        String contractType = vehicleInfo != null ? vehicleInfo.getContractTypeNm() : "임시용차";
        
        Integer maxWeight = 5000;
        Integer maxCbm = 50;
        // 중량/CBM 정보 계산 (임시)
        if(vehicleInfo != null) {
        	maxWeight = vehicleInfo.getMaxWeight() != null ? vehicleInfo.getMaxWeight().intValue() : 5000;
        	maxCbm = vehicleInfo.getMaxCube() != null ? vehicleInfo.getMaxCube().intValue() : 50;
        }
        	
        Integer loadedWeight = engineRoute.getCost() != null ? Math.min(engineRoute.getCost() / 10, maxWeight) : 500;
        Integer weightUsagePercent = (loadedWeight * 100) / maxWeight;
        
        Integer loadedCbm = loadedWeight; // 임시로 중량과 동일하게 설정
        Integer cbmUsagePercent = Math.max(1, (loadedCbm * 100) / Math.max(maxCbm, 1));
        
        // 주문 건수 및 시간/거리 정보
        Integer orderCount = engineRoute.getSteps() != null ? 
            (int) engineRoute.getSteps().stream().filter(step -> "delivery".equals(step.getType())).count() : 0;
        Integer totalTimeMinutes = engineRoute.getDuration() != null ? engineRoute.getDuration() / 60 : 0;
        String totalTimeDisplay = formatTimeDisplay(totalTimeMinutes);
        Double totalDistanceKm = engineRoute.getDistance() != null ? engineRoute.getDistance() / 1000.0 : 0.0;

        if(engineRoute.getSteps() != null) {
        	for(TmEngineStepDto step : engineRoute.getSteps()) {
        		if(step.getId() != null) {
        			
        			String skillYn = "N";
        			TmSetDispatchTruthCustResDto truthCustInfo = orderMap.get(step.getId());
      		      	if("Y".equals(truthCustInfo.getKidsClYn()) || "Y".equals(truthCustInfo.getDlvWaitYn()) || "Y".equals(truthCustInfo.getDistantYn()) || "Y".equals(truthCustInfo.getLngDistantYn()) || "Y".equals(truthCustInfo.getUnloadLvlYn())) {
      		    	  skillYn = "Y";
      		      	}
      		      	
      		        String arrival = step.getArrival();

      		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

      		        LocalDateTime dateTime = LocalDateTime.parse(arrival, formatter);

      		        long timestamp = dateTime
      		                .atZone(ZoneId.of("Asia/Seoul"))
      		                .toEpochSecond();
      		      	
      		      	String expectHhmm = Instant.ofEpochSecond(timestamp)
                          .atZone(ZoneId.of("Asia/Seoul"))
                          .format(DateTimeFormatter.ofPattern("HH:mm"));
      		      	
      		        step.setArrival(String.valueOf(timestamp));
      		      	step.setCustAddress(truthCustInfo.getAddress());
      		      	step.setCustName(truthCustInfo.getCustName());
        			step.setStorerkey(truthCustInfo.getStorerkey());
        			step.setCustType(truthCustInfo.getCustType());
        			step.setClaimYn(truthCustInfo.getClaimYn());
        			step.setFaceInspect(truthCustInfo.getFaceInspect());
        			step.setExpectedArrivalTime(expectHhmm);
        			step.setReqdlvtime1From(truthCustInfo.getReqdlvtime1From());
        			step.setReqdlvtime1To(truthCustInfo.getReqdlvtime1To());
        			step.setKeyCustType(truthCustInfo.getPasswordType());
        			step.setSpecialConditionYn(skillYn);
        			step.setDefDistrictCode(null);
        			step.setDefCarno(truthCustInfo.getCarno());
        			step.setCube(truthCustInfo.getCube());
        			step.setWeight(truthCustInfo.getWeight());
        			step.setOrderQty(truthCustInfo.getOrderQty());
        			step.setSplitDeliverySeq(truthCustInfo.getSplitDeliverySeq());
        			step.setSplitDeliveryYn(truthCustInfo.getSplitDeliveryYn());
        			step.setSlipline(truthCustInfo.getSlipline());
        			step.setSlipno(truthCustInfo.getSlipno());
        			step.setSlipdt(truthCustInfo.getSlipdt());
        			step.setReturnYn("N");
        		}
        	}
        }
        
        return TmVehicleDispatchInfoDto.builder()
            .carno(carno)
            .outGroupCd(outGroupCd)
//            .vehicleName(vehicleName)
            .vehicleType(contractType)
//            .vehicleGroup(vehicleGroup)
//            .maxWeight(maxWeight)
//            .loadedWeight(loadedWeight)
            .weightUsagePercent(weightUsagePercent)
//            .maxCbm(maxCbm)
//            .loadedCbm(loadedCbm)
            .cbmUsagePercent(cbmUsagePercent)
            .orderCount(orderCount)
            .totalTimeMinutes(totalTimeMinutes)
            .totalTimeDisplay(totalTimeDisplay)
            .totalDistanceKm(totalDistanceKm)
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
            .build();
    }

}
