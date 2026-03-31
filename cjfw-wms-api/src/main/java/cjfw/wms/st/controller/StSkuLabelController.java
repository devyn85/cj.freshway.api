package cjfw.wms.st.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StSkuLabelReqDto;
import cjfw.wms.st.dto.StSkuLabelResDto;
import cjfw.wms.st.service.StSkuLabelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : baechan (c_bae@cj.net)
 * @date : 2025.08.25
 * @description : 상품이력번호등록 Controller Class
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.25 baechan (c_bae@cj.net) 생성
 *         </pre>
 */
@Tag(name = "StSkuLabelController API", description = "StSkuLabelController")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/skuLabel")
public class StSkuLabelController {
	private final StSkuLabelService stSkuLabelService;

	/**
	 * @description : 상품이력번호등록 조회 List Method
	 * @issues :
	 * 
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.25 baechan (c_bae@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "상품이력번호등록 조회 List", description = "상품이력번호등록 조회 List")
	@GetMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<StSkuLabelResDto>> getMasterList(@Valid StSkuLabelReqDto reqDto) {
		return ApiResult.createResult(stSkuLabelService.getMasterList(reqDto));
	}
	
	/**
	 * @description : 상품이력번호등록 저장 List Method
	 * @issues :
	 * 
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.27 baechan (c_bae@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "상품이력번호등록 저장", description = "상품이력번호등록 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<StSkuLabelResDto> saveMasterList(@RequestBody StSkuLabelReqDto reqDto) throws Exception {
		return ApiResult.createResult(stSkuLabelService.saveMasterList(reqDto));
	}
	
	/**
	 * @description : 상품이력번호등록 durationtype 조회 List Method ( 엑셀업로드를 위한 )
	 * @issues :
	 * 
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.02 baechan (c_bae@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "상품이력번호등록 durationtype 조회", description = "상품이력번호등록 durationtype 조회")
	@PostMapping(value = "/v1.0/getDurationTypeListByExcelUploadList")
	public ApiResult<List<StSkuLabelResDto>> getDurationTypeListByExcelUploadList(@RequestBody StSkuLabelReqDto reqDto) throws Exception {
		return ApiResult.createResult(stSkuLabelService.getDurationTypeListByExcelUploadList(reqDto));
	}
	
	
}
