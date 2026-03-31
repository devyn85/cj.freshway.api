package cjfw.wms.st.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StKitPlanReqDto;
import cjfw.wms.st.dto.StKitPlanResT1Dto;
import cjfw.wms.st.dto.StKitPlanResT2Dto;
import cjfw.wms.st.dto.StKitPlanResT3Dto;
import cjfw.wms.st.service.StKitPlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 고혜미 (laksjd0606@cj.net) 생성
 * @date : 2025.10.21
 * @description : KIT상품 계획등록 Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.21 고혜미 (laksjd0606@cj.net) 생성 </pre>
 */
@Tag(name = "재고 > 재고작업 > KIT상품 계획등록")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/stKitPlan")
public class StKitPlanController {
	private final StKitPlanService stKitPlanervice;

	/**
	 * @description : KIT상품 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.21 고혜미 (laksjd0606@cj.net) 생성 </pre>
	 */
	@Operation(summary = "KIT상품 목록 조회", description = "KIT상품 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList01")
	public ApiResult<List<StKitPlanResT1Dto>> getMasterList01(@RequestBody StKitPlanReqDto dto) {
		return ApiResult.createResult(stKitPlanervice.getMasterList01(dto));
	}
	
	/**
	 * @description : KIT상품계획 저장 List Method
	 * @issues :
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.21 고혜미 (laksjd0606@cj.net) 생성 </pre>
	 * </pre>
	 */
	@Operation(summary = "KIT상품계획 저장 List", description = "KIT상품계획 저장 List")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<String> saveMasterList(@RequestBody StKitPlanReqDto reqDto) throws Exception{
		return ApiResult.createResult(stKitPlanervice.saveMasterList(reqDto));
	}	

	/**
	 * @description : KIT상품계획등록 > KIT구성조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.21 고혜미 (laksjd0606@cj.net) 생성 </pre>
	 */
	@Operation(summary = "KIT상품계획등록 > KIT구성조회", description = "KIT상품계획등록 > KIT구성조회")
	@PostMapping(value = "/v1.0/getMasterList02")
	public ApiResult<List<StKitPlanResT2Dto>> getMasterList02(@RequestBody StKitPlanReqDto dto) {
		return ApiResult.createResult(stKitPlanervice.getMasterList02(dto));
	}
	
	/**
	 * @description : KIT상품계획등록 > KIT구성조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.21 고혜미 (laksjd0606@cj.net) 생성 </pre>
	 */
	@Operation(summary = "KIT상품계획등록 > KIT구성조회", description = "KIT상품계획등록 > KIT구성조회")
	@PostMapping(value = "/v1.0/getMasterList03")
	public ApiResult<List<StKitPlanResT3Dto>> getMasterList03(@RequestBody StKitPlanReqDto dto) {
		return ApiResult.createResult(stKitPlanervice.getMasterList03(dto));
	}
	
}