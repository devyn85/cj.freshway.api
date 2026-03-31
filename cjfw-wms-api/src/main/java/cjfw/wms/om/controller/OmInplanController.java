package cjfw.wms.om.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.om.dto.OmInplanDetailResDto;
import cjfw.wms.om.dto.OmInplanReqDto;
import cjfw.wms.om.dto.OmInplanResDto;
import cjfw.wms.om.service.OmInplanService;
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
 * @description : 주문 > 주문현황 > 주문이력현황 조회 기능을 구현한 Controller Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.10        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Tag(name = "OmInplan", description = "주문이력현황")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/om/inplan")
public class OmInplanController {

	private final OmInplanService omInplanService;

	/**
	 * @description : 주문이력현황 마스터 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.10        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "주문이력현황 마스터 목록 조회", description = "주문이력현황 마스터 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<Page<OmInplanResDto>> getTotalMasterList(@RequestBody OmInplanReqDto omInplanReqDto) {
		log.info("OmInplanController.getTotalMasterList omInplanReqDto = {}", omInplanReqDto);
		return ApiResult.createResult(omInplanService.getMasterList(omInplanReqDto));
	}
	
	/**
	 * @description : 주문이력현황 상세 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.10        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "주문이력현황 상세 목록 조회", description = "주문이력현황 상세 목록 조회")
	@GetMapping(value = "/v1.0/getDetailList")
	public ApiResult<List<OmInplanDetailResDto>> getCustCloseTypeMasterList(@Valid OmInplanReqDto omInplanReqDto) {
		return ApiResult.createResult(omInplanService.getDetailList(omInplanReqDto));
	}
	
	/**
	 * @description : 진행상태 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.10        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "진행상태 목록 조회", description = "진행상태 목록 조회")
	@GetMapping(value = "/v1.0/getStatusList")
	public ApiResult<List<Map<String, String>>> getStatusList() {
		return ApiResult.createResult(omInplanService.getPlantList());
	}
	
}