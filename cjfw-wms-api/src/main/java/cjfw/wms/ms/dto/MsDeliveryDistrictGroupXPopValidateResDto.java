package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.10.23 
 * @description : 권역그룹 POP 검증 응답 POP 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.23 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "센터 배송 권역 그룹 POP 검증 응답 DTO")
public class MsDeliveryDistrictGroupXPopValidateResDto {
	
	@Schema(description = "일자 중복 Y/N")
	private String duplicateDateYn;
	
	@Schema(description = "시간대 중복 Y/N")
	private String duplicateHourYn;
	
	@Schema(description = "POP 3건 초과 존재 Y/N")
	private String threeExistYn;
}
