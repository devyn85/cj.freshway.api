package cjfw.wms.tm.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.08.06 
 * @description : 일별임시차현황 조회 결과 기능을 구현한 reqDto Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.06 ParkJinWoo 생성
 */
@Data
@Builder
@NoArgsConstructor            
@AllArgsConstructor
@Schema(description = "일별임시차현황 조회 요청 DTO")
public class TmResultTempCarListReqDto extends CommonDto {
	
	/**저장 리스트*/
	@Schema(description = "저장리스트")
	List<TmResultTempCarListResDto> saveList;

	/** 배송일자 */
	@Schema(description = "배송일자")
	private String deliverydt;

    /** 차량 */
    @Schema(description = "차량")
    private String carno;

    /** 차량다중선택 */
    @MultiSearch 
    @Schema(description = "차량다중선택")
    private List<String> carnoMulti;

    /** 운송사 */
    @Schema(description = "운송사")
    private String carrier;

    /** 배송유형 */
    @Schema(description = "배송유형")
    private String tmDeliveryType;

    /** 권역코드 */
    @Schema(description = "권역코드")
    private String district;
    
    /** 계약유형 */
    @Schema(description = "계약유형")
    private String contractType;
    
    /** 물류센터 */
    @Schema(description = "물류센터")
    private String dcCode;
    
}
