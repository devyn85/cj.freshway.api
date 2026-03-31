package cjfw.wms.cm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.cm.dto.CmLoginHistoryPopupResDto;
import cjfw.wms.common.extend.CommonDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JiSooKim (jskim14@cj.net) 
 * @date : 2025.10.24
 * @description : 접속이력정보 팝업 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.24 JiSooKim (jskim14@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CmLoginHistoryPopupService {
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "cmLoginHistoryPopupService.";
	
	private final CommonDao commonDao;
	
	/** 
	 * @description : 접속이력정보 팝업 목록 조회(전체)
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.24  JiSooKim (jskim14@cj.net)       생성
	 * ----------------------------------------------------------- 
	 */
	public List<CmLoginHistoryPopupResDto> getLoginHistoryList(CommonDto cmPopupReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getLoginHst", cmPopupReqDto); 
	}

}


