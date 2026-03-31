package cjfw.wms.tm.dto;

import cjfw.wms.tm.util.AESUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : OhEunbeom
 * @date : 2025.09.18
 * @description : 차량 모니터링 통합 조회 응답 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.18 OhEunbeom      생성 </pre>
 */
@Data
@Schema(description = "차량 모니터링 통합 조회 응답 DTO")
public class TmVehicleMonitoringIntegratedResDto {

    /**센터코드*/
    @Schema(description = "센터코드", example = "FW00")
    private String dccode;

    /**배송일자*/
    @Schema(description = "배송일자")
    private String deliverydt;

    /**차량번호*/
    @Schema(description = "차량번호")
    private String carno;

    /**배송상태*/
    @Schema(description = "배송상태")
    private String deliveryStatus;

    /**위도*/
    @Schema(description = "위도")
    private String latitude;

    /**경도*/
    @Schema(description = "경도")
    private String longitude;

    /**최근 작업 회차정보*/
    @Schema(description = "최근 작업 회차정보")
    private String latestPriority;

    public String getLatitude() {
        return AESUtils.decrypt(latitude);
    }

    public String getLongitude() {
        return AESUtils.decrypt(longitude);
    }
}
