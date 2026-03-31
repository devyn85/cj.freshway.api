package cjfw.wms.ms.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : SangSuSung(kduimux@cj.com) 
 * @date : 2025.07.03 
 * @description : 여신정보 정보 기능을 I/F 구현한 DTO Class 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.03 SangSuSung(kduimux@cj.com) 생성 </pre>
 */
@Data
public class MsCreditInfoDto {
	
	/** 여신정보 리스트 */
	List<MsCreditInfoDto> creditList;	
	/** 연도 */
	private String year;
	/** 납품월 */
	private String month;
	/** 거래처키 */
	private String custkey;		
	/** 연도 - GJAHR */
	private String gjahr;
	/** 납품월 - ZMONTH*/
	private String zmonth;
	/** 납품일 */
	private String day;
	/** 납품일자 */
	private String deliverydt;
	/** 거래처키 - KUNNR */
	private String kunnr;	
    /** 여신한도 - KLIMK */
    private BigDecimal klimk;
    /** 주문가능금액 - SALES */
    private BigDecimal sales;
    /** 당월구매금액 - ZOUTPUT */
    private BigDecimal zoutput;
    /** 당월입금금액 - ZINPUT */
    private BigDecimal zinput;
    /** 초과결제일 - ZDELAY_TXT */
    private String zdelayTxt;
    /** 전일잔고 - ZJANGO */
    private BigDecimal zjango;
    /** 전월잔고 - ZJANGO_M */
    private BigDecimal zjangoM;
    /** 당월누계매출(전일) - MOUTPUT */
    private BigDecimal moutput;
	
}
