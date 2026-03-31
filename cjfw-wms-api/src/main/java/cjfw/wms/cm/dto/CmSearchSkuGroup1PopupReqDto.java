package cjfw.wms.cm.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KwonJungYun (jungyun8667@cj.net)
 * @date : 2025.05.19
 * @description :  소분류코드 검색 팝업 요청dto
 * @issues : <pre>
 * -----------------------------------------------------------
 * DATE AUTHOR MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.05.19 KwonJungYun (jungyun8667@cj.net) 생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "소분류코드 검색 팝업 요청")
public class CmSearchSkuGroup1PopupReqDto extends CommonDto{

	@Schema(description = "상품분류코드/명", nullable = false, example = "2600")
	private String name;

	@Schema(description = "", nullable = false, example = "2600")
	private String specClass;

	@Schema(description = "", nullable = false, example = "2600")
	private String specCategory;
	
	/** 다중선택 */
	@Schema(description = "다중 선택", example = "")
	private String multiSelect;
	
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
