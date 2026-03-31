package cjfw.wms.tm.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : han@wemeetmobility.com
 * @date : 2025.11.28
 * @description : TM 배차 업로드용 T-map 경로 계산 요청 DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "TM 배차 업로드용 T-map 경로 계산 요청 DTO")
public class TmapDispatchRouteReqDto {

    @Schema(description = "요청 좌표 타입 (WGS84GEO)")
    @Builder.Default
    private String reqCoordType = "WGS84GEO";

    @Schema(description = "응답 좌표 타입 (WGS84GEO)")
    @Builder.Default
    private String resCoordType = "WGS84GEO";

    @Schema(description = "경로 좌표 반환 옵션 (0: 경로 좌표 요청, 1: 경로 좌표 요청 안함)")
    @Builder.Default
    private String coordinateFlag = "0";

    @Schema(description = "출발지 이름 (DC명)")
    private String startName;

    @Schema(description = "출발지 경도 (X)")
    private String startX;

    @Schema(description = "출발지 위도 (Y)")
    private String startY;

    @Schema(description = "출발 시간 (YYYYMMDDHHMM)")
    private String startTime;

    @Schema(description = "도착지 이름 (DC명)")
    private String endName;

    @Schema(description = "도착지 경도 (X)")
    private String endX;

    @Schema(description = "도착지 위도 (Y)")
    private String endY;

    @Schema(description = "검색 옵션 (0: 교통최적+최소시간, 10: 최단거리)")
    @Builder.Default
    private String searchOption = "0";

    @Schema(description = "차량 타입")
    @Builder.Default
    private String carType = "0";

    @Schema(description = "경유지 리스트 (priority 순서대로 정렬)")
    private List<ViaPoint> viaPoints;

    /**
     * 경유지 정보
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ViaPoint {

        @Schema(description = "경유지 ID (실착지코드)")
        private String viaPointId;

        @Schema(description = "경유지 이름 (실착지명)")
        private String viaPointName;

        @Schema(description = "경유지 경도 (X)")
        private String viaX;

        @Schema(description = "경유지 위도 (Y)")
        private String viaY;

        @Schema(description = "경유지 소요 시간 (초, 기본 600초 = 10분)")
        @Builder.Default
        private String viaTime = "600";

        @Schema(description = "우선순위")
        private Integer priority;
    }
}
