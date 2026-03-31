package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : OhEunbeom
 * @date : 2025.10.10
 * @description : 배차이력 조회 응답 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.10 OhEunbeom      생성 </pre>
 */
@Data
@Schema(description = "배차이력 조회 응답 DTO")
public class TmPlanCustomerDispatchHistoryPopupResDto{

    /**배송일자*/
    @Schema(description = "배송일자")
    private String deliverydt;

    /**차량번호*/
    @Schema(description = "차량번호")
    private String carno;

    /**차량톤수*/
    @Schema(description = "차량톤수")
    private String carcapacity;

    /**차량톤수명*/
    @Schema(description = "차량톤수명")
    private String carcapacityname;

    /**계약유형*/
    @Schema(description = "계약유형")
    private String contracttype;

    /**계약유형명*/
    @Schema(description = "계약유형명")
    private String contracttypename;

    /**기사명*/
    @Schema(description = "기사명")
    private String drivername;

    /**중량(kg)*/
    @Schema(description = "중량(kg)")
    private String weight;

    /**체적(m³)*/
    @Schema(description = "체적(m³)")
    private String cube;

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

    /**도착시간*/
    @Schema(description = "도착시간 (hh24:mi)")
    private String custarrivaldt;

}
