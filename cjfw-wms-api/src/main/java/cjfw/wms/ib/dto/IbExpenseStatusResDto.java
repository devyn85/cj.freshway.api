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
 * @date : 2025.09.02
 * @description : 비용기표 원가관리리포트 조회 결과 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.02    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Schema(description = "비용기표 원가관리리포트 조회 결과") 
public class IbExpenseStatusResDto extends CommonProcedureDto {   
    
    /** 제비용구코드 */
    @Schema(description = "제비용구코드", nullable = true, example = "")
    private String code;

    /** 제비용구분명 */
    @Schema(description = "제비용구분명", nullable = true, example = "")
    private String name;

    /** 기준일자 */
    @Schema(description = "기준일자", nullable = true, example = "")
    private String baseDate;

    /** QUANTITY */
    @Schema(description = "QUANTITY", nullable = true, example = "")
    private Double quantity;

    /** QUANTITY_UNIT */
    @Schema(description = "QUANTITY_UNIT", nullable = true, example = "")
    private String quantityUnit;

    /** AMOUNT */
    @Schema(description = "AMOUNT", nullable = true, example = "")
    private Double amount;

    /** AMOUNT_UNIT */
    @Schema(description = "AMOUNT_UNIT", nullable = true, example = "")
    private String amountUnit;    
    
    /** DOCUMENT TYPE CODE */
    @Schema(description = "DOCUMENT TYPE CODE", nullable = true, example = "")
    private String documentTypeCode;

    /** DOCUMENT No */
    @Schema(description = "DOCUMENT No", nullable = true, example = "")
    private String documentNumber;

    /** P/O No */
    @Schema(description = "P/O No", nullable = true, example = "")
    private String poNo;

    /** Cost Code */
    @Schema(description = "Cost Code", nullable = true, example = "")
    private String accountDetailCode;

    /** Cost Name */
    @Schema(description = "Cost Name", nullable = true, example = "")
    private String accountDetailName;

}
