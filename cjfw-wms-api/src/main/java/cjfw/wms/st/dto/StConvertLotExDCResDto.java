package cjfw.wms.st.dto;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.06.16
 * @description : 외부비축소비기한변경 조회 결과 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.16    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Schema(description = "외부비축소비기한변경 조회 결과")
public class StConvertLotExDCResDto extends CommonProcedureDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";


	/** 체크유무 */
    @Schema(description = "체크유무", example = "")
    private String checkyn;
    
    /** 데이터번호 */
    @Schema(description = "데이터번호", example = "")
    private Long serialkey;

    /** 물류센터 */
    @Schema(description = "물류센터", example = "")
    private String dccode;

    /** 고객사코드 */
    @Schema(description = "고객사코드", example = "")
    private String storerkey;

    /** 창고 */
    @Schema(description = "창고", example = "")
    private String organize;

    /** 작업구역 */
    @Schema(description = "작업구역", example = "")
    private String area;

    /** 작업구역명 */
    @Schema(description = "작업구역명", example = "")
    private String areaname;

    /** 재고등급(재고속성) */
    @Schema(description = "재고등급(재고속성)", example = "")
    private String fromStockgrade;

    /** 재고등급명(재고속성명) */
    @Schema(description = "재고등급명(재고속성명)", example = "")
    private String stockgradename;

    /** 상품코드 */
    @Schema(description = "상품코드", example = "")
    private String sku;

    /** 상품영 */
    @Schema(description = "상품영", example = "")
    private String skuname;

    /** 상품분류 */
    @Schema(description = "상품분류", example = "")
    private String skugroup;

    /** 작업구역명 */
    @Schema(description = "작업구역명", example = "")
    private String mc;

    /** 로케이션 */
    @Schema(description = "로케이션", example = "")
    private String fromLoc;

    /** LOT */
    @Schema(description = "LOT", example = "")
    private String fromLot;

    /** 재고ID */
    @Schema(description = "재고ID", example = "")
    private String fromStockid;

    /** 재고유형 */
    @Schema(description = "재고유형", example = "")
    private String fromStocktype;

    /** 재고위치 */
    @Schema(description = "재고위치", example = "")
    private String fromStocktypedescr;

    /** 단위 */
    @Schema(description = "단위", example = "")
    private String uom;

    /** 현재고수량 */
    @Schema(description = "현재고수량", example = "")
    private java.math.BigDecimal qty;

    /** 가용재고수량 */
    @Schema(description = "가용재고수량", example = "")
    private java.math.BigDecimal openqty;

    /** 재고할당수량 */
    @Schema(description = "재고할당수량", example = "")
    private java.math.BigDecimal qtyallocated;

    /** 피킹재고수량 */
    @Schema(description = "피킹재고수량", example = "")
    private java.math.BigDecimal qtypicked;

    /** 작업수량(가용재고) */
    @Schema(description = "작업수량(가용재고)", example = "")
    private java.math.BigDecimal tranqty;

    /** 평균중량 */
    @Schema(description = "평균중량", example = "")
    private java.math.BigDecimal avgweight;

    /** 환산박스 */
    @Schema(description = "환산박스", example = "")
    private java.math.BigDecimal calbox;

    /** REALOPENBOX */
    @Schema(description = "REALOPENBOX", example = "")
    private java.math.BigDecimal realopenbox;

    /** 실박스 */
    @Schema(description = "실박스", example = "")
    private java.math.BigDecimal realcfmbox;

    /** 작업박스수량 */
    @Schema(description = "작업박스수량", example = "")
    private Integer tranbox;

    /** 박스처리유무 */
    @Schema(description = "박스처리유무", example = "")
    private String boxflag;

    /** 라벨1(기준일(유통제조)) */
    @Schema(description = "라벨1(기준일(유통제조))", example = "")
    private String fromLottable01;

    /** 라벨2 */
    @Schema(description = "라벨2", example = "")
    private String fromLottable02;

    /** 라벨3 */
    @Schema(description = "라벨3", example = "")
    private String fromLottable03;

    /** 라벨4 */
    @Schema(description = "라벨4", example = "")
    private String fromLottable04;

    /** 라벨5 */
    @Schema(description = "라벨5", example = "")
    private String fromLottable05;

    /** 유통기간잔여 */
    @Schema(description = "유통기간잔여", example = "")
    private String durationTerm;

    /** 유통기한관리방법 */
    @Schema(description = "유통기한관리방법", example = "")
    private String durationtype;

    /** 유통기간 */
    @Schema(description = "유통기간", example = "")
    private Integer duration;

    /** 이력번호 */
    @Schema(description = "이력번호", example = "")
    private String serialno;

    /** B/L번호  */
    @Schema(description = "B/L번호", example = "")
    private String convserialno;

    /** 도축일자 */
    @Schema(description = "도축일자", example = "")
    private String butcherydt;

    /** 계약업체 */
    @Schema(description = "계약업체", example = "")
    private String contractcompany;

    /** 계약업체명 */
    @Schema(description = "계약업체명", example = "")
    private String contractcompanyname;

    /** 유효일자(from) */
    @Schema(description = "유효일자(from)", example = "")
    private String fromvaliddt;

    /** 유효일자(to) */
    @Schema(description = "유효일자(to)", example = "")
    private String tovaliddt;

    /** 계약유형 */
    @Schema(description = "계약유형", example = "")
    private String contracttype;

    /** 바코드 */
    @Schema(description = "바코드", example = "")
    private String barcode;

    /** 도축장 */
    @Schema(description = "도축장", example = "")
    private String factoryname;
    
    /** 잔여율 */
    @Schema(description = "잔여율", example = "")
    private Integer usebydateFreeRt;
    
    /** 최초등록시간 (yyyymmddhh24miss) */
    @Schema(description = "최초등록시간 (yyyymmddhh24miss)", example = "")
    private String adddate;

    /** 최종변경시간 (yyyymmddhh24miss) */
    @Schema(description = "최종변경시간 (yyyymmddhh24miss)", example = "")
    private String editdate;

    /** 최초등록자 */
    @Schema(description = "최초등록자", example = "")
    private String addwho;
    
    /** 최초등록자명 */
    @Schema(description = "최초등록자명", example = "")
    private String addwhoNm;

    /** 최종변경자 */
    @Schema(description = "최종변경자", example = "")
    private String editwho;
    
    /** 최종변경자명 */
    @Schema(description = "최종변경자명", example = "")
    private String editwhoNm;

    /** B/L 번호 */
    @Schema(description = "B/L 번호", example = "")
    private String blno; 

}
