package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.09.25
 * @description : 사전주문 조정의뢰 응답 DTO Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.25        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Schema(description = "출고 > 출고작업 > 사전주문 조정의뢰")
@Data
@Builder
public class WdBeforeOrderAdjustRequestResDto extends CommonDto {
	
	@Schema(description = "체크 여부")
    private String checkYn;

    @Schema(description = "주문업체 키")
    private String storerKey;

    @Schema(description = "배송업체")
    private String courier;

    @Schema(description = "물류센터 코드")
    private String dcCode;

    @Schema(description = "문서 일자")
    private String docDt;

    @Schema(description = "문서 번호")
    private String docNo;

    @Schema(description = "문서 라인")
    private BigDecimal docLine;

    @Schema(description = "배송 일자")
    private String deliveryDate;

    @Schema(description = "수신처 사업자 등록번호")
    private String toVatno;

    @Schema(description = "수신처 상호")
    private String toVatowner;

    @Schema(description = "수신처 고객 키")
    private String toCustKey;

    @Schema(description = "수신처 고객명")
    private String toCustName;

    @Schema(description = "관리사업장 ID")
    private String mngplcId;

    @Schema(description = "관리사업장명")
    private String mngplcName;

    @Schema(description = "전표 일자")
    private String slipDt;

    @Schema(description = "전표 번호")
    private String slipNo;

    @Schema(description = "전표 라인")
    private BigDecimal slipline;

    @Schema(description = "상품 코드")
    private String sku;

    @Schema(description = "상품명")
    private String skuName;

    @Schema(description = "채널")
    private String channel;

    @Schema(description = "주문 수량")
    private BigDecimal orderQty;

    @Schema(description = "오픈 수량")
    private BigDecimal openQty;

    @Schema(description = "이동 수량")
    private BigDecimal tranQty;

    @Schema(description = "단위 (UOM)")
    private String uom;

    @Schema(description = "공장")
    private String plant;

    @Schema(description = "공장 설명")
    private String plantDescr;

    @Schema(description = "부족 문서 번호")
    private String shortageDocNo;

    @Schema(description = "WD 수량")
    private BigDecimal wdQty;

    @Schema(description = "DP 수량")
    private BigDecimal dpQty;

    @Schema(description = "재고 오픈 수량 (N)")
    private BigDecimal stOpenQtyN;

    @Schema(description = "할당 수량")
    private BigDecimal qtyAllocated;

    @Schema(description = "피킹 수량")
    private BigDecimal qtyPicked;

    @Schema(description = "기준 단위 (UOM)")
    private String baseUom;

    @Schema(description = "수량")
    private BigDecimal qty;

    @Schema(description = "재고 오픈 수량")
    private BigDecimal stOpenQty;

    @Schema(description = "작업 프로세스 코드")
    private String workprocessCode;

    @Schema(description = "삭제 여부")
    private String delYn;

    @Schema(description = "MD 비고")
    private String mdRmk;

    @Schema(description = "텍스트")
    private String text;

    @Schema(description = "대체 상품 코드")
    private String altSku;

    @Schema(description = "대체 상품명")
    private String altSkuNm;

    @Schema(description = "매출 고객 계층1")
    private String saleCusHrc1;

    @Schema(description = "주문 유형")
    private String orderType;

    @Schema(description = "수신처 사원명")
    private String toEmpName;
    
    @Schema(description = "주문일자")
    private String orderDate;
    
    @Schema(description = "주문시간")
    private String orderTime;
    
    @Schema(description = "재입고 일자")
    private String reDeliveryDate;

    @Schema(description = "부족 수량")
    private BigDecimal shortageQty;    
}
