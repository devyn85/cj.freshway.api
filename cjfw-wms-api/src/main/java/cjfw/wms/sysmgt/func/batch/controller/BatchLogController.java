/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.sysmgt.func.batch.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.sysmgt.func.batch.dto.BatchLogJobGetReqDto;
import cjfw.wms.sysmgt.func.batch.dto.BatchLogJobGetResDto;
import cjfw.wms.sysmgt.func.batch.dto.BatchLogStepGetReqDto;
import cjfw.wms.sysmgt.func.batch.dto.BatchLogStepGetResDto;
import cjfw.wms.sysmgt.func.batch.dto.BatchLogStepMsgGetReqDto;
import cjfw.wms.sysmgt.func.batch.dto.BatchLogStepMsgGetResDto;
import cjfw.wms.sysmgt.func.batch.service.BatchLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 배치 수행 이력
 */
@Tag(name = "배치 수행 이력", description = "배치 수행 이력 API")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("sysmgt/func/batch")
public class BatchLogController {
	
	private final BatchLogService batchLogService;

	/**
	 * Batch job 리스트 조회<br>
	 */
	@Operation(summary = "Batch job 목록", description = "Batch job 목록 조회")
	@GetMapping(value = "/search")
	public ApiResult<Page<List<BatchLogJobGetResDto>>> searchJobList(@Valid BatchLogJobGetReqDto batchLogJobGetReqDto,Page page) {
		return ApiResult.createResult(batchLogService.getJobList(batchLogJobGetReqDto, page));
	}
	
	/**
	 * Batch Step 조회<br>
	 */
	@Operation(summary = "Batch Step 목록", description = "Batch Step 목록 조회")
	@GetMapping(value = "/searchStep")
	public ApiResult<List<BatchLogStepGetResDto>> searchStepList(@Valid BatchLogStepGetReqDto batchLogStepGetReqDto) {
		return ApiResult.createResult(batchLogService.getStepList(batchLogStepGetReqDto));
	}
		
	/**
	 * Batch Step Info MSG 조회<br>
	 */
	@Operation(summary = "Batch Step Info MSG 목록", description = "Batch Step Info MSG 목록 조회")
	@GetMapping(value = "/searchStepMSG")
	public ApiResult<BatchLogStepMsgGetResDto> searchStepMsgInfo(@Valid BatchLogStepMsgGetReqDto batchLogStepMsgGetReqDto) {
		return ApiResult.createResult(batchLogService.getStepMsg(batchLogStepMsgGetReqDto));
	}

}