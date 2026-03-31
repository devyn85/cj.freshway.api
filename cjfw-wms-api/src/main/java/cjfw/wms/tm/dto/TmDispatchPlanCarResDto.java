package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.10.17 
 * @description : 배차 가능 차량 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.17 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "배차 가능 차량 응답 DTO")
public class TmDispatchPlanCarResDto {
	
	@Schema(description = "테이블 키")
	private String serialkey;
	
	@Schema(description = "센터코드")
	private String dccode;
	
	@Schema(description = "배송일자")
	private String deliveryDate;
	
	@Schema(description = "배차유형")
	private String tmDeliveryType;
	
	@Schema(description = "근태코드")
	private String attendCd;
	
	@Schema(description = "차량번호")
	private String carno;
	
	@Schema(description = "다회전 Y/N")
	private String priorityYn;
	
	@Schema(description = "배송가능 Y/N")
	private String deliveryYn;
	
	@Schema(description = "조차")
	private String outGroupCd;
	
	@Schema(description = "배송 시작시간")
	private String drivingFromDate;
	
	@Schema(description = "배송 종료시간")
	private String drivingToDate;

	@Schema(description = "차량 계약유형코드")
	private String contractType;

	@Schema(description = "차량 계약유형명")
	private String contractTypeData1;

    @Schema(description = "차량 용적")
    private String carCapacity;

	@Schema(description = "기사명")
	private String driverName;
}
