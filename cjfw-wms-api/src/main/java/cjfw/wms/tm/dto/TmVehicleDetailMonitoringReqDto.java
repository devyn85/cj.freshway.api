package cjfw.wms.tm.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : OhEunbeom
 * @date : 2025.09.10
 * @description : 센터별 조차별 차량 모니터링 조회 요청 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.10 OhEunbeom      생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "센터별 조차별 차량 모니터링 조회 요청 DTO")
public class TmVehicleDetailMonitoringReqDto {

    /**센터코드*/
    @Schema(description = "센터코드", required = true)
    private String dccode;

    /**배송일자*/
    @Schema(description = "배송일자", required = true)
    private String deliverydt;

    /**차량그룹*/
    @Schema(description = "차량그룹", required = true)
    private String cargroup;

    /**계약유형*/
    @Schema(description = "계약유형")
    private List<String> contracttype;

    /**배송유형*/
    @Schema(description = "배송유형")
    private String tmDeliverytype;

    /**차량번호 리스트*/
    @Schema(description = "차량번호 리스트")
    private List<String> carnoList;

    /**배송상태*/
    @Schema(description = "배송상태")
    private String deliveryStatus;

}
