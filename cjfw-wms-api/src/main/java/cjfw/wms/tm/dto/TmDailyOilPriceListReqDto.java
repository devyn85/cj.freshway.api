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
 * @date : 2025.09.05 
 * @description : 휴일관리 기능을 구현한 REQ DTO Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.05 ParkJinWoo 생성
 */

@Data
@Builder
@NoArgsConstructor            
@AllArgsConstructor
@Schema(description = "유가관리 조회 reqDto")
public class TmDailyOilPriceListReqDto extends CommonDto {
	
	 /** 저장리스트 */
    @Schema(description = "저장리스트")
	List<TmDailyOilPriceListResDto> saveList;
    /** 센터코드 */
    @Schema(description = "센터코드")
    private String dcCode;

    /** 연료유형 */
    @Schema(description = "연료유형")
    private String gasType;

    /** 조회시작일 */
    @Schema(description = "조회시작일")
    private String startdateTo;

    /** 조회종료일 */
    @Schema(description = "조회종료일")
    private String enddateFrom;
}
