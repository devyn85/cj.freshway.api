package cjfw.wms.kp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.kp.dto.KpCloseReqDto;
import cjfw.wms.kp.dto.KpCloseResDetailDto;
import cjfw.wms.kp.dto.KpCloseResDto;
import cjfw.wms.kp.dto.KpWdLoadMonitoringDetailResDto;
import cjfw.wms.kp.dto.KpWdLoadMonitoringReqDto;
import cjfw.wms.kp.dto.KpWdLoadMonitoringResDto;
import cjfw.wms.kp.service.KpCloseService;
import cjfw.wms.kp.service.KpWdLoadMonitoringService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : ParkYoSep (dytpq362@cj.net)
 * @date : 2025.12.12
 * @description : 지표/모니터링 > 검수지표 > 상차검수지표  조회 Controller Class
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.12 ParkYosep (dytpq362@cj.net) 생성
 *         </pre>
 */
@Tag(name = "지표/모니터링 > 검수지표 > 상차검수지표 ", description = "지표/모니터링 > 검수지표 > 상차검수지표를 조회 한다.")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/kp/wdLoadMonitoring")
public class KpWdLoadMonitoringController {
	private final KpWdLoadMonitoringService kpWdLoadMonitoringService;

	/**
	 * @description : 지표/모니터링 > 검수지표 > 상차검수지표 현황 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.12 ParkYosep (dytpq362@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "지표/모니터링 > 검수지표 > 상차검수지표", description = "지표/모니터링 > 검수지표 > 상차검수지표 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<KpWdLoadMonitoringResDto>> getMasterList(@RequestBody KpWdLoadMonitoringReqDto dto) {
		return ApiResult.createResult(kpWdLoadMonitoringService.getMasterList(dto));
	}	
	
	/**
	 * @description : 지표/모니터링 > 검수지표 > 상차검수지표 현황 상세 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.12 ParkYosep (dytpq362@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "지표/모니터링 > 검수지표 > 상차검수지표 상세 조회", description = "지표/모니터링 > 검수지표 > 상차검수지표 현황 상세 조회")
	@PostMapping(value = "/v1.0/getDetailList")
	public ApiResult<List<KpWdLoadMonitoringDetailResDto>> getDetailList(@RequestBody KpWdLoadMonitoringReqDto dto) {
		return ApiResult.createResult(kpWdLoadMonitoringService.getDetailList(dto));
	}	
	

}
