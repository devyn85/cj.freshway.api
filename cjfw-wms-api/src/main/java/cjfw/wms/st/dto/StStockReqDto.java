package cjfw.wms.st.dto;

import java.math.BigDecimal;
import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : SangSuSung(kduimux@cj.com) 
 * @date : 2025.07.22 
 * @description : 재고조회 요청 기능을 구현한 DTO Class 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.22 SangSuSung(kduimux@cj.com) 생성 </pre>
 */
@Data
@Schema(description = "재고조회 요청")
public class StStockReqDto extends CommonDto {
    
    /** 저장 리스트 */
    List<StConvertCFMDetailResDto> saveList;

    /** 물류센터 */
    @Schema(description = "물류센터")
    private String fixdccode;
    
    /** 물류센터(다중검색) */
    @MultiSearch
    @Schema(description = "물류센터-다중검색")
    private List<String> fixdccodeMulti;    
    
    /** 창고코드 */
    @Schema(description = "창고코드")
    private String organize;
    
    /** 창고코드(다중검색) */
	@MultiSearch
    @Schema(description = "창고코드-다중검색")
    private List<String> organizeMulti;     
    
    /** 정렬키 */
    @Schema(description = "정렬키")
    private String sortkey;
    
    /** 소비기한잔여율 */
    @Schema(description = "소비기한잔여율")
    private String sobiRate;    
    
    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;
    
    /** 상품(다중선택) */
    @MultiSearch
    @Schema(description = "상품(다중OR검색)")
    private List<List<String>> skuMulti;  	    
    
    /** 보관유형 */
    @Schema(description = "보관유형")
    private String storagetype;
    
    /** 보관유형(다중검색) */
	@MultiSearch
    @Schema(description = "보관유형-다중검색")
    private List<String> storagetypeMulti;   
    
    /** 유통기한여부 */
    @Schema(description = "유통기한여부")
    private String lottable01yn;
    
	/** 재고위치 */
	@Schema(description = "재고위치")
	private String stocktype;
    
    /** 재고속성 */
    @Schema(description = "재고속성", example = "")
    private String stockgrade;
    
    /** 이력번호 */
    @Schema(description = "이력번호", example = "SN1234567890")
    private String serialno;    
    
    /** 피킹존 */
    @Schema(description = "피킹존", example = "A02")
	private String zone;
	
    /** 피킹존(다중검색) */
	@MultiSearch
    @Schema(description = "피킹존-다중검색")
    private List<String> zoneMulti; 
	
    /** 기본보관로케이션유형 */
    @Schema(description = "기본보관로케이션유형")
    private String loccategory;

    /** 기준일(유통,제조) */
    @Schema(description = "기준일(유통,제조)")
    private String lottable01;
    
    /** FROM로케이션 */
    @Schema(description = "FROMLOC")
    private String fromloc;

    /** TO로케이션 */
    @Schema(description = "TOLOC")
    private String toloc;

    /** 수량 0 제외여부 */
    @Schema(description = "수량 0 제외여부")
    private String zeroQtyYn;
  
    /** 소비기한잔여율 */
    @Schema(description = "소비기한잔여율")
    private BigDecimal usebydatefreert;
    
    /** except1 */
    @Schema(description = "except1")
    private String except1;
    
    /** except1 */
    @Schema(description = "except2")
    private String except2;
    
    /** except1 */
    @Schema(description = "except3")
    private String except3;
    
    /** except1 */
    @Schema(description = "except4")
    private String except4;    
    
    /** 조회시작일자 */
    @Schema(description = "조회시작일자")
    private String dt1;

    /** 조회종료일자 */
    @Schema(description = "조회종료일자")
    private String dt2;    
    
    /** 조회구분 */
    @Schema(description = "조회구분")
    private String searchType;

	/** 단가/금액표시 여부  */
	@Schema(description = "단가/금액표시 여부 ")
	private String viewPriceYn = "N";
}
