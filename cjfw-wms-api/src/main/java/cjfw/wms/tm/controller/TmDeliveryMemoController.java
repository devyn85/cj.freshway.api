package cjfw.wms.tm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.tm.dto.TmDeliveryMemoReqDto;
import cjfw.wms.tm.dto.TmDeliveryMemoResDto;
import cjfw.wms.tm.service.TmDeliveryMemoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2025.10.27
 * @description : 거래처별 메모사항 조회 Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.27 JiHoPark  생성 </pre>
 */
@Tag(name = "TmDeliveryMemoController", description = "거래처별 메모사항 조회(배송 > 배차현황 > 거래처별 메모사항 조회)")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/tm/deliveryMemo")
public class TmDeliveryMemoController {
	private final TmDeliveryMemoService tmDeliveryMemoService;

	/**
	 * @description : 거래처별 메모사항 조회 - 거래처별 메모사항 조회 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.22 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "거래처별 메모사항 조회 - 거래처별 메모사항 조회 목록 조회", description = "거래처별 메모사항 조회 - 거래처별 메모사항 조회 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<TmDeliveryMemoResDto>> getMasterList(@Valid @RequestBody TmDeliveryMemoReqDto dto) {
		return ApiResult.createResult(tmDeliveryMemoService.getMasterList(dto));
	}

}
