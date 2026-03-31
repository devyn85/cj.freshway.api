package cjfw.wms.st.controller;

import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
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
import cjfw.wms.common.util.EtcUtil;
import cjfw.wms.st.dto.StTplReceiptReqReqDto;
import cjfw.wms.st.dto.StTplReceiptReqUploadFileResDto;
import cjfw.wms.st.service.StTplReceiptReqService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.10.31 
 * @description : OO 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.31 ParkJinWoo 생성
 */
@Tag(name = "정산 > 위탁물류 > 위탁입고요청")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/tplReceiptReq")
public class StTplReceiptReqController {
	private final StTplReceiptReqService stTplReceiptReqService;
	private static final Pattern SAFE_TOKEN = Pattern.compile("^[A-Za-z0-9_\\-]{1,64}$");
	private static final Pattern SAFE_LOCATION = Pattern.compile("^[A-Za-z0-9_\\-]{0,64}$");
	private static final int MAX_FILENAME_LENGTH = 255;
	
	/**
	 * @description : 위탁입고요청 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.03 ParkJinWoo 생성
	 */
	@Operation(summary = "위탁입고요청 목록 조회", description = "위탁입고요청 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<Object> getMasterList(@RequestBody StTplReceiptReqReqDto dto) {
		return ApiResult.createResult(stTplReceiptReqService.getMasterList(dto));
	}
	
	

	/**
	 * @description : 위탁입고요청 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.03 ParkJinWoo 생성
	 */
	@Operation(summary = "위탁입고요청 목록 조회", description = "위탁입고요청 목록 조회")
	@PostMapping(value = "/v1.0/getDocNo")
	public ApiResult<Object> getDocNo(@RequestBody StTplReceiptReqReqDto dto) {
		return ApiResult.createResult(stTplReceiptReqService.getDocNo(dto));
	}
	
	/**
	 * @description : 위탁입고요청 저장 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.03 ParkJinWoo 생성
	 */
	@Operation(summary = "위탁입고요청 목록 조회", description = "위탁입고요청 목록 조회")
	@PostMapping(value = "/v1.0/saveList")
	public ApiResult<Object> saveList(@RequestBody StTplReceiptReqReqDto dto) {
		return ApiResult.createResult(stTplReceiptReqService.saveList(dto));
	}
	/**
	 * @description : 위탁입고요청 저장 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.03 ParkJinWoo 생성
	 */
	@Operation(summary = "위탁입고요청 목록 저장", description = "위탁입고요청 목록 조회")
	@PostMapping(value = "/v1.0/getExcelCheck" ,consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ApiResult<Object> getExcelCheck(
			@RequestBody StTplReceiptReqReqDto dto
          ) {
		return ApiResult.createResult(stTplReceiptReqService.getExcelCheck(dto));
	}
	/**
	 * @description : 이미지 업로드 조회 (목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.30 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "이미지 업로드 조회 (목록)", description = "이미지 업로드 조회 (목록)")
	@GetMapping(value = "/v1.0/getUploadFileList")
	public ApiResult<List<StTplReceiptReqUploadFileResDto>> getUploadFileList(@Valid StTplReceiptReqReqDto dto) {
		return ApiResult.createResult(stTplReceiptReqService.getUploadFileList(dto));
	}
	
	/**
	 * @description : 고객배송정보 이미지 업로드
	 * @issues :<pre> 
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.13        JeongHyeongCheol (wjdgudcjf@cj.net)       생성
	 */
	@Operation(summary = "차량정보 파일 업로드", description = "차량정보 파일 업로드")
	@PostMapping(value = "/v1.0/saveStTplReceiptReqFileUpload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ApiResult<String> saveStTplReceiptReqFileUpload(
            @RequestPart(value="file", required = true) List<MultipartFile> files,
            @RequestPart(value="fileInfoList", required = true) List<FileUpload> fileInfoList) {
        return ApiResult.createResult(stTplReceiptReqService.saveStTplReceiptReqFileUpload(files, fileInfoList));
    }

    /**
     * @description : 고객배송조건 첨부 임시저장(NAS)
     */
    @Operation(summary = "고객배송조건 첨부 임시저장", description = "고객배송조건 첨부 임시저장")
    @PostMapping(value = "/v1.0/saveTplReceipReqFileUploadTemp", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ApiResult<String> saveStTplReceiptReqFileUploadTemp(
            @RequestPart(value="file", required = true) List<MultipartFile> files,
            @RequestPart(value="fileInfoList", required = true) List<FileUpload> fileInfoList,
            @RequestPart(value="docNo", required = true) String docNo,
            @RequestPart(value="docType", required = true) String docType,
            @RequestPart(value="status", required = true) String status,
    		@RequestPart(value="uploadLocation", required = false) String uploadLocation) {
    	// 리소스 주입 방지를 위해 issueno, code 검증
        if (docNo == null || !SAFE_TOKEN.matcher(docNo).matches()) {
            throw new IllegalArgumentException("Invalid issueno");
        }
        if (docType == null || !SAFE_TOKEN.matcher(docType).matches()) {
            throw new IllegalArgumentException("Invalid code");
        }

     // uploadLocation 검증 및 정규화 (경로 구분자 불허, 허용 문자 제한)
        String safeUploadLocation = null;
        if (uploadLocation != null && !uploadLocation.isBlank()) {
            String cleaned = uploadLocation.strip();
            if (!SAFE_LOCATION.matcher(cleaned).matches()) {
                throw new IllegalArgumentException("Invalid uploadLocation");
            }
            // 파일명 형태의 토큰으로 정규화 (경로 구분자 제거)
            safeUploadLocation = Paths.get(cleaned).getFileName().toString();
        }

        // 업로드된 파일 이름 검증 (경로 조작 방지)
        if (files != null) {
            for (MultipartFile mf : files) {
                String original = StringUtils.cleanPath(mf.getOriginalFilename() == null ? "" : mf.getOriginalFilename());
                if (original.isBlank()) {
                    throw new IllegalArgumentException("Empty file name is not allowed");
                }
                if (original.contains("..") || original.contains("/") || original.contains("\\")) {
                    throw new IllegalArgumentException("Invalid file name");
                }
                if (original.length() > MAX_FILENAME_LENGTH) {
                    throw new IllegalArgumentException("File name too long");
                }
            }
        }

        // fileInfoList 항목 검증 (가능한 경우 파일명 검사)
        if (fileInfoList != null) {
            for (FileUpload fu : fileInfoList) {
                try {
                    // 일부 구현체에 getFileName 메서드가 있을 수 있어 리플렉션으로 확인
                    String fname = null;
                    try {
                        fname = (String) fu.getClass().getMethod("getFileName").invoke(fu);
                    } catch (NoSuchMethodException nsme) {
                        // getFileName이 없을 수도 있으므로 무시
                    }
                    if (fname != null) {
                        String cleaned = StringUtils.cleanPath(fname);
                        if (cleaned.contains("..") || cleaned.contains("/") || cleaned.contains("\\")) {
                            throw new IllegalArgumentException("Invalid fileInfoList file name");
                        }
                        if (cleaned.length() > MAX_FILENAME_LENGTH) {
                            throw new IllegalArgumentException("fileInfoList file name too long");
                        }
                    }
                } catch (ReflectiveOperationException roe) {
                    // 리플렉션 실패 시 안전하게 입력 거부
                    throw new IllegalArgumentException("Invalid fileInfoList contents");
                }
            }
        }
        
        // 경로 조작 (Java/JSP) 대응
        uploadLocation = EtcUtil.setFilePathFilter(uploadLocation);
        docNo = EtcUtil.setFilePathFilter(docNo);
        
        return ApiResult.createResult(stTplReceiptReqService.saveStTplReceiptReqFileUploadTemp(files, fileInfoList, docNo, docType, uploadLocation,status));
    }
    
//    /**
//     * @description : 고객배송조건 첨부 확정(EDMS)
//     */
//    @Operation(summary = "고객배송조건 첨부 확정(EDMS)", description = "고객배송조건 첨부 확정(EDMS)")
//    @PostMapping(value = "/v1.0/confirmTplReceipReqFileUpload")
//    public ApiResult<String> confirmStTplReceiptReqFileUpload(@RequestBody StTplReceiptReqReqDto dto) {
//        return ApiResult.createResult(stTplReceiptReqService.confirmStTplReceiptReqFileUpload(dto));
//    }

    /**
     * @description : 고객배송조건 첨부 삭제(소프트 삭제)
     */
    @Operation(summary = "고객배송조건 첨부 삭제", description = "고객배송조건 첨부 삭제")
    @PostMapping(value = "/v1.0/deleteTplReceipReqFileUpload")
    public ApiResult<String> deleteStTplReceiptReqFileUpload(@RequestBody StTplReceiptReqReqDto dto) {
        return ApiResult.createResult(stTplReceiptReqService.deleteStTplReceiptReqFileUpload(dto));
    }
    
	@Operation(summary = "저장위치정보 파일 다운로드", description = "저장위치정보 파일 다운로드")
	@PostMapping(value = "/v1.0/stTplReceiptReqFileDownload")
	public void plantXslFileDownload(HttpServletResponse response, FileDownload fileDownload,  @RequestParam(value="docNo", required = true) String docNo) {
		stTplReceiptReqService.stTplReceiptReqFileDownload(response, fileDownload,docNo);
	}
}