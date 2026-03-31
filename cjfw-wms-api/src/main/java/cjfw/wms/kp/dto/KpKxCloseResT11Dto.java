package cjfw.wms.kp.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiSooKim (jskim14@cj.net)
 * @date : 2025.10.02
 * @description : KP KX 월별 상품 수불 확인 응답 DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "KP KX 월별 상품 수불 확인 응답")
public class KpKxCloseResT11Dto extends CommonDto {
    /** 납품일자 */
    @Schema(description = "납품일자", example = "202510")
    private String deliveryDate;

    /** 조직 */
    @Schema(description = "조직", example = "1000-0001")
    private String organize;

    /** SKU */
    @Schema(description = "SKU", example = "SKU001")
    private String sku;

    /** KX 수량 */
    @JsonAlias({"KX_QTY"})
    @Schema(description = "KX 수량")
    private Integer kxQty;

    /** FW 수량 */
    @Schema(description = "FW 수량")
    private Integer fwQty;

    /** 데이터 구분 */
    @Schema(description = "데이터 구분", example = "KX")
    private String dataDiv;

    /** 플랜트 */
    @Schema(description = "플랜트", example = "CODE1")
    private String plant;

    /** 저장 위치 */
    @Schema(description = "저장 위치", example = "CODE3")
    private String storageLoc;

    /** KX DC 코드 */
    @Schema(description = "KX DC 코드", example = "KXDCCODE")
    private String kxDcCode;

    /** KX DC 명 */
    @Schema(description = "KX DC 명", example = "KXDCNAME")
    private String kxDcName;

    /** 재고 유형 */
    @Schema(description = "재고 유형", example = "GOODBADTYPE")
    private String stockType;

    /** 합격 수량 */
    @Schema(description = "합격 수량")
    private Integer passQty;

    /** 입고 수량 */
    @Schema(description = "입고 수량")
    private Integer receiptQty;

    /** 주문 수량 */
    @Schema(description = "주문 수량")
    private Integer orderQty;

    /** 재고 수량 */
    @Schema(description = "재고 수량")
    private Integer stockQty;

    /** 조정 수량 */
    @Schema(description = "조정 수량")
    private Integer adjustQty;

    /** 이동 수량 */
    @Schema(description = "이동 수량")
    private Integer transferQty;

    /** 파손 수량 */
    @Schema(description = "파손 수량")
    private Integer damageQty;


}