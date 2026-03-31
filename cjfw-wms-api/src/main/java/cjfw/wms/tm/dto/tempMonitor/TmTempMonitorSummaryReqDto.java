package cjfw.wms.tm.dto.tempMonitor;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : System Generated
 * @date : 2025.01.27 
 * @description : 온도 모니터링 요약 조회 요청 DTO
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
@Schema(description = "온도 모니터링 요약 조회 요청 DTO")
public class TmTempMonitorSummaryReqDto extends CommonDto {
	
    /** 센터코드 */
    @Schema(description = "센터코드")
    private String dccode;
    
    /** 차량번호 */
    @Schema(description = "차량번호")
    private String carno;
    
    /** 측정시작일시 */
    @Schema(description = "측정시작일시")
    private String measureFromDt;
    
    /** 측정종료일시 */
    @Schema(description = "측정종료일시")
    private String measureToDt;
    
    /** 센서ID */
    @Schema(description = "센서ID")
    private String sensorId;
    
    /** 센서위치 */
    @Schema(description = "센서위치")
    private String sensorLocation;
    
    /** 요약구간 */
    @Schema(description = "요약구간 (HOUR, DAY, WEEK, MONTH)")
    private String summaryInterval;
    
}
