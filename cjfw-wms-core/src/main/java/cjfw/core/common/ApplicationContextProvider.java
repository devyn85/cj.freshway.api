package cjfw.core.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.17
 * @description : Spring Application Context을 편하게 사용하기 위한 기능을 구현한 Provider Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.17        sungyeon.lee       생성
 */
@Component
public class ApplicationContextProvider implements ApplicationContextAware {
    
	private static class AplicationContextHolder {
		private static final InnerContextResource CONTEXT_PROV = new InnerContextResource();

		private AplicationContextHolder() {
			super();
		}
	}

	private static final class InnerContextResource {
		private ApplicationContext context;

		private InnerContextResource() {
			super();
		}

		private void setContext(ApplicationContext context) {
			this.context = context;
		}
	}
	
	/**
	 * 
	 * @description : getApplicationContext 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public static ApplicationContext getApplicationContext() {
		return AplicationContextHolder.CONTEXT_PROV.context;
	}

	/**
	 * 
	 * @overridden  : @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 * @description : setApplicationContext 기능을 Override하여 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	@Override
	public void setApplicationContext(ApplicationContext ac) {
		AplicationContextHolder.CONTEXT_PROV.setContext(ac);
	}
}
