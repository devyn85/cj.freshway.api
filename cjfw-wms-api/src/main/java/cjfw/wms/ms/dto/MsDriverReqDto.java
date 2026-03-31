package cjfw.wms.ms.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : SangSuSung(kduimux@cj.com) 
 * @date : 2025.05.08 
 * @description : 기사정보 Controller Class 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.08 SangSuSung(kduimux@cj.com) 생성 </pre>
 */
@Data
@EqualsAndHashCode(callSuper=true) 
@Builder
public class MsDriverReqDto extends CommonDto {
	
    /** 운전자고유키 */
    @Schema(description = "운전자고유키", example = "drv001")
    private String driverid;

    /** 운전자명 */
    @Schema(description = "운전자명", example = "김철수")
    private String drivername;
    
    /** 소속사명 */
    @Schema(description = "소속사명", example = "굿모닝운수")
    private String agentname;    
    
    /** 삭제여부 */
    @Schema(description = "삭제여부", example = "N")
    private String delYn;
    
    
}
