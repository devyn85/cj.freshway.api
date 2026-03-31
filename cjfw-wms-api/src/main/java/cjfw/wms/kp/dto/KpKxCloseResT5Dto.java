package cjfw.wms.kp.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiSooKim (jskim14@cj.net)
 * @date : 2025.10.01
 * @description : 상품의 저장위치별 월간수불 확인(문서기준) DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "상품의 저장위치별 월간수불 확인(문서기준)")
public class KpKxCloseResT5Dto extends CommonDto {
    @Schema(description = "SKU")
    private String sku;

    @Schema(description = "문서번호")
    private String docno;

    @Schema(description = "조직")
    private String organize;

    @Schema(description = "SKU명")
    private String skuname;

    @Schema(description = "납품일자")
    private String deliverydate;

    @Schema(description = "DP_STO")
    private String dpSto;

    @Schema(description = "DP_SO")
    private String dpSo;

    @Schema(description = "RT")
    private String rt;

    @Schema(description = "WD_STO")
    private String wdSto;

    @Schema(description = "WD_SO")
    private String wdSo;

    @Schema(description = "WD_JASO")
    private String wdJaso;

    @Schema(description = "WD_PO")
    private String wdPo;

    @Schema(description = "WD_AJ")
    private String wdAj;

    @Schema(description = "DP_AJ")
    private String dpAj;

    @Schema(description = "일일합계")
    private String dayTotal;

    @Schema(description = "누적합계")
    private String total;
}
