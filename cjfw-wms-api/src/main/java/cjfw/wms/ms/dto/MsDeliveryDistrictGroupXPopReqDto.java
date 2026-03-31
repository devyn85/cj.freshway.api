package cjfw.wms.ms.dto;

import java.util.List;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.10.01 
 * @description : 배송권역 그룹 X POP 저장 요청 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.01 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "배송권역 그룹 X POP 저장 요청 DTO")
public class MsDeliveryDistrictGroupXPopReqDto  {
	
	@Schema(description = "데이터 번호")
	private String serialkey;
	
	@Schema(description = "배송그룹 ID")
	private String dlvgroupId;
	
	@Schema(description = "센터코드")
	private String dccode;
	
	@Schema(description = "요청 POP LIST")
	private List<Pop> popList;
	
	@Schema(description = "요청 일자")
	private String effectiveDate;
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Pop extends CommonDto {

		@Schema(description = "데이터 번호")
		private String serialkey;

        @Schema(description = "배송그룹 ID")
        private String dlvgroupId;

        @Schema(description = "센터코드")
        private String dccode;

        @Schema(description = "POP 번호")
		private String popNo;
		
		@Schema(description = "시작시간")
		private String fromHour;
		
		@Schema(description = "종료시간")
		private String toHour;
		
		@Schema(description = "삭제여부")
		private String delYn;
		
		@Schema(description = "시작일자")
		private String fromDate;
		
		@Schema(description = "종료일자")
		private String toDate;

		@Schema(description = "기본대표POP")
		private String baseYn;

	}
	
}
