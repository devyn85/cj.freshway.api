package cjfw.wms.sys.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.sys.dto.SysLabelReqDto;
import cjfw.wms.sys.dto.SysLabelResDto;
import cjfw.wms.sys.service.SysLabelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JiSooKim (jskim14@cj.net)
 * @date        : 2025.08.21
 * @description : Admin > 시스템운영 > 라벨 목록 조회 및 저장 Controller
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.21        JiSooKim (jskim14@cj.net)       생성
 */
@Tag(name = "Admin > 시스템운영 > 라벨", description = "라벨 목록 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/sys/label")
public class SysLabelController {

	private final SysLabelService sysLabelService;

	/**
	 * 
	 * @description :라벨 목록 검색 조회
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.21        JiSooKim (jskim14@cj.net)       생성
	 */
	@Operation(summary = "라벨 목록 조회", description = "라벨 목록 조회")
	@PostMapping(value = "/v1.0/getLabelList")
	public ApiResult<Page<SysLabelResDto>> getMasterList(@RequestBody SysLabelReqDto dto) {
		return ApiResult.createResult(sysLabelService.getMasterList(dto));
	}
		
	/**
	 * @description :라벨 목록 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.21        JiSooKim (jskim14@cj.net)       생성
	 */
	@Operation(summary = "라벨 목록 저장", description = "라벨 목록 저장")
	@PostMapping(value = "/v1.0/saveLabelList")
	public ApiResult<Object> saveMasterList(@RequestBody List<SysLabelReqDto> dto) {
		return ApiResult.createResult(sysLabelService.saveMasterList(dto));
	}
}