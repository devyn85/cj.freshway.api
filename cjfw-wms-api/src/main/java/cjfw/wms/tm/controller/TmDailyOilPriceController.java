package cjfw.wms.tm.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.tm.dto.TmDailyOilPriceListReqDto;
import cjfw.wms.tm.service.TmDailyOilPriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.09.05
 * @description : 유가정보 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.05 ParkJinWoo 생성
 */
@Tag(name = "정산 > 운송비정산 >유가관리", description = "유가관리")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/tm/dailyOilPrice")
public class TmDailyOilPriceController {

	private final TmDailyOilPriceService tmDailyOilPriceService;


	/**
	 * @description : 유가정보 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.05 ParkJinWoo 생성
	 */
	@Operation(summary = "배차마스터체크결과", description = "배차마스터체크결과")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<Object> getMasterList(@Valid @RequestBody TmDailyOilPriceListReqDto reqDto) {
		return ApiResult.createResult(tmDailyOilPriceService.getMasterList(reqDto));
	}
	

	/**
	 * @description : 유가 저장 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.05 ParkJinWoo 생성
	 */
	@Operation(summary = "납품서출력로그(관리자)", description = "납품서츨력로그 (관리자)")
	@PostMapping(value = "/v1.0/saveConfirm")
	public ApiResult<Object> saveConfirm(@Valid @RequestBody TmDailyOilPriceListReqDto reqDto) {
		return ApiResult.createResult(tmDailyOilPriceService.saveConfirm(reqDto));
	}
}