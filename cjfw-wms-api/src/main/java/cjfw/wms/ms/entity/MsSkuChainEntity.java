package cjfw.wms.ms.entity;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.06.26 
 * @description : PLT변환값 마스터 Entity
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.26 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class MsSkuChainEntity extends CommonDto {

	/** 팔렛당 박스수 여부 */
	@Schema(description = "팔렛당 박스수 여부", example = "")
	private String boxperpltYn; 

	/** 삭제여부 */
	@Schema(description = "삭제여부", example = "")
	private String delYn;

	/** 상품코드 */
	@Schema(description = "상품코드", example = "")
	private String sku;

	/** 팔렛당 박스수 */
	@Schema(description = "팔렛당 박스수", example = "")
	private String boxperplt;
	
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

}