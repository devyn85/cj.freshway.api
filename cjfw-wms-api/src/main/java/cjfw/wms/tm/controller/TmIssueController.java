package cjfw.wms.tm.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cjfw.core.file.FileUpload;
import cjfw.core.model.ApiResult;
import cjfw.wms.common.util.StringUtil;
import cjfw.wms.ms.dto.MsCustDeliveryInfoPDetailResDto;
import cjfw.wms.tm.dto.TmIssueReqDto;
import cjfw.wms.tm.dto.TmIssueResDto;
import cjfw.wms.tm.service.TmIssueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author         : YeoSeungCheol (pw6375@cj.net) 
 * @date         : 2025.10.23 
 * @description : 배송이슈 Controller 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.23 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Tag(name = "배송 > 고객배송관리 > 배송이슈")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/tm/issue")
public class TmIssueController {
    private final TmIssueService tmIssueService;

    /**
     * @description : 배송이슈 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.23 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
     */
    @Operation(summary = "배송이슈 목록 조회")
    @PostMapping("/v1.0/getMasterList")
    public ApiResult<List<TmIssueResDto>> getMasterList(@RequestBody TmIssueReqDto reqDto) {
        // 거래처코드 배열 변환
        if ( ObjectUtils.isNotEmpty(reqDto) && StringUtil.isNotEmpty(reqDto.getSchCustKey())) {
            reqDto.setMultiToCustkey(
                Arrays.stream(reqDto.getSchCustKey().split(","))
                      .map(String::trim)
                      .filter(s -> !s.isEmpty())
                      .collect(Collectors.toList())
            );
        }
        return ApiResult.createResult(tmIssueService.getMasterList(reqDto));
    }

    /**
     * @description : 배송이슈 저장(신규/수정)
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.23 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
     */
    @Operation(summary = "배송이슈 저장(신규/수정)")
    @PostMapping("/v1.0/saveMasterList")
    public ApiResult<String> saveMasterList(@RequestBody List<TmIssueReqDto> reqList) {
        return ApiResult.createResult(tmIssueService.saveMasterList(reqList));
    }

    /**
     * @description : 배송이슈 삭제
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.23 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
     */
    @Operation(summary = "배송이슈 삭제")
    @PostMapping("/v1.0/deleteMasterList")
    public ApiResult<String> deleteMasterList(@RequestBody List<TmIssueReqDto> reqList) {
        return ApiResult.createResult(tmIssueService.deleteMasterList(reqList));
    }

    /**
     * @description : 배송이슈 확정
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.23 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
     */
    @Operation(summary = "배송이슈 확정")
    @PostMapping("/v1.0/confirmMasterList")
    public ApiResult<String> confirmMasterList(@RequestBody List<TmIssueReqDto> reqList) {
        return ApiResult.createResult(tmIssueService.confirmMasterList(reqList));
    }

    /**
     * @description : 배송이슈 첨부파일 업로드 팝업 - 첨부파일 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.23 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
     */
    @Operation(summary = "첨부파일 목록")
    @PostMapping("/v1.0/fileList")
    public ApiResult<Object> getPopupUploadFileList(@RequestBody TmIssueReqDto reqDto) {
        return ApiResult.createResult(tmIssueService.getPopupUploadFileList(reqDto));
    }

    /**
     * @description : 배송이슈 첨부파일 업로드 팝업 - 첨부파일 업로드
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.23 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
     */
    @Operation(summary = "첨부파일 업로드")
    @PostMapping("/v1.0/fileUpload")
    public ApiResult<String> insertFileInfo(
            @RequestPart(value="file", required = true) List<MultipartFile> files,
            @RequestPart(value="fileInfoList", required = true) List<FileUpload> fileInfoList,
            @RequestPart(value="serialKey", required = true) String serialKey,
            @RequestPart(value="issueNo", required = true) String issueNo) {
        return ApiResult.createResult(tmIssueService.insertFileInfo(files, fileInfoList, serialKey, issueNo));
    }
    
    /**
     * @description : 배송이슈 첨부파일 업로드 팝업 - 첨부파일 삭제
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.23 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
     */
    @Operation(summary = "첨부파일 삭제")
    @PostMapping("/v1.0/fileDelete")
    public ApiResult<String> saveFileDelete(@RequestBody List<TmIssueReqDto> delList) {
//        return ApiResult.createResult(tmIssueService.saveFileDelete(reqDto));
        return ApiResult.createResult(tmIssueService.saveFileDeleteList(delList));
    }

    /**
     * @description : 첨부파일 다운로드 카운트
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.23 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
     */
    @Operation(summary = "첨부파일 다운로드 카운트")
    @PostMapping("/v1.0/fileDownloadCount")
    public ApiResult<String> updateDownloadCnt(@RequestBody TmIssueReqDto reqDto) {
        return ApiResult.createResult(tmIssueService.updateDownloadCnt(reqDto));
    }
    
    /** @description : excel upload 할때 정보의 validation 체크
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2026.02.05 jun (kthis77@cj.net) 생성 </pre>
    */
    @Operation(summary = "excel upload validation")
    @PostMapping("/v1.0/getValidateExcelList")
    public ApiResult<List<TmIssueResDto>> getValidateExcelList(@RequestBody List<TmIssueReqDto> excelList) {
        return ApiResult.createResult(tmIssueService.getValidateExcelList(excelList));
    }
    
    /**
	 * @description : 협력사 담당자 알림톡 수신 대상 개인정보 복호화(단건)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.17 생성 </pre>
	 */
	@Operation(summary = "협력사 담당자 개인정보 복호화(단건)", description = "협력사 담당자 대상 개인정보 복호화(단건)")
	@PostMapping(value = "/v1.0/getDetailList")
	public ApiResult<TmIssueResDto> getDetailList(@RequestBody @Valid TmIssueReqDto dto) {
		return ApiResult.createResult(tmIssueService.getDetailList(dto));
	}
}
