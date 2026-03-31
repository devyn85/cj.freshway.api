package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : OhEunbeom
 * @date : 2025.09.10
 * @description : 배송일자별 차량 모니터링 전체 카운트 조회 요청 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.10 OhEunbeom      생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "배송일자별 차량 모니터링 전체 카운트 조회 요청 DTO")
public class TmVehicleStatusCountReqDto {

    /**센터코드*/
    @Schema(description = "센터코드")
    private String dccode;

    /**배송일자*/
    @Schema(description = "배송일자", required = true)
    private String deliverydt;

    /**배송일자*/
    @Schema(description = "배송유형")
    private String tmDeliverytype;
}
