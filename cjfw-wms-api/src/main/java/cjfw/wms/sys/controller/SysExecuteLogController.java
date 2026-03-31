package cjfw.wms.sys.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.sys.dto.SysExecuteLogReqDto;
import cjfw.wms.sys.dto.SysExecuteLogResDto;
import cjfw.wms.sys.service.SysExecuteLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiSooKim (jskim14@cj.net) 
 * @date : 2025.07.17 
 * @description : 시스템운영 > 시스템운영 > 프로시저실행로그 목록 조회 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.17 JiSooKim (jskim14@cj.net) 생성 </pre>
 */
@Tag(name = "시스템운영 > 시스템운영 > 프로시저실행로그", description = "프로시저 실행로그 목록 조회")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/sys/executeLog")
public class SysExecuteLogController {

	private final SysExecuteLogService executeLogService;

	/**
	 * @description : 프로시저실행로그 목록 검색 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.17 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	@Operation(summary = "코드 목록 검색 조회", description = "코드 목록 검색 조회")
	@GetMapping(value = "/v1.0/getLogHeaderList")
	public ApiResult<List<SysExecuteLogResDto>> getLogHeaderList(@Valid SysExecuteLogReqDto sysExecuteLogReqDto) {
		return ApiResult.createResult(executeLogService.getLogHeaderList(sysExecuteLogReqDto));
	}


}