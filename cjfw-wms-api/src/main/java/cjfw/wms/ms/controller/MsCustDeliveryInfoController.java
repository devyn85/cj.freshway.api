package cjfw.wms.ms.controller;

import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cjfw.core.file.FileUpload;
import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.cm.dto.CmCustPopupResDto;
import cjfw.wms.common.util.EtcUtil;
import cjfw.wms.ms.dto.MsCustDeliveryInfoPopResDto;
import cjfw.wms.ms.dto.MsCustDeliveryInfoReqDto;
import cjfw.wms.ms.dto.MsCustDeliveryInfoResDto;
import cjfw.wms.ms.dto.MsCustDeliveryInfoUploadFileResDto;
import cjfw.wms.ms.dto.MsCustDlvInfoHisPopupResDto;
import cjfw.wms.ms.service.MsCustDeliveryInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.06.19 
 * @description : 기준정보 > 거래처기준정보 > GPS좌표등록 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.19 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Tag(name = "기준정보 > 거래처기준정보 > GPS좌표등록", description = "GPS좌표등록")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ms/custdelivery")
public class MsCustDeliveryInfoController {

	private final MsCustDeliveryInfoService msCustDeliveryInfoService;
	
	private static final Pattern SAFE_TOKEN = Pattern.compile("^[A-Za-z0-9_\\-]{1,64}$");
	private static final Pattern SAFE_LOCATION = Pattern.compile("^[A-Za-z0-9_\\-]{0,64}$");
	private static final int MAX_FILENAME_LENGTH = 255;
	
	/**
	 * @description : GPS 좌표등록 조회 (목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.30 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "GPS 좌표등록 조회 (목록)", description = "GPS 좌표등록 조회 (목록)")
	@PostMapping(value = "/v1.0/getGpsMasterList")
	public ApiResult<Page<MsCustDeliveryInfoResDto>> getGpsMasterList(@RequestBody @Valid MsCustDeliveryInfoReqDto dto) {
		return ApiResult.createResult(msCustDeliveryInfoService.getGpsMasterList(dto));
	}
	
	/**
	 * @description : GPS 좌표 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.19 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "GPS 좌표 저장", description = "GPS 좌표 저장")
	@PostMapping(value = "/v1.0/saveDeliveryInfo")
	public ApiResult<List<MsCustDeliveryInfoResDto>> saveDeliveryInfo(@RequestBody @Valid MsCustDeliveryInfoReqDto msCustDeliveryInfoReqDto) {
		return ApiResult.createResult(msCustDeliveryInfoService.saveDeliveryInfo(msCustDeliveryInfoReqDto));
	}
	
	/**
	 * @description : 거래처 배송정보 조회 (목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.30 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "거래처 정보 조회 (목록)", description = "거래처 정보 조회 (목록)")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<Page<MsCustDeliveryInfoResDto>> getMasterList(@RequestBody @Valid MsCustDeliveryInfoReqDto dto) {
		return ApiResult.createResult(msCustDeliveryInfoService.getMasterList(dto));
	}
	
	/**
	 * @description : 거래처 배송정보 조회 (단건) 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.30 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "거래처 정보 조회 (단건)", description = "거래처 정보 조회 (단건)")
	@GetMapping(value = "/v1.0/getMaster")
	public ApiResult<MsCustDeliveryInfoResDto> getMaster(@Valid MsCustDeliveryInfoReqDto dto) {
		return ApiResult.createResult(msCustDeliveryInfoService.getMaster(dto));
	}
	
	/**
	 * @description : 거래처 인쇄 배송정보 조회 (목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.30 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "거래처 인쇄 정보 조회 (목록)", description = "거래처 인쇄 정보 조회 (목록)")
	@GetMapping(value = "/v1.0/getMasterPrintList")
	public ApiResult<List<MsCustDeliveryInfoResDto>> getMasterPrintList(@Valid MsCustDeliveryInfoReqDto dto) {
		return ApiResult.createResult(msCustDeliveryInfoService.getMasterPrintList(dto));
	}
		
	/**
	 * @description : 거래처 배송정보 저장(목록조회 화면)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.30 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "거래처 배송정보 저장(목록조회 화면)", description = "거래처 배송정보 저장(목록조회 화면)")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<String> saveMasterList(@RequestBody @Valid List<MsCustDeliveryInfoReqDto> dtoList) throws Exception {
		return ApiResult.createResult(msCustDeliveryInfoService.saveMasterList(dtoList));
	}
	
	/**
	 * @description : 엑셀 업로드 유효성 검사(목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.30 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "엑셀 업로드 유효성 검사(목록)", description = "엑셀 업로드 유효성 검사(목록)")
	@PostMapping(value = "/v1.0/getValidateExcelList")
	public ApiResult<List<MsCustDeliveryInfoResDto>> getValidateExcelList(@RequestBody @Valid List<MsCustDeliveryInfoReqDto> dtoList) {
		return ApiResult.createResult(msCustDeliveryInfoService.getValidateExcelList(dtoList));
	}
	
	/**
	 * @description : 거래처 배송정보 저장(상세조회 화면)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.30 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "고객배송조건 조회화면에서 UPDATE", description = "고객배송조건 조회화면에서 UPDATE")
	@PostMapping(value = "/v1.0/saveMaster")
	public ApiResult<String> saveMaster(@RequestBody @Valid MsCustDeliveryInfoReqDto reqDto) throws Exception {
		return ApiResult.createResult(msCustDeliveryInfoService.saveMaster(reqDto));
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
	public ApiResult<List<MsCustDeliveryInfoUploadFileResDto>> getUploadFileList(@Valid MsCustDeliveryInfoReqDto dto) {
		return ApiResult.createResult(msCustDeliveryInfoService.getUploadFileList(dto));
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
	@PostMapping(value = "/v1.0/saveCustDeliveryInfoFileUpload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ApiResult<String> saveCustDeliveryInfoFileUpload(
            @RequestPart(value="file", required = true) List<MultipartFile> files,
            @RequestPart(value="fileInfoList", required = true) List<FileUpload> fileInfoList) {
        return ApiResult.createResult(msCustDeliveryInfoService.saveCustDeliveryInfoFileUpload(files, fileInfoList));
    }

    /**
     * @description : 고객배송조건 첨부 임시저장(NAS)
     */
    @Operation(summary = "고객배송조건 첨부 임시저장", description = "고객배송조건 첨부 임시저장")
    @PostMapping(value = "/v1.0/saveCustDeliveryInfoFileUploadTemp", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ApiResult<String> saveCustDeliveryInfoFileUploadTemp(
            @RequestPart(value="file", required = true) List<MultipartFile> files,
            @RequestPart(value="fileInfoList", required = true) List<FileUpload> fileInfoList,
            @RequestPart(value="issueno", required = true) String issueno,
            @RequestPart(value="code", required = true) String code,
    		@RequestPart(value="uploadLocation", required = false) String uploadLocation) {
    	// 리소스 주입 방지를 위해 issueno, code 검증
        if (issueno == null || !SAFE_TOKEN.matcher(issueno).matches()) {
            throw new IllegalArgumentException("Invalid issueno");
        }
        if (code == null || !SAFE_TOKEN.matcher(code).matches()) {
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
        issueno = EtcUtil.setFilePathFilter(issueno);
        code = EtcUtil.setFilePathFilter(code);

        // 모든 검증 통과 — 검증된 uploadLocation을 사용하여 서비스 호출 (null 가능)
  	    return ApiResult.createResult(EtcUtil.setFilePathFilter(msCustDeliveryInfoService.saveCustDeliveryInfoFileUploadTemp(files, fileInfoList, issueno, code, uploadLocation)));
    }

    /**
     * @description : 고객배송조건 첨부 확정(EDMS)
     */
    @Operation(summary = "고객배송조건 첨부 확정(EDMS)", description = "고객배송조건 첨부 확정(EDMS)")
    @PostMapping(value = "/v1.0/confirmCustDeliveryInfoFileUpload")
    public ApiResult<String> confirmCustDeliveryInfoFileUpload(@RequestBody MsCustDeliveryInfoReqDto dto) {
        return ApiResult.createResult(msCustDeliveryInfoService.confirmCustDeliveryInfoFileUpload(dto));
    }

    /**
     * @description : 고객배송조건 첨부 삭제(소프트 삭제)
     */
    @Operation(summary = "고객배송조건 첨부 삭제", description = "고객배송조건 첨부 삭제")
    @PostMapping(value = "/v1.0/deleteCustDeliveryInfoFileUpload")
    public ApiResult<String> deleteCustDeliveryInfoFileUpload(@RequestBody MsCustDeliveryInfoReqDto dto) {
        return ApiResult.createResult(msCustDeliveryInfoService.deleteCustDeliveryInfoFileUpload(dto));
    }
    
    /**
	 * @description : 추천실착지코드 팝업 (목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.01.12 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "추천실착지코드 팝업 (목록)", description = "추천실착지코드 팝업 (목록)")
	@PostMapping(value = "/v1.0/getTruthcustkeyList")
	public ApiResult<Page<MsCustDeliveryInfoPopResDto>> getTruthcustkeyList(@RequestBody @Valid MsCustDeliveryInfoReqDto dto) {
		return ApiResult.createResult(msCustDeliveryInfoService.getTruthcustkeyList(dto));
	}
	
    @Operation(summary = "실착지관리거래처코드기준으로 주소 정보 (목록)", description = "고객 배송정보관리 (테스트)")
    @PostMapping(value = "/v1.0/selectAddressInfoList")
    public ApiResult<List<CmCustPopupResDto>> selectAddressInfoList(@RequestBody List<MsCustDeliveryInfoReqDto> list) {
        return ApiResult.createResult(msCustDeliveryInfoService.selectAddressInfoList(list));
    }
    /**
	 * @description : 주소 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.09  (dytpq362@cj.net) 생성 </pre>
	 */
	@Operation(summary = "거래처 정보 조회 (목록)", description = "거래처 정보 조회 (목록)")
	@PostMapping(value = "/v1.0/selectAddressList")
	public ApiResult<List<MsCustDeliveryInfoResDto>> getAddressList(@RequestBody @Valid MsCustDeliveryInfoReqDto dto) {
		return ApiResult.createResult(msCustDeliveryInfoService.getAddressList(dto)); //
	}
	
}
