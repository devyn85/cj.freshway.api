package cjfw.wms.cm.service;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.wms.cm.dto.CmSearchCostCodePopupReqDto;
import cjfw.wms.cm.dto.CmSearchCostCodePopupResDto;
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
public class CmSearchCostCodePopupService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "cmSearchCostCodePopupService.";

	private final CommonDao commonDao;

	/**
	 * @description : 코스트코드 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.09 KimSunHo (sunhokim6229@cj.net) 생성 </pre>
	 */
	public Page<CmSearchCostCodePopupResDto> getCostCodeList(CmSearchCostCodePopupReqDto cmSearchCostCodePopupReqDto, Page page) {
		if(cmSearchCostCodePopupReqDto.getMultiSelect() != null && !cmSearchCostCodePopupReqDto.getMultiSelect().isEmpty()) {
			String[] codeList = cmSearchCostCodePopupReqDto.getMultiSelect().split(",");
			cmSearchCostCodePopupReqDto.setCodeList(codeList);
		}
		Page<CmSearchCostCodePopupResDto> result = commonDao.selectPageList(SERVICEID_PREFIX + "getCostCodeList", cmSearchCostCodePopupReqDto, page);
		return result;
	}
}
