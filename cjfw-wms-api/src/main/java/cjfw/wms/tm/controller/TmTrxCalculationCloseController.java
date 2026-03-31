package cjfw.wms.tm.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.tm.dto.TmTrxCalculationCloseReqDto;
import cjfw.wms.tm.dto.TmTrxCalculationReqDto;
import cjfw.wms.tm.service.TmTrxCalculationCloseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2026.02.04 
 * @description : 운송료정산 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2026.02.04 ParkJinWoo 생성
 */
@Tag(name = "정산 > 운송비정산 >운송료정산", description = "운송료정산")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/tm/trxCalculationClose")
public class TmTrxCalculationCloseController {

	private final TmTrxCalculationCloseService tmTrxCalculationCloseService;


	/**
	 * @description : "운송료정산 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.02.04 ParkJinWoo 생성
	 */
	@Operation(summary = "운송료정산마감 조회", description = "운송료정산마감 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<Object> getMasterList(@Valid @RequestBody TmTrxCalculationCloseReqDto reqDto) {
		return ApiResult.createResult(tmTrxCalculationCloseService.getMasterList(reqDto));
	}
	
   /**
    * @description : 운송료정산 마감 기능을 구현한 Method 
    * @issues : 
    * ----------------------------------------------------------- 
    * DATE AUTHOR MAJOR_ISSUE 
    * ----------------------------------------------------------- 
    * 2026.02.04 ParkJinWoo 생성
    */
    @Operation(summary = "운송료정산 마감 ", description = "운송료정산 마감")
    @PostMapping(value = "/v1.0/saveClosing")
    public ApiResult<String> saveClosing(@RequestBody TmTrxCalculationCloseReqDto reqDto) {
        return ApiResult.createResult(tmTrxCalculationCloseService.saveClosing(reqDto));
    }
    
    /**
     * @description : 운송료정산 월정산 기능을 구현한 Method 
     * @issues : 
     * ----------------------------------------------------------- 
     * DATE AUTHOR MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2026.03.10 ParkJinWoo 생성
     */
     @Operation(summary = "운송료정산 월정산 ", description = "운송료정산 월정산")
     @PostMapping(value = "/v1.0/saveCalculation")
     public ApiResult<String> saveCalculation(@RequestBody TmTrxCalculationCloseReqDto reqDto) {
         return ApiResult.createResult(tmTrxCalculationCloseService.saveCalculation(reqDto));
     }
}