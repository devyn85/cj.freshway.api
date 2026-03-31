package cjfw.wms.om.dto;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.09.18
 * @description : 저장품센터간이체 조회 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.18 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Data
//@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Schema(description = "저장품센터간이체 조회 결과")
public class OmOrderCreationSTOForDcResDto extends CommonProcedureDto {
    @Schema(description = "선택 여부")
    private String checkyn;

    @Schema(description = "고객사코드")
    private String storerkey;

    @Schema(description = "센터코드")
    private String dccode;

    @Schema(description = "상품코드")
    private String sku;

    @Schema(description = "상품명")
    private String skuName;

    @Schema(description = "재고속성")
    private String stockgrade;
   
    @Schema(description = "단위")
    private String uom;

    @Schema(description = "공급센터 이동 가능량")
    private String openqty;

    @Schema(description = "공급받는센터 이동 가능량")
    private String toOpenqty;

    @Schema(description = "공급센터 입고예정량")
    private String dpQty;

    @Schema(description = "수원광역 발주량")
    private String req;
    
    /** PROCESSFLAG */
    @Schema(description = "PROCESSFLAG", example = "")
    private String processflag;
    
    /** PROCESSMSG */
    @Schema(description = "PROCESSMSG", example = "")
    private String processmsg;
    
    /** DESCRIPTION;. */
    @Schema(description = "DESCRIPTION;.", example = "")
    private String description;

    /* ===== 처리결과 조회용 FROM/TO 필드 ===== */
    @Schema(description = "출고 STORERKEY")
    private String fromStorerkey;
    @Schema(description = "출고 DCCODE")
    private String fromDccode;
    @Schema(description = "출고 ORGANIZE")
    private String fromOrganize;
    @Schema(description = "출고 AREA")
    private String fromArea;
    @Schema(description = "출고 SKU")
    private String fromSku;
    @Schema(description = "출고 UOM")
    private String fromUom;
    @Schema(description = "출고 STOCKID")
    private String fromStockid;
    @Schema(description = "출고 STOCKGRADE")
    private String fromStockgrade;

    @Schema(description = "입고 수량")
    private java.math.BigDecimal toOrderqty;
    @Schema(description = "입고 DCCODE")
    private String toDccode;
    @Schema(description = "입고 ORGANIZE")
    private String toOrganize;
    @Schema(description = "입고 AREA")
    private String toArea;
    @Schema(description = "입고 SKU")
    private String toSku;
    @Schema(description = "입고 UOM")
    private String toUom;
    @Schema(description = "입고 STOCKID")
    private String toStockid;
    @Schema(description = "입고 STOCKGRADE")
    private String toStockgrade;

    @Schema(description = "출고 DC명")
    private String fromDcname;
    @Schema(description = "입고 DC명")
    private String toDcname;
    
    @Schema(description = "커스텀 엑스트라 체크박스", example = "N")
    private String customRowCheckYn="N";
    
    @Schema(description = "상품코드 유효성")
    private String skuYn;    
    @Schema(description = "재고속성 유효성")
    private String stockgradeYn;
    @Schema(description = "중복체크")
    private String duplicateYn;    
    @Schema(description = "uid", example = "")
    private String checkId;
    
}
