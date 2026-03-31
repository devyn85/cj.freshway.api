package cjfw.wms.cm.dto;

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
 * @author : SangSuSung(kduimux@cj.cj.com)
 * @date : 2025.05.09
 * @description : 창고 목록 조회 기능을 구현한 Controller Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.05.09 SangSuSung(kduimux@cj.com) 생성 </pre>
 */
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CmOrganizePopupReqDto extends CommonDto {
	/** 창고코드 */
	private String dccode;

	/** 검색어 */

	/** 명 */
	@Schema(description = "창고코드/명", nullable = false, example = "1006411")
	private String name;

	/** 다중선택 */
	@Schema(description = "다중 선택", example = "")
	private String multiSelect;
	
    /** 상품(다중검색) */
	@MultiSearch
    @Schema(description = "상품-다중검색")
    private List<String> multiSelectMulti;  	
	
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
