package cjfw.wms.tm.dto;

import cjfw.core.model.Page;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : OhEunbeom
 * @date : 2025.10.10
 * @description : 배차목록(권역별) 조회 요청 DTO
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
@Schema(description = "배차목록(권역별) 조회 요청 DTO")
public class TmDispatchListByDistrictReqDto extends Page<Object> {

    /**배송시작일자*/
    @Schema(description = "배송시작일자", required = true)
    private String deliverydtFrom;

    /**배송종료일자*/
    @Schema(description = "배송종료일자", required = true)
    private String deliverydtTo;

    /**센터코드*/
    @Schema(description = "센터코드", required = true)
    private String dccode;

    /**행정구역*/
    @Schema(description = "행정구역 (시/도, 시/군/구, 행정동 통합 검색)")
    private String administrativeRegion;

}
