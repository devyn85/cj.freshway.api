package cjfw.wms.kp.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.kp.dto.KpDpShortageResultReqDto;
import cjfw.wms.kp.dto.KpDpShortageResultResT1Dto;
import cjfw.wms.kp.dto.KpDpShortageResultResT2Dto;
import cjfw.wms.kp.dto.KpDpShortageResultResT3Dto;
import cjfw.wms.kp.dto.KpDpShortageResultResT4Dto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.09.08
 * @description : 지표 > 센터 운영 > 입고 결품 현황  Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.08 KimDongHan (liop0123@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class KpDpShortageResultService {
	
    private final CommonDao commonDao;
    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     *
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(KpDpShortageResultService.class.getSimpleName()) + ".";

    /**
     * @description : 지표 > 센터 운영 > 입고 결품 현황 일배_탭 조회 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.08 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
	public List<KpDpShortageResultResT1Dto> getMasterT1List(KpDpShortageResultReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterT1List", dto);
	}
	
	
	/**
	 * @description : 지표 > 센터 운영 > 입고 결품 현황 저장_탭 조회 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.08 KimDongHan (liop0123@cj.net) 생성 </pre>
	 */
	public List<KpDpShortageResultResT2Dto> getMasterT2List(KpDpShortageResultReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterT2List", dto);
	}
	
	/**
	 * @description : 지표 > 센터 운영 > 입고 결품 현황 일배요약_탭 조회 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.08 KimDongHan (liop0123@cj.net) 생성 </pre>
	 */
	public List<KpDpShortageResultResT3Dto> getMasterT3List(KpDpShortageResultReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterT3List", dto);
	}
	
	/**
	 * @description : 지표 > 센터 운영 > 입고 결품 현황 저장요약_탭 조회 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.08 KimDongHan (liop0123@cj.net) 생성 </pre>
	 */
	public List<KpDpShortageResultResT4Dto> getMasterT4List(KpDpShortageResultReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterT4List", dto);
	}
}
