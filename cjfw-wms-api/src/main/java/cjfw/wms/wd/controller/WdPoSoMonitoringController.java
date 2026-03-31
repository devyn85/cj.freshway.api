package cjfw.wms.wd.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.wd.dto.WdPoSoMonitoringGrid1ResDto;
import cjfw.wms.wd.dto.WdPoSoMonitoringReqDto;
import cjfw.wms.wd.dto.WdPoSoMonitoringResDto;
import cjfw.wms.wd.service.WdPoSoMonitoringService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : YangChangHwan (iamai@cj.net)
 * @date : 2025.06.23
 * @description : 출고 > 출고현황 > 일배PO/SO연결모니터링 Controller Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.23 YangChangHwan (iamai@cj.net) 생성 </pre>
 */
@Tag(name = "출고 > 출고현황 > 일배PO/SO연결모니터링", description = "일배PO/SO연결모니터링 API Controller")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/wd/wdPoSoMonitoring")
public class WdPoSoMonitoringController {

	private final WdPoSoMonitoringService wdPoSoMonitoringService;

	/** @description : 일배PO/SO연결모니터링 List Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.23 YangChangHwan (iamai@cj.net) 생성 </pre>
	*/
	@Operation(summary = "일배PO/SO연결모니터링 List", description = "일배PO/SO연결모니터링 List")
	@GetMapping(value="/v1.0/getWdPoSoMonitoringList")
	public ApiResult<List<WdPoSoMonitoringResDto>> getWdPoSoMonitoringList(@Valid WdPoSoMonitoringReqDto reqDto) {
		return ApiResult.createResult(wdPoSoMonitoringService.getWdPoSoMonitoringList(reqDto));
	}

	/** @description : 일배PO/SO연결모니터링 Grid1 List Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.23 YangChangHwan (iamai@cj.net) 생성 </pre>
	*/
	@Operation(summary = "일배PO/SO연결모니터링 Grid1 List", description = "일배PO/SO연결모니터링 Grid1 List")
	@GetMapping(value="/v1.0/getgetWdPoSoMonitoringGrid1List")
	public ApiResult<List<WdPoSoMonitoringGrid1ResDto>> getgetWdPoSoMonitoringGrid1List(@Valid WdPoSoMonitoringReqDto reqDto) {
		return ApiResult.createResult(wdPoSoMonitoringService.getWdPoSoMonitoringGrid1List(reqDto));
	}
}
