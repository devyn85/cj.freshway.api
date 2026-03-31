package cjfw.core.utility;

import java.util.Locale;
import org.springframework.context.support.MessageSourceAccessor;

import cjfw.core.model.UserContext;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.22
 * @description : MessageUtil 기능을 구현한 Class
 * 				  noticeBundle과 연동, 메시지 조회를 하기 위한 기능 포함
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.22        sungyeon.lee       생성
 */
public class MessageUtil {
	
	/**
	 * 
	 * @description : MessageUtil의 생성자
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	private MessageUtil() {}
	
	private static MessageSourceAccessor messageSourceAccessor = (MessageSourceAccessor)ContextUtil.getBean("messageSourceAccessor");

	/**
	 * 
	 * @description : getMessage 기능을 구현한 Method
	 * 				  key로 정의된 message를 가져옴(대상파일 noticeBundle)
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	public static String getMessage(String key) {
		UserContext userContext = ContextUtil.getBean(UserContext.class);
		if(userContext != null){
			return messageSourceAccessor.getMessage(key, userContext.getLocale());
		} else{
			return messageSourceAccessor.getMessage(key);
		}
	}

	/**
	 * 
	 * @description : getMessage 기능을 구현한 Method
	 * 				  key로 정의된 message를 가져옴(대상파일 noticeBundle)
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	public static String getMessage(String key, Object[] args) {
		// arguments 값도 다국어 처리
		int idx = 0;
		for (Object arg : args) {
			arg = messageSourceAccessor.getMessage((String) arg);
			args[idx] = arg;
			idx++;
		}
		
		return messageSourceAccessor.getMessage(key, args);
	}

	/**
	 * 
	 * @description : getMessage 기능을 구현한 Method
	 * 				  해당 locale의 message를 가져옴
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	public static String getMessage(String key, Locale locale) {
		return messageSourceAccessor.getMessage(key, locale);
	}

	/**
	 * 
	 * @description : getMessage 기능을 구현한 Method
	 * 				  해당 locale의 message를 가져옴
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	public static String getMessage(String key, Object[] args, Locale locale) {
		return messageSourceAccessor.getMessage(key, args, locale);
	}
}
