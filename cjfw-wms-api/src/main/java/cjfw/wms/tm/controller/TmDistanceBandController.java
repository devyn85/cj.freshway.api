package cjfw.wms.tm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.tm.dto.TmDistanceBandListReqDto;
import cjfw.wms.tm.dto.TmEntityRuleMasterListReqDto;
import cjfw.wms.tm.dto.TmHjdDataListReqDto;
import cjfw.wms.tm.service.TmDistanceBandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.09.11 
 * @description : 센터별구간설정 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.11 ParkJinWoo 생성
 */
@Tag(name = "정산 > 운송비정산 >센터별구간설정", description = "센터별구간설정")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/tm/distanceBand")
public class TmDistanceBandController {

	private final TmDistanceBandService tmDistanceBandService;


	/**
	 * @description : 센터별구간설정 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.11 ParkJinWoo 생성
	 */
	@Operation(summary = "센터별구간설정 조회", description = "센터별구간설정 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<Object> getMasterList(@Valid @RequestBody TmDistanceBandListReqDto reqDto) {
		return ApiResult.createResult(tmDistanceBandService.getMasterList(reqDto));
	}
	

	/**
	 * @description : 센터별구간설정 수정 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.11 ParkJinWoo 생성
	 */
	@Operation(summary = " 센터별구간설정 수정", description = " 센터별구간설정 수정")
	@PostMapping(value = "/v1.0/saveConfirm")
	public ApiResult<Object> saveConfirm(@Valid @RequestBody TmDistanceBandListReqDto reqDto) {
		return ApiResult.createResult(tmDistanceBandService.saveConfirm(reqDto));
	}
	
	@Operation(summary = " 센터별구간설정 수정", description = " 센터별구간설정 수정")
	@GetMapping(value = "/v1.0/getHjdDataList")
	public ApiResult<Object> getHjdDataList(@Valid TmHjdDataListReqDto reqDto) {
		return ApiResult.createResult(tmDistanceBandService.getHjdDataList(reqDto));
	}
	
	/**
	 * @description : 센터별구간설정 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.11 ParkJinWoo 생성
	 */
	@Operation(summary = "센터별구간설정 조회", description = "센터별구간설정 조회")
	@PostMapping(value = "/v1.0/getHjdDataSetList")
	public ApiResult<Object> getHjdDataSetList(@Valid @RequestBody TmHjdDataListReqDto reqDto) {
		return ApiResult.createResult(tmDistanceBandService.getHjdDataSetList(reqDto));
	}
	/**
	 * @description : 센터별구간설정 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.11 ParkJinWoo 생성
	 */
	@Operation(summary = "센터별구간설정 조회", description = "센터별구간설정 조회")
	@PostMapping(value = "/v1.0/getExcelUpload")
	public ApiResult<Object> ExcelUpload(@Valid @RequestBody TmEntityRuleMasterListReqDto reqDto) {
		return ApiResult.createResult(tmDistanceBandService.ExcelUpload(reqDto));
	}
	
	/**
	 * @description : 센터별구간설정 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.11 ParkJinWoo 생성
	 */
	@Operation(summary = "센터별구간설정 조회", description = "센터별구간설정 조회")
	@PostMapping(value = "/v1.0/saveConfirmTmentity")
	public ApiResult<Object> saveConfirm(@Valid @RequestBody TmEntityRuleMasterListReqDto reqDto) {
		return ApiResult.createResult(tmDistanceBandService.saveConfirmTmentity(reqDto));
	}
	
}