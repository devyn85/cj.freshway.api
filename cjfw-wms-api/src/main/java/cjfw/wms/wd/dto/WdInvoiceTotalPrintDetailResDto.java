package cjfw.wms.wd.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : SangSuSung(kduimux@cj.com) 
 * @date : 2025.07.04 
 * @description : 레포트 출력 기능을 구현한 DTO Class - 레포트 데이터소스가 대문자임 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.04 SangSuSung(kduimux@cj.com) 생성 </pre>
 */
@Data
public class WdInvoiceTotalPrintDetailResDto {
	
	/* 납품서 RD 상세 */
	/** 상품코드 */
    private String sku;
    /** 상품명칭 */
    private String skuname;
    /** 단위 */
    private String uom;
    /** 확정수량 */
    private String confirmqty;
    /** 단가 */
    private String unitprice;
    /** 공급가 */
    private String supplyprice;
    /** 세액 */
    private String taxamount;
    /** 총액 */
    private String totamount;
    /** 납품서생성키 */
    private String headerkey;
    /** 문서번호 */
    private String docno;
    /** 저장타입 */
    private String storageType;
    
    /* 납품서 RD 상세(여신) */
    /** 거래처코드 */
    private String toCustkey;
    /** 여신한도 */
    private String creditlimit;
    /** 전일잔고 */
    private String beforedaybalance;
    /** 당월구매금액 */
    private String curmonthcheck;
    /** 주문가능금액 */
    private String orderbalance;
    /** 당월입금금액 */
    private String curmonthdeposit;
    /** 전월잔고 */
    private String beforemonbalance;
    /** 당월누계매출(전일) */
    private String curmonordamt;
    /** 납품서유형 */
    private String invoicetype;
    /** 문서유형 */
    private String doctype;
    
    // 납품서 RD 상세(여신)
    /** 구매단가 */
    private String purchaseprice;
    /** 부가세 */
    private String vat;
    /** 배송비 */
    private String deliveryprice;

	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
