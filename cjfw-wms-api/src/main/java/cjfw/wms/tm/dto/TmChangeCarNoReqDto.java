package cjfw.wms.tm.dto;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : AI Assistant
 * @date : 2025.01.14
 * @description : 차량번호 변경 요청 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.01.14 AI Assistant          생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "차량번호 변경 요청 DTO")
public class TmChangeCarNoReqDto extends CommonProcedureDto {

	@Schema(description = "물류센터 코드", example = "2600", required = true)
	@NotBlank(message = "물류센터 코드는 필수입니다")
	private String dccode;
	
	@Schema(description = "배송일자 (YYYYMMDD 형식)", example = "20250120", required = true)
	@NotBlank(message = "배송일자는 필수입니다")
	private String deliveryDate;
	
	@Schema(description = "배송유형", example = "1")
	private String tmDeliveryType;

    @Schema(description = "차량번호 (기존 차량번호)", example = "123가4567", required = true)
    @NotBlank(message = "기존 차량번호는 필수입니다")
    private String oldCarno;

    private String oldPop; // where절에 기존pop도 포함하여 사용
	
	@Schema(description = "차량번호 (변경할 차량번호)", example = "123가4567")
	private String carno;
	
	@Schema(description = "귀책차량번호", example = "789나0123")
	private String reasonCarNo;
	
	@Schema(description = "귀책사유", example = "차량 고장으로 인한 차량 변경")
	private String reasonMsg;
	
	@Schema(description = "POP번호", example = "POP001")
	private String pop;

    @Schema(description = "회차", example = "1")
    private String priority;

    @Schema(description = "사용자ID(공통)", example = "testID")
    private String userId;

    @Schema(description = "운송사", example = "1009880")
    private String courier;

    @Schema(description = "2차 운송사", example = "서원물류")
    private String caragentkey;

    @Schema(description = "계약유형")
    private String contractType;

    @Schema(description = "SP 타입")
    private String processType;
}

