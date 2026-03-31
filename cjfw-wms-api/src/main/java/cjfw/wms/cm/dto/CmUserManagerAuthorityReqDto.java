package cjfw.wms.cm.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.06.20 
 * @description : 사용자 권합그룹 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.20 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "사용자 권합그룹 응답 DTO")
public class CmUserManagerAuthorityReqDto extends CommonDto {
	/** 사용자아이디 */
	@Schema(description = "사용자아이디", example = "dev01")
	private String userId;
	
	/** 권한그룹코드 */
	@Schema(description = "권한그룹코드", example = "LOGISONE_00")
	private String authCd;
	
	/** 권한그룹코드(UPDATE 조건문에 사용) */
	@Schema(description = "권한그룹코드(UPDATE 조건문에 사용)", example = "LOGISONE_00")
	private String authCdOld;
}