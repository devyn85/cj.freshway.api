package cjfw.wms.kp.dto;


import java.math.BigDecimal;

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
public class KpEXstorageMonitoringLocalDetailListResDto {
	/** 승인번호 */
    @Schema(description = "승인번호")
    private String mapkeyNo;              // MAPKEY_NO

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

    /** 시리얼정보 확정여부 */
    @Schema(description = "시리얼정보 확정여부")
    private String serialinfoCfmYn;       // SERIALINFO_CFM_YN

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

    /** 거래처코드 */
    @Schema(description = "거래처코드")
    private String fromCustKey;           // FROM_CUSTKEY

    /** 거래처명 */
    @Schema(description = "거래처명")
    private String fromCustName;          // FROM_CUSTNAME

    /** 창고코드 */
    @Schema(description = "창고코드")
    private String organize;              // ORGANIZE

    /** 창고명 */
    @Schema(description = "창고명")
    private String organizeName;          // ORGANIZENAME

    /** 주문수량 */
    @Schema(description = "주문수량")
    private String orderQty;              // ORDERQTY

    /** 기준 UOM */
    @Schema(description = "기준 UOM")
    private String uom;                   // UOM

    /** 기타수량 */
    @Schema(description = "기타수량")
    private String etcQty;                // ETCQTY

    /** 입고박스수 */
    @Schema(description = "입고박스수")
    private BigDecimal dpBoxQty;              // DPBOXQTY

    /** 현재고 수량 */
    @Schema(description = "현재고 수량")
    private BigDecimal qty;                   // QTY

    /** 출고중량 */
    @Schema(description = "출고중량")
    private BigDecimal wdQty;                 // WDQTY

    /** 판매 UOM */
    @Schema(description = "판매 UOM")
    private String salesUom;              // SALESUOM

    /** 박스당 수량 */
    @Schema(description = "박스당 수량")
    private BigDecimal qtyPerBox;             // QTYPERBOX

    /** SKU2 ※ */
    @Schema(description = "SKU2")
    private String sku2;                  // SKU2

    /** 수량 합계 */
    @Schema(description = "수량 합계")
    private BigDecimal qtySum;                // QTY_SUM

    /** ISSUE NO */
    @Schema(description = "ISSUE NO")
    private String issueNo;               // ISSUE_NO

    /** 현재고 박스수량 */
    @Schema(description = "현재고 박스수량")
    private BigDecimal boxQty;                // BOXQTY

    /** UOM2 ※ */
    @Schema(description = "UOM2")
    private String uom2;                  // UOM2

    /** 추가자 */
    @Schema(description = "추가자")
    private String addWho;                // ADDWHO

    /** 수정자 */
    @Schema(description = "수정자")
    private String editWho;               // EDITWHO

    /** 생성자 */
    @Schema(description = "생성자")
    private String createWho;             // CREATEWHO

    /** 등록자 */
    @Schema(description = "등록자")
    private String regWho;                // REGWHO

    /** 등록일자 */
    @Schema(description = "등록일자")
    private String regDate;               // REGDATE

    /** 최종수정자 */
    @Schema(description = "최종수정자")
    private String lastWho;               // LASTWHO

    /** 최종수정일 */
    @Schema(description = "최종수정일")
    private String lastDate;              // LASTDATE

    /** 글자색상 */
    @Schema(description = "글자색상")
    private String fontColor;             // FONTCOLOR

    /** 배경색상 */
    @Schema(description = "배경색상")
    private String bgColor;               // BGCOLOR

    /** 처리상태 */
    @Schema(description = "처리상태")
    private String status;                // STATUS

    /** 이체여부 */
    @Schema(description = "이체여부")
    private String moveYn;                // MOVE_YN

    /** 가중량여부 */
    @Schema(description = "가중량여부")
    private String tempYn;                // TEMP_YN

    /** 거래유형 */
    @Schema(description = "거래유형")
    private String contractType;          // CONTRACTTYPE

    /** 지급일자(PAYYMD) */
    @Schema(description = "지급일자")
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

    /** 참조 추가문서ID ※ */
    @Schema(description = "참조 추가문서ID")
    private String refAddDocId;           // REF_ADD_DOCID

    /** 변환시리얼번호 */
    @Schema(description = "변환시리얼번호")
    private String convSerialNo;          // CONVSERIALNO

    /** 추가 변환시리얼번호 */
    @Schema(description = "추가 변환시리얼번호")
    private String addConvSerialNo;       // ADD_CONVSERIALNO

    /** 시리얼번호 */
    @Schema(description = "시리얼번호")
    private String serialNo;              // SERIALNO

    /** 추가 시리얼번호 */
    @Schema(description = "추가 시리얼번호")
    private String addSerialNo;           // ADD_SERIALNO

    /** 재고ID */
    @Schema(description = "재고ID")
    private String stockId;               // STOCKID

    /** 거래처코드2 ※ */
    @Schema(description = "거래처코드2")
    private String fromCustKey2;          // FROM_CUSTKEY2

    /** 거래처명2 ※ */
    @Schema(description = "거래처명2")
    private String fromCustName2;         // FROM_CUSTNAME2

    /** 유통기한(일) */
    @Schema(description = "유통기한(일)")
    private String duration;              // DURATION

    /** 추가 유통기한(일) */
    @Schema(description = "추가 유통기한(일)")
    private String addDuration;           // ADD_DURATION

    /** 임박재고 여부 */
    @Schema(description = "임박재고 여부")
    private String nearDurationYn;        // NEARDURATIONYN

    /** 추가 임박재고 여부 */
    @Schema(description = "추가 임박재고 여부")
    private String addNearDurationYn;     // ADD_NEARDURATIONYN

    /** LOT 정보01 ※ */
    @Schema(description = "LOT 정보01")
    private String lottable01;            // LOTTABLE01

    /** 추가 LOT 정보01 ※ */
    @Schema(description = "추가 LOT 정보01")
    private String addLottable01;         // ADD_LOTTABLE01

    /** 유통기한(일) Term */
    @Schema(description = "유통기한(일) Term")
    private String durationTerm;          // DURATION_TERM

    /** 반려사유 */
    @Schema(description = "반려사유")
    private String rejectReason;          // REJECT_REASON

    /** 발송메모 */
    @Schema(description = "발송메모")
    private String sendMemo;              // SEND_MEMO

    /** 박스당 수량2 ※ */
    @Schema(description = "박스당 수량2")
    private BigDecimal qtyPerBox2;            // QTYPERBOX2

    /** 계약회사코드 */
    @Schema(description = "계약회사코드")
    private String contractCompany;       // CONTRACTCOMPANY

    /** 계약회사명 */
    @Schema(description = "계약회사명")
    private String contractCompanyName;   // CONTRACTCOMPANYNAME

    /** 추가 재고ID */
    @Schema(description = "추가 재고ID")
    private String addStockId;            // ADD_STOCKID

    /** UOM3 ※ */
    @Schema(description = "UOM3")
    private String uom3;                  // UOM3

    /** 추가 박스수 */
    @Schema(description = "추가 박스수")
    private BigDecimal addBoxQty;             // ADD_BOXQTY

    /** 추가 주문수량 */
    @Schema(description = "추가 주문수량")
    private BigDecimal addOrderQty;           // ADD_ORDERQTY

    /** 추가 유통기한(일) Term */
    @Schema(description = "추가 유통기한(일) Term")
    private String addDurationTerm;       // ADD_DURATION_TERM
}
