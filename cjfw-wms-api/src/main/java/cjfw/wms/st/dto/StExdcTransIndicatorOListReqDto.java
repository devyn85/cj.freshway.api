package cjfw.wms.st.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : ParkJinWoo 
 * @date : 2025.06.16 
 * @description : 외부비축상품별수불현황 조회 요청 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.16    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Builder
@NoArgsConstructor            
@AllArgsConstructor
@Schema(description = "외부비축상품별수불현황 조회 ") 
public class StExdcTransIndicatorOListReqDto extends CommonDto {	
    /** 센터코드 */
    @Schema(description = "센터코드")
    private String dcCode;

    /** 기준년도(YYYY) */
    @Schema(description = "기준년도(YYYY)")
    private String year;

    /** 저장조건 */
    @Schema(description = "저장조건")
    private String storageType;
    
    
    
    
    
}
