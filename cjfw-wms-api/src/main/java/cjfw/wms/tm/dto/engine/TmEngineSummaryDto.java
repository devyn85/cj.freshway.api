package cjfw.wms.tm.dto.engine;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Engine 응답 요약 정보")
public class TmEngineSummaryDto {

    /** 총 비용 */
    @Schema(description = "총 비용")
    private Integer cost;

    /** 경로 수 */
    @Schema(description = "생성된 경로 수")
    private Integer routes;

    /** 미배정 수 */
    @Schema(description = "미배정 주문/작업 수")
    private Integer unassigned;

    /** 설정 시간 */
    @Schema(description = "설정 시간")
    private Integer setup;

    /** 서비스 시간 */
    @Schema(description = "총 서비스 시간")
    private Integer service;

    /** 총 시간 */
    @Schema(description = "총 소요 시간")
    private Integer duration;

    /** 대기 시간 */
    @Schema(description = "총 대기 시간")
    private Integer waitingTime;

    /** 우선순위 */
    @Schema(description = "우선순위")
    private Integer priority;

    /** 위반 사항 */
    @Schema(description = "제약 조건 위반 사항")
    private List<Object> violations;

    /** 총 거리 */
    @Schema(description = "총 이동 거리")
    private Integer distance;

    /** 계산 시간 */
    @Schema(description = "계산 시간 정보")
    private Object computingTimes;
    
    /** 미배차 총 무게 (kg) */
    @Schema(description = "미배차 총 무게 (kg)")
    private Double totalUnassignedWeight;
    
    /** 필요한 1t 용차 대수 */
    @Schema(description = "필요한 1t 용차 대수 (900kg 기준)")
    private Integer requiredCharterVehicles;
}
