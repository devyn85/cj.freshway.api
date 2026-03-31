package cjfw.wms.st.dto;

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
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.09.16
 * @description : 자동창고보충 목록 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.16 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "자동창고보충 목록 요청") 
public class StLocMoveAsrsReqDto extends CommonProcedureDto {
	
	/** 저장 리스트 */
	List<StLocMoveAsrsTab1ResDto> saveBatchList;
	
	/**
	 * loccategory
	 */
	@Schema(description = "loccategory", example = "C100")
	private String loccategory;
	/**
	 * searchserial
	 */
	@Schema(description = "searchserial", example = "C100")
	private String searchserial;
	/**
     * 물류센터
     */
    @Schema(description = "물류센터", example = "C100")
    private String dccode;
    /**
     * 물류센터
     */
    @Schema(description = "물류센터", example = "C100")
    private String fixdccode;
    /**
     * 창고
     */
    @Schema(description = "창고", example = "WH01")
    private String organize;
    /**
     * 상품코드
     */
    @Schema(description = "상품코드", example = "ITEM12345")
    private String sku;
    
    /** 상품(다중OR검색) */
	@MultiSearch
    @Schema(description = "상품-다중OR검색")
    private List<List<String>> skuMulti;    
    
    /**
     * 저장조건
     */
    @Schema(description = "저장조건", example = "A")
    private String storagetype;
    /**
     * 유통기한여부
     */
    @Schema(description = "유통기한여부", example = "Y")
    private String lottable01yn;
    /**
     * 재고위치
     */
    @Schema(description = "재고위치", example = "PICK")
    private String stocktype;
    /**
     * 재고속성
     */
    @Schema(description = "재고속성", example = "GOOD")
    private String stockgrade;
    /**
     * ZONE
     */
    @Schema(description = "ZONE", example = "A")
    private String zone;
    /**
     * B/L 번호
     */
    @Schema(description = "B/L 번호", example = "BL20250806001")
    private String blno;
    /**
     * 이력번호
     */
    @Schema(description = "이력번호", example = "SN20250806001")
    private String serialno;
    /**
     * 계약업체
     */
    @Schema(description = "계약업체", example = "CUST001")
    private String contractcompany;
    /**
     * FROM로케이션
     */
    @Schema(description = "FROM로케이션", example = "F-A-01-01")
    private String fromloc;
    /**
     * TO로케이션
     */
    @Schema(description = "TO로케이션", example = "T-B-02-02")
    private String toloc;
	
	
	
}
