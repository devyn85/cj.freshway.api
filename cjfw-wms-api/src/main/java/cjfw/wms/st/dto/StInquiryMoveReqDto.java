package cjfw.wms.st.dto;

import java.math.BigDecimal;
import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : sss (kduimux@cj.net)
 * @date : 2025.11.24
 * @description : 재고조사결과처리 Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.24 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Data
@Schema(description = "재고조사결과처리 Request DTO")
public class StInquiryMoveReqDto extends CommonProcedureDto {
	/** 저장 리스트*/
    List<StInquiryMoveResSkuDto> saveList;	

    /** 센터코드 */
    @Schema(description = "센터코드")
    private String dccode;

    /** 화주코드 */
    @Schema(description = "화주코드")
    private String storerkey;

    /** 조직 */
    @Schema(description = "조직")
    private String organize;

    /** 구역 */
    @Schema(description = "구역")
    private String area;

    /** 사용자ID */
    @Schema(description = "사용자ID")
    private String userId;

    /** 조사일자(From) */
	@Schema(description = "조사일자(From)")
	private String dt1;

	/** 조사일자(To) */
	@Schema(description = "조사일자(To)")
	private String dt2;    

    /** 조사번호 */
    @Schema(description = "조사번호")
    private String inquiryno;

    /** 상태 */
    @Schema(description = "상태")
    private String status;

    /** 조사유형 */
    @Schema(description = "조사유형")
    private String inquirytype;

    /** 로케이션 From */
    @Schema(description = "로케이션 From")
    private String fromLoc;

    /** 로케이션 To */
    @Schema(description = "로케이션 To")
    private String toLoc;

    /** 조직 리스트 */
    @Schema(description = "조직 리스트")
    private String organizeList;

    /** 상품 리스트 */
    @Schema(description = "상품 리스트")
    private String skuList;

    /** 창고구역 */
    @Schema(description = "창고구역")
    private String wharea;

    /** 존 From */
    @Schema(description = "존 From")
    private String fromZone;

    /** 존 To */
    @Schema(description = "존 To")
    private String toZone;

    /** 고정 센터코드 */
    @Schema(description = "고정 센터코드")
    private String fixdccode;

    /** 고정 화주코드 */
    @Schema(description = "고정 화주코드")
    private String fixstorerkey;

    /** 고정 조직 */
    @Schema(description = "고정 조직")
    private String fixorganize;

    /** 고정 구역 */
    @Schema(description = "고정 구역")
    private String fixarea;
    
    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;    
    
	/** 상품코드-다중OR검색 */
	@MultiSearch
    @Schema(description = "상품코드-다중OR검색")
    private List<List<String>> skuMulti;  
    
    /** 조사일자 */
    @Schema(description = "조사일자")
    private String inquirydt;

    /** 실사구분(0:재고실사,1:소비기한 */
    @Schema(description = "실사구분(0:재고실사,1:소비기한")
    private String lottype;
    
    /** 로케이션 */
    @Schema(description = "로케이션")
    private String loc;
    
    /** 변환유형 (예: 'CL') */
    @Schema(description = "변환유형")
    private String converttype; 

    /** 기준일(소비,제조) */
    @Schema(description = "기준일(소비,제조)")
    private String lottable01;

    /** lottable02 */
    @Schema(description = "lottable02")
    private String lottable02;

    /** lottable03 */
    @Schema(description = "lottable03")
    private String lottable03;

    /** lottable04 */
    @Schema(description = "lottable04")
    private String lottable04;

    /** lottable05 */
    @Schema(description = "lottable05")
    private String lottable05;    
    
    /** 사유메세지 */
    @Schema(description = "사유메세지")
    private String reasonmsg;  
    
    /** 회차 */
    @Schema(description = "회차")
    private BigDecimal priority;
    
	/** 조사별칭 */
	@Schema(description = "조사별칭")
	private String inquiryName;
	
	/** 이동사유코드 */
	@Schema(description = "이동사유코드")
	private String reasoncodeMove;

	/** 재고조사별칭 */
	@Schema(description = "재고조사별칭")
	private String inquiryAlias;
}
