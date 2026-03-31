/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.portal.common.config;

import java.util.List;

import org.apache.commons.lang3.LocaleUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import cjfw.auth.common.CJSecurityRulesInterceptor;
import cjfw.auth.common.SSOInterceptor;
import cjfw.auth.common.service.CJSecurityRulesService;
import cjfw.core.model.UserContext;
import cjfw.core.model.factory.UserContextFactory;
import cjfw.core.utility.StringUtil;
import cjfw.core.web.mvc.SimpleModuleMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Value("${cf.defaultLang}")
	private String defaultLang;

	@Value("${cf.auth.pattern}")
	private String[] authPattern;


	private final CJSecurityRulesService cjSecurityRulesService;
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(cJSecurityRulesInterceptor())
		.addPathPatterns(authPattern);
		
		// SSO Interceptor 추가
//		registry.addInterceptor(ssoInterceptor())
//		.addPathPatterns("/api/cm/sso/v1.0/login");
	}

	@Bean
	public CJSecurityRulesInterceptor cJSecurityRulesInterceptor(){
		return new CJSecurityRulesInterceptor(cjSecurityRulesService);
	}
	
	@Bean
	public SSOInterceptor ssoInterceptor(){
		return new SSOInterceptor();
	}

	@Bean
	public RequestContextListener requestContextListener(){
		return new RequestContextListener();
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowCredentials(true)
				// 도메인이 여러개일 경우   임시
				.allowedOriginPatterns("http://localhost:8088", "https://*.abc.com")
				.allowedOriginPatterns("*")
				.allowedHeaders("*")
				.allowedMethods("*")
				.exposedHeaders("*")
				.maxAge(3600L);
	}

	@Bean
	public LocaleResolver localeResolver() {
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(LocaleUtils.toLocale(StringUtil.toLocaleFormat(defaultLang)));
		return localeResolver;
	}

	@Bean
	@Scope(value="request", proxyMode = ScopedProxyMode.TARGET_CLASS)
	public UserContext userContext() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		UserContext userContext = new UserContextFactory().createUserContext(request);

		// UserContext 정보 로깅
		System.out.println("========================================");
		System.out.println("UserContext Debug Info:");
		System.out.println("  - isValid: " + userContext.isValid());
		System.out.println("  - userId: " + userContext.getUserId());
		System.out.println("  - userNm: " + userContext.getUserNm());
		System.out.println("  - dccode: " + userContext.getDccode());
		System.out.println("  - storerkey: " + userContext.getStorerkey());
		System.out.println("  - organize: " + userContext.getOrganize());
		System.out.println("  - area: " + userContext.getArea());
		System.out.println("  - spid: " + userContext.getSpid());
		System.out.println("  - lang: " + userContext.getLang());
		System.out.println("  - authority: " + userContext.getAuthority());
		System.out.println("  - roles: " + userContext.getRoles());
		System.out.println("  - userNo: " + userContext.getUserNo());
		System.out.println("  - emailAddr: " + userContext.getEmailAddr());
		System.out.println("  - emptype: " + userContext.getEmptype());
		System.out.println("  - empNo: " + userContext.getEmpNo());
		System.out.println("  - custkey: " + userContext.getCustkey());
		System.out.println("  - repUserIdYn: " + userContext.getRepUserIdYn());
		System.out.println("========================================");

		return userContext;
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(jackson2HttpMessageConverter());
	}

	@Bean
	public MappingJackson2HttpMessageConverter jackson2HttpMessageConverter(){
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(new SimpleModuleMapper());
		return converter;
	}

}
