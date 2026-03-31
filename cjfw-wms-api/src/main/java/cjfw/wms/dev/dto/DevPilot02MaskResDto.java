package cjfw.wms.dev.dto;

import cjfw.wms.common.annotation.MaskingAcntno;
import cjfw.wms.common.annotation.MaskingAddress;
import cjfw.wms.common.annotation.MaskingAll;
import cjfw.wms.common.annotation.MaskingBirthday;
import cjfw.wms.common.annotation.MaskingBizno;
import cjfw.wms.common.annotation.MaskingCardno;
import cjfw.wms.common.annotation.MaskingDrvno;
import cjfw.wms.common.annotation.MaskingEmail;
import cjfw.wms.common.annotation.MaskingEngName;
import cjfw.wms.common.annotation.MaskingId;
import cjfw.wms.common.annotation.MaskingIp;
import cjfw.wms.common.annotation.MaskingName;
import cjfw.wms.common.annotation.MaskingPassPort;
import cjfw.wms.common.annotation.MaskingQrcd;
import cjfw.wms.common.annotation.MaskingSsn;
import cjfw.wms.common.annotation.MaskingTelno;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : SangSuSung(kduimux@cj.com) 
 * @date : 2025.06.14 
 * @description : 마스킹 처리를 하는 등답 dto 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.14 SangSuSung(kduimux@cj.com) 생성 </pre>
 */
@Data
@NoArgsConstructor
@Schema(description = "마스킹 목록 응답") 
public class DevPilot02MaskResDto extends CommonDto { 
	
	/** 사원번호 */
	private String empNo; 
	
	/** 이름 */ 
	@MaskingName
	private String name;
	
	/** 영문이름 */ 
	@MaskingEngName
	private String engName;
	
	/** 전화번호 */ 
	@MaskingTelno 
	private String telNo;		
	
	/** 주소 */ 
	@MaskingAddress
	private String addr;	
	
	/** 상세주소 */ 
	@MaskingAll 
	private String dtAddr;	
	
	/** 이메일 */ 
	@MaskingEmail 
	private String email;	
	
	/** 주민번호 */ 
	@MaskingSsn 
	private String jumin;	
	
	/** 생년월일 */ 
	@MaskingBirthday 
	private String birth;		
	
	/** 운전면허번호 */ 
	@MaskingDrvno 
	private String drvLicense;	
	
	/** 여권번호 */ 
	@MaskingPassPort
	private String passport;	
	
	/** 현금영수증카드 */ 
	@MaskingCardno 
	private String cashCard;	
	
	/** 신용카드 */ 
	@MaskingCardno 
	private String creditCard;	
	
	/** 기타카드 */ 
	@MaskingCardno 
	private String etcCard;	
	
	/** 사업자등록번호 */ 
	@MaskingBizno 
	private String bizNo;		
	
	/** 계좌번호 */ 
	@MaskingAcntno 
	private String accountNo;	
	
	/** QR */ 
	@MaskingQrcd
	private String qrCd;
	
	/** IP */ 
	@MaskingIp 
	private String ip;		
	
	/** ID */ 
	@MaskingId 
	private String id;	

}