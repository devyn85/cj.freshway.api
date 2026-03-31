package cjfw.wms.ms.entity;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.06.24 
 * @description : MOQ/LT마스터 Entity
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.24 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class MsSkuChainMoqEntity extends CommonDto {
	
	/** 센터코드 */
	@Schema(description = "센터코드", example = "")
	private String dccode;

	/** 삭제여부 */
	@Schema(description = "삭제여부", example = "")
	private String delYn;

	/** 상품코드 */
	@Schema(description = "상품코드", example = "")
	private String sku;

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

}