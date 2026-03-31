package cjfw.wms.cm.dto;

import cjfw.wms.common.annotation.MaskingEmail;
import cjfw.wms.common.annotation.MaskingId;
import cjfw.wms.common.annotation.MaskingName;
import cjfw.wms.common.annotation.MaskingTelno;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author      : JiSooKim (jskim14@cj.net) 
 * @date        : 2025.09.11 
 * @description : 사용자 팝업 조회 응답 DTO
 * @issues      :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.11  JiSooKim (jskim14@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "사용자 팝업 조회 응답")
public class CmUserPopupResDto extends CommonDto {
    /** 사용자ID */
    @Schema(description = "사용자ID", example = "user01")
    private String userId;
	
	/** 사용자ID 노출 */
	@MaskingId
	@Schema(description = "사용자ID 노출", example = "")
	private String userIdDisp;

    /** 사용자명 */
	@MaskingName
    @Schema(description = "사용자명", example = "홍길동")
    private String userNm;
	
	/** 사용자명(평문) */
	@Schema(description = "사용자명(평문)", example = "홍길동")
    private String userNmNoMasking;

    /** 사원구분 */
    @Schema(description = "사원구분", example = "01")
    private String empType;

    /** 부서코드 */
    @Schema(description = "부서코드", example = "D001")
    private String department;

    /** 핸드폰번호 */
    @MaskingTelno
    @Schema(description = "핸드폰번호", example = "010-1234-5678")
    private String handphoneNo;

    /** 이메일 */
    @MaskingEmail
    @Schema(description = "이메일", example = "user01@cj.net")
    private String mailId;
}
