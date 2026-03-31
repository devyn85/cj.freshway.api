package cjfw.wms.wd.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author 		: YeoSeungCheol (pw6375@cj.net) 
 * @date 		: 2025.11.12
 * @description : 광역출고현황 목록 조회 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.12 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "광역출고현황 목록 조회 요청 DTO")
public class WdInplanSTOReqDto extends CommonDto {

    @Schema(description = "물류센터", example = "2600")
    private String dccode;
    

    @Schema(description = "공급관리처", example = "1000")
    private String fromDccode;

    @Schema(description = "공급받는관리처", example = "2170")
    private String toDccode;

    @Schema(description = "광역출고 시작일(YYYYMMDD)", example = "20250101")
    private String fromSlipdt;

    @Schema(description = "광역출고 종료일(YYYYMMDD)", example = "20250107")
    private String toSlipdt;

    @Schema(description = "문서일자 시작(YYYYMMDD)", example = "20250101")
    private String fromDocdt;

    @Schema(description = "문서일자 종료(YYYYMMDD)", example = "20250107")
    private String toDocdt; 
    
    @Schema(description = "저장조건")
    private String storagetype;

    @Schema(description = "배송그룹(택배사)", example = "CJ")
    private String gMultiCourier;
    
    @Schema(description = "문서번호")
    private String docno;
    
    /** 주문번호(다중검색) */
	@MultiSearch
    @Schema(description = "주문번호-다중OR검색")
    private List<String> docnoMulti; 
 
    @Schema(description = "이력번호")
    private String serialno;
    
    @Schema(description = "진행상태")
    private String status;
    
    @Schema(description = "삭제여부")
    private String delYn;
    
    //
    
    private String fromDeliverydt;
    private String toDeliverydt;
    
    // 탭 요청
    private String doctype;
    private String docdt;
    private String blno;
    private String contractcompany;
    

    @Schema(description = "상품코드")
    private String sku;
    
    /** 상품코드(다중검색) */
	@MultiSearch
    @Schema(description = "상품코드-다중OR검색")
	private List<List<String>> skuMulti;
	
    /** 창고코드 */
    @Schema(description = "창고코드")
    private String organize;
	
    /** 창고코드 - from센터 */
    @MultiSearch 
    @Schema(description = "창고코드 - from센터")
    private List<String> organizeMulti;
    
    /** 창고코드  - to센터 */
    @Schema(description = "창고코드 - to센터")
    private String organize2;
    
    /** 창고코드 - to센터 */
    @MultiSearch 
    @Schema(description = "창고코드 - to센터")
    private List<String> organize2Multi;
}
