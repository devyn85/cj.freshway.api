package cjfw.wms.tm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.tm.dto.TmTrxCalculationReqDto;
import cjfw.wms.tm.dto.TmTrxCalculationResDto;
import cjfw.wms.tm.service.TmTrxCalculationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.10.10 
 * @description : 운송비정산 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.10    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Tag(name = "정산 > 운송비정산", description = "운송비정산")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping({"api/tm/trxcalculation", "ltx/tm/trxcalculation"})
public class TmTrxCalculationController {

    private final TmTrxCalculationService tmTrxCalculationService;

    /**
     * @description : 운송비정산 내역 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
      * 2025.10.10    KimSunHo(sunhokim6229@cj.net)   생성
      */
    @Operation(summary = "운송비정산 내역 조회", description = "운송비정산 내역 조회")
    @PostMapping(value = "/v1.0/getMasterList")
    public ApiResult<List<TmTrxCalculationResDto>> getMasterList(@Valid TmTrxCalculationReqDto reqDto) {
        return ApiResult.createResult(tmTrxCalculationService.getMasterList(reqDto));
    }
	
    /**
     * @description : 월 기준 근무일수 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.10    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "월 기준 근무일수 조회", description = "월 기준 근무일수 조회")
    @PostMapping(value = "/v1.0/getWorkDay")
    public ApiResult<List<TmTrxCalculationResDto>> getWorkDay(@Valid TmTrxCalculationReqDto reqDto) {
        return ApiResult.createResult(tmTrxCalculationService.getWorkDay(reqDto));
    }
    
    /**
     * @description : 차수별 착지 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.10    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "차수별 착지 조회", description = "차수별 착지 조회")
    @PostMapping(value = "/v1.0/getCostPriorityList")
    public ApiResult<List<TmTrxCalculationResDto>> getCostPriorityList(@Valid TmTrxCalculationReqDto reqDto) {
        return ApiResult.createResult(tmTrxCalculationService.getCostPriorityList(reqDto));
    }
    
    /**
     * @description : 차수별 권역이동 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.10    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "차수별 권역이동 조회", description = "차수별 권역이동 조회")
    @PostMapping(value = "/v1.0/getRegnMoveList")
    public ApiResult<List<TmTrxCalculationResDto>> getRegnMoveList(@Valid TmTrxCalculationReqDto reqDto) {
        return ApiResult.createResult(tmTrxCalculationService.getRegnMoveList(reqDto));
    }
	
    /**
     * @description : 운송비정산
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.10    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "운송비정산", description = "운송비정산")
    @PostMapping(value = "/v1.0/saveCalculation")
    public ApiResult<String> saveCalculation(@RequestBody TmTrxCalculationReqDto reqDto) {
        return ApiResult.createResult(tmTrxCalculationService.saveCalculation(reqDto));
    }
    
    /**
     * @description : 운송비정산
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.10    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "운송비정산 마감 ", description = "운송비정산 마감 ")
    @PostMapping(value = "/v1.0/saveClosing")
    public ApiResult<String> saveClosing(@RequestBody TmTrxCalculationReqDto reqDto) {
        return ApiResult.createResult(tmTrxCalculationService.saveClosing(reqDto));
    }

}