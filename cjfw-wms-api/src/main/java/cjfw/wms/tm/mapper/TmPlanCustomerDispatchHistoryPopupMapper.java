package cjfw.wms.tm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cjfw.wms.tm.dto.TmPlanCustomerDispatchHistoryPopupReqDto;
import cjfw.wms.tm.dto.TmPlanCustomerDispatchHistoryPopupResDto;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : OhEunbeom
 * @date : 2025.10.10
 * @description : TM 배차이력 조회 매퍼
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.10 OhEunbeom      생성 </pre>
 */
@Mapper
public interface TmPlanCustomerDispatchHistoryPopupMapper {

    /**
     * 배차이력 조회
     * 
     * @param params 조회 파라미터
     * @return 배차이력 목록
     */
    List<TmPlanCustomerDispatchHistoryPopupResDto> getPlanCustomerDispatchHistoryPopup(TmPlanCustomerDispatchHistoryPopupReqDto params);

}
