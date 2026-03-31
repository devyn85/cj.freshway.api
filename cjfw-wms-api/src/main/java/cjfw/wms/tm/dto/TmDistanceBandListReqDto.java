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
 * @date : 2025.09.11 
 * @description : 센터별구간설정 기능을 구현한 REQ DTO Class 
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
@Schema(description = "센터별구간설정 조회 reqDto")
public class TmDistanceBandListReqDto extends CommonDto {
	
	 /** 저장리스트 */
    @Schema(description = "저장리스트")
	List<TmDistanceBandListResDto> saveList;
    /** 데이터번호 */
    @Schema(description = "데이터번호")
    private Long serialKey;

    private String serialYn;
    
    /** 센터코드 */
    @Schema(description = "센터코드")
    private String dcCode;

    /** 운송사코드 */
    @Schema(description = "운송사코드")
    private String courier;

    /** 운송구간 */
    @Schema(description = "운송구간")
    private String carrierRange;

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

    /** 최초등록자 */
    @Schema(description = "최초등록자")
    private String addWho;

    /** 최종변경자 */
    @Schema(description = "최종변경자")
    private String editWho;
}
