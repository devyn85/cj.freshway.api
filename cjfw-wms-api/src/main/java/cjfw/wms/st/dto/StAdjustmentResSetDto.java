package cjfw.wms.st.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : sss (kduimux@cj.net)
 * @date : 2025.08.25
 * @description : 센터자체감모 Request DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.25 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Schema(description = "센터자체감모 Detail Response DTO")
@Data
public class StAdjustmentResSetDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";


	/** 체크여부 */
	@Schema(description = "체크여부")
	private String checkyn;

	/** 문서일자 */
	@Schema(description = "문서일자")
	private String docdt;

	/** 문서라인번호 */
	@Schema(description = "문서라인번호")
	private BigDecimal docline;

	/** 전표일자 */
	@Schema(description = "전표일자")
	private String slipdt;

	/** 전표번호 */
	@Schema(description = "전표번호")
	private String slipno;

	/** 전표라인번호 */
	@Schema(description = "전표라인번호")
	private BigDecimal slipline;

	/** 입고전표일자 */
	@Schema(description = "입고전표일자")
	private String stSlipdt;

	/** 입고전표번호 */
	@Schema(description = "입고전표번호")
	private String stSlipno;

	/** 입고전표라인번호 */
	@Schema(description = "입고전표라인번호")
	private BigDecimal stSlipline;

	/** 진행상태 */
	@Schema(description = "진행상태")
	private String status;

	/** 진행상태명 */
	@Schema(description = "진행상태명")
	private String statusname;

	/** 협력사명 */
	@Schema(description = "협력사명")
	private String custname;

	/** 상품코드 */
	@Schema(description = "상품코드")
	private String sku;

	/** 상품명칭 */
	@Schema(description = "상품명칭")
	private String skuname;

	/** 라벨출력여부 */
	@Schema(description = "라벨출력여부")
	private String printyn;

	/** 출력수량 */
	@Schema(description = "출력수량")
	private BigDecimal printedqty;

	/** 박스입수량 */
	@Schema(description = "박스입수량")
	private BigDecimal qtyperbox;

	/** 라벨표시입수량 */
	@Schema(description = "라벨표시입수량")
	private String lblQtyperbox;

	/** 단위 */
	@Schema(description = "단위")
	private String uom;

	/** 주문수량 */
	@Schema(description = "주문수량")
	private BigDecimal orderqty;

	/** 검수수량 */
	@Schema(description = "검수수량")
	private BigDecimal inspectqty;

	/** LOT */
	@Schema(description = "LOT")
	private String lot;

	/** LOT테이블01 */
	@Schema(description = "LOT테이블01")
	private String lottable01;

	/** 유통기한(라벨표시) */
	@Schema(description = "유통기한(라벨표시)")
	private String durationTerm;

	/** 라벨용상품코드 */
	@Schema(description = "라벨용상품코드")
	private String lblSku;

	/** 라벨원산지 */
	@Schema(description = "라벨원산지")
	private String lblPlaceoforigin;

	/** 라벨전표일자 */
	@Schema(description = "라벨전표일자")
	private String lblSlipdt;

	/** 라벨상품명칭1 */
	@Schema(description = "라벨상품명칭1")
	private String lblSkuname1;

	/** 라벨상품명칭2 */
	@Schema(description = "라벨상품명칭2")
	private String lblSkuname2;

	/** 라벨협력사코드 */
	@Schema(description = "라벨협력사코드")
	private String lblCustkey;

	/** 라벨협력사명 */
	@Schema(description = "라벨협력사명")
	private String lblCustname;

	/** 라벨바코드 */
	@Schema(description = "라벨바코드")
	private String lblBarcode;

	/** 라벨LOT테이블01 */
	@Schema(description = "라벨LOT테이블01")
	private String lblLottable01;

	/** 라벨바코드텍스트 */
	@Schema(description = "라벨바코드텍스트")
	private String lblBarcodetext;

	/** 라벨타이틀 */
	@Schema(description = "라벨타이틀")
	private String lblTitle;

	/** 기준일(제조) */
	@Schema(description = "기준일(제조)")
	private String durationBegin;

	/** 기준일(소비) */
	@Schema(description = "기준일(소비)")
	private String durationEnd;

	@Schema(description = "")
	private String convBoxqty;

}
