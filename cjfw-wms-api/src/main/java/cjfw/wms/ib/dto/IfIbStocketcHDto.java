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
 * @description : IF_IB_STOCKETC_H 테이블 조회 결과 DTO 
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
@Schema(description = "IF_IB_STOCKETC_H 테이블 조회 결과") 
public class IfIbStocketcHDto extends CommonProcedureDto {	
    
    /** WMWST1 */
   // @Schema(description = "WMWST1", nullable = false, example = "")
   // private String wmwst1;

    /** SERIALKEY */
    @Schema(description = "SERIALKEY", nullable = false, example = "")
    private String serialkey;

    /** ZINVOICE */
    @Schema(description = "ZINVOICE", nullable = false, example = "")
    private String zinvoice;
    
    /** ZCAT */
    @Schema(description = "ZSZCATEQ", nullable = false, example = "")
    private String zcat;

    /** ZSEQ */
    @Schema(description = "ZSEQ", nullable = false, example = "")
    private String zseq;

    /** XRECH */
    @Schema(description = "XRECH", nullable = false, example = "")
    private String xrech;

    /** ZLAST */
    @Schema(description = "ZLAST", nullable = false, example = "")
    private String zlast;

    /** BUKRS */
    @Schema(description = "BUKRS", nullable = false, example = "")
    private String bukrs;

    /** LIFNR */
    @Schema(description = "LIFNR", nullable = false, example = "")
    private String lifnr;

    /** EMPFB */
    @Schema(description = "EMPFB", nullable = false, example = "")
    private String empfB;

    /** BLDAT */
    @Schema(description = "BLDAT", nullable = false, example = "")
    private String bldat;

    /** BUDAT */
    @Schema(description = "BUDAT", nullable = false, example = "")
    private String budat;

    /** WAERS */
    @Schema(description = "WAERS", nullable = false, example = "")
    private String waers;

    /** MWSKZ1 */
    @Schema(description = "MWSKZ1", nullable = false, example = "")
    private String mwskz1;

    /** ZTERM */
    @Schema(description = "ZTERM", nullable = false, example = "")
    private String zterm;

    /** ZFBDT */
    @Schema(description = "ZFBDT", nullable = false, example = "")
    private String zfbdt;

    /** RMWWR */
    @Schema(description = "RMWWR", nullable = false, example = "")
    private String rmwwr;

    /** WMWST1 */
    @Schema(description = "WMWST1", nullable = false, example = "")
    private String wmwst1;

    /** KIDNO */
    @Schema(description = "KIDNO", nullable = false, example = "")
    private String kidno;

    /** BKTXT */
    @Schema(description = "BKTXT", nullable = false, example = "")
    private String bktxt;

    /** ZGUBUN */
    @Schema(description = "ZGUBUN", nullable = false, example = "")
    private String zgubun;

    /** ZBILLNO */
    @Schema(description = "ZBILLNO", nullable = false, example = "")
    private String zbillno;

    /** CARD_NO */
    @Schema(description = "CARD_NO", nullable = false, example = "")
    private String cardNo;

    /** APPR_DATE */
    @Schema(description = "APPR_DATE", nullable = false, example = "")
    private String apprDate;

    /** APPR_DOCU */
    @Schema(description = "APPR_DOCU", nullable = false, example = "")
    private String apprDocu;

    /** CNCL_FLAG */
    @Schema(description = "CNCL_FLAG", nullable = false, example = "")
    private String cnclFlag;

    /** SELL_DOCU */
    @Schema(description = "SELL_DOCU", nullable = false, example = "")
    private String sellDocu;

    /** ZTEXT1 */
    @Schema(description = "ZTEXT1", nullable = false, example = "")
    private String ztext1;

    /** ZTEXT2 */
    @Schema(description = "ZTEXT2", nullable = false, example = "")
    private String ztext2;

    /** ZTEXT3 */
    @Schema(description = "ZTEXT3", nullable = false, example = "")
    private String ztext3;

    /** ZTEXT4 */
    @Schema(description = "ZTEXT4", nullable = false, example = "")
    private String ztext4;

    /** ZTEXT5 */
    @Schema(description = "ZTEXT5", nullable = false, example = "")
    private String ztext5;

    /** XDATS */
    @Schema(description = "XDATS", nullable = false, example = "")
    private String xdats;

    /** XTIMS */
    @Schema(description = "XTIMS", nullable = false, example = "")
    private String xtims;

    /** XUSER */
    @Schema(description = "XUSER", nullable = false, example = "")
    private String xuser;

    /** XSTAT */
    @Schema(description = "XSTAT", nullable = false, example = "")
    private String xstat;

    /** XMSGS */
    @Schema(description = "XMSGS", nullable = false, example = "")
    private String xmsgs;

    /** ZRETURN */
    @Schema(description = "ZRETURN", nullable = false, example = "")
    private String zreturn;

    /** ZRE_BELNR */
    @Schema(description = "ZRE_BELNR", nullable = false, example = "")
    private String zreBelnr;

    /** EXPENSE_SN */
    @Schema(description = "EXPENSE_SN", nullable = false, example = "")
    private String expenseSn;

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

    /** FI_IF_STATUS */
    @Schema(description = "FI_IF_STATUS", nullable = false, example = "")
    private String fiIfStatus;

    /** IF_ID */
    @Schema(description = "IF_ID", nullable = false, example = "")
    private String ifId;
    
    /** IF_TYPE */
    @Schema(description = "IF_TYPE", nullable = false, example = "")
    private String ifType;
    
   
}
