package cjfw.wms.cm.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Authenticator;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.email.model.MailMessage;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ContextUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.cm.dto.CmSendEmailReqDto;
import cjfw.wms.cm.entity.CmEmailLogEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.07.22 
 * @description : Email 서비스
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.22 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CmEmailService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "cmEmailService.";

	private final CommonDao commonDao;
	
	private final UserContext userContext;
	
	public String saveEmail(CmSendEmailReqDto paramDto) throws IOException {
	    
	    CmSendEmailReqDto reqDto = ModelMapperUtil.map(paramDto, CmSendEmailReqDto.class);
	    
	    if (!ObjectUtils.isEmpty(reqDto)) {
	        // 이메일 전송 정보
	        String title = reqDto.getTitle();
	        String conts = reqDto.getConts();
	        String recvName = reqDto.getRecvName();
	        String recvEmail = reqDto.getRecvEmail();
	        String recvEmail2 = reqDto.getRecvEmail2();
	        String sendName = reqDto.getSendName();
	        String sendEmail = reqDto.getSendEmail();
	        String sendType = reqDto.getSendType();
	        String attchFileName = reqDto.getAttchFileName(); 
	        sendType = ObjectUtils.isEmpty(sendType) ? "STD" : sendType;
            LocalDate today = LocalDate.now();

            String yyyy = today.format(DateTimeFormatter.ofPattern("yyyy"));
            String mm   = today.format(DateTimeFormatter.ofPattern("MM"));
            String dd   = today.format(DateTimeFormatter.ofPattern("dd"));
	        
	        // 이메일 전송 정보 설정
	        CmEmailLogEntity cmEmailLogEntity = ModelMapperUtil.map(reqDto, userContext, CmEmailLogEntity.class);
	        cmEmailLogEntity.setCnts(conts);               // 내용
	        cmEmailLogEntity.setSendrEmailAddr(sendEmail); // 발송자이메일주소
	        cmEmailLogEntity.setRcvrNm(recvName);          // 수신자명
	        cmEmailLogEntity.setRcvrEmailAddr(recvEmail);  // 수신자이메일주소
	        
	        // 이메일 전송 저장
	        commonDao.insert("cmSendService.insertSendEmail", cmEmailLogEntity);
	        
	        if ("RPT".equals(sendType)) {
	            MailMessage message = new MailMessage();
                message.setTitle(title);
                message.setContents(conts);
                message.setRcvrNm(recvName);
                message.setRcvrEmailAddr(recvEmail);
                message.setSender(sendEmail);
                message.setAttachFileList(null);
                attchFileName = attchFileName.trim();

	            if (!ObjectUtils.isEmpty(attchFileName)) {
    	            // 리포트 파일을 첨부하는 메일  
    	            // 리포트 파일을 서버에 저장하면 여러 개의 파일로 저장될 수 있다
    	            // attchFileName에는 이 파일들을 아우르는 대표 파일명이다 
    	            // attchFileName에 "_1", "_2" 등이 붙은 파일들이 생성될 수 있기 때문에 대표 파일명을 포함한 여러 개의 파일을 첨부해야 한다 
    	            Path dir = Paths.get(ContextUtil.getProperty("cf.upload.dir.report") +"/" + yyyy + "/" + mm + "/" + dd);
    	            
    	            log.debug("dir : " + dir.toString());
    	            int dotIndex = attchFileName.lastIndexOf('.');
    	            String fileName = "";
    	            String fileExt = "";
    
    	            if (dotIndex > 0 && dotIndex < attchFileName.length() - 1) {
    	                fileName = attchFileName.substring(0, dotIndex);
    	                fileExt = attchFileName.substring(dotIndex + 1);
    	            } 
    	            
    	            String prefix = fileName;
    	            String ext = fileExt;
    	           
                    List<String> fileNames = new ArrayList<>();
                    fileNames.add(attchFileName.trim());
//                            Files.list(dir)
//                                    .map(path -> path.getFileName().toString())
//                                    .filter(name -> name.startsWith(prefix))
//                                    .filter(name -> name.endsWith(ext))
//                                    .collect(Collectors.toList());

//                    List<MailAttachFile> files = null;
//                    for (String name : fileNames) {
//                        MailAttachFile f = new MailAttachFile();
//                        f.setAttachFileName(name);
//                        f.setPhysicalFileName(dir + name);
//                        
//                        files.add(f);
//                    }
//                    
//                    message.setAttachFileList(files);

                    sendEmail(sendName, sendEmail, recvName, recvEmail, title, conts, fileNames);
                    
                    // 2번 수신자에게 발송
                    if (recvEmail2 != null && !recvEmail2.equals("") && !recvEmail2.isEmpty()) {
                        sendEmail(sendName, sendEmail, recvName, recvEmail2, title, conts, fileNames);
                    }
	            } else {
	                sendEmail(sendName, sendEmail, recvName, recvEmail, title, conts, null);
	            }
	            
	            //sendEmail(message, null);
	        } 
	        
	    }
	    
	    return CanalFrameConstants.MSG_COM_SUC_CODE;
	
	}
	
	/**
	 * SMTP Email 전송
	 */
	public boolean sendEmail(String sendNm, String sendEmail, String recvNm, String recvEmail, String title, String body, String fileName, byte[] fileBody)	{
		
		boolean nResult = false;
		
		try {
			
			Properties props = new Properties();
			props.put("mail.smtp.host", ContextUtil.getProperty("cf.email.host"));
			props.put("mail.smtp.port", ContextUtil.getProperty("cf.email.port"));
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.user", ContextUtil.getProperty("cf.email.id"));
			props.put("mail.smtp.password", ContextUtil.getProperty("cf.email.pw"));
			props.put("mail.smtp.connectiontimeout", 5000);
			props.put("mail.smtp.timeout", 5000);
			
			
			Session session = Session.getInstance(props, new Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(
	                    props.getProperty("mail.smtp.user"), 
	                    props.getProperty("mail.smtp.password")
	                );
	            }
	        });
			MimeMessage message = new MimeMessage(session);
			Multipart mimeMultipart = new MimeMultipart();
	        MimeBodyPart mimeBodyPartMain = new MimeBodyPart();
	        MimeBodyPart mimeBodyPartSub = null;
	        
            //제목설정
	        message.setSubject((MimeUtility.encodeText(title, "UTF-8", "B")));
            //송신일시설정
	        message.setSentDate(new Date());
	        //송신자설정
	        message.setFrom(new InternetAddress( new String(sendEmail.getBytes("UTF-8"), "ISO-8859-1") ));
            //수신자설정
            message.setRecipient(javax.mail.internet.MimeMessage.RecipientType.TO,new InternetAddress( new String(recvEmail.getBytes("UTF-8"), "ISO-8859-1")  ));

            
            //내용설정
            mimeBodyPartMain.setText( body );
            mimeMultipart.addBodyPart(mimeBodyPartMain);

            //파일첨부
            if (fileBody != null && fileBody.length > 0) {
            	mimeBodyPartSub = new MimeBodyPart(); 
                ByteArrayDataSource bds = new ByteArrayDataSource(fileBody, "image/tiff" ); 
                bds.setName(fileName);
                mimeBodyPartSub.setDataHandler(new DataHandler(bds)); 
                mimeBodyPartSub.setFileName( MimeUtility.encodeText(bds.getName(),"UTF-8","B") ); 
                mimeMultipart.addBodyPart(mimeBodyPartSub);
            }
            
            //내용설정
            message.setContent(mimeMultipart, "text/html;charset=utf-8");
            
            //전송처리
            Transport.send(message);
            
            nResult = true;
			
		} catch (Exception e){
			
			e.printStackTrace();
			
			nResult = false;
			
			log.error("CmEmailService.sendEmail().Exception : ", e);
			throw new UserHandleException("MSG.COM.ERR.001");
		}
		
		return nResult;
	}
	
	/**
     * SMTP Email 전송
     */
    public boolean sendEmail(String sendNm, String sendEmail, String recvNm, String recvEmail, String title, String body, List<String> fileNames) {
        
        boolean nResult = false;
        try {
            byte[] fileBody = null;
            String filePath = null;
            
            LocalDate today = LocalDate.now();

            String yyyy = today.format(DateTimeFormatter.ofPattern("yyyy"));
            String mm   = today.format(DateTimeFormatter.ofPattern("MM"));
            String dd   = today.format(DateTimeFormatter.ofPattern("dd"));
            
            String senderAddr = ContextUtil.getProperty("cf.email.senderAddr");
            if (senderAddr.isEmpty()) {
                senderAddr = sendEmail;
            }
            
            Properties props = new Properties();
            props.put("mail.smtp.host", ContextUtil.getProperty("cf.email.host"));
            props.put("mail.smtp.port", ContextUtil.getProperty("cf.email.port"));
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.user", ContextUtil.getProperty("cf.email.id"));
            props.put("mail.smtp.password", ContextUtil.getProperty("cf.email.pw"));
            props.put("mail.smtp.connectiontimeout", 5000);
            props.put("mail.smtp.timeout", 5000);
            log.debug("path : " +  "/app/uploads/report/report/" + yyyy + "/" + mm + "/" + dd + "/");
            log.debug("fileNames : " +  fileNames);
            
            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(
                        props.getProperty("mail.smtp.user"), 
                        props.getProperty("mail.smtp.password")
                    );
                }
            });
            
            MimeMessage message = new MimeMessage(session);
            Multipart mimeMultipart = new MimeMultipart();
            MimeBodyPart mimeBodyPartMain = new MimeBodyPart();
            MimeBodyPart mimeBodyPartSub = null;
            
            //제목설정
            message.setSubject((MimeUtility.encodeText(title, "UTF-8", "B")));
            //송신일시설정
            message.setSentDate(new Date());
            //송신자설정
            message.setFrom(new InternetAddress( new String(senderAddr.getBytes("UTF-8"), "ISO-8859-1") ));
            //수신자설정
            message.setRecipient(javax.mail.internet.MimeMessage.RecipientType.TO,new InternetAddress( new String(recvEmail.getBytes("UTF-8"), "ISO-8859-1")  ));

            //내용설정
            mimeBodyPartMain.setText( body );
            mimeMultipart.addBodyPart(mimeBodyPartMain);            
            
            //파일첨부
            for (String fileName : fileNames) {
            	Path dir = Paths.get(ContextUtil.getProperty("cf.upload.dir.report"));
                filePath = ContextUtil.getProperty("cf.upload.dir.report") + "/" + yyyy + "/" + mm + "/" + dd + "/" + fileName;
            
	            File file = new File(filePath);
	            if (file.exists()) {
	            	fileBody = Files.readAllBytes(Paths.get(filePath));
	                
	                if (fileBody.length > 0) {
	                    MimeBodyPart attachmentPart = new MimeBodyPart();
	                    ByteArrayDataSource bds = new ByteArrayDataSource(fileBody, "image/tiff" ); 
	                    bds.setName(fileName);
	                    attachmentPart.setDataHandler(new DataHandler(bds)); 
	                    attachmentPart.setFileName( MimeUtility.encodeText(bds.getName(),"UTF-8","B") ); 
	                    mimeMultipart.addBodyPart(attachmentPart);
	                }
	            } else {
	            	log.debug("@@@@@@@@@@@@@@@@@@@@@@ 파일 존재하지 않음 : {}", filePath);
	            }
            }
            
            //내용설정
            message.setContent(mimeMultipart, "text/html;charset=utf-8");
            
            //전송처리
            Transport.send(message);
            
            nResult = true;
            
        } catch (Exception e){
            
            e.printStackTrace();
            
            nResult = false;
            
            log.error("CmEmailService.sendEmail().Exception : ", e);
            throw new UserHandleException("MSG.COM.ERR.001");
        }
        
        
        return nResult;
    }
	
    public boolean sendEmail(MailMessage mailMessage, String charSet) {
        
        boolean nResult = false;
        /*
        
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
        String smtpPw = StringUtils.isBlank(mailMessage.getSmtpPw()) ? ContextUtil.getProperty("cf.email.pw") : mailMessage.getSmtpPw();
        String smtpDebug = ContextUtil.getProperty("cf.email.debug");
        log.info("### smtpHost : " + smtpHost + ", smtpId : " + smtpId + ", smtpPw : " + smtpPw + ", smtpDebug : " + smtpDebug);
        
        //smtp 설정정보가 존재하지 않을 경우 exception 처리.
        if(StringUtils.isBlank(smtpHost)) {
            throw new SystemException("메일 설정 정보가 존재하지 않습니다.");
        }
//        //메일 템플릿 파일이 없을 경우 exception 처리.
//        String templateFile = StringUtils.defaultString(mailMessage.getTemplateFile());
//        if(StringUtils.isBlank(templateFile)) {
//            throw new SystemException("메일 템플릿 파일이 없습니다");
//        }
//        //템플릿파일을 읽어온 후, 템플릿파일 내용에서 사용자 아규먼트를 치환.
//        String contents = changeContentsKey(getTempReader(MAIL_BASE_ROOT_PATH + templateFile), mailMessage.getContentsArgsMap());
        String contents = mailMessage.getContents();
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

                String uploadFileFullPath = new StringBuilder(ContextUtil.getProperty("cf.file.uploadPath"))
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
        */
        
        return nResult;
    }
}
