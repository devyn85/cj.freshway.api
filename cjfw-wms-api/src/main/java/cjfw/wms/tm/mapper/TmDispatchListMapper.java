package cjfw.wms.tm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cjfw.wms.tm.dto.TmDispatchInfoListResDto;
import cjfw.wms.tm.dto.TmGetDispatchListReqDto;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : AI Assistant
 * @date : 2025.09.17
 * @description : TM 배차 목록 조회 매퍼
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.17 AI Assistant          생성
 */
@Mapper
public interface TmDispatchListMapper {

    /**
     * TM_INPLAN에서 차량별 배차 목록을 조회합니다.
     * 
     * @param params 조회 파라미터 (deliveryDate, dccode)
     * @return 차량별 배차 요약 목록
     */
    List<TmDispatchInfoListResDto> selectDispatchList(TmGetDispatchListReqDto params);

}
