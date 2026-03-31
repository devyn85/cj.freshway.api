package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.08.04 
 * @description : 센터이체마스터 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.04 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
public class MsCenterStoPriorityResDto {
	
	/** 데이터번호 */
	@Schema(description = "데이터번호", example = "")
	private String serialkey;

	/** 센터코드 */
	@Schema(description = "센터코드", example = "")
	private String dccode;
	
	/** 센터마감유형 */
	@Schema(description = "센터마감유형", example = "")
	private String dcClosetype;
		
	/** 수급우선순위 */
	@Schema(description = "수급우선순위", example = "")
	private String toPriority;
	
	/** 상품명 */
	@Schema(description = "상품명", example = "")
	private String skuName;
			
	/** 수급센터우선순위적용여부 */
	@Schema(description = "수급센터우선순위적용여부", example = "")
	private String toDccodePriorityApplyYn;	
	
	/** 우선순위적용여부 유효성 */
	@Schema(description = "우선순위적용여부 유효성", example = "")
	private Integer priorityChk;
	
}