package cjfw.wms.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.sys.dto.SysAuthorityGrpReqDto;
import cjfw.wms.sys.dto.SysAuthorityReqDto;
import cjfw.wms.sys.dto.SysAuthorityResDto;
import cjfw.wms.sys.entity.SysAuthChgLogEntity;
import cjfw.wms.sys.entity.SysAuthorityEntity;
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
public class SysAuthorityService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "sysAuthorityService.";
	
	private final CommonDao commonDao;
	
	private final UserContext userContext;

	/**
	 * @description : 권한그룹 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.15 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public List<SysAuthorityResDto> getAuthorityGroupList(SysAuthorityReqDto sysAuthorityReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getAuthorityGroupList", sysAuthorityReqDto);
	}
	
	/**
	 * @description : 권한그룹 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.15 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public String saveAuthorityGroup(SysAuthorityReqDto sysAuthorityReqDto) {
		if (null != sysAuthorityReqDto.getAuthGrpList()) {
			for (SysAuthorityGrpReqDto authGrp : sysAuthorityReqDto.getAuthGrpList()) {
				SysAuthorityEntity sysAuthorityEntity = ModelMapperUtil.map(authGrp, userContext, SysAuthorityEntity.class);
				
				// 협력사코드 설정
				sysAuthorityEntity.setCustKey(sysAuthorityReqDto.getGCustkey());
				
				if ((CanalFrameConstants.INSERT).equals(authGrp.getRowStatus())) {
					// 대표사용자ID 여부 체크
					if ("Y".equals(sysAuthorityReqDto.getGRepUserIdYn())) {
						// 사용자의 대표권한을 "상위권한그룹코드"로 설정
						sysAuthorityEntity.setUpAuthGroupCd(userContext.getAuthority());
						
						commonDao.insert(SERVICEID_PREFIX +"insertAuthorityChild", sysAuthorityEntity);
					} else {
						commonDao.insert(SERVICEID_PREFIX +"insertAuthority", sysAuthorityEntity);
					}
					
					// 권한 변경 로그 기록
					SysAuthChgLogEntity sysAuthChgLogEntity = ModelMapperUtil.map(authGrp, userContext, SysAuthChgLogEntity.class);
					sysAuthChgLogEntity.setTgtUserId("-");									// 대상사용자ID
					sysAuthChgLogEntity.setPreAuthCd("-");									// 기존권한그룹코드
					sysAuthChgLogEntity.setChgAuthCd(sysAuthorityEntity.getAuthCd());		// 변경권한그룹코드
					sysAuthChgLogEntity.setActionType("C");									// 행위유형(C/R/U/D)
					commonDao.insert(SERVICEID_PREFIX +"insertAuthChgLog", sysAuthChgLogEntity);
				} else if ((CanalFrameConstants.UPDATE).equals(authGrp.getRowStatus())) {
					commonDao.update(SERVICEID_PREFIX +"updateAuthority", sysAuthorityEntity);
				}
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
}
