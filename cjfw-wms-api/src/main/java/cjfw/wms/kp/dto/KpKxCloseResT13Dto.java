package cjfw.wms.kp.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiSooKim (jskim14@cj.net)
 * @date : 2025.10.15
 * @description : KP KX 월말 재고비교내역 응답 DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "KP KX 월말 재고비교내역 응답")
public class KpKxCloseResT13Dto {
    /** DC 코드 */
    @Schema(description = "DC 코드")
    private String dcCode;

    /** 조직 */
    @Schema(description = "조직")
    private String organize;

    /** 조직명 */
    @Schema(description = "조직명")
    private String organizeName;

    /** SKU */
    @Schema(description = "SKU")
    private String sku;

    /** SKU명 */
    @Schema(description = "SKU명")
    private String skuName;

    /** FW 수량 */
    @Schema(description = "FW 수량")
    private Integer fwQty;

    /** KX 수량 */
    @JsonAlias({"KX_QTY"})
    @Schema(description = "KX 수량")
    private Integer kxQty;

    /** 차이 수량 */
    @Schema(description = "차이 수량")
    private Integer diffQty;

    /** 단위 */
    @Schema(description = "단위")
    private String uom;
}
