package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : AI Assistant
 * @date : 2025.09.17
 * @description : TM 배차 목록 검색 조건 요약 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.17 AI Assistant          생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "TM 배차 목록 검색 조건 요약 DTO")
public class TmDispatchSearchSummaryDto {

    /** 검색 기간 */
    @Schema(
        description = "검색 기간 요약",
        example = "2025-03-20 ~ 2025-03-24 (5일간)"
    )
    private String searchPeriod;

    /** 적용된 필터 수 */
    @Schema(
        description = "적용된 필터 개수",
        example = "3"
    )
    private Integer appliedFilterCount;

    /** 권역/지역 필터 */
    @Schema(
        description = "적용된 권역/지역 필터",
        example = "서울권역"
    )
    private String regionFilter;

    /** 차량번호 검색어 */
    @Schema(
        description = "적용된 차량번호 검색어",
        example = "경기105"
    )
    private String carnoFilter;

    /** 차량 상태 필터 */
    @Schema(
        description = "적용된 차량 상태 필터",
        example = "DISPATCHED"
    )
    private String vehicleStatusFilter;

    /** 배차 상태 필터 */
    @Schema(
        description = "적용된 배차 상태 필터",
        example = "ACTIVE"
    )
    private String dispatchStatusFilter;

    /** 우선순위 필터 */
    @Schema(
        description = "적용된 우선순위 필터",
        example = "A"
    )
    private String priorityFilter;

    /** 정렬 조건 */
    @Schema(
        description = "적용된 정렬 조건",
        example = "차량번호 오름차순"
    )
    private String sortCondition;

    /** 총 검색 결과 수 */
    @Schema(
        description = "검색 조건에 맞는 전체 결과 수",
        example = "45"
    )
    private Integer totalResultCount;
}
