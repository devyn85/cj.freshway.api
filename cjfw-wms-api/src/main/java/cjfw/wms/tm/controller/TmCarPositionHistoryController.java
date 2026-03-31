package cjfw.wms.tm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.tm.dto.TmCarPositionHistoryReqDto;
import cjfw.wms.tm.dto.TmCarPositionHistoryResDto;
import cjfw.wms.tm.service.TmCarPositionHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2025.11.14
 * @description : 운행일지 Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.14 JiHoPark  생성 </pre>
 */
@Tag(name = "TmCarPositionHistoryController", description = "운행일지(배송 > 차량관제 > 운행일지)")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/tm/carpositionhistory")
public class TmCarPositionHistoryController {
	private final TmCarPositionHistoryService tmCarPositionHistoryService;

	/**
	 * @description : 운행일지 - 운행일지 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.14 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "운행일지 - 운행일지 목록 조회", description = "운행일지 - 운행일지 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<TmCarPositionHistoryResDto>> getMasterList(@Valid @RequestBody TmCarPositionHistoryReqDto dto) {
		return ApiResult.createResult(tmCarPositionHistoryService.getMasterList(dto));
	}

	/**
	 * @description : 운행일지 - 운행일지 상세 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.14 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "운행일지 - 운행일지 상세 목록 조회", description = "운행일지 - 운행일지 상세 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList2")
	public ApiResult<List<TmCarPositionHistoryResDto>> getMasterList2(@Valid @RequestBody TmCarPositionHistoryReqDto dto) {
		return ApiResult.createResult(tmCarPositionHistoryService.getMasterList2(dto));
	}

	/**
	 * @description : 차량운행일지 - report 정보 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.14 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "차량운행일지 - report 정보 조회", description = "출차지시처리 - report 정보 조회")
	@PostMapping(value = "/v1.0/getCarPositionHistoryInfo")
	public ApiResult<TmCarPositionHistoryResDto> getCarPositionHistoryInfo(@Valid @RequestBody TmCarPositionHistoryReqDto dto) {
		return ApiResult.createResult(tmCarPositionHistoryService.getCarPositionHistoryInfo(dto));
	}
}
