package cjfw.wms.kp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.kp.dto.KpWdShortageResultReqDto;
import cjfw.wms.kp.dto.KpWdShortageResultResT1Dto;
import cjfw.wms.kp.dto.KpWdShortageResultResT2Dto;
import cjfw.wms.kp.service.KpWdShortageResultService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.12.02
 * @description : 지표/모니터링 > 센터운영지표 > 출고결품실적 Controller Class
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.02 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Tag(name = "지표/모니터링 > 센터운영지표 > 출고결품실적", description = "지표/모니터링 > 센터운영지표 > 출고결품실적의 월탭,주간탭,월요약탭,일요약탭을 조회, 저장한다.")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping({"api/kp/wdShortageResult", "ltx/kp/wdShortageResult"})
public class KpWdShortageResultController {
	
	private final KpWdShortageResultService kpWdShortageResultService;
	
	/**
	 * @description : 지표/모니터링 > 센터운영지표 > 출고결품실적 달력변경시 해당월에 있는 날짜 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.02 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "지표/모니터링 > 센터운영지표 > 출고결품실적 달력변경시 해당월에 있는 날짜 조회", description = "지표/모니터링 > 센터운영지표 > 출고결품실적 달력변경시 해당월에 있는 날짜 조회")
	@PostMapping(value = "/v1.0/getColList")
	public ApiResult<List<KpWdShortageResultResT1Dto>> getColList(@RequestBody KpWdShortageResultReqDto dto) {
		return ApiResult.createResult(kpWdShortageResultService.getColList(dto));
	}
	

	/**
	 * @description : 지표/모니터링 > 센터운영지표 > 출고결품실적 월_탭 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.02 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "지표/모니터링 > 센터운영지표 > 출고결품실적 월요약_탭 조회", description = "지표/모니터링 > 센터운영지표 > 출고결품실적 월요약_탭 조회")
	@PostMapping(value = "/v1.0/getMasterT1List")
	public ApiResult<List<KpWdShortageResultResT1Dto>> getMasterT1List(@RequestBody KpWdShortageResultReqDto dto) {
		return ApiResult.createResult(kpWdShortageResultService.getMasterT1List(dto));
	}
	
	/**
	 * @description : 지표/모니터링 > 센터운영지표 > 출고결품실적 월_탭 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.02 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "지표/모니터링 > 센터운영지표 > 출고결품실적 월요약_탭 상세 조회", description = "지표/모니터링 > 센터운영지표 > 출고결품실적 월요약_탭 상세 조회")
	@PostMapping(value = "/v1.0/getDetailT1List")
	public ApiResult<List<KpWdShortageResultResT1Dto>> getDetailT1List(@RequestBody KpWdShortageResultReqDto dto) {
		return ApiResult.createResult(kpWdShortageResultService.getDetailT1List(dto));
	}

	/**
	 * @description : 지표/모니터링 > 센터운영지표 > 출고결품실적 일요약_탭 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.02 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "지표/모니터링 > 센터운영지표 > 출고결품실적 일요약_탭 조회", description = "지표/모니터링 > 센터운영지표 > 출고결품실적 일요약_탭 조회")
	@PostMapping(value = "/v1.0/getMasterT2List")
	public ApiResult<List<KpWdShortageResultResT2Dto>> getMasterT2List(@RequestBody KpWdShortageResultReqDto dto) {
		return ApiResult.createResult(kpWdShortageResultService.getMasterT2List(dto));
	}
	
}
