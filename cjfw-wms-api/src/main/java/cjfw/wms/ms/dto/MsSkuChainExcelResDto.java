package cjfw.wms.ms.dto;

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
public class MsSkuChainExcelResDto {
	
	/** 상품코드 */
	@Schema(description = "상품코드", example = "")
	private String sku;

	/** 센터코드 */
    @Schema(description = "센터코드", example = "")
    private String dccode;

    /** 플랜트(창고) */
    @Schema(description = "플랜트(창고)", example = "")
    private String plant;

    /** 협력사코드 */
    @Schema(description = "협력사코드", example = "")
    private String custkey;

    /** 적재단당박스수(방) */
    @Schema(description = "적재단당박스수(방)", example = "")
    private Integer boxperlayer;

    /** 팔렛당적재단수(단) */
    @Schema(description = "팔렛당적재단수(단)", example = "")
    private Integer layerperplt;

    /** 상품코드 유효성 */
    @Schema(description = "상품코드 유효성", example = "")
    private String skuCheck;
    
    /** 물류센터 유효성 */
    @Schema(description = "물류센터 유효성", example = "")
    private String dccodeChk;
    
    /** 창고 유효성 */
    @Schema(description = "창고 유효성", example = "")
    private String plantChk;
    
    /** 중복 유효성 */
    @Schema(description = "중복 유효성", example = "")
    private String duplicateChk;
    
    /** 존재여부 */
    @Schema(description = "존재여부", example = "")
    private String rowStatus;
	
    /** uid */
    @Schema(description = "uid", example = "")
    private String checkId;
}