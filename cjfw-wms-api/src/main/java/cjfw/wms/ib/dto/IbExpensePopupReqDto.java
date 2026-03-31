package cjfw.wms.ib.dto;

import java.util.List;

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
 * @date : 2025.08.05
 * @description : 비용기표 POPUP 처리 요청 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.08    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "비용기표 팝업 처리 요청") 
public class IbExpensePopupReqDto extends CommonProcedureDto {	    
 
    /** 저장 헤더 정보 */
    IbExpenseDoucmentHeaderPopupResDto saveDocumentHeaderInfo;	
    
    /** 저장 상세 리스트 */
    List<IbExpenseDoucmentDetailPopupResDto> saveList;
    
    /** 저장 헤더 리스트 */
    List<IbExpenseDoucmentHeaderPopupResDto> saveHeaderList;
    
    /** 저장 결재자 리스트 */
    List<IbExpenseApprovalUserPopupResDto> saveApprUserList;    

    /** 데이터번호 */
    @Schema(description = "데이터번호", nullable = false, example = "")
    private String serialkey;
    
    /** 데이터번호 */
    @Schema(description = "데이터번호", nullable = false, example = "")
    private String expenseSn;
    
    /** 아이디/이름 */
    @Schema(description = "아이디/이름", nullable = false, example = "")
    private String useridnm;
    
    /** 아이디 */
    @Schema(description = "아이디", nullable = false, example = "")
    private String userid;
    
    /** 이름 */
    @Schema(description = "이름", nullable = false, example = "")
    private String usernm;
    
    /** 부서 */
    @Schema(description = "부서", nullable = false, example = "")
    private String userdept;
    
    /** 결재제목 */
    @Schema(description = "결재제목", nullable = false, example = "")
    private String title;
    
    /** 요청유형 */
    @Schema(description = "요청유형", nullable = false, example = "")
    private String requesttype;
    
    /** 데이터번호 목록 (구문자는 콤마) */
    @Schema(description = "데이터번호 목록 (구문자는 콤마)", nullable = false, example = "")
    private String serialkeys;
    
    /** 결재자 정보 */
    @Schema(description = "결재자 정보", nullable = false, example = "")
    private String apprUsers;
   
}
