package cjfw.wms.st.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StExdcManualIndicatorReqDto;
import cjfw.wms.st.service.StExdcManualIndicatorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2026.02.03 
 * @description : 수기출고현황 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2026.02.03 ParkJinWoo 생성
 */
@Tag(name = "재고 > 재고현황 > 수기출고현황")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/stExdcManualIndicator")
public class StExdcManualIndicatorController {
	private final StExdcManualIndicatorService stExdcManualIndicatorServuice;
	
	/**
	 * @description : 수기출고현황 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.02.03 ParkJinWoo 생성
	 */
	@Operation(summary = "수기출고현황 헤더목록 조회", description = "수기출고현황 헤더목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<Object> getStDailyInoutExDcMasterList(@Valid @RequestBody StExdcManualIndicatorReqDto reqDto) {
		return ApiResult.createResult(stExdcManualIndicatorServuice.getMasterList(reqDto));
	}
	

}