package cjfw.wms.wd.entity;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @date : 2025.08.21 
 * @description : 피킹유형 미정의 관리처 원거리유형 Entity
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                 MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.21 	공두경 (medstorm@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "피킹유형 미정의 관리처 원거리유형 Entity") 
public class WdAllocationBatchGroupTab3Entity extends CommonDto {
    /** serialkey */
    @Schema(description = "serialkey", nullable = false, example = "")
    private BigDecimal serialkey;

    /** 원거리유형 */
    @Schema(description = "원거리유형", nullable = false, example = "")
    private String mngDistancetype;
    

    /** 사용자 */
    @Schema(description = "사용자", nullable = false, example = "")
    private String gUserId;
    

}
