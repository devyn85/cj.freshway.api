package cjfw.wms.mg.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : sss (kduimux@cj.net)
 * @date : 2025.07.31
 * @description : 재고변경사유현황 Master Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.31 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Schema(description = "재고변경사유현황 Master Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MgModifyLogResDto {
    /** 시리얼키 */
    @Schema(description = "시리얼키")
    private String serialkey;

    /** 수정일자 */
    @Schema(description = "수정일자")
    private String modifydate;

    /** 센터코드 */
    @Schema(description = "센터코드")
    private String dccode;

    /** 수정유형 */
    @Schema(description = "수정유형")
    private String modifytype;

    /** 수정유형명 */
    @Schema(description = "수정유형명")
    private String modifytypename;

    /** 화주코드 */
    @Schema(description = "화주코드")
    private String storerkey;

    /** 조직 */
    @Schema(description = "조직")
    private String organize;

    /** 시리얼여부 */
    @Schema(description = "시리얼여부")
    private String serialyn;

    /** 시리얼여부명 */
    @Schema(description = "시리얼여부명")
    private String serialynname;

    /** 구역 */
    @Schema(description = "구역")
    private String area;

    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;

    /** 상품명 */
    @Schema(description = "상품명")
    private String skuname;

    /** 단위 */
    @Schema(description = "단위")
    private String uom;

    /** 수량 */
    @Schema(description = "수량")
    private BigDecimal qty;

    /** 평균중량 */
    @Schema(description = "평균중량")
    private BigDecimal avgweight;

    /** 박스환산 */
    @Schema(description = "박스환산")
    private BigDecimal calbox;

    /** 실주문박스 */
    @Schema(description = "실주문박스")
    private BigDecimal realorderbox;

    /** 실확정박스 */
    @Schema(description = "실확정박스")
    private BigDecimal realcfmbox;

    /** 출발로케이션 */
    @Schema(description = "출발로케이션")
    private String fromLoc;

    /** 출발로트 */
    @Schema(description = "출발로트")
    private String fromLot;

    /** 출발유통기한 */
    @Schema(description = "출발유통기한")
    private String fromLottable01;

    /** 출발재고ID */
    @Schema(description = "출발재고ID")
    private String fromStockid;

    /** 출발재고유형 */
    @Schema(description = "출발재고유형")
    private String fromStocktype;

    /** 출발재고등급 */
    @Schema(description = "출발재고등급")
    private String fromStockgrade;

    /** 도착로케이션 */
    @Schema(description = "도착로케이션")
    private String toLoc;

    /** 도착로트 */
    @Schema(description = "도착로트")
    private String toLot;

    /** 도착유통기한 */
    @Schema(description = "도착유통기한")
    private String toLottable01;

    /** 도착재고ID */
    @Schema(description = "도착재고ID")
    private String toStockid;

    /** 도착재고유형 */
    @Schema(description = "도착재고유형")
    private String toStocktype;

    /** 도착재고등급 */
    @Schema(description = "도착재고등급")
    private String toStockgrade;

    /** 등록일자 */
    @Schema(description = "등록일자")
    private String editdate;

    /** 등록자 */
    @Schema(description = "등록자")
    private String editwho;

    /** 사용자명 */
    @Schema(description = "사용자명")
    private String username;

    /** 사유코드 */
    @Schema(description = "사유코드")
    private String reasoncode;

    /** 사유메시지 */
    @Schema(description = "사유메시지")
    private String reasonmsg;

    /** 라벨상품코드 */
    @Schema(description = "라벨상품코드")
    private String lblSku;

    /** 라벨입고일자 */
    @Schema(description = "라벨입고일자")
    private String lblSlipdt;

    /** 라벨상품명1 */
    @Schema(description = "라벨상품명1")
    private String lblSkuname1;

    /** 라벨상품명2 */
    @Schema(description = "라벨상품명2")
    private String lblSkuname2;

    /** 라벨거래처코드 */
    @Schema(description = "라벨거래처코드")
    private String lblCustkey;

    /** 라벨거래처명 */
    @Schema(description = "라벨거래처명")
    private String lblCustname;

    /** 라벨바코드 */
    @Schema(description = "라벨바코드")
    private String lblBarcode;

    /** 라벨유통기한 */
    @Schema(description = "라벨유통기한")
    private String lblLottable01;

    /** 라벨바코드텍스트 */
    @Schema(description = "라벨바코드텍스트")
    private String lblBarcodetext;

    /** 라벨타이틀 */
    @Schema(description = "라벨타이틀")
    private String lblTitle;

    /** 출력여부 */
    @Schema(description = "출력여부")
    private String printyn;

    /** 출력수량 */
    @Schema(description = "출력수량")
    private BigDecimal printedqty;
}
