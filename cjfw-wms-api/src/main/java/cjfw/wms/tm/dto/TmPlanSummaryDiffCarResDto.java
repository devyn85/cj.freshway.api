package cjfw.wms.tm.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.11.21 
 * @description : 배차결과비교 차량 응답 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.21 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@Builder
@Schema(description = "배차결과비교 차량 응답 DTO")
public class TmPlanSummaryDiffCarResDto {
	
	@Schema(description = "차량번호")
	private String carno;
	
	@Schema(description = "차량 요약정보")
	private List<TmPlanSummaryDiffCarResDto> carSummaryDiff;
	
	@Schema(description = "거래처 차량 배차 요약정보")
	private List<TmPlanSummaryDiffCustResDto> custSummaryDiff;
	
	
}
