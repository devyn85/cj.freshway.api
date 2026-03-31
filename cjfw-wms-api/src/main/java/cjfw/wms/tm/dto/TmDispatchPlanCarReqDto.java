package cjfw.wms.tm.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.10.17 
 * @description : 배차 가능 차량 조회 요청 DTO 
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
@Schema(description = "배차 가능 차량 조회 요청 DTO")
public class TmDispatchPlanCarReqDto extends CommonDto {
	
	@Schema(description = "센터코드(GET/SAVE)")
	private String dccode;
	
	@Schema(description = "배송일자(GET/SAVE)")
	private String deliveryDate;
	
	@Schema(description = "배차유형(GET/SAVE)")
	private String tmDeliveryType;
	
	@Schema(description = "검색조건(GET)")
	private String searchKeyword;
	
	@Schema(description = "내부 검색조건", hidden = true)
	private String[] searchCarno;
	
	@Schema(description = "차량번호(SAVE)")
	private String carno;
	
	@Schema(description = "다회전 Y/N(SAVE)")
	private String priorityYn;
	
	@Schema(description = "배송 Y/N(SAVE)")
	private String deliveryYn;
	
	@Schema(description = "배송시작일자(SAVE)", example = "YYYY-MM-DD HH:mm:ss")
	private String drivingFromDate;
	
	@Schema(description = "배송도착일자(SAVE)", example = "YYYY-MM-DD HH:mm:ss")
	private String drivingToDate;
	
	@Schema(description = "조차 코드(SAVE)")
	private String outGroupCd;
	
	@Schema(description = "근태 코드(SAVE)")
	private String attendCd;

    private List<String> searchContractType;
}
