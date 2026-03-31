package cjfw.wms.tm.dto;

import cjfw.wms.tm.util.TmPlanCommon;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : OhEunbeom
 * @date : 2025.09.10
 * @description : 센터별 조차별 차량 모니터링 조회 응답 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.10 OhEunbeom      생성 </pre>
 */
@Data
@Schema(description = "센터별 조차별 차량 모니터링 조회 응답 DTO")
public class TmVehicleDetailMonitoringResDto {

    /**센터코드*/
    @Schema(description = "센터코드")
    private String dccode;

    /**차량그룹*/
    @Schema(description = "차량그룹")
    private String cargroup;

    /**배송상태*/
    @Schema(description = "배송상태")
    private String deliveryStatus;

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

    /**앱 미연결 상태 여부*/
    @Schema(description = "앱 미연결 상태 여부")
    private String appDisconnectedYn;

    /**
     * 기사명 마스킹 처리
     * 첫글자와 마지막글자를 제외한 중간 글자를 '*'로 마스킹
     */
    public void setDrivername(String drivername) {
        this.drivername = TmPlanCommon.maskDriverName(drivername);
    }

}
