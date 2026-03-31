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
 * @date : 2025.09.02
 * @description : 외부창고 원가관리리포트 처리 요청 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.02    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "외부창고 원가관리리포트 처리 요청") 
public class IbExpenseStatusReqDto extends CommonProcedureDto {	
    
    /** 저장리스트 */
    List<IbExpenseStatusResDto> saveList;
    
    /** 헤더 조회 리스트 */
    List<IbExpenseStatusResDto> headerList;
    
    /** 상세 조회 리스트 */
    List<IbExpenseStatusResDto> detailList;
    
    /** 조회유형 */
    @Schema(description = "조회유형", nullable = true, example = "")
    private String selectType;
    
    /** Item Code */
	@Schema(description = "Item Code", nullable = true, example = "")
	private String itemcode;

	/** Customer Code */
    @Schema(description = "Customer Code", nullable = true, example = "")
    private String customercode;

    /** ERP P/O No */
    @Schema(description = "ERP P/O No", nullable = true, example = "")
    private String erppono;
	
	/** PO Line No */
    @Schema(description = "PO Line No", nullable = true, example = "")
    private String polineno;	

    /** House B/L No */
    @Schema(description = "House B/L No", nullable = true, example = "")
    private String houseblno;
    
    /** 기준일자 */
    @Schema(description = "기준일자", nullable = true, example = "")
    private String postingdate;
    
}
