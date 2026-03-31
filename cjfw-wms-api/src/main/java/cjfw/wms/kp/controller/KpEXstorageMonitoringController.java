package cjfw.wms.kp.controller;



import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.kp.dto.KpEXstorageMonitoringDetailListReqDto;
import cjfw.wms.kp.dto.KpEXstorageMonitoringDetailSubListReqDto;
import cjfw.wms.kp.dto.KpEXstorageMonitoringDetailTcsListReqDto;
import cjfw.wms.kp.dto.KpEXstorageMonitoringDetailTcsSubListReqDto;
import cjfw.wms.kp.dto.KpEXstorageMonitoringLocalDetailListReqDto;
import cjfw.wms.kp.dto.KpEXstorageMonitoringLocalDetailSubListReqDto;
import cjfw.wms.kp.dto.KpEXstorageMonitoringLocalMasterListReqDto;
import cjfw.wms.kp.dto.KpEXstorageMonitoringMasterListReqDto;
import cjfw.wms.kp.dto.KpEXstorageMonitoringTCSMasterListReqDto;
import cjfw.wms.kp.service.KpEXstorageMonitoringService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.07.14 
 * @description : 외부창고재고모니터링  기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.14 ParkJinWoo 생성
 */
@Tag(name = "지표/모니터링 > 재고운영지표 > 외부비축재고변경사유현황", description = "외부비축재고변경사유현황")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/kp/eXstorageMonitoring")
public class KpEXstorageMonitoringController {

	private final KpEXstorageMonitoringService kpEXstorageMonitoringService;

	/**
	 * @description : 외부창고재고모니터링 헤더 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.14 ParkJinWoo 생성
	 */
	@Operation(summary = "외부창고재고모니터링 헤더 List", description = "외부창고재고모니터링 헤더 List")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<Object> getMasterList(@Valid @RequestBody KpEXstorageMonitoringMasterListReqDto reqDto) {
		return ApiResult.createResult(kpEXstorageMonitoringService.getMasterList(reqDto));
	}

	/**
	 * @description : 외부창고재고모니터링 Local 헤더 List 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.14 ParkJinWoo 생성
	 */
	@Operation(summary = "외부창고재고모니터링 Local 헤더 List", description = "외부창고재고모니터링 Local 헤더 List")
	@PostMapping(value="/v1.0/getDataLocalHeaderList")
	public ApiResult<Object> getDataLocalHeaderList(@Valid @RequestBody KpEXstorageMonitoringLocalMasterListReqDto reqDto) {
		return ApiResult.createResult(kpEXstorageMonitoringService.getDataLocalHeaderList(reqDto));
	}
	
	/**
	 * @description : 외부창고재고모니터링 Tcs 헤더 List 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.14 ParkJinWoo 생성
	 */
	@Operation(summary = "외부창고재고모니터링 Tcs 헤더 List", description = "외부창고재고모니터링 Tcs 헤더 List")
	@PostMapping(value="/v1.0/getDataTcsHeaderList")
	public ApiResult<Object> getDataTcsHeaderList(@Valid @RequestBody KpEXstorageMonitoringTCSMasterListReqDto reqDto) {
		return ApiResult.createResult(kpEXstorageMonitoringService.getDataTcsHeaderList(reqDto));
	}

	/**
	 * @description : 외부창고재고모니터링 상세 List 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.14 ParkJinWoo 생성
	 */
	@Operation(summary = "외부창고재고모니터링 상세 List", description = "외부창고재고모니터링 상세 List")
	@PostMapping(value="/v1.0/getDataDetailList")
	public ApiResult<Object> getDataDetailList(@Valid @RequestBody KpEXstorageMonitoringDetailListReqDto reqDto) {
		return ApiResult.createResult(kpEXstorageMonitoringService.getDataDetailList(reqDto));
	}
	
	/**
	 * @description : 외부창고재고모니터링 Local 상세 List 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.14 ParkJinWoo 생성
	 */
	@Operation(summary = "외부창고재고모니터링 Local 상세 List", description = "외부창고재고모니터링 Local 상세 List")
	@PostMapping(value="/v1.0/getDataLocalDetailList")
	public ApiResult<Object> getDataLocalDetailList(@Valid @RequestBody KpEXstorageMonitoringLocalDetailListReqDto reqDto) {
		return ApiResult.createResult(kpEXstorageMonitoringService.getDataLocalDetailList(reqDto));
	}
	
	/**
	 * @description : 외부창고재고모니터링 sub 상세 List 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.14 ParkJinWoo 생성
	 */
	@Operation(summary = "외부창고재고모니터링 sub 상세 List", description = "외부창고재고모니터링 sub 상세 List")
	@PostMapping(value="/v1.0/getDataDetailSubList")
	public ApiResult<Object> getDataDetailSubList(@Valid @RequestBody KpEXstorageMonitoringDetailSubListReqDto reqDto) {
		return ApiResult.createResult(kpEXstorageMonitoringService.getDataDetailSubList(reqDto));
	}
	
	/**
	 * @description : 외부창고재고모니터링 Tcs 상세 List 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.14 ParkJinWoo 생성
	 */
	@Operation(summary = "외부창고재고모니터링 Tcs 상세 List", description = "외부창고재고모니터링 Tcs 상세 List")
	@PostMapping(value="/v1.0/getDataDetailTcsList")
	public ApiResult<Object> getDataDetailTcsList(@Valid @RequestBody KpEXstorageMonitoringDetailTcsListReqDto reqDto) {
		return ApiResult.createResult(kpEXstorageMonitoringService.getDataDetailTcsList(reqDto));
	}
	
	
	/**
	 * @description : 외부창고재고모니터링 Local Sub 상세 List조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.14 ParkJinWoo 생성
	 */
	@Operation(summary = "외부창고재고모니터링 Local 상세 List", description = "외부창고재고모니터링 Local 상세 List")
	@PostMapping(value="/v1.0/getDataLocalDetailSubList")
	public ApiResult<Object> getDataLocalDetailSubList(@Valid @RequestBody KpEXstorageMonitoringLocalDetailSubListReqDto reqDto) {
		return ApiResult.createResult(kpEXstorageMonitoringService.getDataLocalDetailSubList(reqDto));
	}
	
	/**
	 * @description : 외부창고재고모니터링 Local Sub 상세 List조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.14 ParkJinWoo 생성
	 */
	@Operation(summary = "외부창고재고모니터링 Local 상세 List", description = "외부창고재고모니터링 Local 상세 List")
	@PostMapping(value="/v1.0/getDataDetailTcsSubList")
	public ApiResult<Object> getDataDetailTcsSubList(@Valid @RequestBody KpEXstorageMonitoringDetailTcsSubListReqDto reqDto) {
		return ApiResult.createResult(kpEXstorageMonitoringService.getDataDetailTcsSubList(reqDto));
	}
}
