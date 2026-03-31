package cjfw.wms.om.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.om.dto.OmAutoOrderSetupTgsaReqDto;
import cjfw.wms.om.dto.OmAutoOrderSetupTgsaResDto;
import cjfw.wms.om.dto.OmAutoOrderSetupTgsaDetailResDto;
import cjfw.wms.om.dto.OmAutoOrderSetupTgsaHistoryResDto;
import cjfw.wms.om.dto.OmAutoOrderSetupTgsaInfoResDto;
import cjfw.wms.om.service.OmAutoOrderSetupTgsaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : LeeJeongCheol (progs@cj.net) 
 * @date : 2026.03.06
 * @description : 주문 > 주믄등록 > 당일광역보충발주관리 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2026.03.06 LeeJeongCheol (progs@cj.net) 생성 </pre>
 */
@Tag(name = "주문 > 주믄등록 > 당일광역보충발주관리", description = "당일광역보충발주관리")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/om/autoOrderSetupTgsa")
public class OmAutoOrderSetupTgsaController {

	private final OmAutoOrderSetupTgsaService omAutoOrderSetupTgsaService;

	/**
	 * @description : 당일광역보충발주 마스터 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.06 LeeJeongCheol (progs@cj.net) 생성 </pre>
	 */
	@Operation(summary = "당일광역보충발주 마스터 조회 (목록)", description = "당일광역보충발주 마스터 조회 (목록)")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<OmAutoOrderSetupTgsaResDto>> getMasterList(@RequestBody @Valid OmAutoOrderSetupTgsaReqDto dto) {
		return ApiResult.createResult(omAutoOrderSetupTgsaService.getMasterList(dto));
	}
	
	/**
	 * @description : 당일광역보충발주 상품내역 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.06 LeeJeongCheol (progs@cj.net) 생성 </pre>
	 */
	@Operation(summary = "당일광역보충발주 상품내역 조회 (목록)", description = "당일광역보충발주 상품내역 조회 (목록)")
	@GetMapping(value = "/v1.0/getDetailList")
	public ApiResult<List<OmAutoOrderSetupTgsaDetailResDto>> getDetailList(@Valid OmAutoOrderSetupTgsaReqDto dto) {
		return ApiResult.createResult(omAutoOrderSetupTgsaService.getDetailList(dto));
	}
	
	/**
	 * @description : 당일광역보충발주 이력내역 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.06 LeeJeongCheol (progs@cj.net) 생성 </pre>
	 */
	@Operation(summary = "당일광역보충발주 이력내역 조회 (목록)", description = "당일광역보충발주 이력내역 조회 (목록)")
	@GetMapping(value = "/v1.0/getHistoryList")
	public ApiResult<List<OmAutoOrderSetupTgsaHistoryResDto>> getHistoryList(@Valid OmAutoOrderSetupTgsaReqDto dto) {
		return ApiResult.createResult(omAutoOrderSetupTgsaService.getHistoryList(dto));
	}
	
	/**
	 * @description : 당일광역보충발주 상세내역 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.06 LeeJeongCheol (progs@cj.net) 생성 </pre>
	 */
	@Operation(summary = "당일광역보충발주 상세내역 조회 (단건)", description = "당일광역보충발주 상세내역 조회 (단건)")
	@GetMapping(value = "/v1.0/getMasterInfo")
	public ApiResult<OmAutoOrderSetupTgsaInfoResDto> getMasterInfo(@Valid OmAutoOrderSetupTgsaReqDto dto) {
		return ApiResult.createResult(omAutoOrderSetupTgsaService.getMasterInfo(dto));
	}
	
	/**
	 * @description : 당일광역보충발주 상품 유효성검사
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.06    LeeJeongCheol (progs@cj.net)   생성
	 */
	@Operation(summary = "당일광역보충발주 상품 유효성검사", description = "당일광역보충발주 상품 유효성검사")
	@PostMapping(value = "/v1.0/getDetailListCheck")
	public ApiResult<OmAutoOrderSetupTgsaDetailResDto> getDetailListCheck(@RequestBody @Valid List<OmAutoOrderSetupTgsaReqDto> dto) {
		return ApiResult.createResult(omAutoOrderSetupTgsaService.getDetailListCheck(dto));
	}
	
	/**
	 * @description : 당일광역보충발주 상품 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.06    LeeJeongCheol (progs@cj.net)   생성
	 */
	@Operation(summary = "당일광역보충발주 상품 저장", description = "당일광역보충발주 상품 저장")
	@PostMapping(value = "/v1.0/saveDetailList")
	public ApiResult<OmAutoOrderSetupTgsaDetailResDto> saveDetailList(@RequestBody @Valid List<OmAutoOrderSetupTgsaReqDto> dto) {
		return ApiResult.createResult(omAutoOrderSetupTgsaService.saveDetailList(dto));
	}
	
	/**
	 * @description : 당일광역보충발주 상세 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.06    LeeJeongCheol (progs@cj.net)   생성
	 */
	@Operation(summary = "당일광역보충발주 상세 저장", description = "당일광역보충발주 상세 저장")
	@PostMapping(value = "/v1.0/saveMasterInfo")
	public ApiResult<OmAutoOrderSetupTgsaInfoResDto> saveMasterInfo(@RequestBody @Valid OmAutoOrderSetupTgsaReqDto dto) {
		return ApiResult.createResult(omAutoOrderSetupTgsaService.saveMasterInfo(dto));
	}
	
	/**
	 * @description : 당일광역보충발주 예정량확인 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.06    LeeJeongCheol (progs@cj.net)   생성
	 */
	@Operation(summary = "당일광역보충발주 예정량확인 저장", description = "당일광역보충발주 예정량확인 저장")
	@PostMapping(value = "/v1.0/saveAutoOrderList")
	public ApiResult<List<OmAutoOrderSetupTgsaHistoryResDto>> saveAutoOrderList(@Valid OmAutoOrderSetupTgsaReqDto dto) {
		return ApiResult.createResult(omAutoOrderSetupTgsaService.saveAutoOrderList(dto));
	}
	
	/**
	 * @description : 당일광역보충발주 상세내역 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.06 LeeJeongCheol (progs@cj.net) 생성 </pre>
	 */
	@Operation(summary = "당일광역보충발주 상세설정 조회 (Detail SETUP)", description = "당일광역보충발주 상세설정 조회 (Detail SETUP)")
	@GetMapping(value = "/v1.0/getMasterInfoSetupList")
	public ApiResult<List<OmAutoOrderSetupTgsaInfoResDto>> getMasterInfoSetupList(@Valid OmAutoOrderSetupTgsaReqDto dto) {
		return ApiResult.createResult(omAutoOrderSetupTgsaService.getMasterInfoSetupList(dto));
	}
	
	/**
	 * @description : 당일광역보충발주 상세 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.06    LeeJeongCheol (wjdgudcjf@cj.net)   생성
	 */
	@Operation(summary = "발주 상세설정내역 저장", description = "발주 상세설정내역 저장")
	@PostMapping(value = "/v1.0/saveMasterInfoSetupList")
	public ApiResult<OmAutoOrderSetupTgsaInfoResDto> saveMasterInfoSetupList(@RequestBody @Valid List<OmAutoOrderSetupTgsaReqDto> dto) {
		return ApiResult.createResult(omAutoOrderSetupTgsaService.saveMasterInfoSetupList(dto));
	}

	/**
	 * @description : 당일광역보충발주 변경이력 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.06 LeeJeongCheol (progs@cj.net) 생성 </pre>
	 */
	@Operation(summary = "당일광역보충발주 변경이력 조회 (목록)", description = "당일광역보충발주 변경이력 조회 (목록)")
	@GetMapping(value = "/v1.0/getEditHistoryList")
	public ApiResult<List<OmAutoOrderSetupTgsaHistoryResDto>> getEditHistoryList(@Valid OmAutoOrderSetupTgsaReqDto dto) {
		return ApiResult.createResult(omAutoOrderSetupTgsaService.getEditHistoryList(dto));
	}
	
	/**
	 * @description : 당일광역보충발주 공급받는센터 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.09 LeeJeongCheol (progs@cj.net) 생성 </pre>
	 */
	@Operation(summary = "당일광역보충발주 공급받는센터 조회 (목록)", description = "당일광역보충발주 마감타입기준 공급받는센터 조회 (목록)")
	@GetMapping(value = "/v1.0/getToDcList")
	public ApiResult<List<OmAutoOrderSetupTgsaInfoResDto>> getToDcList(@Valid OmAutoOrderSetupTgsaReqDto dto) {
		log.info(">>>>>>>>>>>>>> dto;"+dto);
		return ApiResult.createResult(omAutoOrderSetupTgsaService.getToDcList(dto));
	}
}