package cjfw.wms.cm.entity;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KwonJungYun (jungyun8667@cj.net)
 * @date : 2025.07.09
 * @description : 시스템작업 임시 WD Entity
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.09 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
 */
@Data
@NoArgsConstructor
public class CmSyProcessTempDmDEntity extends CommonProcedureDto {
	/** 처리유형 */
    @Schema(description = "PROCESSTYPE")
    private String processtype;

    /** 처리자 */
    @Schema(description = "PROCESSCREATOR")
    private String processcreator;
                   
    /** SPID */
    @Schema(description = "SPID")
    private String spid;

    /** 처리플래그 */
    @Schema(description = "PROCESSFLAG")
    private String processflag;

    /** 작업시스템 */
    @Schema(description = "TASKSYSTEM")
    private String tasksystem;

    /** 시리얼키 */
    @Schema(description = "SERIALKEY")
    private BigDecimal serialkey;

    /** 문서일자 */
    @Schema(description = "DOCDT")
    private String docdt;

    /** 문서유형 */
    @Schema(description = "DOCTYPE")
    private String doctype;

    /** 문서번호 */
    @Schema(description = "DOCNO")
    private String docno;

    /** 문서라인 */
    @Schema(description = "DOCLINE")
    private String docline;

    /** 물류센터코드 */
    @Schema(description = "DCCODE")
    private String dccode;

    /** 화주키 */
    @Schema(description = "STORERKEY")
    private String storerkey;

    /** 병합문서유형 */
    @Schema(description = "MERGEDOCTYPE")
    private String mergedoctype;

    /** 병합문서일자 */
    @Schema(description = "MERGEDOCDT")
    private String mergedocdt;

    /** 병합문서번호 */
    @Schema(description = "MERGEDOCNO")
    private String mergedocno;

    /** 계획문서유형 */
    @Schema(description = "PLANDOCTYPE")
    private String plandoctype;

    /** 계획문서일자 */
    @Schema(description = "PLANDOCDT")
    private String plandocdt;

    /** 계획문서번호 */
    @Schema(description = "PLANDOCNO")
    private String plandocno;

    /** 조직 */
    @Schema(description = "ORGANIZE")
    private String organize;

    /** 구역(Area) */
    @Schema(description = "AREA")
    private String area;

    /** 몰송장번호 */
    @Schema(description = "MALLINVOICE")
    private String mallinvoice;

    /** 몰송장라인 */
    @Schema(description = "MALLINVOICELINE")
    private String mallinvoiceline;

    /** 택배사 */
    @Schema(description = "COURIER")
    private String courier;

    /** 송장유형 */
    @Schema(description = "INVOICETYPE")
    private String invoicetype;

    /** 송장번호 */
    @Schema(description = "INVOICENO")
    private String invoiceno;

    /** 송장라인 */
    @Schema(description = "INVOICELINE")
    private String invoiceline;

    /** 발주유형 */
    @Schema(description = "POTYPE")
    private String potype;

    /** 발주키 */
    @Schema(description = "POKEY")
    private String pokey;

    /** 발주라인 */
    @Schema(description = "POLINE")
    private String poline;

    /** 출고유형 */
    @Schema(description = "SOTYPE")
    private String sotype;

    /** 출고키 */
    @Schema(description = "SOKEY")
    private String sokey;

    /** 출고라인 */
    @Schema(description = "SOLINE")
    private String soline;

    /** 주문유형 */
    @Schema(description = "ORDERTYPE")
    private String ordertype;

    /** 원천키 */
    @Schema(description = "SOURCEKEY")
    private String sourcekey;

    /** 원천라인 */
    @Schema(description = "SOURCELINE")
    private String sourceline;

    /** 합포장키 */
    @Schema(description = "MIXBOXKEY")
    private String mixboxkey;

    /** 사이트 */
    @Schema(description = "SITE")
    private String site;

    /** 플랜트 */
    @Schema(description = "PLANT")
    private String plant;

    /** 저장위치 */
    @Schema(description = "STORAGELOC")
    private String storageloc;

    /** 재고이동유형 */
    @Schema(description = "STOCKTRANSTYPE")
    private String stocktranstype;

    /** 입출고유형 */
    @Schema(description = "IOTYPE")
    private String iotype;

    /** 상품코드(SKU) */
    @Schema(description = "SKU")
    private String sku;

    /** 대체상품코드 */
    @Schema(description = "ALTERSKU")
    private String altersku;

    /** 소매상품코드 */
    @Schema(description = "RETAILSKU")
    private String retailsku;

    /** 화주주문수량 */
    @Schema(description = "STORERORDERQTY")
    private BigDecimal storerorderqty;

    /** 화주조정수량 */
    @Schema(description = "STORERADJUSTQTY")
    private BigDecimal storeradjustqty;

    /** 화주확정수량 */
    @Schema(description = "STORERCONFIRMQTY")
    private BigDecimal storerconfirmqty;

    /** 화주단위 */
    @Schema(description = "STORERUOM")
    private String storeruom;

    /** 분자 */
    @Schema(description = "BUNJA")
    private BigDecimal bunja;

    /** 분모 */
    @Schema(description = "BUNMO")
    private BigDecimal bunmo;

    /** 단위 */
    @Schema(description = "UOM")
    private String uom;

    /** 주문수량 */
    @Schema(description = "ORDERQTY")
    private BigDecimal orderqty;

    /** 미결수량 */
    @Schema(description = "OPENQTY")
    private BigDecimal openqty;

    /** 처리수량 */
    @Schema(description = "PROCESSQTY")
    private BigDecimal processqty;

    /** 작업수량 */
    @Schema(description = "WORKQTY")
    private BigDecimal workqty;

    /** 검수수량 */
    @Schema(description = "INSPECTQTY")
    private BigDecimal inspectqty;

    /** 확정수량 */
    @Schema(description = "CONFIRMQTY")
    private BigDecimal confirmqty;

    /** 확정중량 */
    @Schema(description = "CONFIRMWEIGHT")
    private BigDecimal confirmweight;

    /** 송장수량 */
    @Schema(description = "INVOICEQTY")
    private BigDecimal invoiceqty;

    /** 품질검사수량 */
    @Schema(description = "QCQTY")
    private BigDecimal qcqty;

    /** 품질검사플래그 */
    @Schema(description = "QCFLAG")
    private String qcflag;

    /** 반품일자 */
    @Schema(description = "RETURNDATE")
    private String returndate;

    /** 반품수량 */
    @Schema(description = "RETURNQTY")
    private BigDecimal returnqty;

    /** 반품유형 */
    @Schema(description = "RETURNTYPE")
    private String returntype;

    /** 반품메시지 */
    @Schema(description = "RETURNMSG")
    private String returnmsg;

    /** 반품자 */
    @Schema(description = "RETURNWHO")
    private String returnwho;

    /** 로트번호 */
    @Schema(description = "LOT")
    private String lot;

    /** 로트속성1 */
    @Schema(description = "LOTTABLE01")
    private String lottable01;

    /** 로트속성2 */
    @Schema(description = "LOTTABLE02")
    private String lottable02;

    /** 로트속성3 */
    @Schema(description = "LOTTABLE03")
    private String lottable03;

    /** 로트속성4 */
    @Schema(description = "LOTTABLE04")
    private String lottable04;

    /** 로트속성5 */
    @Schema(description = "LOTTABLE05")
    private String lottable05;

    /** 재고ID */
    @Schema(description = "STOCKID")
    private String stockid;

    /** 재고등급 */
    @Schema(description = "STOCKGRADE")
    private String stockgrade;

    /** 통화 */
    @Schema(description = "CURRENCY")
    private String currency;

    /** 환율 */
    @Schema(description = "EXCHANGERATE")
    private BigDecimal exchangerate;

    /** 공장가 */
    @Schema(description = "FACTORYPRICE")
    private BigDecimal factoryprice;

    /** 매입가 */
    @Schema(description = "PURCHASEPRICE")
    private BigDecimal purchaseprice;

    /** 판매가 */
    @Schema(description = "SALEPRICE")
    private BigDecimal saleprice;

    /** 부가세 */
    @Schema(description = "VAT")
    private BigDecimal vat;

    /** 할인율 */
    @Schema(description = "DCRATE")
    private BigDecimal dcrate;

    /** 마진율 */
    @Schema(description = "MARGINRATE")
    private BigDecimal marginrate;

    /** 우선순위 */
    @Schema(description = "PRIORITY")
    private String priority;

    /** 채널 */
    @Schema(description = "CHANNEL")
    private String channel;

    /** 확정일시 */
    @Schema(description = "CONFIRMDATE")
    private String confirmdate;

    /** 배송일자 */
    @Schema(description = "DELIVERYDATE")
    private String deliverydate;

    /** 전기일자 */
    @Schema(description = "POSTINGDATE")
    private String postingdate;

    /** 유효일자 */
    @Schema(description = "EFFECTIVEDATE")
    private String effectivedate;

    /** 요청일자 */
    @Schema(description = "REQUESTDATE")
    private String requestdate;

    /** 메모1 */
    @Schema(description = "MEMO1")
    private String memo1;

    /** 메모2 */
    @Schema(description = "MEMO2")
    private String memo2;

    /** 배송메모 */
    @Schema(description = "DELIVERYMEMO")
    private String deliverymemo;

    /** 요청메모 */
    @Schema(description = "REQUESTMEMO")
    private String requestmemo;

    /** 포장방법 */
    @Schema(description = "PACKINGMETHOD")
    private String packingmethod;

    /** 포장번호 */
    @Schema(description = "PACKINGNO")
    private String packingno;

    /** 프로세스코드 */
    @Schema(description = "PROCESSCODE")
    private String processcode;

    /** 전략코드 */
    @Schema(description = "STRAGETYCODE")
    private String stragetycode;

    /** 작업프로세스코드 */
    @Schema(description = "WORKPROCESSCODE")
    private String workprocesscode;

    /** 인터페이스프로세스코드 */
    @Schema(description = "INTERFACEPROCESSCODE")
    private String interfaceprocesscode;

    /** 설비프로세스코드 */
    @Schema(description = "FACILITYPROCESSCODE")
    private String facilityprocesscode;

    /** 사유코드 */
    @Schema(description = "REASONCODE")
    private String reasoncode;

    /** 사유메시지 */
    @Schema(description = "REASONMSG")
    private String reasonmsg;

    /** 사유코드2 */
    @Schema(description = "REASONCODE2")
    private String reasoncode2;

    /** 사유메시지2 */
    @Schema(description = "REASONMSG2")
    private String reasonmsg2;

    /** 시스템조정메시지 */
    @Schema(description = "SYSADJUSTMSG")
    private String sysadjustmsg;

    /** 수정가능여부 */
    @Schema(description = "MODIFYPOSSYN")
    private String modifypossyn;

    /** 처리가능여부 */
    @Schema(description = "PROCPOSSYN")
    private String procpossyn;

    /** 처리가능메시지 */
    @Schema(description = "PROCPOSSMSG")
    private String procpossmsg;

    /** 기타참조1 */
    @Schema(description = "OTHER01")
    private String other01;

    /** 기타참조2 */
    @Schema(description = "OTHER02")
    private String other02;

    /** 기타참조3 */
    @Schema(description = "OTHER03")
    private String other03;

    /** 기타참조4 */
    @Schema(description = "OTHER04")
    private String other04;

    /** 기타참조5 */
    @Schema(description = "OTHER05")
    private String other05;

    /** 기타참조6 */
    @Schema(description = "OTHER06")
    private String other06;

    /** 기타참조7 */
    @Schema(description = "OTHER07")
    private String other07;

    /** 기타참조8 */
    @Schema(description = "OTHER08")
    private String other08;

    /** 참조값1 */
    @Schema(description = "REFERENCE01")
    private String reference01;

    /** 참조값2 */
    @Schema(description = "REFERENCE02")
    private String reference02;

    /** 참조값3 */
    @Schema(description = "REFERENCE03")
    private String reference03;

    /** 참조값4 */
    @Schema(description = "REFERENCE04")
    private String reference04;

    /** 참조값5 */
    @Schema(description = "REFERENCE05")
    private String reference05;

    /** 참조값6 */
    @Schema(description = "REFERENCE06")
    private String reference06;

    /** 참조값7 */
    @Schema(description = "REFERENCE07")
    private String reference07;

    /** 참조값8 */
    @Schema(description = "REFERENCE08")
    private String reference08;

    /** 참조값9 */
    @Schema(description = "REFERENCE09")
    private String reference09;

    /** 참조값10 */
    @Schema(description = "REFERENCE10")
    private String reference10;

    /** 상태 */
    @Schema(description = "STATUS")
    private String status;

    /** 삭제여부 */
    @Schema(description = "DELYN")
    private String delyn;

    /** 교통담당자 */
    @Schema(description = "TRAFFICCOP")
    private String trafficcop;

    /** 보관담당자 */
    @Schema(description = "ARCHIVECOP")
    private String archivecop;

    /** 등록일시 */
    @Schema(description = "ADDDATE")
    private String adddate;

    /** 수정일시 */
    @Schema(description = "EDITDATE")
    private String editdate;

    /** 등록자 */
    @Schema(description = "ADDWHO")
    private String addwho;

    /** 수정자 */
    @Schema(description = "EDITWHO")
    private String editwho;
}
