package cjfw.wms.ms.service;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.ms.dto.MsCreditInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.06.23
 * @description : 기준정보 > 센터기준정보 > 기둥/빈 공간 정보 목록 조회 및 저장 Service
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.24        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MsCreditInfoService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "msCreditInfoService.";
	private final CommonDao commonDao;
	
	/**
	 * 여신정보 조회<br>
	 * 
	 * @param dto
	 * @return
	 */
	public int getTotalCount(MsCreditInfoDto dto) {
		return commonDao.selectOne(SERVICEID_PREFIX + "getTotalCount", dto);
	}
	
	/**
	 * 여신정보 등록<br>
	 * 
	 * @param dto
	 * @return
	 */
	public int insertToBe(MsCreditInfoDto dto) {
		return commonDao.insert(SERVICEID_PREFIX + "insertToBe", dto);
	}	
	

	
	
}
