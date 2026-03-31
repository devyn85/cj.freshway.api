package cjfw.wms.ms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.ms.dto.MsSkuCbmResDto;
import cjfw.wms.ms.dto.MsSkuDetailResDto;
import cjfw.wms.ms.dto.MsSkuReqDto;
import cjfw.wms.ms.dto.MsSkuResDto;
import cjfw.wms.ms.service.MsSkuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.06.11
 * @description : 기준정보 > 상품기준정보 > 상품정보 목록 조회 및 저장 Controller
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.11        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Tag(name = "기준정보 > 상품기준정보 > 상품정보", description = "상품정보 목록 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ms/sku")
public class MsSkuController {

	private final MsSkuService msSkuService;

	/**
	 * 
	 * @description : 상품정보 목록 검색 조회
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.17        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "상품정보 목록 조회", description = "상품정보 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<Page<MsSkuResDto>> getMasterList(@RequestBody MsSkuReqDto dto) {
		return ApiResult.createResult(msSkuService.getMasterList(dto));
	}
	
	/**
	 * @description : 상품정보 상세 검색 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.17        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "상품정보 상세 조회", description = "상품정보 상세 조회")
	@GetMapping(value = "/v1.0/getMaster")
	public ApiResult<MsSkuDetailResDto> getMaster(MsSkuReqDto dto) {
		return ApiResult.createResult(msSkuService.getMaster(dto));
	}
	
	/**
	 * @description : 상품정보 목록 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.17        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "상품정보 목록 저장", description = "상품정보 목록 저장")
	@PostMapping(value = "/v1.0/saveSkuPlt")
	public ApiResult<Object> saveSkuPlt(@RequestBody List<MsSkuReqDto> dto) {
		return ApiResult.createResult(msSkuService.saveSkuPlt(dto));
	}
	
	/**
	 * 
	 * @description : CBM 목록 검색 조회
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.17        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "CBM 목록 조회", description = "CBM 목록 조회")
	@GetMapping(value = "/v1.0/getCbmList")
	public ApiResult<List<MsSkuCbmResDto>> getCbmList(MsSkuReqDto dto) {
		return ApiResult.createResult(msSkuService.getCbmList(dto));
	}

	/**
	 * 
	 * @description : CBM 목록 저장
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.17        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "CBM 목록 저장", description = "CBM 목록 저장")
	@PostMapping(value = "/v1.0/saveCbm")
	public ApiResult<Object> saveCbm(@RequestBody List<MsSkuReqDto> dto) {
		return ApiResult.createResult(msSkuService.saveCbm(dto));
	}
	
	/**
	 * @description : 상품정보 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.17        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "상품정보 저장", description = "상품정보 저장")
	@PostMapping(value = "/v1.0/saveMaster")
	public ApiResult<Object> saveMaster(@RequestBody MsSkuReqDto dto) {
		return ApiResult.createResult(msSkuService.saveMaster(dto));
	}
	
	/**
	 * 
	 * @description : 엑셀업로드 저장
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.17        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "엑셀업로드 저장", description = "엑셀업로드 저장")
	@PostMapping(value = "/v1.0/saveExcelList")
	public ApiResult<Object> saveExcelList(@RequestBody List<MsSkuReqDto> dto) {
		return ApiResult.createResult(msSkuService.saveExcelList(dto));
	}
	
	/**
	 * @description : 엑셀 업로드 유효성 검사(목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.02        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "엑셀 업로드 유효성 검사(목록)", description = "엑셀 업로드 유효성 검사(목록)")
	@PostMapping(value = "/v1.0/getValidateExcelList")
	public ApiResult<List<MsSkuResDto>> getValidateExcelList(@RequestBody @Valid List<MsSkuReqDto> dtoList) {
		return ApiResult.createResult(msSkuService.getValidateExcelList(dtoList));
	}
}