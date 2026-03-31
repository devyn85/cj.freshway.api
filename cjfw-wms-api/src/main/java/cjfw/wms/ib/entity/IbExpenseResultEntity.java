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
public class IbExpenseResultEntity extends CommonProcedureDto {
    
    /** 인터페이스 유형 */
    @Schema(description = "인터페이스 유형", nullable = false, example = "")
    private String ifType;
    
    /** EAI 처리결과 S : 처리완료 (Success) E : 에러발생 (Error) */
    @Schema(description = "EAI 처리결과 S : 처리완료 (Success) E : 에러발생 (Error)", nullable = false, example = "")
    private String xstat;
    
    /** 생성문서번호 */
    @Schema(description = "생성문서번호", nullable = false, example = "")
    private String zreturn;
    
    /** 회계전표번호 */
    @Schema(description = "회계전표번호", nullable = false, example = "")
    private String zreBelnr;
    
    /** 송장관리번호 */
    @Schema(description = "송장관리번호", nullable = false, example = "")
    private String zinvoice;
    
    /** 순번(처리횟수) */
    @Schema(description = "순번(처리횟수)", nullable = false, example = "")
    private String zseq;
        
    /** 에러메세지 */
    @Schema(description = "에러메세지", nullable = false, example = "")
    private String xmsgs;
    
    /** 에러메세지 */
    @Schema(description = "에러메세지", nullable = false, example = "")
    private String serialkey;
    
}