package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.11.29 
 * @description : 광역일배검수현황 목록 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.29 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "광역일배검수현황 목록 결과")
public class WdInplanSTODailyResDto extends CommonDto{
	/**
     * 출고일자
     */
    @Schema(description = "출고일자", example = "2025-11-29")
    private String slipdt;
    /**
     * 주문번호
     */
    @Schema(description = "주문번호", example = "D20251129001")
    private String docno;
    /**
     * 공급물류센터
     */
    @Schema(description = "공급물류센터", example = "DC01")
    private String wdDccode;
    /**
     * 공급물류센터명
     */
    @Schema(description = "공급물류센터명", example = "광주센터")
    private String wdDcname;
    /**
     * 공급받는물류센터
     */
    @Schema(description = "공급받는물류센터", example = "DC02")
    private String dpDccode;
    /**
     * 공급받는물류센터명
     */
    @Schema(description = "공급받는물류센터명", example = "서울센터")
    private String dpDcname;
    /**
     * 공급협력사코드
     */
    @Schema(description = "공급협력사코드", example = "CUST001")
    private String ppCustkey;
    /**
     * 공급협력사명
     */
    @Schema(description = "공급협력사명", example = "CJ제일제당")
    private String ppCustname;
    /**
     * 고객관리처코드
     */
    @Schema(description = "고객관리처코드", example = "CUST002")
    private String ccCustkey;
    /**
     * 고객관리처명
     */
    @Schema(description = "고객관리처명", example = "CJ프레시웨이")
    private String ccCustname;
    /**
     * 상품코드
     */
    @Schema(description = "상품코드", example = "SKU001")
    private String sku;
    /**
     * 상품명칭
     */
    @Schema(description = "상품명칭", example = "햇반")
    private String skuname;
    /**
     * 저장조건
     */
    @Schema(description = "저장조건", example = "상온")
    private String storagetype;
    /**
     * 단위
     */
    @Schema(description = "단위", example = "EA")
    private String uom;
    /**
     * 주문수량
     */
    @Schema(description = "주문수량", example = "100")
    private BigDecimal orderqty;
    /**
     * 광역출고검수량
     */
    @Schema(description = "광역출고검수량", example = "90")
    private BigDecimal wdInspectqty;
    /**
     * 광역입고검수량
     */
    @Schema(description = "광역입고검수량", example = "80")
    private BigDecimal dpInspectqty;
    /**
     * 출고
     */
    @Schema(description = "출고", example = "불일치")
    private String statusInspectWd;
    /**
     * 입고
     */
    @Schema(description = "입고", example = "불일치")
    private String statusInspectDp;
}
