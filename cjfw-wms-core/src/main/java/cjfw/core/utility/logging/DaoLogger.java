package cjfw.core.utility.logging;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cjfw.core.exception.UserHandleException;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.20
 * @description : DaoLogger 기능을 구현한 Controller Class
 * 				  CommonDao 호출에 대한 로깅을 하기 위해 정의되었으며, 쿼리 실행시간에 대해 aspect를 활용해 처리 함
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.20        sungyeon.lee       생성
 */
@Aspect
@Component
public class DaoLogger {
	private static final Logger log = LoggerFactory.getLogger(DaoLogger.class);
	@Value("${cf.maxResultRows}")
	private int MAXRESULTROWS = 50000;

	/**
	 * 
	 * @description : daoMethods 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.20        sungyeon.lee       생성
	 */
	@Pointcut("execution(* *..dataaccess.*Dao.*(..))")
	public void daoMethods() {
		log.info("### DaoLogger daoMethods");
	}
	
	/**
	 * 
	 * @description : daoMethod 기능을 구현한 Method
	 * 			      pointcut과 advice 조건에 의해 invoke 되는 것을 의도함
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.20        sungyeon.lee       생성
	 */
	@SuppressWarnings("unchecked")
	@Around("daoMethods()")
	public Object daoMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		StopWatch stopWatch = new StopWatch();
		String sql = joinPoint.getArgs()[0].toString();
		StringBuilder logMessage = new StringBuilder("Sql [");
		logMessage.append(sql).append("] called.");
		
		log.info(logMessage.toString());
		stopWatch.start();
		
		/* method invoke */
		Object result = joinPoint.proceed();

		stopWatch.stop();
		/* logging elapsed time */
		StringBuilder elapsedTime = new StringBuilder("Sql [");
		elapsedTime.append(sql).append("] elapsed time : ");
		elapsedTime.append(stopWatch.getTime()).append(" ms.");
		log.info(elapsedTime.toString());
		
		if(result instanceof List) {
			List<Object> selectListResult = (ArrayList<Object>) result;
			
			log.info("Sql [{}] select list size : {}.", sql, selectListResult.size());
			if(selectListResult.size() > MAXRESULTROWS) {
				throw new UserHandleException("MSG.COM.ERR.021", new String[]{Integer.toString(MAXRESULTROWS)});
			}
		}
		
		return result;
	}
}