package cjfw.wms.tm.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import cjfw.wms.tm.dto.engine.TmEngineSummaryDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : AI Assistant
 * @date : 2025.09.18
 * @description : TM 배차 설정 응답 DTO (완전한 차량 정보 포함, saveDispatch와 호환)
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.17 AI Assistant          생성
 * 2025.09.18 AI Assistant          완전한 차량 정보 및 요청 파라미터 포함으로 수정
 * </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "TM 배차 설정 응답 DTO (완전한 차량 정보 포함)")
public class TmSetDispatchResDto {

    /** 물류센터 코드 (요청에서 전달받은 값) */
    @JsonProperty("dccode")
    @Schema(
        description = "물류센터 코드",
        example = "STD", 
        nullable = false
    )
    private String dccode;

    /** 배송일자 (요청에서 전달받은 값) */
    @Schema(
        description = "배송일자 (YYYYMMDD 형식)",
        example = "20250120",
        nullable = false
    )
    private String deliveryDate;

    /** 배송 유형 (요청에서 전달받은 값) */
    @Schema(
        description = "배송 유형",
        example = "WD",
        nullable = true
    )
    private String deliveryType;

    /** TM 배송유형 (요청에서 전달받은 값) */
    @Schema(
        description = "TM 배송유형 (1:배송, 2:새벽, 3:조달, 4:택배, 6:회수)",
        example = "1",
        nullable = true
    )
    private String tmDeliveryType;

    /** 처리 상태 */
    @Schema(
        description = "배차 처리 상태",
        example = "SUCCESS",
        allowableValues = {"SUCCESS", "FAILED"}
    )
    private String status;

    /** 처리 메시지 */
    @Schema(
        description = "배차 처리 결과 메시지",
        example = "배차가 성공적으로 완료되었습니다."
    )
    private String message;

    /** 배차 요약 정보 */
    @Schema(
        description = "CJ 전처리 서버에서 반환된 최적화 결과 요약"
    )
    private TmEngineSummaryDto summary;

    /** 차량별 배차 정보 목록 (완전한 차량 정보 포함) */
    @Schema(
        description = "차량별 배차 정보 목록 (스크린샷 기준 완전한 차량 데이터 포함)"
    )
    private List<TmVehicleDispatchInfoDto> vehicles;

    /** 미배정 주문 목록 */
    @Schema(
        description = "배정되지 않은 주문 목록"
    )
    private List<TmSetDispatchUnassignedOrderResDto> unassignedOrders;

    /** 반품 주문 목록 */
    @Schema(
            description = "전월 1일부터 미배차된 반품 주문 목록"
    )
    private List<TmSetDispatchReturnOrderResDto> returnOrders;

}