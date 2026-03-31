package cjfw.wms.om.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.09.12
 * @description : 저장품자동발주 상세(왼쪽) 기능을 구현한 Res DTO Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.14        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Schema(description = "저장품자동발주 상세(왼쪽) 조회")
public class OmPurchaseStorageAutoTotalDetailLeftResDto extends CommonProcedureDto {
	@Schema(description = "고객사 코드")
    private String storerKey;

    @Schema(description = "센터 코드")
    private String dcCode;

    @Schema(description = "상품 코드")
    private String sku;

    @Schema(description = "상품명")
    private String skuName;

    @Schema(description = "단위")
    private String uom;
    
    @Schema(description = "수급센터")
    private String route;

    @Schema(description = "현재고 수량 (QTY)")
    private BigDecimal qty;

    @Schema(description = "가용 재고 (OPENQTY)")
    private BigDecimal openQty;

    @Schema(description = "주문 수량 (ORDERQTY)")
    private BigDecimal orderQty;

    @Schema(description = "이체량 (STO_ORDERQTY)")
    private BigDecimal stoOrderQty;

    @Schema(description = "피킹 재고 (QTYPICKED)")
    private BigDecimal qtyPicked;
    
    @Schema(description = "이체량 오더생성")
    private BigDecimal createOrderQty;
    
    @Schema(description = "이체량 오더인입")
    private BigDecimal incomingOrderQty;
    
    @Schema(description = "출고센터")
    private String fromDcCode;
}
