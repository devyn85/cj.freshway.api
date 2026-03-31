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
 * @description : 온도 모니터링 요약 조회 결과 DTO
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
@Schema(description = "온도 모니터링 요약 조회 결과 DTO")
public class TmTempMonitorSummaryResDto {
    
    /** 센터코드 */
    @Schema(description = "센터코드")
    private String dccode;
    
    /** 차량번호 */
    @Schema(description = "차량번호")
    private String carno;

    /** 차량명 */
    @Schema(description = "차량명")
    private String carName;

    /** 운전자명 */
    @Schema(description = "운전자명")
    private String driverName;

    /** 운송사명 */
    @Schema(description = "운송사명")
    private String carrierName;

    /** 센서ID */
    @Schema(description = "센서ID")
    private String sensorId;

    /** 센서위치 */
    @Schema(description = "센서위치")
    private String sensorLocation;

    /** 측정시작일시 */
    @Schema(description = "측정시작일시")
    private String measureFromDt;

    /** 측정종료일시 */
    @Schema(description = "측정종료일시")
    private String measureToDt;

    /** 측정건수 */
    @Schema(description = "측정건수")
    private Integer measureCnt;

    /** 최소온도 */
    @Schema(description = "최소온도")
    private Double minTemp;

    /** 최대온도 */
    @Schema(description = "최대온도")
    private Double maxTemp;

    /** 평균온도 */
    @Schema(description = "평균온도")
    private Double avgTemp;

    /** 최소습도 */
    @Schema(description = "최소습도")
    private Double minHumidity;

    /** 최대습도 */
    @Schema(description = "최대습도")
    private Double maxHumidity;

    /** 평균습도 */
    @Schema(description = "평균습도")
    private Double avgHumidity;

    /** 기준온도최소 */
    @Schema(description = "기준온도최소")
    private Double standardMinTemp;

    /** 기준온도최대 */
    @Schema(description = "기준온도최대")
    private Double standardMaxTemp;

    /** 정상건수 */
    @Schema(description = "정상건수")
    private Integer normalCnt;

    /** 고온건수 */
    @Schema(description = "고온건수")
    private Integer highTempCnt;

    /** 저온건수 */
    @Schema(description = "저온건수")
    private Integer lowTempCnt;

    /** 알림건수 */
    @Schema(description = "알림건수")
    private Integer alertCnt;

    /** 정상비율 */
    @Schema(description = "정상비율")
    private Double normalRatio;

    /** 고온비율 */
    @Schema(description = "고온비율")
    private Double highTempRatio;

    /** 저온비율 */
    @Schema(description = "저온비율")
    private Double lowTempRatio;

    /** 알림비율 */
    @Schema(description = "알림비율")
    private Double alertRatio;

    /** 요약구간 */
    @Schema(description = "요약구간")
    private String summaryInterval;

    /** 요약일시 */
    @Schema(description = "요약일시")
    private String summaryDt;
    
}
