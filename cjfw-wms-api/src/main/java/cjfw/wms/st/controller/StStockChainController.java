package cjfw.wms.st.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StStockChainReqDto;
import cjfw.wms.st.dto.StStockChainResDto;
import cjfw.wms.st.service.StStockChainService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.09.23
 * @description : 상품별현재고(PLT)현황 기능을 구현한 Controller Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.23        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Tag(name = "재고 > 재고현황 > 상품별현재고(PLT)현황")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/stockChain")
public class StStockChainController {
	private final StStockChainService stStockChainService;

	/**
	 * @description : 상품별현재고(PLT)현황 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.23        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "상품별현재고(PLT)현황 목록 조회", description = "상품별현재고(PLT)현황 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<StStockChainResDto>> getMasterList(@RequestBody StStockChainReqDto dto) {
		return ApiResult.createResult(stStockChainService.getMasterList(dto));
	}
	
}