package cjfw.wms.wd.entity;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @date : 2025.11.21 
 * @description : 분배예외처리거래처 Entity
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                 MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.21 	공두경 (medstorm@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "분배예외처리거래처 Entity") 
public class WdAllocationBatchGroupTab6Entity extends CommonDto {
    /** 센터코드 */
    @Schema(description = "센터코드", nullable = false, example = "")
    private String dccode;

    /** 거래처코드 */
    @Schema(description = "거래처코드", nullable = false, example = "")
    private String custkey;
    
    /** 비고 */
    @Schema(description = "비고", nullable = false, example = "")
    private String rmk1;
    
    /** 사용여부 */
    @Schema(description = "사용여부", nullable = false, example = "")
    private String useYn;
    
    /** 사용자 */
    @Schema(description = "사용자", nullable = false, example = "")
    private String gUserId;

    /** 화주사 */
    @Schema(description = "화주사", nullable = false, example = "")
    private String gStorerkey;

}
