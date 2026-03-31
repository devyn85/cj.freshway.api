package cjfw.wms.cm.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : YangChangHwan (iamai@cj.net) 
 * @date : 2025.05.29
 * @description : 센터 팝업 Request DTO Class 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.13 YangChangHwan (iamai@cj.net) 생성 </pre>
*/
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "센터 팝업 Request DTO")
public class CmDcPopupReqDto extends CommonDto {
	
	/** 코드 */
	@Schema(description = "코드", nullable = false, example = "")
	private String code;
	
	/** 코드 명 */
	@Schema(description = "코드 명", example = "센터")
	private String name;
	
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
