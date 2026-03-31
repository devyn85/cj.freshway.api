package cjfw.wms.comfunc.func.chart.dto;

import lombok.Data;

/**
 * 샘플Chart 조회 Response DTO
 */
@Data
public class SampleChartGetResDto {
    private String ym; // 년월
    private Integer cnt; // 카운트
}

/**
 * [MPA 참조]
 * > 응답
 * "data":[{"YM":"2021-06","CNT":91},{"YM":"2021-07","CNT":72}, ... ]
 */