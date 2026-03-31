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
 * @description : 외부창고재고모니터링 detial res DTO 기능을 구현한 Controller Class 
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
@Schema(description = "외부창고재고모니터링 detial res DTO")
public class KpEXstorageMonitoringDetailListResDto {

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

    /** 납품예정일 */
    @Schema(description = "납품예정일")
    private String deliveryDate;          // DELIVERYDATE

    /** 문서일자 */
    @Schema(description = "문서일자")
    private String docdt;                 // DOCDT

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

    /** 임시 수량 */
    @Schema(description = "임시 수량")
    private String tmpQty;                // TMP_QTY

    /** 임시 기타수량2 ※ */
    @Schema(description = "임시 기타수량2")
    private String tmpEtcQty2;            // TMP_ETCQTY2

    /** 추가 주문수량 */
    @Schema(description = "추가 주문수량")
    private String addOrderQty;           // ADD_ORDERQTY

    /** 추가 박스수 */
    @Schema(description = "추가 박스수")
    private String addBoxQty;             // ADD_BOXQTY

    /** 임시 시리얼번호 */
    @Schema(description = "임시 시리얼번호")
    private String tmpSerialNo;           // TMP_SERIALNO

    /** 임시 재고ID */
    @Schema(description = "임시 재고ID")
    private String tmpStockId;            // TMP_STOCKID

    /** 임시 LOT 정보01 ※ */
    @Schema(description = "임시 LOT 정보01")
    private String tmpLottable01;         // TMP_LOTTABLE01

    /** 임시 유통기한(일) */
    @Schema(description = "임시 유통기한(일)")
    private String tmpDurationTerm;       // TMP_DURATION_TERM

    /** 임박재고 여부(임시) */
    @Schema(description = "임박재고 여부(임시)")
    private String tmpNearDurationYn;     // TMP_NEARDURATIONYN

    /** 임시 변환시리얼번호 */
    @Schema(description = "임시 변환시리얼번호")
    private String tmpConvSerialNo;       // TMP_CONVSERIALNO

    /** 추가 시리얼번호 */
    @Schema(description = "추가 시리얼번호")
    private String addSerialNo;           // ADD_SERIALNO

    /** 추가 재고ID */
    @Schema(description = "추가 재고ID")
    private String addStockId;            // ADD_STOCKID

    /** 추가 임박재고 여부 */
    @Schema(description = "추가 임박재고 여부")
    private String addNearDurationYn;     // ADD_NEARDURATIONYN

    /** 추가 변환시리얼번호 */
    @Schema(description = "추가 변환시리얼번호")
    private String addConvSerialNo;       // ADD_CONVSERIALNO

    /** 추가 LOT 정보01 ※ */
    @Schema(description = "추가 LOT 정보01")
    private String addLottable01;         // ADD_LOTTABLE01

    /** 추가 유통기한(일) */
    @Schema(description = "추가 유통기한(일)")
    private String addDurationTerm;       // ADD_DURATION_TERM

    /** 시리얼번호 */
    @Schema(description = "시리얼번호")
    private String serialNo;              // SERIALNO

    /** 재고ID */
    @Schema(description = "재고ID")
    private String stockId;               // STOCKID

    /** 변환시리얼번호 */
    @Schema(description = "변환시리얼번호")
    private String convSerialNo;          // CONVSERIALNO

    /** 임박재고 여부 */
    @Schema(description = "임박재고 여부")
    private String nearDurationYn;        // NEARDURATIONYN

    /** LOT 정보01 ※ */
    @Schema(description = "LOT 정보01")
    private String lottable01;            // LOTTABLE01

    /** 유통기한(일) */
    @Schema(description = "유통기한(일)")
    private String durationTerm;          // DURATION_TERM

    /** 주문수량 */
    @Schema(description = "주문수량")
    private String orderQty;              // ORDERQTY

    /** 기타수량 */
    @Schema(description = "기타수량")
    private String etcQty;                // ETCQTY

    /** UOM */
    @Schema(description = "UOM")
    private String uom;                   // UOM

    /** 박스당 수량 */
    @Schema(description = "박스당 수량")
    private String qtyPerBox;             // QTYPERBOX

    /** 이체여부 */
    @Schema(description = "이체여부")
    private String moveYn;                // MOVE_YN

    /** 가중량여부 */
    @Schema(description = "가중량여부")
    private String tempYn;                // TEMP_YN

    /** 거래처코드 */
    @Schema(description = "거래처코드")
    private String fromCustKey;           // FROM_CUSTKEY

    /** 거래처명 */
    @Schema(description = "거래처명")
    private String fromCustName;          // FROM_CUSTNAME

    /** 거래유형 */
    @Schema(description = "거래유형")
    private String contractType;          // CONTRACTTYPE

    /** PAYYMD ※ */
    @Schema(description = "지급일(PAYYMD)")
    private String payymd;                // PAYYMD

    /** 입고메모 */
    @Schema(description = "입고메모")
    private String receiveMemo;           // RECEIVE_MEMO

    /** 참조문서ID */
    @Schema(description = "참조문서ID")
    private String refDocId;              // REF_DOCID

    /** 참조 거래처문서ID */
    @Schema(description = "참조 거래처문서ID")
    private String refCustDocId;          // REF_CUST_DOCID

    /** 추가 문서ID */
    @Schema(description = "추가 문서ID")
    private String addDocId;              // ADD_DOCID

    /** DURATIONS ※ */
    @Schema(description = "DURATION(※ 용어 확인)")
    private String duration;              // DURATION

    /** 반려사유 */
    @Schema(description = "반려사유")
    private String rejectReason;          // REJECT_REASON

    /** 발송메모 */
    @Schema(description = "발송메모")
    private String sendMemo;              // SEND_MEMO

    /** 처리상태 */
    @Schema(description = "처리상태")
    private String status;                // STATUS

    /** 계약회사코드 */
    @Schema(description = "계약회사코드")
    private String contractCompany;       // CONTRACTCOMPANY

    /** 계약회사명 */
    @Schema(description = "계약회사명")
    private String contractCompanyName;   // CONTRACTCOMPANYNAME

    /** 계약유형 일련번호 */
    @Schema(description = "계약유형 일련번호")
    private String contractTypeSn;        // CONTRACTTYPE_SN

    /** 취소사유(메모) */
    @Schema(description = "취소사유")
    private String cancelRmk;             // CANCELRMK

}
