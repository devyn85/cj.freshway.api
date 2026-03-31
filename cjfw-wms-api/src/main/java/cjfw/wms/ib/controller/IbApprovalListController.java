package cjfw.wms.ib.controller;

import java.rmi.RemoteException;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ib.dto.IbApprovalListReqDto;
import cjfw.wms.ib.dto.IbApprovalListResDto;
import cjfw.wms.ib.service.IbApprovalListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.08.25 
 * @description : 외부창고 결재내역 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.25   KimSunHo(sunhokim6229@cj.net)   생성
 */
@Tag(name = "정산 > 외부창고정산 > 외부창고 결재내역", description = "외부창고 결재내역 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ib/approvalList")
public class IbApprovalListController {

	private final IbApprovalListService ibApprovalListService;

	/**
	 * @description : 외부창고 결재내역 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.25    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	@Operation(summary = "외부창고 결재내역 목록 조회", description = "외부창고 결재내역 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<IbApprovalListResDto>> getMasterList(@Valid IbApprovalListReqDto dto) {
		return ApiResult.createResult(ibApprovalListService.getMasterList(dto));
	}
	
	/**
     * @description : 외부창고 결재내역 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.08.25    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "외부창고 결재내역 라인 목록 조회", description = "외부창고 결재내역 라인 목록 조회")
    @PostMapping(value = "/v1.0/getApprovalLine")
    public ApiResult<List<IbApprovalListResDto>> getApprovalLine(@Valid IbApprovalListReqDto dto) {
        return ApiResult.createResult(ibApprovalListService.getApprovalLine(dto));
    }
	
    /**
     * @description : 외부창고 결재내역 상신취소
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.25    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "외부창고 결재내역 상신취소", description = "외부창고 결재내역 상신취소")
    @PostMapping(value = "/v1.0/saveCancelMasterList")
    public ApiResult<String> saveCancelMasterList(@RequestBody IbApprovalListReqDto reqDto)  {
        return ApiResult.createResult(ibApprovalListService.saveCancelMasterList(reqDto));
    }
    
    /**
     * @description : 외부창고 결재내역 반려
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.25    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "외부창고 결재내역 반려", description = "외부창고 결재내역 반려")
    @PostMapping(value = "/v1.0/saveRejectMasterList")
    public ApiResult<String> saveRejectMasterList(@RequestBody IbApprovalListReqDto reqDto)  {
        return ApiResult.createResult(ibApprovalListService.saveRejectMasterList(reqDto));
    }
    
    /**
     * @throws RemoteException 
     * @description : 외부창고 결재내역 결재
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.25    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "외부창고 결재내역 결재", description = "외부창고 결재내역 결재")
    @PostMapping(value = "/v1.0/saveApprovelMasterList")
    public ApiResult<String> saveApprovelMasterList(@RequestBody IbApprovalListReqDto reqDto) throws RemoteException  {
        return ApiResult.createResult(ibApprovalListService.saveApprovelMasterList(reqDto));
    }
	
}