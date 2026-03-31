package cjfw.wms.ms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sap.conn.jco.JCoException;

import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.MsKitReqDto;
import cjfw.wms.ms.dto.MsKitResDto;
import cjfw.wms.ms.service.MsKitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.06.11
 * @description : 기준정보 > 상품기준정보 > Kit상품정보 목록 조회 및 저장 Controller
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.02        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Tag(name = "기준정보 > 상품기준정보 > Kit상품정보", description = "Kit상품정보 목록 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ms/kit")
public class MsKitController {

	private final MsKitService msKitService;

	/**
	 * 
	 * @description : KIT 상품정보 목록 검색 조회
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.02        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "Kit상품정보 목록 조회", description = "Kit상품정보 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<MsKitResDto>> getMasterList(@RequestBody MsKitReqDto dto) {
		return ApiResult.createResult(msKitService.getMasterList(dto));
	}
	
	/**
	 * @throws JCoException 
	 * @description : KIT 상품정보 목록 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.17        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "Kit상품정보 목록 저장", description = "KIT상품정보 목록 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<Object> saveMasterList(@RequestBody List<MsKitReqDto> dto) throws JCoException {
		return ApiResult.createResult(msKitService.saveMasterList(dto));
	}
	
	/**
	 * 
	 * @description : KIT 상품정보 목록 검색 조회 (팝업)
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.02        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "Kit상품정보 목록 조회", description = "Kit상품정보 목록 조회")
	@GetMapping(value = "/v1.0/getKitSkuList")
	public ApiResult<List<MsKitResDto>> getKitSkuList(MsKitReqDto dto) {
		return ApiResult.createResult(msKitService.getKitSkuList(dto));
	}
	
	/**
	 * @description : 엑셀 업로드 유효성 검사(목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.02        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "엑셀 업로드 유효성 검사(목록)", description = "엑셀 업로드 유효성 검사(목록)")
	@PostMapping(value = "/v1.0/getValidateExcelList")
	public ApiResult<List<MsKitResDto>> getValidateExcelList(@RequestBody @Valid List<MsKitReqDto> dtoList) {
		return ApiResult.createResult(msKitService.getValidateExcelList(dtoList));
	}
}