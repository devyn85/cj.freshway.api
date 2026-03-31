package cjfw.wms.cm.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : sss (kduimux@cj.net) 
 * @date : 2025.05.09 
 * @description : 단순 팝업 조회 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.09 sss (kduimux@cj.net) 생성 </pre>
 */
@Data
public class CmPopupResDto {
	
	/** 코드 */
	@Schema(description = "코드")
	private String code;

	/** 명 */
	@Schema(description = "명")
	private String name;
	
	
	// START.BOX 팝업 - 일단 같이 쓰자.리소스 절약
	/**체적*/
	@Schema(description = "체적")
	private BigDecimal cube;
	// END.BOX 팝업
	

}