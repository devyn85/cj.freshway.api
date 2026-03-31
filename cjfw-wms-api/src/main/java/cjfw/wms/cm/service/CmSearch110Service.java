/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.cm.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.wms.cm.dto.CmSearchDistrictPopupReqDto;
import cjfw.wms.cm.dto.CmSearchDistrictPopupResDto;
import cjfw.wms.cm.dto.CmSearchLocationPopupReqDto;
import cjfw.wms.cm.dto.CmSearchLocationPopupResDto;
import cjfw.wms.cm.dto.CmSearchSkuGroup1PopupReqDto;
import cjfw.wms.cm.dto.CmSearchSkuGroup1PopupResDto;
import cjfw.wms.constants.CmSearchConstants;
import lombok.RequiredArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KwonJungYun (jungyun8667@cj.net)
 * @date : 2025.05.12
 * @description : 공통 검색 Service Class
 * @issues : <pre>
 * -----------------------------------------------------------
 * DATE AUTHOR MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.05.12 KwonJungYun (jungyun8667@cj.net) 생성
 */
@Service
@RequiredArgsConstructor
public class CmSearch110Service {

	private transient static final String SERVICEID_PREFIX = "cmSearch110Service.";
	private final CommonDao commonDao;


	/**
	 * @description : 로케이션 팝업 데이터 조회
	 * @issues :
	 * -----------------------------------------------------------
	 * DATE AUTHOR MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.12 KwonJungYun (jungyun8667@cj.net) 생성
	 */
	public Page<CmSearchLocationPopupResDto> getLocationPopupList(CmSearchLocationPopupReqDto cmSearchLocationReqDto, Page<?> page) {
		return commonDao.selectPageList(SERVICEID_PREFIX + "getLocationList", cmSearchLocationReqDto, page);
	}

	/**
	 * @description : 배송권역 팝업 데이터 조회
	 * @issues :
	 * -----------------------------------------------------------
	 * DATE AUTHOR MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.12 KwonJungYun (jungyun8667@cj.net) 생성
	 */
	public Page<CmSearchDistrictPopupResDto> getDistrictPopupList(CmSearchDistrictPopupReqDto cmSearchDistrictPopupReqDto, Page page) {
		if(cmSearchDistrictPopupReqDto.getMultiSelect() != null && !cmSearchDistrictPopupReqDto.getMultiSelect().isEmpty()) {
			String[] codeList = cmSearchDistrictPopupReqDto.getMultiSelect().split(",");
			cmSearchDistrictPopupReqDto.setCodeList(codeList);
		}
		Page<CmSearchDistrictPopupResDto> result = commonDao.selectPageList(SERVICEID_PREFIX + "getDistrictList", cmSearchDistrictPopupReqDto, page);
		return result;
	}
	/**
	 * @description : 소분류 팝업 데이터 조회
	 * @issues :
	 * -----------------------------------------------------------
	 * DATE AUTHOR MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.12 KwonJungYun (jungyun8667@cj.net) 생성
	 */
	public Page<CmSearchSkuGroup1PopupResDto> getSkuGroup1PopupList(CmSearchSkuGroup1PopupReqDto cmSearchSkuGroup1PopupReqDto, Page<?> page) {
		cmSearchSkuGroup1PopupReqDto.setSpecCategory(CmSearchConstants.SKUGROUP1_SPECCATEGORY);
		cmSearchSkuGroup1PopupReqDto.setSpecClass(CmSearchConstants.SKUGROUP1_SPECCLASS);
		
		prepareMulti(cmSearchSkuGroup1PopupReqDto);
		return commonDao.selectPageList(SERVICEID_PREFIX + "getSkuGroup1List", cmSearchSkuGroup1PopupReqDto, page);
	}
	
	/* multiSelect → 999개 청크(codeGroups) */
	private void prepareMulti(CmSearchSkuGroup1PopupReqDto req) {
	    String csv = req.getMultiSelect();
	    if (csv == null || csv.isBlank()) return;

	    List<String> items = Arrays.stream(csv.split(","))
	        .map(String::trim)
	        .filter(s -> !s.isEmpty())
	        .distinct()
	        .limit(5000)
	        .collect(Collectors.toList());
	    if (items.isEmpty()) return;

	    int batchSize = 999;
	    List<List<String>> groups = new ArrayList<>();
	    for (int i = 0; i < items.size(); i += batchSize) {
	        groups.add(items.subList(i, Math.min(i + batchSize, items.size())));
	    }
	    req.setCodeGroups(groups);
	}		

}
