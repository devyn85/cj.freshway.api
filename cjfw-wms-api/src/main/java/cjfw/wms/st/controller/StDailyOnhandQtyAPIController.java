package cjfw.wms.st.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StDailyOnhandQtyAPIReqDto;
import cjfw.wms.st.dto.StDailyOnhandQtyAPIResDto;
import cjfw.wms.st.service.StDailyOnhandQtyAPIService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.09.04 
 * @description : 외부창고 API 재고현황 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.04    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Tag(name = "재고 > 재고현황 > 외부창고 API 재고현황", description = "외부창고 API 재고현황 조회")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/dailyonhandqtyapi")
public class StDailyOnhandQtyAPIController {

	private final StDailyOnhandQtyAPIService stDailyOnhandQtyAPIService;

	/**
	 * @description : 외부창고 API 재고현황 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.04    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	@Operation(summary = "외부창고 API 재고현황 목록 조회", description = "외부창고 API 재고현황 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<StDailyOnhandQtyAPIResDto>> getMasterList(@Valid StDailyOnhandQtyAPIReqDto dto) {
		return ApiResult.createResult(stDailyOnhandQtyAPIService.getMasterList(dto));
	}
	
}