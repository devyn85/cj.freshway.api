package cjfw.wms.om.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.07.09
 * @description : 주문이력현황 Detail Res DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.09        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Schema(description = "주문이력현황 Detail 조회")
public class OmInplanDetailResDto {
	@Schema(description = "문서 번호")
    private String docNo;

    @Schema(description = "문서 라인")
    private String docLine;

    @Schema(description = "지역명")
    private String area;

    @Schema(description = "상품 코드")
    private String sku;

    @Schema(description = "상품 설명")
    private String description;

    @Schema(description = "오픈 수량")
    private BigDecimal openQty;

    @Schema(description = "수정일", example = "YYYYMMDDHH24MISS")
    private String editDate;

    @Schema(description = "수정자")
    private String editWho;

    @Schema(description = "단위 (UOM)")
    private String uom;

    @Schema(description = "주문 수량")
    private BigDecimal orderQty;

    @Schema(description = "주문 조정 수량")
    private BigDecimal orderAdjustQty;

    @Schema(description = "삭제 여부")
    private String delYn;

    @Schema(description = "삭제 여부 설명")
    private String delYnDescr;
    
    @Schema(description = "최종 변경자")
    private String editWhoNm;
}
