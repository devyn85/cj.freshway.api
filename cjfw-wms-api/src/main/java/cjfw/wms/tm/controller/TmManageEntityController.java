package cjfw.wms.tm.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.tm.dto.TmManageEntityMasterListReqDto;
import cjfw.wms.tm.service.TmManageEntityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.08.04 
 * @description : 정산항목관리 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.04 ParkJinWoo 생성
 */
@Tag(name = "정산 > 운송비정산 > 정산항목관리", description = "정산항목관리")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/tm/manageEntity")
public class TmManageEntityController {

	private final TmManageEntityService tmManageEntityService;


	/**
	 * @description : 정산항목관리 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.04 ParkJinWoo 생성
	 */
	@Operation(summary = "납품서출력로그(관리자)", description = "납품서츨력로그 (관리자)")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<Object> getMasterList(@Valid @RequestBody TmManageEntityMasterListReqDto reqDto) {
		return ApiResult.createResult(tmManageEntityService.getMasterList(reqDto));
	}
	
	/**
	 * @description : 정산항목관리 저장 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.04 ParkJinWoo 생성
	 */
	@Operation(summary = "납품서출력로그(관리자)", description = "납품서츨력로그 (관리자)")
	@PostMapping(value = "/v1.0/saveConfirm")
	public ApiResult<Object> saveConfirm(@Valid @RequestBody TmManageEntityMasterListReqDto reqDto) {
		return ApiResult.createResult(tmManageEntityService.saveConfirm(reqDto));
	}


}