package cjfw.core.web.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.SystemException;
import cjfw.core.exception.UnAuthorizedException;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.ApiResult;
import cjfw.core.utility.StringUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.22
 * @description : ExceptionAdvice 기능을 구현한 Class
 * @issues      :<pre>
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.22        sungyeon.lee       생성
 * 2025.05.30        sangsu.sung        abstract로 변경/@Order 추가/exception 메소드 추가 </pre>
 */
@Order(Ordered.LOWEST_PRECEDENCE)
@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public abstract class ExceptionAdvice {
    private final CommonDao commonDao;
    
    /** ExceptionAdvice 상속받기 사용 시 implement해야 할 method */
    public abstract ApiResult<Object> exception(HttpServletRequest request, Exception e);

    /**
     * 
     * @description : methodArgumentNotValidException 기능을 구현한 Method
     * 			  	  400 - Valid 에러
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.22        sungyeon.lee       생성
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResult methodArgumentNotValidException(HttpServletRequest request, BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<ValidFailMessage> validFailMessages = new ArrayList<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            ValidFailMessage validFailMessage = ValidFailMessage.builder()
                    .rejectField(fieldError.getField())
                    .rejectValue(fieldError.getRejectedValue())
                    .message(fieldError.getDefaultMessage())
                    .build();
            validFailMessages.add(validFailMessage);
        }
        //insertExceptionLog(request, e);
        return ApiResult.createResult(validFailMessages, "Bad Request with Not Valid Parameters", HttpServletResponse.SC_BAD_REQUEST);
    }

    /**
     * 
     * @description : unAuthorizedException 기능을 구현한 Method
     * 				  401 에러
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.22        sungyeon.lee       생성
     */
    @ExceptionHandler(UnAuthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiResult unAuthorizedException(HttpServletRequest request, UnAuthorizedException e) {
        return ApiResult.createResult(e.getErrorMessage(), e.getStatusCode());
    }

    /**
     * 
     * @description : notFoundException 기능을 구현한 Method
     * 				  404 에러
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.22        sungyeon.lee       생성
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResult noHandlerFoundException(HttpServletRequest request, Exception e) {
        //insertExceptionLog(request, e);
        return ApiResult.createResult(e.getMessage(), HttpServletResponse.SC_NOT_FOUND);
    }

    /**
     * 
     * @description : methodNotAllowedException 기능을 구현한 Method
     * 				  405 에러
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.22        sungyeon.lee       생성
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ApiResult httpRequestMethodNotSupportedException(HttpServletRequest request, Exception e) {
        //insertExceptionLog(request, e);
        return ApiResult.createResult(e.getMessage(), HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    /**
     * 
     * @description : notAcceptableException 기능을 구현한 Method
     * 				  406 에러(Client 요청 헤더의 Accept 설정 에러)
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.22        sungyeon.lee       생성
     */
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public String notAcceptableException(HttpServletRequest request, Exception e) {
        //insertExceptionLog(request, e);
        return e.getMessage();
    }


    /**
     * 
     * @description : unsupportedMediaTypeException 기능을 구현한 Method
     * 				  415 에러(Client 요청 헤더의 Content-Type 설정 에러)
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.22        sungyeon.lee       생성
     */
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public ApiResult unsupportedMediaTypeException(HttpServletRequest request, Exception e) {
        //insertExceptionLog(request, e);
        return ApiResult.createResult(e.getMessage(), HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
    }

    /**
     * 
     * @description : userHandleException 기능을 구현한 Method
     * 				  UserHandleException - 사용자정의(의도된) Exception
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.22        sungyeon.lee       생성
     */
    @ExceptionHandler(UserHandleException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResult userHandleException(HttpServletRequest request, UserHandleException e) {
        insertExceptionLog(request, e);
        return ApiResult.createResult(e.getErrorMessage(), e.getStatusCode());
    }

    /**
     * 
     * @description : systemException 기능을 구현한 Method
     * 				  SystemException, 기타 RuntimeException
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.22        sungyeon.lee       생성
     */
    @ExceptionHandler({SystemException.class, RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResult systemException(HttpServletRequest request, Exception e) {
        log.error("ExceptionAdvice : ", e);
        insertExceptionLog(request, e);
        return ApiResult.createResult("MSG.COM.ERR.001", CanalFrameConstants.ERROR_CODE_SYSEXCEP);
    }

    /**
     *
     * @description : 예외 메시지를 등록하는 Method
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.05.25        sungyeon.lee       생성
     */
    protected void insertExceptionLog(HttpServletRequest request, Exception e){
        String errorMessage = getExceptionMessageDetail(e);
        errorMessage = StringUtil.substringByByteUTF8(errorMessage, 4000);
        String uri = request.getRequestURI();

        ExnLogEntity exnLogEntity = new ExnLogEntity();
        exnLogEntity.setSvrAddr(request.getLocalAddr());
        exnLogEntity.setClntAddr((String)request.getAttribute(CanalFrameConstants.CLIENT_IP));
        exnLogEntity.setExcptCnts(errorMessage);
        exnLogEntity.setCallUri(uri);

        commonDao.insert("cJSecurityRulesService.insertLoggingError2DB", exnLogEntity);
    }

    /**
     *
     * @description : 예외처리 메시지 상세를 반환하는 기능을 구현한 Method
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2022.12.30        sungyeon.lee       생성
     */
    private String getExceptionMessageDetail(Throwable t) {
        StringBuilder sb = new StringBuilder();
        sb.append(t.toString());
        sb.append("\n");
        StackTraceElement[] trace = t.getStackTrace();
        for(int i = 0; i < 1; i++) {
            sb.append("\tat ");
            sb.append(trace[i]);
            sb.append("\n");
        }

        Throwable ourCause = t.getCause();
        if(ourCause != null) {
            sb.append(getExceptionMessageAsCause(ourCause, trace));
        }
        return sb.toString();
    }

    /**
     *
     * @description : cause를 포함한 예외처리 메시지를 반환하는 기능을 구현한 Method
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2022.12.30        sungyeon.lee       생성
     */
    private String getExceptionMessageAsCause(Throwable t, StackTraceElement[] causedTrace) {
        StringBuilder sb = new StringBuilder();

        StackTraceElement[] trace = t.getStackTrace();
        int m = trace.length - 1;
        int n = causedTrace.length - 1;
        while(m >= 0 && n >= 0 && trace[m].equals(causedTrace[n])) {
            m--;
            n--;
        }

        sb.append("Caused by: ");
        sb.append(t);
        sb.append("\n");
        for(int i = 0; i <= 0; i++) {
            sb.append("\tat ");
            sb.append(trace[i]);
            sb.append("\n");
        }
        // Recurse if we have a cause
        Throwable ourCause = t.getCause();
        if(ourCause != null) {
            sb.append(getExceptionMessageAsCause(ourCause, trace));
        }

        return sb.toString();
    }
}
