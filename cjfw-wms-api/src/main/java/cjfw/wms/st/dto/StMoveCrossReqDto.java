package cjfw.wms.st.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : sss (kduimux@cj.net)
 * @date : 2025.08.25
 * @description : CROSS자동보충 Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.25 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Schema(description = "CROSS자동보충 Request DTO")
@Data
public class StMoveCrossReqDto extends CommonProcedureDto {
	/** 메인그리드 저장 리스트 */
	List<StMoveCrossResDetailDto> saveList;	
	
    /** 고정센터코드 */
    @Schema(description = "고정센터코드")
    private String fixdccode;		
	
    /** 보관유형 */
    @Schema(description = "보관유형")
    private String storagetype;

    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;
    
    /** 상품(다중OR검색) */
	@MultiSearch
    @Schema(description = "상품-다중OR검색")
    private List<List<String>> skuMulti;      

    /** 입고로케이션 */
    @Schema(description = "입고로케이션")
    private String toLoc;

    /** 입고로트 */
    @Schema(description = "입고로트")
    private String toLot;

    /** 입고단위 */
    @Schema(description = "입고단위")
    private String toUom;

    /** 센터코드 */
    @Schema(description = "센터코드")
    private String dccode;

    /** 화주코드 */
    @Schema(description = "화주코드")
    private String storerkey;

    /** 조직 */
    @Schema(description = "조직")
    private String organize;

    /** 구역 */
    @Schema(description = "구역")
    private String area;


    /** 재고등급 */
    @Schema(description = "재고등급")
    private String stockgrade;

    /** 재고ID */
    @Schema(description = "재고ID")
    private String stockid;
    


    /** 단위 */
    @Schema(description = "단위")
    private String uom;

    /** 로케이션 */
    @Schema(description = "로케이션")
    private String loc;

    /** 로트 */
    @Schema(description = "로트")
    private String lot;    
    
    
}
