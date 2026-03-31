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
 * @description : 저장품자동발주 상세(오른쪽) 조회 기능을 구현한 Res DTO Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.14        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Schema(description = "저장품자동발주 상세(오른쪽) 조회")
public class OmPurchaseStorageAutoTotalDetailRightResDto extends CommonProcedureDto {
	@Schema(description = "센터 코드")
    private String dcCode;

    @Schema(description = "상품 코드")
    private String sku;

    @Schema(description = "상품명")
    private String skuName;

    @Schema(description = "단위 (UOM)")
    private String uom;

    @Schema(description = "주문 유형 (WORKPROCESSCODE)")
    private String workProcessCode;

    @Schema(description = "오픈 수량 (미처리 수량)")
    private BigDecimal openQty;

    @Schema(description = "PO 수량 (ORDERQTY)")
    private BigDecimal purchaseQty;

    @Schema(description = "공급 확정 수량 (PREQTY)")
    private BigDecimal preQty;

    @Schema(description = "입고 확정 수량 (CONFIRMQTY)")
    private BigDecimal confirmQty;

    @Schema(description = "입고 예정일")
    private String slipDt;

    @Schema(description = "주문 번호")
    private String docNo;

    @Schema(description = "주문 상세 번호")
    private BigDecimal docLine;

    @Schema(description = "거래처 코드 (FROM_CUSTKEY)")
    private String fromCustKey;

    @Schema(description = "거래처명 (FROM_CUSTNAME)")
    private String fromCustName;

    @Schema(description = "조직 코드")
    private String organize;

    @Schema(description = "조직 명칭")
    private String organizeName;

    @Schema(description = "발주 생성일")
    private String addDate;
    
    @Schema(description = "주문 번호")
    private String requestNo;
    
    @Schema(description = "주문 상세 번호")
    private String requestLine;
    
    @Schema(description = "주문 번호")
    private String slipNo;
    
    @Schema(description = "주문 상세 번호")
    private String slipLine;
    
    @Schema(description = "거래처 코드")
    private String custKey;

    @Schema(description = "거래처타입")
    private String custType;
    
    @Schema(description = "삭제가능상태")
    private String statusYn;
    
    @Schema(description = "재고번호")
    private String stockid;
}
