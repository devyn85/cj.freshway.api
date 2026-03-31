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
public class KpEXstorageMonitoringDetailTcsListResDto {


	/** 승인번호 */
    @Schema(description = "승인번호")
    private String mapkeyNo;              // MAPKEY_NO

    /** PO 번호 */
    @Schema(description = "PO 번호")
    private String poKey;                 // POKEY

    /** PO 항번 */
    @Schema(description = "PO 항번")
    private String poLine;                // POLINE

    /** 납품예정일 */
    @Schema(description = "납품예정일")
    private String deliveryDate;          // DELIVERYDATE

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

    /** 재고ID */
    @Schema(description = "재고ID")
    private String stockId;               // STOCKID

    /** 변환시리얼번호 */
    @Schema(description = "변환시리얼번호")
    private String convSerialNo;          // CONVSERIALNO

    /** 주문수량 */
    @Schema(description = "주문수량")
    private String orderQty;              // ORDERQTY

    /** UOM */
    @Schema(description = "UOM")
    private String uom;                   // UOM

    /** 기타수량 */
    @Schema(description = "기타수량")
    private String etcQty;                // ETCQTY

    /** 박스당 수량 */
    @Schema(description = "박스당 수량")
    private String qtyPerBox;             // QTYPERBOX

}
