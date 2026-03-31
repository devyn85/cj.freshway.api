package cjfw.wms.tm.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.tm.dto.tempMonitor.TmTempGraphReqDto;
import cjfw.wms.tm.dto.tempMonitor.TmTempMonitorByCarReqDto;
import cjfw.wms.tm.dto.tempMonitor.TmTempMonitorDescReqDto;
import cjfw.wms.tm.dto.tempMonitor.TmTempMonitorSummaryReqDto;
import cjfw.wms.tm.service.TmLocationMonitorSummaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : System Generated
 * @date : 2025.01.27 
 * @description : 온도 모니터링 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.01.27 System Generated 생성
 */

@Tag(name = "배차 > 온도모니터링", description = "온도 모니터링")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/tm/tmLocationMonitorSummary")
public class TmLocationMonitorSummaryController {

    private final TmLocationMonitorSummaryService service;

    /**
     * @description : 차량별 온도 모니터링 조회 기능을 구현한 Method
     * @issues :
     * -----------------------------------------------------------
     * DATE AUTHOR MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.01.27 System Generated 생성
     */
    @Operation(summary = "차량별 온도 모니터링 조회", description = "차량별 온도 모니터링 조회")
    @PostMapping(value = "/v1.0/getTempMonitorByCar")
    public ApiResult<Object> getTempMonitorByCar(@Valid @RequestBody TmTempMonitorByCarReqDto reqDto) {
        return ApiResult.createResult(service.getTempMonitorByCar(reqDto));
    }

    /**
     * @description : 온도 그래프 조회 기능을 구현한 Method
     * @issues :
     * -----------------------------------------------------------
     * DATE AUTHOR MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.01.27 System Generated 생성
     */
    @Operation(summary = "온도 그래프 조회", description = "온도 그래프 조회")
    @PostMapping(value = "/v1.0/getTempGraph")
    public ApiResult<Object> getTempGraph(@Valid @RequestBody TmTempGraphReqDto reqDto) {
        return ApiResult.createResult(service.getTempGraph(reqDto));
    }

    /**
     * @description : 온도 모니터링 상세 조회 기능을 구현한 Method
     * @issues :
     * -----------------------------------------------------------
     * DATE AUTHOR MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.01.27 System Generated 생성
     */
    @Operation(summary = "온도 모니터링 상세 조회", description = "온도 모니터링 상세 조회")
    @PostMapping(value = "/v1.0/getTempMonitorDesc")
    public ApiResult<Object> getTempMonitorDesc(@Valid @RequestBody TmTempMonitorDescReqDto reqDto) {
        return ApiResult.createResult(service.getTempMonitorDesc(reqDto));
    }

    /**
     * @description : 온도 모니터링 요약 조회 기능을 구현한 Method
     * @issues :
     * -----------------------------------------------------------
     * DATE AUTHOR MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.01.27 System Generated 생성
     */
    @Operation(summary = "온도 모니터링 요약 조회", description = "온도 모니터링 요약 조회")
    @PostMapping(value = "/v1.0/getTempMonitorSummary")
    public ApiResult<Object> getTempMonitorSummary(@Valid @RequestBody TmTempMonitorSummaryReqDto reqDto) {
        return ApiResult.createResult(service.getTempMonitorSummary(reqDto));
    }

}
