package cjfw.wms.kp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.kp.dto.KpDailyUnloadReqDto;
import cjfw.wms.kp.dto.KpDailyUnloadResDto;
import cjfw.wms.kp.service.KpDailyUnloadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2026.01.19
 * @description : 데일리 생산성 하역 지표 관리 Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2026.01.19 JiHoPark  생성 </pre>
 */
@Tag(name = "KpDailyUnloadController", description = "데일리 생산성 하역 지표 관리(지표 > 생산성 > 데일리 생산성 하역 지표 관리)")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/kp/kpdailyunload")
public class KpDailyUnloadController {
	private final KpDailyUnloadService kpDailyUnloadService;

	/**
	 * @description : 데일리 생산성 하역 지표 관리 - 투입인원 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2026.01.19 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "데일리 생산성 하역 지표 관리 - 투입인원 목록 조회", description = "데일리 생산성 하역 지표 관리 - 투입인원 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<KpDailyUnloadResDto>> getMasterList(@Valid @RequestBody KpDailyUnloadReqDto dto) {
		return ApiResult.createResult(kpDailyUnloadService.getMasterList(dto));
	}

	/**
	 * @description : 데일리 생산성 하역 지표 관리 - 센터업무관리 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2026.01.20 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "데일리 생산성 하역 지표 관리 - 센터업무관리 목록 조회", description = "데일리 생산성 하역 지표 관리 - 센터업무관리 목록 조회")
	@PostMapping(value = "/v1.0/getPopupMasterList")
	public ApiResult<List<KpDailyUnloadResDto>> getPopupMasterList(@Valid @RequestBody KpDailyUnloadReqDto dto) {
		return ApiResult.createResult(kpDailyUnloadService.getPopupMasterList(dto));
	}

	/**
	 * @description : 데일리 생산성 하역 지표 관리 - 센터업무관리(예외) 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2026.01.20 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "데일리 생산성 하역 지표 관리 - 센터업무관리(예외) 목록 조회", description = "데일리 생산성 하역 지표 관리 - 센터업무관리(예외) 목록 조회")
	@PostMapping(value = "/v1.0/getPopupMasterList2")
	public ApiResult<List<KpDailyUnloadResDto>> getPopupMasterList2(@Valid @RequestBody KpDailyUnloadReqDto dto) {
		return ApiResult.createResult(kpDailyUnloadService.getPopupMasterList2(dto));
	}

	/**
	 * @description : 데일리 생산성 하역 지표 관리 - 분류피킹 제외대상 고객 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2026.01.21 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "데일리 생산성 하역 지표 관리 - 분류피킹 제외대상 고객 목록 조회", description = "데일리 생산성 하역 지표 관리 - 분류피킹 제외대상 고객 목록 조회")
	@PostMapping(value = "/v1.0/getPopupMasterList3")
	public ApiResult<List<KpDailyUnloadResDto>> getPopupMasterList3(@Valid @RequestBody KpDailyUnloadReqDto dto) {
		return ApiResult.createResult(kpDailyUnloadService.getPopupMasterList3(dto));
	}

	/**
	 * @description : 데일리 생산성 하역 지표 관리 - 투입인원 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2026.01.27 JiHoPark 생성 </pre>
	 */
	@Operation(summary = "데일리 생산성 하역 지표 관리 - 투입인원 저장", description = "데일리 생산성 하역 지표 관리 - 투입인원 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<String> saveMasterList(@RequestBody KpDailyUnloadReqDto dto) {
		return ApiResult.createResult(kpDailyUnloadService.saveMasterList(dto));
	}

	/**
	 * @description : 데일리 생산성 하역 지표 관리 - 센터업무관리 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2026.01.23 JiHoPark 생성 </pre>
	 */
	@Operation(summary = "데일리 생산성 하역 지표 관리 - 센터업무관리 저장", description = "데일리 생산성 하역 지표 관리 - 센터업무관리 저장")
	@PostMapping(value = "/v1.0/savePopupMasterList")
	public ApiResult<String> savePopupMasterList(@RequestBody KpDailyUnloadReqDto dto) {
		return ApiResult.createResult(kpDailyUnloadService.savePopupMasterList(dto));
	}

	/**
	 * @description : 데일리 생산성 하역 지표 관리 - 센터업무관리 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2026.01.29 JiHoPark 생성 </pre>
	 */
	@Operation(summary = "데일리 생산성 하역 지표 관리 - 센터업무관리 저장", description = "데일리 생산성 하역 지표 관리 - 센터업무관리(예외) 저장")
	@PostMapping(value = "/v1.0/savePopupMasterList2")
	public ApiResult<String> savePopupMasterList2(@RequestBody KpDailyUnloadReqDto dto) {
		return ApiResult.createResult(kpDailyUnloadService.savePopupMasterList2(dto));
	}

	/**
	 * @description : 데일리 생산성 하역 지표 관리 - 분류피킹 제외 대상 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2026.01.29 JiHoPark 생성 </pre>
	 */
	@Operation(summary = "데일리 생산성 하역 지표 관리 - 분류피킹 제외 대상 저장", description = "데일리 생산성 하역 지표 관리 - 분류피킹 제외 대상 저장")
	@PostMapping(value = "/v1.0/savePopupMasterList3")
	public ApiResult<String> savePopupMasterList3(@RequestBody KpDailyUnloadReqDto dto) {
		return ApiResult.createResult(kpDailyUnloadService.savePopupMasterList3(dto));
	}

}
