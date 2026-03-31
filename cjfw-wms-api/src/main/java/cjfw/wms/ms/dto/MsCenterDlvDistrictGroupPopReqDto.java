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
 * @date : 2025.09.12 
 * @description : 배송 권역 그룹XPOP 요청 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.12 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "배송 권역 그룹XPOP 요청 DTO")
public class MsCenterDlvDistrictGroupPopReqDto extends CommonDto {
	
	@Schema(description = "배송권역그룹 테이블번호")
	private String serialkey;
	
	@Schema(description = "센터코드")
	private String dccode;
	
	@Schema(description = "배송권역그룹 유효시작일")
	private String fromDate;
	
	@Schema(description = "배송권역그룹 유효종료일")
	private String toDate;
	
	@Schema(description = "조회 일자")
	private String effectiveDate;
	
	@Schema(description = "배송권역그룹 삭제여부")
	private String delYn;
	
	@Schema(description = "배송권역그룹 비고")
	private String description;
	
	@Schema(description = "권역그룹아이디")
	private String dlvgroupId;
	
	@Schema(description = "권역그룹명")
	private String dlvgroupNm;
	
	@Schema(description = "배송권역 POP 리스트")
	private List<Pop> popList;
	
	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Pop extends CommonDto {
		
		@Schema(description = "테이블번호")
		private String serialkey;
		
		@Schema(description = "POP 번호")
		private String popNo;
		
		@Schema(description = "유효시작시간")
		private String fromHour;
		
		@Schema(description = "유효종료시간")
		private String toHour;
		
		@Schema(description = "유효시작일")
		private String fromDate;
		
		@Schema(description = "유효종료일")
		private String toDate;
		
		@Schema(description = "삭제여부")
		private String delYn;
	}
	
}
