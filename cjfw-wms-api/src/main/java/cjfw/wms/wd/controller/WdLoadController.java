package cjfw.wms.wd.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.wd.dto.WdLoadReqDto;
import cjfw.wms.wd.dto.WdLoadResDto;
import cjfw.wms.wd.service.WdLoadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2025.11.12
 * @description : 출차지시처리 Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.12 JiHoPark  생성 </pre>
 */
@Tag(name = "WdLoadController", description = "출차지시처리(출고 > 출차지시 > 출차지시처리)")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/wd/load")
public class WdLoadController {
	private final WdLoadService wdLoadService;

	/**
	 * @description : 출차지시처리 - 출차지시처리 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.12 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "출차지시처리 - 출차지시처리 목록 조회", description = "출차지시처리 - 출차지시처리 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<WdLoadResDto>> getMasterList(@Valid @RequestBody WdLoadReqDto dto) {
		return ApiResult.createResult(wdLoadService.getMasterList(dto));
	}

	/**
	 * @description : 출차지시처리 - 출차지시처리 상세 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.12 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "출차지시처리 - 출차지시처리 상세 목록 조회", description = "출차지시처리 - 출차지시처리 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList2")
	public ApiResult<List<WdLoadResDto>> getMasterList2(@Valid @RequestBody WdLoadReqDto dto) {
		return ApiResult.createResult(wdLoadService.getMasterList2(dto));
	}

	/**
	 * @description : 출차지시처리 - report 정보 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.13 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "출차지시처리 - report 정보 조회", description = "출차지시처리 - report 정보 조회")
	@PostMapping(value = "/v1.0/getLoadReportInfo")
	public ApiResult<WdLoadResDto> getLoadReportInfo(@Valid @RequestBody WdLoadReqDto dto) {
		return ApiResult.createResult(wdLoadService.getLoadReportInfo(dto));
	}

}
