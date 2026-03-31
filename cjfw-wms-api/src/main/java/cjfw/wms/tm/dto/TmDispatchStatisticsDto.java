package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : AI Assistant
 * @date : 2025.09.17
 * @description : TM 배차 목록 통계 정보 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.17 AI Assistant          생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "TM 배차 목록 통계 정보 DTO")
public class TmDispatchStatisticsDto {

    /** 총 차량 수 */
    @Schema(
        description = "배차된 총 차량 수",
        example = "12"
    )
    private Integer totalVehicleCount;

    /** 총 주문 건수 */
    @Schema(
        description = "총 주문 건수",
        example = "150"
    )
    private Integer totalOrderCount;

    /** 완료된 주문 건수 */
    @Schema(
        description = "완료된 주문 건수",
        example = "89"
    )
    private Integer completedOrderCount;

    /** 진행 중인 주문 건수 */
    @Schema(
        description = "진행 중인 주문 건수",
        example = "61"
    )
    private Integer inProgressOrderCount;

    /** 지연된 주문 건수 */
    @Schema(
        description = "지연된 주문 건수",
        example = "8"
    )
    private Integer delayedOrderCount;

    /** 총 배송 거리 (km) */
    @Schema(
        description = "총 배송 거리 (km)",
        example = "456.8"
    )
    private Double totalDistanceKm;

    /** 총 배송 시간 (분) */
    @Schema(
        description = "총 예상 배송 시간 (분)",
        example = "720"
    )
    private Integer totalDurationMinutes;

    /** 총 중량 (kg) */
    @Schema(
        description = "총 중량 (kg)",
        example = "5420.5"
    )
    private Double totalWeightKg;

    /** 총 부피 (CBM) */
    @Schema(
        description = "총 부피 (CBM)",
        example = "890.2"
    )
    private Double totalCbm;

    /** 평균 적재율 (%) */
    @Schema(
        description = "평균 차량 적재율 (%)",
        example = "82.3"
    )
    private Double averageLoadingRate;

    /** 정시도착 차량 수 */
    @Schema(
        description = "정시도착한 차량 수",
        example = "9"
    )
    private Integer onTimeVehicleCount;

    /** 정시도착률 (%) */
    @Schema(
        description = "정시도착률 (%)",
        example = "75.0"
    )
    private Double onTimeDeliveryRate;

    /** 차량 상태별 통계 */
    @Schema(
        description = "차량 상태별 개수"
    )
    private VehicleStatusStatisticsDto vehicleStatusStats;

    /** 배차 상태별 통계 */
    @Schema(
        description = "배차 상태별 개수"
    )
    private DispatchStatusStatisticsDto dispatchStatusStats;

    /** 우선순위별 통계 */
    @Schema(
        description = "우선순위별 개수"
    )
    private PriorityStatisticsDto priorityStats;

    /** 차량 상태별 통계 */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "차량 상태별 통계")
    public static class VehicleStatusStatisticsDto {
        private Integer dispatched;    // 배차완료
        private Integer loading;       // 상차중
        private Integer departed;      // 출발
        private Integer arrived;       // 도착
        private Integer completed;     // 완료
    }

    /** 배차 상태별 통계 */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "배차 상태별 통계")
    public static class DispatchStatusStatisticsDto {
        private Integer active;        // 활성
        private Integer completed;     // 완료
        private Integer cancelled;     // 취소
    }

    /** 우선순위별 통계 */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "우선순위별 통계")
    public static class PriorityStatisticsDto {
        private Integer priorityA;     // A등급
        private Integer priorityB;     // B등급
        private Integer priorityC;     // C등급
    }
}
