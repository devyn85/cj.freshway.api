package cjfw.wms.om.dto;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.08.08 
 * @description : 마감기준시간 콤보데이터 조회과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.08 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "마감기준시간 콤보데이터 조회")
public class OmCloseMonitoringResCloseTimeDto extends CommonProcedureDto {
	@Schema(description = "dccode", example = "")
    private String dccode;
    @Schema(description = "basecode", example = "")
    private String basecode;
    @Schema(description = "basedescr", example = "")
    private String basedescr;
}
