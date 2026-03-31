package cjfw.wms.tm.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cjfw.core.file.FileUpload;
import cjfw.core.model.ApiResult;
import cjfw.wms.tm.dto.TmCrmWmsMemoReqDto;
import cjfw.wms.tm.dto.TmCrmWmsMemoResDto;
import cjfw.wms.tm.service.TmCrmWmsMemoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author 		: YeoSeungCheol (pw6375@cj.net) 
 * @date 		: 2025.10.22 
 * @description : CRM 요청관리 Controller 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.22 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Tag(name = "배송 > 고객배송관리 > CRM요청관리")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/tm/crmWmsMemo")
public class TmCrmWmsMemoController {
    private final TmCrmWmsMemoService tmCrmWmsMemoService;

    /**
	 * @description : 일별메모 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.22 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    @Operation(summary = "일별메모 목록 조회")
    @PostMapping("/v1.0/getMasterList")
    public ApiResult<List<TmCrmWmsMemoResDto>> getMasterList(@RequestBody TmCrmWmsMemoReqDto reqDto) {
        return ApiResult.createResult(tmCrmWmsMemoService.getMasterList(reqDto));
    }

    /**
	 * @description : 처리이력목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.22 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    @Operation(summary = "처리이력목록 조회")
    @PostMapping("/v1.0/getDetailHistoryList")
    public ApiResult<List<TmCrmWmsMemoResDto>> getHistoryList(@RequestBody TmCrmWmsMemoReqDto reqDto) {
        return ApiResult.createResult(tmCrmWmsMemoService.getDetailHistoryList(reqDto));
    }
    
    /**
	 * @description : 처리상세 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.29 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    @Operation(summary = "처리상세 조회")
    @PostMapping("/v1.0/getDetailInfo")
    public ApiResult<TmCrmWmsMemoResDto> getDetailInfo(@RequestBody TmCrmWmsMemoReqDto reqDto) {
        return ApiResult.createResult(tmCrmWmsMemoService.getDetailInfo(reqDto));
    }

    /**
	 * @description : 처리상세 확정
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.22 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    @Operation(summary = "처리상세 확정")
    @PostMapping("/v1.0/saveCrmApply")
    public ApiResult<String> saveCrmApply(@RequestBody TmCrmWmsMemoReqDto reqDto) {
        List<TmCrmWmsMemoResDto> mstList = reqDto.getMasterList();
        List<TmCrmWmsMemoResDto> histList = reqDto.getHistList();
        return ApiResult.createResult(tmCrmWmsMemoService.saveCrmApply(mstList, histList));
    }

    /**
	 * @description : 목록 또는 처리이력 삭제
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.22 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    @Operation(summary = "목록 또는 처리이력 삭제")
    @PostMapping("/v1.0/delete")
    public ApiResult<String> crmDelete(@RequestBody TmCrmWmsMemoReqDto reqDto) {
        List<TmCrmWmsMemoResDto> mstList = reqDto.getMasterList();
        List<TmCrmWmsMemoResDto> histList = reqDto.getHistList();
        
        return ApiResult.createResult(tmCrmWmsMemoService.deleteCrm(mstList, histList));
    }

    /**
	 * @description : 일별메모 상세 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.22 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    @Operation(summary = "일별메모 저장(등록/수정)")
    @PostMapping("/v1.0/saveDetail")
    public ApiResult<String> saveDetail(@RequestBody TmCrmWmsMemoReqDto reqDto) {
        return ApiResult.createResult(tmCrmWmsMemoService.saveDetail(reqDto));
    }

    /**
	 * @description : CRM요청관리 첨부파일 팝업 - 파일목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.22 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    @PostMapping("/v1.0/fileList")
    @Operation(summary = "처리이력목록 - 파일첨부 - CRM요청관리 첨부파일 팝업의 파일목록")
    public ApiResult<Object> getPopupUploadFileList(@RequestBody TmCrmWmsMemoReqDto reqDto) {
        return ApiResult.createResult(tmCrmWmsMemoService.getPopupUploadFileList(reqDto));
    }

    /**
	 * @description : CRM요청관리 첨부파일 팝업 - 파일 업로드
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.22 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    @PostMapping("/v1.0/fileUpload")
    @Operation(summary = "처리이력목록 - 파일첨부 - CRM요청관리 첨부파일 팝업 - 파일 업로드")
    public ApiResult<String> insertFileInfo(
    		@RequestPart(value="file", required = true) List<MultipartFile> files,
			@RequestPart(value="fileInfoList", required = true) List<FileUpload> fileInfoList,
			@RequestPart(value="serialKey", required = true) String serialKey,
			@RequestPart(value="issueNo", required = true) String issueNo) {
        return ApiResult.createResult(tmCrmWmsMemoService.insertFileInfo(files, fileInfoList, serialKey, issueNo));
    }

    /**
	 * @description : CRM요청관리 첨부파일 팝업 - 파일 삭제
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.22 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    @PostMapping("/v1.0/fileDelete")
    @Operation(summary = "처리이력목록 - 파일첨부 - CRM요청관리 첨부파일 팝업 - 파일 삭제")
    public ApiResult<String> saveFileDelete(@RequestBody TmCrmWmsMemoReqDto reqDto) {
        return ApiResult.createResult(tmCrmWmsMemoService.saveFileDelete(reqDto));
    }

    /**
	 * @description : 파일 다운로드 카운트
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.22 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    @Operation(summary = "파일 다운로드 카운트")
    @PostMapping("/v1.0/fileDownloadCount")
    public ApiResult<String> updateDownloadCnt(@RequestBody TmCrmWmsMemoReqDto reqDto) {
        return ApiResult.createResult(tmCrmWmsMemoService.updateDownloadCnt(reqDto));
    }
}
