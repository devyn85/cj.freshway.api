package cjfw.wms.rt.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import cjfw.core.model.ApiResult;
import cjfw.wms.rt.dto.RtInplanSNExcelResDto;
import cjfw.wms.rt.dto.RtInplanTotalDetailResDto;
import cjfw.wms.rt.dto.RtInplanTotalReqDto;
import cjfw.wms.rt.dto.RtInplanTotalResDto;
import cjfw.wms.rt.dto.RtInplanTotalSerialInfoResDto;
import cjfw.wms.rt.service.RtInplanTotalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.06.04 
 * @description : 반품진행현황 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.04 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Tag(name = "RtInplanTotal", description = "반품진행현황")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/rt/rtInplanTotal")
public class RtInplanTotalController {

	private final RtInplanTotalService rtInplanTotalService;

	/**
	 * @description : 반품진행현황 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.04 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "반품진행현황 목록 조회", description = "반품진행현황 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<RtInplanTotalResDto>> getMasterList(@RequestBody RtInplanTotalReqDto rtInplanTotalReqDto) {
			
				
		return ApiResult.createResult(rtInplanTotalService.getMasterList(rtInplanTotalReqDto));
	}
	
	/**
	 * @description : 반품진행현황 클레임내역 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.05 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "반품진행현황 클레임내역 조회", description = "반품진행현황 클레임내역 조회")
	@PostMapping(value = "/v1.0/getDetailList")
	public ApiResult<List<RtInplanTotalDetailResDto>> getDetailList(@RequestBody RtInplanTotalReqDto rtInplanTotalReqDto) {
		
		
		return ApiResult.createResult(rtInplanTotalService.getDetailList(rtInplanTotalReqDto));
	}
	
	
	/**
	 * @description : 반품진행현황 이력정보 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.05 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "반품진행현황 이력정보 조회", description = "반품진행현황 이력정보 조회")
	@PostMapping(value = "/v1.0/getSerialInfoList")
	public ApiResult<List<RtInplanTotalSerialInfoResDto>> getSerialInfoList(@RequestBody RtInplanTotalReqDto rtInplanTotalReqDto) {
		
		
		return ApiResult.createResult(rtInplanTotalService.getSerialInfoList(rtInplanTotalReqDto));
	}
	
	/**
	 * @description : 반품진행현황 엑셀자료 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.04 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "반품진행현황 엑셀자료 조회", description = "반품진행현황 엑셀자료 조회")
	@PostMapping(value = "/v1.0/getDataExcelList")
	public ApiResult<List<RtInplanSNExcelResDto>> getDataExcelList(@RequestBody RtInplanTotalReqDto rtInplanTotalReqDto) {
			
				
		return ApiResult.createResult(rtInplanTotalService.getDataExcelList(rtInplanTotalReqDto));
	}
	
	
	public static boolean isNull(String str) {		 
		return str == null || str.trim().isEmpty();
	}

}