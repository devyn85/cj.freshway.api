/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.auth.common;

import cjfw.auth.common.model.CanalFrameUserDetails;
import cjfw.auth.common.service.CJSecurityRulesService;
import cjfw.core.common.CanalFrameConstants;
import cjfw.core.model.ApiResult;
import cjfw.core.utility.LocaleUtil;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@RequiredArgsConstructor
public class CJSecurityRulesInterceptor implements HandlerInterceptor {

	private final CJSecurityRulesService cJSecurityRulesService;

    @Override
    public boolean preHandle(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse, Object handler) throws Exception {

        requestLogger(httpservletrequest);

        CanalFrameUserDetails userDetails = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth.getDetails() instanceof CanalFrameUserDetails) {
			userDetails = (CanalFrameUserDetails) auth.getDetails();
		}

		//호출로깅
		String requestURI = httpservletrequest.getRequestURI();
		Map<String, String> inputData = new HashMap<>();
		if(userDetails != null) {
			inputData.put("USER_ID", userDetails.getUsername());
		}
		inputData.put("LOG_MSG","call ".concat(requestURI));
		inputData.put("USER_PWD","N/A");
		inputData.put("IP_ADDR",(String)httpservletrequest.getAttribute(CanalFrameConstants.CLIENT_IP));
//		cJSecurityRulesService.insertUsersLog(inputData);

//		if(!isAuthenticatedMenu(userDetails, httpservletrequest)){
//			log.warn("CJSecurityRulesInterceptor preHandle Fail");
//			httpservletresponse.setCharacterEncoding(CanalFrameConstants.DEFAULT_CHARACTERSET);
//			httpservletresponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
//			httpservletresponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
//			ApiResult res = ApiResult.createResult("Forbidden", HttpServletResponse.SC_FORBIDDEN);
//			httpservletresponse.getWriter().write(new Gson().toJson(res));
//			return false;
//		}

        return true;
    }


	public boolean isAuthenticatedMenu(CanalFrameUserDetails userDetails, HttpServletRequest request){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<String> roleList = new ArrayList<String>();

		if(userDetails.getRoles().size()>0){
			for(int i=0;i<userDetails.getRoles().size();i++){
				roleList.add(userDetails.getRoles().get(i).getAuthority());
			}
		}
		paramMap.put("roles",roleList);
		List<String> localeList = new ArrayList<String>();
		localeList.add(0, LocaleUtil.getUserLocaleString(request));
		paramMap.put("locale", localeList);
		paramMap.put("uri", request.getRequestURI());

		// TO-DO(GS) : API URI와 프로그램 메뉴에 등록된 화면 URL과 매핑 체크
//		Map<String, String> menu = cJSecurityRulesService.getAuthenticatedMenu(paramMap);
//		if(menu != null) {
//			return true;
//		}
//		return false;
		return true;
	}



    /**
	 * request 정보를 logging.<br>
	 *<br>
	 * @param request HttpServletRequest
	 */
	private void requestLogger(HttpServletRequest request) {
		String requestIP = (String)request.getAttribute(CanalFrameConstants.CLIENT_IP);
		String requestURL = request.getRequestURL().toString();

		/* log 메세지를 만든다 */
		log.info("[IP\t: {}]", requestIP);
		log.info("[URL\t: {}]", requestURL);
	}
}