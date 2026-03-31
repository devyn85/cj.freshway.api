package cjfw.wms.common.extend;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 * @author : SangSuSung(kduimux@cj.cj.com) 
 * @date : 2025.04.30 
 * @description : 팝업 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.04.30 SangSuSung(kduimux@cj.cj.com) 생성
 */
@Data
public class CommonPopupDto extends CommonDto {
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
	@Schema(description = "FIX검색여부.%1개일")
	private String fixedSearchYn;	
	
	/** 콤마검색여부.엔터검색.input/돋보기 검색 시 콤마포함여부 */
	@Schema(description = "FIX검색여부.%1개일")
	private String commaSearchYn;
	/* END.검색어 관련 필드 */	
	
}
