package cjfw.wms.batch.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.batch.dto.BatchHistoryReqDto;
import cjfw.wms.batch.dto.BatchHistoryResDto;
import cjfw.wms.batch.service.BatchHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : yewon.kim (yewon.kim9@cj.net)
 * @date : 2025.07.08
 * @description : 배치 이력 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.08 yewon.kim (yewon.kim9@cj.net) 생성 </pre>
 */
@Tag(name = "배치 > 배치관리 > 배치 이력", description = "배치이력")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/batch/batchHistory")
public class BatchHistoryController {

	private final BatchHistoryService batchHistoryService;

	/**
	 * @description : 배치 JOB 이력 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.08 yewon.kim (yewon.kim9@cj.net) 생성 </pre>
	 */
	@Operation(summary = "배치 JOB 이력", description = "배치 JOB 이력")
	@GetMapping(value = "/v1.0/getBatchJobHistList")
	public ApiResult<List<BatchHistoryResDto>> getBatchJobHistList(@Valid BatchHistoryReqDto dto) {
		return ApiResult.createResult(batchHistoryService.getBatchJobHistList(dto));
	}

    /**
     * @description : 배치 JOB 상세내역 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.12.13 yewon.kim (yewon.kim9@cj.net) 생성 </pre>
     */
    @Operation(summary = "배치 JOB 상세 내역 조회", description = "배치 JOB 상세 내역 조회")
    @GetMapping(value = "/v1.0/getBatchJobDetailHistList")
    public ApiResult<List<BatchHistoryResDto>> getBatchJobDetailHistList(@Valid BatchHistoryReqDto dto) {
        return ApiResult.createResult(batchHistoryService.getBatchJobDetailHistList(dto));
    }

	/**
	 * @description : 배치 PARAM 이력 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.08 yewon.kim (yewon.kim9@cj.net) 생성 </pre>
	 */
	@Operation(summary = "배치 PARAM 이력", description = "배치 PARAM 이력")
	@GetMapping(value = "/v1.0/getBatchParamHistList")
	public ApiResult<List<BatchHistoryResDto>> getBatchParamList(@Valid BatchHistoryReqDto dto) {
		return ApiResult.createResult(batchHistoryService.getBatchParamHistList(dto));
	}

	/**
	 * @description : 배치 PARAM 이력 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.08 yewon.kim (yewon.kim9@cj.net) 생성 </pre>
	 */
	@Operation(summary = "배치 STEP 이력", description = "배치 STEP 이력")
	@GetMapping(value = "/v1.0/getBatchStepHistList")
	public ApiResult<List<BatchHistoryResDto>> getBatchStepList(@Valid BatchHistoryReqDto dto) {
		return ApiResult.createResult(batchHistoryService.getBatchStepHistList(dto));
	}
}