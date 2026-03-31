package cjfw.wms.ms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.MsDistrictReqDto;
import cjfw.wms.ms.dto.MsDistrictResDto;
import cjfw.wms.ms.service.MsDistrictService;
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
 * @date        : 2025.08.08
 * @description : 기준정보 > 권역기준정보 > 권역별차량관리 목록 조회 및 저장 Controller
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.08        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Tag(name = "기준정보 > 권역기준정보 > 권역별차량관리", description = "권역별차량관리 목록 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ms/district")
public class MsDistrictController {

	private final MsDistrictService msDistrictService;

	/**
	 * 
	 * @description : 권역별차량관리 목록 검색 조회
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.08        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "권역별차량관리 목록 조회", description = "권역별차량관리 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<MsDistrictResDto>> getMasterList(@RequestBody MsDistrictReqDto dto) {
		return ApiResult.createResult(msDistrictService.getMasterList(dto));
	}
		
	/**
	 * @description : 권역별차량관리 목록 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.08        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "권역별차량관리 목록 저장", description = "권역별차량관리 목록 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<Object> saveMasterList(@RequestBody List<MsDistrictReqDto> dto) {
		log.info("권역별차량관리 목록 저장 요청: {}", dto);
		return ApiResult.createResult(msDistrictService.saveMasterList(dto));
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
	public ApiResult<List<MsDistrictResDto>> getValidateExcelList(@RequestBody @Valid List<MsDistrictReqDto> dtoList) {
		return ApiResult.createResult(msDistrictService.getValidateExcelList(dtoList));
	}
	
}