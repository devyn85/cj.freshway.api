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
 * @description : 당일광역보충발주(FO) 이체대상정보 조회 목록 DTO 
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
@Schema(description = "당일광역보충발주(FO) 이체대상정보 조회 목록") 
public class OmOrderCreationSTOOrdBaseFOStoResDto extends CommonDto  {

    /** 선택 */
    @Schema(description = "", nullable = true, example = "")
    private String checkyn;

    /** 회사코드 */
    @Schema(description = "", nullable = true, example = "")
    private String storerkey;
    
    /** 공급센터코드 */
    @Schema(description = "", nullable = true, example = "")
    private String stoDccode;
    
    /** 공급센터명 */
    @Schema(description = "", nullable = true, example = "")
    private String stoDccodename;

    /** 센터코드 */
    @Schema(description = "", nullable = true, example = "")
    private String dccode;
    
    /** 센터코드명 */
    @Schema(description = "", nullable = true, example = "")
    private String dccodename;
    
    /** 이체주문번호 */
    @Schema(description = "", nullable = true, example = "")
    private String stoDocno;
    
    /** 이체주문번호항번 */
    @Schema(description = "", nullable = true, example = "")
    private String stoDocline;
    
    /** 이체량 */
    @Schema(description = "", nullable = true, example = "")
    private BigDecimal stoOrderqty;
    
    /** 주문번호 */
    @Schema(description = "", nullable = true, example = "")
    private String docno;
    
    /** 주문번호항번 */
    @Schema(description = "", nullable = true, example = "")
    private String docline;
    
    /** 주문량 */
    @Schema(description = "", nullable = true, example = "")
    private BigDecimal orderqty;
    
    /** 관리처코드 */
    @Schema(description = "", nullable = true, example = "")
    private String toCustkey;
    
    /** 관리처명 */
    @Schema(description = "", nullable = true, example = "")
    private String toCustkeyname;
    
    /** 저장조건 */
    @Schema(description = "", nullable = true, example = "")
    private String storagetype;
    
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
    
    /** 상품코드 */
    @Schema(description = "", nullable = true, example = "")
    private String sku;

    /** 상품명 */
    @Schema(description = "", nullable = true, example = "")
    private String skuname;
    
    /** 단위 */
    @Schema(description = "", nullable = true, example = "")
    private String uom;
    
    /** 등록자 */
    @Schema(description = "", nullable = true, example = "")
    private String addwho;

    /** 등록자명 */
    @Schema(description = "", nullable = true, example = "")
    private String addwhoname;

    /** 등록일시 */
    @Schema(description = "", nullable = true, example = "")
    private String adddate;

    /** 문서일자 */
    @Schema(description = "문서일자", example = "")
    private String docdt;
    
    /** 문서유형 */
    @Schema(description = "문서유형", example = "")
    private String doctype;
    
    /** 배송일자 */
    @Schema(description = "배송일자", example = "")
    private String deliverydate;

}
