package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.10.29 
 * @description : 거래처 상세 팝업 메모 요청 API 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.29 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "거래처 상세 팝업 메모 요청 API")
public class TmPlanCustomerDetailPopupMemoReqDto {
	
	@Schema(description = "거래처키")
	private String custkey;
	
	@Schema(description = "고객사코드")
	private String storerkey;
	
	@Schema(description = "거래처유형")
	private String custtype;
	
	@Schema(description = "배송일자")
	private String deliveryDate;
	
	@Schema(description = "배송유형")
	private String tmDeliveryType;
	
	@Schema(description = "센터코드")
	private String dccode;
	
	@Schema(description = "배차상태")
	private String dispatchStatus;
	
	@Schema(description = "차량번호(배차 저장 이전 요청 X)", nullable = true)
	private String carno;

	@Schema(description = "메모 text")
	private String memo;
	
	
}
