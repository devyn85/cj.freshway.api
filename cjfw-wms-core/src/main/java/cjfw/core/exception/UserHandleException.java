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
 * @description : UserHandleException 기능을 구현한 Class
 * 				  사용자 정의 Exception을 처리하기 위한 기능을 포함
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.17        sungyeon.lee       생성
 */
public class UserHandleException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = -8838508461104216866L;
	private int statusCode;         // 상태코드(Default: -1)
	private String errorCode;		//에러코드
	private String errorMessage;	//에러 메세지
	private String[] args;			//에러 메세지에 바인딩 될 argument
	private Map<String, Serializable> userData;		//오류가 발생하여도 리턴데이터 또는 다른 처리를 할 수 있는 저장도 제공해줌
	private String displayCode;		//기본 에러코드 외에 별도로 표시할 display 에러코드. (컨트롤러에서 메세지를 추가하여 처리하는 경우)
	private String displayMessage;	//기본 에러메세지 외에 별도로 표시할 display 에러메세지. (컨트롤러에서 메세지를 추가하여 처리하는 경우)
	private String[] displayArgs;	//display 에러메세지에 바인딩 될 argument

	/**
	 * 
	 * @description : UserHandleException의 생성자
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public UserHandleException(Throwable throwable) {
		super(throwable);
		this.statusCode = CanalFrameConstants.ERROR_CODE_USEREXCEP;
	}
	
	/**
	 * 
	 * @description : UserHandleException의 생성자
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public UserHandleException(String errorCode) {
		super(errorCode);
		this.statusCode = CanalFrameConstants.ERROR_CODE_USEREXCEP;
		this.errorCode = errorCode;
		this.errorMessage = MessageUtil.getMessage(errorCode);
	}

	/**
	 * 
	 * @description : UserHandleException의 생성자
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public UserHandleException(int statusCode, String errorCode) {
		super(errorCode);
		this.statusCode = statusCode;
		this.errorCode = errorCode;
		this.errorMessage = MessageUtil.getMessage(errorCode);
	}

	/**
	 * 
	 * @description : UserHandleException의 생성자
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public UserHandleException(String errorCode, Map<String, Serializable> userData) {
		super(errorCode);
		this.statusCode = CanalFrameConstants.ERROR_CODE_USEREXCEP;
		this.errorCode = errorCode;
		this.userData = userData;
	}

	/**
	 * 
	 * @description : UserHandleException의 생성자
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public UserHandleException(int statusCode, String errorCode, Map<String, Serializable> userData) {
		super(errorCode);
		this.statusCode = statusCode;
		this.errorCode = errorCode;
		this.userData = userData;
	}
	
	/**
	 * 
	 * @description : UserHandleException의 생성자
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public UserHandleException(String errorCode, Map<String, Serializable> userData, Throwable throwable) {
		super(errorCode, throwable);
		this.statusCode = CanalFrameConstants.ERROR_CODE_USEREXCEP;
		this.userData = userData;
		this.errorCode = errorCode;
		this.errorMessage = MessageUtil.getMessage(errorCode);
	}

	/**
	 * 
	 * @description : UserHandleException의 생성자
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public UserHandleException(int statusCode, String errorCode, Map<String, Serializable> userData, Throwable throwable) {
		super(errorCode, throwable);
		this.statusCode = statusCode;
		this.userData = userData;
		this.errorCode = errorCode;
		this.errorMessage = MessageUtil.getMessage(errorCode);
	}

	/**
	 * 
	 * @description : UserHandleException의 생성자
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public UserHandleException(String errorCode, Throwable throwable) {
		super(errorCode, throwable);
		this.statusCode = CanalFrameConstants.ERROR_CODE_USEREXCEP;
		this.errorCode = errorCode;
		this.errorMessage = MessageUtil.getMessage(errorCode);
	}

	/**
	 * 
	 * @description : UserHandleException의 생성자
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public UserHandleException(int statusCode, String errorCode, Throwable throwable) {
		super(errorCode, throwable);
		this.statusCode = statusCode;
		this.errorCode = errorCode;
		this.errorMessage = MessageUtil.getMessage(errorCode);
	}

	/**
	 * 
	 * @description : UserHandleException의 생성자
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public UserHandleException(String errorCode, String[] args) {
		super(errorCode);
		this.statusCode = CanalFrameConstants.ERROR_CODE_USEREXCEP;
		this.errorCode = errorCode;
		this.errorMessage = MessageUtil.getMessage(errorCode, args);
		this.args = args;
	}

	/**
	 * 
	 * @description : UserHandleException의 생성자
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public UserHandleException(int statusCode, String errorCode, String[] args) {
		super(errorCode);
		this.statusCode = statusCode;
		this.errorCode = errorCode;
		this.errorMessage = MessageUtil.getMessage(errorCode, args);
		this.args = args;
	}

	/**
	 * 
	 * @description : UserHandleException의 생성자
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public UserHandleException(String errorCode, String[] args, Throwable throwable) {
		super(errorCode, throwable);
		this.statusCode = CanalFrameConstants.ERROR_CODE_USEREXCEP;
		this.errorCode = errorCode;
		this.errorMessage = MessageUtil.getMessage(errorCode, args);
		this.args = args;
	}

	/**
	 * 
	 * @description : UserHandleException의 생성자
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public UserHandleException(int statusCode, String errorCode, String[] args, Throwable throwable) {
		super(errorCode, throwable);
		this.statusCode = statusCode;
		this.errorCode = errorCode;
		this.errorMessage = MessageUtil.getMessage(errorCode, args);
		this.args = args;
	}

	/**
	 * 
	 * @description : OO 기능을 구현한 Method
	 * 				  display 에러코드 지정하고 해당 코드에 맞는 display 메세지에 argument를 바인딩하여 지정하는 기능
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public void setDisplayMessage(String displayCode, String[] displayArgs) {
		this.displayCode = displayCode;
		this.displayMessage = MessageUtil.getMessage(displayCode, displayArgs);
		this.displayArgs = displayArgs;
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
	 * @description : getDisplayCode 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public String getDisplayCode() {
		return displayCode;
	}

	/**
	 * 
	 * @description : setDisplayCode 기능을 구현한 Method
	 * 				  display 에러코드 지정하고 해당 코드에 맞는 display메세지도 지정하는 기능
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public void setDisplayCode(String displayCode) {
		this.displayCode = displayCode;
		this.displayMessage = MessageUtil.getMessage(displayCode);
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
	 * 				  locale에 알맞는 에러 메세지를 반환하는 기능
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
	 * @description : getDisplayMessage 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public String getDisplayMessage() {
		return displayMessage;
	}

	/**
	 * 
	 * @description : getDisplayMessage 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public String getDisplayMessage(Locale locale) {
		return MessageUtil.getMessage(displayCode, displayArgs, locale);
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
	 * @description : getDisplayArgs 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public Object[] getDisplayArgs() {
		return displayArgs;
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
	 * @description : setDisplayArgs 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public void setDisplayArgs(String[] displayArgs) {
		this.displayArgs = displayArgs;
	}

	
	/**
	 * 
	 * @description : getUserData 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public Object getUserData() {
		return userData;
	}

	/**
	 * 
	 * @description : setUserData 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public void setUserData(Map<String, Serializable> userData) {
		this.userData = userData;
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