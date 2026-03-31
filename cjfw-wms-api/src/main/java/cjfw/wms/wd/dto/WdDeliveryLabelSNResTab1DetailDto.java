package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.10.15 
 * @description : 이력배송라벨출력-분류표생성 상세 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.15 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "이력배송라벨출력-분류표생성 상세 결과")
public class WdDeliveryLabelSNResTab1DetailDto extends CommonDto{
	/**
     * 품목번호
     */
    @Schema(description = "품목번호", example = "1")
    private String docline;
    /**
     * 상품코드
     */
    @Schema(description = "상품코드", example = "SKU0001")
    private String sku;
    /**
     * 상품명칭
     */
    @Schema(description = "상품명칭", example = "샘플상품A")
    private String skuname;
    /**
     * 플랜트
     */
    @Schema(description = "플랜트", example = "[1000]CJ제일제당")
    private String plantDescr;
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
     * 주문수량
     */
    @Schema(description = "주문수량", example = "100")
    private BigDecimal orderqty;
    /**
     * 판매단위
     */
    @Schema(description = "판매단위", example = "EA")
    private String uom;
    /**
     * 진행상태
     */
    @Schema(description = "진행상태", example = "확정")
    private String status;
    /**
     * Dccode
     */
    @Schema(description = "Dccode", example = "01")
    private String dccode;
    /**
     * Storerkey
     */
    @Schema(description = "Storerkey", example = "KEY001")
    private String storerkey;
    /**
     * Organize
     */
    @Schema(description = "Organize", example = "C10")
    private String organize;
    /**
     * Area
     */
    @Schema(description = "Area", example = "A10")
    private String area;
    /**
     * Docdt
     */
    @Schema(description = "Docdt", example = "2025-10-15 10:00:00")
    private String docdt;
    /**
     * Docno
     */
    @Schema(description = "Docno", example = "ORD12345")
    private String docno;
    /**
     * Doctype
     */
    @Schema(description = "Doctype", example = "WDO")
    private String doctype;
    /**
     * 출고일자
     */
    @Schema(description = "출고일자", example = "2025-10-15")
    private String slipdt;
    /**
     * Slipno
     */
    @Schema(description = "Slipno", example = "SLP0001")
    private String slipno;
    /**
     * Sliptype
     */
    @Schema(description = "Sliptype", example = "WDP")
    private String sliptype;
    /**
     * Slipline
     */
    @Schema(description = "Slipline", example = "001")
    private String slipline;
    /**
     * Plant
     */
    @Schema(description = "Plant", example = "1000")
    private String plant;
    /**
     * Contractcompanyname
     */
    @Schema(description = "Contractcompanyname", example = "CJ물류")
    private String contractcompanyname;
}
