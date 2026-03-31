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
 * @description : Engine 요청 DTO (cj-process-server /optimize/base API 호출용)
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.01.14 AI Assistant          생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Engine 최적화 요청 DTO")
public class TmEngineRequestDto {

    /** 배송 작업 목록 */
    @Schema(description = "배송 작업 목록 (주문 정보)")
    private List<TmEngineShipmentDto> shipments;

    /** 배송 작업 목록 */
    @Schema(description = "배송 작업 목록 (주문 정보)추후")
    private List<TmEngineJobDto> jobs;

    /** 차량 목록 */
    @Schema(description = "사용 가능한 차량 목록", nullable = false)
    private List<TmEngineVehicleDto> vehicles;

    private TmEngineOptionsDto options;

    private String timezone;

    private String request_id;

    private String description;

}
