package cjfw.wms.st.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StDailyInoutCloseReqDto;
import cjfw.wms.st.dto.StDailyInoutCloseResDto;
import cjfw.wms.st.service.StDailyInoutCloseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.07.22
 * @description : 수불마감정보 기능을 구현한 Controller Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.22        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Tag(name = "기준정보 > 센터기준정보 > 수불마감정보", description = "수불마감정보 목록 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/dailyInoutClose")
public class StDailyInoutCloseController {

	private final StDailyInoutCloseService stDailyInoutCloseService;

	/**
	 * 
	 * @description : 수불마감정보 목록 검색 조회
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.22        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "수불마감정보 목록 조회", description = "수불마감정보 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<StDailyInoutCloseResDto>> getMasterList(@RequestBody StDailyInoutCloseReqDto dto) {
		return ApiResult.createResult(stDailyInoutCloseService.getMasterList(dto));
	}
		
	/**
	 * @description : 수불마감정보 목록 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.22        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "수불마감정보 목록 저장", description = "수불마감정보 목록 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<Object> saveMasterList(@RequestBody List<StDailyInoutCloseReqDto> dto) {
		return ApiResult.createResult(stDailyInoutCloseService.saveMasterList(dto));
	}
	
	/**
	 * @description : 수불마감정보 수불 일괄 마감
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.22        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "수불마감정보 수불 일괄 마감", description = "수불마감정보 목록 저장")
	@PostMapping(value = "/v1.0/saveBatchClose")
	public ApiResult<Object> saveBatchClose(@RequestBody StDailyInoutCloseReqDto dto) {
		return ApiResult.createResult(stDailyInoutCloseService.saveBatchClose(dto));
	}
}