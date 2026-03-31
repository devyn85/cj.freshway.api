package cjfw.wms.om.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.om.dto.OmPurchaseCtlReqDto;
import cjfw.wms.om.service.OmPurchaseCtlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.11.18
 * @description : 주문 > 주문등록 > 저장품자동발주관제 기능을 구현한 Controller Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.18        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Tag(name = "OmPurchaseCtl", description = "저장품자동발주관제")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping({"api/om/purchaseCtl", "ltx/om/purchaseCtl"})
public class OmPurchaseCtlController {

	private final OmPurchaseCtlService omPurchaseCtlService;
	
	/**
	 * @description : 저장품자동발주관제 탭1 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.18        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "저장품자동발주관제 탭1 목록 조회", description = "저장품자동발주관제 탭1 목록 조회")
	@GetMapping(value = "/v1.0/getTab1MasterList")
	public ApiResult<List<Map<String, Object>>> getTab1MasterList(@Valid OmPurchaseCtlReqDto omPurchaseCtlReqDto) {
		return ApiResult.createResult(omPurchaseCtlService.getTab1MasterList(omPurchaseCtlReqDto));
	}

	/**
	 * @description : 저장품자동발주관제 탭2 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.18        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "저장품자동발주관제 탭2 목록 조회", description = "저장품자동발주관제 탭2 목록 조회")
	@GetMapping(value = "/v1.0/getTab2MasterList")
	public ApiResult<List<Map<String, Object>>> getTab2MasterList(@Valid OmPurchaseCtlReqDto omPurchaseCtlReqDto) {
		return ApiResult.createResult(omPurchaseCtlService.getTab2MasterList(omPurchaseCtlReqDto));
	}
	
	/**
	 * @description : 저장품자동발주관제 탭2 상세 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.18        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "저장품자동발주관제 탭2 상세 조회", description = "저장품자동발주관제 탭2 상세 조회")
	@GetMapping(value = "/v1.0/getTab2DetailList")
	public ApiResult<List<Map<String, Object>>> getTab2DetailList(@Valid OmPurchaseCtlReqDto omPurchaseCtlReqDto) {
		return ApiResult.createResult(omPurchaseCtlService.getTab2DetailList(omPurchaseCtlReqDto));
	}
	
	/**
	 * @description : 저장품자동발주관제 탭2 상세2 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.18        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "저장품자동발주관제 탭2 상세2 조회", description = "저장품자동발주관제 탭2 상세2 조회")
	@GetMapping(value = "/v1.0/getTab2Detail2List")
	public ApiResult<List<Map<String, Object>>> getTab2Detail2List(@Valid OmPurchaseCtlReqDto omPurchaseCtlReqDto) {
		return ApiResult.createResult(omPurchaseCtlService.getTab2Detail2List(omPurchaseCtlReqDto));
	}
	
	/**
	 * @description : 저장품자동발주관제 탭3 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.18        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "저장품자동발주관제 탭3 목록 조회", description = "저장품자동발주관제 탭3 목록 조회")
	@GetMapping(value = "/v1.0/getTab3MasterList")
	public ApiResult<List<Map<String, Object>>> getTab3MasterList(@Valid OmPurchaseCtlReqDto omPurchaseCtlReqDto) {
		return ApiResult.createResult(omPurchaseCtlService.getTab3MasterList(omPurchaseCtlReqDto));
	}
	
	/**
	 * @description : 저장품자동발주관제 탭3 상세 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.18        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "저장품자동발주관제 탭3 상세 조회", description = "저장품자동발주관제 탭3 상세 조회")
	@GetMapping(value = "/v1.0/getTab3DetailList")
	public ApiResult<List<Map<String, Object>>> getTab3DetailList(@Valid OmPurchaseCtlReqDto omPurchaseCtlReqDto) {
		return ApiResult.createResult(omPurchaseCtlService.getTab3DetailList(omPurchaseCtlReqDto));
	}
	
	/**
	 * @description : 저장품자동발주관제 탭3 상세2 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.18        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "저장품자동발주관제 탭3 상세2 조회", description = "저장품자동발주관제 탭3 상세2 조회")
	@GetMapping(value = "/v1.0/getTab3Detail2List")
	public ApiResult<List<Map<String, Object>>> getTab3Detail2List(@Valid OmPurchaseCtlReqDto omPurchaseCtlReqDto) {
		return ApiResult.createResult(omPurchaseCtlService.getTab3Detail2List(omPurchaseCtlReqDto));
	}
}