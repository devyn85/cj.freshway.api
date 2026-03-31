package cjfw.wms.sys.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.sys.dto.SysIFManagerReqDto;
import cjfw.wms.sys.dto.SysIFManagerResDto;
import cjfw.wms.sys.service.SysIFManagerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiSooKim (jskim14@cj.net) 
 * @date : 2025.08.01 
 * @description : 시스템운영 > 인터페이스모니터링 > 인터페이스 상태관리 목록 조회 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.01 JiSooKim (jskim14@cj.net) 생성 </pre>
 */
@Tag(name = "시스템운영 > 인터페이스모니터링 > 인터페이스 상태관리", description = "인터페이스 상태관리 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/sys/ifManager")
public class SysIFManagerController {

	private final SysIFManagerService sysIFManagerService;

	/**
	 * @description : 인터페이스 상태관리 목록 검색 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.01 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	@Operation(summary = "인터페이스 상태관리 조회", description = "인터페이스 상태관리 조회")
	@GetMapping(value = "/v1.0/getIFManagerHeaderList")
	public ApiResult<List<SysIFManagerResDto>> getIFManagerHeaderList(@Valid SysIFManagerReqDto sysIFManagerReqDto) {
		return ApiResult.createResult(sysIFManagerService.getIFManagerHeaderList(sysIFManagerReqDto));
	}
	
	/**
	 * @description : 인터페이스 상태관리 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.01 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	@Operation(summary = "인터페이스 상태관리 저장", description = "인터페이스 상태관리 저장")
	@PostMapping(value = "/v1.0/saveIFManager")
	public ApiResult<String> saveIFManager(@RequestBody @Valid List<SysIFManagerReqDto> sysIFManagerReqDto) {
		return ApiResult.createResult(sysIFManagerService.saveIFManager(sysIFManagerReqDto));
	}


}