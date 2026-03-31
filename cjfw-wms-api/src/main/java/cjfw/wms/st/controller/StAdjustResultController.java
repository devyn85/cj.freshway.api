package cjfw.wms.st.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StAdjustResultDetailResDto;
import cjfw.wms.st.dto.StAdjustResultReqDto;
import cjfw.wms.st.dto.StAdjustResultResDto;
import cjfw.wms.st.service.StAdjustResultService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.05.23 
 * @description : 재고감모현황 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.23 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Tag(name = "StAdjustResult", description = "재고감모현황")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/adjustResult")
public class StAdjustResultController {

	private final StAdjustResultService stAdjustResultService;

	/**
	 * @description : 재고감모현황 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.23 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "재고감모현황 목록 조회", description = "재고감모현황 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<StAdjustResultResDto>> getMasterList(@Valid @RequestBody StAdjustResultReqDto stAdjustResultReqDto) {
		return ApiResult.createResult(stAdjustResultService.getMasterList(stAdjustResultReqDto));
	}
	
	/**
	 * @description : 재고감모현황 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.23 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "재고감모현황 상세 조회", description = "재고감모현황 상세 조회")
	@GetMapping(value = "/v1.0/getDetailList")
	public ApiResult<List<StAdjustResultDetailResDto>> getDetailList(@Valid StAdjustResultReqDto stAdjustResultReqDto) {
		return ApiResult.createResult(stAdjustResultService.getDetailList(stAdjustResultReqDto));
	}
	
	
	public static boolean isNull(String str) {		 
		return str == null || str.trim().isEmpty();
	}

}