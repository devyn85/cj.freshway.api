package cjfw.wms.kp.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 박요셉 (dytpq362@cj.net) 
 * @date : 2025.12.18
 * @description : 공정별생산성 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.12.18 박요셉 (dytpq362@cj.net) 생성</pre>
 * */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "결품대응현황 목록 요청") 
public class KpProcessResultReqDto extends CommonDto {
	  @Schema(description = "시작변경일자", example = "202511050000")
	    private String fromModifyDate;
	    
	    @Schema(description = "종료변경일자", example = "202511300000")
	    private String toModifyDate;    
	    
	    @Schema(description = "물류센터 코드")
	    private String dcCode;	

	    @Schema(description = "센터공정순서")
	    private String processStep;	
	    
	    @Schema(description = "id")
	    private String userId;	


}
