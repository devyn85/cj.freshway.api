package cjfw.wms.cm.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.05.12 
 * @description : 본점 검색 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.13 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "본점 검색 요청") 
public class CmCustBrandPopupReqDto extends CommonDto {
	
	/** 회사 */
	@Schema(description = "회사", nullable = false, example = "4900,8774")
	private String storerkey;	

	/** code */
	@Schema(description = "code", nullable = false, example = "Y00050")
	private String code;
	
	/** 코드/명 */
	@Schema(description = "코드/명", nullable = false, example = "Y00050")
	private String name;
	
	/** 본점코드-다중선택 */
	@Schema(description = "본점코드-다중선택", nullable = false, example = "4900,8774")
	private String multiSelect;
	
	/** custBrandList  */	
	private String[] custBrandList;
	
	@Schema(description = "999개 청크 목록(OR IN 생성용)")
	private java.util.List<java.util.List<String>> codeGroups;
	
    /* START.검색어 관련 필드 */
	@Schema(description = "검색어 영어")
	private String keywordEng;
	
	@Schema(description = "검색어 정규식")
	private String keywordRegexp;
	
	@Schema(description = "영문검색어 정규식")		
	private String keywordEngRegexp;
	
	@Schema(description = "엔터검색 여부")
	private String isEnter;
	
	/** FIX검색여부.%1개일 */
	@Schema(description = "FIX검색여부.%1개일", example = "")
	private String fixedSearchYn;		
	
	
}
