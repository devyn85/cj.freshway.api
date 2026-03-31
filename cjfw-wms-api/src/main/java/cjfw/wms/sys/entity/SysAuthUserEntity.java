package cjfw.wms.sys.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.08.21 
 * @description : 권한별 사용자 정보 Entity
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.21 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Data
public class SysAuthUserEntity {
	/** 권한그룹코드 */
	@Schema(description = "권한그룹코드", example = "")
	private String authCd;
	
	/** 권한그룹코드(UPDATE 조건문에 사용) */
	@Schema(description = "권한그룹코드(UPDATE 조건문에 사용)", example = "")
	private String authCdOld;
	
	/** 사용자ID */
	@Schema(description = "사용자ID", example = "")
	private String userId;
	
	/** 등록자ID */
	@Schema(description = "등록자ID", example = "")
	private String regId;
	
	/** 등록일시 */
	@Schema(description = "등록일시", example = "")
	private String regDtm;
	
	/** 수정자ID */
	@Schema(description = "수정자ID", example = "")
	private String updId;
	
	/** 수정일시 */
	@Schema(description = "수정일시", example = "")
	private String updDtm;
}