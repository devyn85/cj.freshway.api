package cjfw.wms.om.dto;

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
 * @date : 2025.07.08
 * @description : 외부센터 보충발주 처리 결과 조회 목록 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.08    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "외부센터 보충발주 처리 결과 조회 목록") 
public class OmOrderCreationSTOForOutResultResDto extends CommonProcedureDto {	    
    
    /** 출고 STORERKEY */
    @Schema(description = "출고 STORERKEY", example = "")
    private String fromStorerkey;
    
    /** 출고 DCCODE */
    @Schema(description = "출고 DCCODE", example = "")
    private String fromDccode;    
    
    /** 출고 DCNAME */
    @Schema(description = "출고 DCNAME", example = "")
    private String fromDcname;
    
    /** 출고 ORGANIZE */
    @Schema(description = "출고 ORGANIZE", example = "")
    private String fromOrganize;

    /** 출고 AREA */
    @Schema(description = "출고 AREA", example = "")
    private String fromArea;

    /** 출고 SKU */
    @Schema(description = "출고 SKU", example = "")
    private String fromSku;

    /** 출고 UOM */
    @Schema(description = "출고 UOM", example = "")
    private String fromUom;
    
    /** 출고 STOCKGRADE */
    @Schema(description = "출고 STOCKGRADE", example = "")
    private String fromStockgrade;
    
    /** 입고 수량 */
    @Schema(description = "입고 수량", example = "")
    private BigDecimal toOrderqty;
    
    /** 입고 DCCODE */
    @Schema(description = "입고 DCCODE", example = "")
    private String toDccode;
    
    /** 입고 ORGANIZE */
    @Schema(description = "입고 ORGANIZE", example = "")
    private String toOrganize;
    
    /** 입고 AREA */
    @Schema(description = "입고 AREA", example = "")
    private String toArea;
    
    /** 입고 SKU */
    @Schema(description = "입고 SKU", example = "")
    private String toSku;    
    
    /** 입고 UOM */
    @Schema(description = "입고 UOM", example = "")
    private String toUom;
    
    /** 입고 STOCKGRADE */
    @Schema(description = "입고 STOCKGRADE", example = "")
    private String toStockgrade;
    
    /** PROCESSFLAG */
    @Schema(description = "PROCESSFLAG", example = "")
    private String processflag;
    
    /** PROCESSMSG */
    @Schema(description = "PROCESSMSG", example = "")
    private String processmsg;    
    
    /** DESCRIPTION */
    @Schema(description = "DESCRIPTION", example = "")
    private String description;

    /** 입고 DCNAME */
    @Schema(description = "입고 DCNAME", example = "")
    private String toDcname;
      
}
