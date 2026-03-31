package cjfw.wms.ms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.MsDCxCustReqDto;
import cjfw.wms.ms.dto.MsDCxCustResDto;
import cjfw.wms.ms.service.MsDCxCustService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.07.08
 * @description : 기준정보 > 센터기준정보 > 센터별거래처관리 목록 조회 및 저장 Controller
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.08        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Tag(name = "기준정보 > 센터기준정보 > 센터별거래처관리", description = "센터별거래처관리 목록 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ms/dCxCust")
public class MsDCxCustController {

	private final MsDCxCustService msDCxCustService;

	/**
	 * 
	 * @description : 센터별거래처관리 목록 검색 조회
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.08        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "센터별거래처관리 목록 조회", description = "센터별거래처관리 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<MsDCxCustResDto>> getMasterList(@RequestBody MsDCxCustReqDto dto) {
		return ApiResult.createResult(msDCxCustService.getMasterList(dto));
	}
		
	/**
	 * @description : 센터별거래처관리 목록 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.08        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "센터별거래처관리 목록 저장", description = "센터서류 목록 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<String> saveMasterList(@RequestBody List<MsDCxCustReqDto> dto) {
		return ApiResult.createResult(msDCxCustService.saveMasterList(dto));
	}
	
}