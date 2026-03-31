package cjfw.wms.st.dto;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : sss (kduimux@cj.net)
 * @date : 2025.08.25
 * @description : CROSS자동보충 Request DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.25 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Schema(description = "CROSS자동보충 Detail Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StMoveCrossResDetailDto extends CommonProcedureDto {
    /** 체크여부 */
    @Schema(description = "체크여부")
    private String checkyn;

    /** 출고재고유형 */
    @Schema(description = "출고재고유형")
    private String fromStocktype;

    /** 출고재고유형명 */
    @Schema(description = "출고재고유형명")
    private String stocktypenm;

    /** 출고재고등급 */
    @Schema(description = "출고재고등급")
    private String fromStockgrade;

    /** 출고재고등급명 */
    @Schema(description = "출고재고등급명")
    private String stockgradenm;

    /** 출고로케이션 */
    @Schema(description = "출고로케이션")
    private String fromLoc;

    /** 출고SKU */
    @Schema(description = "출고SKU")
    private String fromSku;

    /** 상품명 */
    @Schema(description = "상품명")
    private String skuname;

    /** 보관유형 */
    @Schema(description = "보관유형")
    private String storagetype;

    /** 보관유형명 */
    @Schema(description = "보관유형명")
    private String storagetypenm;

    /** 출고단위 */
    @Schema(description = "출고단위")
    private String fromUom;

    /** 출고수량 */
    @Schema(description = "출고수량")
    private String fromOrderqty;

    /** 할당수량 */
    @Schema(description = "할당수량")
    private String etcqty1;

    /** 피킹수량 */
    @Schema(description = "피킹수량")
    private String etcqty2;

    /** 가용수량 */
    @Schema(description = "가용수량")
    private String openqty;

    /** 입고수량 */
    @Schema(description = "입고수량")
    private String toOrderqty;

    /** 유통기한 */
    @Schema(description = "유통기한")
    private String lottable01;

    /** 유통기한/기준 */
    @Schema(description = "유통기한/기준")
    private String durationTerm;

    /** 출고센터코드 */
    @Schema(description = "출고센터코드")
    private String fromDccode;

    /** 출고화주코드 */
    @Schema(description = "출고화주코드")
    private String fromStorerkey;

    /** 출고조직 */
    @Schema(description = "출고조직")
    private String fromOrganize;

    /** 출고구역 */
    @Schema(description = "출고구역")
    private String fromArea;

    /** 출고로트 */
    @Schema(description = "출고로트")
    private String fromLot;

    /** 출고재고ID */
    @Schema(description = "출고재고ID")
    private String fromStockid;

    /** 입고센터코드 */
    @Schema(description = "입고센터코드")
    private String toDccode;

    /** 입고화주코드 */
    @Schema(description = "입고화주코드")
    private String toStorerkey;

    /** 입고조직 */
    @Schema(description = "입고조직")
    private String toOrganize;

    /** 입고구역 */
    @Schema(description = "입고구역")
    private String toArea;

    /** 입고SKU */
    @Schema(description = "입고SKU")
    private String toSku;

    /** 입고로케이션 */
    @Schema(description = "입고로케이션")
    private String toLoc;

    /** 입고로트 */
    @Schema(description = "입고로트")
    private String toLot;

    /** 입고재고ID */
    @Schema(description = "입고재고ID")
    private String toStockid;

    /** 입고재고등급 */
    @Schema(description = "입고재고등급")
    private String toStockgrade;

    /** 입고단위 */
    @Schema(description = "입고단위")
    private String toUom;

}
