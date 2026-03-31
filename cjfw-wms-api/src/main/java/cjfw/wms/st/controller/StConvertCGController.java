package cjfw.wms.st.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StConvertCGReqDto;
import cjfw.wms.st.dto.StConvertCGResDto;
import cjfw.wms.st.service.StConvertCGService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 고혜미 (laksjd0606@cj.net) 생성
 * @date : 2025.09.18
 * @description : 재고속성변경 Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.18 고혜미 (laksjd0606@cj.net) 생성 </pre>
 */
@Tag(name = "재고 > 재고속성 > 재고속성변경")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/stConvertCG")
public class StConvertCGController {
	private final StConvertCGService stConvertCGService;

	/**
	 * @description : 재고조회 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.18 고혜미 (laksjd0606@cj.net) 생성 </pre>
	 */
	@Operation(summary = "재고속성변경 목록 조회", description = "재고속성변경 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<StConvertCGResDto>> getMasterList(@RequestBody StConvertCGReqDto dto) {
		return ApiResult.createResult(stConvertCGService.getMasterList(dto));
	}
	
	/**
	 * @description : 재고조회 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.18 고혜미 (laksjd0606@cj.net) 생성 </pre>
	 */
	@Operation(summary = "재고속성변경 목록 조회", description = "재고속성변경 목록 조회")
	@PostMapping(value = "/v1.0/getDetailList1")
	public ApiResult<List<StConvertCGResDto>> getDetailList1(@RequestBody StConvertCGReqDto dto) {
		return ApiResult.createResult(stConvertCGService.getDetailList1(dto));
	}	
	
	/**
	 * @description : 재고조회 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.18 고혜미 (laksjd0606@cj.net) 생성 </pre>
	 */
	@Operation(summary = "재고속성변경 목록 조회", description = "재고속성변경 목록 조회")
	@PostMapping(value = "/v1.0/getDetailList2")
	public ApiResult<List<StConvertCGResDto>> getDetailList2(@RequestBody StConvertCGReqDto dto) {
		return ApiResult.createResult(stConvertCGService.getDetailList2(dto));
	}	
	
	/**
	 * @description : 재고속성변경 저장 List Method
	 * @issues :
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.19 고혜미 (laksjd0606@cj.net) 생성 </pre>
	 * </pre>
	 */
	@Operation(summary = "재고속성변경 저장 List", description = "재고속성변경 저장 List")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<String> saveMasterList(@RequestBody StConvertCGReqDto reqDto) throws Exception{
		return ApiResult.createResult(stConvertCGService.saveMasterList(reqDto));
	}		
}