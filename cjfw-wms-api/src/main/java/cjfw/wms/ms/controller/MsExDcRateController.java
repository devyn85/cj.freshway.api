package cjfw.wms.ms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.MsExDcRateCheckRateAvgReqDto;
import cjfw.wms.ms.dto.MsExDcRateCheckRateAvgResDto;
import cjfw.wms.ms.dto.MsExDcRateExcelCheckReqDto;
import cjfw.wms.ms.dto.MsExDcRateExcelCheckResDto;
import cjfw.wms.ms.dto.MsExDcRateGetSkuDataSelectReqDto;
import cjfw.wms.ms.dto.MsExDcRateGetSkuDataSelectResDto;
import cjfw.wms.ms.dto.MsExDcRateGetSkuSpecReqDto;
import cjfw.wms.ms.dto.MsExDcRateGetSkuSpecResDto;
import cjfw.wms.ms.dto.MsExDcRateReqDto;
import cjfw.wms.ms.dto.MsExDcRateResDto;
import cjfw.wms.ms.dto.MsExDcRateSaveReqDto;
import cjfw.wms.ms.service.MsExDcRateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved. 
 *
 * @author : ParkJinWoo 
 * @date : 2025.05.29 
 * @description : 외부창고요율관리 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.29 ParkJinWoo 생성
 */
@Tag(name = "기준정보 > 센터기준정보 > 외부창고요율관리")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ms/exdcrate")
public class MsExDcRateController {
	private final MsExDcRateService msExDcRateService;
	
	@Operation(summary = "외부창고요율관리 목록조회", description = "외부창고요율관리 목록조회")
	@PostMapping(value = "/v1.0/getExDcRateList")
	public ApiResult<List<MsExDcRateResDto>> getExDcRateList(@Valid @RequestBody MsExDcRateReqDto msExDcRateReqDto) {
		return ApiResult.createResult(msExDcRateService.getExDcRateList(msExDcRateReqDto));
	}
	
	@Operation(summary = "외부창고요율 저장", description = "외부창고요율 저장")
	@PostMapping(value = "/v1.0/saveConfirm")
	public ApiResult<String> saveConfirm(@RequestBody @Valid List<MsExDcRateSaveReqDto> MsExDcRateSaveReqDto) {
		return ApiResult.createResult(msExDcRateService.saveConfirm(MsExDcRateSaveReqDto));
	}
	
	@Operation(summary = "요율 평균 체크", description = "요율 평균 체크")
	@PostMapping(value = "/v1.0/checkRateAvg")
	public ApiResult<List<MsExDcRateCheckRateAvgResDto>> checkRateAvg(@RequestBody @Valid List<MsExDcRateCheckRateAvgReqDto> MsExDcRateCheckRateAvgReqDto) {
		return ApiResult.createResult(msExDcRateService.checkRateAvg(MsExDcRateCheckRateAvgReqDto));
	}
		
	@Operation(summary = "외부창고요율 삭제", description = "외부창고요율 삭제")
	@PostMapping(value = "/v1.0/deleteConfirm")
	public ApiResult<String> deleteConfirm(@RequestBody @Valid List<MsExDcRateSaveReqDto> MsExDcRateSaveReqDto) {
		return ApiResult.createResult(msExDcRateService.deleteConfirm(MsExDcRateSaveReqDto));
	}
	@Operation(summary = "엑셀 업로드 유효성 검사", description = "엑셀 업로드 유효성 검사")
	@PostMapping(value = "/v1.0/getExcelCheck")
	public ApiResult<List<MsExDcRateExcelCheckResDto>> getExcelCheck( @RequestBody @Valid MsExDcRateExcelCheckReqDto MsExDcRateExcelCheckReqDto) {
		return ApiResult.createResult(msExDcRateService.getExcelCheck(MsExDcRateExcelCheckReqDto));
	}
	@Operation(summary = "SKU 리스트 조회", description = "SKU 리스트 조회")
	@GetMapping(value = "/v1.0/getSkuSpecForMsExDcRate")
	public ApiResult<List<MsExDcRateGetSkuSpecResDto>> getSkuSpecForMsExDcRate(@Valid MsExDcRateGetSkuSpecReqDto MsExDcRateGetSkuSpecReqDto) {
		return ApiResult.createResult(msExDcRateService.getSkuSpecForMsExDcRate(MsExDcRateGetSkuSpecReqDto));
	}
	
	@Operation(summary = "SKU 데이터 단건 조회", description = "SKU 데이터 단건 조회")
	@GetMapping(value = "/v1.0/getDataSelectSkuForMsExDcRate")
	public ApiResult<MsExDcRateGetSkuDataSelectResDto> getDataSelectSkuForMsExDcRate(@Valid MsExDcRateGetSkuDataSelectReqDto MsExDcRateGetSkuDataSelectReqDto) {
		return ApiResult.createResult(msExDcRateService.getDataSelectSkuForMsExDcRate(MsExDcRateGetSkuDataSelectReqDto));
	}
}
 