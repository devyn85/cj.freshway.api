package cjfw.wms.st.dto;

import java.math.BigDecimal;

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
 * @date : 2025.07.21 
 * @description : 외부창고정산 보관료계산 요청 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.70.21    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "외부창고정산 보관료계산 요청") 
public class StExDCStoragePopupReqDto extends CommonProcedureDto {	
    
    /** 실행모드 */
    @Schema(description = "실행모드", nullable = false, example = "")
    private String procType;
    
    /** 물류센터 */
    @Schema(description = "물류센터", nullable = false, example = "")
    private String fixdccode;
    
    /** 창고 */
    @Schema(description = "창고", nullable = false, example = "")
    private String organize;
    
    /** 마감월 */
    @Schema(description = "마감월", nullable = false, example = "")
    private String yyyymm;
    
    /** 과세금액 */
    @Schema(description = "과세금액", nullable = false, example = "")
    private BigDecimal w1amt;
    
    /** 면세금액 */
    @Schema(description = "면세금액", nullable = false, example = "")
    private BigDecimal x3amt;
    
}
