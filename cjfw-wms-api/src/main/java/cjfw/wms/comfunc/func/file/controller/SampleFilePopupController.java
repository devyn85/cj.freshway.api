/**
 * Copyright (c) 2020 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.comfunc.func.file.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cjfw.core.file.FileDownload;
import cjfw.core.file.FileUpload;
import cjfw.core.file.dto.FileUploadResDto;
import cjfw.core.model.ApiResult;
import cjfw.wms.comfunc.func.file.dto.SampleFilePopupGetReqDto;
import cjfw.wms.comfunc.func.file.dto.SampleFilePopupGetResDto;
import cjfw.wms.comfunc.func.file.service.SampleFilePopupService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/**
 * 
 * The class FileUploadController<br>
 *
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/func/file")
public class SampleFilePopupController {

	private final SampleFilePopupService sampleFilePopupService;
	
	/**
	 * 첨부파일 리스트를 검색한다.<br>
	 */
	@GetMapping(value = "/search")
	public ApiResult<List<SampleFilePopupGetResDto>> getFileList(SampleFilePopupGetReqDto sampleFilePopupGetReqDto) {
		return ApiResult.createResult(sampleFilePopupService.getFileList(sampleFilePopupGetReqDto));
	}
	
	/**
	 * 첨부파일 저장<br>
	 */
	@PostMapping(value = "/save", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public FileUploadResDto saveFile(
			@RequestPart(value="file",required = false) List<MultipartFile> files,
			@RequestPart(value="fileInfoList",required = false) List<FileUpload> fileInfoList) {
		return sampleFilePopupService.saveFileList(files, fileInfoList);
	}
	
	/**
	 * download<br>
	 */
	@PostMapping(value = "/downloadFile")
	public void downloadFile(HttpServletResponse response, FileDownload fileDownload){
		sampleFilePopupService.downloadFile(response, fileDownload);
	}
	
	@Operation(summary = "차량정보 파일 업로드", description = "차량정보 파일 업로드")
	@PostMapping(value = "/v1.0/carDriverSaveFileUpload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ApiResult<String> carDriverSaveFileUpload(
			@RequestPart(value="file", required = true) List<MultipartFile> files,
			@RequestPart(value="fileInfoList", required = true) List<FileUpload> fileInfoList) {
		return ApiResult.createResult(sampleFilePopupService.carDriverSaveFileUpload(files, fileInfoList));
	}
	
	@Operation(summary = "저장위치정보 파일 업로드", description = "저장위치정보 파일 업로드")
	@PostMapping(value = "/v1.0/plantXslSaveFileUpload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public FileUploadResDto plantXslSaveFileUpload(
			@RequestPart(value="file", required = true) List<MultipartFile> files,
			@RequestPart(value="fileInfoList", required = true) List<FileUpload> fileInfoList) {
		return sampleFilePopupService.plantXslSaveFileUpload(files, fileInfoList);
	}
	
	@Operation(summary = "저장위치정보 파일 다운로드", description = "저장위치정보 파일 다운로드")
	@PostMapping(value = "/v1.0/plantXslFileDownload")
	public void plantXslFileDownload(HttpServletResponse response, FileDownload fileDownload) {
		sampleFilePopupService.plantXslFileDownload(response, fileDownload);
	}
}

