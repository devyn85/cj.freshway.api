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
 * @date : 2025.08.09 
 * @description : Tax Type 검색 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.09 KimSunHo (sunhokim6229@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Tax Type 검색 요청")
public class CmSearchTaxTypePopupReqDto extends CommonDto {
	/** Tax Type코드/명 */
	@Schema(description = "Tax Type코드/명", nullable = false, example = "")
	private String searchVal;

	/** Tax Type코드/명 */
	@Schema(description = "Tax Type코드/명", nullable = false, example = "")
	private String name;
	
	/** data1 */
    @Schema(description = "data1", nullable = false, example = "")
    private String data1;
        
    /** data2 */
    @Schema(description = "data2", nullable = false, example = "")
    private String data2;
	
	/** 다중선택 */
	private String multiSelect;
	
	/** 코드리스트 */
    private String[] codeList;
}
