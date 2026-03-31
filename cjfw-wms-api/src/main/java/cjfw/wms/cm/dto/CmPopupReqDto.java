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
 * @author : sss (kduimux@cj.net) 
 * @date : 2025.05.09 
 * @description : 팝업 조회 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.09 sss (kduimux@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CmPopupReqDto extends CommonPopupDto {
	/** 코드 or 명*/
	@Schema(description = "코드")
	private String name;
	
	/** 다중선택 */
    @Schema(description = "다중 선택")
	private String multiSelect;	
}
