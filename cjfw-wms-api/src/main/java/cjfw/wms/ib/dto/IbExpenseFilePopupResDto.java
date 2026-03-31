package cjfw.wms.ib.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.08.08
 * @description : 비용기표 파일 조회 결과 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.08   KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "비용기표 파일 조회 결과") 
public class IbExpenseFilePopupResDto extends CommonProcedureDto {	
    
    /** 일련번호 */
    @Schema(description = "일련번호", nullable = false, example = "")
    private String serialkey;

    /** 문서ID */
    @Schema(description = "문서ID", nullable = false, example = "")
    private String docId;

    /** 문문서유형코드서타입 */
    @Schema(description = "문문서유형코드서타입", nullable = false, example = "")
    private String docType;

    /** 문서구분명 */
    @Schema(description = "문서구분명", nullable = false, example = "")
    private String docName;

    /** 파일명 */
    @Schema(description = "파일명", nullable = false, example = "")
    private String fileNm;

    /** 파일확장자 */
    @Schema(description = "파일확장자", nullable = false, example = "")
    private String attchFileExtNm;

    /** 업로드 대상 파일 경로 */
    @Schema(description = "업로드 대상 파일 경로", nullable = false, example = "")
    private String fileLocation;

    /** 파일크기(byte) */
    @Schema(description = "파일크기(byte)", nullable = false, example = "")
    private BigDecimal attchFileSz;

    /** 채번 변환된 파일명 */
    @Schema(description = "채번 변환된 파일명", nullable = false, example = "")
    private String attchFileNm;

    /** 서버전송 후 리턴받은 HASH_ID(2015.4.21 기준 미사용) */
    @Schema(description = "서버전송 후 리턴받은 HASH_ID(2015.4.21 기준 미사용)", nullable = false, example = "")
    private String uploadHashId;

    /** 서버전송 후 리턴받은 고유파일ID(HASH_ID + TRNAS_FILE_NAME) */
    @Schema(description = "서버전송 후 리턴받은 고유파일ID(HASH_ID + TRNAS_FILE_NAME)", nullable = false, example = "")
    private String uploadResDocId;

    /** 변환 후 업로드된 파일명 */
    @Schema(description = "변환 후 업로드된 파일명", nullable = false, example = "")
    private String uploadFileName;

    /** 업무모듈별 WORKPLACE ID */
    @Schema(description = "업무모듈별 WORKPLACE ID", nullable = false, example = "")
    private String uploadWorkplaceId;

    /** 업로드된 서버 위치(2015.04.21 기준 미사용) */
    @Schema(description = "업로드된 서버 위치(2015.04.21 기준 미사용)", nullable = false, example = "")
    private String uploadLocation;

    /** 다운로드 횟수 */
    @Schema(description = "다운로드 횟수", nullable = false, example = "")
    private String downloadCount;

    /** FILE_TYPE */
    @Schema(description = "FILE_TYPE", nullable = false, example = "")
    private String fileType;

    /** 상태 */
    @Schema(description = "상태", nullable = false, example = "")
    private String status;

    /** 최초등록자 */
    @Schema(description = "최초등록자", nullable = false, example = "")
    private String addwho;

    /** 최초등록시간 */
    @Schema(description = "최초등록시간", nullable = false, example = "")
    private String adddate;

    /** 최종변경자 */
    @Schema(description = "최종변경자", nullable = false, example = "")
    private String editwho;

    /** 최종변경시간 */
    @Schema(description = "최종변경시간", nullable = false, example = "")
    private String editdate;

}
