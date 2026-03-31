package cjfw.wms.sys.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.sys.dto.SysAuthorityReqDto;
import cjfw.wms.sys.dto.SysAuthorityResDto;
import cjfw.wms.sys.service.SysAuthorityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.05.15 
 * @description : ADMIN > 시스템운영 > 그룹권한 목록 조회 및 저장 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.15 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Tag(name = "ADMIN > 시스템운영 > 그룹권한", description = "그룹권한 목록 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/sys/authority")
public class SysAuthorityController {

	private final SysAuthorityService sysAuthorityService;

	/**
	 * @description : 권한그룹 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.15 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "권한그룹 목록 조회", description = "권한그룹 목록 조회")
	@GetMapping(value = "/v1.0/getAuthorityGroupList")
	public ApiResult<List<SysAuthorityResDto>> getAuthorityGroupList(@Valid SysAuthorityReqDto sysAuthorityReqDto) {
		return ApiResult.createResult(sysAuthorityService.getAuthorityGroupList(sysAuthorityReqDto));
	}
	
	/**
	 * @description : 권한그룹 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.15 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "권한그룹 저장", description = "권한그룹 저장")
	@PostMapping(value = "/v1.0/saveAuthorityGroup")
	public ApiResult<String> saveAuthorityGroup(@RequestBody @Valid SysAuthorityReqDto sysAuthorityReqDto) {
		return ApiResult.createResult(sysAuthorityService.saveAuthorityGroup(sysAuthorityReqDto));
	}
}