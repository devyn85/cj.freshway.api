package cjfw.wms.ms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.MsSkuDcLocChangeReqDto;
import cjfw.wms.ms.dto.MsSkuDcLocChangeResDto;
import cjfw.wms.ms.dto.MsSkuDcLocChangeResultResDto;
import cjfw.wms.ms.service.MsSkuDcLocChangeService;
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
 * @date        : 2025.07.15
 * @description : 기준정보 > 센터기준정보 > 기본LOC 일괄등록 목록 조회 및 저장 Controller
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.15        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Tag(name = "기준정보 > 센터기준정보 > 기본LOC 일괄등록 정보", description = "기본LOC 일괄등록 정보 목록 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ms/skuDcLocChange")
public class MsSkuDcLocChangeController {

	private final MsSkuDcLocChangeService msSkuDcLocChangeService;

	/**
	 * 
	 * @description : 기본LOC 일괄등록 목록 검색 조회
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.24        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "기본LOC 일괄등록 목록 조회", description = "기본LOC 일괄등록 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<MsSkuDcLocChangeResDto>> getMasterList(@RequestBody MsSkuDcLocChangeReqDto dto) {
		log.info("getMasterList called with parameters: {}", dto);
		return ApiResult.createResult(msSkuDcLocChangeService.getMasterList(dto));
	}
	
	/**
	 * 
	 * @description : 기본LOC 일괄등록 목록 검색 조회
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.24        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "기본LOC 일괄등록 목록 저장", description = "기본LOC 일괄등록 목록 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<List<MsSkuDcLocChangeResultResDto>> saveMasterList(@RequestBody MsSkuDcLocChangeReqDto dto) {
		log.info("getMasterList called with parameters: {}", dto);
		return ApiResult.createResult(msSkuDcLocChangeService.saveMasterList(dto));
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
	public ApiResult<List<MsSkuDcLocChangeResultResDto>> getValidateExcelList(@RequestBody @Valid List<MsSkuDcLocChangeReqDto> dtoList) {
		return ApiResult.createResult(msSkuDcLocChangeService.getValidateExcelList(dtoList));
	}
	
	/**
	 * 
	 * @description : 수발주정보 상세 목록 검색 조회
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.24        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "엑셀 업로드 목록 조회", description = "수발주정보 엑셀 업로드 목록 조회")
	@PostMapping(value = "/v1.0/getExcelUploadList")
	public ApiResult<List<MsSkuDcLocChangeResultResDto>> getExcelUploadList(@RequestBody MsSkuDcLocChangeReqDto dto) {
		log.info("Received dto: {}", dto);
		return ApiResult.createResult(msSkuDcLocChangeService.getExcelUploadList(dto));
	}
	/**
	 * 
	 * @description : 존정보 조회
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2026.03.26       생성
	 */
	@Operation(summary = "zone  조회", description = "zone 조회")
	@PostMapping(value = "/v1.0/getZoneList")
	public ApiResult<MsSkuDcLocChangeResDto> getZoneList(@RequestBody MsSkuDcLocChangeReqDto dto) {
		return ApiResult.createResult(msSkuDcLocChangeService.getZoneList(dto));
	}
}