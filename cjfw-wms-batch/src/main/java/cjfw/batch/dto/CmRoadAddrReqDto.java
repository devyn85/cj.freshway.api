package cjfw.batch.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 김환수 (hwansoo.kim@cj.net)
 * @date : 2025.11.11
 * @description : CM_ROAD_ADDRESS(도로명주소) 주소정제 DTO (배치용)  
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.12.04 김환수 (hwansoo.kim@cj.net) 생성 </pre>
 */

@Data
@Schema(description = "CM_ROAD_ADDRESS(도로명주소) 행정동코드 생성 ") 
public class CmRoadAddrReqDto {

	@Schema(description = "COMMIT ROW COUNT")
	private String commtCnt;
	@Schema(description = "FETCH ROW COUNT")
	private String fetchCnt;

}
