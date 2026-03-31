package cjfw.wms.wd.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.wd.dto.WdInvoiceTotalPrintResDto;
import cjfw.wms.wd.dto.WdInvoiceTotalReqDto;
import cjfw.wms.wd.dto.WdInvoiceTotalResDto;
import cjfw.wms.wd.service.WdInvoiceTotalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 성상수 (kduimux@cj.net) 생성
 * @date : 2025.05.16
 * @description : 통합납품서출력 Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.05.16 성상수 (kduimux@cj.net) 생성 </pre>
 */
@Tag(name = "WdInvoiceTotal", description = "통합납품서출력")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/wd/wdInvoiceTotal")
public class WdInvoiceTotalController {

	private final WdInvoiceTotalService wdInvoiceTotalService;

	/**
	 * @description : 차량 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.12 성상수 (kduimux@cj.net) 생성 </pre>
	 */
	@Operation(summary = "통합납품서출력 목록 조회", description = "통합납품서출력 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<WdInvoiceTotalResDto>> getMasterList(@RequestBody WdInvoiceTotalReqDto dto) {
		return ApiResult.createResult(wdInvoiceTotalService.getMasterList(dto));
	}

	/**
	 * @description : 통합납품서출력 상세목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.12 성상수 (kduimux@cj.net) 생성 </pre>
	 */
	@Operation(summary = "통합납품서출력 상세목록 조회", description = "통합납품서출력 상세목록 조회")
	@GetMapping(value = "/v1.0/getDetailList")
	public ApiResult<List<WdInvoiceTotalResDto>> getDetailList(WdInvoiceTotalReqDto dto) {
		return ApiResult.createResult(wdInvoiceTotalService.getDetailList(dto));
	}
	
	/**
	 * @description : 통합납품서출력 목록 출력
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.12 성상수 (kduimux@cj.net) 생성 </pre>
	 */
	@Operation(summary = "통합납품서출력 출력 데이터 생성", description = "통합납품서출력 출력 데이터 생성")
	@PostMapping(value = "/v1.0/printMasterList")
	public ApiResult<WdInvoiceTotalPrintResDto> printMasterList(@RequestBody WdInvoiceTotalReqDto dto) throws Exception {
		return ApiResult.createResult(wdInvoiceTotalService.printMasterList(dto));
	}	
	
	/**
	 * @description : 통합납품서출력 목록 출력
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.12 성상수 (kduimux@cj.net) 생성 </pre>
	 */
	@Operation(summary = "통합납품서출력 출력 데이터 생성-거래처별", description = "통합납품서출력 출력 데이터 생성-거래처별")
	@PostMapping(value = "/v1.0/printDetailList")
	public ApiResult<WdInvoiceTotalPrintResDto> printDetailList(@RequestBody WdInvoiceTotalReqDto dto) throws Exception {
		return ApiResult.createResult(wdInvoiceTotalService.printDetailList(dto));
	}	
}