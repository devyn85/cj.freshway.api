package cjfw.wms.om.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.om.dto.OmPurchaseModifyReqDto;
import cjfw.wms.om.dto.OmPurchaseModifyResDto;
import cjfw.wms.om.service.OmPurchaseModifyService;
import cjfw.wms.om.service.OmPurchaseStorageAutoTotalService;
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
 * @date        : 2025.09.12
 * @description : 주문 > 주문등록 > 저장품발주삭제 기능을 구현한 Controller Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.12        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Tag(name = "OmPurchaseModifyPO", description = "월간주간발주량체크PO")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping({"api/om/purchaseModify", "ltx/om/purchaseModify"})
public class OmPurchaseModifyController {

	private final OmPurchaseModifyService omPurchaseModifyService;
	
	private final OmPurchaseStorageAutoTotalService omPurchaseStorageAutoTotalService;

	/**
	 * @description : 저장품발주삭제PO 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.12        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "저장품발주삭제PO 목록 조회", description = "저장품발주삭제PO 목록 조회")
	@GetMapping(value = "/v1.0/getMasterListPO")
	public ApiResult<List<OmPurchaseModifyResDto>> getMasterListPO(@Valid OmPurchaseModifyReqDto omPurchaseModifyReqDto) {
		return ApiResult.createResult(omPurchaseModifyService.getMasterListPO(omPurchaseModifyReqDto));
	}
	
	/**
	 * @description : 저장품발주삭제PO 상세 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.12        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "저장품발주삭제PO 상세 목록 조회", description = "저장품발주삭제PO 상세 목록 조회")
	@GetMapping(value = "/v1.0/getDetailListPO")
	public ApiResult<List<OmPurchaseModifyResDto>> getDetailListPO(@Valid OmPurchaseModifyReqDto omPurchaseModifyReqDto) {
		return ApiResult.createResult(omPurchaseModifyService.getDetailListPO(omPurchaseModifyReqDto));
	}
	
	/**
	 * @description : 저장품발주삭제STO 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.12        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "저장품발주삭제STO 목록 조회", description = "저장품발주삭제STO 목록 조회")
	@GetMapping(value = "/v1.0/getMasterListSTO")
	public ApiResult<List<OmPurchaseModifyResDto>> getMasterListSTO(@Valid OmPurchaseModifyReqDto omPurchaseModifyReqDto) {
		return ApiResult.createResult(omPurchaseModifyService.getMasterListSTO(omPurchaseModifyReqDto));
	}
	
	/**
	 * @description : 저장품발주삭제STO 상세 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.12        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "저장품발주삭제STO 상세 목록 조회", description = "저장품발주삭제STO 상세 목록 조회")
	@GetMapping(value = "/v1.0/getDetailListSTO")
	public ApiResult<List<OmPurchaseModifyResDto>> getDetailListSTO(@Valid OmPurchaseModifyReqDto omPurchaseModifyReqDto) {
		return ApiResult.createResult(omPurchaseModifyService.getDetailListSTO(omPurchaseModifyReqDto));
	}
	
	/**
	 * @throws Exception 
	 * @description : 저장품발주삭제(PO) 목록 삭제
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.16        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "저장품발주삭제PO 삭제", description = "저장품발주삭제PO 삭제")
	@PostMapping(value = "/v1.0/deleteMasterPO")
	public ApiResult<Object> deleteMasterPO(@RequestBody OmPurchaseModifyReqDto dto) throws Exception {
		return ApiResult.createResult(omPurchaseModifyService.deleteMasterPO(dto));
	}
	
	/**
	 * @throws Exception 
	 * @description : 저장품발주삭제(PO) 목록 삭제
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.16        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "저장품발주삭제STO 삭제", description = "저장품발주삭제PO 삭제")
	@PostMapping(value = "/v1.0/deleteMasterSTO")
	public ApiResult<Object> deleteMasterSTO(@RequestBody OmPurchaseModifyReqDto dto) throws Exception {
		return ApiResult.createResult(omPurchaseModifyService.deleteMasterSTO(dto));
	}
	
	/**
	 * @throws Exception 
	 * @description : 저장품발주삭제(PO) 목록 재발주
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.16        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "저장품발주삭제PO 재발주", description = "저장품발주삭제PO 재발주")
	@PostMapping(value = "/v1.0/reorderMasterPO")
	public ApiResult<Object> reorderMasterPO(@RequestBody OmPurchaseModifyReqDto dto) throws Exception {
		return ApiResult.createResult(omPurchaseStorageAutoTotalService.reorderMasterPO(dto));
	}
	
	/**
	 * @throws Exception 
	 * @description : 저장품발주삭제(STO) 목록 재발주
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.16        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "저장품발주삭제STO 재발주", description = "저장품발주삭제STO 재발주")
	@PostMapping(value = "/v1.0/reorderMasterSTO")
	public ApiResult<Object> reorderMasterSTO(@RequestBody OmPurchaseModifyReqDto dto) throws Exception {
		return ApiResult.createResult(omPurchaseStorageAutoTotalService.reorderMasterSTO(dto));
	}
	
	/**
	 * @throws Exception 
	 * @description : 저장품발주삭제(STO) 목록 외부창고 재발주
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.16        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "저장품발주삭제STO 외부창고 재발주", description = "저장품발주삭제STO 외부창고 재발주")
	@PostMapping(value = "/v1.0/reorderMasterOutSTO")
	public ApiResult<Object> reorderMasterOutSTO(@RequestBody OmPurchaseModifyReqDto dto) throws Exception {
		return ApiResult.createResult(omPurchaseStorageAutoTotalService.reorderMasterOutSTO(dto));
	}
}