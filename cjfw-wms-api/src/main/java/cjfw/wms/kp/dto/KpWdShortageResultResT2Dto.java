package cjfw.wms.kp.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.12.02
 * @description : 출고결품실적 Master Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.02 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "출고결품실적 Master Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KpWdShortageResultResT2Dto {
	/** 출고일자 */
    @Schema(description = "출고일자", example = "20260323")
    private String slipdt;

    /** 물류센터 */
    @Schema(description = "물류센터", example = "서울센터")
    private String dccode;

    /** 주문번호 */
    @Schema(description = "주문번호", example = "ORD202603230001")
    private String docno;

    /** 영업조직 */
    @Schema(description = "영업조직", example = "영업1팀")
    private String saleorganize;

    /** 고객코드 */
    @Schema(description = "고객코드", example = "CUST001")
    private String custkey;

    /** 고객명 */
    @Schema(description = "고객명", example = "현대모비스")
    private String custname;

    /** 배송인도처코드 */
    @Schema(description = "배송인도처코드", example = "BILL001")
    private String billtocustkey;

    /** 배송인도처명 */
    @Schema(description = "배송인도처명", example = "(주)인도처")
    private String billtocustname;

    /** POP번호 */
    @Schema(description = "POP번호", example = "P12345")
    private String delierygroup;

    /** 차량번호 */
    @Schema(description = "차량번호", example = "12가3456")
    private String carno;

    /** 저장유무 */
    @Schema(description = "저장유무", example = "Y")
    private String channel;

    /** 저장조건 */
    @Schema(description = "저장조건", example = "냉장")
    private String storagetype;

    /** 상품코드 */
    @Schema(description = "상품코드", example = "SKU001")
    private String sku;

    /** 상품명칭 */
    @Schema(description = "상품명칭", example = "오렌지주스")
    private String skuname;

    /** 판매단위 */
    @Schema(description = "판매단위", example = "BOX")
    private String storeruom;

    /** 주문수량 */
    @Schema(description = "주문수량", example = "10.0")
    private BigDecimal storeropenqty;

    /** 결품수량 */
    @Schema(description = "결품수량", example = "2.0")
    private BigDecimal storercancelqty;

    /** 결품사유 */
    @Schema(description = "결품사유", example = "재고부족")
    private String reasoncode;

    /** 협력사코드 */
    @Schema(description = "협력사코드", example = "VEND001")
    private String vendor;

    /** 협력사명 */
    @Schema(description = "협력사명", example = "우리공급")
    private String vendorname;

    /** 담당MA */
    @Schema(description = "담당MA", example = "홍길동")
    private String maname;

    /** MD코드 */
    @Schema(description = "MD코드", example = "MD01")
    private String mdname;

    /** 수급담당 */
    @Schema(description = "수급담당", example = "이철수")
    private String pomdcode;

    /** storerkey */
    @Schema(description = "storerkey", example = "S001")
    private String storerkey;

    /** organize */
    @Schema(description = "organize", example = "ORG01")
    private String organize;

    /** docdt */
    @Schema(description = "docdt", example = "20260323")
    private String docdt;

    /** storerconfirmqty */
    @Schema(description = "storerconfirmqty", example = "8.0")
    private BigDecimal storerconfirmqty;
}
