package cjfw.wms.ib.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.08.08
 * @description : 비용기표 매입세금계산서 조회 결과 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.08   KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "비용기표 인터페이스 조회 결과") 
public class IbExpenseIfStatusPopupResDto extends CommonProcedureDto {	
    
    /** 트랜잭션코드 */
    @Schema(description = "트랜잭션코드", nullable = false, example = "")
    private String transactionCd;

    /** 송장관리번호 */
    @Schema(description = "송장관리번호", nullable = false, example = "")
    private String zinvoice;

    /** 순번(처리횟수) */
    @Schema(description = "순번(처리횟수)", nullable = false, example = "")
    private BigDecimal zseq;

    /** 생성문서번호 */
    @Schema(description = "생성문서번호", nullable = false, example = "")
    private String zreturn;

    /** 인터페이스ID */
    @Schema(description = "인터페이스ID", nullable = false, example = "")
    private String ifId;

    /** 전송상태 */
    @Schema(description = "전송상태", nullable = false, example = "")
    private String fiIfStatus;

    /** 에러메세지 */
    @Schema(description = "에러메세지", nullable = false, example = "")
    private String xmsgs;

    /** 최종변경시간 */
    @Schema(description = "최종변경시간", nullable = false, example = "")
    private String editdate;

    /** 최종변경자 */
    @Schema(description = "최종변경자", nullable = false, example = "")
    private String editwho;

}
