package cjfw.wms.cm.entity;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 *
 * @author      : LeeJeongCheol (progs@cj.net)
 * @date        : 2025.09.11
 * @description : 당일광역보충발주용 휴일관리 정보 Entity
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2026.03.04        LeeJeongCheol 		(progs@cj.net)       생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "당일광역보충발주용 휴일관리 정보 Entity")
public class CmDcCalendarManagerEntity extends CommonDto {
    @Schema(description = "일련번호")
    private String serialkey;

    @Schema(description = "물류센터 코드")
    private String dccode;

    @Schema(description = "달력 ID")
    private String calendarId;

    @Schema(description = "연(YYYY)")
    private String yy;

    @Schema(description = "월(MM, 2자리)")
    private String mm;

    @Schema(description = "일(DD, 2자리)")
    private String dd;

    @Schema(description = "요일구분 코드")
    private String dayGb;

    @Schema(description = "휴일여부(Y/N)")
    private String restYn;

    @Schema(description = "휴일 설명")
    private String restDesc;

    @Schema(description = "최초등록시각")
    private String adddate;

    @Schema(description = "최종변경시각")
    private String editdate;

    @Schema(description = "최초등록자ID")
    private String addwho;

    @Schema(description = "최종변경자ID")
    private String editwho;
}
