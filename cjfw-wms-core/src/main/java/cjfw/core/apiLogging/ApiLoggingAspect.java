package cjfw.core.apiLogging;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;


/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : yewon.kim
 * @date        : 2026.01.25
 * @description : API 호출 및 수신 로그 DB
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2026.01.25        yewon.kim          최초생성
 */
@Slf4j
@Aspect
@Component
public class ApiLoggingAspect {

    private final LoggingService loggingService;

    private final TransactionTemplate requiresNewTx;

    public ApiLoggingAspect(LoggingService loggingService, PlatformTransactionManager transactionManager) {
        this.loggingService = loggingService;
                    TransactionTemplate tx = new TransactionTemplate(transactionManager);
               tx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
                this.requiresNewTx = tx;
    }

    @Pointcut("execution(public * cjfw.core.common.ApiClient.*(..))")
    public void apiClient() {}

    @Pointcut("execution(public * *..etcApi.service.CrmApiService.*(..))")
    public void crmApi() {}

    @Pointcut("execution(public * *..etcApi.service.OfnApiService.*(..))")
    public void ofnApi() {}


    /**
	 * 
	 * @description : WMS -> CRM 호출 API
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2026.01.25        yewon.kim          최초생성
	 */
    @Around("apiClient()")
	public Object logOutBoundApi(ProceedingJoinPoint joinPoint) throws Throwable {
        //■ 1. API 호출 이전 데이터 수집
        Object[] args = joinPoint.getArgs();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String httpMethod = request.getMethod();
        String sourceIp = request.getRemoteAddr();
        String url = (String) args[0];
        Map<String, Object> requestMap = (Map) args[3];
        String worker = (String) requestMap.get("CREATEDBYNAME");

        Timestamp requestTimestamp = new Timestamp(System.currentTimeMillis());
        Object result;
        boolean success = false;
        String responseBody = null;
        String errorMessage = null;
        String errorStackTrace = null;

        try{
            //API 메소드 실행
            result = joinPoint.proceed();

            //■ 2. API 호출 성공
            success = true;
            responseBody = result.toString();
        }catch (Throwable e){
            //■ 2. API 호출 실패
            success = false;
            errorMessage = e.getMessage();
            errorStackTrace = getStackTrace(e);

            throw e;
        }finally{
            //■ 4. API 호출 (성공/실패) 후 로그 DB 저장
            Timestamp responseTimestamp = new Timestamp(System.currentTimeMillis());
            long latency = responseTimestamp.getTime() - requestTimestamp.getTime();

            Map<String, Object> paramsMap = new HashMap<String, Object>();

            paramsMap.put("HTTPMETHOD", httpMethod);
            paramsMap.put("URL", url);
            paramsMap.put("SOURCEIP", sourceIp);
            paramsMap.put("REQUESTTIME", requestTimestamp);
            paramsMap.put("RESPONSETIME", responseTimestamp);
            paramsMap.put("LATENCY", latency);
            paramsMap.put("REQUESTBODY", requestMap.toString());
            paramsMap.put("RESPONSEBODY", responseBody);
            paramsMap.put("STATUS", success ? "S" : "E");
            paramsMap.put("ERRORMESSAGE", errorMessage);
            paramsMap.put("ERRORSTACKTRACE", errorStackTrace);
            paramsMap.put("WORKER", worker);

            try {
                requiresNewTx.executeWithoutResult(status -> loggingService.saveApiLog(paramsMap));
            } catch (Exception logEx) {
                logEx.printStackTrace();
            }
        }

        return result;
	}

    private String getStackTrace(Throwable ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        return sw.toString();
    }


    /**
     *
     * @description : CRM,OFN -> WMS 호출 API
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2026.01.25        yewon.kim          최초생성
     */
    @Around("crmApi() || ofnApi()")
    public Object logInBoundApi(ProceedingJoinPoint joinPoint) throws Throwable {
        //■ 1. API 호출 이전 데이터 수집
        Object[] args = joinPoint.getArgs();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String httpMethod = request.getMethod();
        String sourceIp = request.getRemoteAddr();
        String url = request.getRequestURI();
        Map<String, Object> requestMap = (Map) args[0];
        String worker = (String) requestMap.get("CREATEDBYNAME");

        Timestamp requestTimestamp = new Timestamp(System.currentTimeMillis());

        Object result;
        boolean success = false;
        String responseBody = null;
        String errorMessage = null;
        String errorStackTrace = null;

        try{
            //API 메소드 실행
            result = joinPoint.proceed();

            //■ 2. API 호출 성공
            success = true;
            responseBody = new ObjectMapper().convertValue(result, Map.class).get("ds_list").toString();
        }catch (Throwable e){
            //■ 2. API 호출 실패
            success = false;
            errorMessage = e.getMessage();
            errorStackTrace = getStackTrace(e);

            throw e;
        }finally{
            //■ 4. API 호출 (성공/실패) 후 로그 DB 저장
            Timestamp responseTimestamp = new Timestamp(System.currentTimeMillis());
            long latency = responseTimestamp.getTime() - requestTimestamp.getTime();

            Map<String, Object> paramsMap = new HashMap<String, Object>();

            paramsMap.put("HTTPMETHOD", httpMethod);
            paramsMap.put("URL", url);
            paramsMap.put("SOURCEIP", sourceIp);
            paramsMap.put("REQUESTTIME", requestTimestamp);
            paramsMap.put("RESPONSETIME", responseTimestamp);
            paramsMap.put("LATENCY", latency);
            paramsMap.put("REQUESTBODY", requestMap.toString());
            paramsMap.put("RESPONSEBODY", responseBody);
            paramsMap.put("STATUS", success ? "S" : "E");
            paramsMap.put("ERRORMESSAGE", errorMessage);
            paramsMap.put("ERRORSTACKTRACE", errorStackTrace);
            paramsMap.put("WORKER", worker);

            try {
                requiresNewTx.executeWithoutResult(status -> loggingService.saveApiLog(paramsMap));
            } catch (Exception logEx) {
                // 로그 저장 실패가 본 로직(업무 트랜잭션)에 영향 주지 않도록 방어
                logEx.printStackTrace();
            }
        }

        return result;
    }
}