package cjfw.wms.tm.entity;

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
 * @description : 휴일관리 Entity 기능을 구현한 Entity Class 
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
@Schema(description = "유가관리 Entity") 
public class TmDailyOilPriceEntity extends CommonDto {
	
	 /** 센터코드 */
    @Schema(description = "센터코드")
    private String dcCode;

    /** 시작일자 */
    @Schema(description = "시작일자")
    private String fromDate;

    /** 종료일자 */
    @Schema(description = "종료일자")
    private String toDate;

    /** 연료유형 */
    @Schema(description = "연료유형")
    private String gasType;

    /** 평균가격 */
    @Schema(description = "평균가격")
    private String avgPrice;

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
    private String addWhoNm;

    /** 수정자명 */
    @Schema(description = "수정자명")
    private String editWhoNm;
    
    /** old센터코드 */
    @Schema(description = "old센터코드")
    private String oldDcCode;
    
    /** old시작일자 */
    @Schema(description = "old시작일자")
    private String oldFromDate;
    
    /** old종료일자 */
    @Schema(description = "old종료일자")
    private String oldToDate;
    
    /** old연료유형 */
    @Schema(description = "old연료유형")
    private String oldGasType;
    
    /** 행 상태 */
    @Schema(description = "행 상태")
    private String rowStatus;
}
