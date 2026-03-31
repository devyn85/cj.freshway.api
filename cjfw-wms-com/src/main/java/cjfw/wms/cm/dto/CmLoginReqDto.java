package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.05.09 
 * @description : 로그인 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.09 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "로그인 정보")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CmLoginReqDto {
	
	/** 데이터번호 */
	@Schema(description = "데이터번호", example = "100004")
	private String serialkey;
	
	/** 사용자ID */
    @Schema(description = "사용자ID", nullable = false, example = "dev01")
    private String userId;
    
    /** 사용자ID(기존) */
    @Schema(description = "사용자ID(기존)", nullable = false, example = "dev01")
    private String userIdOld;
    
	/** 사용자ID */
    @Schema(description = "사용자ID", nullable = false, example = "dev01")
    private String username;

    /** 암호화된 비밀번호 */
    @Schema(description = "암호화된 비밀번호", nullable = false, example = "")
    private String password;
    
    /** 암호화된 이전 패스워드 */
    @Schema(description = "암호화된 이전 패스워드", example = "")
    private String curPwdNo;
    
    /** 암호화된 패스워드 */
    @Schema(description = "암호화된 패스워드", example = "")
    private String pwdNo;
    
    /** 사용자명 */
    @Schema(description = "사용자명", example = "홍길동")
    private String userNm;
    
    /** 핸드폰번호 */
    @Schema(description = "핸드폰번호", example = "010-9999-8888")
    private String handphoneNo;
    
    /** 진행상태 */
    @Schema(description = "진행상태", example = "81")
    private String status;
    
    /** 인증번호 */
    @Schema(description = "인증번호", example = "123456")
    private String securityKey;
    
    /** 검증 사용 여부 */
    @Schema(description = "검증 사용 여부", example = "Y")
    private String verificationYn;
    
    /** 통합회원번호 */
    @Schema(description = "통합회원번호", example = "1000002576")
    private String userNo;
}
