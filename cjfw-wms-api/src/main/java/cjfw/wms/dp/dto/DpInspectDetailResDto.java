package cjfw.wms.dp.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.05.22
 * @description : 입고검수처리 조회 기능을 구현한 DTO Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.05.22        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Schema(description = "입고검수처리 DETAIL 목록 결과")
public class DpInspectDetailResDto {
	@Schema(description = "상품 코드", example = "SKU001")
    private String sku;

    @Schema(description = "상품명")
    private String skuName;

    @Schema(description = "채널")
    private String channel;

    @Schema(description = "채널명")
    private String channelName;

    @Schema(description = "주문 수량")
    private BigDecimal orderQty;

    @Schema(description = "검수 수량")
    private BigDecimal inspectQty;

    @Schema(description = "부족 수량")
    private BigDecimal shortageQty;

    @Schema(description = "고객사 UOM")
    private String storerUom;

    @Schema(description = "상태 코드")
    private String status;

    @Schema(description = "상태명")
    private String statusName;

    @Schema(description = "DAILY_TO_CUSTKEY")
    private String dailyToCustKey;

    @Schema(description = "DAILY_TO_CUSTNAME")
    private String dailyToCustName;

    @Schema(description = "박스당 수량")
    private BigDecimal qtyPerBox;

    @Schema(description = "플랜트 코드")
    private String plant;

    @Schema(description = "플랜트 설명")
    private String plantDescr;
    	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
