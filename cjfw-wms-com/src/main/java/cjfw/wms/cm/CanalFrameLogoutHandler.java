package cjfw.wms.cm;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseCookie.ResponseCookieBuilder;

import cjfw.auth.common.service.CJSecurityRulesService;
import cjfw.core.common.CanalFrameConstants;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ContextUtil;
import cjfw.core.utility.DateUtil;
import cjfw.wms.cm.service.CmUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CanalFrameLogoutHandler {
	
    private final CJSecurityRulesService cJSecurityRulesService;
    
    private final CmUserService cmUserService;

    public void procLogout(HttpServletRequest request, HttpServletResponse response) {

        UserContext userContext = ContextUtil.getBean(UserContext.class);
        if(userContext.isValid()){
            String userId = userContext.getUserId();
            saveLogoutInfo(request, userId);
            Map<String, String> inParams = new HashMap();
            inParams.put("userId", userId);
            inParams.put("refreshToken", null);

            //토큰 제거
            ResponseCookieBuilder cookieBuilder = ResponseCookie.from("refresh_token", null)
                // 토큰의 유효 기간
                .maxAge(0)
                .path("/")
                .httpOnly(true);

            String springProfiles = System.getProperty("spring.profiles.active", "local");

            if (springProfiles.equalsIgnoreCase("prd")) {
                cookieBuilder.secure(true);
                cookieBuilder.sameSite("None");
            }

            ResponseCookie cookie = cookieBuilder.build();
            response.setHeader("Set-Cookie", cookie.toString());
            cJSecurityRulesService.updateUserRefreshToken(inParams); // refreshToken 사용자 DB에서 삭제
        }
    }

    private void saveLogoutInfo(HttpServletRequest request, String userId){
        Map<String, String> loginLogoutMap = new HashMap<String, String>();
        String loginDateTime = DateUtil.getDateTimeToString(LocalDateTime.now());
  
        loginLogoutMap.put("userId", userId);
        loginLogoutMap.put("LOG_DTM", loginDateTime);
        loginLogoutMap.put("IP_ADDR", (String) request.getAttribute(CanalFrameConstants.CLIENT_IP));
        cJSecurityRulesService.insertLogoutNewTx(loginLogoutMap);
        
        loginLogoutMap.put("SUCCESS_YN", "2");
        cmUserService.updateSecurityRules(loginLogoutMap);
    }

}
