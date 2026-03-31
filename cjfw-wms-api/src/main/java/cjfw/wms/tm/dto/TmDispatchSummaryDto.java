package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : AI Assistant
 * @date : 2025.01.14
 * @description : TM 배차 요약 정보 DTO
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
@Schema(description = "배차 요약 정보 DTO")
public class TmDispatchSummaryDto {

    /** 총 주문 수 */
    @Schema(
        description = "총 주문 수",
        example = "150"
    )
    private Integer totalOrders;

    /** 배정된 주문 수 */
    @Schema(
        description = "배정된 주문 수",
        example = "145"
    )
    private Integer assignedOrders;

    /** 미배정 주문 수 */
    @Schema(
        description = "미배정 주문 수",
        example = "5"
    )
    private Integer unassignedOrders;

    /** 사용된 차량 수 */
    @Schema(
        description = "사용된 차량 수",
        example = "12"
    )
    private Integer usedVehicles;

    /** 총 거리 (km) */
    @Schema(
        description = "총 예상 이동 거리 (km)",
        example = "1250.5"
    )
    private Double totalDistanceKm;

    /** 총 소요 시간 (분) */
    @Schema(
        description = "총 예상 소요 시간 (분)",
        example = "480"
    )
    private Integer totalDurationMinutes;

    /** 평균 차량 적재율 (%) */
    @Schema(
        description = "평균 차량 적재율 (%)",
        example = "85.2"
    )
    private Double avgLoadingRate;
    
    /** 미배차 총 무게 (kg) */
    @Schema(
        description = "미배차 총 무게 (kg)",
        example = "2150.5"
    )
    private Double totalUnassignedWeight;
    
    /** 필요한 1t 용차 대수 */
    @Schema(
        description = "필요한 1t 용차 대수 (900kg 기준)",
        example = "3"
    )
    private Integer requiredCharterVehicles;
}
