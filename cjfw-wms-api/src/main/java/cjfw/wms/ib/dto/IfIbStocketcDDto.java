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
 * @description : IF_IB_STOCKETC_D 테이블 조회 결과 DTO 
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
@Schema(description = "IF_IB_STOCKETC_D 테이블 조회 결과") 
public class IfIbStocketcDDto extends CommonProcedureDto {	
    
    /** SERIALKEY */
    @Schema(description = "SERIALKEY", nullable = false, example = "")
    private String serialkey;

    /** ZINVOICE */
    @Schema(description = "ZINVOICE", nullable = false, example = "")
    private String zinvoice;

    /** BUZEI */
    @Schema(description = "BUZEI", nullable = false, example = "")
    private String buzei;

    /** ZSEQ */
    @Schema(description = "ZSEQ", nullable = false, example = "")
    private String zseq;

    /** MWSKZ */
    @Schema(description = "MWSKZ", nullable = false, example = "")
    private String mwskz;

    /** WRBTR */
    @Schema(description = "WRBTR", nullable = false, example = "")
    private String wrbtr;

    /** WERKS */
    @Schema(description = "WERKS", nullable = false, example = "")
    private String werks;

    /** MATNR */
    @Schema(description = "MATNR", nullable = false, example = "")
    private String matnr;

    /** FRBNR */
    @Schema(description = "FRBNR", nullable = false, example = "")
    private String frbnr;

    /** HISTNO */
    @Schema(description = "HISTNO", nullable = false, example = "")
    private String histno;

    /** ZEBELN */
    @Schema(description = "ZEBELN", nullable = false, example = "")
    private String zebeln;

    /** ZEBELP */
    @Schema(description = "ZEBELP", nullable = false, example = "")
    private String zebelp;

    /** MENGE */
    @Schema(description = "MENGE", nullable = false, example = "")
    private String menge;

    /** BSTME */
    @Schema(description = "BSTME", nullable = false, example = "")
    private String bstme;

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
