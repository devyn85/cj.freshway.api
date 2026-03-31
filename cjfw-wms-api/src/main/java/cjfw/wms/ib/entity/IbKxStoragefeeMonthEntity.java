package cjfw.wms.ib.entity;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.11.05
 * @description : 일별 보관료 계산 Entity Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.05        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Schema(description = "일별 보관료 계산 Entity")

@Data
public class IbKxStoragefeeMonthEntity extends CommonProcedureDto {
	
	@Schema(description = "체크 여부")
    private String checkYn;

    @Schema(description = "마감 여부")
    private String closeYn;

    @Schema(description = "시리얼 키")
    private BigDecimal serialKey;

    @Schema(description = "재고 일자 (YYYYMMDD)")
    private String stockDate;

    @Schema(description = "연월 (YYYYMM)")
    private String yyyyMm;

    @Schema(description = "센터 코드")
    private String dcCode;

    @Schema(description = "조직 코드")
    private String organize;

    @Schema(description = "센터명")
    private String dcName;

    @Schema(description = "카테고리 코드 (ZCAT)")
    private String zcat;

    @Schema(description = "카테고리명")
    private String zcat2;

    @Schema(description = "상품 코드")
    private String sku;

    @Schema(description = "상품명")
    private String skuName;

    @Schema(description = "PLT당 단가")
    private BigDecimal pricePlt;

    @Schema(description = "PLT당 청구 단가")
    private BigDecimal invoicePlt;

    @Schema(description = "PLT 수량")
    private BigDecimal pltCnt;

    @Schema(description = "단위 (UOM)")
    private String uom;

    @Schema(description = "총 수량")
    private BigDecimal qty;

    @Schema(description = "PLT당 단가 (PRICE_PER_PLT)")
    private BigDecimal pricePerPlt;

    @Schema(description = "박스당 낱개 수")
    private BigDecimal qtyPerBox;

    @Schema(description = "팔레트당 박스 수")
    private BigDecimal boxPerPlt;

    @Schema(description = "수정자")
    private String editWho;

    @Schema(description = "월별 상태 코드")
    private String status;

    @Schema(description = "월별 상태명")
    private String status2;
    
    @Schema(description = "연월 (YYYYMM)")
    private String zmonth;
}
