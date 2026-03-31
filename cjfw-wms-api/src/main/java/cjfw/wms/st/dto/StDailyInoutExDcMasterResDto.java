package cjfw.wms.st.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.07.10 
 * @description : 외부비축상품별수불현황 기능 resDTO
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.10 ParkJinWoo 생성
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StDailyInoutExDcMasterResDto {
	/* ───── 기본 정보 ───────────────────────────────────── */
	/** SKU 코드 */
	@Schema(description = "SKU 코드", nullable = true, example = "")
	private String sku;

	/** SKU 코드 (추가 식별용) */
	@Schema(description = "SKU 코드", nullable = true, example = "")
	private String skuNm;

	/** 기본 단위(EA, PACK, KG 등) */
	@Schema(description = "기본 단위 (EA, PACK, KG 등)", nullable = true, example = "")
	private String uom;

	/** Box당 수량 */
	@Schema(description = "Box당 수량", nullable = true, example = "")
	private double qtyPerBox;

	/** SKU 명 */
	@Schema(description = "SKU 명", nullable = true, example = "")
	private String skuName;

	/* ───── 재고 수량 ───────────────────────────────────── */
	/** 기초 재고 수량 */
	@Schema(description = "기초 재고 수량", nullable = true, example = "")
	private BigDecimal baseQty;

	/** 현재 재고 수량 */
	@Schema(description = "재고 기준 재고수량", nullable = true, example = "")
	private BigDecimal stockQty;

	/** 기말 재고 수량 */
	@Schema(description = "기말 재고 수량", nullable = true, example = "")
	private BigDecimal endQty;

	/** 기말 재고(박스 표시) */
	@Schema(description = "기말 재고 수량(박스 표기)", nullable = true, example = "")
	private String endBoxDisp;

	/* ───── 입고(STO) ──────────────────────────────────── */
	/** 입고(STO) 수량 */
	@Schema(description = "입고(STO) 수량", nullable = true, example = "")
	private double dpStoQty;

	/** 입고(STO) 박스표기 */
	@Schema(description = "입고(STO) 박스표기", nullable = true, example = "")
	private String dpStoBoxDisp;

	/** 입고(STO) 박스 */
	@Schema(description = "입고(STO) 박스", nullable = true, example = "")
	private double dpStoBox;

	/* ───── 입고(PO) ──────────────────────────────────── */
	/** 입고(PO) 수량 */
	@Schema(description = "입고(PO) 수량", nullable = true, example = "")
	private BigDecimal dpPoQty;

	/** 입고(PO) 박스표기 */
	@Schema(description = "입고(PO) 박스표기", nullable = true, example = "")
	private String dpPoBoxDisp;

	/** 입고(PO) 박스 */
	@Schema(description = "입고(PO) 박스", nullable = true, example = "")
	private double dpPoBox;

	/* ───── 역감모 ─────────────────────────────────────── */
	/** 역감모 수량 */
	@Schema(description = "역감모 수량", nullable = true, example = "")
	private BigDecimal dpAjDcQty;

	/** 역감모 박스표기 */
	@Schema(description = "역감모 박스표기", nullable = true, example = "")
	private String dpAjDcBoxDisp;

	/** 역감모 박스 */
	@Schema(description = "역감모 박스", nullable = true, example = "")
	private double dpAjDcBox;

	/* ───── 반품(RT) ──────────────────────────────────── */
	/** 반품 수량 */
	@Schema(description = "반품(RT) 수량", nullable = true, example = "")
	private BigDecimal rtQty;

	/** 반품 박스표기 */
	@Schema(description = "반품(RT) 박스표기", nullable = true, example = "")
	private String rtBoxDisp;

	/** 반품 박스 */
	@Schema(description = "반품(RT) 박스", nullable = true, example = "")
	private double rtBox;

	/* ───── 출고(STO) ─────────────────────────────────── */
	/** 출고(STO) 수량 */
	@Schema(description = "출고(STO) 수량", nullable = true, example = "")
	private BigDecimal wdStoQty;

	/** 출고(STO) 박스표기 */
	@Schema(description = "출고(STO) 박스표기", nullable = true, example = "")
	private String wdStoBoxDisp;

	/** 출고(STO) 박스 */
	@Schema(description = "출고(STO) 박스", nullable = true, example = "")
	private double wdStoBox;

	/* ───── 출고(SO) ──────────────────────────────────── */
	/** 출고(SO) 수량 */
	@Schema(description = "출고(SO) 수량", nullable = true, example = "")
	private BigDecimal wdSoQty;

	/** 출고(SO) 박스표기 */
	@Schema(description = "출고(SO) 박스표기", nullable = true, example = "")
	private String wdSoBoxDisp;

	/** 출고(SO) 박스 */
	@Schema(description = "출고(SO) 박스", nullable = true, example = "")
	private double wdSoBox;

	/* ───── 출고(PO) 협력사 반품 ───────────────────────── */
	/** 출고(PO) 수량 */
	@Schema(description = "출고(PO) 수량", nullable = true, example = "")
	private BigDecimal wdPoQty;

	/** 출고(PO) 박스표기 */
	@Schema(description = "출고(PO) 박스표기", nullable = true, example = "")
	private String wdPoBoxDisp;

	/** 출고(PO) 박스 */
	@Schema(description = "출고(PO) 박스", nullable = true, example = "")
	private double wdPoBox;

	/* ───── 감모 ──────────────────────────────────────── */
	/** 감모 수량 */
	@Schema(description = "감모 수량", nullable = true, example = "")
	private BigDecimal wdAjDcQty;

	/** 감모 박스표기 */
	@Schema(description = "감모 박스표기", nullable = true, example = "")
	private String wdAjDcBoxDisp;

	/** 감모 박스 */
	@Schema(description = "감모 박스", nullable = true, example = "")
	private double wdAjDcBox;

	/* ───── 폐기 ──────────────────────────────────────── */
	/** 폐기 수량 */
	@Schema(description = "폐기 수량", nullable = true, example = "")
	private BigDecimal wdAjDuQty;

	/** 폐기 박스표기 */
	@Schema(description = "폐기 박스표기", nullable = true, example = "")
	private String wdAjDuBoxDisp;

	/** 폐기 박스 */
	@Schema(description = "폐기 박스", nullable = true, example = "")
	private double wdAjDuBox;

	/* ───── 총계 및 입·출고계 ─────────────────────────── */
	/** 총 수량 */
	@Schema(description = "총 수량", nullable = true, example = "")
	private BigDecimal totalQty;

	/** 총 박스 */
	@Schema(description = "총 박스", nullable = true, example = "")
	private double totalBox;

	/** 입고 총 수량 */
	@Schema(description = "입고 총 수량", nullable = true, example = "")
	private BigDecimal inTotalQty;

	/** 입고 총 박스 */
	@Schema(description = "입고 총 박스", nullable = true, example = "")
	private double inTotalBox;

	/** 출고 총 수량 */
	@Schema(description = "출고 총 수량", nullable = true, example = "")
	private BigDecimal outTotalQty;

	/** 출고 총 박스 */
	@Schema(description = "출고 총 박스", nullable = true, example = "")
	private double outTotalBox;

	/** 입고 총 박스표기 */
	@Schema(description = "입고 총 박스표기", nullable = true, example = "")
	private String inTotalBoxDisp;

	/** 출고 총 박스표기 */
	@Schema(description = "출고 총 박스표기", nullable = true, example = "")
	private String outTotalBoxDisp;

	/* ───── 기타 정보 ─────────────────────────────────── */
	/** 변환 시리얼 */
	@Schema(description = "변환 시리얼", nullable = true, example = "")
	private String convSerialNo;

	/** 창고 코드 */
	@Schema(description = "창고 코드", nullable = true, example = "")
	private String organize;

	/** 창고 명 */
	@Schema(description = "창고 명", nullable = true, example = "")
	private String organizeNm;
		/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	 /**매각_수량*/
    @Schema(description = "매각_수량")
    private java.math.BigDecimal wdAjSaQty;

    /**매각_박스표기*/
    @Schema(description = "매각_박스표기")
    private String wdAjSaBoxDisp;

    /**매각_박스*/
    @Schema(description = "매각_박스")
    private java.math.BigDecimal wdAjSaBox;
	
}
