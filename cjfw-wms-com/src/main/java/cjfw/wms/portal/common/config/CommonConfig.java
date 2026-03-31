/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.portal.common.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.LocaleUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableAsync;

import cjfw.core.exception.ExceptionManager;
import cjfw.core.exception.SystemException;
import cjfw.core.utility.StringUtil;

@Slf4j
@Configuration
@EnableAsync
public class CommonConfig {
	
	@Value("${cf.defaultLang:ko_KR}")
	private String defaultLang;

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("messages/message/common");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }

    @Bean
    public MessageSourceAccessor messageSourceAccessor() throws SystemException {
        return new MessageSourceAccessor(messageSource(), LocaleUtils.toLocale(StringUtil.toLocaleFormat(defaultLang)));
    }
	
	@Bean
	public ExceptionManager exceptionManager() {
		return new ExceptionManager();
	}

}
