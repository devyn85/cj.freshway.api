package cjfw.batch.exdcSkuAmount.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "외부비축재고조회 Req Dto") 
public class ExdcSkuAmountReqDto {
    /** 저장 리스트 */
    List<ExdcSkuAmountEntity> saveList;
    
    /** 고정 물류센터 코드 */
	@Schema(description = "고정 물류센터 코드(FIX DCCODE)", nullable = true, example = "")
	private String fixdccode;

	/** 재고 유형 */
	@Schema(description = "재고 유형(STOCKTYPE)", nullable = true, example = "")
	private String stockType;

	/** 재고 등급 */
	@Schema(description = "재고 등급(STOCKGRADE)", nullable = true, example = "")
	private String stockGrade;

	/** 보관 구역(ZONE) */
	@Schema(description = "보관 구역 ZONE", nullable = true, example = "")
	private String zone;

	/** 저장 방식 */
	@Schema(description = "저장 방식(STORAGETYPE)", nullable = true, example = "")
	private String storageType;

	/** LOT Table01 필터 여부 */
	@Schema(description = "LOT Table01 필터 Y/N", nullable = true, example = "")
	private String lottable01Yn;

	/** 직렬검색 여부 */
	@Schema(description = "직렬검색 여부(Y/N)", nullable = true, example = "")
	private String searchSerial;

	/** 시리얼 번호 */
	@Schema(description = "시리얼 번호", nullable = true, example = "")
	private String serialNo;

	/** BL 번호 */
	@Schema(description = "BL 번호(다중 – 콤마/개행 구분)", nullable = true, example = "")
	private String blNo;
	
	/** BL 번호 */
    @MultiSearch
    @Schema(description = "BL 번호(다중 – 콤마/개행 구분)", nullable = false, example = "")
    private List<String> blNoMulti;

	/** 계약 고객사 코드 */
	@Schema(description = "계약 고객사 코드", nullable = true, example = "")
	private String contractCompany;

	/** SKU */
	@Schema(description = "SKU", nullable = true, example = "")
	private String sku;
	
	/** SKU 다중선택 */
	@MultiSearch
    @Schema(description = "SKU 다중선택", nullable = true, example = "")
    private List<List<String>> skuMulti;

	/** 창고 다중선택 */
	@Schema(description = "창고 다중선택", nullable = true, example = "")
	private String organize;

	/** 대분류(2자리) */
	@Schema(description = "대분류(2자리)", nullable = true, example = "")
	private String skuL;

	/** 중분류(4자리) */
	@Schema(description = "중분류(4자리)", nullable = true, example = "")
	private String skuM;

	/** 소분류(6자리) */
	@Schema(description = "소분류(6자리)", nullable = true, example = "")
	private String skuS;

	/** 세분류(6+) */
	@Schema(description = "세분류(6+)", nullable = true, example = "")
	private String skuD;

	/** BOXQTY 필터 여부 */
	@Schema(description = "BOXQTY 필터(Y/N)", nullable = true, example = "")
	private String boxQtyYn;

	/** 소비기한(%) */
	@Schema(description = "소비기한%", nullable = true, example = "")
	private String duratuinTerm;

	/** 중량 있는 재고라인만 여부 */
	@Schema(description = "중량있는 재고라인만 ", nullable = true, example = "")
	private String chkQty;
	@Schema(description = "중량있는 재고라인만 ", nullable = true, example = "")
	private String chkQty1;

	/** 재고 상태 */
	@Schema(description = "stockStatus", nullable = true, example = "")
	private String stockStatus;
	
	/** 상품 조회 */
    @Schema(description = "상품 조회", nullable = true, example = "")
    private String stockStatus20;
    
    /** 위탁 조회 */
    @Schema(description = "위탁 조회", nullable = true, example = "")
    private String stockStatus30;
    
    /** 미착 조회 */
    @Schema(description = "미착 조회", nullable = true, example = "")
    private String stockStatus40;
	
    /** 상품조회여부 */
    @Schema(description = "skuall", nullable = true, example = "")
    private String skuall;	
    
    /** 위탁재고조회여부 */
    @Schema(description = "skutpl", nullable = true, example = "")
    private String skutpl;
    
}
