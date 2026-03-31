package cjfw.wms.cm.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.wms.cm.dto.CmMainUserAuthResDto;
import cjfw.wms.cm.dto.CmMainUserReqDto;
import cjfw.wms.cm.dto.CmMainUserResDto;
import cjfw.wms.cm.dto.CmUserRoleResDto;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.CommonConstants;
import cjfw.wms.common.extend.CommonDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.05.09 
 * @description : 로그인 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.09 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CmUserService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "cmUserService.";

	private final CommonDao commonDao;
	
	private final UserContext userContext;
	
	/**
	 * @description : 로그인 사용자 정보 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.09 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public CmMainUserResDto getUserAuth(CmMainUserReqDto cmMainUserReqDto){
		return commonDao.selectOne(SERVICEID_PREFIX + "getUserAuth", cmMainUserReqDto);
	}
	
	/**
	 * @description : 로그인 후 사용자 정보 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.12 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public CmMainUserResDto getCmUser(CmMainUserReqDto cmMainUserReqDto){
		
		// PKG 파라마터 세팅 - 공통(1/4)
		ProcedureParametersFactory.initParamDto(cmMainUserReqDto, cmMainUserReqDto, "SPCM_USER");
		
		// PKG 파라마터 세팅 및 실행 - 업무(2/4)
		cmMainUserReqDto.setAvc_COMMAND("LOGIN");
		commonDao.exec(CommonConstants.COMMON_CALLPROCEDURE, cmMainUserReqDto);
		
		// 프로시저 OUT Parameter(3/4)
		String resultCode    = (String)cmMainUserReqDto.getResultCode();
		String resultMessage = (String)cmMainUserReqDto.getResultMessage();
		
		// 프로시저 Exception 처리(4/4)
		if (!resultCode.equals("0")) {
			throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage);
		}
		
		CmMainUserResDto cmMainUserResDto = commonDao.selectOne(SERVICEID_PREFIX + "getCmUser", cmMainUserReqDto);
		cmMainUserResDto.setMobNo(""); // 개인정보 보호를 위해 핸드폰번호 삭제
		
		// 권한 여러개일 경우 대비
		if (userContext.getRoles() != null && !"".equals(userContext.getRoles())) {
			String replaced = cjfw.core.utility.StringUtil.nvl(userContext.getRoles()).replace("WAYLO_", "");
			String output = replaced.replace("\\|", ",");
			cmMainUserResDto.setRoles(output);
		}
		
		return cmMainUserResDto;
	}
	
	/**
	 * @description : 사용자 접속 권한 정보 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.12 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public List<CmMainUserAuthResDto> getCmUserAuthority(CmMainUserReqDto cmMainUserReqDto){
		return commonDao.selectList(SERVICEID_PREFIX + "getCmUserAuthority", cmMainUserReqDto);
	}
	
	/**
	 * @description : 사용자 권한그룹코드 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.15 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public List<CmUserRoleResDto> getUserRoleList(CommonDto commonDto){
		return commonDao.selectList(SERVICEID_PREFIX + "getUserRoleList", commonDto);
	}
	
	/**
	 * @description : 검색유형별 사용자 정보 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.30 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public CmMainUserResDto getUserBySelType(CmMainUserReqDto cmMainUserReqDto){		
		return commonDao.selectOne(SERVICEID_PREFIX + "getUserBySelType", cmMainUserReqDto);
	}
	
	/**
	 * @description : 사용자 이메일 정보 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.19 Baechan (c_bae@cj.net) 생성 </pre>
	 */
	public CmMainUserResDto getUserEmailByUserId(CmMainUserReqDto cmMainUserReqDto){		
		return commonDao.selectOne(SERVICEID_PREFIX + "getUserEmailByUserId", cmMainUserReqDto);
	}
	
	/**
	 * @description : 로그인 성공여부에 따른 보안 정보 수정
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.21 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public String updateSecurityRules(Map<String, String> inParams){		
		commonDao.update(SERVICEID_PREFIX + "updateSecurityRules", inParams);
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 사용자별 허용 IP 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.21 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public List<Object> getUserAllowedIpList(Map<String, String> userMap){
		return commonDao.selectList(SERVICEID_PREFIX + "getUserAllowedIpList", userMap);
	}
	
	/**
	 * @description : 로그인 로그 기록
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.21 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public void insertLoginNewTx(Map<String, String> loginMap){
		commonDao.insert(SERVICEID_PREFIX + "insertLoginNewTx", loginMap);
	}
	
	/**
	 * @description : refreshToken DB 업데이트
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.15 breaker3317 생성 </pre>
	 */
	public String updateUserRefreshToken(Map<String, String> inParams){		
		commonDao.update(SERVICEID_PREFIX + "updateUserRefreshToken", inParams);
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
}
