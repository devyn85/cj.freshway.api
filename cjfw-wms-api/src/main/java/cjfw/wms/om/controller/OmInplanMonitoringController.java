package cjfw.wms.om.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.om.dto.OmInplanMonitoringChkFsIfResDto;
import cjfw.wms.om.dto.OmInplanMonitoringChkFsResDto;
import cjfw.wms.om.dto.OmInplanMonitoringChkRsltFsResDto;
import cjfw.wms.om.dto.OmInplanMonitoringCloseWdResDto;
import cjfw.wms.om.dto.OmInplanMonitoringCustCloseTypeResDto;
import cjfw.wms.om.dto.OmInplanMonitoringReqDto;
import cjfw.wms.om.dto.OmInplanMonitoringTotalResDto;
import cjfw.wms.om.service.OmInplanMonitoringService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.07.10
 * @description : 주문 > 주문현황 > 주문수신모니터링 조회 기능을 구현한 Controller Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.10        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Tag(name = "OmInplanMonitoring", description = "마감주문반영")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/om/inplanMonitoring")
public class OmInplanMonitoringController {

	private final OmInplanMonitoringService omInplanMonitoringService;

	/**
	 * @description : WMS진행현황 마스터 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.10        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "WMS진행현황 마스터 목록 조회", description = "WMS진행현황 목록 조회")
	@GetMapping(value = "/v1.0/getTotalMasterList")
	public ApiResult<List<OmInplanMonitoringTotalResDto>> getTotalMasterList(@Valid OmInplanMonitoringReqDto omInplanMonitoringReqDto) {
		return ApiResult.createResult(omInplanMonitoringService.getTotalMasterList(omInplanMonitoringReqDto));
	}
	
	/**
	 * @description : 주문수신모니터링-경로별마감 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.10        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "경로별마감 마스터 목록 조회", description = "경로별마감 목록 조회")
	@GetMapping(value = "/v1.0/getCustCloseTypeMasterList")
	public ApiResult<List<OmInplanMonitoringCustCloseTypeResDto>> getCustCloseTypeMasterList(@Valid OmInplanMonitoringReqDto omInplanMonitoringReqDto) {
		return ApiResult.createResult(omInplanMonitoringService.getCustCloseTypeMasterList(omInplanMonitoringReqDto));
	}
	
	/**
	 * @description : 주문수신모니터링-경로별마감 상세 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.10        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "경로별마감 상세 목록 조회", description = "경로별마감 목록 조회")
	@GetMapping(value = "/v1.0/getCustCloseTypeDetailList")
	public ApiResult<List<OmInplanMonitoringCustCloseTypeResDto>> getCustCloseTypeDetailList(@Valid OmInplanMonitoringReqDto omInplanMonitoringReqDto) {
		return ApiResult.createResult(omInplanMonitoringService.getCustCloseTypeDetailList(omInplanMonitoringReqDto));
	}
	
	/**
	 * @description : 강제마감
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.10        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "강제마감", description = "강제마감")
	@PostMapping(value = "/v1.0/saveOrderCloseStatus")
	public ApiResult<Object> saveOrderCloseStatus(@RequestBody OmInplanMonitoringReqDto dto) {
		return ApiResult.createResult(omInplanMonitoringService.saveOrderCloseStatus(dto));
	}
	
	/**
	 * @description : WMS진행현황 마스터 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.10        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "출고마감 마스터 목록 조회", description = "출고마감 목록 조회")
	@GetMapping(value = "/v1.0/getCloseWdMasterList")
	public ApiResult<List<OmInplanMonitoringCloseWdResDto>> getCloseWdMasterList(@Valid OmInplanMonitoringReqDto omInplanMonitoringReqDto) {
		return ApiResult.createResult(omInplanMonitoringService.getCloseWdMasterList(omInplanMonitoringReqDto));
	}
	
	/**
	 * @description : 영업오더차이 마스터 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.10        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "영업오더차이 마스터 목록 조회", description = "영업오더차이 목록 조회")
	@GetMapping(value = "/v1.0/getChkFsMasterList")
	public ApiResult<List<OmInplanMonitoringChkFsResDto>> getChkFsMasterList(@Valid OmInplanMonitoringReqDto omInplanMonitoringReqDto) {
		return ApiResult.createResult(omInplanMonitoringService.getChkFsMasterList(omInplanMonitoringReqDto));
	}
	
	/**
	 * @description : 영업오더차이 상세 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.10        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "영업오더차이 상세 조회", description = "영업오더차이 상세 조회")
	@GetMapping(value = "/v1.0/getChkFsDetailList")
	public ApiResult<List<OmInplanMonitoringChkFsResDto>> getChkFsDetailList(@Valid OmInplanMonitoringReqDto omInplanMonitoringReqDto) {
		return ApiResult.createResult(omInplanMonitoringService.getChkFsDetailList(omInplanMonitoringReqDto));
	}
	
	/**
	 * @description : 영업오더차이 I/F 상세 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.10        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "영업오더차이 I/F 상세 조회", description = "영업오더차이 I/F 상세 조회")
	@GetMapping(value = "/v1.0/getOrderListChkFSIF")
	public ApiResult<List<OmInplanMonitoringChkFsIfResDto>> getOrderListChkFSIF(@Valid OmInplanMonitoringReqDto omInplanMonitoringReqDto) {
		return ApiResult.createResult(omInplanMonitoringService.getOrderListChkFSIF(omInplanMonitoringReqDto));
	}
	
	/**
	 * @description : 영업실적차이 마스터 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.10        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "영업실적차이 마스터 목록 조회", description = "영업실적차이 목록 조회")
	@GetMapping(value = "/v1.0/getChkRsltFsMasterList")
	public ApiResult<List<OmInplanMonitoringChkRsltFsResDto>> getChkRsltFsMasterList(@Valid OmInplanMonitoringReqDto omInplanMonitoringReqDto) {
		return ApiResult.createResult(omInplanMonitoringService.getChkRsltFsMasterList(omInplanMonitoringReqDto));
	}
	
	/**
	 * @description : 영업실적차이 상세 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.10        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "영업실적차이 상세 목록 조회", description = "영업실적차이 상세 목록 조회")
	@GetMapping(value = "/v1.0/getChkRsltFsDetailList")
	public ApiResult<List<OmInplanMonitoringChkRsltFsResDto>> getChkRsltFsDetailList(@Valid OmInplanMonitoringReqDto omInplanMonitoringReqDto) {
		return ApiResult.createResult(omInplanMonitoringService.getChkRsltFsDetailList(omInplanMonitoringReqDto));
	}
	
	/**
	 * @description : 영업실적차이 I/F 상세 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.10        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "영업실적차이 I/F 상세 조회", description = "영업실적차이 I/F 상세 조회")
	@GetMapping(value = "/v1.0/getOrderListChkRsltFSIF")
	public ApiResult<List<OmInplanMonitoringChkFsIfResDto>> getOrderListChkRsltFSIF(@Valid OmInplanMonitoringReqDto omInplanMonitoringReqDto) {
		return ApiResult.createResult(omInplanMonitoringService.getOrderListChkRsltFSIF(omInplanMonitoringReqDto));
	}
}