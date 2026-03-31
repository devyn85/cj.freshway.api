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
import cjfw.wms.cm.dto.CmSearchDistrictPopupReqDto;
import cjfw.wms.cm.dto.CmSearchDistrictPopupResDto;
import cjfw.wms.cm.dto.CmSearchLocationPopupReqDto;
import cjfw.wms.cm.dto.CmSearchLocationPopupResDto;
import cjfw.wms.cm.dto.CmSearchSkuGroup1PopupReqDto;
import cjfw.wms.cm.dto.CmSearchSkuGroup1PopupResDto;
import cjfw.wms.cm.service.CmSearch110Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KwonJungYun (jungyun8667@cj.net)
 * @date : 2025.05.12
 * @description : 공통 검색 Controller Class
 * @issues : <pre>
 * -----------------------------------------------------------
 * DATE AUTHOR MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.05.12 KwonJungYun (jungyun8667@cj.net) 생성
 */
@Tag(name = "공통 검색", description = "공통 검색")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/cm/search")
public class CmSearch110Controller {

	private final CmSearch110Service service;

	/**
	 * @description : 로케이션 팝업 데이터 조회
	 * @issues : <pre>
	 * -----------------------------------------------------------
	 * DATE AUTHOR MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.12 KwonJungYun (jungyun8667@cj.net) 생성
	 */
	@Operation(summary = "로케이션 팝업 목록 조회", description = "로케이션 팝업 목록 조회")
	@PostMapping(value="/v1.0/getLocationPopupList")
	public ApiResult<Page<CmSearchLocationPopupResDto>> getLocationPopupList(@RequestBody CmSearchLocationPopupReqDto cmSearchLocationPopupReqDto, Page<?> page) {
		return ApiResult.createResult(service.getLocationPopupList(cmSearchLocationPopupReqDto, page));
	}

	@Operation(summary = "배송권역 팝업 목록 조회", description = "배송권역 팝업 목록 조회")
	@GetMapping(value="/v1.0/getDistrictPopupList")
	public ApiResult<Page<CmSearchDistrictPopupResDto>> getDistrictPopupList(CmSearchDistrictPopupReqDto cmSearchDistrictPopupReqDto, Page page) {
		return ApiResult.createResult(service.getDistrictPopupList(cmSearchDistrictPopupReqDto, page));
	}

	@Operation(summary = "소분류 팝업 목록 조회", description = "소분류 팝업 목록 조회")
	@PostMapping(value="/v1.0/getSkuGroup1PopupList")
	public ApiResult<Page<CmSearchSkuGroup1PopupResDto>> getSkuGroup1PopupList(@RequestBody CmSearchSkuGroup1PopupReqDto cmSearchSkuGroup1PopupReqDto, Page<?> page) {
		return ApiResult.createResult(service.getSkuGroup1PopupList(cmSearchSkuGroup1PopupReqDto, page));
	}
}