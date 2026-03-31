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
public class KpEXstorageMonitoringLocalDetailSubListResDto {

	/** 승인번호 */
    @Schema(description = "승인번호")
    private String mapkeyNo;              // MAPKEY_NO

    /** ISSUE NO */
    @Schema(description = "ISSUE NO")
    private String issueNo;               // ISSUE_NO

    /** 승인라인 */
    @Schema(description = "승인라인")
    private String mapkeyLine;            // MAPKEY_LINE


    /* ────── SO(판매주문) 정보 ────── */

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


    /* ────── 추가 시리얼/재고 정보 ────── */

    /** 추가 시리얼번호 (ADD_SEIALNO) */
    @Schema(description = "추가 시리얼번호")
    private String addSerialNo;           // ADD_SEIALNO

    /** 추가 재고ID */
    @Schema(description = "추가 재고ID")
    private String addStockId;            // ADD_STOCKID

    /** 추가 변환시리얼번호 */
    @Schema(description = "추가 변환시리얼번호")
    private String addConvSerialNo;       // ADD_CONVSERIALNO

    /** 시리얼정보 확정여부 */
    @Schema(description = "시리얼정보 확정여부")
    private String serialinfoCfmYn;       // SERIALINFO_CFM_YN

    /** 추가 유통기한(일) */
    @Schema(description = "추가 유통기한(일)")
    private String addDuration;           // ADD_DURATION

    /** 추가 임박재고 여부 */
    @Schema(description = "추가 임박재고 여부")
    private String adNearDurationYn;      // AD_NEARDURATIONYN

    /** 추가 LOT 정보01 */
    @Schema(description = "추가 LOT 정보01")
    private String adLottable01;          // AD_LOTTABLE01

    /** 추가 유통기한 Term */
    @Schema(description = "추가 유통기한 Term")
    private String adDurationTerm;        // AD_DURATION_TERM


    /* ────── 추가 수량 정보 ────── */

    /** 추가 주문수량 */
    @Schema(description = "추가 주문수량")
    private String addOrderQty;           // ADD_ORDERQTY

    /** 추가 박스수 */
    @Schema(description = "추가 박스수")
    private String addBoxQty;             // ADD_BOXQTY


    /* ────── 상태/플래그 ────── */

    /** 처리상태 */
    @Schema(description = "처리상태")
    private String status;                // STATUS


    /* ────── PO 정보 ────── */

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


    /* ────── 기본 수량/단위 ────── */

    /** 주문수량 */
    @Schema(description = "주문수량")
    private String orderQty;              // ORDERQTY

    /** UOM */
    @Schema(description = "UOM")
    private String uom;                   // UOM

    /** 기타수량 */
    @Schema(description = "기타수량")
    private String etcQty;                // ETCQTY


    /* ────── 상품/창고 정보 ────── */

    /** SKU */
    @Schema(description = "SKU")
    private String sku;                   // SKU

    /** 상품명 */
    @Schema(description = "상품명")
    private String skuName;               // SKUNAME

    /** 창고유형명 */
    @Schema(description = "창고유형명")
    private String storageTypeName;       // STORAGETYPENAME

    /** 조직(ORG) */
    @Schema(description = "조직(ORG)")
    private String org;                   // ORG


    /* ────── 거래처/계약/창고 ────── */

    /** 거래처코드 */
    @Schema(description = "거래처코드")
    private String fromCustKey;           // FROM_CUSTKEY

    /** 거래처명 */
    @Schema(description = "거래처명")
    private String fromCustName;          // FROM_CUSTNAME

    /** 계약유형 */
    @Schema(description = "계약유형")
    private String contractType;          // CONTRACTTYPE

    /** 창고코드 */
    @Schema(description = "창고코드")
    private String organize;              // ORGANIZE

    /** 창고명 */
    @Schema(description = "창고명")
    private String organizeName;          // ORGANIZENAME


    /* ────── 재고/시리얼 기본 ────── */

    /** 시리얼번호 */
    @Schema(description = "시리얼번호")
    private String serialNo;              // SERIALNO

    /** 재고ID */
    @Schema(description = "재고ID")
    private String stockId;               // STOCKID

    /** 변환시리얼번호 */
    @Schema(description = "변환시리얼번호")
    private String convSerialNo;          // CONVSERIALNO

    /** 유통기한(일) */
    @Schema(description = "유통기한(일)")
    private String duration;              // DURATION

    /** 임박재고 여부 */
    @Schema(description = "임박재고 여부")
    private String nearDurationYn;        // NEARDURATIONYN

    /** LOT 정보01 */
    @Schema(description = "LOT 정보01")
    private String lottable01;            // LOTTABLE01

    /** 유통기한 Term */
    @Schema(description = "유통기한 Term")
    private String durationTerm;          // DURATION_TERM


    /* ────── 기타 수량/단위 ────── */

    /** 기타수량(ORDR_QTY) */
    @Schema(description = "기타수량(ORDR_QTY)")
    private String etcQty2;               // ETCQTY (ORDR_QTY)

    /** 박스당 수량 */
    @Schema(description = "박스당 수량")
    private String qtyPerBox;             // QTYPERBOX


    /* ────── 플래그 ────── */

    /** 이체여부(Y/N) */
    @Schema(description = "이체여부")
    private String moveYn;                // MOVE_YN

    /** 가중량여부(Y/N) */
    @Schema(description = "가중량여부")
    private String tempYn;                // TEMP_YN


    /* ────── 메모/사유 ────── */

    /** 반려사유 */
    @Schema(description = "반려사유")
    private String rejectReason;          // REJECT_REASON

    /** 발송메모 */
    @Schema(description = "발송메모")
    private String sendMemo;              // SEND_MEMO
}
