package cjfw.wms.tm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.tm.dto.TmTransportControlReqDto;
import cjfw.wms.tm.dto.TmTransportControlResDto;
import cjfw.wms.tm.service.TmTransportControlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2025.11.05
 * @description : 수송배차조정 Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.05 JiHoPark  생성 </pre>
 */
@Tag(name = "TmTransportControlController", description = "수송배차조정(정산 > 운송비정산 > 수송배차조정)")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/tm/transportcontrol")
public class TmTransportControlController {
	private final TmTransportControlService tmTransportControlService;

	/**
	 * @description : 수송배차조정 - 노선 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.06 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "수송배차조정 - 노선 목록 조회", description = "수송배차조정 - 노선 목록 조회")
	@PostMapping(value = "/v1.0/getTransportRoutingList")
	public ApiResult<List<TmTransportControlResDto>> getTransportRoutingList(@Valid @RequestBody TmTransportControlReqDto dto) {
		return ApiResult.createResult(tmTransportControlService.getTransportRoutingList(dto));
	}

	/**
	 * @description : 수송배차조정 - 수송배차조정 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.05 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "수송배차조정 - 수송배차조정 목록 조회", description = "수송배차조정 - 수송배차조정 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<TmTransportControlResDto>> getMasterList(@Valid @RequestBody TmTransportControlReqDto dto) {
		return ApiResult.createResult(tmTransportControlService.getMasterList(dto));
	}

	/**
	 * @description : 수송배차조정 - 수송배차조정 상세 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.05 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "수송배차조정 - 수송배차조정 상세 목록 조회", description = "수송배차조정 - 수송배차조정 상세 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList2")
	public ApiResult<List<TmTransportControlResDto>> getMasterList2(@Valid @RequestBody TmTransportControlReqDto dto) {
		return ApiResult.createResult(tmTransportControlService.getMasterList2(dto));
	}

	/**
	 * @description : 수송배차조정 - 차량정보 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.11 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "수송배차조정 - 차량정보 조회", description = "수송배차조정 - 차량정보 조회")
	@PostMapping(value = "/v1.0/getCarInfo")
	public ApiResult<TmTransportControlResDto> getCarInfo(@Valid @RequestBody TmTransportControlReqDto dto) {
		return ApiResult.createResult(tmTransportControlService.getCarInfo(dto));
	}

	/**
	 * @description : 수송배차조정 - 비용 정보 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.11 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "수송배차조정 - 비용 정보 조회", description = "수송배차조정 - 비용 정보 조회")
	@PostMapping(value = "/v1.0/getTotPrice")
	public ApiResult<TmTransportControlResDto> getTotPrice(@Valid @RequestBody TmTransportControlReqDto dto) {
		return ApiResult.createResult(tmTransportControlService.getTotPrice(dto));
	}

	/**
	 * @description : 수송배차조정 - 목록 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.11 JiHoPark 생성 </pre>
	 */
	@Operation(summary = "수송배차조정 - 목록 저장", description = "주문그룹 목록 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<String> saveMasterList(@RequestBody TmTransportControlReqDto dto) {
		return ApiResult.createResult(tmTransportControlService.saveMasterList(dto));
	}
}
