package cjfw.wms.cm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.cm.dto.CmCarrierPopupReqDto;
import cjfw.wms.cm.dto.CmCarrierPopupResDto;
import cjfw.wms.cm.dto.CmSkuSpecPopupReqDto;
import cjfw.wms.cm.dto.CmSkuSpecPopupResDto;
import cjfw.wms.cm.service.CmSearch20Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KimSunHo (sunhokim6229@cj.net) 
 * @date : 2025.05.30 
 * @description : 공통 검색 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.04.30 KimSunHo (sunhokim6229@cj.net) 생성 </pre>
 */
@Tag(name = "공통 검색", description = "공통 검색")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/cm/search")
public class CmSearch20Controller {

	private final CmSearch20Service cmSearchService;

	/**
	 * @description : 운송사 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.09 KimSunHo (sunhokim6229@cj.net) 생성 </pre>
	 */
	@Operation(summary = "운송사 목록 조회", description = "운송사 목록 조회")
	@PostMapping(value = "/v1.0/getCarrierList")
	public ApiResult<Page<CmCarrierPopupResDto>> getCarrierList(@RequestBody CmCarrierPopupReqDto cmSearchCarrierPopupReqDto, Page<?> page) {
		return ApiResult.createResult(cmSearchService.getCarrierList(cmSearchCarrierPopupReqDto, page));
	}
	/**
	 * @description : 운송사 목록 조회(드롭다운용)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.12 ParkYosep (dytpq362@cj.net)
	 */
	@Operation(summary = "운송사 목록 조회", description = "운송사 목록 조회")
	@PostMapping(value = "/v1.0/getCarrierDropList")
	public ApiResult<Page<CmCarrierPopupResDto>> getCarrierList1(@RequestBody CmCarrierPopupReqDto cmSearchCarrierPopupReqDto, Page<?> page) {
		return ApiResult.createResult(cmSearchService.getCarrierDropList(cmSearchCarrierPopupReqDto, page));
	}
	
	/**
	 * @description : 상품 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.12 KimSunHo (sunhokim6229@cj.net) 생성 </pre>
	 */
	@Operation(summary = "상품 분류 목록 조회", description = "상품 목록 조회")
	@GetMapping(value = "/v1.0/getSkuSpecList")
	public ApiResult<List<CmSkuSpecPopupResDto>> getSkuSpecList(@Valid CmSkuSpecPopupReqDto cmSkuSpecPopupReqDto) {
		return ApiResult.createResult(cmSearchService.getSkuSpecList(cmSkuSpecPopupReqDto));
	}
}