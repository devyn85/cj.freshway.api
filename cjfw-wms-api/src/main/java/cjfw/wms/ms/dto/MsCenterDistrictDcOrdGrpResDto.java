package cjfw.wms.ms.dto;


import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.11.13 
 * @description : 센터 권역 행정동 우선순위 응답 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.13 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@NoArgsConstructor
@Schema(description = "센터 권역 행정동 우선순위 응답 DTO")
public class MsCenterDistrictDcOrdGrpResDto {

	@Hidden
	@Schema(description = "테이블 키")
	private String serialkey;
	
	@Schema(description = "주문 그룹")
	private String ordGrp;
	
	@Schema(description = "주문 그룹명")
	private String ordGrpNm;
	
	@Schema(description = "우선순위 1 센터")
	private String pr1Dccode;
	
	@Schema(description = "우선순위 2 센터")
	private String pr2Dccode;

	@Schema(description = "우선순위 1 센터명")
	private String pr1Dcname;

	@Schema(description = "우선순위 2 센터명")
	private String pr2Dcname;

	@Schema(description = "FW/FO")
	private String dcgroup;

	@Hidden
	@Schema(description = "적용시작일자")
	private String fromDate;

	@Hidden
	@Schema(description = "적용종료일자")
	private String toDate;
	
	@Schema(description = "삭제 여부", allowableValues = {"Y", "N"})
	private String delYn;

	@Schema(description = "배송물류센터")
	private String deliveryDccode;
	@Schema(description = "배송물류센터명")
	private String deliveryDcname;

	@Hidden
    @Schema(description = "신규 여부", allowableValues = {"Y", "N"})
	private String isNew;

}
