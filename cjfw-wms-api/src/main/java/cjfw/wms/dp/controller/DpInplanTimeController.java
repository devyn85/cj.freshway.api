package cjfw.wms.dp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.dp.dto.DpInplanTimeReqDto;
import cjfw.wms.dp.dto.DpInplanTimeResDto;
import cjfw.wms.dp.service.DpInplanTimeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.12.01
 * @description : 입고 > 입고현황 > 입고 예정진행 현황(입차시간) Controller Class
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.01 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Tag(name = "입고 > 입고현황 > 입고 예정진행 현황(입차시간)", description = "입고 > 입고현황 > 입고 예정진행 현황(입차시간)을 조회한다.")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/dp/inplanTime")
public class DpInplanTimeController {
	private final DpInplanTimeService dpInplanTimeService;

	/**
	 * @description : 입고 > 입고현황 > 입고 예정진행 현황(입차시간) 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.01 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "입고 > 입고현황 > 입고 예정진행 현황(입차시간) 조회", description = "입고 > 입고현황 > 입고 예정진행 현황(입차시간) 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<DpInplanTimeResDto>> getMasterList(@RequestBody DpInplanTimeReqDto dto) {
		return ApiResult.createResult(dpInplanTimeService.getMasterList(dto));
	}
}
