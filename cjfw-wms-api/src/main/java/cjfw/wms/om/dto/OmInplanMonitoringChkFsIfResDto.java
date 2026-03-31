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
 * @description : 주문수신모니터링-영업오더차이 Res DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.09        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Schema(description = "주문수신모니터링-영업오더차이 조회")
public class OmInplanMonitoringChkFsIfResDto {
	@Schema(description = "IF 일자")
    private String ifDate;

    @Schema(description = "IF 플래그")
    private String ifFlag;

    @Schema(description = "IF 메모")
    private String ifMemo;

    // --- 원본 오더 수량 (STORER*) ---
    @Schema(description = "업체 주문 수량")
    private BigDecimal storerOrderQty;

    @Schema(description = "업체 조정 수량")
    private BigDecimal storerAdjustQty;

    @Schema(description = "업체 오픈 수량")
    private BigDecimal storerOpenQty;

    @Schema(description = "업체 확정 수량")
    private BigDecimal storerConfirmQty;

    // --- WMS 내부 수량 ---
    @Schema(description = "주문 수량")
    private BigDecimal orderQty;

    @Schema(description = "주문 조정 수량")
    private BigDecimal orderAdjustQty;

    @Schema(description = "오픈 수량")
    private BigDecimal openQty;

    @Schema(description = "오픈 조정 수량")
    private BigDecimal openAdjustQty;

    @Schema(description = "처리 수량")
    private BigDecimal processQty;

    @Schema(description = "작업 수량 (Work Qty)")
    private BigDecimal workQty;

    @Schema(description = "검사 수량 (Inspect Qty)")
    private BigDecimal inspectQty;

    @Schema(description = "확정 수량 (Confirm Qty)")
    private BigDecimal confirmQty;

    @Schema(description = "인보이스 수량")
    private BigDecimal invoiceQty;

    @Schema(description = "QC 수량")
    private BigDecimal qcQty;

    @Schema(description = "반품 수량")
    private BigDecimal returnQty;
}
