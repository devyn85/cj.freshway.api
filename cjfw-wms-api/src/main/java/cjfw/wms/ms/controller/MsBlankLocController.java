package cjfw.wms.ms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.MsBlankLocReqDto;
import cjfw.wms.ms.dto.MsBlankLocResDto;
import cjfw.wms.ms.dto.MsBlankLocZoneResDto;
import cjfw.wms.ms.service.MsBlankLocService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.06.24
 * @description : 기준정보 > 센터기준정보 > 기둥/빈 공간 정보 목록 조회 및 저장 Controller
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.24        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Tag(name = "기준정보 > 센터기준정보 > 기둥/빈 공간 정보", description = "기둥/빈 공간 정보 목록 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ms/blankLoc")
public class MsBlankLocController {

	private final MsBlankLocService msBlankLocService;

	/**
	 * 
	 * @description : 기둥/빈 공간 정보 목록 검색 조회
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.24        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "기둥/빈 공간 정보 목록 조회", description = "기둥/빈 공간 정보 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<MsBlankLocResDto>> getMasterList(@RequestBody MsBlankLocReqDto dto) {
		return ApiResult.createResult(msBlankLocService.getMasterList(dto));
	}
		
	/**
	 * @description : 기둥/빈 공간 정보 목록 저장5000500
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.24        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "기둥/빈 공간 정보 목록 저장", description = "기둥/빈 공간 정보 목록 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<Object> saveMasterList(@RequestBody List<MsBlankLocReqDto> dto) {
		return ApiResult.createResult(msBlankLocService.saveMasterList(dto));
	}
	
	/**
	 * 
	 * @description : ZONE 정보 목록 검색 조회
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.24        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "ZONE 정보 목록 조회", description = "ZONE 정보 목록 조회")
	@GetMapping(value = "/v1.0/getDataZone")
	public ApiResult<List<MsBlankLocZoneResDto>> getDataZone(MsBlankLocReqDto dto) {
		return ApiResult.createResult(msBlankLocService.getDataZone(dto));
	}
}