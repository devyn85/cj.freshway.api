package cjfw.core.utility;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Locale;
import org.apache.commons.lang3.LocaleUtils;
import org.springframework.web.servlet.LocaleResolver;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.22
 * @description : OO 기능을 구현한 Controller Class
 * 				  LocaleUtil에서는 SessionLocaleResolver의 resolveLocale()을 직접 호출하지는 않지만,
 * 				  SessionLocaleResolver의 세션명을 그대로 사용하고 있고, 또 localeResolver가 bean으로 등록되어 있기 때문에
 * 				  RequestContextUtils.getLocale(request) 과 같은 메서드에도 대응할 수 있음
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.22        sungyeon.lee       생성
 */
public class LocaleUtil {
	/**
	 * 
	 * @description : LocaleUtil의 생성자
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	private LocaleUtil() {}
	
	private static LocaleResolver localeResolver = (LocaleResolver)ContextUtil.getBean("localeResolver");

	/**
	 * 	기본 언어의 ISO-639 2-letter 형식 문자열
	 */
	public static final String DEFAULT_LANG = resolveDefaultLanguage();

	/**
	 * 	기본 언어의 로케일 객체
	 */
	public static final Locale DEFAULT_LOCALE = toLocale(DEFAULT_LANG);

	/**
	 * 
	 * @description : resolveDefaultLanguage 기능을 구현한 Method
	 * 				  기본 언어의 ISO-639 2-letter 형식 문자열을 반환
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	private static String resolveDefaultLanguage() {
		return ContextUtil.getProperty("cf.defaultLang");
	}

	/**
	 * 
	 * @description : getDefaultLanguage 기능을 구현한 Method
	 * 				  기본 언어의 ISO-639 2-letter 형식 문자열을 반환
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	public static String getDefaultLanguage() {
		return DEFAULT_LANG;
	}

	/**
	 * 
	 * @description : getDefaultLocale 기능을 구현한 Method
	 * 				  기본 언어의 Locale 객체를 반환
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	public static Locale getDefaultLocale() {
		return DEFAULT_LOCALE;
	}

	/**
	 * 
	 * @description : getUserLocale 기능을 구현한 Method
	 * 				  현재 사용자 세션의 Locale 객체를 반환
	 * 				  세션 Locale 객체가 존재하지 않을 경우 (ex: 로그인 하지 않았을 경우) 기본 언어 Locale 객체를 반환
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	public static Locale getUserLocale(HttpServletRequest request) {
		Locale locale = localeResolver.resolveLocale(request);
		return (locale != null) ? (Locale)locale : getDefaultLocale();
	}
	
	/**
	 * 
	 * @description : getUserLocaleString 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	public static String getUserLocaleString(HttpServletRequest request) {
		Locale locale = localeResolver.resolveLocale(request);
		locale = (locale != null) ? (Locale)locale : getDefaultLocale();
		return locale.toString().toLowerCase();  // Locale DB저장 방식이 소문자임으로 소문자로 변환하여 리턴  
	}

	/**
	 * 
	 * @description : toLocale 기능을 구현한 Method
	 * 				  문자열의 Locale정보를 Locale객체로 반환
	 * 				  문자열이 null 인경우 DEFAULT_LANG을 기준 Locale 반환
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	public static Locale toLocale(String localeLetter) {
		if(localeLetter == null) {
			localeLetter = getDefaultLanguage();
		}
		return LocaleUtils.toLocale(StringUtil.toLocaleFormat(localeLetter));
	}
	
	/**
	 * 
	 * @description : setUserLocale 기능을 구현한 Method
	 * 				  현재 사용자 세션에 인수로 받은 Locale객체를 바인딩
	 * 				  g_lang 이라는 이름의 ISO-639-1 (2-letter standard) 문자열도 세션에 바인딩
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	public static void setUserLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		localeResolver.setLocale(request, response, locale);
	}
}
