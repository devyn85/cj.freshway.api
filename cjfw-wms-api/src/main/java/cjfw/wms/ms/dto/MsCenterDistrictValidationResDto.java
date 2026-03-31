package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.09.18 
 * @description : 센터 권역 검증 응답 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.18 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "센터 권역 검증 응답 DTO ")
public class MsCenterDistrictValidationResDto {
	
	@Schema(description = "일련번호")
	private String serialkey;

	@Schema(description = "행정동명", example = "중구")
	private String hjdongNm;
	
	@Schema(description = "행정동코드")
	private String hjdongCd;
	
	@Schema(description = "물류센터명A", example = "부산물류센터")
	private String dcnameA;
	
	@Schema(description = "물류센터 A 행정동 유효시작일")
	private String fromA;
	
	@Schema(description = "물류센터 A 행정동 유효종료일")
	private String toA;
	
	@Schema(description = "물류센터명B", example = "부산물류센터")
	private String dcnameB;
	
	@Schema(description = "물류센터 B 행정동 유효시작일")
	private String fromB;
	
	@Schema(description = "물류센터 B 행정동 유효종료일")
	private String toB;
	
	@Schema(description = "물류센터 B 행정동 유효종료일")
	private String errorMessage;
	
}
