package cjfw.wms.om.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.om.dto.OmOrderCustDailyDetailResDto;
import cjfw.wms.om.dto.OmOrderCustDailyPrintResDto;
import cjfw.wms.om.dto.OmOrderCustDailyReqDto;
import cjfw.wms.om.dto.OmOrderCustDailyResDto;
import cjfw.wms.om.service.OmOrderCustDailyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.06.20 
 * @description : 일배협력사별주문현황 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.20 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Tag(name = "OmOrderCustDaily", description = "일배협력사별주문현황")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/om/omOrderCustDaily")
public class OmOrderCustDailyController {

	private final OmOrderCustDailyService omOrderCustDailyService;

	/**
	 * @description : 일배협력사별주문현황 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.20 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "일배협력사별주문현황 목록 조회", description = "일배협력사별주문현황 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<OmOrderCustDailyResDto>> getMasterList(@RequestBody OmOrderCustDailyReqDto omOrderCustDailyReqDto) {
			
				
		return ApiResult.createResult(omOrderCustDailyService.getMasterList(omOrderCustDailyReqDto));
	}
	
	/**
	 * @description : 일배협력사별주문현황 주문현황 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.20 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "일배협력사별주문현황 주문현황 조회", description = "일배협력사별주문현황 주문현황 조회")
	@PostMapping(value = "/v1.0/getDetailList")
	public ApiResult<List<OmOrderCustDailyDetailResDto>> getDetailList(@RequestBody OmOrderCustDailyReqDto omOrderCustDailyReqDto) {
		
		
		return ApiResult.createResult(omOrderCustDailyService.getDetailList(omOrderCustDailyReqDto));
	}
	/**
	 * @description : 일배협력사별주문현황 프린트 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.20 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "일배협력사별주문현황 프린트 조회", description = "일배협력사별주문현황 프린트 조회")
	@PostMapping(value = "/v1.0/getDataPrintList")
	public ApiResult<List<OmOrderCustDailyPrintResDto>> getDataPrintList(@RequestBody OmOrderCustDailyReqDto omOrderCustDailyReqDto) {
			
				
		return ApiResult.createResult(omOrderCustDailyService.getDataPrintList(omOrderCustDailyReqDto));
	}
	
	
	public static boolean isNull(String str) {		 
		return str == null || str.trim().isEmpty();
	}

}