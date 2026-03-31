package cjfw.wms.portal.common.sample.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.portal.common.sample.dto.ZoneManagerGetReqDto;
import cjfw.wms.portal.common.sample.dto.ZoneManagerGetResDto;
import cjfw.wms.portal.common.sample.dto.ZoneManagerSaveReqDto;
import cjfw.wms.portal.common.sample.service.ZoneManagerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : KimSunHo (sunhokim6229@cj.net)
 * @date        : 2025.04.21
 * @description : 존정보 관리 기능을 구현한 Controller Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.04.21        KimSunHo (sunhokim6229@cj.net)       생성
 */
@Tag(name = "존정보", description = "존정보 API")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("sample/center/zone")
public class ZoneManagerController {
	
	private final ZoneManagerService zoneManagerService;
	
	/**
	 * 존정보 목록을 검색한다.<br>
	 */
	@Operation(summary = "존 정보 목록", description = "존 정보 목록 조회")
	@GetMapping(value = "/list")
	public ApiResult<List<ZoneManagerGetResDto>> list(@Valid ZoneManagerGetReqDto zoneManagerReqDto) {
		return ApiResult.createResult(zoneManagerService.getZoneList(zoneManagerReqDto));
	}
	
	/**
	 * 존정보 목록을 검색한다.<br>
	 */
	@Operation(summary = "존 정보 저장", description = "존 정보 저장")
	@PostMapping(value = "/save")
	public ApiResult save(@RequestBody @Valid ZoneManagerSaveReqDto zoneManagerSaveReqDto) {
		return ApiResult.createResult(zoneManagerService.saveZone(zoneManagerSaveReqDto));
	}

}
