package cjfw.wms.wd.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.wd.dto.WdShortageListResDto;
import cjfw.wms.wd.dto.WdShortageListReqDto;
import cjfw.wms.wd.service.WdShortageListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net)
 * @date : 2026.03.05
 * @description : 출고결품현황 Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2026.03.05 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Tag(name = "WdShortageList", description = "출고결품현황")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/wd/shortageList")
public class WdShortageListController {

	private final WdShortageListService wdShortageListService;

	/**
	 * @description : 출고결품현황 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2026.03.05 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "출고결품현황 목록 조회", description = "출고결품현황 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<WdShortageListResDto>> getDataHeaderlist(@RequestBody WdShortageListReqDto dto, Page page) {


		return ApiResult.createResult(wdShortageListService.getMasterList(dto, page));
	}

}