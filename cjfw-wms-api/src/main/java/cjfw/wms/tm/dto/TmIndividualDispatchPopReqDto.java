package cjfw.wms.tm.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2026. CJ Freshway Co. all rights reserved.
 * @author : devyn
 * @date : 2026.03.04
 * @description : 개별배차 팝업 목록 조회 요청 DTO
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
public class TmIndividualDispatchPopReqDto extends CommonDto {

    /**차량번호*/
    @Schema(description = "차량번호")
    private String code;

    /**검색어*/
    @Schema(description = "검색어")
    private String name;

    /**계약유형*/
    @Schema(description = "계약유형")
    private String contractType;

    /**다중선택 (콤마구분)*/
    @Schema(description = "다중선택 (콤마구분)")
    private String multiSelect;

}
