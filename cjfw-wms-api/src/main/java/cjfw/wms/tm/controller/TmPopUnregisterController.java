package cjfw.wms.tm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.tm.dto.TmPopUnregisterDeliveryResDto;
import cjfw.wms.tm.dto.TmPopUnregisterReqDto;
import cjfw.wms.tm.dto.TmPopUnregisterResDto;
import cjfw.wms.tm.dto.TmPopUnregisterRolltainerResDto;
import cjfw.wms.tm.service.TmPopUnregisterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.05.23 
 * @description : 거래처별 POP 미등록 현황 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.22    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Tag(name = "배송 > 배차현황 > 거래처별 POP 미등록 현황", description = "거래처별 POP 미등록 현황 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/tm/popunregister")
public class TmPopUnregisterController {

	private final TmPopUnregisterService tmPopUnregisterService;

	/**
	 * @description : 거래처별 POP 미등록 현황 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.02    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	@Operation(summary = "거래처별 POP 미등록 현황 조회", description = "거래처별 POP 미등록 현황 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<TmPopUnregisterResDto>> getMasterList(@Valid TmPopUnregisterReqDto reqDto) {
		return ApiResult.createResult(tmPopUnregisterService.getMasterList(reqDto));
	}
	
	/**
	 * @description : 거래처별 배송이력 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.02    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	@Operation(summary = "거래처별 배송이력 조회", description = "거래처별 배송이력 조회")
	@PostMapping(value = "/v1.0/getCustDeliveryList")
	public ApiResult<List<TmPopUnregisterDeliveryResDto>> getCustDeliveryList(@Valid TmPopUnregisterReqDto reqDto) {
		return ApiResult.createResult(tmPopUnregisterService.getCustDeliveryList(reqDto));
	}
	
	/**
	 * @description : 차량별 롤테이너별 배송이력 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.02    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	@Operation(summary = "차량별 롤테이너별 배송이력 조회", description = "차량별 롤테이너별 배송이력 조회")
	@PostMapping(value = "/v1.0/getCarRolltainerList")
	public ApiResult<List<TmPopUnregisterRolltainerResDto>> getCarRolltainerList(@Valid TmPopUnregisterReqDto reqDto) {
		return ApiResult.createResult(tmPopUnregisterService.getCarRolltainerList(reqDto));
	}
	
	/**
	 * @description : 추천 POP 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.02    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	@Operation(summary = "추천 POP 조회", description = "추천 POP 조회")
	@PostMapping(value = "/v1.0/getRecommendPOPList")
	public ApiResult<List<TmPopUnregisterDeliveryResDto>> getRecommendPOPList(@Valid TmPopUnregisterReqDto reqDto) {
		return ApiResult.createResult(tmPopUnregisterService.getRecommendPOPList(reqDto));
	}
	
	/**
	 * @description : 거래처별 POP 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.10    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	@Operation(summary = "거래처별 POP 저장", description = "거래처별 POP 저장")
	@PostMapping(value = "/v1.0/saveCustPOP")
	public ApiResult<String> saveCustPOP(@RequestBody @Valid TmPopUnregisterReqDto reqDto) {
		return ApiResult.createResult(tmPopUnregisterService.saveCustPOP(reqDto));
	}
}