package cjfw.core.email.model;

import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.email.utility.MailUtil;
import cjfw.core.exception.SystemException;
import cjfw.core.model.CanalFrameMessage;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.17
 * @description : OO 기능을 구현한 Controller Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.17        sungyeon.lee       생성
 */
public class MailMessage extends CanalFrameMessage {

	private static final long serialVersionUID = -4584095286736232715L;
	private static final Logger log = LoggerFactory.getLogger(MailMessage.class);

	//메일 작성 관련 정보
	private String sender;			//보내는 사람 이메일 주소
	private String title;			//제목
	private String rcvrNm; 	 		//수신자명
	private String rcvrEmailAddr;	//수신자 이메일 주소
	private String templateFile;	//템플릿 파일명
	private String senderId;		//발신자 ID
	private String contents;		//메일 내용
	private Map<String, String> contentsArgsMap;	//사용자 아규먼트 맵 ({key1:aaa, key2:bbb ...... })
	private List<InternetAddress> recipients;		//받는 사람 이메일 주소 목록 (홍길동<hong@cj.net>;김대길<kim@cj.net>;..)
	
	// smtp 접속정보
	private String smtpHost;		//smtp host
	private String smtpId;			//smtp access id
	private String smtpPw;			//smtp access pwd

	/**
	 * 
	 * @description : MailMessage의 생성자
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public MailMessage() {
		super();
		contentsArgsMap = new HashMap<String, String>();
		recipients = new ArrayList<InternetAddress>();
	}

	/**
	 * 
	 * @description : MailMessage의 생성자
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public MailMessage(String serviceName) {
		this();
		this.setServiceName(StringUtils.defaultString(serviceName));
	}

	/**
	 * 
	 * @description : addContentsArg 기능을 구현한 Method
	 * 				  contentsArgsMap 세팅을 보다 용이하게 하기위한 setter 기능
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public void addContentsArg(String key, String value) {
		contentsArgsMap.put(key, value);
	}

	/**
	 * 
	 * @overridden  : @see java.lang.Object#toString()
	 * @description : toString 기능을 Override하여 구현한 Method
	 * 				  수신자 목록 등을 출력 할 수 있도록 Override하여 활용
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("recipients = ");
		try {
			for(InternetAddress recipient : recipients) {
				//캐릭터셋을 변환하여 저장했으므로 역변환하여 보여줌
				sb.append(new String(recipient.toString().getBytes("ISO-8859-1"), CanalFrameConstants.DEFAULT_CHARACTERSET));
			}
		} catch(Exception e) {
			log.error("MailMessage.toString.Exception : ", e);
		}

		return sb.toString();
	}

	/**
	 * 
	 * @description : addRecipient 기능을 구현한 Method
	 * 				  메일 수신자 문자열을 추가하면 InternetAddress 의 형태로 수신자 목록에 등록하는 기능
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public void addRecipient(String recipient) {
		try {
			recipients.add(new InternetAddress(MailUtil.convISO(recipient)));
		} catch(AddressException e) {
			log.error("MailMessage.addRecipient.Exception : ", e);
			throw new SystemException("invalid email address. adrdess = " + recipient);
		}
	}

	/**
	 * 
	 * @overridden  : @see cjfw.core.model.CanalFrameMessage#getFileLogContents(java.lang.String)
	 * @description : getFileLogContents 기능을 Override하여 구현한 Method
	 * 
	 * 포맷 예시
	 * 
	 * 이메일 파일로그의 형식에 맞추어 StringBuilder 리턴
	 
	  	※ 파일로그(이메일) 형식 정의
			
	    			TITLE<#3>CNTS<#3>RCVR_NM<#3>RCVR_EMAIL_ADDR<#3>SEND_DY<#3>SENDR_ID<#3>SENDR_EMAIL_ADDR
	    		    <#2>
				    ORGN_FILE_NM<#5>TGT_FILE_NM
				    <#4>
			        ORGN_FILE_NM<#5>TGT_FILE_NM
			        <#4>
			        
			        ...
			        
			        <#1>
	  	※ 반환 형식 : 각종 구분자로 구성한, 파일로그의 내용이 되는 한 줄의 문자열		
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public StringBuilder getFileLogContents(String curTime) {

		//첨부파일정보
		return new StringBuilder(StringUtils.defaultString(getTitle())).append(SEP_3)
				.append(StringUtils.defaultString(getContents())).append(SEP_3).append(StringUtils.defaultString(getRcvrNm()))
				.append(SEP_3).append(StringUtils.defaultString(getRcvrEmailAddr())).append(SEP_3).append(curTime).append(SEP_3)
				.append(StringUtils.defaultString(getSenderId())).append(SEP_3).append(StringUtils.defaultString(getSender()))
				.append(SEP_2).append(getFileLogAttachInfo());
	}

	/**
	 * 
	 * @description : getSender 기능을 구현한 Method
	 * 				  보내는 사람 이메일 주소를 반환 기능
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public String getSender() {
		return sender;
	}

	/**
	 * 
	 * @description : setSender 기능을 구현한 Method
	 * 				  보내는 사람 이메일 주소를 세팅
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}

	/**
	 * 
	 * @description : getTitle 기능을 구현한 Method
	 * 				  이메일 제목을 반환 기능
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 
	 * @description : setTitle 기능을 구현한 Method
	 * 				  이메일 제목을 세팅
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 
	 * @description : getRecipients 기능을 구현한 Method
	 * 				  이메일 주소 목록 반환 기능
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public List<InternetAddress> getRecipients() {
		return recipients;
	}

	/**
	 * 
	 * @description : setRecipients 기능을 구현한 Method
	 * 				  이메일 주소 목록 세팅(수신자 이메일 주소)
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public void setRecipients(List<InternetAddress> recipients) {
		this.recipients = recipients;
	}

	/**
	 * 
	 * @description : getRcvrNm 기능을 구현한 Method
	 * 				  수신자명 반환 기능
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public String getRcvrNm() {
		return rcvrNm;
	}

	/**
	 * 
	 * @description : setRcvrNm 기능을 구현한 Method
	 * 				  수신자명 세팅 기능
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public void setRcvrNm(String rcvrNm) {
		this.rcvrNm = rcvrNm;
	}

	/**
	 * 
	 * @description : getRcvrEmailAddr 기능을 구현한 Method
	 * 				  수신자 이메일 주소 반환 기능
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public String getRcvrEmailAddr() {
		return rcvrEmailAddr;
	}

	/**
	 * 
	 * @description : setRcvrEmailAddr 기능을 구현한 Method
	 * 				  수신자 이메일 주소 세팅 기능
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public void setRcvrEmailAddr(String rcvrEmailAddr) {
		this.rcvrEmailAddr = rcvrEmailAddr;
	}

	/**
	 * 
	 * @description : getContentsArgsMap 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public Map<String, String> getContentsArgsMap() {
		return contentsArgsMap;
	}

	/**
	 * 
	 * @description : setContentsArgsMap 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public void setContentsArgsMap(Map<String, String> contentsArgsMap) {
		this.contentsArgsMap = contentsArgsMap;
	}

	/**
	 * 
	 * @description : getTemplateFile 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public String getTemplateFile() {
		return templateFile;
	}

	/**
	 * 
	 * @description : setTemplateFile 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public void setTemplateFile(String templateFile) {
		this.templateFile = templateFile;
	}

	/**
	 * 
	 * @description : getSenderId 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public String getSenderId() {
		return senderId;
	}

	/**
	 * 
	 * @description : setSenderId 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	/**
	 * 
	 * @description : getContents 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public String getContents() {
		return contents;
	}

	/**
	 * 
	 * @description : setContents 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public void setContents(String contents) {
		this.contents = contents;
	}

	/**
	 * 
	 * @description : getSmtpHost 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public String getSmtpHost() {
		return smtpHost;
	}

	/**
	 * 
	 * @description : setSmtpHost 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	/**
	 * 
	 * @description : getSmtpId 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public String getSmtpId() {
		return smtpId;
	}

	/**
	 * 
	 * @description : setSmtpId 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public void setSmtpId(String smtpId) {
		this.smtpId = smtpId;
	}

	/**
	 * 
	 * @description : getSmtpPw 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public String getSmtpPw() {
		return smtpPw;
	}

	/**
	 * 
	 * @description : setSmtpPw 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public void setSmtpPw(String smtpPw) {
		this.smtpPw = smtpPw;
	}

	/**
	 * 
	 * @overridden  : @see cjfw.core.model.CanalFrameMessage#getAttachFileList()
	 * @description : getAttachFileList 기능을 Override하여 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	@Override
	public List<MailAttachFile> getAttachFileList() {
		return attachFileList;
	}

	/**
	 * 
	 * @overridden  : @see cjfw.core.model.CanalFrameMessage#setAttachFileList(java.util.List)
	 * @description : setAttachFileList 기능을 Override하여 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	@Override
	public void setAttachFileList(List<MailAttachFile> attachFileList) {
		this.attachFileList = attachFileList;
	}

	/**
	 * 
	 * @description : getSerialversionuid 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
