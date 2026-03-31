package cjfw.wms.om.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.om.dto.OmPurchaseCheckReqDto;
import cjfw.wms.om.dto.OmPurchaseCheckResDto;
import cjfw.wms.om.service.OmPurchaseCheckService;
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
 * @date        : 2025.07.11
 * @description : 주문 > 주문현황 > 월간주간발주량체크PO 조회 기능을 구현한 Controller Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.11        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Tag(name = "OmPurchaseCheckPO", description = "월간주간발주량체크PO")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/om/purchaseCheck")
public class OmPurchaseCheckController {

	private final OmPurchaseCheckService omPurchaseCheckService;

	/**
	 * @description : 월간주간발주량체크PO 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.10        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "월간주간발주량체크PO 상세 목록 조회", description = "월간주간발주량체크PO 목록 조회")
	@GetMapping(value = "/v1.0/getMasterListPO")
	public ApiResult<List<OmPurchaseCheckResDto>> getMasterList(@Valid OmPurchaseCheckReqDto omPurchaseCheckReqDto) {
		return ApiResult.createResult(omPurchaseCheckService.getMasterListPO(omPurchaseCheckReqDto));
	}
	
	/**
	 * @description : 월간주간발주량체크PO 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.10        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "월간주간발주량체크PO 상세 목록 조회", description = "월간주간발주량체크PO 목록 조회")
	@GetMapping(value = "/v1.0/getMasterListSTO")
	public ApiResult<List<OmPurchaseCheckResDto>> getMasterListSTO(@Valid OmPurchaseCheckReqDto omPurchaseCheckReqDto) {
		return ApiResult.createResult(omPurchaseCheckService.getMasterListSTO(omPurchaseCheckReqDto));
	}
}