package cjfw.wms.st.dto;

import java.math.BigDecimal;
import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.07.21 
 * @description : 외부창고정산 조회 요청 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.70.21    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "외부창고정산 조회 요청") 
public class StExDCStorageReqDto extends CommonProcedureDto {	
    
    /** 저장 리스트 */
    List<StExDCStorageResDto> saveList;
    
    /** 물류센터 */
	@Schema(description = "물류센터", nullable = false, example = "")
	private String dccode;
	
	/** 물류센터 */
    @Schema(description = "물류센터", nullable = false, example = "")
    private String fixdccode;
	
	/** 재고연월 */
    @Schema(description = "재고연월", nullable = false, example = "")
    private String stockmonth;
    
    /** 구역 */
    @Schema(description = "구역", nullable = false, example = "")
    private String area;
    
    /** 상품명칭 */
    @Schema(description = "상품명칭", nullable = false, example = "")
    private String skunm;
    
    /** 마감코드 */
    @Schema(description = "마감코드", nullable = false, example = "")
    private String magamcode;
    	
    /** 창고 */
    @Schema(description = "창고", nullable = false, example = "")
    private String organize;    
    
    /** 창고-다중검색 */
    @MultiSearch
    @Schema(description = "창고-다중검색", nullable = false, example = "")
    private List<String> organizeMulti;
    
    /** 구분 */
    @Schema(description = "구분", nullable = false, example = "")
    private String gubun;
    
    /** 유형 */
    @Schema(description = "유형", nullable = false, example = "")
    private String type;
    
    /** 입출고번호 */
    @Schema(description = "입출고번호", nullable = false, example = "")
    private String docno;
    
    /** 입출고번호-다중검색 */
    @MultiSearch
    @Schema(description = "입출고번호-다중검색", nullable = false, example = "")
    private List<String> docnoMulti;
    
    /** 세금코드 */
    @Schema(description = "세금코드", nullable = false, example = "")
    private String taxCls;
	
    /** 고객코드 */
    @Schema(description = "고객코드", nullable = false, example = "")
    private String custkey;
    
    /** 상품코드 */   
    @Schema(description = "상품코드", nullable = false, example = "")
    private String sku;
    
    /** 상품코드-다중검색 */
    @MultiSearch
    @Schema(description = "상품코드-다중검색", nullable = false, example = "")
    private List<String> skuMulti;
    
    /** 발주번호 */
    @Schema(description = "발주번호", nullable = false, example = "")
    private String pokey;
    
    /** 수출입발주번호 */
    @Schema(description = "수출입발주번호", nullable = false, example = "")
    private String tcspokey;
    
    /** 선하증권번호 */
    @Schema(description = "선하증권번호", nullable = false, example = "")
    private String convserialno;
    
    /** B/L번호-다중검색 */
    @MultiSearch
    @Schema(description = "B/L번호-다중검색", nullable = false, example = "")
    private List<String> convserialnoMulti;
    
    /** 이력번호 */
    @Schema(description = "이력번호", nullable = false, example = "")
    private String serialno;
    
    /** Stock ID */
    @Schema(description = "Stock ID", nullable = false, example = "")
    private String stockid;
    
    /** 마감비용번호 */
    @Schema(description = "마감비용번호", nullable = false, example = "")
    private String monthexpno;
    
    /** 코스트센터코드 */
    @Schema(description = "코스트센터코드", nullable = false, example = "")
    private String costcd;
    
    /** 컨테이너 번호 */
    @Schema(description = "컨테이너 번호", nullable = false, example = "")
    private String containerno;
    
    /** 등록자 */
    @Schema(description = "등록자", nullable = false, example = "")
    private String addwho;
    
    /** 수정자 */
    @Schema(description = "수정자", nullable = false, example = "")
    private String editwho;
    
    /** 입고료 */
    @Schema(description = "입고료", nullable = false, example = "")
    private BigDecimal grAmount;

    /** 출고료 */
    @Schema(description = "출고료", nullable = false, example = "")
    private BigDecimal giAmount;
    
    /** 창고료 */
    @Schema(description = "창고료", nullable = false, example = "")
    private BigDecimal stockAmount;
    
    /** 데이터번호 */
    @Schema(description = "데이터번호", nullable = false, example = "")
    private String serialkey; 
    
    /** 작업료트랜잭션일련번호 */
    @Schema(description = "작업료트랜잭션일련번호", nullable = false, example = "")
    private String workTransactionSn;
    
    /** 박스수량 */
    @Schema(description = "박스수량", nullable = false, example = "")
    private String boxqty; 
    
    /** 수량 */
    @Schema(description = "수량", nullable = false, example = "")
    private String quantity;
    
    /** 수량 0조회 */
    @Schema(description = "수량 0조회", nullable = false, example = "")
    private Integer qtyxzero;
    
    /** 당일매입매출제외 */
    @Schema(description = "당일매입매출제외", nullable = false, example = "")
    private Integer contracttype;
    
    /** 마감월 */
    @Schema(description = "마감월", nullable = false, example = "")
    private String yyyymm;
    
    /** 과세금액 */
    @Schema(description = "과세금액", nullable = false, example = "")
    private BigDecimal w1amt;
    
    /** 면세금액 */
    @Schema(description = "면세금액", nullable = false, example = "")
    private BigDecimal x3amt;
    
    /** 상품코드 */
    @Schema(description = "상품코드", nullable = false, example = "")
    private String[] skuList;
    
    /** 창고 */
    @Schema(description = "창고", nullable = false, example = "")
    private String[] organizeList;

}
