package cjfw.wms.ms.entity;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.06.23
 * @description : 센터서류 Entity
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.24        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Schema(description = "센터서류 Entity") 
public class MsCenterDocEntity extends CommonDto {	
	/** 일련번호 (고유 키) */
    @Schema(description = "일련번호 (고유 키)", example = "123456789012345")
    private String serialKey;

    /** 요청 번호 */
    @Schema(description = "요청 번호", example = "REQ20250624-001", maxLength = 20, requiredMode = Schema.RequiredMode.REQUIRED)
    private String reqNo;

    /** DC 코드 */
    @Schema(description = "DC 코드", example = "DC01", maxLength = 10, requiredMode = Schema.RequiredMode.REQUIRED)
    private String dccode;

    /** 화주 키 */
    @Schema(description = "화주 키", defaultValue = "FW00", maxLength = 20, requiredMode = Schema.RequiredMode.REQUIRED)
    private String storerkey;

    /** 거래처 키 */
    @Schema(description = "거래처 키", example = "CUST001", maxLength = 20)
    private String custkey;

    /** 요청자 ID */
    @Schema(description = "요청자 ID", example = "testuser", maxLength = 24)
    private String reqId;

    /** 요청 문서 종류 */
    @Schema(description = "요청 문서 종류", example = "거래명세서", maxLength = 20)
    private String reqDoc;

    /** 요청 일자 */
    @Schema(description = "요청 일자", example = "2025-06-24T13:51:00", requiredMode = Schema.RequiredMode.REQUIRED)
    private String reqDate;

    /** 등록 메모 */
    @Schema(description = "등록 메모", example = "긴급 요청건입니다.", maxLength = 500)
    private String regMemo;

    /** 메일 전송 여부 */
    @Schema(description = "메일 전송 여부", defaultValue = "N", example = "N", maxLength = 1, requiredMode = Schema.RequiredMode.REQUIRED)
    private String mailYn = "N";

    /** 기타 필드 1 */
    @Schema(description = "기타 필드 1", maxLength = 100)
    private String other01;

    /** 기타 필드 2 */
    @Schema(description = "기타 필드 2", maxLength = 100)
    private String other02;

    /** 기타 필드 3 */
    @Schema(description = "기타 필드 3", maxLength = 100)
    private String other03;

    /** 기타 필드 4 */
    @Schema(description = "기타 필드 4", maxLength = 100)
    private String other04;

    /** 기타 필드 5 */
    @Schema(description = "기타 필드 5", maxLength = 100)
    private String other05;

    /** 상태 코드 */
    @Schema(description = "상태 코드", defaultValue = "00", example = "00", maxLength = 10, requiredMode = Schema.RequiredMode.REQUIRED)
    private String status;

    /** 삭제 여부 */
    @Schema(description = "삭제 여부", defaultValue = "N", example = "N", maxLength = 1, requiredMode = Schema.RequiredMode.REQUIRED)
    private String delYn;

    /** Traffic Cop 필드 */
    @Schema(description = "Traffic Cop 필드", maxLength = 10)
    private String trafficcop;

    /** Archive Cop 필드 */
    @Schema(description = "Archive Cop 필드", maxLength = 1)
    private String archivecop;

    /** 등록 여부 */
    @Schema(description = "등록 여부", defaultValue = "N", example = "Y", maxLength = 1)
    private String regYn;

    /** 문서 수 */
    @Schema(description = "문서 수", defaultValue = "N", example = "3", maxLength = 2)
    private String docCnt;

    /** 등록일시 (자동 생성) */
    @Schema(description = "등록일시 (자동 생성)", accessMode = Schema.AccessMode.READ_ONLY)
    private String adddate;

    /** 수정일시 (자동 갱신) */
    @Schema(description = "수정일시 (자동 갱신)", accessMode = Schema.AccessMode.READ_ONLY)
    private String editdate;

    /** 등록자 */
    @Schema(description = "등록자", defaultValue = "SYSTEM", example = "SYSTEM", maxLength = 24, requiredMode = Schema.RequiredMode.REQUIRED)
    private String addwho;

    /** 수정자 */
    @Schema(description = "수정자", defaultValue = "SYSTEM", example = "SYSTEM", maxLength = 24, requiredMode = Schema.RequiredMode.REQUIRED)
    private String editwho;
    
    /** 파일고유 KEY */
    @Schema(description = "파일고유 KEY", example = "")
    private String uploadResDocId;
    
    /** 파일명 */
    @Schema(description = "파일명", example = "")
    private String fileName;
    
    /** 파일확장자 */
    @Schema(description = "파일확장자", example = "")
    private String fileExtension;
    
    /** 파일크기BYTE */
    @Schema(description = "파일크기BYTE", example = "")
    private String fileSizeBytes;
    
    /** 연동파일명 */
    @Schema(description = "연동파일명", example = "")
    private String transFileName;
}
