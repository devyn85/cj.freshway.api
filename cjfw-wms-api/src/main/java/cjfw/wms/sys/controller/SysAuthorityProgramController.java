package cjfw.wms.sys.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.sys.dto.SysAuthorityProgramReqDto;
import cjfw.wms.sys.dto.SysAuthorityProgramResDto;
import cjfw.wms.sys.service.SysAuthorityProgramService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.08.08 
 * @description : ADMIN > 시스템운영 > 권한그룹별 프로그램 관리
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.08 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Tag(name = "ADMIN > 시스템운영 > 권한그룹별 프로그램 관리", description = "권한그룹별 프로그램 목록 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/sys/authorityProgram")
public class SysAuthorityProgramController {

	private final SysAuthorityProgramService sysAuthorityProgramService;
	
	/**
	 * @description : 권한그룹별 프로그램 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.15 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "권한그룹별 프로그램 조회", description = "권한그룹별 프로그램 조회")
	@GetMapping(value = "/v1.0/getAuthorityProgramList")
	public ApiResult<List<SysAuthorityProgramResDto>> getAuthorityProgramList(@Valid SysAuthorityProgramReqDto sysAuthorityProgramReqDto) {
		return ApiResult.createResult(sysAuthorityProgramService.getAuthorityProgramList(sysAuthorityProgramReqDto));
	}
	
	/**
	 * @description : 권한그룹별 프로그램 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.15 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "권한그룹별 프로그램 저장", description = "권한그룹별 프로그램 저장")
	@PostMapping(value = "/v1.0/saveAuthorityProgram")
	public ApiResult<String> saveAuthorityProgram(@RequestBody @Valid SysAuthorityProgramReqDto sysAuthorityProgramReqDto) {
		return ApiResult.createResult(sysAuthorityProgramService.saveAuthorityProgram(sysAuthorityProgramReqDto));
	}
}