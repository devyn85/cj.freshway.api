package cjfw.wms.st.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StAdjustmentBatchReqDto;
import cjfw.wms.st.dto.StAdjustmentBatchResDto;
import cjfw.wms.st.dto.StAdjustmentCommCdResDto;
import cjfw.wms.st.service.StAdjustmentBatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2025.09.24
 * @description : 일괄재고조정 Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.24 JiHoPark  생성 </pre>
 */
@Tag(name = "StAdjustmentBatch", description = "일괄재고조정(재고 > 재고조정 > 일괄재고조정)")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/adjustmentbatch")
public class StAdjustmentBatchController {
	private final StAdjustmentBatchService stAdjustmentBatchService;

	/**
	 * @description : 일괄재고조정 - zone 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.25 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "일괄재고조정 - zone 목록 조회", description = "일괄재고조정 - zone 목록 조회")
	@PostMapping(value = "/v1.0/getZoneList")
	public ApiResult<List<StAdjustmentCommCdResDto>> getZoneList() {
		return ApiResult.createResult(stAdjustmentBatchService.getZoneList());
	}

	/**
	 * @description : 일괄재고조정 - 재고조정 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.24 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "일괄재고조정 - 재고조정 목록 조회", description = "일괄재고조정 - 재고조정 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<StAdjustmentBatchResDto>> getMasterList(@Valid @RequestBody StAdjustmentBatchReqDto dto) {
		return ApiResult.createResult(stAdjustmentBatchService.getMasterList(dto));
	}

	/**
	 * @description : 일괄재고조정 - 일괄재고조정 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.29 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "일괄재고조정 - 일괄재고조정 저장", description = "일괄재고조정 - 일괄재고조정 저장")
	@PostMapping(value = "/v1.0/saveMasterList1")
	public  ApiResult<List<StAdjustmentBatchResDto>> saveMasterList1(@RequestBody StAdjustmentBatchReqDto dto) {
		return ApiResult.createResult(stAdjustmentBatchService.saveMasterList1(dto));
	}

}
