package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.11.07 
 * @description : 수동 분할배차 요청 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.07 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "수동 분할배차 요청 DTO")
public class TmPlanUndispatchManualSplitReqDto {
	
	@Schema(description = "센터코드")
	private String dccode;
	
	@Schema(description = "실착지키")
	private String truthCustkey;
	
	@Schema(description = "배송일자")
	private String deliveryDate;
	
	@Schema(description = "배송유형")
	private String tmDeliveryType;
	
	@Schema(description = "배차상태 ',' 단위로 구분 다중검색")
	private String dispatchStatus;
	
	@Schema(description = "거래처 코드 ',' 단위로 구분 다중검색")
	private String custCode;
	
}
