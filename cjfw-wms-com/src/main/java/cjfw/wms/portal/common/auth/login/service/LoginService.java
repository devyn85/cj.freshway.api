/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.portal.common.auth.login.service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cjfw.auth.common.entity.SecurityRulesEntity;
import cjfw.auth.common.service.CJSecurityRulesService;
import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.utility.CryptoUtil;
import cjfw.wms.cm.CanalFrameLoginValidator;
import cjfw.wms.portal.common.auth.login.dto.FindIdGetReqDto;
import cjfw.wms.portal.common.auth.login.dto.FindIdGetResDto;
import cjfw.wms.portal.common.auth.login.dto.PwdChangeComResDto;
import cjfw.wms.portal.common.auth.login.dto.PwdChangeReqDto;
import cjfw.wms.portal.common.auth.login.dto.VrfctnCdCheckReqDto;
import cjfw.wms.portal.common.auth.login.dto.VrfctnCdSendReqDto;
import cjfw.wms.portal.common.auth.login.entity.CfUsersEntity;
import cjfw.wms.portal.common.auth.login.entity.PwdHstEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService {

	// 유효하지 않은 정보입니다.
	private static final String MSG_COM_VAL_121 = "MSG.COM.VAL.121";
	// 시간이 초과되었습니다.
	private static final String MSG_COM_ERR_072 = "MSG.COM.ERR.072";
	// 인증코드를 다시 확인해주세요.
	private static final String MSG_COM_ERR_073 = "MSG.COM.ERR.073";
	// 확인 되었습니다.
	private static final String MSG_COM_SUC_026 = "MSG.COM.SUC.026";

	private static final int PWDHSTCNT = 3;// 패스워드 이력저장 갯수

	private final CommonDao commonDao;
	private final CJSecurityRulesService cJSecurityRulesService;
	private CanalFrameLoginValidator canalFrameLoginValidator;

	/**
	 * 아이디찾기 조회
	 */
	public List<FindIdGetResDto> getFindId(FindIdGetReqDto findIdGetReqDto) {
		return commonDao.selectList("loginService.getFindId", findIdGetReqDto);
	}

	/**
	 * 인증코드전송
	 */
	public PwdChangeComResDto sendVerificationCode(VrfctnCdSendReqDto vrfctnCdSendReqDto) {
		/**************** 아이디, 이메일 유효성 검사 시작 *******************************/
		int cnt = (int) commonDao.selectOne("loginService.checkLoginIdEmail", vrfctnCdSendReqDto);
		if (cnt == 0) {
			throw new UserHandleException(MSG_COM_VAL_121);
		}
		/**************** 아이디, 이메일 유효성 검사 끝 *******************************/

		/**************** 인증코드 생성 시작 *******************************/
		// 6자리 랜덤 숫자
		int certNumLength = 6;
		SecureRandom random = new SecureRandom();
		int range = (int) Math.pow(10, certNumLength);
		int trim = (int) Math.pow(10, certNumLength - 1.0);
		int result = random.nextInt(range) + trim;
		if (result > range) {
			result = result - trim;
		}
		log.info("{}", result);
		vrfctnCdSendReqDto.setVrfctnCd(String.valueOf(result));
		commonDao.insert("loginService.insertVerificationCode", vrfctnCdSendReqDto);
		/**************** 인증코드 생성 끝 *******************************/

		/**************** 인증코드 메일 전송 시작 *******************************/
		// SMTP Server 있을 때 구현
		/**************** 인증코드 메일 전송 끝 *******************************/

		// 전송 되었습니다.
		return PwdChangeComResDto.builder().successYn("1").verificationCd(String.valueOf(result)).build();
	}

	/**
	 * 인증코드확인
	 */
	public String checkVerificationCode(VrfctnCdCheckReqDto vrfctnCdCheckReqDto) {
		Map vrfctnCdInfo = commonDao.selectOne("loginService.checkVerificationCode", vrfctnCdCheckReqDto);

		if (vrfctnCdInfo == null) {
			throw new UserHandleException(MSG_COM_VAL_121); // 유효하지 않은 정보입니다.
		}

		String timeFlag = String.valueOf(vrfctnCdInfo.get("TIMEFLAG"));
		String verificationCode = String.valueOf(vrfctnCdInfo.get("VRFCTN_CD"));
		if ("0".equals(timeFlag)) {
			throw new UserHandleException(MSG_COM_ERR_072); // 시간이 초과되었습니다.
		}
		if (!verificationCode.equals(vrfctnCdCheckReqDto.getVrfctnCd())) {
			throw new UserHandleException(MSG_COM_ERR_073); // 인증코드를 다시 확인해주세요.
		}
		return MSG_COM_SUC_026; // 확인 되었습니다.
	}

	/**
	 * 비밀번호 변경
	 */
	public String savePwdChange(PwdChangeReqDto pwdChangeReqDto) {

		List<PwdHstEntity> passwordHistory = commonDao.selectList("loginService.getPwdHst", pwdChangeReqDto);

		// 비밀번호 유효성 검증
		canalFrameLoginValidator.validPassword(pwdChangeReqDto, passwordHistory);

		// 비밀번호 변경
		CfUsersEntity cfUsersEntity = new CfUsersEntity();
		cfUsersEntity.setUserId(pwdChangeReqDto.getUserId());
		// Client에서 SHA256 처리해서 넘어온 값에 대한 SHA256 n회 처리
		cfUsersEntity.setUserPwd(CryptoUtil.SHA256(pwdChangeReqDto.getPwd()));
		cfUsersEntity.setMailAddr(pwdChangeReqDto.getEmail());
		int cnt = commonDao.update("loginService.changePwd", cfUsersEntity);
		if (cnt < 1) {
			throw new UserHandleException(MSG_COM_VAL_121); // 유효하지 않은 정보입니다.
		}
		// 비밀번호 변경 이후 변경일자, 실패 카운트 등 메타정보 초기화
		commonDao.update("loginService.changeSecuRules", cfUsersEntity);

		// 계정 잠금 해제
		cJSecurityRulesService.updateUnlockUserId(Map.of("userId", pwdChangeReqDto.getUserId()));

		// 임시 패스워드 사용 여부 변경
		SecurityRulesEntity securityRulesEntity = new SecurityRulesEntity();
		securityRulesEntity.setUserId(pwdChangeReqDto.getUserId());
		securityRulesEntity.setTmpPwdYn("0");
		commonDao.update("loginService.changeTmpPwdSts", securityRulesEntity);

		// 로그인 관련 이력 저장
		PwdHstEntity pwdHstEntity = new PwdHstEntity();
		pwdHstEntity.setUserId(cfUsersEntity.getUserId());
		pwdHstEntity.setUserPwd(cfUsersEntity.getUserPwd());
		int count = passwordHistory.size();
		if (count < PWDHSTCNT) {
			pwdHstEntity.setUserPwdSeq(count + 1);
			commonDao.insert("loginService.insertPwdHst", pwdHstEntity);
		} else {
			// PWDHSTCNT건 중 가장 오래된 패스워드 이력 업데이트
			commonDao.update("loginService.updatePwdHst", pwdHstEntity);
		}

		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
}
