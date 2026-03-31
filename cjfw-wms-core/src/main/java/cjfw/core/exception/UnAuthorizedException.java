package cjfw.core.exception;

import java.io.Serializable;
import java.util.Locale;
import java.util.Map;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.utility.MessageUtil;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.17
 * @description : UnAuthorizedException 기능을 구현한 Controller Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.17        sungyeon.lee       생성
 */
public class UnAuthorizedException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -8838508461104216866L;
    private int statusCode;         // 상태코드(Default: -1)
    private String errorCode;		//에러코드
    private String errorMessage;	//에러 메세지
    private String[] args;			//에러 메세지에 바인딩 될 argument

    /**
     * 
     * @description : UnAuthorizedException의 생성자
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.17        sungyeon.lee       생성
     */
    public UnAuthorizedException(Throwable throwable) {
        super(throwable);
        this.statusCode = CanalFrameConstants.ERROR_CODE_USEREXCEP;
    }

    /**
     * 
     * @description : UnAuthorizedException의 생성자
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.17        sungyeon.lee       생성
     */
    public UnAuthorizedException(String errorCode) {
        super(errorCode);
        this.statusCode = CanalFrameConstants.ERROR_CODE_USEREXCEP;
        this.errorCode = errorCode;
        this.errorMessage = MessageUtil.getMessage(errorCode);
    }

    /**
     * 
     * @description : UnAuthorizedException의 생성자
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.17        sungyeon.lee       생성
     */
    public UnAuthorizedException(int statusCode, String errorCode) {
        super(errorCode);
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.errorMessage = MessageUtil.getMessage(errorCode);
    }

    /**
     * 
     * @description : UnAuthorizedException의 생성자
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.17        sungyeon.lee       생성
     */
    public UnAuthorizedException(String errorCode, Map<String, Serializable> userData) {
        super(errorCode);
        this.statusCode = CanalFrameConstants.ERROR_CODE_USEREXCEP;
        this.errorCode = errorCode;
    }

    /**
     * 
     * @description : UnAuthorizedException의 생성자
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.17        sungyeon.lee       생성
     */
    public UnAuthorizedException(int statusCode, String errorCode, Map<String, Serializable> userData) {
        super(errorCode);
        this.statusCode = statusCode;
        this.errorCode = errorCode;
    }

    /**
     * 
     * @description : UnAuthorizedException의 생성자
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.17        sungyeon.lee       생성
     */
    public UnAuthorizedException(String errorCode, Map<String, Serializable> userData, Throwable throwable) {
        super(errorCode, throwable);
        this.statusCode = CanalFrameConstants.ERROR_CODE_USEREXCEP;
        this.errorCode = errorCode;
        this.errorMessage = MessageUtil.getMessage(errorCode);
    }

    /**
     * 
     * @description : UnAuthorizedException의 생성자
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.17        sungyeon.lee       생성
     */
    public UnAuthorizedException(int statusCode, String errorCode, Map<String, Serializable> userData, Throwable throwable) {
        super(errorCode, throwable);
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.errorMessage = MessageUtil.getMessage(errorCode);
    }

    /**
     * 
     * @description : UnAuthorizedException의 생성자
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.17        sungyeon.lee       생성
     */
    public UnAuthorizedException(String errorCode, Throwable throwable) {
        super(errorCode, throwable);
        this.statusCode = CanalFrameConstants.ERROR_CODE_USEREXCEP;
        this.errorCode = errorCode;
        this.errorMessage = MessageUtil.getMessage(errorCode);
    }

    /**
     * 
     * @description : UnAuthorizedException의 생성자
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.17        sungyeon.lee       생성
     */
    public UnAuthorizedException(int statusCode, String errorCode, Throwable throwable) {
        super(errorCode, throwable);
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.errorMessage = MessageUtil.getMessage(errorCode);
    }

    /**
     * 
     * @description : UnAuthorizedException의 생성자
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.17        sungyeon.lee       생성
     */
    public UnAuthorizedException(String errorCode, String[] args) {
        super(errorCode);
        this.statusCode = CanalFrameConstants.ERROR_CODE_USEREXCEP;
        this.errorCode = errorCode;
        this.errorMessage = MessageUtil.getMessage(errorCode, args);
        this.args = args;
    }

    /**
     * 
     * @description : UnAuthorizedException의 생성자
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.17        sungyeon.lee       생성
     */
    public UnAuthorizedException(int statusCode, String errorCode, String[] args) {
        super(errorCode);
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.errorMessage = MessageUtil.getMessage(errorCode, args);
        this.args = args;
    }

    /**
     * 
     * @description : UnAuthorizedException의 생성자
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.17        sungyeon.lee       생성
     */
    public UnAuthorizedException(String errorCode, String[] args, Throwable throwable) {
        super(errorCode, throwable);
        this.statusCode = CanalFrameConstants.ERROR_CODE_USEREXCEP;
        this.errorCode = errorCode;
        this.errorMessage = MessageUtil.getMessage(errorCode, args);
        this.args = args;
    }

    /**
     * 
     * @description : UnAuthorizedException의 생성자
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.17        sungyeon.lee       생성
     */
    public UnAuthorizedException(int statusCode, String errorCode, String[] args, Throwable throwable) {
        super(errorCode, throwable);
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.errorMessage = MessageUtil.getMessage(errorCode, args);
        this.args = args;
    }


    /**
     * 
     * @description : getErrorCode 기능을 구현한 Method
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.17        sungyeon.lee       생성
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * 
     * @description : setErrorCode 기능을 구현한 Method
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.17        sungyeon.lee       생성
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }


    /**
     * 
     * @description : getErrorMessage 기능을 구현한 Method
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.17        sungyeon.lee       생성
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * 
     * @description : getErrorMessage 기능을 구현한 Method
     * 				  ocale에 알맞는 에러 메세지를 반환, 멤버 변수의 기본 에러 메세지는 변경하지 않음
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.17        sungyeon.lee       생성
     */
    public String getErrorMessage(Locale locale) {
        return MessageUtil.getMessage(errorCode, args, locale);
    }

    /**
     * 
     * @description : setErrorMessage 기능을 구현한 Method
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.17        sungyeon.lee       생성
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    /**
     * 
     * @description : getArgs 기능을 구현한 Method
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.17        sungyeon.lee       생성
     */
    public Object[] getArgs() {
        return args;
    }

    /**
     * 
     * @description : setArgs 기능을 구현한 Method
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.17        sungyeon.lee       생성
     */
    public void setArgs(String[] args) {
        this.args = args;
    }


    /**
     * 
     * @description : setStatusCode 기능을 구현한 Method
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.17        sungyeon.lee       생성
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * 
     * @description : getStatusCode 기능을 구현한 Method
     * 				  Client에 보여줄 Status code를 반환
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.17        sungyeon.lee       생성
     */
    public int getStatusCode() {
        return statusCode;
    }


}
