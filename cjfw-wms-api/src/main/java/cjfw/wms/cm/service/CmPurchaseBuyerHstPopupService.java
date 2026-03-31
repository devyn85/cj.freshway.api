package cjfw.wms.cm.service;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.wms.cm.dto.CmPurchaseBuyerHstPopupReqDto;
import cjfw.wms.cm.dto.CmPurchaseBuyerHstPopupResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.05.12
 * @description : 공통 검색 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.12 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class CmPurchaseBuyerHstPopupService {
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "cmPurchaseBuyerHstPopupService.";
	
	private final CommonDao commonDao;

	/**
	 * @description : 수급담당 변경이력 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.13 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
	public Page<CmPurchaseBuyerHstPopupResDto> getPurchaseBuyerHstList(CmPurchaseBuyerHstPopupReqDto cmPurchaseBuyerHstPopupReqDto, Page<?> page) {
		return commonDao.selectPageList(SERVICEID_PREFIX + "getPurchaseBuyerHstList", cmPurchaseBuyerHstPopupReqDto, page);
		
	}
	
}
