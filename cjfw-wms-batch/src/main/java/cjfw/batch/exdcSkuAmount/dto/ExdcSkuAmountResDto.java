package cjfw.batch.exdcSkuAmount.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.07.01 
 * @description : 외부비축재고조회resDto 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.01 ParkJinWoo 생성
 */
@Data
@Schema(description = "외부비축재고조회resDto")
public class ExdcSkuAmountResDto {
	/** 선택 여부(0/1) */
	@Schema(description = "선택 여부(0/1)", nullable = true, example = "")
	private String checkYn;

	/** 물류센터 코드 */
	@Schema(description = "물류센터 코드", nullable = true, example = "")
	private String dcCode;

	/** 창고 코드 */
	@Schema(description = "창고(조직) 코드", nullable = true, example = "")
	private String organize;

	/** 상품그룹 */
	@Schema(description = "상품그룹", nullable = true, example = "")
	private String skuGroup;

	/** 창고명 */
	@Schema(description = "창고(조직) 명", nullable = true, example = "")
	private String organizeName;

	/** 재고 유형 */
	@Schema(description = "재고 유형", nullable = true, example = "")
	private String stockType;

	/** 재고 등급 */
	@Schema(description = "재고 등급", nullable = true, example = "")
	private String stockGrade;

	/** ZONE */
	@Schema(description = "ZONE", nullable = true, example = "")
	private String zone;

	/** LOC */
	@Schema(description = "LOC", nullable = true, example = "")
	private String loc;

	/** SKU */
	@Schema(description = "SKU", nullable = true, example = "")
	private String sku;

	/** SKU 명 */
	@Schema(description = "SKU 명", nullable = true, example = "")
	private String skuname;

	/** 단위(UOM) */
	@Schema(description = "UOM", nullable = true, example = "")
	private String uom;

	/** 수량 */
	@Schema(description = "수량", nullable = true, example = "")
	private BigDecimal qty;

	/** 오픈 수량 */
	@Schema(description = "오픈 수량", nullable = true, example = "")
	private BigDecimal openQty;

	/** 할당 수량 */
	@Schema(description = "할당 수량", nullable = true, example = "")
	private BigDecimal qtyAllocated;

	/** 피킹 수량 */
	@Schema(description = "피킹 수량", nullable = true, example = "")
	private BigDecimal qtyPicked;

	/** 유통기한 임박 여부(Y/N) */
	@Schema(description = "유통기한 임박 여부(Y/N)", nullable = true, example = "")
	private String nearDurationYn;

	/** LOT Table01 */
	@Schema(description = "LOT Table01", nullable = true, example = "")
	private String lotTable01;

	/** 유통기한 경과/총기간 */
	@Schema(description = "유통기한 경과/총기간", nullable = true, example = "")
	private String durationTerm;

	/** 시리얼 번호 */
	@Schema(description = "시리얼 번호", nullable = true, example = "")
	private String serialNo;

	/** 보관 방식 */
	@Schema(description = "보관 방식", nullable = true, example = "")
	private String storageType;

	/** 유통기한 */
	@Schema(description = "유통기한", nullable = true, example = "")
	private String duration;

	/** 유통기한 단위 */
	@Schema(description = "유통기한 단위", nullable = true, example = "")
	private String durationType;

	/** 박스 입수 */
	@Schema(description = "박스 입수", nullable = true, example = "")
	private String qtyPerBox;

	/** 재고 상태 */
	@Schema(description = "재고 상태", nullable = true, example = "")
	private String stockStatus;

	/** SAP 단가 */
	@Schema(description = "sap단가", nullable = true, example = "")
	private String sapPrice;

	/** 수량(BOX) 정수부 */
	@Schema(description = "수량 환산(QTY1)", nullable = true, example = "")
	private BigDecimal qty1;

	/** 수량(BOX) 나머지 */
	@Schema(description = "수량 환산(QTY2)", nullable = true, example = "")
	private BigDecimal qty2;

	/** 단위1 */
	@Schema(description = "UOM1", nullable = true, example = "")
	private String uom1;

	/** 단위2 */
	@Schema(description = "UOM2", nullable = true, example = "")
	private String uom2;

	/** 평균 중량 */
	@Schema(description = "평균 중량", nullable = true, example = "")
	private BigDecimal avgWeight;

	/** 환산 박스 */
	@Schema(description = "환산 박스", nullable = true, example = "")
	private BigDecimal calBox;

	/** 실제 박스수 */
	@Schema(description = "실제 박스수", nullable = true, example = "")
	private BigDecimal realBox;

	/** 실박스 예정(2170) */
	@Schema(description = "실박스 예정", nullable = true, example = "")
	private BigDecimal realOrderBox;

	/** 실박스 확정(2170) */
	@Schema(description = "실박스 확정", nullable = true, example = "")
	private BigDecimal realCfmBox;

	/** BOX 플래그(2170) */
	@Schema(description = "BOX 플래그", nullable = true, example = "")
	private String boxFlag;

	/** 실중량 여부 */
	@Schema(description = "실중량 여부", nullable = true, example = "")
	private String realYn;

	/** 공장명 */
	@Schema(description = "공장명", nullable = true, example = "")
	private String factoryName;

	/** 변환 시리얼 */
	@Schema(description = "변환 시리얼", nullable = true, example = "")
	private String convSerialNo;

	/** 도축 일자 */
	@Schema(description = "도축 일자", nullable = true, example = "")
	private String butcheryDt;

	/** 계약사 코드 */
	@Schema(description = "계약사 코드", nullable = true, example = "")
	private String contractCompany;

	/** 계약사 명 */
	@Schema(description = "계약사 명", nullable = true, example = "")
	private String contractCompanyName;

	/** 계약사 담당자명 */
	@Schema(description = "계약사 담당자명", nullable = true, example = "")
	private String contractCompanyEmpName1;

	/** 유효 시작일 */
	@Schema(description = "유효 시작일", nullable = true, example = "")
	private String fromValidDt;

	/** 유효 종료일 */
	@Schema(description = "유효 종료일", nullable = true, example = "")
	private String toValidDt;

	/** 계약 구분 */
	@Schema(description = "계약 구분", nullable = true, example = "")
	private String contractType;

	/** 바코드 */
	@Schema(description = "바코드", nullable = true, example = "")
	private String barcode;

	/** 발주 KEY */
	@Schema(description = "PO KEY", nullable = true, example = "")
	private String poKey;

	/** 발주 LINE */
	@Schema(description = "PO LINE", nullable = true, example = "")
	private String poLine;
	
	/* 미착재고 조회 항목 추가 */
	
	/** 고객사 */
    @Schema(description = "고객사", nullable = true, example = "")
    private String storerkey;
	
	/** 문서번호 */
    @Schema(description = "문서번호", nullable = true, example = "")
    private String docno;
    
    /** 문서라인번호 */
    @Schema(description = "문서라인번호", nullable = true, example = "")
    private String docline;
    
    /** 고객사 */
    @Schema(description = "고객사", nullable = true, example = "")
    private String custkey;
    
    /** 미통관(보세입고전) 수량 */
    @Schema(description = "미통관(보세입고전) 수량", nullable = true, example = "")
    private BigDecimal stockQty;
    
    /** 미통관(보세입고후) 수량 */
    @Schema(description = "미통관(보세입고후) 수량", nullable = true, example = "")
    private BigDecimal bondQty;
    
    /** 미통관재고(Box) */
    @Schema(description = "미통관재고(Box)", nullable = true, example = "")
    private BigDecimal stockBox;
    
    /** 보세입고일 */
    @Schema(description = "보세입고일", nullable = true, example = "")
    private String bondCarryDate;
    
    /** EST No */
    @Schema(description = "EST No", nullable = true, example = "")
    private String estNo;
    
    /** 주재료 원산국 */
    @Schema(description = "주재료 원산국", nullable = true, example = "")
    private String countryoforigin;
    
    /** 컨테이너번호 */
    @Schema(description = "컨테이너번호", nullable = true, example = "")
    private String containerno;
    
    /** 유통기한 잔여일 */
    @Schema(description = "유통기한 잔여일", nullable = true, example = "")
    private String expiryDay;
    
    /** LC No. */
    @Schema(description = "LC No.", nullable = true, example = "")
    private String lcNo;
    
    /** 판매담당코드 */
    @Schema(description = "판매담당코드", nullable = true, example = "")
    private String somdcode;
    
    /** 판매담당명 */
    @Schema(description = "판매담당명", nullable = true, example = "")
    private String somdname;
    
    /** 소비기한률 */
    @Schema(description = "durationRate", nullable = true, example = "")
    private String durationRate;
    
    private String storageLoc;    
    private String serialType;	
	private String stockamt;
	private String stockPrice;	
	private String price;    
	private String serialKey;	
	private String sumQty;
	private String stockamtmsg;
	private String stockQty1;	
	private String skuL;
	private String skuM;
	private String skuS;
	private String skuD;	
	private String expiredt;
	private String manufacturedt;

	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
