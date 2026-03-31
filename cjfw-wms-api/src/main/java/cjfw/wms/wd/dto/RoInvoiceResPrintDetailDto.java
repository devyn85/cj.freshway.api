package cjfw.wms.wd.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.07.16
 * @description : 반품명세서출력 Print Detail Response DTO Class (getPrintDetailList)
 */
@Schema(description = "반품명세서출력 Print Detail Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoInvoiceResPrintDetailDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";

    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;

    /** 상품명 */
    @Schema(description = "상품명")
    private String skuname;

    /** 단위 */
    @Schema(description = "단위")
    private String uom;

    /** 확정수량 */
    @Schema(description = "확정수량")
    private java.math.BigDecimal confirmqty;

    /** 단가 */
    @Schema(description = "단가")
    private java.math.BigDecimal unitprice;

    /** 공급가액 */
    @Schema(description = "공급가액")
    private java.math.BigDecimal supplyprice;

    /** 세액 */
    @Schema(description = "세액")
    private java.math.BigDecimal taxamount;

    /** 합계금액 */
    @Schema(description = "합계금액")
    private java.math.BigDecimal totamount;

    /** 헤더키 */
    @Schema(description = "헤더키")
    private String headerkey;

    /** 문서번호 */
    @Schema(description = "문서번호")
    private String docno;

    /** 보관유형 */
    @Schema(description = "보관유형")
    private String storagetype;
}
