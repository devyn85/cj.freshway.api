package cjfw.wms.ms.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.11.14 
 * @description : 센터 권역 주문그룹 우선순위 요청 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.14 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Schema(description = "센터 권역 주문그룹 우선순위 요청 DTO")
public class MsCenterDistrictDcOrdGrpReqDto extends CommonDto {
	
	@Schema(description = "테이블 키")
	private String serialkey;
	
	@Schema(description = "주문 그룹", example = "A200")
	private String ordGrp;
	
	@Schema(description = "FW 센터코드", example = "2620")
	private String pr1Dccode;
	
	@Schema(description = "FO 센터코드", example = "2400")
	private String pr2Dccode;
	
	@Schema(description = "유효 시작일", example = "20231218")
	private String fromDate;
	
	@Schema(description = "유효 종료일", example = "20231231")
	private String toDate;
	
	@Schema(description = "삭제 Y/N")
	private String delYn;

	@Schema(description = "2620")
	private String deliveryDccode;

	@Schema(description = "조회일자", example = "20251201")
	private String effectiveDate;

	@Hidden
	private String editDate;
	@Hidden
	private String editWho;

}
