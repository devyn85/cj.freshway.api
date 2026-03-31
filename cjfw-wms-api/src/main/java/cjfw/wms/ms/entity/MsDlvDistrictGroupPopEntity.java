package cjfw.wms.ms.entity;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.09.15 
 * @description : 배송 권역 그룹 X POP 엔티티 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.15 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "배송 권역 그룹 X POP Entity") 
public class MsDlvDistrictGroupPopEntity extends CommonDto {
	
	@Schema(description = "일련번호 (고유 키)", example = "123456789012345")
	private int serialkey;
	
	@Schema(description = "센터코드")
	private String dccode;
	
	@Schema(description = "권역그룹아이디")
	private String dlvgroupId;
	
	@Schema(description = "pop번호")
	private String popNo;
	
	@Schema(description = "시작시간")
	private String fromHour;
	
	@Schema(description = "종료시간")
	private String toHour;
	
	@Schema(description = "삭제여부")
	private String delYn;
	
	@Schema(description = "유효시작일자")
	private String fromDate;
	
	@Schema(description = "유효종료일자")
	private String toDate;
	
	@Schema(description = "추가일자")
	private String addDate;
	
	@Schema(description = "수정일자")
	private String editDate;
	
	@Schema(description = "추가자")
	private String addWho;
	
	@Schema(description = "수정자")
	private String editWho;
}
