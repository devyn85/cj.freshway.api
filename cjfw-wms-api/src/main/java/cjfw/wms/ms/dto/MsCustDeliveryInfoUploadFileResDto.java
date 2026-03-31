package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net)
 * @date : 2025.08.20 
 * @description : 거래처 정보 조회 (단건) 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.20 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Schema(description = "거래처 정보 조회 (단건) 응답 DTO")
public class MsCustDeliveryInfoUploadFileResDto {
	
	/** 체크 여부 */
    @Schema(description = "체크 여부", example = "")
    private String checkyn;

    /** 데이터 번호 */
    @Schema(description = "데이터 번호", example = "")
    private String serialkey;

    /** 이슈 번호 */
    @Schema(description = "이슈 번호", example = "")
    private String issueno;

    /** 문서 타입 */
    @Schema(description = "문서 타입", example = "")
    private String docType;

    /** 문서 이름 */
    @Schema(description = "문서 이름", example = "")
    private String docName;

    /** 파일 이름 */
    @Schema(description = "파일 이름", example = "")
    private String fileName;

    /** 파일 확장자 */
    @Schema(description = "파일 확장자", example = "")
    private String fileExtension;

    /** 파일 위치 */
    @Schema(description = "파일 위치", example = "")
    private String fileLocation;

    /** 파일 크기 (바이트) */
    @Schema(description = "파일 크기 (바이트)", example = "")
    private Integer fileSizeBytes;

    /** 변환 파일 이름 */
    @Schema(description = "변환 파일 이름", example = "")
    private String transFileName;

    /** 업로드 해시 ID */
    @Schema(description = "업로드 해시 ID", example = "")
    private String uploadHashId;

    /** 업로드 리소스 문서 ID */
    @Schema(description = "업로드 리소스 문서 ID", example = "")
    private String uploadResDocId;

    /** 업로드 파일 이름 */
    @Schema(description = "업로드 파일 이름", example = "")
    private String uploadFileName;

    /** 업로드 작업장 ID */
    @Schema(description = "업로드 작업장 ID", example = "")
    private String uploadWorkplaceId;

    /** 업로드 위치 */
    @Schema(description = "업로드 위치", example = "")
    private String uploadLocation;

    /** 다운로드 횟수 */
    @Schema(description = "다운로드 횟수", example = "")
    private String downloadCount;

    /** 파일 타입 */
    @Schema(description = "파일 타입", example = "")
    private String fileType;

    /** 상태 */
    @Schema(description = "상태", example = "")
    private String status;

    /** 등록자 */
    @Schema(description = "등록자", example = "")
    private String addwho;

    /** 등록일 */
    @Schema(description = "등록일", example = "")
    private String adddate;

    /** 수정자 */
    @Schema(description = "수정자", example = "")
    private String editwho;

    /** 수정일 */
    @Schema(description = "수정일", example = "")
    private String editdate;
}
