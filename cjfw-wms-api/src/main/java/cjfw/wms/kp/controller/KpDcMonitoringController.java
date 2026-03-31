package cjfw.wms.kp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.kp.dto.KpDcMonitoringReqDto;
import cjfw.wms.kp.dto.KpDcMonitoringResDto;
import cjfw.wms.kp.service.KpDcMonitoringService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.12.02 
 * @description : Home 센터 운영 모니터링
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.12.02 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Tag(name = "Home 센터 운영 모니터링", description = "Home 센터 운영 모니터링")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/kp/dc")
public class KpDcMonitoringController {
	
	private final KpDcMonitoringService kpDcMonitoringService;

	/**
	 * @description : 신규 마스터 정보 조회 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.02 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "신규 마스터 정보 조회", description = "신규 마스터 정보 조회")
	@GetMapping(value = "/v1.0/getNewMasterRead")
	public  ApiResult<List<KpDcMonitoringResDto>> getNewMasterRead(KpDcMonitoringReqDto dto) {
		return ApiResult.createResult(kpDcMonitoringService.getNewMasterRead(dto));
	}
	
	/**
	 * @description : 신규 마스터 상세 정보 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.02 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "신규 마스터 상세 정보 조회", description = "신규 마스터 상세 정보 조회")
	@GetMapping(value = "/v1.0/getNewMasterDetailRead")
	public  ApiResult<List<KpDcMonitoringResDto>> getNewMasterDetailRead(KpDcMonitoringReqDto dto) {
		return ApiResult.createResult(kpDcMonitoringService.getNewMasterDetailRead(dto));
	}

}
