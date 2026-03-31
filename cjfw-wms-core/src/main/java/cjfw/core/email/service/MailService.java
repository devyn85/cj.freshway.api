package cjfw.core.email.service;

import jakarta.activation.DataHandler;
import jakarta.activation.FileDataSource;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMessage.RecipientType;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.internet.MimeUtility;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.email.model.MailAttachFile;
import cjfw.core.email.model.MailMessage;
import cjfw.core.email.utility.MailUtil;
import cjfw.core.email.utility.UserAuthentication;
import cjfw.core.exception.SystemException;
import cjfw.core.utility.ContextUtil;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.17
 * @description : 메일 전송용 VO클래스인 MailMessage 객체를 활용해 메일 전송 기능을 구현한 Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.17        sungyeon.lee       생성
 */
@Service
@RequiredArgsConstructor
public class MailService {
	
	private static final Logger log = LoggerFactory.getLogger(MailService.class);
	private static final String MAIL_BASE_ROOT_PATH = "classpath:static/form/mail/";


	private final CommonDao commonDao;

	private final ResourceLoader resourceLoader;
	
	/**
	 * 
	 * @description : sendMail 기능을 구현한 Method
	 * 
	 * 		+ 메일 발송 정책
	 * 
	 * 			- 자바메일 API 호출시, 수신자 배열을 넘기지 않고 수신자별로 각각 호출함으로써, 동일한 메일을 받는 사람들이 누구인지 서로 알 수 없게 한다.
	 * 			- 기존에는 수신자/참조/숨은참조가 존재했었으나, 참조/숨은참조를 제거하고 수신자 필드만 사용하기로 한다. (UI에서도 삭제, 테이블 칼럼도 삭제)
	 * 			- 기존에는 본 메서드의 리턴값인 메일발송의 결과가 1,2,3,4 의 int type으로 정의되어 있었으나, true/false로 변경되었고 로그테이블의 메일발송결과 칼럼도 삭제하였다.
	 * 
	 * 		+ 로그 생성 정책
	 * 
	 * 			- 수신자 1인당 1ROW씩 로그를 생성한다.
	 * 			- MailMessage 객체 생성 인자인 logType에의해 로그를 DB에 남길 것인지 FILE로 남길 것인지가 결정된다. 
	 * 
	 * 		+ 반환 값
	 * 
	 * 			- 메일 발송 여부 true : 정상 발송, false : 오류 발생
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public boolean sendMail(MailMessage mailMessage) {
		return sendMail(mailMessage, null);
	}

	/**
	 * 
	 * @description : sendMail 기능을 구현한 Method
	 * 				  입력받은 charSet으로 sendMail메서드를 호출하여 이메일을 발송하는 기능
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public boolean sendMail(MailMessage mailMessage, String charSet) {
		if(charSet == null) {
			charSet = CanalFrameConstants.DEFAULT_CHARACTERSET;
		}
		log.info("### mail message charSet : " + charSet);
		log.info(mailMessage.toString());
		//리턴값 초기화
		boolean ret = true;
		
		// 이미지 기본경로 셋팅
		mailMessage.addContentsArg("cf.static.basePath", StringUtils.defaultString(ContextUtil.getProperty("cf.static.basePath")));
		
		//smtp 정보 가져오기 - smtp 인증이 필요할 경우 smtp정보를 파라미터로 보내는 경우가 있다. 일반적으로는 설정파일에서 가져옴.
		String smtpHost = StringUtils.isBlank(mailMessage.getSmtpHost()) ? ContextUtil.getProperty("cf.email.host") : mailMessage.getSmtpHost();
		String smtpId = StringUtils.isBlank(mailMessage.getSmtpId()) ? ContextUtil.getProperty("cf.email.id") : mailMessage.getSmtpId();
		String smtpPw =	StringUtils.isBlank(mailMessage.getSmtpPw()) ? ContextUtil.getProperty("cf.email.pw") : mailMessage.getSmtpPw();
		String smtpDebug = ContextUtil.getProperty("cf.email.debug");
		log.info("### smtpHost : " + smtpHost + ", smtpId : " + smtpId + ", smtpPw : " + smtpPw + ", smtpDebug : "	+ smtpDebug);
		
		//smtp 설정정보가 존재하지 않을 경우 exception 처리.
		if(StringUtils.isBlank(smtpHost)) {
			throw new SystemException("메일 설정 정보가 존재하지 않습니다.");
		}
		//메일 템플릿 파일이 없을 경우 exception 처리.
		String templateFile = StringUtils.defaultString(mailMessage.getTemplateFile());
		if(StringUtils.isBlank(templateFile)) {
			throw new SystemException("메일 템플릿 파일이 없습니다");
		}
		//템플릿파일을 읽어온 후, 템플릿파일 내용에서 사용자 아규먼트를 치환.
		String contents = changeContentsKey(getTempReader(MAIL_BASE_ROOT_PATH + templateFile), mailMessage.getContentsArgsMap());
		mailMessage.setContents(contents);
		log.info("### contents\n\n" + contents + "\n\n");
		//첨부파일 정보
		List<MailAttachFile> attachFileList = mailMessage.getAttachFileList();
		//메일세션을 생성하기 위해서 프로퍼티에 메일 정보를 담는다.
		Properties props = new Properties();
		props.put("mail.smtp.host", smtpHost);
		//메일 세션
		Session session = null;
		//smtp 계정정보가 존재할 경우 권한이 있는 메일세션 생성
		//계정정보가 없을 경우
		if(StringUtils.isBlank(smtpId) || StringUtils.isBlank(smtpPw)) {
			props.put("mail.smtp.auth", "false");
			session = Session.getInstance(props);
		} else {
			//계정정보가 있을 경우
			props.put("mail.smtp.auth", "true");
			UserAuthentication auth = new UserAuthentication(smtpId, smtpPw);
			session = Session.getInstance(props, auth);
		}
		//메일 세션 디버그 여부 설정
		if(StringUtils.isNotEmpty(smtpDebug)) {
			session.setDebug(Boolean.getBoolean(smtpDebug));
		}
		//자바 메일 발송을 위한 준비
		MimeMessage mimeMessage = new MimeMessage(session);
		//create the Multipart and its parts to it
		Multipart mimeMultipart = new MimeMultipart();
		//80 Appendix B: Examples Using the JavaMail API (Sending a Message) 
		MimeBodyPart mimeBodyPartMain = new MimeBodyPart();
		MimeBodyPart mimeBodyPartSub = null;
		FileDataSource fileDataSource = null;
		try {
			//MimeMessage 객체에 값 설정
			//보내는 사람
			mimeMessage.setFrom(new InternetAddress(MailUtil.convISO(mailMessage.getSender(), charSet)));
			//제목
			mimeMessage.setSubject(MimeUtility.encodeText(mailMessage.getTitle(), charSet, "B"));
			//발신일시
			mimeMessage.setSentDate(new Date());
			//MimeMessage > MimeMultipart > MimeBodyPart 에 값 설정
			mimeBodyPartMain.setContent(contents, "text/html; charset=" + charSet);
			mimeMultipart.addBodyPart(mimeBodyPartMain);

			//첨부파일의 갯수만큼 루프
			for(MailAttachFile attachFile : attachFileList) {
				String realFileName = attachFile.getAttachFileName();
				String targetFilePath = attachFile.getPhysicalFilePath();
				String targetFileName = attachFile.getPhysicalFileName();
				if(StringUtils.isBlank(realFileName)) {
					throw new SystemException("원본 파일명이 누락되었습니다.");
				}
				if(StringUtils.isBlank(targetFilePath)) {
					throw new SystemException("업로드 파일 경로가 누락되었습니다.");
				}
				if(StringUtils.isBlank(targetFileName)) {
					throw new SystemException("업로드 파일 명이 누락되었습니다.");
				}

				String uploadFileFullPath =	new StringBuilder(ContextUtil.getProperty("cf.file.uploadPath"))
						.append(File.separator).append(targetFilePath).append(File.separator).append(targetFileName).toString();
				log.info("### uploadFileFullPath : " + uploadFileFullPath);

				//메일에 파일 첨부
				if(StringUtils.isNotEmpty(realFileName)) {
					//create the second message part
					mimeBodyPartSub = new MimeBodyPart();
					//attach the file to the message
					fileDataSource = new FileDataSource(uploadFileFullPath);
					mimeBodyPartSub.setDataHandler(new DataHandler(fileDataSource));
					mimeBodyPartSub.setFileName(MailUtil.convISO(realFileName));
					mimeMultipart.addBodyPart(mimeBodyPartSub);
				}
			}
			//add the Multipart to the message
			mimeMessage.setContent(mimeMultipart);
			//수신자 정보
			List<InternetAddress> recipients = mailMessage.getRecipients();
			//각 수신자별로 루프를 돌면서 자바메일 발송 API 호출
			for(int i = 0; i < recipients.size(); i++) {
				InternetAddress recipient = recipients.get(i);
				boolean isSuc = sendMailAct(recipient, mimeMessage, mailMessage, charSet);
				if(!isSuc){
					ret = false;
				}
			}
		} catch(MessagingException e) {
			log.error("MailService.sendMail().MessagingException : ", e);
			throw new SystemException(e.getMessage());
		} catch(UnsupportedEncodingException e) {
			log.error("MailService.sendMail().UnsupportedEncodingException : ", e);
			throw new SystemException(e.getMessage());
		}
		return ret;
	}

	/**
	 * 
	 * @description : sendMailAct 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	private boolean sendMailAct(InternetAddress recipient, MimeMessage mimeMessage, MailMessage mailMessage, String charSet) {
		boolean isSuc = true;
		//자바 메일 발송을 위한 준비
		try {
			//수신자 설정
			mimeMessage.setRecipient(RecipientType.TO, recipient);
			String rcvrNm =	StringUtils.isNotBlank(recipient.getPersonal()) ? 
							new String(recipient.getPersonal().getBytes("ISO-8859-1"), charSet) : "";
			//수신자명
			mailMessage.setRcvrNm(rcvrNm);
			//수신자 이메일 주소
			mailMessage.setRcvrEmailAddr(recipient.getAddress());
			//자바메일 발송 API 호출
			Transport.send(mimeMessage);
			//로그타입이 DB일 경우 메일 전송 DB Log 작성
			if("DB".equals(ContextUtil.getProperty("cf.email.logType"))) {
				saveMailLogDB(mailMessage);
			}
			//로그타입이 FILE일 경우 메일 전송 File Log 작성
			if("FILE".equals(ContextUtil.getProperty("cf.email.logType"))) {
				MailUtil.printLogFile(mailMessage);
			}
		} catch(Exception e) {
			log.error("MailService.sendMailAct().Fail : {}, Fail recipient Address : {}",  e, recipient.getAddress());
			//루프 내에서 오류가 발생한다고 하더라도 다른 사람들에의 이메일 전송이 중단되어서는 안된다.
			isSuc = false;
		}
		return isSuc;
	}

	/**
	 * 
	 * @description : OO 기능을 구현한 Method
	 * 				  메일 로그 DB insert, 각 수신자별로 1 row 씩 저장
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	private void saveMailLogDB(MailMessage mailMessage) {
		Map<String, String> paramMap = new HashMap<String, String>();

		paramMap.put("title", mailMessage.getTitle());
		paramMap.put("cnts", mailMessage.getContents());
		paramMap.put("sendrEmailAddr", mailMessage.getSender());
		paramMap.put("rcvrNm", mailMessage.getRcvrNm());
		paramMap.put("rcvrEmailAddr", mailMessage.getRcvrEmailAddr());
		paramMap.put("sendrId", mailMessage.getSenderId());
		paramMap.put("regKId", mailMessage.getSenderId());
		paramMap.put("updKId", mailMessage.getSenderId());
		// FrameOne v3.0, insert() ==> insert된 행의 갯수 (myBatis 에서 변경)
		String emailNo = null;
		commonDao.insert("Mail.createEmailLog", paramMap);
		// java.math.BigDecimal 타입
		emailNo = String.valueOf(paramMap.get("EMAIL_NO"));

		//첨부파일 로그 저장 메서드 호출
		saveMailAttachFile(emailNo, mailMessage);
	}

	/**
	 * 
	 * @description : saveMailAttachFile 기능을 구현한 Method
	 * 				  메일에 첨부된 파일에 대한 정보를 insert 기능
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	private void saveMailAttachFile(String emailNo, MailMessage mailMessage) {
		Map<String, String> paramMap = new HashMap<String, String>();

		paramMap.put("emailNo", emailNo);
		paramMap.put("regKId", mailMessage.getSenderId());
		paramMap.put("updKId", mailMessage.getSenderId());

		for(MailAttachFile mailAttachFile : mailMessage.getAttachFileList()) {

			paramMap.put("orgnFileNm", mailAttachFile.getAttachFileName());
			paramMap.put(
					"tgtFileNm",
					new StringBuilder(mailAttachFile.getPhysicalFilePath()).append(File.separator)
							.append(mailAttachFile.getPhysicalFileName()).toString());

			//첨부파일 로그 DB insert
			commonDao.insert("mail.createAttachFileLog", paramMap);
		}
	}
	
	/**
	 * 
	 * @description : changeContentsKey 기능을 구현한 Method
	 * 				  템플릿 파일 내의 key 값들을 교체하는 기능
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	private String changeContentsKey(String pContent, @SuppressWarnings("rawtypes") Map contentsKeyMap) {
		String localPcontent = pContent;

		@SuppressWarnings("rawtypes")
		Set keys = contentsKeyMap.keySet();

		for(Object key : keys) {
			String contentsKey = new StringBuilder("\\{").append(key.toString()).append("\\}").toString();
			if(contentsKeyMap.get(key) != null) {
				localPcontent = localPcontent.replaceAll(contentsKey, contentsKeyMap.get(key).toString());
			}
		}
		return localPcontent;
	}

	/**
	 * 
	 * @description : getTempReader 기능을 구현한 Method
	 * 				  입력받은 fileName에 해당하는 메일 템플릿 파일을 읽어 반환하는 기능
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	private String getTempReader(String fileName) {

		String retStr = "";
		try {
			InputStream inputStream = resourceLoader.getResource(fileName).getInputStream();
			retStr = FileCopyUtils.copyToString(new InputStreamReader(inputStream));
		} catch(IOException e) {
			log.error("MailService.getTempReader().IOException : ", e);
			throw new SystemException(e);
		}
		return retStr;
	}
}