package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : OhEunbeom
 * @date : 2025.09.18
 * @description : 실착지 위치 조회 응답 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.18 OhEunbeom      생성 </pre>
 */
@Data
@Schema(description = "실착지 위치 조회 응답 DTO")
public class TmCustomerLocationResDto {

    @Schema(description = "배송순번")
    private Integer sortSeq;

    @Schema(description = "실착지코드")
    private String truthcustkey;

    @Schema(description = "위도")
    private String latitude;

    @Schema(description = "경도")
    private String longitude;
}
