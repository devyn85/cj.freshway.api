package cjfw.wms.tm.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.tm.dto.TmCalendarListReqDto;
import cjfw.wms.tm.service.TmCalendarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.08.22 
 * @description : 휴일관리 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.22 ParkJinWoo 생성
 */
@Tag(name = "정산 > 운송비정산 >휴일관리", description = "휴일관리")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/tm/calendar")
public class TmCalendarController {

	private final TmCalendarService tmCalendarService;


	/**
	 * @description : 휴일관리 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.22 ParkJinWoo 생성
	 */
	@Operation(summary = "배차마스터체크결과", description = "배차마스터체크결과")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<Object> getMasterList(@Valid @RequestBody TmCalendarListReqDto reqDto) {
		return ApiResult.createResult(tmCalendarService.getMasterList(reqDto));
	}
	

	/**
	 * @description : 휴일관리 수정 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.22 ParkJinWoo 생성
	 */
	@Operation(summary = "납품서출력로그(관리자)", description = "납품서츨력로그 (관리자)")
	@PostMapping(value = "/v1.0/saveConfirm")
	public ApiResult<Object> saveConfirm(@Valid @RequestBody TmCalendarListReqDto reqDto) {
		return ApiResult.createResult(tmCalendarService.saveConfirm(reqDto));
	}
	
	/**
	 * @description : 휴일관리 달력생성 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.24 ParkYoSep 생성
	 */
	@Operation(summary = "달력 생성", description = "달력 생성")
	@PostMapping(value = "/v1.0/saveCalendar")
	public ApiResult<String> saveCalendar(@Valid @RequestBody TmCalendarListReqDto reqDto) {
		return ApiResult.createResult(tmCalendarService.saveCalendar(reqDto));
	}
}