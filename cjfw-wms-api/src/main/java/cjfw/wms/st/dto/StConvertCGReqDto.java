package cjfw.wms.st.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : 고혜미(laksjd0606@cj.net)
 * @date : 2025.09.18
 * @description : 재고속성변경조회 Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.18 고혜미(laksjd0606@cj.net) 생성
 *         </pre>
 */
@Schema(description = "재고속성변경조회 Request DTO")
@Data
public class StConvertCGReqDto extends CommonProcedureDto {
    
    /** 저장 리스트 */
    List<StConvertCGResDto> saveList;

    /** 물류센터 */
    @Schema(description = "물류센터")
    private String fixdccode;
    
    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;    
    
    /** 상품(다중OR검색) */
	@MultiSearch
    @Schema(description = "상품-다중OR검색")
    private List<List<String>> skuMulti;
    
    /** 정렬키 */
    @Schema(description = "정렬순서")
    private String sortkey;
    
    /** 창고구분 */
    @Schema(description = "창고구분")
    private String wharea;
    
    /** 창고층 */
    @Schema(description = "창고층")
    private String whareafloor;    

    /** 재고속성 */
    @Schema(description = "재고속성", example = "")
    private String stockgrade; 
    
    /** FROM존 */
    @Schema(description = "FROMZONE")
    private String fromzone;

    /** TO존 */
    @Schema(description = "TOZONE")
    private String tozone;
    
    /** 피킹존 */
    @Schema(description = "피킹존")
    private String zone;          
        
    /** FROM로케이션 */
    @Schema(description = "FROMLOC")
    private String fromloc;

    /** TO로케이션 */
    @Schema(description = "TOLOC")
    private String toloc;
    
    /** 로케이션종류 */
    @Schema(description = "로케이션종류")
    private String loccategory;         
    
    /** 로케이션유형 */
    @Schema(description = "로케이션종류")
    private String loctype;       
    
    /** 창고 */
    @Schema(description = "창고")
    private String organize;     
    
    private String searchbl;

    
    
}
