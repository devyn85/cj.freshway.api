package cjfw.wms.ib.dto;

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
@Schema(description = "비용기표 결재자정보 조회 결과") 
public class IbExpenseApprovalUserPopupResDto extends CommonProcedureDto {	
    
    /** 0 : 기안, 2 : 결재 */
    @Schema(description = "0 : 기안, 2 : 결재", nullable = false, example = "")
    private String requesttype;
    
    /** 0 : 기안, 2 : 결재 */
    @Schema(description = "0 : 기안, 2 : 결재", nullable = false, example = "")
    private String requesttypename;

    /** 사용자ID */
    @Schema(description = "사용자ID", nullable = false, example = "")
    private String userId;
    
    /** 직책 */
    @Schema(description = "직책", nullable = false, example = "")
    private String duty;

    /** 사용자명 */
    @Schema(description = "사용자명", nullable = false, example = "")
    private String userNm;

    /** 고객사명 */
    @Schema(description = "고객사명", nullable = false, example = "")
    private String storernm;

    /** 부서명 */
    @Schema(description = "부서명", nullable = false, example = "")
    private String deptnm;

    /** 이메일 */
    @Schema(description = "이메일", nullable = false, example = "")
    private String email;
    
    /** 결재선 */
    @Schema(description = "결재선", nullable = false, example = "")
    private String description;
    
    /** 임시번호 */
    @Schema(description = "임시번호", nullable = false, example = "")
    private long dummyLineNo;
    
    /** 순번 */
    @Schema(description = "순번", nullable = false, example = "")
    private long seq;
   
}
