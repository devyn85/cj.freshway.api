package cjfw.auth.jwt;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseCookie.ResponseCookieBuilder;
import org.springframework.stereotype.Component;

import cjfw.auth.common.service.CJSecurityRulesService;
import cjfw.core.model.UserContext;
import cjfw.core.redis.RedisService;
import cjfw.core.utility.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * JWT 인증 갱신 공통
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthUtil {

    private final CJSecurityRulesService cjSecurityRulesService;
    private final JwtUtil jwtUtil; // NOSONAR
    private final RedisService redisService; // NOSONAR


    private static long refreshExpTime;

    @Value("${cf.jwt.refreshExpTime:-1}")
    private void setRefreshExpTime(long refreshExpTime){
        this.refreshExpTime = refreshExpTime; // NOSONAR
    }

    /**
     * AccessToken 갱신
     */
    public String refreshAccessToken(String refreshToken, String accessToken){

        String newAccessToken = null;
        String isValidRefreshToken = jwtUtil.isValidJwt(refreshToken);
        if("00".equals(isValidRefreshToken)){ // refreshToken 유효
            UserContext tokenUserContext = jwtUtil.getForceUserContext(accessToken);
            if(tokenUserContext != null){
//                Map<String, String> inParams = new HashMap<String, String>();
//                inParams.put("refreshToken", refreshToken);
//                Map<String, String> refreshResultMap = cjSecurityRulesService.getUserRefreshToken(inParams);
                
//                // 해당 RefreshToken의 사용자 존재 체크
//                // 운영 서버 외 "hyeyeon822" ID일 경우 제외
//                if(refreshResultMap != null || (!"prd".equals(System.getProperty("spring.profiles.active", "local")) && "hyeyeon822".equals(tokenUserContext.getUserId()))){
//                    newAccessToken = jwtUtil.buildAccessJwt(tokenUserContext); // AccessToken 생성
//                    log.info("New AccessToken Created");
//                } else{
//                    log.warn("User RefreshToken not Matched!!");
//                    throw new UnAuthorizedException(CanalFrameConstants.ERROR_CODE_DUPLICATE_LOGIN, "MSG.COM.ERR.103");
//                }
                
                newAccessToken = jwtUtil.buildAccessJwt(tokenUserContext); // AccessToken 생성
                log.info("New AccessToken Created");
            }else{
                log.warn("Fail Get UserContext from refreshToken");
            }
        } else{
            log.warn("RefreshToken not valid");
        }
        return newAccessToken;
    }

    /**
     * RefreshToken 남은 expired 기간 확인 후 설정한 refreshExpTime의 1/2이 지난 경우 자동 갱신
     * ===> RefreshToken 매번 갱신해서 연장으로 변경(필요에 따라 커스터마아징 가능)
     */
    public String checkAndUpdateRefreshToken(String refreshToken, String accessToken){
        String newRefreshToken = null;
        //Date expireDate = jwtUtil.getExpireDate(refreshToken);
        //if(expireDate != null){
        //    Date now = new Date();
        //    long diffMs = (expireDate.getTime() - now.getTime()); //ms 차이
        //    if(diffMs < (refreshExpTime/2)){
                UserContext tokenUserContext = jwtUtil.getForceUserContext(accessToken);
                newRefreshToken = jwtUtil.buildRefreshJwt(); // RefreshToken 생성
                Map<String, String> inParams = new HashMap();
                inParams.put("userId", tokenUserContext.getUserId());
                inParams.put("refreshToken", newRefreshToken);
                cjSecurityRulesService.updateUserRefreshToken(inParams);
                log.info("New RefreshToken Created");
        //    }
        //}
        return newRefreshToken;
    }

    public static void setRefreshTokenInHttpOnlyCookie(HttpServletResponse res,
        String refreshToken, String cookieName){

        // Duration으로 maxAge세팅 하도록 코드 보완
        // (밀리초를 그대로 넣을 경우 쿠키 maxAge가 해당 값으로 세팅되지 않음)
        Duration duration = Duration.of(refreshExpTime, ChronoUnit.MILLIS);

        ResponseCookieBuilder cookieBuilder = ResponseCookie.from(cookieName, refreshToken)
            // 토큰의 유효 기간
            .maxAge(duration)
            .path("/")
            .httpOnly(true);

        String springProfiles = System.getProperty("spring.profiles.active", "local");

        if (springProfiles.equalsIgnoreCase("prd")) {
            cookieBuilder.secure(true);
            cookieBuilder.sameSite("None");
        }

        ResponseCookie cookie = cookieBuilder.build();
        res.setHeader("Set-Cookie", cookie.toString());
    }
    
    // 중복 로그인 체크 (FRAMEONE_USER.SPID 값 기준)
    public String getSpid(String accessToken) {
    	String rtnSpid = "";
    	
    	// 토근에 담긴 사용자 정보 가져오기
    	UserContext tokenUserContext = jwtUtil.getForceUserContext(accessToken);
    	if (tokenUserContext != null) {
    		Map<String, String> inParams = new HashMap<String, String>();
    		inParams.put("userId", tokenUserContext.getUserId());
    		inParams.put("spid", tokenUserContext.getSpid());
    		
    		// 운영 서버 외 "hyeyeon822" ID일 경우 제외
//    		if (!"prd".equals(System.getProperty("spring.profiles.active", "local")) && "hyeyeon822".equals(tokenUserContext.getUserId())) {
    		if ("hyeyeon822".equals(tokenUserContext.getUserId())) {
    			rtnSpid = "Y";
    		} else {
    			// Redis에서 사용자 정보 조회
    	        // 중복 로그인 체크 DB 부화 줄이기 위해 Redis 사용
//    			String profile = System.getProperty("spring.profiles.active", "local");
//    			String spid = (String) redisService.get(profile+":user:spid:"+tokenUserContext.getUserId());
//    			if (spid != null && spid.equals(tokenUserContext.getSpid())) {
//    				rtnSpid = spid;
//    			}
    			
    			Map<String, String> refreshResultMap = cjSecurityRulesService.getSpid(inParams);
    			if (refreshResultMap != null) {
        			rtnSpid = refreshResultMap.get("SPID");
        		}
    		}
    	}
    	
    	return rtnSpid;
    }
    
    // 토큰에 담기 사용자ID 가져오기
    public String getGUserId(String accessToken) {
    	String rtnGUserId = "";
    	
    	// 토근에 담긴 사용자 정보 가져오기
    	UserContext tokenUserContext = jwtUtil.getForceUserContext(accessToken);
    	if (tokenUserContext != null) {
    		rtnGUserId = tokenUserContext.getUserId();
    	}
    	
    	return rtnGUserId;
    }
}
