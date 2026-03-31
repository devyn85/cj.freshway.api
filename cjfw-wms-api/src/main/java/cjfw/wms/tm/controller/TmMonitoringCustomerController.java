package cjfw.wms.tm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.tm.dto.TmMonitoringCustomerReqDto;
import cjfw.wms.tm.dto.TmMonitoringCustomerResDto;
import cjfw.wms.tm.service.TmMonitoringCustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2025.11.24
 * @description : 배송고객모니터링 Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.24 JiHoPark  생성 </pre>
 */
@Tag(name = "TmMonitoringCustomerController", description = "배송고객모니터링(배송 > 배차현황 > 배송고객모니터링)")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/tm/monitoringcustomer")
public class TmMonitoringCustomerController {
	private final TmMonitoringCustomerService tmMonitoringCustomerService;

	/**
	 * @description : 배송고객모니터링 - 모니터링 그룹 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.24 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "배송고객모니터링 - 모니터링 그룹 목록 조회", description = "배송고객모니터링 - 모니터링 그룹 목록 조회")
	@PostMapping(value = "/v1.0/getMonitoringCustomerGroupList")
	public ApiResult<List<TmMonitoringCustomerResDto>> getMonitoringCustomerGroupList(@Valid @RequestBody TmMonitoringCustomerReqDto dto) {
		return ApiResult.createResult(tmMonitoringCustomerService.getMonitoringCustomerGroupList(dto));
	}

	/**
	 * @description : 배송고객모니터링 - 모니터링 그룹 상세 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.24 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "배송고객모니터링 - 모니터링 그룹 상세 목록 조회", description = "배송고객모니터링 - 모니터링 그룹 상세 목록 조회")
	@PostMapping(value = "/v1.0/getMonitoringCustomerGroupDetailList")
	public ApiResult<List<TmMonitoringCustomerResDto>> getMonitoringCustomerGroupDetailList(@Valid @RequestBody TmMonitoringCustomerReqDto dto) {
		return ApiResult.createResult(tmMonitoringCustomerService.getMonitoringCustomerGroupDetailList(dto));
	}

	/**
	 * @description : 배송고객모니터링 - 배송고객모니터링 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.24 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "배송고객모니터링 - 배송고객모니터링 목록 조회", description = "배송고객모니터링 - 배송고객모니터링 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<TmMonitoringCustomerResDto>> getMasterList(@Valid @RequestBody TmMonitoringCustomerReqDto dto) {
		return ApiResult.createResult(tmMonitoringCustomerService.getMasterList(dto));
	}

	/**
	 * @description : 배송고객모니터링 - 배송고객모니터링 목록 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.26 JiHoPark 생성 </pre>
	 */
	@Operation(summary = "배송고객모니터링 - 배송고객모니터링 목록 저장", description = "배송고객모니터링 - 배송고객모니터링 목록 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<String> saveMasterList(@RequestBody TmMonitoringCustomerReqDto dto) {
		return ApiResult.createResult(tmMonitoringCustomerService.saveMasterList(dto));
	}

	/**
	 * @description : 배송고객모니터링 - 모니터링 그룹 목록 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.27 JiHoPark 생성 </pre>
	 */
	@Operation(summary = "배송고객모니터링 - 모니터링 그룹 목록 저장", description = "배송고객모니터링 - 모니터링 그룹 목록 저장")
	@PostMapping(value = "/v1.0/saveMonitoringCustomerGroupList")
	public ApiResult<String> saveMonitoringCustomerGroupList(@RequestBody TmMonitoringCustomerReqDto dto) {
		return ApiResult.createResult(tmMonitoringCustomerService.saveMonitoringCustomerGroupList(dto));
	}

	/**
	 * @description : 배송고객모니터링 - 모니터링 그룹 상세 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.27 JiHoPark 생성 </pre>
	 */
	@Operation(summary = "배송고객모니터링 - 모니터링 그룹 상세 저장", description = "배송고객모니터링 - 모니터링 그룹 상세 저장")
	@PostMapping(value = "/v1.0/saveMonitoringCustomerGroupDetailList")
	public ApiResult<String> saveMonitoringCustomerGroupDetailList(@RequestBody TmMonitoringCustomerReqDto dto) {
		return ApiResult.createResult(tmMonitoringCustomerService.saveMonitoringCustomerGroupDetailList(dto));
	}

	/**
	 * @description : 배송고객모니터링 - 이메일 발송
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.26 JiHoPark 생성 </pre>
	 */
	@Operation(summary = "배송고객모니터링 - 이메일 발송", description = "배송고객모니터링 - 이메일 발송")
	@PostMapping(value = "/v1.0/sendEmail")
	public ApiResult<String> sendEmail(@RequestBody TmMonitoringCustomerReqDto dto) {
		return ApiResult.createResult(tmMonitoringCustomerService.sendEmail(dto));
	}


}
