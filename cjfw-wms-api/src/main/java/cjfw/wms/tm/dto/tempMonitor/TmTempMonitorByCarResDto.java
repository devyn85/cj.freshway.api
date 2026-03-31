package cjfw.wms.tm.dto.tempMonitor;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : System Generated
 * @date : 2025.01.27 
 * @description : 차량별 온도 모니터링 조회 결과 DTO
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.01.27 System Generated 생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "차량별 온도 모니터링 조회 결과 DTO")
public class TmTempMonitorByCarResDto {

    @Schema(description = "배송일자")
    private String deliverydt;
    @Schema(description = "차량번호")
    private String carno;
    @Schema(description = "회차")
    private String priority;
    @Schema(description = "기사명")
    private String drivername;
    @Schema(description = "계약 유형")
    private String contracttype;
    @Schema(description = "계약 유형명")
    private String contracttypeNm;
    @Schema(description = "적정온도율(냉장)")
    private Double temperature1NomlRate;
    @Schema(description = "적정온도율(냉동)")
    private Double temperature2NomlRate;
    @Schema(description = "이탈율(냉장)")
    private Double temperature1OutRate;
    @Schema(description = "이탈율(냉동)")
    private Double temperature2OutRate;
    @Schema(description = "냉장평균온도")
    private Double temperature1Avg;
    @Schema(description = "냉동평균온도")
    private Double temperature2Avg;
    @Schema(description = "최저/최고 냉장온도")
    private String temperature1MinMax;
    @Schema(description = "최저/최고 냉동온도")
    private String temperature2MinMax;
    @Schema(description = "온도수집구간")
    private String timeRange;

}
