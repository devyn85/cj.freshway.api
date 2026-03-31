package cjfw.wms.ms.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.06.26 
 * @description : PLT변환값 마스터 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.26 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
public class MsSkuChainResDto {
			
	/** 체크여부 */
	@Schema(description = "체크여부", example = "")
	private String checkyn;

	/** 데이터번호 */
	@Schema(description = "데이터번호", example = "")
	private String serialkey;

	/** 상품코드 */
	@Schema(description = "상품코드", example = "")
	private String sku;

	/** 기본 제품명 없을경우 SKU코드 */
	@Schema(description = "기본 제품명 없을경우 SKU코드", example = "")
	private String description;

	/** 대분류 혹은 저장 타입 1-저온 2-상온 3-제약 4-사료벌크 5-셋트 */
	@Schema(description = "대분류 혹은 저장 타입 1-저온 2-상온 3-제약 4-사료벌크 5-셋트", example = "")
	private String storagetype;

	/** 고객사코드 */
	@Schema(description = "고객사코드", example = "")
	private String storerkey;

	/** 단위 */
	@Schema(description = "단위", example = "")
	private String baseuom;

	/** 삭제여부 */
	@Schema(description = "삭제여부", example = "")
	private String delYn;

	/** 팔렛당 박스수 */
	@Schema(description = "팔렛당 박스수", example = "")
	private String boxperplt;
	
	/** 평균중량 */
	@Schema(description = "평균중량", example = "")
	private String grossweight;

	/** 최초등록자 */
	@Schema(description = "최초등록자", example = "")
	private String addwho;

	/** 최초등록시간 */
	@Schema(description = "최초등록시간", example = "")
	private String adddate;

	/** 최종변경자 */
	@Schema(description = "최종변경자", example = "")
	private String editwho;

	/** 최종변경시간 */
	@Schema(description = "최종변경시간", example = "")
	private String editdate;

	/** 최초등록자 */
	@Schema(description = "최초등록자", example = "")
	private String regNm;
	
	/** 최종변경자 */
	@Schema(description = "최종변경자", example = "")
	private String updNm;
	
	/** 센터코드 */
    @Schema(description = "센터코드", example = "")
    private String dccode;

    /** 센터명 */
    @Schema(description = "센터명", example = "")
    private String dcname;

    /** 플랜트(창고) */
    @Schema(description = "플랜트(창고)", example = "")
    private String plant;
    
    /** 플랜트(창고)명 */
    @Schema(description = "플랜트(창고)명", example = "")
    private String plantname;

    /** 협력사코드 */
    @Schema(description = "협력사코드", example = "")
    private String custkey;

    /** 협력사명 */
    @Schema(description = "협력사명", example = "")
    private String custname;

    /** 상품분류 */
    @Schema(description = "상품분류", example = "")
    private String gpc;

    /** 적재단당박스수(방) */
    @Schema(description = "적재단당박스수(방)", example = "")
    private Integer boxperlayer;

    /** 팔렛당적재단수(단) */
    @Schema(description = "팔렛당적재단수(단)", example = "")
    private Integer layerperplt;

    /** PLT변환값 */
    @Schema(description = "PLT변환값", example = "")
    private String pltconversion;

    /** PLT변환값(환산) */
    @Schema(description = "PLT변환값(환산)", example = "")
    private String plttrans;   
    
    /** 커스텀 엑스트라 체크박스 */
    @Schema(description = "커스텀 엑스트라 체크박스", example = "N")
    private String customRowCheckYn = "N";
	
    /** 비정량 여부 */
    @Schema(description = "비정량 여부")
    private String line01;

    /** 표준중량 */
    @Schema(description = "표준중량")
    private BigDecimal line02;
	
}