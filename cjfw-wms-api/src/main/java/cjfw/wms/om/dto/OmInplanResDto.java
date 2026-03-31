package cjfw.wms.om.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.07.09
 * @description : 주문이력현황 Res DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.09        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Schema(description = "주문이력현황 조회")
public class OmInplanResDto {
	@Schema(description = "물류센터 코드")
    private String dcCode;

    @Schema(description = "고객사 코드")
    private String storerKey;

    @Schema(description = "조직 코드")
    private String organize;

    @Schema(description = "지역 코드")
    private String area;

    @Schema(description = "문서 일자", example = "YYYYMMDD")
    private String docDt;

    @Schema(description = "문서 번호")
    private String docNo;

    @Schema(description = "문서 유형")
    private String docType;

    @Schema(description = "PO 유형")
    private String poType;

    @Schema(description = "오더 유형")
    private String orderType;

    @Schema(description = "대상 고객사 코드")
    private String toCustKey;

    @Schema(description = "대상 고객사명")
    private String toCustName;

    @Schema(description = "문서 라인")
    private String docLine;

    @Schema(description = "상품 코드")
    private String sku;

    @Schema(description = "상품명")
    private String skuName;

    @Schema(description = "고객사 주문 수량")
    private BigDecimal storerOrderQty;

    @Schema(description = "고객사 오픈 수량")
    private BigDecimal storerOpenQty;

    @Schema(description = "주문 수량")
    private BigDecimal orderQty;

    @Schema(description = "주문 조정 수량")
    private BigDecimal orderAdjustQty;

    @Schema(description = "검수 수량")
    private BigDecimal inspectQty;

    @Schema(description = "확정 수량")
    private BigDecimal confirmQty;

    @Schema(description = "단위 (UOM)")
    private String uom;

    @Schema(description = "상태")
    private String status;

    @Schema(description = "배송 일자", example = "YYYYMMDD")
    private String deliveryDate;

    @Schema(description = "삭제 여부")
    private String delYn;

    @Schema(description = "기타 04 (판매 조직)")
    private String other04;
}
