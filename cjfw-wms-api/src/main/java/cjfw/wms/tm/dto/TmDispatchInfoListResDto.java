package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.09.24 
 * @description : 배차 정보 목록 조회시 응답 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.24 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Schema(description = "배차계획목록 응답 DTO")
public class TmDispatchInfoListResDto {

    @Schema(description = "센터코드")
    private String dccode;
	
	@Schema(description = "배송일자")
	private String deliverydt;
	
	@Schema(description = "차량번호")
	private String carno;
	
	@Schema(description = "권역")
	private String districtcode;

	@Schema(description = "권역명")
	private String districtcodeNm;
	
	@Schema(description = "권역그")
	private String districtgroup;

	@Schema(description = "권역그룹명")
	private String districtgroupNm;
	
	@Schema(description = "계약유형")
	private String contractType;
	
	@Schema(description = "회차")
	private String priority;
	
	@Schema(description = "상태")
	private String status;
	
	@Schema(description = "popno")
	private String pop;
	
	@Schema(description = "배차상태")
	private String dispatchStatus;
	
	@Schema(description = "착지수")
	private String destinationCount;
	
	@Schema(description = "CBM")
	private String totalCbm;
	
	@Schema(description = "물동량")
	private String totalWeightKg;
	
	@Schema(description = "귀책 사유")
	private String reasonMsg;
	
	@Schema(description = "귀책 차량 코드")
	private String reasonCarNo;
	
	@Schema(description = "운송사코드")
	private String courier;
	
	@Schema(description = "운송사명")
	private String couriername;
	
	@Schema(description = "2차 운송사코드")
	private String caragentkey;
	
	@Schema(description = "2차 운송사명")
	private String caragentname;

    @Schema(description = "배송유형", example = "1")
    private String tmDeliveryType;

    @Schema(description = "변경전 popno")
    private String oriPop;

    @Schema(description = "변경전 차량번호")
    private String oriCarno;
}
