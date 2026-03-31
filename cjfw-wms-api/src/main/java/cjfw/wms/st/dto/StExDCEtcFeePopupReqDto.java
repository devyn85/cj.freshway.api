package cjfw.wms.st.dto;

import java.util.List;

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
 * @description : 외부창고정산 기타비용등록 요청 DTO 
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
@Schema(description = "외부창고정산 기타비용등록 요청") 
public class StExDCEtcFeePopupReqDto extends CommonProcedureDto {	
    
    /** 저장 리스트 */
    List<StExDCStorageResDto> serialkeyList;
    
    /** 저장 리스트 */
    List<StExDCEctFeePopupResDto> priceList;
    
    /** 물류센터 */
    @Schema(description = "물류센터", nullable = false, example = "")
    private String dccode;
    
    /** 고객사 */
    @Schema(description = "고객사", nullable = false, example = "")
    private String custkey;
    
    /** 물류센터 */
    @Schema(description = "물류센터", nullable = false, example = "")
    private String fixdccode;
    
}
