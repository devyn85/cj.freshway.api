package cjfw.wms.ms.entity;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.05.23 
 * @description : 센터상품속성 Entity
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.23    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "센터상품속성 Entity") 
public class MsSkuDcSetEntity extends CommonDto {	
	/** 데이터번호 */
    @Schema(description = "데이터번호", nullable = false, example = "")
    private Long serialkey;

	/** 센터코드 */
    @Schema(description = "센터코드", nullable = false, example = "")
    private String dccode;

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

    /** 적치유형 */
    @Schema(description = "적치유형", nullable = false, example = "")
    private String putawaytype;

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

    /** 삭제여부 */
    @Schema(description = "삭제여부", nullable = false, example = "")
    private String delYn;
    
    /** 삭제여부 */
    @Schema(description = "삭제여부", nullable = false, example = "")
    private String delYnname;

    /** 고부피여부 */
    @Schema(description = "고부피여부", nullable = false, example = "")
    private String cubeYn;

    /** 쿠폰여부 */
    @Schema(description = "쿠폰여부", nullable = false, example = "")
    private String cpYn;
    
    /** 등록자ID */
	@Schema(description = "등록자ID", example = "dev01")
	private String regId;

	/** 등록일시 */
	@Schema(description = "등록일시", example = "2015-10-23 오후 6:08:43")
	private String regDtm;

	/** 수정자ID */
	@Schema(description = "수정자ID", example = "dev01")
	private String updId;

	/** 수정일시 */
	@Schema(description = "수정일시", example = "2021-10-27 오후 5:39:05")
	private String updDtm;
	
	/** 프로시저 실행 성공여부 */
	@Schema(description = "프로시저 실행 성공여부", example = "")
	private Integer success;
	
	/** 프로시저 실행 에러코드 */
	@Schema(description = "프로시저 실행 에러코드", example = "")
	private Integer err;
	
	/** 프로시저 실행 에러메시지 */
	@Schema(description = "프로시저 실행 에러메시지", example = "")
	private String errmsg;
}
