package cjfw.wms.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.sys.dto.SysAuthorityProgramReqDto;
import cjfw.wms.sys.dto.SysAuthorityProgramResDto;
import cjfw.wms.sys.entity.SysAuthProgEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.05.15 
 * @description : ADMIN > 시스템운영 > 그룹권한 정보를 조회 및 저장 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.15 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SysAuthorityProgramService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "sysAuthorityProgramService.";
	
	private final CommonDao commonDao;
	
	private final UserContext userContext;
	
	/**
	 * @description : 권한그룹별 프로그램 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.08 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public List<SysAuthorityProgramResDto> getAuthorityProgramList(SysAuthorityProgramReqDto sysAuthorityProgramReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getAuthorityProgramList", sysAuthorityProgramReqDto);
	}
	
	/**
	 * @description : 권한그룹별 프로그램 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.08 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public String saveAuthorityProgram(SysAuthorityProgramReqDto sysAuthorityProgramReqDto) {
		if (null != sysAuthorityProgramReqDto.getAuthProgramList()) {
			for (SysAuthorityProgramResDto authProgram : sysAuthorityProgramReqDto.getAuthProgramList()) {
				SysAuthProgEntity sysAuthProgEntity = ModelMapperUtil.map(authProgram, userContext, SysAuthProgEntity.class);
				if ((CanalFrameConstants.INSERT).equals(authProgram.getRowStatus()) || (CanalFrameConstants.UPDATE).equals(authProgram.getRowStatus())) {
					commonDao.insert(SERVICEID_PREFIX +"deleteAuthorityProgram", sysAuthProgEntity);
					commonDao.update(SERVICEID_PREFIX +"insertAuthorityProgram", sysAuthProgEntity);
					
					// FRAMEONE_PROGRAM 테이블 AUTHORITY 칼럼에 권한코드 추가/삭제
					if ("1".equals(sysAuthProgEntity.getUseYn())) {
						commonDao.update(SERVICEID_PREFIX +"updateProgramAuthorityAdd", sysAuthProgEntity);
					} else if ("0".equals(sysAuthProgEntity.getUseYn())) {
						commonDao.update(SERVICEID_PREFIX +"updateProgramAuthorityDel", sysAuthProgEntity);
					}
				}
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
}
