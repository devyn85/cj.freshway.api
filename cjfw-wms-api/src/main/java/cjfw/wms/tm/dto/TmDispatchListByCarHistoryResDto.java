package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : System
 * @date : 2025.01.15
 * @description : 배차목록(차량 변경내역) 조회 응답 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.01.15 System      생성 </pre>
 */
@Data
@Schema(description = "배차목록(차량 변경내역) 조회 응답 DTO")
public class TmDispatchListByCarHistoryResDto {

    /**물류센터*/
    @Schema(description = "물류센터")
    private String dccode;

    /**센터명*/
    @Schema(description = "센터명")
    private String dcname;

    /**배송일자*/
    @Schema(description = "배송일자")
    private String deliverydt;

    /**변경전 > 슈트번호*/
    @Schema(description = "변경전 > 슈트번호")
    private String docknoF;

    /**변경전 > POP번호*/
    @Schema(description = "변경전 > POP번호")
    private String popnoOld;

    /**변경전 > 롤테이너번호*/
    @Schema(description = "변경전 > 롤테이너번호")
    private String rolltainerNoOld;

    /**변경전 > 차량번호*/
    @Schema(description = "변경전 > 차량번호")
    private String carnoOld;

    /**변경전 > 회차*/
    @Schema(description = "변경전 > 회차")
    private String priorityOld;

    /**고객 > 실착지코드*/
    @Schema(description = "고객 > 실착지코드")
    private String truthcustkey;

    /**고객 > 실착지명*/
    @Schema(description = "고객 > 실착지명")
    private String truthcustname;

    /**고객 > 관리처코드*/
    @Schema(description = "고객 > 관리처코드")
    private String custkey;

    /**고객 > 관리처명*/
    @Schema(description = "고객 > 관리처명")
    private String custname;

    /**고객 > 키 유형*/
    @Schema(description = "고객 > 키 유형")
    private String keytype2;

    /**고객 > 키 유형명*/
    @Schema(description = "고객 > 키 유형명")
    private String keytype2Name;

    /**변경후 > 슈트번호*/
    @Schema(description = "변경후 > 슈트번호")
    private String docknoB;

    /**변경후 > POP번호*/
    @Schema(description = "변경후 > POP번호")
    private String popno;

    /**변경후 > 롤테이너번호*/
    @Schema(description = "변경후 > 롤테이너번호")
    private String rolltainerNo;

    /**변경후 > 차량번호*/
    @Schema(description = "변경후 > 차량번호")
    private String carno;

    /**변경후 > 회차*/
    @Schema(description = "변경후 > 회차")
    private String priority;
}
