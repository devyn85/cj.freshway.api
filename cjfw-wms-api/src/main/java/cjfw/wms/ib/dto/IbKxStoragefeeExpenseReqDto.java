package cjfw.wms.ib.dto;

import java.math.BigDecimal;
import java.util.List;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.11.05
 * @description : 보관료 마감 REQ DTO Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.05        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Schema(description = "보관료 마감 REQ DTO")

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IbKxStoragefeeExpenseReqDto extends CommonProcedureDto {
	List<IbKxStoragefeeExpenseReqDto> saveList;
	
	@Schema(description = "체크 여부")
    private String checkYn;

    @Schema(description = "마감 여부")
    private String closeYn;

    @Schema(description = "대상 연월 (YYYYMM)")
    private String zmonth;
    
    @Schema(description = "대상 연월 (YYYYMMDD)")
    private String stockDate;

    @Schema(description = "센터 코드")
    private String dcCode;

    @Schema(description = "센터명")
    private String dcName;

    @Schema(description = "카테고리 코드 (ZCAT)")
    private String zcat;

    @Schema(description = "카테고리명")
    private String zcat2;

    @Schema(description = "상품 코드")
    private String sku;
    
    @Schema(description = "상품 코드")
    private String multiSku;

    @Schema(description = "상품명")
    private String skuName;

    @Schema(description = "출고 금액")
    private BigDecimal zwrBtrOut;

    @Schema(description = "입고 금액")
    private BigDecimal zwrBtrIn;

    @Schema(description = "단위 (UOM)")
    private String uom;

    @Schema(description = "재고 수량")
    private BigDecimal qty;

    @Schema(description = "인보이스 번호 (비용 처리 키)")
    private String zinvoice;

    @Schema(description = "월별 마감 상태 코드")
    private String status;

    @Schema(description = "월별 마감 상태명")
    private String status2;

    @Schema(description = "IF 상태 코드")
    private String ifStatus;

    @Schema(description = "비용 처리 상태 코드")
    private String expenseStatus;

    @Schema(description = "비용 처리 상태명")
    private String expenseStatus2;
}
