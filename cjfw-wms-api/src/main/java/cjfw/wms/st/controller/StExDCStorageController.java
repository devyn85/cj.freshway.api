package cjfw.wms.st.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StExDCEctFeePopupResDto;
import cjfw.wms.st.dto.StExDCEtcFeePopupReqDto;
import cjfw.wms.st.dto.StExDCStoragePopupReqDto;
import cjfw.wms.st.dto.StExDCStorageReqDto;
import cjfw.wms.st.dto.StExDCStorageResDto;
import cjfw.wms.st.service.StExDCStorageService;
import cjfw.wms.sys.dto.SysAuthorityUserResDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.07.21 
 * @description : 외부창고정산 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.04    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Tag(name = "정산 > 외부창고정산 > 외부창고정산", description = "외부창고정산 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping({"api/st/exdcstorage", "ltx/st/exdcstorage"})
public class StExDCStorageController {

	private final StExDCStorageService stExDCStorageService;

	/**
	 * @description : 외부창고정산 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.21    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	@Operation(summary = "외부창고정산 목록 조회", description = "외부창고정산 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<StExDCStorageResDto>> getMasterList(@Valid StExDCStorageReqDto dto) {
		return ApiResult.createResult(stExDCStorageService.getMasterList(dto));
	}
	
	/**
     * @description : 외부창고정산 엑셀 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.07.21    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "외부창고정산 엑셀 조회", description = "외부창고정산 엑셀 조회")
    @PostMapping(value = "/v1.0/getMasterExcelList")
    public ApiResult<List<StExDCStorageResDto>> getMasterExcelList(@Valid StExDCStorageReqDto dto) {
        return ApiResult.createResult(stExDCStorageService.getMasterExcelList(dto));
    }
	
    /**
     * @throws Exception
     * @description : 외부창고정산 저장
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.07.21    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "외부창고정산 저장", description = "외부창고정산 저장")
    @PostMapping(value = "/v1.0/saveMasterList")
    public ApiResult<String> saveMasterList(@RequestBody StExDCStorageReqDto dto) throws Exception {
        return ApiResult.createResult(stExDCStorageService.saveMasterList(dto));
    }
    
    /**
     * @throws Exception
     * @description : 외부창고정산 저장
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.07.21    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "외부창고정산 저장", description = "외부창고정산 저장")
    @PostMapping(value = "/v1.0/saveExcelList")
    public ApiResult<List<StExDCStorageResDto>> saveExcelList(@RequestBody StExDCStorageReqDto dto) throws Exception {
        return ApiResult.createResult(stExDCStorageService.saveExcelList(dto));
    }
    
    /**
     * @throws Exception
     * @description : 외부창고정산 기타비용등록
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.07.21    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "외부창고정산 기타비용등록", description = "외부창고정산 기타비용등록")
    @PostMapping(value = "/v1.0/saveEtcFee")
    public ApiResult<String> saveEtcFee(@RequestBody StExDCEtcFeePopupReqDto dto) throws Exception {
        return ApiResult.createResult(stExDCStorageService.saveEtcFee(dto));
    }
    
    /**
     * @throws Exception
     * @description : 외부창고정산 보관료 비용 마감
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.07.21    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "외부창고정산 보관료 비용 마감", description = "외부창고정산 보관료 비용 마감")
    @PostMapping(value = "/v1.0/saveExdcStorage")
    public ApiResult<String> saveExdcStorage(@RequestBody StExDCStoragePopupReqDto dto) throws Exception {
        return ApiResult.createResult(stExDCStorageService.saveExdcStorage(dto));
    }
    
    /**
     * @throws Exception
     * @description : 외부창고정산 수량 강제 조정
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.07.21    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "외부창고정산 수량 강제 조정", description = "외부창고정산 수량 강제 조정")
    @PostMapping(value = "/v1.0/saveQtyEdit")
    public ApiResult<String> saveQtyEdit(@RequestBody StExDCStorageReqDto dto) throws Exception {
        return ApiResult.createResult(stExDCStorageService.saveQtyEdit(dto));
    }
    
    /**
     * @description : 외부창고정산 기타비용등록 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.07.21    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "외부창고정산 기타비용등록 목록 조회", description = "외부창고정산 기타비용등록 목록 조회")
    @PostMapping(value = "/v1.0/getJournalcodeList")
    public ApiResult<List<StExDCEctFeePopupResDto>> getJournalcodeList(@Valid StExDCEtcFeePopupReqDto dto) {
        return ApiResult.createResult(stExDCStorageService.getJournalcodeList(dto));
    }

    /**
     * @description : 외부창고정산 엑셀업로드 데이터 검증
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.22    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "외부창고정산 엑셀업로드 데이터 검증", description = "외부창고정산 엑셀업로드 데이터 검증")
    @PostMapping(value = "/v1.0/validateExcel")
    public ApiResult<List<StExDCStorageResDto>> validateExcel(@RequestBody @Valid StExDCStorageReqDto reqDto) {
        return ApiResult.createResult(stExDCStorageService.validateExcel(reqDto));
    }
    
    /**
     * @description : 외부창고정산 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.07.21    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "권한 목록 조회", description = "권한 목록 조회")
    @PostMapping(value = "/v1.0/getAuthority")
    public ApiResult<List<SysAuthorityUserResDto>> getAuthority(@Valid StExDCStorageReqDto dto) {
        return ApiResult.createResult(stExDCStorageService.getAuthority(dto));
    }
}