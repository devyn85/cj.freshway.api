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
 * @description : 외부창고재고모니터링 TCS 기능을 구현한 Controller Class 
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
@Schema(description = "외부창고재고모니터링 TCS res DTO")
public class KpEXstorageMonitoringTCSMasterListResDto {

	 /** 승인번호 */
    @Schema(description = "승인번호")
    private String mapkeyNo;              // MAPKEYNO

    /** PO 번호 */
    @Schema(description = "PO 번호")
    private String poKey;                 // POKEY

    /** PO 항번 */
    @Schema(description = "PO 항번")
    private String poLine;                // POLINE

    /** 납품예정일 */
    @Schema(description = "납품예정일")
    private String deliveryDate;          // DELIVERYDATE

    /** 문서일자 */
    @Schema(description = "문서일자")
    private String docDt;                 // DOCDT

    /** 문서번호 */
    @Schema(description = "문서번호")
    private String docNo;                 // DOCNO

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

    /** 거래유형 */
    @Schema(description = "거래유형")
    private String contractType;          // CONTRACTTYPE

    /** 이체여부 */
    @Schema(description = "이체여부")
    private String moveYn;                // MOVEYN

    /** 가중량여부 */
    @Schema(description = "가중량여부")
    private String tempYn;                // TEMPYN

    /** 입고중량 */
    @Schema(description = "입고중량")
    private String dpWeight;              // DPWEIGHT

    /** 입고박스수 */
    @Schema(description = "입고박스수")
    private String dpBoxQty;              // DPBOXQTY

    /** 현재고 수량 */
    @Schema(description = "현재고 수량")
    private String openQty;               // OPENQTY

    /** 현재고 박스수량 */
    @Schema(description = "현재고 박스수량")
    private String boxQty;                // BOXQTY

    /** 생성자 */
    @Schema(description = "생성자")
    private String createWho;             // CREATEWHO

    /** 글자색상 */
    @Schema(description = "글자색상")
    private String fontColor;             // FONTCOLOR

    /** 배경색상 */
    @Schema(description = "배경색상")
    private String bgColor;               // BGCOLOR
}
