package cjfw.core.model.factory;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Locale;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ContextUtil;
import cjfw.core.utility.JwtUtil;
import cjfw.core.utility.LocaleUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.20
 * @description : UserContextFactory 기능을 구현한 Class
 * 				  accessToken 기반 UserContext 객체 생성 기능
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.20        sungyeon.lee       생성
 */
@Slf4j
public class UserContextFactory {
    private static final String ACCESSTOKEN_HEADER = "Authorization";
    private static final String ACCESSTOKEN_BEARER = "Bearer ";

    /**
     * 
     * @description : createUserContext 기능을 구현한 Method
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.20        sungyeon.lee       생성
     */
    public UserContext createUserContext(HttpServletRequest request) {
        String accessToken = getAccessTokenFromHeader(request);
        Locale locale = LocaleUtil.getUserLocale(request);
        String clientIp = (String)request.getAttribute(CanalFrameConstants.CLIENT_IP);
        if(accessToken == null){
            return UserContext.builder()
                    .locale(locale)
                    .clientIp(clientIp)
                    .isValid(false)
                    .build();
        }

        JwtUtil jwtUtil = ContextUtil.getBean(JwtUtil.class);
        UserContext tokenUserContext = jwtUtil.getUserContext(accessToken); // 토큰에서 사용자 정보
        UserContext userContext = null;
        if(tokenUserContext != null){ // AccessToken 유효
            userContext = tokenUserContext.toBuilder()
                    .locale(locale)
                    .clientIp(clientIp)
                    .isValid(true)
                    .build();
        } else {
            userContext = UserContext.builder()
                    .locale(locale)
                    .clientIp(clientIp)
                    .isValid(false)
                    .build();
            if("01".equals(jwtUtil.isValidJwt(accessToken))) {  // AccessToken 만료
                userContext.setTokenExpired(true);
            }
        }

        return userContext;
    }

    /**
     * 
     * @description : getAccessTokenFromHeader 기능을 구현한 Method
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.20        sungyeon.lee       생성
     */
    private String getAccessTokenFromHeader(HttpServletRequest request){
        String token = request.getHeader(ACCESSTOKEN_HEADER);
        if(token == null) {
            return null;
        }
        return token.substring(ACCESSTOKEN_BEARER.length());
    }

}
