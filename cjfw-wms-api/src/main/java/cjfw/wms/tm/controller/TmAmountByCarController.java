package cjfw.wms.tm.controller;

import java.rmi.RemoteException;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cjfw.core.file.FileDownload;
import cjfw.core.file.FileUpload;
import cjfw.core.model.ApiResult;
import cjfw.wms.tm.dto.TmAmountByCarFilePopupResDto;
import cjfw.wms.tm.dto.TmAmountByCarReqDto;
import cjfw.wms.tm.dto.TmAmountByCarResDto;
import cjfw.wms.tm.service.TmAmountByCarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KwonJungYun (jungyun8667@cj.net)
 * @date : 2025.08.01
 * @description : 차량별수당관리
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.01 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
 */
@Tag(name = "정산 > 운송비정산 > 차량별수당관리", description = "차량별수당관리")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/tm/amountbycar")
public class TmAmountByCarController {

	private final TmAmountByCarService tmAmountByCarService;

	/**
	 * @description : 차량별수당관리 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.01 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
	 */
	@Operation(summary = "차량별수당관리 목록 조회", description = "차량별수당관리 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<TmAmountByCarResDto>> getMasterList(@Valid @RequestBody TmAmountByCarReqDto tmAmountByCarReqDto) {
		return ApiResult.createResult(tmAmountByCarService.getMasterList(tmAmountByCarReqDto));
	}

	/**
	 * @description : 차량별수당관리 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.01 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
	 */
	@Operation(summary = "차량별수당관리 목록 조회", description = "차량별수당관리 조회")
	@PostMapping(value = "/v1.0/getCarInfo")
	public ApiResult<List<TmAmountByCarResDto>> getCarInfo(@Valid @RequestBody TmAmountByCarReqDto tmAmountByCarReqDto) {
		return ApiResult.createResult(tmAmountByCarService.getCarInfo(tmAmountByCarReqDto));
	}

	/**
	 * @description : 차량별수당관리 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.01 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
	 */
	@Operation(summary = "차량별수당관리 목록 저장", description = "차량별수당관리 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<String> saveMasterList(@Valid @RequestBody TmAmountByCarReqDto tmAmountByCarReqDto) {
		return ApiResult.createResult(tmAmountByCarService.saveMasterList(tmAmountByCarReqDto));
	}
	
//	/**
//	 * @description : 차량별수당관리 저장
//	 * @issues :<pre>
//	 * -----------------------------------------------------------
//	 * DATE       AUTHOR                MAJOR_ISSUE
//	 * -----------------------------------------------------------
//	 * 2025.08.01 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
//	 */
//	@Operation(summary = "차량별수당관리 목록 저장", description = "차량별수당관리 저장")
//	@PostMapping(value = "/v1.0/getExcelValChk")
//	public ApiResult<?> getExcelValChk(@Valid @RequestBody TmAmountByCarReqDto tmAmountByCarReqDto) {
//		return ApiResult.createResult(tmAmountByCarService.getExcelValChkv(tmAmountByCarReqDto));
//	}
	
	/**
	 * @description : 차량별수당관리 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.01 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
	 */
	@Operation(summary = "차량별수당관리 목록 조회", description = "차량별수당관리 조회")
	@PostMapping(value = "/v1.0/getExcelValChk")
	public ApiResult<List<TmAmountByCarResDto>> getExcelValChk(@Valid @RequestBody TmAmountByCarReqDto tmAmountByCarReqDto) {
		return ApiResult.createResult(tmAmountByCarService.getExcelValChk(tmAmountByCarReqDto));
	}
	/**
	 * @description : 차량별수당관리 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.01 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
	 */
	@Operation(summary = "차량별수당관리 목록 저장", description = "차량별수당관리 저장")
	@PostMapping(value = "/v1.0/saveExcel")
	public ApiResult<String> saveExcel(@Valid @RequestBody TmAmountByCarReqDto tmAmountByCarReqDto) {
		return ApiResult.createResult(tmAmountByCarService.saveExcel(tmAmountByCarReqDto));
	}
	
	/**
	 * @description : 마감 확인 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.01.12 ParkJinWoo 생성
	 */
	@Operation(summary = "마감 확인", description = "마감 확인")
	@PostMapping(value = "/v1.0/getTrspCloseChk")
	public ApiResult<List<TmAmountByCarResDto>> trspCloseChk(@Valid @RequestBody TmAmountByCarReqDto reqDto) {
		return ApiResult.createResult(tmAmountByCarService.trspCloseChk(reqDto));
	}

    /**
     * @description : 파일 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2026.03.22    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "파일 목록 조회", description = "파일 목록 조회")
    @PostMapping(value = "/v1.0/getFileList")
    public ApiResult<List<TmAmountByCarFilePopupResDto>> getFileList(@Valid TmAmountByCarReqDto dto) throws RemoteException {
        return ApiResult.createResult(tmAmountByCarService.getFileList(dto));
    }
    
    /**
     * @description : 첨부파일 삭제
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2026.03.22    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "첨부파일 삭제", description = "첨부파일 삭제")
    @PostMapping(value = "/v1.0/deleteUploadFile")
    public ApiResult<String> deleteUploadFile(@RequestBody TmAmountByCarReqDto dto)  {
        return ApiResult.createResult(tmAmountByCarService.deleteUploadFile(dto));
    }
    
    /**
     * @description : 파일 업로드
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2026.03.22    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "파일 업로드", description = "파일 업로드")
    @PostMapping(value = "/v1.0/saveFileUpload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ApiResult<String> saveFileUpload(@Valid TmAmountByCarReqDto dto
                                        ,@RequestPart(value="file", required = true) List<MultipartFile> files
                                        ,@RequestPart(value="fileInfoList", required = true) List<FileUpload> fileInfoList)  {
        return ApiResult.createResult(tmAmountByCarService.saveFileUpload(dto, files, fileInfoList));
    }
    
    /**
     * @description : 파일 다운로드
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2026.03.22    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "파일 다운로드", description = "파일 다운로드")
    @PostMapping(value = "/v1.0/downloadFile")
    public void downloadFile(HttpServletResponse response, FileDownload fileDownload, @Valid TmAmountByCarReqDto dto) {
        tmAmountByCarService.downloadFile(response, fileDownload, dto);
    }
    
}