package cjfw.wms.tm.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.tm.dto.TmFuelEfficiencyReqDto;
import cjfw.wms.tm.service.TmFuelEfficiencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.09.10 
 * @description : 연비관리 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.10 ParkJinWoo 생성
 */
@Tag(name = "정산 > 운송비정산 >연비관리", description = "연비관리")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/tm/fuelEfficiency")
public class TmFuelEfficiencyController {

	private final TmFuelEfficiencyService tmFuelEfficiencyService;


	/**
	 * @description : 연비관리 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.10 ParkJinWoo 생성
	 */
	@Operation(summary = "연비관리조회", description = "연비관리조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<Object> getMasterList(@Valid @RequestBody TmFuelEfficiencyReqDto reqDto) {
		return ApiResult.createResult(tmFuelEfficiencyService.getMasterList(reqDto));
	}
	

	/**
	 * @description : 연비관리 수정 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.10 ParkJinWoo 생성
	 */
	@Operation(summary = "연비관리수정", description = "연비관리수정")
	@PostMapping(value = "/v1.0/saveConfirm")
	public ApiResult<Object> saveConfirm(@Valid @RequestBody TmFuelEfficiencyReqDto reqDto) {
		return ApiResult.createResult(tmFuelEfficiencyService.saveConfirm(reqDto));
	}
}