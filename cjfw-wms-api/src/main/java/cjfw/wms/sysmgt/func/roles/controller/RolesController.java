/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.sysmgt.func.roles.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.sysmgt.func.roles.dto.RolesGetReqDto;
import cjfw.wms.sysmgt.func.roles.dto.RolesGetResDto;
import cjfw.wms.sysmgt.func.roles.dto.RolesSaveReqDto;
import cjfw.wms.sysmgt.func.roles.dto.UserRolesGetReqDto;
import cjfw.wms.sysmgt.func.roles.dto.UserRolesGetResDto;
import cjfw.wms.sysmgt.func.roles.service.RolesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "권한관리", description = "권한관리 API")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("sysmgt/func/roles")
public class RolesController {
	
	private final RolesService rolesService;

	/**
	 * 권한 리스트 조회
	 */
	@Operation(summary = "권한 목록", description = "권한 목록 조회")
	@GetMapping(value = "/list")
	public ApiResult<List<RolesGetResDto>> list(@Valid RolesGetReqDto rolesGetReqDto) {
		return ApiResult.createResult(rolesService.getRoleList(rolesGetReqDto));
	}

	/**
	 * 권장 저장(CUD)
	 */
	@Operation(summary = "권한 저장", description = "권한 목록 저장")
	@PostMapping(value = "/save")
	public ApiResult save(@RequestBody @Valid RolesSaveReqDto rolesSaveReqDto) {
		return ApiResult.createResult(rolesService.saveRoles(rolesSaveReqDto));
	}

	/**
	 * 사용자 권한 리스트 조회
	 */
	@Operation(summary = "사용자별 권한 목록", description = "사용자별 권한 목록 조회")
	@PostMapping(value = "/userRoleList")
	public ApiResult<List<UserRolesGetResDto>> userRolelist(@RequestBody @Valid UserRolesGetReqDto userRolesGetReqDto) {
		return ApiResult.createResult(rolesService.getUserRoleList(userRolesGetReqDto));
	}

}
