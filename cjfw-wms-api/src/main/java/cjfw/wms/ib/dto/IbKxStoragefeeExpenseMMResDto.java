package cjfw.wms.ib.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : JangJaeHyun (jhjang43@cj.net)
 * @date : 2025.12.29
 * @description : 비용기표(1000센터) RES DTO Class
 * @issues : ----------------------------------------------------------- DATE
 *         AUTHOR MAJOR_ISSUE
 *         -----------------------------------------------------------
 *         2025.12.29 ParkYoSep (dytpq362@cj.net) 생성
 */
@Schema(description = "비용기표(1000센터) RES DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IbKxStoragefeeExpenseMMResDto extends CommonProcedureDto {
//    @Schema(description = "마감 여부")
//    private String closeYn;

	@Schema(description = "시리얼 키")
	private long serialKey;

	@Schema(description = "대상 연월 (YYYYMM)")
	private String zmonth;

	@Schema(description = "센터 코드")
	private String dcCode;

	@Schema(description = "MM전송번호")
	private String zinvoice;

	@Schema(description = "세금계산서 번호")
	private String issueId;

	@Schema(description = "MM송장번호")
	private String invNo; /* INV_NO (서브쿼리 결과) */

	@Schema(description = "slipNo")
	private String slipNo; /* SLIP_NO (서브쿼리 결과) */

	@Schema(description = "상태")
	private String status2;

	@Schema(description = "IF 상태 코드")
	private String ifStatus;

	@Schema(description = "수량")
	private BigDecimal qty;

	@Schema(description = "단위")
	private String uom;

	@Schema(description = "송장 금액")
	private BigDecimal zwrBtrIn;

	@Schema(description = "부가세")
	private String wmwst1; /* WMWST1 */

	@Schema(description = "Supplier CODE")
	private String adjustmentSupplierCode; /* ADJUSTMENT_SUPPLIER_CODE */

	@Schema(description = "Supplier NAME")
	private String adjustmentSupplierName; /* ADJUSTMENT_SUPPLIER_NAME */

	@Schema(description = "Summary")
	private String btext; /* BTEXT */

	@Schema(description = "수정자 ID")
	private String editWho;

	@Schema(description = "수정자명")
	private String editWhoNm;
//    @Schema(description = "센터명")
//    private String dcName;

//    @Schema(description = "카테고리 코드 (ZCAT)")
//    private String zcat;

//    @Schema(description = "카테고리명")
//    private String zcat2;

//    @Schema(description = "상품 코드")
//    private String sku;
//    
//    @Schema(description = "상품 코드")
//    private String multiSku;

//    @Schema(description = "상품명")
//    private String skuName;

	@Schema(description = "송장 금액")
	private BigDecimal zwrBtrOut;

	@Schema(description = "상태코드")
	private String status;

	@Schema(description = "추가 일자")
	private String addDate;

	@Schema(description = "추가자 ID")
	private String addWho;

	@Schema(description = "수정 일자")
	private String editDate;

	@Schema(description = "송장관리번호")
	private String keyNo;

	@Schema(description = "인터페이스 문서번호")
	private String ifDocNo;

	@Schema(description = "전송상태")
	private String fiIfStatus;
	
	@Schema(description = "인터페이스 ID")
	private String ifId;

}
