package cjfw.core.web.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import cjfw.core.common.CanalFrameConstants;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.22
 * @description : OO 기능을 구현한 Controller Class
 * 				  시스템 전반적으로 참조할 정보를 가공하여 저장하는 필터
 * 			
 * 				  [상세 흐름]
 * 				  (1) 요청URI를 가공하여 request의 속성으로 저장
 * 				  (2) 클라이언트 IP를 얻어서 request의 속성으로 저장
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.22        sungyeon.lee       생성
 */
@Configuration
public class EnvironmentSetupFilter implements Filter {
	private static final Logger log = LoggerFactory.getLogger(EnvironmentSetupFilter.class);
	
	/**
	 * 
	 * @overridden  : @see jakarta.servlet.Filter#doFilter(jakarta.servlet.ServletRequest, jakarta.servlet.ServletResponse, jakarta.servlet.FilterChain)
	 * @description : doFilter 기능을 Override하여 구현한 Method
	 * 				  시스템 전반적으로 참조할 정보를 가공하여 저장
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {

		//----------------------------------- 요청 URI 작업 - start ---------------------------------------------
		//요청 URI를 contextPath를 제거한 형태로 가공하여 request에 저장
		HttpServletRequest hReq = (HttpServletRequest)request;

		String contextPath = hReq.getContextPath();
		String reqURL = hReq.getRequestURL().toString();
		//요청 URI - get 파라미터가 제거된 상태
		String reqURI = hReq.getRequestURI();

		if(reqURI.startsWith(contextPath)) {
			reqURI = reqURI.substring(reqURI.indexOf(contextPath) + contextPath.length());
		}

		//요청 URI를 request에 저장
		request.setAttribute("REQ_URI", reqURI);
		//요청 URI를 request에 저장
		request.setAttribute("REQ_URL", reqURL);

		//----------------------------------- 요청 URI 작업 - end ----------------------------------------------

		//----------------------------------- 클라이언트 IP 작업 - start -----------------------------------------
		//클라이언트 IP를 획득하여 request에 저장
		String ipaddr = hReq.getHeader("X-Forwarded-For");
		if(ipaddr == null || "".equals(ipaddr)) {
			ipaddr = hReq.getHeader("Proxy-Client-IP");
			if(ipaddr == null || "".equals(ipaddr)) {
				ipaddr = hReq.getRemoteAddr();
			}
		}

		request.setAttribute(CanalFrameConstants.CLIENT_IP, ipaddr);
		//----------------------------------- 클라이언트 IP 작업 - end -----------------------------------------
		
		// 요청의 ContentType이 multipart/form-data가 아닌 경우에만 RequestWrapper로 감싸서 처리
		try {
			if (request.getContentType() == null) {
				chain.doFilter(request, response);
				return;
			}
			if (request.getContentType().startsWith("multipart/form-data")) {
				chain.doFilter(request, response);
			} else {
				RequestWrapper wrappedRequest = new RequestWrapper((HttpServletRequest) request);
				chain.doFilter(wrappedRequest, response);
			}
		} finally {
			try {
				log.info("");
			} catch (Exception e) {
				log.error("ResourceReleaseFilter", e);
			}
		}		
	}

	/**
	 * 
	 * @overridden  : @see jakarta.servlet.Filter#init(jakarta.servlet.FilterConfig)
	 * @description : init 기능을 Override하여 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		log.info("## EnvironmentSetupFilter init");
	}

	/**
	 * 
	 * @overridden  : @see jakarta.servlet.Filter#destroy()
	 * @description : destroy 기능을 Override하여 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	@Override
	public void destroy() {
		log.info("## EnvironmentSetupFilter destroy");
	}
}
