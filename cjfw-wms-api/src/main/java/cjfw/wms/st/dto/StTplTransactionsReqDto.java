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
 * @date : 2025.11.04  
 * @description : 위탁입출고현황 Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.04 parkYosep (dytpq362@cj.net) 생성
 *         </pre>
 */
@Schema(description = "위탁입출고현황 Request DTO")
@Data
public class StTplTransactionsReqDto extends CommonProcedureDto {
    /** 저장 리스트 */
    List<StTplTransactionsResDto> saveList;	
    
    /** 물류센터코드 */
    @Schema(description = "물류센터코드")
    private String dcCode;
    
    /** 창고 */
    @Schema(description = "창고")
    private String organize;
    
    /** 시작일자 */
    @Schema(description = "시작일자")
    private String fromDate;

    /** 종료일자 */
    @Schema(description = "종료일자")
    private String toDate;
    
    /** 화주 */
    @Schema(description = "화주")
    private String tplUser;

    /** 입출고유형 */
    @Schema(description = "입출고유형")
    private String iotype;
    
    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;
    
    /** 상품(다중OR검색) */
	@MultiSearch
    @Schema(description = "상품-다중OR검색")
    private List<List<String>> skuMulti;    

    /** 시리얼번호 */
    @Schema(description = "시리얼번호")
    private String serialno;
    
    
    
    
}
