package cjfw.wms.om.dto;

import java.math.BigDecimal;
import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonDto;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2026.03.10
 * @description : 당일광역보충발주(FO) 조회 Req DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2026.03.10    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class OmOrderCreationSTOOrdBaseFOReqDto extends CommonProcedureDto {
    
    /** 저장 리스트 */
    List<OmOrderCreationSTOOrdBaseFOResDto> saveList;
	
	/** 공급센터 C/D타입 무시 */
	@Schema(description = "공급센터 C/D타입 무시", example = "")
	private String ignorecrossyn;
	
	/** onlyordqty */
	@Schema(description = "onlyordqty", example = "")
	private String onlyordqty;
	
	/** 내 보유재고무시 */
	@Schema(description = "내 보유재고무시", example = "")
	private String stuseyn;
	
	/** 축육상품제외 */
	@Schema(description = "축육상품제외", example = "")
	private String serialyn;
	
	/** 저장조건 */
	@Schema(description = "저장조건", example = "")
	private String storagetype;
	
	/** 이체일자 */
	@Schema(description = "이체일자", example = "")
	private String deliverydate;
	
	/** 물류센터 */
	@Schema(description = "물류센터", example = "")
	private String dccode;
	
	/** 내 C/D상품조회 */
	@Schema(description = "내 C/D상품조회", example = "")
	private String ordcrossyn;
	
	/** 고객마감유형 */
	@Schema(description = "고객마감유형", example = "")
	private String custorderclosetype;
	
	/** 상품 */
	@Schema(description = "상품", example = "")
	private String sku;
	
	/** 상품 */
	@MultiSearch
	@Schema(description = "상품", example = "")
	private List<List<String>> skuMulti;
	
	/** 제외상품 */
	@Schema(description = "제외상품", example = "")
	private String skuexcept;
	
	/** 제외상품 */
	@MultiSearch
	@Schema(description = "제외상품", example = "")
	private List<List<String>> skuexceptMulti;
	
	/** 지정원거리유형 */
	@Schema(description = "지정원거리유형", example = "")
	private String distancetype;
	
	/** 지정원거리유형 */
	@MultiSearch
	@Schema(description = "지정원거리유형", example = "")
	private List<String> distancetypeMulti;
	
	/** 제외원거리유형 */
    @Schema(description = "제외원거리유형", example = "")
    private String setdistancetype;
    
    /** 제외원거리유형 */
    @MultiSearch
    @Schema(description = "제외원거리유형", example = "")
    private List<String> setdistancetypeMulti;
    
	/** stockcrossyn */
	@Schema(description = "stockcrossyn", example = "")
	private String stockcrossyn;
	
	/** 공급센터 */
	@Schema(description = "공급센터", example = "")
	private String dcA;
	
	/** poyn */
	@Schema(description = "poyn", example = "")
	private String poyn;
	
	/** stoyn */
	@Schema(description = "stoyn", example = "")
	private String stoyn;
	
	/** 공급센터 현재고 */
    @Schema(description = "공급센터 현재고", example = "")
    private String stqtyyn;
    
    /** 발주센터 현재고 */
    @Schema(description = "발주센터 현재고", example = "")
    private String opqtyyn;

	/** kxyn */
	@Schema(description = "kxyn", example = "")
	private String kxyn;
	
	/** 공급받는센터 */
    @Schema(description = "공급받는센터", example = "")
    private String toDccode;
    
    /** 공급센터 */
    @Schema(description = "공급센터", example = "")
    private String fromDccode;
    
    /** 이체주문번호 */
    @Schema(description = "이체주문번호", example = "")
    private String stoDocno;
    
    /** 주문번호 */
    @Schema(description = "주문번호", example = "")
    private String docno;
    
    /** 주문번호 */
    @MultiSearch
    @Schema(description = "주문번호", example = "")
    private List<String> docnoMulti;
    
    /** 관리처코드 */
    @Schema(description = "관리처코드", example = "")
    private String toCustkey;
    
    /** 관리처코드 */
    @MultiSearch
    @Schema(description = "관리처코드", example = "")
    private List<String> toCustkeyMulti;

}
