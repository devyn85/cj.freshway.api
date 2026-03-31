package cjfw.wms.st.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StInquiryReqDto;
import cjfw.wms.st.dto.StInquiryResDetailDto;
import cjfw.wms.st.dto.StInquiryResDto;
import cjfw.wms.st.service.StInquiryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.11.02
 * @description : 재고 > 재고조사 > 재고조사등록 Controller Class
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.02 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Tag(name = "재고 > 재고조사 > 재고조사등록", description = "재고 > 재고조사 > 재고조사등록을 조회, 저장 한다.")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/inquiry")
public class StInquiryController {
	private final StInquiryService stInquiryService;

	/**
	 * @description : 재고 > 재고조사 > 재고조사등록 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.02 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "재고 > 재고조사 > 재고조사등록 조회", description = "재고 > 재고조사 > 재고조사등록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<StInquiryResDto>> getMasterList(@RequestBody StInquiryReqDto dto) {
		return ApiResult.createResult(stInquiryService.getMasterList(dto));
	}

	/**
	 * @description : 재고 > 재고조사 > 재고조사등록 상세 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.02 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "재고 > 재고조사 > 재고조사등록 상세 조회", description = "재고 > 재고조사 > 재고조사등록 상세 조회")
	@PostMapping(value = "/v1.0/getDetailList")
	public ApiResult<List<StInquiryResDetailDto>> getDetailList(@RequestBody StInquiryReqDto dto) {
		return ApiResult.createResult(stInquiryService.getDetailList(dto));
	}
	
	/**
	 * @description : 재고 > 재고조사 > 재고조사등록 상세 저장(5건 보다 큰 경우) Method
	 * @issues :
	 *
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.02 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "재고 > 재고조사 > 재고조사등록 상세 저장", description = "재고 > 재고조사 > 재고조사등록 상세 저장")
	@PostMapping(value = "/v1.0/saveDetailList1")
	public ApiResult<String> saveDetailList1(@RequestBody StInquiryReqDto dto) throws Exception {
	    return ApiResult.createResult(stInquiryService.saveDetailList1(dto));
	}
	
	/**
	 * @description : 재고 > 재고조사 > 재고조사등록 상세 저장(5건 보다 작은 경우) Method
	 * @issues :
	 *
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.02 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "재고 > 재고조사 > 재고조사등록 상세 저장", description = "재고 > 재고조사 > 재고조사등록 상세 저장")
	@PostMapping(value = "/v1.0/saveDetailList2")
	public ApiResult<String> saveDetailList2(@RequestBody StInquiryReqDto dto) throws Exception {
		return ApiResult.createResult(stInquiryService.saveDetailList2(dto));
	}	
	
	/**
	 * @description : 재고 > 재고조사 > 엑셀다운로드 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2026.01.16 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "재고 > 재고조사 > 재고조사등록 엑셀다운로드 조회", description = "재고 > 재고조사 > 엑셀다운로드 조회")
	@PostMapping(value = "/v1.0/getExcelList")
	public ApiResult<List<StInquiryResDetailDto>> getExcelList(@RequestBody StInquiryReqDto dto) {
		return ApiResult.createResult(stInquiryService.getExcelList(dto));
	}
	
	/**
	 * @description : 재고 > 재고조사 > 엑셀업로드 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2026.01.16 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "재고 > 재고조사 > 재고조사등록 엑셀업로드 조회", description = "재고 > 재고조사 > 엑셀업로드 조회")
	@PostMapping(value = "/v1.0/getExcelUpList")
	public ApiResult<List<StInquiryResDetailDto>> getExcelUpList(@RequestBody StInquiryReqDto dto) {
		return ApiResult.createResult(stInquiryService.getExcelUpList(dto));
	}
}
