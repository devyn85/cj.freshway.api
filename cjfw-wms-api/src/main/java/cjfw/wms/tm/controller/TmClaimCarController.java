package cjfw.wms.tm.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.tm.dto.TmClaimCarClaimListReqDto;
import cjfw.wms.tm.dto.TmClaimCarDtlListReqDto;
import cjfw.wms.tm.dto.TmClaimCarListReqDto;
import cjfw.wms.tm.service.TmClaimCarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.08.07 
 * @description : 배송클레임정보 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.07 ParkJinWoo 생성
 */
@Tag(name = "배송 > 배차현황 > 배송클레임정보", description = "배송클레임정보")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/tm/claimCar")
public class TmClaimCarController {

	private final TmClaimCarService tmClaimCarService;

	/**
	 * @description : 배송클레임정보 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.07 ParkJinWoo 생성
	 */
	@Operation(summary = "배송클레임정보", description = "배송클레임정보")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<Object> getMasterList(@Valid @RequestBody TmClaimCarListReqDto reqDto) {
		return ApiResult.createResult(tmClaimCarService.getMasterList(reqDto));
	}
	/**
	 * @description : 배송클레임정보 서브 조회 기능을 구현한 Method 
	 * @issues : 추후 추가예정
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.07 ParkJinWoo 생성
	 */
	@Operation(summary = "배송클레임서브정보", description = "배송클레임서브정보")
	@PostMapping(value = "/v1.0/getMaster1List")
	public ApiResult<Object> getMaster1List(@Valid @RequestBody TmClaimCarDtlListReqDto reqDto) {
		//추후 변경
		return ApiResult.createResult(tmClaimCarService.getMaster1List(reqDto));
	}
	
	/**
	 * @description : 배송클레임정보 클레임 리스트 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.07 ParkJinWoo 생성
	 */
	@Operation(summary = "배송클레임정보 클레임 세부 리스트", description = "배송클레임정보 클레임 세부 리스트")
	@PostMapping(value = "/v1.0/getClaimDtlList")
	public ApiResult<Object> getClaimDtlList(@Valid @RequestBody TmClaimCarClaimListReqDto reqDto) {
		//추후 변경
		return ApiResult.createResult(tmClaimCarService.getClaimDtlList(reqDto));
	}
	
	/**
	 * @description : 배송클레임정보 저장 기능을 구현한 Method 
	 * @issues : 추후 하단 그리드 저장 기능 추가
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.07 ParkJinWoo 생성
	 */
	@Operation(summary = "배송클레임정보 저장", description = "배송클레임정보 저장")
	@PostMapping(value = "/v1.0/saveConfirm")
	public ApiResult<Object> saveConfirm(@Valid @RequestBody TmClaimCarDtlListReqDto reqDto) {
		return ApiResult.createResult(tmClaimCarService.saveConfirm(reqDto));
	}
}