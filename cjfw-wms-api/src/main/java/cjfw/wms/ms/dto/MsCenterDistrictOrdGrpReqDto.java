package cjfw.wms.ms.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.08.29 
 * @description : 센터 권역 주문 그룹 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.29 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "센터 권역 주문 그룹 요청 DTO")
public class MsCenterDistrictOrdGrpReqDto extends CommonDto {
	
	@Schema(description = "테이블 키")
	private String serialkey;
	
	@Schema(description = "적용 일자")
	private String effectiveDate;
	
	@Schema(description = "행정동 코드")
	private String hjdongCd;
	
	@Schema(description = "센터 코드")
	private String dccode;
	
	@Schema(description = "유효 시작일")
	private String fromDate;
	
	@Schema(description = "유효 종료일")
	private String toDate;
	
	@Schema(description = "주문 그룹")
	private String ordGrp;
	
}
