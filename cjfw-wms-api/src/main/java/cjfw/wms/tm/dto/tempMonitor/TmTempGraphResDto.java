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
 * @description : 온도 그래프 조회 결과 DTO
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
@Schema(description = "온도 그래프 조회 결과 DTO")
public class TmTempGraphResDto {

    @Schema(description = "차량번호")
    private String carno;

    @Schema(description = "측정시간")
    private String recordTime;

    @Schema(description = "냉장온도")
    private String refrig;

    @Schema(description = "냉동온도")
    private String freeze;

    @Schema(description = "냉장상태")
    private String refrigStatus;

    @Schema(description = "냉동상태")
    private String freezeStaus;

    @Schema(description = "고객사명")
    private String custname;

    @Schema(description = "도착시간")
    private String arrivalTime;
}
