package cjfw.wms.om.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.om.dto.OmCloseReqDto;
import cjfw.wms.om.dto.OmCloseResDto;
import cjfw.wms.om.service.OmCloseService;
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
 * @date        : 2025.09.19
 * @description : 주문 > 주문현황 > 주문마감현황 조회 기능을 구현한 Controller Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.19        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Tag(name = "OmClose", description = "주문이력현황")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/om/close")
public class OmCloseController {

	private final OmCloseService omCloseService;

	/**
	 * @description : 주문마감현황 마스터 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.19        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "주문마감현황 마스터 목록 조회", description = "주문마감현황 목록 조회")
	@GetMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<OmCloseResDto>> getMasterList(@Valid OmCloseReqDto omCloseReqDto) {
		return ApiResult.createResult(omCloseService.getMasterList(omCloseReqDto));
	}
	
	/**
	 * @description : 주문마감현황 상세 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.19        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "주문마감현황 상세 목록 조회", description = "주문마감현황 목록 조회")
	@GetMapping(value = "/v1.0/getDetailList")
	public ApiResult<List<OmCloseResDto>> getDetailList(@Valid OmCloseReqDto omCloseReqDto) {
		return ApiResult.createResult(omCloseService.getDetailList(omCloseReqDto));
	}
	
	/**
	 * @description : 주문마감현황 상세 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.19        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "주문마감현황 상세2 목록 조회", description = "주문마감현황 목록 조회")
	@GetMapping(value = "/v1.0/getDetail2List")
	public ApiResult<List<OmCloseResDto>> getDetail2List(@Valid OmCloseReqDto omCloseReqDto) {
		return ApiResult.createResult(omCloseService.getDetail2List(omCloseReqDto));
	}
}