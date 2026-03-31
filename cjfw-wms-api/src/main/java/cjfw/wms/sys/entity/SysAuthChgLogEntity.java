package cjfw.wms.sys.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.11.17 
 * @description : 권한 변경 로그 Entity
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.17 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Data
public class SysAuthChgLogEntity {
	/** 대상사용자ID */
	@Schema(description = "대상사용자ID", example = "")
	private String tgtUserId;
	
	/** 기존권한그룹코드 */
	@Schema(description = "기존권한그룹코드", example = "")
	private String preAuthCd;
	
	/** 변경권한그룹코드 */
	@Schema(description = "변경권한그룹코드", example = "")
	private String chgAuthCd;
	
	/** 행위유형(C/R/U/D) */
	@Schema(description = "행위유형(C/R/U/D)", example = "")
	private String actionType;
	
	/** 권한부여자ID */
	@Schema(description = "권한부여자ID", example = "")
	private String grantUserId;
	
	/** 부여일시 */
	@Schema(description = "부여일시", example = "")
	private String grantDt;
	
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