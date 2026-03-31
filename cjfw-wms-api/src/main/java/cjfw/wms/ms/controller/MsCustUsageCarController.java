package cjfw.wms.ms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.MsCustUsageCarReqDto;
import cjfw.wms.ms.dto.MsCustUsageCarResDto;
import cjfw.wms.ms.service.MsCustUsageCarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.08.29 
 * @description : 기준정보 > 거래처기준정보 > 거래처별전용차량정보  Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.29 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Tag(name = "기준정보 > 거래처기준정보 > 거래처별전용차량정보 ", description = "거래처별전용차량정보 ")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ms/custUsageCar")
public class MsCustUsageCarController {

	private final MsCustUsageCarService msCustUsageCarService;

	/**
	 * @description : 거래처별전용차량정보 조회 (목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.29 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "거래처별전용차량정보 조회 (목록)", description = "거래처별전용차량정보 조회 (목록)")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<MsCustUsageCarResDto>> getMasterList(@RequestBody @Valid MsCustUsageCarReqDto dto) {
		return ApiResult.createResult(msCustUsageCarService.getMasterList(dto));
	}
	
	/**
	 * @description : 거래처별전용차량정보 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.29    JeongHyeongCheol (wjdgudcjf@cj.net)   생성
	 */
	@Operation(summary = "거래처 정보 저장", description = "거래처 정보 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<String> saveMasterList(@RequestBody @Valid List<MsCustUsageCarReqDto> dto) {
		return ApiResult.createResult(msCustUsageCarService.saveMasterList(dto));
	}

	/**
	 * @description : 엑셀업로드 유효성검사
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.01.21    JeongHyeongCheol (wjdgudcjf@cj.net)   생성
	 */
	@Operation(summary = "엑셀업로드 유효성검사", description = "엑셀업로드 유효성검사")
	@PostMapping(value = "/v1.0/getValidateSaveList")
	public ApiResult<List<MsCustUsageCarResDto>> getValidateSaveList(@RequestBody @Valid List<MsCustUsageCarReqDto> dto) {
		return ApiResult.createResult(msCustUsageCarService.getValidateSaveList(dto));
	}
}