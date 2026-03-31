package cjfw.wms.ms.controller;

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
import cjfw.wms.ms.dto.MsCarDriverDetailResDto;
import cjfw.wms.ms.dto.MsCarDriverEntryInfoResDto;
import cjfw.wms.ms.dto.MsCarDriverExcelReqDto;
import cjfw.wms.ms.dto.MsCarDriverReqDto;
import cjfw.wms.ms.dto.MsCarDriverResDto;
import cjfw.wms.ms.service.MsCarDriverService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.08.12
 * @description : 기준정보 > 차량기준정보 > 차량정보 목록 조회 및 저장 Controller
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.03        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Tag(name = "기준정보 > 차량기준정보 > 차량정보", description = "차량정보 목록 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ms/carDriver")
public class MsCarDriverController {

	private final MsCarDriverService msCarDriverService;

	/**
	 * 
	 * @description : 차량정보 목록 검색 조회
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.03        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "차량정보 목록 조회", description = "차량정보 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<MsCarDriverResDto>> getMasterList(@RequestBody MsCarDriverReqDto dto) {
		return ApiResult.createResult(msCarDriverService.getMasterList(dto));
	}
	
	/**
	 * 
	 * @description : 차량정보 검색 조회
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.03        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "차량정보 상세 조회", description = "차량정보 상세 조회")
	@GetMapping(value = "/v1.0/getMaster")
	public ApiResult<MsCarDriverDetailResDto> getMaster(MsCarDriverReqDto dto) {
		return ApiResult.createResult(msCarDriverService.getMaster(dto));
	}
	
	/**
	 * 
	 * @description : 차량정보 입출차정보 목록 검색 조회
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.03        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "차량정보 입출차정보 목록 조회", description = "차량정보 입출차정보 목록 조회")
	@PostMapping(value = "/v1.0/getEntryInfoList")
	public ApiResult<List<MsCarDriverEntryInfoResDto>> getEntryInfoList(@RequestBody MsCarDriverReqDto dto) {
		return ApiResult.createResult(msCarDriverService.getEntryInfoList(dto));
	}
		
	/**
	 * @description : 차량정보 목록 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.03        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "차량정보 목록 저장", description = "차량정보 목록 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<Object> saveMasterList(@RequestBody List<MsCarDriverReqDto> dto) {
		return ApiResult.createResult(msCarDriverService.saveMasterList(dto));
	}
	
	/**
	 * @description : 차량정보 상세 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.03        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "차량정보 상세 저장", description = "차량정보 상세 저장")
	@PostMapping(value = "/v1.0/saveMaster")
	public ApiResult<Object> saveMaster(@RequestBody MsCarDriverReqDto dto) {
		return ApiResult.createResult(msCarDriverService.saveMaster(dto));
	}
	
	@Operation(summary = "차량정보 파일 업로드", description = "차량정보 파일 업로드")
	@PostMapping(value = "/v1.0/carDriverSaveFileUpload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ApiResult<String> carDriverSaveFileUpload(
			@RequestPart(value="file", required = true) List<MultipartFile> files,
			@RequestPart(value="fileInfoList", required = true) List<FileUpload> fileInfoList) {
		return ApiResult.createResult(msCarDriverService.carDriverSaveFileUpload(files, fileInfoList));
	}
	
	
	/**
	 * 
	 * @description : 기사ID목록 조회  팝업 기능 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.09 ParkJinWoo (jw.park@cj.net) 생성
	 */
	@Operation(summary = "기사ID목록 조회",description = "기사ID목록 조회")
	@PostMapping(value ="/v1.0/getDriverList" )
	public ApiResult<MsCarDriverResDto> getDriverList(@RequestBody MsCarDriverReqDto msCarDriverReqDto ){
		return ApiResult.createResult(msCarDriverService.getDriverList(msCarDriverReqDto));
	}
	
	/**
	 * @description : 차량정보 일괄업로드(excel) & 유효성검증 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.04     (dytpq362@cj.net)   생성
	 */
	@Operation(summary = "차량정보일괄업로드", description = "차량정보일괄업로드")
	@PostMapping(value = "/v1.0/saveExcelList")
	public ApiResult<List<MsCarDriverDetailResDto>> saveExcelList(@RequestBody @Valid MsCarDriverExcelReqDto dto) {
		return ApiResult.createResult(msCarDriverService.saveExcelList(dto));
	}
}