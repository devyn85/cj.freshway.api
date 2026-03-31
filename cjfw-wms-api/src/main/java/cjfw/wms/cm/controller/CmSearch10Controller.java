/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.cm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.cm.dto.CmCustPopupReqDto;
import cjfw.wms.cm.dto.CmCustPopupResDto;
import cjfw.wms.cm.dto.CmSkuInfoPopupReqDto;
import cjfw.wms.cm.dto.CmSkuInfoPopupResDto;
import cjfw.wms.cm.service.CmSearch10Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/cm/search")
public class CmSearch10Controller {

	private final CmSearch10Service service;

	/**
	 * 거래처 데이터 조회
	 */
	@PostMapping(value="/v1.0/getCustPopupList")
	public ApiResult<Page<CmCustPopupResDto>> getCustPopupPagingList(@RequestBody CmCustPopupReqDto CmCustPopupReqDto, Page page) {
		return ApiResult.createResult(service.getCustPopupPagingList(CmCustPopupReqDto, page));
	}
	
	/**
	 * 상품정보 데이터 조회
	 */
	@GetMapping(value="/v1.0/getSkuInfoPopup")
	public ApiResult<CmSkuInfoPopupResDto> getSkuInfoPopup(CmSkuInfoPopupReqDto cmCustPopupReqDto) {
		return ApiResult.createResult(service.getSkuInfoPopup(cmCustPopupReqDto));
	}
	
	/**
     * 관리처 데이터 조회
     */
    @GetMapping(value="/v1.0/getMngPlcPopupList")
    public ApiResult<Page<CmCustPopupResDto>> getMngPlcPopupPagingList(CmCustPopupReqDto CmCustPopupReqDto, Page page) {
        return ApiResult.createResult(service.getMngPlcPopupPagingList(CmCustPopupReqDto, page));
    }
}