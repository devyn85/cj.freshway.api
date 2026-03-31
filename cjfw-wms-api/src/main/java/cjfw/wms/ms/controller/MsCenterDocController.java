package cjfw.wms.ms.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cjfw.core.file.FileUpload;
import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.MsCenterDocReqDto;
import cjfw.wms.ms.dto.MsCenterDocResDto;
import cjfw.wms.ms.service.MsCenterDocService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.06.24
 * @description : 기준정보 > 센터기준정보 > 센터서류 목록 조회 및 저장 Controller
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.24        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Tag(name = "기준정보 > 센터기준정보 > 센터서류", description = "센터서류 목록 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ms/centerDoc")
public class MsCenterDocController {

	private final MsCenterDocService msCenterDocService;

	/**
	 * 
	 * @description : 센터서류 목록 검색 조회
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.24        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "센터서류 목록 조회", description = "센터서류 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<MsCenterDocResDto>> getMasterList(@RequestBody MsCenterDocReqDto dto) {
		log.info("Received dto: {}", dto);
		return ApiResult.createResult(msCenterDocService.getMasterList(dto));
	}
		
	/**
	 * @description : 센터서류 목록 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.24        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "센터서류 목록 저장", description = "센터서류 목록 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<Object> saveMasterList(@RequestBody List<MsCenterDocReqDto> dto) {
		return ApiResult.createResult(msCenterDocService.saveMasterList(dto));
	}
	
	@Operation(summary = "센터서류 파일 업로드", description = "센터서류 파일 업로드")
	@PostMapping(value = "/v1.0/centerDocSaveFileUpload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ApiResult<String> carDriverSaveFileUpload(
			@RequestPart(value="params", required = true) MsCenterDocReqDto msCenterDocReqDto,
			@RequestPart(value="file", required = true) List<MultipartFile> files,
			@RequestPart(value="fileInfoList", required = true) List<FileUpload> fileInfoList) {
		return ApiResult.createResult(msCenterDocService.centerDocSaveFileUpload(msCenterDocReqDto, files, fileInfoList));
	}
	
	/**
	 * @description : 센터서류 파일 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.02.05 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "센터서류 파일 목록 조회", description = "센터서류 파일 목록 조회")
	@PostMapping(value = "/v1.0/getMasterFileList")
	public ApiResult<List<MsCenterDocResDto>> getMasterFileList(@RequestBody MsCenterDocReqDto dto) {
		return ApiResult.createResult(msCenterDocService.getMasterFileList(dto));
	}
	
}