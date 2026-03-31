package cjfw.wms.tm.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.tm.dto.TmEntityRuleMasterListReqDto;
import cjfw.wms.tm.service.TmEntityRuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.08.11 
 * @description : 통합수당관리 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.11 ParkJinWoo 생성
 */
@Tag(name = "정산 > 운송비정산 >통합수당관리", description = "통합수당관리")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/tm/entityRule")
public class TmEntityRuleController {

	private final TmEntityRuleService tmEntityRuleService;


	/**
	 * @description : 통합수당관리 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.11 ParkJinWoo 생성
	 */
	@Operation(summary = "배차마스터체크결과", description = "배차마스터체크결과")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<Object> getMasterList(@Valid @RequestBody TmEntityRuleMasterListReqDto reqDto) {
		return ApiResult.createResult(tmEntityRuleService.getMasterList(reqDto));
	}
	
	/**
	 * @description : 통합수당관리 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.11 ParkJinWoo 생성
	 */
	@Operation(summary = "배차마스터체크결과", description = "배차마스터체크결과")
	@PostMapping(value = "/v1.0/geSttlItemCdList")
	public ApiResult<Object> geSttlItemCdList(@Valid @RequestBody TmEntityRuleMasterListReqDto reqDto) {
		return ApiResult.createResult(tmEntityRuleService.geSttlItemCdList(reqDto));
	}
	/**
	 * @description : 통합수당관리 저장 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.11 ParkJinWoo 생성
	 */
	@Operation(summary = "납품서출력로그(관리자)", description = "납품서츨력로그 (관리자)")
	@PostMapping(value = "/v1.0/saveConfirm")
	public ApiResult<Object> saveConfirm(@Valid @RequestBody TmEntityRuleMasterListReqDto reqDto) {
		return ApiResult.createResult(tmEntityRuleService.saveConfirm(reqDto));
	}
	/**
	 * @description : 통합수당관리 저장 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.11 ParkJinWoo 생성
	 */
	@Operation(summary = "납품서출력로그(관리자)", description = "납품서츨력로그 (관리자)")
	@PostMapping(value = "/v1.0/saveExcel")
	public ApiResult<Object> saveExcel(@Valid @RequestBody TmEntityRuleMasterListReqDto reqDto) {
		return ApiResult.createResult(tmEntityRuleService.saveExcel(reqDto));
	}
	
	/**
	 * @description : 통합수당관리 저장 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.11 ParkJinWoo 생성
	 */
	@Operation(summary = "납품서출력로그(관리자)", description = "납품서츨력로그 (관리자)")
	@PostMapping(value = "/v1.0/apiPostExcelUploadTmEntity")
	public ApiResult<Object> apiPostExcelUpload(@Valid @RequestBody TmEntityRuleMasterListReqDto reqDto) {
		return ApiResult.createResult(tmEntityRuleService.apiPostExcelUpload(reqDto));
	}
	
	/**
	 * @description : 마감조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.01.12 ParkJinWoo 생성
	 */
	@Operation(summary = "마감조회", description = "마감조회")
	@PostMapping(value = "/v1.0/apiTrspCloseChk")
	public ApiResult<Object> trspCloseChk(@Valid @RequestBody TmEntityRuleMasterListReqDto reqDto) {
		return ApiResult.createResult(tmEntityRuleService.trspCloseChk(reqDto));
	}
}