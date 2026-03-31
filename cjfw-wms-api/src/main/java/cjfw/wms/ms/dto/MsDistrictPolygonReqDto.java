package cjfw.wms.ms.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.08.19 
 * @description : 행정동 폴리곤데이터 요청 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.19 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "행정동 폴리곤데이터 조회 조건 DTO")
public class MsDistrictPolygonReqDto {
	
	@Schema(description = "폴리곤 포함여부", nullable = false, example = "0")
	private boolean pg;
	
	@Schema(description = "조회할 권역 유형 CTP(시/군), SIG(시군구), HJDONG(읍면동)", nullable = false, example = "CTP")
	private String districtType;
	
	@Schema(description = "조회 기준일자", nullable = false, example = "YYYYMMDD")
	private String searchDate;
	
	@Schema(description = "조회 CTP(시/군)", nullable = false, example = "[11]")
	private String searchCtp;
	
}
