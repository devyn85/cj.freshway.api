package cjfw.wms.ms.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.07.24
 * @description : 수발주기초정보 기능을 구현한 DTO Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.24        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Builder
@Schema(description = "수발주기초정보 조회 결과 DTO")
public class MsPurchaseCustDetailResDto {

	@Schema(description = "물류센터 코드", example = "DC001")
    private String dcCode;

    @Schema(description = "구매처 코드", example = "CUST001")
    private String custKey;

    @Schema(description = "구매처명")
    private String custName;

    @Schema(description = "상품 코드", example = "SKU001")
    private String sku;

    @Schema(description = "상품명")
    private String skuName;

    @Schema(description = "플랜트 코드", example = "PLANT01")
    private String plant;

    @Schema(description = "플랜트 설명", example = "[PLANT01]플랜트명")
    private String plantDescr;

    @Schema(description = "단위 (UOM)")
    private String uom;

    @Schema(description = "재발주점")
    private BigDecimal reorderPoint;

    @Schema(description = "목표 재고")
    private BigDecimal stockGoal;

    @Schema(description = "최소 일수")
    private BigDecimal stockMinDay;

    @Schema(description = "목표 일수")
    private BigDecimal stockMaxDay;

    @Schema(description = "보유 일수 [현재고/일평균]")
    private BigDecimal stockDay;

    @Schema(description = "재고 일평균")
    private BigDecimal stockAvgDay;

    @Schema(description = "표준 편차")
    private BigDecimal stdDeviation;

    @Schema(description = "최대 재고")
    private BigDecimal maxStock;

    @Schema(description = "현재고")
    private BigDecimal stockQty;

    @Schema(description = "안전 계수 [상위 그리드]")
    private BigDecimal coefficientSafety;

    @Schema(description = "리드타임 [상위 그리드]")
    private BigDecimal leadTime;

    @Schema(description = "주문 간격 [상위 그리드]")
    private BigDecimal purInterval;

    @Schema(description = "안전 재고")
    private BigDecimal stockSafety;

    @Schema(description = "적정 재고")
    private BigDecimal stockOptimal;

    @Schema(description = "무출고 일수 (D-1월)")
    private BigDecimal shipDay1W;

    @Schema(description = "무출고 일수 (D-2월)")
    private BigDecimal shipDay2W;

    @Schema(description = "무출고 일수 (D-3월)")
    private BigDecimal shipDay3W;

    @Schema(description = "출고 수량 (D-1월)")
    private BigDecimal shipQty1W;

    @Schema(description = "출고 수량 (D-2월)")
    private BigDecimal shipQty2W;

    @Schema(description = "출고 수량 (D-3월)")
    private BigDecimal shipQty3W;

    @Schema(description = "출고 수량 변화 (D-1월 대비)")
    private String shipQtyChg1W; // CASE 문 결과가 '1', '-1', '0' 이므로 String

    @Schema(description = "출고 수량 변화 (D-2월 대비)")
    private String shipQtyChg2W; // CASE 문 결과가 '1', '-1', '0' 이므로 String

    @Schema(description = "출고 수량 변화 (D-3월 대비)")
    private String shipQtyChg3W; // '0'으로 고정되므로 String

    @Schema(description = "주문 1일차")
    private BigDecimal ord1Day;

    @Schema(description = "주문 2일차")
    private BigDecimal ord2Day;

    @Schema(description = "주문 3일차")
    private BigDecimal ord3Day;

    @Schema(description = "주문 4일차")
    private BigDecimal ord4Day;

    @Schema(description = "주문 5일차")
    private BigDecimal ord5Day;

    @Schema(description = "주문 6일차")
    private BigDecimal ord6Day;

    @Schema(description = "주문 7일차")
    private BigDecimal ord7Day;

    @Schema(description = "주문 8일차")
    private BigDecimal ord8Day;

    @Schema(description = "주문 9일차")
    private BigDecimal ord9Day;

    @Schema(description = "주문 10일차")
    private BigDecimal ord10Day;

    @Schema(description = "주문 11일차")
    private BigDecimal ord11Day;

    @Schema(description = "주문 12일차")
    private BigDecimal ord12Day;

    @Schema(description = "주문 13일차")
    private BigDecimal ord13Day;

    @Schema(description = "주문 14일차")
    private BigDecimal ord14Day;

    @Schema(description = "영업 일수 (D-1월)")
    private BigDecimal bizDateMonth1;

    @Schema(description = "영업 일수 (D-2월)")
    private BigDecimal bizDateMonth2;

    @Schema(description = "영업 일수 (D-3월)")
    private BigDecimal bizDateMonth3;

    @Schema(description = "안전 재고2 (임시 계산용)")
    private BigDecimal stockSafety2Temp;

    @Schema(description = "예상 오픈 수량")
    private BigDecimal preOpenQty;

    @Schema(description = "안전 재고2")
    private BigDecimal stockSafety2;
    
    @Schema(description = "안전 재고 보유일2")
    private BigDecimal stockDay2;
}
