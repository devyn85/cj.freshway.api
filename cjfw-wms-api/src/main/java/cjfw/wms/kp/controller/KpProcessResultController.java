package cjfw.wms.kp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.kp.dto.KpProcessResultReqDto;
import cjfw.wms.kp.dto.KpProcessResultResTab1Dto;
import cjfw.wms.kp.dto.KpProcessResultResTab2Dto;
import cjfw.wms.kp.dto.KpProcessResultResTab3Dto;
import cjfw.wms.kp.service.KpProcessResultService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 박요셉 (dytpq362@cj.net)
 * @date : 2025.12.18
 * @description : 공정별생산성 Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.18 박요셉 (dytpq362@cj.net)생성 </pre>
 */
@Tag(name = "KpProcessResult", description = "공정별생산성")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/kp/processResult")
public class KpProcessResultController {

	private final KpProcessResultService kpProcessResultService;

	/**
	 * @description : 공정별생산성 센터별 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.18 박요셉 (dytpq362@cj.net) 생성 </pre>
	 */
	@Operation(summary = "공정별생산성 센터별 목록 조회", description = "공정별생산성 센터별 목록 조회")
	@PostMapping(value = "/v1.0/getTab1MasterList")
	public ApiResult<List<KpProcessResultResTab1Dto>> getTab1MasterList(@RequestBody KpProcessResultReqDto dto) {
		return ApiResult.createResult(kpProcessResultService.getTab1MasterList(dto));
	}
	
	/**
	 * @description : 공정별생산성 작업자별 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 *  2025.12.18 박요셉 (dytpq362@cj.net) 생성</pre>
	 */
	@Operation(summary = "공정별생산성 작업자별 목록 조회", description = "공정별생산성 작업자별 목록 조회")
	@PostMapping(value = "/v1.0/getTab2MasterList")
	public ApiResult<List<KpProcessResultResTab2Dto>> getTab2MasterList(@RequestBody KpProcessResultReqDto dto) {
		return ApiResult.createResult(kpProcessResultService.getTab2MasterList(dto));
	}
	
	/**
	 * @description : 공정별생산성 Raw 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 *  2025.12.18 박요셉 (dytpq362@cj.net) 생성 </pre>
	 */
	@Operation(summary = "공정별생산성 Raw 목록 조회", description = "공정별생산성 Raw 목록 조회")
	@PostMapping(value = "/v1.0/getTab3MasterList")
	public ApiResult<List<KpProcessResultResTab3Dto>> getTab3MasterList(@RequestBody KpProcessResultReqDto dto) {
		return ApiResult.createResult(kpProcessResultService.getTab3MasterList(dto));
	}
	
	


}