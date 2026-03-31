package cjfw.wms.tm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.tm.dto.TmWorklogByCustomerDetailReqDto;
import cjfw.wms.tm.dto.TmWorklogByCustomerDetailResDto;
import cjfw.wms.tm.dto.TmWorklogByCustomerReqDto;
import cjfw.wms.tm.dto.TmWorklogByCustomerResDto;
import cjfw.wms.tm.service.TmWorklogByCustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KwonJungYun (jungyun8667@cj.net)
 * @date : 2025.06.23
 * @description : 배차작업로그(거래처별) Controller Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.23 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
 */
@Tag(name = "배송 > 배차현황 > 배차작업로그(거래처별)", description = "배차작업로그(거래처별)")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/tm/custlog")
public class TmWorkLogByCustomerController {

	private final TmWorklogByCustomerService tmWorkLogByCustomerService;

	/**
	 * @description : 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.23 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
	 */
	@Operation(summary = "배차작업로그(거래처별) 목록 조회", description = "배차작업로그(거래처별) 조회")
	@PostMapping(value = "/v1.0/getWorkLogByCustomerList")
	public ApiResult<List<TmWorklogByCustomerResDto>> getWorkLogByCustomerList(@Valid @RequestBody TmWorklogByCustomerReqDto tmWorkLogByCustomerReqDto) {
		return ApiResult.createResult(tmWorkLogByCustomerService.getWorklogByCustomerList(tmWorkLogByCustomerReqDto));
	}
	/**
	 * @description : 상세 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.23 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
	 */
	@Operation(summary = "배차작업로그(거래처별) 상세 목록 조회", description = "배차작업로그(거래처별) 상세 조회")
	@PostMapping(value = "/v1.0/getWorkLogByCustomerDetailList")
	public ApiResult<List<TmWorklogByCustomerDetailResDto>> getWorkLogByCustomerList(@Valid @RequestBody TmWorklogByCustomerDetailReqDto tmWorkLogByCustomerDetailReqDto) {
		return ApiResult.createResult(tmWorkLogByCustomerService.getWorklogByCustomerDetailList(tmWorkLogByCustomerDetailReqDto));
	}
}