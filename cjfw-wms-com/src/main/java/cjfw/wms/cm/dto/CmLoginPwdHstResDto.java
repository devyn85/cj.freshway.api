package cjfw.wms.cm.dto;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.10.16 
 * @description : 비밀번호 이력 Entity(테이블: TB_CF_PWD_HST)
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.16 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "비밀번호 히스토리 응답 DTO")
public class CmLoginPwdHstResDto {
    /** 암호화된 비밀번호 */
	@Schema(description = "암호화된 비밀번호", example = "")
    private String userPwd;
	
    /** 등록일시 */
	@Schema(description = "등록일시", example = "")
    private Date regDt;
}
