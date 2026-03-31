package cjfw.wms.ms.entity;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.06.18
 * @description : 상품기준정보 UOM Entity
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.18        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Schema(description = "상품기준정보 Entity") 
public class MsUomEntity extends CommonDto {	
	@Schema(description = "일련번호 (고유 키)", example = "123456789012345")
    private BigDecimal serialKey;

    @Schema(description = "화주 키", example = "STORER001")
    private String storerKey;

    @Schema(description = "재고 품목 코드", example = "SKU0001")
    private String sku;

    @Schema(description = "기본 측정 단위", example = "EA")
    private String baseUom;

    @Schema(description = "측정 단위", example = "BOX")
    private String uom;

    @Schema(description = "분자 값", example = "10.00000")
    private BigDecimal bunja;

    @Schema(description = "분모 값", example = "1.00000")
    private BigDecimal bunmo;

    @Schema(description = "변환 값", example = "10.00000")
    private BigDecimal convertVal;

    @Schema(description = "변환 수량", example = "10")
    private BigDecimal convertQty;

    @Schema(description = "총 중량", example = "100.50000")
    private BigDecimal grossWeight;

    @Schema(description = "순 중량", example = "95.20000")
    private BigDecimal netWeight;

    @Schema(description = "부피 (CBM)", example = "1.25000")
    private BigDecimal cube;

    @Schema(description = "부피 설명", example = "1 CBM")
    private String cubeDescr;

    @Schema(description = "바코드 1", example = "12345678901234567890123456789012")
    private String barcode1;

    @Schema(description = "바코드 2")
    private String barcode2;

    @Schema(description = "바코드 3")
    private String barcode3;

    @Schema(description = "용기/선박 정보", example = "VESSEL_A")
    private String vessel;

    @Schema(description = "상태 코드", example = "00")
    private String status;

    @Schema(description = "기본 단위 여부 (Y/N)", example = "N")
    private String defaultYn;

    @Schema(description = "삭제 여부 (Y/N)", example = "N")
    private String delYn;

    @Schema(description = "트래픽 코프", example = "NORMAL")
    private String trafficcop;

    @Schema(description = "아카이브 코프", example = "N")
    private String archivecop;

    @Schema(description = "추가 일시", example = "2023-01-01T10:00:00")
    private String adddate;

    @Schema(description = "수정 일시", example = "2023-01-01T10:00:00")
    private String editdate;

    @Schema(description = "추가자", example = "SYSTEM")
    private String addwho;

    @Schema(description = "수정자", example = "SYSTEM")
    private String editwho;

    @Schema(description = "길이", example = "10.00000")
    private BigDecimal length;

    @Schema(description = "너비", example = "5.00000")
    private BigDecimal width;

    @Schema(description = "높이", example = "2.00000")
    private BigDecimal height;
}
