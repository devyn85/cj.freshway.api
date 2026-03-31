package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.09.22 
 * @description : TMap 주쇼 -> 좌표 변경 요청 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.22 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "TMAP 주소 -> 좌표 요청 DTO")
public class TmTmapFullAddrGeoResDto {
	
	private CoordinateInfo coordinateInfo;
	
	@Data
	public static class CoordinateInfo {
		private String coordType;
		private String addressFlag;
		private String page;
		private String count;
		private String totalCount;
		private Coordinate[] coordinate;
	}
	
	@Data
	public static class Coordinate {
		private String matchFlag;
		private String lat;
		private String lon;
		private String latEntr;
		private String lonEntr;
		private String city_do;
		private String gu_gun;
		private String eup_myun;
		private String legalDong;
		private String legalDongCode;
		private String adminDong;
		private String adminDongCode;
		private String ri;
		private String bunji;
		private String buildingName;
		private String buildingDong;
		private String newMatchFlag;
		private String newLat;
		private String newLon;
		private String newLatEntr;
		private String newLonEntr;
		private String newRoadName;
		private String newBuildingIndex;
		private String newBuildingName;
		private String newBuildingCateName;
		private String newBuildingDong;
		private String zipcode;
		private String remainder;
	}

}
