package cjfw.wms.tm.dto;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : OhEunbeom
 * @date : 2025.09.18
 * @description : 차량위치 조회 응답 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.18 OhEunbeom      생성 </pre>
 */
@Data
@Schema(description = "차량위치 조회 응답 DTO")
public class TmVehicleLocationResDto {

    /**차량번호*/
    @Schema(description = "차량번호")
    private String carno;

    /**위도*/
    @Schema(description = "위도")
    private String latitude;

    /**경도*/
    @Schema(description = "경도")
    private String longitude;

    /**주소*/
    @Schema(description = "주소")
    private String address2;

    /**방위각*/
    @Schema(description = "방위각")
    private Integer azimuth;

    /**속도*/
    @Schema(description = "속도")
    private Integer speed;

    /**RPM*/
    @Schema(description = "RPM")
    private Integer rpm;

    /**일별 주행거리*/
    @Schema(description = "일별 주행거리")
    private Integer dailyMileage;

    /**총 주행거리*/
    @Schema(description = "총 주행거리")
    private Integer totalMileage;

    /**차량상태코드*/
    @Schema(description = "차량상태코드")
    private String carStatusCd;

    /**기록주기*/
    @Schema(description = "기록주기")
    private Integer recordInterval;

    /**기록데이터*/
    @Schema(description = "기록데이터")
    private String recordData;

    /**디바이스IMEI*/
    @Schema(description = "디바이스IMEI")
    private String deviceImei;

    /**로드일시*/
    @Schema(description = "로드일시")
    private Date loadDate;

    /**순번*/
    @Schema(description = "순번")
    private Integer rn;

}
