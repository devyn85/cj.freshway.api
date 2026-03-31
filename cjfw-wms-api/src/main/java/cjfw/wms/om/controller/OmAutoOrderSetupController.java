package cjfw.wms.om.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.om.dto.OmAutoOrderSetupDetailResDto;
import cjfw.wms.om.dto.OmAutoOrderSetupHistoryResDto;
import cjfw.wms.om.dto.OmAutoOrderSetupInfoResDto;
import cjfw.wms.om.dto.OmAutoOrderSetupReqDto;
import cjfw.wms.om.dto.OmAutoOrderSetupResDto;
import cjfw.wms.om.service.OmAutoOrderSetupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.07.24
 * @description : 주문 > 주믄등록 > 저장품자동발주관리 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.24 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Tag(name = "주문 > 주믄등록 > 저장품자동발주관리", description = "저장품자동발주관리")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/om/autoOrderSetup")
public class OmAutoOrderSetupController {

	private final OmAutoOrderSetupService omAutoOrderSetupService;

	/**
	 * @description : 자동발주관리 마스터 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.24 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "자동발주관리 마스터 조회 (목록)", description = "자동발주관리 마스터 조회 (목록)")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<OmAutoOrderSetupResDto>> getMasterList(@RequestBody @Valid OmAutoOrderSetupReqDto dto) {
		return ApiResult.createResult(omAutoOrderSetupService.getMasterList(dto));
	}
	
	/**
	 * @description : 자동발주관리 상품내역 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.24 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "자동발주관리 상품내역 조회 (목록)", description = "자동발주관리 상품내역 조회 (목록)")
	@GetMapping(value = "/v1.0/getDetailList")
	public ApiResult<List<OmAutoOrderSetupDetailResDto>> getDetailList(@Valid OmAutoOrderSetupReqDto dto) {
		return ApiResult.createResult(omAutoOrderSetupService.getDetailList(dto));
	}
	
	/**
	 * @description : 자동발주관리 이력내역 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.24 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "자동발주관리 이력내역 조회 (목록)", description = "자동발주관리 이력내역 조회 (목록)")
	@GetMapping(value = "/v1.0/getHistoryList")
	public ApiResult<List<OmAutoOrderSetupHistoryResDto>> getHistoryList(@Valid OmAutoOrderSetupReqDto dto) {
		return ApiResult.createResult(omAutoOrderSetupService.getHistoryList(dto));
	}
	
	/**
	 * @description : 자동발주관리 상세내역 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.24 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "자동발주관리 상세내역 조회 (단건)", description = "자동발주관리 상세내역 조회 (단건)")
	@GetMapping(value = "/v1.0/getMasterInfo")
	public ApiResult<OmAutoOrderSetupInfoResDto> getMasterInfo(@Valid OmAutoOrderSetupReqDto dto) {
		return ApiResult.createResult(omAutoOrderSetupService.getMasterInfo(dto));
	}
	
	/**
	 * @description : 자동발주관리 상품 유효성검사
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.24    JeongHyeongCheol (wjdgudcjf@cj.net)   생성
	 */
	@Operation(summary = "자동발주관리 상품 유효성검사", description = "자동발주관리 상품 유효성검사")
	@PostMapping(value = "/v1.0/getDetailListCheck")
	public ApiResult<OmAutoOrderSetupDetailResDto> getDetailListCheck(@RequestBody @Valid List<OmAutoOrderSetupReqDto> dto) {
		return ApiResult.createResult(omAutoOrderSetupService.getDetailListCheck(dto));
	}
	
	/**
	 * @description : 자동발주관리 상품 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.24    JeongHyeongCheol (wjdgudcjf@cj.net)   생성
	 */
	@Operation(summary = "자동발주관리 상품 저장", description = "자동발주관리 상품 저장")
	@PostMapping(value = "/v1.0/saveDetailList")
	public ApiResult<OmAutoOrderSetupDetailResDto> saveDetailList(@RequestBody @Valid List<OmAutoOrderSetupReqDto> dto) {
		return ApiResult.createResult(omAutoOrderSetupService.saveDetailList(dto));
	}
	
	/**
	 * @description : 자동발주관리 상세 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.24    JeongHyeongCheol (wjdgudcjf@cj.net)   생성
	 */
	@Operation(summary = "자동발주관리 상세 저장", description = "자동발주관리 상세 저장")
	@PostMapping(value = "/v1.0/saveMasterInfo")
	public ApiResult<OmAutoOrderSetupInfoResDto> saveMasterInfo(@RequestBody @Valid OmAutoOrderSetupReqDto dto) {
		return ApiResult.createResult(omAutoOrderSetupService.saveMasterInfo(dto));
	}
	
	/**
	 * @description : 자동발주관리 예정량확인 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.24    JeongHyeongCheol (wjdgudcjf@cj.net)   생성
	 */
	@Operation(summary = "자동발주관리 예정량확인 저장", description = "자동발주관리 예정량확인 저장")
	@PostMapping(value = "/v1.0/saveAutoOrderList")
	public ApiResult<List<OmAutoOrderSetupHistoryResDto>> saveAutoOrderList(@Valid OmAutoOrderSetupReqDto dto) {
		return ApiResult.createResult(omAutoOrderSetupService.saveAutoOrderList(dto));
	}
	
	/**
	 * @description : 자동발주관리 상세내역 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.24 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "자동발주관리 상세설정 조회 (Detail SETUP)", description = "자동발주관리 상세설정 조회 (Detail SETUP)")
	@GetMapping(value = "/v1.0/getMasterInfoSetupList")
	public ApiResult<List<OmAutoOrderSetupInfoResDto>> getMasterInfoSetupList(@Valid OmAutoOrderSetupReqDto dto) {
		return ApiResult.createResult(omAutoOrderSetupService.getMasterInfoSetupList(dto));
	}
	
	/**
	 * @description : 자동발주관리 상세 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.24    JeongHyeongCheol (wjdgudcjf@cj.net)   생성
	 */
	@Operation(summary = "발주 상세설정내역 저장", description = "발주 상세설정내역 저장")
	@PostMapping(value = "/v1.0/saveMasterInfoSetupList")
	public ApiResult<OmAutoOrderSetupInfoResDto> saveMasterInfoSetupList(@RequestBody @Valid List<OmAutoOrderSetupReqDto> dto) {
		return ApiResult.createResult(omAutoOrderSetupService.saveMasterInfoSetupList(dto));
	}

	/**
	 * @description : 자동발주관리 변경이력 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.24 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "자동발주관리 변경이력 조회 (목록)", description = "자동발주관리 변경이력 조회 (목록)")
	@GetMapping(value = "/v1.0/getEditHistoryList")
	public ApiResult<List<OmAutoOrderSetupHistoryResDto>> getEditHistoryList(@Valid OmAutoOrderSetupReqDto dto) {
		return ApiResult.createResult(omAutoOrderSetupService.getEditHistoryList(dto));
	}
	
	/**
	 * @description : 자동발주관리 마감유형 상세설정 삭제
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.17 LeeJeongCheol (progs@cj.net) 생성 </pre>
	 */
	@Operation(summary = "자동발주관리 마감유형 상세설정 삭제", description = "자동발주관리 마감유형 상세설정 삭제")
	@PostMapping(value = "/v1.0/saveDeleteCloseType")
	public ApiResult<OmAutoOrderSetupInfoResDto> saveDeleteCloseType(@RequestBody @Valid List<OmAutoOrderSetupReqDto> dto) {
		return ApiResult.createResult(omAutoOrderSetupService.saveDeleteCloseType(dto));
	}
}