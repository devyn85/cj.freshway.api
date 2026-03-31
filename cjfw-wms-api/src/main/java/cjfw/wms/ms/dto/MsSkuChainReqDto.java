package cjfw.wms.ms.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.06.26 
 * @description : PLT변환값 마스터 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.26 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class MsSkuChainReqDto extends CommonDto {
	
	/** 팔렛당 박스수 여부 */
	@Schema(description = "팔렛당 박스수 여부", example = "")
	private String boxperpltYn; 

	/** 삭제여부 */
	@Schema(description = "삭제여부", example = "")
	private String delYn;

	/** PLT변환값입력여부 */
	@Schema(description = "PLT변환값입력여부", example = "")
	private String pltYn;
	
	/** 상품코드 */
	@Schema(description = "상품코드", example = "")
	private String sku;
	
	/** 상품코드 */
	@MultiSearch
	@Schema(description = "상품코드", example = "")
	private List<List<String>> skuMulti;

	/** 팔렛당 박스수 */
	@Schema(description = "팔렛당 박스수", example = "")
	private String boxperplt;
	
	/** 센터코드 */
    @Schema(description = "센터코드", example = "")
    private String dccode;
    
    /** 센터코드 */
	@MultiSearch
	@Schema(description = "센터코드-다중OR검색", example = "")
	private List<String> dccodeMulti;

    /** 플랜트(창고) */
    @Schema(description = "플랜트(창고)", example = "")
    private String plant;
    
    /** 플랜트 */
	@MultiSearch
	@Schema(description = "플랜트-다중OR검색", example = "")
	private List<List<String>> plantMulti;

    /** 협력사코드 */
    @Schema(description = "협력사코드", example = "")
    private String custkey;
    
	/** 적재단당박스수(방) */
    @Schema(description = "적재단당박스수(방)", example = "")
    private Integer boxperlayer;

    /** 팔렛당적재단수(단) */
    @Schema(description = "팔렛당적재단수(단)", example = "")
    private Integer layerperplt;
    
    /** 저장조건 */
    @Schema(description = "저장조건", example = "")
    private String storagetype;
    
    /** 저장조건 */
	@MultiSearch
	@Schema(description = "저장조건-다중OR검색", example = "")
	private List<String> storagetypeMulti;
    
    /** 단위 */
    @Schema(description = "단위", example = "")
    private String baseuom;
    
    /** uid */
    @Schema(description = "uid", example = "")
    private String checkId;

}
