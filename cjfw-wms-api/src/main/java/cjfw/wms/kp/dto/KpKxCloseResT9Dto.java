package cjfw.wms.kp.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiSooKim (jskim14@cj.net)
 * @date : 2026.01.15
 * @description : KP KX Close 응답 DTO - T9 소유권 이전실적 확인결과
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "소유권 이전실적 확인결과")
public class KpKxCloseResT9Dto {
    @Schema(description = "문서유형(OTHER01)")
    private String doctype;

    @Schema(description = "KX유형(OTHER02)")
    private String kxtype;

    @Schema(description = "납품일자(SLIPDT)")
    private String deliverydate;

    @Schema(description = "KX센터코드(OTHER03)")
    private String kxdccode;

    @Schema(description = "KX전표번호(SLIPNO)")
    private String kxslipno;

    @Schema(description = "KX전표라인번호(SLIPLINE)")
    private String kxsliplineno;

    @Schema(description = "문서번호(DOCNO)")
    private String docno;

    @Schema(description = "문서라인(DOCLINE)")
    private String docline;

    @Schema(description = "상품코드(SKU)")
    private String sku;

    @Schema(description = "KX수량(CONFIRMQTY)")
    private BigDecimal kxqty;

    @Schema(description = "KX단위(OTHER04)")
    private String kxuom;

    @Schema(description = "수량(OTHER05)")
    private String qty;

    @Schema(description = "단위(UOM)")
    private String uom;

    @Schema(description = "체크여부(CHECKYN)")
    private String checkyn;

    @Schema(description = "송장수량(INVOICEQTY)")
    private String invoiceqty;

    @Schema(description = "처리상태(PROCESSFLAG)")
    private String processflag;

    @Schema(description = "처리메시지(PROCESSMSG)")
    private String processmsg;

    @Schema(description = "SRM 확정수량(ETCQTY1)")
    private String srmConfirmqty;

    @Schema(description = "WMS 확정수량(ETCQTY2)")
    private String wmsConfirmqty;
}