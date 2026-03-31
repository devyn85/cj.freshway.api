package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.09.11 
 * @description : 배송 권역 그룹 POP 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.11 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@Builder
@Schema(description = "배송 권역 그룹 POP 응답 DTO")
public class MsDlvDistrictGroupPopResDto {
	
	@Schema(description = "테이블 번호")
	private String serialkey;
	
	@Schema(description = "센터코드")
	private String dccode;
	
	@Schema(description = "배송그룹 아이디")
    private String dlvgroupId;
	
	@Schema(description = "pop번호")
	private String popNo;
	
	@Schema(description = "시작시간")
	private String fromHour;
	
	@Schema(description = "종료시간")
	private String toHour;
	
	@Schema(description = "유효 시작일자")
    private String fromDate;
	
	@Schema(description = "유효 종료일자")
	private String toDate;
	
	@Schema(description = "삭제 여부")
	private String delYn;
}
