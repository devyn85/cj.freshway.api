package cjfw.wms.dp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.dp.dto.DpInplanSNDetailResDto;
import cjfw.wms.dp.dto.DpInplanSNExcelResDto;
import cjfw.wms.dp.dto.DpInplanSNReqDto;
import cjfw.wms.dp.dto.DpInplanSNResDto;
import cjfw.wms.dp.service.DpInplanSNService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net)
 * @date : 2025.06.17
 * @description : 이력상품입고현황 Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.17 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Tag(name = "DpInplanSN", description = "이력상품입고현황")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/dp/dpInplanSN")
public class DpInplanSNController {

	private final DpInplanSNService dpInplanSNService;

	/**
	 * @description : 이력상품입고현황 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.17 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "이력상품입고현황 목록 조회", description = "이력상품입고현황 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<DpInplanSNResDto>> getMasterList(@RequestBody DpInplanSNReqDto dpInplanSNReqDto, Page page) {


		return ApiResult.createResult(dpInplanSNService.getMasterList(dpInplanSNReqDto, page));
	}
	
	/**
	 * @description : 이력상품입고현황 상세 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.17 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "이력상품입고현황 상세 조회", description = "이력상품입고현황 상세 조회")
	@PostMapping(value = "/v1.0/getDetailList")
	public ApiResult<List<DpInplanSNDetailResDto>> getDetailList(@RequestBody DpInplanSNReqDto dpInplanSNReqDto, Page page) {


		return ApiResult.createResult(dpInplanSNService.getDetailList(dpInplanSNReqDto, page));
	}
	
	/**
	 * @description : 이력상품입고현황 엑셀 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.17 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "이력상품입고현황 엑셀 조회", description = "이력상품입고현황 엑셀 조회")
	@PostMapping(value = "/v1.0/getDataExcelList")
	public ApiResult<List<DpInplanSNExcelResDto>> getDataExcelList(@RequestBody DpInplanSNReqDto dpInplanSNReqDto, Page page) {
		
		
		return ApiResult.createResult(dpInplanSNService.getDataExcelList(dpInplanSNReqDto, page));
	}


}