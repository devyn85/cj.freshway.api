package cjfw.wms.tm.dto.tempMonitor;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "온도 모니터링 상세 조회 래퍼 응답 DTO")
public class TmTempMonitorDescWrapResDto {

    @Schema(description = "전체 건수")
    private long totalCount;

    @Schema(description = "상세 목록")
    private List<TmTempMonitorDescResDto> list;

    @Schema(description = "냉장 상태별 건수")
    private TmTempStatusCountDto refrig;

    @Schema(description = "냉동 상태별 건수")
    private TmTempStatusCountDto freeze;
}
