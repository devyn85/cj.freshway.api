/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.comfunc.func.paging.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.comfunc.func.paging.dto.SamplePagingGetReqDto;
import cjfw.wms.comfunc.func.paging.dto.SamplePagingGetResDto;
import cjfw.wms.comfunc.func.paging.service.SamplePagingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("comfunc/func/paging")
public class SamplePagingController {

    private final SamplePagingService samplePagingService;

    /**
     * 네비게이션 페이징 데이터 조회<br>
     */
    @GetMapping(value = "/navi/list")
    public ApiResult<Page<SamplePagingGetResDto>> naviList(SamplePagingGetReqDto sampleReqDto, Page page) {
        return ApiResult.createResult(samplePagingService.getNaviPagingList(sampleReqDto, page));
    }

    /**
     * 스크롤 페이징 데이터 조회<br>
     */
    @GetMapping(value = "/scroll/list")
    public ApiResult<Page<SamplePagingGetResDto>> scrollList(SamplePagingGetReqDto sampleReqDto, Page page) {
        return ApiResult.createResult(samplePagingService.getScrollPagingList(sampleReqDto, page));
    }
}

/**
 * LIST_COUNT: 10
 * START_ROW: 10
 * END_ROW: 20
 *
 * FROM_DT: "2021-05-17"
 * THRU_DT: "2022-05-17"
 */
