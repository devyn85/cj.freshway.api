package cjfw.wms.tm.dto.engine;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import static cjfw.wms.tm.constant.TmConstant.CONTRACT_TYPE_TEMPORARY;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Engine 차량 경로 정보")
public class TmEngineRouteDto {

    /** 차량 ID */
    @Schema(description = "차량 ID")
    private String vehicle;

    /** 경로 단계들 */
    @Schema(description = "경로 단계 목록")
    private List<TmEngineStepDto> steps;

    /** 비용 */
    @Schema(description = "경로 비용")
    private Integer cost;

    /** 설정 시간 */
    @Schema(description = "설정 시간")
    private Integer setup;

    /** 소요 시간 */
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

    /** 설명 */
    @Schema(description = "경로 설명")
    private String description;

    /** 지오메트리 */
    @Schema(
            description = "전체 경로 지오메트리 (CJ 전처리 서버에서 제공하는 형태, WKT LineString 또는 기타 형태)",
            example = "LINESTRING(127.0276 37.4979, 127.0356 37.5051, 127.0456 37.5123)",
            nullable = true
    )
    private String geometry;

    /** 거리 */
    @Schema(description = "총 이동 거리")
    private Integer distance;

    public String getCarNo() {
        if (ObjectUtils.isEmpty(vehicle)) return "";
        if (vehicle.startsWith(CONTRACT_TYPE_TEMPORARY)) return vehicle;
        int lastIndex = vehicle.lastIndexOf("-");
        if (lastIndex != -1) return vehicle.substring(0, lastIndex);
        return vehicle;
    }

    public int getRoundSeq() {
        int roundSeq = 1;
        if (ObjectUtils.isEmpty(vehicle)) return roundSeq;
        if (vehicle.startsWith(CONTRACT_TYPE_TEMPORARY)) return roundSeq;
        int lastIndex = vehicle.lastIndexOf("-");
        if (lastIndex != -1) {
            try {
                return Integer.parseInt(vehicle.substring(lastIndex + 1));
            } catch (NumberFormatException e) {
                return roundSeq;
            }
        }
        return roundSeq;
    }
}