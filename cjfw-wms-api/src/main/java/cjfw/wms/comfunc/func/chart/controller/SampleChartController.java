/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.comfunc.func.chart.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.comfunc.func.chart.dto.SampleChartGetReqDto;
import cjfw.wms.comfunc.func.chart.dto.SampleChartGetResDto;
import cjfw.wms.comfunc.func.chart.service.SampleChartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("comfunc/func/chart")
public class SampleChartController {

	private final SampleChartService service;

	/**
	 * 시스템 예외 월별 건수를 조회한다.<br>
	 */
	@GetMapping(value = "/list")
	public ApiResult<List<SampleChartGetResDto>> getBoardList(SampleChartGetReqDto sampleChartGetReqDto) {
		return ApiResult.createResult(service.getSystemExceptionCount(sampleChartGetReqDto));

		/**
		 * [MPA 참조]
		 * > 요청
		 * FROM_DT: "2021-06-20"
		 * THRU_DT: "2022-06-20"
		 *
		 * > 응답
		 * "data":[{"YM":"2021-06","CNT":91},{"YM":"2021-07","CNT":72}, ... ]
		 */
	}
}

