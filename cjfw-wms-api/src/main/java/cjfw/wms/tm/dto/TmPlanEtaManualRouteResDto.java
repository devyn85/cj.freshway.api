package cjfw.wms.tm.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.10.28 
 * @description : TMap 경로 계산 응답 Dto 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.28 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "TMap 경로 계산 응답 DTO")
public class TmPlanEtaManualRouteResDto {

    private Properties properties;

    @Schema(description = "Feature 리스트")
    private List<Feature> features;

    @Data
    public static class Properties {
        @Schema(description = "총 이동 거리")
        private String totalDistance;

        @Schema(description = "총 소요 시간")
        private String totalTime;

        @Schema(description = "총 요금")
        private String totalFare;
    }

    @Data
    public static class Feature {

        @Schema(description = "Feature 타입 (Point, LineString)")
        private String type;

        @Schema(description = "지오메트리 정보")
        private Geometry geometry;

        @Schema(description = "Feature 속성 정보")
        private Properties properties;

        @Data
        public static class Geometry {

            @Schema(description = "Geometry 타입 (Point, LineString)")
            private String type;

            @Schema(description = "좌표 정보 (Point: [x, y], LineString: [[x1,y1],[x2,y2],...])")
            private Object coordinates;
        }

        @Data
        public static class Properties {

            @Schema(description = "Feature 인덱스")
            private String index;

            @Schema(description = "경유지 ID")
            private String viaPointId;

            @Schema(description = "경유지 이름")
            private String viaPointName;

            @Schema(description = "경유지 상세 주소")
            private String viaDetailAddress;

            @Schema(description = "그룹 키")
            private String groupKey;

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

            @Schema(description = "요금")
            private String Fare;

            @Schema(description = "POI ID")
            private String poiId;

            @Schema(description = "포인트 타입 (S: 출발, E: 도착, B1: 경유지 등)")
            private String pointType;
        }
    }
}
