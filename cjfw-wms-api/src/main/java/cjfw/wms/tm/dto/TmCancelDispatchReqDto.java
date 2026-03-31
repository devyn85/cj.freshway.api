package cjfw.wms.tm.dto;

import java.util.List;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : AI Assistant
 * @date : 2025.01.14
 * @description : 배차 취소 요청 DTO
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
@EqualsAndHashCode(callSuper = true)
@Schema(description = "배차 취소 요청 DTO")
public class TmCancelDispatchReqDto extends CommonProcedureDto {

	@Schema(description = "물류센터 코드", example = "2600", required = true)
	@NotBlank(message = "물류센터 코드는 필수입니다")
	private String dccode;
	
	@Schema(description = "배송일자 (YYYYMMDD 형식)", example = "20250120", required = true)
	@NotBlank(message = "배송일자는 필수입니다")
	private String deliveryDate;
	
	@Schema(description = "배송유형", example = "1")
	private String tmDeliveryType;

    @Schema(description = "문서유형", example = "WD")
    private String docType;
	
    @Schema(description = "사용자ID(공통)", example = "testID")
    private String userId;

    @Schema(description = "부분 저장 대상 차량 목록", hidden = true)
    private List<String> carnoList;

    @Schema(description = "부분 저장 취소 스코프 (TRUTHCUSTKEY 목록)", hidden = true)
    private List<String> cancelScope;
}

