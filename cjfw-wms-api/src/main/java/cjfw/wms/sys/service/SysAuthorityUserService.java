package cjfw.wms.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.sys.dto.SysAuthorityUserReqDto;
import cjfw.wms.sys.dto.SysAuthorityUserResDto;
import cjfw.wms.sys.entity.SysAuthChgLogEntity;
import cjfw.wms.sys.entity.SysAuthUserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.08.20 
 * @description : ADMIN > 시스템운영 > 권한그룹별 프로그램 관리 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.20 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SysAuthorityUserService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "sysAuthorityUserService.";
	
	private final CommonDao commonDao;
	
	private final UserContext userContext;
	
	/**
	 * @description : 권한그룹별 사용자 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.20 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public List<SysAuthorityUserResDto> getAuthorityUserList(SysAuthorityUserReqDto sysAuthorityUserReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getAuthorityUserList", sysAuthorityUserReqDto);
	}
	
	/**
	 * @description : 권한그룹별 사용자 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.21 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public String saveAuthorityUser(SysAuthorityUserReqDto sysAuthorityUserReqDto) {
		if (null != sysAuthorityUserReqDto.getAuthUserList()) {
			for (SysAuthorityUserResDto authUser : sysAuthorityUserReqDto.getAuthUserList()) {
				SysAuthUserEntity sysAuthUserEntity = ModelMapperUtil.map(authUser, userContext, SysAuthUserEntity.class);
				if ("1".equals(authUser.getUseYn())) {
					commonDao.update(SERVICEID_PREFIX +"insertAuthorityUser", sysAuthUserEntity);
				} else if ("0".equals(authUser.getUseYn())) {
					commonDao.update(SERVICEID_PREFIX +"deleteAuthorityUser", sysAuthUserEntity);
				}
				
				// 권한 변경 로그 기록
				SysAuthChgLogEntity sysAuthChgLogEntity = ModelMapperUtil.map(authUser, userContext, SysAuthChgLogEntity.class);
				sysAuthChgLogEntity.setTgtUserId(authUser.getUserId());				// 대상사용자ID
				if ("1".equals(authUser.getUseYn())) {
					sysAuthChgLogEntity.setPreAuthCd("-");							// 기존권한그룹코드
					sysAuthChgLogEntity.setChgAuthCd(authUser.getAuthCd());			// 변경권한그룹코드
					sysAuthChgLogEntity.setActionType("C");							// 행위유형(C/R/U/D)
				} else if ("0".equals(authUser.getUseYn())) {
					sysAuthChgLogEntity.setPreAuthCd(authUser.getAuthCd());			// 기존권한그룹코드
					sysAuthChgLogEntity.setChgAuthCd("-");							// 변경권한그룹코드
					sysAuthChgLogEntity.setActionType("D");							// 행위유형(C/R/U/D)
				}
				commonDao.insert("sysAuthorityService.insertAuthChgLog", sysAuthChgLogEntity);
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
}
