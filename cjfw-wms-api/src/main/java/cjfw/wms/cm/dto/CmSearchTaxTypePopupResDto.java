package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KimSunHo (sunhokim6229@cj.net) 
 * @date : 2025.08.09
 * @description : Tax Type 검색 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.09 KimSunHo (sunhokim6229@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "Tax Type 검색 응답")
public class CmSearchTaxTypePopupResDto {
	/** 코드 */
	@Schema(description = "Tax Type 코드", nullable = false, example = "")
	private String code;
	
	/** 명 */
	@Schema(description = "Tax Type명", nullable = false, example = "")
	private String name;
	
	/** 부가세비율 */
    @Schema(description = "부가세비율", nullable = false, example = "")
    private String data1;
	
}