package cjfw.wms.st.dto;

import java.math.BigDecimal;
import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : 고혜미(laksjd0606@cj.net)
 * @date : 2025.10.21
 * @description : KIT상품 계획등록조회 Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.18 고혜미(laksjd0606@cj.net) 생성
 *         </pre>
 */
@Schema(description = "KIT상품 계획등록조회 Request DTO")
@Data
public class StKitPlanReqDto extends CommonProcedureDto {
    
    /** 저장 리스트 */
    List<StKitPlanResT1Dto> saveList;
    
    /** 물류센터 */
    @Schema(description = "물류센터")
    private String fixdccode;
    
    /** 물류센터 */
    @Schema(description = "물류센터")
    private String dccode; 
    
    
    /** KIT상품코드 */
    @Schema(description = "KIT상품코드")
    private String kitSku;    

	/** 상품코드(다중OR검색) */
	@MultiSearch
    @Schema(description = "상품코드-다중OR검색")
    private List<List<String>> kitSkuMulti; 
	

    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;
    
    /** 상품(다중OR검색) */
	@MultiSearch
    @Schema(description = "상품-다중OR검색")
    private List<List<String>> skuMulti;
	
    /** 조회 월 */
    @Schema(description = "조회 월")
    private String month;
    
    /** 계획일 */
    @Schema(description = "계획일")
    private String planDt;
    
    /** DP(영업요청수량) */
    @Schema(description = "DP(영업요청수량)")
    private BigDecimal dpQty;
    
    /** 요청수량 */
    @Schema(description = "요청수량")
    private BigDecimal orderqty;
    
    /** 계획수량 */
    @Schema(description = "계획수량")
    private BigDecimal openqty;
    
    /** 생산수량 */
    @Schema(description = "생산수량")
    private BigDecimal confirmqty;
    
    
}
