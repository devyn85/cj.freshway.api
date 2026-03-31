/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.sysmgt.func.exc.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.sysmgt.func.exc.dto.ExcLogGetReqDto;
import cjfw.wms.sysmgt.func.exc.dto.ExcLogGetResDto;
import cjfw.wms.sysmgt.func.exc.service.ExcLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 시스템 예외 이력
 */
@Tag(name = "시스템예외이력", description = "시스템예외이력 API")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("sysmgt/func/exc/excLog")
public class ExcLogController {
	
	private final ExcLogService excLogService;

	/**
	 * 시스템 예외 이력 조회<br>
	 */
	@Operation(summary = "시스템예외이력 목록", description = "시스템예외이력 목록 조회")
	@GetMapping(value = "/search")
	public ApiResult<Page<List<ExcLogGetResDto>>> searchExcLogList(@Valid ExcLogGetReqDto excLogGetReqDto,Page page) {
		return ApiResult.createResult(excLogService.getExcLogList(excLogGetReqDto,page));
	}
}