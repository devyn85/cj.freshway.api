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
 * @date : 2025.09.11 
 * @description : 휴일관리 Entity 기능을 구현한 Entity Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.11 ParkJinWoo 생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "휴일관리 Entity") 
public class TmDistanceBandEntity extends CommonDto {
	/** 데이터번호 */
    @Schema(description = "데이터번호")
    private Long serialKey;

    /** 센터코드 */
    @Schema(description = "센터코드")
    private String dcCode;

    /** 운송사코드 */
    @Schema(description = "운송사코드")
    private String courier;

    /** 운송구간 */
    @Schema(description = "운송구간")
    private String courierRange;

    /** 행정동코드 */
    @Schema(description = "행정동코드")
    private String hjdongCd;

    /** 비고 */
    @Schema(description = "비고")
    private String rmk;

    /** 적용시작일자(YYYYMMDD) */
    @Schema(description = "적용시작일자(YYYYMMDD)")
    private String fromDate;

    /** 적용종료일자(YYYYMMDD) */
    @Schema(description = "적용종료일자(YYYYMMDD)")
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

    /** 등록자명 */
    @Schema(description = "등록자명")
    private String addWhoName;

    /** 수정자명 */
    @Schema(description = "수정자명")
    private String editWhoName;

    /** 수정자명 */
    @Schema(description = "시도명")
    private String ctpKorNm;

    /** 수정자명 */
    @Schema(description = "시군구명")
    private String sigKorNm;
    /** 수정자명 */
    @Schema(description = "행정동")
    private String hjdongNm;
    
    /** 행 상태 */
    @Schema(description = "행 상태")
    private String rowStatus;
    
    /** 행 상태 */
    @Schema(description = "운송구간상세")
    private String courierRangeDtl;
    
    
    
//    /**센터구간 */
//    @Schema(description = "센터구간")
//    private String courierRange;
}
