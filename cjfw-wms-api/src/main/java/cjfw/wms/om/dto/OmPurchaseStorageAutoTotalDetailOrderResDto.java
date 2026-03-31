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
 * @description : 저장품자동발주 상세(가운데) 조회 기능을 구현한 Res DTO Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.14        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Schema(description = "저장품자동발주 상세(가운데) 조회")
public class OmPurchaseStorageAutoTotalDetailOrderResDto extends CommonProcedureDto {
	@Schema(description = "데이터 구분 코드 (DCCODE, 주문합계, 입고정보, 재고현황)")
    private String dcCode;

    @Schema(description = "상품 코드")
    private String sku;

    @Schema(description = "D+1일차 수량 (주문, 입고, 재고 중 하나)")
    private BigDecimal wdQty01;

    @Schema(description = "D+2일차 수량")
    private BigDecimal wdQty02;

    @Schema(description = "D+3일차 수량")
    private BigDecimal wdQty03;

    @Schema(description = "D+4일차 수량")
    private BigDecimal wdQty04;

    @Schema(description = "D+5일차 수량")
    private BigDecimal wdQty05;

    @Schema(description = "D+6일차 수량")
    private BigDecimal wdQty06;

    @Schema(description = "D+7일차 수량")
    private BigDecimal wdQty07;

    @Schema(description = "D+8일차 수량")
    private BigDecimal wdQty08;

    @Schema(description = "D+9일차 수량")
    private BigDecimal wdQty09;

    @Schema(description = "D+10일차 수량")
    private BigDecimal wdQty10;

    @Schema(description = "기준 재고 수량 (STOCKQTY)")
    private BigDecimal stockQty;
}
