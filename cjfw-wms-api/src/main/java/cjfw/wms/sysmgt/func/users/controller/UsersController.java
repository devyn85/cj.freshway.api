/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.sysmgt.func.users.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.model.ApiResult;
import cjfw.wms.sysmgt.func.users.dto.UsersGetReqDto;
import cjfw.wms.sysmgt.func.users.dto.UsersGetResDto;
import cjfw.wms.sysmgt.func.users.dto.UsersSaveReqDto;
import cjfw.wms.sysmgt.func.users.dto.UsersSaveResDto;
import cjfw.wms.sysmgt.func.users.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "사용자관리", description = "사용자관리 API")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("sysmgt/func/users")
public class UsersController {
	
	private final UsersService usersService;

	/**
	 * 사용자 리스트 조회
	 */
	@Operation(summary = "사용자 목록", description = "사용자 목록 조회")
	@PostMapping(value = "/list")
	public ApiResult<List<UsersGetResDto>> list(@RequestBody @Valid UsersGetReqDto userGetReqDto) {
		return ApiResult.createResult(usersService.getUserList(userGetReqDto));
	}

	/**
	 * 사용자 저장(CUD)
	 */
	@Operation(summary = "사용자 저장", description = "사용자 목록 저장")
	@PostMapping(value = "/save")
	public ApiResult<UsersSaveResDto> save(@RequestBody @Valid UsersSaveReqDto userSaveReqDto) {
		UsersSaveResDto usersSaveResDto = usersService.saveUsers(userSaveReqDto);
		return ApiResult.createResult(usersSaveResDto, CanalFrameConstants.MSG_COM_SUC_CODE);
	}
}
