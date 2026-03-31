package cjfw.wms.ib.entity;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.08.11 
 * @description : 비용기표 저장 Entity
 * @issues :<pre> 
  * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.08    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
public class IbExpenseIfIbStockEntity extends CommonProcedureDto {
    
    /** 인터페이스 문서번호 */
    @Schema(description = "인터페이스 문서번호", nullable = false, example = "")
    private String ifDocNo;
    
}