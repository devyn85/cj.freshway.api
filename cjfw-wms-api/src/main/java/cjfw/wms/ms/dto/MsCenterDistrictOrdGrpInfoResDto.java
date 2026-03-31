package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.09.29 
 * @description : 주문 그룹 정보 응답 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.29 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@NoArgsConstructor
@Schema(description = "주문 그룹 정보 응답 DTO")
public class MsCenterDistrictOrdGrpInfoResDto {
	
	@Schema(description = "주문그룹 ID")
	private String ordGrp;

	@Schema(description = "주문그룹명")
	private String ordGrpNm;
}
