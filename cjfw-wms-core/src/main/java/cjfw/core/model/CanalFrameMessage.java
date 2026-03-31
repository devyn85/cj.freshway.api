package cjfw.core.model;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cjfw.core.email.model.MailAttachFile;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.20
 * @description : CanalFrameMessage 기능을 구현한 Class
 * 				  이메일 VO객체와 SMS/MMS VO객체의 상위 클래스
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.20        sungyeon.lee       생성
 */
public abstract class CanalFrameMessage implements Serializable {
	private static final long serialVersionUID = 2980738489174753252L;
	// 파일로그 저장시 구분자
	public static final String SEP_1 = "<#1>";			//구분자1 - 메일로그의 적재단위(수신자 단위)를 구분하는 구분자
	public static final String SEP_2 = "<#2>";			//구분자2 - 메일로그와 첨부파일로그를 구분하는 구분자 
	public static final String SEP_3 = "<#3>";			//구분자3 - 메일로그에서 각 항목을 구분하는 구분자 
	public static final String SEP_4 = "<#4>";			//구분자4 - 첨부파일로그 리스트에서 각 첨부파일로그를 구분하는 구분자 
	public static final String SEP_5 = "<#5>";			//구분자5 - 각 첨부파일로그 내에서 각 항목을 구분하는 구분자 
	protected String serviceName;						//로그파일에 남길 서비스명
	protected List<MailAttachFile> attachFileList;		// NOSONAR //첨부파일명 리스트 

	/**
	 * 
	 * @description : CanalFrameMessage의 생성자
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.20        sungyeon.lee       생성
	 */
	protected CanalFrameMessage() {
		this.attachFileList = new ArrayList<MailAttachFile>();
	}

	/**
	 * 
	 * @description : getFileLogContents 기능을 구현한 Method
	 * 				  파일로그의 형식에 맞추어 StringBuilder 반환
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.20        sungyeon.lee       생성
	 */
	public abstract StringBuilder getFileLogContents(String curTime);

	/**
	 * 
	 * @description : getFileLogAttachInfo 기능을 구현한 Method
	 * 				  파일로그의 내용에 들어가는 첨부파일 정보 반환
	 * 				  첨부파일이 여러개일 경우 구분자 추가
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.20        sungyeon.lee       생성
	 */
	public String getFileLogAttachInfo() {
		StringBuilder sb = new StringBuilder();
		for(int k = 0; k < getAttachFileList().size(); k++) {
			MailAttachFile attachFile = getAttachFileList().get(k);
			//첨부파일이 여러개일 경우 구분자 추가
			if(k > 0) {
				sb.append(SEP_4);
			}
			//첨부파일 물리경로
			String targetFilePath =
					new StringBuilder(attachFile.getPhysicalFilePath()).append(File.separator)
							.append(attachFile.getPhysicalFileName()).toString();
			sb.append(attachFile.getAttachFileName()).append(SEP_5).append(targetFilePath);
		}
		return sb.toString();
	}

	/**
	 * 
	 * @description : getServiceName 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.20        sungyeon.lee       생성
	 */
	public String getServiceName() {
		return serviceName;
	}

	/**
	 * 
	 * @description : setServiceName 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.20        sungyeon.lee       생성
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	/**
	 * 
	 * @description : getAttachFileList 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.20        sungyeon.lee       생성
	 */
	public List<MailAttachFile> getAttachFileList() {
		return attachFileList;
	}

	/**
	 * 
	 * @description : setAttachFileList 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.20        sungyeon.lee       생성
	 */
	public void setAttachFileList(List<MailAttachFile> attachFileList) {
		this.attachFileList = attachFileList;
	}
}
