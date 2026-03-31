package cjfw.wms.tm.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.10.10 
 * @description : 운송비정산서 요청 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.10    KimSunHo(sunhokim6229@cj.net)   생성 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "운송비정산서 요청") 
public class TmTrxCalculationReportReqDto extends CommonProcedureDto {	
    
    /** 물류센터 */
    @Schema(description = "물류센터", nullable = true, example = "")
    private String fixdccode;
    
    /** 기준일자 */
	@Schema(description = "기준일자", nullable = true, example = "")
	private String fromdate;
	
	/** 기준일자 */
    @Schema(description = "기준일자", nullable = true, example = "")
    private String todate;
	
	/** 정산구분 */
	@Schema(description = "정산구분", nullable = true, example = "")
	private String deliveryType;
	
	/** 마감유형 */
    @Schema(description = "마감유형", nullable = true, example = "")
    private String closeType;
    
    /** 계약유형 */
    @Schema(description = "계약유형", nullable = true, example = "")
    private String contractType;
    
    /** 운송사 */
    @Schema(description = "운송사", nullable = true, example = "")
    private String courier;
    
    /** 운송사 */
    @MultiSearch
    @Schema(description = "운송사", nullable = true, example = "")
    private List<String> courierMulti;
    
    /** 차량번호 */
    @Schema(description = "차량번호", nullable = true, example = "")
    private String carno;
    
    /** 차량번호 */
    @MultiSearch
    @Schema(description = "차량번호", nullable = true, example = "")
    private List<String> carnoMulti;
    
    /** 톤급 */
    @Schema(description = "톤급", nullable = true, example = "")
    private String carcapacity;
	
}
