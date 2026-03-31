package cjfw.wms.wd.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.wd.dto.WdBeforeOrderAdjustRequestReqDto;
import cjfw.wms.wd.dto.WdBeforeOrderAdjustRequestResDto;
import cjfw.wms.wd.service.WdBeforeOrderAdjustRequestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.09.25
 * @description : 출고 > 출고작업 > 사전주문 조정의뢰 기능을 구현한 Controller Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.25        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Tag(name = "WdBeforeOrderAdjustRequest", description = "사전주문 조정의뢰")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/wd/beforeOrderAdjustRequest")
public class WdBeforeOrderAdjustRequestController {

	private final WdBeforeOrderAdjustRequestService wdBeforeOrderAdjustRequestService;

	/**
	 * @description : 사전주문 조정의뢰 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.25        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "사전주문 조정의뢰 조회", description = "피킹작업지시-조회생성 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<WdBeforeOrderAdjustRequestResDto>> getTab1MasterList(@RequestBody WdBeforeOrderAdjustRequestReqDto dto) {
		return ApiResult.createResult(wdBeforeOrderAdjustRequestService.getMasterList(dto));
	}
	
	/**
	 * @throws Exception
	 * @description : 사전주문 조정의뢰를 생성
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.25        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "사전주문조정의뢰를 생성", description = "사전주문조정의뢰를 생성")
	@PostMapping(value = "/v1.0/saveOrderRequest")
	public ApiResult<String> saveOrderRequest(@RequestBody WdBeforeOrderAdjustRequestReqDto dto) throws Exception {
		return ApiResult.createResult(wdBeforeOrderAdjustRequestService.saveOrderRequest(dto));
	}


}