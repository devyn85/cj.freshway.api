package cjfw.wms.cm.dto;

import cjfw.wms.common.annotation.MaskingEmail;
import cjfw.wms.common.annotation.MaskingId;
import cjfw.wms.common.annotation.MaskingName;
import cjfw.wms.common.annotation.MaskingTelno;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author      : JiSooKim (jskim14@cj.net) 
 * @date        : 2025.09.11
 * @description : 수신그룹 조회 응답 DTO
 * @issues      :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.11 JiSooKim (jskim14@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "수신그룹 조회 응답")
public class CmReceiveGroupResDto extends CommonDto {
    /** 수신그룹ID */
    @Schema(description = "수신그룹ID", example = "10001")
    private Long recvGroupId;

    /** 수신그룹명 */
    @Schema(description = "수신그룹명", example = "관리자그룹")
    private String recvGroupNm;

    /** 메모 */
    @Schema(description = "메모", example = "관리자 전용 그룹")
    private String memo;

    /** 사용여부 */
    @Schema(description = "사용여부", example = "Y")
    private String useYn;

    /** 등록일자 */
    @Schema(description = "등록일자", example = "2025-09-01")
    private String addDate;

    /** 수정일자 */
    @Schema(description = "수정일자", example = "2025-09-01")
    private String editDate;

    /** 등록자 */
    @Schema(description = "등록자", example = "SYSTEM")
    private String addWho;

    /** 수정자 */
    @Schema(description = "수정자", example = "SYSTEM")
    private String editWho;

    /** 사용자ID */
    @Schema(description = "사용자ID", example = "user01")
    private String userId;
    
    /** 사용자ID 노출 */
	@MaskingId
	@Schema(description = "사용자ID 노출", example = "")
	private String userIdDisp;

    /** 사원명 */
    @MaskingName
    @Schema(description = "사원명", example = "홍길동")
    private String userNm;

    /** 사원구분 */
    @Schema(description = "사원구분", example = "1")
    private String empType;

    /** 부서코드 */
    @Schema(description = "부서코드", example = "D001")
    private String affiliation;
    
    /** 인사부서명 */
    @Schema(description = "인사부서명", example = "식품서비스팀")
    private String department;

    /** 핸드폰번호 */
    @MaskingTelno
    @Schema(description = "핸드폰번호", example = "010-1234-5678")
    private String handphoneNo;

    /** 이메일 */
    @MaskingEmail
    @Schema(description = "이메일", example = "user01@cj.net")
    private String mailId;
    
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