package cjfw.wms.tm.dto.engine;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "경로 단계 정보 DTO")
public class TmRouteStepDto {

    /** 단계 유형 */
    @Schema(
        description = "경로 단계 유형 (pickup, delivery, start, end)",
        example = "pickup"
    )
    private String stepType;

    /** 위치 좌표 */
    @Schema(
        description = "위치 좌표 [경도, 위도]",
        example = "[127.0276, 37.5172]"
    )
    private List<Double> location;

    /** 주소 */
    @Schema(
        description = "위치 주소",
        example = "서울특별시 강남구 역삼동 123-45"
    )
    private String address;

    /** 도착 시간 */
    @Schema(
        description = "도착 시간 (YYYY-MM-DD HH:MM:SS)",
        example = "2025-01-20 10:30:00"
    )
    private String arrivalTime;

    /** 출발 시간 */
    @Schema(
        description = "출발 시간 (YYYY-MM-DD HH:MM:SS)",
        example = "2025-01-20 10:45:00"
    )
    private String departureTime;

    /** 소요 시간 */
    @Schema(
        description = "해당 지점 소요 시간 (초)",
        example = "900"
    )
    private Integer duration;

    /** 서비스 시간 */
    @Schema(
        description = "서비스 수행 시간 (초)",
        example = "600"
    )
    private Integer serviceTime;

    /** 이전 지점에서의 거리 */
    @Schema(
        description = "이전 지점에서의 거리 (km)",
        example = "5.2"
    )
    private Double distanceFromPrevious;

    /** 관련 주문 ID */
    @Schema(
        description = "관련 주문 ID",
        example = "WD20250120_001"
    )
    private String shipmentId;

    /** 설명 */
    @Schema(
        description = "단계 설명",
        example = "고객 A 픽업"
    )
    private String description;
}
