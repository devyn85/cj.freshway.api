package cjfw.wms.om.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.10.31 
 * @description : 당일광역보충발주 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.31 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
public class OmOrderCreationSTOOrdBaseResultResDto {
			
	/** 체크여부 */
    @Schema(description = "체크여부", example = "")
    private String checkyn;

    /** FROM_STORERKEY */
    @Schema(description = "출고 화주 키", example = "")
    private String fromStorerkey;

    /** FROM_DCCODE */
    @Schema(description = "출고 DC 코드", example = "")
    private String fromDccode;

    /** FROM_ORGANIZE */
    @Schema(description = "출고 조직", example = "")
    private String fromOrganize;

    /** FROM_AREA */
    @Schema(description = "출고 영역", example = "")
    private String fromArea;

    /** FROM_SKU */
    @Schema(description = "출고 SKU", example = "")
    private String fromSku;

    /** FROM_UOM */
    @Schema(description = "출고 UOM", example = "")
    private String fromUom;

    /** FROM_STOCKGRADE */
    @Schema(description = "출고 재고 등급", example = "")
    private String fromStockgrade;

    /** TO_ORDERQTY */
    @Schema(description = "입고 주문 수량", example = "")
    private String toOrderqty; 

    /** TO_DCCODE */
    @Schema(description = "입고 DC 코드", example = "")
    private String toDccode;

    /** TO_ORGANIZE */
    @Schema(description = "입고 조직", example = "")
    private String toOrganize;

    /** TO_AREA */
    @Schema(description = "입고 영역", example = "")
    private String toArea;

    /** TO_SKU */
    @Schema(description = "입고 SKU", example = "")
    private String toSku;

    /** TO_UOM */
    @Schema(description = "입고 UOM", example = "")
    private String toUom;

    /** TO_STOCKGRADE */
    @Schema(description = "입고 재고 등급", example = "")
    private String toStockgrade;

    /** PROCESSFLAG */
    @Schema(description = "처리 플래그", example = "")
    private String processflag;

    /** PROCESSMSG */
    @Schema(description = "처리 메시지", example = "")
    private String processmsg;

    /** DESCRIPTION */
    @Schema(description = "설명", example = "")
    private String description;

    /** FROM_DCNAME */
    @Schema(description = "출고 DC 이름", example = "")
    private String fromDcname;

    /** TO_DCNAME */
    @Schema(description = "입고 DC 이름", example = "")
    private String toDcname;
    
	
}