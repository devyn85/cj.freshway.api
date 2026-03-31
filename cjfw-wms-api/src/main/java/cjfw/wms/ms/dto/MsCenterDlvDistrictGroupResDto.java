package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.09.12 
 * @description : 센터 배송 권역 그룹 응답 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.12 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "센터 배송 권역 그룹 응답 DTO")
public class MsCenterDlvDistrictGroupResDto {
	@Schema(description = "테이블 번호")
	private String serialkey;
	
	@Schema(description = "센터 코드")
	private String dccode;
	
	@Schema(description = "센터명")
	private String dcname;
	
	@Schema(description = "배송 그룹 아이디")
	private String dlvgroupId;
	
	@Schema(description = "배송 그룹명")
	private String dlvgroupNm;
	
	@Schema(description = "삭제여부")
	private String delYn;
	
	@Schema(description = "유효 시작일자")
	private String fromDate;
	
	@Schema(description = "유효 종료일자")
	private String toDate;
}
