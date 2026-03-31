package cjfw.wms.tm.dto.engine;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : AI Assistant
 * @date : 2025.01.14
 * @description : Engine 차량 정보 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.01.14 AI Assistant          생성
 * 
 * Vehicle 객체
    id(string, 필수)
    pop(string, 선택)
    vehicle_type("fixed"|"yongcha", 필수)
    zones(string[], 선택)
    start_address/start_location([lon, lat], 선택)
    end_address/end_location([lon, lat], 선택)
    work_start_time/work_end_time(string|datetime, 선택)
    break_start_time/break_end_time(string|datetime, 선택)
    capacity(int[]≥0, 선택), skills(int[]≥0, 선택), preferred_skills(string[], 선택), car_number(string, 선택)
    gate_in_time(string|datetime, 선택): 센터 입차 시간
    gate_out_time(string|datetime, 선택): 센터 출차 시간
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Engine 차량 정보 DTO")
public class TmEngineVehicleDto {

	private String id;
    private String pop;
    /** 시작 위치 [경도, 위도] */
    @Schema(
        description = "차량 시작 위치 좌표 [경도, 위도]",
        example = "[127.0276, 37.4979]"
    )
    private List<Double> start_location;

    /** 종료 위치 [경도, 위도] */
    @Schema(
        description = "차량 종료 위치 좌표 [경도, 위도]",
        example = "[127.0276, 37.4979]"
    )
    private List<Double> end_location;

    /** 최대 적재 용량 [부피, 무게, 개수] */
    @Schema(
        description = "차량 최대 적재 용량 [부피, 무게, 개수]",
        example = "[100, 1000, 100]"
    )
    private List<Integer> capacity;

    /** 차량 스킬 */
    @Schema(
        description = "차량이 보유한 스킬 목록",
        example = "[]"
    )
    private List<String> skills;

    /** 차량 유형 */
    @Schema(
        description = "차량 유형 (fixed: 고정차, yongcha: 용차)",
        example = "fixed",
        allowableValues = {"fixed", "yongcha"}
    )
    private String vehicle_type;

    /** 근무 시작 시간 (YYYY-MM-DD HH:MM:SS) */
    @Schema(
        description = "차량 근무 시작 시간",
        example = "2025-01-20 08:00:00"
    )
    private String work_start_time;

    /** 근무 종료 시간 (YYYY-MM-DD HH:MM:SS) */
    @Schema(
        description = "차량 근무 종료 시간",
        example = "2025-01-20 18:00:00"
    )
    private String work_end_time;

    /** 소프트(우선순위) 스킬 : 고정배차 기능 처리 (고정이 아닌 우선배차 처리 함) */
    private List<String> preferred_skills;
    private String car_number;
    /** 존 목록 */  
    private List<String> zones;
    /** 시작 주소 */
    private String start_address;
    /** 종료 주소 */
    private String end_address;
    /** 휴게 시작 시간 */
    private String break_start_time;
    /** 센터 입차 시간 */
    private String gate_in_time;
    /** 센터 출차 시간 */
    private String gate_out_time;
    /** 다회전 시 최대 회전 수 */
    private int max_trips;

    /** 최대 착지 수 */
    @Schema(
            description = "차량 최대 착지수",
            example = "3"
    )
    private Integer max_stops;

    /** 최소 착지수 (이 값 이상의 착지점에 배송해야 함) */
    @Schema(
        description = "차량 최소 착지수",
        example = "3"
    )
    private Integer min_stops;

    private Integer max_visit_pops;
}
