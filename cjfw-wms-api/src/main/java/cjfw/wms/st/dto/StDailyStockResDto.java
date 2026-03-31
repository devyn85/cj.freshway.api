package cjfw.wms.st.dto;

import java.math.BigDecimal;
import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 *
 * @author : KimSunHo(sunhokim6229@cj.net)
 * @date : 2025.07.11
 * @description : 재고정보조회 결과 DTO
 * @issues :
 * -----------------------------------------------------------
 * DATE          AUTHOR                  MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.11    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Schema(description = "재고정보조회 결과 DTO")
public class StDailyStockResDto extends CommonProcedureDto {

    /** 센터코드 */
    @Schema(description = "센터코드")
    private String dccode;

    /** 조직코드 */
    @Schema(description = "조직코드")
    private String organize;
    
    /** 조직(다중검색) */
	@MultiSearch
    @Schema(description = "조직-다중검색")
    private List<String> organizeMulti;   

    /** 재고유형 */
    @Schema(description = "재고유형")
    private String stocktype;

    /** 재고등급 */
    @Schema(description = "재고등급")
    private String stockgrade;

    /** 존 */
    @Schema(description = "존")
    private String zone;

    /** 플렛여부 */
    @Schema(description = "플렛여부")
    private String pltFlg;

    /** 위치 */
    @Schema(description = "위치")
    private String loc;

    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;
    
    /** 상품(다중OR검색) */
    @Schema(description = "상품-다중OR검색")
    private List<List<String>> skuMulti;       

    /** 상품명 */
    @Schema(description = "상품명")
    private String skuname;

    /** 단위 */
    @Schema(description = "단위")
    private String uom;

    /** 재고수량 */
    @Schema(description = "재고수량")
    private BigDecimal qty;

    /** 박스당수량 */
    @Schema(description = "박스당수량")
    private BigDecimal qtyperbox;

    /** 개방수량 */
    @Schema(description = "개방수량")
    private BigDecimal openqty;

    /** 할당수량 */
    @Schema(description = "할당수량")
    private BigDecimal qtyallocated;

    /** 피킹수량 */
    @Schema(description = "피킹수량")
    private BigDecimal qtypicked;

    /** 유효기간여부 */
    @Schema(description = "유효기간여부")
    private String neardurationyn;

    /** 로트테이블01 */
    @Schema(description = "로트테이블01")
    private String lottable01;

    /** 유효기간 */
    @Schema(description = "유효기간")
    private String durationTerm;

    /** 시리얼번호 */
    @Schema(description = "시리얼번호")
    private String serialno;

    /** 보관유형 */
    @Schema(description = "보관유형")
    private String storagetype;

    /** 유효기간 */
    @Schema(description = "유효기간")
    private String duration;

    /** 유효기간유형 */
    @Schema(description = "유효기간유형")
    private String durationtype;

    /** 수량1 */
    @Schema(description = "수량1")
    private BigDecimal qty1;

    /** 수량2 */
    @Schema(description = "수량2")
    private BigDecimal qty2;

    /** 단위1 */
    @Schema(description = "단위1")
    private String uom1;

    /** 단위2 */
    @Schema(description = "단위2")
    private String uom2;

    /** 재고수량(kg) */
    @Schema(description = "재고수량(kg)")
    private BigDecimal stockQtyWeight;

    /** 개방수량(kg) */
    @Schema(description = "개방수량(kg)")
    private BigDecimal stockOpenqtyWeight;

    /** 위치유형 */
    @Schema(description = "위치유형")
    private String loctype;
    
    /** 제조일자 */
    @Schema(description = "제조일자")
    private String manufacturedt;    
    
    /** 소비일자 */
    @Schema(description = "소비일자")
    private String expiredt;    
    
    /** 소비기한잔여율 */
    @Schema(description = "소비기한잔여율")
    private BigDecimal usebydatefreert;

    /** stockdate */
    @Schema(description = "stockdate")
    private String stockdate;

    /** convserialno */
    @Schema(description = "convserialno")
    private String convserialno;
		/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
