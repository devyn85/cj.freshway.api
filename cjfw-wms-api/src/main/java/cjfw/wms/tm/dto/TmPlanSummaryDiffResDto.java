package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.11.20 
 * @description : 배차결과 비교 요약정보 응답 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.20 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "배차결과 비교 요약정보 응답 DTO")
public class TmPlanSummaryDiffResDto {
	
	@Schema(description = "계약 유형")
	private String contractType;
	
	@Schema(description = "계약 유형명")
	private String contractnm;
	
	@Schema(description = "차량유형")
	private String item;

	@Schema(description = "차량대수")
	private String tempDispatch;
	
	@Schema(description = "중량")
	private String confirmed;
	
	@Schema(description = "체적")
	private String gap;
	
}
