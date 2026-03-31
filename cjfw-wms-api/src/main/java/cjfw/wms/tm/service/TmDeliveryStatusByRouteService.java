package cjfw.wms.tm.service;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.wms.tm.dto.TmDeliveryStatusByRouteReqDto;
import cjfw.wms.tm.dto.TmDeliveryStatusByRouteResDto;
import cjfw.wms.tm.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : System
 * @date : 2025.01.XX
 * @description : 배송현황(경로별) 조회 서비스
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.01.XX System      생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmDeliveryStatusByRouteService {

    private transient static final String SERVICEID_PREFIX = "tmDeliveryStatusByRouteService.";

    private final CommonDao commonDao;

    /**
     * @description : 배송현황(경로별) 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.01.XX System      생성 </pre>
     */
    public Page<TmDeliveryStatusByRouteResDto> getDeliveryStatusByRoute(TmDeliveryStatusByRouteReqDto reqDto) {
        // 페이지네이션 유틸리티를 사용하여 Page 객체 생성
        Page<TmDeliveryStatusByRouteResDto> page = PaginationUtil.createPage(
            reqDto.getPageNum(), 
            reqDto.getListCount()
        );

        Page<TmDeliveryStatusByRouteResDto> result = commonDao.selectPageList(SERVICEID_PREFIX + "getDeliveryStatusByRoute", reqDto, page);
        return result;
    }
}

