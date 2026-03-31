package cjfw.wms.kp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.kp.dto.KpDpInspectMonitoringDtlResDto;
import cjfw.wms.kp.dto.KpDpInspectMonitoringExcelResDto;
import cjfw.wms.kp.dto.KpDpInspectMonitoringPrintResDto;
import cjfw.wms.kp.dto.KpDpInspectMonitoringReqDto;
import cjfw.wms.kp.dto.KpDpInspectMonitoringResDto;
import cjfw.wms.kp.dto.KpDpInspectMonitoringSumResDto;
import cjfw.wms.kp.service.KpDpInspectMonitoringService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiSooKim (jskim14@cj.net)  
 * @date : 2025.12.26 
 * @description : 지표/모니터링 > 검수지표 > 입고검수현황 목록 조회 및 저장 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.12.26 JiSooKim (jskim14@cj.net)  생성 </pre>
 */
@Tag(name = "지표/모니터링 > 검수지표 > 입고검수현황", description = "입고검수현황 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping({"api/kp/dpInspectMonitoring", "ltx/kp/dpInspectMonitoring"})
public class KpDpInspectMonitoringController {

	private final KpDpInspectMonitoringService KpDpInspectMntService;

	/**
	 * @description : 입고검수현황 목록 검색 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.26 JiSooKim (jskim14@cj.net)  생성 </pre>
	 */
	@Operation(summary = "KX입고검수현황 조회", description = "KX출고진행현황 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<KpDpInspectMonitoringResDto>> getInplanKXList(@RequestBody KpDpInspectMonitoringReqDto KpDpInspectMonitoringReqDto) {
		return ApiResult.createResult(KpDpInspectMntService.getHeaderList(KpDpInspectMonitoringReqDto));
	}
	
	/**
	 * @description : 입고검수현황 상세 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.26 JiSooKim (jskim14@cj.net)  생성 </pre>
	 */
	@Operation(summary = "입고검수현황 상세 목록 조회", description = "입고검수현황 상세 목록 조회")
	@PostMapping(value = "/v1.0/getDetailList")
	public ApiResult<List<KpDpInspectMonitoringDtlResDto>> getDetailList(@RequestBody KpDpInspectMonitoringReqDto KpDpInspectMonitoringReqDto) {
		return ApiResult.createResult(KpDpInspectMntService.getDetailList(KpDpInspectMonitoringReqDto));
	}

	
	/**
	 * @description : 검수완료 - 일괄
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.26 JiSooKim (jskim14@cj.net)  생성 </pre>
	 */

	@Operation(summary = "검수완료 (일괄)", description = "검수완료(일괄)")
	@PostMapping(value = "/v1.0/saveInspectAll")
	public ApiResult<String> saveInspectAll(@RequestBody @Valid KpDpInspectMonitoringReqDto dto) throws Exception {
		return ApiResult.createResult(KpDpInspectMntService.saveInspectAll(dto));
	}	
	
	/**
	 * @description : 검수완료 - 단건
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.26 JiSooKim (jskim14@cj.net)  생성 </pre>
	 */
	@Operation(summary = "검수완료 (단건)", description = "")
	@PostMapping(value = "/v1.0/saveInspect")
	public ApiResult<String> saveInspect(@RequestBody @Valid KpDpInspectMonitoringReqDto KpDpInspectMonitoringReqDto) {
		return ApiResult.createResult(KpDpInspectMntService.saveInspect(KpDpInspectMonitoringReqDto));
	}

	
	/**
	 * @description : SMS 1차발송 (일괄)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.26 JiSooKim (jskim14@cj.net)  생성 </pre>
	 */
	@Operation(summary = "SMS 1차발송 (일괄)", description = "SMS 1차발송 (일괄)")
	@PostMapping(value = "/v1.0/saveSendSMSAll")
	public ApiResult<String> sendSMSAll(@RequestBody @Valid KpDpInspectMonitoringReqDto KpDpInspectMonitoringReqDto) {
		return ApiResult.createResult(KpDpInspectMntService.sendSMSAll(KpDpInspectMonitoringReqDto));
	}
	
	/**
	 * @description : SMS 2차발송 (일괄)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.26 JiSooKim (jskim14@cj.net)  생성 </pre>
	 */
	@Operation(summary = "SMS 2차발송 (일괄) 저장", description = "SMS 2차발송 (일괄) 저장")
	@PostMapping(value = "/v1.0/saveSendSMSAllResend")
	public ApiResult<String> sendSMSAllResend(@RequestBody @Valid KpDpInspectMonitoringReqDto KpDpInspectMonitoringReqDto) {
		return ApiResult.createResult(KpDpInspectMntService.sendSMSAllResend(KpDpInspectMonitoringReqDto));
	}
	
	/**
	 * @description : SMS 1차발송 (단건)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.26 JiSooKim (jskim14@cj.net)  생성 </pre>
	 */
	@Operation(summary = "SMS 1차발송 (단건)", description = "SMS 1차발송 (단건)")
	@PostMapping(value = "/v1.0/saveSendSMS")
	public ApiResult<String> sendSMS(@RequestBody @Valid KpDpInspectMonitoringReqDto KpDpInspectMonitoringReqDto) {
		return ApiResult.createResult(KpDpInspectMntService.saveSendSMS(KpDpInspectMonitoringReqDto));
	}
	
	/**
	 * @description : SMS 1차발송 (단건)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.26 JiSooKim (jskim14@cj.net)  생성 </pre>
	 */
	@Operation(summary = "SMS 1차발송 (단건)", description = "SMS 1차발송 (단건)")
	@PostMapping(value = "/v1.0/saveSendSMSResend")
	public ApiResult<String> sendSMSResend(@RequestBody @Valid KpDpInspectMonitoringReqDto KpDpInspectMonitoringReqDto) {
		return ApiResult.createResult(KpDpInspectMntService.saveSendSMSResend(KpDpInspectMonitoringReqDto));
	}
	
    /**
	 * @description : 광역출고현황 - 마스터 그리드 인쇄
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.26 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
    @PostMapping("/v1.0/getPrintData")
    public ApiResult<List<KpDpInspectMonitoringPrintResDto>> getPrintDetailInvoice(@RequestBody @Valid KpDpInspectMonitoringReqDto dto) {
        return ApiResult.createResult(KpDpInspectMntService.getPrintData(dto));
    }
    
	/**
	 * @description : 입고검수현황 엑셀자료 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.26 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	@Operation(summary = "엑셀자료 조회", description = "엑셀자료 조회")
	@PostMapping(value = "/v1.0/getDataExcelList")
	public ApiResult<List<KpDpInspectMonitoringExcelResDto>> getDataExcelList(@RequestBody KpDpInspectMonitoringReqDto dto) {
			
				
		return ApiResult.createResult(KpDpInspectMntService.getDataExcelList(dto));
	}
	
    
	/**
	 * @description : 집계PDP 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.26 JiSooKim (jskim14@cj.net)  생성 </pre>
	 */
	@Operation(summary = "집계PDP 조회", description = "집계PDP 조회")
	@PostMapping(value = "/v1.0/getDpInspectSumPDP")
	public ApiResult<List<KpDpInspectMonitoringSumResDto>> getDpInspectSumPDP(@RequestBody KpDpInspectMonitoringReqDto KpDpInspectMonitoringReqDto) {
		return ApiResult.createResult(KpDpInspectMntService.getDpInspectSumPDP(KpDpInspectMonitoringReqDto));
	}

}