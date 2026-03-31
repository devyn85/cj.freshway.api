package cjfw.wms.st.dto;

import java.math.BigDecimal;
import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : 고혜미(laksjd0606@cj.net)
 * @date : 2025.11.05
 * @description : KIT처리 조회 Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.05 고혜미(laksjd0606@cj.net) 생성
 *         </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "KIT처리 조회 Request DTO")
public class StMKitReqDto extends CommonProcedureDto {
    
    /** 저장 리스트 */
    List<StMKitResT1Dto> saveList;    

    /** 저장 리스트 */
    List<StMKitResT1Dto> saveKitList;
    
    /** processtype */
    @Schema(description = "processtype")
    private String processtype;
    
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
	
    /** 계획일자[FROM] */
    @Schema(description = "계획일자[FROM]")
    private String fromDate;
    
    /** 계획일자[TO] */
    @Schema(description = "계획일자[TO]")
    private String toDate;

    /** 저장조건 */
    @Schema(description = "저장조건")
    private String storagetype;
 
    /** 계획일자 */
    @Schema(description = "계획일자")
    private String planDt;
    
	/** 작업일자-멀티 */
	@MultiSearch
    @Schema(description = "작업일자-멀티")
    private List<List<String>> planDtMulti;  
    
    /** KIT 계획수량 */
    @Schema(description = "KIT 계획수량")
    private BigDecimal openqty;

    /** KIT 생산수량 */
    @Schema(description = "KIT 생산수량")
    private BigDecimal confirmqty;
    
    /** 재고속성 */
    @Schema(description = "재고속성")
    private String stockgrade;
    
    /** 재고구분ID */
    @Schema(description = "재고구분ID")
    private String stockid;
    
    /** 로케이션 */
    @Schema(description = "로케이션")
    private String loc;
    
    /** KIT 소비일자 */
    @Schema(description = "KIT 소비일자")
    private String minExpiredt;
    
    /** 소비일자 */
    @Schema(description = "소비일자")
    private String expiredt;
    
    /** 시리얼넘버 */
    @Schema(description = "시리얼넘버")
    private String serialno;
    
    /** 비고 */
    @Schema(description = "비고")
    private String rmk;
    
    /** 구분 */
    @Schema(description = "구분")
    private String procdiv;
}
