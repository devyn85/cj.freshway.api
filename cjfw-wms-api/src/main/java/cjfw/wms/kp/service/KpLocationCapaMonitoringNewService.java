package cjfw.wms.kp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.kp.dto.KpLocationCapaMonitoringNewReqDto;
import cjfw.wms.kp.dto.KpLocationCapaMonitoringNewResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author      : YeoSeungCheol (pw6375@cj.net)
 * @date        : 2025.11.17
 * @description : 센터CAPA현황(NEW) Service
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.17        YeoSeungCheol (pw6375@cj.net)       생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class KpLocationCapaMonitoringNewService {

    private static final String SERVICEID_PREFIX = "kpLocationCapaMonitoringNewService.";

    private final CommonDao commonDao;

    /**
	 * @description : 로케이션 CAPA 현황 요약 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.17 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    public List<KpLocationCapaMonitoringNewResDto> getMasterListTab1(KpLocationCapaMonitoringNewReqDto dto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterListTab1", dto);
    }

    /**
	 * @description : 로케이션 CAPA 현황 랙별 상세 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.17 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    public List<KpLocationCapaMonitoringNewResDto> getMasterListTab2(KpLocationCapaMonitoringNewReqDto dto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterListTab2", dto);
    }

    /**
	 * @description : 로케이션 CAPA 현황 유통기한 상태 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.17 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    public List<KpLocationCapaMonitoringNewResDto> getDataStatusCount(KpLocationCapaMonitoringNewReqDto dto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getDataStatusCount", dto);
    }

    /**
	 * @description : 로케이션 CAPA 현황 - 랙별 상세탭 셀 내 유통기한 조회 (하단 그리드)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.17 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    public KpLocationCapaMonitoringNewResDto getDataTotalCount(KpLocationCapaMonitoringNewReqDto dto) {
        return commonDao.selectOne(SERVICEID_PREFIX + "getDataTotalCount", dto);
    }
    
    /**
	 * @description : 로케이션 CAPA 현황 - 렉별 상세탭 CAPA 상세 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.28 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    public List<KpLocationCapaMonitoringNewResDto> getDetailRead(KpLocationCapaMonitoringNewReqDto dto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getDetailRead", dto);
    }
}
