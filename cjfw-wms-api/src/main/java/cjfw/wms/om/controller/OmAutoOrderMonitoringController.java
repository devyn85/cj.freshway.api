package cjfw.wms.om.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.om.dto.OmAutoOrderMonitoringReqDto;
import cjfw.wms.om.dto.OmAutoOrderMonitoringResDto;
import cjfw.wms.om.service.OmAutoOrderMonitoringService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : JiSooKim (jskim14@cj.net)
 * @date : 2025.08.12
 * @description : 자동발주모니터링 Controller Class
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.12 JiSooKim (jskim14@cj.net) 생성
 *         </pre>
 */
@Tag(name = "시스템운영 > 인터페이스모니터링 > 인터페이스 상태관리", description = "OmAutoOrderMonitoringController")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/om/autoOrderMonitoring")
public class OmAutoOrderMonitoringController {
	private final OmAutoOrderMonitoringService omAutoOrderMonitoringService;

	/**
	 * @description : 자동발주모니터링 조회 List Method
	 * @issues :
	 * 
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.12 JiSooKim (jskim14@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "자동발주모니터링 조회 List", description = "자동발주모니터링 조회 List")
	@GetMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<OmAutoOrderMonitoringResDto>> getMasterList(@Valid OmAutoOrderMonitoringReqDto reqDto) {
		return ApiResult.createResult(omAutoOrderMonitoringService.getMasterList(reqDto));
	}
}
