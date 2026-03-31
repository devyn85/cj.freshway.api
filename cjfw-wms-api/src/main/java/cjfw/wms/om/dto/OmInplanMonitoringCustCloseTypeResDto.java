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
 * @description : 주문수신모니터링-경로별마감 Res DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.09        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Schema(description = "주문수신모니터링-경로별마감 조회")
public class OmInplanMonitoringCustCloseTypeResDto {
	@Schema(description = "물류센터 코드", example = "2600")
    private String dcCode;

    @Schema(description = "센터명")
    private String dcName;

    @Schema(description = "배송일자", example = "20250709")
    private String deliveryDate;

    @Schema(description = "마감 유형 코드", example = "STD")
    private String closeCode;

    @Schema(description = "마감 유형 시간")
    private String closeTime;

    @Schema(description = "오더 건수")
    private BigDecimal ordCs;

    @Schema(description = "삭제 건수")
    private BigDecimal delCs;

    @Schema(description = "마감 건수")
    private BigDecimal cloCs;

    @Schema(description = "오픈 건수")
    private BigDecimal opnCs;

    @Schema(description = "주문 수량")
    private BigDecimal ordQs;

    @Schema(description = "삭제 수량")
    private BigDecimal delQs;

    @Schema(description = "마감 수량")
    private BigDecimal cloQs;

    @Schema(description = "오픈 수량")
    private BigDecimal opnQs;

    @Schema(description = "WMS 마감 상태", example = "완료/진행중")
    private String wmsCloseChk;

    @Schema(description = "FS 마감 비교율")
    private BigDecimal wmsCloseRate;

    @Schema(description = "버튼 활성화 플래그 (인터페이스 마감 여부)", example = "Y/N")
    private String btnFlag;
    
    @Schema(description = "수신처 고객 키")
    private String toCustKey;

    @Schema(description = "수신처 고객명")
    private String toCustName;

    @Schema(description = "문서 번호")
    private String docNo;

    @Schema(description = "문서 라인")
    private BigDecimal docLine;

    @Schema(description = "상품 코드")
    private String sku;

    @Schema(description = "상품명")
    private String skuName;

    @Schema(description = "업체별 오픈 수량")
    private BigDecimal storerOpenQty;
}
