package cjfw.wms.st.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.09.23
 * @description : 상품별현재고(PLT)현황 결과 DTO Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.23        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Schema(description = "상품별현재고(PLT)현황 결과 DTO")
public class StStockChainResDto extends CommonDto {

	@Schema(description = "물류센터 코드")
    private String dcCode;

    @Schema(description = "센터명")
    private String dcName;

    @Schema(description = "조직 코드")
    private String organize;

    @Schema(description = "재고 유형")
    private String stockType;

    @Schema(description = "재고 등급")
    private String stockGrade;

    @Schema(description = "PLT 플래그")
    private String pltFlg;

    @Schema(description = "상품 코드")
    private String sku;

    @Schema(description = "상품명")
    private String skuName;

    @Schema(description = "보관 유형")
    private String storageType;

    @Schema(description = "단위 (UOM)")
    private String uom;

    @Schema(description = "수량")
    private BigDecimal qty;

    @Schema(description = "박스당 낱개 수")
    private BigDecimal qtyPerBox;

    @Schema(description = "팔레트당 박스 수")
    private BigDecimal boxPerPlt;

    @Schema(description = "참조 정보15")
    private String reference15;

    @Schema(description = "MOQ 상품 코드")
    private String moqSku;

    @Schema(description = "리드타임")
    private BigDecimal leadTime;

    @Schema(description = "팔레트당 수량")
    private BigDecimal boxPerPltQty;
		/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
