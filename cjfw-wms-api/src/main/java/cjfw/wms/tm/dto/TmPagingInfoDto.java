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
 * @description : TM 페이징 정보 DTO
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
@Schema(description = "페이징 정보 DTO")
public class TmPagingInfoDto {

    /** 현재 페이지 번호 */
    @Schema(
        description = "현재 페이지 번호 (1부터 시작)",
        example = "1"
    )
    private Integer currentPage;

    /** 페이지당 항목 수 */
    @Schema(
        description = "페이지당 항목 수",
        example = "20"
    )
    private Integer pageSize;

    /** 전체 항목 수 */
    @Schema(
        description = "전체 항목 수",
        example = "45"
    )
    private Integer totalCount;

    /** 전체 페이지 수 */
    @Schema(
        description = "전체 페이지 수",
        example = "3"
    )
    private Integer totalPages;

    /** 현재 페이지의 첫 번째 항목 번호 */
    @Schema(
        description = "현재 페이지의 첫 번째 항목 번호",
        example = "1"
    )
    private Integer startRowNumber;

    /** 현재 페이지의 마지막 항목 번호 */
    @Schema(
        description = "현재 페이지의 마지막 항목 번호",
        example = "20"
    )
    private Integer endRowNumber;

    /** 이전 페이지 존재 여부 */
    @Schema(
        description = "이전 페이지 존재 여부",
        example = "false"
    )
    private Boolean hasPreviousPage;

    /** 다음 페이지 존재 여부 */
    @Schema(
        description = "다음 페이지 존재 여부",
        example = "true"
    )
    private Boolean hasNextPage;

    /** 첫 번째 페이지 여부 */
    @Schema(
        description = "첫 번째 페이지 여부",
        example = "true"
    )
    private Boolean isFirstPage;

    /** 마지막 페이지 여부 */
    @Schema(
        description = "마지막 페이지 여부",
        example = "false"
    )
    private Boolean isLastPage;
}
