package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.09.15 
 * @description : 배송 권역 / 권역 그룹 폴리곤 응답 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.15 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@Builder
@Schema(description = "배송 권역 / 권역 그룹 폴리곤 응답 DTO")
public class MsDlvDistrictPolygonResDto {
	
	@Schema(description = "테이블 번호")
	private String serialkey;
	
	@Schema(description = "센터 코드")
	private String dccode;
	
	@Schema(description = "배송 권역 타입 (코드 생성 필요) 임시값 DISTRICT/GROUP")
	private String dlvDistrictType;

	@Schema(description = "배송 권역 타입별 이름")
	private String dlvDistrictNm;
	
	@Schema(description ="권역 아이디(타입에 따라 변동) DISTRICT -> 권역아이디 / GROUP -> 권역그룹아이디")
	private String districtId;
	
	@Schema(description ="유효 시작일")
	private String fromDate;
	
	@Schema(description ="유효 종료일")
	private String toDate;
	
	@Schema(description ="geojson 폴리곤")
	private String geojson;
	
	@Schema(description ="삭제 여부")
	private String delYn;
}
