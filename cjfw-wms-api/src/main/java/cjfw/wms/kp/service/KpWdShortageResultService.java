package cjfw.wms.kp.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.kp.dto.KpWdShortageResultReqDto;
import cjfw.wms.kp.dto.KpWdShortageResultResT1Dto;
import cjfw.wms.kp.dto.KpWdShortageResultResT2Dto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.12.02
 * @description : 지표/모니터링 > 센터운영지표 > 출고결품실적 Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.02 KimDongHan (liop0123@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class KpWdShortageResultService {
	
    private final CommonDao commonDao;

    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     *
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(KpWdShortageResultService.class.getSimpleName()) + ".";
    
    /**
     * @description : 지표/모니터링 > 센터운영지표 > 출고결품실적 달력변경시 해당월에 있는 날짜 조회 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.12.02 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
	public List<KpWdShortageResultResT1Dto> getColList(KpWdShortageResultReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getColList", dto);
	}

    /**
     * @description : 지표/모니터링 > 센터운영지표 > 출고결품실적 월요약_탭 조회 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.12.02 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
	public List<KpWdShortageResultResT1Dto> getMasterT1List(KpWdShortageResultReqDto dto) {
		List<String> colList = dto.getColList();
		dto.setDays(colList);
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterT1List", dto);
	}
	
	/**
	 * @description : 지표/모니터링 > 센터운영지표 > 출고결품실적 월요약_탭 상세 조회 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.02 KimDongHan (liop0123@cj.net) 생성 </pre>
	 */
	public List<KpWdShortageResultResT1Dto> getDetailT1List(KpWdShortageResultReqDto dto) {
		List<String> colList = dto.getColList();
		dto.setDays(colList);
		return commonDao.selectList(SERVICEID_PREFIX + "getDetailT1List", dto);
	}

    /**
     * @description : 지표/모니터링 > 센터운영지표 > 출고결품실적 일요약_탭 조회 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.12.02 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
	public List<KpWdShortageResultResT2Dto> getMasterT2List(KpWdShortageResultReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterT2List", dto);
	}
	
}
