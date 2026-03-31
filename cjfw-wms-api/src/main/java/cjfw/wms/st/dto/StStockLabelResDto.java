package cjfw.wms.st.dto;

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
 * @description : 재고라벨출력 Master Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.31 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Schema(description = "재고라벨출력 Master Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StStockLabelResDto {

    /** 센터코드 */
    @Schema(description = "센터코드")
    private String dccode;

    /** 조직 */
    @Schema(description = "조직")
    private String organize;

    /** 재고유형 */
    @Schema(description = "재고유형")
    private String stocktype;

    /** 등급 */
    @Schema(description = "등급")
    private String stockgrade;

    /** 존 */
    @Schema(description = "존")
    private String zone;

    /** 로케이션 */
    @Schema(description = "로케이션")
    private String loc;

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

    /** 가용수량 */
    @Schema(description = "가용수량")
    private BigDecimal openqty;

    /** 할당수량 */
    @Schema(description = "할당수량")
    private BigDecimal qtyallocated;

    /** 피킹수량 */
    @Schema(description = "피킹수량")
    private BigDecimal qtypicked;

    /** 유통기한임박여부 */
    @Schema(description = "유통기한임박여부")
    private String neardurationyn;

    /** LOT정보 */
    @Schema(description = "LOT정보")
    private String lottable01;

    /** 유통기한/기준 */
    @Schema(description = "유통기한/기준")
    private String durationTerm;

    /** 시리얼번호 */
    @Schema(description = "시리얼번호")
    private String serialno;

    /** 보관유형 */
    @Schema(description = "보관유형")
    private String storagetype;

    /** 유통기한 */
    @Schema(description = "유통기한")
    private String duration;

    /** 유통기한타입 */
    @Schema(description = "유통기한타입")
    private String durationtype;

    /** BOX수량 */
    @Schema(description = "BOX수량")
    private BigDecimal qty1;

    /** 낱개수량 */
    @Schema(description = "낱개수량")
    private BigDecimal qty2;

    /** BOX단위 */
    @Schema(description = "BOX단위")
    private String uom1;

    /** 낱개단위 */
    @Schema(description = "낱개단위")
    private String uom2;

    /** 라벨용 상품코드 */
    @Schema(description = "라벨용 상품코드")
    private String lblSku;

    /** 라벨용 입고일 */
    @Schema(description = "라벨용 입고일")
    private String lblSlipdt;

    /** 라벨용 상품명1 */
    @Schema(description = "라벨용 상품명1")
    private String lblSkuname1;

    /** 라벨용 상품명2 */
    @Schema(description = "라벨용 상품명2")
    private String lblSkuname2;

    /** 라벨용 고객코드 */
    @Schema(description = "라벨용 고객코드")
    private String lblCustkey;

    /** 라벨용 고객명 */
    @Schema(description = "라벨용 고객명")
    private String lblCustname;

    /** 라벨용 고객명2 */
    @Schema(description = "라벨용 고객명2")
    private String lblCustname2;

    /** 라벨용 바코드 */
    @Schema(description = "라벨용 바코드")
    private String lblBarcode;

    /** 라벨용 재고ID */
    @Schema(description = "라벨용 재고ID")
    private String lblStockid;

    /** 라벨용 시리얼번호 */
    @Schema(description = "라벨용 시리얼번호")
    private String lblSerialno;

    /** 라벨용 중량 */
    @Schema(description = "라벨용 중량")
    private String lblWeight;

    /** 라벨용 도축일자 */
    @Schema(description = "라벨용 도축일자")
    private String lblConvertlot;

    /** 라벨용 LOT정보 */
    @Schema(description = "라벨용 LOT정보")
    private String lblLottable01;

    /** 라벨용 바코드텍스트 */
    @Schema(description = "라벨용 바코드텍스트")
    private String lblBarcodetext;

    /** 라벨용 타이틀 */
    @Schema(description = "라벨용 타이틀")
    private String lblTitle;

    /** 출력여부 */
    @Schema(description = "출력여부")
    private String printyn;

    /** 출력수량 */
    @Schema(description = "출력수량")
    private BigDecimal printedqty;

    /** 시리얼여부 */
    @Schema(description = "시리얼여부")
    private String serialyn;

    /** 라벨용 박스당수량 */
    @Schema(description = "라벨용 박스당수량")
    private String lblQtyperbox;

    /** 라벨용 원산지 */
    @Schema(description = "라벨용 원산지")
    private String lblPlaceoforigin;
    
    /** 제조일자 */
    @Schema(description = "제조일자")
    private String manufacturedt;    
    
    /** 소비일자 */
    @Schema(description = "소비일자")
    private String expiredt;       
    
    /** 소비기한잔여율 */
    @Schema(description = "소비기한잔여율")
    private BigDecimal usebydatefreert;  
    
    /** 플래그 */
    @Schema(description = "플래그")
    private String flag;

    /** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
    /** 입고예정수량 */
    @Schema(description = "입고예정수량")
    private BigDecimal qtyexpected;
}
