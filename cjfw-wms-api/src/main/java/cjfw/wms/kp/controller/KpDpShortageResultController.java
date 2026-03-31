package cjfw.wms.kp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.kp.dto.KpDpShortageResultReqDto;
import cjfw.wms.kp.dto.KpDpShortageResultResT1Dto;
import cjfw.wms.kp.dto.KpDpShortageResultResT2Dto;
import cjfw.wms.kp.dto.KpDpShortageResultResT3Dto;
import cjfw.wms.kp.dto.KpDpShortageResultResT4Dto;
import cjfw.wms.kp.service.KpDpShortageResultService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.09.08
 * @description : 지표 > 센터 운영 > 입고 결품 현황 Controller Class
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.08 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Tag(name = "지표 > 센터 운영 > 입고 결품 현황", description = "지표 > 센터 운영 > 입고 결품 현황의 일배, 저장, 일배요약, 저장요약을 조회한다.")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/kp/dpShortageResult")
public class KpDpShortageResultController {
	private final KpDpShortageResultService kpDpShortageResultService;

	/**
	 * @description : 지표 > 센터 운영 > 입고 결품 현황 일배_탭 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.08 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "지표 > 센터 운영 > 입고 결품 현황 일배_탭 조회", description = "지표 > 센터 운영 > 입고 결품 현황 일배_탭 조회")
	@PostMapping(value = "/v1.0/getMasterT1List")
	public ApiResult<List<KpDpShortageResultResT1Dto>> getMasterT1List(@RequestBody KpDpShortageResultReqDto dto) {
		return ApiResult.createResult(kpDpShortageResultService.getMasterT1List(dto));
	}	
	
	/**
	 * @description : 지표 > 센터 운영 > 입고 결품 현황 저장_탭 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.08 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "지표 > 센터 운영 > 입고 결품 현황 저장_탭 조회", description = "지표 > 센터 운영 > 입고 결품 현황 저장_탭 조회")
	@PostMapping(value = "/v1.0/getMasterT2List")
	public ApiResult<List<KpDpShortageResultResT2Dto>> getMasterT2List(@RequestBody KpDpShortageResultReqDto dto) {
		return ApiResult.createResult(kpDpShortageResultService.getMasterT2List(dto));
	}	
	
	/**
	 * @description : 지표 > 센터 운영 > 입고 결품 현황 일배요약_탭 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.08 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "지표 > 센터 운영 > 입고 결품 현황 일배요약_탭 조회", description = "지표 > 센터 운영 > 입고 결품 현황 일배요약_탭 조회")
	@PostMapping(value = "/v1.0/getMasterT3List")
	public ApiResult<List<KpDpShortageResultResT3Dto>> getMasterT3List(@RequestBody KpDpShortageResultReqDto dto) {
		return ApiResult.createResult(kpDpShortageResultService.getMasterT3List(dto));
	}
	
	/**
	 * @description : 지표 > 센터 운영 > 입고 결품 현황 저장요약_탭 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.08 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "지표 > 센터 운영 > 입고 결품 현황 저장요약_탭 조회", description = "지표 > 센터 운영 > 입고 결품 현황 저장요약_탭 조회")
	@PostMapping(value = "/v1.0/getMasterT4List")
	public ApiResult<List<KpDpShortageResultResT4Dto>> getMasterT4List(@RequestBody KpDpShortageResultReqDto dto) {
		return ApiResult.createResult(kpDpShortageResultService.getMasterT4List(dto));
	}	
}
