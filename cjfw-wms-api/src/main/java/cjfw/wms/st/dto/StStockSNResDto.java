package cjfw.wms.st.dto;

import java.math.BigDecimal;

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
public class StStockSNResDto extends CommonProcedureDto {

    /** 센터코드 */
    @Schema(description = "센터코드")
    private String dccode;

    /** 조직코드 */
    @Schema(description = "조직코드")
    private String organize;

    /** 재고유형 */
    @Schema(description = "재고유형")
    private String stocktype;

    /** 재고등급 */
    @Schema(description = "재고등급")
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
    
    /** 입고예정수량 */
    @Schema(description = "입고예정수량")
    private BigDecimal qtyexpected;

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

    /** 일련번호 */
    @Schema(description = "일련번호")
    private String serialno;

    /** 보관유형 */
    @Schema(description = "보관유형")
    private String storagetype;

    /** 포장키 */
    @Schema(description = "포장키")
    private String pokey;

    /** 포장라인 */
    @Schema(description = "포장라인")
    private String poline;

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

    /** 공장명 */
    @Schema(description = "공장명")
    private String factoryname;

    /** 변환일련번호 */
    @Schema(description = "변환일련번호")
    private String convserialno;

    /** 도축일자 */
    @Schema(description = "도축일자")
    private String butcherydt;

    /** 계약고객 */
    @Schema(description = "계약고객")
    private String contractcompany;

    /** 계약고객명 */
    @Schema(description = "계약고객명")
    private String contractcompanyname;

    /** 유효기간시작일자 */
    @Schema(description = "유효기간시작일자")
    private String fromvaliddt;

    /** 유효기간종료일자 */
    @Schema(description = "유효기간종료일자")
    private String tovaliddt;

    /** 계약유형 */
    @Schema(description = "계약유형")
    private String contracttype;

    /** 바코드 */
    @Schema(description = "바코드")
    private String barcode;

    /** 제조일자 */
    @Schema(description = "제조일자")
    private String manufacturedt;    
    
    /** 소비일자 */
    @Schema(description = "소비일자")
    private String expiredt;       
    
    /** 소비기한잔여율 */
    @Schema(description = "소비기한잔여율")
    private String usebydatefreert;
		/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
