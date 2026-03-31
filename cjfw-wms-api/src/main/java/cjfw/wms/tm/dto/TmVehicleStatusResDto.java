package cjfw.wms.tm.dto;

import cjfw.wms.tm.util.TmPlanCommon;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : OhEunbeom
 * @date : 2025.09.18
 * @description : 차량번호와 배송상태 조회 응답 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.18 OhEunbeom      생성 </pre>
 */
@Data
@Schema(description = "차량번호와 배송상태 조회 응답 DTO")
public class TmVehicleStatusResDto {

    /**센터코드*/
    @Schema(description = "센터코드")
    private String dccode;

    /**배송일자*/
    @Schema(description = "배송일자")
    private String deliverydt;

    /**차량그룹*/
    @Schema(description = "차량그룹")
    private String cargroup;

    /**배송상태*/
    @Schema(description = "배송상태")
    private String deliveryStatus;

    /**배송상태명*/
    @Schema(description = "배송상태명")
    private String deliveryStatusName;

    /**계약유형*/
    @Schema(description = "계약유형")
    private String contracttype;

    /**계약유형명*/
    @Schema(description = "계약유형명")
    private String contractname;

    /**차량번호*/
    @Schema(description = "차량번호")
    private String carno;

    /**기사코드*/
    @Schema(description = "기사코드")
    private String driver1;

    /**기사명*/
    @Schema(description = "기사명")
    private String drivername;

    /**위도*/
    @Schema(description = "위도")
    private String latitude;

    /**경도*/
    @Schema(description = "경도")
    private String longitude;

    /**최근 작업 회차정보*/
    @Schema(description = "최근 작업 회차정보")
    private String latestPriority;

    /**
     * 기사명 마스킹 처리
     * 첫글자와 마지막글자를 제외한 중간 글자를 '*'로 마스킹
     */
    public void setDrivername(String drivername) {
        this.drivername = TmPlanCommon.maskDriverName(drivername);
    }

}
