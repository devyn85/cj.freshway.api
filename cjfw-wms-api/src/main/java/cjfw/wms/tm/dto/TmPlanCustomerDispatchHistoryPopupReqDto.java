package cjfw.wms.tm.dto;

import cjfw.core.model.Page;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : OhEunbeom
 * @date : 2025.10.10
 * @description : 배차이력 조회 요청 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.10 OhEunbeom      생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Schema(description = "배차이력 조회 요청 DTO")
public class TmPlanCustomerDispatchHistoryPopupReqDto extends Page<Object> {

    /**거래처*/
    @Schema(description = "거래처")
    private String custkey;

    /**배송일자(범위) 시작*/
    @Schema(description = "배송일자(범위) 시작", required = true)
    private String deliverydtFrom;

    /**배송일자(범위) 종료*/
    @Schema(description = "배송일자(범위) 종료", required = true)
    private String deliverydtTo;

    /**센터코드*/
    @Schema(description = "센터코드", required = true)
    private String dccode;

    /**배송 유형*/
    @Schema(description = "배송 유형")
    private String tmDeliverytype;
}
