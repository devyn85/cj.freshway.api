package cjfw.wms.tm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.tm.dto.TmAllocationCheckDetailReqDto;
import cjfw.wms.tm.dto.TmAllocationCheckMasterReqDto;
import cjfw.wms.tm.service.TmAllocationCheckService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.06.30 
 * @description : 배차마스터체크결과 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.04 ParkJinWoo 생성
 */
@Tag(name = "TM > 배차관리", description = "배차마스터체크결과")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/tm/allocationCheck")
public class TmAllocationCheckController {

	private final TmAllocationCheckService tmAllocationCheckService;


	/**
	 * @description :배차마스터체크결과)조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.04 ParkJinWoo 생성
	 */
	@Operation(summary = "배차마스터체크결과", description = "배차마스터체크결과")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<Object> getMasterList(@Valid @RequestBody TmAllocationCheckMasterReqDto tmAllocationCheckMasterReqDto) {
		return ApiResult.createResult(tmAllocationCheckService.getMasterList(tmAllocationCheckMasterReqDto));
	}

	/**
	 * @description :배차마스터체크결과)조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.04 ParkJinWoo 생성
	 */
	@Operation(summary = "배차마스터체크결과", description = "배차마스터체크결과")
	@GetMapping(value = "/v1.0/getDetailList")
	public ApiResult<Object> getDetailList(@Valid  TmAllocationCheckDetailReqDto tmAllocationCheckDetailReqDto,Page page) {
		return ApiResult.createResult(tmAllocationCheckService.getDetailList(tmAllocationCheckDetailReqDto,page));
	}
}