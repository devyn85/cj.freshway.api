package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KimSunHo (sunhokim6229@cj.net) 
 * @date : 2025.08.09
 * @description : Supplier 검색 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.09 KimSunHo (sunhokim6229@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "Supplier 검색 응답")
public class CmSearchSupplierPopupResDto {
	/** Supplier 코드 */
	@Schema(description = "Supplier 코드", nullable = false, example = "")
	private String code;
	
	/** Supplier명 */
	@Schema(description = "Supplier명", nullable = false, example = "")
	private String name;
	
	/** 사업자번호 */
    @Schema(description = "사업자번호", nullable = false, example = "")
    private String vatno;
    
    /** 지급코드 */
    @Schema(description = "지급코드", nullable = false, example = "")
    private String paymentTerm;
    
    /** 지급명 */
    @Schema(description = "지급명", nullable = false, example = "")
    private String paymentTermName;
}