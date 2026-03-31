package cjfw.wms.cm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.wms.cm.dto.CmSearchSupplierPopupReqDto;
import cjfw.wms.cm.dto.CmSearchSupplierPopupResDto;
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
public class CmSearchSupplierPopupService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "cmSearchSupplierPopupService.";

	private final CommonDao commonDao;

	/**
	 * @description : Supplier 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.09 KimSunHo (sunhokim6229@cj.net) 생성 </pre>
	 */
	public List<CmSearchSupplierPopupResDto> getSupplierList(CmSearchSupplierPopupReqDto cmSupplierPopupReqDto) {
//		if(cmSupplierPopupReqDto.getMultiSelect() != null && !cmSupplierPopupReqDto.getMultiSelect().isEmpty()) {
//			String[] codeList = cmSupplierPopupReqDto.getMultiSelect().split(",");
//			cmSupplierPopupReqDto.setCodeList(codeList);
//		}
//		Page<CmSearchSupplierPopupResDto> result = commonDao.selectPageList(SERVICEID_PREFIX + "getSupplierList", cmSupplierPopupReqDto, page);
//		return result;
	    
	    return commonDao.selectList(SERVICEID_PREFIX + "getSupplierList", cmSupplierPopupReqDto);
	}
}
