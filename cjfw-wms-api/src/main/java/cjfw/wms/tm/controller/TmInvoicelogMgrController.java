package cjfw.wms.tm.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.tm.dto.TmInvoicelogMgrReqDto;
import cjfw.wms.tm.service.TmInvoicelogMgrService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.06.30 
 * @description : 납품서 츨력로그 (관리자) 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.30 ParkJinWoo 생성
 */
@Tag(name = "출고 > 출차지시 > 납품서출력로그(관리자)", description = "납품서 츨력로그 (관리자)")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/tm/invoicelogMgr")
public class TmInvoicelogMgrController {

	private final TmInvoicelogMgrService tmInvoicelogMgrService;


	/**
	 * @description :납품서 츨력로그 (관리자)조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.30 ParkJinWoo 생성
	 */
	@Operation(summary = "납품서출력로그(관리자)", description = "납품서츨력로그 (관리자)")
	@PostMapping(value = "/v1.0/getInvoiceLogList")
	public ApiResult<Object> getInvoiceLogList(@Valid @RequestBody TmInvoicelogMgrReqDto tmInvoicelogMgrReqDto) {
		return ApiResult.createResult(tmInvoicelogMgrService.getInvoiceLogList(tmInvoicelogMgrReqDto));
	}

}