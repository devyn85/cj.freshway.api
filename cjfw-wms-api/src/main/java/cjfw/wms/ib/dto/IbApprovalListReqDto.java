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
 * @date : 2025.08.25
 * @description : 외부창고 결재내역 처리 요청 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.25    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "외부창고 결재내역 처리 요청") 
public class IbApprovalListReqDto extends CommonProcedureDto {	
    
    /** 저장리스트 */
    List<IbApprovalListResDto> saveList;	
    
    /** 일자 시작 */
	@Schema(description = "일자 시작", nullable = true, example = "")
	private String dateFrom;

	/** 일자 종료 */
    @Schema(description = "일자 종료", nullable = true, example = "")
    private String dateTo;
    
    /** 결재구분 (0:기안,1:합의,2:결재3:확인,9:통보) */
    @Schema(description = "결재구분 (0:기안,1:합의,2:결재3:확인,9:통보)", nullable = true, example = "")
    private String lineType;

    /** 기안/결재자이름 */
    @Schema(description = "기안/결재자이름", nullable = true, example = "")
    private String personName;
	
	/** 일자 유형 */
    @Schema(description = "일자 유형", nullable = true, example = "")
    private String dateType;	
    
    /** 결재최종상태('':전체, 1:미결, 2:완결,3:반려,4:상신취소) */
    @Schema(description = "결재최종상태('':전체, 1:미결, 2:완결,3:반려,4:상신취소)", nullable = true, example = "")
    private String apprStatus;    
    
    /** 결재처리유형 (2:결재, 3:반려) */
    @Schema(description = "결재처리유형 (2:결재, 3:반려)", nullable = true, example = "")
    private String approvalType;
    
    /** 데이터번호 */
    @Schema(description = "데이터번호", nullable = true, example = "")
    private String serialkey;
   
}
