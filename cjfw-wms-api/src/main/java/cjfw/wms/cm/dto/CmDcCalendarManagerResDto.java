package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author      : LeeJeongCheol (progs@cj.net)
 * @date        : 2026.03.04
 * @description : 발주용휴일관리 조회(목록) 응답 DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2026.03.04        LeeJeongCheol (progs@cj.net)       생성
 */
@Data
@Schema(description = "발주용휴일관리 조회(목록) 응답 DTO")
public class CmDcCalendarManagerResDto {
    @Schema(description = "일련번호")
    private String serialkey;

    @Schema(description = "물류센터 코드")
    private String dccode;
    
    @Schema(description = "물류센터 명")
    private String dcnm;

    @Schema(description = "구분 (1000센터 or 그외(공통)")
    private String calendarId;

    @Schema(description = "연(YYYY)")
    private String yy;

    @Schema(description = "월(MM)")
    private String mm;

    @Schema(description = "일(DD)")
    private String dd;

    @Schema(description = "요일구분 코드")
    private String dayGb;

    @Schema(description = "요일구분 명칭")
    private String dayGbNm;

    @Schema(description = "휴일여부(Y/N)")
    private String restYn;

    @Schema(description = "휴일여부 명칭")
    private String restYnNm;

    @Schema(description = "휴일 설명")
    private String restDesc;

    @Schema(description = "최초등록시각(YYYY-MM-DD HH24:MI:SS)")
    private String addDate;

    @Schema(description = "최종변경시각(YYYY-MM-DD HH24:MI:SS)")
    private String editDate;

    @Schema(description = "등록자명(사용자명)")
    private String addWho;

    @Schema(description = "수정자명(사용자명)")
    private String editWho;
    
    @Schema(description = "커스텀 엑스트라 체크박스")
	private String customRowCheckYn="N";
}
