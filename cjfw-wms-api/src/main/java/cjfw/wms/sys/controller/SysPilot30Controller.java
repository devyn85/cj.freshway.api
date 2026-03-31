package cjfw.wms.sys.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.sys.dto.SysPilot30ReqDto;
import cjfw.wms.sys.dto.SysPilot30ResDto;
import cjfw.wms.sys.service.SysPilot30Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : SangSuSung(kduimux@cj.com) 
 * @date : 2025.05.10 
 * @description : ADMIN > 시스템운영 > 프로그램 목록 조회 및 저장 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.10 SangSuSung(kduimux@cj.cj.com) 생성 </pre>
 */
@Tag(name = "ADMIN > 시스템운영 > 프로그램", description = "프로그램 목록 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/sys/pilot30")
public class SysPilot30Controller {

	private final SysPilot30Service SysPilot30Service;

	/**
	 * @description : 프로그램 목록 검색 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.09 SangSuSung(kduimux@cj.com) 생성 </pre>
	 */
	@Operation(summary = "프로그램 목록 조회", description = "프로그램 목록 조회")
	@GetMapping(value = "/v1.0/getPilot30List")
	public ApiResult<List<SysPilot30ResDto>> getPilot30List(SysPilot30ReqDto dto) {
		return ApiResult.createResult(SysPilot30Service.getPilot30List(dto));
	}

	/**
	 * @description : 프로그램 목록 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.09 SangSuSung(kduimux@cj.com) 생성 </pre>
	 */
	@Operation(summary = "프로그램 목록 저장", description = "프로그램 목록 저장")
	@PostMapping(value = "/v1.0/savePilot30")
	public ApiResult<Object> saveProgram(@RequestBody List<SysPilot30ReqDto> dto) {
		return ApiResult.createResult(SysPilot30Service.savePilot30(dto));
	}
}