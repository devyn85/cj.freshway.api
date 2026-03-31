package cjfw.wms.cb.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cjfw.core.file.FileUpload;
import cjfw.core.model.ApiResult;
import cjfw.wms.cb.dto.CbCommunityDetailResDto;
import cjfw.wms.cb.dto.CbCommunityReqDto;
import cjfw.wms.cb.dto.CbCommunityResDto;
import cjfw.wms.cb.service.CbCommunityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiSooKim (jskim14@cj.net) 
 * @date : 2025.08.29 
 * @description : ADMIN > 시스템운영 > 시스템운영자열람자료 조회 및 저장 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.29 JiSooKim (jskim14@cj.net) 생성 </pre>
 */
@Tag(name = "ADMIN > 시스템운영 > 시스템정보", description = "시스템운영자열람자료 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/cb/community")
public class CbCommunityController {

	private final CbCommunityService cbCommunityService;

	/**
	 * @description : 시스템운영자열람자료 검색 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.29 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	@Operation(summary = "시스템운영자열람자료 조회", description = "시스템운영자열람자료 조회")
	@GetMapping(value = "/v1.0/getCommunityList")
	public ApiResult<List<CbCommunityResDto>> getProgramList(@Valid CbCommunityReqDto cbCommunityReqDto) {
		return ApiResult.createResult(cbCommunityService.getCommunityList(cbCommunityReqDto));
	}
	
	/**
	 * @description : 시스템운영자열람자료 검색 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.29 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	@Operation(summary = "시스템운영자열람자료 조회", description = "시스템운영자열람자료 조회")
	@GetMapping(value = "/v1.0/getCommunityDetail")
	public ApiResult<CbCommunityDetailResDto> getDetail(@Valid CbCommunityReqDto cbCommunityReqDto) {
		return ApiResult.createResult(cbCommunityService.getDetail(cbCommunityReqDto));
	}

	/**
	 * @description : 시스템운영자열람자료 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.29 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	@Operation(summary = "시스템운영자열람자료 저장", description = "시스템운영자열람자료 저장")
	@PostMapping(value = "/v1.0/saveCommunity", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ApiResult<String> saveCommunity(
			@RequestPart(value="params", required = true) List<CbCommunityReqDto> cbCommunityReqDto,
			@RequestPart(value="file", required = false) List<MultipartFile> files,
			@RequestPart(value="fileInfoList", required = false) List<FileUpload> fileInfoList) {
		return ApiResult.createResult(cbCommunityService.saveBoard(cbCommunityReqDto, files, fileInfoList));
	}
}