package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.08.20 
 * @description : 센터 권역 조회 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.20 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "센터권역 조회 결과 DTO")
public class MsCenterDistrictResDto {
	
    @Schema(description = "물류센터 코드", example = "2600")
    private String dccode;

    @Schema(description = "물류센터명", example = "부산물류센터")
    private String dcname;
    
    @Schema(description = "센터 권역 폴리곤", example = "geojson")
    private String geojson;
    
    @Schema(description = "센터 유형", example = "FW")
    private String dccategory;

    @Schema(description = "시작일자")
    private String fromdate;

    @Schema(description = "종료일자")
    private String todate;
}
