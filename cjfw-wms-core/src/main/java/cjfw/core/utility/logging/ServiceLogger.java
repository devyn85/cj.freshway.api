package cjfw.core.utility.logging;

import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.20
 * @description : ServiceLogger 기능을 구현한 Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.20        sungyeon.lee       생성
 */
@Aspect
@Component
public class ServiceLogger {
	private static final Logger log = LoggerFactory.getLogger(ServiceLogger.class);

	/**
	 * 
	 * @description : serviceMethods 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.20        sungyeon.lee       생성
	 */
	@Pointcut("execution(* *..service.*Service.*(..))")
	public void serviceMethods() {
		log.info("### ServiceLogger serviceMethods");
	}

	/**
	 * 
	 * @description : serviceMethodProfile 기능을 구현한 Method
	 * 				  pointcut과 advice 조건에 의해 invoke
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.20        sungyeon.lee       생성
	 */
	@Around("serviceMethods()")
	public Object serviceMethodProfile(ProceedingJoinPoint joinPoint) throws Throwable {
		StopWatch stopWatch = new StopWatch();
		String methodName = joinPoint.getSignature().toShortString();
		String target = joinPoint.getTarget().toString();
		String classPath = target.substring(0, target.lastIndexOf('@'));
		/* logging service method call. */
		StringBuilder logMessage = new StringBuilder(classPath);
		logMessage.append(".").append(methodName).append("() called.");
		log.info(logMessage.toString());
		stopWatch.start();
		
		/* method invoke */
		Object result = joinPoint.proceed();

		stopWatch.stop();
		/* logging elapsed time */
		StringBuilder elapsedTime = new StringBuilder("Service method [");
		elapsedTime.append(methodName).append("] elapsed time : ");
		elapsedTime.append(stopWatch.getTime()).append(" ms.");
		log.info(elapsedTime.toString());
		
		return result;
	}
}
