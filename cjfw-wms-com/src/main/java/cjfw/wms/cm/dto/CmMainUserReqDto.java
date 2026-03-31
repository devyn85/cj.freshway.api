package cjfw.wms.cm.dto;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.05.12 
 * @description : Main 사용자정보 조회 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.12 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Schema(description = "로그인 사용자 조회 요청")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CmMainUserReqDto extends CommonProcedureDto {	
	/** 조회Type */
    @Schema(description = "조회Type", example = "mobNo")
    private String selType;
    
	/** 사용자ID */
    @Schema(description = "사용자ID", example = "dev01")
    private String userId;
    
    /** 통합회원번호(SSO용) */
    @Schema(description = "통합회원번호(SSO용)", example = "1000000652")
    private String userNo;
    
    /** SSO 로그인 여부 */
    @Schema(description = "SSO 로그인 여부", example = "Y")
    private String ssoLoginYn;
}