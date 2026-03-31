/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.comfunc.func.timezone.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.comfunc.func.timezone.dto.SampleTimeZoneGetResDto;
import cjfw.wms.comfunc.func.timezone.service.SampleTimeZoneService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("comfunc/func/timezone")
public class SampleTimeZoneController {

    private final SampleTimeZoneService sampleTimeZoneService;

    /**
     * 타임존 샘플 데이터 조회<br>
     */
    @GetMapping(value = "/list")
	public ApiResult<List<SampleTimeZoneGetResDto>> naviList() {
		return ApiResult.createResult(sampleTimeZoneService.getList());
	}
}
