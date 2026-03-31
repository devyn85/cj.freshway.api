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
 * @description : 비용기표 매입세금계산서 처리 요청 DTO 
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
@Schema(description = "비용기표 매입세금계산서 처리 요청") 
public class IbExpenseElecTaxPopupReqDto extends CommonProcedureDto {	
    
    /** 데이터번호 */
    @Schema(description = "데이터번호", nullable = false, example = "")
    private String serialkey;
    
    /** 전자세금계산서 맵핑조건 */
    @Schema(description = "전자세금계산서 맵핑조건", nullable = false, example = "")
    private String bupla;
    
    /** 협력사 코드 */
    @Schema(description = "협력사 코드", nullable = false, example = "")
    private String adjustmentSupplierCode;
    
    /** 협력사 명칭 */
    @Schema(description = "협력사 명칭", nullable = false, example = "")
    private String adjustmentSupplierName;
    
    /** 사업자번호 */
    @Schema(description = "사업자번호", nullable = false, example = "")
    private String cbRegisno;
    
    /** 시작일자 */
    @Schema(description = "시작일자", nullable = false, example = "")
    private String issuedateFrom;
    
    /** 종료일자 */
    @Schema(description = "종료일자", nullable = false, example = "")
    private String issuedateTo;
    
    /** 전표매핑여부 */
    @Schema(description = "전표매핑여부", nullable = false, example = "")
    private String docFlag;
   
}
