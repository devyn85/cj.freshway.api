package cjfw.wms.wd.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2026.02.27 
 * @description : 피킹그룹내 CAR 존재여부 조회 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2026.02.27 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "피킹그룹내 CAR 존재여부 조회 결과")
public class WdAllocationBatchGroupResCarDto extends CommonDto{
	/** 존재여부 */
    @Schema(description = "존재여부", nullable = false, example = "Y")
    private String carExistYn;
}
