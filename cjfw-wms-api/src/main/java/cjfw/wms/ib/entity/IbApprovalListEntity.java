package cjfw.wms.ib.entity;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.08.25 
 * @description : 외부창고 결재내역 저장 Entity
 * @issues :<pre> 
  * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.25    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
public class IbApprovalListEntity extends CommonProcedureDto {
    
    /** 결재마스터일련번호 */
    @Schema(description = "결재마스터일련번호")
    private String serialkey;
    
}