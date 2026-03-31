package cjfw.wms.wd.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.wd.dto.WdShipmentETCEXDCElectApprovalDto;
import cjfw.wms.wd.dto.WdShipmentETCEXDCReqDto;
import cjfw.wms.wd.dto.WdShipmentETCEXDCResDto;
import cjfw.wms.wd.service.WdShipmentETCEXDCService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2025.09.11
 * @description : 외부센터매각출고처리 Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.11 JiHoPark  생성 </pre>
 */
@Tag(name = "WdShipmentETCEXDC", description = "외부센터매각출고처리(출고 > 기타출고 > 외부센터매각출고처리)")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/wd/shipmentetcexdc")
public class WdShipmentETCEXDCController {
	private final WdShipmentETCEXDCService wdShipmentETCEXDCService;

	/**
	 * @description : 외부센터매각출고처리 - 기타출고 요청 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.27 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "외부센터매각출고처리 - 기타출고 요청 목록 조회", description = "외부센터매각출고처리 - 기타출고 요청 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<WdShipmentETCEXDCResDto>> getMasterList(@Valid @RequestBody WdShipmentETCEXDCReqDto dto) {
		return ApiResult.createResult(wdShipmentETCEXDCService.getMasterList(dto));
	}

	/**
	 * @description : 외부센터매각출고처리 - 기타출고 요청 결과 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.28 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "외부센터매각출고처리 - 기타출고 요청 결과 목록 조회", description = "외부센터매각출고처리 - 기타출고 요청 결과 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList2")
	public ApiResult<List<WdShipmentETCEXDCResDto>> getMasterList2(@Valid @RequestBody WdShipmentETCEXDCReqDto dto) {
		return ApiResult.createResult(wdShipmentETCEXDCService.getMasterList2(dto));
	}

	/**
	 * @description : 외부센터매각출고처리 - 기타출고 결재 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.28 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "외부센터매각출고처리 - 기타출고 결재 목록 조회", description = "외부센터매각출고처리 - 기타출고 결재 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList3")
	public ApiResult<List<WdShipmentETCEXDCElectApprovalDto>> getMasterList3(@Valid @RequestBody WdShipmentETCEXDCReqDto dto) {
		return ApiResult.createResult(wdShipmentETCEXDCService.getMasterList3(dto));
	}

	/**
	 * @description : 외부센터매각출고처리 - 기타출고 처리 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.28 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "외부센터매각출고처리 - 기타출고 처리 조회", description = "외부센터매각출고처리 - 기타출고 처리 조회")
	@PostMapping(value = "/v1.0/getMasterList4")
	public ApiResult<List<WdShipmentETCEXDCResDto>> getMasterList4(@Valid @RequestBody WdShipmentETCEXDCReqDto dto) {
		return ApiResult.createResult(wdShipmentETCEXDCService.getMasterList4(dto));
	}

	/**
	 * @description : 외부센터매각출고처리 - 기타출고 요청 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.29 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "외부센터매각출고처리 - 기타출고 요청 저장", description = "외부센터매각출고처리 - 기타출고 요청 저장")
	@PostMapping(value = "/v1.0/saveMasterList1")
	public  ApiResult<List<WdShipmentETCEXDCResDto>> saveMasterList1(@RequestBody WdShipmentETCEXDCReqDto dto) {
		return ApiResult.createResult(wdShipmentETCEXDCService.saveMasterList1(dto));
	}

	/**
	 * @description : 외부센터매각출고처리 - 기타출고 결재 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.29 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "외부센터매각출고처리 - 기타출고 결재 저장", description = "외부센터매각출고처리 - 기타출고 결재 저장")
	@PostMapping(value = "/v1.0/saveMasterList3")
	public ApiResult<String> saveMasterList3(@RequestBody WdShipmentETCEXDCReqDto dto) {
		return ApiResult.createResult(wdShipmentETCEXDCService.saveMasterList3(dto));
	}

	/**
	 * @description : 외부센터매각출고처리 - 기타출고 결재 전자결재
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.29 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "외부센터매각출고처리 - 기타출고 결재 전자결재", description = "외부센터매각출고처리 - 기타출고 결재 전자결재")
	@PostMapping(value = "/v1.0/saveElectApproval")
	public ApiResult<String> saveElectApproval(@RequestBody WdShipmentETCEXDCReqDto dto) {
		return ApiResult.createResult(wdShipmentETCEXDCService.saveElectApproval(dto));
	}

	/**
	 * @description : 외부센터매각출고처리 - 기타출고 처리 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.29 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "외부센터매각출고처리 - 기타출고 처리 저장", description = "외부센터매각출고처리 - 기타출고 처리 저장")
	@PostMapping(value = "/v1.0/saveMasterList4")
	public ApiResult<String> saveMasterList4(@RequestBody WdShipmentETCEXDCReqDto dto) {
		return ApiResult.createResult(wdShipmentETCEXDCService.saveMasterList4(dto));
	}

	/**
	 * @description : 외부센터매각출고처리 - 기타출고 확정 처리
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.30 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "외부센터매각출고처리 - 기타출고 확정 처리", description = "외부센터매각출고처리 - 기타출고 확정 처리")
	@PostMapping(value = "/v1.0/confirmMasterList4")
	public ApiResult<String> confirmMasterList4(@RequestBody WdShipmentETCEXDCReqDto dto) {
		return ApiResult.createResult(wdShipmentETCEXDCService.confirmMasterList4(dto));
	}

}
