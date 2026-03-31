package cjfw.wms.tm.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
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
 * @description : 파일 조회 결과 DTO 
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
@Schema(description = "파일 조회 결과") 
public class TmAmountDailyFilePopupResDto extends CommonDto {	
    
    /** 일련번호 */
    @Schema(description = "일련번호", nullable = false, example = "")
    private String serialkey;
    
    /** 헤더ID */
    @Schema(description = "헤더ID", nullable = false, example = "")
    private String serialkeyH;

    /** 문서구분명 */
    @Schema(description = "문서구분명", nullable = false, example = "")
    private String docName;
    
    /** 운송사코드 */
    @Schema(description = "운송사코드", nullable = false, example = "")
    private String courier;
    
    /** 차량번호 */
    @Schema(description = "차량번호", nullable = false, example = "")
    private String carno;

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

    /** 업로드 파일명*/
    @Schema(description = "업로드 파일명", nullable = false, example = "")
    private String uploadFileName;

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
