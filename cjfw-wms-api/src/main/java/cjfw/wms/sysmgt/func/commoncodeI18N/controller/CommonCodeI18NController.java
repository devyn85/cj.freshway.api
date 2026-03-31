/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.sysmgt.func.commoncodeI18N.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.sysmgt.func.commoncodeI18N.dto.CommonCodeI18NSaveReqDto;
import cjfw.wms.sysmgt.func.commoncodeI18N.dto.I18nCodeDtlGetReqDto;
import cjfw.wms.sysmgt.func.commoncodeI18N.dto.I18nCodeGrpGetReqDto;
import cjfw.wms.sysmgt.func.commoncodeI18N.dto.I18nCodeGrpGetResDto;
import cjfw.wms.sysmgt.func.commoncodeI18N.service.CommonCodeI18NService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "다국어 공통코드관리", description = "다국어 공통코드관리 API")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("sysmgt/func/commoncodeI18N")
public class CommonCodeI18NController {
	
	private final CommonCodeI18NService CommonCodeI18NService;

	/**
	 * 공통 그룹 코드를 검색한다.<br>
	 */
	@Operation(summary = "다국어 공통그룹코드 목록", description = "다국어 공통그룹코드 목록 조회")
	@GetMapping(value = "/searchGroupCd")
	public ApiResult<List<I18nCodeGrpGetResDto>> getGroupCdList(@Valid I18nCodeGrpGetReqDto i18CodeGrpReqDto) {
		return ApiResult.createResult(CommonCodeI18NService.getGroupCdList(i18CodeGrpReqDto));
	}

	/**
	 * 공통 코드를 조회한다.<br>
	 */
	@Operation(summary = "다국어 공통코드 목록", description = "다국어 공통코드 목록 조회")
	@GetMapping(value = "/searchCommonCd")
	public ApiResult<List<Map>> getCommonCdList(@Valid I18nCodeDtlGetReqDto i18CodeDtlReqDto) {
		return ApiResult.createResult(CommonCodeI18NService.getCommonCdList(i18CodeDtlReqDto));
	}

	/**
	 * 공통 코드 변경 정보를 업데이트 한다.<br>
	 */
	@Operation(summary = "다국어 공통코드 저장", description = "다국어 공통코드 저장(등록/수정/삭제)")
	@PostMapping(value = "/save")
	public ApiResult saveCommonCd(@RequestBody @Valid CommonCodeI18NSaveReqDto commonCodeI18NSaveReqDto) {
		return ApiResult.createResult(CommonCodeI18NService.saveCommonCd(commonCodeI18NSaveReqDto));
	}

}

