package cjfw.wms.st.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2026.02.03 
 * @description : 수기출고현황 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2026.02.03 ParkJinWoo 생성
 */
@Schema(description = "수기출고현황 Request DTO")
@Data
public class StExdcManualIndicatorReqDto extends CommonDto {
    
    /** 기준월(YYYYMM) */
    @Schema(description = "기준월(YYYYMM)")
    private String baseYyyymm;

    /** 물류센터코드 */
    @Schema(description = "물류센터코드")
    private String dcCode;


    /** 창고코드 */
    @Schema(description = "창고코드")
    private String organize;



    
    
}
