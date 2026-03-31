package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.07.03 
 * @description : 이력피킹현황 목록 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.03 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "이력피킹현황 목록 결과")
public class WdPickingListSNResDto {
	/**
     * 물류센터
     */
    @Schema(description = "물류센터", example = "DC001")
    private String dccode;
    /**
     * 물류센터명
     */
    @Schema(description = "물류센터명", example = "물류센터명 예시")
    private String dcname;
    /**
     * 플랜트
     */
    @Schema(description = "플랜트", example = "PLANT01")
    private String plant;
    /**
     * 출고일자
     */
    @Schema(description = "출고일자", example = "2025-01-01")
    private String slipdt;
    /**
     * 주문번호
     */
    @Schema(description = "주문번호", example = "ORD001")
    private String docno;
    /**
     * 품목번호
     */
    @Schema(description = "품목번호", example = "ITEM001")
    private String docline;
    /**
     * 관리처코드
     */
    @Schema(description = "관리처코드", example = "CUST001")
    private String toCustkey;
    /**
     * 관리처명
     */
    @Schema(description = "관리처명", example = "관리처명 예시")
    private String toCustname;
    /**
     * 상품코드
     */
    @Schema(description = "상품코드", example = "SKU001")
    private String sku;
    /**
     * 상품명칭
     */
    @Schema(description = "상품명칭", example = "상품명칭 예시")
    private String skuname;
    /**
     * 주문수량
     */
    @Schema(description = "주문수량", example = "100")
    private BigDecimal orderqty;
    /**
     * 피킹작업량
     */
    @Schema(description = "피킹작업량", example = "90")
    private BigDecimal pickedqty;
    /**
     * 확정수량
     */
    @Schema(description = "확정수량", example = "80")
    private BigDecimal confirmqty;
    /**
     * 취소량
     */
    @Schema(description = "취소량", example = "10")
    private BigDecimal cancelqty;
    /**
     * 진행상태
     */
    @Schema(description = "진행상태", example = "1")
    private String status;
    /**
     * 진행상태
     */
    @Schema(description = "진행상태", example = "1")
    private String statuscode;
    /**
     * 배송라벨
     */
    @Schema(description = "배송라벨", example = "INV001")
    private String invoiceno;
    /**
     * 운송장수량
     */
    @Schema(description = "운송장수량", example = "70")
    private BigDecimal invoiceqty;
    /**
     * 단위
     */
    @Schema(description = "단위", example = "EA")
    private String uom;
    /**
     * 작업센터코드
     */
    @Schema(description = "작업센터코드", example = "FROMDC01")
    private String fromDccode;
    /**
     * 작업센터명
     */
    @Schema(description = "작업센터명", example = "작업센터명 예시")
    private String fromDcname;
    /**
     * 이력번호
     */
    @Schema(description = "이력번호", example = "SN001")
    private String serialno;
    /**
     * 개체식별/유통이력
     */
    @Schema(description = "개체식별/유통이력", example = "STOCKID01")
    private String stockid;
    /**
     * 피킹작업량
     */
    @Schema(description = "피킹작업량", example = "60")
    private BigDecimal invoicepickedqty;
    /**
     * STO확정수량
     */
    @Schema(description = "STO확정수량", example = "50")
    private BigDecimal invoicestoconfirmqty;
    /**
     * 확정수량
     */
    @Schema(description = "확정수량", example = "40")
    private BigDecimal invoiceconfirmqty;
    /**
     * 주문유형
     */
    @Schema(description = "주문유형", example = "40")
    private String ordertype;
    /**
     * 피킹 입력자
     */
    @Schema(description = "피킹 입력자", example = "40")
    private String addwho;
    /**
     * 입력시간
     */
    @Schema(description = "입력시간", example = "40")
    private String adddate;
    /**
     * */
    @Schema(description = "", example = "1")
    private BigDecimal storerorderqty;
    /**
     * */
    @Schema(description = "", example = "1")
    private BigDecimal storercancelqty;
    /**
     * */
    @Schema(description = "", example = "1")
    private BigDecimal storerinvoiceqty;
    /**
     * */
    @Schema(description = "", example = "UOM01")
    private String storeruom;
}
