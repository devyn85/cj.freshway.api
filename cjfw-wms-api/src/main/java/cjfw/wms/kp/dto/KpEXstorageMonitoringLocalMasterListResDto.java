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
 * @description : 외부창고재고모니터링 Local 기능을 구현한 Controller Class 
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
@Schema(description = "외부창고재고모니터링 Local res DTO")
public class KpEXstorageMonitoringLocalMasterListResDto {

	 /** 승인번호 */
    @Schema(description = "승인번호")
    private String mapkeyNo;              // MAPKEYNO

    /** ISSUE NO */
    @Schema(description = "ISSUE NO")
    private String issueNo;               // ISSUENO

    /** 입고중량 */
    @Schema(description = "입고중량")
    private String dpWeight;              // DPWEIGHT

    /** 입고박스수 */
    @Schema(description = "입고박스수")
    private String dpBoxQty;              // DPBOXQTY

    /** 출고중량 */
    @Schema(description = "출고중량")
    private BigDecimal wdQty;                 // WDQTY

    /** 출고박스수 */
    @Schema(description = "출고박스수")
    private BigDecimal wdBoxQty;              // WDBOXQTY

    /** 현재고 수량 */
    @Schema(description = "현재고 수량")
    private BigDecimal qty;                   // QTY

    /** 현재고 박스수량 */
    @Schema(description = "현재고 박스수량")
    private BigDecimal boxQty;                // BOXQTY

    /** 조정중량 */
    @Schema(description = "조정중량")
    private BigDecimal adQty;                 // ADQTY

    /** 조정 박스수 */
    @Schema(description = "조정 박스수")
    private String adBoxQty;              // ADBOXQTY

    /** PO 번호 */
    @Schema(description = "PO 번호")
    private String pokey;                 // POKEY

    /** PO 항번 */
    @Schema(description = "PO 항번")
    private String poLine;                // POLINE

    /** DPKEY */
    @Schema(description = "DPKEY")
    private String dpkey;                 // DPKEY

    /** 시리얼정보 확정여부 */
    @Schema(description = "시리얼정보 확정여부")
    private String serialinfoCfmYn;       // SERIALINFO_CFM_YN

    /** 납품예정일 */
    @Schema(description = "납품예정일")
    private String deliveryDate;          // DELIVERYDATE

    /** 문서일자 */
    @Schema(description = "문서일자")
    private String docDt;                 // DOCDT

    /** 거래처코드 */
    @Schema(description = "거래처코드")
    private String fromCustKey;           // FROMCUSTKEY

    /** 거래처명 */
    @Schema(description = "거래처명")
    private String fromCustName;          // FROMCUSTNAME

    /** 창고코드 */
    @Schema(description = "창고코드")
    private String organize;              // ORGANIZE

    /** 창고명 */
    @Schema(description = "창고명")
    private String organizeName;          // ORGANIZENAME

    /** BASE UOM */
    @Schema(description = "기준 단위(BASE UOM)")
    private String baseUom;               // BASEUOM

    /** SALES UOM */
    @Schema(description = "판매 단위(SALES UOM)")
    private String salesUom;              // SALESUOM

    /** 박스당 수량 */
    @Schema(description = "박스당 수량")
    private BigDecimal qtyPerBox;             // QTYPERBOX

    /** 변환 시리얼번호 */
    @Schema(description = "변환 시리얼번호")
    private String convSerialNo;          // CONVSERIALNO

    /** SKU */
    @Schema(description = "SKU")
    private String sku;                   // SKU

    /** UOM */
    @Schema(description = "UOM")
    private String uom;                   // UOM

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

    /** 거래유형 */
    @Schema(description = "거래유형")
    private String contractType;          // CONTRACTTYPE

    /** 이체여부 */
    @Schema(description = "이체여부")
    private String moveYn;                // MOVEYN

    /** 가중량여부 */
    @Schema(description = "가중량여부")
    private String tempYn;                // TEMPYN
}
