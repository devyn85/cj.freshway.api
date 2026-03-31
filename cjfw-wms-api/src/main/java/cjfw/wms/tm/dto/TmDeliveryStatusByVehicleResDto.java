package cjfw.wms.tm.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : System
 * @date : 2025.01.XX
 * @description : 차량별 당일 배송현황 조회 응답 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.01.XX System      생성 </pre>
 */
@Data
@Schema(description = "차량별 당일 배송현황 조회 응답 DTO")
public class TmDeliveryStatusByVehicleResDto {

    /**계약유형*/
    @Schema(description = "계약유형")
    private String contracttype;

    /**계약유형명*/
    @Schema(description = "계약유형명")
    private String contracttypename;

    /**착지 수*/
    @Schema(description = "착지 수")
    private Integer arrivalCount;

    /**전체 보고수*/
    @Schema(description = "전체 보고수")
    private Integer arrivalReportCount;

    /**전체 보고율*/
    @Schema(description = "전체 보고율 (%)")
    private BigDecimal arrivalReportRate;

    /**전체 미보고수*/
    @Schema(description = "전체 미보고수")
    private Integer unarrivalReportCount;

    @Schema(description = "PHOTO_RATE")
    private BigDecimal photoRate;

    /**자동 보고수*/
    @Schema(description = "자동 보고수")
    private Integer normalReportCount;

    /**자동 보고율*/
    @Schema(description = "자동 보고율 (%)")
    private BigDecimal normalReportRate;

    /**수동 보고수*/
    @Schema(description = "수동 보고수")
    private Integer manualReportCount;

    /**수동 보고율*/
    @Schema(description = "수동 보고율 (%)")
    private BigDecimal manualReportRate;

    /**배송일자*/
    @Schema(description = "배송일자")
    private String deliverydt;

    /**센터코드*/
    @Schema(description = "센터코드")
    private String dccode;

    public BigDecimal getArrivalReportRate() {
        return scale3(arrivalReportRate);
    }

    public BigDecimal getNormalReportRate() {
        return scale3(normalReportRate);
    }

    public BigDecimal getPhotoRate() {
        return scale3(photoRate);
    }

    public BigDecimal getManualReportRate() {
        return scale3(manualReportRate);
    }

    private BigDecimal scale3(BigDecimal value) {
        return value == null ? null : value.setScale(2, RoundingMode.DOWN);
    }

}

