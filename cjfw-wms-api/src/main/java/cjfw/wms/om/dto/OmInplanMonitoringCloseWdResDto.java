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
 * @description : 주문수신모니터링-출고마감 Res DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.09        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Schema(description = "주문수신모니터링-출고마감 조회")
public class OmInplanMonitoringCloseWdResDto {
	@Schema(description = "물류센터")
    private String dcCode;

    @Schema(description = "배송일자")
    private String deliveryDate;

    @Schema(description = "문서 유형")
    private String docType;

    @Schema(description = "IF 전송 유형 (작업 유형)")
    private String ifSendType;

    @Schema(description = "오더 유형")
    private String orderType;

    @Schema(description = "플랜트")
    private String plant;

    @Schema(description = "고객 주문 마감 유형")
    private String custOrderCloseType;

    @Schema(description = "고객 코드 (관리처)")
    private String custKey;

    @Schema(description = "오더 건수")
    private BigDecimal orderCnt;

    @Schema(description = "오더 삭제 건수")
    private BigDecimal orderDelCnt;

    @Schema(description = "오더 마감 건수 (OMS FLAG='Y')")
    private BigDecimal orderOmsflagCnt;

    @Schema(description = "오더 오픈 건수 (OMS FLAG!='Y')")
    private BigDecimal orderOpenCnt;

    @Schema(description = "주문 수량")
    private BigDecimal orderQty;

    @Schema(description = "주문 삭제 수량")
    private BigDecimal orderDelQty;

    @Schema(description = "주문 마감 수량 (OMS FLAG='Y')")
    private BigDecimal orderOmsflagQty;

    @Schema(description = "주문 확정 수량 (STORERCONFIRMQTY)")
    private BigDecimal orderOpenQty;

    @Schema(description = "WMS 마감 상태")
    private String wmsCloseChk;

    @Schema(description = "FS 마감 비교")
    private String fsCntChk;

    @Schema(description = "FS 마감 비교 (율)")
    private BigDecimal fsCntRate;

    @Schema(description = "체크 여부")
    private String checkYn;
}
