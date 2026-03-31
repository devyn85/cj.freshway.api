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
@Schema(description = "비용기표 팝업 조회 결과") 
public class IbExpenseDoucmentHeaderPopupResDto extends CommonProcedureDto {	
    
    /** 일련번호 */
    @Schema(description = "일련번호", nullable = false, example = "")
    private BigDecimal serialkey;

    /** 센터코드 */
    @Schema(description = "센터코드", nullable = false, example = "")
    private String dccode;

    /** 고객사코드 */
    @Schema(description = "고객사코드", nullable = false, example = "")
    private String storerkey;

    /** 법인식별번호 */
    @Schema(description = "법인식별번호", nullable = false, example = "")
    private BigDecimal memberId;

    /** 사업장식별번호 */
    @Schema(description = "사업장식별번호", nullable = false, example = "")
    private BigDecimal divisionId;

    /** 조직식별번호 */
    @Schema(description = "조직식별번호", nullable = false, example = "")
    private BigDecimal organizationId;

    /** 업무유형식별번호 */
    @Schema(description = "업무유형식별번호", nullable = false, example = "")
    private BigDecimal businessTypeId;

    /** 업무유형코드 */
    @Schema(description = "업무유형코드", nullable = false, example = "")
    private String businessTypeCode;

    /** 업무유형명칭 */
    @Schema(description = "업무유형명칭", nullable = false, example = "")
    private String businessTypeName;

    /** 문서 관리번호 */
    @Schema(description = "문서 관리번호", nullable = false, example = "")
    private String keyNo;

    /** 수정횟수 */
    @Schema(description = "수정횟수", nullable = false, example = "")
    private BigDecimal revisionNo;

    /** 비용 발생일자 */
    @Schema(description = "비용 발생일자", nullable = false, example = "")
    private String issueDate;

    /** 비용 지급 예정일 */
    @Schema(description = "비용 지급 예정일", nullable = false, example = "")
    private String dueDate;

    /** 비용 발생업체 코드 */
    @Schema(description = "비용 발생업체 코드", nullable = false, example = "")
    private String adjustmentSupplierCode;

    /** 비용 발생 업체 명칭 */
    @Schema(description = "비용 발생 업체 명칭", nullable = false, example = "")
    private String adjustmentSupplierName;

    /** 정산업체코드 */
    @Schema(description = "정산업체코드", nullable = false, example = "")
    private String adjustmentSiteCode;

    /** 정산업체명 */
    @Schema(description = "정산업체명", nullable = false, example = "")
    private String adjustmentSiteName;

    /** 대체 지급처 코드 */
    @Schema(description = "대체 지급처 코드", nullable = false, example = "")
    private String actualSupplierCode;

    /** 대체 지급처 명칭 */
    @Schema(description = "대체 지급처 명칭", nullable = false, example = "")
    private String actualSupplierName;

    /** 대금 지급 방법 ID */
    @Schema(description = "대금 지급 방법 ID", nullable = false, example = "")
    private BigDecimal paymentTermsId;

    /** 대금 지급 방법 코드(2AA, 2AL.. ) */
    @Schema(description = "대금 지급 방법 코드(2AA, 2AL.. )", nullable = false, example = "")
    private String paymentTermsCode;

    /** 대금 지급 방법 명칭 */
    @Schema(description = "대금 지급 방법 명칭", nullable = false, example = "")
    private String paymentTermsName;

    /** 구매 담당장 ID */
    @Schema(description = "구매 담당장 ID", nullable = false, example = "")
    private Long purchasePersonId;

    /** 구매 담당자 코드 */
    @Schema(description = "구매 담당자 코드", nullable = false, example = "")
    private String purchasePersonCode;

    /** 구매 담당자 */
    @Schema(description = "구매 담당자", nullable = false, example = "")
    private String purchasePerson;

    /** 계정분류식별자 */
    @Schema(description = "계정분류식별자", nullable = false, example = "")
    private Long accountKindId;

    /** 계정분류코드 */
    @Schema(description = "계정분류코드", nullable = false, example = "")
    private String accountKindCode;

    /** 계정분류명 */
    @Schema(description = "계정분류명", nullable = false, example = "")
    private String accountKindName;

    /** 대변 계정 ID */
    @Schema(description = "대변 계정 ID", nullable = false, example = "")
    private Long accountCrId;

    /** 대변 계정 코드 */
    @Schema(description = "대변 계정 코드", nullable = false, example = "")
    private String accountCrCode;

    /** 대변 계정 명칭 */
    @Schema(description = "대변 계정 명칭", nullable = false, example = "")
    private String accountCrName;

    /** 계정 상세 ID */
    @Schema(description = "계정 상세 ID", nullable = false, example = "")
    private Long accountDetailId;

    /** 계정 상세 코드 */
    @Schema(description = "계정 상세 코드", nullable = false, example = "")
    private String accountDetailCode;

    /** 계정 상세 명칭 */
    @Schema(description = "계정 상세 명칭", nullable = false, example = "")
    private String accountDetailName;

    /** 공급가격 */
    @Schema(description = "공급가격", nullable = false, example = "")
    private BigDecimal supplyPrice;

    /** 공급원화가격 */
    @Schema(description = "공급원화가격", nullable = false, example = "")
    private BigDecimal supplyPriceKrw;

    /** 금액 단위 */
    @Schema(description = "금액 단위", nullable = false, example = "")
    private String amountUnit;

    /** 금액단위 ID */
    @Schema(description = "금액단위 ID", nullable = false, example = "")
    private Long amountUnitId;

    /** 환율타입 ID */
    @Schema(description = "환율타입 ID", nullable = false, example = "")
    private Long exchangeTypeId;

    /** 환율 타입 */
    @Schema(description = "환율 타입", nullable = false, example = "")
    private String exchangeType;

    /** 환율타입 명칭 */
    @Schema(description = "환율타입 명칭", nullable = false, example = "")
    private String exchangeTypeName;

    /** 환율일자 */
    @Schema(description = "환율일자", nullable = false, example = "")
    private String exchangeDate;

    /** 환율 */
    @Schema(description = "환율", nullable = false, example = "")
    private BigDecimal exchangeRate;

    /** 세금 타입 ID */
    @Schema(description = "세금 타입 ID", nullable = false, example = "")
    private Long taxTypeId;

    /** 세금 타입 코드 */
    @Schema(description = "세금 타입 코드", nullable = false, example = "")
    private String taxTypeCode;

    /** 세금 타입 명칭 */
    @Schema(description = "세금 타입 명칭", nullable = false, example = "")
    private String taxTypeName;

    /** Tax Rate */
    @Schema(description = "Tax Rate", nullable = false, example = "")
    private BigDecimal taxRate;

    /** 세금 금액 */
    @Schema(description = "세금 금액", nullable = false, example = "")
    private BigDecimal taxAmount;

    /** 원화 세금 금액 */
    @Schema(description = "원화 세금 금액", nullable = false, example = "")
    private BigDecimal taxAmountKrw;

    /** 금액 */
    @Schema(description = "금액", nullable = false, example = "")
    private BigDecimal amount;

    /** 원화금액 */
    @Schema(description = "원화금액", nullable = false, example = "")
    private BigDecimal amountKrw;

    /** 적요 사항 */
    @Schema(description = "적요 사항", nullable = false, example = "")
    private String summary;

    /** 소스 */
    @Schema(description = "소스", nullable = false, example = "")
    private String source;

    /** ENT : 작성 / CFM : 결재승인 / SCO : 완료(인터페이스성공) */
    @Schema(description = "ENT : 작성 / CFM : 결재승인 / SCO : 완료(인터페이스성공)", nullable = false, example = "")
    private String status;
    
    /** 예비필드(Attributes1) - Excel Upload SN */
    @Schema(description = "예비필드(Attributes1) - Excel Upload SN", nullable = false, example = "")
    private String attributes1;

    /** 비용발생 프로그램 구분 CH,NULL=수기등록 SM=STOCK MONTHLY SC=STOCK CASE PC=PURCHASE CLAIM */
    @Schema(description = "비용발생 프로그램 구분 CH,NULL=수기등록 SM=STOCK MONTHLY SC=STOCK CASE PC=PURCHASE CLAIM", nullable = false, example = "")
    private String attributes2;

    /** 예비필드(Attributes3) */
    @Schema(description = "예비필드(Attributes3)", nullable = false, example = "")
    private String attributes3;

    /** 예비필드(Attributes4) */
    @Schema(description = "예비필드(Attributes4)", nullable = false, example = "")
    private String attributes4;

    /** 예비필드(Attributes5) */
    @Schema(description = "예비필드(Attributes5)", nullable = false, example = "")
    private String attributes5;

    /** 예비필드(Attributes6) */
    @Schema(description = "예비필드(Attributes6)", nullable = false, example = "")
    private String attributes6;

    /** 예비필드(Attributes7) */
    @Schema(description = "예비필드(Attributes7)", nullable = false, example = "")
    private String attributes7;

    /** 예비필드(Attributes8) */
    @Schema(description = "예비필드(Attributes8)", nullable = false, example = "")
    private String attributes8;

    /** 예비필드(Attributes9) */
    @Schema(description = "예비필드(Attributes9)", nullable = false, example = "")
    private String attributes9;

    /** 예비필드(Attributes10) */
    @Schema(description = "예비필드(Attributes10)", nullable = false, example = "")
    private String attributes10;

    /** 예비필드(Attributes11) */
    @Schema(description = "예비필드(Attributes11)", nullable = false, example = "")
    private String attributes11;

    /** 예비필드(Attributes12) */
    @Schema(description = "예비필드(Attributes12)", nullable = false, example = "")
    private String attributes12;

    /** 예비필드(Attributes13) */
    @Schema(description = "예비필드(Attributes13)", nullable = false, example = "")
    private String attributes13;

    /** 예비필드(Attributes14) */
    @Schema(description = "예비필드(Attributes14)", nullable = false, example = "")
    private String attributes14;

    /** 예비필드(Attributes15) */
    @Schema(description = "예비필드(Attributes15)", nullable = false, example = "")
    private String attributes15;

    /** 예비필드(Attributes_Date1) */
    @Schema(description = "예비필드(Attributes_Date1)", nullable = false, example = "")
    private String attributesDate1;

    /** 예비필드(Attributes_Date2) */
    @Schema(description = "예비필드(Attributes_Date2)", nullable = false, example = "")
    private String attributesDate2;

    /** 예비필드(Attributes_Date3) */
    @Schema(description = "예비필드(Attributes_Date3)", nullable = false, example = "")
    private String attributesDate3;

    /** 예비필드(Attributes_Date4) */
    @Schema(description = "예비필드(Attributes_Date4)", nullable = false, example = "")
    private String attributesDate4;

    /** 예비필드(Attributes_Date5) */
    @Schema(description = "예비필드(Attributes_Date5)", nullable = false, example = "")
    private String attributesDate5;
    
    /** 파트터사  문서 상태 N:작성상태 Y:Request 상태 */
    @Schema(description = "파트터사  문서 상태 N:작성상태 Y:Request 상태", nullable = false, example = "")
    private String ipStatus;

    /** 관세사 코드 */
    @Schema(description = "관세사 코드", nullable = false, example = "")
    private String customsCode;

    /** 관세사 명 */
    @Schema(description = "관세사 명", nullable = false, example = "")
    private String customsName;

    /** 사업장 */
    @Schema(description = "사업장", nullable = false, example = "")
    private String businessplaceCd;

    /** 인터페이스 사용자 */
    @Schema(description = "인터페이스 사용자", nullable = false, example = "")
    private String ifUserId;

    /** 인터페이스 일자 */
    @Schema(description = "인터페이스 일자", nullable = false, example = "")
    private String ifDate;

    /** 회계년도 */
    @Schema(description = "회계년도", nullable = false, example = "")
    private String fiscalYear;

    /** 전표번호 */
    @Schema(description = "전표번호", nullable = false, example = "")
    private String slipNo;

    /** Posting Date */
    @Schema(description = "Posting Date", nullable = false, example = "")
    private String postingDate;

    /** 전표유형(T : 정상,  M :마이너스) */
    @Schema(description = "전표유형(T : 정상,  M :마이너스)", nullable = false, example = "")
    private String acctCls;

    /** 증빙일 */
    @Schema(description = "증빙일", nullable = false, example = "")
    private String taxYmd;

    /** 정산유형 */
    @Schema(description = "정산유형", nullable = false, example = "")
    private String taxSttlCls;

    /** 기표부서코드 */
    @Schema(description = "기표부서코드", nullable = false, example = "")
    private String invDeptCd;

    /** 코스트 센터(운영팀) */
    @Schema(description = "코스트 센터(운영팀)", nullable = false, example = "")
    private String costcd;

    /** 정산담당자 (기본) */
    @Schema(description = "정산담당자 (기본)", nullable = false, example = "")
    private String invUserId;

    /** 지불조건 */
    @Schema(description = "지불조건", nullable = false, example = "")
    private String paymentTerm;

    /** 지불방법 */
    @Schema(description = "지불방법", nullable = false, example = "")
    private String paymentMethod;

    /** 보류여부 */
    @Schema(description = "보류여부", nullable = false, example = "")
    private String paymentDly;

    /** 송장번호 - MM */
    @Schema(description = "송장번호 - MM", nullable = false, example = "")
    private String invNo;

    /** 세금계산서번호 */
    @Schema(description = "세금계산서번호", nullable = false, example = "")
    private String taxNo;

    /** 분개코드 */
    @Schema(description = "분개코드", nullable = false, example = "")
    private String journalTypeCode;

    /** 분개 */
    @Schema(description = "분개", nullable = false, example = "")
    private String journalTypeName;

    /** 인터페이스ID */
    @Schema(description = "인터페이스ID", nullable = false, example = "")
    private String ifId;

    /** SCO : 요청성공 / CCO : 요청취소성공 / ERR : 인터페이스(요청/요청취소) 실패 */
    @Schema(description = "SCO : 요청성공 / CCO : 요청취소성공 / ERR : 인터페이스(요청/요청취소) 실패", nullable = false, example = "")
    private String ifStatus;

    /** T : 세금계산서, I : 거래명세서, A:계산서,  E : 기타 계산서 */
    @Schema(description = "T : 세금계산서, I : 거래명세서, A:계산서,  E : 기타 계산서", nullable = false, example = "")
    private String taxTag;

    /** 구매그룹코드 */
    @Schema(description = "구매그룹코드", nullable = false, example = "")
    private String purchasingGroupCode;

    /** 전자세금계산서 업체구분 (E:미연동, G:Gtax, S:SmartBill, T:KT-NET,L:KL-NET) */
    @Schema(description = "전자세금계산서 업체구분 (E:미연동, G:Gtax, S:SmartBill, T:KT-NET,L:KL-NET)", nullable = false, example = "")
    private String etaxComCd;

    /** Y:정발행, N:역발행 */
    @Schema(description = "Y:정발행, N:역발행", nullable = false, example = "")
    private String direTag;

    /** 세액구분(F:면세, Y:과세, Z:영세) */
    @Schema(description = "세액구분(F:면세, Y:과세, Z:영세)", nullable = false, example = "")
    private String vatCls;

    /** 매출타입 */
    @Schema(description = "매출타입", nullable = false, example = "")
    private String saleCd;

    /** 트랜잭션코드(OB/FI) */
    @Schema(description = "트랜잭션코드(OB/FI)", nullable = false, example = "")
    private String transactionCd;

    /** 회사(신생법인/계열사) ID */
    @Schema(description = "회사(신생법인/계열사) ID", nullable = false, example = "")
    private Long companyId;

    /** 품의상태 STB(StandBy) : 품의 대기, REQ(Request) : 품의 요청 , REJ(Reject) : 품의 반려, APP(Approval) : 품의 확정 */
    @Schema(description = "품의상태 STB(StandBy) : 품의 대기, REQ(Request) : 품의 요청 , REJ(Reject) : 품의 반려, APP(Approval) : 품의 확정", nullable = false, example = "")
    private String apprStatus;

    /** 품의요청/반려/승인 일자 */
    @Schema(description = "품의요청/반려/승인 일자", nullable = false, example = "")
    private String apprDate;

    /** STOCK_IO_ITEM.DOCUMENT_NO */
    @Schema(description = "STOCK_IO_ITEM.DOCUMENT_NO", nullable = false, example = "")
    private String rcvNo;

    /** 전표처리 인터페이스 번호 CH-YYMM-00000 => 'CH-'||TO_CHAR(SYSDATE,'YYMM')||'-'||SUBSTRB(TO_CHAR(LPAD(IB_EXPENSE_IF_S.NEXTVAL,20,0)),-5) */
    @Schema(description = "전표처리 인터페이스 번호 CH-YYMM-00000 => 'CH-'||TO_CHAR(SYSDATE,'YYMM')||'-'||SUBSTRB(TO_CHAR(LPAD(IB_EXPENSE_IF_S.NEXTVAL,20,0)),-5)", nullable = false, example = "")
    private String ifDocNo;

    /** Y=아이템에 의한 자동 설정, N=매뉴얼 선택 */
    @Schema(description = "Y=아이템에 의한 자동 설정, N=매뉴얼 선택", nullable = false, example = "")
    private String taxAutoFlag;

    /** 세금계산서 역발행 여부 */
    @Schema(description = "세금계산서 역발행 여부", nullable = false, example = "")
    private String invSign;

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
    
    /** 고객사명 */
    @Schema(description = "고객사명", nullable = false, example = "")
    private String companyName;

    /** 고객사명 */
    @Schema(description = "고객사명", nullable = false, example = "")
    private String businessplaceName;

    /** 물류센터명 */
    @Schema(description = "물류센터명", nullable = false, example = "")
    private String dcname;

    /** 지불방법명 */
    @Schema(description = "지불방법명", nullable = false, example = "")
    private String paymentMethodName;

    /** 지불조건명 */
    @Schema(description = "지불조건명", nullable = false, example = "")
    private String paymentTermName;

    /** COSTCT_NM */
    @Schema(description = "COSTCT_NM", nullable = false, example = "")
    private String costctNm;

    /** PURCHASING_GROUP_NAME */
    @Schema(description = "PURCHASING_GROUP_NAME", nullable = false, example = "")
    private String purchasingGroupName;

    /** 트랜잭션코드(OB/FI) */
    @Schema(description = "트랜잭션코드(OB/FI)", nullable = false, example = "")
    private String transactionNm;

    /** 사업자등록번호 */
    @Schema(description = "사업자등록번호", nullable = false, example = "")
    private String cbRegisno;

    /** TAX_TAG_NM */
    @Schema(description = "TAX_TAG_NM", nullable = false, example = "")
    private String taxTagNm;
    
    /** 내부결재상태 */
    @Schema(description = "내부결재상태", nullable = false, example = "")
    private String internalApprStatus;
    
    /** Posting 상태 */
    @Schema(description = "Posting 상태", nullable = false, example = "")
    private String fiIfStatus;
    
    /** 3자물류유형코드 */
    @Schema(description = "3자물류유형코드", nullable = false, example = "")
    private String tplType;
    
    /** 년월 */
    @Schema(description = "년월", nullable = false, example = "")
    private String yyyymm;

}
