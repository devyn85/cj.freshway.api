package cjfw.wms.tm.dto;

import java.util.List;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.08.22 
 * @description : 휴일관리 기능을 구현한 REQ DTO Class 
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
@Schema(description = "휴일관리 조회 reqDto")
public class TmCalendarListReqDto extends CommonDto {
	
	 /** 저장리스트 */
    @Schema(description = "저장리스트")
	List<TmCalendarListResDto> saveList;
    
    /** 물류센터리스트 */
    @Schema(description = "물류센터리스트")
    private String[] dcCodeList;
    
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

    /** 휴일설명 */
    @Schema(description = "휴일설명")
    private String restDesc;

    /** 시작일자 */
    @Schema(description = "시작일자")
    private String date;

    /** 종료일자 */
    @Schema(description = "종료일자")
    private String dateTo;
    

    /** 근무여부 */
    @Schema(description = "근무여부")
    private String workYn;
    
    

}

