package cjfw.wms.sys.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.sys.dto.SysProgramReqDto;
import cjfw.wms.sys.dto.SysProgramResDto;
import cjfw.wms.sys.service.SysProgramService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.04.30 
 * @description : ADMIN > 시스템운영 > 프로그램 목록 조회 및 저장 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.04.30 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Tag(name = "ADMIN > 시스템운영 > 프로그램", description = "프로그램 목록 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/sys/program")
public class SysProgramController {

	private final SysProgramService sysProgramService;

	/**
	 * @description : 프로그램 목록 검색 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.04.30 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "프로그램 목록 조회", description = "프로그램 목록 조회")
	@GetMapping(value = "/v1.0/getProgramList")
	public ApiResult<List<SysProgramResDto>> getProgramList(@Valid SysProgramReqDto sysProgramReqDto) {
		return ApiResult.createResult(sysProgramService.getProgramList(sysProgramReqDto));
	}

	/**
	 * @description : 프로그램 목록 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.04.30 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "프로그램 목록 저장", description = "프로그램 목록 저장")
	@PostMapping(value = "/v1.0/saveProgram")
	public ApiResult<String> saveProgram(@RequestBody @Valid List<SysProgramReqDto> programs) {
		return ApiResult.createResult(sysProgramService.saveProgram(programs));
	}
}