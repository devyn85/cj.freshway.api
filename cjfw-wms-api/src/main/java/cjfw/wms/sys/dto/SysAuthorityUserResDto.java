package cjfw.wms.sys.dto;

import cjfw.wms.common.annotation.MaskingId;
import cjfw.wms.common.annotation.MaskingName;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.08.20 
 * @description : 권한그룹별 사용자 조회 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.20 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "권한그룹별 사용자 조회 응답 DTO")
public class SysAuthorityUserResDto extends CommonDto {
	/** 권한그룹코드 */
	@Schema(description = "권한그룹코드", example = "LOGISONE_00")
	private String authCd;
	
	/** 사용자ID */
	@Schema(description = "사용자ID", example = "")
	private String userId;
	
	/** 사용자ID 노출 */
	@MaskingId
	@Schema(description = "사용자ID 노출", example = "")
	private String userIdDisp;
	
	/** 사용자명 */
	@MaskingName
	@Schema(description = "사용자명", example = "")
	private String userNm;
	
	/** 소속구분 */
	@Schema(description = "소속구분", example = "")
	private String empType;
	
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
	
	/** 포함여부 */
	@Schema(description = "포함여부", example = "")
	private String useYn;
	
	/** 등록자명 */
	@Schema(description = "등록자명", example = "")
	private String regNm;
	
	/** 수정자명 */
	@Schema(description = "수정자명", example = "")
	private String updNm;
	
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}