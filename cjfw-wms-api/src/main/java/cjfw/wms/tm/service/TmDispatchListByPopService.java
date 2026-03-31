package cjfw.wms.tm.service;

import cjfw.core.model.Page;
import cjfw.wms.tm.dto.TmDispatchListByPopReqDto;
import cjfw.wms.tm.dto.TmDispatchListByPopResDto;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : System
 * @date : 2025.01.15
 * @description : 배차목록(POP별) 조회 서비스
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.01.15 System      생성 </pre>
 */
public interface TmDispatchListByPopService {
    
    /**
     * 배차목록(POP별) 조회
     * @param reqDto 조회 조건
     * @return 배차목록(POP별) 목록
     */
    Page<TmDispatchListByPopResDto> getDispatchListByPop(TmDispatchListByPopReqDto reqDto);
}
