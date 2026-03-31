package cjfw.wms.tm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.tm.dto.TmDeliveryStatusByVehicleCarReqDto;
import cjfw.wms.tm.dto.TmDeliveryStatusByVehicleCarResDto;
import cjfw.wms.tm.dto.TmDeliveryStatusByVehicleMonthReqDto;
import cjfw.wms.tm.dto.TmDeliveryStatusByVehicleReqDto;
import cjfw.wms.tm.dto.TmDeliveryStatusByVehicleResDto;
import cjfw.wms.tm.service.TmDeliveryStatusByVehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : System
 * @date : 2025.01.XX
 * @description : 차량별 당일 배송현황 조회 컨트롤러
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.01.XX System      생성 </pre>
 */
@Slf4j
@RestController
@RequestMapping("api/tm/deliveryStatusByVehicle")
@RequiredArgsConstructor
@Tag(name = "차량별 당일 배송현황 조회", description = "차량별 당일 배송현황 조회 API")
public class TmDeliveryStatusByVehicleController {

    private final TmDeliveryStatusByVehicleService tmDeliveryStatusByVehicleService;

    /**
     * 차량별 당일 배송현황 조회
     * @param reqDto 요청 DTO
     * @return 차량별 당일 배송현황 조회 결과
     * @author System
     * @date 2025.01.XX
     */
    @PostMapping("/v1.0/getDeliveryStatusByVehicle")
    @Operation(summary = "차량별 당일 배송현황 조회", description = "차량별 당일 배송현황을 계약유형별로 조회합니다.")
    public ApiResult<List<TmDeliveryStatusByVehicleResDto>> getDeliveryStatusByVehicle(@RequestBody TmDeliveryStatusByVehicleReqDto reqDto) {
        log.info("차량별 당일 배송현황 조회 요청: {}", reqDto);

        List<TmDeliveryStatusByVehicleResDto> result = tmDeliveryStatusByVehicleService.getDeliveryStatusByVehicle(reqDto);
        log.info("차량별 당일 배송현황 조회 결과: {} 건", result.size());

        return ApiResult.createResult(result);
    }

    /**
     * 차량별 당월 배송현황 조회
     * @param reqDto 요청 DTO
     * @return 차량별 당월 배송현황 조회 결과
     * @author System
     * @date 2025.01.XX
     */
    @PostMapping("/v1.0/getDeliveryStatusByVehicleMonth")
    @Operation(summary = "차량별 당월 배송현황 조회", description = "차량별 당월 배송현황을 계약유형별로 조회합니다.")
    public ApiResult<List<TmDeliveryStatusByVehicleResDto>> getDeliveryStatusByVehicleMonth(@RequestBody TmDeliveryStatusByVehicleMonthReqDto reqDto) {
        log.info("차량별 당월 배송현황 조회 요청: {}", reqDto);

        List<TmDeliveryStatusByVehicleResDto> result = tmDeliveryStatusByVehicleService.getDeliveryStatusByVehicleMonth(reqDto);
        log.info("차량별 당월 배송현황 조회 결과: {} 건", result.size());

        return ApiResult.createResult(result);
    }

    /**
     * 계약유형별 차량 목록 조회
     * @param reqDto 요청 DTO
     * @return 계약유형별 차량 목록 조회 결과
     * @author System
     * @date 2025.01.XX
     */
    @PostMapping("/v1.0/getDeliveryStatusByVehicleCar")
    @Operation(summary = "계약유형별 차량 목록 조회", description = "계약유형별 차량 목록을 조회합니다.")
    public ApiResult<List<TmDeliveryStatusByVehicleCarResDto>> getDeliveryStatusByVehicleCar(@RequestBody TmDeliveryStatusByVehicleCarReqDto reqDto) {
        log.info("계약유형별 차량 목록 조회 요청: {}", reqDto);

        List<TmDeliveryStatusByVehicleCarResDto> result = tmDeliveryStatusByVehicleService.getDeliveryStatusByVehicleCar(reqDto);
        log.info("계약유형별 차량 목록 조회 결과: {} 건", result.size());

        return ApiResult.createResult(result);
    }

}

