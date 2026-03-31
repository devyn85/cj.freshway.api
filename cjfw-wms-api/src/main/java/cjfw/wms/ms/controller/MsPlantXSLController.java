package cjfw.wms.ms.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cjfw.core.file.FileDownload;
import cjfw.core.file.FileUpload;
import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.MsPlantXSLDetailReqDto;
import cjfw.wms.ms.dto.MsPlantXSLDetailResDto;
import cjfw.wms.ms.dto.MsPlantXSLListReqDto;
import cjfw.wms.ms.dto.MsPlantXSLListResDto;
import cjfw.wms.ms.dto.MsPlantXSLSaveReqDto;
import cjfw.wms.ms.entity.MsPlantXSLFileEntity;
import cjfw.wms.ms.service.MsPlantXSLService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KwonJungYun (jungyun8667@cj.net)
 * @date : 2025.05.29
 * @description : 저장위치정보 Controller
 * @issues : <pre>
 * -----------------------------------------------------------
 * DATE AUTHOR MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.05.29 KwonJungYun (jungyun8667@cj.net) 생성
 */
@Tag(name = "기준정보 > 거래처기준정보 > 저장위치정보", description = "저장위치정보 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ms/plantxsl")
public class MsPlantXSLController {

	private final MsPlantXSLService msPlantXSLService;


/**
 * @description : 저장위치정보 목록 조회
 * @issues :
 * -----------------------------------------------------------
 * DATE AUTHOR MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.05.29 KwonJungYun (jungyun8667@cj.net) 생성
 */
	@Operation(summary = "저장위치정보 목록 조회", description = "저장위치정보 목록 조회")
	@PostMapping(value = "/v1.0/getPlantXSLList")
	public ApiResult<List<MsPlantXSLListResDto>> getPlantXSLList(@Valid @RequestBody MsPlantXSLListReqDto msPlantXSLReqDto) {
		return ApiResult.createResult(msPlantXSLService.getPlantXSLList(msPlantXSLReqDto));
	}

	/**
	 * @description : 저장위치정보 상세 조회
	 * @issues :
	 * -----------------------------------------------------------
	 * DATE AUTHOR MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.29 KwonJungYun (jungyun8667@cj.net) 생성
	 */
	@Operation(summary = "저장위치정보 상세 조회", description = "저장위치정보 상세 조회")
	@GetMapping(value = "/v1.0/getPlantXSLDtl")
	public ApiResult<MsPlantXSLDetailResDto> getPlantXSLDtl(@Valid MsPlantXSLDetailReqDto msPlantXSLReqDto) {
		return ApiResult.createResult(msPlantXSLService.getPlantXSLDtl(msPlantXSLReqDto));
	}

	/**
	 * @description : 저장위치정보 상세 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.22    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	@Operation(summary = "저장위치정보 상세 저장", description = "저장위치정보 상세 저장")
	@PostMapping(value = "/v1.0/savePlantXSLDtl")
	public ApiResult<String> savePlantXSLDtl(@RequestBody @Valid MsPlantXSLSaveReqDto reqDto) {
		return ApiResult.createResult(msPlantXSLService.savePlantXSL(reqDto));
	}

	/**
	 * @description : 플랜트 셀렉트박스 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.22    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	@Operation(summary = "저장위치정보 상세 저장", description = "저장위치정보 상세 저장")
	@GetMapping(value = "/v1.0/getPlantList")
	public ApiResult<List<Map<String,String>>> getPlantList() {
		return ApiResult.createResult(msPlantXSLService.getPlantList());
	}



	/**
	 * @description : 저장위치정보 첨부파일 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.02 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "저장위치정보 첨부파일 목록 조회", description = "저장위치정보 첨부파일 목록 조회")
	@PostMapping(value = "/v1.0/getMasterFileList")
	public ApiResult<List<MsPlantXSLFileEntity>> getMasterFileList(@Valid @RequestBody MsPlantXSLSaveReqDto dto) {

		return ApiResult.createResult(msPlantXSLService.getMasterFileList(dto));
	}

	/**
	 * @description : 저장위치정보 첨부파일 COUNT
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.18 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "저장위치정보 첨부파일 COUNT", description = "저장위치정보 첨부파일 COUNT")
	@PostMapping(value = "/v1.0/getAtchFileCnt")
	public ApiResult<MsPlantXSLDetailResDto> getAtchFileCnt(@Valid @RequestBody MsPlantXSLSaveReqDto dto) {
		return ApiResult.createResult(msPlantXSLService.getAtchFileCnt(dto));
	}

	/**
	 * @description : 저장위치정보 파일 업로드
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.02 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "저장위치정보 파일 업로드", description = "저장위치정보 파일 업로드")
	@PostMapping(value = "/v1.0/saveFileUpload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ApiResult<String> plantXslSaveFileUpload(
			@RequestPart(value="file", required = true) List<MultipartFile> files,
			@RequestPart(value="fileInfoList", required = true) List<FileUpload> fileInfoList,
			@RequestPart(value="serialkey", required = true) String serialkey) {
		return ApiResult.createResult(msPlantXSLService.saveFileUpload(files, fileInfoList, serialkey));
	}

	/**
	 * @description : 저장위치정보 파일 다운로드
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.02 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "저장위치정보 파일 다운로드", description = "저장위치정보 파일 다운로드")
	@PostMapping(value = "/v1.0/saveFileDownload")
	public void plantXslFileDownload(HttpServletResponse response, FileDownload fileDownload) {
		msPlantXSLService.saveFileDownload(response, fileDownload);
	}

	/**
	 * @description : 파일 삭제
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.03 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "파일 삭제", description = "파일 삭제")
	@PostMapping(value = "/v1.0/saveFileDelete")
	public ApiResult<String> saveFileDelete(@RequestBody MsPlantXSLSaveReqDto dto) {
		return ApiResult.createResult(msPlantXSLService.saveFileDelete(dto));
	}
}