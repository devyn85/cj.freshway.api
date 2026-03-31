package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.05.09 
 * @description : 귀속 부서 조회 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.09 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
public class CmCostCenterPopupResDto {
	
	/** 코스트센터코드 */
	@Schema(description = "코스트센터코드")
	private String code;

	/** 코스트센터명 */
	@Schema(description = "코스트센터명")
	private String name;
	
	/** 시작일자 */
	@Schema(description = "시작일자")
	private String fromdate;
	
	/** 종료일자 */
	@Schema(description = "종료일자")
	private String todate;
	

}