package cjfw.wms.kp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.kp.dto.KpDcMonitoringReqDto;
import cjfw.wms.kp.dto.KpDcMonitoringResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.12.02 
 * @description : Home 센터 운영 모니터링
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.12.02 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class KpDcMonitoringService {
	
    /**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
    private transient static final String SERVICEID_PREFIX = "kpDcMonitoringService.";
    
    private final CommonDao commonDao;

    /**
     * @description : 신규 마스터 정보 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.12.02 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
     */
	public List<KpDcMonitoringResDto> getNewMasterRead(KpDcMonitoringReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getNewMasterRead", dto);
	}
	
	/**
     * @description : 신규 마스터 상세 정보 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.12.02 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
     */
	public List<KpDcMonitoringResDto> getNewMasterDetailRead(KpDcMonitoringReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getNewMasterDetailRead", dto);
	}

}
