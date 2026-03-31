package cjfw.wms.cm.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ContextUtil;
import cjfw.core.utility.CryptoUtil;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.cm.dto.CmUserManagerAuthorityReqDto;
import cjfw.wms.cm.dto.CmUserManagerAuthorityResDto;
import cjfw.wms.cm.dto.CmUserManagerConnectReqDto;
import cjfw.wms.cm.dto.CmUserManagerConnectResDto;
import cjfw.wms.cm.dto.CmUserManagerReqDto;
import cjfw.wms.cm.dto.CmUserManagerResDto;
import cjfw.wms.cm.dto.CmUserTmpManagerResDto;
import cjfw.wms.cm.entity.CmSmsMmsSendEntity;
import cjfw.wms.cm.entity.CmUserConnectEntity;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.CommonConstants;
import cjfw.wms.ms.entity.MsCarDriverEntity;
import cjfw.wms.sys.dto.SysAuthorityResDto;
import cjfw.wms.sys.entity.SysAuthChgLogEntity;
import cjfw.wms.sys.entity.SysAuthUserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.04.30 
 * @description : 기준정보 > 사용자및센터정보 > 사용자정보 관리 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.04.30 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CmUserManagerService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private static final String SERVICEID_PREFIX = "cmUserManagerService.";

	private final CommonDao commonDao;
	
	private final UserContext userContext;
	
	/**
	 * @description : 권한그룹 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.12 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public List<SysAuthorityResDto> getAuthorityGroupList(CmUserManagerReqDto cmUserManagerReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getAuthorityGroupList", cmUserManagerReqDto);
	}
	
	/**
	 * @description : 사용자 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.20 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public List<CmUserManagerResDto> getUserList(CmUserManagerReqDto cmUserManagerReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getUserList", cmUserManagerReqDto);
	}
	
	/**
	 * @description : 사용자 상세 정보 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.20 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public CmUserManagerResDto getUserDetail(CmUserManagerReqDto cmUserManagerReqDto) {
		return commonDao.selectOne(SERVICEID_PREFIX + "getUserDetail", cmUserManagerReqDto);
	}
	
	/**
	 * @description : 사용자 센터 권한 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.20 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public List<CmUserManagerConnectResDto> getUserConnectList(CmUserManagerReqDto cmUserManagerReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getUserConnectList", cmUserManagerReqDto);
	}
	
	/**
	 * @description : 사용자 그룹권한 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.20 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public List<CmUserManagerAuthorityResDto> getUserAuthorityList(CmUserManagerReqDto cmUserManagerReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getUserAuthorityList", cmUserManagerReqDto);
	}
	
	/**
	 * @description : 사용자 관리 저장 Root
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.28 breaker3317 생성 </pre>
	 */
	public String saveUserRoot(CmUserManagerReqDto cmUserManagerReqDto) {
		CmUserManagerResDto userDtlDto = cmUserManagerReqDto.getUserDtl();
		
		if ("Y".equals(userDtlDto.getTempYn()) || ("Y".equals(cmUserManagerReqDto.getGRepUserIdYn()) && "I".equals(userDtlDto.getRowStatus()))) {
			// 임시 사용자를 저장시 OR 업체대표가 신규 사용자를 저장시
			saveUserTmp(cmUserManagerReqDto);
		} else if ("01".equals(cmUserManagerReqDto.getGEmptype()) || "Y".equals(cmUserManagerReqDto.getGRepUserIdYn())) {
			// 정직원 OR 업체대표가 사용자 저장시
			saveUser(cmUserManagerReqDto);
		} else {
			throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.001")); // 시스템 오류가 발생했습니다. 시스템 관리자에게 문의 바랍니다.
		}
				
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 사용자 관리 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.05 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public String saveUser(CmUserManagerReqDto cmUserManagerReqDto) {
		CmUserManagerResDto userDtlDto = cmUserManagerReqDto.getUserDtl();
		
		// 신규 사용자 등록시 사원유형이 "배송업체" 일 경우 사용자ID 자동 채번
		if (
				(CanalFrameConstants.INSERT).equals(userDtlDto.getRowStatus()) // 신규 사용자 등록시
				&& userDtlDto.getTempYn() == null && !"Y".equals(userDtlDto.getTempYn()) // 임시 테이블 사용자 아닐 경우
				&& userDtlDto.getDriverYn() == null && !"Y".equals(userDtlDto.getDriverYn()) // 차량정보 등록 아닐 경우
				&& userDtlDto.getEmpType() != null && "C01".equals(userDtlDto.getEmpType()) // 사원유형이 "배송업체" 일 경우
				&& !"Y".equals(userDtlDto.getRepUserIdYn()) // 대표ID가 아닐 경우
		) {
			var entity = ModelMapperUtil.map(userDtlDto, userContext, MsCarDriverEntity.class);
			String driverId = commonDao.selectOne("msCarDriverService.getInsertDriverId", entity);
			userDtlDto.setUserId(driverId);
			userDtlDto.setRepUserIdYn("N"); // 강제로 대표ID "N"으로 설정
		} else if ((CanalFrameConstants.INSERT).equals(userDtlDto.getRowStatus()) && userDtlDto.getTempYn() == null && !"Y".equals(userDtlDto.getTempYn()) && userDtlDto.getDriverYn() == null && !"Y".equals(userDtlDto.getDriverYn())) {
			// 신규 사용자 등록시 사용자ID에 "_WAYLO" 붙이기
			// [ 임시 테이블 사용자 / 드라이버 사용자 ] 제외
			userDtlDto.setUserId(userDtlDto.getUserId() + "_WAYLO");
		}
		
		// 사용자 정보 저장
		if ((CanalFrameConstants.INSERT).equals(userDtlDto.getRowStatus()) || (CanalFrameConstants.UPDATE).equals(userDtlDto.getRowStatus())) {
			// 필수값 체크
    		if (userDtlDto.getEmpType() != null && ("A01".equals(userDtlDto.getEmpType()) || "B01".equals(userDtlDto.getEmpType()) || "C01".equals(userDtlDto.getEmpType()) || "D01".equals(userDtlDto.getEmpType())) && (userDtlDto.getCustkey() == null || "".equals(userDtlDto.getCustkey()))) {
    			// 화주/물류협력사/배송업체/공용기기 사용자일 경우 업체코드 필수값 체크
    			throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.074", new String[]{"업체명"})); // {0} 항목은 필수 입니다.
    		}
    		
			/**************** 사용자 정보 저장 start *******************************/
			// PKG 파라마터 세팅 - 공통(1/4)
			ProcedureParametersFactory.initParamDto(cmUserManagerReqDto, userDtlDto, "SPCM_USER");
			
			// PKG 파라마터 세팅 - 업무(2/4)    
			List<String> keyListDynamic = new ArrayList<>();
			keyListDynamic.add("USERID");
			keyListDynamic.add("USERNAME");
			keyListDynamic.add("AUTHORITY");
			keyListDynamic.add("EMPTYPE");
			keyListDynamic.add("DEF_DCCODE");
			keyListDynamic.add("DEF_STORERKEY");
			keyListDynamic.add("DEF_ORGANIZE");
			keyListDynamic.add("DEF_AREA");
			keyListDynamic.add("DEPARTMENT");
			keyListDynamic.add("DEL_YN");
			keyListDynamic.add("STATUS");
			keyListDynamic.add("REP_USER_ID_YN");
			keyListDynamic.add("WEB_USE_YN");
			keyListDynamic.add("DC_APP_USER_YN");
			keyListDynamic.add("DLV_APP_USER_YN");
			keyListDynamic.add("CUSTKEY");
			
			List<String> valueListDynamic = new ArrayList<>();
			valueListDynamic.add(userDtlDto.getUserId());
			valueListDynamic.add(userDtlDto.getUserNm());
			valueListDynamic.add(userDtlDto.getAuthority());
			valueListDynamic.add(userDtlDto.getEmpType());
			valueListDynamic.add(userDtlDto.getDefDccode());
			valueListDynamic.add(userDtlDto.getDefStorerkey());
			valueListDynamic.add(userDtlDto.getDefOrganize());
			valueListDynamic.add(userDtlDto.getDefArea());
			valueListDynamic.add(userDtlDto.getDepartment());
			valueListDynamic.add(userDtlDto.getDelYn());
			valueListDynamic.add(userDtlDto.getStatus());
			valueListDynamic.add(userDtlDto.getRepUserIdYn());
			valueListDynamic.add(userDtlDto.getWebUseYn());
			valueListDynamic.add(userDtlDto.getDcAppUserYn());
			valueListDynamic.add(userDtlDto.getDlvAppUserYn());
			valueListDynamic.add(userDtlDto.getCustkey());
	        
	        // WMS 내부 신규 사용자 등록시
	        if ((CanalFrameConstants.INSERT).equals(userDtlDto.getRowStatus())) {	    		
	        	LocalDate future180 = LocalDate.now().plusDays(180);
        		LocalDate future210 = LocalDate.now().plusDays(210);
        		
        		keyListDynamic.add("LANG_CODE");
        		keyListDynamic.add("COM_CD");
	        	keyListDynamic.add("PASSWDVALIDDATE");
	        	keyListDynamic.add("USEVALIDDATE");
	        	
	        	valueListDynamic.add("ko");
	        	valueListDynamic.add(userDtlDto.getCustkey()); // "업체코드" 값 설정
	        	valueListDynamic.add(future180.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))); // "암호만료기간" (6개월 주기)
	        	valueListDynamic.add(future210.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))); // "사용만료기간" (6개월 주기 + 1개월)
        		
	        	// 사용자 임시 정보 테이블에서 이동할 경우
	        	if (userDtlDto.getTempYn() != null && "Y".equals(userDtlDto.getTempYn())) {
		        	keyListDynamic.add("EMPNO");
		        	keyListDynamic.add("PWD_NO");
		        	keyListDynamic.add("TEMP_YN");
		        	keyListDynamic.add("TMP_PWD_YN");
		        	
		        	valueListDynamic.add(userDtlDto.getEmpNo());
		        	valueListDynamic.add(userDtlDto.getPwdNo());
		        	valueListDynamic.add("Y"); // 임시 사용자 정보 여부
		        	valueListDynamic.add("0"); // "임시비밀번호발급여부"
	        	} else {
	        		userDtlDto.setEmpNo(commonDao.selectOne(SERVICEID_PREFIX + "getMaxEmpNo", userDtlDto));
		        	
		        	// 임시 비밀번호 만들기
	        		userDtlDto.setPwdNo(makeTmpPwd());
		            
		        	keyListDynamic.add("EMPNO");
		        	keyListDynamic.add("PWD_NO");
		        	keyListDynamic.add("TMP_PWD_YN");
		        	
		        	valueListDynamic.add(userDtlDto.getEmpNo()); // "WL" ||  "사원번호" MAX + 1 값 적용
//		        	valueListDynamic.add(CryptoUtil.SHA256(userDtlDto.getPwdNo()));
		        	valueListDynamic.add(CryptoUtil.SHA256(userDtlDto.getPwdNo() + ContextUtil.getProperty("cf.sha256SaltKey")));
		        	valueListDynamic.add("1"); // "임시비밀번호발급여부"
	        	}
	        } else {
	        	keyListDynamic.add("EMPNO");
	        	keyListDynamic.add("LANG_CODE");
	        	keyListDynamic.add("SSO_USE_YN");
	        	
	        	valueListDynamic.add(userDtlDto.getEmpNo());
	        	valueListDynamic.add(userDtlDto.getLangCode());
	        	valueListDynamic.add(userDtlDto.getSsoUseYn());
				
	        	// null 체크
				if (userDtlDto.getPasswdvaliddate() != null) {
					keyListDynamic.add("PASSWDVALIDDATE");
					valueListDynamic.add(userDtlDto.getPasswdvaliddate());
				}				
				if (userDtlDto.getUsevaliddate() != null) {
					keyListDynamic.add("USEVALIDDATE");
					valueListDynamic.add(userDtlDto.getUsevaliddate());
				}
				
				// 임시 비밀번호 발급
				if (userDtlDto.getPwdNo() != null && !"".equals(userDtlDto.getPwdNo())) {
					keyListDynamic.add("PWD_NO");
					keyListDynamic.add("TMP_PWD_YN");
					
//					valueListDynamic.add(CryptoUtil.SHA256(userDtlDto.getPwdNo()));
					valueListDynamic.add(CryptoUtil.SHA256(userDtlDto.getPwdNo() + ContextUtil.getProperty("cf.sha256SaltKey")));
					valueListDynamic.add("1"); // "임시비밀번호발급여부"
				}
	        }
	        
	        userDtlDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyListDynamic.toArray(new String[0]), valueListDynamic.toArray(new String[0])));
	        
			
			// PKG 파라마터 세팅 및 실행 - 업무(2/4)
	        if ((CanalFrameConstants.INSERT).equals(userDtlDto.getRowStatus())) {
	        	userDtlDto.setAvc_COMMAND("CREATION");
	        } else if ((CanalFrameConstants.UPDATE).equals(userDtlDto.getRowStatus())) {
	        	userDtlDto.setAvc_COMMAND("MODIFY");
	        }
			commonDao.exec(CommonConstants.COMMON_CALLPROCEDURE, userDtlDto);
			
			
			// 프로시저 OUT Parameter(3/4)
			String resultCode    = userDtlDto.getResultCode();
			String resultMessage = userDtlDto.getResultMessage();
			
			
			// 프로시저 Exception 처리(4/4)
			if (!resultCode.equals("0")) {
				throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage);
			}
			/**************** 사용자 정보 저장 end *******************************/
			
			
			
			/**************** 사원정보 저장 start *******************************/
			// "핸드폰번호" or "이메일" 정보가 있을 경우
			if (!"".equals(userDtlDto.getHandphoneNo()) || !"".equals(userDtlDto.getMailId())) {
				// 한 쿼리 사용하기 위해 해당 로직 추가
				if (userDtlDto.getCustkey() == null || "".equals(userDtlDto.getCustkey())) {
					userDtlDto.setCustkey(userDtlDto.getComCd());
				}
				commonDao.insert(SERVICEID_PREFIX + "saveUserEmp", userDtlDto);
				
				// 기사 테이블 핸드폰번호 수정
				if (
					(CanalFrameConstants.UPDATE).equals(userDtlDto.getRowStatus()) // 수정시
					&& userDtlDto.getEmpType() != null && "C01".equals(userDtlDto.getEmpType()) // 사원유형이 "배송업체" 일 경우
					&& "N".equals(userDtlDto.getTempYn()) // 임시 테이블 사용자 아닐 경우
				) {	
					commonDao.insert(SERVICEID_PREFIX + "updateDriver", userDtlDto);
				}
			}
			/**************** 사원정보 저장 end *******************************/
			
			
			
			/**************** 기사정보 등록 start *******************************/
			if (
					(CanalFrameConstants.INSERT).equals(userDtlDto.getRowStatus()) // 신규 사용자 등록시
					&& userDtlDto.getTempYn() == null && !"Y".equals(userDtlDto.getTempYn()) // 임시 테이블 사용자 아닐 경우
					&& userDtlDto.getDriverYn() == null && !"Y".equals(userDtlDto.getDriverYn()) // 차량정보 등록 아닐 경우
					&& userDtlDto.getEmpType() != null && "C01".equals(userDtlDto.getEmpType()) // 사원유형이 "배송업체" 일 경우
					&& !"Y".equals(userDtlDto.getRepUserIdYn()) // 대표ID가 아닐 경우
			) {
				commonDao.insert(SERVICEID_PREFIX + "insertDriver", userDtlDto);
			}
			/**************** 기사정보 등록 end *******************************/
			
			
			
			/**************** 임시 비밀번호 SMS 전송 start *******************************/
			// 임시 비밀번호 실 사용자에게 문자 전송
			if ((CanalFrameConstants.INSERT).equals(userDtlDto.getRowStatus()) && !"Y".equals(userDtlDto.getTempYn()) && !"Y".equals(userDtlDto.getDriverYn())) {
				sendSmsPwdTmp(userDtlDto);
	        }
			/**************** 임시 비밀번호 SMS 전송 end *******************************/
		}
		
		/**************** 신규 드라이버 사용자 등록시 센터 권한 설정 start *******************************/
//		if ((CanalFrameConstants.INSERT).equals(userDtlDto.getRowStatus()) && userDtlDto.getDriverYn() != null && "Y".equals(userDtlDto.getDriverYn())) {
//			// "기본센터코드", "기본조직코드" 값 기본 권한으로 설정
//			List<CmUserManagerConnectReqDto> connectList = new ArrayList<CmUserManagerConnectReqDto>();
//			CmUserManagerConnectReqDto connect = new CmUserManagerConnectReqDto();
//			connect.setUserId(userDtlDto.getUserId());
//			connect.setDccode(userDtlDto.getDefDccode());
//			connect.setOrganize(userDtlDto.getDefOrganize());
//			connect.setRowStatus(CanalFrameConstants.INSERT);
//			connectList.add(connect);
//			cmUserManagerReqDto.setConnectList(connectList);
//		}
		/**************** 신규 드라이버 사용자 등록시 센터 권한 설정 end *******************************/
		
		// 사용자별 센터 권한 저장
		saveUserConnect(cmUserManagerReqDto);
		
		// 사용자별 권한 저장
		saveUserAuthority(cmUserManagerReqDto);

		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 사용자 관리 저장 (업체 대표 ID)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.26 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public String saveUserTmp(CmUserManagerReqDto cmUserManagerReqDto) {
		CmUserManagerResDto userDtlDto = cmUserManagerReqDto.getUserDtl();
		
		// 필수값 체크
		if (userDtlDto.getUserNm() == null || "".equals(userDtlDto.getUserNm())) {
			throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.074", new String[]{"사용자명"})); // {0} 항목은 필수 입니다.
		} else if (userDtlDto.getAuthority() == null || "".equals(userDtlDto.getAuthority())) {
			throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.074", new String[]{"대표권한"})); // {0} 항목은 필수 입니다.
		}
//		else if (userDtlDto.getHandphoneNo() == null || "".equals(userDtlDto.getHandphoneNo())) {
//			throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.074", new String[]{"핸드폰번호"})); // {0} 항목은 필수 입니다.
//		}
		
		// 신규 사용자 등록시
		if ((CanalFrameConstants.INSERT).equals(userDtlDto.getRowStatus())) {
			/**************** 임시 사용자 정보 저장 start *******************************/
			// 사용자ID 자동 채번
			String maxSerialKey = commonDao.selectOne(SERVICEID_PREFIX + "getMaxUserTmpNo", userDtlDto);
			userDtlDto.setSerialKey(maxSerialKey);
			userDtlDto.setUserId(maxSerialKey + "_WAYLO");
			
			// 사원번호 MAX 값 설정
			userDtlDto.setEmpNo(commonDao.selectOne(SERVICEID_PREFIX + "getMaxEmpNo", userDtlDto));
			
			// 상태값 "80(임시저장)" 설정
			userDtlDto.setStatus("80");
			
			// 임시 사용자정보 테이블 저장 (SY_FRAMEONE_USER)
			commonDao.insert(SERVICEID_PREFIX + "saveUserTmp", userDtlDto);
			/**************** 임시 사용자 정보 저장 end *******************************/
			
			
			
			/**************** 사원정보 저장 start *******************************/
			// "핸드폰번호" or "이메일" 정보가 있을 경우
			if (!"".equals(userDtlDto.getHandphoneNo()) || !"".equals(userDtlDto.getMailId())) {
				commonDao.insert(SERVICEID_PREFIX + "saveUserEmp", userDtlDto);
			}
			/**************** 사원정보 저장 end *******************************/
			
			
			
			/**************** 권한 설정 start *******************************/
			// "대표권한" 값 기본 권한으로 설정
			List<CmUserManagerAuthorityReqDto> authorityList = new ArrayList<CmUserManagerAuthorityReqDto>();
			CmUserManagerAuthorityReqDto auth = new CmUserManagerAuthorityReqDto();
			auth.setAuthCd("WAYLO_"+userDtlDto.getAuthority());
			auth.setUserId(userDtlDto.getUserId());
			auth.setRowStatus(CanalFrameConstants.INSERT);
			authorityList.add(auth);
			cmUserManagerReqDto.setAuthorityList(authorityList);
			/**************** 권한 설정 end *******************************/
		} else if ((CanalFrameConstants.UPDATE).equals(userDtlDto.getRowStatus())) {
			/**************** 임시 사용자 정보 저장 start *******************************/
			// 임시 사용자정보 테이블 저장 (SY_FRAMEONE_USER)
			commonDao.insert(SERVICEID_PREFIX + "saveUserTmp", userDtlDto);
			/**************** 임시 사용자 정보 저장 end *******************************/
			
			
			
			/**************** 사원정보 저장 start *******************************/
			// "핸드폰번호" or "이메일" 정보가 있을 경우
			if (!"".equals(userDtlDto.getHandphoneNo()) || !"".equals(userDtlDto.getMailId())) {
				commonDao.insert(SERVICEID_PREFIX + "saveUserEmp", userDtlDto);
			}
			/**************** 사원정보 저장 end *******************************/
		}
		
		// 사용자별 센터 권한 저장
		saveUserConnect(cmUserManagerReqDto);
		
		// 사용자별 권한 저장
		saveUserAuthority(cmUserManagerReqDto);
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 기사 사용자 정보 수정
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.23 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public String saveDriverUser(CmUserManagerReqDto cmUserManagerReqDto) {
		CmUserManagerResDto userDtlDto = cmUserManagerReqDto.getUserDtl();
		
		if ((CanalFrameConstants.UPDATE).equals(userDtlDto.getRowStatus())) {
			if (userDtlDto.getHandphoneNo() != null && !"".equals(userDtlDto.getHandphoneNo()) && userDtlDto.getUserId() != null && !"".equals(userDtlDto.getUserId())) {
				commonDao.insert(SERVICEID_PREFIX + "saveDriverUserEmp", userDtlDto);
			}
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 사용자별 센터 권한 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.05 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public String saveUserConnect(CmUserManagerReqDto cmUserManagerReqDto) {
		if (null != cmUserManagerReqDto.getConnectList()) {
			CmUserManagerResDto userDtlDto = cmUserManagerReqDto.getUserDtl();
			
			for (CmUserManagerConnectReqDto connect : cmUserManagerReqDto.getConnectList()) {
				CmUserConnectEntity cmUserConnectEntity = ModelMapperUtil.map(connect, userContext, CmUserConnectEntity.class);
				cmUserConnectEntity.setUserId(userDtlDto.getUserId());
				
				if ((CanalFrameConstants.INSERT).equals(connect.getRowStatus())) {
					commonDao.insert(SERVICEID_PREFIX +"insertUserConnect", cmUserConnectEntity);
				} else if ((CanalFrameConstants.UPDATE).equals(connect.getRowStatus()) && !"".equals(connect.getSerialkey())) {
					commonDao.insert(SERVICEID_PREFIX +"updateUserConnect", cmUserConnectEntity);
				}
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 사용자별 권한 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.08 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public String saveUserAuthority(CmUserManagerReqDto cmUserManagerReqDto) {
		if (null != cmUserManagerReqDto.getAuthorityList()) {
			CmUserManagerResDto userDtlDto = cmUserManagerReqDto.getUserDtl();
			
			for (CmUserManagerAuthorityReqDto auth : cmUserManagerReqDto.getAuthorityList()) {
				SysAuthUserEntity sysAuthUserEntity = ModelMapperUtil.map(auth, userContext, SysAuthUserEntity.class);
				sysAuthUserEntity.setUserId(userDtlDto.getUserId());
				
				if ((CanalFrameConstants.INSERT).equals(auth.getRowStatus())) {
					commonDao.insert(SERVICEID_PREFIX +"insertUserAuthority", sysAuthUserEntity);
				} else if ((CanalFrameConstants.UPDATE).equals(auth.getRowStatus())) {
					commonDao.insert(SERVICEID_PREFIX +"updateAuthority", sysAuthUserEntity);
				}
				
				// 권한 변경 로그 기록
				SysAuthChgLogEntity sysAuthChgLogEntity = ModelMapperUtil.map(auth, userContext, SysAuthChgLogEntity.class);
				sysAuthChgLogEntity.setTgtUserId(auth.getUserId());					// 대상사용자ID
				if ((CanalFrameConstants.INSERT).equals(auth.getRowStatus())) {
					sysAuthChgLogEntity.setPreAuthCd("-");							// 기존권한그룹코드
					sysAuthChgLogEntity.setChgAuthCd(auth.getAuthCd());				// 변경권한그룹코드
					sysAuthChgLogEntity.setActionType("C");							// 행위유형(C/R/U/D)
				} else if ((CanalFrameConstants.UPDATE).equals(auth.getRowStatus())) {
					sysAuthChgLogEntity.setPreAuthCd(auth.getAuthCdOld());			// 기존권한그룹코드
					sysAuthChgLogEntity.setChgAuthCd(auth.getAuthCd());				// 변경권한그룹코드
					sysAuthChgLogEntity.setActionType("U");							// 행위유형(C/R/U/D)
				}
				commonDao.insert("sysAuthorityService.insertAuthChgLog", sysAuthChgLogEntity);
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 사용자 인증 SMS 전송
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.28 breaker3317 생성 </pre>
	 */
	public String insertSmsVerification(CmUserManagerReqDto cmUserManagerReqDto) {
		if (null != cmUserManagerReqDto.getUserList()) {
			for (CmUserManagerReqDto user : cmUserManagerReqDto.getUserList()) {
				// 수신자 정보 조회
				CmUserTmpManagerResDto cmUserTmpManagerResDto = commonDao.selectOne(SERVICEID_PREFIX + "getUserTmpDetail", user);
				if (cmUserTmpManagerResDto == null || cmUserTmpManagerResDto.getHandphoneNo() == null || "".equals(cmUserTmpManagerResDto.getHandphoneNo())) {
					throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.074", new String[]{"핸드폰번호"})); // {0} 항목은 필수 입니다.
				}
				
				CmSmsMmsSendEntity cmSmsMmsSendEntity = ModelMapperUtil.map(user, userContext, CmSmsMmsSendEntity.class);
				cmSmsMmsSendEntity.setSendTitle("사용자 인증"); // 제목
				cmSmsMmsSendEntity.setSendMessage("[CJ프레시웨이 WayLo 사용자 인증 발송]\r\n\r\nWayLo 사용을 위한 인증 링크가 발송 되었습니다.\r\n아래 링크를 통해 사용자("+cmUserTmpManagerResDto.getUserNm()+") 인증해주세요.\r\n\r\n" + ContextUtil.getProperty("cf.domainUrl") + ContextUtil.getProperty("cf.link.custUserSmsSec")); // 메세지
				cmSmsMmsSendEntity.setReceiveName(cmUserTmpManagerResDto.getUserNm()); // 수신자명
				cmSmsMmsSendEntity.setReceivePhone(cmUserTmpManagerResDto.getHandphoneNo()); // 수신자핸드폰번호
				
				// SMS 전송
				commonDao.insert("cmSendService.insertSendSms", cmSmsMmsSendEntity);
				
				// "81(사용자인증대기)"으로 상태값 변경
				CmUserManagerResDto userDtlDto = new CmUserManagerResDto();
				userDtlDto.setUserId(user.getUserId());
				userDtlDto.setStatus("81");
				commonDao.insert(SERVICEID_PREFIX + "saveUserTmp", userDtlDto);
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 사용자 승인
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.29 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public String saveUserApprv(CmUserManagerReqDto cmUserManagerReqDto) {
		if (null != cmUserManagerReqDto.getUserList()) {
			for (CmUserManagerReqDto user : cmUserManagerReqDto.getUserList()) {
				
				if ("82".equals(user.getStatus())) {
					// "83(정직원승인대기)"으로 상태값 변경
					CmUserManagerResDto userDtlDto = new CmUserManagerResDto();
					userDtlDto.setUserId(user.getUserId());
					userDtlDto.setStatus("83");
					commonDao.insert(SERVICEID_PREFIX + "saveUserTmp", userDtlDto);
				} else if ("83".equals(user.getStatus())) {
					/**************** SY_FRAMEONE_USER 임시 사용자 정보를 FRAMEONE_USER 테이블로 이동 start *******************************/
					// SY_FRAMEONE_USER 임시 사용자 정보 조회
					CmUserTmpManagerResDto cmUserTmpManagerResDto = commonDao.selectOne(SERVICEID_PREFIX + "getUserTmpDetail", user);
					
					// 사용자 관리 저장을 위한 DTO 변환
					CmUserManagerResDto userDtlDto = ModelMapperUtil.map(cmUserTmpManagerResDto, userContext, CmUserManagerResDto.class);
					userDtlDto.setRowStatus(CanalFrameConstants.INSERT);
					cmUserManagerReqDto.setUserDtl(userDtlDto);
					
					// 임시 사용자 정보를 FRAMEONE_USER로 이동
					saveUser(cmUserManagerReqDto);
					/**************** SY_FRAMEONE_USER 임시 사용자 정보를 FRAMEONE_USER 테이블로 이동 start *******************************/
					
					
					
					/**************** SY_FRAMEONE_USER 임시 사용자 정보 삭제 start *******************************/
					commonDao.delete(SERVICEID_PREFIX + "deleteUserTmp", user);
					/**************** SY_FRAMEONE_USER 임시 사용자 정보 삭제 end *******************************/
				} else {
					throw new UserHandleException("MSG.COM.ERR.001");
				}
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 임시비밀번호 생성 및 발송
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.23 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public String saveUserTmpPwd(CmUserManagerReqDto cmUserManagerReqDto) {		
		if (null != cmUserManagerReqDto.getUserList()) {
			for (CmUserManagerReqDto user : cmUserManagerReqDto.getUserList()) {
				// 사용자 정보 조회
				CmUserManagerResDto cmUserManagerResDto = commonDao.selectOne(SERVICEID_PREFIX + "getUserDetail", user);
				if (cmUserManagerResDto == null || cmUserManagerResDto.getHandphoneNo() == null || "".equals(cmUserManagerResDto.getHandphoneNo())) {
					throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.074", new String[]{"핸드폰번호"})); // {0} 항목은 필수 입니다.
				}
				
				// 임시 비빌번호 생성
				cmUserManagerResDto.setPwdNo(makeTmpPwd());
				
				// 수정 DATA 정제
				DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
				if (cmUserManagerResDto.getAuthority() != null) {
					cmUserManagerResDto.setAuthority(cmUserManagerResDto.getAuthority().replace("WAYLO_", ""));
				}				
				cmUserManagerResDto.setPasswdvaliddate(LocalDate.parse(cmUserManagerResDto.getPasswdvaliddate(), inputFormatter).format(outputFormatter)); // 암호만료기간
				cmUserManagerResDto.setUsevaliddate(LocalDate.parse(cmUserManagerResDto.getUsevaliddate(), inputFormatter).format(outputFormatter)); // 사용만료기간
				
				// 사용자 정보 수정
				cmUserManagerResDto.setRowStatus(CanalFrameConstants.UPDATE);
				user.setUserDtl(cmUserManagerResDto);
				saveUser(user);
				
				// 임시 비밀번호 실 사용자에게 문자 전송
				sendSmsPwdTmp(cmUserManagerResDto);
			}
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 임시 비밀번호 실 사용자에게 문자 전송
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.23 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public void sendSmsPwdTmp(CmUserManagerResDto user) {
		CmSmsMmsSendEntity cmSmsMmsSendEntity = ModelMapperUtil.map(user, userContext, CmSmsMmsSendEntity.class);
		cmSmsMmsSendEntity.setSendTitle("임시 비밀번호"); // 제목
		cmSmsMmsSendEntity.setSendMessage("[CJ프레시웨이 WayLo]\r\n\r\n임시 비밀번호는 [" + user.getPwdNo() + "]입니다."); // 메세지
//		cmSmsMmsSendEntity.setSendMessage("[임시 비밀번호 발송] 프레시웨이 웹 사용을 위한 임시 비밀번호가 발송 되었습니다.\r\n임시 비밀번호 : "+user.getPwdNo()+"\r\n로그인ID는 관리자에게 문의해주세요."); // 메세지
		cmSmsMmsSendEntity.setReceiveName(user.getUserNm()); // 수신자명
		cmSmsMmsSendEntity.setReceivePhone(user.getHandphoneNo()); // 수신자핸드폰번호
		cmSmsMmsSendEntity.setAddWho(user.getUserId()); // 사용자ID (임시 사용 후 삭제 예정)
		
		commonDao.insert("cmSendService.insertSendSms", cmSmsMmsSendEntity);
	}
	
	/**
	 * @description : 사용자 잠김 계정 해제
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.12 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public String saveUnlockUser(CmUserManagerReqDto cmUserManagerReqDto) {
		if (null != cmUserManagerReqDto.getUserList()) {
			for (CmUserManagerReqDto user : cmUserManagerReqDto.getUserList()) {
				commonDao.insert(SERVICEID_PREFIX + "saveUnlockUser", user);
			}
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 임시 비밀번호 만들기 ("cj${난수4자리}!@")
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.23 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public String makeTmpPwd() {
		int number = (int)(Math.random() * 10000); // 0 ~ 9999
        String formatted = String.format("%04d", number);
        return "cj" + formatted + "!@";
	}

}
