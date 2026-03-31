package cjfw.wms.tm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.tm.dto.TmTrxCalculationReportReqDto;
import cjfw.wms.tm.dto.TmTrxCalculationReportResDto;
import cjfw.wms.tm.dto.TmTrxCalculationResultReqDto;
import cjfw.wms.tm.dto.TmTrxCalculationResultResDto;
import cjfw.wms.tm.service.TmTrxCalculationResultService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.10.20 
 * @description : 운송비정산현황 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.20    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Tag(name = "정산 > 운송비정산현황", description = "운송비정산현황")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/tm/trxcalculationresult")
public class TmTrxCalculationResultController {

	private final TmTrxCalculationResultService tmTrxCalculationResultService;

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
	public ApiResult<List<TmTrxCalculationResultResDto>> getMasterList(@Valid TmTrxCalculationResultReqDto reqDto) {
		return ApiResult.createResult(tmTrxCalculationResultService.getMasterList(reqDto));
	}
	
    /**
     * @description : 월 기준 근무일수 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.20    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "월 기준 근무일수 조회", description = "월 기준 근무일수 조회")
    @PostMapping(value = "/v1.0/getWorkDay")
    public ApiResult<List<TmTrxCalculationResultResDto>> getWorkDay(@Valid TmTrxCalculationResultReqDto reqDto) {
        return ApiResult.createResult(tmTrxCalculationResultService.getWorkDay(reqDto));
    }

}