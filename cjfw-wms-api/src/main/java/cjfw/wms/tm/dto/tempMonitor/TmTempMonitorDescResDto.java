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
 * @description : 온도 모니터링 상세 조회 결과 DTO
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
@Schema(description = "온도 모니터링 상세 조회 결과 DTO")
public class TmTempMonitorDescResDto {

    @Schema(description = "차량번호")
    private String carno;

    @Schema(description = "고객코드")
    private String custkey;

    @Schema(description = "고객명")
    private String custname;

    @Schema(description = "측정시간")
    private String recordTime;

    @Schema(description = "냉장")
    private String refrig;

    @Schema(description = "냉동")
    private String freeze;

    @Schema(description = "냉장상태")
    private String refrigStatus;

    @Schema(description = "냉장상태명")
    private String refrigStatusNm;

    @Schema(description = "냉동상태")
    private String freezeStatus;

    @Schema(description = "냉장상태명")
    private String freezeStatusNm;

    @Schema(description = "배송일자")
    private String deliverydt;

    @Schema(description = "회차", example = "1")
    private String priority;

    @Schema(description = "운행일시", example = "2025-10-01 19:15")
    private String workdate;

    @Schema(description = "운행일시", example = "19:15")
    private String workTime;

    @Schema(description = "시간단위(1분/5분/10분/30분/60분)", example = "10")
    private int timeUnit;

    @Schema(description = "userStop")
    private boolean userStop = false;

//    @JsonIgnore
//    private String worklogtype;
//    @JsonIgnore
//    private Date startDate;
//    @JsonIgnore
//    private String startDateFormat;
//    @JsonIgnore
//    private Date endDate;
//    @JsonIgnore
//    private String endDateFormat;
//    @JsonIgnore
//    private Date recordTime1;
}
