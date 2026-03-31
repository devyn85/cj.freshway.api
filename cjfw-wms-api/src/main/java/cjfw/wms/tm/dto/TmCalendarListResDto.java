package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.08.22 
 * @description : 휴일관리 기능을 구현한 RES DTO Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.22 ParkJinWoo 생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "휴일관리 조회 resDto")
public class TmCalendarListResDto {

	 /** 일련번호 */
    @Schema(description = "일련번호")
    private Long serialKey;

    /** 센터코드 */
    @Schema(description = "센터코드")
    private String dcCode;

    /** 달력ID */
    @Schema(description = "달력ID")
    private String calendarId;

    /** 연도 */
    @Schema(description = "연도")
    private String yy;

    /** 월 */
    @Schema(description = "월")
    private String mm;

    /** 일 */
    @Schema(description = "일")
    private String dd;

    /** 일구분 */
    @Schema(description = "일구분")
    private String dayGb;

    /** 휴일여부 */
    @Schema(description = "휴일여부")
    private String restYn;

    /** 휴일여부 */
    @Schema(description = "휴일여부")
    private String workYn;

    /** 휴일설명 */
    @Schema(description = "휴일설명")
    private String restDesc;

    /** 최초등록시간 */
    @Schema(description = "최초등록시간")
    private String addDate;

    /** 최종변경시간 */
    @Schema(description = "최종변경시간")
    private String editDate;

    /** 최초등록자 */
    @Schema(description = "최초등록자")
    private String addWho;

    /** 최종변경자 */
    @Schema(description = "최종변경자")
    private String editWho;

    /** 등록자명 */
    @Schema(description = "등록자명")
    private String addWhoName;

    /** 수정자명 */
    @Schema(description = "수정자명")
    private String editWhoName;
    /** 행 상태 */
    @Schema(description = "행 상태")
    private String rowStatus;
    
}
