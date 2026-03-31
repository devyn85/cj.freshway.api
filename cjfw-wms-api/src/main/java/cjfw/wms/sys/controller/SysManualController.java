package cjfw.wms.sys.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cjfw.core.file.FileUpload;
import cjfw.core.model.ApiResult;
import cjfw.wms.sys.dto.SysManualReqDto;
import cjfw.wms.sys.dto.SysManualResDto;
import cjfw.wms.sys.dto.SysProgramReqDto;
import cjfw.wms.sys.service.SysManualService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2026.01.29 
 * @description : ADMIN > 시스템운영 > 매뉴얼 목록 조회 및 저장 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2026.01.29 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Tag(name = "ADMIN > 시스템운영 > 매뉴얼", description = "매뉴얼 목록 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/sys/manual")
public class SysManualController {

	private final SysManualService sysManualService;
	
	/**
	 * @description : 매뉴얼 목록 검색 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.01.29 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "매뉴얼 목록 조회", description = "매뉴얼 목록 조회")
	@GetMapping(value = "/v1.0/getManualList")
	public ApiResult<List<SysManualResDto>> getManualList(@Valid SysProgramReqDto sysProgramReqDto) {
		return ApiResult.createResult(sysManualService.getManualList(sysProgramReqDto));
	}
	
	/**
	 * @description : 매뉴얼 파일 업로드
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.01.30 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@PostMapping(value = "/v1.0/saveManualFileUpload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ApiResult<String> saveFaxFileUpload(
			@RequestPart(value="params", required = true) SysManualReqDto sysManualReqDto,
			@RequestPart(value="file", required = false) List<MultipartFile> files,
			@RequestPart(value="fileInfoList", required = false) List<FileUpload> fileInfoList) {
		return ApiResult.createResult(sysManualService.saveManual(sysManualReqDto, files, fileInfoList));
	}
	
}