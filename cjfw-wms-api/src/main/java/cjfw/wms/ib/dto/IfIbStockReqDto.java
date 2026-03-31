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
 * @date : 2025.08.05
 * @description : I/F 테이블 처리 요청 DTO 
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
@Schema(description = "I/F 테이블  처리 요청") 
public class IfIbStockReqDto extends CommonProcedureDto {	
    
    /** 등록일자 시작 */
	@Schema(description = "등록일자 시작", nullable = false, example = "")
	private String serialkey;

	/** 등록일자 종료 */
    @Schema(description = "등록일자 종료", nullable = false, example = "")
    private String adddateTo;
    
    /** 수정일자시작 */
    @Schema(description = "수정일자시작", nullable = false, example = "")
    private String editdateFrom;
    
    /** 수정일자종료 */
    @Schema(description = "수정일자종료", nullable = false, example = "")
    private String editdateTo;
	
	/** Document Date(전기일) 시작 */
    @Schema(description = "Document Date(전기일) 시작", nullable = false, example = "")
    private String docdtFrom;	
    
	/** Document Date(전기일) 종료 */
    @Schema(description = "Document Date(전기일) 종료", nullable = false, example = "")
    private String docdtTo;
    
    /** Tax Date(증빙일) 시작 */
    @Schema(description = "Tax Date(증빙일) 시작", nullable = false, example = "")
    private String taxdateFrom;

    /** Tax Date(증빙일) 종료 */
    @Schema(description = "Tax Date(증빙일) 종료", nullable = false, example = "")
    private String taxdateTo;    
    
    /** BaseLine Date 시작 */
    @Schema(description = "BaseLine Date 시작", nullable = false, example = "")
    private String baselinedateFrom;

    /** BaseLine Date 종료 */
    @Schema(description = "BaseLine Date 종료", nullable = false, example = "")
    private String baselinedateTo;
    
    /** Posting. Date 시작 */
    @Schema(description = "Posting. Date 시작", nullable = false, example = "")
    private String postingdateFrom;

    /** Posting. Date 종료 */
    @Schema(description = "Posting. Date 종료", nullable = false, example = "")
    private String postingdateTo;
    
    /** 재고월 */
    @Schema(description = "재고월", nullable = false, example = "")
    private String yyyymm;
    
    /** STATUS */
    @Schema(description = "STATUS", nullable = false, example = "")
    private String status;  //STATUS_EXPENSE
    
    /** IF_STATUS */
    @Schema(description = "IF_STATUS", nullable = false, example = "")
    private String ifStatus;    //STATUS_EXPENSE_IF
    
    /** 비용종류 */
    @Schema(description = "비용종류", nullable = false, example = "")
    private String attributes2; //EXPENSEGUBUN
    
    /** Document No */
    @Schema(description = "Document No", nullable = false, example = "")
    private String keyno;   
    
    /** ERP P/O No */
    @Schema(description = "ERP P/O No", nullable = false, example = "")
    private String erppono;
    
    /** 등록자 */
    @Schema(description = "등록자", nullable = false, example = "")
    private String addwho;
    
    /** 수정자 */
    @Schema(description = "수정자", nullable = false, example = "")
    private String editwho;
    
    /** Adj. Supplier Code */
    @Schema(description = "Adj. Supplier Code", nullable = false, example = "")
    private String adjustmentsuppliercode;
    
    /** Adj. Supplier Name */
    @Schema(description = "Adj. Supplier Name", nullable = false, example = "")
    private String adjustmentsuppliername;
    
    /** 창고 */
    @Schema(description = "창고", nullable = false, example = "")
    private String organize;
    
    /** Supplier Code */
    @Schema(description = "Supplier Code", nullable = false, example = "")
    private String supplierCode;

   
}
