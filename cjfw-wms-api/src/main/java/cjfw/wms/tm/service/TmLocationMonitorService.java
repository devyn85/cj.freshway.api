package cjfw.wms.tm.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cjfw.auth.jwt.JwtAuthenticationFilter;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ContextUtil;
import cjfw.wms.tm.dto.TmCarTrackQryLogDto;
import cjfw.wms.tm.dto.TmCarTrackQryLogListReqDto;
import cjfw.wms.tm.dto.TmCarTrackQryLogListResDto;
import cjfw.wms.tm.dto.TmVehicleConditionCountReqDto;
import cjfw.wms.tm.dto.TmVehicleConditionCountResDto;
import cjfw.wms.tm.dto.TmVehicleDetailMonitoringReqDto;
import cjfw.wms.tm.dto.TmVehicleDetailMonitoringResDto;
import cjfw.wms.tm.dto.TmVehicleGroupMonitoringReqDto;
import cjfw.wms.tm.dto.TmVehicleGroupMonitoringResDto;
import cjfw.wms.tm.dto.TmVehicleMonitoringReqDto;
import cjfw.wms.tm.dto.TmVehicleMonitoringResDto;
import cjfw.wms.tm.dto.TmVehicleStatusCountReqDto;
import cjfw.wms.tm.dto.TmVehicleStatusCountResDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : OhEunbeom
 * @date : 2025.09.10
 * @description : 센터별 차량 모니터링 조회 서비스
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.10 OhEunbeom      생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmLocationMonitorService {

	private transient static final String SERVICEID_PREFIX = "tmLocationMonitorService.";

	private final CommonDao commonDao;

	private final UserContext userContext;

	/**
	 * @description : 센터별 차량 모니터링 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.10 System Generated      생성
	 * 2025.11.10 OhEunbeom      조회 이력 저장 추가 </pre>
	 */
	public List<TmVehicleMonitoringResDto> getVehicleMonitoringList(TmVehicleMonitoringReqDto reqDto) {
		// 센터코드가 null이 아닐 때만 조회 이력 저장
		if (reqDto.getDccode() != null && !reqDto.getDccode().isEmpty()) {
			try {
				saveQueryLog(reqDto);
			} catch (Exception e) {
				log.error("조회 이력 저장 중 오류 발생", e);
			}
		}
		
		List<TmVehicleMonitoringResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getVehicleMonitoringList", reqDto);
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
	private void saveQueryLog(TmVehicleMonitoringReqDto reqDto) {
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

            if(reqDto.getCarnoList() != null && !reqDto.getCarnoList().isEmpty()){
                for (int i = 0; i < reqDto.getCarnoList().size(); i++) {

                    TmCarTrackQryLogDto logDto = TmCarTrackQryLogDto.builder()
                            .dccode(reqDto.getDccode())
                            .deliverydt(reqDto.getDeliverydt())
                            .userId(userContext.getUserId())
                            .username(userContext.getUserNm())
                            .ipAddress(ipAddress)
                            .device(device)
                            .carno(reqDto.getCarnoList().get(i))
                            .build();

                    commonDao.insert(SERVICEID_PREFIX + "insertCarTrackQryLog", logDto);
                }
            }else{
                TmCarTrackQryLogDto logDto = TmCarTrackQryLogDto.builder()
                        .dccode(reqDto.getDccode())
                        .deliverydt(reqDto.getDeliverydt())
                        .userId(userContext.getUserId())
                        .username(userContext.getUserNm())
                        .ipAddress(ipAddress)
                        .device(device)
                        .build();

                commonDao.insert(SERVICEID_PREFIX + "insertCarTrackQryLog", logDto);
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
	 * @description : 센터별 조차 차량 모니터링 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.10 OhEunbeom      생성 </pre>
	 */
	public List<TmVehicleGroupMonitoringResDto> getVehicleGroupMonitoringList(TmVehicleGroupMonitoringReqDto reqDto) {
		List<TmVehicleGroupMonitoringResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getVehicleGroupMonitoringList", reqDto);
		return result;
	}

	/**
	 * @description : 센터별 조차별 차량 모니터링 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09. Principles OhEunbeom      생성
	 * 2025.10.29 OhEunbeom      앱 미연결 상태 여부 추가 </pre>
	 */
	public List<TmVehicleDetailMonitoringResDto> getVehicleDetailMonitoringList(TmVehicleDetailMonitoringReqDto reqDto) {
		List<TmVehicleDetailMonitoringResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getVehicleDetailMonitoringList", reqDto);
		return result;
	}

	/**
	 * @description : 배송일자별 차량 모니터링 전체 카운트 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.10 OhEunbeom      생성 </pre>
	 */
	public List<TmVehicleStatusCountResDto> getVehicleStatusCountList(TmVehicleStatusCountReqDto reqDto) {
		List<TmVehicleStatusCountResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getVehicleStatusCountList", reqDto);
		return result;
	}

	/**
	 * @description : 배송일자별 차량 모니터링 조건 카운트 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.10 OhEunbeom      생성 </pre>
	 */
	public List<TmVehicleConditionCountResDto> getVehicleConditionCountList(TmVehicleConditionCountReqDto reqDto) {
		List<TmVehicleConditionCountResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getVehicleConditionCountList", reqDto);
		return result;
	}

	/**
	 * @description : 차량 이력 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.10 OhEunbeom      생성 </pre>
	 */
	public List<TmCarTrackQryLogListResDto> getCarTrackQryLogList(TmCarTrackQryLogListReqDto reqDto) {
		List<TmCarTrackQryLogListResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getCarTrackQryLogList", reqDto);
		
		// 마스킹 처리
		for (TmCarTrackQryLogListResDto dto : result) {
			// 사용자ID: 첫 네자리 이후 마스킹
			if (dto.getUserId() != null && dto.getUserId().length() > 4) {
				String userId = dto.getUserId();
				String maskedUserId = userId.substring(0, 4) + "*".repeat(userId.length() - 4);
				dto.setUserId(maskedUserId);
			}
			
			// 사용자명: 성과 이름의 마지막 자리 제외 마스킹
			if (dto.getUsername() != null && dto.getUsername().length() > 1) {
				String username = dto.getUsername();
				String maskedUsername = username.substring(0, 1) + "*".repeat(username.length() - 2) + username.substring(username.length() - 1);
				dto.setUsername(maskedUsername);
			}
			
			// IP주소: 두 번째 옥텟까지 마스킹
			if (dto.getIpAddress() != null) {
				String ipAddress = dto.getIpAddress();
				String[] parts = ipAddress.split("\\.");
				if (parts.length == 4) {
					// 두 번째와 세 번째 옥텟을 마스킹
					// 예: 192.168.123.45 -> 192.***.***.45
					parts[1] = "***";
					parts[2] = "***";
					dto.setIpAddress(String.join(".", parts));
				}
			}
		}
		
		return result;
	}

	/**
	 * @description : 센터별 차량 모니터링 조회 (Carrier 버전)
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.12 OhEunbeom      생성 </pre>
	 */
	public List<TmVehicleMonitoringResDto> getVehicleMonitoringListByCarrier(TmVehicleMonitoringReqDto reqDto) {
		// 센터코드가 null이 아닐 때만 조회 이력 저장
		if (reqDto.getDccode() != null && !reqDto.getDccode().isEmpty()) {
			try {
				saveQueryLog(reqDto);
			} catch (Exception e) {
				log.error("조회 이력 저장 중 오류 발생", e);
			}
		}
		
		List<TmVehicleMonitoringResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getVehicleMonitoringListByCarrier", reqDto);
		return result;
	}

	/**
	 * @description : 센터별 조차 차량 모니터링 조회 (Carrier 버전)
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.12 OhEunbeom      생성 </pre>
	 */
	public List<TmVehicleGroupMonitoringResDto> getVehicleGroupMonitoringListByCarrier(TmVehicleGroupMonitoringReqDto reqDto) {
		List<TmVehicleGroupMonitoringResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getVehicleGroupMonitoringListByCarrier", reqDto);
		return result;
	}

	/**
	 * @description : 센터별 조차별 차량 모니터링 조회 (Carrier 버전)
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.12 OhEunbeom      생성 </pre>
	 */
	public List<TmVehicleDetailMonitoringResDto> getVehicleDetailMonitoringListByCarrier(TmVehicleDetailMonitoringReqDto reqDto) {
		List<TmVehicleDetailMonitoringResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getVehicleDetailMonitoringListByCarrier", reqDto);
		return result;
	}

	/**
	 * @description : 배송일자별 차량 모니터링 전체 카운트 조회 (Carrier 버전)
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.12 OhEunbeom      생성 </pre>
	 */
	public List<TmVehicleStatusCountResDto> getVehicleStatusCountListByCarrier(TmVehicleStatusCountReqDto reqDto) {
		List<TmVehicleStatusCountResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getVehicleStatusCountListByCarrier", reqDto);
		return result;
	}

	/**
	 * @description : 배송일자별 차량 모니터링 조건 카운트 조회 (Carrier 버전)
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.12 OhEunbeom      생성 </pre>
	 */
	public List<TmVehicleConditionCountResDto> getVehicleConditionCountListByCarrier(TmVehicleConditionCountReqDto reqDto) {
		List<TmVehicleConditionCountResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getVehicleConditionCountListByCarrier", reqDto);
		return result;
	}

}
