package cjfw.wms.ib.dto;

import java.math.BigDecimal;

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
 * @date : 2025.08.05
 * @description : 비용기표 조회 결과 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.05    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "비용기표 조회 결과") 
public class IbExpenseResDto extends CommonProcedureDto {
    
    /** 물류센터 */
    @Schema(description = "물류센터", nullable = false, example = "")
    private String dccode;
    
    /** 상태코드 */
    @Schema(description = "상태코드", nullable = false, example = "")
    private String statusCode;

    /** 상태코드명 */
    @Schema(description = "상태코드명", nullable = false, example = "")
    private String status;

    /** 내부결재상태 */
    @Schema(description = "내부결재상태", nullable = false, example = "")
    private String internalApprStatus;

    /** ERP APPR STATUS */
    @Schema(description = "ERP APPR STATUS", nullable = false, example = "")
    private String erpApprStatus;

    /** IF Status */
    @Schema(description = "IF Status", nullable = false, example = "")
    private String status1;

    /** Appr Status */
    @Schema(description = "Appr Status", nullable = false, example = "")
    private String apprStatus;

    /** Business Type ID */
    @Schema(description = "Business Type ID", nullable = false, example = "")
    private String businessTypeId;

    /** Document No */
    @Schema(description = "Document No", nullable = false, example = "")
    private String keyNo;

    /** Document Date */
    @Schema(description = "Document Date", nullable = false, example = "")
    private String issueDate;

    /** Due Date */
    @Schema(description = "Due Date", nullable = false, example = "")
    private String dueDate;

    /** Supplier Code */
    @Schema(description = "Supplier Code", nullable = false, example = "")
    private String adjustmentSupplierCode;

    /** Supplier Name */
    @Schema(description = "Supplier Name", nullable = false, example = "")
    private String adjustmentSupplierName;

    /** ACTUAL SUPPLIER CODE */
    @Schema(description = "ACTUAL SUPPLIER CODE", nullable = false, example = "")
    private String actualSupplierCode;

    /** ACTUAL SUPPLIER NAME */
    @Schema(description = "ACTUAL SUPPLIER NAME", nullable = false, example = "")
    private String actualSupplierName;

    /** ADJUSTMENT SITE CODE */
    @Schema(description = "ADJUSTMENT SITE CODE", nullable = false, example = "")
    private String adjustmentSiteCode;

    /** ADJUSTMENT SITE NAME */
    @Schema(description = "ADJUSTMENT SITE NAME", nullable = false, example = "")
    private String adjustmentSiteName;

    /** 차변계정코드 */
    @Schema(description = "차변계정코드", nullable = false, example = "")
    private String accountCrCode;

    /** 차변계정명 */
    @Schema(description = "차변계정명", nullable = false, example = "")
    private String accountCrName;

    /** EXCHANGE RATE */
    @Schema(description = "EXCHANGE RATE", nullable = false, example = "")
    private BigDecimal exchangeRate;

    /** SUPPLY PRICE KRW */
    @Schema(description = "SUPPLY PRICE KRW", nullable = false, example = "")
    private BigDecimal supplyPriceKrw;

    /** TAX AMOUNT KRW */
    @Schema(description = "TAX AMOUNT KRW", nullable = false, example = "")
    private BigDecimal taxAmountKrw;

    /** AMOUNT_KRW */
    @Schema(description = "AMOUNT_KRW", nullable = false, example = "")
    private BigDecimal amountKrw;

    /** 통화 */
    @Schema(description = "통화", nullable = false, example = "")
    private String amountUnit;

    /** Supply Price */
    @Schema(description = "Supply Price", nullable = false, example = "")
    private BigDecimal supplyPrice;

    /** Tax Amount */
    @Schema(description = "Tax Amount", nullable = false, example = "")
    private BigDecimal taxAmount;

    /** Tax Type Code */
    @Schema(description = "Tax Type Code", nullable = false, example = "")
    private String taxTypeCode;

    /** Amount */
    @Schema(description = "Amount", nullable = false, example = "")
    private BigDecimal amount;

    /** Summary */
    @Schema(description = "Summary", nullable = false, example = "")
    private String summary;

    /** 생성인 */
    @Schema(description = "생성인", nullable = false, example = "")
    private String addwho;
    
    /** 생성인 */
    @Schema(description = "생성인", nullable = false, example = "")
    private String addwhoNm;

    /** 들록일자 */
    @Schema(description = "등록일자", nullable = false, example = "")
    private String adddate;

    /** 최종변경자 */
    @Schema(description = "최종변경자", nullable = false, example = "")
    private String editwho;
    
    /** 최종변경자 */
    @Schema(description = "최종변경자", nullable = false, example = "")
    private String editwhoNm;

    /** 최종변경시간 */
    @Schema(description = "최종변경시간", nullable = false, example = "")
    private String editdate;

    /** 데이터번호 */
    @Schema(description = "데이터번호", nullable = false, example = "")
    private String serialkey;

    /** Member ID */
    @Schema(description = "Member ID", nullable = false, example = "")
    private String memberId;

    /** PURCHASING GROUP CODE */
    @Schema(description = "PURCHASING GROUP CODE", nullable = false, example = "")
    private String purchasingGroupCode;

    /** 고객사코드 */
    @Schema(description = "고객사코드", nullable = false, example = "")
    private String storerkey;

    /** 고객사명 */
    @Schema(description = "고객사명", nullable = false, example = "")
    private String storername;

    /** Account Detail */
    @Schema(description = "Account Detail", nullable = false, example = "")
    private String accountDetail;

    /** Item */
    @Schema(description = "Item", nullable = false, example = "")
    private Integer lovItem;

    /** 파일 */
    @Schema(description = "파일", nullable = false, example = "")
    private Integer fileCnt;

    /** Fiscal Year */
    @Schema(description = "Fiscal Year", nullable = false, example = "")
    private Integer fiscalYear;

    /** Posting Date */
    @Schema(description = "Posting Date", nullable = false, example = "")
    private String postingDate;

    /** Transaction CD */
    @Schema(description = "Transaction CD", nullable = false, example = "")
    private String transactionCd;

    /** Transaction 명 */
    @Schema(description = "Transaction 명", nullable = false, example = "")
    private String transactionCdValue;

    /** Interface Count */
    @Schema(description = "Interface Count", nullable = false, example = "")
    private Integer interfaceCnt;

    /** MM송장번호 */
    @Schema(description = "MM송장번호", nullable = false, example = "")
    private String invNo;

    /** IF No */
    @Schema(description = "IF No", nullable = false, example = "")
    private String ifDocNo;

    /** 발주번호 */
    @Schema(description = "발주번호", nullable = false, example = "")
    private String poNo;

    /** Slip No */
    @Schema(description = "Slip No", nullable = false, example = "")
    private String slipNo;

    /** 비용종류 */
    @Schema(description = "비용종류", nullable = false, example = "")
    private String attributes2;

    /** Posting Status */
    @Schema(description = "Posting Status", nullable = false, example = "")
    private String fiIfStatus;
    
    /** Posting Status Name */
    @Schema(description = "Posting Status Name", nullable = false, example = "")
    private String fiIfStatusName;
    
    /** IF Status */
    @Schema(description = "IF Status", nullable = false, example = "")
    private String ifStatus;

    /** 재고년월 */
    @Schema(description = "재고년월", nullable = false, example = "")
    private String yyyymm;

    /** 창고코드 */
    @Schema(description = "창고코드", nullable = false, example = "")
    private String organize;
    
    /** 창고명 */
    @Schema(description = "창고명", nullable = false, example = "")
    private String organizeName;

    /** 세금코드 */
    @Schema(description = "세금코드", nullable = false, example = "")
    private String taxTypeName;

    /** Tax Invoice */
    @Schema(description = "Tax Invoice", nullable = false, example = "")
    private String taxTag;

    /** Tax No */
    @Schema(description = "Tax No", nullable = false, example = "")
    private String taxNo;

    /** 증빙일 */
    @Schema(description = "증빙일", nullable = false, example = "")
    private String taxYmd;
    
    /** 3자물류유형코드 */
    @Schema(description = "3자물류유형코드", nullable = false, example = "")
    private String tplType;
    
}
