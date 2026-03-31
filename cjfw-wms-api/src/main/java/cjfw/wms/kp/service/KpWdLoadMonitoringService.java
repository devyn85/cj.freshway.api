package cjfw.wms.kp.service;


import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


import cjfw.core.dataaccess.CommonDao;

import cjfw.wms.kp.dto.KpWdLoadMonitoringDetailResDto;
import cjfw.wms.kp.dto.KpWdLoadMonitoringReqDto;
import cjfw.wms.kp.dto.KpWdLoadMonitoringResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : ParkYosep (dytpq362@cj.net)
 * @date : 2025.12.12
 * @description : 지표/모니터링 > 검수지표 > 상차검수지표 현황 Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.12 ParkYosep (dytpq362@cj.net) 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class KpWdLoadMonitoringService {
	
    private final CommonDao commonDao;
    private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(KpWdLoadMonitoringService.class.getSimpleName()) + ".";

    /**
     * @description : 지표/모니터링 > 검수지표 > 상차검수지표 현황 조회 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.12.12 ParkYosep (dytpq362@cj.net) 생성
     */
	public List<KpWdLoadMonitoringResDto> getMasterList(KpWdLoadMonitoringReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
	}

    /**
     * @description : 지표/모니터링 >검수지표 > 상차검수지표 현황 상세 조회 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.12.12 ParkYosep (dytpq362@cj.net) 생성
     */
	public List<KpWdLoadMonitoringDetailResDto> getDetailList(KpWdLoadMonitoringReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDetailList", dto);
	}

	
}
