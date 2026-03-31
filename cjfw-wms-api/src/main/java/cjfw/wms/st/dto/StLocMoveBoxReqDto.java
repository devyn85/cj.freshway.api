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
 * @date : 2025.07.31
 * @description : 재고일괄이동(수원3층) Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.31 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Data
@Schema(description = "재고일괄이동(수원3층) Request DTO")
public class StLocMoveBoxReqDto extends CommonProcedureDto {
	/** 메인그리드 저장 리스트 */
	List<StLocMoveBoxResDto> saveList;	
	
    /** 고정센터코드 */
    @Schema(description = "고정센터코드")
    private String fixdccode;

    /** 보관유형 */
    @Schema(description = "보관유형")
    private String storagetype;

    /** 상품 */
    @Schema(description = "상품")
    private String sku;
    
    /** 상품(다중OR검색) */
	@MultiSearch
    @Schema(description = "상품-다중OR검색")
    private List<List<String>> skuMulti;      

    /** LOTTABLE01YN */
    @Schema(description = "LOTTABLE01YN")
    private String lottable01yn;

    /** 로케이션카테고리 */
    @Schema(description = "로케이션카테고리")
    private String loccategory;

    /** 시리얼검색 */
    @Schema(description = "시리얼검색")
    private String searchserial;

    /** 출발로케이션 */
    @Schema(description = "출발로케이션")
    private String fromloc;

    /** 도착로케이션 */
    @Schema(description = "도착로케이션")
    private String toloc;

    /** 구역 */
    @Schema(description = "구역")
    private String zone;

    /** 시리얼번호 */
    @Schema(description = "시리얼번호")
    private String serialno;

    /** BL번호 */
    @Schema(description = "BL번호")
    private String blno;

    /** 계약업체 */
    @Schema(description = "계약업체")
    private String contractcompany;
    
    /** 거래처(다중OR검색) */
	@MultiSearch
    @Schema(description = "거래처-다중OR검색")
    private List<List<String>> contractcompanyMulti;         

    /** 재고유형 */
    @Schema(description = "재고유형")
    private String stocktype;

    /** 재고등급 */
    @Schema(description = "재고등급")
    private String stockgrade;

    /** 조직 */
    @Schema(description = "조직")
    private String organize;
    
    
}
