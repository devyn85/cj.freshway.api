/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.auth.jwt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.gson.Gson;

import cjfw.auth.common.model.CanalFrameUserDetails;
import cjfw.core.common.CanalFrameConstants;
import cjfw.core.model.ApiResult;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ContextUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * JWT 인증 체크 Filter
 */
@Order(0)
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	private final JwtAuthUtil jwtAuthUtil;

	private Pattern include_pattern;
	
	public static final ThreadLocal<String> ipHolder = new ThreadLocal<>();
	
	private static final String ACCESSTOKEN_HEADER = "Authorization";
    private static final String ACCESSTOKEN_BEARER = "Bearer ";

	public JwtAuthenticationFilter(String[] filterAuthPattern, JwtAuthUtil jwtAuthUtil) {
		this.jwtAuthUtil = jwtAuthUtil;
		for(int i=0; i < filterAuthPattern.length; i++){
			filterAuthPattern[i] = "(" + filterAuthPattern[i].replace("**",".+") + ")";
		}
		String regexIncludeAuthPattern = String.join("|", filterAuthPattern); // 정규식 생성
		log.info("regexIncludeAuthPattern: {}", regexIncludeAuthPattern );
		include_pattern = Pattern.compile(regexIncludeAuthPattern, Pattern.CASE_INSENSITIVE);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		String servletPath = request.getServletPath();
		Matcher m = include_pattern.matcher(servletPath);
		boolean isApplyFilter = !m.find(); // "${cf.auth.pattern}"에 등록된 패턴은 제외
		log.info("{}, isApplyFilter : {}", servletPath, isApplyFilter);
		
		// 글로벌하게 IP 정보 가져올 수 있게 추가
		HttpServletRequest req = (HttpServletRequest) request;
        String ip = req.getRemoteAddr();
        ipHolder.set(ip);

		if (isApplyFilter) {			
			UserContext userContext = ContextUtil.getBean(UserContext.class);
			if(!userContext.isValid()){
				if(userContext.isTokenExpired()){
					response.setCharacterEncoding(CanalFrameConstants.DEFAULT_CHARACTERSET);
					response.setContentType(MediaType.APPLICATION_JSON_VALUE);
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					ApiResult res = ApiResult.createResult("MSG.COM.ERR.101", CanalFrameConstants.ERROR_CODE_ACCESSTOKEN_EXPIRED);
					response.getWriter().write(new Gson().toJson(res));
					return;
				} else{
					filterChain.doFilter(request, response);
					return;
				}
			}
			
			String spid = jwtAuthUtil.getSpid(getAccessTokenFromHeader(request));
			String gUserId = jwtAuthUtil.getGUserId(getAccessTokenFromHeader(request));
			String hUserId = request.getHeader("X-USER-ID");
			if (spid == null || "".equals(spid)) {
				// 중복 로그인 체크 (FRAMEONE_USER.SPID 방식)
				response.setCharacterEncoding(CanalFrameConstants.DEFAULT_CHARACTERSET);
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				ApiResult res = ApiResult.createResult("MSG.COM.ERR.103", CanalFrameConstants.ERROR_CODE_DUPLICATE_LOGIN);
				response.getWriter().write(new Gson().toJson(res));
				return;
			} else if (!servletPath.contains("/api/cm/main/") && !gUserId.equals(hUserId)) {
				// 동일한 브라우저에서 다른 사용자ID 사용시
				response.setCharacterEncoding(CanalFrameConstants.DEFAULT_CHARACTERSET);
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				ApiResult res = ApiResult.createResult("MSG.COM.ERR.104", CanalFrameConstants.ERROR_CODE_DIFFERENT_LOGIN);
				response.getWriter().write(new Gson().toJson(res));
				return;
			}
			
			// 중복 로그인 체크 (RefreshToken 방식)
//			String accessToken = getAccessTokenFromHeader(request);
//			String refreshToken = getCookieValue(request, "refresh_token");
//			try {
//				jwtAuthUtil.refreshAccessToken(refreshToken, accessToken);
//			} catch (Exception e) {
//				response.setCharacterEncoding(CanalFrameConstants.DEFAULT_CHARACTERSET);
//				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//				ApiResult res = ApiResult.createResult("MSG.COM.ERR.103", CanalFrameConstants.ERROR_CODE_DUPLICATE_LOGIN);
//				response.getWriter().write(new Gson().toJson(res));
//				return;
//			}
			
			this.setSecurityContext(userContext);
		}
		
		try {
			filterChain.doFilter(request, response);
        } finally {
            ipHolder.remove();
        }
	}

	private void setSecurityContext(UserContext userContext){
		String userId = userContext.getUserId();
		List<String> roleList = Stream.of(userContext.getRoles().split("\\|"))
				.collect(Collectors.toList());

		CanalFrameUserDetails customUserDetails = new CanalFrameUserDetails(userId, "");
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		for(String role: roleList){
			roles.add(new SimpleGrantedAuthority(role));
		}
		customUserDetails.setRoles(roles);

		UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(userId, "", customUserDetails.getAuthorities());
		result.setDetails(customUserDetails);
		SecurityContextHolder.getContext().setAuthentication(result);
	}
	
    private String getAccessTokenFromHeader(HttpServletRequest request){
        String token = request.getHeader(ACCESSTOKEN_HEADER);
        if(token == null) {
            return null;
        }
        return token.substring(ACCESSTOKEN_BEARER.length());
    }
    
    public String getCookieValue(HttpServletRequest request, String name) {
	    Cookie[] cookies = request.getCookies(); // 모든 쿠키 가져오기
	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            if (cookie.getName().equals(name)) {
	                return cookie.getValue(); // 해당 이름의 쿠키 값 반환
	            }
	        }
	    }
	    return null; // 없을 경우 null 반환
	}

}