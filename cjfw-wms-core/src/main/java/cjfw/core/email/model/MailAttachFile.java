package cjfw.core.email.model;

import java.io.Serializable;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.17
 * @description : MailAttachFile 기능을 구현한 Controller Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.17        sungyeon.lee       생성
 */
public class MailAttachFile implements Serializable{

	private static final long serialVersionUID = -7950538656454451628L;
	private String attachFileName;  	//원본 파일명
	private String physicalFilePath;	//업로드 파일경로
	private String physicalFileName;	//업로드 파일명
	private String fileType;			//파일 종류 (MMS 전송시 사용)

	/**
	 * 
	 * @description : getAttachFileName 기능을 구현한 Method
	 * 				  첨부파일명을 반환하는 기능
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public String getAttachFileName() {
		return attachFileName;
	}

	/**
	 * 
	 * @description : setAttachFileName 기능을 구현한 Method
	 * 				  첨부파일명을 받아 AttatchFile 객체에 설정하는 기능
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public void setAttachFileName(String attachFileName) {
		this.attachFileName = attachFileName;
	}

	/**
	 * 
	 * @description : getPhysicalFilePath 기능을 구현한 Method
	 * 				  파일의 절대경로를 반환하는 기능
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public String getPhysicalFilePath() {
		return physicalFilePath;
	}

	/**
	 * 
	 * @description : setPhysicalFilePath 기능을 구현한 Method
	 * 				  파일의 절대경로를 설정하는 기능
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public void setPhysicalFilePath(String physicalFilePath) {
		this.physicalFilePath = physicalFilePath;
	}

	/**
	 * 
	 * @description : getPhysicalFileName 기능을 구현한 Method
	 * 				  파일의 절대경로를 포함한 파일명을 반환하는 기능
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public String getPhysicalFileName() {
		return physicalFileName;
	}

	/**
	 * 
	 * @description : setPhysicalFileName 기능을 구현한 Method
	 * 				  파일의 절대경로를 포함한 파일명을 설정하는 기능
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public void setPhysicalFileName(String physicalFileName) {
		this.physicalFileName = physicalFileName;
	}

	/**
	 * 
	 * @description : getFileType 기능을 구현한 Method
	 * 				  파일유형을 반환하는 기능
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public String getFileType() {
		return fileType;
	}

	/**
	 * 
	 * @description : setFileType 기능을 구현한 Method
	 * 				  파일유형을 설정하는 기능
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
}