package cjfw.wms.rt.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.06.27 
 * @description :외부비축협력사반품지시 요청 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.27    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "외부비축협력사반품지시 목록 요청") 
public class RtReturnOutExDCReqDto extends CommonProcedureDto {
    
    /** 저장 리스트 */
    List<RtReturnOutExDCResDto> saveHeaderList;
    
    /** 저장 리스트 */
    List<RtReturnOutExDCResDto> saveDetailList;
    
    /** 물류센터 */
    @Schema(description = "물류센터", nullable = false, example = "")
    private String fixdccode;    

	/** 상품코드 */
    @Schema(description = "상품코드", nullable = false, example = "")
    private String sku;
    
    /** 상품코드 */
    @MultiSearch
    @Schema(description = "상품코드", nullable = false, example = "")
    private List<List<String>> skuMulti;
    
    /** 창고 */
    @Schema(description = "창고", nullable = false, example = "")
    private String organize;    
    
    /** area */
    @Schema(description = "area", nullable = false, example = "")
    private String area;
    
    /** 반품일자 */
    @Schema(description = "반품일자", nullable = false, example = "")
    private String docdt; 
    
    /** 식별번호유무 */
    @Schema(description = "식별번호유무", nullable = false, example = "")
    private String serialyn;
    
    /** 재고위치 */
    @Schema(description = "재고위치", nullable = false, example = "")
    private String stocktype;
    
    /** stockgrade */
    @Schema(description = "stockgrade", nullable = false, example = "")
    private String stockgrade;
    
    /** B/L번호 */
    @Schema(description = "B/L번호", nullable = false, example = "")
    private String blno;
    
	/** BL 번호 */
    @MultiSearch
    @Schema(description = "BL 번호(다중 – 콤마/개행 구분)", nullable = false, example = "")
    private List<String> blnoMulti;
    
    /** 이력번호 */
    @Schema(description = "이력번호", nullable = false, example = "")
    private String serialno;
    
    /** 계약업체 */
    @Schema(description = "계약업체", nullable = false, example = "")
    private String wdCustkey;
    
    /** 계약업체 */
    @MultiSearch
    @Schema(description = "계약업체", nullable = false, example = "")
    private List<String> wdCustkeyMulti;
    
    /** 협력사코드 */
    @Schema(description = "협력사코드", nullable = false, example = "")
    private String fromCustkey;
    
    /** 협력사코드 */
    @MultiSearch
    @Schema(description = "협력사코드", nullable = false, example = "")
    private List<String> fromCustkeyMulti;
    
    /** processtype */
    @Schema(description = "processtype", nullable = false, example = "")
    private String processtype;
    
    /** 협력사반품일자 */
    @Schema(description = "협력사반품일자", nullable = false, example = "")
    private String wdDate;
    
    /** docExistYn */
    @Schema(description = "docExistYn", nullable = false, example = "")
    private String docExistYn;
	
}
