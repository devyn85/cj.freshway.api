package cjfw.wms.rt.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.rt.dto.RtReturnOutExDCReqDto;
import cjfw.wms.rt.dto.RtReturnOutExDCResDto;
import cjfw.wms.rt.service.RtReturnOutExDCService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.06.27 
 * @description : 외부비축협력사반품지시 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.27    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Tag(name = "반품 > 반출처리 > 외부비축협력사반품지시", description = "외부비축협력사반품지시 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/rt/returnoutexdc")
public class RtReturnOutExDCController {

	private final RtReturnOutExDCService rtReturnOutExDCService;

	/**
	 * @description : 외부비축소비기한변경 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.15    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	@Operation(summary = "외부비축소비기한변경 목록 조회", description = "외부비축소비기한변경 목록 조회")
	@GetMapping(value = "/v1.0/getDataHeaderList")
	public ApiResult<List<RtReturnOutExDCResDto>> getDataHeaderList(@Valid RtReturnOutExDCReqDto dto) {
		return ApiResult.createResult(rtReturnOutExDCService.getDataHeaderList(dto));
	}
	
	/**
	 * @throws Exception
	 * @description : 외부비축소비기한변경 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.15    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	@Operation(summary = "외부비축소비기한변경 저장", description = "외부비축소비기한변경 저장")
	@PostMapping(value = "/v1.0/saveConfirm")
	public ApiResult<String> saveConfirm(@RequestBody RtReturnOutExDCReqDto rtReturnOutExDCReqDto) throws Exception {
		return ApiResult.createResult(rtReturnOutExDCService.saveConfirm(rtReturnOutExDCReqDto));
	}

}