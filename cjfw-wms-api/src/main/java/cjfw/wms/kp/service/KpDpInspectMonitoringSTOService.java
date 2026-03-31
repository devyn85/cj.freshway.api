package cjfw.wms.kp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.kp.dto.KpDpInspectMonitoringSTOReqDto;
import cjfw.wms.kp.dto.KpDpInspectMonitoringSTOResDetailDto;
import cjfw.wms.kp.dto.KpDpInspectMonitoringSTOResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.11.29 
 * @description : 광역출고검수현황 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.29 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class KpDpInspectMonitoringSTOService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	
	private transient static final String SERVICEID_PREFIX = "kpDpInspectMonitoringSTOService.";
	
	private final CommonDao commonDao;
	private final UserContext userContext;
	private final CmCommonService cmCommonService;

	/**
	 * @description : 광역출고검수현황 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.29 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<KpDpInspectMonitoringSTOResDto> getMasterList(KpDpInspectMonitoringSTOReqDto dto) {
		log.info("### parameter = "+dto.toString());
		
		List<KpDpInspectMonitoringSTOResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
		return list;
	}
	
	/**
	 * @description : 광역출고검수현황 상세 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.29 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<KpDpInspectMonitoringSTOResDetailDto> getDetailList(KpDpInspectMonitoringSTOReqDto dto) {
		log.info("### parameter = "+dto.toString());
		
		List<KpDpInspectMonitoringSTOResDetailDto> list = commonDao.selectList(SERVICEID_PREFIX + "getDetailList", dto);
		return list;
	}
}
