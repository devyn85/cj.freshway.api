package cjfw.wms.kp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.kp.dto.KpSyncStockMonitoringReqDto;
import cjfw.wms.kp.dto.KpSyncStockMonitoringResDto;
import cjfw.wms.kp.service.KpSyncStockMonitoringService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * KP 재고동기화 모니터링 Controller
 * 탭: TOBE(신규), ASISTOBE(차이) - existsYn 파라미터로 구분
 * /api/kp/syncStockMonitoring/v1.0/getStockMonitoringList
 */
@Tag(name = "KP > 재고동기화 모니터링", description = "KP 재고동기화 모니터링 (탭: TOBE/ASISTOBE)")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/kp/syncStockMonitoring")
public class KpSyncStockMonitoringController {

	private final KpSyncStockMonitoringService kpSyncStockMonitoringService;

	@Operation(summary = "재고동기화 모니터링 목록", description = "재고동기화 모니터링 목록 조회 (탭: existsYn 1=TOBE 신규, 2=ASISTOBE 차이)")
	@GetMapping(value = "/v1.0/getStockMonitoringList")
	public ApiResult<Object> getStockMonitoringList(@Valid KpSyncStockMonitoringReqDto reqDto) {
		return ApiResult.createResult(kpSyncStockMonitoringService.getStockMonitoringList(reqDto));
	}

	@Operation(summary = "재고동기화 모니터링 목록 (POST)", description = "재고동기화 모니터링 목록 조회 (Body)")
	@PostMapping(value = "/v1.0/getStockMonitoringList")
	public ApiResult<Object> getStockMonitoringListPost(@Valid @RequestBody KpSyncStockMonitoringReqDto reqDto) {
		return ApiResult.createResult(kpSyncStockMonitoringService.getStockMonitoringList(reqDto));
	}
}
