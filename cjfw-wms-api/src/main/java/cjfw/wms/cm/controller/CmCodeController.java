package cjfw.wms.cm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.cm.dto.CmCodeReqDto;
import cjfw.wms.cm.dto.CmMainCodeResDto;
import cjfw.wms.cm.service.CmCodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.04.30 
 * @description : 기준정보 > 기타기준정보 > 코드마스터 목록 조회 및 저장 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.04.30 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Tag(name = "기준정보 > 기타기준정보 > 코드마스터", description = "코드 목록 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/cm/code")
public class CmCodeController {

	private final CmCodeService cmCodeService;

	/**
	 * @description : 코드 목록 검색 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.04.30 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "코드 목록 검색 조회", description = "코드 목록 검색 조회")
	@GetMapping(value = "/v1.0/getCodeHeaderList")
	public ApiResult<List<CmMainCodeResDto>> getCodeHeaderList(@Valid CmCodeReqDto cmCodeReqDto) {
		return ApiResult.createResult(cmCodeService.getCodeHeaderList(cmCodeReqDto));
	}
	
	/**
	 * @description : 코드 상세 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.04.30 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "코드 상세 목록 조회", description = "코드 상세 목록 조회")
	@GetMapping(value = "/v1.0/getCodeDetailList")
	public ApiResult<List<CmMainCodeResDto>> getCodeDetailList(@Valid CmCodeReqDto cmCodeReqDto) {
		return ApiResult.createResult(cmCodeService.getCodeDetailList(cmCodeReqDto));
	}
	
	/**
	 * @description : 그룹 코드 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.19 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "그룹 코드 저장", description = "그룹 코드 저장")
	@PostMapping(value = "/v1.0/saveCmGrpCode")
	public ApiResult<String> saveCmGrpCode(@RequestBody @Valid CmCodeReqDto cmCodeReqDto) {
		return ApiResult.createResult(cmCodeService.saveCmGrpCode(cmCodeReqDto));
	}
	
	/**
	 * @description : 상세 코드 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.02 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "상세 코드 저장", description = "상세 코드 저장")
	@PostMapping(value = "/v1.0/saveCmDtlCode")
	public ApiResult<String> saveCmDtlCode(@RequestBody @Valid CmCodeReqDto cmCodeReqDto) {
		return ApiResult.createResult(cmCodeService.saveCmDtlCode(cmCodeReqDto));
	}

}