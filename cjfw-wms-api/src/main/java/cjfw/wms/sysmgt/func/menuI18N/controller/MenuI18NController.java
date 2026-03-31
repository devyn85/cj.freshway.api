/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.sysmgt.func.menuI18N.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.sysmgt.func.menuI18N.dto.MenuI18NGetReqDto;
import cjfw.wms.sysmgt.func.menuI18N.dto.MenuI18NSaveReqDto;
import cjfw.wms.sysmgt.func.menuI18N.service.MenuI18NService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "다국어 메뉴관리", description = "다국어 메뉴관리 API")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("sysmgt/func/menuI18N")
public class MenuI18NController {
	
	private final MenuI18NService menuI18NService;

	/**
	 * 메뉴 다국어 리스트 조회
	 */
	@Operation(summary = "다국어 메뉴 목록", description = "다국어 메뉴 목록 조회")
	@GetMapping(value = "/list")
	public ApiResult<List<Map>> list(@Valid MenuI18NGetReqDto menuI18NGetReqDto) {
		return ApiResult.createResult(menuI18NService.getMenuI18NList(menuI18NGetReqDto));
	}

	/**
	 * 메뉴 다국어 저장
	 */
	@Operation(summary = "다국어 메뉴 저장", description = "다국어 메뉴 목록 저장")
	@PostMapping(value = "/save")
	public ApiResult save(@RequestBody @Valid MenuI18NSaveReqDto menuI18NSaveReqDto) {
		return ApiResult.createResult(menuI18NService.saveMenuI18N(menuI18NSaveReqDto));
	}
}

