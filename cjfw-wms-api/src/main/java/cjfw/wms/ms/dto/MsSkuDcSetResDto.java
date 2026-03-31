package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.05.23
 * @description : 센터상품속성 조회 결과 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.23    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Schema(description = "센터상품속성 조회 결과")
public class MsSkuDcSetResDto {    
	/** row 번호 */
    @Schema(description = "row 번호", nullable = false, example = "")
    private Integer rowcnt;

    /** 센터코드 */
    @Schema(description = "센터코드", nullable = false, example = "")
    private String dccode;
    
    /** 센터코드명 */
    @Schema(description = "센터코드명", nullable = false, example = "")
    private String dccodename;

    /** 고객사 */
    @Schema(description = "고객사", nullable = false, example = "")
    private String storerkey;

    /** 상품코드 */
    @Schema(description = "상품코드", nullable = false, example = "")
    private String sku;

    /** 상품명 */
    @Schema(description = "상품명", nullable = false, example = "")
    private String skuDescr;

    /** 크로스도킹 */
    @Schema(description = "크로스도킹", nullable = false, example = "")
    private String crossdocktype;
    
    /** 크로스도킹명 */
    @Schema(description = "크로스도킹명", nullable = false, example = "")
    private String crossdocktypename;

    /** 적치유형 */
    @Schema(description = "적치유형", nullable = false, example = "")
    private String putawaytype;
    
    /** 적치유형명 */
    @Schema(description = "적치유형명", nullable = false, example = "")
    private String putawaytypename;

    /** 기본보관창고동 */
    @Schema(description = "기본보관창고동", nullable = false, example = "")
    private String wharea;

    /** 기본보관창고층 */
    @Schema(description = "기본보관창고층", nullable = false, example = "")
    private String whareafloor;

    /** 기본보관로케이션유형 */
    @Schema(description = "기본보관로케이션유형", nullable = false, example = "")
    private String loccategory;

    /** 기본보관로케이션층 */
    @Schema(description = "기본보관로케이션층", nullable = false, example = "")
    private String loclevel;

    /** 기본보관존 */
    @Schema(description = "기본보관존", nullable = false, example = "")
    private String zone;

    /** 기본보관장소 */
    @Schema(description = "기본보관장소", nullable = false, example = "")
    private String loc;

    /** abc */
    @Schema(description = "abc", nullable = false, example = "")
    private String abc;

    /** 최소발주수량 */
    @Schema(description = "최소발주수량", nullable = false, example = "")
    private Integer minpoqty;

    /** 목표발주수량 */
    @Schema(description = "목표발주수량", nullable = false, example = "")
    private Integer targetpoqty;

    /** 유효일자 */
    @Schema(description = "유효일자", nullable = false, example = "")
    private String effectivedate;
    
    /** 유효일자 */
    @Schema(description = "유효일자", nullable = false, example = "")
    private String validEffectivedate;

    /** 기타01 */
    @Schema(description = "기타01", nullable = false, example = "")
    private String other01;

    /** 기타02 */
    @Schema(description = "기타02", nullable = false, example = "")
    private String other02;
    
    /** 기타02 */
    @Schema(description = "기타02", nullable = false, example = "")
    private String other02name;

    /** 기타03 */
    @Schema(description = "기타03", nullable = false, example = "")
    private String other03;

    /** 기타04 */
    @Schema(description = "기타04", nullable = false, example = "")
    private String other04;

    /** 기타05 */
    @Schema(description = "기타05", nullable = false, example = "")
    private String other05;

    /** 상태 */
    @Schema(description = "상태", nullable = false, example = "")
    private String status;
    
    /** 상태명 */
    @Schema(description = "상태명", nullable = false, example = "")
    private String statusname;

    /** SMS 처리대상여부 */
    @Schema(description = "SMS 처리대상여부", nullable = false, example = "")
    private String smsYn;

    /** 배송분류표 생성유형 */
    @Schema(description = "배송분류표 생성유형", nullable = false, example = "")
    private String invoiceCrtType;
    
    /** 배송분류표 생성유형명 */
    @Schema(description = "배송분류표 생성유형명", nullable = false, example = "")
    private String invoiceCrtTypename;   

    /** 삭제여부 */
    @Schema(description = "삭제여부", nullable = false, example = "")
    private String delYn;
    
    /** 삭제여부 */
    @Schema(description = "삭제여부", nullable = false, example = "")
    private String delYnname;

    /** 데이터번호 */
    @Schema(description = "데이터번호", nullable = false, example = "")
    private String serialkey;

    /** 고부피여부 */
    @Schema(description = "고부피여부", nullable = false, example = "")
    private String cubeYn;

    /** 쿠폰여부 */
    @Schema(description = "쿠폰여부", nullable = false, example = "")
    private String cpYn;
    
    /** 최초등록시간 (yyyymmddhh24miss) */
    @Schema(description = "최초등록시간 (yyyymmddhh24miss)", nullable = false, example = "")
    private String adddate;

    /** 최종변경시간 (yyyymmddhh24miss) */
    @Schema(description = "최종변경시간 (yyyymmddhh24miss)", nullable = false, example = "")
    private String editdate;

    /** 최초등록자 */
    @Schema(description = "최초등록자", nullable = false, example = "")
    private String addwho;
    
    /** 최초등록자 */
    @Schema(description = "최초등록자", nullable = false, example = "")
    private String addwhoNm;

    /** 최종변경자명 */
    @Schema(description = "최종변경자명", nullable = false, example = "")
    private String editwho;
    
    /** 최종변경자명 */
    @Schema(description = "최종변경자명", nullable = false, example = "")
    private String editwhoNm;
    
    /** GridRow 저장 구분 */
    @Schema(description = "GridRow 저장 구분", example = "U", allowableValues = {"I", "U", "D"})
    private String rowStatus;
    
    /** PROCESSFLAG */
    @Schema(description = "PROCESSFLAG", example = "")
    private String processflag;
    
    /** PROCESSMSG */
    @Schema(description = "PROCESSMSG", example = "")
    private String processmsg;

}
