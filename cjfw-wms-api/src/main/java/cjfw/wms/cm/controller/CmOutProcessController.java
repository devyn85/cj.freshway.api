package cjfw.wms.cm.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.gwms.dto.Gwms3PlApiReqDto;
import cjfw.wms.gwms.dto.GwmsDp3PlApiResDto;
import cjfw.wms.gwms.dto.GwmsDpInplan3PlApiResDto;
import cjfw.wms.gwms.dto.GwmsSt3PlApiResDto;
import cjfw.wms.gwms.dto.GwmsWdInplan3PlApiResDto;
import cjfw.wms.gwms.service.Gwms3PLApiService;
import cjfw.wms.st.dto.StDailyOnhandQtyAPIIFReqDto;
import cjfw.wms.st.service.StDailyOnhandQtyAPIIFService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.11.03 
 * @description : 외부 인터페이스  API
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.03 KimSunHo(sunhokim6229@cj.net) 생성 </pre>
 */
@Tag(name = "외부 인터페이스 ", description = "외부 인터페이스 API")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/cm/public")
public class CmOutProcessController {

	private final StDailyOnhandQtyAPIIFService stDailyOnhandQtyAPIIFService;
	private final Gwms3PLApiService gwms3PLApiService;
	
	/**
	 * @description : 외부창고 API 재고현황 인터페이스
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.03 KimSunHo(sunhokim6229@cj.net) 생성 </pre>
	 */
	@Operation(summary = "외부창고 API 재고현황 인터페이스", description = "외부창고 API 재고현황 인터페이스")
	@PostMapping(value = "/v1.0/saveExdcDailyOnhandQty")
	public ResponseEntity<Map<String, String>> saveExdcDailyOnhandQty(HttpServletRequest request, @RequestBody StDailyOnhandQtyAPIIFReqDto reqDto) {
	    return stDailyOnhandQtyAPIIFService.saveIFMasterList(request, reqDto);
	}
	
	/**
	 * @description : 입고 확정내역 조회 API
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.05 KimDongHan (liop0123@cj.net) 생성
	 */
	@Operation(summary = "입고 확정내역 조회 API", description = "입고 확정내역 조회 API")
	@PostMapping(value = "/v1.0/get3PlDpData")
	public ApiResult<List<GwmsDp3PlApiResDto>> get3PlDpData(HttpServletRequest request, @RequestBody Gwms3PlApiReqDto reqDto) {
		return ApiResult.createResult(gwms3PLApiService.get3PlDpData(request, reqDto));
	}
	
	/**
	 * @description : 재고 현황 조회 API
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.05 KimDongHan (liop0123@cj.net) 생성
	 */
	@Operation(summary = "재고 현황 조회 API", description = "재고 현황 조회 API")
	@PostMapping(value = "/v1.0/get3PlStData")
	public ApiResult<List<GwmsSt3PlApiResDto>> get3PlStData(HttpServletRequest request, @RequestBody Gwms3PlApiReqDto reqDto) {
		return ApiResult.createResult(gwms3PLApiService.get3PlStData(request, reqDto));
	}
	
	/**
	 * @description : 입고진행 현황 조회 API
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.05 KimDongHan (liop0123@cj.net) 생성
	 */
	@Operation(summary = "입고진행 현황 조회 API", description = "입고진행 현황 조회 API")
	@PostMapping(value = "/v1.0/get3PlDpInplanData")
	public ApiResult<List<GwmsDpInplan3PlApiResDto>> get3PlDpInplanData(HttpServletRequest request, @RequestBody Gwms3PlApiReqDto reqDto) {
		return ApiResult.createResult(gwms3PLApiService.get3PlDpInplanData(request, reqDto));
	}
	
	/**
	 * @description : 출고진행 현황 조회 API
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.05 KimDongHan (liop0123@cj.net) 생성
	 */
	@Operation(summary = "출고진행 현황 조회 API", description = "출고진행 현황 조회 API")
	@PostMapping(value = "/v1.0/get3PlWdInplanData")
	public ApiResult<List<GwmsWdInplan3PlApiResDto>> get3PlWdInplanData(HttpServletRequest request, @RequestBody Gwms3PlApiReqDto reqDto) {
		return ApiResult.createResult(gwms3PLApiService.get3PlWdInplanData(request, reqDto));
	}

}
