package cjfw.wms.ms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.ms.dto.MsSkuDcSetSTOReqDto;
import cjfw.wms.ms.dto.MsSkuDcSetSTOResDto;
import cjfw.wms.ms.dto.MsSkuReqDto;
import cjfw.wms.ms.dto.MsSkuResDto;
import cjfw.wms.ms.service.MsSkuDcSetSTOService;
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
 * @date        : 2025.07.04
 * @description : 기준정보 > 상품기준정보 > 센터상품속성(광역일배) 목록 조회 및 저장 Controller
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.04        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Tag(name = "기준정보 > 상품기준정보 > 센터상품속성(광역일배)", description = "센터상품속성(광역일배) 목록 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ms/skuDcSetSTO")
public class MsSkuDcSetSTOController {

	private final MsSkuDcSetSTOService msSkuDcSetSTOService;

	/**
	 * 
	 * @description : 센터상품속성(광역일배) 목록 검색 조회
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.04        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "센터상품속성(광역일배) 목록 조회", description = "센터상품속성(광역일배) 목록 조회")
	@GetMapping(value = "/v1.0/getMasterList")
	public ApiResult<Page<MsSkuDcSetSTOResDto>> getMasterList(MsSkuDcSetSTOReqDto dto, Page<?> page) {
		return ApiResult.createResult(msSkuDcSetSTOService.getMasterList(dto, page));
	}
	
	/**
	 * @description : 센터상품속성(광역일배) 상세 검색 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.04        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "센터상품속성(광역일배) 상세 조회", description = "센터상품속성(광역일배) 상세 조회")
	@GetMapping(value = "/v1.0/getMaster")
	public ApiResult<MsSkuDcSetSTOResDto> getMaster(MsSkuDcSetSTOReqDto dto) {
		return ApiResult.createResult(msSkuDcSetSTOService.getMaster(dto));
	}
	
	/**
	 * @description : 센터상품속성(광역일배) 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.04        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "센터상품속성(광역일배) 저장", description = "센터상품속성(광역일배) 저장")
	@PostMapping(value = "/v1.0/saveMaster")
	public ApiResult<Object> saveMaster(@RequestBody List<MsSkuDcSetSTOReqDto> dtoList) {
		log.info("Saving Master: {}", dtoList);
		return ApiResult.createResult(msSkuDcSetSTOService.saveMaster(dtoList));
	}
	
	/**
	 * 
	 * @description : 엑셀업로드 저장
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.01        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "엑셀업로드 저장", description = "엑셀업로드 저장")
	@PostMapping(value = "/v1.0/saveExcelList")
	public ApiResult<Object> saveExcelList(@RequestBody List<MsSkuDcSetSTOReqDto> dto) {
		return ApiResult.createResult(msSkuDcSetSTOService.saveExcelList(dto));
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
	public ApiResult<List<MsSkuDcSetSTOResDto>> getValidateExcelList(@RequestBody @Valid List<MsSkuDcSetSTOReqDto> dtoList) {
		return ApiResult.createResult(msSkuDcSetSTOService.getValidateExcelList(dtoList));
	}
}