package cjfw.wms.tm.dto;

import java.util.List;

import cjfw.wms.common.extend.CommonDto;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2026.02.04 
 * @description : 운송료정산운송료정산 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2026.02.04 ParkJinWoo 생성
 */
@Data
@Builder
@NoArgsConstructor            
@AllArgsConstructor
@Schema(description = "운송료정산 조회 요청Dto")
public class TmTrxCalculationCloseReqDto extends CommonProcedureDto {
    
    /** 저장리스트 */
    private List<TmTrxCalculationCloseResDto> saveList;
	
    /** 정산월 */
    @Schema(description = "정산월")
    private String sttlYm;

    /** 물류센터코드 */
    @Schema(description = "물류센터코드")
    private String dcCode;

    /** 운송사코드 */
    @Schema(description = "운송사코드")
    private String courier;

    /** 정산마감여부 */
    @Schema(description = "정산마감여부")
    private String closeYn;
    
    /** 배송일자 */
    @Schema(description = "배송일자")
    private String deliverydate;
    
}
