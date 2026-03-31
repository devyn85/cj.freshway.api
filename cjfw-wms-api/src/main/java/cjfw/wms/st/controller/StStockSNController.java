package cjfw.wms.st.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StStockResDto;
import cjfw.wms.st.dto.StStockSNReqDto;
import cjfw.wms.st.service.StStockSNService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 성상수 (kduimux@cj.net) 생성
 * @date : 2025.05.16
 * @description : 재고조회 Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.05.16 성상수 (kduimux@cj.net) 생성 </pre>
 */
@Tag(name = "재고 > 재고현황 > 이력재고처리현황")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/stStockSN")
public class StStockSNController {
	private final StStockSNService stStockSDService;

	/**
	 * @description : 이력재고처리현황 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.12 성상수 (kduimux@cj.net) 생성 </pre>
	 */
	@Operation(summary = "이력재고처리현황 목록 조회", description = "이력재고처리현황 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<StStockResDto>> getMasterList(@RequestBody StStockSNReqDto dto) {
		return ApiResult.createResult(stStockSDService.getMasterList(dto));
	}
	

}