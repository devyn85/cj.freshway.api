package cjfw.wms.om.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2026.03.10
 * @description : 당일광역보충발주(FO) 상세 조회 목록 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2026.03.10    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "당일광역보충발주(FO) 상세 조회 목록") 
public class OmOrderCreationSTOOrdBaseFODetailResDto extends CommonDto  {

    /** 선택 */
    @Schema(description = "", nullable = true, example = "")
    private String checkyn;

    /** 회사코드 */
    @Schema(description = "", nullable = true, example = "")
    private String storerkey;

    /** 센터코드 */
    @Schema(description = "", nullable = true, example = "")
    private String dccode;

    /** 상품코드 */
    @Schema(description = "", nullable = true, example = "")
    private String sku;

    /** 상품명 */
    @Schema(description = "", nullable = true, example = "")
    private String description;

    /** 재고속성 */
    @Schema(description = "", nullable = true, example = "")
    private String stockgrade;

    /** 단위 */
    @Schema(description = "", nullable = true, example = "")
    private String uom;

    /** 공급센터 크로스타입 */
    @Schema(description = "", nullable = true, example = "")
    private String fromCrossdocktype;

    /** 공급받는센터 크로스타입 */
    @Schema(description = "", nullable = true, example = "")
    private String toCrossdocktype;

    /** DC_A 가용재고 */
    @Schema(description = "", nullable = true, example = "")
    private BigDecimal openqty1;

    /** 발주센터 가용재고 */
    @Schema(description = "", nullable = true, example = "")
    private BigDecimal openqty9;

    /** 입고예정량 */
    @Schema(description = "", nullable = true, example = "")
    private BigDecimal dpQty;

    /** 주문량 */
    @Schema(description = "", nullable = true, example = "")
    private BigDecimal orderqty;

    /** 발주예정량 */
    @Schema(description = "", nullable = true, example = "")
    private BigDecimal reqOrderqty;

    /** 분배량 */
    @Schema(description = "", nullable = true, example = "")
    private BigDecimal processqty;

    /** 공급센터1 발주량 */
    @Schema(description = "", nullable = true, example = "")
    private BigDecimal req1;

    /** 발주센터 가용재고 */
    @Schema(description = "", nullable = true, example = "")
    private BigDecimal openqty5;

}
