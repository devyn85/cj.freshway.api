package cjfw.wms.common.exception.dto;

import lombok.ToString;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : SangSuSung(kduimux@cj.com) 
 * @date : 2025.05.29 
 * @description : Exception 발생 시 에러코드를 수집하는 Dto Class 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.29 SangSuSung(kduimux@cj.com) 생성 </pre>
 */
@ToString
public class ExceptionDto {
	/** 오류코드 */
    private int errorCode;
    /** 오류명 */
    private String errorMsg;
    /** 오류stack명 */
    private String stackMsg;
    /** 로그기록여부 */
    private boolean isLogWrite;    
    
	/** 오류코드
	 * @return the errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * 오류코드
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	/** 오류메세지
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/** 오류코드
	 * @param errorMsg the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}    
	/** 오류Stack메세지
	 * @return the stackMsg
	 */
	public String getStackMsg() {
		return stackMsg;
	}

	/** 오류Stack메세지
	 * @param stackMsg the stackMsg to set
	 */
	public void setStackMsg(String stackMsg) {
		this.stackMsg = stackMsg;
	}

	/** 로그기록여부 */
	public boolean getIsLogWrite() {
		return isLogWrite;
	}

	/** 로그기록여부 */
	public void setIsLogWrite(boolean logWriteYn) {
		this.isLogWrite = logWriteYn;
	}	
	
	
}