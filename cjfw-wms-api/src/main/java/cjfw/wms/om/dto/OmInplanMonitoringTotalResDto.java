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
 * @description : 주문수신모니터링-WMS진행현황 Res DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.09        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Schema(description = "주문수신모니터링-WMS진행현황 조회")
public class OmInplanMonitoringTotalResDto {
    @Schema(description = "물류센터 코드", example = "DC001")
    private String dcCode;

    @Schema(description = "문서 유형", example = "DN")
    private String docType;

    @Schema(description = "배송일자", example = "20240709")
    private String deliveryDate;

    @Schema(description = "인터페이스 전송 타입")
    private String ifSendType;

    @Schema(description = "플랜트 코드")
    private String plant;

    @Schema(description = "최초 건수")
    private BigDecimal storerOrderCnt;

    @Schema(description = "최초 수량")
    private BigDecimal storerOrderQty;

    @Schema(description = "최종 건수")
    private BigDecimal storerOpenCnt;

    @Schema(description = "최종 수량")
    private BigDecimal storerOpenQty;

    @Schema(description = "실적 건수")
    private BigDecimal storerConfirmCnt;

    @Schema(description = "실적 수량")
    private BigDecimal storerConfirmQty;

    @Schema(description = "예정 건수")
    private BigDecimal orderCnt;

    @Schema(description = "예정 수량")
    private BigDecimal orderQty;

    @Schema(description = "삭제 건수")
    private BigDecimal orderDelCnt;

    @Schema(description = "삭제 수량")
    private BigDecimal orderDelQty;

    @Schema(description = "마감 안된 건수")
    private BigDecimal orderOmsflagNCnt;

    @Schema(description = "마감 안된 수량")
    private BigDecimal orderOmsflagNQty;

    @Schema(description = "마감 건수")
    private BigDecimal orderOmsflagCnt;

    @Schema(description = "마감 수량")
    private BigDecimal orderOmsflagQty;

    @Schema(description = "지시 건수")
    private BigDecimal processCnt;

    @Schema(description = "지시 수량")
    private BigDecimal processQty;

    @Schema(description = "작업 건수")
    private BigDecimal workCnt;

    @Schema(description = "작업 수량")
    private BigDecimal workQty;

    @Schema(description = "확정 건수")
    private BigDecimal confirmCnt;

    @Schema(description = "확정 수량")
    private BigDecimal confirmQty;

    @Schema(description = "전송 건수")
    private BigDecimal orderIfFlagCnt;

    @Schema(description = "전송 수량")
    private BigDecimal orderIfFlagQty;

    @Schema(description = "상차 건수")
    private BigDecimal inspectCnt;

    @Schema(description = "상차 수량")
    private BigDecimal inspectQty;

    @Schema(description = "무게")
    private BigDecimal weight;
}
