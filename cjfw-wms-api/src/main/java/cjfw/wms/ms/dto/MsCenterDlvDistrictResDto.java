package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.09.05 
 * @description : 배송 권역 응답 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.05 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@NoArgsConstructor
@Schema(description = "센터 배송 권역 응답 DTO")
public class MsCenterDlvDistrictResDto {
	
	@Schema(description = "시리얼 번호")
	private String serialkey;
	
	@Schema(description = "센터 코드")
	private String dccode;
	
	@Schema(description = "권역그룹 ID")
	private String dlvgroupId;
	
	@Schema(description = "권역 그룹명")
	private String dlvgroupNm;
	
	@Schema(description = "권역 ID")
	private String dlvdistrictId;
	
	@Schema(description = "배송 권역 ID")
	private String dlvdistrictNm;
	
	@Schema(description = "배송 권역 행정동 갯수")
	private String hjdongCount;
	
	@Schema(description = "비고")
    private String description;
	
	@Schema(description = "유효시작일자")
	private String fromDate;
	
	@Schema(description = "유효종료일자")
	private String toDate;
	
	@Schema(description = "pop리스트")
	private String popList;
	
	@Schema(description = "삭제 여부")
	private String delYn;
	
}
