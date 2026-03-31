package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.08.20 
 * @description : 배송권역조회 응답 요청 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.20 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "POP 조회 응답 DTO")
public class CmDistrictPopupResDto {
	@Schema(description = "시리얼키", example="1")
	private String serialKeyString;
	
	@Schema(description= "권역그룹", example="STD")
	private String districtGroup;
	
	@Schema(description= "권역코드", example="01")
	private String districtCode;
	
	@Schema(description= "권역명", example="서울")
	private String districtName;
}
