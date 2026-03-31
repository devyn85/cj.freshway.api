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
 * @date : 2025.09.16 
 * @description : 휴일관리 기능을 구현한 REQ DTO Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.16 ParkJinWoo 생성
 */

@Data
@Builder
@NoArgsConstructor            
@AllArgsConstructor
@Schema(description = "휴일관리 조회 reqDto")
public class TmAttendanceListReqDto extends CommonDto {
	
	 /** 저장리스트 */
    @Schema(description = "저장리스트")
	List<TmAttendanceListResDto> saveList;
    /** 센터코드 */
    @Schema(description = "센터코드")
    private String dcCode;

    /** 연도(YYYY) */
    @Schema(description = "연도(YYYY)")
    private String yy;

    /** 월(MM) */
    @Schema(description = "월(MM)")
    private String mm;

    /** 차량번호 */
    @Schema(description = "차량번호")
    private String carNo;

    /** 차량유형 */
    @Schema(description = "차량유형")
    private String carType;

    /** 계약유형 */
    @Schema(description = "계약유형")
    private String contractType;

    /** 운송사키(CARRIERKEY) */
    @Schema(description = "운송사키(CARRIERKEY)")
    private String carrierKey;
}
