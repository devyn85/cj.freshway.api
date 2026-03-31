package cjfw.wms.tm.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.tm.dto.TmInplanMsgListReqDto;
import cjfw.wms.tm.service.TmInplanMsgService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.08.07 
 * @description : 배송전달사항 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.07 ParkJinWoo 생성
 */
@Tag(name = "배송 > 배차현황 > 배송전달사항", description = "배송전달사항")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/tm/inplanMessage")
public class TmInplanMessageController {

	private final TmInplanMsgService tmInplanMsgService;

	/**
	 * @description : 배송전달사항 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.07 ParkJinWoo 생성
	 */
	@Operation(summary = "납품서출력로그(관리자)", description = "납품서츨력로그 (관리자)")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<Object> getMasterList(@Valid @RequestBody TmInplanMsgListReqDto reqDto) {
		return ApiResult.createResult(tmInplanMsgService.getMasterList(reqDto));
	}
	
	/**
	 * @description : 배송전달사항 저장 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.07 ParkJinWoo 생성
	 */
	@Operation(summary = "납품서출력로그(관리자)", description = "납품서츨력로그 (관리자)")
	@PostMapping(value = "/v1.0/saveConfirm")
	public ApiResult<Object> saveConfirm(@Valid @RequestBody TmInplanMsgListReqDto reqDto) {
		return ApiResult.createResult(tmInplanMsgService.saveConfirm(reqDto));
	}
}