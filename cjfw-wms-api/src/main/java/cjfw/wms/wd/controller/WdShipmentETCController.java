package cjfw.wms.wd.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.wd.dto.WdShipmentETCReqDto;
import cjfw.wms.wd.dto.WdShipmentETCResTab1Dto;
import cjfw.wms.wd.dto.WdShipmentETCResTab2Dto;
import cjfw.wms.wd.dto.WdShipmentETCResTab3Dto;
import cjfw.wms.wd.service.WdShipmentETCService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 고혜미
 * @date : 2025.10.15
 * @description : 매각출고처리 Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.15 고혜미 생성 </pre>
 */
@Tag(name = "WdShipmentETC", description = "출고 > 기타출고 > 매각출고처리")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/wd/shipmentETC")
public class WdShipmentETCController {

	private final WdShipmentETCService wdShipmentETCService;

	/**
	 * @description : 기타출고 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.15 고혜미 생성 </pre>
	 */
	@Operation(summary = "매각출고처리 > 기타출고 목록 조회", description = "매각출고처리 > 기타출고 목록 조회")
	@PostMapping(value = "/v1.0/getTab1MasterList")
	public ApiResult<List<WdShipmentETCResTab1Dto>> getTab1MasterList(@RequestBody WdShipmentETCReqDto dto) {
		return ApiResult.createResult(wdShipmentETCService.getTab1MasterList(dto));
	}
	
	/**
	 * @description : 매각출고처리 - 기타출고 처리 저장[매각]
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.16 고혜미 생성 </pre>
	 */
	@Operation(summary = "매각출고처리 > 기타출고 처리 저장", description = "매각출고처리 > 기타출고 처리 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<String> saveMasterList(@RequestBody WdShipmentETCReqDto reqDto) throws Exception {
		return ApiResult.createResult(wdShipmentETCService.saveMasterList(reqDto));
	}		
	
	
	/**
	 * @description : 매각출고처리 - 기타출고 처리 저장[매각]
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.16 고혜미 생성 </pre>
	 */
	@Operation(summary = "매각출고처리 > 기타출고 처리 저장", description = "매각출고처리 > 기타출고 처리 저장")
	@PostMapping(value = "/v1.0/saveMasterList01")
	public ApiResult<String> saveMasterList01(@RequestBody WdShipmentETCReqDto reqDto) throws Exception {
		return ApiResult.createResult(wdShipmentETCService.saveMasterList01(reqDto));
	}		
	
	/**
	 * @description : 매각출고처리 - 기타출고 처리 저장[기부]
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.16 고혜미 생성 </pre>
	 */
	@Operation(summary = "매각출고처리 > 기타출고 처리 저장", description = "매각출고처리 > 기타출고 처리 저장")
	@PostMapping(value = "/v1.0/saveMasterList02")
	public ApiResult<String> saveMasterList02(@RequestBody WdShipmentETCReqDto reqDto) throws Exception {
		return ApiResult.createResult(wdShipmentETCService.saveMasterList02(reqDto));
	}	
	
	/**
	 * @description : 매각내역 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.26 고혜미 생성 </pre>
	 */
	@Operation(summary = "매각출고처리 > 처리결과 목록 조회", description = "매각출고처리 > 매각내역 목록 조회")
	@PostMapping(value = "/v1.0/getTab3MasterList")
	public ApiResult<List<WdShipmentETCResTab3Dto>> getTab3MasterList(@RequestBody WdShipmentETCReqDto dto) {			
		return ApiResult.createResult(wdShipmentETCService.getTab3MasterList(dto));
	}	
	
	/**
	 * @description : 매각출고처리 - 매각내역 처리 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.16 고혜미 생성 </pre>
	 */
	@Operation(summary = "매각출고처리 > 매각내역 처리 저장", description = "매각출고처리 > 매각내역 처리 저장")
	@PostMapping(value = "/v1.0/saveMasterList03")
	public ApiResult<String> saveMasterList03(@RequestBody WdShipmentETCReqDto reqDto) throws Exception {
		return ApiResult.createResult(wdShipmentETCService.saveMasterList03(reqDto));
	}	
	
	/**
	 * @description : 처리결과 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.26 고혜미 생성 </pre>
	 */
	@Operation(summary = "매각출고처리 > 처리결과 목록 조회", description = "매각출고처리 > 처리결과 목록 조회")
	@PostMapping(value = "/v1.0/getTab2MasterList")
	public ApiResult<List<WdShipmentETCResTab2Dto>> getTab2MasterList(@RequestBody WdShipmentETCReqDto dto) {
		
		
		return ApiResult.createResult(wdShipmentETCService.getTab2MasterList(dto));
	}
	

}