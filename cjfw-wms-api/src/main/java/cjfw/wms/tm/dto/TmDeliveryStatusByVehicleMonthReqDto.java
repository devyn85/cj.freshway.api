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
 * @description : 차량별 당월 배송현황 조회 요청 DTO
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
@Schema(description = "차량별 당월 배송현황 조회 요청 DTO")
public class TmDeliveryStatusByVehicleMonthReqDto {

    /**센터코드*/
    @Schema(description = "센터코드", required = true)
    private String dccode;

    /**배송일자*/
    @Schema(description = "배송일자 (YYYYMMDD 형식, 해당 월의 데이터 조회)", required = true, example = "20250115")
    private String deliverydt;

}

