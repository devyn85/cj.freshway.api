package cjfw.wms.st.controller;

import java.rmi.RemoteException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StAdjustmentRequestExDCElectApprovalDto;
import cjfw.wms.st.dto.StAdjustmentRequestExDCReqDto;
import cjfw.wms.st.dto.StAdjustmentRequestExDCResDto;
import cjfw.wms.st.service.StAdjustmentRequestExDCService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2025.08.26
 * @description : 외부비축재고조정 Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.26 JiHoPark  생성 </pre>
 */
@Tag(name = "StAdjustmentRequestExDC", description = "외부비축재고조정(재고 > 재고조정 > 외부비축재고조정)")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/adjustmentreqeustexdc")
public class StAdjustmentRequestExDCController {
	private final StAdjustmentRequestExDCService stAdjustmentRequestExDCService;

	/**
	 * @description : 외부비축재고조정 - 재고조정 요청 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.27 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "외부비축재고조정 - 재고조정 요청 목록 조회", description = "외부비축재고조정 - 재고조정 요청 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<StAdjustmentRequestExDCResDto>> getMasterList(@Valid @RequestBody StAdjustmentRequestExDCReqDto dto) {
		return ApiResult.createResult(stAdjustmentRequestExDCService.getMasterList(dto));
	}

	/**
	 * @description : 외부비축재고조정 - 요청처리 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.28 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "외부비축재고조정 - 요청처리 목록 조회", description = "외부비축재고조정 - 요청처리 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList2")
	public ApiResult<List<StAdjustmentRequestExDCResDto>> getMasterList2(@Valid @RequestBody StAdjustmentRequestExDCReqDto dto) {
		return ApiResult.createResult(stAdjustmentRequestExDCService.getMasterList2(dto));
	}

	/**
	 * @description : 외부비축재고조정 - 재고조정 결재 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.28 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "외부비축재고조정 - 재고조정 결재 목록 조회", description = "외부비축재고조정 - 재고조정 결재 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList3")
	public ApiResult<List<StAdjustmentRequestExDCElectApprovalDto>> getMasterList3(@Valid @RequestBody StAdjustmentRequestExDCReqDto dto) {
		return ApiResult.createResult(stAdjustmentRequestExDCService.getMasterList3(dto));
	}

	/**
	 * @description : 외부비축재고조정 - 재고조정 처리 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.28 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "외부비축재고조정 - 재고조정 처리 조회", description = "외부비축재고조정 - 재고조정 처리 조회")
	@PostMapping(value = "/v1.0/getMasterList4")
	public ApiResult<List<StAdjustmentRequestExDCResDto>> getMasterList4(@Valid @RequestBody StAdjustmentRequestExDCReqDto dto) {
		return ApiResult.createResult(stAdjustmentRequestExDCService.getMasterList4(dto));
	}

	/**
	 * @description : 외부비축재고조정 - 재고조정 결재 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.29 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "외부비축재고조정 - 재고조정 요청 저장", description = "외부비축재고조정 - 재고조정 요청 저장")
	@PostMapping(value = "/v1.0/saveMasterList1")
	public  ApiResult<List<StAdjustmentRequestExDCResDto>> saveMasterList1(@RequestBody StAdjustmentRequestExDCReqDto dto) {
		return ApiResult.createResult(stAdjustmentRequestExDCService.saveMasterList1(dto));
	}

	/**
	 * @description : 외부비축재고조정 - 재고조정 결재 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.29 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "외부비축재고조정 - 재고조정 결재 저장", description = "외부비축재고조정 - 재고조정 결재 저장")
	@PostMapping(value = "/v1.0/saveMasterList3")
	public ApiResult<String> saveMasterList3(@RequestBody StAdjustmentRequestExDCReqDto dto) {
		return ApiResult.createResult(stAdjustmentRequestExDCService.saveMasterList3(dto));
	}

	/**
	 * @description : 외부비축재고조정 - 재고조정 결재 전자결재
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.29 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "외부비축재고조정 - 재고조정 결재 전자결재", description = "외부비축재고조정 - 재고조정 결재 전자결재")
	@PostMapping(value = "/v1.0/saveElectApproval")
	public ApiResult<String> saveElectApproval(@RequestBody StAdjustmentRequestExDCReqDto dto) {
		return ApiResult.createResult(stAdjustmentRequestExDCService.saveElectApproval(dto));
	}

	/**
	 * @description : 외부비축재고조정 - 재고조정 결재 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.29 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "외부비축재고조정 - 재고조정 처리 저장", description = "외부비축재고조정 - 재고조정 처리 저장")
	@PostMapping(value = "/v1.0/saveMasterList4")
	public ApiResult<String> saveMasterList4(@RequestBody StAdjustmentRequestExDCReqDto dto) {
		return ApiResult.createResult(stAdjustmentRequestExDCService.saveMasterList4(dto));
	}

	/**
     * @throws RemoteException
     * @description : 전자결재 최종결재완료된 재고 자동처리
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.05 JiHoPark  생성 </pre>
	 */
    @Operation(summary = "전자결재 최종결재완료된 재고 자동처리", description = "전자결재 최종결재완료된 재고 자동처리")
    @GetMapping(value = "/v1.0/saveStockAutoHandle")
    public ApiResult<String> saveStockAutoHandle(@RequestParam("approvalreqdt") String approvalreqdt,
            									 @RequestParam("approvalreqno") String approvalreqno,
            									 @RequestParam("apprstatus") String apprstatus,
            									 @RequestParam("docId") String docId,
            									 @RequestParam("approveId") String approveId,
            									 @RequestParam("approveNm") String approveNm) throws RemoteException {
        return ApiResult.createResult(stAdjustmentRequestExDCService.saveStockAutoHandle(approvalreqdt, approvalreqno, apprstatus, docId, approveId, approveNm));
    }
}
