package cjfw.wms.st.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StDailyInoutExDcDetailReqDto;
import cjfw.wms.st.dto.StDailyInoutExDcMasterReqDto;
import cjfw.wms.st.service.StDailyInoutExDcService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.07.10 
 * @description : 외부비축상품별수불현황 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.10 ParkJinWoo 생성
 */
@Tag(name = "재고 > 재고현황 > 외부비축상품별수불현황")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/dailyInoutExDc")
public class StDailyInoutExDcController {
	private final StDailyInoutExDcService stDailyInoutExDcService;
	
	/**
	 * @description : 외부비축상품별수불현황 헤더목록 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.10 ParkJinWoo 생성
	 */
	@Operation(summary = "외부비축상품별수불현황 헤더목록 조회", description = "외부비축상품별수불현황 헤더목록 조회")
	@PostMapping(value = "/v1.0/getStDailyInoutExDcMasterList")
	public ApiResult<Object> getStDailyInoutExDcMasterList(@Valid @RequestBody StDailyInoutExDcMasterReqDto stDailyInoutExDcMasterReqDto) {
		return ApiResult.createResult(stDailyInoutExDcService.getStDailyInoutExDcMasterList(stDailyInoutExDcMasterReqDto));
	}
	/**
	 * @description : 외부비축상품별수불현황 상세목록 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.10 ParkJinWoo 생성
	 */
	@Operation(summary = "외부비축상품별수불현황 헤더서브목록 조회", description = "외부비축상품별수불현황 상세목록 조회")
	@PostMapping(value = "/v1.0/getStDailyInoutExDcDetailList")
	public ApiResult<Object> getStDailyInoutExDcDetailList(@Valid @RequestBody StDailyInoutExDcDetailReqDto stDailyInoutExDcDetailReqDto) {
		return ApiResult.createResult(stDailyInoutExDcService.getStDailyInoutExDcDetailList(stDailyInoutExDcDetailReqDto));
	}

}