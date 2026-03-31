package cjfw.wms.tm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.tm.dto.TmVehicleCustomerListReqDto;
import cjfw.wms.tm.dto.TmVehicleCustomerListResDto;
import cjfw.wms.tm.dto.TmVehicleInfoReqDto;
import cjfw.wms.tm.dto.TmVehicleInfoResDto;
import cjfw.wms.tm.service.TmLocationMonitorCustomerListByCarPopupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : OhEunbeom
 * @date : 2025.09.15
 * @description : 거래처 목록 팝업 조회
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.15 OhEunbeom      생성 </pre>
 */
@Tag(name = "운송관리 > 거래처 목록 팝업", description = "거래처 목록 팝업 조회")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/tm/locationMonitorCustomerListByCarPopup")
public class TmLocationMonitorCustomerListByCarPopupController {

	private final TmLocationMonitorCustomerListByCarPopupService tmLocationMonitorCustomerListByCarPopupService;

	/**
	 * @description : 차량 상세 정보 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.15 OhEunbeom                생성 </pre>
	 */
	@Operation(summary = "차량 상세 정보 조회", description = "선택한 차량의 상세 정보를 조회합니다.")
	@PostMapping(value = "/v1.0/getVehicleInfo")
	public ApiResult<TmVehicleInfoResDto> getVehicleInfo(@RequestBody TmVehicleInfoReqDto tmVehicleInfoReqDto) {
		return ApiResult.createResult(tmLocationMonitorCustomerListByCarPopupService.getVehicleInfo(tmVehicleInfoReqDto));
	}

	/**
	 * @description : 차량별 거래처 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.15 OhEunbeom                생성 </pre>
	 */
	@Operation(summary = "차량별 거래처 목록 조회", description = "선택한 차량의 거래처 목록을 시간순으로 조회합니다.")
	@PostMapping(value = "/v1.0/getVehicleCustomerList")
	public ApiResult<List<TmVehicleCustomerListResDto>> getVehicleCustomerList(@RequestBody TmVehicleCustomerListReqDto tmVehicleCustomerListReqDto) {
		return ApiResult.createResult(tmLocationMonitorCustomerListByCarPopupService.getVehicleCustomerList(tmVehicleCustomerListReqDto));
	}

	/**
	 * @description : 차량 상세 정보 조회 (Carrier 버전)
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.12 OhEunbeom                생성 </pre>
	 */
	@Operation(summary = "차량 상세 정보 조회 (Carrier)", description = "선택한 차량의 상세 정보를 조회합니다. (TM_CARRIER_INPLAN 사용)")
	@PostMapping(value = "/v1.0/getVehicleInfoByCarrier")
	public ApiResult<TmVehicleInfoResDto> getVehicleInfoByCarrier(@RequestBody TmVehicleInfoReqDto tmVehicleInfoReqDto) {
		return ApiResult.createResult(tmLocationMonitorCustomerListByCarPopupService.getVehicleInfoByCarrier(tmVehicleInfoReqDto));
	}

	/**
	 * @description : 차량별 거래처 목록 조회 (Carrier 버전)
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.12 OhEunbeom                생성 </pre>
	 */
	@Operation(summary = "차량별 거래처 목록 조회 (Carrier)", description = "선택한 차량의 거래처 목록을 시간순으로 조회합니다. (TM_CARRIER_INPLAN 사용)")
	@PostMapping(value = "/v1.0/getVehicleCustomerListByCarrier")
	public ApiResult<List<TmVehicleCustomerListResDto>> getVehicleCustomerListByCarrier(@RequestBody TmVehicleCustomerListReqDto tmVehicleCustomerListReqDto) {
		return ApiResult.createResult(tmLocationMonitorCustomerListByCarPopupService.getVehicleCustomerListByCarrier(tmVehicleCustomerListReqDto));
	}

}
