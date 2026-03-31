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
 * @description : 당일광역보충발주(FO) 조회 목록 DTO 
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
@Schema(description = "당일광역보충발주(FO) 조회 목록") 
public class OmOrderCreationSTOOrdBaseFOResDto extends CommonDto  {

    /** 선택 */
    @Schema(description = "", nullable = true, example = "")
    private String checkyn;
    
    /** 이체일자 */
    @Schema(description = "이체일자", example = "")
    private String deliverydate;

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
    
    /** 저장조건 */
    @Schema(description = "", nullable = true, example = "")
    private String storagetype;

    /** 단위 */
    @Schema(description = "", nullable = true, example = "")
    private String uom;

    /** 공급센터 크로스타입 */
    @Schema(description = "", nullable = true, example = "")
    private String fromCrossdocktype;
    
    /** 공급센터 크로스타입명 */
    @Schema(description = "", nullable = true, example = "")
    private String fromCrossdocktypeName;

    /** 공급받는센터 크로스타입 */
    @Schema(description = "", nullable = true, example = "")
    private String toCrossdocktype;
    
    /** 공급받는센터 크로스타입명 */
    @Schema(description = "", nullable = true, example = "")
    private String toCrossdocktypeName;

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
    
    /** 미발주량 */
    @Schema(description = "", nullable = true, example = "")
    private BigDecimal unOrderqty;
    
    /** 출발 센터코드 */
    @Schema(description = "", nullable = true, example = "")
    private String fromDccode;
    
    /** 출발 조직 */
    @Schema(description = "", nullable = true, example = "")
    private String fromOrganize;

    /** 출발 Area */
    @Schema(description = "", nullable = true, example = "")
    private String fromArea;
    
    /** 출발 상품코드 */
    @Schema(description = "", nullable = true, example = "")
    private String fromSku;

    /** 출발 단위 */
    @Schema(description = "", nullable = true, example = "")
    private String fromUom;
    
    /** 출발 재고속성 */
    @Schema(description = "", nullable = true, example = "")
    private String fromStockgrade;
    
    /** 출발 재고ID */
    @Schema(description = "", nullable = true, example = "")
    private String fromStockid;
    
    /** 출발 Loc */
    @Schema(description = "", nullable = true, example = "")
    private String fromLoc;
    
    /** 출발 Lot */
    @Schema(description = "", nullable = true, example = "")
    private String fromLot;
    
    /** 이동수량 */
    @Schema(description = "", nullable = true, example = "")
    private BigDecimal toOrderqty;

    /** 도착 센터코드 */
    @Schema(description = "", nullable = true, example = "")
    private String toDccode;

    /** 도착 조직 */
    @Schema(description = "", nullable = true, example = "")
    private String toOrganize;

    /** 도착 Area */
    @Schema(description = "", nullable = true, example = "")
    private String toArea;

    /** 도착 상품코드 */
    @Schema(description = "", nullable = true, example = "")
    private String toSku;

    /** 도착 단위 */
    @Schema(description = "", nullable = true, example = "")
    private String toUom;

    /** 도착 재고속성 */
    @Schema(description = "", nullable = true, example = "")
    private String toStockgrade;
    
    /** 메모 */
    @Schema(description = "", nullable = true, example = "")
    private String memo1;
    
    /** 문서번호 리스트 */
    @Schema(description = "", nullable = true, example = "")
    private String docnoList;

}
