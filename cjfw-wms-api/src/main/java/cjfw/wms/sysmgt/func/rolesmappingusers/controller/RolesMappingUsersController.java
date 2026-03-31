/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.sysmgt.func.rolesmappingusers.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.sysmgt.func.rolesmappingusers.dto.RolesMappingUsersGetReqDto;
import cjfw.wms.sysmgt.func.rolesmappingusers.dto.RolesMappingUsersGetResDto;
import cjfw.wms.sysmgt.func.rolesmappingusers.dto.RolesMappingUsersSaveReqDto;
import cjfw.wms.sysmgt.func.rolesmappingusers.service.RolesMappingUsersService;
import cjfw.wms.sysmgt.func.users.dto.UsersGetResDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "권한별 사용자관리", description = "권한별 사용자관리 API")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("sysmgt/func/rolesmappingusers")
public class RolesMappingUsersController {
	
	private final RolesMappingUsersService rolesMappingUsersService;

	/**
	 * 권한 사용자 페이지 리스트 조회
	 */
	@Operation(summary = "권한별 사용자 목록", description = "권한별 사용자 목록 조회")
	@GetMapping(value = "/list")
	public ApiResult<Page<RolesMappingUsersGetResDto>> list(@Valid RolesMappingUsersGetReqDto rolesMappingUsersGetReqDto, Page page) {
		return ApiResult.createResult(rolesMappingUsersService.getRolesMappingUserList(rolesMappingUsersGetReqDto, page));
	}
	
	@Operation(summary = "권한별 사용자 저장", description = "권한별 사용자 저장")
	@PostMapping(value = "/save")
	public ApiResult save(@RequestBody @Valid RolesMappingUsersSaveReqDto rolesMappingUsersSaveReqDto) {
		return ApiResult.createResult(rolesMappingUsersService.saveRolesMappingUsers(rolesMappingUsersSaveReqDto));
	}

	@Operation(summary = "사용자 목록", description = "사용자 목록 조회")
	@PostMapping(value = "/userList")
	public ApiResult<Page<UsersGetResDto>> userList(@RequestBody RolesMappingUsersGetReqDto rolesMappingUsersGetReqDto) {
		return ApiResult.createResult(rolesMappingUsersService.getUserList(rolesMappingUsersGetReqDto));
	}
}