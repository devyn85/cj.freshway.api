package cjfw.wms.tm.dto;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2026.01.20 
 * @description : 출도착지표 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2026.01.20 ParkJinWoo 생성
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "거래처별 메모사항 조회 request dto")
public class TmDeliveryIndicatorReqDto extends CommonProcedureDto {
	  /** 물류센터 */
    @Schema(description = "물류센터")
    private String dcCode;

    /** 기준일자(YYYYMMDD) */
    @Schema(description = "기준일자(YYYYMMDD)")
    private String deliveryDt;

    private String rateStd;
}
