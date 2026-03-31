package cjfw.wms.ib.entity;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.08.11 
 * @description : 비용기표 이메일로그 Entity
 * @issues :<pre> 
  * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.08    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
public class IbExpenseEmailLogEntity extends CommonProcedureDto {
    
    /** 제목*/
    @Schema(description = "제목", nullable = false, example = "")
    private String title;
    
    /** 내용 */
    @Schema(description = "내용", nullable = false, example = "")
    private String cnts;

    /** 보내는 메일 주소 */
    @Schema(description = "보내는 메일 주소", nullable = false, example = "")
    private String sendrEmailAddr;
    
    /** 받는 사람 이름 */
    @Schema(description = "받는 사람 이름", nullable = false, example = "")
    private String rcvrNm;

    /** 받는 메일 주소 */
    @Schema(description = "받는 메일 주소", nullable = false, example = "")
    private String rcvrEmailAddr;

    /** 참조 메일 주소 */
    @Schema(description = "참조 메일 주소", nullable = false, example = "")
    private String refEmailAddr;
    
}