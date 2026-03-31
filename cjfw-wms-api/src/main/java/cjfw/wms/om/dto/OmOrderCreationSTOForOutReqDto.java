package cjfw.wms.om.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
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
 * @date : 2025.07.08
 * @description : 외부센터 보충발주 처리 요청 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.08    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "외부센터 보충발주 처리 요청") 
public class OmOrderCreationSTOForOutReqDto extends CommonProcedureDto {	
    /** 저장 리스트 */
    List<OmOrderCreationSTOForOutResDto> saveList;
    
	/** 물류센터 */
	@Schema(description = "물류센터", nullable = false, example = "")
	private String dccode;

	/** 공급물류센터 */
    @Schema(description = "공급물류센터", nullable = false, example = "")
    private String fromDccode;
    
    /** 공급빋는센터 */
    @Schema(description = "공급받는센터", nullable = false, example = "")
    private String toDccode;
	
	/** 창고 */
    @Schema(description = "창고", nullable = false, example = "")
    private String organize;
    
    /** 창고 */
    @MultiSearch
    @Schema(description = "창고", nullable = false, example = "")
    private List<String> organizeMulti;
	
	/** area */
    @Schema(description = "area", nullable = false, example = "")
    private String area;

    /** 상품코드 */
    @Schema(description = "상품코드", nullable = false, example = "")
    private String sku;
    
    /** 상품코드 */
    @MultiSearch
    @Schema(description = "상품코드", nullable = false, example = "")
    private List<List<String>> skuMulti;
    
    /** 저장조건 */
    @Schema(description = "저장조건", nullable = false, example = "")
    private String storagetype;
    
    /** 이력번호 */
    @Schema(description = "이력번호", nullable = false, example = "")
    private String serialno;
    
    /** BL번호 */
    @Schema(description = "BL번호", nullable = false, example = "")
    private String blno;
    
    /** BL 번호 */
    @MultiSearch
    @Schema(description = "BL 번호(다중 – 콤마/개행 구분)", nullable = false, example = "")
    private List<String> blnoMulti;
    
    /** 계약업체 */
    @Schema(description = "계약업체", nullable = false, example = "")
    private String contractcompany;
    
    /** 계약업체 */
    @MultiSearch
    @Schema(description = "계약업체", nullable = false, example = "")
    private List<String> contractcompanyMulti;
    
    /** 이체일자 */
    @Schema(description = "이체일자", nullable = false, example = "")
    private String deliverydate;
   
}
