package cjfw.wms.om.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.07.11
 * @description : 월간주간발주량체크PO Res DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.11        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Schema(description = "월간주간발주량체크PO 조회")
public class OmPurchaseCheckResDto {
	@Schema(description = "물류센터 코드", example = "DC001")
    private String dcCode;

    @Schema(description = "고객사 코드", example = "STORER001")
    private String storerKey;

    @Schema(description = "상품 코드", example = "SKU001")
    private String sku;

    @Schema(description = "상품명")
    private String skuName;

    @Schema(description = "단위 (UOM)")
    private String uom;

    @Schema(description = "당일 주문 수량")
    private BigDecimal theDay;

    @Schema(description = "주간 차이 (당일 주문량 - 주간 평균)")
    private BigDecimal weekDiff;

    @Schema(description = "월간 차이 (당일 주문량 - 월간 평균)")
    private BigDecimal monthDiff;

    @Schema(description = "주간 평균 주문 수량")
    private BigDecimal weekAvg;

    @Schema(description = "주간 최대 주문 수량")
    private BigDecimal weekMax;

    @Schema(description = "주간 최소 주문 수량")
    private BigDecimal weekMin;

    @Schema(description = "월간 평균 주문 수량")
    private BigDecimal monthAvg;

    @Schema(description = "월간 최대 주문 수량")
    private BigDecimal monthMax;

    @Schema(description = "월간 최소 주문 수량")
    private BigDecimal monthMin;
    
    @Schema(description = "월 총 입고량 (30일)")
    private BigDecimal monthTotalQty;
    
    @Schema(description = "월 입고횟수 (30일)")
    private BigDecimal monthCount;
}
