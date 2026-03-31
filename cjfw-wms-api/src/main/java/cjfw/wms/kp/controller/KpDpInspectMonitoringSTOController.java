package cjfw.wms.kp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.kp.dto.KpDpInspectMonitoringSTOReqDto;
import cjfw.wms.kp.dto.KpDpInspectMonitoringSTOResDetailDto;
import cjfw.wms.kp.dto.KpDpInspectMonitoringSTOResDto;
import cjfw.wms.kp.service.KpDpInspectMonitoringSTOService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net)
 * @date : 2025.11.29
 * @description : 광역출고검수현황 Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.29 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Tag(name = "dpInspectMonitoringSTO", description = "광역출고검수현황")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/kp/dpInspectMonitoringSTO")
public class KpDpInspectMonitoringSTOController {

	private final KpDpInspectMonitoringSTOService kpDpInspectMonitoringSTOService;

	/**
	 * @description : 광역출고검수현황 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.29 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "광역출고검수현황 목록 조회", description = "광역출고검수현황 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<KpDpInspectMonitoringSTOResDto>> getMasterList(@RequestBody KpDpInspectMonitoringSTOReqDto dto) {


		return ApiResult.createResult(kpDpInspectMonitoringSTOService.getMasterList(dto));
	}
	
	/**
	 * @description : 광역출고검수현황 상세 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.29 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "광역출고검수현황 상세 조회", description = "광역출고검수현황 상세 조회")
	@PostMapping(value = "/v1.0/getDetailList")
	public ApiResult<List<KpDpInspectMonitoringSTOResDetailDto>> getDetailList(@RequestBody KpDpInspectMonitoringSTOReqDto dto) {
		
		
		return ApiResult.createResult(kpDpInspectMonitoringSTOService.getDetailList(dto));
	}
}