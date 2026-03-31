package cjfw.wms.dp.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.dp.dto.DpInplanTimeReqDto;
import cjfw.wms.dp.dto.DpInplanTimeResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.12.01
 * @description : 입고 > 입고현황 > 입고 예정진행 현황(입차시간) Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.01 KimDongHan (liop0123@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class DpInplanTimeService {
	
    private final CommonDao commonDao;

    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     *
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(DpInplanTimeService.class.getSimpleName()) + ".";

    /**
     * @description : 입고 > 입고현황 > 입고 예정진행 현황(입차시간) 조회 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.11.17 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
    public List<DpInplanTimeResDto> getMasterList(DpInplanTimeReqDto dto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
    }

}
