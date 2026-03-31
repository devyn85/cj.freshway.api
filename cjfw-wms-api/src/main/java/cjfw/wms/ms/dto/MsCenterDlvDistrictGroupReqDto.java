package cjfw.wms.ms.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
 * @description : 배송 권역 그룹 요청 DTO 
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
@Schema(description = "배송 권역 그룹 요청 DTO")
public class MsCenterDlvDistrictGroupReqDto extends CommonDto {
	@Schema(description = "테이블 번호")
	private String serialkey;
	
	@Schema(description = "센터 코드", example = "2600")
	private String dccode;
	
	@Schema(description = "조회 일자", example = "20251210")
	private String effectiveDate;

	@Schema(description = "배송 그룹 아이디")
	private String dlvgroupId;
	
	@Schema(description = "배송 그룹명")
	private String dlvgroupNm;
	
	@Schema(description = "비고")
	private String description;
	
	@Schema(description = "삭제여부")
	private String delYn;
	
	@Schema(description = "유효 시작일자")
	private String fromDate;
	
	@Schema(description = "유효 종료일자")
	private String toDate;
	
	@Schema(description = "검색 키워드 (배송그룹명 + 배송그룹 아이디)")
	private String searchKeyword;
	
	@JsonIgnore
	@Schema(description = "센터 코드 리스트")
	private String[] gMultiDccodeList;

	@Schema(description = "다중 선택", example = "")
	private String multiSelect;
}
