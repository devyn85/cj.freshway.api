package cjfw.wms.ms.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.09.05 
 * @description : 센터 권역 행정동 요청 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.05 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "센터 권역 행정동 요청 DTO")
public class MsCenterDlvDistrictHjdongReqDto {
	
	@Schema(description = "테이블 키(센터 권역)")
	private String serialkey;
	
	@Schema(description = "센터 코드")
	private String dccode;

	@Schema(description = "권역그룹 ID")
	private String dlvgroupId;

	@Schema(description = "배송권역 ID")
	private String dlvdistrictId;
	
	@Schema(description = "요청 일자")
	private String effectiveDate;
	
	@Schema(description = "행정동 리스트")
	private List<Hjdong> hjdongList;
	
	@Data
	public static class Hjdong extends CommonDto {
	
		@Schema(description = "테이블 키(센터 권역 행정동)")
		private String serialkey;

        @Schema(description = "센터 코드")
        private String dccode;

		@Schema(description = "권역그룹 ID")
		private String dlvgroupId;

        @Schema(description = "배송권역 ID")
        private String dlvdistrictId;

		@Schema(description = "행정동 코드")
	    private String hjdongCd;
		
		@Schema(description = "유효시작일자")
		private String fromDate;
		
		@Schema(description = "유효종료일자")
		private String toDate;
		
		@Schema(description = "삭제여부")
		private String delYn;

		@Hidden
		private int rowCount;

		@Hidden
		private String editDate;
		@Hidden
		private String editWho;

	}
}
