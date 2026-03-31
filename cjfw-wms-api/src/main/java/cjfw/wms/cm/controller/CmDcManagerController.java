package cjfw.wms.cm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.cm.dto.CmDcManagerReqDto;
import cjfw.wms.cm.dto.CmDcManagerResDto;
import cjfw.wms.cm.service.CmDcManagerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangJaeHyun (jhjang43@cj.net)
 * @date : 2025.07.17 
 * @description : 기준정보 > 사용자및센터정보 > 물류센터관리
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.17        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Tag(name = "기준정보 > 사용자및센터정보 > 물류센터관리", description = "물류센터 정보 관리")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/cm/dc")
public class CmDcManagerController {

	private final CmDcManagerService cmDcManagerService;
	
	/**
	 * @description : 물류센터관리 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.17        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "물류센터관리 목록 조회", description = "물류센터관리 목록 조회")
	@GetMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<CmDcManagerResDto>> getMasterList(@Valid CmDcManagerReqDto cmDcManagerReqDto) {
		return ApiResult.createResult(cmDcManagerService.getMasterList(cmDcManagerReqDto));
	}
	
	/**
	 * @description : 물류센터관리 상세 정보 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.17        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "물류센터관리 상세 정보 조회", description = "물류센터관리 상세 정보 조회")
	@GetMapping(value = "/v1.0/getDetail")
	public ApiResult<CmDcManagerResDto> getDetail(@Valid CmDcManagerReqDto cmDcManagerReqDto) {
		return ApiResult.createResult(cmDcManagerService.getDetail(cmDcManagerReqDto));
	}
	
	/**
	 * @description : 물류센터관리 목록 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.17        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "물류센터관리 목록 저장", description = "물류센터관리 목록 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<List<CmDcManagerReqDto>> saveMasterList(@RequestBody List<CmDcManagerReqDto> dto) {
		return ApiResult.createResult(cmDcManagerService.saveMasterList(dto));
	}
	
}