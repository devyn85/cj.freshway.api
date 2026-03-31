package cjfw.wms.ib.controller;

import java.rmi.RemoteException;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cjfw.core.file.FileDownload;
import cjfw.core.file.FileUpload;
import cjfw.core.model.ApiResult;
import cjfw.wms.cm.dto.CmSearchCostCodePopupReqDto;
import cjfw.wms.cm.dto.CmSearchCostCodePopupResDto;
import cjfw.wms.ib.dto.IbExpenseApprovalUserPopupResDto;
import cjfw.wms.ib.dto.IbExpenseDoucmentDetailPopupResDto;
import cjfw.wms.ib.dto.IbExpenseDoucmentHeaderPopupResDto;
import cjfw.wms.ib.dto.IbExpenseElecTaxPopupReqDto;
import cjfw.wms.ib.dto.IbExpenseElecTaxPopupResDto;
import cjfw.wms.ib.dto.IbExpenseFilePopupResDto;
import cjfw.wms.ib.dto.IbExpenseIfStatusPopupResDto;
import cjfw.wms.ib.dto.IbExpensePopupReqDto;
import cjfw.wms.ib.dto.IbExpenseReqDto;
import cjfw.wms.ib.dto.IbExpenseResDto;
import cjfw.wms.ib.service.IbExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.08.05 
 * @description : 비용기표 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.05   KimSunHo(sunhokim6229@cj.net)   생성
 */
@Tag(name = "정산 > 외부창고정산 > 비용기표", description = "비용기표 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ib/expense")
public class IbExpenseController {

	private final IbExpenseService ibExpenseService;

	/**
	 * @description : 비용기표 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.05    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	@Operation(summary = "비용기표 목록 조회", description = "비용기표 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<IbExpenseResDto>> getMasterList(@Valid IbExpenseReqDto dto) {
		return ApiResult.createResult(ibExpenseService.getMasterList(dto));
	}
	
	/**
     * @description : 비용기표 문서정보 팝업 헤더 정보 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.08.05    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "비용기표 문서정보 팝업 헤더 정보 조회", description = "비용기표 문서정보 팝업 헤더 정보 조회")
    @PostMapping(value = "/v1.0/getPopupDocumentInfoHeader")
    public ApiResult<IbExpenseDoucmentHeaderPopupResDto> getPopupDocumentInfoHeader(@Valid IbExpensePopupReqDto dto) {
        return ApiResult.createResult(ibExpenseService.getPopupDocumentInfoHeader(dto));
    }

    /**
     * @description : 비용기표 문서정보 팝업 상세 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.08.05    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "비용기표 문서정보 팝업 상세 목록 조회", description = "비용기표 문서정보 팝업 상세 목록 조회")
    @PostMapping(value = "/v1.0/getPopupDocumentInfoDetail")
    public ApiResult<List<IbExpenseDoucmentDetailPopupResDto>> getPopupDocumentInfoDetail(@Valid IbExpensePopupReqDto dto) {
        return ApiResult.createResult(ibExpenseService.getPopupDocumentInfoDetail(dto));
    }
    
    /**
     * @throws RemoteException 
     * @description : 비용기표 매입세금계산서 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.08.05    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "비용기표 매입세금계산서 목록 조회", description = "비용기표 매입세금계산서 목록 조회")
    @PostMapping(value = "/v1.0/getElecTaxList")
    public ApiResult<List<IbExpenseElecTaxPopupResDto>> getElecTaxList(@Valid IbExpenseElecTaxPopupReqDto dto) throws RemoteException {
        return ApiResult.createResult(ibExpenseService.getElecTaxList(dto));
    }
    
    /**
     * @throws RemoteException 
     * @description : 비용기표 매입세금계산서 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.08.05    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "비용기표 코스트코드 목록 조회", description = "비용기표 코스트코드 목록 조회")
    @PostMapping(value = "/v1.0/getCostCodeList")
    public ApiResult<List<CmSearchCostCodePopupResDto>> getCostCodeList(@Valid CmSearchCostCodePopupReqDto dto) throws RemoteException {
        return ApiResult.createResult(ibExpenseService.getCostCodeList(dto));
    }
    
    /**
     * @throws RemoteException 
     * @description : 비용기표 매입세금계산서 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.08.05    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "비용기표 인터페이스 결과 목록 조회", description = "비용기표 인터페이스 결과 목록 조회")
    @PostMapping(value = "/v1.0/getIFStatusList")
    public ApiResult<List<IbExpenseIfStatusPopupResDto>> getIFStatusList(@Valid IbExpensePopupReqDto dto) throws RemoteException {
        return ApiResult.createResult(ibExpenseService.getIFStatusList(dto));
    }
    
    /**
     * @throws Exception
     * @description : Document Info 헤더 저장
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.07.21    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "Document Info 헤더 저장", description = "Document Info 헤더 저장")
    @PostMapping(value = "/v1.0/savePopupDocumentInfoHeader")
    public ApiResult<String> savePopupDocumentInfoHeader(@RequestBody IbExpensePopupReqDto dto) throws Exception {
        return ApiResult.createResult(ibExpenseService.savePopupDocumentInfoHeader(dto));
    }
    
    /**
     * @throws Exception
     * @description : Document Info 헤더 저장
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.07.21    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "Document Info 디테일 저장", description = "Document Info 디테일 저장")
    @PostMapping(value = "/v1.0/savePopupDocumentInfoDetail")
    public ApiResult<String> savePopupDocumentInfoDetail(@RequestBody IbExpensePopupReqDto dto) throws Exception {
        return ApiResult.createResult(ibExpenseService.savePopupDocumentInfoDetail(dto));
    }
    
    /**
     * @description : Document Info KG 비용분배 저장
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.08    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "Document Info KG 비용분배 저장", description = "Document Info KG 비용분배 저장")
    @PostMapping(value = "/v1.0/saveKGPopupDocumentInfoDetail")
    public ApiResult<String> saveKGPopupDocumentInfoDetail(@RequestBody IbExpensePopupReqDto dto)  {
        return ApiResult.createResult(ibExpenseService.saveKGPopupDocumentInfoDetail(dto));
    }
    
    /**
     * @description : 비용기표 확정
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.08    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "비용기표 확정", description = "비용기표 확정")
    @PostMapping(value = "/v1.0/saveConfirm")
    public ApiResult<String> saveConfirm(@RequestBody IbExpenseReqDto dto)  {
        return ApiResult.createResult(ibExpenseService.saveConfirm(dto));
    }    
    
    /**
     * @description : 비용기표 확정 취소
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.08    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "비용기표 확정 취소", description = "비용기표 확정 취소")
    @PostMapping(value = "/v1.0/saveConfirmCancel")
    public ApiResult<String> saveConfirmCancel(@RequestBody IbExpenseReqDto dto)  {
        return ApiResult.createResult(ibExpenseService.saveConfirmCancel(dto));
    }
    
    /**
     * @description : 비용기표 삭제
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.08    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "비용기표 삭제", description = "비용기표 삭제")
    @PostMapping(value = "/v1.0/deleteMasterList")
    public ApiResult<String> deleteMasterList(@RequestBody IbExpenseReqDto dto)  {
        return ApiResult.createResult(ibExpenseService.deleteMasterList(dto));
    }
    
    /**
     * @description : 파일 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.08.05    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "파일 목록 조회", description = "파일 목록 조회")
    @PostMapping(value = "/v1.0/getFileList")
    public ApiResult<List<IbExpenseFilePopupResDto>> getFileList(@Valid IbExpensePopupReqDto dto) throws RemoteException {
        return ApiResult.createResult(ibExpenseService.getFileList(dto));
    }
    
    /**
     * @description : 파일 업로드
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.08    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "파일 업로드", description = "파일 업로드")
    @PostMapping(value = "/v1.0/saveFileUpload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ApiResult<String> saveFileUpload(@Valid IbExpensePopupReqDto dto
                                        ,@RequestPart(value="file", required = true) List<MultipartFile> files
                                        ,@RequestPart(value="fileInfoList", required = true) List<FileUpload> fileInfoList)  {
        return ApiResult.createResult(ibExpenseService.saveFileUpload(dto, files, fileInfoList));
    }
    
    /**
     * @description : 파일 업로드
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.20    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "일괄 파일 업로드", description = "일괄 파일 업로드")
    @PostMapping(value = "/v1.0/saveFileUploadMulti", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ApiResult<String> saveFileUploadMulti(
                                        @RequestPart(value="file", required = true) List<MultipartFile> files
                                        ,@RequestPart(value="fileInfoList", required = true) List<FileUpload> fileInfoList
                                        ,@RequestPart(value="saveList", required = true) List<IbExpenseDoucmentHeaderPopupResDto> saveList)  {
        return ApiResult.createResult(ibExpenseService.saveFileUploadMulti(files, fileInfoList, saveList));
    }
    
    /**
     * @description : 파일 다운로드
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.08    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "파일 다운로드", description = "파일 업로드")
    @PostMapping(value = "/v1.0/downloadFile")
    public void downloadFile(@Valid IbExpenseReqDto dto, HttpServletResponse response, FileDownload fileDownload)  {
        ibExpenseService.downloadFile(response, fileDownload);
    }
    
    /**
     * @description : 결재자정보 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.08.05    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "결재자정보 목록 조회", description = "결재자정보 목록 조회")
    @PostMapping(value = "/v1.0/getPopupApprovalUserInfo")
    public ApiResult<List<IbExpenseApprovalUserPopupResDto>> getPopupApprovalUserInfo(@Valid IbExpensePopupReqDto dto) throws RemoteException {
        return ApiResult.createResult(ibExpenseService.getPopupApprovalUserInfo(dto));
    }
    
    /**
     * @description : 비용기표 헤더 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.08.05    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "비용기표 헤더 조회", description = "비용기표 헤더 조회")
    @PostMapping(value = "/v1.0/getPopupApprovalExpenseInfo")
    public ApiResult<List<IbExpenseDoucmentHeaderPopupResDto>> getPopupApprovalExpenseInfo(@Valid IbExpensePopupReqDto dto) throws RemoteException {
        return ApiResult.createResult(ibExpenseService.getPopupApprovalExpenseInfo(dto));
    }
    
    /**
     * @description : 비용기표 승인요청 저장 
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.08    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "비용기표 승인요청 저장", description = "비용기표 승인요청 저장")
    @PostMapping(value = "/v1.0/saveApprovalRequest")
    public ApiResult<String> saveApprovalRequest(@RequestBody IbExpensePopupReqDto dto)  {
        return ApiResult.createResult(ibExpenseService.saveApprovalRequest(dto));
    }
    
    /**
     * @throws RemoteException 
     * @description : 비용기표 Posting
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.08    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "비용기표 Posting", description = "비용기표 Posting")
    @PostMapping(value = "/v1.0/savePosting")
    public ApiResult<String> savePosting(@RequestBody IbExpensePopupReqDto dto) throws RemoteException  {
        return ApiResult.createResult(ibExpenseService.posting(dto));
    }
    
    /**
     * @throws RemoteException 
     * @description : 비용기표 Posting 취소
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.08    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "비용기표 Posting", description = "비용기표 Posting")
    @PostMapping(value = "/v1.0/savePostingCancel")
    public ApiResult<String> savePostingCancel(@RequestBody IbExpensePopupReqDto dto) throws RemoteException  {
        return ApiResult.createResult(ibExpenseService.postingCancel(dto));
    }
    
    /**
     * @description : 비용기표 체크 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.08.20    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "비용기표 체크 목록 조회", description = "비용기표 체크 목록 조회")
    @PostMapping(value = "/v1.0/getKeynoList")
    public ApiResult<List<IbExpenseResDto>> getKeynoList(@Valid IbExpensePopupReqDto dto) {
        return ApiResult.createResult(ibExpenseService.getKeynoList(dto));
    }
    
    /**
     * @throws RemoteException 
     * @description : test
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.08.20    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "비용기표 체크 목록 조회", description = "비용기표 체크 목록 조회")
    @GetMapping(value = "/v1.0/getApprPosting/{approvalReauestNo}/{status}")
    public ApiResult<List<IbExpenseResDto>> getApprPosting(@PathVariable String approvalReauestNo, @PathVariable String status) throws RemoteException {
        return ApiResult.createResult(ibExpenseService.getApprPosting(approvalReauestNo, status));
    }

}