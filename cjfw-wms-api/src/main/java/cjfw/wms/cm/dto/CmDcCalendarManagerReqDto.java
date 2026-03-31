package cjfw.wms.cm.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author      : LeeJeongCheol (progs@cj.net)
 * @date        : 2026.03.04
 * @description : 발주용휴일관리 조회(목록) 요청 DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2026.03.04        LeeJeongCheol (progs@cj.net)       생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Schema(description = "당일광역보충발주용 휴일관리 조회(목록) 요청 DTO")
public class CmDcCalendarManagerReqDto extends CommonDto {
	@Schema(description = "일련번호")
    private String serialkey;
	
    @Schema(description = "달력 ID", example = "STD")
    private String calendarId;
    
    @Schema(description = "물류센터코드", example = "1000")
    private String dccode;

    @Schema(description = "연(YYYY)", example = "2025")
    private String yy;

    @Schema(description = "월(MM)", example = "09")
    private String mm;

    @Schema(description = "일(DD)", example = "11")
    private String dd;

    @Schema(description = "요일구분코드(DAY_GB)", example = "SUN")
    private String dayGb;

    @Schema(description = "휴일유무(Y/N)", example = "Y")
    private String restYn;

    @Schema(description = "휴일내용", example = "추석 대체공휴일")
    private String restDesc;

    @Schema(description = "조회시작일(YYYYMMDD)", example = "20250101")
    private String dateFrom;

    @Schema(description = "조회종료일(YYYYMMDD)", example = "20251231")
    private String dateTo;
}
