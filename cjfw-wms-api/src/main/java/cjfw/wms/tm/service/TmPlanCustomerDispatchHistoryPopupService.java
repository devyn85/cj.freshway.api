package cjfw.wms.tm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.wms.tm.util.TmPlanCommon;
import cjfw.wms.tm.dto.TmPlanCustomerDispatchHistoryPopupReqDto;
import cjfw.wms.tm.dto.TmPlanCustomerDispatchHistoryPopupResDto;
import cjfw.wms.tm.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : OhEunbeom
 * @date : 2025.10.10
 * @description : 배차이력 조회 서비스
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.10 OhEunbeom      생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmPlanCustomerDispatchHistoryPopupService {

    private transient static final String SERVICEID_PREFIX = "cjfw.wms.tm.mapper.TmPlanCustomerDispatchHistoryPopupMapper.";

    private final CommonDao commonDao;

    /**
     * @description : 배차이력 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.10 OhEunbeom      생성 </pre>
     */
    public Page<TmPlanCustomerDispatchHistoryPopupResDto> getPlanCustomerDispatchHistoryPopup(TmPlanCustomerDispatchHistoryPopupReqDto reqDto) {
        // 페이지네이션 유틸리티를 사용하여 Page 객체 생성
        Page<TmPlanCustomerDispatchHistoryPopupResDto> page = PaginationUtil.createPage(
            reqDto.getPageNum(), 
            reqDto.getListCount()
        );

        Page<TmPlanCustomerDispatchHistoryPopupResDto> result = commonDao.selectPageList(SERVICEID_PREFIX + "getPlanCustomerDispatchHistoryPopup", reqDto, page);
        
        // 기사명 마스킹 처리 (첫자리와 마지막자리 제외하고 마스킹)
        if (result != null && result.getList() != null) {
            List<TmPlanCustomerDispatchHistoryPopupResDto> list = result.getList();
            for (TmPlanCustomerDispatchHistoryPopupResDto dto : list) {
                if (dto.getDrivername() != null && !dto.getDrivername().isEmpty()) {
                    dto.setDrivername(TmPlanCommon.maskDriverName(dto.getDrivername()));
                }
            }
        }
        
        return result;
    }

}
