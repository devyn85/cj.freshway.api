package cjfw.wms.tm.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.tm.dto.TmDeliveryIndicatorReqDto;
import cjfw.wms.tm.service.TmDeliveryIndicatorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2026.01.20 
 * @description : 출도착지표 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2026.01.20 ParkJinWoo 생성
 */
@Tag(name = "정산 > 운송비정산 >유가관리", description = "유가관리")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/tm/deliveryIndicator")
public class TmDeliveryIndicatorController {

	private final TmDeliveryIndicatorService tmDeliveryIndicatorService;


	/**
	 * @description : 출도착지표 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.05 ParkJinWoo 생성
	 */
	@Operation(summary = "출도착지표 조회", description = "출도착지표 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<Object> getMasterList(@Valid @RequestBody TmDeliveryIndicatorReqDto reqDto) {
		return ApiResult.createResult(tmDeliveryIndicatorService.getMasterList(reqDto));
	}
	


}