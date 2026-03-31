package cjfw.wms.ms.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.MsDeliveryDistrictHistoryReqDto;
import cjfw.wms.ms.service.MsDeliveryDistrictHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : System Generated
 * @date : 2025.01.XX 
 * @description : 기준정보 > 배송 권역관리 > 배송 권역 이력 조회 기능을 구현한 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.01.XX System Generated 생성 </pre>
 */
@Tag(name = "기준정보 > 배송 권역관리 > 배송 권역 이력", description = "배송 권역 이력 목록 조회")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ms/deliveryDistrictHistory")
public class MsDeliveryDistrictHistoryController {
	
	private final MsDeliveryDistrictHistoryService msDeliveryDistrictHistoryService;
	
	
	/**
	 * @description : 배송 권역 이력 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.01.XX System Generated 생성 </pre>
	 */
	@Operation(summary = "배송 권역 이력 목록 조회", description = "조회 조건에 따른 배송 권역 이력 목록 조회")
	@PostMapping(value = "/v1.0/getHistoryList")
	public ApiResult<Object> getHistoryList(@RequestBody MsDeliveryDistrictHistoryReqDto dto) {
		return ApiResult.createResult(msDeliveryDistrictHistoryService.getHistoryList(dto));
	}
}

