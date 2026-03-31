package cjfw.wms.tm.service.impl;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.wms.tm.dto.TmDispatchListByPopReqDto;
import cjfw.wms.tm.dto.TmDispatchListByPopResDto;
import cjfw.wms.tm.service.TmDispatchListByPopService;
import cjfw.wms.tm.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : System
 * @date : 2025.01.15
 * @description : 배차목록(POP별) 조회 서비스 구현체
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.01.15 System      생성 </pre>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TmDispatchListByPopServiceImpl implements TmDispatchListByPopService {

    private transient static final String SERVICEID_PREFIX = "tmDispatchListByPopService.";

    private final CommonDao commonDao;

    @Override
    public Page<TmDispatchListByPopResDto> getDispatchListByPop(TmDispatchListByPopReqDto reqDto) {
        log.info("배차목록(POP별) 조회 시작 - dccode: {}, deliverydtFrom: {}, deliverydtTo: {}", 
                reqDto.getDccode(), reqDto.getDeliverydtFrom(), reqDto.getDeliverydtTo());
        
        // 페이지네이션 유틸리티를 사용하여 Page 객체 생성
        Page<TmDispatchListByPopResDto> page = PaginationUtil.createPage(
            reqDto.getPageNum(), 
            reqDto.getListCount()
        );

        Page<TmDispatchListByPopResDto> result = commonDao.selectPageList(SERVICEID_PREFIX + "getDispatchListByPop", reqDto, page);
        
        log.info("배차목록(POP별) 조회 완료 - 총 건수: {}, 조회 건수: {}", result.getTotalCount(), result.getList().size());
        
        return result;
    }
}
