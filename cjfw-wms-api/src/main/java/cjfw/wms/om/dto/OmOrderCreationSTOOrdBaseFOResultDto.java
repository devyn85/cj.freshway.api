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
 * @description : 당일광역보충발주(FO) 처리결과 목록 DTO 
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
@Schema(description = "당일광역보충발주(FO) 처리결과 목록") 
public class OmOrderCreationSTOOrdBaseFOResultDto extends CommonDto  {

    /** 선택 */
    @Schema(description = "", nullable = true, example = "")
    private String checkyn;

    /** 출발 회사코드 */
    @Schema(description = "", nullable = true, example = "")
    private String fromStorerkey;

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

    /** 처리여부 */
    @Schema(description = "", nullable = true, example = "")
    private String processflag;

    /** 처리메시지 */
    @Schema(description = "", nullable = true, example = "")
    private String processmsg;

    /** 상품명 */
    @Schema(description = "", nullable = true, example = "")
    private String description;

    /** 출발 센터명 */
    @Schema(description = "", nullable = true, example = "")
    private String fromDcname;

    /** 도착 센터명 */
    @Schema(description = "", nullable = true, example = "")
    private String toDcname;

}
