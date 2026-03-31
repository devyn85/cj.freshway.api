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
 * @description : 실제 이동경로 조회 요청 DTO
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
@Schema(description = "실제 이동경로 조회 요청 DTO")
public class TmActualRouteReqDto {

    @Schema(description = "센터코드")
    private String dccode;

    @Schema(description = "배송일자")
    private String deliverydt;

    @Schema(description = "차량번호")
    private String carno;

    @Schema(description = "회차")
    private String priority;

    @Schema(description = "배송유형")
    private String tmDeliverytype;

}
