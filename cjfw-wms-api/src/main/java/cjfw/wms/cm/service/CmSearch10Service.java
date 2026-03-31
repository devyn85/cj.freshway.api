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
import cjfw.wms.cm.dto.CmCustPopupReqDto;
import cjfw.wms.cm.dto.CmCustPopupResDto;
import cjfw.wms.cm.dto.CmSkuInfoPopupReqDto;
import cjfw.wms.cm.dto.CmSkuInfoPopupResDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CmSearch10Service {
	
	private final CommonDao commonDao;
	
	/**
	 * 거래처를 조회한다.
	 */
	public Page<CmCustPopupResDto> getCustPopupPagingList(CmCustPopupReqDto cmCustPopupReqDto, Page page) {
		prepareMulti(cmCustPopupReqDto);
		return commonDao.selectPageList("cmSearch10Service.getCustPopupPagingList", cmCustPopupReqDto, page);
	}

	/**
	 * 상품정보를 조회한다.
	 */
	public CmSkuInfoPopupResDto getSkuInfoPopup(CmSkuInfoPopupReqDto cmCustPopupReqDto) {
		CmSkuInfoPopupResDto result = commonDao.selectOne("cmSearch10Service.getSkuInfoPopup", cmCustPopupReqDto);
		return result;
	}
	
	/**
     * 관리처를 조회한다.
     */
    public Page<CmCustPopupResDto> getMngPlcPopupPagingList(CmCustPopupReqDto cmCustPopupReqDto, Page page) {
        Page<CmCustPopupResDto> result = commonDao.selectPageList("cmSearch10Service.getMngPlcPopupPagingList", cmCustPopupReqDto, page);
        return result;
    }
    
	/* multiSelect → 999개 청크(codeGroups) */
	private void prepareMulti(CmCustPopupReqDto req) {
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
