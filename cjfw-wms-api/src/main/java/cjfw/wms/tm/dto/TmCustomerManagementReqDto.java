package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : OhEunbeom
 * @date : 2025.09.18
 * @description : 관리처 목록 조회 요청 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.18 OhEunbeom      생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "관리처 목록 조회 요청 DTO")
public class TmCustomerManagementReqDto {

    /**배송날짜*/
    @Schema(description = "배송날짜", required = true, example = "20240901")
    private String deliverydt;

    /**차량번호*/
    @Schema(description = "차량번호", required = true, example = "TEST01가1234")
    private String carno;

    /**회차*/
    @Schema(description = "회차", required = true, example = "1")
    private String priority;

    /**실착지키*/
    @Schema(description = "실착지키", required = true, example = "TEST_CUST_001")
    private String truthcustkey;

}
