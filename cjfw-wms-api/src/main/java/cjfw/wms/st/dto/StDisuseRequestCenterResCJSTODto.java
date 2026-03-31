package cjfw.wms.st.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : 공두경 (pw6375@cj.net) 
 * @date : 2025.12.27
 * @description : 제일제당(STO) 처리 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.12.27 공두경 (pw6375@cj.net) 생성 </pre>
 */
@Data
//@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Schema(description = "제일제당(STO) 처리")
public class StDisuseRequestCenterResCJSTODto extends CommonProcedureDto {


    // from
    @Schema(description = "출고 STORERKEY")
    private String fromStorerkey;
    
    @Schema(description = "출고 DC코드")
    private String fromDccode;
    
    @Schema(description = "출고 ORGANIZE")
    private String fromOrganize;
    
    @Schema(description = "출고 AREA")
    private String fromArea;
    
    @Schema(description = "출고 SKU")
    private String fromSku;
    
    @Schema(description = "출고 UOM")
    private String fromUom;

    @Schema(description = "출고 STOCKID")
    private String fromStockid;
    
    @Schema(description = "출고 재고등급")
    private String fromStockgrade;

    @Schema(description = "출고 LOC")
    private String fromLoc;
    
    @Schema(description = "출고 LOT")
    private String fromLot;

    @Schema(description = "입고 수량(TO)")
    private BigDecimal toOrderqty;

    // TO
    @Schema(description = "입고 DC코드")
    private String toDccode;
    
    @Schema(description = "입고 ORGANIZE")
    private String toOrganize;
    
    @Schema(description = "입고 AREA")
    private String toArea;
    
    @Schema(description = "입고 SKU")
    private String toSku;
    
    @Schema(description = "입고 UOM")
    private String toUom;
    
    @Schema(description = "입고 LOC")
    private String toLoc;
    
    @Schema(description = "입고 LOT")
    private String toLot;
    
    @Schema(description = "입고 STOCKID")
    private String toStockid;
    
    @Schema(description = "입고 재고등급")
    private String toStockgrade;
    
    @Schema(description = "이체유형-반품STO")
    private String stotype;
    
    @Schema(description = "deliverydate")
    private String deliverydate;
    
 
}
