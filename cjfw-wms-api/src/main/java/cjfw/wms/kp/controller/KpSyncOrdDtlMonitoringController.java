package cjfw.wms.kp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.kp.dto.KpSyncOrdDtlMonitoringReqDto;
import cjfw.wms.kp.service.KpSyncOrdDtlMonitoringService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * KP 주문동기화 모니터링 상세(탭별) Controller
 * Base: /api/kp/syncOrdDtlMonitoring/v1.0/
 */
@Tag(name = "KP > 주문동기화 모니터링 상세", description = "KP 주문동기화 모니터링 상세(DP/RT/WD/AJ/ST 탭)")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/kp/syncOrdDtlMonitoring")
public class KpSyncOrdDtlMonitoringController {

	private final KpSyncOrdDtlMonitoringService kpSyncOrdDtlMonitoringService;

	@Operation(summary = "DP_INPLAN 탭", description = "주문동기화 모니터링 DP_INPLAN 목록 조회")
	@GetMapping(value = "/v1.0/getDPInplanList")
	public ApiResult<Object> getDPInplanList(@Valid KpSyncOrdDtlMonitoringReqDto reqDto) {
		return ApiResult.createResult(kpSyncOrdDtlMonitoringService.getDPInplanList(reqDto));
	}

	@Operation(summary = "DP_INPLAN 탭 (POST)", description = "주문동기화 모니터링 DP_INPLAN 목록 조회 (Body)")
	@PostMapping(value = "/v1.0/getDPInplanList")
	public ApiResult<Object> getDPInplanListPost(@Valid @RequestBody KpSyncOrdDtlMonitoringReqDto reqDto) {
		return ApiResult.createResult(kpSyncOrdDtlMonitoringService.getDPInplanList(reqDto));
	}

	@Operation(summary = "RT_INPLAN 탭", description = "주문동기화 모니터링 RT_INPLAN 목록 조회")
	@GetMapping(value = "/v1.0/getRTInplanList")
	public ApiResult<Object> getRTInplanList(@Valid KpSyncOrdDtlMonitoringReqDto reqDto) {
		return ApiResult.createResult(kpSyncOrdDtlMonitoringService.getRTInplanList(reqDto));
	}

	@Operation(summary = "RT_INPLAN 탭 (POST)", description = "주문동기화 모니터링 RT_INPLAN 목록 조회 (Body)")
	@PostMapping(value = "/v1.0/getRTInplanList")
	public ApiResult<Object> getRTInplanListPost(@Valid @RequestBody KpSyncOrdDtlMonitoringReqDto reqDto) {
		return ApiResult.createResult(kpSyncOrdDtlMonitoringService.getRTInplanList(reqDto));
	}

	@Operation(summary = "WD_INPLAN 탭", description = "주문동기화 모니터링 WD_INPLAN 목록 조회")
	@GetMapping(value = "/v1.0/getWDInplanList")
	public ApiResult<Object> getWDInplanList(@Valid KpSyncOrdDtlMonitoringReqDto reqDto) {
		return ApiResult.createResult(kpSyncOrdDtlMonitoringService.getWDInplanList(reqDto));
	}

	@Operation(summary = "WD_INPLAN 탭 (POST)", description = "주문동기화 모니터링 WD_INPLAN 목록 조회 (Body)")
	@PostMapping(value = "/v1.0/getWDInplanList")
	public ApiResult<Object> getWDInplanListPost(@Valid @RequestBody KpSyncOrdDtlMonitoringReqDto reqDto) {
		return ApiResult.createResult(kpSyncOrdDtlMonitoringService.getWDInplanList(reqDto));
	}

	@Operation(summary = "AJ_INPLAN 탭", description = "주문동기화 모니터링 AJ_INPLAN 목록 조회")
	@GetMapping(value = "/v1.0/getAJInplanList")
	public ApiResult<Object> getAJInplanList(@Valid KpSyncOrdDtlMonitoringReqDto reqDto) {
		return ApiResult.createResult(kpSyncOrdDtlMonitoringService.getAJInplanList(reqDto));
	}

	@Operation(summary = "AJ_INPLAN 탭 (POST)", description = "주문동기화 모니터링 AJ_INPLAN 목록 조회 (Body)")
	@PostMapping(value = "/v1.0/getAJInplanList")
	public ApiResult<Object> getAJInplanListPost(@Valid @RequestBody KpSyncOrdDtlMonitoringReqDto reqDto) {
		return ApiResult.createResult(kpSyncOrdDtlMonitoringService.getAJInplanList(reqDto));
	}

	@Operation(summary = "ST_INPLAN 탭", description = "주문동기화 모니터링 ST_INPLAN 목록 조회")
	@GetMapping(value = "/v1.0/getSTInplanList")
	public ApiResult<Object> getSTInplanList(@Valid KpSyncOrdDtlMonitoringReqDto reqDto) {
		return ApiResult.createResult(kpSyncOrdDtlMonitoringService.getSTInplanList(reqDto));
	}

	@Operation(summary = "ST_INPLAN 탭 (POST)", description = "주문동기화 모니터링 ST_INPLAN 목록 조회 (Body)")
	@PostMapping(value = "/v1.0/getSTInplanList")
	public ApiResult<Object> getSTInplanListPost(@Valid @RequestBody KpSyncOrdDtlMonitoringReqDto reqDto) {
		return ApiResult.createResult(kpSyncOrdDtlMonitoringService.getSTInplanList(reqDto));
	}
}
