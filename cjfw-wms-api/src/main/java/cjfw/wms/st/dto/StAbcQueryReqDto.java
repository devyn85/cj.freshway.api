package cjfw.wms.st.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.11.12
 * @description : 재고 > 재고운영 > ABC 분석 Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.12 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "재고 > 재고운영 > ABC 분석 Request DTO")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StAbcQueryReqDto extends CommonProcedureDto {
    /** 물류센터 */
    @Schema(description = "물류센터")
    private String fixdccode;	
    
    /** 물류센터(다중검색) */
    @MultiSearch
    @Schema(description = "물류센터-다중검색")
    private List<String> fixdccodeMulti;      
		
	
	/* 물류센터 */
	@Schema(description = "물류센터")
	private String dccode;
	
	/* 기준일자 */
	@Schema(description = "기준일자")
	private String docdt;
	
	/* 창고 */
	@Schema(description = "창고")
	private String organize;
	
	/* 창고-멀티 */
	@MultiSearch
    @Schema(description = "창고-멀티")
    private List<String> organizeMulti; 
	
	/* 피킹존 From */
	@Schema(description = "피킹존 From")
	private String fromzone;

	/* 피킹존 To */
	@Schema(description = "피킹존 To")
	private String tozone;
	
	/* 상품코드 */
	@Schema(description = "상품코드")
	private String sku;
	
	/* 상품코드-멀티 */
	@MultiSearch
    @Schema(description = "상품코드-멀티")
    private List<List<String>> skuMulti;  
	
	/* 창고구분 */
	@Schema(description = "창고구분")
	private String wharea;
	
	/* 로케이션 종류 */
	@Schema(description = "로케이션 종류")
	private String loccategory;
	
	/* 로케이션 From */
	@Schema(description = "로케이션 From")
	private String fromloc;

	/* 로케이션 To */
	@Schema(description = "로케이션 To")
	private String toloc;
	
	/* 제외존 */
	@Schema(description = "제외존")
	private String excludeZone;
	
	/* saveDetailList */
	@Schema(description = "saveDataT2List")
	private List<StAbcQueryResT2Dto> saveDataT2List;
}
