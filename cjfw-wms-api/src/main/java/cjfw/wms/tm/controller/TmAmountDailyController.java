package cjfw.wms.tm.controller;

import java.rmi.RemoteException;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cjfw.core.file.FileDownload;
import cjfw.core.file.FileUpload;
import cjfw.core.model.ApiResult;
import cjfw.wms.tm.dto.TmAmountDailyFilePopupResDto;
import cjfw.wms.tm.dto.TmAmountDailyReqDto;
import cjfw.wms.tm.dto.TmAmountDailyResDto;
import cjfw.wms.tm.service.TmAmountDailyService;
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
 * @description : 일자별수당관리
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.01 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
 */
@Tag(name = "정산 > 운송비정산 > 일자별수당관리", description = "일자별수당관리")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/tm/amountdaily")
public class TmAmountDailyController {

	private final TmAmountDailyService tmAmountByDailyService;

	/**
	 * @description : 일자별수당관리 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.01 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
	 */
	@Operation(summary = "일자별수당관리 목록 조회", description = "일자별수당관리 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<TmAmountDailyResDto>> getMasterList(@Valid @RequestBody TmAmountDailyReqDto tmAmountByDailyReqDto) {
		return ApiResult.createResult(tmAmountByDailyService.getMasterList(tmAmountByDailyReqDto));
	}

	/**
	 * @description : 일자별수당관리 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.01 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
	 */
	@Operation(summary = "일자별수당관리 목록 조회", description = "일자별수당관리 조회")
	@PostMapping(value = "/v1.0/getCarInfo")
	public ApiResult<List<TmAmountDailyResDto>> getCarInfo(@Valid @RequestBody TmAmountDailyReqDto tmAmountByDailyReqDto) {
		return ApiResult.createResult(tmAmountByDailyService.getCarInfo(tmAmountByDailyReqDto));
	}
	/**
	 * @description : 일자별수당관리 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.01 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
	 */
	@Operation(summary = "일자별수당관리 목록 저장", description = "일자별수당관리 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<String> saveMasterList(@Valid @RequestBody TmAmountDailyReqDto tmAmountByDailyReqDto) {
		return ApiResult.createResult(tmAmountByDailyService.saveMasterList(tmAmountByDailyReqDto));
	}
	
	/**
	 * @description : 일자별수당관리 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.01 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
	 */
	@Operation(summary = "일자별수당관리 목록 저장", description = "일자별수당관리 저장")
	@PostMapping(value = "/v1.0/getExcelValChk")
	public ApiResult<List<TmAmountDailyResDto>> getExcelValChk(@Valid @RequestBody TmAmountDailyReqDto tmAmountByDailyReqDto) {
		return ApiResult.createResult(tmAmountByDailyService.getExcelValChk(tmAmountByDailyReqDto));
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
	public ApiResult<List<TmAmountDailyResDto>> trspCloseChk(@Valid @RequestBody TmAmountDailyReqDto tmAmountByDailyReqDto) {
		return ApiResult.createResult(tmAmountByDailyService.trspCloseChk(tmAmountByDailyReqDto));
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
    public ApiResult<List<TmAmountDailyFilePopupResDto>> getFileList(@Valid TmAmountDailyReqDto dto) throws RemoteException {
        return ApiResult.createResult(tmAmountByDailyService.getFileList(dto));
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
    public ApiResult<String> deleteUploadFile(@RequestBody TmAmountDailyReqDto dto)  {
        return ApiResult.createResult(tmAmountByDailyService.deleteUploadFile(dto));
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
    public ApiResult<String> saveFileUpload(@Valid TmAmountDailyReqDto dto
                                        ,@RequestPart(value="file", required = true) List<MultipartFile> files
                                        ,@RequestPart(value="fileInfoList", required = true) List<FileUpload> fileInfoList)  {
        return ApiResult.createResult(tmAmountByDailyService.saveFileUpload(dto, files, fileInfoList));
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
    public void downloadFile(HttpServletResponse response, FileDownload fileDownload, @Valid TmAmountDailyReqDto dto) {
        tmAmountByDailyService.downloadFile(response, fileDownload, dto);
    }
	
}