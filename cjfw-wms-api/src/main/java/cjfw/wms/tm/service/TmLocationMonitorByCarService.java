package cjfw.wms.tm.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cjfw.auth.jwt.JwtAuthenticationFilter;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ContextUtil;
import cjfw.wms.tm.dto.TmActualRoutePointDto;
import cjfw.wms.tm.dto.TmActualRouteReqDto;
import cjfw.wms.tm.dto.TmActualRouteResDto;
import cjfw.wms.tm.dto.TmCarTrackQryLogDto;
import cjfw.wms.tm.dto.TmCustomerLocationReqDto;
import cjfw.wms.tm.dto.TmCustomerLocationResDto;
import cjfw.wms.tm.dto.TmCustomerManagementReqDto;
import cjfw.wms.tm.dto.TmCustomerManagementResDto;
import cjfw.wms.tm.dto.TmPlanRouteReqDto;
import cjfw.wms.tm.dto.TmPlanRouteResDto;
import cjfw.wms.tm.dto.TmVehicleLocationMonitoringReqDto;
import cjfw.wms.tm.dto.TmVehicleLocationReqDto;
import cjfw.wms.tm.dto.TmVehicleLocationResDto;
import cjfw.wms.tm.dto.TmVehicleMonitoringIntegratedResDto;
import cjfw.wms.tm.dto.TmVehicleStatusResDto;
import cjfw.wms.tm.dto.TmWeatherInfoReqDto;
import cjfw.wms.tm.dto.TmWeatherInfoResDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : OhEunbeom
 * @date : 2025.09.18
 * @description : 차량별 위치 모니터링 서비스
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.18 OhEunbeom      생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmLocationMonitorByCarService {

	private transient static final String SERVICEID_PREFIX = "tmLocationMonitorByCarService.";
	private transient static final String LOCATION_MONITOR_SERVICEID_PREFIX = "tmLocationMonitorService.";

	private final CommonDao commonDao;

	private final UserContext userContext;

	/**
	 * @description : 차량 모니터링 차량마커 조회 (검색조건)
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.18 OhEunbeom      생성 </pre>
	 */
	public List<TmVehicleMonitoringIntegratedResDto> getVehicleStatusList(TmVehicleLocationMonitoringReqDto reqDto) {
		// 1단계: 차량번호와 배송상태 조회
		List<TmVehicleStatusResDto> vehicleStatusList = commonDao.selectList(SERVICEID_PREFIX + "getVehicleStatusList", reqDto);
		
		if (vehicleStatusList.isEmpty()) {
			return new ArrayList<>();
		}
		
		// 5단계: 통합 결과 생성
		List<TmVehicleMonitoringIntegratedResDto> result = vehicleStatusList.stream()
			.map(status -> {
				TmVehicleMonitoringIntegratedResDto integrated = new TmVehicleMonitoringIntegratedResDto();
				integrated.setDccode(status.getDccode());
                integrated.setDeliverydt(status.getDeliverydt());
				integrated.setCarno(status.getCarno());
				integrated.setDeliveryStatus(status.getDeliveryStatus());
				integrated.setLatestPriority(status.getLatestPriority());
				integrated.setLatitude(status.getLatitude());
				integrated.setLongitude(status.getLongitude());
				
				return integrated;
			})
			.collect(Collectors.toList());
		
		return result;
	}

	/**
	 * @description : 차량 모니터링 차량마커 조회 (차량번호)
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.18 OhEunbeom      생성
	 * 2025.11.10 OhEunbeom      조회 이력 저장 추가 </pre>
	 */
	public List<TmVehicleMonitoringIntegratedResDto> getVehicleLocationIntegratedList(TmVehicleLocationReqDto reqDto) {
		// 센터코드가 null이 아닐 때만 조회 이력 저장
		if (reqDto.getDccode() != null && !reqDto.getDccode().isEmpty()) {
			try {
				saveQueryLog(reqDto);
			} catch (Exception e) {
				log.error("조회 이력 저장 중 오류 발생", e);
			}
		}
		
        List<TmVehicleMonitoringIntegratedResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getVehicleLocationIntegratedList", reqDto);
		return result;
	}
	
	/**
	 * @description : 조회 이력 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.10 OhEunbeom      생성 </pre>
	 */
	private void saveQueryLog(TmVehicleLocationReqDto reqDto) {
		try {
			HttpServletRequest request = ContextUtil.getRequest();
			String userAgent = request.getHeader("User-Agent");
			
			// Device 타입 판단
			String device = getDeviceType(userAgent);
			
			// IP 주소 가져오기
			String ipAddress = JwtAuthenticationFilter.ipHolder.get();
			if (ipAddress == null || ipAddress.isEmpty()) {
				ipAddress = request.getRemoteAddr();
			}
			
			// 차량번호 리스트가 있으면 각 차량번호마다 조회 이력 저장
			if (reqDto.getCarnoList() != null && !reqDto.getCarnoList().isEmpty()) {
				for (String carno : reqDto.getCarnoList()) {
					TmCarTrackQryLogDto logDto = TmCarTrackQryLogDto.builder()
							.dccode(reqDto.getDccode())
							.deliverydt(reqDto.getDeliverydt())
							.userId(userContext.getUserId())
							.username(userContext.getUserNm())
							.ipAddress(ipAddress)
							.device(device)
							.carno(carno)
							.build();
					
					commonDao.insert(LOCATION_MONITOR_SERVICEID_PREFIX + "insertCarTrackQryLog", logDto);
				}
			} else {
				// 차량번호가 없을 경우에도 조회 이력 저장 (차량번호는 null)
				TmCarTrackQryLogDto logDto = TmCarTrackQryLogDto.builder()
						.dccode(reqDto.getDccode())
						.deliverydt(reqDto.getDeliverydt())
						.userId(userContext.getUserId())
						.username(userContext.getUserNm())
						.ipAddress(ipAddress)
						.device(device)
						.carno(null)
						.build();
				
				commonDao.insert(LOCATION_MONITOR_SERVICEID_PREFIX + "insertCarTrackQryLog", logDto);
			}
		} catch (Exception e) {
			log.error("조회 이력 저장 중 오류 발생", e);
		}
	}
	
	/**
	 * @description : User-Agent 기반 Device 타입 판단
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.10 OhEunbeom      생성 </pre>
	 */
	private String getDeviceType(String userAgent) {
		if (userAgent == null || userAgent.isEmpty()) {
			return "Web";
		}
		
		String ua = userAgent.toLowerCase();
		
		// Tablet 판단
		if (ua.contains("tablet") || ua.contains("ipad")) {
			return "Tablet";
		}
		
		// Mobile 판단
		if (ua.contains("mobile") || ua.contains("android") || ua.contains("iphone") || 
		    ua.contains("ipod") || ua.contains("blackberry") || ua.contains("windows phone")) {
			return "Mobile";
		}
		
		// 기본값은 Web
		return "Web";
	}

	/**
	 * @description : 계획 경로 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.18 OhEunbeom      생성 </pre>
	 */
	public List<TmPlanRouteResDto> getPlanRouteList(TmPlanRouteReqDto reqDto) {
		List<TmPlanRouteResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getPlanRouteList", reqDto);
		return result;
	}

	/**
	 * @description : 실제 이동경로 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.18 OhEunbeom      생성
	 * 2025.01.XX System      GeoJSON 생성 로직을 자바 단으로 이동 </pre>
	 */
	public List<TmActualRouteResDto> getActualRouteList(TmActualRouteReqDto reqDto) {
		// 좌표점 리스트 조회
		List<TmActualRoutePointDto> pointList = commonDao.selectList(SERVICEID_PREFIX + "getActualRoutePoints", reqDto);
		
		if (pointList == null || pointList.isEmpty()) {
			return new ArrayList<>();
		}
		
		// 좌표점 리스트를 차량번호별로 그룹화
		Map<String, List<TmActualRoutePointDto>> pointMap = pointList.stream()
			.collect(Collectors.groupingBy(TmActualRoutePointDto::getCarno));
		
		// 차량번호별로 결과 생성
		List<TmActualRouteResDto> result = new ArrayList<>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		
		for (Map.Entry<String, List<TmActualRoutePointDto>> entry : pointMap.entrySet()) {
			String carno = entry.getKey();
			List<TmActualRoutePointDto> points = entry.getValue();
			
			// 시간순 정렬
			Collections.sort(points, (p1, p2) -> {
				if (p1.getLoadDate() == null && p2.getLoadDate() == null) return 0;
				if (p1.getLoadDate() == null) return 1;
				if (p2.getLoadDate() == null) return -1;
				return p1.getLoadDate().compareTo(p2.getLoadDate());
			});
			
			// 메타 정보 계산
			TmActualRouteResDto resDto = new TmActualRouteResDto();
			resDto.setCarno(carno);
			resDto.setPointCount(points.size());
			
			if (!points.isEmpty()) {
				// 시작/종료 시간 설정
				TmActualRoutePointDto firstPoint = points.get(0);
				TmActualRoutePointDto lastPoint = points.get(points.size() - 1);
				
				if (firstPoint.getLoadDate() != null) {
					resDto.setTripDate(dateFormat.format(firstPoint.getLoadDate()));
					resDto.setStartTime(timeFormat.format(firstPoint.getLoadDate()));
				}
				if (lastPoint.getLoadDate() != null) {
					resDto.setEndTime(timeFormat.format(lastPoint.getLoadDate()));
				}
				
				// GeoJSON LineString 생성
				resDto.setGeojsonLinestring(generateGeoJsonLineString(points));
			}
			
			result.add(resDto);
		}
		
		return result;
	}
	
	/**
	 * @description : 좌표점 리스트로부터 GeoJSON LineString 생성
	 * @param points 좌표점 리스트
	 * @return GeoJSON LineString 문자열
	 */
	private String generateGeoJsonLineString(List<TmActualRoutePointDto> points) {
		if (points == null || points.isEmpty()) {
			return null;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("{\"type\":\"LineString\",\"coordinates\":[");
		
		boolean first = true;
		for (TmActualRoutePointDto point : points) {
			if ( ( point.getLongitude() != null && !"".equals(point.getLongitude()) ) && ( point.getLatitude() != null && !"".equals(point.getLatitude()) ) ) {
				if (!first) {
					sb.append(",");
				}
				sb.append("[").append(point.getLongitude()).append(",").append(point.getLatitude()).append("]");
				first = false;
			}
		}
		
		sb.append("]}");
		return sb.toString();
	}

	/**
	 * @description : 실착지 위치 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.18 OhEunbeom      생성 </pre>
	 */
	public List<TmCustomerLocationResDto> getCustomerLocationList(TmCustomerLocationReqDto reqDto) {
		List<TmCustomerLocationResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getCustomerLocationList", reqDto);
		return result;
	}

	/**
	 * @description : 관리처 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.18 OhEunbeom      생성 </pre>
	 */
	public List<TmCustomerManagementResDto> getCustomerManagementList(TmCustomerManagementReqDto reqDto) {
		List<TmCustomerManagementResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getCustomerManagementList", reqDto);
		return result;
	}

	/**
	 * @description : 날씨정보 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.01.XX System Generated      생성 </pre>
	 */
	public List<TmWeatherInfoResDto> getWeatherInfoList(TmWeatherInfoReqDto reqDto) {
		List<TmWeatherInfoResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getWeatherInfoList", reqDto);
		return result;
	}

	/**
	 * @description : 차량 모니터링 차량마커 조회 (검색조건) - Carrier 버전
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.12 OhEunbeom      생성 </pre>
	 */
	public List<TmVehicleMonitoringIntegratedResDto> getVehicleStatusListByCarrier(TmVehicleLocationMonitoringReqDto reqDto) {
		// 1단계: 차량번호와 배송상태 조회
		List<TmVehicleStatusResDto> vehicleStatusList = commonDao.selectList(SERVICEID_PREFIX + "getVehicleStatusListByCarrier", reqDto);
		
		if (vehicleStatusList.isEmpty()) {
			return new ArrayList<>();
		}
		
		// 2단계: 차량번호 리스트 추출
		List<String> carnoList = vehicleStatusList.stream()
			.map(TmVehicleStatusResDto::getCarno)
			.collect(Collectors.toList());
		
		// 3단계: 차량위치 조회
		TmVehicleLocationReqDto locationReqDto = TmVehicleLocationReqDto.builder()
			.deliverydt(reqDto.getDeliverydt())
			.carnoList(carnoList)
			.build();
		
		List<TmVehicleLocationResDto> vehicleLocationList = commonDao.selectList(SERVICEID_PREFIX + "getVehicleLocationList", locationReqDto);
		
		// 4단계: 위치정보를 Map으로 변환 (차량번호 기준)
		Map<String, TmVehicleLocationResDto> locationMap = vehicleLocationList.stream()
			.collect(Collectors.toMap(TmVehicleLocationResDto::getCarno, location -> location, (existing, replacement) -> existing));
		
		// 5단계: 통합 결과 생성
		List<TmVehicleMonitoringIntegratedResDto> result = vehicleStatusList.stream()
			.map(status -> {
				TmVehicleMonitoringIntegratedResDto integrated = new TmVehicleMonitoringIntegratedResDto();
				integrated.setDccode(status.getDccode());
                integrated.setDeliverydt(status.getDeliverydt());
				integrated.setCarno(status.getCarno());
				integrated.setDeliveryStatus(status.getDeliveryStatus());
				integrated.setLatestPriority(status.getLatestPriority());
				
				// 위치정보가 있으면 설정
				TmVehicleLocationResDto location = locationMap.get(status.getCarno());
				if (location != null) {
					integrated.setLatitude(location.getLatitude());
					integrated.setLongitude(location.getLongitude());
				}
				
				return integrated;
			})
			.collect(Collectors.toList());
		
		return result;
	}

	/**
	 * @description : 차량 모니터링 차량마커 조회 (차량번호) - Carrier 버전
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.12 OhEunbeom      생성 </pre>
	 */
	public List<TmVehicleMonitoringIntegratedResDto> getVehicleLocationIntegratedListByCarrier(TmVehicleLocationReqDto reqDto) {
		// 센터코드가 null이 아닐 때만 조회 이력 저장
		if (reqDto.getDccode() != null && !reqDto.getDccode().isEmpty()) {
			try {
				saveQueryLog(reqDto);
			} catch (Exception e) {
				log.error("조회 이력 저장 중 오류 발생", e);
			}
		}
		
        List<TmVehicleMonitoringIntegratedResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getVehicleLocationIntegratedListByCarrier", reqDto);
		return result;
	}

	/**
	 * @description : 실제 이동경로 조회 - Carrier 버전
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.12 OhEunbeom      생성 </pre>
	 */
	public List<TmActualRouteResDto> getActualRouteListByCarrier(TmActualRouteReqDto reqDto) {
		// 좌표점 리스트 조회
		List<TmActualRoutePointDto> pointList = commonDao.selectList(SERVICEID_PREFIX + "getActualRoutePointsByCarrier", reqDto);
		
		if (pointList == null || pointList.isEmpty()) {
			return new ArrayList<>();
		}
		
		// 좌표점 리스트를 차량번호별로 그룹화
		Map<String, List<TmActualRoutePointDto>> pointMap = pointList.stream()
			.collect(Collectors.groupingBy(TmActualRoutePointDto::getCarno));
		
		// 차량번호별로 결과 생성
		List<TmActualRouteResDto> result = new ArrayList<>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		
		for (Map.Entry<String, List<TmActualRoutePointDto>> entry : pointMap.entrySet()) {
			String carno = entry.getKey();
			List<TmActualRoutePointDto> points = entry.getValue();
			
			// 시간순 정렬
			Collections.sort(points, (p1, p2) -> {
				if (p1.getLoadDate() == null && p2.getLoadDate() == null) return 0;
				if (p1.getLoadDate() == null) return 1;
				if (p2.getLoadDate() == null) return -1;
				return p1.getLoadDate().compareTo(p2.getLoadDate());
			});
			
			// 메타 정보 계산
			TmActualRouteResDto resDto = new TmActualRouteResDto();
			resDto.setCarno(carno);
			resDto.setPointCount(points.size());
			
			if (!points.isEmpty()) {
				// 시작/종료 시간 설정
				TmActualRoutePointDto firstPoint = points.get(0);
				TmActualRoutePointDto lastPoint = points.get(points.size() - 1);
				
				if (firstPoint.getLoadDate() != null) {
					resDto.setTripDate(dateFormat.format(firstPoint.getLoadDate()));
					resDto.setStartTime(timeFormat.format(firstPoint.getLoadDate()));
				}
				if (lastPoint.getLoadDate() != null) {
					resDto.setEndTime(timeFormat.format(lastPoint.getLoadDate()));
				}
				
				// GeoJSON LineString 생성
				resDto.setGeojsonLinestring(generateGeoJsonLineString(points));
			}
			
			result.add(resDto);
		}
		
		return result;
	}

	/**
	 * @description : 실착지 위치 조회 - Carrier 버전
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.12 OhEunbeom      생성 </pre>
	 */
	public List<TmCustomerLocationResDto> getCustomerLocationListByCarrier(TmCustomerLocationReqDto reqDto) {
		List<TmCustomerLocationResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getCustomerLocationListByCarrier", reqDto);
		return result;
	}

	/**
	 * @description : 관리처 목록 조회 - Carrier 버전
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.12 OhEunbeom      생성 </pre>
	 */
	public List<TmCustomerManagementResDto> getCustomerManagementListByCarrier(TmCustomerManagementReqDto reqDto) {
		List<TmCustomerManagementResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getCustomerManagementListByCarrier", reqDto);
		return result;
	}

}
