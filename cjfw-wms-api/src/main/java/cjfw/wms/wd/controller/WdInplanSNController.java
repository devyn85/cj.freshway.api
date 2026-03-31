package cjfw.wms.wd.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.wd.dto.WdInplanSNReqDto;
import cjfw.wms.wd.dto.WdInplanSNResDetailDto;
import cjfw.wms.wd.dto.WdInplanSNResDto;
import cjfw.wms.wd.dto.WdInplanSNResExcelDto;
import cjfw.wms.wd.service.WdInplanSNService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net)
 * @date : 2025.05.16
 * @description : 출고진행현황 Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.05.16 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Tag(name = "WdInplanSN", description = "이력상품출고현황")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/wd/inplanSN")
public class WdInplanSNController {

	private final WdInplanSNService wdInplanSNService;

	/**
	 * @description : 이력상품출고현황 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.10 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "이력상품출고현황 목록 조회", description = "이력상품출고현황 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<WdInplanSNResDto>> getDataHeaderlist(@RequestBody WdInplanSNReqDto wdInplanSNReqDto, Page page) {


		return ApiResult.createResult(wdInplanSNService.getMasterList(wdInplanSNReqDto, page));
	}
	
	/**
	 * @description : 이력상품출고현황 상세 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.10 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "이력상품출고현황 상세 조회", description = "이력상품출고현황 상세 조회")
	@PostMapping(value = "/v1.0/getDetailList")
	public ApiResult<List<WdInplanSNResDetailDto>> getDetailList(@RequestBody WdInplanSNReqDto wdInplanSNReqDto, Page page) {


		return ApiResult.createResult(wdInplanSNService.getDetailList(wdInplanSNReqDto, page));
	}
	
	/**
	 * @description : 이력상품출고현황 엑셀 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.11 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "이력상품출고현황 엑셀 조회", description = "이력상품출고현황 엑셀 조회")
	@PostMapping(value = "/v1.0/getDataExcelList")
	public ApiResult<List<WdInplanSNResExcelDto>> getDataExcelList(@RequestBody WdInplanSNReqDto wdInplanSNReqDto, Page page) {
		
		
		return ApiResult.createResult(wdInplanSNService.getDataExcelList(wdInplanSNReqDto, page));
	}


}