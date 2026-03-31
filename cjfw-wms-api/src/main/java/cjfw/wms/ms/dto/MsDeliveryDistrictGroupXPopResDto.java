package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.Map;


/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.10.14 
 * @description : 배송 권역 그룹 X POP 응답 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.14 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "센터 배송 권역 그룹 POP 응답 DTO")
public class MsDeliveryDistrictGroupXPopResDto {
	
	@Schema(description = "테이블 번호")
	private String serialkey;
	
	@Schema(description = "유효종료일자")
	private String fromDate;
	
	@Schema(description = "유효 시작일자")
	private String toDate;
	
	@Schema(description = "POP번호")
	private String popNo;
	
	@Schema(description = "POP명")
	private String popName;
	
	@Schema(description = "배송권역그룹 코드")
	private String dlvgroupId;

	@Schema(description = "권역그룹명")
	private String dlvgroupNm;

	@Schema(description = "유효 시작시간")
	private String fromHour;
	
	@Schema(description = "유효 종료시간")
	private String toHour;
	
	@Schema(description = "삭제여부")
	private String delYn;
	
	@Schema(description = "POP 마스터 대표 Y/N")
	private String baseYn;

	@Schema(description = "POP 적용시작일자")
	private String popFromdate;

	@Schema(description = "POP 적용종료일자")
	private String popTodate;

	private List<Map<String, String>> groupPopList;
}
