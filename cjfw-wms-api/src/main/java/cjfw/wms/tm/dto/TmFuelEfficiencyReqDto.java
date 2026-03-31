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
 * @date : 2025.09.10 
 * @description : 연비관리 기능을 구현한 REQ DTO Class 
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
@Schema(description = "연비관리 조회 reqDto")
public class TmFuelEfficiencyReqDto extends CommonDto {

	private List<TmFuelEfficiencyResDto> saveList;
    /** 센터코드 */
    @Schema(description = "센터코드")
    private String dcCode;

    /** 적용시작일자 */
    @Schema(description = "적용시작일자")
    private String fromDate;

    /** 적용종료일자 */
    @Schema(description = "적용종료일자")
    private String toDate;

    /** 톤수 */
    @Schema(description = "톤수")
    private String ton;

    /** 유종구분 */
    @Schema(description = "유종구분")
    private String fuelType;
    
    /** 이력조회 */
    @Schema(description = "이력조회")
    private String serialYn;
}
