package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.05.12 
 * @description : Main 사용자정보 조회 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.12 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "로그인 사용자 조회 응답")
public class CmMainUserResDto {
	/** 통합회원번호 */
    @Schema(description = "통합회원번호", example = "1000001329")
    private String userNo;
    
	/** 사용자ID */
    @Schema(description = "사용자ID", example = "dev01")
    private String userId;
    
    /** 사용자명 */
    @Schema(description = "사용자명", example = "개발자01")
    private String userNm;
    
    /** 패스워드 */
    @Schema(description = "패스워드", example = "")
    private String pwdNo;
    
    /** 사원번호 */
    @Schema(description = "사원번호", example = "830911")
    private String empNo;
    
    /** 최종패스워드갱신일자 */
    @Schema(description = "최종패스워드갱신일자", example = "20250113")
    private String pwdChgDt;
    
    /** 연속로그인실패수 */
    @Schema(description = "연속로그인실패수", example = "0")
    private String failInCnt;
    
    /** 사용자상태코드 */
    @Schema(description = "사용자상태코드", example = "01")
    private String userStsCd;
    
    /** 임시비밀번호발급여부 */
    @Schema(description = "임시비밀번호발급여부", example = "0")
    private String tmpPwdYn;
    
    /** 모든 네트워크 아이피 허용 여부 */
    @Schema(description = "모든 네트워크 아이피 허용 여부", example = "0")
    private String allowAllIpYn;
    
    /** 소속구분 */
    @Schema(description = "소속구분", example = "01")
    private String emptype;
    
    /** 권한코드 */
    @Schema(description = "권한코드", example = "05")
    private String authority;
    
    /** 권한코드 목록 */
    @Schema(description = "권한코드 목록", example = "00,05")
    private String roles;
    
    /** 다국어코드 */
    @Schema(description = "다국어코드", example = "ko")
    private String langCode;
    
    /** 기본센터코드 */
    @Schema(description = "기본센터코드", example = "2600")
    private String defDccode;
    
    /** 기본고객코드 */
    @Schema(description = "기본고객코드", example = "FW00")
    private String defStorerkey;
    
    /** 기본조직코드 */
    @Schema(description = "기본조직코드", example = "STD")
    private String defOrganize;
    
    /** 기본창고코드 */
    @Schema(description = "기본창고코드", example = "STD")
    private String defArea;
    
    /** 부서코드 */
    @Schema(description = "부서코드", example = "F&D ITO팀")
    private String department;
    
    /** 회사코드 */
    @Schema(description = "회사코드", example = "B50")
    private String comCd;
    
    /** 초기시스템구분 */
    @Schema(description = "초기시스템구분", example = "LOGISONE")
    private String startSysCl;
    
    /** 접속 여부 */
    @Schema(description = "접속 여부", example = "N")
    private String connectYn;
    
    /** 리부팅권장 */
    @Schema(description = "리부팅권장", example = "N")
    private String reboot;
    
    /** 접속DB SPID */
    @Schema(description = "접속DB SPID", example = "1184221002")
    private String spid;
    
    /** 최종접속IP */
    @Schema(description = "최종접속IP", example = "10.184.221.2")
    private String ipaddress;
    
    /** 접속시스템코드 */
    @Schema(description = "접속시스템코드", example = "")
    private String ethernetaddress;
    
    /** 최종로그인일시 */
    @Schema(description = "최종로그인일시", example = "2025-02-05 오전 9:44:39")
    private String lastlogin;
    
    /** 삭제여부 */
    @Schema(description = "삭제여부", example = "N")
    private String delYn;
    
    /** 메일ID */
    @Schema(description = "메일ID", example = "test@cj.net")
    private String emailAddr;
    
    /** 핸드폰번호 */
    @Schema(description = "핸드폰번호", example = "01010041004")
    private String mobNo;
    
    /** 회사전화번호 */
    @Schema(description = "회사전화번호", example = "0210041004")
    private String telNo;
    
    /** 부서코드 */
    @Schema(description = "부서코드", example = "B50G001667")
    private String deptCd;
    
    /** 부서명 */
    @Schema(description = "부서명", example = "")
    private String deptNm;
    
    /** 대표사용자ID여부 */
    @Schema(description = "대표사용자ID여부", example = "")
    private String repUserIdYn;
    
    /** 웹사용여부 */
    @Schema(description = "웹사용여부", example = "")
    private String webUseYn;
    
    /** 센터APP사용여부 */
    @Schema(description = "센터APP사용여부", example = "")
    private String dcAppUserYn;
    
    /** 배송APP사용여부 */
    @Schema(description = "배송APP사용여부", example = "")
    private String dlvAppUserYn;
    
    /** 업체코드 */
    @Schema(description = "업체코드", example = "")
    private String custkey;
    
    /** 업체명 */
    @Schema(description = "업체명", example = "")
    private String custkeyNm;
    
    /** 비정규직여부 */
    @Schema(description = "비정규직여부", example = "")
    private String nonRegularYn;
    
    /** SSO사용여부 */
    @Schema(description = "SSO사용여부", example = "")
    private String ssoUseYn;
    
    
    
    
    /** 칼럼 외 */
    
    /** 최종패스워드갱신일자 */
    @Schema(description = "최종패스워드갱신일자", example = "119")
    private String pwdRevByDiff;
    
    /** 사용만료기간갱신일자 */
    @Schema(description = "사용만료기간갱신일자", example = "119")
    private String useRevByDiff;
    
    /** 최종로그인일시 */
    @Schema(description = "최종로그인일시", example = "96")
    private String loginDtDiff;
    
    /** 암호만료기간 */
    @Schema(description = "암호만료기간", example = "20390325000000")
    private String passwdvaliddate;
    
    /** 사용만료기간 */
    @Schema(description = "사용만료기간", example = "20400330000000")
    private String usevaliddate;
}