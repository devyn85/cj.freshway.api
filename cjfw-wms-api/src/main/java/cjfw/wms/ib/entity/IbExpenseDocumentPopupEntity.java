package cjfw.wms.ib.entity;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.08.11 
 * @description : 비용기표 DocumentInfo Entity
 * @issues :<pre> 
  * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.08    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
public class IbExpenseDocumentPopupEntity extends CommonProcedureDto {
    
    /** 데이터번호 */
    @Schema(description = "데이터번호", nullable = false, example = "")
    private String serialkey;

    /** 데이터번호 */
    @Schema(description = "데이터번호", nullable = false, example = "")
    private String expenseSn;
    
    /** 부가세차액 */
    @Schema(description = "부가세차액", nullable = false, example = "")
    private BigDecimal taxDiff;
    
    /** 공급가차액 */
    @Schema(description = "공급가차액", nullable = false, example = "")
    private BigDecimal supplyPriceDiff;
}