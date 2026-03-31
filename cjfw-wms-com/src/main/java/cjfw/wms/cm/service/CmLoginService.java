package cjfw.wms.cm.service;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.cm.dto.CmKkoMsgSendEntity;
import cjfw.wms.cm.dto.CmLoginPwdHstResDto;
import cjfw.wms.cm.dto.CmLoginReqDto;
import cjfw.wms.cm.dto.CmLoginResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.10.01 
 * @description : 로그인 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.01 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CmLoginService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "cmLoginService.";

	private final CommonDao commonDao;
	
	private final UserContext userContext;
	
	/**
	 * @description : 인증번호 전송
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.14 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public String saveVerificationCode(CmLoginReqDto cmLoginReqDto){		
		// "사용자인증대기(81)" 상태인지 체크
		cmLoginReqDto.setStatus("81");
		CmLoginResDto cmLoginResDto = commonDao.selectOne(SERVICEID_PREFIX + "getUserTmpDetail", cmLoginReqDto);
		if (cmLoginResDto == null) {
			throw new UserHandleException(MessageUtil.getMessage("MSG.COM.VAL.121")); // 유효하지 않은 정보입니다.
		}
		cmLoginReqDto.setUserId(cmLoginResDto.getUserId());
		
		// 인증번호 생성
		int num = new SecureRandom().nextInt(1_000_000); // 0 .. 999999
		String securityKey = String.format("%06d", num);
		cmLoginReqDto.setSecurityKey(securityKey);
		
		// 인증번호 저장
		commonDao.insert(SERVICEID_PREFIX + "insertVerificationCode", cmLoginReqDto);
		
		// 인증번호 전송 (실시간 SMS)
		String message = "[CJ프레시웨이 WayLo 인증번호 발송]\r\n\r\nWayLo 인증번호가 발송 되었습니다.\r\n인증번호 : " + securityKey;
		CmKkoMsgSendEntity cmKkoMsgSendEntity = ModelMapperUtil.map(cmLoginReqDto, userContext, CmKkoMsgSendEntity.class);
		cmKkoMsgSendEntity.setSendTitle("인증번호 발송"); // 제목
		cmKkoMsgSendEntity.setSendMessage(message); // 메세지
		cmKkoMsgSendEntity.setReceivePhone(cmLoginReqDto.getHandphoneNo()); // 수신자핸드폰번호
//		Calendar calendar = Calendar.getInstance();
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmSS");
//		String message = "[인증번호 발송] 프레시웨이 인증번호가 발송 되었습니다.\r\n인증번호 : " + securityKey;
//		WebServiceUtil.sendSMS("15770807", cmLoginReqDto.getHandphoneNo(), dateFormat.format(calendar.getTime()), message);
		
		commonDao.insert("cmSendService.insertSendSms", cmKkoMsgSendEntity);
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 인증번호 확인
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.14 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public String checkVerificationCode(CmLoginReqDto cmLoginReqDto){
		CmLoginResDto cmLoginResDto = commonDao.selectOne(SERVICEID_PREFIX + "checkVerificationCode", cmLoginReqDto);
		if (cmLoginResDto == null) {
			throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.073")); // 인증코드를 다시 확인해주세요.
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 사용자ID 중복 count 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.01 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public CmLoginResDto getDuplicateUserIdCount(CmLoginReqDto cmLoginReqDto){
		return commonDao.selectOne(SERVICEID_PREFIX + "getDuplicateUserIdCount", cmLoginReqDto);
	}
	
	/**
	 * @description : 사용자 정보 수정
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.14 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public String saveUserInfo(CmLoginReqDto cmLoginReqDto){	
		// "사용자인증대기(81)" 상태인지 체크
		cmLoginReqDto.setStatus("81");
		CmLoginResDto cmLoginResDto = commonDao.selectOne(SERVICEID_PREFIX + "getUserTmpDetail", cmLoginReqDto);
		if (cmLoginResDto == null) {
			throw new UserHandleException(MessageUtil.getMessage("MSG.COM.VAL.121")); // 유효하지 않은 정보입니다.
		}
		cmLoginReqDto.setUserIdOld(cmLoginResDto.getUserId());
		
		// 임시 사용자정보 수정 (사용자ID/비밀번호)
		cmLoginReqDto.setUserId(cmLoginReqDto.getUserId() + "_WAYLO"); // 사용자ID에 "_WAYLO" 붙이기
		cmLoginReqDto.setStatus("82"); // "대표인증대기(82)"
		commonDao.update(SERVICEID_PREFIX + "updateUserTmp", cmLoginReqDto);
		
		// 센터 권한 수정 (사용자ID)
		commonDao.update(SERVICEID_PREFIX + "updateUserConnect", cmLoginReqDto);
		
		// 그룹권한 수정 (사용자ID)
		commonDao.update(SERVICEID_PREFIX + "updateUserAuthority", cmLoginReqDto);
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 사용자 비밀번호 수정
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.15 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public String saveUserPwdNo(CmLoginReqDto cmLoginReqDto){	
		// 사용자 존재 유무 체크
		CmLoginResDto cmLoginResDto = commonDao.selectOne(SERVICEID_PREFIX + "getUserDetail", cmLoginReqDto);
		if (cmLoginResDto == null) {
			throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.093")); // 현재 비밀번호가 일치하지 않습니다.
		}
		
		// 기존 패스워드 이력 체크
		List<CmLoginPwdHstResDto> passwordHistory = commonDao.selectList(SERVICEID_PREFIX + "getUserPwdHst", cmLoginReqDto);
		LocalDate today = LocalDate.now();
        LocalDate prechangedate;
		for(CmLoginPwdHstResDto userData : passwordHistory){
            if (cmLoginReqDto.getPwdNo().equals(userData.getUserPwd())) {
                // 이전 패스워드와 중복
                throw new UserHandleException("MSG.COM.ERR.092"); // 최근 사용한 비밀번호를 재사용 할 수 없습니다.
            }
            prechangedate = Instant.ofEpochMilli(userData.getRegDt().getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            if (prechangedate.isEqual(today)) {
                // 1일 1회 변경 제한
                throw new UserHandleException("MSG.COM.ERR.091", new String[]{"1"}); // 패스워드 최소 사용기간은 {0}일 입니다.
            }
        }
		
		// 사용자 비밀번호 수정
		commonDao.update(SERVICEID_PREFIX + "updateUserPwdNo", cmLoginReqDto);
		
		// 로그인 관련 이력 저장
		commonDao.insert(SERVICEID_PREFIX + "insertUserPwdHst", cmLoginReqDto);
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 사용자ID 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.13 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public CmLoginResDto getUserIdByUserNo(CmLoginReqDto cmLoginReqDto){
		return commonDao.selectOne(SERVICEID_PREFIX + "getUserIdByUserNo", cmLoginReqDto);
	}
	
}
