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
public class WdInvoicePrintDetailResDto {
	
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

    /** CUSTNM */
    @Schema(description = "CUSTNM")
    private String custnm;

    /** SOURCE_SYSTEM */
    @Schema(description = "SOURCE_SYSTEM")
    private String sourceSystem;

    /** MEMOLEVEL */
    @Schema(description = "MEMOLEVEL")
    private String memolevel;

    /** DESCRIPTION */
    @Schema(description = "DESCRIPTION")
    private String description;

    /** ISSUENO */
    @Schema(description = "ISSUENO")
    private String issueno;

    /** UPLOAD_RES_DOC_ID */
    @Schema(description = "UPLOAD_RES_DOC_ID")
    private String uploadResDocId;

    /** FILE1 */
    @Schema(description = "FILE1")
    private String file1;

    /** FILE2 */
    @Schema(description = "FILE2")
    private String file2;

    /** FILE3 */
    @Schema(description = "FILE3")
    private String file3;

    /** DELIVERYDT */
    @Schema(description = "DELIVERYDT")
    private String deliverydt;

    /** IFKEY */
    @Schema(description = "IFKEY")
    private String ifkey;

    /** CUSTKEY */
    @Schema(description = "CUSTKEY")
    private String custkey;

    /** CUSTNAME */
    @Schema(description = "CUSTNAME")
    private String custname;

    /** CUSTTYPE */
    @Schema(description = "CUSTTYPE")
    private String custtype;

    /** ADDRESSMATCHYN */
    @Schema(description = "ADDRESSMATCHYN")
    private String addressmatchyn;

    /** ARRIVALADDRESS */
    @Schema(description = "ARRIVALADDRESS")
    private String arrivaladdress;

    /** ARRIVALDETAILADDRESS */
    @Schema(description = "ARRIVALDETAILADDRESS")
    private String arrivaldetailaddress;

    /** ARRIVALPOSTALCODE */
    @Schema(description = "ARRIVALPOSTALCODE")
    private String arrivalpostalcode;

    /** FTFINSPECTIONYN */
    @Schema(description = "FTFINSPECTIONYN")
    private String ftfinspectionyn;

    /** INSPECTIONWORKERPHONE */
    @Schema(description = "INSPECTIONWORKERPHONE")
    private String inspectionworkerphone;

    /** INSPECTORPRINTYN */
    @Schema(description = "INSPECTORPRINTYN")
    private String inspectorprintyn;

    /** PARKINGHEIGHT */
    @Schema(description = "PARKINGHEIGHT")
    private String parkingheight;

    /** KEYTYPE */
    @Schema(description = "KEYTYPE")
    private String keytype;

    /** KEYDETAIL */
    @Schema(description = "KEYDETAIL")
    private String keydetail;

    /** DELIVERYREQUESTTIMESTART */
    @Schema(description = "DELIVERYREQUESTTIMESTART")
    private String deliveryrequesttimestart;

    /** DELIVERYREQUESTTIMEEND */
    @Schema(description = "DELIVERYREQUESTTIMEEND")
    private String deliveryrequesttimeend;

    /** DELIVERYAVAILABLETIME */
    @Schema(description = "DELIVERYAVAILABLETIME")
    private String deliveryavailabletime;

    /** BUILDINGOPENTIME */
    @Schema(description = "BUILDINGOPENTIME")
    private String buildingopentime;

    /** MOVEMENTENTRY */
    @Schema(description = "MOVEMENTENTRY")
    private String movemententry;

    /** GOODSLOCATIONFROZEN */
    @Schema(description = "GOODSLOCATIONFROZEN")
    private String goodslocationfrozen;

    /** GOODSLOCATIONREFRIG */
    @Schema(description = "GOODSLOCATIONREFRIG")
    private String goodslocationrefrig;

    /** GOODSLOCATIONROOM */
    @Schema(description = "GOODSLOCATIONROOM")
    private String goodslocationroom;

    /** RETURNLOCATION */
    @Schema(description = "RETURNLOCATION")
    private String returnlocation;

    /** INITREQUESTDT */
    @Schema(description = "INITREQUESTDT")
    private String initrequestdt;

    /** INITREQUESTTIMESTART */
    @Schema(description = "INITREQUESTTIMESTART")
    private String initrequesttimestart;

    /** INITREQUESTTIMEEND */
    @Schema(description = "INITREQUESTTIMEEND")
    private String initrequesttimeend;

    /** INITFTFINSPECTIONYN */
    @Schema(description = "INITFTFINSPECTIONYN")
    private String initftfinspectionyn;

    /** INITDELIVERYCONTACT */
    @Schema(description = "INITDELIVERYCONTACT")
    private String initdeliverycontact;

    /** INITDELIVERYDESC */
    @Schema(description = "INITDELIVERYDESC")
    private String initdeliverydesc;

    /** DELIVERYTYPE */
    @Schema(description = "DELIVERYTYPE")
    private String deliverytype;

    /** TEMPTARGET */
    @Schema(description = "TEMPTARGET")
    private String temptarget;

    /** LABELPRINTTYPE */
    @Schema(description = "LABELPRINTTYPE")
    private String labelprinttype;

    /** DELIVERYNOTIYN */
    @Schema(description = "DELIVERYNOTIYN")
    private String deliverynotiyn;

    /** DELIVERYNOTIPHONE */
    @Schema(description = "DELIVERYNOTIPHONE")
    private String deliverynotiphone;

    /** DELIVERYNOTITIMESTART */
    @Schema(description = "DELIVERYNOTITIMESTART")
    private String deliverynotitimestart;

    /** DELIVERYNOTITIMEEND */
    @Schema(description = "DELIVERYNOTITIMEEND")
    private String deliverynotitimeend;

    /** EMPLOEENUMBER */
    @Schema(description = "EMPLOEENUMBER")
    private String emploeenumber;

    /** CREATEDBYEMAIL */
    @Schema(description = "CREATEDBYEMAIL")
    private String createdbyemail;

    /** CREATEDBYNAME */
    @Schema(description = "CREATEDBYNAME")
    private String createdbyname;

    /** CREATEDDATE */
    @Schema(description = "CREATEDDATE")
    private String createddate;

    /** STORETYPE */
    @Schema(description = "STORETYPE")
    private String storetype;

    /** EDMSFILEID */
    @Schema(description = "EDMSFILEID")
    private String edmsfileid;

    /** STATUS */
    @Schema(description = "STATUS")
    private String status;

    /** ADDDATE */
    @Schema(description = "ADDDATE")
    private String adddate;

    /** EDITDATE */
    @Schema(description = "EDITDATE")
    private String editdate;

    /** ADDWHO */
    @Schema(description = "ADDWHO")
    private String addwho;

    /** EDITWHO */
    @Schema(description = "EDITWHO")
    private String editwho;

    /** BUILDINGOPENTIMENM */
    @Schema(description = "BUILDINGOPENTIMENM")
    private String buildingopentimenm;

    /** DELIVERYAVAILABLETIMENM */
    @Schema(description = "DELIVERYAVAILABLETIMENM")
    private String deliveryavailabletimenm;

    /** DELIVERYTYPENM */
    @Schema(description = "DELIVERYTYPENM")
    private String deliverytypenm;

    /** KEYTYPENM */
    @Schema(description = "KEYTYPENM")
    private String keytypenm;

    /** LABELPRINTTYPENM */
    @Schema(description = "LABELPRINTTYPENM")
    private String labelprinttypenm;

    /** PARKINGHEIGHTNM */
    @Schema(description = "PARKINGHEIGHTNM")
    private String parkingheightnm;

    /** FILE12 */
    @Schema(description = "FILE12")
    private String file12;

    /** FILE13 */
    @Schema(description = "FILE13")
    private String file13;

    /** FILE14 */
    @Schema(description = "FILE14")
    private String file14;

    /** FILE15 */
    @Schema(description = "FILE15")
    private String file15;

    /** FILE16 */
    @Schema(description = "FILE16")
    private String file16;

    /** FILE12_1 */
    @Schema(description = "FILE12_1")
    private String file12_1;

    /** FILE12_2 */
    @Schema(description = "FILE12_2")
    private String file12_2;

    /** FILE13_1 */
    @Schema(description = "FILE13_1")
    private String file13_1;

    /** FILE13_2 */
    @Schema(description = "FILE13_2")
    private String file13_2;

    /** FILE14_1 */
    @Schema(description = "FILE14_1")
    private String file14_1;

    /** FILE14_2 */
    @Schema(description = "FILE14_2")
    private String file14_2;

    /** FILE15_1 */
    @Schema(description = "FILE15_1")
    private String file15_1;

    /** FILE15_2 */
    @Schema(description = "FILE15_2")
    private String file15_2;

    /** FILE16_1 */
    @Schema(description = "FILE16_1")
    private String file16_1;

    /** FILE16_2 */
    @Schema(description = "FILE16_2")
    private String file16_2;

    /** qtyperbox */
    @Schema(description = "qtyperbox")
    private String qtyperbox;

    	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
