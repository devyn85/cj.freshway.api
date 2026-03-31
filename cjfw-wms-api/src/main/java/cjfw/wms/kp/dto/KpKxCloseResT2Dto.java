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
 * @date : 2025.09.26
 * @description : KP KX Close - KX검증 DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "KP KX Close 결과(검증자료)")
public class KpKxCloseResT2Dto {

    @Schema(description = "시리얼키")
    private String serialkey;

    @Schema(description = "KX 주문일자")
    private String kxorderdate;

    @Schema(description = "KX 주문유형")
    private String kxordertype;

    @Schema(description = "KX DC 코드")
    private String kxdccode;

    @Schema(description = "KX 주문키")
    private String kxorderkey;

    @Schema(description = "KX 라인번호")
    private String kxlineno;

    @Schema(description = "문서일자")
    private String docdt;

    @Schema(description = "문서구분")
    private String doctype;

    @Schema(description = "문서번호")
    private String docno;

    @Schema(description = "문서라인")
    private String docline;

    @Schema(description = "전표일자")
    private String slipdt;

    @Schema(description = "전표번호")
    private String slipno;

    @Schema(description = "전표라인")
    private String slipline;

    @Schema(description = "DC 코드")
    private String dccode;

    @Schema(description = "보관처키")
    private String storerkey;

    @Schema(description = "조직")
    private String organize;

    @Schema(description = "확정수량")
    private BigDecimal confirmqty;

    @Schema(description = "IF 플래그")
    private String ifFlag;

    @Schema(description = "IF 일시")
    private String ifDate;

    @Schema(description = "SKU")
    private String sku;

    @Schema(description = "DM SKU")
    private String dmSku;

    @Schema(description = "DM 수량")
    private String dmQty;

    @Schema(description = "RT SKU")
    private String rtSku;

    @Schema(description = "RT 수량")
    private String rtQty;

    @Schema(description = "체크여부")
    private String checkyn;
}
