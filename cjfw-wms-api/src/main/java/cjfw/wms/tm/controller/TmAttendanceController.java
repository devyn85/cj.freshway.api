package cjfw.wms.tm.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.tm.dto.TmAttendanceListReqDto;
import cjfw.wms.tm.service.TmAttendanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.09.16 
 * @description : 근태관리 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.16 ParkJinWoo 생성
 */
@Tag(name = "정산 > 운송비정산 >근태관리", description = "근태관리")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/tm/attendance")
public class TmAttendanceController {

	private final TmAttendanceService tmAttendanceService;


	/**
	 * @description : 근태관리 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.16 ParkJinWoo 생성
	 */
	@Operation(summary = "근태관리 조회", description = "근태관리 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<Object> getMasterList(@Valid @RequestBody TmAttendanceListReqDto reqDto) {
		return ApiResult.createResult(tmAttendanceService.getMasterList(reqDto));
	}
	

	/**
	 * @description : 근태관리 수정 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.16 ParkJinWoo 생성
	 */
	@Operation(summary = "근태관리 수정", description = "근태관리 수정")
	@PostMapping(value = "/v1.0/saveConfirm")
	public ApiResult<Object> saveConfirm(@Valid @RequestBody TmAttendanceListReqDto reqDto) {
		return ApiResult.createResult(tmAttendanceService.saveConfirm(reqDto));
	}
}