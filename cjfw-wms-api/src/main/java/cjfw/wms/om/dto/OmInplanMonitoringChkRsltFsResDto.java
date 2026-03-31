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
 * @description : 주문수신모니터링-영업실적차이 Res DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.09        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Schema(description = "주문수신모니터링-영업실적차이 조회")
public class OmInplanMonitoringChkRsltFsResDto {
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

    @Schema(description = "FS 확정 무게 (수량)", example = "123.45")
    private BigDecimal cfmWf;

    @Schema(description = "FS 확정 수량", example = "100")
    private BigDecimal cfmQf;

    @Schema(description = "FS 취소 수량", example = "10")
    private BigDecimal cnlQf;

    @Schema(description = "FS 확정 건수", example = "5")
    private BigDecimal cfmCf;

    @Schema(description = "WMS 확정 무게 (수량)", example = "0") // 쿼리에 0으로 고정된 값이므로 BigDecimal
    private BigDecimal cfmWs;

    @Schema(description = "WMS 확정 수량", example = "50")
    private BigDecimal cfmQs;

    @Schema(description = "WMS 취소 수량", example = "5")
    private BigDecimal cnlQs;

    @Schema(description = "WMS 확정 건수", example = "0") // 쿼리에 0으로 고정된 값이므로 BigDecimal
    private BigDecimal cfmCs;
    
    @Schema(description = "문서 번호")
    private String docNo;

    @Schema(description = "문서 라인")
    private String docLine;

    @Schema(description = "상품 코드")
    private String sku;
    
    @Schema(description = "문서 일자 (DOCDT)")
    private String docDt;
}
