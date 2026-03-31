package cjfw.wms.cm.service;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.common.extend.CommonProcedureDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : SangSuSung(kduimux@cj.com) 
 * @date : 2025.05.30 
 * @description : 공통기능 관련 기능을 구현한 Service Class 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.30 SangSuSung(kduimux@cj.com) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CmCommonService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "cmCommonService.";

	private final CommonDao commonDao;
	
    /**
     * @description : 패키지를 호출하는 기능을 구현한 Method 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.05.30 SangSuSung(kduimux@cj.com) 생성 </pre>
     */
	public int saveProcedure(Object dto){
		String packagename = ""; // 패키지명	
		if(dto instanceof CommonProcedureDto ) {
			packagename = ((CommonProcedureDto) dto).getPackagename();
		}
		log.info("packagename->{}", packagename);
		return commonDao.exec(SERVICEID_PREFIX + "callProcedure", dto); 
	}
	

}
