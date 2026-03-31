package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : YangChangHwan (iamai@cj.net) 
 * @date : 2025.05.29
 * @description : 센터 팝업 Response DTO Class 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.13 YangChangHwan (iamai@cj.net) 생성 </pre>
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "센터 팝업 Response DTO")
public class CmDcPopupResDto {
	
	// 코드
	@Schema(description = "코드")
    private String code;
	
    // 명
	@Schema(description = "명")
    private String name;
}
