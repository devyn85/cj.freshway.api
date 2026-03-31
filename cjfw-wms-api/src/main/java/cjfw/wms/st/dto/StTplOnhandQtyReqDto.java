package cjfw.wms.st.dto;

import java.util.List;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : ParkYoSep 
 * @date : 2025.11.18  
 * @description : 위탁재고현황 Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.18 parkYosep (dytpq362@cj.net) 생성
 *         </pre>
 */
@Schema(description = "위탁재고현황 Request DTO")
@Data
public class StTplOnhandQtyReqDto extends CommonProcedureDto {

    /** 저장 리스트 */
    List<StTplCalcResultResDto> saveList;
    
    /** 물류센터 */
    @Schema(description = "물류센터", nullable = false, example = "")
    private String dcCode;
        
    /** 창고 */
    @Schema(description = "창고", nullable = false, example = "")
    private String organize;    
    
    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;    
    
    /** 창고-다중검색 */
//    @MultiSearch
//    @Schema(description = "창고-다중검색", nullable = false, example = "")
//    private List<String> organizeMulti;
     
    
    /** 화주 */
    @Schema(description = "화주", nullable = false, example = "")
    private String tplUser;
    
    /** 거래처 */
    @Schema(description = "거래처", nullable = false, example = "")
    private String custkey;
    
    /** 협력사 */
    @Schema(description = "협력사", nullable = false, example = "")
    private String vendor;
    
    /** 선하증권번호 */
    @Schema(description = "선하증권번호", nullable = true, example = "")
    private String convserialno;

    /** 이력번호 */
    @Schema(description = "이력번호")
    private String serialNo;
    
}
