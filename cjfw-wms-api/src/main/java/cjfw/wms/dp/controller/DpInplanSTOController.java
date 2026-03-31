package cjfw.wms.dp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.dp.dto.DpInplanSTODetailResDto;
import cjfw.wms.dp.dto.DpInplanSTOExcelResDto;
import cjfw.wms.dp.dto.DpInplanSTOReqDto;
import cjfw.wms.dp.dto.DpInplanSTOResDto;
import cjfw.wms.dp.dto.DpInplanSTOSerialInfoResDto;
import cjfw.wms.dp.service.DpInplanSTOService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.06.18 
 * @description : 광역입고현황 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.18 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Tag(name = "DpInplanSTO", description = "광역입고현황")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/dp/dpInplanSTO")
public class DpInplanSTOController {

	private final DpInplanSTOService dpInplanSTOService;

	/**
	 * @description : 광역입고현황 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.18 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "광역입고현황 목록 조회", description = "광역입고현황 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<DpInplanSTOResDto>> getMasterList(@RequestBody DpInplanSTOReqDto dpInplanSTOReqDto) {
			
				
		return ApiResult.createResult(dpInplanSTOService.getMasterList(dpInplanSTOReqDto));
	}
	
	/**
	 * @description : 광역입고현황 주문현황 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.05 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "광역입고현황 주문현황 조회", description = "광역입고현황 주문현황 조회")
	@PostMapping(value = "/v1.0/getDetailList")
	public ApiResult<List<DpInplanSTODetailResDto>> getDetailList(@RequestBody DpInplanSTOReqDto dpInplanSTOReqDto) {
		
		
		return ApiResult.createResult(dpInplanSTOService.getDetailList(dpInplanSTOReqDto));
	}
	
	
	/**
	 * @description : 광역입고현황 이력현황 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.05 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "광역입고현황 이력현황 조회", description = "광역입고현황 이력현황 조회")
	@PostMapping(value = "/v1.0/getSerialInfoList")
	public ApiResult<List<DpInplanSTOSerialInfoResDto>> getSerialInfoList(@RequestBody DpInplanSTOReqDto dpInplanSTOReqDto) {
		
		
		return ApiResult.createResult(dpInplanSTOService.getSerialInfoList(dpInplanSTOReqDto));
	}
	
	/**
	 * @description : 광역입고현황 엑셀자료 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.18 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "광역입고현황 엑셀자료 조회", description = "광역입고현황 엑셀자료 조회")
	@PostMapping(value = "/v1.0/getDataExcelList")
	public ApiResult<List<DpInplanSTOExcelResDto>> getDataExcelList(@RequestBody DpInplanSTOReqDto dpInplanSTOReqDto) {
			
				
		return ApiResult.createResult(dpInplanSTOService.getDataExcelList(dpInplanSTOReqDto));
	}
	
	
	public static boolean isNull(String str) {		 
		return str == null || str.trim().isEmpty();
	}

}