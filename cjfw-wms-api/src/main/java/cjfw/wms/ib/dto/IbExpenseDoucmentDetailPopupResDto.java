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
public class IbExpenseDoucmentDetailPopupResDto extends CommonProcedureDto {	
    
    /** 일련번호 */
    @Schema(description = "일련번호", nullable = false, example = "")
    private long serialkey;

    /** 센터코드 */
    @Schema(description = "센터코드", nullable = false, example = "")
    private String dccode;

    /** 고객사코드 */
    @Schema(description = "고객사코드", nullable = false, example = "")
    private String storerkey;

    /** 조직코드 */
    @Schema(description = "조직코드", nullable = false, example = "")
    private String organize;

    /** Header SN */
    @Schema(description = "Header SN", nullable = false, example = "")
    private String expenseSn;

    /** 멤버아이디 */
    @Schema(description = "멤버아이디", nullable = false, example = "")
    private String memberId;

    /** 사업장 */
    @Schema(description = "사업장", nullable = false, example = "")
    private String divisionId;

    /** 부서 */
    @Schema(description = "부서", nullable = false, example = "")
    private String organizationId;

    /** 비지니스타입 */
    @Schema(description = "비지니스타입", nullable = false, example = "")
    private String businessTypeId;

    /** 비지니스타입 코드 */
    @Schema(description = "비지니스타입 코드", nullable = false, example = "")
    private String businessTypeCode;

    /** 비지니스타입 명 */
    @Schema(description = "비지니스타입 명", nullable = false, example = "")
    private String businessTypeName;

    /** 문서번호 */
    @Schema(description = "문서번호", nullable = false, example = "")
    private String keyNo;

    /** 리비젼 */
    @Schema(description = "리비젼", nullable = false, example = "")
    private String revisionNo;

    /** ITEM/TAX */
    @Schema(description = "ITEM/TAX", nullable = false, example = "")
    private String creditKind;

    /** 문서 타입 */
    @Schema(description = "문서 타입", nullable = false, example = "")
    private String documentTypeId;

    /** Doc Type */
    @Schema(description = "Doc Type", nullable = false, example = "")
    private String documentTypeCode;

    /** 문서타입 명 */
    @Schema(description = "문서타입 명", nullable = false, example = "")
    private String documentTypeName;

    /** 참조문서의 일련번호 */
    @Schema(description = "참조문서의 일련번호", nullable = false, example = "")
    private String documentSn;

    /** 참조문서의 문서번호 */
    @Schema(description = "참조문서의 문서번호", nullable = false, example = "")
    private String documentNumber;

    /** 계정종류 */
    @Schema(description = "계정종류", nullable = false, example = "")
    private String accountKindId;

    /** 계정종류 코드 */
    @Schema(description = "계정종류 코드", nullable = false, example = "")
    private String accountKindCode;

    /** 계정종류 명 */
    @Schema(description = "계정종류 명", nullable = false, example = "")
    private String accountKindName;

    /** 차변계정 */
    @Schema(description = "차변계정", nullable = false, example = "")
    private String accountDrId;

    /** Account Code */
    @Schema(description = "Account Code", nullable = false, example = "")
    private String accountDrCode;

    /** 차변계정명 */
    @Schema(description = "차변계정명", nullable = false, example = "")
    private String accountDrName;

    /** 비용 */
    @Schema(description = "비용", nullable = false, example = "")
    private String accountDetailId;

    /** Cost Code */
    @Schema(description = "Cost Code", nullable = false, example = "")
    private String accountDetailCode;

    /** 비용명 */
    @Schema(description = "비용명", nullable = false, example = "")
    private String accountDetailName;

    /** 공급가 */
    @Schema(description = "공급가", nullable = false, example = "")
    private String supplyPrice;

    /** 공급가(원화) */
    @Schema(description = "공급가(원화)", nullable = false, example = "")
    private String supplyPriceKrw;

    /** 과세타입 */
    @Schema(description = "과세타입", nullable = false, example = "")
    private String taxTypeId;

    /** Tax code */
    @Schema(description = "Tax code", nullable = false, example = "")
    private String taxTypeCode;

    /** 과세타입 명 */
    @Schema(description = "과세타입 명", nullable = false, example = "")
    private String taxTypeName;

    /** Tax Rate */
    @Schema(description = "Tax Rate", nullable = false, example = "")
    private BigDecimal taxRate;

    /** 세금 */
    @Schema(description = "세금", nullable = false, example = "")
    private BigDecimal taxAmount;

    /** 세금(원화) */
    @Schema(description = "세금(원화)", nullable = false, example = "")
    private BigDecimal taxAmountKrw;

    /** 총금액 */
    @Schema(description = "총금액", nullable = false, example = "")
    private BigDecimal amount;

    /** 총금액(원화) */
    @Schema(description = "총금액(원화)", nullable = false, example = "")
    private BigDecimal amountKrw;

    /** 비고 */
    @Schema(description = "비고", nullable = false, example = "")
    private String summary;

    /** 참조문서 종류 */
    @Schema(description = "참조문서 종류", nullable = false, example = "")
    private String source;

    /** 상태 */
    @Schema(description = "상태", nullable = false, example = "")
    private String status;

    /** PO번호 */
    @Schema(description = "PO번호", nullable = false, example = "")
    private String poNo;

    /** 배부누적 금액 */
    @Schema(description = "배부누적 금액", nullable = false, example = "")
    private String distSupplyPrice;

    /** 환율 */
    @Schema(description = "환율", nullable = false, example = "")
    private String exchangeTypeId;

    /** 환율타입 */
    @Schema(description = "환율타입", nullable = false, example = "")
    private String exchangeType;

    /** 환율타입명 */
    @Schema(description = "환율타입명", nullable = false, example = "")
    private String exchangeTypeName;

    /** 환율일자 */
    @Schema(description = "환율일자", nullable = false, example = "")
    private String exchangeDate;

    /** 환율 */
    @Schema(description = "환율", nullable = false, example = "")
    private String exchangeRate;

    /** EAP 식별번호 */
    @Schema(description = "EAP 식별번호", nullable = false, example = "")
    private String epaSn;

    /** IP 식별번호 */
    @Schema(description = "IP 식별번호", nullable = false, example = "")
    private String ipSn;

    /** II 식별번호 */
    @Schema(description = "II 식별번호", nullable = false, example = "")
    private String iiSn;

    /** CL 식별번호 */
    @Schema(description = "CL 식별번호", nullable = false, example = "")
    private String clSn;

    /** 참고 */
    @Schema(description = "참고", nullable = false, example = "")
    private String remark;

    /** 실제 사용 Po No */
    @Schema(description = "실제 사용 Po No", nullable = false, example = "")
    private String attribute1;

    /** Attribute2 */
    @Schema(description = "Attribute2", nullable = false, example = "")
    private String attribute2;

    /** Attribute3 */
    @Schema(description = "Attribute3", nullable = false, example = "")
    private String attribute3;

    /** Attribute4 */
    @Schema(description = "Attribute4", nullable = false, example = "")
    private String attribute4;

    /** 사업장 */
    @Schema(description = "사업장", nullable = false, example = "")
    private String attribute5;

    /** 팀코드 */
    @Schema(description = "팀코드", nullable = false, example = "")
    private String attribute6;

    /** 팀명 */
    @Schema(description = "팀명", nullable = false, example = "")
    private String attribute7;

    /** 공정코드 */
    @Schema(description = "공정코드", nullable = false, example = "")
    private String attribute8;

    /** 공정명 */
    @Schema(description = "공정명", nullable = false, example = "")
    private String attribute9;

    /** 증빙유형 */
    @Schema(description = "증빙유형", nullable = false, example = "")
    private String attribute10;

    /** 세금계산서 건수 */
    @Schema(description = "세금계산서 건수", nullable = false, example = "")
    private String attribute11;

    /** 세금계산서 발행 거래처 */
    @Schema(description = "세금계산서 발행 거래처", nullable = false, example = "")
    private String attribute12;

    /** EXCEL TABLE */
    @Schema(description = "EXCEL TABLE", nullable = false, example = "")
    private String attribute13;

    /** EXCEL TABLE.SN */
    @Schema(description = "EXCEL TABLE.SN", nullable = false, example = "")
    private String attribute14;

    /** TYPE */
    @Schema(description = "TYPE", nullable = false, example = "")
    private String attribute15;

    /** Attribute Date1 */
    @Schema(description = "Attribute Date1", nullable = false, example = "")
    private String attributeDate1;

    /** Attribute Date2 */
    @Schema(description = "Attribute Date2", nullable = false, example = "")
    private String attributeDate2;

    /** Attribute Date3 */
    @Schema(description = "Attribute Date3", nullable = false, example = "")
    private String attributeDate3;

    /** Attribute Date4 */
    @Schema(description = "Attribute Date4", nullable = false, example = "")
    private String attributeDate4;

    /** Attribute Date5 */
    @Schema(description = "Attribute Date5", nullable = false, example = "")
    private String attributeDate5;

    /** PURCHASE_ORDER.SN */
    @Schema(description = "PURCHASE_ORDER.SN", nullable = false, example = "")
    private String poSn;

    /** 코스트센터코드 */
    @Schema(description = "코스트센터코드", nullable = false, example = "")
    private String costcd;

    /** 상품코드 */
    @Schema(description = "상품코드", nullable = false, example = "")
    private String itemCode;

    /** 고객코드 */
    @Schema(description = "고객코드", nullable = false, example = "")
    private String saleCustomerCode;

    /** 부가세자동계산 */
    @Schema(description = "부가세자동계산", nullable = false, example = "")
    private String taxFlag;

    /** 최초등록시간 */
    @Schema(description = "최초등록시간", nullable = false, example = "")
    private String adddate;

    /** 최종변경시간 */
    @Schema(description = "최종변경시간", nullable = false, example = "")
    private String editdate;

    /** 최초등록자 */
    @Schema(description = "최초등록자", nullable = false, example = "")
    private String addwho;

    /** 최종변경자 */
    @Schema(description = "최종변경자", nullable = false, example = "")
    private String editwho;

    /** 박스당수량 */
    @Schema(description = "박스당수량", nullable = false, example = "")
    private BigDecimal qtyperbox;

    /** 순중량 */
    @Schema(description = "순중량", nullable = false, example = "")
    private BigDecimal netweight;

    /** 기본단위 */
    @Schema(description = "기본단위", nullable = false, example = "")
    private String baseuom;

    /** 구매단위 */
    @Schema(description = "구매단위", nullable = false, example = "")
    private String purchaseuom;

    /** 반품단위 */
    @Schema(description = "반품단위", nullable = false, example = "")
    private String returnuom;

    /** 직송수량 */
    @Schema(description = "직송수량", nullable = false, example = "")
    private BigDecimal quantity;

    /** 직송단위 환산 [KG] */
    @Schema(description = "직송단위 환산 [KG]", nullable = false, example = "")
    private BigDecimal kgCal;

    /** 공급가 NEW */
    @Schema(description = "SUPPLY_PRICE_NEW", nullable = false, example = "")
    private BigDecimal supplyPriceNew;

    /** 세금 NEW */
    @Schema(description = "TAX_AMOUNT_NEW", nullable = false, example = "")
    private BigDecimal taxAmountNew;

    /** 총액 NEW */
    @Schema(description = "AMOUNT_NEW", nullable = false, example = "")
    private BigDecimal amountNew;
    
    /** 창고명 */
    @Schema(description = "창고명", nullable = false, example = "")
    private String organizename;
    
    /** 상품명 */
    @Schema(description = "상품명", nullable = false, example = "")
    private String skuname;
    
    /** 저장조건명 */
    @Schema(description = "저장조건명", nullable = false, example = "")
    private String storagetypename;
    
    /** 주문유형명 */
    @Schema(description = "주문유형명", nullable = false, example = "")
    private String ordertypename;

    /** KG비중 */
    @Schema(description = "KG비중", nullable = false, example = "")
    private BigDecimal rateQty;

    /** KG 비용분배 */
    @Schema(description = "KG 비용분배", nullable = false, example = "")
    private BigDecimal disCost;
    
    /** 관리처코드 */
    @Schema(description = "관리처코드", nullable = false, example = "")
    private String toCustkey;
    
    /** 관리처명 */
    @Schema(description = "관리처명", nullable = false, example = "")
    private String toCustname;
    
    /** B/L번호 */
    @Schema(description = "B/L번호", nullable = false, example = "")
    private String convserialno;
    
    /** 년월 */
    @Schema(description = "년월", nullable = false, example = "")
    private String yyyymm;
    
}
