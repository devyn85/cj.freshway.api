package cjfw.wms.tm.dto;

import java.util.Date;

import cjfw.wms.tm.util.AESUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : System
 * @date : 2025.01.XX
 * @description : 실제 이동경로 좌표점 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.01.XX System      생성 </pre>
 */
@Data
@Schema(description = "실제 이동경로 좌표점 DTO")
public class TmActualRoutePointDto {

    @Schema(description = "차량번호")
    private String carno;

    @Schema(description = "수집일시")
    private Date loadDate;

    @Schema(description = "위도")
    private String latitude;

    @Schema(description = "경도")
    private String longitude;

    public String getLatitude() {
        return AESUtils.decrypt(latitude);
    }

    public String getLongitude() {
        return AESUtils.decrypt(longitude);
    }
}

