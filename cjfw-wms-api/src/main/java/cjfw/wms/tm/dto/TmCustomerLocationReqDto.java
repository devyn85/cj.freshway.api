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
 * @description : 실착지 위치 조회 요청 DTO
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
@Schema(description = "실착지 위치 조회 요청 DTO")
public class TmCustomerLocationReqDto {

    /**배송일자*/
    @Schema(description = "배송일자", required = true, example = "20241201")
    private String deliverydt;

    /**차량번호*/
    @Schema(description = "물류센터", required = true)
    private String dccode;

    /**차량번호*/
    @Schema(description = "배송유형", required = true)
    private String tmDeliverytype;

    /**차량번호*/
    @Schema(description = "차량번호", required = true, example = "TEST01가1234")
    private String carno;

    /**회차정보*/
    @Schema(description = "회차정보", required = true, example = "1")
    private String priority;

}
