package cjfw.wms.tm.service;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.wms.tm.dto.TmDispatchListByCarReqDto;
import cjfw.wms.tm.dto.TmDispatchListByCarResDto;
import cjfw.wms.tm.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : OhEunbeom
 * @date : 2025.01.15
 * @description : 배차목록(차량별) 조회 서비스
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.01.15 OhEunbeom      생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmDispatchListByCarService {

    private transient static final String SERVICEID_PREFIX = "tmDispatchListByCarService.";

    private final CommonDao commonDao;

    /**
     * @description : 배차목록(차량별) 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.01.15 OhEunbeom      생성 </pre>
     */
    public Page<TmDispatchListByCarResDto> getDispatchListByCar(TmDispatchListByCarReqDto reqDto) {
        // 페이지네이션 유틸리티를 사용하여 Page 객체 생성
        Page<TmDispatchListByCarResDto> page = PaginationUtil.createPage(
            reqDto.getPageNum(), 
            reqDto.getListCount()
        );

        return commonDao.selectPageList(SERVICEID_PREFIX + "getDispatchListByCar", reqDto, page);
    }

}
