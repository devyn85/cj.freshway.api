package cjfw.wms.cm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.cm.dto.CmFileUploadReqDto;
import cjfw.wms.cm.dto.CmFileUploadResDto;
import cjfw.wms.cm.service.CmFileUploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.08.29 
 * @description : 업로드 파일 정보 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.29 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Tag(name = "업로드 파일 정보", description = "업로드 파일 정보")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/cm/fileUpload")
public class CmFileUploadController {

	private final CmFileUploadService cmFileUploadService;

	/**
	 * @description : 파일 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.29 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "파일 목록 조회", description = "파일 목록 조회")
	@GetMapping(value = "/v1.0/getFileInfoList")
	public ApiResult<List<CmFileUploadResDto>> getFileInfoList(@Valid CmFileUploadReqDto cmFileUploadReqDto) {
		return ApiResult.createResult(cmFileUploadService.getFileInfoList(cmFileUploadReqDto));
	}

}