package cjfw.wms.cm.service;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.wms.cm.dto.CmSearchTaxTypePopupReqDto;
import cjfw.wms.cm.dto.CmSearchTaxTypePopupResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KimSunHo (sunhokim6229@cj.net) 
 * @date : 2025.08.09 
 * @description : 공통 검색 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.09 KimSunHo (sunhokim6229@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CmSearchTaxTypePopupService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "cmSearchTaxTypePopupService.";

	private final CommonDao commonDao;

	/**
	 * @description : Tax Type 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.09 KimSunHo (sunhokim6229@cj.net) 생성 </pre>
	 */
	public Page<CmSearchTaxTypePopupResDto> getTaxTypeList(CmSearchTaxTypePopupReqDto cmTaxTypePopupReqDto, Page page) {
		if(cmTaxTypePopupReqDto.getMultiSelect() != null && !cmTaxTypePopupReqDto.getMultiSelect().isEmpty()) {
			String[] codeList = cmTaxTypePopupReqDto.getMultiSelect().split(",");
			cmTaxTypePopupReqDto.setCodeList(codeList);
		}
		Page<CmSearchTaxTypePopupResDto> result = commonDao.selectPageList(SERVICEID_PREFIX + "getTaxTypeList", cmTaxTypePopupReqDto, page);
		return result;
	}
}
