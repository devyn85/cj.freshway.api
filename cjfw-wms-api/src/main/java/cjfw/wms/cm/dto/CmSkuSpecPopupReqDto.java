package cjfw.wms.cm.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KimSunHo (sunhokim6229@cj.net) 
 * @date : 2025.05.12 
 * @description : 삼품 검색 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.12 KimSunHo (sunhokim6229@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "상품 검색 요청")
public class CmSkuSpecPopupReqDto extends CommonDto {
	/** 분류코드 */
	@Schema(description = "분류코드", nullable = false, example = "SKUGROUP")
	private String speccategory;
	
	/** 분류코드/명 */
	@Schema(description = "분류코드/명", nullable = false, example = "")
	private String name;
	
	/** 설명 */
	@Schema(description = "설명", nullable = false, example = "")
	private String specclass;	
	
	/** 다중선택 */
	private String multiSelect;
	
	/** 코드리스트 */
    private String[] codeList;
}
