package cjfw.wms.kp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiSooKim (jskim14@cj.net)
 * @date : 2025.12.11
 * @description : 문서정보 팝업 재고처리현황 RES DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "문서정보 팝업 RES DTO")
public class KpKxCloseDocPopupTransactionResDto {
    
    @Schema(description = "")
    private String serialkey;

    @Schema(description = "")
    private String trandate;

    @Schema(description = "")
    private String dccode;

    @Schema(description = "")
    private String trantype;

    @Schema(description = "")
    private String trantypename;

    @Schema(description = "")
    private String storerkey;

    @Schema(description = "")
    private String organize;

    @Schema(description = "")
    private String serialyn;

    @Schema(description = "")
    private String serialynname;

    @Schema(description = "")
    private String area;

    @Schema(description = "")
    private String sku;

    @Schema(description = "")
    private String skuname;

    @Schema(description = "")
    private String uom;

    @Schema(description = "")
    private Double avgweight;

    @Schema(description = "")
    private Double calbox;

    @Schema(description = "")
    private Double realorderbox;

    @Schema(description = "")
    private Double realcfmbox;

    @Schema(description = "")
    private Double fromStockqty;

    @Schema(description = "")
    private Double toStockqty;

    @Schema(description = "")
    private Double qty;

    @Schema(description = "")
    private String fromLoc;

    @Schema(description = "")
    private String fromLot;

    @Schema(description = "")
    private String fromLottable01;

    @Schema(description = "")
    private String fromStockid;

    @Schema(description = "")
    private String fromStocktype;

    @Schema(description = "")
    private String fromStockgrade;

    @Schema(description = "")
    private String toLoc;

    @Schema(description = "")
    private String toLot;

    @Schema(description = "")
    private String toLottable01;

    @Schema(description = "")
    private String toStockid;

    @Schema(description = "")
    private String toStocktype;

    @Schema(description = "")
    private String toStockgrade;

    @Schema(description = "")
    private String docno;

    @Schema(description = "")
    private Integer docline;

    @Schema(description = "")
    private String adddate;

    @Schema(description = "")
    private String addwho;

    @Schema(description = "")
    private String username;
}
