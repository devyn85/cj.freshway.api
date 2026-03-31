package cjfw.wms.tm.entity;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net)
 * @date : 2025.10.28
 * @description : 배송이슈 첨부파일 Entity
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.28 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Data
public class TmIssueFileUploadPopupEntity extends CommonProcedureDto {
	/** 조회 시 체크 여부 (고정값 '0') */
    @Schema(description = "체크 여부", example = "")
    private String checkYn;

    /** 파일 시리얼키 (PK, 시퀀스 자동 생성) */
    @Schema(description = "시리얼키", example = "")
    private String serialKey;

    /** 문서 ID (업로드 대상 ID) */
    @Schema(description = "문서 ID", example = "PLANT123")
    private String docId;

    /** 문서 유형 */
    @Schema(description = "문서 유형", example = "")
    private String docType;

    /** 문서 구분 명 (고정값: MS_PLANTXSL) */
    @Schema(description = "문서 구분", example = "MS_PLANTXSL")
    private String docName;

    /** 원본 파일명 */
    @Schema(description = "파일명", example = "")
    private String fileName;

    /** 파일 확장자 */
    @Schema(description = "파일 확장자", example = "")
    private String fileExtension;

    /** 파일 저장 위치 */
    @Schema(description = "파일 저장 위치", example = "")
    private String fileLocation;

    /** 파일 크기 (바이트) */
    @Schema(description = "파일 크기(Bytes)", example = "")
    private String fileSizeBytes;

    /** 전송 파일명 (임시명 등) */
    @Schema(description = "전송 파일명", example = "")
    private String transFileName;

    /** 업로드 해시 ID (사용 안 함, 공백으로 저장됨) */
    @Schema(description = "업로드 해시 ID", example = "")
    private String uploadHashId;

    /** 업로드 응답 문서 ID */
    @Schema(description = "업로드 응답 문서 ID", example = "")
    private String uploadResDocId;

    /** 업로드 파일명 */
    @Schema(description = "업로드 파일명", example = "")
    private String uploadFileName;

    /** 업로드 작업장 ID */
    @Schema(description = "업로드 작업장 ID", example = "")
    private String uploadWorkplaceId;

    /** 업로드 위치 (사용 안 함, 공백으로 저장됨) */
    @Schema(description = "업로드 위치", example = "")
    private String uploadLocation;

    /** 다운로드 횟수 */
    @Schema(description = "다운로드 횟수", example = "3")
    private Integer downloadCount;

    /** 파일 내용 (사용 안 함, 공백으로 저장됨) */
    @Schema(description = "파일 내용", example = "")
    private String fileContent;

    /** 파일 유형 */
    @Schema(description = "파일 유형", example = "HTTP")
    private String fileType;

    /** 상태 (Y: 사용, N: 미사용) */
    @Schema(description = "상태", example = "Y")
    private String status;

    /** 등록자 ID */
    @Schema(description = "등록자 ID", example = "admin")
    private String addWho;

    /** 등록일시 */
    @Schema(description = "등록일시", example = "2025-06-11T14:30:00")
    private String addDate;

    /** 수정자 ID */
    @Schema(description = "수정자 ID", example = "admin")
    private String editWho;

    /** 수정일시 */
    @Schema(description = "수정일시", example = "2025-06-11T14:30:00")
    private String editDate;

    @Schema(description = "수정일시", example = "2025-06-11T14:30:00")
    private String attchFileNm;

    @Schema(description = "수정일시", example = "2025-06-11T14:30:00")
    private String fileNm;

    @Schema(description = "수정일시", example = "2025-06-11T14:30:00")
    private String attchFileSz;

    @Schema(description = "수정일시", example = "2025-06-11T14:30:00")
    private String attchFileExtNm;
    
    // 추가    
    @Schema(description = "이슈번호")
    private String issueNo;
}
