package cjfw.wms.ms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.MsSkuChainExcelResDto;
import cjfw.wms.ms.dto.MsSkuChainReqDto;
import cjfw.wms.ms.dto.MsSkuChainResDto;
import cjfw.wms.ms.service.MsSkuChainService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.06.26 
 * @description : 기준정보 > 상품기준정보 > PLT변환값 마스터 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.26 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Tag(name = "기준정보 > 상품기준정보 > PLT변환값 마스터", description = "PLT변환값 마스터")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ms/skuChain")
public class MsSkuChainController {

	private final MsSkuChainService msSkuChainService;

	/**
	 * @description : PLT 변환값 마스터 정보 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.26 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "PLT 변환값 마스터 정보 조회", description = "PLT 변환값 마스터 정보 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<MsSkuChainResDto>> getMasterList(@RequestBody @Valid MsSkuChainReqDto dto) {
		return ApiResult.createResult(msSkuChainService.getMasterList(dto));
	}
	
	/**
	 * @description :  상품 정보 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.02.06 생성 </pre>
	 */
	@Operation(summary = "상품 정보 조회", description = "상품 정보 조회")
	@PostMapping(value = "/v1.0/getSkuInfo")
	public ApiResult<MsSkuChainResDto> getSkuInfo(@RequestBody @Valid MsSkuChainReqDto dto) {
		return ApiResult.createResult(msSkuChainService.getSkuInfo(dto));
	}
	/**
	 * @description : 상품코드 유효성검사
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.26    JeongHyeongCheol (wjdgudcjf@cj.net)   생성
	 */
	@Operation(summary = "상품코드 유효성검사", description = "상품코드 유효성검사")
	@PostMapping(value = "/v1.0/getValidateSaveList")
	public ApiResult<List<MsSkuChainExcelResDto>> getValidateSaveList(@RequestBody @Valid List<MsSkuChainReqDto> dto) {
		return ApiResult.createResult(msSkuChainService.getValidateSaveList(dto));
	}
	
	/**
	 * @description : 상품 정보 MERGE
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.26    JeongHyeongCheol (wjdgudcjf@cj.net)   생성
	 */
	@Operation(summary = "상품 정보 MERGE", description = "상품 정보 MERGE")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<String> saveMasterList(@RequestBody @Valid List<MsSkuChainReqDto> dto) {
		return ApiResult.createResult(msSkuChainService.saveMasterList(dto));
	}

}