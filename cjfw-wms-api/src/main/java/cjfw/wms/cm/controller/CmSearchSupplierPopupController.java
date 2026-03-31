package cjfw.wms.cm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.cm.dto.CmSearchSupplierPopupReqDto;
import cjfw.wms.cm.dto.CmSearchSupplierPopupResDto;
import cjfw.wms.cm.service.CmSearchSupplierPopupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KimSunHo (sunhokim6229@cj.net) 
 * @date : 2025.08.09 
 * @description : 공통 검색 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.09 KimSunHo (sunhokim6229@cj.net) 생성 </pre>
 */
@Tag(name = "공통 검색", description = "공통 검색")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/cm/search")
public class CmSearchSupplierPopupController {

	private final CmSearchSupplierPopupService cmSupplierPopupService;

	/**
	 * @description : Supplier 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.09 KimSunHo (sunhokim6229@cj.net) 생성 </pre>
	 */
	@Operation(summary = "Supplier 목록 조회", description = "Supplier 목록 조회")
	@GetMapping(value = "/v1.0/getSupplierList")
	public ApiResult<List<CmSearchSupplierPopupResDto>> getSupplierList(@Valid CmSearchSupplierPopupReqDto cmSupplierPopupReqDto) {
		return ApiResult.createResult(cmSupplierPopupService.getSupplierList(cmSupplierPopupReqDto));
	}
}