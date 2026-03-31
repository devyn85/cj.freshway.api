package cjfw.wms.st.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StAdjustmentRequestExDCReqDto;
import cjfw.wms.st.dto.StDisuseRequestExDCElectApprovalDto;
import cjfw.wms.st.dto.StDisuseRequestExDCReqDto;
import cjfw.wms.st.dto.StDisuseRequestExDCResDto;
import cjfw.wms.st.service.StDisuseRequestExDCService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2025.08.26
 * @description : 외부비축재고폐기처리 Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.26 JiHoPark  생성 </pre>
 */
@Tag(name = "StDisuseRequestExDC", description = "외부비축재고폐기처리(재고 > 재고조정 > 외부비축재고폐기처리)")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/disuserequestexdc")
public class StDisuseRequestExDCController {
	private final StDisuseRequestExDCService stDisuseRequestExDCService;

	/**
	 * @description : 외부비축재고폐기처리 - 폐기 요청 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.27 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "외부비축재고폐기처리 - 폐기 요청 목록 조회", description = "외부비축재고폐기처리 - 폐기 요청 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<StDisuseRequestExDCResDto>> getMasterList(@Valid @RequestBody StDisuseRequestExDCReqDto dto) {
		return ApiResult.createResult(stDisuseRequestExDCService.getMasterList(dto));
	}

	/**
	 * @description : 외부비축재고폐기처리 - 폐기 결재 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.28 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "외부비축재고폐기처리 - 폐기 결재 목록 조회", description = "외부비축재고폐기처리 - 폐기 결재 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList3")
	public ApiResult<List<StDisuseRequestExDCElectApprovalDto>> getMasterList3(@Valid @RequestBody StDisuseRequestExDCReqDto dto) {
		return ApiResult.createResult(stDisuseRequestExDCService.getMasterList3(dto));
	}

	/**
	 * @description : 외부비축재고폐기처리 - 폐기 처리 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.28 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "외부비축재고폐기처리 - 폐기 처리 목록 조회", description = "외부비축재고폐기처리 - 폐기 처리 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList4")
	public ApiResult<List<StDisuseRequestExDCResDto>> getMasterList4(@Valid @RequestBody StDisuseRequestExDCReqDto dto) {
		return ApiResult.createResult(stDisuseRequestExDCService.getMasterList4(dto));
	}

	/**
	 * @description : 외부비축재고폐기처리 - 폐기 요청 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.29 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "외부비축재고폐기처리 - 폐기 요청 저장", description = "외부비축재고폐기처리 - 폐기 요청 저장")
	@PostMapping(value = "/v1.0/saveMasterList1")
	public  ApiResult<List<StDisuseRequestExDCResDto>> saveMasterList1(@RequestBody StDisuseRequestExDCReqDto dto) {
		return ApiResult.createResult(stDisuseRequestExDCService.saveMasterList1(dto));
	}

	/**
	 * @description : 외부비축재고폐기처리 - 폐기 결재 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.29 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "외부비축재고폐기처리 - 폐기 결재 저장", description = "외부비축재고폐기처리 - 폐기 결재 저장")
	@PostMapping(value = "/v1.0/saveMasterList3")
	public ApiResult<String> saveMasterList3(@RequestBody StAdjustmentRequestExDCReqDto dto) {
		return ApiResult.createResult(stDisuseRequestExDCService.saveMasterList3(dto));
	}

	/**
	 * @description : 외부비축재고폐기처리 - 폐기 결재 전자결재
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.29 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "외부비축재고폐기처리 - 폐기 결재 전자결재", description = "외부비축재고폐기처리 - 폐기 결재 전자결재")
	@PostMapping(value = "/v1.0/saveElectApproval")
	public ApiResult<String> saveElectApproval(@RequestBody StAdjustmentRequestExDCReqDto dto) {
		return ApiResult.createResult(stDisuseRequestExDCService.saveElectApproval(dto));
	}

	/**
	 * @description : 외부비축재고폐기처리 - 폐기 처리 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.29 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "외부비축재고폐기처리 - 폐기 처리 저장", description = "외부비축재고폐기처리 - 폐기 처리 저장")
	@PostMapping(value = "/v1.0/saveMasterList4")
	public ApiResult<String> saveMasterList4(@RequestBody StDisuseRequestExDCReqDto dto) {
		return ApiResult.createResult(stDisuseRequestExDCService.saveMasterList4(dto));
	}

}
