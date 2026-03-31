package cjfw.wms.batch.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.batch.dto.BatchMngReqDto;
import cjfw.wms.batch.dto.BatchMngResDto;
import cjfw.wms.batch.service.BatchPopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : yewon.kim (yewon.kim9@cj.net)
 * @date : 2025.07.09
 * @description : 배치 등록/수정 > 팝업 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.09 yewon.kim (yewon.kim9@cj.net) 생성 </pre>
 */
@Tag(name = "배치 > 배치관리 > 배치 등록/수정 > 팝업", description = "배치 팝업")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/batch/batchPop")
public class BatchPopController {

	private final BatchPopService batchPopService;

	/**
	 * @description : 배치 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.09 yewon.kim (yewon.kim9@cj.net) 생성 </pre>
	 */
	@Operation(summary = "배치 인수 설정 팝업", description = "배치 인수 설정 팝업")
	@GetMapping(value = "/v1.0/getBatchParamSetList")
	public ApiResult<List<BatchMngResDto>> getBatchPopList(@Valid BatchMngReqDto dto) {
		return ApiResult.createResult(batchPopService.getBatchParamSetList(dto));
	}

	/**
	 * @description : 배치 인수 저장.
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.09 yewon.kim (yewon.kim9@cj.net)   생성
	 */
	@Operation(summary = "배치 인수 저장", description = "배치 인수 저장")
	@PostMapping(value = "/v1.0/saveBatchParamSetList")
	public ApiResult<String> saveBatchParamSetList(@RequestBody @Valid List<BatchMngReqDto> dto) {
		return ApiResult.createResult(batchPopService.saveBatchParamSetList(dto));
	}
}