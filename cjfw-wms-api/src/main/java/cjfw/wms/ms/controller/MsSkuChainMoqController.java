package cjfw.wms.ms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.MsSkuChainMoqReqDto;
import cjfw.wms.ms.dto.MsSkuChainMoqResDto;
import cjfw.wms.ms.service.MsSkuChainMoqService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.06.24 
 * @description : 기준정보 > 상품기준정보 > MOQ/LT마스터 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.24 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Tag(name = "기준정보 > 상품기준정보 > MOQ/LT마스터", description = "MOQ/LT마스터")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ms/skuChainMoq")
public class MsSkuChainMoqController {

	private final MsSkuChainMoqService msSkuChainMoqService;

	/**
	 * @description : 체인 상품 정보 조회 (목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.24 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "체인 상품 정보 조회 (목록)", description = "체인 상품 정보 조회 (목록)")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<MsSkuChainMoqResDto>> getMasterList(@RequestBody @Valid MsSkuChainMoqReqDto dto) {
		return ApiResult.createResult(msSkuChainMoqService.getMasterList(dto));
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
	public ApiResult<List<MsSkuChainMoqResDto>> getValidateSaveList(@RequestBody @Valid List<MsSkuChainMoqReqDto> dto) {
		return ApiResult.createResult(msSkuChainMoqService.getValidateSaveList(dto));
	}
	
	/**
	 * @description : 상품 정보 MERGE
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.24    JeongHyeongCheol (wjdgudcjf@cj.net)   생성
	 */
	@Operation(summary = "상품 정보 MERGE", description = "상품 정보 MERGE")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<String> saveMasterList(@RequestBody @Valid List<MsSkuChainMoqReqDto> dto) {
		return ApiResult.createResult(msSkuChainMoqService.saveMasterList(dto));
	}

}