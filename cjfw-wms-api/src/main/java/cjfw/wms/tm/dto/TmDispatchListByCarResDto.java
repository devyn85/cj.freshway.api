package cjfw.wms.tm.dto;

import cjfw.wms.tm.util.TmPlanCommon;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : OhEunbeom
 * @date : 2025.01.15
 * @description : 배차목록(차량별) 조회 응답 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.01.15 OhEunbeom      생성 </pre>
 */
@Data
@Schema(description = "배차목록(차량별) 조회 응답 DTO")
public class TmDispatchListByCarResDto {

    /**센터코드*/
    @Schema(description = "센터코드")
    private String dccode;

    /**센터명*/
    @Schema(description = "센터명")
    private String dcname;
    
    /**배송일자*/
    @Schema(description = "배송일자 (YYYY-MM-DD)")
    private String deliverydt;

    /**차량번호*/
    @Schema(description = "차량번호")
    private String carno;

    /**기사명*/
    @Schema(description = "기사명")
    private String drivername;

    /**휴대전화*/
    @Schema(description = "휴대전화")
    private String phone1;

    /**계약유형*/
    @Schema(description = "계약유형")
    private String contracttype;

    /**계약유형명*/
    @Schema(description = "계약유형명")
    private String contractname;

    /**배송유형*/
    @Schema(description = "배송유형")
    private String tmDeliverytype;

    /**배송유형명*/
    @Schema(description = "배송유형명")
    private String deliverytypename;

    /**반품여부*/
    @Schema(description = "반품여부 (Y: 반품, N: 배송)")
    private String returnYn;

    /**차량톤급*/
    @Schema(description = "차량톤급")
    private String carcapacity;

    /**차량톤급명*/
    @Schema(description = "차량톤급명")
    private String carcapacityName;

    /**회차*/
    @Schema(description = "회차")
    private String priority;

    /**운송사코드*/
    @Schema(description = "운송사코드")
    private String courier;

    /**운송사명*/
    @Schema(description = "운송사명")
    private String couriername;

    /**2차 운송사코드*/
    @Schema(description = "2차 운송사코드")
    private String caragentkey;

    /**2차 운송사명*/
    @Schema(description = "2차 운송사명")
    private String carriername;

    /**총 POP수*/
    @Schema(description = "총 POP수")
    private Integer totalPopCount;

    /**총 권역수*/
    @Schema(description = "총 권역수")
    private Integer totalDistrictCount;

    /**총 실착지 수*/
    @Schema(description = "총 실착지 수")
    private Integer totalTruthCustCount;

    /**주요 실착지 수*/
    @Schema(description = "주요 실착지 수 (OTD, 대면검수, 특수조건 중 하나라도 해당)")
    private Integer totalMajorCustCount;

    /**총 CBM*/
    @Schema(description = "총 CBM")
    private String totalCube;

    /**총 물량*/
    @Schema(description = "총 물량")
    private String totalWeight;

    /**
     * 기사명 마스킹 처리
     * 첫글자와 마지막글자를 제외한 중간 글자를 '*'로 마스킹
     */
    public void setDrivername(String drivername) {
        this.drivername = TmPlanCommon.maskDriverName(drivername);
    }

    /**
     * 휴대전화 마스킹 처리
     * 앞자리 3자리와 뒤자리 4자리 제외하고 나머지를 '*'로 마스킹
     */
    public void setPhone1(String phone1) {
        if (phone1 != null && phone1.length() >= 7) {
            // 앞자리 3자리 + 중간 마스킹 + 뒤자리 4자리
            String front = phone1.substring(0, 3);
            String back = phone1.substring(phone1.length() - 4);
            String middle = "*".repeat(phone1.length() - 7);
            this.phone1 = front + middle + back;
        } else {
            this.phone1 = phone1;
        }
    }
}
