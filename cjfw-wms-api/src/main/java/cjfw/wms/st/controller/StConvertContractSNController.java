package cjfw.wms.st.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StConvertContractSNReqDto;
import cjfw.wms.st.service.StConvertContractSNService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.11.14 
 * @description : 상품이력계약정보변경 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.14 ParkJinWoo 생성
 */
@Tag(name = "재고 > 재고작업 > 상품이력계약정보변경", description = "상품이력계약정보변경")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/convertContractSN")
public class StConvertContractSNController {

	private final StConvertContractSNService stConvertContractSNService;

	/**
	 * @description : 상품이력계약정보변경 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.14 ParkJinWoo 생성
	 */
	@Operation(summary = "상품이력계약정보변경조회", description = "상품이력계약정보변경조회")
	@PostMapping(value = "/v1.0/getDataHeaderList")
	public ApiResult<Object> getMasterList(@Valid @RequestBody StConvertContractSNReqDto reqDto) {
		return ApiResult.createResult(stConvertContractSNService.getMasterList(reqDto));
	}
	
	
	/**
	 * @description : 상품이력계약정보변경 저장 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.14 ParkJinWoo 생성
	 */
	@Operation(summary = "상품이력계약정보변경저장", description = "상품이력계약정보변경저장")
	@PostMapping(value = "/v1.0/saveConfirm")
	public ApiResult<Object> saveDataList(@Valid @RequestBody StConvertContractSNReqDto reqDto) {
		return ApiResult.createResult(stConvertContractSNService.saveDataList(reqDto));
	}

}