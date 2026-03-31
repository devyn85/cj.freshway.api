/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.comfunc.func.paging.service;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.wms.comfunc.func.paging.dto.SamplePagingGetReqDto;
import cjfw.wms.comfunc.func.paging.dto.SamplePagingGetResDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SamplePagingService {

    private final CommonDao commonDao;

    /**
     * 내비게이션 페이지 샘플 리스트
     */
    public Page<SamplePagingGetResDto> getNaviPagingList(SamplePagingGetReqDto sampleReqDto, Page page) {
        Page<SamplePagingGetResDto> result = commonDao.selectPageList("samplePagingService.getPagingList", sampleReqDto, page);
        return result;
    }

    /**
     * 스크롤 페이지 샘플 리스트
     */
    public Page<SamplePagingGetResDto> getScrollPagingList(SamplePagingGetReqDto sampleReqDto, Page page) {
        Page<SamplePagingGetResDto> result = commonDao.selectPageList("samplePagingService.getPagingList", sampleReqDto, page);
        return result;
    }
}
