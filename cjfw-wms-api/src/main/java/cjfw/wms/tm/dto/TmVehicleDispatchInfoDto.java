package cjfw.wms.tm.dto;

import java.util.List;

import cjfw.wms.tm.dto.engine.TmEngineStepDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : AI Assistant
 * @date : 2025.09.18
 * @description : TM 차량별 배차 정보 DTO (스크린샷 기준 완전한 차량 정보)
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.18 AI Assistant          생성
 * </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "차량별 배차 정보 (완전한 차량 데이터)")
public class TmVehicleDispatchInfoDto {

    /** 차량번호 */
    @Schema(
        description = "차량번호",
        example = "경기105바1234",
        nullable = false
    )
    private String carno;
    
    @Schema(description = "식별용 차량아이디")
    private String uniqueId;

    /** 차량명/운전자명 */
    @Schema(
        description = "차량명 또는 운전자명",
        example = "박상현",
        nullable = true
    )
    private String vehicleName;

    /** 차량 유형 */
    @Schema(
        description = "차량 유형 (고정, 1조, 2조 등)",
        example = "고정",
        nullable = true
    )
    private String vehicleType;
    
    /** 계약 유형 코드*/
    @Schema(
        description = "차량 유형 (고정, 1조, 2조 등)",
        example = "고정",
        nullable = true
    )
    private String contractType;


    /** 차량 그룹 */
    @Schema(
        description = "차량 그룹 정보",
        example = "1조",
        nullable = true
    )
    private String vehicleGroup;
    
    /** 조차 코드 */
    @Schema(
        description = "조차 코드",
        example = "10",
        nullable = true
    )
    private String outGroupCd;

    /** 최대 중량 */
    @Schema(description = "차량 최대 중량 (kg)", example = "5000", nullable = true)
    private String maxWeight;
    
    /** 최대 착지수 */
    @Schema(description = "차량 최대 착지수", example = "10", nullable = true)
    private String maxLanding;
    
    /** 최대 CBM */
    @Schema(description = "차량 최대 용적 (CBM)", example = "50000", nullable = true)
    private String maxCbm;
    
    @Schema(description = "차량 적정 팔레트", example = "100", nullable = true)
    private String optPlt;

    /** 최대 중량 */
    @Schema(
        description = "적재량",
        example = "3.5톤",
        nullable = true
    )
    private String carCapacity;

    /** 적재 중량 */
    @Schema(
        description = "현재 적재 중량 (kg)",
        example = "500",
        nullable = true
    )
    private String loadedWeight;

    /** 중량 사용률 */
    @Schema(
        description = "중량 사용률 (%)",
        example = "10",
        nullable = true
    )
    private Integer weightUsagePercent;

    /** 적재 CBM */
    @Schema(
        description = "현재 적재 용적 (CBM)",
        example = "500",
        nullable = true
    )
    private String loadedCbm;

    /** CBM 사용률 */
    @Schema(
        description = "용적 사용률 (%)",
        example = "1",
        nullable = true
    )
    private Integer cbmUsagePercent;

    /** 주문 건수 */
    @Schema(
        description = "배정된 주문 건수",
        example = "10",
        nullable = true
    )
    private Integer orderCount;

    /** 소요 시간 (분) */
    @Schema(
        description = "총 소요 시간 (분)",
        example = "150",
        nullable = true
    )
    private Integer totalTimeMinutes;

    /** 소요 시간 표시 */
    @Schema(
        description = "소요 시간 표시 (예: 2시간 30분)",
        example = "2시간 30분",
        nullable = true
    )
    private String totalTimeDisplay;

    /** 총 거리 (km) */
    @Schema(
        description = "총 이동 거리 (km)",
        example = "20.3",
        nullable = true
    )
    private Double totalDistanceKm;

    /** 경로 단계들 */
    @Schema(
        description = "경로 단계 목록"
    )
    private List<TmEngineStepDto> steps;

    /** 비용 */
    @Schema(
        description = "경로 비용",
        example = "12000"
    )
    private Integer cost;

    /** 설정 시간 */
    @Schema(
        description = "설정 시간 (초)",
        example = "300"
    )
    private Integer setup;

    /** 소요 시간 (초) */
    @Schema(
        description = "총 소요 시간 (초)",
        example = "14400"
    )
    private Integer duration;

    /** 대기 시간 */
    @Schema(
        description = "총 대기 시간 (초)",
        example = "0"
    )
    private Integer waitingTime;

    /** 우선순위 */
    @Schema(
        description = "우선순위",
        example = "1"
    )
    private Integer priority;
    
    @Schema(description = "회차 순번", example = "1")
    @Builder.Default
    private Integer roundSeq = 1;

    @Schema(description = "2회전 배차 완료 여부")
    private boolean isAlreadyRounded;

    @Schema(description = "경로 재계산 필요 여부")
    private boolean needRecalculation;

    /** 위반 사항 */
    @Schema(
        description = "제약 조건 위반 사항"
    )
    private List<Object> violations;

    /** 설명 */
    @Schema(
        description = "경로 설명",
        example = "차량 경기105바1234의 배송 경로"
    )
    private String description;

    /** 지오메트리 */
    @Schema(
        description = "전체 경로 지오메트리 (WKT LineString 또는 기타 형태)",
        example = "LINESTRING(127.0276 37.4979, 127.0356 37.5051, 127.0456 37.5123)",
        nullable = true
    )
    private String geometry;

    /** 거리 (미터) */
    @Schema(
        description = "총 이동 거리 (미터)",
        example = "45200"
    )
    private Integer distance;

    @Schema(description = "운전자이름")
    private String drivername;

    @Schema(description = "운전자 연락처")
    private String phone1;

    // 차량 고유 아이디 생성 
    public void createUniqueId() {
    	this.uniqueId = this.carno + "-" + this.roundSeq.toString();
    }
}
