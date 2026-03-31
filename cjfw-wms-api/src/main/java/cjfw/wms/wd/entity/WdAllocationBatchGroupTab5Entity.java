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
 * @date : 2025.08.20 
 * @description : 거래처상품별 상미율 Entity
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                 MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.20 	공두경 (medstorm@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "거래처상품별 상미율 Entity") 
public class WdAllocationBatchGroupTab5Entity extends CommonDto {
    /** 센터코드 */
    @Schema(description = "센터코드", nullable = false, example = "")
    private String dccode;

    /** 거래처코드 */
    @Schema(description = "거래처코드", nullable = false, example = "")
    private String custkey;
    
    /** 상품 */
    @Schema(description = "상품", nullable = false, example = "")
    private String sku;    

    /** 소비기한잔여율 */
    @Schema(description = "소비기한잔여율", nullable = false, example = "")
    private BigDecimal usebydateFreeRt;

        /** 비고 */
    @Schema(description = "비고", nullable = false, example = "")
    private String rmk1;
    
    /** 사용여부 */
    @Schema(description = "사용여부", nullable = false, example = "")
    private String useYn;
    
    /** 화주사 */
    @Schema(description = "화주사", nullable = false, example = "")
    private String gStorerkey;
    
    /** 사용자 */
    @Schema(description = "사용자", nullable = false, example = "")
    private String gUserId;

}
