package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : OhEunbeom
 * @date : 2025.09.18
 * @description : 계획 경로 조회 응답 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.18 OhEunbeom      생성 </pre>
 */
@Data
@Schema(description = "계획 경로 조회 응답 DTO")
public class TmPlanRouteResDto {

    @Schema(description = "배송일자")
    private String deliverydt;

    @Schema(description = "차량번호")
    private String carno;

    @Schema(description = "회차번호")
    private String priority;

    @Schema(description = "거래처유형")
    private String custtype;

    @Schema(description = "이동경로")
    private String routePolyline;

}
