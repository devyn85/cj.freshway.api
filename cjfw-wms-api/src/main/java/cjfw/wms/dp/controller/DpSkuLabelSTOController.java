package cjfw.wms.dp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.dp.dto.DpSkuLabelSTOReqDto;
import cjfw.wms.dp.dto.DpSkuLabelSTOResDetailDto;
import cjfw.wms.dp.dto.DpSkuLabelSTOResDto;
import cjfw.wms.dp.service.DpSkuLabelSTOService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.07.07
 * @description : 입고라벨출력(광역) Controller Class
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.07 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Tag(name = "DpSkuLabelSTOController API", description = "DpSkuLabelSTOController")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/dp/skuLabelSTO")
public class DpSkuLabelSTOController {
	private final DpSkuLabelSTOService dpSkuLabelSTOService;

	/**
	 * @description : 입고라벨출력(광역) 조회 List Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.07 KimDongHyeon (tirran123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "입고라벨출력(광역) 조회 List", description = "입고라벨출력(광역) 조회 List")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<DpSkuLabelSTOResDto>> getMasterList(@RequestBody DpSkuLabelSTOReqDto reqDto) {
		return ApiResult.createResult(dpSkuLabelSTOService.getMasterList(reqDto));
	}

	/**
	 * @description : 입고라벨출력(광역) 상세 조회 List Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.07 KimDongHyeon (tirran123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "입고라벨출력(광역) 상세 조회 List", description = "입고라벨출력(광역) 상세 조회 List")
	@PostMapping(value = "/v1.0/getDetailList")
	public ApiResult<List<DpSkuLabelSTOResDetailDto>> getDetailList(@RequestBody DpSkuLabelSTOReqDto reqDto) {
		return ApiResult.createResult(dpSkuLabelSTOService.getDetailList(reqDto));
	}
}
