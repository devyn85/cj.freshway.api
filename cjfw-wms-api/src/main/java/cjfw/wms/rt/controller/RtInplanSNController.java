package cjfw.wms.rt.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.rt.dto.RtInplanSNDetailResDto;
import cjfw.wms.rt.dto.RtInplanSNExcelResDto;
import cjfw.wms.rt.dto.RtInplanSNReqDto;
import cjfw.wms.rt.dto.RtInplanSNResDto;
import cjfw.wms.rt.service.RtInplanSNService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.05.28 
 * @description : 이력상품반품현황 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.28 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Tag(name = "RtInplanSN", description = "이력상품반품현황")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/rt/rtInplanSN")
public class RtInplanSNController {

	private final RtInplanSNService rtInplanSNService;

	/**
	 * @description : 이력상품반품현황 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.29 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "이력상품반품현황 목록 조회", description = "이력상품반품현황 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<RtInplanSNResDto>> getMasterList(@RequestBody RtInplanSNReqDto rtInplanSNReqDto) {
			
				
		return ApiResult.createResult(rtInplanSNService.getMasterList(rtInplanSNReqDto));
	}
	
	/**
	 * @description : 이력상품반품현황 상세 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.29 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "이력상품반품현황 상세 조회", description = "이력상품반품현황 상세 조회")
	@PostMapping(value = "/v1.0/getDetailList")
	public ApiResult<List<RtInplanSNDetailResDto>> getDetailList(@RequestBody RtInplanSNReqDto rtInplanSNReqDto) {
		
		
		return ApiResult.createResult(rtInplanSNService.getDetailList(rtInplanSNReqDto));
	}
	
	/**
	 * @description : 이력상품반품현황 엑셀자료 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.30 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "이력상품반품현황 엑셀자료 조회", description = "이력상품반품현황 엑셀자료 조회")
	@PostMapping(value = "/v1.0/getDataExcelList")
	public ApiResult<List<RtInplanSNExcelResDto>> getDataExcelList(@RequestBody RtInplanSNReqDto rtInplanSNReqDto) {
			
				
		return ApiResult.createResult(rtInplanSNService.getDataExcelList(rtInplanSNReqDto));
	}
	
	
	public static boolean isNull(String str) {		 
		return str == null || str.trim().isEmpty();
	}

}