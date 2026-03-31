package cjfw.wms.kp.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.kp.dto.KpCenterDayStateReqDto;
import cjfw.wms.kp.dto.KpCenterDayStateResT1Dto;
import cjfw.wms.kp.dto.KpCenterDayStateResT2Dto;
import cjfw.wms.kp.dto.KpCenterDayStateResT3Dto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.09.03
 * @description : 지표 > 센터 운영 > 출고 유형별 물동 현황 Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.03 KimDongHan (liop0123@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class KpCenterDayStateService {
	
    private final CommonDao commonDao;
    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     *
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(KpCenterDayStateService.class.getSimpleName()) + ".";

    /**
     * @description : 지표 > 센터 운영 > 출고 유형별 물동 현황 배송물동_탭 조회 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.03 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
	public List<KpCenterDayStateResT1Dto> getMasterT1List(KpCenterDayStateReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterT1List", dto);
	}
	
    /**
     * @description : 지표 > 센터 운영 > 출고 유형별 물동 현황 수송물동_탭 조회 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.03 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
	public List<KpCenterDayStateResT2Dto> getMasterT2List(KpCenterDayStateReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterT2List", dto);
	}
	
	/**
	 * @description : 지표 > 센터 운영 > 출고 유형별 물동 현황 배송차량_탭 조회 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.03 KimDongHan (liop0123@cj.net) 생성 </pre>
	 */
	public List<KpCenterDayStateResT3Dto> getMasterT3List(KpCenterDayStateReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterT3List", dto);
	}
}
