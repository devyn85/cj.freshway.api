package cjfw.wms.rt.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.rt.dto.RtReceiptConfirmExdcMasterReqDto;
import cjfw.wms.rt.dto.RtReceiptConfirmExdcMasterSubReqDto;
import cjfw.wms.rt.service.RtReceiptConfirmExdcService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.07.18 
 * @description : 외부비축반품확정 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.18 ParkJinWoo 생성
 */
@Tag(name = "반품 > 반출처리 > 외부비축반품확정", description = "외부비축반품확정")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/rt/receiptConfirmExdc")
public class RtReceiptConfirmExdcController {

	private final RtReceiptConfirmExdcService rtReceiptConfirmExdcService;

	
	/**
	 * @description : 외부비축반품확정 입고내역 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.18 ParkJinWoo 생성
	 */
	@Operation(summary = "외부비축반품확정 입고내역 조회", description = "외부비축반품확정 입고내역 조회")
	@PostMapping(value = "/v1.0/getDataHeaderList")
	public ApiResult<Object> getDataHeaderList(@Valid @RequestBody RtReceiptConfirmExdcMasterReqDto dto) {
		return ApiResult.createResult(rtReceiptConfirmExdcService.getDataHeaderList(dto));
	}
	
	/**
	 * @description : 외부비축반품확정 출고내역 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.18 ParkJinWoo 생성
	 *
	 **/
	@Operation(summary = "외부비축반품확정 출고내역 조회", description = "외부비축반품확정 출고내역 조회")
	@PostMapping(value = "/v1.0/getDataHeaderSubList")
	public  ApiResult<Object> getDataHeaderSubList(@Valid @RequestBody RtReceiptConfirmExdcMasterSubReqDto dto) {
		return ApiResult.createResult(rtReceiptConfirmExdcService.getDataHeaderSubList(dto));
	}
	
	
	/**
	 * @description : 외부비축반품확정 입고내역 저장 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.18 ParkJinWoo 생성
	 *
	 **/
	@Operation(summary = "외부비축반품확정 입고내역 저장", description = "외부비축반품확정 입고내역 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public  ApiResult<Object> saveRtReceiptConfirmExdcHeader(@Valid @RequestBody RtReceiptConfirmExdcMasterReqDto dto) {
		return ApiResult.createResult(rtReceiptConfirmExdcService.saveRtReceiptConfirmExdcHeader(dto));
	}

	/**
	 * @description : 외부비축반품확정 출고내역 저장 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.18 ParkJinWoo 생성
	 *
	 **/
	@Operation(summary = "외부비축반품확정 출고내역 저장", description = "외부비축반품확정 출고내역 저장")
	@PostMapping(value = "/v1.0/saveMasterList1")
	public  ApiResult<Object> saveRtReceiptConfirmExdcSub(@Valid @RequestBody RtReceiptConfirmExdcMasterSubReqDto dto) {
		return ApiResult.createResult(rtReceiptConfirmExdcService.saveRtReceiptConfirmExdcSub(dto));
	}
	
}