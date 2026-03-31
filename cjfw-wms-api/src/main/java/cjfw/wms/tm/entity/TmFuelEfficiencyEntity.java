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
 * @date : 2025.09.10 
 * @description : 연비관리 Entity 기능을 구현한 Entity Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.10 ParkJinWoo 생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "연비관리 Entity") 
public class TmFuelEfficiencyEntity extends CommonDto {
	
	 /** 데이터번호 */
    @Schema(description = "데이터번호")
    private String serialKey;

    /** 센터코드 */
    @Schema(description = "센터코드")
    private String dcCode;

    /** 시작연식 */
    @Schema(description = "시작연식")
    private Integer yearFrom;

    /** 종료연식 */
    @Schema(description = "종료연식")
    private Integer yearTo;

    /** 톤수 */
    @Schema(description = "톤수")
    private String ton;

    /** 유종구분 */
    @Schema(description = "유종구분")
    private String fuelType;

    /** 연비 */
    @Schema(description = "연비")
    private Double fuelEfficiency;

    /** 적용시작일자 */
    @Schema(description = "적용시작일자")
    private String fromDate;

    /** 적용종료일자 */
    @Schema(description = "적용종료일자")
    private String toDate;

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

    /** 운송사명 */
    @Schema(description = "운송사명")
    private String description;

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
