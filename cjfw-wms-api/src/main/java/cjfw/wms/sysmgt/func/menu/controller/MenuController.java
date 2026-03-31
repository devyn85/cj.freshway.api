/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.sysmgt.func.menu.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.sysmgt.func.menu.dto.MenuGetReqDto;
import cjfw.wms.sysmgt.func.menu.dto.MenuGetResDto;
import cjfw.wms.sysmgt.func.menu.dto.MenuSaveReqDto;
import cjfw.wms.sysmgt.func.menu.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "메뉴관리", description = "메뉴관리 API")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("sysmgt/func/menu")
public class MenuController {

	private final MenuService menuService;
	
	@Operation(summary = "메뉴 목록", description = "메뉴 목록 조회")
	@GetMapping(value = "/list")
	public ApiResult<List<MenuGetResDto>> list(@Valid MenuGetReqDto menuGetReqDto) {
		return ApiResult.createResult(menuService.getMenuList(menuGetReqDto));
	}
	
	@Operation(summary = "메뉴 저장", description = "메뉴 목록 저장")
	@PostMapping(value = "/save")
	public ApiResult save(@RequestBody @Valid MenuSaveReqDto menuSaveReqDto) {
		return ApiResult.createResult(menuService.saveMenu(menuSaveReqDto));
	}
}