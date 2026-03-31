package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : OhEunbeom
 * @date : 2025.09.18
 * @description : 계획 경로 조회 요청 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.18 OhEunbeom      생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "계획 경로 조회 요청 DTO")
public class TmPlanRouteReqDto {

    /**센터코드*/
    @Schema(description = "센터코드", required = true, example = "FW00")
    private String dccode;

    /**배송일자*/
    @Schema(description = "배송일자", required = true)
    private String deliverydt;

    /**배송유형*/
    @Schema(description = "배송유형", required = true)
    private String tmDeliverytype;

    /**차량번호*/
    @Schema(description = "차량번호", required = true)
    private String carno;

    /**회차번호*/
    @Schema(description = "회차번호", required = true)
    private String priority;
}
