package cjfw.wms.st.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StDailyStockReqDto;
import cjfw.wms.st.dto.StDailyStockResDto;
import cjfw.wms.st.dto.StStockDataCodeListResDto;
import cjfw.wms.st.service.StDailyStockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : KimDongHyeon (tirran123@cj.net) 생성
 * @date : 2025.11.05
 * @description : 시점별재고조회 Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.05 KimDongHyeon (tirran123@cj.net) 생성 </pre>
 */
@Tag(name = "재고 > 재고현황 > 시점별재고조회")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/dailyStock")
public class StDailyStockController {
	private final StDailyStockService stDailyStockService;

	/**
	 * @description : 시점별재고조회 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.12 KimDongHyeon (tirran123@cj.net) 생성 </pre>
	 */
	@Operation(summary = "시점별재고조회 목록 조회", description = "시점별재고조회 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<StDailyStockResDto>> getMasterList(@RequestBody StDailyStockReqDto dto) {
		return ApiResult.createResult(stDailyStockService.getMasterList(dto));
	}
	
	/**
	 * @description : 존 리스트 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.16 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "존 리스트 조회 ", description = "존 리스트 조회 ")
	@GetMapping(value = "/v1.0/getDataCodeList")
	public ApiResult<List<StStockDataCodeListResDto>> getDataCodeList(@Valid StDailyStockReqDto dto) {
		return ApiResult.createResult(stDailyStockService.getDataCodeList(dto));
	}	
}