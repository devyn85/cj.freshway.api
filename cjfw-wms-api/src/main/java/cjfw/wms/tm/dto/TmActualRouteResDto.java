package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : OhEunbeom
 * @date : 2025.09.18
 * @description : 실제 이동경로 조회 응답 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.18 OhEunbeom      생성 </pre>
 */
@Data
@Schema(description = "실제 이동경로 조회 응답 DTO")
public class TmActualRouteResDto {

    /**차량번호*/
    @Schema(description = "차량번호", example = "TEST01가1234")
    private String carno;

    /**이동일자*/
    @Schema(description = "이동일자", example = "20241201")
    private String tripDate;

    /**시작시간*/
    @Schema(description = "시작시간", example = "09:00")
    private String startTime;

    /**종료시간*/
    @Schema(description = "종료시간", example = "18:30")
    private String endTime;

    /**좌표점 개수*/
    @Schema(description = "좌표점 개수", example = "120")
    private Integer pointCount;

    /**실제 이동경로(GeoJSON LineString 형식)*/
    @Schema(description = "실제 이동경로(GeoJSON LineString 형식)")
    private String geojsonLinestring;

}
