package cjfw.core.email.utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.output.FileWriterWithEncoding;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.model.CanalFrameMessage;
import cjfw.core.utility.ContextUtil;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.17
 * @description : 메일 전송용 VO클래스인 MailMessage 객체를 활용해 메일 발송 기능을 구현한 Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.17        sungyeon.lee       생성
 */
public class MailUtil {
	
	private static final Logger log = LoggerFactory.getLogger(MailUtil.class);

	/**
	 * 
	 * @description : MailUtil의 생성자
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	private MailUtil() {}

	public static final String DIVISION_EMAIL = "email";	//로그의 종류 구분 (디렉토리명, 파일명 구성시 사용됨)

	/**
	 * 
	 * @description : convISO 기능을 구현한 Method
	 * 				  입력받은 문자열을 캐릭터셋으로 인코딩한 바이트배열에서 ISO-8859-1로 변환
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public static String convISO(String str) {
		return convISO(str, null);
	}

	/**
	 * 
	 * @description : convISO 기능을 구현한 Method
	 * 				  입력받은 문자열을 입력받은 캐릭터셋으로 인코딩한 바이트배열에서 ISO-8859-1로 변환
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public static String convISO(String str, String charSet) {
		if(charSet == null) {
			charSet = CanalFrameConstants.DEFAULT_CHARACTERSET;
		}
		
		log.info("### mail convISO charSet : " + charSet);

		String localStr = str;
		String tmp = "";
		if(localStr == null || localStr.length() == 0) {
			return "";
		}
		try {
			tmp = new String(localStr.getBytes(charSet), "ISO-8859-1");
		} catch(UnsupportedEncodingException uee) {
			log.error("MailUtil.convISO().UnsupportedEncodingException : ", uee);
		} catch(Exception e) {
			log.error("MailUtil.convISO().Exception : ", e);
		}
		return tmp;
	}
	
	/**
	 * 
	 * @description : printLogFile 기능을 구현한 Method
	 * 
	 * 메일로그 파일 출력
	 *	 - 메일로그/첨부파일로그 모두 본 메서드에서 한 번에 처리한다.
	 *
	 * 1. 파일로그시 첨부파일로그도 덧붙여 하나의 row에 넣는다.
	 * 2. 로그 디렉토리 존재하지 않으면 생성 (로그파일 기본 디렉토리 하위에 현재연도로 디렉토리 생성)
	 *   - logFilePath는 다음과 같은 형태이다. => E:/logTest/2012/testService_email_20120101.log 
	 * 3. 로그파일의 내용은 각 message 객체가 가지고 있는 getFileLogContents() 메서드에서 작성한다.
	 * 
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public static void printLogFile(CanalFrameMessage message) {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String curTime = LocalDateTime.now().format(formatter);
		String curDay = curTime.substring(0, 8);
		String curYear = curDay.substring(0, 4);

		//로그파일이 생성되는 기본 디렉토리
		String baseDirectory = null;
		//로그파일명 중간에 들어가는 이름
		String fileMiddleName = null;
		fileMiddleName = DIVISION_EMAIL;
		baseDirectory = StringUtils.defaultString(ContextUtil.getProperty("cf.email.logPath"));
		//로그 디렉토리 존재하지 않으면 생성 (로그파일 기본 디렉토리 하위에 현재연도로 디렉토리 생성)
		String logDir = new StringBuilder(baseDirectory).append(File.separator).append(curYear).toString();
		//디렉토리 생성
		new File(logDir).mkdirs();
		// logFilePath는 다음과 같은 형태이다. => E:/logTest/2012/testService_email_20120101.log 
		String logFilePath =
				new StringBuilder(logDir).append(File.separator).append(StringUtils.defaultString(message.getServiceName()))
						.append("_").append(fileMiddleName).append("_").append(curDay).append(".log").toString();
		log.info("### " + fileMiddleName + " 로그파일 경로 : " + logFilePath);
		try(
			//파일로그시 첨부파일로그도 덧붙여 하나의 row에 넣는다.
			//로그파일의 내용은 각 message 객체가 가지고 있는 getFileLogContents() 메서드에서 작성한다. 
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriterWithEncoding(logFilePath, 
					CanalFrameConstants.DEFAULT_CHARACTERSET, true)));
		) {
			pw.println(message.getFileLogContents(curTime).append(CanalFrameMessage.SEP_1).toString());
		} catch(Exception e) {
			log.error("MailUtil.printLogFile.Exception : ", e);
		}
	}
}
