package cjfw.wms.st.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : parkyosep(dytpq362@cj.com)
 * @date : 2025.11.06
 * @description : 화주 조회 기능을 구현한 Controller Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.06 parkyosep(dytpq362@cj.com) 생성 </pre>
 */
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StTplUserReqDto extends CommonDto {
	/** 창고코드 */
	private String dccode;

	/** 검색어 */

	/** 명 */
	@Schema(description = "화주명")
	private String name;

    /** 시작일자 */
    @Schema(description = "시작일자")
    private String fromDate;

    /** 종료일자 */
    @Schema(description = "종료일자")
    private String toDate;
    
    /** 일자 */
    @Schema(description = "기준일자")
    private String date;
    
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
