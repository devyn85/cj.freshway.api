package cjfw.wms.st.dto;

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
 * @date : 2025.09.04 
 * @description : 외부창고 API 재고현황 조회 요청 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.04    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "외부창고 API 재고현황 조회 요청") 
public class StDailyOnhandQtyAPIReqDto extends CommonProcedureDto {	

    /** 물류센터 */
	@Schema(description = "물류센터", nullable = true, example = "")
	private String fixdccode;
	
	/** 창고 */
	@Schema(description = "창고", nullable = true, example = "")
	private String organize;
	
	/** 조회일자 */
	@Schema(description = "조회일자", nullable = true, example = "")
	private String searchdate;
	
	/** 상품코드 */
    @Schema(description = "상품코드", nullable = true, example = "")
    private String sku;
    
    /** B/L번호 */
    @Schema(description = "B/L번호", nullable = true, example = "")
    private String convserialno;
    
}
