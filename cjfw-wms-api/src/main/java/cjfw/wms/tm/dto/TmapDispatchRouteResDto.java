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
 * @description : TM 배차 업로드용 T-map 경로 계산 응답 DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "TM 배차 업로드용 T-map 경로 계산 응답 DTO")
public class TmapDispatchRouteResDto {

    @Schema(description = "총 이동 거리 (미터)")
    private Integer totalDistance;

    @Schema(description = "총 소요 시간 (초)")
    private Integer totalTime;

    @Schema(description = "Feature 리스트 (경유지별 경로 정보)")
    private List<Feature> features;

    /**
     * T-map API Feature (Point 또는 LineString)
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Feature {

        @Schema(description = "Feature 타입 (Point 또는 LineString)")
        private String type;

        @Schema(description = "지오메트리 정보")
        private Geometry geometry;

        @Schema(description = "Feature 속성 정보")
        private Properties properties;

        /**
         * Geometry 정보 (좌표)
         */
        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Geometry {

            @Schema(description = "Geometry 타입 (Point 또는 LineString)")
            private String type;

            @Schema(description = "좌표 정보 (Point: [x, y], LineString: [[x1,y1],[x2,y2],...])")
            private Object coordinates;
        }

        /**
         * Properties 정보 (경유지 상세 정보)
         */
        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Properties {

            @Schema(description = "Feature 인덱스")
            private String index;

            @Schema(description = "경유지 ID (실착지코드)")
            private String viaPointId;

            @Schema(description = "경유지 이름 (실착지명)")
            private String viaPointName;

            @Schema(description = "도착 시간 (YYYYMMDDHHMMSS)")
            private String arriveTime;

            @Schema(description = "완료 시간 (YYYYMMDDHHMMSS)")
            private String completeTime;

            @Schema(description = "거리 (미터)")
            private String distance;

            @Schema(description = "소요 시간 (초)")
            private String time;

            @Schema(description = "배달 시간 (초)")
            private String deliveryTime;

            @Schema(description = "대기 시간 (초)")
            private String waitTime;

            @Schema(description = "포인트 타입 (S: 출발, E: 도착, B1~BN: 경유지)")
            private String pointType;
        }
    }

    /**
     * TM_PLAN_ROUTE INSERT용 간소화된 경로 정보
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RouteInfo {

        @Schema(description = "목적지 실착지코드")
        private String toCustKey;

        @Schema(description = "주행거리 (미터)")
        private Integer driveDistance;

        @Schema(description = "주행시간 (초)")
        private Integer driveTime;

        @Schema(description = "경로 좌표 (Polyline)")
        private String routePolyline;

        @Schema(description = "도착 예정 시간 (YYYYMMDDHHMMSS)")
        private String toExpctDate;

        @Schema(description = "우선순위")
        private Integer priority;
    }
}
