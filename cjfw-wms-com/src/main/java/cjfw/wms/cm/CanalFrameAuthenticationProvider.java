/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.cm;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cjfw.auth.common.model.CanalFrameUserDetails;
import cjfw.auth.common.service.CJSecurityRulesService;
import cjfw.core.common.CanalFrameConstants;
import cjfw.core.utility.DateUtil;
import cjfw.wms.cm.dto.CmMainUserReqDto;
import cjfw.wms.cm.dto.CmMainUserResDto;
import cjfw.wms.cm.dto.CmUserRoleResDto;
import cjfw.wms.cm.service.CmUserService;
import cjfw.wms.common.extend.CommonDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class CanalFrameAuthenticationProvider implements AuthenticationProvider {

	private static final int PASSWORD_FAIL_CNT = 5;
	
    private final CJSecurityRulesService cJSecurityRulesService;
    
    private final CmUserService cmUserService;

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(CanalFrameAuthenticationToken.class);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        String remoteNetworkAddress = (String) attr.getRequest().getAttribute(CanalFrameConstants.CLIENT_IP);
        
        CanalFrameAuthenticationToken token = (CanalFrameAuthenticationToken) authentication;

        String userId = (String)token.getPrincipal();
        String userPassword = (String)token.getCredentials();
        String verificationYn = token.getVerificationYn();
        String ssoLoginYn = token.getSsoLoginYn();
        
        Map<String, String> inParams = new HashMap<String, String>();
        inParams.put("userId", userId);
        CmMainUserReqDto cmMainUserReqDto = new CmMainUserReqDto();
        cmMainUserReqDto.setGUserId(userId);
        cmMainUserReqDto.setSsoLoginYn(ssoLoginYn);
        
        CmMainUserResDto user = cmUserService.getUserAuth(cmMainUserReqDto);
        List<Object> allowedIp = cmUserService.getUserAllowedIpList(inParams);

        // ID 불일치
        if(user == null){
            throw new BadCredentialsException("010");
        }
        
        // 비정규직 중 SSO 처리 대상자 체크
        if (ssoLoginYn != null && "Y".equals(ssoLoginYn) && user.getNonRegularYn() != null && "Y".equals(user.getNonRegularYn()) && user.getSsoUseYn() != null && "N".equals(user.getSsoUseYn())) {
        	throw new BadCredentialsException("090");
        }
        
        // 로그인한 ID를 통한 계정 유무 검사
        // 상태 코드
        // 잠긴 계정인지 검사
        if("02".equals(user.getUserStsCd())){
        	// 로그인 실패 로그 기록
        	insertLoginNewTx(userId, remoteNetworkAddress, "0");
        	
        	throw new BadCredentialsException("080");
        }
        
        // 비밀번호 불일치 체크
        // Client에서 SHA256 n회 처리된 값과 이미 SHA256 Hashing 처리되어 DB에 존재하는 USER_PWD와 비교
        if(!"Y".equals(ssoLoginYn) && !userPassword.equals(user.getPwdNo())){
        	// 로그인 실패 로그 기록
        	insertLoginNewTx(userId, remoteNetworkAddress, "0");
        	
        	if(Integer.parseInt(user.getFailInCnt().toString()) < PASSWORD_FAIL_CNT) {
        	
	            inParams.put("SUCCESS_YN", "0"); 
	            cmUserService.updateSecurityRules(inParams);
	            throw new BadCredentialsException("010");
        	} else {
        		inParams.put("SUCCESS_YN", "3");
        		cmUserService.updateSecurityRules(inParams);
        		throw new BadCredentialsException("050");
        	}
        }

        Map<String, String> validResultMap = new HashMap();
        if(!CanalFrameLoginValidator.validLogin(validResultMap, remoteNetworkAddress, user, allowedIp, verificationYn)){
        	// 로그인 실패 로그 기록
        	insertLoginNewTx(userId, remoteNetworkAddress, "0");
        	
            throw new BadCredentialsException(validResultMap.get("result"));
        }

        // 로그인 성공 로그 기록
        insertLoginNewTx(userId, remoteNetworkAddress, "1");

        // 로그인 성공 이후
        Map<String, String> loginMap = new HashMap<String, String>();
        loginMap.put("userId", userId);
        loginMap.put("IP_ADDR", remoteNetworkAddress);
        loginMap.put("SUCCESS_YN", "1");
        cmUserService.updateSecurityRules(loginMap);

        Map<String, String> userIdMap = new HashMap<String, String>();
        userIdMap.put("USER_ID", userId);

        CommonDto commonDto = new CommonDto();
        commonDto.setGUserId(userId);
        List<CmUserRoleResDto> roleList = cmUserService.getUserRoleList(commonDto);
        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();

        for (CmUserRoleResDto role : roleList) {
        	roles.add(new SimpleGrantedAuthority(role.getAuthCd()));
		}

        CanalFrameUserDetails customUserDetails = new CanalFrameUserDetails(userId, userPassword);
        customUserDetails.setRoles(roles);

        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(userId, userPassword, roles);
        result.setDetails(customUserDetails);
        return result;
    }
    
    /**
     * @description : 로그인 성공여부 기록
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.05 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
     */
    public void insertLoginNewTx(String userId, String remoteNetworkAddress, String loginSuccYn) {
    	String loginDateTime = DateUtil.getDateTimeToString(LocalDateTime.now());
        Map<String, String> loginMap = new HashMap<String, String>();
        loginMap.put("userId", userId);
        loginMap.put("LOG_DTM", loginDateTime);
        loginMap.put("IP_ADDR", remoteNetworkAddress);
        loginMap.put("loginSuccYn", loginSuccYn);
        cmUserService.insertLoginNewTx(loginMap);
    }
}
