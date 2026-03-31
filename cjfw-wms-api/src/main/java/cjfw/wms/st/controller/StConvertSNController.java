package cjfw.wms.st.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StConvertSNReqDto;
import cjfw.wms.st.dto.StConvertSNResDetailT1Dto;
import cjfw.wms.st.dto.StConvertSNResDetailT2Dto;
import cjfw.wms.st.dto.StConvertSNResDto;
import cjfw.wms.st.service.StConvertSNService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.09.11
 * @description : 재고 > 재고조정 > 상품이력번호변경  Controller Class
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.11 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Tag(name = "재고 > 재고조정 > 상품이력번호변경", description = "상품이력번호변경를 조회, 저장한다.")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/convertSN")
public class StConvertSNController {
	private final StConvertSNService stConvertSNService;

	/**
	 * @description : 재고 > 재고조정 > 상품이력번호변경 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.11 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "재고 > 재고조정 > 상품이력번호변경 마스터 조회", description = "재고 > 재고조정 > 상품이력번호변경 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<StConvertSNResDto>> getMasterList(@RequestBody StConvertSNReqDto dto) {
		return ApiResult.createResult(stConvertSNService.getMasterList(dto));
	}	
	
	/**
	 * @description : 재고 > 재고조정 > 상품이력번호변경 재고현황_탭 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.11 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "재고 > 재고조정 > 상품이력번호변경 재고현황_탭 조회", description = "재고 > 재고조정 > 상품이력번호변경 재고현황_탭 조회")
	@PostMapping(value = "/v1.0/getDetailT1List")
	public ApiResult<List<StConvertSNResDetailT1Dto>> getDetailT1List(@RequestBody StConvertSNReqDto dto) {
		return ApiResult.createResult(stConvertSNService.getDetailT1List(dto));
	}	
	
	/**
	 * @description : 재고 > 재고조정 > 상품이력번호변경 마스터 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.11 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "재고 > 재고조정 > 상품이력번호변경 입출이력_탭 조회", description = "재고 > 재고조정 > 상품이력번호변경 입출이력_탭 조회")
	@PostMapping(value = "/v1.0/getDetailT2List")
	public ApiResult<List<StConvertSNResDetailT2Dto>> getDetailT2List(@RequestBody StConvertSNReqDto dto) {
		return ApiResult.createResult(stConvertSNService.getDetailT2List(dto));
	}	
	
	/**
	 * @description : 재고 > 재고조정 > 상품이력번호변경 저장 Method
	 * @issues :
	 *
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.11 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "재고 > 재고조정 > 상품이력번호변경 저장", description = "재고 > 재고조정 > 상품이력번호변경 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<String> saveMasterList(@RequestBody StConvertSNReqDto dto) throws Exception {
	    return ApiResult.createResult(stConvertSNService.saveMasterList(dto));
	}	
	
}
