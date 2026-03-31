package cjfw.wms.om.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.om.dto.OmPurchaseStorageAutoTotalDetailLeftResDto;
import cjfw.wms.om.dto.OmPurchaseStorageAutoTotalDetailOrderResDto;
import cjfw.wms.om.dto.OmPurchaseStorageAutoTotalDetailRightResDto;
import cjfw.wms.om.dto.OmPurchaseStorageAutoTotalReqDto;
import cjfw.wms.om.dto.OmPurchaseStorageAutoTotalResDto;
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
 * @date        : 2025.10.14
 * @description : 주문 > 주문등록 > 저장품자동발주 기능을 구현한 Controller Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.14        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Tag(name = "OmPurchaseStorageAutoTotal", description = "저장품자동발주")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping({"api/om/purchaseStorageAutoTotal", "ltx/om/purchaseStorageAutoTotal"})
public class OmPurchaseStorageAutoTotalController {

	private final OmPurchaseStorageAutoTotalService omPurchaseStorageAutoTotalService;

	/**
	 * @description : 저장품자동발주 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.14        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "저장품자동발주 목록 조회", description = "저장품자동발주 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<OmPurchaseStorageAutoTotalResDto>> getMasterList(@RequestBody OmPurchaseStorageAutoTotalReqDto omPurchaseStorageAutoTotalReqDto) {
		return ApiResult.createResult(omPurchaseStorageAutoTotalService.getMasterList(omPurchaseStorageAutoTotalReqDto));
	}
	
	/**
	 * @description : 저장품자동발주 상세 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.14        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "저장품자동발주 상세 목록 조회", description = "저장품자동발주 상세 목록 조회")
	@PostMapping(value = "/v1.0/getDetailLeftInfo")
	public ApiResult<List<OmPurchaseStorageAutoTotalDetailLeftResDto>> getDetailLeftInfo(@RequestBody OmPurchaseStorageAutoTotalReqDto omPurchaseStorageAutoTotalReqDto) {
		return ApiResult.createResult(omPurchaseStorageAutoTotalService.getDetailLeftInfo(omPurchaseStorageAutoTotalReqDto));
	}
	
	/**
	 * @description : 저장품자동발주 상세 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.14        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "저장품자동발주 상세 목록 조회", description = "저장품자동발주 상세 목록 조회")
	@PostMapping(value = "/v1.0/getDetailOrderInfo")
	public ApiResult<List<OmPurchaseStorageAutoTotalDetailOrderResDto>> getDetailOrderInfo(@RequestBody OmPurchaseStorageAutoTotalReqDto omPurchaseStorageAutoTotalReqDto) {
		return ApiResult.createResult(omPurchaseStorageAutoTotalService.getDetailOrderInfo(omPurchaseStorageAutoTotalReqDto));
	}
	
	/**
	 * @description : 저장품자동발주 상세 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.14        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "저장품자동발주 상세 목록 조회", description = "저장품자동발주 상세 목록 조회")
	@PostMapping(value = "/v1.0/getDetailRightInfo")
	public ApiResult<List<OmPurchaseStorageAutoTotalDetailRightResDto>> getDetailRightInfo(@RequestBody OmPurchaseStorageAutoTotalReqDto omPurchaseStorageAutoTotalReqDto) {
		return ApiResult.createResult(omPurchaseStorageAutoTotalService.getDetailRightInfo(omPurchaseStorageAutoTotalReqDto));
	}
	
	/**
	 * @description : 입출고 당일이후 집계 최종 생성시간 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.14        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "입출고 당일이후 집계 최종 생성시간 조회", description = "입출고 당일이후 집계 최종 생성시간 조회")
	@GetMapping(value = "/v1.0/getLastCreation")
	public ApiResult<OmPurchaseStorageAutoTotalResDto> getLastCreation(@Valid OmPurchaseStorageAutoTotalReqDto omPurchaseStorageAutoTotalReqDto) {
		return ApiResult.createResult(omPurchaseStorageAutoTotalService.getLastCreation(omPurchaseStorageAutoTotalReqDto));
	}
	
	/**
	 * @description : 수발주 입출고 내역 갱신
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.14        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "수발주 입출고 내역 갱신", description = "수발주 입출고 내역 갱신")
	@GetMapping(value = "/v1.0/recreationPurchaseTotal")
	public ApiResult<Object> recreationPurchaseTotal(@Valid OmPurchaseStorageAutoTotalReqDto omPurchaseStorageAutoTotalReqDto) {
		return ApiResult.createResult(omPurchaseStorageAutoTotalService.recreationPurchaseTotal(omPurchaseStorageAutoTotalReqDto));
	}
		
	/**
	 * @throws Exception 
	 * @description : PO 발주 처리
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.14        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "PO 발주 처리", description = "PO 발주 처리")
	@PostMapping(value = "/v1.0/savePurchase")
	public ApiResult<Object> savePurchase(@RequestBody OmPurchaseStorageAutoTotalReqDto dto) throws Exception {
		return ApiResult.createResult(omPurchaseStorageAutoTotalService.savePurchase(dto));
	}
	
	/**
	 * @throws Exception 
	 * @description : 이체(STO) 발주 처리
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.14        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "이체(STO) 발주 처리", description = "이체(STO) 발주 처리")
	@PostMapping(value = "/v1.0/savePurchaseSTO")
	public ApiResult<Object> savePurchaseSTO(@RequestBody OmPurchaseStorageAutoTotalReqDto dto) throws Exception {
		return ApiResult.createResult(omPurchaseStorageAutoTotalService.savePurchaseSTO(dto));
	}
	
	/**
	 * @throws Exception 
	 * @description : 외부창고 이체(STO) 발주 처리
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.14        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "외부창고 이체(STO) 발주 처리", description = "외부창고 이체(STO) 발주 처리")
	@PostMapping(value = "/v1.0/savePurchaseOutSTO")
	public ApiResult<Object> savePurchaseOutSTO(@RequestBody OmPurchaseStorageAutoTotalReqDto dto) throws Exception {
		return ApiResult.createResult(omPurchaseStorageAutoTotalService.savePurchaseOutSTO(dto));
	}
	
	/**
	 * @throws Exception 
	 * @description : 발주보류 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.14        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "발주보류 저장", description = "발주보류 저장")
	@PostMapping(value = "/v1.0/savePoHoldYn")
	public ApiResult<Object> savePoHoldYn(@RequestBody OmPurchaseStorageAutoTotalReqDto dto) throws Exception {
		return ApiResult.createResult(omPurchaseStorageAutoTotalService.savePoHoldYn(dto));
	}
	
	/**
	 * @throws Exception 
	 * @description : 수급 이미지 호출 로그 생성
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.14        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "수급 이미지 호출 로그 생성", description = "수급 이미지 호출 로그 생성")
	@PostMapping(value = "/v1.0/saveSuppImgLog")
	public ApiResult<Object> saveSuppImgLog(@RequestBody OmPurchaseStorageAutoTotalReqDto dto) throws Exception {		
		return ApiResult.createResult(omPurchaseStorageAutoTotalService.saveSuppImgLog(dto));
	}
	
	/**
	 * @description : 외부창고 이메일 전송
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2026.02.23        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "외부창고 이메일 전송", description = "외부창고 이메일 전송")
	@PostMapping(value = "/v1.0/saveEmail")
	public ApiResult<OmPurchaseStorageAutoTotalReqDto> saveEmail(@RequestBody @Valid OmPurchaseStorageAutoTotalReqDto dto) throws Exception {
		return ApiResult.createResult(omPurchaseStorageAutoTotalService.saveEmail(dto));
	}
}