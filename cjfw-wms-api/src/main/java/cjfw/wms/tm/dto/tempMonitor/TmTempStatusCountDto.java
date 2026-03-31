package cjfw.wms.tm.dto.tempMonitor;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "온도 상태별 건수 DTO")
public class TmTempStatusCountDto {

    @Schema(description = "정상 건수")
    private int nomlCnt;

    @Schema(description = "이탈 건수")
    private int outCnt;

    @Schema(description = "확인불가 건수")
    private int naCnt;
}
