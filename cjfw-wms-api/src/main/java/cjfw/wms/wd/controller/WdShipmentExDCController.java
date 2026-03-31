package cjfw.wms.wd.controller;

import java.rmi.RemoteException;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.wd.dto.WdShipmentExDCPricePopupResDto;
import cjfw.wms.wd.dto.WdShipmentExDCReqDto;
import cjfw.wms.wd.dto.WdShipmentExDCResDto;
import cjfw.wms.wd.dto.WdShipmentExDCStockpriceResDto;
import cjfw.wms.wd.service.WdShipmentExDCService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.06.30 
 * @description : 외부비축출고처리 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.30    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Tag(name = "출고 > 출고작업 > 외부비축출고처리", description = "외부비축출고처리")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/wd/shipmentexdc")
public class WdShipmentExDCController {

	private final WdShipmentExDCService wdShipmentExDCService;

	/**
	 * @throws RemoteException 
	 * @description : 외부비축출고처리 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.19    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	@Operation(summary = "외부비축출고처리 목록 조회", description = "외부비축출고처리 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<WdShipmentExDCResDto>> getMasterList(@Valid WdShipmentExDCReqDto wdShipmentExDCReqDto) throws RemoteException {
		return ApiResult.createResult(wdShipmentExDCService.getMasterList(wdShipmentExDCReqDto));
	}	
	
	/**
     * @description : 외부비축출고처리 같은 운송비그룹 대상 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.06.19    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "외부비축출고처리 같은 운송비그룹 대상 조회", description = "외부비축출고처리 같은 운송비그룹 대상 조회")
    @PostMapping(value = "/v1.0/getPriceMasterList")
    public ApiResult<List<WdShipmentExDCResDto>> getPriceMasterList(@Valid WdShipmentExDCReqDto wdShipmentExDCReqDto) {
        return ApiResult.createResult(wdShipmentExDCService.getPriceMasterList(wdShipmentExDCReqDto));
    }
	
	@Operation(summary = "외부비축출고처리 대상 확정 저장", description = "외부비축출고처리 대상 확정 저장")
    @PostMapping(value = "/v1.0/saveMasterList")
    public ApiResult<String> saveMasterList(@RequestBody WdShipmentExDCReqDto wdShipmentExDCReqDto) throws Exception {
        return ApiResult.createResult(wdShipmentExDCService.saveMasterList(wdShipmentExDCReqDto));
    }
	
	/**
     * @description : 외부비축출고처리 운송료 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.13    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "외부비축출고처리 운송료 목록 조회", description = "외부비축출고처리 운송료 목록 조회")
    @PostMapping(value = "/v1.0/getPriceList")
    public ApiResult<List<WdShipmentExDCPricePopupResDto>> getPriceList(@Valid WdShipmentExDCReqDto wdShipmentExDCReqDto) {
        return ApiResult.createResult(wdShipmentExDCService.getPriceList(wdShipmentExDCReqDto));
    }
    
    /**
     * @description : 외부비축출고처리 운임단가 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.13    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "외부비축출고처리 운임단가 목록 조회", description = "외부비축출고처리 운임단가 목록 조회")
    @PostMapping(value = "/v1.0/getCarrierPriceList")
    public ApiResult<List<WdShipmentExDCPricePopupResDto>> getCarrierPriceList(@Valid WdShipmentExDCReqDto wdShipmentExDCReqDto) {
        return ApiResult.createResult(wdShipmentExDCService.getCarrierPriceList(wdShipmentExDCReqDto));
    }
    
    @Operation(summary = "외부비축출고처리 운송료 배부 저장", description = "외부비축출고처리 운송료 배부 저장")
    @PostMapping(value = "/v1.0/savePriceList")
    public ApiResult<String> savePriceList(@RequestBody WdShipmentExDCReqDto wdShipmentExDCReqDto) throws Exception {
        return ApiResult.createResult(wdShipmentExDCService.savePriceList(wdShipmentExDCReqDto));
    }
    
    /**
     * @description : 외부비축출고처리 운송료 저장
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.13    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "외부비축출고처리 운송료 저장", description = "외부비축출고처리 운송료 저장")
    @PostMapping(value = "/v1.0/saveREFERENCE10")
    public ApiResult<String> saveREFERENCE10(@RequestBody WdShipmentExDCReqDto wdShipmentExDCReqDto) throws Exception {
        return ApiResult.createResult(wdShipmentExDCService.saveREFERENCE10(wdShipmentExDCReqDto));
    }
    
    /**
     * @description : SCM담당자 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.19    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "SCM담당자 목록 조회", description = "SCM담당자 목록 조회")
    @PostMapping(value = "/v1.0/getScmUserList")
    public ApiResult<List<WdShipmentExDCResDto>> getScmUserList(@Valid WdShipmentExDCReqDto wdShipmentExDCReqDto) {
        return ApiResult.createResult(wdShipmentExDCService.getScmUserList(wdShipmentExDCReqDto));
    }
    
    /**
     * @description : 보관료 계산 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.13    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "보관료 계산 조회", description = "보관료 계산 조회")
    @PostMapping(value = "/v1.0/getStockPrice")
    public ApiResult<List<WdShipmentExDCStockpriceResDto>> getStockPrice(@Valid WdShipmentExDCReqDto wdShipmentExDCReqDto) {
        return ApiResult.createResult(wdShipmentExDCService.getStockPrice(wdShipmentExDCReqDto));
    }

}