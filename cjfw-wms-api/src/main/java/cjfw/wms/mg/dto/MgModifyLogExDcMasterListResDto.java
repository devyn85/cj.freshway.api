package cjfw.wms.mg.dto;


import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.07.11 
 * @description :외부비축재고변경사유현황 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.11 ParkJinWoo 생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "기둥/빈 공간 정보 조회 조건 DTO")
public class MgModifyLogExDcMasterListResDto {

	/* ───── 기본 정보 ───────────────────────────────────── */
	/** 데이터번호 */
	@Schema(description = "데이터번호", nullable = true, example = "")
	private int serialKey;

	/** 수정일자(YYYYMMDD) */
	@Schema(description = "수정일자(YYYYMMDD)", nullable = true, example = "")
	private String modifyDate;

	/** 물류센터 코드 */
	@Schema(description = "물류센터 코드", nullable = true, example = "")
	private String dcCode;

	/** 수정유형 */
	@Schema(description = "수정유형", nullable = true, example = "")
	private String modifyType;

	/** 수정유형명 */
	@Schema(description = "수정유형명", nullable = true, example = "")
	private String modifyTypeName;

	/** 고객사코드 */
	@Schema(description = "고객사코드", nullable = true, example = "")
	private String storerKey;

	/** 창고 코드 */
	@Schema(description = "창고 코드", nullable = true, example = "")
	private String organize;

	/** AREA */
	@Schema(description = "AREA", nullable = true, example = "")
	private String area;

	/* ───── 상품 ─────────────────────────────────────────── */
	/** 상품코드 */
	@Schema(description = "상품코드", nullable = true, example = "")
	private String sku;

	/** 상품명 */
	@Schema(description = "상품명", nullable = true, example = "")
	private String skuName;

	/** 시리얼관리여부(Y/N) */
	@Schema(description = "시리얼관리여부", nullable = true, example = "")
	private String serialyn;

	/** 시리얼관리여부명 */
	@Schema(description = "시리얼관리여부명", nullable = true, example = "")
	private String serialynName;

	/** 단위 */
	@Schema(description = "단위(UOM)", nullable = true, example = "")
	private String uom;

	/* ───── 수량 / 무게 ──────────────────────────────────── */
	/** 재고수량 */
	@Schema(description = "재고수량", nullable = true, example = "")
	private BigDecimal qty;

	/** 평균중량 */
	@Schema(description = "평균중량", nullable = true, example = "")
	private BigDecimal avgWeight;

	/** 환산박스 */
	@Schema(description = "환산박스", nullable = true, example = "")
	private BigDecimal calBox;

	/** 실박스예정 */
	@Schema(description = "실박스예정", nullable = true, example = "")
	private BigDecimal realOrderBox;

	/** 실박스확정 */
	@Schema(description = "실박스확정", nullable = true, example = "")
	private BigDecimal realCfmBox;

	/* ───── 출발 정보 ───────────────────────────────────── */
	/** 출발 LOC */
	@Schema(description = "출발 LOC", nullable = true, example = "")
	private String fromLoc;

	/** 출발 LOT */
	@Schema(description = "출발 LOT", nullable = true, example = "")
	private String fromLot;

	/** 출발 LOTTABLE01 */
	@Schema(description = "출발 LOTTABLE01", nullable = true, example = "")
	private String fromLottable01;

	/** 출발 바코드 */
	@Schema(description = "출발 바코드", nullable = true, example = "")
	private String fromStockId;

	/** 출발 재고유형 */
	@Schema(description = "출발 재고유형", nullable = true, example = "")
	private String fromStockType;

	/** 출발 재고등급 */
	@Schema(description = "출발 재고등급", nullable = true, example = "")
	private String fromStockGrade;

	/* ───── 도착 정보 ───────────────────────────────────── */
	/** 도착 LOC */
	@Schema(description = "도착 LOC", nullable = true, example = "")
	private String toLoc;

	/** 도착 LOT */
	@Schema(description = "도착 LOT", nullable = true, example = "")
	private String toLot;

	/** 도착 LOTTABLE01 */
	@Schema(description = "도착 LOTTABLE01", nullable = true, example = "")
	private String toLottable01;

	/** 도착 바코드 */
	@Schema(description = "도착 바코드", nullable = true, example = "")
	private String toStockId;

	/** 도착 재고유형 */
	@Schema(description = "도착 재고유형", nullable = true, example = "")
	private String toStockType;

	/** 도착 재고등급 */
	@Schema(description = "도착 재고등급", nullable = true, example = "")
	private String toStockGrade;

	/* ───── 편집 / 사유 ─────────────────────────────────── */
	/** 수정일시 */
	@Schema(description = "수정일시", nullable = true, example = "")
	private String editDate;

	/** 수정자 */
	@Schema(description = "수정자", nullable = true, example = "")
	private String editWho;

	/** 사용자명 */
	@Schema(description = "사용자명", nullable = true, example = "")
	private String userName;

	/** 사유코드 */
	@Schema(description = "사유코드", nullable = true, example = "")
	private String reasonCode;

	/** 사유메시지 */
	@Schema(description = "사유메시지", nullable = true, example = "")
	private String reasonMsg;

	/* ───── 라벨 출력용 ─────────────────────────────────── */
	/** 라벨 SKU */
	@Schema(description = "라벨 SKU", nullable = true, example = "")
	private String lblSku;

	/** 라벨 SLIPDT */
	@Schema(description = "라벨 SLIPDT", nullable = true, example = "")
	private String lblSlipDt;

	/** 라벨 상품명1 */
	@Schema(description = "라벨 상품명1", nullable = true, example = "")
	private String lblSkuName1;

	/** 라벨 상품명2 */
	@Schema(description = "라벨 상품명2", nullable = true, example = "")
	private String lblSkuName2;

	/** 라벨 거래처코드 */
	@Schema(description = "라벨 거래처코드", nullable = true, example = "")
	private String lblCustKey;

	/** 라벨 거래처명 */
	@Schema(description = "라벨 거래처명", nullable = true, example = "")
	private String lblCustName;

	/** 라벨 바코드 */
	@Schema(description = "라벨 바코드", nullable = true, example = "")
	private String lblBarcode;

	/** 라벨 LOTTABLE01 */
	@Schema(description = "라벨 LOTTABLE01", nullable = true, example = "")
	private String lblLottable01;

	/** 라벨 바코드텍스트 */
	@Schema(description = "라벨 바코드텍스트", nullable = true, example = "")
	private String lblBarcodeText;

	/** 라벨 타이틀 */
	@Schema(description = "라벨 타이틀", nullable = true, example = "")
	private String lblTitle;

	/** 인쇄여부(Y/N) */
	@Schema(description = "인쇄여부(Y/N)", nullable = true, example = "")
	private String printYn;

	/** 인쇄수량 */
	@Schema(description = "인쇄수량", nullable = true, example = "")
	private BigDecimal printedQty;
	/**
	 * FROM 시리얼번호
	 */
	@Schema(description = "FROM 시리얼번호")
	private String fromSerialNo;

	/**
	 * FROM 변환시리얼번호
	 */
	@Schema(description = "FROM 변환시리얼번호")
	private String fromConvSerialNo;

	/**
	 * TO 시리얼번호
	 */
	@Schema(description = "TO 시리얼번호")
	private String toSerialNo;

	/**
	 * TO 변환시리얼번호
	 */
	@Schema(description = "TO 변환시리얼번호")
	private String toConvSerialNo;
	
	/**
	 * 창고명
	 */
	@Schema(description = "창고명")
	private String organizeNm;

}
