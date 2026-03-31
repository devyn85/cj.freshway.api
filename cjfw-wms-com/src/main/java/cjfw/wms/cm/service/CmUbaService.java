package cjfw.wms.cm.service;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.cm.dto.CmUbaReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.07.28 
 * @description : 그룹정보유출보안관제(UBA) 로그 기록
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.28 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CmUbaService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "cmUbaService.";

	private final CommonDao commonDao;
	
	/**
	 * @description : 그룹정보유출보안관제(UBA) 로그 기록
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.28 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public String saveUbaLog(CmUbaReqDto cmUbaReqDto) {		
		commonDao.insert(SERVICEID_PREFIX + "insertUbaLog", cmUbaReqDto);
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}

}
