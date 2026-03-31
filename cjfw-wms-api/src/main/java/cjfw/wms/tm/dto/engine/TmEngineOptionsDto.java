package cjfw.wms.tm.dto.engine;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 앤진 options 필드 설명
 * optimization_level("fast"|"standard"|"thorough"): 최적화 수준(속도 vs 품질 트레이드오프).
 * time_window_flexibility(int, 분): 시간창 유연성(버퍼). 크면 시간 제약을 더 느슨하게 평가.
 * enable_virtual_vehicles(bool): 1차 배차 후 미배차가 있을 때 가상차 자동 생성 여부.
 * max_virtual_vehicles(int|null): 생성 가능한 가상차 최대 개수(enable_virtual_vehicles가 true일 때 적용).
 * pop_grouping_enabled(bool): POP 코드 기반 스킬 부여/그룹핑 활성화.
 * priority_weight(float): 우선순위 요소 가중치.
 * distance_weight(float): 거리 요소 가중치.
 * time_weight(float): 시간(소요/제약) 요소 가중치.
 * multi_trip(object): 다회전 설정
 *  enabled(bool): 다회전 활성화
 *  reload_cost_per_trip(int): 한 번 재출발 시 비용
 *  default_reload_time_seconds(int): 기본 재출발 준비시간(초)
 *  default_max_trips_per_vehicle(int): 차량별 기본 최대 회전 수
 * @return
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "엔진 최적화 옵션 DTO")
public class TmEngineOptionsDto {

    @Schema(description = "거리 가중치", example = "1")
    private double distance_weight;

    @Schema(description = "가상 차량 사용 여부", example = "true")
    private boolean enable_virtual_vehicles;

    @Schema(description = "최대 가상 차량 수", example = "10")
    private int max_virtual_vehicles;

    @Schema(description = "최적화 레벨", example = "standard", allowableValues = {"standard", "advanced"})
    private String optimization_level;

    @Schema(description = "POP 그룹핑 사용 여부", example = "true")
    private boolean pop_grouping_enabled;

    @Schema(description = "우선순위 가중치", example = "1.5")
    private double priority_weight;

    @Schema(description = "시간 가중치", example = "1.2")
    private double time_weight;

    @Schema(description = "시간 창 유연성 (분)", example = "30")
    private int time_window_flexibility;

    private int max_locations_per_pop; // 최대 POP 수, pop_grouping_enabled = true 일 때 유효한 값 added 2025-12-30

    private MultiTrip multi_trip;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MultiTrip {
        private boolean enabled;
        private int reload_cost_per_trip;
        private int default_reload_time_seconds;
        private int default_max_trips_per_vehicle;

    }

    public static TmEngineOptionsDto getDefault() {
        return TmEngineOptionsDto.builder()
                .distance_weight(1)
                .enable_virtual_vehicles(false)
                .max_virtual_vehicles(10)
                .optimization_level("standard")
                .pop_grouping_enabled(false)
                .max_locations_per_pop(50)
                .priority_weight(1.5)
                .time_weight(1.2)
                .time_window_flexibility(30)
                .multi_trip(MultiTrip.builder()
                    .enabled(true)
                    .reload_cost_per_trip(10000)
                    .default_reload_time_seconds(3600)
                    .default_max_trips_per_vehicle(2)
                    .build())
                .build();
    }
}