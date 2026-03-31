package cjfw.wms.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.wms.sys.dto.SysIFManagerReqDto;
import cjfw.wms.sys.dto.SysIFManagerResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiSooKim (jskim14@cj.net) 
 * @date : 2025.08.01 
 * @description : 시스템운영 > 시스템운영 > 프로시저 실행로그 목록 조회 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.01 JiSooKim (jskim14@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SysIFManagerService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "sysIFManagerService.";

	private final CommonDao commonDao;
	
	private final UserContext userContext;

	/**
	 * @description : 인터페이스 상태관리 목록 검색 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.01 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	public List<SysIFManagerResDto> getIFManagerHeaderList(SysIFManagerReqDto sysIFManagerReqDto) {

		return commonDao.selectList(SERVICEID_PREFIX + "getIFManagerHeaderList", sysIFManagerReqDto);
		
	}
	
	/**
	 * @description : 인터페이스 상태관리 목록 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.01 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	public String saveIFManager(List<SysIFManagerReqDto> sysIFManagerReqDto) {
		for(SysIFManagerReqDto sysIFManager : sysIFManagerReqDto ) {
			
			commonDao.update(SERVICEID_PREFIX +"updateIFManager", sysIFManager);
		}
	
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	


}
