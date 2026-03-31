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
 * @date : 2025.09.18
 * @description : 차량위치 조회 요청 DTO
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
@Schema(description = "차량위치 조회 요청 DTO")
public class TmVehicleLocationReqDto {

    /**센터코드*/
    @Schema(description = "센터코드")
    private String dccode;

    /**배송일자*/
    @Schema(description = "배송일자", required = true)
    public String deliverydt;

    /**차량번호 리스트*/
    @Schema(description = "차량번호 리스트", required = true)
    private List<String> carnoList;

}
