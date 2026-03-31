package cjfw.wms.tm.dto;

import cjfw.wms.common.extend.CommonProcedureDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.10.01 
 * @description : 배차 계획 저장 요청 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.01 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Schema(description = "배차 계획 저장 요청 DTO") 
public class TmPlanReqDto extends CommonProcedureDto {
	
	@Schema(description = "배송일자")
	private String deliveryDate;
	
	@Schema(description = "배송유형")
	private String deliveryType;
	
	@Schema(description = "배송유형")
	private String tmDeliveryType;
	
	@JsonProperty
	@Schema(description = "가배차 저장 플래그")
	private boolean isPreDispatch;

	@JsonProperty
	@Schema(description = "부분 저장 플래그")
	private boolean isPartialSave;

	@Schema(description = "미배차 전환 주문 ID 목록 (TRUTHCUSTKEY)")
	private String[] unassignedOrders;

	@Schema(description = "센터코드")
	private String dccode;
	
	@Schema(description = "차량 정보")
	private Vehicle[] vehicles;
	
	@Schema(description = "고객사 코드")
	private String custCode;
    private String userId;
	
	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Vehicle{
		@Schema(description = "차량번호")
		private String carno; 
		
		@Schema(description = "출차그룹 코드")
		private String outGroupCd;
		
		@Schema(description = "회차순번")
		private String roundSeq;

		@Schema(description = "차량의 계약유형")
		private String contractType;
		
		@Schema(description = "경로")
		private TmPlanEtaStepReqDto[] steps;
	}
	
}
