package cjfw.wms.tm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.tm.dto.TmCarTrackQryLogListReqDto;
import cjfw.wms.tm.dto.TmCarTrackQryLogListResDto;
import cjfw.wms.tm.dto.TmVehicleConditionCountReqDto;
import cjfw.wms.tm.dto.TmVehicleConditionCountResDto;
import cjfw.wms.tm.dto.TmVehicleDetailMonitoringReqDto;
import cjfw.wms.tm.dto.TmVehicleDetailMonitoringResDto;
import cjfw.wms.tm.dto.TmVehicleGroupMonitoringReqDto;
import cjfw.wms.tm.dto.TmVehicleGroupMonitoringResDto;
import cjfw.wms.tm.dto.TmVehicleMonitoringReqDto;
import cjfw.wms.tm.dto.TmVehicleMonitoringResDto;
import cjfw.wms.tm.dto.TmVehicleStatusCountReqDto;
import cjfw.wms.tm.dto.TmVehicleStatusCountResDto;
import cjfw.wms.tm.service.TmLocationMonitorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : OhEunbeom
 * @date : 2025.09.10
 * @description : 센터별 차량 모니터링 조회
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.10 OhEunbeom      생성 </pre>
 */
@Tag(name = "운송관리 > 센터별 차량 모니터링", description = "센터별 차량 모니터링 조회")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/tm/locationMonitor")
public class TmLocationMonitorController {

	private final TmLocationMonitorService tmLocationMonitorService;

	/**
	 * @description : 센터별 차량수 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.10 OhEunbeom      생성 </pre>
	 */
	@Operation(summary = "센터별 차량수 조회", description = "센터별 차량수 조회")
	@PostMapping(value = "/v1.0/getVehicleMonitoringList")
	public ApiResult<List<TmVehicleMonitoringResDto>> getVehicleMonitoringList(@RequestBody TmVehicleMonitoringReqDto tmVehicleMonitoringReqDto) {
		return ApiResult.createResult(tmLocationMonitorService.getVehicleMonitoringList(tmVehicleMonitoringReqDto));
	}

	/**
	 * @description : 센터 조차별 차량수 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.10 System Generated      생성 </pre>
	 */
	@Operation(summary = "센터 조차별 차량수 조회", description = "센터 조차별 차량수 조회")
	@PostMapping(value = "/v1.0/getVehicleGroupMonitoringList")
	public ApiResult<List<TmVehicleGroupMonitoringResDto>> getVehicleGroupMonitoringList(@RequestBody TmVehicleGroupMonitoringReqDto tmVehicleGroupMonitoringReqDto) {
		return ApiResult.createResult(tmLocationMonitorService.getVehicleGroupMonitoringList(tmVehicleGroupMonitoringReqDto));
	}

	/**
	 * @description : 센터별 조차별 차량 모니터링 정보 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.10 OhEunbeom      생성 </pre>
	 */
	@Operation(summary = "센터별 조차별 차량 모니터링 정보 조회", description = "센터별 조차별 차량 모니터링 정보 조회")
	@PostMapping(value = "/v1.0/getVehicleDetailMonitoringList")
	public ApiResult<List<TmVehicleDetailMonitoringResDto>> getVehicleDetailMonitoringList(@RequestBody TmVehicleDetailMonitoringReqDto tmVehicleDetailMonitoringReqDto) {
		return ApiResult.createResult(tmLocationMonitorService.getVehicleDetailMonitoringList(tmVehicleDetailMonitoringReqDto));
	}

	/**
	 * @description : 배송일자별 차량 모니터링 전체 카운트 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.10 OhEunbeom      생성 </pre>
	 */
	@Operation(summary = "배송상태별 차량 모니터링 전체 카운트 조회", description = "배송상태별 차량 모니터링 전체 카운트 조회")
	@PostMapping(value = "/v1.0/getVehicleStatusCountList")
	public ApiResult<List<TmVehicleStatusCountResDto>> getVehicleStatusCountList(@RequestBody TmVehicleStatusCountReqDto tmVehicleStatusCountReqDto) {
		return ApiResult.createResult(tmLocationMonitorService.getVehicleStatusCountList(tmVehicleStatusCountReqDto));
	}

	/**
	 * @description : 배송일자별 차량 모니터링 조건 카운트 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.10 OhEunbeom      생성 </pre>
	 */
	@Operation(summary = "배송상태별 차량 모니터링 조건 카운트 조회", description = "배송상태별 차량 모니터링 조건 카운트 조회")
	@PostMapping(value = "/v1.0/getVehicleConditionCountList")
	public ApiResult<List<TmVehicleConditionCountResDto>> getVehicleConditionCountList(@RequestBody TmVehicleConditionCountReqDto tmVehicleConditionCountReqDto) {
		return ApiResult.createResult(tmLocationMonitorService.getVehicleConditionCountList(tmVehicleConditionCountReqDto));
	}

	/**
	 * @description : 차량위치 조회 이력 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.12 OhEunbeom      생성 </pre>
	 */
	@Operation(summary = "차량위치 조회 이력 조회", description = "차량위치 조회 이력 조회")
	@PostMapping(value = "/v1.0/getCarTrackQryLogList")
	public ApiResult<List<TmCarTrackQryLogListResDto>> getCarTrackQryLogList(@RequestBody TmCarTrackQryLogListReqDto tmCarTrackQryLogListReqDto) {
		return ApiResult.createResult(tmLocationMonitorService.getCarTrackQryLogList(tmCarTrackQryLogListReqDto));
	}

	/**
	 * @description : 센터별 차량수 조회 (Carrier 버전)
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.12 OhEunbeom      생성 </pre>
	 */
	@Operation(summary = "센터별 차량수 조회 (Carrier)", description = "센터별 차량수 조회 (TM_CARRIER_INPLAN 사용)")
	@PostMapping(value = "/v1.0/getVehicleMonitoringListByCarrier")
	public ApiResult<List<TmVehicleMonitoringResDto>> getVehicleMonitoringListByCarrier(@RequestBody TmVehicleMonitoringReqDto tmVehicleMonitoringReqDto) {
		return ApiResult.createResult(tmLocationMonitorService.getVehicleMonitoringListByCarrier(tmVehicleMonitoringReqDto));
	}

	/**
	 * @description : 센터 조차별 차량수 조회 (Carrier 버전)
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.12 OhEunbeom      생성 </pre>
	 */
	@Operation(summary = "센터 조차별 차량수 조회 (Carrier)", description = "센터 조차별 차량수 조회 (TM_CARRIER_INPLAN 사용)")
	@PostMapping(value = "/v1.0/getVehicleGroupMonitoringListByCarrier")
	public ApiResult<List<TmVehicleGroupMonitoringResDto>> getVehicleGroupMonitoringListByCarrier(@RequestBody TmVehicleGroupMonitoringReqDto tmVehicleGroupMonitoringReqDto) {
		return ApiResult.createResult(tmLocationMonitorService.getVehicleGroupMonitoringListByCarrier(tmVehicleGroupMonitoringReqDto));
	}

	/**
	 * @description : 센터별 조차별 차량 모니터링 정보 조회 (Carrier 버전)
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.12 OhEunbeom      생성 </pre>
	 */
	@Operation(summary = "센터별 조차별 차량 모니터링 정보 조회 (Carrier)", description = "센터별 조차별 차량 모니터링 정보 조회 (TM_CARRIER_INPLAN 사용)")
	@PostMapping(value = "/v1.0/getVehicleDetailMonitoringListByCarrier")
	public ApiResult<List<TmVehicleDetailMonitoringResDto>> getVehicleDetailMonitoringListByCarrier(@RequestBody TmVehicleDetailMonitoringReqDto tmVehicleDetailMonitoringReqDto) {
		return ApiResult.createResult(tmLocationMonitorService.getVehicleDetailMonitoringListByCarrier(tmVehicleDetailMonitoringReqDto));
	}

	/**
	 * @description : 배송일자별 차량 모니터링 전체 카운트 조회 (Carrier 버전)
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.12 OhEunbeom      생성 </pre>
	 */
	@Operation(summary = "배송상태별 차량 모니터링 전체 카운트 조회 (Carrier)", description = "배송상태별 차량 모니터링 전체 카운트 조회 (TM_CARRIER_INPLAN 사용)")
	@PostMapping(value = "/v1.0/getVehicleStatusCountListByCarrier")
	public ApiResult<List<TmVehicleStatusCountResDto>> getVehicleStatusCountListByCarrier(@RequestBody TmVehicleStatusCountReqDto tmVehicleStatusCountReqDto) {
		return ApiResult.createResult(tmLocationMonitorService.getVehicleStatusCountListByCarrier(tmVehicleStatusCountReqDto));
	}

	/**
	 * @description : 배송일자별 차량 모니터링 조건 카운트 조회 (Carrier 버전)
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.12 OhEunbeom      생성 </pre>
	 */
	@Operation(summary = "배송상태별 차량 모니터링 조건 카운트 조회 (Carrier)", description = "배송상태별 차량 모니터링 조건 카운트 조회 (TM_CARRIER_INPLAN 사용)")
	@PostMapping(value = "/v1.0/getVehicleConditionCountListByCarrier")
	public ApiResult<List<TmVehicleConditionCountResDto>> getVehicleConditionCountListByCarrier(@RequestBody TmVehicleConditionCountReqDto tmVehicleConditionCountReqDto) {
		return ApiResult.createResult(tmLocationMonitorService.getVehicleConditionCountListByCarrier(tmVehicleConditionCountReqDto));
	}

}
