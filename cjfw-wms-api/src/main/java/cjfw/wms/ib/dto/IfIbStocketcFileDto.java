package cjfw.wms.ib.dto;

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
 * @description : IF_IB_STOCKETC_FILE 테이블 조회 결과 DTO 
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
@Schema(description = "IF_IB_STOCKETC_FILE 테이블 조회 결과") 
public class IfIbStocketcFileDto extends CommonProcedureDto {	
    
    /** SERIALKEY */
    @Schema(description = "SERIALKEY", nullable = false, example = "")
    private String serialkey;

    /** BUKRS */
    @Schema(description = "BUKRS", nullable = false, example = "")
    private String bukrs;

    /** ZSYST */
    @Schema(description = "ZSYST", nullable = false, example = "")
    private String zsyst;

    /** IF_NO */
    @Schema(description = "IF_NO", nullable = false, example = "")
    private String ifNo;

    /** SEQ */
    @Schema(description = "SEQ", nullable = false, example = "")
    private String seq;

    /** ZDOCNO */
    @Schema(description = "ZDOCNO", nullable = false, example = "")
    private String zdocno;

    /** ZEVID1 */
    @Schema(description = "ZEVID1", nullable = false, example = "")
    private String zevid1;

    /** ZDOCTYPE */
    @Schema(description = "ZDOCTYPE", nullable = false, example = "")
    private String zdoctype;

    /** ZATTID */
    @Schema(description = "ZATTID", nullable = false, example = "")
    private String zattid;

    /** ZREQUESTNO */
    @Schema(description = "ZREQUESTNO", nullable = false, example = "")
    private String zrequestno;

    /** ZCATEGORY */
    @Schema(description = "ZCATEGORY", nullable = false, example = "")
    private String zcategory;

    /** ADDDATE */
    @Schema(description = "ADDDATE", nullable = false, example = "")
    private String adddate;

    /** EDITDATE */
    @Schema(description = "EDITDATE", nullable = false, example = "")
    private String editdate;

    /** ADDWHO */
    @Schema(description = "ADDWHO", nullable = false, example = "")
    private String addwho;

    /** EDITWHO */
    @Schema(description = "EDITWHO", nullable = false, example = "")
    private String editwho;
   
}
