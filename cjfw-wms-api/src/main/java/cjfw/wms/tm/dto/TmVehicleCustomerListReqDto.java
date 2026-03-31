/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 차량별 거래처 목록 조회 요청 DTO
 * 
 * @author System
 * @since 2025.09.11
 */
@Data
public class TmVehicleCustomerListReqDto {
    
    @Schema(description = "센터코드")
    private String dccode;
    
    @Schema(description = "배송일자")
    private String deliverydt;
    
    @Schema(description = "차량번호")
    private String carno;
    
    @Schema(description = "회차정보")
    private String prepriority;

    @Schema(description = "배송유형")
    private String tmDeliverytype;
}
