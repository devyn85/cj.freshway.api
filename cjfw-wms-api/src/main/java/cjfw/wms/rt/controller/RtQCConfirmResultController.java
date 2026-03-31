package cjfw.wms.rt.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.rt.dto.RtQCConfirmResultReqDto;
import cjfw.wms.rt.dto.RtQCConfirmResultResDto;
import cjfw.wms.rt.service.RtQCConfirmResultService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.07.21
 * @description : 반품판정현황 Controller Class
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.21 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Tag(name = "RtQCConfirmResultController API", description = "RtQCConfirmResultController")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/rt/qcConfirmResult")
public class RtQCConfirmResultController {
	private final RtQCConfirmResultService rtQCConfirmResultService;

	/**
	 * @description : 반품판정현황 조회 List Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.21 KimDongHyeon (tirran123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "반품판정현황 조회 List", description = "반품판정현황 조회 List")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<RtQCConfirmResultResDto>> getMasterList(@RequestBody RtQCConfirmResultReqDto reqDto) {
		return ApiResult.createResult(rtQCConfirmResultService.getMasterList(reqDto));
	}

	/**
	 * @description : 반품판정현황 클레임사유 (세)  조회 List Method
	 * @issues :
	 *
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.21 KimDongHyeon (tirran123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "클레임사유  조회 List", description = "반품판정현황 조회 List")
	@PostMapping(value = "/v1.0/getClaimTypeList")
	public ApiResult<List<Map>> getClaimTypeList(@RequestBody RtQCConfirmResultReqDto reqDto) {
		return ApiResult.createResult(rtQCConfirmResultService.getClaimTypeList(reqDto));
	}

	/**
	 * @description : 반품판정현황 클레임사유 (소)  조회 List Method
	 * @issues :
	 *
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.21 KimDongHyeon (tirran123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "클레임사유  조회 List", description = "반품판정현황 조회 List")
	@PostMapping(value = "/v1.0/getClaimTypeList2")
	public ApiResult<List<Map>> getClaimTypeList2(@RequestBody RtQCConfirmResultReqDto reqDto) {
		return ApiResult.createResult(rtQCConfirmResultService.getClaimTypeList2(reqDto));
	}
}
