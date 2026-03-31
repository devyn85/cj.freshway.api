package cjfw.wms.st.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.10.31 
 * @description : 위탁물류입고요청 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.31 ParkJinWoo 생성
 */
@Schema(description = "위탁물류입고요청 Request DTO")
@Data
public class StTplReceiptReqReqDto extends CommonProcedureDto {
    
    /** 저장 리스트 */
    List<StTplReceiptReqResDto> saveList;

    /** 물류센터 */
    @Schema(description = "물류센터")
    private String dcCode;
    
    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;    
    
    /** 상품코드-다중검색 */
    @MultiSearch
    @Schema(description = "상품코드", nullable = false, example = "")
    private  List<List<String>> skuMulti;
    

    /** 창고 */
    @Schema(description = "창고")
    private String organize;    

    /** 요청일자from */
    @Schema(description = "fromDate")
    private String fromDate;

    /** 요청일자to */
    @Schema(description = "toDate")
    private String toDate;

    /** 요청일자to */
    @Schema(description = "화주")
    private String custkey;

    /** bl번호to */
    @Schema(description = "bl번호")
    private String blNo;

    /** 이력번호 */
    @Schema(description = "이력번호")
    private String serialNo;
    
    /** 이력번호 */
    @Schema(description = "이력번호")
    private String tplUser;

    private String docNo;
    private String vendor;
    
    private String docType;
    
    private String fromCustKey;

    private String stockId;
//    private String serialkeys;
    private java.util.List<String> serialkeys;
    
}
