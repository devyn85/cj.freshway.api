package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.07.10 
 * @description : 출고재고분배-지정분배 목록 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.10 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "출고재고분배-지정분배 목록 결과")
public class WdAllocationBatchGroupResTab2Dto extends CommonDto{
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
	/**
     * 전표번호
     */
    @Schema(description = "전표번호", example = "DOC001")
    private String docno;
    /**
     * 품목번호
     */
    @Schema(description = "품목번호", example = "1")
    private String docline;
    /**
     * 진행상태
     */
    @Schema(description = "진행상태", example = "완료")
    private String status;
    /**
     * 상품분류
     */
    @Schema(description = "상품분류", example = "의류")
    private String skugroup;
    /**
     * 상품코드
     */
    @Schema(description = "상품코드", example = "ITEM001")
    private String sku;
    /**
     * 상품명칭
     */
    @Schema(description = "상품명칭", example = "남성 티셔츠")
    private String skuname;
    /**
     * 단위
     */
    @Schema(description = "단위", example = "EA")
    private String uom;
    /**
     * 저장유무
     */
    @Schema(description = "저장유무", example = "Y")
    private String channel;
    /**
     * 저장조건
     */
    @Schema(description = "저장조건", example = "냉장")
    private String storagetype;
    /**
     * 식별번호유무
     */
    @Schema(description = "식별번호유무", example = "Y")
    private String serialyn;
    /**
     * 원주문수량
     */
    @Schema(description = "원주문수량", example = "10")
    private BigDecimal orderqtySt;
    /**
     * 주문수량
     */
    @Schema(description = "주문수량", example = "10")
    private BigDecimal orderqty;
    /**
     * 분배량
     */
    @Schema(description = "분배량", example = "5")
    private BigDecimal processqty;
    /**
     * 개체식별/소비이력
     */
    @Schema(description = "개체식별/소비이력", example = "STOCK001")
    private String toStockid;
    /**
     * LOC
     */
    @Schema(description = "LOC", example = "A-01-01")
    private String toLoc;
    @Schema(description = "DC코드", example = "DC001")
    private String dccode;
    @Schema(description = "화주키", example = "STORER001")
    private String storerkey;
    @Schema(description = "조직", example = "ORG001")
    private String organize;
    @Schema(description = "지역", example = "AREA001")
    private String area;
    @Schema(description = "고객키", example = "CUST001")
    private String custkey;
    @Schema(description = "문서일자", example = "2023-01-01")
    private String docdt;
    @Schema(description = "문서유형", example = "TYPE01")
    private String doctype;
    @Schema(description = "전표일자", example = "2023-01-01")
    private String slipdt;
    @Schema(description = "전표번호", example = "SLIP001")
    private String slipno;
    @Schema(description = "전표품목", example = "1")
    private String slipline;
    @Schema(description = "라인01", example = "LINEVAL01")
    private String line01;
}
