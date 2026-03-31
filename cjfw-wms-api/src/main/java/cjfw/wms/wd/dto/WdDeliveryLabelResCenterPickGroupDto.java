package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.12.19 
 * @description : 센터별 피킹그룹 조회 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.12.19 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "센터별 피킹그룹 조회 결과")
public class WdDeliveryLabelResCenterPickGroupDto{
    /**
     * commcd
     */
    @Schema(description = "commcd", example = "")
    private String commCd;
    /**
     * commnm
     */
    @Schema(description = "commnm", example = "")
    private String commNm;
    /**
     * dccode
     */
    @Schema(description = "dccode", example = "")
    private String dccode;
    
}
