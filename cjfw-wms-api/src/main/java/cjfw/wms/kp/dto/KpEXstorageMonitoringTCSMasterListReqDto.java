package cjfw.wms.kp.dto;


import cjfw.wms.common.extend.CommonDto;
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
@Schema(description = "외부창고재고모니터링 TCS req DTO")
public class KpEXstorageMonitoringTCSMasterListReqDto extends CommonDto {
	/** 센터코드 */
    @Schema(description = "센터코드(DCCODE)", example = "2170")
    private String dcCode;                // DCCODE   사용하지 않으면 삭제 OK

    /** 보관업체 */
    @Schema(description = "보관업체(STORERKEY)", example = "FW00")
    private String storerKey;             // STORERKEY   사용하지 않으면 삭제 OK


    /* ────────── 날짜 조건 ────────── */

    /** 입고일자(시작) */
    @Schema(description = "입고일자 From (YYYYMMDD)", example = "20250701")
    private String slipDtFrom;            // SLIPDT_FROM

    /** 입고일자(종료) */
    @Schema(description = "입고일자 To (YYYYMMDD)", example = "20250724")
    private String slipDtTo;              // SLIPDT_TO


    /* ────────── 다중(콤마) 파라미터 ────────── */

//    /** PO 번호 다중 선택 */
//    @Schema(description = "POKEY 다중 선택(콤마 구분)", example = "PO001,PO002")
//    private String poKey;                 // POKEY_MULTI

    /** 창고코드 다중 선택 */
    @Schema(description = "ORGANIZE 다중 선택(콤마 구분)", example = "A001,B001")
    private String organize;              // ORGANIZE_MULTI

    /** 거래처코드 다중 선택 */
    @Schema(description = "CUSTKEY 다중 선택(콤마 구분)", example = "C001,C002")
    private String fromCustKey;           // FROMCUSTKEY_MULTI

    /** SKU 다중 선택 */
    @Schema(description = "SKU 다중 선택", example = "[\"SKU001\",\"SKU002\"]")
    private String sku;                   // SKU_MULTI


    /* ────────── 단건 조건 ────────── */

    /** 승인번호 */
    @Schema(description = "승인번호")
    private String mapkeyno;              // MAPKEYNO

    /** PO 번호(단건) */
    @Schema(description = "PO 번호")
    private String pokey;                 // POKEY

    /** 문서번호 */
    @Schema(description = "문서번호")
    private String docno;                 // DOCNO

    /** 재고 ID */
    @Schema(description = "재고ID")
    private String stockId;               // STOCKID

    /** B/L 번호 */
    @Schema(description = "B/L 번호")
    private String blNo;                  // BLNO

    /** 시리얼번호 */
    @Schema(description = "시리얼번호")
    private String serialNo;              // SERIALNO

    /** 상품대분류 */
    @Schema(description = "상품대분류")
    private String skul;                 // SKUL

    /** 진행상태 */
    @Schema(description = "진행상태(MAP_DIV)")
    private String mapDiv;               // MAPDIV
    
    
}
