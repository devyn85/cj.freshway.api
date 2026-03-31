package cjfw.wms.tm.dto;

import java.util.Collections;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : han@wemeetmobility.com
 * @date : 2025.11.27
 * @description : 좌표 갱신 결과 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.27 han@wemeetmobility.com 생성
 * </pre>
 */
@Getter
@AllArgsConstructor
@Schema(description = "좌표 갱신 결과 DTO")
public class CoordinateUpdateResult {

    /** 총 갱신 대상 건수 */
    @Schema(description = "총 갱신 대상 건수", example = "10")
    private int totalCount;

    /** 성공 건수 */
    @Schema(description = "성공 건수", example = "8")
    private int successCount;

    /** 실패 건수 */
    @Schema(description = "실패 건수", example = "2")
    private int failCount;

    /** 실패 목록 */
    @Schema(description = "실패 목록")
    private List<CoordinateFailInfo> failList;

    /**
     * 성공 결과 생성 (갱신 대상 없음)
     *
     * @return 성공 결과
     */
    public static CoordinateUpdateResult success() {
        return new CoordinateUpdateResult(0, 0, 0, Collections.emptyList());
    }

    /**
     * 실패 건이 있는지 확인
     *
     * @return 실패 건이 있으면 true
     */
    public boolean hasFailures() {
        return failCount > 0;
    }
}
