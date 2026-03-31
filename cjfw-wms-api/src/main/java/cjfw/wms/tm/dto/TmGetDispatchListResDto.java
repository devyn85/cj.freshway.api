package cjfw.wms.tm.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : AI Assistant
 * @date : 2025.09.17
 * @description : TM 배차 목록 조회 응답 DTO
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
@Schema(description = "TM 배차 목록 조회 응답 DTO")
public class TmGetDispatchListResDto {

    /** 조회 시작일자 */
    @Schema(
        description = "조회된 시작일자",
        example = "20250320"
    )
    private String searchStartDate;

    /** 조회 종료일자 */
    @Schema(
        description = "조회된 종료일자",
        example = "20250324"
    )
    private String searchEndDate;

    /** 물류센터코드 */
    @Schema(
        description = "조회된 물류센터코드",
        example = "105"
    )
    private String dccode;

    /** 물류센터명 */
    @Schema(
        description = "물류센터명",
        example = "경기105하1234 물류센터"
    )
    private String dcname;

    /** 검색 조건 요약 */
    @Schema(
        description = "적용된 검색 조건 요약"
    )
    private TmDispatchSearchSummaryDto searchSummary;

    /** 배차 통계 정보 */
    @Schema(
        description = "배차 결과 통계 정보"
    )
    private TmDispatchStatisticsDto statistics;

    /** 차량별 배차 목록 */
    @Schema(
        description = "차량별 배차 상세 정보 목록"
    )
    private List<TmDispatchInfoListResDto> dispatchList;

    /** 페이징 정보 */
    @Schema(
        description = "페이징 정보"
    )
    private TmPagingInfoDto pagingInfo;
}
