package cjfw.wms.wd.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.wd.dto.WdDeliveryLabelDailyReqDto;
import cjfw.wms.wd.dto.WdDeliveryLabelDailyResPrintDto;
import cjfw.wms.wd.dto.WdDeliveryLabelDailyResTab1Dto;
import cjfw.wms.wd.dto.WdDeliveryLabelDailyResTab2Dto;
import cjfw.wms.wd.service.WdDeliveryLabelDailyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net)
 * @date : 2026.02.19
 * @description : 일배분류서출력 Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2026.02.19 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Tag(name = "deliveryLabelDaily", description = "일배분류서출력")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/wd/deliveryLabelDaily")
public class WdDeliveryLabelDailyController {

	private final WdDeliveryLabelDailyService wdDeliveryLabelDailyService;

	/**
	 * @description : 일배분류서출력-일배 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2026.02.19 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "일배분류서출력-일배 목록 조회", description = "일배분류서출력-일배 목록 조회")
	@PostMapping(value = "/v1.0/getTab1MasterList")
	public ApiResult<List<WdDeliveryLabelDailyResTab1Dto>> getTab1MasterList(@RequestBody WdDeliveryLabelDailyReqDto dto) {


		return ApiResult.createResult(wdDeliveryLabelDailyService.getTab1MasterList(dto));
	}
	
	/**
	 * @description : 일배분류서출력-광역일배 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2026.02.19 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "일배분류서출력-광역일배 목록 조회", description = "일배분류서출력-광역일배 목록 조회")
	@PostMapping(value = "/v1.0/getTab2MasterList")
	public ApiResult<List<WdDeliveryLabelDailyResTab2Dto>> getTab2MasterList(@RequestBody WdDeliveryLabelDailyReqDto dto) {


		return ApiResult.createResult(wdDeliveryLabelDailyService.getTab2MasterList(dto));
	}

	/**
	 * @description : 일배분류서출력-일배 출력 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2026.02.19 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "일배분류서출력-일배 출력 조회", description = "일배분류서출력-일배 출력 조회")
	@PostMapping(value = "/v1.0/getTab1PrintList")
	public ApiResult<WdDeliveryLabelDailyResPrintDto> getTab1PrintList(@RequestBody WdDeliveryLabelDailyReqDto dto) {


		return ApiResult.createResult(wdDeliveryLabelDailyService.getTab1PrintList(dto));
	}
	
	/**
	 * @description : 일배분류서출력-광역일배 출력 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2026.02.19 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "일배분류서출력-광역일배 출력 조회", description = "일배분류서출력-광역일배 출력 조회")
	@PostMapping(value = "/v1.0/getTab2PrintList")
	public ApiResult<WdDeliveryLabelDailyResPrintDto> getTab2PrintList(@RequestBody WdDeliveryLabelDailyReqDto dto) {
		
		
		return ApiResult.createResult(wdDeliveryLabelDailyService.getTab2PrintList(dto));
	}
	
}