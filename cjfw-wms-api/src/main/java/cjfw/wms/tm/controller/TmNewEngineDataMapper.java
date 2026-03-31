package cjfw.wms.tm.controller;

import static cjfw.wms.tm.constant.TmConstant.CONTRACT_TYPE_TEMPORARY;
import static cjfw.wms.tm.constant.TmConstant.KST;
import static cjfw.wms.tm.constant.TmConstant.STEP_TYPE_DELIVERY;
import static cjfw.wms.tm.constant.TmConstant.STEP_TYPE_PICKUP;
import static cjfw.wms.tm.constant.TmConstant.TM_DELIVERY_TYPE_DELIVERY;
import static cjfw.wms.tm.constant.TmConstant.TM_DELIVERY_TYPE_RETURN;
import static cjfw.wms.tm.constant.TmConstant.VEHICLE_TYPE_FIXED;
import static cjfw.wms.tm.constant.TmConstant.VEHICLE_TYPE_YONGCHA;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cjfw.wms.tm.client.TmEngineOptimizeConfig;
import cjfw.wms.tm.dto.*;
import cjfw.wms.tm.dto.engine.*;
import cjfw.wms.tm.util.TmPlanCommon;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import cjfw.core.exception.UserHandleException;
import cjfw.core.utility.ContextUtil;
import cjfw.wms.tm.domain.TmPlanOption;
import cjfw.wms.tm.dto.TmPlanEtaManualRouteReqDto.ViaPoint;
import cjfw.wms.tm.entity.TmPlanOptionEntity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class TmNewEngineDataMapper {

    private final TmEngineOptimizeConfig tmEngineOptimizeConfig;
    private final ObjectMapper objectMapper;

    @Value("${tm.test-mode:false}")
    private boolean testMode;

	
	// DEFAULT VALUE는 DB 데이터 미존재로 임의 설정 추후 제거 필요
    private static final double DEFAULT_LONGITUDE = 126.9780; // 서울시청 경도
    private static final double DEFAULT_LATITUDE = 37.5665;   // 서울시청 위도
    private static final int DEFAULT_PICKUP_DURATION_SECONDS = 300;  // 5분
    private static final int DEFAULT_DELIVERY_DURATION_SECONDS = 600; // 10분
    private static final int DEFAULT_PRIORITY = 5;
    private static final int AMOUNT_CONVERSION_FACTOR = 1;
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter HHMM_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private static final DateTimeFormatter HHMM_FORMATTER_WORK_HOUR = DateTimeFormatter.ofPattern("HHmm");
    public static final ZoneId SEOUL_ZONE_ID = ZoneId.of("Asia/Seoul");
    private static final String TMAP_URL = ContextUtil.getProperty("spring.tmap.url");
    private static final String TMAP_APPKEY = ContextUtil.getProperty("spring.tmap.appkey");
    private static final String TMAP_PATH_PREFIX = "/routes/routeSequential";
    private static final String TMAP_PATH_SUFFIX = "?version=1";

    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmm");

    
    /**
     * @description : 수동 엔진 요청 Method 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.13 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
     */
    public TmSetDispatchResDto buildManualRequest(
    		TmPlanEtaOptimizeAutoReqDto request,
    		Map<String, TmVehicleInfoDto> vehicleMap,
    		Map<String, TmSetDispatchTruthCustResDto> orderMap,
    		Map<String, TmSetDispatchTruthCustResDto> splitOrderMap
    ) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("appKey", TMAP_APPKEY);
        RestTemplate restTemplate = new RestTemplate();
        
        TmSetDispatchResDto successResponse = new TmSetDispatchResDto();
        List<TmVehicleDispatchInfoDto> vehicleDispatchInfoList = new ArrayList<>();
        
        request.getVehicles().forEach(vehicle -> {
            if (vehicle.getSteps().isEmpty()) return;
        	TmPlanEtaManualRouteReqDto routeReqDto = new TmPlanEtaManualRouteReqDto();
        	TmVehicleInfoDto vehicleInfo = vehicleMap.get(vehicle.getCarno());
            vehicleInfo.setRoundSeq(vehicle.getRoundSeq());

        	String longitude = vehicleInfo.getLongitude() == null ? "126.9780" : vehicleInfo.getLongitude().toString();
        	String latitude = vehicleInfo.getLatitude() == null ? "37.5665" : vehicleInfo.getLatitude().toString();

            // 수동 재계산 시 T-map API의 startTime은 출차 시간(drivingFromdate)을 사용
            // 자동 배차(Engine)와 동일하게 출차 시간 기준으로 경로 계산
            // getDrivingStartDateTime()을 사용하여 야간 근무 보정 로직 적용
            LocalDateTime dateTime = vehicleInfo.getDrivingStartDateTime();
            String startTime = dateTime.format(OUTPUT_FORMATTER);
            
            // 차량 출발/종료위치 추가
            routeReqDto.setStartName("start");
            routeReqDto.setEndName("end");
            routeReqDto.setSearchOption("0");
            routeReqDto.setCoordinateFlag("0");
            routeReqDto.setStartTime(startTime);
            routeReqDto.setStartX(longitude);
            routeReqDto.setStartY(latitude);
            
            ArrayList<ViaPoint> routes = new ArrayList<>(); 
        	vehicle.getSteps().stream().forEach(step -> {

        		step.setRoundSeq(vehicle.getRoundSeq());
				String seq = step.getSplitDeliverySeq();
				if(step.getSplitDeliverySeq() == null || step.getSplitDeliverySeq().isEmpty() ||  "N".equals(step.getSplitDeliveryYn())) {
					seq = "0";
				}
				
				if("start".equals(step.getType()) || "end".equals(step.getType())) {
					return;
				}
				
        		TmSetDispatchTruthCustResDto orderData = splitOrderMap.get(step.getId() + "-" + step.getType() + "-" + seq);
        		
        		int workTime = TmPlanCommon.toInt(orderData.getWorkTime(), 10) * 60;
        		
//        		workTime = workTime != null && !workTime.isEmpty() ? workTime.split("\\.")[0] : "600";
        		
        		TmPlanEtaManualRouteReqDto.ViaPoint viaPoint = new TmPlanEtaManualRouteReqDto.ViaPoint();
        		
        		viaPoint.setViaPointId(step.getId() + "-" + step.getType() + "-" + seq);
        		viaPoint.setViaPointName(step.getId());
        		viaPoint.setViaX(step.getLocation().get(0).toString());
        		viaPoint.setViaY(step.getLocation().get(1).toString());
        		viaPoint.setViaTime(String.valueOf(workTime));

                // OTD 넣기
                TmSetDispatchTruthCustResDto viaOrderMap = orderMap.get(step.getId());
                List<Long> otdTimeList = viaOrderMap.getOtdTime(request.getDeliveryDate());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
                if(viaOrderMap.getReqdlvtime1From() != null){
                    viaPoint.setWishStartTime(sdf.format(new java.util.Date(otdTimeList.get(0) * 1000)));
                    viaPoint.setWishEndTime(sdf.format(new java.util.Date(otdTimeList.get(1) * 1000)));
                }
        		routes.add(viaPoint);
        	});

            // [겷함 #524] 수동재계산 건 네이버경로 상 운행거리와 WayLo 운행거리 2배 차이나는 현상
            // 주행 거리 조정 위해 마지막 거래처 좌표를 차량 end 좌표로 설정하는 것으로 보완
            String lastPointLongitude = routes.get(routes.size() -1).getViaX();
            String lastPointLatitude = routes.get(routes.size() -1).getViaY();
            routeReqDto.setEndX(lastPointLongitude);
            routeReqDto.setEndY(lastPointLatitude);
        	
        	routeReqDto.setViaPoints(routes);
        	HttpEntity<TmPlanEtaManualRouteReqDto> entity = new HttpEntity<>(routeReqDto, headers);
        	
        	int jobCount = entity.getBody().getViaPoints().size();
        	String tMapRequestJobCount = "";
        	if(jobCount <= 30) {
        		tMapRequestJobCount = "30";
        	}else if(jobCount <= 100) {
        		tMapRequestJobCount = "100";
        	}else {
        		tMapRequestJobCount = "200";
        	}

            try {
                log.info("[TMAP-API][{}, {}] {}", vehicle.getCarno(), vehicle.getId(), objectMapper.writeValueAsString(routeReqDto));
            } catch (JsonProcessingException e) {
                log.error("TMAP api call fail : {}", e.getMessage(), e);
            }
            ResponseEntity<TmPlanEtaManualRouteResDto> response = restTemplate.exchange(
                    TMAP_URL + TMAP_PATH_PREFIX + tMapRequestJobCount + TMAP_PATH_SUFFIX,
                    HttpMethod.POST,
                    entity,
                    TmPlanEtaManualRouteResDto.class
            );
            
            if(!response.getStatusCode().is2xxSuccessful()) {
            	throw new UserHandleException("MSG_COM_ERR_999", new String[]{"수동 경로계산 오류"});
            }
            
            TmVehicleDispatchInfoDto result = convertTmapResponse(response.getBody(), vehicleInfo, orderMap, splitOrderMap);
            vehicleDispatchInfoList.add(result);
        });

        successResponse.setVehicles(vehicleDispatchInfoList);
        return successResponse;
    }
    
    /**
     * @description : 수동배차 값 매핑 Method 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.13 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
     */
    private TmVehicleDispatchInfoDto convertTmapResponse(
    		TmPlanEtaManualRouteResDto dto,
    		TmVehicleInfoDto vehicleInfo,
    		Map<String, TmSetDispatchTruthCustResDto> orderMap,
    		Map<String, TmSetDispatchTruthCustResDto> splitOrderMap
		) {
    	
    	Map<String, TmEngineStepDto> stepMap = new LinkedHashMap<>();
    	
    	TmVehicleDispatchInfoDto vehicleDispatchInfoDto = new TmVehicleDispatchInfoDto();
    	
    	String carCapacity = vehicleInfo.getCarCapacity();
	    if(carCapacity != null) {
           	carCapacity += "톤";
        }
        if(carCapacity == null && vehicleInfo.getMaxWeight() != null) {
    		carCapacity = vehicleInfo.getMaxWeight() / 1000 + "톤";
        }
    	
    	vehicleDispatchInfoDto.setCarno(vehicleInfo.getCarno());
    	vehicleDispatchInfoDto.setMaxCbm(vehicleInfo.getMaxCube() == null ? "3000" : vehicleInfo.getMaxCube().toString());
    	vehicleDispatchInfoDto.setMaxWeight(vehicleInfo.getMaxWeight() == null ? "3000" :vehicleInfo.getMaxWeight().toString());
    	vehicleDispatchInfoDto.setContractType(vehicleInfo.getContractType());
        vehicleDispatchInfoDto.setCarCapacity(carCapacity);
        vehicleDispatchInfoDto.setOutGroupCd(vehicleInfo.getOutGroupCd());
        vehicleDispatchInfoDto.setVehicleType(vehicleInfo.getContractTypeNm());
        vehicleDispatchInfoDto.setDrivername(TmPlanCommon.maskDriverName(vehicleInfo.getDrivername()));
        vehicleDispatchInfoDto.setPhone1(vehicleInfo.getPhone1());

    	double orderTotalCbm = 0.0;
    	double orderTotalWeight = 0.0;
        int orderCnt = 0;
    	
        if (dto.getFeatures() == null || dto.getFeatures().isEmpty()) {
            return null;
        }
        
        for (TmPlanEtaManualRouteResDto.Feature feature : dto.getFeatures()) {
        	TmEngineStepDto stepDto = new TmEngineStepDto();
            stepDto.setRoundSeq(vehicleInfo.getRoundSeq());

            String arrival = String.valueOf(parseArrival(feature.getProperties().getArriveTime()));
            if ("S".equals(feature.getProperties().getPointType())) {
            	stepDto.setType("start");
            	stepDto.setArrival(arrival);
            	stepDto.setExpectedArrivalTime(feature.getProperties().getArriveTime());
            	stepDto.setLocation((List<Double>) feature.getGeometry().getCoordinates());
            	stepMap.put("start", stepDto);
            } else if ("E".equals(feature.getProperties().getPointType())) {
            	stepDto = stepMap.get("end") == null ? stepDto : stepMap.get("end");
            	stepDto.setType("end");
            	stepDto.setArrival(arrival);
            	stepDto.setExpectedArrivalTime(feature.getProperties().getArriveTime());
        		if("LineString".equals(feature.getGeometry().getType())) {
        			stepDto.setGeometry(encode(feature.getGeometry().getCoordinates()));
        		} else if ("Point".equals(feature.getGeometry().getType())) {
        			stepDto.setLocation((List<Double>) feature.getGeometry().getCoordinates());
        		}
        		stepMap.put("end", stepDto);
            } else if (!"N".equals(feature.getProperties().getPointType()) &&
                    !ObjectUtils.isEmpty(feature.getProperties().getViaPointId())) {
            	String viaPointId = feature.getProperties().getViaPointId();
            	
            	TmSetDispatchTruthCustResDto orderInfo = splitOrderMap.get(viaPointId);
               	stepDto = stepMap.get(viaPointId) == null ? stepDto : stepMap.get(viaPointId);
            	
        		if("LineString".equals(feature.getGeometry().getType())) {
        			stepDto.setGeometry(encode(feature.getGeometry().getCoordinates()));
        		} else if ("Point".equals(feature.getGeometry().getType())) {
        			stepDto.setLocation((List<Double>) feature.getGeometry().getCoordinates());
                    orderCnt++;
                    orderTotalCbm += Double.parseDouble(orderInfo.getCube());
                    orderTotalWeight += Double.parseDouble(orderInfo.getWeight());
        		}
        		
        		
        		stepDto.setArrival(arrival);
        		stepDto.setClaimYn(orderInfo.getClaimYn());
        		stepDto.setCustAddress(orderInfo.getAddress());
        		stepDto.setCustName(orderInfo.getCustName());
        		stepDto.setExpectedArrivalTime(feature.getProperties().getArriveTime());
        		stepDto.setDistance(feature.getProperties().getDistance());
        		stepDto.setId(viaPointId);
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
        		stepDto.setService(Integer.parseInt(feature.getProperties().getDeliveryTime()));
        		stepDto.setFaceInspect(orderInfo.getFaceInspect());
                stepDto.setReturnYn(orderInfo.getReturnYn());
                stepDto.setStorerkey(orderInfo.getStorerkey());
                stepDto.setCustType(orderInfo.getCustType());
                stepDto.setPasswordType(orderInfo.getPasswordType());
                stepDto.setPasswordTypeCd(orderInfo.getPasswordTypeCd());
                stepDto.setPop(orderInfo.getPop());
        		stepDto.createUniqueId();

        		stepMap.put(viaPointId, stepDto);
            }
        }

        List<TmEngineStepDto> result = new ArrayList<>();
        
        vehicleDispatchInfoDto.setLoadedCbm(Double.toString(orderTotalCbm));
        vehicleDispatchInfoDto.setLoadedWeight(Double.toString(orderTotalWeight));
        vehicleDispatchInfoDto.setOrderCount(orderCnt);
        double totalDistance = Double.parseDouble(dto.getProperties().getTotalDistance());
        int totalTime = Integer.parseInt(dto.getProperties().getTotalTime());
        vehicleDispatchInfoDto.setTotalDistanceKm(Optional.of(totalDistance).orElse(0.0) / 1000.0);
        vehicleDispatchInfoDto.setTotalTimeMinutes(Optional.of(totalTime).orElse(0) / 60);
        vehicleDispatchInfoDto.setTotalTimeDisplay(formatTimeDisplay(vehicleDispatchInfoDto.getTotalTimeMinutes()));
        
        result.addAll(stepMap.values());
        
        result.forEach(r -> {
        	if(!"end".equals(r.getId()) && !"start".equals(r.getId()) && r.getId() != null) {
        		
        		int lastDashIndex = r.getId().lastIndexOf('-'); 
                int secondToLastDashIndex = r.getId().lastIndexOf('-', lastDashIndex - 1); 
                String id = r.getId().substring(0, secondToLastDashIndex);
                String type = r.getId().substring(secondToLastDashIndex + 1, lastDashIndex);
                String splitDeliverySeq = r.getId().substring(lastDashIndex + 1);
                
                r.setSplitDeliverySeq(splitDeliverySeq);
        		r.setDescription(id+"-"+type);
        		r.setId(id);
        		r.setType(type);
        	}
        	
        	if(r.getArrival() != null) {
        	  String time = Instant.ofEpochSecond(Long.parseLong(r.getArrival()))
                      .atZone(ZoneId.of("Asia/Seoul"))
                      .format(DateTimeFormatter.ofPattern("HH:mm"));
        	  r.setExpectedArrivalTime(time);
        	}	
        });
        
        vehicleDispatchInfoDto.setSteps(result);
        vehicleDispatchInfoDto.setGeometry(encode(mergeAllLineStringGeometries(dto)));
        vehicleDispatchInfoDto.setRoundSeq(vehicleInfo.getRoundSeq());
        vehicleDispatchInfoDto.createUniqueId();
        return vehicleDispatchInfoDto;
    }


    /**
     * TMap 응답에서 모든 LineString geometry를 병합하는 메서드
     *
     * @param dto TMap 경로 응답 DTO
     * @return 병합된 좌표 리스트 (List<List<Double>>)
     */
    public List<Double[]> mergeAllLineStringGeometries(TmPlanEtaManualRouteResDto dto) {
        List<Double[]> mergedGeometry = new ArrayList<>();

        if (dto == null || dto.getFeatures() == null || dto.getFeatures().isEmpty()) {
            return mergedGeometry;
        }

        for (TmPlanEtaManualRouteResDto.Feature feature : dto.getFeatures()) {
            if (feature.getGeometry() == null) {
                continue;
            }

            // LineString 타입인 경우에만 좌표 추가
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
     * @description : 엔진 요청 Request 생성 Method 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.13 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
     */
    public TmEngineRequestDto buildEngineRequest(List<TmSetDispatchTruthCustResDto> orders,
                                                 List<TmVehicleInfoDto> vehicles,
                                                 TmDispatchCenterInfoResDto centerInfo,
                                                 TmPlanOption dispatchOptionDto,
                                                 TmSetDispatchReqDto request) {
        
        // 주문을 Shipment로 변환
        List<TmEngineShipmentDto> shipments = orders.stream()
            .map(item -> toEngineShipment(item, centerInfo, null, dispatchOptionDto, request.getDeliveryDate()))
            .filter(Objects::nonNull)
            .toList();

        // 주문을 Jobs로 변환 (변경예정)
        List<TmEngineJobDto> jobs = orders.stream()
                .map(item -> toEngineJob(item, centerInfo, null, dispatchOptionDto, request.getDeliveryDate()))
                .filter(Objects::nonNull)
                .toList();

        // 차량을 Vehicle로 변환
        List<TmEngineVehicleDto> engineVehicles = vehicles.stream()
            .map(item -> toEngineVehicle(item, dispatchOptionDto))
            .filter(Objects::nonNull)
            .toList();

        return TmEngineRequestDto.builder()
            // .shipments(shipments)
                .jobs(jobs)
            .vehicles(engineVehicles)
            .options(tmEngineOptimizeConfig.getOptimizeOption(dispatchOptionDto))
            .timezone(KST)
            .description(request.getDescription())
            .build();
    }
    
    /**
     * @description : 재계산 요청 Request 생성 Method 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.13 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
     */
    public TmEngineRequestDto planEtaOptimizeAutoParseEngineRequest(
            TmPlanEtaOptimizeAutoReqDto request,
            TmDispatchCenterInfoResDto centerInfo,
            Map<String, TmSetDispatchTruthCustResDto> orderMap,
            Map<String, TmVehicleInfoDto> vehicleMap,
            TmPlanOption planOption) {

        List<TmEngineVehicleDto> vehicleDtoList = request.getVehicles().stream()
                .map(vehicle -> createEngineVehicleFromPlan(vehicle, vehicleMap.get(vehicle.getCarno()), planOption))
                .toList();

        List<TmEngineShipmentDto> shipmentDtoList = request.getVehicles().stream()
                .flatMap(vehicle -> vehicle.getSteps().stream()
                        .map(step -> createEngineShipmentFromPlan(
                                                                step,
                                                                vehicle.getId(),
                                                                centerInfo,
                                                                orderMap.get(step.getId()),
                                                                planOption,
                                                                request.getDeliveryDate()))

                )
                .filter(Objects::nonNull)
                .toList();

        List<TmEngineJobDto> jobsDtoList = request.getVehicles().stream()
                .flatMap(vehicle -> vehicle.getSteps().stream()
                        .map(step -> createEngineJobFromPlan(
                                step,
                                vehicle.getId(),
                                centerInfo,
                                orderMap.get(step.getId()),
                                planOption,
                                request.getDeliveryDate()))
                )
                .filter(Objects::nonNull)
                .toList();

        return TmEngineRequestDto.builder()
                .vehicles(vehicleDtoList)
                // .shipments(shipmentDtoList)
                .jobs(jobsDtoList)
                .options(tmEngineOptimizeConfig.getOptimizeOption(planOption))
                .timezone(KST)
                .description(request.getDescription())
                .build();
    }
    /**
     * @description : 실비차 엔진 요청 Request Mapping Method 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.17 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
     */
    public TmEngineRequestDto buildTemporaryCarEngineRequest(
            List<TmSetDispatchTemporaryCarResDto> vehicleDtoList,
            List<TmSetDispatchUnassignedOrderResDto> orderDtoList,
            TmDispatchCenterInfoResDto centerInfo,
            String deliveryDate,
            TmSetDispatchOutGroupReqDto request) {
    	
    	// 요청 실비차 데이터 기준 차량 데이터 매핑
    	List<TmEngineVehicleDto> vehicle =  vehicleDtoList.stream().map(temporaryCar -> {
    		TmEngineVehicleDto vehicleDto = new TmEngineVehicleDto();
    		vehicleDto.setId(temporaryCar.getId());
    		vehicleDto.setStart_location(Arrays.asList(
                    parseCoordinate(temporaryCar.getLongitude()).orElse(DEFAULT_LONGITUDE),
                    parseCoordinate(temporaryCar.getLatitude()).orElse(DEFAULT_LATITUDE)
            ));
    		vehicleDto.setVehicle_type(VEHICLE_TYPE_YONGCHA);
    		vehicleDto.setWork_start_time(temporaryCar.getDrivingFromdate());
    		vehicleDto.setWork_end_time(temporaryCar.getDrivingTodate());
//            실비차 (maxCbm: m³ → cm³ 변환)
    		vehicleDto.setCapacity(Arrays.asList(
                    toInteger(temporaryCar.getMaxCbm(), 15, 1000000),
                    toInteger(temporaryCar.getMaxWeight(), 2000, 1),
//                    50 * 1,
                    Integer.parseInt(temporaryCar.getMaxLoadQty())
			));
            vehicleDto.setMax_trips(1);

            // 최대 착지수 설정 (최대착지수 = 최대착지수), null인 경우 0
            int maxStops = 0;
            if (!ObjectUtils.isEmpty(temporaryCar.getMaxLoadQty())) {
                try {
                    maxStops = Integer.parseInt(temporaryCar.getMaxLoadQty().trim());
                } catch (NumberFormatException e) {
                    log.warn("최대 착지수 변환 실패 - 차량: {}, 값: {}", "실비차", temporaryCar.getMaxLoadQty());
                }
            }

            vehicleDto.setMax_stops(maxStops);
            vehicleDto.setMin_stops(0); // 실비차는 최소 착지수 제한 없음
//            실비차
//    		vehicleDto.setCapacity(temporaryCar.getCapacity(planOption));
    		return vehicleDto;
    	}).toList();
    	
    	// 미배차 주문 데이터 생성
    	List<TmEngineShipmentDto> shipment =  orderDtoList.stream().map(order -> {
            List<Integer> amount = Arrays.asList(
                    toInteger(order.getCube(), 1, 1),
                    toInteger(order.getWeight(), 1, 1),
//                    toInteger(order.getOrderQty(), 1, 1),
                    1
            );

            List<Double> pickupLocation = Arrays.asList(
                    parseCoordinate(centerInfo.getLongitude()).orElse(DEFAULT_LONGITUDE),
                    parseCoordinate(centerInfo.getLatitude()).orElse(DEFAULT_LATITUDE)
            );
            
            // 수동분할 고려 requestId 생성
            String requestId = order.getId();
            if ("Y".equals(order.getSplitDeliveryYn())) {
                int splitSeq = Integer.parseInt(order.getSplitDeliverySeq());
                requestId = order.getId() + "-" + splitSeq;
            }
    		
//            boolean isPickupType = "2".equals(order.getTmDeliveryType());

            return TmEngineShipmentDto.builder()
                    .id(requestId)
                    .type("pop")
                    .pickup_location(pickupLocation)
                    .delivery_location(order.getLocation())
                    .amount(amount)
                    .delivery_address(order.getCustAddress())
                    .pickup_address(Optional.ofNullable(centerInfo.getDcname()).orElse(""))
                    .pickup_duration(DEFAULT_PICKUP_DURATION_SECONDS)
                    .delivery_duration(DEFAULT_DELIVERY_DURATION_SECONDS)
//                    .skills(skills)
                    .priority(DEFAULT_PRIORITY)
                    .otd_time(order.getOtdTime(deliveryDate))
                    .build();
    	}).toList();

        // 미배차 주문 데이터 생성(Job)
        List<TmEngineJobDto> jobs = orderDtoList.stream().map(order -> {
            List<Integer> amount = Arrays.asList(
                    toInteger(order.getCube(), 1, 1),
                    toInteger(order.getWeight(), 1, 1),
//                    toInteger(order.getOrderQty(), 1, 1),
                    1
            );

            // 수동분할 고려 requestId 생성
            String requestId = order.getId();
            if ("Y".equals(order.getSplitDeliveryYn())) {
                int splitSeq = Integer.parseInt(order.getSplitDeliverySeq());
                requestId = order.getId() + "-" + splitSeq;
            }

//            boolean isPickupType = "2".equals(order.getTmDeliveryType());

            return TmEngineJobDto.builder()
                    .id(requestId)
                    .location(order.getLocation())
                    .delivery(amount)
                    .service(DEFAULT_DELIVERY_DURATION_SECONDS)
                    .priority(DEFAULT_PRIORITY)
                    .otd_time(order.getOtdTime(deliveryDate))
                    .build();
        }).toList();


        return TmEngineRequestDto.builder()
                .vehicles(vehicle)
                // .shipments(shipment)
                .jobs(jobs)
                .options(tmEngineOptimizeConfig.getDefaultOptions())
                .timezone(KST)
                .description(request.getDescription())
                .build();
    }

    private TmEngineVehicleDto createEngineVehicleFromPlan(TmPlanEtaVehicleReqDto vehicle,
                                                           TmVehicleInfoDto vehicleInfo,
                                                           TmPlanOption planOption) {
    	// vehicle: 요청으로 들어온 값, vehicleInfo: DB에서 조회한 값
        List<Integer> capacity;
        String vehicleType = VEHICLE_TYPE_FIXED;
        // 실비차일시 요청 시 들어온 입력값 데이터 사용
    	if(CONTRACT_TYPE_TEMPORARY.equals(vehicle.getContractType())) {
            capacity = vehicle.getCapacity(planOption);
            vehicleType = VEHICLE_TYPE_YONGCHA;
    	} else {
            if (testMode) {
                capacity = getCapacityForTestMode(planOption);
            } else {
                capacity = vehicleInfo.getCapacity(planOption);
            }
    	}

        List<Double> startLocation = Arrays.asList(
                Optional.ofNullable(vehicleInfo.getLongitude()).orElse(DEFAULT_LONGITUDE),
                Optional.ofNullable(vehicleInfo.getLatitude()).orElse(DEFAULT_LATITUDE)
        );

        // 배차 옵션에 다회전 여부 ON, 차량 설정에 ON(적용필요)
        List<Double> endLocation = null;
        if (planOption.isOnMultiTurn()) {
            endLocation = startLocation;
        }

        LocalDateTime workStartTime = vehicleInfo.getDrivingStartDateTime();
        LocalDateTime workEndTime = vehicleInfo.getDrivingEndDateTime();

        int maxTrip = vehicle.getMaxTrips(vehicleInfo, planOption.isOnMultiTurn());

        if (testMode) {
            if (vehicleInfo.isInvalidDrivingTime()) {
                workEndTime = workStartTime.plusHours(5); // [TODO] 임시 로직, 제거 필요
            }
        } else {
            if (vehicleInfo.isInvalidDrivingTime()) {
                throw new UserHandleException("주행 종료시간은 시작시간보다 커야합니다. " + vehicleInfo.getCarno());
            }
        }

        // 최소 착지수 설정 (기본착지수 = 최소착지수), null인 경우 0
        int minStops = 0;
        if (vehicleInfo.getMinLanding() != null && !vehicleInfo.getMinLanding().trim().isEmpty()) {
            try {
                minStops = Integer.parseInt(vehicleInfo.getMinLanding().trim());
            } catch (NumberFormatException e) {
                log.warn("최소 착지수 변환 실패 - 차량: {}, 값: {}", vehicleInfo.getCarno(), vehicleInfo.getMinLanding());
            }
        }

        return TmEngineVehicleDto.builder()
                .id(vehicle.getId())
                .start_location(startLocation)
                .end_location(null)
                .capacity(capacity)
                .preferred_skills(Collections.singletonList(vehicle.getId()))
                .vehicle_type(vehicleType)
                .work_start_time(workStartTime.format(DATETIME_FORMATTER))
                .work_end_time(workEndTime.format(DATETIME_FORMATTER))
                .max_trips(maxTrip)
                .car_number(vehicle.getCarno())
                .min_stops(minStops)
                .build();
    }

    private List<Integer> getCapacityForTestMode(TmPlanOption planOption) {
        List<Integer> capacity = new ArrayList<>();
        if (planOption.isOnMaxCbm())
            capacity.add(10); // , 1000, 10);
        capacity.add(1000);
        if (planOption.isOnMaxLocation())
            capacity.add(10);
        return capacity;
    }

    private TmEngineShipmentDto createEngineShipmentFromPlan(
                                    TmPlanEtaStepReqDto step,
                                    String carno,
                                    TmDispatchCenterInfoResDto centerInfo,
                                    TmSetDispatchTruthCustResDto orderInfo,
                                    TmPlanOption planOption,
                                    String deliveryDate) {
        if (step.getId() == null || orderInfo == null) return null;
        if (/*("2".equals(step.getTmDeliveryType()) && "delivery".equals(step.getType())) || */
                ("1".equals(step.getTmDeliveryType()) && "pickup".equals(step.getType()))) {
            return null;
        }

        String requestId = step.getId();
        if ("Y".equals(step.getSplitDeliveryYn())) {
            int splitSeq = Integer.parseInt(step.getSplitDeliverySeq()) + 1 ;
            requestId = step.getId() + "-" + splitSeq;
        }

        if (TM_DELIVERY_TYPE_RETURN.equals(orderInfo.getTmDeliveryType())) {
            return createEngineShipment4Return(requestId, step, carno, centerInfo, orderInfo, planOption, deliveryDate);
        } else {
            return createEngineShipment4Delivery(requestId, step, carno, centerInfo, orderInfo, planOption, deliveryDate);
        }

    }

    private TmEngineShipmentDto createEngineShipment4Return(String requestId,
                                                            TmPlanEtaStepReqDto step,
                                                            String carno,
                                                            TmDispatchCenterInfoResDto centerInfo,
                                                            TmSetDispatchTruthCustResDto orderInfo,
                                                            TmPlanOption planOption,
                                                            String deliveryDate) {

        // 반품 배차 응답 데이티 이상으로 임시조치. (엔진에서 jobs 강제 처리(임시) 후과). 해야할 일에 대해 임시로 조치하고 그 후과에 대해 또 임시 조치하고 결국 일은 늘어나고 효율 떨어지고 생산성 떨어지고 어떤 선택을 해야하는지 배우기를 제발...
        // TODO: [엔진에서 jobs 강제 처리(임시조치)][임시조치] 엔진 요청 jobs 구조로 반영 시, 아래 코드 원복 또는 보완 필요
        // by 윤광호님 : 기존에 jobs로 요청해야 하는 요청들이 shipments로 요청을 보내고 있었기 때문에,
        // 엔진 쪽에서 강제로 shipments를 jobs으로 수정하면서 발생하는 현상입니다.
        // @이기현 이사 님과 협의한 바로는 픽업위치는 물류센터, 딜리버리는 반품장소로 지정하시면 됩니다.
        // 용적량은 최소로 설정하면 됩니다(0,0,0 같은 값). 이 방법은 임시적인 방법이므로 추후에 수정 될 수 있습니다. cc. @박초롱 책임
        List<Double> deliveryLocation = Arrays.asList(
                parseCoordinate(centerInfo.getLongitude()).orElse(DEFAULT_LONGITUDE),
                parseCoordinate(centerInfo.getLatitude()).orElse(DEFAULT_LATITUDE)
        );

        List<Double> pickupLocation = Arrays.asList(
                parseCoordinate(orderInfo.getLongitude()).orElse(DEFAULT_LONGITUDE),
                parseCoordinate(orderInfo.getLatitude()).orElse(DEFAULT_LATITUDE)
        );

        //  반품의 경우 중량, 체적 = 0 설정해서 전송. 중량으로 반품 건 배차 실패되지 않아야
        List<Integer> amount = step.getAmount(planOption);

        return TmEngineShipmentDto.builder()
                .id(requestId)
                .type("pop")
                .pickup_address(Optional.ofNullable(orderInfo.getCustName()).orElse(""))
                .pickup_location(deliveryLocation)
                .delivery_address(Optional.ofNullable(centerInfo.getDcname()).orElse(""))
                .delivery_location(pickupLocation)
                .amount(amount)
                .pickup_duration(DEFAULT_PICKUP_DURATION_SECONDS)
                .delivery_duration(DEFAULT_DELIVERY_DURATION_SECONDS)
                .preferred_skills(Collections.singletonList(carno))
                .priority(DEFAULT_PRIORITY)
                .otd_time(orderInfo.getOtdTime(deliveryDate))
                .build();
    }

    private TmEngineShipmentDto createEngineShipment4Delivery(String requestId,
                                                              TmPlanEtaStepReqDto step,
                                                              String carno,
                                                              TmDispatchCenterInfoResDto centerInfo,
                                                              TmSetDispatchTruthCustResDto orderInfo,
                                                              TmPlanOption planOption,
                                                              String deliveryDate) {

        List<Double> pickupLocation = Arrays.asList(
                parseCoordinate(centerInfo.getLongitude()).orElse(DEFAULT_LONGITUDE),
                parseCoordinate(centerInfo.getLatitude()).orElse(DEFAULT_LATITUDE)
        );

        List<Integer> amount = step.getAmount(planOption);

        // 특수조건/출입KEY 스킬 설정 (담당차량 기반)
        List<String> skillsForSpecialCondition = orderInfo.getSkillsForSpecialCondition(planOption.isOnSkills());

        TmEngineShipmentDto engineShipmentDto = TmEngineShipmentDto.builder()
                .id(requestId)
                .type("pop")
                .pickup_address(Optional.ofNullable(centerInfo.getDcname()).orElse(""))
                .pickup_location(pickupLocation)
                .delivery_address(Optional.ofNullable(orderInfo.getCustName()).orElse(""))
                .delivery_location(step.getLocation())
                .amount(amount)
                .pickup_duration(DEFAULT_PICKUP_DURATION_SECONDS)
                .delivery_duration(Optional.ofNullable(orderInfo.getWorkTime())
                        .map(minutes -> TmPlanCommon.toInt(minutes, 10) * 60)
                        .orElse(DEFAULT_DELIVERY_DURATION_SECONDS))
                .skills(skillsForSpecialCondition)
                .blocked_vehicle_ids(orderInfo.getBlockedCars())
                .priority(DEFAULT_PRIORITY)
                .otd_time(orderInfo.getOtdTime(deliveryDate))
                .build();

        if (!ObjectUtils.isEmpty(orderInfo.getCarno())) {
            engineShipmentDto.setPreferred_skills(Collections.singletonList(orderInfo.getCarno())) ;
        }
        return engineShipmentDto;
    }

    /**
     * 주문 정보를 Engine Shipment DTO로 변환합니다.
     */
    public TmEngineShipmentDto toEngineShipment(TmSetDispatchTruthCustResDto order,
                                                TmDispatchCenterInfoResDto centerInfo,
                                                Map<String, String> stepVehicleMap,
                                                TmPlanOption planOption,
                                                String deliveryDate) {
        if (order == null) {
            log.warn("주문 정보가 null입니다.");
            return null;
        }
        
        // 미배차로 처리?
        if(order.getLatitude() == null || order.getLongitude() == null) {
        	return null;
        }

        try {
        	
        	// DEFAULT VALUE는 DB 데이터 미존재로 임의 설정 추후 제거 필요
            List<Double> centerLocation = Arrays.asList(
                    parseCoordinate(centerInfo.getLongitude()).orElse(DEFAULT_LONGITUDE),
                    parseCoordinate(centerInfo.getLatitude()).orElse(DEFAULT_LATITUDE)
            );
            List<Double> custLocation = Arrays.asList(
                    parseCoordinate(order.getLongitude()).orElse(DEFAULT_LONGITUDE),
                    parseCoordinate(order.getLatitude()).orElse(DEFAULT_LATITUDE)
            );

            // 특수조건/출입KEY 스킬 설정 (담당차량 기반)
            List<String> skillsForSpecialCondition = order.getSkillsForSpecialCondition(planOption.isOnSkills());

            boolean isPickupType = "2".equals(order.getTmDeliveryType());

            TmEngineShipmentDto engineShipmentDto = TmEngineShipmentDto.builder()
                    .id(Optional.ofNullable(order.getTruthCustKey()).orElse("UNKNOWN"))
                    .type("pop")
                    .pickup_address(isPickupType ? order.getCustName() == null ? order.getAddress() : order.getCustName() : centerInfo.getDcname())
                    .pickup_location(isPickupType ? custLocation : centerLocation)
                    .delivery_address(isPickupType ? centerInfo.getDcname() : order.getCustName() == null ? order.getAddress() : order.getCustName())
                    .delivery_location(isPickupType ? centerLocation : custLocation)
                    .amount(order.getAmount(planOption))
                    .pickup_duration(DEFAULT_PICKUP_DURATION_SECONDS)
                    .delivery_duration(Optional.ofNullable(order.getWorkTime())
                            .map(minutes -> TmPlanCommon.toInt(minutes, 10) * 60)
                            .orElse(DEFAULT_DELIVERY_DURATION_SECONDS))
                    .priority(DEFAULT_PRIORITY)
                    .blocked_vehicle_ids(order.getBlockedCars())
                    .skills(skillsForSpecialCondition)
                    .otd_time(order.getOtdTime(deliveryDate))
//                    .pop(order.getPop()) // TODO: 엔진 개발 완료 시 pop 전송
                    .build();

            if (!ObjectUtils.isEmpty(order.getCarno())) {
                engineShipmentDto.setPreferred_skills(Collections.singletonList(order.getCarno())) ;
            }
            return engineShipmentDto;
        } catch (Exception e) {
            log.error("주문 정보를 Engine Shipment로 변환 중 오류 발생 - 주문: {}, 오류: {}", order.getTruthCustKey(), e.getMessage(), e);
            return null;
        }
    }

    /**
     * 차량 정보를 Engine Vehicle DTO로 변환합니다. (중복 메서드 통합)
     */
    public TmEngineVehicleDto toEngineVehicle(TmVehicleInfoDto vehicle, TmPlanOption planOption) {
        if (vehicle == null) {
            log.warn("차량 정보가 null입니다.");
            return null;
        }

        try {
            List<Double> startLocation = Arrays.asList(
                    Optional.ofNullable(vehicle.getLongitude()).orElse(DEFAULT_LONGITUDE),
                    Optional.ofNullable(vehicle.getLatitude()).orElse(DEFAULT_LATITUDE)
            );

            // 차량별 종착지 확인 후 endLocation 지정 필요.
            List<Double> endLocation = null;
            // 배차 옵션에 다회전 여부 ON, 차량 설정에 ON(적용필요)
            if (planOption.isOnMultiTurn()) {
                 endLocation = startLocation;
            }

            List<Integer> capacity= null;
            if (testMode) {
                capacity = getCapacityForTestMode(planOption);
            } else {
                capacity = vehicle.getCapacity(planOption);
            }

            LocalDateTime workStartTime = vehicle.getDrivingStartDateTime();
            LocalDateTime workEndTime = vehicle.getDrivingEndDateTime();

            if (testMode) {
                if (vehicle.isInvalidDrivingTime()) {
                    workEndTime = workStartTime.plusHours(5); // [TODO] 임시 로직, 제거 필요
                }
            } else {
                if (vehicle.isInvalidDrivingTime()) {
                    throw new UserHandleException("주행 종료시간은 시작시간보다 커야합니다. " + vehicle.getCarno());
                }
            }

            List<String> skills = new ArrayList<>();
            
            if (vehicle.getCarno() != null) {
                skills.add(vehicle.getCarno());
            }

            // 최소 착지수 설정 (기본착지수 = 최소착지수), null인 경우 0
            int minStops = 0;
            if (vehicle.getMinLanding() != null && !vehicle.getMinLanding().trim().isEmpty()) {
                try {
                    minStops = Integer.parseInt(vehicle.getMinLanding().trim());
                } catch (NumberFormatException e) {
                    log.warn("최소 착지수 변환 실패 - 차량: {}, 값: {}", vehicle.getCarno(), vehicle.getMinLanding());
                }
            }

            return TmEngineVehicleDto.builder()
                    .id(vehicle.getCarno())
                    .start_location(startLocation)
                    .end_location(null)
                    .capacity(capacity)
                    .skills(skills)
                    .preferred_skills(skills)
                    .vehicle_type(VEHICLE_TYPE_FIXED)
                    .work_start_time(workStartTime.format(DATETIME_FORMATTER))
                    .work_end_time(workEndTime.format(DATETIME_FORMATTER))
                    .max_trips(vehicle.getMaxTrips(planOption.isOnMultiTurn()))
                    .car_number(vehicle.getCarno())
                    .min_stops(minStops)
                    .build();

        } catch (Exception e) {
            log.error("차량 정보를 Engine Vehicle로 변환 중 오류 발생 - 차량: {}, 오류: {}", vehicle.getCarno(), e.getMessage(), e);
            return null;
        }
    }



    /**
     * @description : 엔진 계산 응답 response 생성 Method 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.13 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
     */
    public TmSetDispatchResDto createSuccessResponse(
            TmSetDispatchReqDto request,
            TmEngineResponseDto engineResponse,
            List<TmVehicleInfoDto> vehicles,
            List<TmSetDispatchTruthCustResDto> orders,
            List<TmSetDispatchReturnOrderResDto> returnOrders) {

        // DB 데이터로 Map 생성 ( 데이터 매핑용 )
        Map<String, TmVehicleInfoDto> vehicleMap = vehicles.stream()
                .collect(Collectors.toMap(
                        TmVehicleInfoDto::getCarno,
                        v -> v,
                        (oldValue, newValue) -> newValue
                ));

        return createResponseDto(request.getDccode(), request.getDeliveryDate(), request.getDeliveryType(),
                engineResponse, vehicleMap, orders, returnOrders, null);
    }
    public TmSetDispatchResDto createSuccessResponse(
            TmSetDispatchReqDto request,
            TmEngineResponseDto engineResponse,
            Map<String, TmVehicleInfoDto> vehicleMap,
            List<TmSetDispatchTruthCustResDto> orders,
            List<TmSetDispatchReturnOrderResDto> returnOrders) {

        return createResponseDto(request.getDccode(), request.getDeliveryDate(), request.getDeliveryType(),
                engineResponse, vehicleMap, orders, returnOrders, null);
    }
    
    /**
     * @description : 분할 배송 포함된 응답 response 생성 Method 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.13 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
     */
    public TmSetDispatchResDto createSuccessResponse(
            TmPlanEtaOptimizeAutoReqDto request,
            TmEngineResponseDto engineResponse,
            List<TmVehicleInfoDto> vehicles,
            List<TmSetDispatchTruthCustResDto> orders,
            Map<String, TmPlanEtaStepReqDto> splitOrderMap) {

        // DB 데이터로 Map 생성 ( 데이터 매핑용 )
        Map<String, TmVehicleInfoDto> vehicleMap = vehicles.stream()
                .collect(Collectors.toMap(
                        TmVehicleInfoDto::getCarno,
                        v -> v,
                        (oldValue, newValue) -> newValue
                ));

        return createResponseDto(request.getDccode(), request.getDeliveryDate(), request.getTmDeliveryType(),
                engineResponse, vehicleMap, orders, null, null);
    }

    public TmSetDispatchResDto createSuccessResponse(
            TmPlanEtaOptimizeAutoReqDto request,
            TmEngineResponseDto engineResponse,
            Map<String, TmVehicleInfoDto> vehicleMap,
            Map<String, TmSetDispatchTruthCustResDto> orderMap,
            Map<String, TmPlanEtaStepReqDto> splitOrderMap) {

        return createResponseDto(request.getDccode(), request.getDeliveryDate(), request.getTmDeliveryType(),
                engineResponse, vehicleMap, orderMap, null, null);
    }

    /**
     * @description : 실패 응답 response 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.13 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
     */
    public TmSetDispatchResDto createFailureResponse(String message) {
        return TmSetDispatchResDto.builder()
                .status("FAILED")
                .message(message)
                .build();
    }

    
    /**
     * @description : 엔진 Response 기준 응답 생성 Method 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.13 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
     */
    private TmSetDispatchResDto createResponseDto(
            String dccode, 
            String deliveryDate, 
            String deliveryType,
            TmEngineResponseDto engineResponse,
            Map<String, TmVehicleInfoDto> vehicleMap,
            List<TmSetDispatchTruthCustResDto> orders,
            List<TmSetDispatchReturnOrderResDto> returnOrders,
            Map<String, TmPlanEtaStepReqDto> splitOrderMap) {

        Map<String, TmSetDispatchTruthCustResDto> orderMap = orders.stream()
                .collect(Collectors.toMap(
                        TmSetDispatchTruthCustResDto::getTruthCustKey,
                        o -> o,
                        (oldValue, newValue) -> newValue
                ));

        return createResponseDto(dccode, deliveryDate, deliveryType, engineResponse, vehicleMap, orderMap, returnOrders, splitOrderMap);
    }

    private TmSetDispatchResDto createResponseDto(
            String dccode,
            String deliveryDate,
            String deliveryType,
            TmEngineResponseDto engineResponse,
            Map<String, TmVehicleInfoDto> vehicleMap,
            Map<String, TmSetDispatchTruthCustResDto> orderMap,
            List<TmSetDispatchReturnOrderResDto> returnOrders,
            Map<String, TmPlanEtaStepReqDto> splitOrderMap) {
        try {

    		// 엔진 Response에 Map 데이터 기준 값 할당
            List<TmVehicleDispatchInfoDto> vehicleDispatchInfoList = engineResponse.getRoutes().stream()
                    .map(route -> processEngineRoute(route, vehicleMap, orderMap, splitOrderMap, engineResponse.isOnMaxWeight()))
                    .filter(Objects::nonNull)
                    .toList();

            List<TmSetDispatchUnassignedOrderResDto> unassignedOrders = processUnassignedOrders(engineResponse.getUnassigned(), orderMap, splitOrderMap);

            // 다회전, Pickup 병합 등 후처리
            List<TmVehicleDispatchInfoDto> finalVehicleList = postProcessRoutes(vehicleDispatchInfoList);
            
            // 각 데이터 unique id 할당
            finalVehicleList.forEach(vehicle -> {
            	vehicle.createUniqueId();
            	vehicle.getSteps().forEach(TmEngineStepDto::createUniqueId);
        	});

            if (returnOrders != null)
                returnOrders.forEach(TmSetDispatchReturnOrderResDto::createUniqueId);
            

            String resultMessage = "배차가 성공적으로 완료되었습니다.";
            if (finalVehicleList.isEmpty()) {
                resultMessage = "설정된 조건으로 인해 배차가 되지 않아 미배차 처리되었습니다.";
            }

            return TmSetDispatchResDto.builder()
                    .dccode(dccode)
                    .deliveryDate(deliveryDate)
                    .deliveryType(deliveryType)
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

    private List<TmVehicleDispatchInfoDto> postProcessRoutes(List<TmVehicleDispatchInfoDto> routes) {
        // 1. 동일 위치 Pickup 병합
        routes.forEach(route -> {
        	// 현재 연속된 pickup을 merge 처리하는데, 반품도 고려해야함
            List<TmEngineStepDto> mergedSteps = mergePickupSteps(route.getSteps());
            route.setSteps(mergedSteps);
        });

        // 2. 다회전 차량 분할
        return routes.stream()
                .flatMap(this::splitRouteByRounds)
                .toList();
    }

    /**
     * @description : 미할당 거래처 데이터 매핑 Method 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.13 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
     */
    private List<TmSetDispatchUnassignedOrderResDto> processUnassignedOrders(
                                                List<TmSetDispatchUnassignedOrderResDto> unassigned,
                                                Map<String, TmSetDispatchTruthCustResDto> orderMap,
                                                Map<String, TmPlanEtaStepReqDto> splitOrderMap) {
    	// 배송일 경우에도 엔진에서 pickup을 미할당주문으로 response하기 때문에 필터링 처리, 반품 고려 필요?
        // 반품건 엔진 재계산후, 미배차로 떨어지는 경우 description 에 2개 쌍으로 들어 옴
        // 543671144-pickup-stop , 543671144-delivery-stop
        return unassigned.stream()
                .filter(order -> orderMap.containsKey(order.getId()))
                .filter(order -> isFilter(order, orderMap.get(order.getId())))
                .map(order -> {
                    enrichUnassignedOrder(order, orderMap, splitOrderMap);
                    order.createUniqueId();
                    return order;
                })
                .toList();
    }

    // 배송주문은 delivery 인 경우 pickup type, 반품주문인 경우 delivery step 클라이언트 보내지 않음
    private boolean isFilter(TmSetDispatchUnassignedOrderResDto order, TmSetDispatchTruthCustResDto tmSetDispatchTruthCustResDto) {
        if (order.getDescription() == null) return false;
        if (TM_DELIVERY_TYPE_DELIVERY.equals(tmSetDispatchTruthCustResDto.getTmDeliveryType())) {
            return !order.getDescription().toLowerCase().contains(STEP_TYPE_PICKUP);
        }
        if (TM_DELIVERY_TYPE_RETURN.equals(tmSetDispatchTruthCustResDto.getTmDeliveryType())) {
            return !order.getDescription().toLowerCase().contains(STEP_TYPE_DELIVERY);
        }
        return false;
    }

    // 배송주문은 delivery 인 경우 pickup type, 반품주문인 경우 delivery step 클라이언트 보내지 않음
    private boolean isFilter(TmEngineStepDto step) {
        // TODO: [엔진에서 jobs 강제 처리(임시조치)][임시조치] 엔진 요청 jobs 구조로 반영 시, 아래 코드 원복 또는 보완 필요

//        if (TM_DELIVERY_TYPE_DELIVERY.equals(step.getTmDeliveryType()))
            return !STEP_TYPE_PICKUP.equals(step.getType());
//        if (TM_DELIVERY_TYPE_RETURN.equals(step.getTmDeliveryType()))
//            return !STEP_TYPE_DELIVERY.equals(step.getType());
//        return true;
    }

    private void enrichUnassignedOrder(TmSetDispatchUnassignedOrderResDto order, Map<String, TmSetDispatchTruthCustResDto> orderMap, Map<String, TmPlanEtaStepReqDto> splitOrderMap) {
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
        order.setDefCarno(truthCustInfo.getCarno());
        order.setPop(truthCustInfo.getPop());

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

    private TmVehicleDispatchInfoDto processEngineRoute(TmEngineRouteDto engineRoute,
                                                        Map<String, TmVehicleInfoDto> vehicleMap,
                                                        Map<String, TmSetDispatchTruthCustResDto> orderMap,
                                                        Map<String, TmPlanEtaStepReqDto> splitOrderMap,
                                                        boolean isOnMaxWeight) {

        TmVehicleInfoDto vehicleInfo = vehicleMap.get(engineRoute.getCarNo());
        if (vehicleInfo == null) {
            log.warn("차량 정보를 찾을 수 없습니다: {}, {}, {}", engineRoute.getCarNo(), engineRoute, vehicleMap.keySet());
            return null; // 또는 기본값 처리
        }

        if(CONTRACT_TYPE_TEMPORARY.equals(vehicleInfo.getContractType())) {

            int orderCount = (int) engineRoute.getSteps().stream().filter(step -> "delivery".equals(step.getType())).count();

            int prevDistance = 0;
            double totalWeight = 0.0;
            double totalCube = 0.0;

            for (TmEngineStepDto step : engineRoute.getSteps()) {
                enrichStepDto(step, orderMap, splitOrderMap, prevDistance);
                prevDistance = Integer.parseInt(step.getDistance());

                // 배송 유형 일 경우 step의 job 이 delivery 일때 용적량, 체적 더해줌
                if (TM_DELIVERY_TYPE_DELIVERY.equals(step.getTmDeliveryType())) {
                    if (STEP_TYPE_DELIVERY.equals(step.getType())) {
                        String weight = Optional.ofNullable(step.getWeight()).orElse("0");
                        String cube = Optional.ofNullable(step.getCube()).orElse("0");
                        totalWeight += Double.parseDouble(weight);
                        totalCube += Double.parseDouble(cube);
                    }
                } else if (TM_DELIVERY_TYPE_RETURN.equals(step.getTmDeliveryType())) {

                }

            }

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
                    .maxCbm(String.valueOf(vehicleInfo.getMaxCube()))
                    .maxLanding(String.valueOf(vehicleInfo.getMaxLanding()))
                    .build();
        }else {


            String minWeight = Optional.ofNullable(vehicleInfo.getMinWeight()).map(Object::toString).orElse("2500");
            String maxWeight = Optional.ofNullable(vehicleInfo.getMaxWeight()).map(Object::toString).orElse("5000");
            String maxCbm = Optional.ofNullable(vehicleInfo.getMaxCube()).map(Object::toString).orElse("50");
            String maxLanding = Optional.ofNullable(vehicleInfo.getMaxLanding()).map(Object::toString).orElse("10");

            if(!isOnMaxWeight){
                maxWeight = minWeight;
            }

            int orderCount = (int) engineRoute.getSteps().stream().filter(step -> "delivery".equals(step.getType())).count();

            int prevDistance = 0;
            double totalWeight = 0.0;
            double totalCube = 0.0;

            for (TmEngineStepDto step : engineRoute.getSteps()) {
                enrichStepDto(step, orderMap, splitOrderMap, prevDistance);
                prevDistance = Integer.parseInt(step.getDistance());

                // 배송 유형 일 경우 step의 job 이 delivery 일때 용적량, 체적 더해줌
                if (TM_DELIVERY_TYPE_DELIVERY.equals(step.getTmDeliveryType())) {
                    if (STEP_TYPE_DELIVERY.equals(step.getType())) {
                        String weight = Optional.ofNullable(step.getWeight()).orElse("0");
                        String cube = Optional.ofNullable(step.getCube()).orElse("0");
                        totalWeight += Double.parseDouble(weight);
                        totalCube += Double.parseDouble(cube);
                    }
                } else if (TM_DELIVERY_TYPE_RETURN.equals(step.getTmDeliveryType())) {

                }

            }

            String carCapacity = vehicleInfo.getCarCapacity();
            if (carCapacity != null) {
                carCapacity += "톤";
            }
            if (carCapacity == null && vehicleInfo.getMaxWeight() != null) {
                carCapacity = vehicleInfo.getMaxWeight() / 1000 + "톤";
            }

            return TmVehicleDispatchInfoDto.builder()
                    .carno(engineRoute.getCarNo())
                    .outGroupCd(vehicleInfo.getOutGroupCd())
                    .vehicleType(vehicleInfo.getContractTypeNm())
                    .contractType(vehicleInfo.getContractType())
                    .drivername(TmPlanCommon.maskDriverName(vehicleInfo.getDrivername()))
                    .phone1(vehicleInfo.getPhone1())
                    .carCapacity(carCapacity)
                    .maxWeight(maxWeight) // 최대 중량
                    .maxCbm(maxCbm) // 최대 체적
                    .maxLanding(maxLanding) // 최대 착지
                    .loadedWeight(Double.toString(totalWeight))
                    .loadedCbm(Double.toString(totalCube))
                    .weightUsagePercent((int) ((totalWeight * 100) / Math.max(1, Double.parseDouble(maxWeight))))
                    .cbmUsagePercent((int) ((totalCube * 100) / Math.max(1, Double.parseDouble(maxCbm))))
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
    }

    private void enrichStepDto(TmEngineStepDto step,
                               Map<String, TmSetDispatchTruthCustResDto> orderMap,
                               Map<String, TmPlanEtaStepReqDto> splitOrderMap,
                               Integer prevDistance) {
        if (step.getId() == null) return;

        String originalId = step.getId();
        TmPlanEtaStepReqDto splitData = (splitOrderMap != null) ? splitOrderMap.get(originalId) : null;

        if (splitData != null) {
            int lastIndex = originalId.lastIndexOf("-");
            if (lastIndex != -1) {
                originalId = originalId.substring(0, lastIndex);
            }
            step.setId(originalId);
            step.setSplitDeliveryYn(splitData.getSplitDeliveryYn());
            step.setSplitDeliverySeq(splitData.getSplitDeliverySeq());
            step.setWeight(splitData.getWeight());
            step.setCube(splitData.getCube());
            step.setOrderQty(splitData.getOrderQty());
            step.setSlipno(splitData.getSlipno());
            step.setSlipline(splitData.getSlipline());
            step.setSlipdt(splitData.getSlipdt());
        }

        TmSetDispatchTruthCustResDto truthCustInfo = orderMap.get(originalId);
        if (truthCustInfo == null) return;

        long arrivalTimestamp = parseArrival(step.getArrival());
        step.setArrival(String.valueOf(arrivalTimestamp));
        step.setExpectedArrivalTime(Instant.ofEpochSecond(arrivalTimestamp).atZone(SEOUL_ZONE_ID).format(HHMM_FORMATTER));

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
        step.setDefCarno(truthCustInfo.getCarno());
        step.setReturnYn(truthCustInfo.getReturnYn());
        step.setTmDeliveryType(truthCustInfo.getTmDeliveryType());
        step.setReqdlvtime1From(truthCustInfo.getReqdlvtime1From());
        step.setReqdlvtime1To(truthCustInfo.getReqdlvtime1To());
        step.setPasswordType(truthCustInfo.getPasswordType());
        step.setPasswordTypeCd(truthCustInfo.getPasswordTypeCd());
        step.setPop(truthCustInfo.getPop());

        if (splitData == null) { // 분할 데이터가 없을 때만 주문 정보로 채움
            step.setOrderQty(truthCustInfo.getOrderQty());
            step.setCube(truthCustInfo.getCube());
            step.setWeight(truthCustInfo.getWeight());
            step.setSplitDeliverySeq(truthCustInfo.getSplitDeliverySeq());
            step.setSplitDeliveryYn(truthCustInfo.getSplitDeliveryYn());
            step.setSlipline(truthCustInfo.getSlipline());
            step.setSlipno(truthCustInfo.getSlipno());
            step.setSlipdt(truthCustInfo.getSlipdt());
        }

        int stepDistance = Integer.parseInt(step.getDistance()) - prevDistance;
        step.setStepDistance(Integer.toString(stepDistance));
    }

    private long parseArrival(String arrival) {
        if (arrival == null || arrival.isEmpty()) return 0L;
        try {
            // yyyyMMddHHmmss 형식 처리
            if (arrival.length() == 14) {
                LocalDateTime dateTime = LocalDateTime.parse(arrival, DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                return dateTime.atZone(SEOUL_ZONE_ID).toEpochSecond();
            }
            // Unix timestamp (초) 형식 처리
            return Long.parseLong(arrival);
        } catch (Exception e) {
            log.warn("도착 시간 파싱 실패: {}. 0을 반환합니다.", arrival);
            return 0L;
        }
    }

    private boolean hasSpecialConditions(TmSetDispatchTruthCustResDto truthCustInfo) {
        return "Y".equals(truthCustInfo.getKidsClYn()) ||
                "Y".equals(truthCustInfo.getDlvWaitYn()) ||
                "Y".equals(truthCustInfo.getDistantYn()) ||
                "Y".equals(truthCustInfo.getLngDistantYn()) ||
                "Y".equals(truthCustInfo.getUnloadLvlYn());
    }

    private Stream<TmVehicleDispatchInfoDto> splitRouteByRounds(TmVehicleDispatchInfoDto originalVehicle) {
        List<TmEngineStepDto> originalSteps = originalVehicle.getSteps();
        if (originalSteps == null || originalSteps.isEmpty()) {
            return Stream.of(originalVehicle);
        }

        // 1. 회차 번호(roundSeq) 설정
        // start -> job-> reload -> job -> end
        // 1 : 배송getTmDeliveryType
        // 2 : 반품getTmDeliveryType
        int tempRoundSeq = 1;
        for (int i = 0; i < originalSteps.size(); i++) {
            TmEngineStepDto currentStep = originalSteps.get(i);
            // TODO: [엔진에서 jobs 강제 처리(임시조치)][임시조치] 엔진 요청 jobs 구조로 반영 시, 아래 코드 원복 또는 보완 필요

            if (i > 0 && "reload".equals(currentStep.getType()) && /*"1".equals(currentStep.getTmDeliveryType()) &&*/ !"start".equals(originalSteps.get(i - 1).getType())) {
                tempRoundSeq++;
            }
            currentStep.setRoundSeq(tempRoundSeq);
        }

        // 2. 회차별로 그룹화
        Map<Integer, List<TmEngineStepDto>> stepsByRound = originalSteps.stream()
                .collect(Collectors.groupingBy(TmEngineStepDto::getRoundSeq));

        if (stepsByRound.size() <= 1 && tempRoundSeq == 1) {
            List<TmEngineStepDto> filteredSteps = postProcess4ReturnOrder(originalVehicle.getSteps());
            originalVehicle.setSteps(filteredSteps);
            return Stream.of(originalVehicle); // 다회전이 아니면 원본 그대로 반환
        }

        // 3. 각 회차를 별도의 차량 정보로 재구성
        List<Integer> sortedRoundKeys = new ArrayList<>(stepsByRound.keySet());
        Collections.sort(sortedRoundKeys);

        List<TmVehicleDispatchInfoDto> finalVehicleList = new ArrayList<>();
        for (int i = 0; i < sortedRoundKeys.size(); i++) {
            Integer roundSeq = sortedRoundKeys.get(i);
            List<TmEngineStepDto> roundSteps = stepsByRound.get(roundSeq);

            // 3-1. start/end 스텝 추가
            addStartEndSteps(roundSteps, i, sortedRoundKeys, stepsByRound);

            // 3-2. 회차별 요약 정보 계산
            RoundSummary summary = calculateRoundSummary(roundSteps);

            List<TmEngineStepDto> filteredRoundSteps = postProcess4ReturnOrder(roundSteps);

            // 3-3. 회차별 차량 DTO 생성
            TmVehicleDispatchInfoDto roundVehicle = createRoundVehicleDto(originalVehicle, roundSeq, filteredRoundSteps, summary);
            roundVehicle.setAlreadyRounded(true);
            finalVehicleList.add(roundVehicle);
        }
        return finalVehicleList.stream();
    }

    private List<TmEngineStepDto> postProcess4ReturnOrder(List<TmEngineStepDto> steps) {
        // 반품 배송 건에 대한 후처리, 지도에 마커 및 경로 출력용
        // TODO: [엔진에서 jobs 강제 처리(임시조치)][임시조치] 엔진 요청 jobs 구조로 반영 시, 아래 코드 원복 또는 보완 필요

        List<TmEngineStepDto> filteredSteps = steps.stream().filter(this::isFilter).toList();
//        AtomicBoolean isIncludedReturnOrder = new AtomicBoolean(false);
//        AtomicReference<TmEngineStepDto> lastDelivery = new AtomicReference<>();
//        Map<String, TmEngineStepDto> pickupStep4Return = new HashMap<>();
//        filteredSteps.forEach(step -> {
//            // 반품인 경우 step type 을 delivery 로 변경. 지도에 마커 표시를 위해
//            if (TM_DELIVERY_TYPE_RETURN.equals(step.getTmDeliveryType()) &&
//                    STEP_TYPE_PICKUP.equals(step.getType())) {
//                isIncludedReturnOrder.set(true);
//                step.setType(STEP_TYPE_DELIVERY);
//                pickupStep4Return.put(step.getId(), step);
//            }
//            else if (TM_DELIVERY_TYPE_RETURN.equals(step.getTmDeliveryType()) &&
//                    STEP_TYPE_DELIVERY.equals(step.getType())) {
//                step.setType(STEP_TYPE_DELIVERY);
//                pickupStep4Return.put(step.getId(), step);
//            }
//            if (STEP_TYPE_DELIVERY.equals(step.getType())) lastDelivery.set(step);
//            if (isIncludedReturnOrder.get() && "end".equals(step.getType())) {
//                step.setLocation(lastDelivery.get().getLocation());
//                step.setGeometry(encode(lastDelivery.get().getLocation()));
//            }
//        });
        return filteredSteps;
    }
    
    /**
     * @description : 배차옵션 값 매핑 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.25 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
     */
    public TmDispatchOptionsReqDto dispatchOption(List<TmPlanOptionEntity> entityList) {
    	TmDispatchOptionsReqDto dto = new TmDispatchOptionsReqDto();
    	
//    	for(TmPlanOptionEntity entity : entityList) {
//	    	if(entity.getPlanOptionCd().equals("IS_MULTI_TURN")) {
//	    		dto.setUseRounds(entity.getSetValue().equals("1"));
//	    		if("N".equals(entity.getUseYn())) {
//	    			dto.setUseRounds(false);
//	    		}
//	    	}else if(entity.getPlanOptionCd().equals("MAX_CBM")) {
//	    		dto.setMaxCbm(entity.getSetValue());
//	    		if("N".equals(entity.getUseYn())) {
//	    			dto.setMaxCbm("0");
//	    		}
//	    	}else if(entity.getPlanOptionCd().equals("MAX_POP_COUNT")) {
//	    		dto.setPopCount(entity.getSetValue());
//	    		if("N".equals(entity.getUseYn())) {
//	    			dto.setPopCount("0");
//	    		}
//	    	}else if(entity.getPlanOptionCd().equals("MAX_TRUTH_CUST_COUNT")) {
//	    		dto.setUseMaxLocation(entity.getSetValue().equals("1"));
//	    		if("N".equals(entity.getUseYn())) {
//	    			dto.setUseMaxLocation(false);
//	    		}
//	    	}else if(entity.getPlanOptionCd().equals("MAX_WEIGHT")) {
//	    		dto.setUseMaxWeight(entity.getSetValue().equals("1"));
//	    		if("N".equals(entity.getUseYn())) {
//	    			dto.setUseMaxWeight(false);
//	    		}
//	    	}else if(entity.getPlanOptionCd().equals("SPECIAL_CONDITIONS")) {
//	    		dto.setUseSkills(entity.getSetValue().equals("1"));
//	    		if("N".equals(entity.getUseYn())) {
//	    			dto.setUseSkills(false);
//	    		}
//	    	}
//    	}
    		
    	return dto;
    }

    private void addStartEndSteps(List<TmEngineStepDto> roundSteps, int currentIndex, List<Integer> sortedKeys, Map<Integer, List<TmEngineStepDto>> stepsByRound) {
        if (roundSteps.isEmpty()) return;

        List<Double> pickupLocation = roundSteps.stream()
                .filter(s -> "reload".equals(s.getType()))
                .map(TmEngineStepDto::getLocation)
                .findFirst()
                .orElse(null);

        if (pickupLocation == null) return; // 픽업 위치가 없으면 start/end 추가 불가

        // start 스텝 추가
        if (roundSteps.stream().noneMatch(s -> "start".equals(s.getType()))) {
            TmEngineStepDto startStep = new TmEngineStepDto();
            startStep.setType("start");
            startStep.setLocation(pickupLocation);
            startStep.setArrival(roundSteps.get(0).getArrival());
            startStep.setRoundSeq(roundSteps.get(0).getRoundSeq());
            roundSteps.add(0, startStep);
        }

        // end 스텝 추가
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
            endStep.setLocation(pickupLocation);
            endStep.setArrival(endStepArrival);
            endStep.setRoundSeq(roundSteps.get(0).getRoundSeq());
            roundSteps.add(endStep);
        }
    }

    private RoundSummary calculateRoundSummary(List<TmEngineStepDto> roundSteps) {
        RoundSummary summary = new RoundSummary();
        long minArrival = Long.MAX_VALUE;
        long maxArrival = 0L;

        for (TmEngineStepDto step : roundSteps) {
        	if (step.getWeight() != null && step.getWeight().matches("-?\\d+(\\.\\d+)?")) {
                summary.loadedWeight += Math.round(Double.parseDouble(step.getWeight()));
            }
        	if (step.getCube() != null && step.getCube().matches("-?\\d+(\\.\\d+)?")) {
                summary.loadedCbm += (int) Math.round(Double.parseDouble(step.getCube()));
            }
            if ("delivery".equals(step.getType())) {
                summary.orderCount++;
            }
            if (step.getStepDistance() != null && !step.getStepDistance().isEmpty()) {
                summary.roundDistanceMeters += Integer.parseInt(step.getStepDistance());
            }
            if (step.getArrival() != null && !step.getArrival().isEmpty()) {
                long arrival = Long.parseLong(step.getArrival());
                minArrival = Math.min(arrival, minArrival);
                maxArrival = Math.max(arrival, maxArrival);
            }
        }
        summary.totalDurationSeconds = (int) (maxArrival - minArrival);
        return summary;
    }

    private TmVehicleDispatchInfoDto createRoundVehicleDto(TmVehicleDispatchInfoDto original, int roundSeq, List<TmEngineStepDto> steps, RoundSummary summary) {
        int totalTimeMinutes = summary.totalDurationSeconds / 60;
        double roundDistanceKm = summary.roundDistanceMeters / 1000.0;
        int weightUsagePercent = (int) (summary.loadedWeight / Double.parseDouble(original.getMaxWeight()));
        int cbmUsagePercent = (int) (summary.loadedCbm / Double.parseDouble(original.getMaxCbm()));
        
        return TmVehicleDispatchInfoDto.builder()
                .carno(original.getCarno())
                .outGroupCd(original.getOutGroupCd())
                .vehicleName(original.getVehicleName())
                .drivername(TmPlanCommon.maskDriverName(original.getDrivername()))
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
                .loadedWeight(summary.loadedWeight.toString())
                .loadedCbm(summary.loadedCbm.toString())
                .orderCount(summary.orderCount)
                .weightUsagePercent(weightUsagePercent)
                .cbmUsagePercent(cbmUsagePercent)
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

    private static class RoundSummary {
        Double loadedWeight = 0.0;
        Double loadedCbm = 0.0;
        Integer orderCount = 0;
        Integer roundDistanceMeters = 0;
        Integer totalDurationSeconds = 0;
    }

    private List<TmEngineStepDto> mergePickupSteps(List<TmEngineStepDto> steps) {
        if (steps == null || steps.isEmpty()) {
            return new ArrayList<>();
        }

        List<TmEngineStepDto> mergedSteps = new ArrayList<>();
        int i = 0;
        while (i < steps.size()) {
            TmEngineStepDto currentStep = steps.get(i);

            if (isMergeablePickup(currentStep)) {
                List<TmEngineStepDto> stepsToMerge = findConsecutivePickups(steps, i);
                if (stepsToMerge.size() > 1) {
                    try {
                        // 첫 번째 스텝을 기준으로 병합된 스텝 생성 (내용은 이미 동일하다고 가정)
                        TmEngineStepDto mergedStep = objectMapper.readValue(objectMapper.writeValueAsString(stepsToMerge.get(0)), TmEngineStepDto.class);
                        mergedSteps.add(mergedStep);
                        i += stepsToMerge.size();
                        continue;
                    } catch (Exception e) {
                        log.warn("스텝 병합 중 오류 발생. 원본 스텝을 그대로 사용합니다.", e);
                        mergedSteps.addAll(stepsToMerge);
                        i += stepsToMerge.size();
                        continue;
                    }
                }
            }
            mergedSteps.add(currentStep);
            i++;
        }
        return mergedSteps;
    }

    private boolean isMergeablePickup(TmEngineStepDto step) {
        // TODO: [엔진에서 jobs 강제 처리(임시조치)][임시조치] 엔진 요청 jobs 구조로 반영 시, 아래 코드 원복 또는 보완 필요

        return (/*TM_DELIVERY_TYPE_DELIVERY.equals(step.getTmDeliveryType()) &&*/
                    STEP_TYPE_PICKUP.equals(step.getType()) &&
                    step.getDescription() != null &&
                    step.getDescription().contains(STEP_TYPE_PICKUP))
//               || (TM_DELIVERY_TYPE_RETURN.equals(step.getTmDeliveryType()) &&
//                    STEP_TYPE_DELIVERY.equals(step.getType()) &&
//                    step.getDescription() != null &&
//                    step.getDescription().contains(STEP_TYPE_DELIVERY))
                ;
    }

    private List<TmEngineStepDto> findConsecutivePickups(List<TmEngineStepDto> steps, int startIndex) {
        List<TmEngineStepDto> consecutivePickups = new ArrayList<>();
        TmEngineStepDto startStep = steps.get(startIndex);
        consecutivePickups.add(startStep);

        for (int i = startIndex + 1; i < steps.size(); i++) {
            TmEngineStepDto nextStep = steps.get(i);
            if (isMergeablePickup(nextStep) && Objects.equals(startStep.getLocation(), nextStep.getLocation())) {
                consecutivePickups.add(nextStep);
            } else {
                break;
            }
        }
        return consecutivePickups;
    }

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

    private int toInteger(Double value, int defaultValue) {
        double result = Optional.ofNullable(value).orElse((double) defaultValue) * AMOUNT_CONVERSION_FACTOR;
        return Math.max((int) Math.round(result), 1);
    }

    private int toInteger(String s, int defaultValue, int factor) {
        if (s == null || s.isBlank()) {
            return Math.max(defaultValue * factor, 1);
        }
        try {
            double value = Double.parseDouble(s);
            int result = (int) Math.round(value * factor);
            return Math.max(result, 1);
        } catch (NumberFormatException e) {
            log.warn("숫자 변환 실패: '{}'. 기본값 {}을 사용합니다.", s, defaultValue);
            return Math.max(defaultValue * factor, 1);
        }
    }

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
     * Polyline  인코딩 처리
     * @param obj
     * @return
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
                    coords.add((double[])o);
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

    public static List<double[]> decode(String encodedPolyline) {
        if (encodedPolyline == null || encodedPolyline.isEmpty()) {
            return Collections.emptyList();
        }
        List<double[]> poly = new ArrayList<>();
        int index = 0, len = encodedPolyline.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
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

            poly.add(new double[]{(double) lng / 1E5, (double) lat / 1E5});
        }
        return poly;
    }

    public String toWktLineString(List<double[]> polylineList) {
        if (polylineList == null || polylineList.isEmpty()) {
            return null;
        }
        return polylineList.stream()
                .map(p -> String.format("%.8f %.8f", p[0], p[1]))
                .collect(Collectors.joining(", ", "LINESTRING (", ")"));
    }
    
    public String toWktPoint(List<Double> point) {
        if (point == null || point.size() < 2) {
            return null;
        }
        // POINT (경도 위도)
        return String.format("POINT (%.8f %.8f)", point.get(0), point.get(1));
    }

    /**
     * 주문 정보를 Engine Job DTO로 변환합니다. (2026.01.11 추가)
     */
    public TmEngineJobDto toEngineJob(TmSetDispatchTruthCustResDto order,
                                      TmDispatchCenterInfoResDto centerInfo,
                                      Map<String, String> stepVehicleMap,
                                      TmPlanOption planOption,
                                      String deliveryDate) {
        if (order == null) {
            log.warn("주문 정보가 null입니다.");
            return null;
        }

        // 미배차로 처리?
        if(order.getLatitude() == null || order.getLongitude() == null) {
            return null;
        }

        try {

            // DEFAULT VALUE는 DB 데이터 미존재로 임의 설정 추후 제거 필요
            List<Double> centerLocation = Arrays.asList(
                    parseCoordinate(centerInfo.getLongitude()).orElse(DEFAULT_LONGITUDE),
                    parseCoordinate(centerInfo.getLatitude()).orElse(DEFAULT_LATITUDE)
            );
            List<Double> custLocation = Arrays.asList(
                    parseCoordinate(order.getLongitude()).orElse(DEFAULT_LONGITUDE),
                    parseCoordinate(order.getLatitude()).orElse(DEFAULT_LATITUDE)
            );

            // 특수조건/출입KEY 스킬 설정 (담당차량 기반)
            List<String> skillsForSpecialCondition = order.getSkillsForSpecialCondition(planOption.isOnSkills());

            boolean isPickupType = "2".equals(order.getTmDeliveryType());

            TmEngineJobDto engineJobDto = TmEngineJobDto.builder()
                    .id(Optional.ofNullable(order.getTruthCustKey()).orElse("UNKNOWN"))
                    .location(custLocation)
                    .delivery(order.getAmount(planOption))
                    .priority(DEFAULT_PRIORITY)
                    .blocked_vehicle_ids(order.getBlockedCars())
                    .skills(skillsForSpecialCondition)
                    .otd_time(order.getOtdTime(deliveryDate))
                    .build();

            if (!ObjectUtils.isEmpty(order.getCarno())) {
                engineJobDto.setPreferred_skills(Collections.singletonList(order.getCarno())) ;
            }
            return engineJobDto;
        } catch (Exception e) {
            log.error("주문 정보를 Engine Job로 변환 중 오류 발생 - 주문: {}, 오류: {}", order.getTruthCustKey(), e.getMessage(), e);
            return null;
        }
    }

    private TmEngineJobDto createEngineJobFromPlan(
            TmPlanEtaStepReqDto step,
            String carno,
            TmDispatchCenterInfoResDto centerInfo,
            TmSetDispatchTruthCustResDto orderInfo,
            TmPlanOption planOption,
            String deilveryDate) {
        if (step.getId() == null || orderInfo == null) return null;
        if (/*("2".equals(step.getTmDeliveryType()) && "delivery".equals(step.getType())) || */
                ("1".equals(step.getTmDeliveryType()) && "reload".equals(step.getType()))) {
            return null;
        }

        String requestId = step.getId();
        if ("Y".equals(step.getSplitDeliveryYn())) {
            int splitSeq = Integer.parseInt(step.getSplitDeliverySeq()) + 1 ;
            requestId = step.getId() + "-" + splitSeq;
        }

        if (TM_DELIVERY_TYPE_RETURN.equals(orderInfo.getTmDeliveryType())) {
            return createEngineJob4Return(requestId, step, carno, centerInfo, orderInfo, planOption, deilveryDate);
        } else {
            return createEngineJob4Delivery(requestId, step, carno, centerInfo, orderInfo, planOption, deilveryDate);
        }

    }

    private TmEngineJobDto createEngineJob4Return(String requestId,
                                                  TmPlanEtaStepReqDto step,
                                                  String carno,
                                                  TmDispatchCenterInfoResDto centerInfo,
                                                  TmSetDispatchTruthCustResDto orderInfo,
                                                  TmPlanOption planOption,
                                                  String deilveryDate) {

        List<Double> location = Arrays.asList(
                parseCoordinate(orderInfo.getLongitude()).orElse(DEFAULT_LONGITUDE),
                parseCoordinate(orderInfo.getLatitude()).orElse(DEFAULT_LATITUDE)
        );

        //  반품의 경우 중량, 체적 = 0 설정해서 전송. 중량으로 반품 건 배차 실패되지 않아야
        List<Integer> amount = step.getAmount(planOption);


        return TmEngineJobDto.builder()
                .id(requestId)
                .location(location)
                .delivery(amount)
                .service(DEFAULT_DELIVERY_DURATION_SECONDS)
                .priority(DEFAULT_PRIORITY)
                .otd_time(orderInfo.getOtdTime(deilveryDate))
                .build();
    }

    private TmEngineJobDto createEngineJob4Delivery(String requestId,
                                                    TmPlanEtaStepReqDto step,
                                                    String carno,
                                                    TmDispatchCenterInfoResDto centerInfo,
                                                    TmSetDispatchTruthCustResDto orderInfo,
                                                    TmPlanOption planOption,
                                                    String deilveryDate) {
        List<Integer> amount = step.getAmount(planOption);

        // 특수조건/출입KEY 스킬 설정 (담당차량 기반)
        List<String> skillsForSpecialCondition = orderInfo.getSkillsForSpecialCondition(planOption.isOnSkills());

        TmEngineJobDto engineJobDto = TmEngineJobDto.builder()
                .id(requestId)
                .location(step.getLocation())
                .delivery(amount)
                .service(DEFAULT_DELIVERY_DURATION_SECONDS)
                .skills(skillsForSpecialCondition)
                .blocked_vehicle_ids(orderInfo.getBlockedCars())
                .priority(DEFAULT_PRIORITY)
                .otd_time(orderInfo.getOtdTime(deilveryDate))
                .build();

        if (!ObjectUtils.isEmpty(orderInfo.getCarno())) {
            engineJobDto.setPreferred_skills(Collections.singletonList(orderInfo.getCarno())) ;
        }
        return engineJobDto;
    }
}
