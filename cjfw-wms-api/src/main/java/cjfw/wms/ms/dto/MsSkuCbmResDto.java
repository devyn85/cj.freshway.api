package cjfw.wms.ms.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.06.11
 * @description : CBM 조회 결과 DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.11        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Builder
@Schema(description = "상품정보 조회 결과 DTO")
public class MsSkuCbmResDto {
	@Schema(description = "테이블 시리얼 번호")
    private String serialKey;

    @Schema(description = "고객사 코드", example = "FW00")
    private String storerKey;

    @Schema(description = "상품 코드", example = "103055")
    private String sku;

    @Schema(description = "기본 UOM")
    private String baseUom;

    @Schema(description = "BUNJA UOM 코드")
    private String uom;

    @Schema(description = "BUNMO UOM에 내입된 수량")
    private BigDecimal bunja;

    @Schema(description = "BASE UOM에 내입된 수량 (대부분 1)")
    private BigDecimal bunmo;

    @Schema(description = "BUNJA/BUNMO 자동 계산 값")
    private BigDecimal convertVal; // 계산된 값이므로 Double

    @Schema(description = "UOM이 가지고 있는 낱개 수량")
    private BigDecimal convertQty;

    @Schema(description = "총 중량 (Kg)")
    private BigDecimal grossWeight;

    @Schema(description = "실 중량 (Kg)")
    private BigDecimal netWeight;

    @Schema(description = "상품 체적 (cm^3, 1CBM=1000000)")
    private BigDecimal cube;

    @Schema(description = "상품 체적 설명 (길이-너비-높이, 단위 mm)")
    private String cubeDescr;

    @Schema(description = "UOM에 해당하는 바코드1")
    private String barcode1;

    @Schema(description = "UOM에 해당하는 바코드2")
    private String barcode2;

    @Schema(description = "UOM에 해당하는 바코드3")
    private String barcode3;

    @Schema(description = "포장 용기 코드")
    private String vessel;

    @Schema(description = "상태")
    private String status;

    @Schema(description = "기본값 여부")
    private String defaultYn;

    @Schema(description = "삭제 여부")
    private String delYn;

    @Schema(description = "데이터 흐름 제어")
    private String trafficCop;

    @Schema(description = "아카이브 제어")
    private String archiveCop;

    @Schema(description = "최초 등록 시간", example = "YYYYMMDDHH24MISS")
    private String addDate;

    @Schema(description = "최종 변경 시간", example = "YYYYMMDDHH24MISS")
    private String editDate;

    @Schema(description = "최초 등록자")
    private String addWho;

    @Schema(description = "최종 변경자")
    private String editWho;

    @Schema(description = "상품 체적 설명 (길이)")
    private BigDecimal length;

    @Schema(description = "상품 체적 설명 (너비)")
    private BigDecimal width;

    @Schema(description = "상품 체적 설명 (높이)")
    private BigDecimal height;
}
