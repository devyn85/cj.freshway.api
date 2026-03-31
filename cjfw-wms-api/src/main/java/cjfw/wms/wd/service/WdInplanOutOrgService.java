package cjfw.wms.wd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.wms.wd.dto.WdInplanOutOrgSoReqDto;
import cjfw.wms.wd.dto.WdInplanOutOrgSoResDto;
import cjfw.wms.wd.dto.WdInplanOutOrgStoReqDto;
import cjfw.wms.wd.dto.WdInplanOutOrgStoResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.11.27 
 * @description : 외부창고운송비현황 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.27 ParkJinWoo 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WdInplanOutOrgService {
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "WdInplanOutOrgService.";

	private final CommonDao commonDao;
	private final UserContext userContext;

	/**
	 * @description : 외부창고운송비현황 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.27 ParkJinWoo 생성
	 */
	public List<WdInplanOutOrgSoResDto> getMasterList(WdInplanOutOrgSoReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
	}

	/**
	 * @description : 외부창고운송비현황 STO조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.27 ParkJinWoo 생성
	 */
	public List<WdInplanOutOrgStoResDto> getMasterList2(WdInplanOutOrgStoReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList2", dto);
	}
	
}
