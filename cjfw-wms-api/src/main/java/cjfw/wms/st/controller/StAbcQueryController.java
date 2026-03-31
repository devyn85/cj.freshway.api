package cjfw.wms.st.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StAbcQueryReqDto;
import cjfw.wms.st.dto.StAbcQueryResT1Dto;
import cjfw.wms.st.dto.StAbcQueryResT2Dto;
import cjfw.wms.st.service.StAbcQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.11.12
 * @description : 재고 > 재고운영 > ABC 분석 Controller Class
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.12 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Tag(name = "재고 > 재고운영 > ABC 분석", description = "재고 > 재고운영 > ABC 분석을 조회, 저장 한다.")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/abcQuery")
public class StAbcQueryController {
	
	private final StAbcQueryService stAbcQueryService;

	/**
	 * @description : 재고 > 재고운영 > ABC 분석 분석_탭 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.12 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "재고 > 재고운영 > ABC 분석 분석_탭 조회", description = "재고 > 재고운영 > ABC 분석 분석_탭 조회")
	@PostMapping(value = "/v1.0/getMasterT1List")
	public ApiResult<List<StAbcQueryResT1Dto>> getMasterT1List(@RequestBody StAbcQueryReqDto dto) {
		return ApiResult.createResult(stAbcQueryService.getMasterT1List(dto));
	}
	
	/**
	 * @description : 재고 > 재고운영 > ABC 분석 기준_탭 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.12 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "재고 > 재고운영 > ABC 분석 기준_탭 조회", description = "재고 > 재고운영 > ABC 분석 기준_탭 조회")
	@PostMapping(value = "/v1.0/getMasterT2List")
	public ApiResult<List<StAbcQueryResT2Dto>> getMasterT2List(@RequestBody StAbcQueryReqDto dto) {
		return ApiResult.createResult(stAbcQueryService.getMasterT2List(dto));
	}
	
	/**
	 * @description : 재고 > 재고운영 > ABC 분석 기준_탭 조회 저장 Method
	 * @issues :
	 *
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.12 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "재고 > 재고운영 > ABC 분석 기준_탭 조회 저장", description = "재고 > 재고운영 > ABC 분석 기준_탭 조회 저장")
	@PostMapping(value = "/v1.0/saveMasterT2List")
	public ApiResult<String> saveMasterList(@RequestBody StAbcQueryReqDto dto) throws Exception {
	    return ApiResult.createResult(stAbcQueryService.saveMasterT2List(dto));	
	}
}
