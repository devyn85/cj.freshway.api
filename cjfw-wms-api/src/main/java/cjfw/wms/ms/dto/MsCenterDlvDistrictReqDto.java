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
 * @date : 2025.09.05 
 * @description : 배송 권역 요청 DTO 
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
@Schema(description = "센터 배송 권역 요청 DTO")
public class MsCenterDlvDistrictReqDto extends CommonDto {
	
	@Schema(description = "시리얼 번호")
	private String serialkey;
	
	@Schema(description = "적용 일자")
	private String effectiveDate;
	
	@Schema(description = "센터 코드")
	private String dccode;
	
	@Schema(description = "권역그룹 ID")
	private String dlvgroupId;
	
	@Schema(description = "권역 그룹명")
	private String dlvgroupNm;
	
	@Schema(description = "검색 키워드 (POPNO + POPNAME")
	private String searchKeyword;
	
	@Schema(description = "권역 ID")
	private String dlvdistrictId;
	
	@Schema(description = "배송 권역 명")
	private String dlvdistrictNm;
	
	@Schema(description = "배송 권역 ID / 권역명 조회")
	private String searchDistrict;
	
	@Schema(description = "배송 권역 그룹 ID / 권역명 조회")
	private String searchDistrictGroup;
	
	@Schema(description = "비고")
    private String description;
	
	@Schema(description = "유효시작일자")
	private String fromDate;
	
	@Schema(description = "유효종료일자")
	private String toDate;
	
	@Schema(description = "삭제유무")
	private String delYn;
	
	@Schema(description = "센터코드 리스트")
	private String[] gMultiDccodeList;

	@Schema(description = "다중 선택", example = "")
	private String multiSelect;
}
