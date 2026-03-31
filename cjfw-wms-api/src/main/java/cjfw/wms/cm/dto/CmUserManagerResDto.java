package cjfw.wms.cm.dto;

import cjfw.wms.common.annotation.MaskingEmail;
import cjfw.wms.common.annotation.MaskingId;
import cjfw.wms.common.annotation.MaskingName;
import cjfw.wms.common.annotation.MaskingTelno;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.06.20 
 * @description : 사용자 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.20 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "사용자 응답 DTO")
public class CmUserManagerResDto extends CommonProcedureDto {
	/** 데이터번호 */
	@Schema(description = "데이터번호", example = "")
	private String serialKey;
	
	/** 사용자ID */
	@Schema(description = "사용자ID", example = "")
	private String userId;
	
	/** 사용자ID 노출 */
	@MaskingId
	@Schema(description = "사용자ID 노출", example = "")
	private String userIdDisp;
	
	/** 패스워드 */
	@Schema(description = "패스워드", example = "")
	private String pwdNo;
	
	/** 회사코드 */
	@Schema(description = "회사코드", example = "")
	private String comCd;
	
	/** 사원번호 */
	@Schema(description = "사원번호", example = "")
	private String empNo;
	
	/** 소속구분 */
	@Schema(description = "소속구분", example = "")
	private String empType;
	
	/** 권한코드 */
	@Schema(description = "권한코드", example = "")
	private String authority;
	
	/** 권한코드명 */
	@Schema(description = "권한코드명", example = "")
	private String authorityName;
	
	/** 사용자명 */
	@Schema(description = "사용자명", example = "")
	private String userNm;
	
	/** 사용자명 노출 */
	@MaskingName
	@Schema(description = "사용자명 노출", example = "")
	private String userNmDisp;
	
	/** 다국어코드 */
	@Schema(description = "다국어코드", example = "")
	private String langCode;
	
	/** 기본센터코드 */
	@Schema(description = "기본센터코드", example = "")
	private String defDccode;
	
	/** 기본고객코드 */
	@Schema(description = "기본고객코드", example = "")
	private String defStorerkey;
	
	/** 기본조직코드 */
	@Schema(description = "기본조직코드", example = "")
	private String defOrganize;
	
	/** 기본창고코드 */
	@Schema(description = "기본창고코드", example = "")
	private String defArea;
	
	/** 부서코드 */
	@Schema(description = "부서", example = "")
	private String department;
	
	/** 인사부서명 */
    @Schema(description = "인사부서명", example = "식품서비스팀")
    private String depthrNm;
	
	/** 암호만료기간 */
	@Schema(description = "암호만료기간", example = "")
	private String passwdvaliddate;
	
	/** 사용만료기간 */
	@Schema(description = "사용만료기간", example = "")
	private String usevaliddate;
	
	/** 진행상태 */
	@Schema(description = "진행상태", example = "")
	private String status;
	
	/** 삭제여부 */
	@Schema(description = "삭제여부", example = "N")
	private String delYn;
	
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
	@Schema(description = "업체명", example = "[33828]수협강서공판장")
	private String custkeyNm;
	
	/** 메일ID */
	@MaskingEmail
	@Schema(description = "메일ID", example = "")
	private String mailId;
	
	/** 핸드폰번호 */
	@MaskingTelno
	@Schema(description = "핸드폰번호", example = "")
	private String handphoneNo;
	
	/** 임시테이블여부 */
	@Schema(description = "임시테이블여부", example = "")
	private String tempYn;
	
	/** 드라이버여부 */
	@Schema(description = "드라이버여부", example = "")
	private String driverYn;
	
	/** 차량 소유 업체 키 */
	@Schema(description = "차량 소유 업체 키", example = "")
    private String carAgentKey;
	
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
	/** 비정규직여부 */
	@Schema(description = "비정규직여부", example = "")
	private String nonRegularYn;
	
	/** SSO사용여부 */
	@Schema(description = "SSO사용여부", example = "")
	private String ssoUseYn;
	
	/** 사용자상태코드 */
	@Schema(description = "사용자상태코드", example = "01")
	private String userStsCd;
}