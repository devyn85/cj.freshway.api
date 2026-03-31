package cjfw.wms.om.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.om.dto.OmCloseMonitoringReqDto;
import cjfw.wms.om.dto.OmCloseMonitoringResCloseTimeDto;
import cjfw.wms.om.dto.OmCloseMonitoringResDto;
import cjfw.wms.om.service.OmCloseMonitoringService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.06.23 
 * @description : 마감주문반영 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.23 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Tag(name = "OmCloseMonitoring", description = "마감주문반영")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/om/omCloseMonitoring")
public class OmCloseMonitoringController {

	private final OmCloseMonitoringService omCloseMonitoringService;

	/**
	 * @description : 전표모니터링 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.23 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "전표모니터링 목록 조회", description = "전표모니터링 목록 조회")
	@GetMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<OmCloseMonitoringResDto>> getMasterList(@Valid OmCloseMonitoringReqDto omCloseMonitoringReqDto) {
			
				
		return ApiResult.createResult(omCloseMonitoringService.getMasterList(omCloseMonitoringReqDto));
	}
	
	/**
	 * @throws Exception
	 * @description : 마감주문반영 저장
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.23 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "마감주문반영 저장", description = "마감주문반영 저장")
	@PostMapping(value = "/v1.0/saveConfirm")
	public ApiResult<String> saveConfirm(@RequestBody OmCloseMonitoringReqDto dto) throws Exception {
		return ApiResult.createResult(omCloseMonitoringService.saveConfirm(dto));
	}
	
	/**
	 * @throws Exception
	 * @description : 마감기준시간 콤보데이터 조회
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.08 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "마감기준시간 콤보데이터 조회", description = "마감기준시간 콤보데이터 조회")
	@PostMapping(value = "/v1.0/getCloseTime")
	public ApiResult<List<OmCloseMonitoringResCloseTimeDto>> getCloseTime(@RequestBody OmCloseMonitoringReqDto dto) throws Exception {
		return ApiResult.createResult(omCloseMonitoringService.getCloseTime(dto));
	}
	

	
	public static boolean isNull(String str) {		 
		return str == null || str.trim().isEmpty();
	}

}