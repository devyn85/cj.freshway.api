package cjfw.wms.om.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.om.dto.OmPurchaseMonitoringGraphResDto;
import cjfw.wms.om.dto.OmPurchaseMonitoringReqDto;
import cjfw.wms.om.dto.OmPurchaseMonitoringResDto;
import cjfw.wms.om.service.OmPurchaseMonitoringService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.10.14
 * @description : 주문 > 주믄등록 > 저장품발주현황 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.14 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Tag(name = "주문 > 주믄등록 > 저장품발주현황", description = "저장품발주현황")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/om/purchaseMonitoring")
public class OmPurchaseMonitoringController {

	private final OmPurchaseMonitoringService omPurchaseMonitoringService;

	/**
	 * @description : PO 발주현황 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.14 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "PO 발주현황 조회 (목록)", description = "PO 발주현황 조회 (목록)")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<OmPurchaseMonitoringResDto>> getMasterList(@RequestBody @Valid OmPurchaseMonitoringReqDto dto) {
		return ApiResult.createResult(omPurchaseMonitoringService.getMasterList(dto));
	}
	
	/**
	 * @description : 발주추이 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.14 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "발주추이 조회 (목록)", description = "발주추이 조회 (목록)")
	@GetMapping(value = "/v1.0/getGraphList")
	public ApiResult<List<OmPurchaseMonitoringGraphResDto>> getGraphList(@Valid OmPurchaseMonitoringReqDto dto) {
		return ApiResult.createResult(omPurchaseMonitoringService.getGraphList(dto));
	}
}