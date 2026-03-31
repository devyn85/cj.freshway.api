package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.11.21 
 * @description : 배차결과 비교 차량 거래처 배차 응답 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.21 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "배차결과 비교 차량 거래처 배차 응답 DTO")
public class TmPlanSummaryDiffCustResDto {
	
	@Schema(description = "실착지키")
	private String truthcustkey;
	
	@Schema(description = "고객사명")
	private String custname;
	
	@Schema(description = "가배차 Y/N")
	private String preDispatchYn;
	
	@Schema(description = "배차 Y/N")
	private String dispatchYn;
	
}