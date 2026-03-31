package cjfw.wms.om.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.07.09
 * @description : 주문수신모니터링-영업오더차이 Res DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.09        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Schema(description = "주문수신모니터링-영업오더차이 조회")
public class OmInplanMonitoringChkFsResDto {
	@Schema(description = "물류센터 코드")
    private String dcCode;

    @Schema(description = "문서 유형")
    private String docType;

    @Schema(description = "배송일자", example = "20250709")
    private String deliveryDate;

    @Schema(description = "대상 고객사 코드")
    private String toCustKey;

    @Schema(description = "플랜트")
    private String plant;

    @Schema(description = "마감 유형 코드")
    private String closeCode;

    @Schema(description = "마감 시간")
    private String closeTime;

    @Schema(description = "FS 오더 건수")
    private BigDecimal ordCf;

    @Schema(description = "FS 삭제 건수")
    private BigDecimal delCf;

    @Schema(description = "FS 마감 건수")
    private BigDecimal cloCf;

    @Schema(description = "FS 오픈 건수")
    private BigDecimal opnCf;

    @Schema(description = "FS 오더 수량")
    private BigDecimal ordQf;

    @Schema(description = "FS 삭제 수량")
    private BigDecimal delQf;

    @Schema(description = "FS 마감 수량")
    private BigDecimal cloQf;

    @Schema(description = "WMS 오더 건수")
    private BigDecimal ordCs;

    @Schema(description = "WMS 삭제 건수")
    private BigDecimal delCs;

    @Schema(description = "WMS 마감 건수")
    private BigDecimal cloCs;

    @Schema(description = "WMS 오픈 건수")
    private BigDecimal opnCs;

    @Schema(description = "WMS 오더 수량")
    private BigDecimal ordQs;

    @Schema(description = "WMS 삭제 수량")
    private BigDecimal delQs;

    @Schema(description = "WMS 마감 수량")
    private BigDecimal cloQs;
    
    @Schema(description = "문서 번호")
    private String docNo;

    @Schema(description = "문서 라인")
    private String docLine;

    @Schema(description = "상품 코드")
    private String sku;
    
    @Schema(description = "문서 일자 (DOCDT)")
    private String docDt;
}
