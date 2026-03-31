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
 * @date : 2025.08.25
 * @description : 외부창고 결재내역 조회 결과 DTO 
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
@Schema(description = "외부창고 결재내역 조회 결과") 
public class IbApprovalListResDto extends CommonProcedureDto {	
    
    /** 결재최종상태 */
    @Schema(description = "결재최종상태", nullable = false, example = "")
    private String apprStatus;

    /** 상태코드명 */
    @Schema(description = "결재최종상태명", nullable = false, example = "")
    private String apprStatusName;

    /** 결재문서번호 */
    @Schema(description = "결재문서번호", nullable = false, example = "")
    private String apprNo;

    /** 상신제목 */
    @Schema(description = "상신제목", nullable = false, example = "")
    private String title;

    /** 최초등록자 */
    @Schema(description = "최초등록자", nullable = false, example = "")
    private String addwho;

    /** 최초등록자명 */
    @Schema(description = "최초등록자명", nullable = false, example = "")
    private String addwhoname;
    
    /** 최초등록일시 */
    @Schema(description = "최초등록일시", nullable = false, example = "")
    private String adddate;
    
    /** 첨부파일여부 */
    @Schema(description = "첨부파일여부", nullable = false, example = "")
    private String lovFile;
    
    /** 결재자아이디 */
    @Schema(description = "결재자아이디", nullable = false, example = "")
    private String approvalwho;

    /** 결재자명 */
    @Schema(description = "결재자명", nullable = false, example = "")
    private String approvalwhoname;
    
    /** 기안일시 */
    @Schema(description = "기안일시", nullable = false, example = "")
    private String reqdt;

    /** 기안/결재일시 */
    @Schema(description = "기안/결재일시", nullable = false, example = "")
    private String approvaldt;

    /** 결재마스터일련번호 */
    @Schema(description = "결재마스터일련번호", nullable = false, example = "")
    private String serialkey;

    /** 결재문서일련번호 */
    @Schema(description = "결재문서일련번호", nullable = false, example = "")
    private String documentSn;
    
    /** 결재문서일련번호 */
    @Schema(description = "결재문서일련번호", nullable = false, example = "")
    private String adSn;
    
    /** 첨부파일건수 */
    @Schema(description = "첨부파일건수", nullable = false, example = "")
    private BigDecimal filecountsn;
    
    /** 경비처리일련번호 */
    @Schema(description = "경비처리일련번호", nullable = false, example = "")
    private BigDecimal expenseSn;
    
    /** 결재구분 (0:기안,1:합의,2:결재3:확인,9:통보) */
    @Schema(description = "결재구분 (0:기안,1:합의,2:결재3:확인,9:통보)", nullable = false, example = "")
    private String lineType;
    
    /** 결재구분명 */
    @Schema(description = "결재구분명", nullable = false, example = "")
    private String lineTypeName;
    
    /** 직위 */
    @Schema(description = "직위", nullable = false, example = "")
    private String personPosition;
    
    /** 성명 */
    @Schema(description = "성명", nullable = false, example = "")
    private String personName;
    
    /** 전화번호*/
    @Schema(description = "전화번호", nullable = false, example = "")
    private String phone;  
    
    /** 기안/결재일시*/
    @Schema(description = "기안/결재일시", nullable = false, example = "")
    private String apprDate;
    
    /** 인터페이스유형*/
    @Schema(description = "인터페이스유형", nullable = false, example = "")
    private String ifType;
}
