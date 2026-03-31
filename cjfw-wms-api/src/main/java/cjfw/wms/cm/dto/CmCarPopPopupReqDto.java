package cjfw.wms.cm.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved. 
 *
 * @author : ParkJinWoo (jw.park@cj.net) 
 * @date : 2025.05.09 
 * @description : 로케이션 팝업 리스트 조회 요청 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.09 ParkJinWoo (jw.park@cj.net) 생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CmCarPopPopupReqDto extends CommonDto {
	/**코드*/
    @Schema(description = "code")
    private String code;
	/**명*/
    @Schema(description = "name")
    private String name;
    
    
	@Schema(description = "계약유형", example = "DELIVERY")
	private String contractType;

    @Schema(description = "다중선택", example = "1222,089,150")
    private String multiSelect;
}
