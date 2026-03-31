package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2026. CJ Freshway Co. all rights reserved.
 * @author : devyn
 * @date : 2026.03.04
 * @description : 개별배차 팝업 목록 조회 응답 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2026.03.04 devyn                 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TmIndividualDispatchPopResDto {

    /**차량번호*/
    @Schema(description = "차량번호")
    private String code;

    /**기사명*/
    @Schema(description = "기사명")
    private String driverName;

    /**계약유형*/
    @Schema(description = "계약유형")
    private String contractType;

    /**구역코드*/
    @Schema(description = "구역코드")
    private String name;

    /**운송협력사코드*/
    @Schema(description = "운송협력사코드")
    private String custKey;

    /**운송사코드*/
    @Schema(description = "운송사코드")
    private String courier;

    /**운송사명*/
    @Schema(description = "운송사명")
    private String couriername;

    /**2차 운송사코드*/
    @Schema(description = "2차 운송사코드")
    private String caragentkey;

    /**2차 운송사명*/
    @Schema(description = "2차 운송사명")
    private String caragentname;

}
