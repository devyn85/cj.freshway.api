package cjfw.wms.tm.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.06.30 
 * @description : 납품서출력로그(관리자) 조회 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.30 ParkJinWoo 생성
 */
@Data
@Builder
@NoArgsConstructor            
@AllArgsConstructor
@Schema(description = "배차작업로그(거래처별) 요청 DTO")
public class TmInvoicelogMgrReqDto extends CommonDto {
//	  @Schema(description = "물류센터 코드", example = "DC01")
//	    private String dcCode;
//
//	    @Schema(description = "위탁사(화주) 코드", example = "STORER_01")
//	    private String storerKey;

	    /** 고정값이지만 필터링용으로 노출 */
	    @Schema(description = "전표 유형")
	    private String docType;
	    
	    /** 고정값이지만 필터링용으로 노출 */
	    @Schema(description = "물류센터")
	    private String dcCode;

	    @Schema(description = "고객사 코드 (LIKE 검색)", example = "CU001")
	    private String custKey;

	    @Schema(description = "전표 번호 (LIKE 검색)", example = "IV20250627-001")
	    private String docNo;

	    @Schema(description = "전표일 From (YYYYMMDD)", example = "20250601")
	    private String fromInvoiceDt;

	    @Schema(description = "전표일 To (YYYYMMDD)", example = "20250630")
	    private String toInvoiceDt;
	    
	    @Schema(description = "출력일자", example = "20250630")
	    private String prtData01;
}
