package cjfw.wms.st.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StAdjustmentCommCdResDto;
import cjfw.wms.st.dto.StAdjustmentRequestElectApprovalDto;
import cjfw.wms.st.dto.StAdjustmentRequestReqDto;
import cjfw.wms.st.dto.StAdjustmentRequestResDto;
import cjfw.wms.st.service.StAdjustmentRequestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2025.10.13
 * @description : 재고조정 요청/처리 Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.13 JiHoPark  생성 </pre>
 */
@Tag(name = "StAdjustmentRequest", description = "재고조정 요청/처리(재고 > 재고조정 > 재고조정 요청/처리)")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/adjustmentRequest")
public class StAdjustmentRequestController {
	private final StAdjustmentRequestService stAdjustmentRequestService;

	/**
	 * @description : 일괄재고조정 - zone 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.13 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "재고조정 요청/처리 - zone 목록 조회", description = "일괄재고조정 - zone 목록 조회")
	@PostMapping(value = "/v1.0/getZoneList")
	public ApiResult<List<StAdjustmentCommCdResDto>> getZoneList() {
		return ApiResult.createResult(stAdjustmentRequestService.getZoneList());
	}

	/**
	 * @description : 재고조정 요청/처리 - 재고조정 요청 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.13 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "재고조정 요청/처리 - 재고조정 요청 목록 조회", description = "재고조정 요청/처리 - 재고조정 요청 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<StAdjustmentRequestResDto>> getMasterList(@Valid @RequestBody StAdjustmentRequestReqDto dto) {
		return ApiResult.createResult(stAdjustmentRequestService.getMasterList(dto));
	}

	/**
	 * @description : 재고조정 요청/처리 - 재고조정 결재 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.16 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "재고조정 요청/처리 - 재고조정 결재 목록 조회", description = "재고조정 요청/처리 - 재고조정 결재 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList3")
	public ApiResult<List<StAdjustmentRequestElectApprovalDto>> getMasterList3(@Valid @RequestBody StAdjustmentRequestReqDto dto) {
		return ApiResult.createResult(stAdjustmentRequestService.getMasterList3(dto));
	}

	/**
	 * @description : 재고조정 요청/처리 - 재고조정 처리 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.15 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "재고조정 요청/처리 - 재고조정 처리 목록 조회", description = "재고조정 요청/처리 - 재고조정 처리 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList4")
	public ApiResult<List<StAdjustmentRequestResDto>> getMasterList4(@Valid @RequestBody StAdjustmentRequestReqDto dto) {
		return ApiResult.createResult(stAdjustmentRequestService.getMasterList4(dto));
	}

	/**
	 * @description : 재고조정 요청/처리 - 재고조정 요청 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.14 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "재고조정 요청/처리 - 재고조정 요청 저장", description = "재고조정 요청/처리 - 재고조정 요청 저장")
	@PostMapping(value = "/v1.0/saveMasterList1")
	public  ApiResult<List<StAdjustmentRequestResDto>> saveMasterList1(@RequestBody StAdjustmentRequestReqDto dto) {
		return ApiResult.createResult(stAdjustmentRequestService.saveMasterList1(dto));
	}

	/**
	 * @description : 재고조정 요청/처리 - 재고조정 결재 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.16 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "재고조정 요청/처리 - 재고조정 결재 저장", description = "재고조정 요청/처리 - 재고조정 결재 저장")
	@PostMapping(value = "/v1.0/saveMasterList3")
	public ApiResult<String> saveMasterList3(@RequestBody StAdjustmentRequestReqDto dto) {
		return ApiResult.createResult(stAdjustmentRequestService.saveMasterList3(dto));
	}

	/**
	 * @description : 외부비축재고조정 - 재고조정 결재 전자결재
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.16 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "재고조정 요청/처리 - 재고조정 결재 전자결재", description = "재고조정 요청/처리 - 재고조정 결재 전자결재")
	@PostMapping(value = "/v1.0/saveElectApproval")
	public ApiResult<String> saveElectApproval(@RequestBody StAdjustmentRequestReqDto dto) {
		return ApiResult.createResult(stAdjustmentRequestService.saveElectApproval(dto));
	}

	/**
	 * @description : 재고조정 요청/처리 - 재고조정 처리 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.15 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "재고조정 요청/처리 - 재고조정 처리 저장", description = "재고조정 요청/처리 - 재고조정 처리 저장")
	@PostMapping(value = "/v1.0/saveMasterList4")
	public ApiResult<String> saveMasterList4(@RequestBody StAdjustmentRequestReqDto dto) {
		return ApiResult.createResult(stAdjustmentRequestService.saveMasterList4(dto));
	}
}
