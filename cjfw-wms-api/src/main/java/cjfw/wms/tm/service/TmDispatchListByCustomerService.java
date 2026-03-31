package cjfw.wms.tm.service;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.wms.tm.dto.TmDispatchListByCustomerReqDto;
import cjfw.wms.tm.dto.TmDispatchListByCustomerResDto;
import cjfw.wms.tm.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : OhEunbeom
 * @date : 2025.09.10
 * @description : 배차목록(거래처별) 조회 서비스
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.10 OhEunbeom      생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmDispatchListByCustomerService {

    private transient static final String SERVICEID_PREFIX = "tmDispatchListByCustomerService.";

    private final CommonDao commonDao;

    /**
     * @description : 차량 모니터링 차량마커 조회 (검색조건)
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.23 OhEunbeom      생성 </pre>
     */
    public Page<TmDispatchListByCustomerResDto> getDispatchListByCustomer(TmDispatchListByCustomerReqDto reqDto) {
        // 페이지네이션 유틸리티를 사용하여 Page 객체 생성
        Page<TmDispatchListByCustomerResDto> page = PaginationUtil.createPage(
            reqDto.getPageNum(), 
            reqDto.getListCount()
        );

        return commonDao.selectPageList(SERVICEID_PREFIX + "getDispatchListByCustomer", reqDto, page);
    }

}
