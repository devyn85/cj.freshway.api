package cjfw.wms.om.dto;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.09.19
 * @description : 주문마감현황 기능을 구현한 Req DTO Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.19        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "주문마감현황 요청") 
public class OmCloseReqDto extends CommonProcedureDto {
	@Schema(description = "멀티 물류센터 코드", example = "2600")
    private String multiDcCode[];
	
	@Schema(description = "물류센터 코드", example = "2600")
    private String dcCode;
	
	@Schema(description = "전표일자", nullable = false, example = "")
	private String slipDt;
	
	@Schema(description = "배송일자", example = "20250725")
    private String deliveryDt;
	
    @Schema(description = "문서 유형", example = "WD")
    private String docType;
	
    @Schema(description = "작업 프로세스 코드", example = "PO")
    private String workProcessCode;
    
    @Schema(description = "플랜트 코드", example = "PLANT01")
    private String plant;
    
    @Schema(description = "마감 코드", example = "00")
    private String closeKey;
}
