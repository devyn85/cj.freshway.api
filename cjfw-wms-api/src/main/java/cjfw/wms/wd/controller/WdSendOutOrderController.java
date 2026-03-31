package cjfw.wms.wd.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.cm.dto.CmSendEmailReqDto;
import cjfw.wms.tm.dto.TmEntityRuleMasterListReqDto;
import cjfw.wms.wd.dto.WdSendOutOrderPrintResDto;
import cjfw.wms.wd.dto.WdSendOutOrderReqDto;
import cjfw.wms.wd.dto.WdSendOutOrderResDto;
import cjfw.wms.wd.service.WdSendOutOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.06.19 
 * @description : 외부비축출고지시서 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.19    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Tag(name = "출고 > 출고작업 > 외부비축출고지시서", description = "외부비축출고지시서 조회")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/wd/sendoutorder")
public class WdSendOutOrderController {

	private final WdSendOutOrderService wdSendOutOrderService;

	/**
	 * @description : 오더있는 출고지시서 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.19    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	@Operation(summary = "오더있는 출고지시서 목록 조회", description = "오더있는 출고지시서 목록 조회")
	@GetMapping(value = "/v1.0/getDatalistOrder")
	public ApiResult<List<WdSendOutOrderResDto>> getDatalistOrder(@Valid WdSendOutOrderReqDto wdSendOutOrderReqDto) {
		return ApiResult.createResult(wdSendOutOrderService.getDatalistOrder(wdSendOutOrderReqDto));
	}
	
	
	/**
     * @description : 오더없는 출고지시서 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.06.19    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "오더없는 출고지시서 목록 조회", description = "오더없는 출고지시서 목록 조회")
    @GetMapping(value = "/v1.0/getDatalistNotOrder")
    public ApiResult<List<WdSendOutOrderResDto>> getDatalistNotOrder(@Valid WdSendOutOrderReqDto wdSendOutOrderReqDto) {
        return ApiResult.createResult(wdSendOutOrderService.getDatalistNotOrder(wdSendOutOrderReqDto));
    }
	
    
    /**
     * @throws Exception
     * @description : 수기출고 삭제
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.06.19    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "수기출고 삭제", description = "수기출고 삭제")
    @PostMapping(value = "/v1.0/deleteOrder")
    public ApiResult<String> deleteOrder(@RequestBody WdSendOutOrderReqDto wdSendOutOrderReqDto) throws Exception {
    	return ApiResult.createResult(wdSendOutOrderService.deleteOrder(wdSendOutOrderReqDto));
    }
    
    /**
     * @throws Exception
     * @description : 출고지시서 인쇄
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.06.19    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "출고지시서 인쇄", description = "출고지시서 인쇄")
    @PostMapping(value = "/v1.0/printMasterList")
    public ApiResult<WdSendOutOrderPrintResDto> printMasterList(@RequestBody WdSendOutOrderReqDto wdSendOutOrderReqDto) throws Exception {
        return ApiResult.createResult(wdSendOutOrderService.printMasterList(wdSendOutOrderReqDto));
    }
    
    /**
     * @description : 출고지시서 인쇄 로그 저장
     * @issues : 
     * ----------------------------------------------------------- 
     * DATE AUTHOR MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2026.01.07 ParkJinWoo 생성
     */
	@Operation(summary = "출고지시서 인쇄 로그 저장", description = "출고지시서 인쇄 로그 저장")
	@PostMapping(value = "/v1.0/savePrintHistory")
	public ApiResult<Object> savePrintHistory(@Valid @RequestBody WdSendOutOrderReqDto reqDto) {
		return ApiResult.createResult(wdSendOutOrderService.savePrintHistory(reqDto));
	}

}