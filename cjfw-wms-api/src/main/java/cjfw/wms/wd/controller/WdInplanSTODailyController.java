package cjfw.wms.wd.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.wd.dto.WdInplanSTODailyReqDto;
import cjfw.wms.wd.dto.WdInplanSTODailyResDto;
import cjfw.wms.wd.service.WdInplanSTODailyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net)
 * @date : 2025.11.29
 * @description : 광역일배검수현황 Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.29 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Tag(name = "inplanSTODaily", description = "광역일배검수현황")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping({"api/wd/inplanSTODaily", "ltx/wd/inplanSTODaily"})
public class WdInplanSTODailyController {

	private final WdInplanSTODailyService wdInplanSTODailyService;

	/**
	 * @description : 광역일배검수현황 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.29 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "광역일배검수현황 목록 조회", description = "광역일배검수현황 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<WdInplanSTODailyResDto>> getMasterList(@RequestBody WdInplanSTODailyReqDto dto) {


		return ApiResult.createResult(wdInplanSTODailyService.getMasterList(dto));
	}
}