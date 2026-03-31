package cjfw.wms.tm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.tm.dto.TmActualRouteReqDto;
import cjfw.wms.tm.dto.TmActualRouteResDto;
import cjfw.wms.tm.dto.TmCustomerLocationReqDto;
import cjfw.wms.tm.dto.TmCustomerLocationResDto;
import cjfw.wms.tm.dto.TmCustomerManagementReqDto;
import cjfw.wms.tm.dto.TmCustomerManagementResDto;
import cjfw.wms.tm.dto.TmPlanRouteReqDto;
import cjfw.wms.tm.dto.TmPlanRouteResDto;
import cjfw.wms.tm.dto.TmVehicleLocationMonitoringReqDto;
import cjfw.wms.tm.dto.TmVehicleLocationReqDto;
import cjfw.wms.tm.dto.TmVehicleMonitoringIntegratedResDto;
import cjfw.wms.tm.dto.TmWeatherInfoReqDto;
import cjfw.wms.tm.dto.TmWeatherInfoResDto;
import cjfw.wms.tm.service.TmLocationMonitorByCarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : OhEunbeom
 * @date : 2025.09.18
 * @description : 차량별 (예상, 실제) 이동 경로 및 거래처 마커 조회
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.18 OhEunbeom      생성 </pre>
 */
@Tag(name = "운송관리 > 차량별 (예상, 실제) 이동 경로 및 거래처 마커", description = "차량별 (예상, 실제) 이동 경로 및 거래처 마커 조회")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/tm/locationMonitorByCar")
public class TmLocationMonitorByCarController {

    private final TmLocationMonitorByCarService tmLocationMonitorByCarService;

    @Operation(summary = "차량 모니터링 차량마커 조회 (검색조건)", description = "차량 모티러링 검색조건 기반으로 차량번호와 배송상태를 조회한 후, 해당 차량들의 위치 정보를 조회합니다.")
    @PostMapping("/v1.0/getVehicleStatusList")
    public ApiResult<List<TmVehicleMonitoringIntegratedResDto>> getVehicleStatusList(@RequestBody TmVehicleLocationMonitoringReqDto reqDto) {
        log.info("=== getVehicleStatusList (통합조회) 시작 ===");
        log.info("요청 파라미터: {}", reqDto);

        List<TmVehicleMonitoringIntegratedResDto> result = tmLocationMonitorByCarService.getVehicleStatusList(reqDto);

        log.info("조회 결과 건수: {}", result.size());
        log.info("=== getVehicleStatusList (통합조회) 종료 ===");

        return ApiResult.createResult(result);
    }

    @Operation(summary = "차량 모니터링 차량마커 조회 (차량번호)", description = "배송일자와 차량번호 리스트로 해당 차량들의 최근 위치 정보를 조회합니다.")
    @PostMapping("/v1.0/getVehicleLocationList")
    public ApiResult<List<TmVehicleMonitoringIntegratedResDto>> getVehicleLocationList(@RequestBody TmVehicleLocationReqDto reqDto) {
        log.info("=== getVehicleLocationList 시작 ===");
        log.info("요청 파라미터: {}", reqDto);

        List<TmVehicleMonitoringIntegratedResDto> result = tmLocationMonitorByCarService.getVehicleLocationIntegratedList(reqDto);

        log.info("조회 결과 건수: {}", result.size());
        log.info("=== getVehicleLocationList 종료 ===");

        return ApiResult.createResult(result);
    }

    @Operation(summary = "계획 경로 조회", description = "배송일자, 차량번호, 회차번호로 해당 차량의 계획 경로 정보를 조회합니다.")
    @PostMapping("/v1.0/getPlanRouteList")
    public ApiResult<List<TmPlanRouteResDto>> getPlanRouteList(@RequestBody TmPlanRouteReqDto reqDto) {
        List<TmPlanRouteResDto> result = tmLocationMonitorByCarService.getPlanRouteList(reqDto);
        return ApiResult.createResult(result);
    }

    @Operation(summary = "실제 이동경로 조회", description = "배송일자, 차량번호, 회차로 해당 차량의 실제 이동경로 정보를 조회합니다.")
    @PostMapping("/v1.0/getActualRouteList")
    public ApiResult<List<TmActualRouteResDto>> getActualRouteList(@RequestBody TmActualRouteReqDto reqDto) {
        log.info("=== getActualRouteList 시작 ===");
        log.info("요청 파라미터: {}", reqDto);

        List<TmActualRouteResDto> result = tmLocationMonitorByCarService.getActualRouteList(reqDto);

        log.info("조회 결과 건수: {}", result.size());
        log.info("=== getActualRouteList 종료 ===");

        return ApiResult.createResult(result);
    }

    @Operation(summary = "실착지 위치 조회", description = "배송일자, 차량번호, 회차정보로 해당 차량의 실착지 위치 정보를 조회합니다.")
    @PostMapping("/v1.0/getCustomerLocationList")
    public ApiResult<List<TmCustomerLocationResDto>> getCustomerLocationList(@RequestBody TmCustomerLocationReqDto reqDto) {
        log.info("=== getCustomerLocationList 시작 ===");
        log.info("요청 파라미터: {}", reqDto);

        List<TmCustomerLocationResDto> result = tmLocationMonitorByCarService.getCustomerLocationList(reqDto);

        log.info("조회 결과 건수: {}", result.size());
        log.info("=== getCustomerLocationList 종료 ===");

        return ApiResult.createResult(result);
    }

    @Operation(summary = "관리처 목록 조회", description = "실착지키로 해당 실착지의 관리처 목록과 상세 정보를 조회합니다.")
    @PostMapping("/v1.0/getCustomerManagementList")
    public ApiResult<List<TmCustomerManagementResDto>> getCustomerManagementList(@RequestBody TmCustomerManagementReqDto reqDto) {
        log.info("=== getCustomerManagementList 시작 ===");
        log.info("요청 파라미터: {}", reqDto);

        List<TmCustomerManagementResDto> result = tmLocationMonitorByCarService.getCustomerManagementList(reqDto);

        log.info("조회 결과 건수: {}", result.size());
        log.info("=== getCustomerManagementList 종료 ===");

        return ApiResult.createResult(result);
    }

    @Operation(summary = "날씨정보 조회", description = "현재 시간 기준으로 날씨정보를 조회합니다.")
    @PostMapping("/v1.0/getWeatherInfoList")
    public ApiResult<List<TmWeatherInfoResDto>> getWeatherInfoList(@RequestBody TmWeatherInfoReqDto reqDto) {
        log.info("=== getWeatherInfoList 시작 ===");
        log.info("요청 파라미터: {}", reqDto);

        List<TmWeatherInfoResDto> result = tmLocationMonitorByCarService.getWeatherInfoList(reqDto);

        log.info("조회 결과 건수: {}", result.size());
        log.info("=== getWeatherInfoList 종료 ===");

        return ApiResult.createResult(result);
    }

    @Operation(summary = "차량 모니터링 차량마커 조회 (검색조건) - Carrier", description = "차량 모티러링 검색조건 기반으로 차량번호와 배송상태를 조회한 후, 해당 차량들의 위치 정보를 조회합니다. (TM_CARRIER_INPLAN 사용)")
    @PostMapping("/v1.0/getVehicleStatusListByCarrier")
    public ApiResult<List<TmVehicleMonitoringIntegratedResDto>> getVehicleStatusListByCarrier(@RequestBody TmVehicleLocationMonitoringReqDto reqDto) {
        log.info("=== getVehicleStatusListByCarrier (통합조회) 시작 ===");
        log.info("요청 파라미터: {}", reqDto);

        List<TmVehicleMonitoringIntegratedResDto> result = tmLocationMonitorByCarService.getVehicleStatusListByCarrier(reqDto);

        log.info("조회 결과 건수: {}", result.size());
        log.info("=== getVehicleStatusListByCarrier (통합조회) 종료 ===");

        return ApiResult.createResult(result);
    }

    @Operation(summary = "차량 모니터링 차량마커 조회 (차량번호) - Carrier", description = "배송일자와 차량번호 리스트로 해당 차량들의 최근 위치 정보를 조회합니다. (TM_CARRIER_INPLAN 사용)")
    @PostMapping("/v1.0/getVehicleLocationListByCarrier")
    public ApiResult<List<TmVehicleMonitoringIntegratedResDto>> getVehicleLocationListByCarrier(@RequestBody TmVehicleLocationReqDto reqDto) {
        log.info("=== getVehicleLocationListByCarrier 시작 ===");
        log.info("요청 파라미터: {}", reqDto);

        List<TmVehicleMonitoringIntegratedResDto> result = tmLocationMonitorByCarService.getVehicleLocationIntegratedListByCarrier(reqDto);

        log.info("조회 결과 건수: {}", result.size());
        log.info("=== getVehicleLocationListByCarrier 종료 ===");

        return ApiResult.createResult(result);
    }

    @Operation(summary = "실제 이동경로 조회 - Carrier", description = "배송일자, 차량번호, 회차로 해당 차량의 실제 이동경로 정보를 조회합니다. (TM_CARRIER_INPLAN 사용)")
    @PostMapping("/v1.0/getActualRouteListByCarrier")
    public ApiResult<List<TmActualRouteResDto>> getActualRouteListByCarrier(@RequestBody TmActualRouteReqDto reqDto) {
        log.info("=== getActualRouteListByCarrier 시작 ===");
        log.info("요청 파라미터: {}", reqDto);

        List<TmActualRouteResDto> result = tmLocationMonitorByCarService.getActualRouteListByCarrier(reqDto);

        log.info("조회 결과 건수: {}", result.size());
        log.info("=== getActualRouteListByCarrier 종료 ===");

        return ApiResult.createResult(result);
    }

    @Operation(summary = "실착지 위치 조회 - Carrier", description = "배송일자, 차량번호, 회차정보로 해당 차량의 실착지 위치 정보를 조회합니다. (TM_CARRIER_INPLAN 사용)")
    @PostMapping("/v1.0/getCustomerLocationListByCarrier")
    public ApiResult<List<TmCustomerLocationResDto>> getCustomerLocationListByCarrier(@RequestBody TmCustomerLocationReqDto reqDto) {
        log.info("=== getCustomerLocationListByCarrier 시작 ===");
        log.info("요청 파라미터: {}", reqDto);

        List<TmCustomerLocationResDto> result = tmLocationMonitorByCarService.getCustomerLocationListByCarrier(reqDto);

        log.info("조회 결과 건수: {}", result.size());
        log.info("=== getCustomerLocationListByCarrier 종료 ===");

        return ApiResult.createResult(result);
    }

    @Operation(summary = "관리처 목록 조회 - Carrier", description = "실착지키로 해당 실착지의 관리처 목록과 상세 정보를 조회합니다. (TM_CARRIER_INPLAN 사용)")
    @PostMapping("/v1.0/getCustomerManagementListByCarrier")
    public ApiResult<List<TmCustomerManagementResDto>> getCustomerManagementListByCarrier(@RequestBody TmCustomerManagementReqDto reqDto) {
        log.info("=== getCustomerManagementListByCarrier 시작 ===");
        log.info("요청 파라미터: {}", reqDto);

        List<TmCustomerManagementResDto> result = tmLocationMonitorByCarService.getCustomerManagementListByCarrier(reqDto);

        log.info("조회 결과 건수: {}", result.size());
        log.info("=== getCustomerManagementListByCarrier 종료 ===");

        return ApiResult.createResult(result);
    }

}
