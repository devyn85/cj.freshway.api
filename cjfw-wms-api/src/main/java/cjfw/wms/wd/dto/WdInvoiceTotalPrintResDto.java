package cjfw.wms.wd.dto;

import java.util.List;

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
public class WdInvoiceTotalPrintResDto {
	/** 레포트 Header */
	List<WdInvoiceTotalPrintResDto> reportHeader;
	
	/** 레포트 상세 리스트 - 상세는 PrintDetailResDto 로 통합	 */
	List<WdInvoicePrintDetailResDto> reportDetailList;
	/** 레포트 여신 - 상세는 PrintDetailResDto 로 통합	 */
	List<WdInvoiceTotalPrintDetailResDto> reportCredit;	
	/** 레포트 배송비용 - 상세는 PrintDetailResDto 로 통합	 */
	List<WdInvoiceTotalPrintDetailResDto> reportDlvCost;	
	/** 일메모파일 - 상세는 PrintDetailResDto 로 통합	 */
	List<WdInvoiceTotalPrintDetailResDto> reportFileList;
	/** CRM정보 - 상세는 PrintDetailResDto 로 통합	 */
	List<WdInvoiceTotalPrintDetailResDto> reportCrmCustdlv;		

    /** 납품일자 */
    private String deliverydt;
    /** 공급자사업자등록번호 */
    private String fromVatno;
    /** 공급자상호 */
    private String fromVatname;
    /** 공급자성명 */
    private String fromVatowner;
    /** 공급자업태 */
    private String fromVattype;
    /** 공급자종목 */
    private String fromVatcategory;
    /** 공급자주소 */
    private String fromVataddress;
    /** 공급자주소1 */
    private String fromVataddress1;
    /** 공급자주소2 */
    private String fromVataddress2;
    /** 거래처코드 */
    private String toCustkey;
    /** 공급받는자사업자등록번호 */
    private String toVatno;
    /** 공급받는자상호 */
    private String toVatname;
    /** 공급받는자성명 */
    private String toVatowner;
    /** 공급받는자주소 */
    private String toVataddress;
    /** 공급받는자주소1 */
    private String toVataddress1;
    /** 공급받는자주소2 */
    private String toVataddress2;
    /** 공급받는자업태 */
    private String toVattype;
    /** 공급받는자종목 */
    private String toVatcategory;
    /** 고객지원센터담당자 */
    private String csPerson;
    /** 고객지원센터담당자전화 */
    private String csTel;
    /** 담당영업사원명 */
    private String empname;
    /** 담당영업사원전화 */
    private String empphone;
    /** 출고센터코드 */
    private String fromCustkey;
    /** 출고센터명 */
    private String fromCustname;
    /** 출고센터주소 */
    private String fromAddress;
    /** 차량번호 */
    private String carno;
    /** POP번호 */
    private String deliverygroup;
    /** 배송기사명 */
    private String drivername;
    /** 배송기사연락처 */
    private String driverPhone;
    /** 납품처명 */
    private String toCustname;
    /** 납품처연락처 */
    private String toPhone;
    /** 납품처주소 */
    private String toAddress;
    /** 납품처주소1 */
    private String toAddress1;
    /** 납품처주소2 */
    private String toAddress2;
    /** 납품서유형 */
    private String invoicetype;
    /** 납품서유형 */
    private String doctype;
    /** 납품서생성키 */
    private String headerkey;
    /** 메모사항1 */
    private String memo1;
    /** 납품서하단유형 */
    private String invoicetypeBt;
    /** 납품서하단차량번호 */
    private String btCarno;
    /** fromPhone1 */
    private String fromPhone1;
    /** fromFax */
    private String fromFax;
    /** toPhone1 */
    private String toPhone1;
    /** toFax */
    private String toFax;
    /** totalAmt */
    private String totalAmt;
    /** todayAmount */
    private String todayAmount;
    /** todayAmt */
    private String todayAmt;
    /** checkTemper */
    private String checkTemper;
    /** inspectorprintyn */
    private String inspectorprintyn;
    /** newoldtype */
    private String newoldtype;

    @Schema(description = "실착지 코드")
    private String truthcustkey;

    	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
