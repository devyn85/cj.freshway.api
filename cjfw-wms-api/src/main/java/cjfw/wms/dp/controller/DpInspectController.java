package cjfw.wms.dp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.dp.dto.DpInspectDetailResDto;
import cjfw.wms.dp.dto.DpInspectHeaderResDto;
import cjfw.wms.dp.dto.DpInspectReqDto;
import cjfw.wms.dp.dto.DpInspectTotalResDto;
import cjfw.wms.dp.service.DpInspectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.05.22
 * @description : 입고검수처리 기능을 구현한 Controller Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.05.22        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Tag(name = "입고 > 입고작업 > 입고검수처리", description = "입고검수처리")	
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/dp/inspect")
public class DpInspectController {

	private final DpInspectService DpInspectService;
	
	@Operation(summary = "입고검수처리 목록 조회", description = "입고검수처리 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<DpInspectHeaderResDto>> getMasterList(@RequestBody DpInspectReqDto dpInspectReqDto) {
		return ApiResult.createResult(DpInspectService.getMasterList(dpInspectReqDto));
	}

	@Operation(summary = "입고검수처리 총량 조회", description = "입고검수처리 총량 조회")
	@PostMapping(value = "/v1.0/getTotalList")
	public ApiResult<List<DpInspectTotalResDto>> getTotalList(@RequestBody DpInspectReqDto dpInspectReqDto) {
		return ApiResult.createResult(DpInspectService.getTotalList(dpInspectReqDto));
	}
	
	@Operation(summary = "입고검수처리 일배상세 조회", description = "입고검수처리 일배상세 조회")
	@PostMapping(value = "/v1.0/getDetailList")
	public ApiResult<List<DpInspectDetailResDto>> getDetailList(@RequestBody DpInspectReqDto dpInspectReqDto) {
		return ApiResult.createResult(DpInspectService.getDetailList(dpInspectReqDto));
	}
	
	@Operation(summary = "입고검수처리 저장", description = "입고검수처리 저장")
	@PostMapping(value = "/v1.0/saveMaster")
	public ApiResult<String> saveMaster(@RequestBody DpInspectReqDto dpInspectReqDto) throws Exception {
		return ApiResult.createResult(DpInspectService.saveMaster(dpInspectReqDto));
	}
}