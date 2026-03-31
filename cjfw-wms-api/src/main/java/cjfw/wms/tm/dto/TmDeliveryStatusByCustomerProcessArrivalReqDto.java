package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : System
 * @date : 2025.01.XX
 * @description : 배송현황(거래처별) 도착처리 요청 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.01.XX System      생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "배송현황(거래처별) 도착처리 요청 DTO")
public class TmDeliveryStatusByCustomerProcessArrivalReqDto {

    /**센터코드*/
    @Schema(description = "센터코드", required = true, example = "2600")
    private String dccode;

    /**배송일자*/
    @Schema(description = "배송일자", required = true, example = "20250101")
    private String deliverydt;

    /**차량번호*/
    @Schema(description = "차량번호", required = true)
    private String carno;

    /**실착지코드*/
    @Schema(description = "실착지코드", required = true)
    private String custkey;

    /**회차*/
    @Schema(description = "회차", required = true)
    private String priority;

    /**도착시간*/
    @Schema(description = "도착시간 (hh24mi 형식, 예: 1430)")
    private String arrivedtime;

    /**출발시간*/
    @Schema(description = "출발시간 (hh24mi 형식, 예: 1530)")
    private String departuretime;

    @Schema(description = "사용자ID(공통)", example = "testID")
    private String userId;

}

