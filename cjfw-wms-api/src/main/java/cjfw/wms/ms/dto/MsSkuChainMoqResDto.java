package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.06.24 
 * @description : MOQ/LT마스터 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.24 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
public class MsSkuChainMoqResDto {
			
	/** 선택여부 */
	@Schema(description = "선택여부", example = "")
	private String checkyn;

	/** 센터코드 */
	@Schema(description = "센터코드", example = "")
	private String dccode;
	
	/** 센터명 */
	@Schema(description = "센터명", example = "")
	private String dcname;
	
	/** 협력사코드 */
	@Schema(description = "협력사코드", example = "")
	private String custkey;
	
	/** 협력사명 */
	@Schema(description = "협력사명", example = "")
	private String custname;

	/** 고객사 */
	@Schema(description = "고객사", example = "")
	private String storerkey;

	/** 상품코드 */
	@Schema(description = "상품코드", example = "")
	private String sku;

	/** 기본 제품명 없을경우 SKU코드 */
	@Schema(description = "기본 제품명 없을경우 SKU코드", example = "")
	private String description;

	/** 삭제여부 */
	@Schema(description = "삭제여부", example = "")
	private String delYn;

	/** PLT당 BOX수 */
    @Schema(description = "PLT당 BOX수", example = "")
    private String boxperplt;
    
	/** 리드타임 */
	@Schema(description = "리드타임", example = "")
	private String leadtime;
	
	/** 상품 MOQ (BOX) */
	@Schema(description = "상품 MOQ (BOX)", example = "")
	private String moqSku;

    /** 상품 MOQ (PLT) */
    @Schema(description = "상품 MOQ (PLT)", example = "")
    private String moqSkuPlt; 

    /** 협력사 MOQ (BOX) */
    @Schema(description = "협력사 MOQ (BOX)", example = "")
    private String moqVenderBox;

    /** 협력사 MOQ (PLT) */
    @Schema(description = "협력사 MOQ (PLT)", example = "")
    private String moqVenderPlt;
    
	/** 수정자 */
	@Schema(description = "수정자", example = "")
	private String addwho;

	/** 최초등록시간 */
	@Schema(description = "최초등록시간", example = "")
	private String adddate;

	/** 수정자 */
	@Schema(description = "수정자", example = "")
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
	
	/** 상품코드 유효성 */
    @Schema(description = "상품코드 유효성", example = "")
    private String skuChk;
    
    /** 물류센터 유효성 */
    @Schema(description = "물류센터 유효성", example = "")
    private String dccodeChk;
    
    /** 중복 유효성 */
    @Schema(description = "중복 유효성", example = "")
    private String duplicateChk;
	
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
		
}