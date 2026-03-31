package cjfw.core.exception;

import java.sql.SQLException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.17
 * @description : ExceptionManager 기능을 구현한 Class
 * 				  Exception 핸들링을 위한 기능 정의 
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.17        sungyeon.lee       생성
 */
@Aspect
public class ExceptionManager {

	private static final Logger log = LoggerFactory.getLogger(ExceptionManager.class);

	/**
	 * 
	 * @description : exceptionHandler 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	@AfterThrowing(pointcut = "(execution(* *..service.*Service.*(..)))", throwing = "ex")
	public void exceptionHandler(JoinPoint joinPoint, Throwable ex) throws Throwable {

		// UserHandleException 일 경우에는 throw
		// RuntimeException 일 경우에만 SystemException 으로 wrapping
		if(ex instanceof UserHandleException) {
			if(ExceptionUtils.getRootCause(ex) instanceof SQLException) {
				log.error("ExceptionManager.exceptionHandler().SQLException : ", ex);
			}
			throw ex;
		} else {
			SystemException systemException = new SystemException(ex);
			//log.error("ExceptionManager.exceptionHandler().SystemException : ", ex);
			throw systemException;
		}
	}
}
