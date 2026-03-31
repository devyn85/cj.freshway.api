package cjfw.wms.kp.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.07.14 
 * @description : 외부창고재고모니터링 local detial res DTO 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.14 ParkJinWoo 생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "외부창고재고모니터링 local detial res DTO")
public class KpEXstorageMonitoringDetailSubListResDto {
	 /** 승인번호 */
    @Schema(description = "승인번호")
    private String mapkeyNo;              // MAPKEY_NO

    /** 승인라인 */
    @Schema(description = "승인라인")
    private String mapkeyLine;            // MAPKEY_LINE

    /** ISSUE NO */
    @Schema(description = "ISSUE NO")
    private String issueNo;               // ISSUE_NO

    /** PO 번호 */
    @Schema(description = "PO 번호")
    private String poKey;                 // POKEY

    /** PO 항번 */
    @Schema(description = "PO 항번")
    private String poLine;                // POLINE

    /** 추가 PO 번호 */
    @Schema(description = "추가 PO 번호")
    private String addPoKey;              // ADDPOKEY

    /** 추가 PO 항번 */
    @Schema(description = "추가 PO 항번")
    private String addPoLine;             // ADDPOLINE


    /* ────────── SO(판매주문) 매핑 ────────── */

    /** 임시 SO 번호 */
    @Schema(description = "임시 SO 번호")
    private String tmpSoKey;              // TMP_SOKEY

    /** 임시 SO 라인 */
    @Schema(description = "임시 SO 라인")
    private String tmpSoLine;             // TMP_SOLINE

    /** SO 번호 */
    @Schema(description = "SO 번호")
    private String soKey;                 // SOKEY

    /** SO 라인 */
    @Schema(description = "SO 라인")
    private String soLine;                // SOLINE

    /** 추가 SO 번호 */
    @Schema(description = "추가 SO 번호")
    private String adSoKey;               // AD_SOKEY

    /** 추가 SO 라인 */
    @Schema(description = "추가 SO 라인")
    private String adSoLine;              // AD_SOLINE


    /* ────────── 주요 날짜/키 정보 ────────── */

    /** 납품예정일 */
    @Schema(description = "납품예정일")
    private String deliveryDate;          // DELIVERYDATE

    /** 문서일자 */
    @Schema(description = "문서일자")
    private String docdt;                 // DOCDT

    /** 가확정납품번호 */
    @Schema(description = "가확정납품번호")
    private String tmpDpKey;              // TMP_DPKEY

    /** 확정납품번호 */
    @Schema(description = "확정납품번호")
    private String dpKey;                 // DPKEY


    /* ────────── 상품 정보 ────────── */

    /** SKU */
    @Schema(description = "SKU")
    private String sku;                   // SKU

    /** 상품명 */
    @Schema(description = "상품명")
    private String skuName;               // SKUNAME

    /** 창고유형명 ※ */
    @Schema(description = "창고유형명")
    private String storageTypeName;       // STORAGETYPENAME

    /** 조직(ORG) ※ */
    @Schema(description = "조직(ORG)")
    private String org;                   // ORG


    /* ────────── 수량·박스 정보 ────────── */

    @Schema(description = "임시 수량")
    private String tmpQty;                // TMP_QTY

    @Schema(description = "임시 기타수량2")
    private String tmpEtcQty2;            // TMP_ETCQTY2

    @Schema(description = "추가 주문수량")
    private String addOrderQty;           // ADD_ORDERQTY

    @Schema(description = "추가 박스수")
    private String addBoxQty;             // ADD_BOXQTY


    /* ────────── 시리얼/LOT/유통기한 정보 ────────── */

    @Schema(description = "임시 시리얼번호")
    private String tmpSerialNo;           // TMP_SERIALNO

    @Schema(description = "임시 재고ID")
    private String tmpStockId;            // TMP_STOCKID

    @Schema(description = "임시 LOT 정보01")
    private String tmpLottable01;         // TMP_LOTTABLE01

    @Schema(description = "임시 유통기한(일)")
    private String tmpDurationTerm;       // TMP_DURATION_TERM

    @Schema(description = "임박재고 여부(임시)")
    private String tmpNearDurationYn;     // TMP_NEARDURATIONYN

    @Schema(description = "임시 변환시리얼번호")
    private String tmpConvSerialNo;       // TMP_CONVSERIALNO

    @Schema(description = "추가 시리얼번호 (오타 유지)")
    private String addSerialNo;           // ADD_SEIALNO

    @Schema(description = "추가 재고ID")
    private String addStockId;            // ADD_STOCKID

    @Schema(description = "추가 임박재고 여부")
    private String adNearDurationYn;      // AD_NEARDURATIONYN

    @Schema(description = "추가 변환시리얼번호")
    private String addConvSerialNo;       // ADD_CONVSERIALNO

    @Schema(description = "추가 LOT 정보01")
    private String adLottable01;          // AD_LOTTABLE01

    @Schema(description = "추가 유통기한(일)")
    private String adDurationTerm;        // AD_DURATION_TERM

    @Schema(description = "시리얼번호")
    private String serialNo;              // SERIALNO

    @Schema(description = "재고ID")
    private String stockId;               // STOCKID

    @Schema(description = "변환시리얼번호")
    private String convSerialNo;          // CONVSERIALNO

    @Schema(description = "임박재고 여부")
    private String nearDurationYn;        // NEARDURATIONYN

    @Schema(description = "LOT 정보01")
    private String lottable01;            // LOTTABLE01

    @Schema(description = "유통기한(일)")
    private String durationTerm;          // DURATION_TERM


    /* ────────── 수량/단위 정보 ────────── */

    @Schema(description = "주문수량")
    private String orderQty;              // ORDERQTY

    @Schema(description = "기타수량")
    private String etcQty;                // ETCQTY

    @Schema(description = "UOM")
    private String uom;                   // UOM

    @Schema(description = "박스당 수량")
    private String qtyPerBox;             // QTYPERBOX


    /* ────────── 상태 및 플래그 ────────── */

    @Schema(description = "이체여부(Y/N)")
    private String moveYn;                // MOVE_YN

    @Schema(description = "가중량여부(Y/N)")
    private String tempYn;                // TEMP_YN

    @Schema(description = "처리상태")
    private String status;                // STATUS


    /* ────────── 거래처/계약 정보 ────────── */

    @Schema(description = "거래처코드")
    private String fromCustKey;           // FROM_CUSTKEY

    @Schema(description = "거래처명")
    private String fromCustName;          // FROM_CUSTNAME

    @Schema(description = "거래유형")
    private String contractType;          // CONTRACTTYPE

    @Schema(description = "지급일자")
    private String payymd;                // PAYYMD

    @Schema(description = "계약회사코드")
    private String contractCompany;       // CONTRACTCOMPANY

    @Schema(description = "계약회사명")
    private String contractCompanyName;   // CONTRACTCOMPANYNAME

    @Schema(description = "계약유형 SN")
    private String contractTypeSn;        // CONTRACTTYPE_SN


    /* ────────── 문서/메모 정보 ────────── */

    @Schema(description = "입고메모")
    private String receiveMemo;           // RECEIVE_MEMO

    @Schema(description = "참조 문서ID")
    private String refDocId;              // REF_DOCID

    @Schema(description = "참조 거래처문서ID")
    private String refCustDocId;          // REF_CUST_DOCID

    @Schema(description = "추가 문서ID")
    private String addDocId;              // ADD_DOCID

    @Schema(description = "반려사유")
    private String rejectReason;          // REJECT_REASON

    @Schema(description = "발송메모")
    private String sendMemo;              // SEND_MEMO

    @Schema(description = "취소사유")
    private String cancelRmk;             // CANCELRMK


    /* ────────── FAX 전송 이력 ────────── */

    @Schema(description = "FAX 전송결과")
    private String faxLastTrRslt;         // FAX_LAST_TR_RSLT

    @Schema(description = "FAX 전송일시")
    private String faxLastTrSenddate;     // FAX_LAST_TR_SENDDATE

    @Schema(description = "FAX 발송자명")
    private String faxLastTrSendname;     // FAX_LAST_TR_SENDNAME


    /* ────────── 문서 번호/라인 ────────── */

    @Schema(description = "문서번호")
    private String docNo;                 // DOCNO

    @Schema(description = "문서라인")
    private String docLine;               // DOCLINE
}
