package cjfw.wms.kp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.kp.dto.KpSyncOrdMonitoringDetailReqDto;
import cjfw.wms.kp.dto.KpSyncOrdMonitoringMasterReqDto;
import cjfw.wms.kp.service.KpSyncOrdMonitoringService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * KP 주문동기화 모니터링 Controller
 * /api/kp/syncOrdMonitoring/v1.0/getMasterList
 * /api/kp/syncOrdMonitoring/v1.0/getDetailList
 */
@Tag(name = "KP > 주문동기화 모니터링", description = "KP 주문동기화 모니터링")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/kp/syncOrdMonitoring")
public class KpSyncOrdMonitoringController {

	private final KpSyncOrdMonitoringService kpSyncOrdMonitoringService;

	@Operation(summary = "Header 목록", description = "주문동기화 모니터링 Header 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<Object> getMasterList(@Valid @RequestBody KpSyncOrdMonitoringMasterReqDto reqDto) {
		return ApiResult.createResult(kpSyncOrdMonitoringService.getMasterList(reqDto));
	}

	@Operation(summary = "Detail 목록", description = "Header에서 DOCNO 선택 시 Detail 조회")
	@GetMapping(value = "/v1.0/getDetailList")
	public ApiResult<Object> getDetailList(@Valid KpSyncOrdMonitoringDetailReqDto reqDto, Page<?> page) {
		return ApiResult.createResult(kpSyncOrdMonitoringService.getDetailList(reqDto, page));
	}
}
