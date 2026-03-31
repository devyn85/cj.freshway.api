/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.comfunc.func.popup.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.comfunc.func.popup.dto.SamplePopupEmpGetReqDto;
import cjfw.wms.comfunc.func.popup.dto.SamplePopupEmpGetResDto;
import cjfw.wms.comfunc.func.popup.service.SamplePopupEmpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 공통팝업 사원조회 컨트롤러
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("comfunc/func/popup/emp")
public class SamplePopupEmpController {

	private final SamplePopupEmpService service;

	/**
	 * 사원 데이터 조회
	 */
	@GetMapping(value="getData")
	public ApiResult<List<SamplePopupEmpGetResDto>> popupPageDapt(SamplePopupEmpGetReqDto samplePopupEmpGetReqDto) {
		return ApiResult.createResult(service.getEmpList(samplePopupEmpGetReqDto));
	}
}