package cjfw.wms.tm.dto.engine;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : AI Assistant
 * @date : 2026.01.07
 * @description : Engine 배송 작업 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2026.01.07 AI Assistant          생성
 *
 * Job 객체
 id(string, 선택): 주문 식별자
 description(string, 선택): 작업 설명/주소
 location(List<Double>) : 작업 위치 좌표
 delivery(List<Int>) : 배송량 [부피, 무게, 개수]
 service(int) : 서비스 시간(초)
 time_windows(List<String>) : 작업 가능 시간대
 skills(List<String>) : 필요스킬
 preferred_skills(List<String>) : 선호 스킬
 blocked_vehicle_ids(List<String>) : 배제할 차량 ID 목록
 priority(int) : 우선순위
 zones(string[]) : 담당권역
 otd_time(string|datetime, 선택): OTD(정시 배송) 목표 시간
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Engine 배송 작업 DTO")
public class TmEngineJobDto {

    /** 주문 ID */
    @Schema(
            description = "주문 고유 ID",
            example = "WD20250117_001"
    )
    private String id;

    private String pop;

    @Schema(description = "작업 설명/주소")
    private String description;

    @Schema(description = "작업 위치 좌표 [경도, 위도]", example = "[127.0276, 37.4979]")
    private List<Double> location;

    @Schema(description = "배송량 [부피, 무게, 개수]", example = "[5, 100, 25]")
    private List<Integer> delivery;

    @Schema(description = "서비스시간", example = "300")
    private int service;

    @Schema(description = "서비스시간")
    private List<String> time_windows;

    @Schema(description = "스킬")
    private List<String> skills;

    @Schema(description = "선호스킬")
    private List<String> preferred_skills;

    @Schema(description = "배제할 차량 ID 목록")
    private List<String> blocked_vehicle_ids;

    @Schema(description = "우선순위 (높을수록 우선)")
    private int priority;

    @Schema(description = "담당 권역")
    private String[] zones;

    /** OTD(정시 배송) 목표 시간 */
    private List<Long> otd_time;
    
    @Schema(description = "전용차량")
    private List<String> dedicated_vehicle;
    
}
