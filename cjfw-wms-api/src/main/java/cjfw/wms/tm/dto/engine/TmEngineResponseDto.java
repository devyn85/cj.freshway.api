package cjfw.wms.tm.dto.engine;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import cjfw.wms.tm.dto.TmSetDispatchUnassignedOrderResDto;
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
@Schema(description = "Engine 최적화 응답 DTO (EngineResponseSchema 호환)")
public class TmEngineResponseDto {

    /** 응답 코드 */
    @Schema(
        description = "응답 코드 (200: 성공, 500: 실패)",
        example = "200"
    )
    private Integer code;

    /** 요약 정보 */
    @Schema(
        description = "최적화 결과 요약"
    )
    private TmEngineSummaryDto summary;

    /** 미배정 주문/작업 목록 */
    @Schema(
        description = "배정되지 않은 주문/작업 목록"
    )
    private List<TmSetDispatchUnassignedOrderResDto> unassigned;

    /** 차량별 경로 목록 */
    @Schema(
        description = "차량별 경로 목록"
    )
    private List<TmEngineRouteDto> routes;

    @Schema(description = "최대 중량 사용 여부(차량요약정보 퍼센테이지 사용)")
    @Builder.Default
    private boolean isOnMaxWeight = true;
}