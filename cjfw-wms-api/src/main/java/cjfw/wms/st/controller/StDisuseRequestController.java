package cjfw.wms.st.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StDisuseRequestApprovalResDto;
import cjfw.wms.st.dto.StDisuseRequestProcessResDto;
import cjfw.wms.st.dto.StDisuseRequestReqDto;
import cjfw.wms.st.dto.StDisuseRequestResDto;
import cjfw.wms.st.service.StDisuseRequestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KwonJungYun (jungyun8667@cj.net)
 * @date : 2025.07.01
 * @description : 외부비축폐기요청 Controller Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.01 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
 */
@Tag(name = "재고 > 재고조정 > 외부비축폐기요청", description = "외부비축폐기요청 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/disusereqeust")
public class StDisuseRequestController {

	private final StDisuseRequestService stDisuseRequestService;

	/**
	 * @description : 외부비축폐기요청 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.01 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
	 */
	@Operation(summary = "외부비축폐기요청 목록 조회", description = "외부비축폐기요청 목록 조회")
	@PostMapping(value = "/v1.0/getDisuseRequestList")
	public ApiResult<List<StDisuseRequestResDto>> getDisuseRequestList(@Valid @RequestBody StDisuseRequestReqDto dto) {
		return ApiResult.createResult(stDisuseRequestService.getDisuseRequestList(dto));
	}

	/**
	 * @description : 외부비축폐기요청처리 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.01 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
	 */
	@Operation(summary = "외부비축폐기요청 처리목록 조회", description = "외부비축폐기요청 처리목록 조회")
	@PostMapping(value = "/v1.0/getDisuseProcessList")
	public ApiResult<List<StDisuseRequestProcessResDto>> getDisuseProcessList(@Valid @RequestBody StDisuseRequestReqDto dto) {
		return ApiResult.createResult(stDisuseRequestService.getDisuseProcessList(dto));
	}

	/**
	 * @description : 외부비축폐기요청결재 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.10 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
	 */
	@Operation(summary = "외부비축폐기요청 결재목록 조회", description = "외부비축폐기요청 결재목록 조회")
	@PostMapping(value = "/v1.0/getDisuseApprovalList")
	public ApiResult<List<StDisuseRequestApprovalResDto>> getDisuseApprovalList(@Valid @RequestBody StDisuseRequestReqDto dto) {
		return ApiResult.createResult(stDisuseRequestService.getDisuseApprovalList(dto));
	}

	/**
	 * @description : 외부비축폐기요청처리 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.10 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
	 */
	@Operation(summary = "외부비축폐기요청 저장", description = "외부비축폐기요청 저장")
	@PostMapping(value = "/v1.0/saveDisuseRequestList")
	public ApiResult<List<StDisuseRequestResDto>> saveDisuseRequestList(@RequestBody StDisuseRequestReqDto stDisuseRequestReqDto) throws Exception {
		return ApiResult.createResult(stDisuseRequestService.saveDisuseRequestList(stDisuseRequestReqDto));
	}


	/**
	 * @description : 외부비축폐기요청 결재
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.10 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
	 */
	@Operation(summary = "외부비축폐기요청 결재", description = "외부비축폐기요청 결재")
	@PostMapping(value = "/v1.0/saveDisuseApproval")
	public ApiResult<String> saveDisuseApprovalList(@RequestBody StDisuseRequestReqDto stDisuseRequestReqDto) throws Exception {
		return ApiResult.createResult(stDisuseRequestService.saveDisuseApproval(stDisuseRequestReqDto));
	}

	/**
	 * @description : 외부비축폐기요청처리 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.10 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
	 */
	@Operation(summary = "외부비축폐기요청처리 저장", description = "외부비축폐기요청 저장")
	@PostMapping(value = "/v1.0/saveDisuseProcessList")
	public ApiResult<String> saveDisuseProcessList(@RequestBody StDisuseRequestReqDto reqDto) throws Exception {
		return ApiResult.createResult(stDisuseRequestService.saveDisuseProcess(reqDto));
	}


	/**
	 * @description : 외부비축폐기결재 삭제
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.10 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
	 */
	@Operation(summary = "외부비축폐기결재 삭제", description = "외부비축폐기결재 삭제")
	@PostMapping(value = "/v1.0/cancelDisuseApproval")
	public ApiResult<String> cancelDisuseApproval(@RequestBody StDisuseRequestReqDto reqDto) throws Exception {
		return ApiResult.createResult(stDisuseRequestService.cancelDisuseApproval(reqDto));
	}

	/**
	 * @description : 외부비축폐기처리 삭제
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.10 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
	 */
	@Operation(summary = "외부비축폐기처리 삭제", description = "외부비축폐기처리 삭제")
	@PostMapping(value = "/v1.0/cancelDisuseProcessList")
	public ApiResult<String> cancelDisuseProcessList(@RequestBody StDisuseRequestReqDto reqDto) throws Exception {
		return ApiResult.createResult(stDisuseRequestService.cancelDisuseProcessList(reqDto),"MSG.COM.SUC.003"); // 저장되었습니다. => UI에서 CmLoopTranPopup 사용 시 처리메세지 표시
	}


}