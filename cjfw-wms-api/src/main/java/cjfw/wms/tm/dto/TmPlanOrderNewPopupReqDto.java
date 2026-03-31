package cjfw.wms.tm.dto;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net)
 * @date : 2025.10.31
 * @description : 신규 주문 알람 조회 요청 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.31 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Schema(description = "신규 주문 알람 조회 요청 DTO")
public class TmPlanOrderNewPopupReqDto extends CommonProcedureDto {
	
	@Schema(description = "센터코드", example = "2600", nullable = false)
	private String dccode;
	
	@Schema(description = "배송 일자", example = "20250921", nullable = false)
	private String deliveryDate;
	
	@Schema(description = "배차유형", example = "1", nullable = false)
	private String tmDeliveryType;
	
	@Schema(description = "", example = "", nullable = true, hidden=true)
	private String[] custCodeList;

}
