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
 * @description : Engine 배송 작업 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.01.14 AI Assistant          생성
 * 
 * Shipment 객체
    id(string, 선택): 주문 식별자
    pop(string, 선택): POP 코드
    type("pop"|"o2o", 필수): 주문 유형
    pickup_address(string, 필수) / pickup_location([lon, lat], 필수)
    pickup_time_window(string|datetime, 선택: YYYY-MM-DD HH:MM:SS) / pickup_duration(int≥0, 선택, 단위:초)
    delivery_address(string, 필수) / delivery_location([lon, lat], 필수)
    delivery_time_window(string|datetime, 선택) / delivery_duration(int≥0, 선택, 단위:초)
    amount(int[]≥0, 선택: [부피, 무게, 개수])
    zones(string[], 선택), skills(int[]≥0, 선택)
    preferred_skills(string[], 선택): 소프트(우선순위) 스킬. 차량과 주문이 같은 값을 가지면 배차 우선순위만 높이고, 없더라도 배차는 허용함.
    blocked_vehicle_ids(string[], 선택): 강성 배차용. 여기에 포함된 차량 ID는 이 주문을 방문할 수 없음. 배제 목록이 비어 있으면 모든 차량 허용.
    otd_time(string|datetime, 선택): OTD(정시 배송) 목표 시간
    delivery_available_time(string|datetime, 선택): 납품 가능 시간
    building_open_time(string|datetime, 선택): 건물 개방 시간
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Engine 배송 작업 DTO")
public class TmEngineShipmentDto {

    /** 주문 ID */
    @Schema(
        description = "주문 고유 ID",
        example = "WD20250117_001"
    )
    private String id;

    private String pop;

    /** 픽업 주소 */
    @Schema(
        description = "픽업 주소",
        example = "서울시 강남구 역삼동 123-45"
    )
    private String pickup_address;

    /** 픽업 위치 [경도, 위도] */
    @Schema(
        description = "픽업 위치 좌표 [경도, 위도]",
        example = "[127.0276, 37.4979]"
    )
    private List<Double> pickup_location;

    /** 배송 주소 */
    @Schema(
        description = "배송 주소",
        example = "서울시 서초구 방배동 678-90"
    )
    private String delivery_address;

    /** 배송 위치 [경도, 위도] */
    @Schema(
        description = "배송 위치 좌표 [경도, 위도]",
        example = "[127.0286, 37.4989]"
    )
    private List<Double> delivery_location;

    /** 픽업 소요 시간 (초) */
    @Schema(
        description = "픽업 작업 소요 시간 (초)",
        example = "300"
    )
    private Integer pickup_duration;

    /** 배송 소요 시간 (초) */
    @Schema(
        description = "배송 작업 소요 시간 (초)",
        example = "600"
    )
    private Integer delivery_duration;

    /** 수량 [ORDER_CUBE, ORDER_WEIGHT, ORDER_QTY] */
    @Schema(
        description = "배송 수량 배열 [부피, 무게, 개수] - CJ 전처리 서버 스키마 순서",
        example = "[5, 100, 25]"
    )
    private List<Integer> amount;

    /** 스킬 요구사항 */
    @Schema(
        description = "배송에 필요한 스킬 목록, 전용차량 배차에 사용",
        example = "[]"
    )
    private List<String> skills;

    /** 작업 유형 */
    @Schema(
        description = "작업 유형 (pop: 고정 주문, o2o: 신규 주문)",
        example = "pop",
        allowableValues = {"pop", "o2o"}
    )
    private String type;

    /** 우선순위 */
    @Schema(
        description = "배송 우선순위 (1-10, 높을수록 우선)",
        example = "5"
    )
    private Integer priority;
  
    /** 소프트(우선순위) 스킬 : 고정배차 기능 처리 (고정이 아닌 우선배차 처리 함) */
    private List<String> preferred_skills;

    /** 강성 배차용. 여기에 포함된 차량 ID는 이 주문을 방문할 수 없음. 배제 목록이 비어 있으면 모든 차량 허용. */
    private List<String> blocked_vehicle_ids;

    /** OTD(정시 배송) 목표 시간 */
    private List<Long> otd_time;

    /** 납품 가능 시간 */
    private String delivery_available_time;

    /** 건물 개방 시간 */
    private String building_open_time;
}
