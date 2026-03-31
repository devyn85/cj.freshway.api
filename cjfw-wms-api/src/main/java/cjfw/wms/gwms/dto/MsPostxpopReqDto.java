package cjfw.wms.gwms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 김환수 (hwansoo.kim@cj.net)
 * @date : 2025.11.05
 * @description : 중계 API > 국가주소연계 API 마스터 물류 송신(WM001)
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.05 김환수 (hwansoo.kim@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "우편번호POP정보") 
public class MsPostxpopReqDto {

	@Schema(description = "우편번호")
	private String zipcode;                 // ZIPCODE
	@Schema(description = "사용자")
	private String userid;

}
