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
 * @date : 2025.06.24 
 * @description : MOQ/LT마스터 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.24 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class MsSkuChainMoqReqDto extends CommonDto {
	
	/** 센터코드 */
	@Schema(description = "센터코드", example = "")
	private String dccode;
	
	/** 센터코드 */
	@MultiSearch
	@Schema(description = "센터코드-다중OR검색", example = "")
	private List<String> dccodeMulti;

	/** 삭제여부 */
	@Schema(description = "삭제여부", example = "")
	private String delYn;

	/** 상품코드 */
	@Schema(description = "상품코드", example = "")
	private String sku;
	
	/** 센터코드 */
	@MultiSearch
	@Schema(description = "센터코드-다중OR검색", example = "")
	private List<List<String>> skuMulti;

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
