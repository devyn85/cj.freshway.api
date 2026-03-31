package cjfw.wms.tm.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.09.25 
 * @description : 배차 대상 차량 조회 요청 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.25 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "배차 대상 차량 조회 요청 DTO")
public class TmDispatchVehicleReqDto {
	
	@Schema(description = "센터코드")
	private String dccode;
	
	@Schema(description = "센터명")
	private String dcname;
	
	@Schema(description = "배송일자")
	private String deliveryDate;

    @Schema(description = "차량번호")
    private String carno;

    @Schema(description = "버튼호출정보")
    private String planEntryType;
	
}
