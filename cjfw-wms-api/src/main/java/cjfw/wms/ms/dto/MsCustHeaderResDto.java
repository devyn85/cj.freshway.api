package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.06.09 
 * @description : 고객정보(New) 주출고처 리스트 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.09 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
public class MsCustHeaderResDto {
	/** 센터코드 */
	@Schema(description = "센터코드", example = "")
	private String basecode;
	
	/** 센터명 */
	@Schema(description = "센터명", example = "")
	private String basedescr;
	
}