package cjfw.wms.kp.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.kp.dto.KpCenterDayTMDlvStateReqDto;
import cjfw.wms.kp.dto.KpCenterDayTMDlvStateResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.09.01
 * @description : 지표 > 센터 운영 > 배송조별 출자 평균시간 현황 Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.01 KimDongHan (liop0123@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class KpCenterDayTMDlvStateService {

	private final CommonDao commonDao;

    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     *
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(KpCenterDayTMDlvStateService.class.getSimpleName()) + ".";

    /**
     * @description : 지표 > 센터 운영 > 배송조별 출자 평균시간 현황 조회 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.01 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
	public List<KpCenterDayTMDlvStateResDto> getMasterList(KpCenterDayTMDlvStateReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
	}

}
