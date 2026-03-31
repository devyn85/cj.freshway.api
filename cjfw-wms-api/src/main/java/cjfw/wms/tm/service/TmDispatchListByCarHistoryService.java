package cjfw.wms.tm.service;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.wms.tm.dto.TmDispatchListByCarHistoryReqDto;
import cjfw.wms.tm.dto.TmDispatchListByCarHistoryResDto;
import cjfw.wms.tm.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : System
 * @date : 2025.01.15
 * @description : 배차목록(차량 변경내역) 조회 서비스
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.01.15 System      생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmDispatchListByCarHistoryService {

    private transient static final String SERVICEID_PREFIX = "tmDispatchListByCarHistoryService.";

    private final CommonDao commonDao;

    /**
     * @description : 배차목록(차량 변경내역) 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.01.15 System      생성 </pre>
     */
    public Page<TmDispatchListByCarHistoryResDto> getDispatchListByCarHistory(TmDispatchListByCarHistoryReqDto reqDto) {
        // 페이지네이션 유틸리티를 사용하여 Page 객체 생성
        Page<TmDispatchListByCarHistoryResDto> page = PaginationUtil.createPage(
            reqDto.getPageNum(), 
            reqDto.getListCount()
        );

        return commonDao.selectPageList(SERVICEID_PREFIX + "getDispatchListByCarHistory", reqDto, page);
    }

}
