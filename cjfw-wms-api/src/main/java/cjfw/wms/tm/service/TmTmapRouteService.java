package cjfw.wms.tm.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.utility.ContextUtil;
import cjfw.wms.tm.dto.TmInplanUpdateDto;
import cjfw.wms.tm.dto.TmapDispatchRouteReqDto;
import cjfw.wms.tm.dto.TmapDispatchRouteResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : han@wemeetmobility.com
 * @date : 2025.11.28
 * @description : TM 배차 T-map API 호출 서비스
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.28 han@wemeetmobility.com 생성
 * </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmTmapRouteService {

    private static final String SERVICEID_PREFIX = "tmTmapRouteMapper.";

    private final CommonDao commonDao;

    // T-map API 상수
    private static final String TMAP_URL = ContextUtil.getProperty("spring.tmap.url");
    private static final String TMAP_APPKEY = ContextUtil.getProperty("spring.tmap.appkey");
    private static final String TMAP_ROUTE_PATH = "/routes/routeSequential";

    // T-map routeSequential API 경유지 제한
    private static final int TMAP_ROUTE_LIMIT_SMALL = 30;   // routeSequential30
    private static final int TMAP_ROUTE_LIMIT_MEDIUM = 100; // routeSequential100
    private static final int TMAP_ROUTE_LIMIT_LARGE = 200;  // routeSequential200

    /**
     * 차량별 T-map 경로 계산 (전체 프로세스)
     *
     * @param updateList TM_INPLAN 업데이트 DTO 리스트
     * @param dccode 물류센터 코드
     * @param deliveryDate 배송일자 (YYYYMMDD)
     * @return 차량별 T-map API 응답 (carno -> TmapDispatchRouteResDto)
     */
    public Map<String, TmapDispatchRouteResDto> getRoutesForVehicles(
        List<TmInplanUpdateDto> updateList,
        String dccode,
        String deliveryDate
    ) {
    	
    	 System.out.println("그룹핑 전 개수" + updateList.size());
        // 1. 차량별 그룹화 (우선순위 정렬 후 그룹화)
        Map<String, List<TmInplanUpdateDto>> groupedByCarno = updateList.stream()
            .filter(item -> item.getCarno() != null && !item.getCarno().trim().isEmpty())
            .sorted(Comparator.comparingInt(item ->
                item.getPriority() != null ? item.getPriority() : Integer.MAX_VALUE
            ))
            .collect(Collectors.groupingBy(TmInplanUpdateDto::getCarno));
        
        
        log.info("차량별 그룹화 완료 - 차량: {}대", groupedByCarno.size());

        // 2. DC 좌표 조회
        Map<String, String> dcCoordinate = fetchDcCoordinate(dccode);
        log.info("DC 좌표 조회 완료 - dccode: {}", dccode);

        // 3. 실착지 좌표 조회
        Map<String, Map<String, String>> customerCoordinates = fetchCustomerCoordinates(updateList);
        log.info("고객 좌표 조회 완료 - 고객: {}건", customerCoordinates.size());

        Map<String, TmapDispatchRouteResDto> routeResultsByCarno =null;
        try {
			
		
        // 4. 차량별 T-map API 호출 및 결과 수집
        	
        	routeResultsByCarno = groupedByCarno.entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> 
                    getRoutes(
                    entry.getValue(),    // routes
                    dcCoordinate,
                    customerCoordinates,
                    deliveryDate)
            ));
        
        } catch (NullPointerException e) {
			// TODO: handle exception
		}

        log.info("T-map API 경로 계산 완료 - 차량: {}대", routeResultsByCarno.size());

        return routeResultsByCarno;
    }

    /**
     * T-map API 경로 계산 (단일 차량)
     *
     * @param routes 경로 리스트 (우선순위 정렬됨)
     * @param dcCoordinate DC 좌표 {latitude, longitude}
     * @param customerCoordinates 고객 좌표 맵 (truthCustKey -> {latitude, longitude})
     * @param deliveryDate 배송일자 (YYYYMMDD)
     * @return T-map API 응답 DTO
     */
    public TmapDispatchRouteResDto getRoutes(
        List<TmInplanUpdateDto> routes,
        Map<String, String> dcCoordinate,
        Map<String, Map<String, String>> customerCoordinates,
        String deliveryDate
    ) {
    	
    	// 1. T-map 요청 DTO 생성
    	
    	if(100<routes.size()) {
    		 routes = routes.subList(0,98);
    		
    	}
        TmapDispatchRouteReqDto request = buildTmapRequest(
            routes, dcCoordinate, customerCoordinates, deliveryDate
        );

        // 2. 경유지 개수 검증 및 API 경로 결정
        int viaPointCount = request.getViaPoints() != null ? request.getViaPoints().size() : 0;
        String tmapJobCount;
		
		System.out.println(  "viaPointCount"+viaPointCount +"________"   );
		 
		 
        if (viaPointCount <= TMAP_ROUTE_LIMIT_SMALL) {
            tmapJobCount = String.valueOf(TMAP_ROUTE_LIMIT_SMALL);
        } else if (viaPointCount <= TMAP_ROUTE_LIMIT_MEDIUM) {
            tmapJobCount = String.valueOf(TMAP_ROUTE_LIMIT_MEDIUM);
        } else if (viaPointCount <= TMAP_ROUTE_LIMIT_LARGE) {
            tmapJobCount = String.valueOf(TMAP_ROUTE_LIMIT_LARGE);
        } else {
            throw new IllegalArgumentException(
                String.format("경유지 개수가 제한을 초과했습니다: %d개 (최대 %d개)",
                    viaPointCount, TMAP_ROUTE_LIMIT_LARGE)
            );
        }

        // 3. T-map API 호출
        String url = TMAP_URL + TMAP_ROUTE_PATH + tmapJobCount + "?version=1";

        log.info("T-map routeSequential API 호출: URL={}, 경유지={}개", url, viaPointCount);

        // HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("appKey", TMAP_APPKEY);

        HttpEntity<TmapDispatchRouteReqDto> entity = new HttpEntity<>(request, headers);
        RestTemplate restTemplate = new RestTemplate();

        // ⚠️ DEBUG: T-map 요청 데이터 로깅
        log.info("=== T-map 요청 데이터 ===");
        log.info("URL: {}", url);
        log.info("startName: {}, startX: {}, startY: {}, startTime: {}",
            request.getStartName(), request.getStartX(), request.getStartY(), request.getStartTime());
        log.info("endName: {}, endX: {}, endY: {}",
            request.getEndName(), request.getEndX(), request.getEndY());
        log.info("viaPoints count: {}", request.getViaPoints() != null ? request.getViaPoints().size() : 0);
        if (request.getViaPoints() != null && !request.getViaPoints().isEmpty()) {
            log.info("첫 번째 viaPoint: id={}, name={}, x={}, y={}, priority={}",
                request.getViaPoints().get(0).getViaPointId(),
                request.getViaPoints().get(0).getViaPointName(),
                request.getViaPoints().get(0).getViaX(),
                request.getViaPoints().get(0).getViaY(),
                request.getViaPoints().get(0).getPriority());
        }

        // API 호출
        ResponseEntity<TmapDispatchRouteResDto> response = null;
        
	        response = restTemplate.exchange(
	            url,
	            HttpMethod.POST,
	            entity,
	            TmapDispatchRouteResDto.class
	         );
	        
	        // 응답 검증
	        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
	            throw new RuntimeException("T-map API 호출 실패: " + response.getStatusCode());
        }
      
        log.info("T-map API 호출 성공 - 경유지: {}개, 총거리: {}m, 총시간: {}초",
            viaPointCount,
            response.getBody().getTotalDistance(),
            response.getBody().getTotalTime());

        return response.getBody();
    }

    /**
     * T-map API 요청 DTO 생성
     *
     * @param routes 경로 리스트 (우선순위 정렬됨)
     * @param dcCoordinate DC 좌표
     * @param customerCoordinates 고객 좌표 맵
     * @param deliveryDate 배송일자 (YYYYMMDD)
     * @return T-map API 요청 DTO
     */
    private TmapDispatchRouteReqDto buildTmapRequest(
        List<TmInplanUpdateDto> routes,
        Map<String, String> dcCoordinate,
        Map<String, Map<String, String>> customerCoordinates,
        String deliveryDate
    ) {
      // 출발 시간 생성 (배송일자 08:00 시작)
        String startTime = deliveryDate + "0800";  // YYYYMMDDHHMM

        // 경유지 리스트 생성
        List<TmapDispatchRouteReqDto.ViaPoint> viaPoints = routes.stream()
            .map(route -> {
                String truthCustKey = route.getTruthCustKey();
                Map<String, String> coordinate = customerCoordinates.get(truthCustKey);
                
                if (coordinate == null) {
                    //throw new IllegalStateException(
                      //  "고객 좌표를 찾을 수 없습니다. truthCustKey: " + truthCustKey
                    //);
                	
                	System.out.println("고객 좌표를 찾을 수 없습니다. truthCustKey: " + truthCustKey);
                	 return TmapDispatchRouteReqDto.ViaPoint.builder()
                             .viaPointId("569051000")
                             .viaPointName("569051000")  // 실착지명이 없으면 코드 사용
                             .viaX("126.820134")  // X = 경도 (Oracle alias 대문자)
                             .viaY("34.432289")   // Y = 위도 (Oracle alias 대문자)
                             .priority(1)
                             .viaTime("600")  // 기본 10분
                             .build();
                
                }

                return TmapDispatchRouteReqDto.ViaPoint.builder()
                    .viaPointId(truthCustKey)
                    .viaPointName(truthCustKey)  // 실착지명이 없으면 코드 사용
                    .viaX(coordinate.get("LONGITUDE"))  // X = 경도 (Oracle alias 대문자)
                    .viaY(coordinate.get("LATITUDE"))   // Y = 위도 (Oracle alias 대문자)
                    .priority(route.getPriority())
                    .viaTime("600")  // 기본 10분
                    .build();
            })
            .toList();

        // T-map 요청 DTO 생성
        return TmapDispatchRouteReqDto.builder()
            .startName("DC")
            .startX(dcCoordinate.get("LONGITUDE"))  // Oracle alias 대문자
            .startY(dcCoordinate.get("LATITUDE"))   // Oracle alias 대문자
            .startTime(startTime)
            .endName("DC")
            .endX(dcCoordinate.get("LONGITUDE"))    // Oracle alias 대문자
            .endY(dcCoordinate.get("LATITUDE"))     // Oracle alias 대문자
            .viaPoints(viaPoints)
            .build();
    }

    /**
     * DC 좌표 조회
     *
     * @param dccode 물류센터 코드
     * @return DC 좌표 {latitude, longitude}
     */
    private Map<String, String> fetchDcCoordinate(String dccode) {
        Map<String, Object> params = new HashMap<>();
        params.put("dccode", dccode);

        @SuppressWarnings("unchecked")
        Map<String, String> result = (Map<String, String>) (Map<?, ?>) commonDao.selectOne(
            SERVICEID_PREFIX + "selectDcCoordinate",
            params
        );

        if (result == null) {
            throw new IllegalStateException("DC 좌표를 찾을 수 없습니다. dccode: " + dccode);
        }

        return result;
    }

    /**
     * 고객 좌표 조회
     *
     * @param updateList TM_INPLAN 업데이트 DTO 리스트
     * @return 좌표 정보 (truthCustKey -> {latitude, longitude})
     */
    private Map<String, Map<String, String>> fetchCustomerCoordinates(List<TmInplanUpdateDto> updateList) {
        // 1. 실착지코드 리스트 추출 (중복 제거)
        List<String> truthCustKeyList = updateList.stream()
            .map(TmInplanUpdateDto::getTruthCustKey)
            .filter(key -> key != null && !key.trim().isEmpty())
            .distinct()
            .toList();

        if (truthCustKeyList.isEmpty()) {
            return new HashMap<>();
        }

        // 2. DB 조회
        Map<String, Object> params = new HashMap<>();
        params.put("truthCustKeyList", truthCustKeyList);

        @SuppressWarnings("unchecked")
        List<Map<String, String>> resultList = (List<Map<String, String>>) (List<?>) commonDao.selectList(
            SERVICEID_PREFIX + "selectCustomerCoordinates",
            params
        );

        // 3. truthCustKey를 키로 하는 Map으로 변환
        // Oracle은 alias를 대문자로 변환하므로 TRUTHCUSTKEY, LATITUDE, LONGITUDE로 읽어야 함
        return resultList.stream()
            .collect(Collectors.toMap(
                row -> row.get("TRUTHCUSTKEY"),
                row -> {
                    Map<String, String> coordinate = new HashMap<>();
                    coordinate.put("LATITUDE", row.get("LATITUDE"));      // 대문자로 통일
                    coordinate.put("LONGITUDE", row.get("LONGITUDE"));    // 대문자로 통일
                    return coordinate;
                }
            ));
    }
}
