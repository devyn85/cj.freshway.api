package cjfw.wms.cm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.cm.dto.CmPurchaseBuyerHstPopupReqDto;
import cjfw.wms.cm.dto.CmPurchaseBuyerHstPopupResDto;
import cjfw.wms.cm.service.CmPurchaseBuyerHstPopupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.05.12
 * @description : 공통 검색 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.04.30 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Tag(name = "기준정보 > 거래처기준정보 > 협력사정보", description = "협력사정보")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/cm/search")
public class CmPurchaseBuyerHstPopupController {
	private final CmPurchaseBuyerHstPopupService cmPurchaseBuyerHstPopupService;
	/**
	 * @description : 수급담당 변경이력 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.15 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
	@Operation(summary = "수급담당 변경이력 조회", description = "수급담당 변경이력 조회")
	@GetMapping(value = "/v1.0/getPurchaseBuyerHstList")
	public ApiResult<Page<CmPurchaseBuyerHstPopupResDto>> getPurchaseBuyerHst(CmPurchaseBuyerHstPopupReqDto cmPurchaseBuyerHstPopupReqDto, Page<?> page) {
		return ApiResult.createResult(cmPurchaseBuyerHstPopupService.getPurchaseBuyerHstList(cmPurchaseBuyerHstPopupReqDto, page));
	}
	
}