package cjfw.wms.tm.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.10.28 
 * @description : TMap 경로 계산 요청 Dto 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.28 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@NoArgsConstructor            
@AllArgsConstructor
@Schema(description = "TMap 경로 계산 요청 DTO")
public class TmPlanEtaManualRouteReqDto {
	
    @Schema(description = "요청 좌표 타입 (예: WGS84GEO)")
    private String reqCoordType;

    @Schema(description = "응답 좌표 타입 (예: WGS84GEO)")
    private String resCoordType;
    
    @Schema(description = "경로 좌표 반환 옵션 (0 경로 좌표 요청 / 1 경로 좌표 요청 안함)")
    private String coordinateFlag;

    @Schema(description = "출발지 이름")
    private String startName;

    @Schema(description = "출발지 X 좌표")
    private String startX;

    @Schema(description = "출발지 Y 좌표")
    private String startY;

    @Schema(description = "출발 시간 (YYYYMMDDHHMM)")
    private String startTime;

    @Schema(description = "도착지 이름")
    private String endName;

    @Schema(description = "도착지 X 좌표")
    private String endX;

    @Schema(description = "도착지 Y 좌표")
    private String endY;

    @Schema(description = "도착지 POI ID")
    private String endPoiId;

    @Schema(description = "검색 옵션")
    private String searchOption;

    @Schema(description = "차량 타입")
    private String carType;

    @Schema(description = "경유지 리스트")
    private List<ViaPoint> viaPoints;

    @Data
    public static class ViaPoint {

        @Schema(description = "경유지 ID")
        private String viaPointId;

        @Schema(description = "경유지 이름")
        private String viaPointName;

        @Schema(description = "경유지 상세 주소")
        private String viaDetailAddress;

        @Schema(description = "경유지 X 좌표")
        private String viaX;

        @Schema(description = "경유지 Y 좌표")
        private String viaY;

        @Schema(description = "경유지 POI ID")
        private String viaPoiId;

        @Schema(description = "경유지 소요 시간 (초)")
        private String viaTime;

        @Schema(description = "희망 출발 시간")
        private String wishStartTime;

        @Schema(description = "희망 종료 시간")
        private String wishEndTime;
    }
    
}
