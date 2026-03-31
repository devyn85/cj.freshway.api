package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : OhEunbeom
 * @date : 2025.09.10
 * @description : 센터별 차량 모니터링 조회 응답 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.10 OhEunbeom      생성 </pre>
 */
@Data
@Schema(description = "센터별 차량 모니터링 조회 응답 DTO")
public class TmVehicleMonitoringResDto {

    /**센터코드*/
    @Schema(description = "센터코드")
    private String dccode;

    /**센터명*/
    @Schema(description = "센터명")
    private String dcname;

    /**차량 수*/
    @Schema(description = "차량 수")
    private Integer cnt;

}
