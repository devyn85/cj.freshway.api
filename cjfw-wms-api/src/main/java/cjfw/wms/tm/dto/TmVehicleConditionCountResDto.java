package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : OhEunbeom
 * @date : 2025.09.10
 * @description : 배송일자별 차량 모니터링 조건 카운트 조회 응답 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.10 OhEunbeom      생성 </pre>
 */
@Data
@Schema(description = "배송일자별 차량 모니터링 조건 카운트 조회 응답 DTO")
public class TmVehicleConditionCountResDto {

    /**배송상태*/
    @Schema(description = "배송상태")
    private String deliveryStatus;

    /**배송상태명*/
    @Schema(description = "배송상태명")
    private String deliveryStatusName;

    /**차량 수*/
    @Schema(description = "차량 수")
    private Integer cnt;

}
