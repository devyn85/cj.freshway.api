/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.sysmgt.func.rolesmappingmenu.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.sysmgt.func.rolesmappingmenu.dto.RolesMappingMenuCopyReqDto;
import cjfw.wms.sysmgt.func.rolesmappingmenu.dto.RolesMappingMenuGetReqDto;
import cjfw.wms.sysmgt.func.rolesmappingmenu.dto.RolesMappingMenuGetResDto;
import cjfw.wms.sysmgt.func.rolesmappingmenu.dto.RolesMappingMenuSaveReqDto;
import cjfw.wms.sysmgt.func.rolesmappingmenu.service.RolesMappingMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "권한별 메뉴관리", description = "권한별 메뉴관리 API")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("sysmgt/func/rolesmappingmenu")
public class RolesMappingMenuController {
	
	private final RolesMappingMenuService rolesMappingMenuService;

	/**
	 * 권한메뉴 리스트 조회
	 */
	@Operation(summary = "권한별 메뉴 목록", description = "권한별 메뉴 목록 조회")
	@GetMapping(value = "/list")
	public ApiResult<List<RolesMappingMenuGetResDto>> list(@Valid RolesMappingMenuGetReqDto rolesMappingMenuGetReqDto) {
		return ApiResult.createResult(rolesMappingMenuService.getRolesMappingMenuList(rolesMappingMenuGetReqDto));
	}

	/**
	 * 권한메뉴 저장
	 */
	@Operation(summary = "권한별 메뉴 저장", description = "권한별 메뉴 목록 저장")
	@PostMapping(value = "/save")
	public ApiResult save(@Valid @RequestBody RolesMappingMenuSaveReqDto rolesMappingMenuSaveReqDto) {
		return ApiResult.createResult(rolesMappingMenuService.saveRolesMappingMenus(rolesMappingMenuSaveReqDto));
	}

	/**
	 * 권한메뉴 복사
	 */
	@Operation(summary = "권한별 메뉴 복사", description = "권한별 메뉴 목록 복사")
	@PostMapping(value = "/copy")
	public ApiResult copy(@Valid @RequestBody RolesMappingMenuCopyReqDto rolesMappingMenuCopyReqDto) {
		return ApiResult.createResult(rolesMappingMenuService.saveCopyRolesMappingMenus(rolesMappingMenuCopyReqDto));
	}
}
