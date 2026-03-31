package cjfw.wms.wd.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.wd.dto.WdItemTraceReqDto;
import cjfw.wms.wd.dto.WdItemTraceResDto;
import cjfw.wms.wd.service.WdItemTraceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.11.17
 * @description : 모니터링 > 검수 > 검수 공정별 Controller Class
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.17 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Tag(name = "모니터링 > 검수 > 검수 공정별", description = "모니터링 > 검수 > 검수 공정별을 조회한다.")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping({"api/wd/itemTrace", "ltx/wd/itemTrace"})
public class WdItemTraceController {
	
	private final WdItemTraceService wdItemTraceService;
	
	/**
	 * @description : 모니터링 > 검수 > 검수 공정별 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.17 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "모니터링 > 검수 > 검수 공정별 조회", description = "모니터링 > 검수 > 검수 공정별 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<WdItemTraceResDto>> getMasterList(@RequestBody WdItemTraceReqDto dto) {
		return ApiResult.createResult(wdItemTraceService.getMasterList(dto));
	}
	
	/**
	 * @description : 모니터링 > 검수 > 검수 공정별_팝업 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.17 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "모니터링 > 검수 > 검수 공정별_팝업 조회", description = "모니터링 > 검수 > 검수 공정별_팝업 조회")
	@PostMapping(value = "/v1.0/getPopList")
	public ApiResult<List<WdItemTraceResDto>> getPopList(@RequestBody WdItemTraceReqDto dto) {
		return ApiResult.createResult(wdItemTraceService.getPopList(dto));
	}
}
