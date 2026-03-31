package cjfw.wms.wd.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.11.21 
 * @description : 출고분배 관련 필요정보 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.21 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "출고분배 관련 필요정보 결과")
public class WdAllocationBatchGroupResCheckDto extends CommonDto{
	/** 선마감여부 */
    @Schema(description = "선마감여부", nullable = false, example = "Y")
    private String dailyDeadlineYn;
}
