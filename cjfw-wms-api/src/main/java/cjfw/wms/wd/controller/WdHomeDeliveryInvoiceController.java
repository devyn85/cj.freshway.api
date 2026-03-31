package cjfw.wms.wd.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.wd.dto.WdHomeDeliveryInvoicePopupDeliveryStatusReqDto;
import cjfw.wms.wd.dto.WdHomeDeliveryInvoicePopupDeliveryStatusResDto;
import cjfw.wms.wd.dto.WdHomeDeliveryInvoicePopupDocumentModifyResDto;
import cjfw.wms.wd.service.WdHomeDeliveryInvoiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.05.21 
 * @description : 택배송장 발행서비스 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.21 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Tag(name = "WdHomeDeliveryInvoice", description = "택배송장 발행서비스")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/wd/homeDeliveryInvoice")
public class WdHomeDeliveryInvoiceController {

	private final WdHomeDeliveryInvoiceService WdHomeDeliveryInvoiceService;

	/**
	 * @description : 출고진행현황 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.12 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "출고진행현황 목록 조회", description = "출고진행현황 목록 조회")
	@PostMapping(value = "/v1.0/getDeliveryStatus")
	public ApiResult<List<WdHomeDeliveryInvoicePopupDeliveryStatusResDto>> getDeliveryStatus(@RequestBody WdHomeDeliveryInvoicePopupDeliveryStatusReqDto wdHomeDeliveryInvoicePopupDeliveryStatusReqDto, Page page) {
			
				
		return ApiResult.createResult(WdHomeDeliveryInvoiceService.getDeliveryStatus(wdHomeDeliveryInvoicePopupDeliveryStatusReqDto, page));
	}
	
	
	/**
	 * @description : 주문변경내역 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.13 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "주문변경내역 목록 조회", description = "주문변경내역 목록 조회")
	@PostMapping(value = "/v1.0/getDocumentModify")
	public ApiResult<List<WdHomeDeliveryInvoicePopupDocumentModifyResDto>> getDocumentModifyDetailForDocno(@RequestBody WdHomeDeliveryInvoicePopupDeliveryStatusReqDto wdHomeDeliveryInvoicePopupDeliveryStatusReqDto, Page page) {
		
		
		return ApiResult.createResult(WdHomeDeliveryInvoiceService.getDocumentModifyDetailForDocno(wdHomeDeliveryInvoicePopupDeliveryStatusReqDto, page));
	}
	
	
	public static boolean isNull(String str) {		 
		return str == null || str.trim().isEmpty();
	}

}