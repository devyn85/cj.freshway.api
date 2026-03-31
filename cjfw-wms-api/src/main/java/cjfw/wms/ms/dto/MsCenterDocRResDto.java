package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.06.24
 * @description : 센터서류 조회 결과 DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.24        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
//@Builder
@Schema(description = "센터서류 조회 결과 DTO")
public class MsCenterDocRResDto {
	@Schema(description = "테이블 시리얼 번호")
    private String serialKey;

    @Schema(description = "물류센터 코드", example = "2600")
    private String dcCode;

    @Schema(description = "고객사 코드", example = "CUST001")
    private String custKey;

    @Schema(description = "고객사명")
    private String custName;

    @Schema(description = "요청 ID")
    private String reqId;

    @Schema(description = "요청 문서")
    private String reqDoc;
    
    @Schema(description = "요청 일자")
    private String reqDate;

    @Schema(description = "최초 등록자")
    private String addWho;

    @Schema(description = "최초 등록 시간", example = "YYYY-MM-DD HH:mm:ss")
    private String addDate;

    @Schema(description = "최종 변경자")
    private String editWho;

    @Schema(description = "최종 변경 시간", example = "YYYY-MM-DD HH:mm:ss")
    private String editDate;

    @Schema(description = "등록 메모")
    private String regMemo;

    @Schema(description = "센터 파일")
    private String centerFile;

    @Schema(description = "등록 여부")
    private String regYn;

    @Schema(description = "문서 개수")
    private String docCnt;

    @Schema(description = "요청 번호")
    private String reqNo;

    @Schema(description = "기타 정보1")
    private String other01;

    @Schema(description = "기타 정보2")
    private String other02;
    
    @Schema(description = "최종 변경자")
    private String editWhoNm;
    
    @Schema(description = "최초 등록자")
    private String addWhoNm;
    
    @Schema(description = "업로드 파일ID")
    private String uploadResDocId;
    
    @Schema(description = "파일명")
    private String fileName;
    
    @Schema(description = "파일확장자")
    private String fileExtension;
    
    @Schema(description = "파일크기BYTE")
    private String fileSizeBytes;
    
    @Schema(description = "연동파일명")
    private String transFileName;

    @Schema(description = "사업자번호")
    private String vatno;
}
