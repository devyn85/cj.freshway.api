package cjfw.wms.cm.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.cm.dto.CmMainUserReqDto;
import cjfw.wms.cm.dto.CmMainUserResDto;
import cjfw.wms.cm.dto.CmSendReqDto;
import cjfw.wms.cm.entity.CmEmailLogEntity;
import cjfw.wms.cm.entity.CmSmsMmsSendEntity;
import cjfw.wms.webservice.utility.WebServiceUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.07.28 
 * @description : Email/SMS 전송
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.28 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CmSendService {

	private final CommonDao commonDao;
	
	private final UserContext userContext;
	
	/**
	 * @description : Email 전송
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.28 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public String saveEmail(CmSendReqDto cmSendReqDto) {
		// 수신자 사용자ID 필수값 체크
		if (cmSendReqDto.getRcvrId() == null || "".equals(cmSendReqDto.getRcvrId())) {
			throw new UserHandleException("MSG.COM.ERR.001");
		}
		
		// 수신자 정보 조회
		CmMainUserReqDto cmMainUserReqDto = new CmMainUserReqDto();
		cmMainUserReqDto.setGUserId(cmSendReqDto.getRcvrId());
		CmMainUserResDto cmMainUserResDto = commonDao.selectOne("cmUserService.getCmUser", cmMainUserReqDto);
		
		if (cmMainUserResDto == null) {
			throw new UserHandleException("MSG.COM.ERR.001");
		}
		
		// 이메일 전송 정보 설정
		CmEmailLogEntity cmEmailLogEntity = ModelMapperUtil.map(cmSendReqDto, userContext, CmEmailLogEntity.class);
		if ("qa".equals(System.getProperty("spring.profiles.active", "local"))) {
			// QA서버에서 이메일 전송 테스트를 위해 화이트 리스트 이메일 주소 하드코딩
			cmEmailLogEntity.setSendrEmailAddr("postmaster@fwportalqa01a.cjfwqa.com"); // 발송자이메일주소
		} else {
			cmEmailLogEntity.setSendrEmailAddr(cmSendReqDto.getGEmailAddr()); // 발송자이메일주소
		}
		cmEmailLogEntity.setRcvrNm(cmMainUserResDto.getUserNm()); // 수신자명
		cmEmailLogEntity.setRcvrEmailAddr(cmMainUserResDto.getEmailAddr()); // 수신자이메일주소
		
		commonDao.insert("cmSendService.insertSendEmail", cmEmailLogEntity);
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : SMS 전송
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.28 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public String saveSms(CmSendReqDto cmSendReqDto) {
		// 수신자 사용자ID 필수값 체크
		if (cmSendReqDto.getRcvrId() == null || "".equals(cmSendReqDto.getRcvrId())) {
			throw new UserHandleException("MSG.COM.ERR.001");
		}
		
		// 수신자 정보 조회
		CmMainUserReqDto cmMainUserReqDto = new CmMainUserReqDto();
		cmMainUserReqDto.setGUserId(cmSendReqDto.getRcvrId());
		CmMainUserResDto cmMainUserResDto = commonDao.selectOne("cmUserService.getCmUser", cmMainUserReqDto);
		
		if (cmMainUserResDto == null) {
			throw new UserHandleException("MSG.COM.ERR.001");
		}
						
		CmSmsMmsSendEntity cmSmsMmsSendEntity = ModelMapperUtil.map(cmSendReqDto, userContext, CmSmsMmsSendEntity.class);
		cmSmsMmsSendEntity.setSendTitle(cmSendReqDto.getTitle()); // 제목
		cmSmsMmsSendEntity.setSendMessage(cmSendReqDto.getCnts()); // 메세지
		cmSmsMmsSendEntity.setReceiveName(cmMainUserResDto.getUserNm()); // 수신자명
		cmSmsMmsSendEntity.setReceivePhone(cmMainUserResDto.getMobNo()); // 수신자핸드폰번호
		
		commonDao.insert("cmSendService.insertSendSms", cmSmsMmsSendEntity);
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : SMS 전송 (실시간)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.04 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public String saveSmsRealTime(CmSendReqDto cmSendReqDto) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmSS");
		WebServiceUtil.sendSMS(cmSendReqDto.getSendPhone(), cmSendReqDto.getRcvrPhone(), dateFormat.format(calendar.getTime()), cmSendReqDto.getCnts());
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}

}
