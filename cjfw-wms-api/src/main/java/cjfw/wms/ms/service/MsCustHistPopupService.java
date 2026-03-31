package cjfw.wms.ms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.ms.dto.MsCustHistPopupReqDto;
import cjfw.wms.ms.dto.MsCustHistPopupResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.07.29 
 * @description : 이력정보 상세정보 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.29 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MsCustHistPopupService {
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private static final String SERVICEID_PREFIX = "msCustHist.";
	private final CommonDao commonDao;
	
	/**
	 * @description : 이력정보 상세정보 조회 Service
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.29 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
	public List<MsCustHistPopupResDto> getCustHistList (MsCustHistPopupReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getCustHistList", dto);
	}
}
