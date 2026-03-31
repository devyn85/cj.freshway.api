package cjfw.wms.st.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : ParkYoSep 
 * @date : 2025.11.12  
 * @description : 위탁정산내역현황 Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.12 parkYosep (dytpq362@cj.net) 생성
 *         </pre>
 */
@Schema(description = "위탁입출고현황 Request DTO")
@Data
public class StTplCalcResultReqDto extends CommonProcedureDto {

    /** 저장 리스트 */
    List<StTplCalcResultResDto> saveList;
    
    /** 물류센터 */
    @Schema(description = "물류센터", nullable = false, example = "")
    private String dcCode;
    
    /** 연월 */
    @Schema(description = "재고연월", nullable = false, example = "")
    private String month;
    
    /** 구역 */
    @Schema(description = "구역", nullable = false, example = "")
    private String area;
    
        
    /** 창고 */
    @Schema(description = "창고", nullable = false, example = "")
    private String organize;    
    
    /** 창고-다중검색 */
    @MultiSearch
    @Schema(description = "창고-다중검색", nullable = false, example = "")
    private List<String> organizeMulti;
     
    
    /** 화주 */
    @Schema(description = "화주", nullable = false, example = "")
    private String tplUser;
}
