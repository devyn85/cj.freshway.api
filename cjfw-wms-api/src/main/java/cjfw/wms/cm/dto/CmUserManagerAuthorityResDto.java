package cjfw.wms.cm.dto;

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
public class CmUserManagerAuthorityResDto {
	/** 사용자아이디 */
	@Schema(description = "사용자아이디", example = "dev01")
	private String userId;
	
	/** 권한그룹코드 */
	@Schema(description = "권한그룹코드", example = "LOGISONE_00")
	private String authCd;
	
	/** 권한그룹코드(UPDATE 조건문에 사용) */
	@Schema(description = "권한그룹코드(UPDATE 조건문에 사용)", example = "LOGISONE_00")
	private String authCdOld;
	
	/** 권한그룹명 */
	@Schema(description = "권한그룹명", example = "시스템관리자")
	private String authNm;
	
	/** 등록자ID */
	@Schema(description = "등록자ID", example = "dev01")
	private String regId;

	/** 등록일시 */
	@Schema(description = "등록일시", example = "2015-10-23 오후 6:08:43")
	private String regDtm;
	
	/** 등록자명 */
	@Schema(description = "등록자명", example = "")
	private String regNm;
	
	/** 수정자ID */
	@Schema(description = "수정자ID", example = "dev01")
	private String updId;

	/** 수정일시 */
	@Schema(description = "수정일시", example = "2021-10-27 오후 5:39:05")
	private String updDtm;
	
	/** 수정자명 */
	@Schema(description = "수정자명", example = "")
	private String updNm;
	
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}