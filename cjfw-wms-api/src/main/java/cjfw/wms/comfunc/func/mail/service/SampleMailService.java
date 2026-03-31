/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.comfunc.func.mail.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.email.model.MailAttachFile;
import cjfw.core.email.model.MailMessage;
import cjfw.core.email.service.MailService;
import cjfw.wms.comfunc.func.mail.dto.SampleMailSendReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class SampleMailService {
	
	private final MailService mailService;
	
	/**
	 * 메일 보내기 서비스 템플릿 <br>
	 * 설정할 mailMessage 멤버 : <br>
	 * Id(메일 템플릿 아이디), Sender(보내는이 주소), senderId(보내는이 아이디), title(메일 제목), contentsArgsMap(템플렛에 넣을 key-value 내용), AttachFileList(첨부파일 리스트) <br>
	 * 참고 : smtpHost, smtpId, smtpPw 는 property에 없을 경우 참고됩니다.
	 */
	public boolean sendSimpleMail (SampleMailSendReqDto sampleMailSendReqDto) {

		//메일 전송 성공 여부
		boolean suc = false;
		
		MailMessage mailMessage = new MailMessage("sendMail");

		Map<String, String> conArgMap = new HashMap<String, String>();	//템플릿의 map argument
		conArgMap.put("big_title", "메일 전송 테스트");
		conArgMap.put("memberName", "CJ 유저");
		conArgMap.put("key1", "TEST MAIL");
		conArgMap.put("key2", "이 메일은 테스트 메일입니다..");

		/**************** mailMessage 객체에 메일 정보 셋팅 *****************/
		
		mailMessage.setTemplateFile(sampleMailSendReqDto.getMailTemplateId());
		mailMessage.setSender(sampleMailSendReqDto.getSenderAddr());
		mailMessage.setSenderId(sampleMailSendReqDto.getSenderId());
		mailMessage.setTitle(sampleMailSendReqDto.getMailTitle());
		mailMessage.setContentsArgsMap(conArgMap);
		mailMessage.addRecipient(sampleMailSendReqDto.getReceiverAddr());	// 수신자를 "ISO-8859-1"형식으로 List변수 recipients에 어펜드(append)
		
		/*************************** 파일 첨부 ***************************/
		//첨부할 파일 리스트
		List<MailAttachFile> filesToAttach = new ArrayList<MailAttachFile>();
		
		//하나의 파일에 대해..
		MailAttachFile file = new MailAttachFile();
		file.setAttachFileName(sampleMailSendReqDto.getNewFileName());
		file.setPhysicalFilePath(sampleMailSendReqDto.getPhysicFilePath());
		file.setPhysicalFileName(sampleMailSendReqDto.getPhysicFileName());
		
		//파일 추가
		filesToAttach.add(file);
		mailMessage.setAttachFileList(filesToAttach);
		log.info("파일 추가 완료 - 경로: {} / {}", sampleMailSendReqDto.getPhysicFilePath(), sampleMailSendReqDto.getPhysicFileName());
		
	
		/*************************** 메일 전송 ***************************/
		log.info("::: 메일 발송 ::: \n mailMessage=[ {} ] " , (Object)mailMessage);

		try {
			//메일 발송
			suc = mailService.sendMail(mailMessage, CanalFrameConstants.DEFAULT_CHARACTERSET);
		} catch (Exception e) {
			log.error("SampleMailService.sendSimpleMail().Exception : ", e);
		}
		return suc;
	}
	
	/**
	 * [ 추가 참조 샘플 코드 - 코드만 참조 ]
	 * 메일 보내기 서비스 <br>
	 * 사용자 입력 값을 메일로 전송<br>
	 * Id(메일 템플릿 아이디), Sender(보내는이 주소), senderId(보내는이 아이디), title(메일 제목), contentsArgsMap(템플렛에 넣을 key-value 내용), AttachFileList(첨부파일 리스트) <br>
	 * 참고 : smtpHost, smtpId, smtpPw 는 property에 없을 경우 참고됩니다.
	 */
	private static final String SUC_YN = "SUC_YN";

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map sendMail (Map inParams) {

		Map outParams = new HashMap();

		//메일 전송 성공 여부
		boolean suc = false;
		
		// 메일메세지 객체(MailMessage)생성 parameter (로그타입, 서비스이름)
		MailMessage mailMessage = new MailMessage("sendMail");
		 
		// 실제 사용 템플릿
		String MailTempleteId = (String)inParams.get("MAIL_TEMPLETE"); // 메일 템플릿 아이디
		String MailTitle = (String)inParams.get("MAIL_TILTLE");	//메일제목
		String SenderAddress="cjtester@cj.net"; // 발송 할  이메일 주소 (from DB)
		String SenderID = (String)inParams.get("userId");	//보내는 ID (DB로그 기록시 수정자 정보로 사용)
		String receiverName = (String)inParams.get("MAIL_RICIEVER_UESER_ID");
		Map<String, String> conArgMap = new HashMap<String, String>();
		
		if("test".equals((String)inParams.get("CLS"))) {
			// 템플릿 파라미터 예제 전용
			conArgMap.put("big_title", "메일 전송 테스트");
			conArgMap.put("memberName", receiverName);
			conArgMap.put("key1", "TEST MAIL");
			conArgMap.put("key2", "본 메일은 테스트 메일입니다.");
			String contentInserted = (String)inParams.get("MAIL_CONTENT");
			conArgMap.put("key2", contentInserted);
		} else {
			// 템플릿 파라미터 실제 사용
			conArgMap = (Map)inParams.get("MAIL_ARGS"); // 메일 파라미터
		}
		
		//수신자를 아래와 같이 설정할 수 도 있음
//		String recieverNm = inParams.getVariableAsString("MAIL_RICIEVER_NM")
		String recieverAdd;
		if(System.getProperty("spring.profiles.active").equalsIgnoreCase("prd")) {
			if("".equals(inParams.get("MAIL_RICIEVER_ADDR"))) {
				outParams.put(SUC_YN, "수신자가 없습니다.");
				return outParams;
			}
			recieverAdd = (String)inParams.get("MAIL_RICIEVER_ADDR");
		} else {
			recieverAdd = "seokjun.song@cj.net";
		}
		String recvUserId = (String)inParams.get("MAIL_RICIEVER_USER_ID");
		String[] recieverArr = recieverAdd.split(";");
		String[] recvUserIds = recvUserId.split(";");		
		
		/**************** mailMessage 객체에 메일 정보 셋팅 *****************/
		mailMessage.setTemplateFile(MailTempleteId);
		mailMessage.setSender(SenderAddress);
		mailMessage.setSenderId(SenderID);
		mailMessage.setTitle(MailTitle);
		
		String strRecipient = "";
		if(recieverArr.length == recvUserIds.length) {
			for(int i = 0; i < recieverArr.length; i++) {
				strRecipient = "";
				strRecipient += recvUserIds[i];
				strRecipient += "<";
				strRecipient += recieverArr[i];
				strRecipient += ">";
				mailMessage.addRecipient(strRecipient);
			}
	    } else {
			for(int i = 0; i < recieverArr.length; i++) 
				mailMessage.addRecipient(recieverArr[i]);
		}

		mailMessage.setContentsArgsMap(conArgMap);
		/*************************** 파일 첨부 ***************************/
		//첨부할 파일 리스트
		List<MailAttachFile> filesToAttach = new ArrayList<MailAttachFile>();
		
		//하나의 파일에 대해..
		//변경될 첨부파일 이름
		String newFileName = "NewAttachFileName.txt";
		//물리 경로, 이름
		String PhysicFilePath = ".";
		String PhysicFileName = (String)inParams.get("MAIL_ATTACH");
		
		//파일 이름이 공백이 아닐 경우 파일 추가
		if(!"".equals(PhysicFileName)) {
			MailAttachFile file = new MailAttachFile();
			file.setAttachFileName(newFileName);
			file.setPhysicalFilePath(PhysicFilePath);
			file.setPhysicalFileName(PhysicFileName);
			
			//파일 추가
			filesToAttach.add(file);
			mailMessage.setAttachFileList(filesToAttach);
			log.info("=== 첨부 파일 추가 완료 - 경로: {} / {}", PhysicFilePath, PhysicFileName);
		} else {
			log.info("=== 첨부 파일 없음 : 파일 없이 전송");
		}
	
		/*************************** 메일 전송 ***************************/
		log.info("=== 메일 발송 === mailMessage=[ {} ] " , (Object)mailMessage);
		
		try {
			//메일 발송
			suc = mailService.sendMail(mailMessage, CanalFrameConstants.DEFAULT_CHARACTERSET);
		} catch (Exception e) {
			log.error("SampleMailService.sendMail().Exception : ", e);
			outParams.put(SUC_YN, "시스템 애러 발생: " + e.getMessage());
		}
		
		outParams.put("SUC_CD", suc);
		//메일 발송 성공, 실패 여부에 따라 메시지 셋팅
		if (suc) {
			outParams.put(SUC_YN, "메일을 발송했습니다.");
		} else {
			outParams.put(SUC_YN, "발송에 실패 했습니다. 수신자 중 메일을 받지 못한 수신자가 있을 수 있습니다.");
		}
		return outParams;
	}
}
