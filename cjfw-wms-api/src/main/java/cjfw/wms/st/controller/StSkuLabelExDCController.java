package cjfw.wms.st.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StSkuLabelExDCCheckBarcodeResDto;
import cjfw.wms.st.dto.StSkuLabelExDCReqDto;
import cjfw.wms.st.dto.StSkuLabelExDCResDto;
import cjfw.wms.st.service.StSkuLabelExDCService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : baechan (c_bae@cj.net)
 * @date : 2025.09.03
 * @description : 상품이력번호등록(재고생성) Controller Class
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.03 baechan (c_bae@cj.net) 생성
 *         </pre>
 */
@Tag(name = "StSkuLabelExDCController API", description = "StSkuLabelExDCController")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/skuLabelExDC")
public class StSkuLabelExDCController {
	private final StSkuLabelExDCService stSkuLabelExDCService;

	/**
	 * @description : 상품이력번호등록(재고생성) 조회 List Method
	 * @issues :
	 * 
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.03 baechan (c_bae@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "상품이력번호등록(재고생성) 조회 List", description = "상품이력번호등록(재고생성) 조회 List")
	@GetMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<StSkuLabelExDCResDto>> getMasterList(@Valid StSkuLabelExDCReqDto reqDto) {
		return ApiResult.createResult(stSkuLabelExDCService.getMasterList(reqDto));
	}
	
	/**
	 * @description : 상품이력번호등록(재고생성) 저장 List Method
	 * @issues :
	 * 
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.03 baechan (c_bae@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "상품이력번호등록(재고생성) 저장", description = "상품이력번호등록(재고생성) 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<StSkuLabelExDCResDto> saveMasterList(@RequestBody StSkuLabelExDCReqDto reqDto) throws Exception {
		return ApiResult.createResult(stSkuLabelExDCService.saveMasterList(reqDto));
	}
	
	/**
	 * @description : 상품이력번호등록(재고생성) durationtype 조회 List Method ( 엑셀업로드를 위한 )
	 * @issues :
	 * 
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.03 baechan (c_bae@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "상품이력번호등록(재고생성) durationtype 조회", description = "상품이력번호등록(재고생성) durationtype 조회")
	@PostMapping(value = "/v1.0/getDurationTypeListByExcelUploadList")
	public ApiResult<List<StSkuLabelExDCResDto>> getDurationTypeListByExcelUploadList(@RequestBody StSkuLabelExDCReqDto reqDto) throws Exception {
		return ApiResult.createResult(stSkuLabelExDCService.getDurationTypeListByExcelUploadList(reqDto));
	}
	
	/**
	 * @description : 상품이력번호등록(재고생성) 바코드 중복 조회 List Method
	 * @issues :
	 * 
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.05 baechan (c_bae@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "상품이력번호등록(재고생성) 바코드 중복 조회", description = "상품이력번호등록(재고생성) 바코드 중복 조회")
	@PostMapping(value = "/v1.0/getCheckBarcodeDuplicate")
	public ApiResult<StSkuLabelExDCCheckBarcodeResDto> getCheckBarcodeDuplicate(@RequestBody StSkuLabelExDCReqDto reqDto) throws Exception {
		return ApiResult.createResult(stSkuLabelExDCService.getCheckBarcodeDuplicate(reqDto));
	}
	/**
	 * @description : 상품이력번호등록(재고생성) 엑셀 업로드정합성 검사 List Method
	 * @issues :
	 * 
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.22 생성
	 * </pre>
	 */
	@Operation(summary = "상품이력번호등록(재고생성) 엑셀 업로드정합성 검사", description = "상품이력번호등록(재고생성) 엑셀 업로드정합성 검사")
	@PostMapping(value = "/v1.0/getExcelUploadSkuLabelExDCList")
	public ApiResult<List<StSkuLabelExDCResDto>> getExcelUploadSkuLabelExDCList(@RequestBody StSkuLabelExDCReqDto reqDto) throws Exception {
		return ApiResult.createResult(stSkuLabelExDCService.getExcelUploadSkuLabelExDCList(reqDto));
	}
	
	
}
