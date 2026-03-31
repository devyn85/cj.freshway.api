package cjfw.wms.webservice.utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import cjfw.core.exception.UserHandleException;
import cjfw.wms.webservice.sms.DT_SHR0130_EAI;
import cjfw.wms.webservice.sms.DT_SHR0130_EAIT_PARAM;
import cjfw.wms.webservice.sms.DT_SHR0130_EAI_response;
import cjfw.wms.webservice.sms.SI_SHR0130_EAI_SOProxy;
import cjfw.wms.webservice.sso.webservice.SSO_NON_SAP_EAIPortTypeProxy;
import cjfw.wms.webservice.sso.webservice.xsd.SSO_NON_SAP_REQUEST;
import cjfw.wms.webservice.sso.webservice.xsd.SSO_NON_SAP_RESPONSE;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.07.28 
 * @description : 웹서비스 유틸
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.28 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Slf4j
public class WebServiceUtil  {	
	
	/**
	 * @description : WebService를 통해서 단문 SMS메시지를 전송
	 * 
	 * @param fromPhoneNo 발신 전화번호 (01*-****-**** OR 01********)
	 * @param toPhoneNo 수신 전화번호 (01*-****-**** OR 01********)
	 * @param reserveDate 전송날짜(YYYYMMDDHHMMSS) 
	 * @param message 메시지
	 * @return 결과값(성공 : S, 실패 : F)
	 * 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.28 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public static String sendSMS(String fromPhoneNo, String toPhoneNo, String reserveDate, String message) {
		SI_SHR0130_EAI_SOProxy proxy = new SI_SHR0130_EAI_SOProxy();
		String result = null;
		
		log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ fromPhoneNo : {}", fromPhoneNo);
		log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ toPhoneNo : {}", toPhoneNo);
		log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ reserveDate : {}", reserveDate);
		log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ message : {}", message);
		
		try {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmSS");
			
			DT_SHR0130_EAI  smsData = new DT_SHR0130_EAI();
			smsData.setXSYS("SCM");
			smsData.setXDATS(dateFormat.format(calendar.getTime()));
			smsData.setXTIMS(timeFormat.format(calendar.getTime()));
			smsData.setXROWS("1");
			
			DT_SHR0130_EAIT_PARAM[] paramList = new DT_SHR0130_EAIT_PARAM[1];
			DT_SHR0130_EAIT_PARAM param = new DT_SHR0130_EAIT_PARAM();
			
			param.setID("SCM");			
			param.setCALLBACK(fromPhoneNo);
			param.setPHONE(toPhoneNo);
			if(reserveDate != null && !reserveDate.trim().equals("")) {
				param.setDATE(reserveDate);
			}
			param.setMSG(message);
			
			
			paramList[0] = param;
			smsData.setT_PARAM(paramList);
			DT_SHR0130_EAI_response response = proxy.si_shr0130_eai_so(smsData);
			result = response.getXSTAT();
		} catch(Exception e) {
			log.error("SMS(SHR0130) 전송중 에러발생", e);
			throw new UserHandleException("MSG.COM.ERR.001");
		}
		
		return result;
	}
	
	/**
	 * @description : IAM에 SSO처리를 위해  1회용 Ticket를 요청
	 * @param userServiceNo 사용자 통합 사용자 번호
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.19 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public static String getSSOTicket(String userServiceNo) {
		String ssoTicket = null;
		SSO_NON_SAP_EAIPortTypeProxy proxy = new SSO_NON_SAP_EAIPortTypeProxy();
		
		try {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmSS");
			
			SSO_NON_SAP_REQUEST data = new SSO_NON_SAP_REQUEST();
			data.setXSYS("SCM");
			data.setXDATS(dateFormat.format(calendar.getTime()));
			data.setXTIMS(timeFormat.format(calendar.getTime()));
			data.setXROWS("1");
			data.setZSYST_FR("SCM");
			data.setZSYST_TO("BIP");
			data.setINT_SVC_NO(userServiceNo);
			
			SSO_NON_SAP_RESPONSE response = proxy.GET_SSO_TICKET(data);
			ssoTicket = response.getSSO_TICKET();
			
			// 오류처리
			if("E".equals(response.getXSTAT())) {
				throw new UserHandleException("SSO 인증정보 요청중 오류발생");
			}
			
		} catch(Exception e) {
			log.error("SSO_NON_SAP 1회용 Ticket 발행중 오류발생", e);
			throw new UserHandleException("MSG.COM.ERR.001");
		}
		
		return ssoTicket;
	}
}