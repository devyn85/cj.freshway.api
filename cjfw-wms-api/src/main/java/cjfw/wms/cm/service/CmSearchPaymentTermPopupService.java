package cjfw.wms.cm.service;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.wms.cm.dto.CmSearchPaymentTermPopupReqDto;
import cjfw.wms.cm.dto.CmSearchPaymentTermPopupResDto;
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
public class CmSearchPaymentTermPopupService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "cmSearchPaymentTermPopupService.";

	private final CommonDao commonDao;

	/**
	 * @description : PAYMENT TERM 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.09 KimSunHo (sunhokim6229@cj.net) 생성 </pre>
	 */
	public Page<CmSearchPaymentTermPopupResDto> getPaymentTermList(CmSearchPaymentTermPopupReqDto cmPaymentTermPopupReqDto, Page page) {
		if(cmPaymentTermPopupReqDto.getMultiSelect() != null && !cmPaymentTermPopupReqDto.getMultiSelect().isEmpty()) {
			String[] codeList = cmPaymentTermPopupReqDto.getMultiSelect().split(",");
			cmPaymentTermPopupReqDto.setCodeList(codeList);
		}
		Page<CmSearchPaymentTermPopupResDto> result = commonDao.selectPageList(SERVICEID_PREFIX + "getPaymentTermList", cmPaymentTermPopupReqDto, page);
		return result;
	}
}
