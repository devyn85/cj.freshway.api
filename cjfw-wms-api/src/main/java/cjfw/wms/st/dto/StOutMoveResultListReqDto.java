package cjfw.wms.st.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.07.29
 * @description : 외부비축센터간이동reㅂDto 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.29 ParkJinWoo 생성 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "외부비축센터간이동reqDto") 
public class StOutMoveResultListReqDto extends CommonDto{

    /** 사용자 ID */
    @Schema(description = "사용자 ID")
    private String gUserId;

    /** 세션 SPID */
    @Schema(description = "세션 SPID")
    private String gSpid;
    
    
}
