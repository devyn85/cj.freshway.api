package cjfw.wms.sys.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.05.15 
 * @description : 그룹권한 조회 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.15 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "프로그램 권한 요청 DTO")
public class SysAuthorityGrpReqDto {
	/** 권한그룹코드 */
	@Schema(description = "권한그룹코드", example = "LOGISONE_00")
	private String authCd;

	/** 권한그룹명 */
	@Schema(description = "권한그룹명", example = "시스템관리자")
	private String authNm;
	
	/** 권한그룹구분 */
	@Schema(description = "권한그룹구분", example = "1")
	private String authCl;
	
	/** 시스템구분 */
	@Schema(description = "시스템구분", example = "LOGISONE")
	private String systemCl;
	
	/** 사용여부 */
	@Schema(description = "사용여부", example = "1")
	private String useYn;
	
	/** 등록자ID */
	@Schema(description = "등록자ID", example = "dev01")
	private String regId;

	/** 등록일시 */
	@Schema(description = "등록일시", example = "2015-10-23 오후 6:08:43")
	private String regDtm;

	/** 수정자ID */
	@Schema(description = "수정자ID", example = "dev01")
	private String updId;

	/** 수정일시 */
	@Schema(description = "수정일시", example = "2021-10-27 오후 5:39:05")
	private String updDtm;
	
	/** GridRow 저장 구분 */
	@Schema(description = "GridRow 저장 구분", example = "U", allowableValues = {"I", "U", "D"})
    private String rowStatus;
}
