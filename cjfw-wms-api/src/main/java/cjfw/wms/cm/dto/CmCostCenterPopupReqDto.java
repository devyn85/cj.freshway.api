package cjfw.wms.cm.dto;

import cjfw.wms.common.extend.CommonPopupDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.05.09 
 * @description : 귀속 부서 조회 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.09 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CmCostCenterPopupReqDto extends CommonPopupDto {
	/** 코스트센터코드 or 센터명*/
	@Schema(description = "코스트센터코드", example = "J663")
	private String name;
	
	/** 다중선택 */
    @Schema(description = "다중 선택")
	private String multiSelect;	
}
