package cjfw.wms.tm.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.tm.dto.TmResultTempCarListReqDto;
import cjfw.wms.tm.service.TmResultTempCarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.08.06 
 * @description : 일별임시차현황 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.06 ParkJinWoo 생성
 */

@Tag(name = "배차 > 배차현황 > 일별임시차현황", description = "일별임시차현황")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/tm/resultTempCar")
public class TmResultTempCarController {

	private final TmResultTempCarService tmResultTempCarService;


	/**
	 * @description : 일별임시차현황 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.06 ParkJinWoo 생성
	 */
	@Operation(summary = "일별임시차현황 조회", description = "일별임시차현황 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<Object> getMasterList(@Valid @RequestBody TmResultTempCarListReqDto tmResultTempCarListReqDto) {
		return ApiResult.createResult(tmResultTempCarService.getMasterList(tmResultTempCarListReqDto));
	}

}